package crypto

import (
	"bytes"
	"compress/gzip"
	"encoding/base64"
	"encoding/json"
	"fmt"
	"io"
)

// DecryptDllpgdRequest decrypts a dllpgd request body through the 5-layer pipeline (reverse).
// HTTP body → Base64 decode → AES-256-CFB decrypt (first 16 bytes = IV) → Base64 decode → GZIP decompress → JSON
func DecryptDllpgdRequest(body []byte, aesKey []byte) (json.RawMessage, error) {
	// Layer 5 → 4: Base64 decode
	aesEncrypted, err := base64.StdEncoding.DecodeString(string(body))
	if err != nil {
		return nil, fmt.Errorf("base64 decode (outer): %w", err)
	}

	// Layer 4 → 3: AES-256-CFB decrypt
	b64Gzipped, err := AESCFBDecrypt(aesEncrypted, aesKey)
	if err != nil {
		return nil, fmt.Errorf("AES decrypt: %w", err)
	}

	// Layer 3 → 2: Base64 decode
	gzipped, err := base64.StdEncoding.DecodeString(string(b64Gzipped))
	if err != nil {
		return nil, fmt.Errorf("base64 decode (inner): %w", err)
	}

	// Layer 2 → 1: GZIP decompress
	gz, err := gzip.NewReader(bytes.NewReader(gzipped))
	if err != nil {
		return nil, fmt.Errorf("gzip reader: %w", err)
	}
	defer gz.Close()

	jsonBytes, err := io.ReadAll(gz)
	if err != nil {
		return nil, fmt.Errorf("gzip decompress: %w", err)
	}

	return json.RawMessage(jsonBytes), nil
}

// EncryptDllpgdPayload encrypts a JSON payload through the 5-layer pipeline (forward).
// JSON → GZIP compress → Base64 encode → AES-256-CFB encrypt → Base64 encode
func EncryptDllpgdPayload(jsonData []byte, aesKey []byte) ([]byte, error) {
	// Layer 1 → 2: GZIP compress
	var gzBuf bytes.Buffer
	gzWriter := gzip.NewWriter(&gzBuf)
	if _, err := gzWriter.Write(jsonData); err != nil {
		return nil, fmt.Errorf("gzip compress: %w", err)
	}
	if err := gzWriter.Close(); err != nil {
		return nil, fmt.Errorf("gzip close: %w", err)
	}

	// Layer 2 → 3: Base64 encode
	b64Inner := base64.StdEncoding.EncodeToString(gzBuf.Bytes())

	// Layer 3 → 4: AES-256-CFB encrypt
	aesEncrypted, err := AESCFBEncrypt([]byte(b64Inner), aesKey)
	if err != nil {
		return nil, fmt.Errorf("AES encrypt: %w", err)
	}

	// Layer 4 → 5: Base64 encode
	b64Outer := base64.StdEncoding.EncodeToString(aesEncrypted)

	return []byte(b64Outer), nil
}

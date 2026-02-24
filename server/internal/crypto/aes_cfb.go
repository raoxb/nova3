package crypto

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/md5"
	"crypto/rand"
	"fmt"
	"io"
	"strings"
)

// DeriveAESKey derives the AES-256 key from a seed string.
// Process: MD5(seed) → uppercase hex → first 32 bytes as UTF-8 key.
// For seed "GreenDay": MD5 → "66987CE7134F63EF7EE6F5024AD312B3" (32 bytes).
func DeriveAESKey(seed string) []byte {
	hash := md5.Sum([]byte(seed))
	hexStr := strings.ToUpper(fmt.Sprintf("%x", hash))
	return []byte(hexStr[:32])
}

// AESCFBEncrypt encrypts plaintext using AES-256-CFB with a random 16-byte IV prepended.
// segment_size=128 (full block) matches Java's AES/CFB/NoPadding default.
func AESCFBEncrypt(plaintext, key []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	iv := make([]byte, aes.BlockSize)
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		return nil, err
	}

	ciphertext := make([]byte, aes.BlockSize+len(plaintext))
	copy(ciphertext[:aes.BlockSize], iv)

	stream := cipher.NewCFBEncrypter(block, iv)
	stream.XORKeyStream(ciphertext[aes.BlockSize:], plaintext)

	return ciphertext, nil
}

// AESCFBDecrypt decrypts ciphertext encrypted with AES-256-CFB.
// The first 16 bytes are the IV.
func AESCFBDecrypt(ciphertext, key []byte) ([]byte, error) {
	if len(ciphertext) < aes.BlockSize {
		return nil, fmt.Errorf("ciphertext too short: %d bytes", len(ciphertext))
	}

	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	iv := ciphertext[:aes.BlockSize]
	data := ciphertext[aes.BlockSize:]

	plaintext := make([]byte, len(data))
	stream := cipher.NewCFBDecrypter(block, iv)
	stream.XORKeyStream(plaintext, data)

	return plaintext, nil
}

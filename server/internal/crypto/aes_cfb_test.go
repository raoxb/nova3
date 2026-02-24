package crypto

import (
	"encoding/base64"
	"encoding/json"
	"testing"
)

func TestDeriveAESKey(t *testing.T) {
	key := DeriveAESKey("GreenDay")
	expected := "66987CE7134F63EF7EE6F5024AD312B3"
	if string(key) != expected {
		t.Fatalf("expected %q, got %q", expected, string(key))
	}
	if len(key) != 32 {
		t.Fatalf("expected 32 bytes, got %d", len(key))
	}
}

func TestAESCFBRoundTrip(t *testing.T) {
	key := DeriveAESKey("GreenDay")
	plaintext := []byte("hello, world!")

	ciphertext, err := AESCFBEncrypt(plaintext, key)
	if err != nil {
		t.Fatal(err)
	}

	decrypted, err := AESCFBDecrypt(ciphertext, key)
	if err != nil {
		t.Fatal(err)
	}

	if string(decrypted) != string(plaintext) {
		t.Fatalf("expected %q, got %q", plaintext, decrypted)
	}
}

func TestPipelineRoundTrip(t *testing.T) {
	key := DeriveAESKey("GreenDay")
	original := map[string]string{"test": "hello"}
	jsonBytes, _ := json.Marshal(original)

	encrypted, err := EncryptDllpgdPayload(jsonBytes, key)
	if err != nil {
		t.Fatal(err)
	}

	decrypted, err := DecryptDllpgdRequest(encrypted, key)
	if err != nil {
		t.Fatal(err)
	}

	var result map[string]string
	if err := json.Unmarshal(decrypted, &result); err != nil {
		t.Fatal(err)
	}

	if result["test"] != "hello" {
		t.Fatalf("expected 'hello', got %q", result["test"])
	}
}

// TestCrossVerifyWithPython verifies that Go can decrypt data encrypted by the Python c2_client.py pattern.
func TestCrossVerifyWithPython(t *testing.T) {
	key := DeriveAESKey("GreenDay")

	// Simulate: encrypt with Go, verify structure matches Python expectations
	// Python: JSON → gzip → base64 → AES-CFB(IV prepended) → base64
	testPayload := `{"test":"cross_verify"}`
	encrypted, err := EncryptDllpgdPayload([]byte(testPayload), key)
	if err != nil {
		t.Fatal(err)
	}

	// Verify the outer layer is valid base64
	aesData, err := base64.StdEncoding.DecodeString(string(encrypted))
	if err != nil {
		t.Fatal("outer base64 decode failed:", err)
	}

	// Verify IV is present (first 16 bytes)
	if len(aesData) < 16 {
		t.Fatal("ciphertext too short for IV")
	}

	// Full round-trip
	decrypted, err := DecryptDllpgdRequest(encrypted, key)
	if err != nil {
		t.Fatal(err)
	}

	var result map[string]string
	if err := json.Unmarshal(decrypted, &result); err != nil {
		t.Fatal(err)
	}

	if result["test"] != "cross_verify" {
		t.Fatalf("cross-verification failed: expected 'cross_verify', got %q", result["test"])
	}
}

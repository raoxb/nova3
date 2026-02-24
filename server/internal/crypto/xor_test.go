package crypto

import (
	"bytes"
	"testing"
)

func TestXORCryptSymmetric(t *testing.T) {
	data := []byte("hello, world!")
	key := []byte("secret")

	encrypted := XORCrypt(data, key)
	if bytes.Equal(encrypted, data) {
		t.Fatal("encrypted should differ from plaintext")
	}

	decrypted := XORCrypt(encrypted, key)
	if !bytes.Equal(decrypted, data) {
		t.Fatalf("expected %q, got %q", data, decrypted)
	}
}

func TestXORCryptEmptyKey(t *testing.T) {
	data := []byte("hello")
	result := XORCrypt(data, nil)
	if !bytes.Equal(result, data) {
		t.Fatal("empty key should return data unchanged")
	}
}

func TestXORCryptCycling(t *testing.T) {
	// Verify key cycling works correctly
	data := []byte{0x01, 0x02, 0x03, 0x04, 0x05}
	key := []byte{0xFF, 0x00}
	expected := []byte{0xFE, 0x02, 0xFC, 0x04, 0xFA}

	result := XORCrypt(data, key)
	if !bytes.Equal(result, expected) {
		t.Fatalf("expected %x, got %x", expected, result)
	}
}

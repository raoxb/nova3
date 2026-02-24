package crypto

// XORCrypt performs repeating-key XOR encryption/decryption (symmetric).
func XORCrypt(data, key []byte) []byte {
	if len(key) == 0 {
		return data
	}
	result := make([]byte, len(data))
	keyLen := len(key)
	for i := range data {
		result[i] = data[i] ^ key[i%keyLen]
	}
	return result
}

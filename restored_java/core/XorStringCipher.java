package core;

import java.nio.charset.StandardCharsets;

/**
 * MALWARE ANALYSIS -- XOR string cipher implementation
 *
 * Original: IllIIlIIII1.llllIIIIll1
 *
 * Implements {@link StringCipherInterface} using a simple XOR cipher.
 * Each byte of the data is XOR-ed with the corresponding byte of the
 * key, cycling through the key when it is shorter than the data.
 *
 * This is the primary string obfuscation mechanism used throughout the
 * malware. All encrypted log tags, API paths, and UI strings are
 * decrypted at runtime through this class (loaded via
 * {@link StringCipherLoader}).
 *
 * The XOR operation is symmetric: encrypt and decrypt are the same
 * operation, just with different input/output types.
 *
 * NOTE: The static {@code xorBytes} method modifies the input array
 * in-place and returns it (no copy is made).
 */
public final class XorStringCipher implements StringCipherInterface {

    // =========================================================================
    // Static XOR Helper
    // =========================================================================

    /**
     * XOR each byte of {@code data} with the cycling {@code key}.
     *
     * Original: lIIIIlllllIlll1(byte[], byte[]) -> byte[]
     *
     * WARNING: This method mutates the {@code data} array in-place.
     *
     * @param data the data bytes to XOR (modified in-place)
     * @param key  the key bytes (cycled if shorter than data)
     * @return the same {@code data} array after XOR transformation
     */
    public static byte[] xorBytes(byte[] data, byte[] key) {
        int dataLen = data.length;
        int keyLen = key.length;
        int keyIndex = 0;
        int dataIndex = 0;
        while (dataIndex < dataLen) {
            if (keyIndex >= keyLen) {
                keyIndex = 0;
            }
            data[dataIndex] = (byte) (data[dataIndex] ^ key[keyIndex]);
            dataIndex++;
            keyIndex++;
        }
        return data;
    }

    // =========================================================================
    // StringCipherInterface Implementation
    // =========================================================================

    /**
     * Always returns {@code true} -- this cipher is unconditionally available.
     *
     * Original: llllIIIIll1(String) -> boolean
     *
     * @param name ignored
     * @return always {@code true}
     */
    @Override
    public boolean isActive(String name) {
        return true;
    }

    /**
     * Encrypt a plaintext string by XOR-ing its UTF-8 bytes with the key.
     *
     * Original: llllIIIIll1(String, byte[]) -> byte[]
     *
     * @param plaintext the string to encrypt
     * @param key       the XOR key bytes
     * @return the XOR-encrypted byte array
     */
    @Override
    public byte[] encrypt(String plaintext, byte[] key) {
        return xorBytes(plaintext.getBytes(StandardCharsets.UTF_8), key);
    }

    /**
     * Decrypt XOR-encrypted bytes back to a UTF-8 string.
     *
     * Original: llllIIIIll1(byte[], byte[]) -> String
     *
     * @param encrypted the XOR-encrypted bytes
     * @param key       the XOR key bytes
     * @return the decrypted plaintext string
     */
    @Override
    public String decrypt(byte[] encrypted, byte[] key) {
        return new String(xorBytes(encrypted, key), StandardCharsets.UTF_8);
    }
}

package core;

/**
 * MALWARE ANALYSIS — String cipher implementation interface
 *
 * Original: lIIIIlllllIlll1.llllIllIl1
 *
 * Interface for XOR-based string cipher used throughout the malware.
 * Multiple implementations exist, loaded dynamically via class name.
 *
 * Methods:
 *   - decrypt(byte[], byte[]):   decrypt XOR-encoded bytes to string
 *   - encrypt(String, byte[]):   encrypt string to XOR-encoded bytes
 *   - isActive(String):          check if cipher is active/available
 */
public interface StringCipherInterface {

    /**
     * Decrypt XOR-encoded bytes using the key.
     * Original: llllIIIIll1(byte[], byte[]) -> String
     */
    String decrypt(byte[] encrypted, byte[] key);

    /**
     * Encrypt a string using the key.
     * Original: llllIIIIll1(String, byte[]) -> byte[]
     */
    byte[] encrypt(String plaintext, byte[] key);

    /**
     * Check if the cipher implementation is available.
     * Original: llllIIIIll1(String) -> boolean
     */
    boolean isActive(String name);
}

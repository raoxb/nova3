package core;

/**
 * MALWARE ANALYSIS — Encryption interface (single-arg)
 *
 * Original: lIIIIlllllIlll1.lIIIIlllllIlll1
 *
 * Simple encryption interface with a single-argument encrypt method.
 * Distinct from StringCipherInterface which takes a key parameter.
 */
public interface EncryptionInterface {

    /**
     * Encrypt/hash a string.
     * Original: llllIIIIll1(String) -> byte[]
     */
    byte[] encrypt(String input);
}

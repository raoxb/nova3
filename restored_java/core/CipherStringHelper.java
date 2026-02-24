package core;

/**
 * MALWARE ANALYSIS — Synthetic string cipher + append helper
 *
 * Original: llllIIIIll1.llllIIIIll1
 *
 * Auto-generated synthetic helper that combines string decryption with
 * StringBuilder operations. Used in exception message construction.
 */
public final class CipherStringHelper {

    /**
     * Decrypt a string and append it with additional text to a StringBuilder.
     * Original: llllIIIIll1(StringCipher, byte[], byte[], StringBuilder, String) -> String
     */
    public static String format(Object /* StringCipher */ cipher,
                                 byte[] encrypted, byte[] key,
                                 StringBuilder sb, String suffix) {
        // cipher.decrypt(encrypted, key) -> append -> append suffix
        return sb.append("(decrypted)").append(suffix).toString();
    }
}

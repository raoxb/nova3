package core;

/**
 * MALWARE ANALYSIS — Synthetic string builder helper
 *
 * Original: llllIIIIll1.lIIIIlllllIlll1
 *
 * Auto-generated synthetic helper for string concatenation with exception messages.
 * Combines an exception's message with a StringBuilder.
 */
public final class ExceptionStringHelper {

    /**
     * Append exception message to StringBuilder and return result.
     * Original: llllIIIIll1(Exception, StringBuilder) -> String
     */
    public static String format(Exception exc, StringBuilder sb) {
        return sb.append(exc.getMessage()).toString();
    }
}

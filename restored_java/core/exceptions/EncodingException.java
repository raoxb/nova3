package core.exceptions;

import java.io.UnsupportedEncodingException;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: IllIlIllll1/IllIIlIIII1
 *
 * Wraps an UnsupportedEncodingException as an unchecked RuntimeException.
 * Thrown when a required character encoding (typically UTF-8) is not
 * available on the platform, which should never happen in practice.
 *
 * Original obfuscated name: IllIlIllll1.IllIIlIIII1
 */
public class EncodingException extends RuntimeException {

    /**
     * Wraps the given UnsupportedEncodingException.
     *
     * @param cause the encoding exception to wrap; must not be null
     * @throws NullPointerException if cause is null
     */
    public EncodingException(UnsupportedEncodingException cause) {
        super(cause);
        if (cause == null) {
            throw new NullPointerException("cause must not be null");
        }
    }
}

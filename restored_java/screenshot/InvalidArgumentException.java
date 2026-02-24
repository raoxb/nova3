package screenshot;

/**
 * MALWARE ANALYSIS — Custom IllegalArgumentException
 *
 * Original: IIIlIllIlI1.llllIllIl1
 *
 * Simple wrapper around IllegalArgumentException used in the screenshot package.
 */
public class InvalidArgumentException extends IllegalArgumentException {

    public InvalidArgumentException(String message) {
        super(message);
    }
}

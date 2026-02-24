package core.exceptions;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: IllIlIllll1/IlIllIlllIllI1
 *
 * Thrown when a WebSocket protocol violation is detected (e.g. invalid frame
 * opcode, fragmentation error, missing mask). Fixed error code 1002 corresponds
 * to RFC 6455 "protocol error" close code.
 *
 * Original obfuscated name: IllIlIllll1.IlIllIlllIllI1
 */
public class WebSocketProtocolException extends WebSocketException {

    /** Fixed error code for protocol violations (RFC 6455 Section 7.4.1) */
    private static final int ERROR_CODE = 1002;

    public WebSocketProtocolException() {
        super(ERROR_CODE);
    }

    public WebSocketProtocolException(String message) {
        super(ERROR_CODE, message);
    }

    public WebSocketProtocolException(Throwable cause) {
        super(ERROR_CODE, cause);
    }

    public WebSocketProtocolException(String message, Throwable cause) {
        super(ERROR_CODE, message, cause);
    }
}

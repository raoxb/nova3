package core.exceptions;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: IllIlIllll1/llllIllIl1
 *
 * Base exception for all WebSocket errors. Carries an integer error code
 * identifying the specific failure condition. Used throughout the WebSocket
 * client/server implementation for connection, protocol, and I/O errors.
 *
 * Original obfuscated name: IllIlIllll1.llllIllIl1
 */
public class WebSocketException extends Exception {

    /** Numeric error code identifying the failure type */
    private final int errorCode;

    public WebSocketException(int errorCode) {
        this.errorCode = errorCode;
    }

    public WebSocketException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public WebSocketException(int errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public WebSocketException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /** Returns the numeric error code for this WebSocket failure. */
    public int getErrorCode() {
        return this.errorCode;
    }
}

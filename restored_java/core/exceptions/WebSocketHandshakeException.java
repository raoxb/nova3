package core.exceptions;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: IllIlIllll1/IlIlllIIlI1
 *
 * Thrown when the WebSocket opening handshake fails (e.g. bad HTTP upgrade
 * response, missing Sec-WebSocket-Accept header, invalid status code).
 * Uses fixed error code 1002.
 *
 * Original obfuscated name: IllIlIllll1.IlIlllIIlI1
 */
public class WebSocketHandshakeException extends WebSocketException {

    /** Fixed error code for handshake failures */
    private static final int ERROR_CODE = 1002;

    public WebSocketHandshakeException() {
        super(ERROR_CODE);
    }

    public WebSocketHandshakeException(String message) {
        super(ERROR_CODE, message);
    }

    public WebSocketHandshakeException(Throwable cause) {
        super(ERROR_CODE, cause);
    }

    public WebSocketHandshakeException(String message, Throwable cause) {
        super(ERROR_CODE, message, cause);
    }
}

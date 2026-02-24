package core.exceptions;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: IllIlIllll1/llllllIlIIIlll1
 *
 * Thrown when the WebSocket connection is closed, either by the remote peer
 * or locally. Extends WebSocketException with a fixed error code of 1009
 * and carries an additional close code from the WebSocket close frame.
 *
 * Original obfuscated name: IllIlIllll1.llllllIlIIIlll1
 */
public class WebSocketCloseException extends WebSocketException {

    /** Fixed base error code for close exceptions */
    private static final int ERROR_CODE = 1009;

    /** The WebSocket close code from the close frame (RFC 6455 Section 7.4) */
    private final int closeCode;

    public WebSocketCloseException() {
        super(ERROR_CODE);
        this.closeCode = 0;
    }

    public WebSocketCloseException(int closeCode) {
        super(ERROR_CODE);
        this.closeCode = closeCode;
    }

    public WebSocketCloseException(String message) {
        super(ERROR_CODE, message);
        this.closeCode = 0;
    }

    public WebSocketCloseException(String message, int closeCode) {
        super(ERROR_CODE, message);
        this.closeCode = closeCode;
    }

    /** Returns the WebSocket close code from the close frame. */
    public int getCloseCode() {
        return this.closeCode;
    }
}

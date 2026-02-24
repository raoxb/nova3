package core.exceptions;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: IllIlIllll1/llllIIIIll1
 *
 * Exception carrying a WebSocket status code (e.g. 1000 normal closure,
 * 1001 going away, etc.). Used when a specific WebSocket close code
 * needs to be propagated without the full WebSocketException hierarchy.
 *
 * Original obfuscated name: IllIlIllll1.llllIIIIll1
 */
public class WebSocketCodeException extends Exception {

    /** WebSocket status code (RFC 6455 Section 7.4) */
    private final int code;

    public WebSocketCodeException(int code) {
        this.code = code;
    }

    /** Returns the WebSocket status code. */
    public int getCode() {
        return this.code;
    }
}

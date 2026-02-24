package core.exceptions;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: IllIlIllll1/lIIIIlllllIlll1
 *
 * Unchecked exception carrying an HTTP status code, thrown when the WebSocket
 * upgrade request receives an unexpected HTTP response (e.g. 401, 403, 500).
 *
 * Original obfuscated name: IllIlIllll1.lIIIIlllllIlll1
 */
public class WebSocketStatusException extends RuntimeException {

    /** HTTP status code from the failed upgrade response */
    private final int statusCode;

    public WebSocketStatusException() {
        this.statusCode = 0;
    }

    public WebSocketStatusException(int statusCode) {
        this.statusCode = statusCode;
    }

    /** Returns the HTTP status code from the failed upgrade response. */
    public int getStatusCode() {
        return this.statusCode;
    }
}

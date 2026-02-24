package core;

/**
 * MALWARE ANALYSIS — WebSocket status exception
 *
 * Original: IIlIllIIll1.llllIIIIll1
 *
 * Exception thrown for WebSocket-level status errors (not HTTP).
 * Contains a status code, path, and message for identifying the error source.
 *
 * NOTE: This is distinct from the exceptions in IllIlIllll1 package.
 * This exception lives in the IIlIllIIll1 package and is used specifically
 * for signaling/connection status errors.
 *
 * Fields:
 *   - statusCode (int):   error status code
 *   - path (String):      the path/endpoint that failed
 *   - message (String):   error description
 */
public class WebSocketStatusError extends Exception {

    /** Error status code. Original: f65lIIIIlllllIlll1 */
    public final int statusCode;

    /** Error description. Original: f66llllIIIIll1 */
    public final String errorDescription;

    /** Error path/endpoint. Original: f67llllIllIl1 */
    public final String path;

    /**
     * Constructor.
     * Original: llllIIIIll1(String, int, String)
     */
    public WebSocketStatusError(String description, int statusCode, String path) {
        super("WebSocket error: " + description + " code: " + statusCode + " path: " + path);
        this.statusCode = statusCode;
        this.path = path;
        this.errorDescription = description;
    }

    /**
     * Constructor with cause.
     * Original: llllIIIIll1(String, int, String, Throwable)
     */
    public WebSocketStatusError(String description, int statusCode, String path, Throwable cause) {
        super("WebSocket error: " + description + " code: " + statusCode + " path: " + path, cause);
        this.statusCode = statusCode;
        this.path = path;
        this.errorDescription = description;
    }

    public int getStatusCode() { return statusCode; }
    public String getErrorDescription() { return errorDescription; }
    public String getPath() { return path; }
}

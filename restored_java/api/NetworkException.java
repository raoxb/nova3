package api;

/**
 * NetworkException — 网络异常
 *
 * Original: IlIllIlllIllI1.lIIIIlllllIlll1.llllIIIIll1
 *         (HttpClient inner class, also restored as HttpClient.HttpClientException)
 *
 * Thrown when an HTTP request fails at the network level (connection timeout,
 * I/O error, DNS failure, etc.) or when the server returns a non-2xx status.
 *
 * NOTE: This is the SAME class as HttpClient.HttpClientException.
 *       Both names refer to obfuscated class: IlIllIlllIllI1.lIIIIlllllIlll1.llllIIIIll1
 *       This standalone file exists because the TaskOrchestrator and ApiClient
 *       reference it as a top-level exception type in catch blocks.
 *
 * Fields (from HttpClient.HttpClientException):
 *   - f241llllIIIIll1 → statusCode (int, HTTP status code, 0 if not HTTP error)
 *   - f240lIIIIlllllIlll1 → responseBody (String, error response body)
 *
 * Constructors:
 *   - NetworkException(String message) — generic network error
 *   - NetworkException(String message, Throwable cause) — wrapping an I/O exception
 *   - NetworkException(String message, int statusCode, String responseBody) — HTTP error
 */
public class NetworkException extends Exception {

    /** HTTP status code (0 if the error is not HTTP-related, e.g., connection timeout) */
    public final int statusCode;          /* f241llllIIIIll1 */

    /** HTTP error response body (empty string if not available) */
    public final String responseBody;     /* f240lIIIIlllllIlll1 */

    /**
     * Creates a NetworkException for a generic network error.
     *
     * Original: llllIIIIll1(String str)
     */
    public NetworkException(String message) {
        super(message);
        this.statusCode = 0;
        this.responseBody = "";
    }

    /**
     * Creates a NetworkException wrapping an underlying I/O exception.
     *
     * Original: llllIIIIll1(String str, Throwable cause)
     * Typically wraps: "网络错误: " + IOException.getMessage()
     */
    public NetworkException(String message, Throwable cause) {
        super(message, cause);
        this.statusCode = 0;
        this.responseBody = "";
    }

    /**
     * Creates a NetworkException for an HTTP error response.
     *
     * Original: llllIIIIll1(String str, int statusCode, String responseBody)
     * Typically: "HTTP错误：" + statusCode
     */
    public NetworkException(String message, int statusCode, String responseBody) {
        super(message);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }
}

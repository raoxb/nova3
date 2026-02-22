package core;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/**
 * TaskConfig — SDK 运行配置
 *
 * Original: IlIlllIIlI1.llllIllIl1
 *
 * Holds the runtime configuration for the malware SDK instance.
 * Created during SDK initialization, stored in AppContext.taskConfig.
 *
 * Manages:
 *   - Application context reference
 *   - Signaling (WebRTC) mode flag and connection
 *   - Offer ID and Job ID from C&C signaling response
 *   - API key for endpoint URL construction
 *   - Main thread Handler for UI thread dispatch
 *   - SDK initialization lifecycle (context init, signaling check, task start)
 *
 * ┌──────────────────────────────────────────────────────────────┐
 * │ Field Mapping (obfuscated → restored)                        │
 * ├──────────────────────────────────────────────────────────────┤
 * │ f264IlIlIIlIII1      → signalingConnection                 │
 * │ f265IlIllIlllIllI1   → jobId                               │
 * │ f266IlIlllIIlI1      → offerId                             │
 * │ f267IllIIlIIII1       → isSignalingEnabled (from C&C)      │
 * │ f268lIIIIlllllIlll1   → isSignalingMode                    │
 * │ f269lIllIIIlIl1       → mainHandler                        │
 * │ f270llllIIIIll1        → context                            │
 * │ f271llllIllIl1          → processName                       │
 * │ f272llllllIlIIIlll1    → apiKey                             │
 * └──────────────────────────────────────────────────────────────┘
 *
 * Initialization flow (from llllIIIIll1(Context, String, Atom)):
 *   1. Set WebView data directory suffix for process isolation
 *   2. Sleep 500ms for WebView init
 *   3. Store context, set static references in AppContext
 *   4. Check signaling mode via IIlIllIIll1.IIlIllIIll1()
 *   5. Initialize logging and event reporting subsystems
 *   6. Initialize device fingerprint manager
 *   7. Check environment compatibility (WebRTC, CPU cores ≥ 4, API ≥ 33)
 *   8. Initialize signaling connection if eligible
 *   9. Check signaling plugin availability
 *   10. Create WebView and start first task
 *   11. Schedule 30-minute recurring task restart timer
 */
public class TaskConfig {

    /** Signaling (WebRTC) connection instance. Original: f264IlIlIIlIII1 */
    public Object /* SignalingConnection */ signalingConnection;

    /** Application context. Original: f270llllIIIIll1 */
    public Context context;

    /** Whether signaling mode is supported on this device. Original: f268lIIIIlllllIlll1 */
    public boolean isSignalingMode = false;

    /** Current process name. Original: f271llllIllIl1 */
    public String processName = "";

    /** Whether signaling is enabled by C&C response. Original: f267IllIIlIIII1 */
    public boolean isSignalingEnabled = false;

    /** Offer ID from C&C signaling check response. Original: f266IlIlllIIlI1 */
    public String offerId = "";

    /** Job ID from C&C signaling check response. Original: f265IlIllIlllIllI1 */
    public String jobId = "";

    /** API key / URL path component. Original: f272llllllIlIIIlll1 */
    public String apiKey = "";

    /** Main thread Handler for posting UI work. Original: f269lIllIIIlIl1 */
    public final Handler mainHandler = new Handler(Looper.getMainLooper());

    // =========================================================================
    // Getters
    // =========================================================================

    /**
     * Returns whether signaling is enabled by the C&C server response.
     * Original: IIlIllIIll1() → return f267IllIIlIIII1
     */
    public boolean isSignalingEnabled() {
        return this.isSignalingEnabled;
    }

    /**
     * Returns the Job ID assigned by the C&C signaling response.
     * Original: IlIllIlllIllI1() → return f265IlIllIlllIllI1
     */
    public String getJobId() {
        return this.jobId;
    }

    /**
     * Returns the signaling connection instance.
     * Original: IlIlllIIlI1() → return f264IlIlIIlIII1
     */
    public Object /* SignalingConnection */ getSignalingConnection() {
        return this.signalingConnection;
    }

    /**
     * Returns the application context.
     * Original: IllIIlIIII1() → return f270llllIIIIll1
     */
    public Context getContext() {
        return this.context;
    }

    /**
     * Returns whether the device supports signaling mode.
     * Original: lIllIIIlIl1() → return f268lIIIIlllllIlll1
     */
    public boolean isSignalingMode() {
        return this.isSignalingMode;
    }

    /**
     * Returns the offer ID from the C&C signaling check response.
     * Original: llllllIlIIIlll1() → return f266IlIlllIIlI1
     */
    public String getOfferId() {
        return this.offerId;
    }

    /**
     * Returns the API key / URL path component.
     * Original: llllIllIl1() → return f272llllllIlIIIlll1
     */
    public String getApiKey() {
        return this.apiKey;
    }
}

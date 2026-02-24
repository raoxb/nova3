package core;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.ScheduledExecutorService;

/**
 * MALWARE ANALYSIS — Device fingerprint singleton manager
 *
 * Original: llllllIlIIIlll1.llllIIIIll1
 *
 * Collects and stores device fingerprint information in an Atom protobuf object.
 * This is the central device-identity manager used throughout the malware:
 *
 *   - Generates or retrieves a Google Advertising ID (gaId) from SharedPreferences
 *   - Populates an Atom instance with:
 *       deviceId, timezone, locale, phoneTimestamp, androidVersion,
 *       phoneModel (Build.BRAND + " " + Build.MODEL), appPackageName,
 *       appVersion, gaId, appChannel, sessionId
 *   - Provides static accessors for the current Atom, appChannel, etc.
 *
 * Referenced from:
 *   - AppContext (IIlIllIIll1) — getDeviceId(), getAtom() delegate here
 *   - SignalingConnection — reads atom for device registration
 *   - WebViewAutomationBase — reads gaId for telemetry
 *
 * Fields:
 *   f738IlIlIIlIII1          → initialized (boolean)
 *   f739IlIllIlllIllI1       → scheduledExecutor (ScheduledExecutorService)
 *   f740IlIlllIIlI1          → GA_ID_PREF_KEY (encrypted String constant)
 *   f741IllIIlIIII1          → LOG_TAG_INIT (encrypted String constant)
 *   f742lIIIIlllllIlll1      → atom (Atom singleton)
 *   f743llllIIIIll1           → LOG_TAG (encrypted String constant)
 *   f744llllIllIl1            → channel (String, mutable)
 *   f745llllllIlIIIlll1      → KEEPALIVE_INTERVAL = 5000L
 */
public class DeviceFingerprintManager {

    /** Whether initialization has completed. Original: f738IlIlIIlIII1 */
    public static boolean initialized = false;

    /** Background scheduler (used for periodic tasks). Original: f739IlIllIlllIllI1 */
    public static ScheduledExecutorService scheduledExecutor = null;

    /** SharedPreferences key for Google Advertising ID. Original: f740IlIlllIIlI1 (encrypted) */
    public static final String GA_ID_PREF_KEY = "(encrypted:gaId_key)";

    /** Logging tag for init messages. Original: f741IllIIlIIII1 (encrypted) */
    public static final String LOG_TAG_INIT = "(encrypted:init_tag)";

    /** Primary log tag. Original: f743llllIIIIll1 (encrypted) */
    public static final String LOG_TAG = "(encrypted:DeviceFP)";

    /** Atom protobuf singleton — holds all device identity fields. Original: f742lIIIIlllllIlll1 */
    public static Object /* Atom */ atom = null; // new Atom()

    /** Current app channel string. Original: f744llllIllIl1 */
    public static String channel = "";

    /** Keep-alive interval in millis. Original: f745llllllIlIIIlll1 */
    public static final long KEEPALIVE_INTERVAL = 5000L;

    // Static initializer — decrypts string constants, creates default Atom
    static {
        // LOG_TAG = cipher.decrypt(...)
        // LOG_TAG_INIT = cipher.decrypt(...)
        // GA_ID_PREF_KEY = cipher.decrypt(...)
        // atom = new Atom();
        // channel = "";
        // initialized = false;
    }

    /**
     * Initialize with logging — logs INFO message then delegates to init().
     *
     * Original: lIIIIlllllIlll1(Context, String)
     *
     * @param context  application context
     * @param appChannel  channel identifier string
     */
    public static void initWithLog(Context context, String appChannel) {
        // Log.info(LOG_TAG, cipher.decrypt("Initializing..."))
        init(context, appChannel);
    }

    /**
     * Core initialization — collects device info and populates the Atom singleton.
     *
     * Original: llllIIIIll1(Context, String)
     *
     * Steps:
     * 1. Retrieve gaId from SharedPreferences (key = GA_ID_PREF_KEY)
     * 2. If not found, generate one via device ID helper (length 5)
     * 3. If still empty, use a fallback pattern "00000000-0000-0000-0000-0000" (encrypted)
     * 4. Save gaId to SharedPreferences
     * 5. Create a new Atom and populate:
     *    - deviceId from AppContext helper
     *    - DeviceInfo: timezone, locale, phoneTimestamp, androidVersion, phoneModel
     *    - version from AppContext (versionCode)
     *    - appPackageName from context.getPackageName()
     *    - appVersion from AppContext helper
     *    - gaId
     *    - appChannel
     * 6. On exception: create fallback Atom with deviceId = "unknown" (encrypted),
     *    set appPackageName only
     *
     * @param context  application context
     * @param appChannel  channel identifier string
     */
    public static void init(Context context, String appChannel) {
        try {
            // 1. Get gaId from SharedPreferences
            String gaId = null; // AppContext.getSharedPref(context, GA_ID_PREF_KEY)

            // 2. Generate if missing
            if (gaId == null || gaId.isEmpty()) {
                gaId = null; // DeviceIdHelper.generate(context, 5)
            }

            // 3. Fallback pattern
            if (gaId == null || gaId.isEmpty()) {
                gaId = "00000000-0000-0000-0000-000000000000"; // cipher.decrypt(fallback)
            }

            // 4. Save to SharedPreferences
            // SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
            // editor.putString(GA_ID_PREF_KEY, gaId);
            // editor.apply();

            // 5. Build Atom
            Object /* Atom */ newAtom = null; // new Atom();
            atom = newAtom;

            // atom.setDeviceId(AppContext.getInstallId(context));
            // atom.setDeviceInfo(new DeviceInfo());
            // atom.getDeviceInfo().setTimezone(TimeZone.getDefault().getID());
            // atom.getDeviceInfo().setLocale(Locale.getDefault().toString());
            // atom.getDeviceInfo().setPhoneTimestamp(System.currentTimeMillis());
            // atom.getDeviceInfo().setAndroidVersion(Build.VERSION.RELEASE);
            // atom.getDeviceInfo().setPhoneModel(Build.BRAND + " " + Build.MODEL);
            // atom.setVersion(AppContext.getVersionCode(context));
            // atom.setAppPackageName(context.getPackageName());
            // atom.setAppVersion(AppContext.getVersionName(context));
            // atom.setGaId(gaId);
            // atom.setAppChannel(appChannel);

            // Log.info(LOG_TAG, cipher.decrypt("init success..."))

        } catch (Exception e) {
            // Log.error(LOG_TAG, "init failed: " + e)

            // Fallback atom with minimal info
            // atom = new Atom();
            // atom.setDeviceId("unknown");    // cipher.decrypt(...)
            // atom.setAppPackageName(context.getPackageName());
        }
    }

    /**
     * Get current channel.
     * Original: llllIllIl1() -> String
     */
    public static String getChannel() {
        return channel;
    }

    /**
     * Get current app channel from atom.
     * Original: lIIIIlllllIlll1() -> String
     */
    public static String getAppChannel() {
        return null; // atom.getAppChannel()
    }

    /**
     * Set session ID on the atom.
     * Original: llllIIIIll1(String)
     *
     * @param sessionId  the session ID to set
     */
    public static void setSessionId(String sessionId) {
        if (sessionId == null || atom == null) {
            return;
        }
        // atom.setSessionId(sessionId);
    }

    /**
     * Get the current Atom, creating a default if null.
     * If sessionId is empty, generates one from the install ID.
     *
     * Original: llllIIIIll1() -> Atom
     *
     * @return the current Atom singleton (never null)
     */
    public static Object /* Atom */ getAtom() {
        Object /* Atom */ currentAtom = atom;
        if (currentAtom == null) {
            currentAtom = null; // new Atom();
        }
        // if (Objects.equals(currentAtom.getSessionId(), "")) {
        //     currentAtom.setSessionId(
        //         AppContext.getInstallId(Objects.requireNonNull(AppContext.appContext)));
        // }
        return currentAtom;
    }

    /**
     * Replace the Atom singleton (if non-null).
     * Original: llllIIIIll1(Atom)
     *
     * @param newAtom  the replacement Atom
     */
    public static void setAtom(Object /* Atom */ newAtom) {
        if (newAtom != null) {
            atom = newAtom;
        }
    }
}

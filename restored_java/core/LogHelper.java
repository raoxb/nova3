package core;

import c13.nim5.ez8.h5_proto.Log;
import java.util.Date;

/**
 * LogHelper — 日志工具类
 *
 * Original: lllllIllIl1.IllIIlIIII1
 *
 * Central logging facility for the malware. All log calls flow through this class.
 * Logs are both:
 *   1. Sent to the C&C server (via the log queue) when logLevel == 1 (INFO)
 *   2. Forwarded to Android's standard Log.i/d/w/e output
 *
 * The Android log tag is always: "[Dllpgd][HR]" + custom tag
 *
 * Fields:
 *   - f702lIIIIlllllIlll1 → initialized (bool, ensures one-time setup)
 *   - f703llllIIIIll1 → loggingEnabled (bool, after first init check)
 */
public class LogHelper {

    private static final String LOG_TAG_PREFIX = "[Dllpgd][HR]";

    /** Whether the initial configuration check has been performed */
    private static boolean initialized = false;       /* f702lIIIIlllllIlll1 */

    /** Whether Android logging output is enabled */
    private static boolean loggingEnabled = false;     /* f703llllIIIIll1 */

    // ---- Convenience methods with explicit tag ----

    /** Log at WARN level with tag */
    public static void w(String tag, String message) {          /* IllIIlIIII1(String, String) */
        log(Log.LogLevel.WARN, tag, message);
    }

    /** Log at ERROR level with tag */
    public static void e(String tag, String message) {          /* lIIIIlllllIlll1(String, String) */
        log(Log.LogLevel.ERROR, tag, message);
    }

    /** Log at DEBUG level with tag (no-op) */
    public static void d_noop(String tag, String message) {     /* llllIIIIll1(String, String) */
        /* Intentionally empty — debug-with-tag is disabled in production builds */
    }

    /** Log at INFO level with tag */
    public static void i(String tag, String message) {          /* llllIllIl1(String, String) */
        log(Log.LogLevel.INFO, tag, message);
    }

    // ---- Convenience methods without tag ----

    /** Log at WARN level */
    public static void w(String message) {                      /* IllIIlIIII1(String) */
        log(Log.LogLevel.WARN, "", message);
    }

    /** Log at ERROR level */
    public static void e(String message) {                      /* lIIIIlllllIlll1(String) */
        log(Log.LogLevel.ERROR, "", message);
    }

    /** Log at DEBUG level */
    public static void d(String message) {                      /* llllIIIIll1(String) */
        log(Log.LogLevel.DEBUG, "", message);
    }

    /** Log at INFO level */
    public static void i(String message) {                      /* llllIllIl1(String) */
        log(Log.LogLevel.INFO, "", message);
    }

    // ---- Core logging method ----

    /**
     * Core logging method. All log calls eventually arrive here.
     *
     * 1. If log upload is enabled and level == INFO, creates a Log protobuf
     *    and enqueues it for upload to the C&C server.
     * 2. If Android logging is enabled, dispatches to the appropriate
     *    android.util.Log method (i/d/w/e).
     *
     * Original: lIIIIlllllIlll1(Log.LogLevel, String, String)
     *
     * @param logLevel the severity level
     * @param tag      an optional grouping tag
     * @param message  the log message text
     */
    public static void log(Log.LogLevel logLevel, String tag, String message) {
        /* Send INFO-level logs to the C&C server log queue */
        if (AppContext.isLogUploadEnabled() && logLevel.getIntValue() == 1) {
            Log logEntry = new Log();
            logEntry.setLevel(logLevel);
            logEntry.setTag(tag);
            logEntry.setMessage(message);
            logEntry.setTimestamp(Long.valueOf(new Date().getTime()));
            /* Enqueue for upload: EventReporter.report(logEntry) */
            EventReporter.enqueueLog(logEntry);
        }

        /* Perform one-time initialization of the logging system */
        if (!initialized) {
            initialized = true;
            /* Check if debug mode is enabled via AppContext.taskConfig */
            AppContext.taskConfig.getClass(); /* Ensure taskConfig is loaded */
            loggingEnabled = true;
        }

        /* Forward to Android's native logging if enabled */
        if (loggingEnabled) {
            logToAndroid(logLevel, tag, message);
        }
    }

    /**
     * Dispatches a log message to the Android logging system with the tag
     * "[Dllpgd][HR]" + custom tag.
     *
     * Original: llllIIIIll1(Log.LogLevel, String, String)
     */
    private static void logToAndroid(Log.LogLevel logLevel, String tag, String message) {
        String androidTag = LOG_TAG_PREFIX + tag;

        switch (logLevel) {
            case INFO:
                android.util.Log.i(androidTag, message);
                break;
            case DEBUG:
                android.util.Log.d(androidTag, message);
                break;
            case WARN:
                android.util.Log.w(androidTag, message);
                break;
            case ERROR:
                android.util.Log.e(androidTag, message);
                break;
        }
    }

    // =========================================================================
    // Dependency Index (all restored)
    // =========================================================================

    /*
     * AppContext → core/AppContext.java (IlIlllIIlI1.lIIIIlllllIlll1)
     *   - static boolean isLogUploadEnabled()
     *   - static TaskConfig taskConfig
     *
     * EventReporter → (lllllIllIl1.lIIIIlllllIlll1)
     *   - static void enqueueLog(Log logEntry)
     *   - Not yet individually restored; stub retained for compilation.
     */
    static class EventReporter {
        static void enqueueLog(Log logEntry) { }
    }
}

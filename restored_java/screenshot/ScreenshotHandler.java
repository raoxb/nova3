package screenshot;

import android.os.Handler;
import android.os.Looper;

/**
 * MALWARE ANALYSIS — Thread/Handler utility for screenshot system
 *
 * Original: IIIlIllIlI1.lIIIIlllllIlll1
 *
 * Provides utility methods for posting runnables to the main thread
 * and creating background/daemon threads.
 *
 * Methods:
 *   - postToMainThread(Runnable): post to main looper Handler
 *   - runOnNewThread(Runnable):   new non-daemon thread
 *   - runOnDaemonThread(Runnable): new daemon thread
 */
public class ScreenshotHandler {

    /** Main thread handler. Original: f17llllIIIIll1 */
    public static Handler mainHandler = new Handler(Looper.getMainLooper());

    /**
     * Post a runnable to the main (UI) thread.
     * Original: lIIIIlllllIlll1(Runnable)
     */
    public static void postToMainThread(Runnable runnable) {
        if (mainHandler != null) {
            mainHandler.post(runnable);
        }
    }

    /**
     * Run on a new thread.
     * Original: llllIIIIll1(Runnable)
     */
    public static void runOnNewThread(Runnable runnable) {
        new Thread(runnable).start();
    }

    /**
     * Run on a new daemon thread.
     * Original: llllIllIl1(Runnable)
     */
    public static void runOnDaemonThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
    }
}

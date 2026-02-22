package llIIllIl1;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public abstract class llllIIIIll1 extends llllllIlIIIlll1 {

    /* renamed from: IlIllll1, reason: collision with root package name */
    public static final String f656IlIllll1 = "AbstractWebSocket";

    /* renamed from: lllllIllIl1, reason: collision with root package name */
    public static int f657lllllIllIl1 = 65536;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public ScheduledFuture<?> f661IlIlllIIlI1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public ScheduledExecutorService f662IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public boolean f663lIIIIlllllIlll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public boolean f665llllIllIl1;

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public long f660IlIllIlllIllI1 = TimeUnit.SECONDS.toNanos(60);

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public boolean f666llllllIlIIIlll1 = false;

    /* renamed from: IlIlIIlIII1, reason: collision with root package name */
    public boolean f659IlIlIIlIII1 = false;

    /* renamed from: lIllIIIlIl1, reason: collision with root package name */
    public final Object f664lIllIIIlIl1 = new Object();

    /* renamed from: IIlIllIIll1, reason: collision with root package name */
    public int f658IIlIllIIll1 = 0;

    /* renamed from: llIIllIl1.llllIIIIll1$llllIIIIll1, reason: collision with other inner class name */
    public class RunnableC0021llllIIIIll1 implements Runnable {

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public ArrayList<IlIllIlllIllI1> f668llllIIIIll1 = new ArrayList<>();

        public RunnableC0021llllIIIIll1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            long nanoTime;
            this.f668llllIIIIll1.clear();
            try {
                this.f668llllIIIIll1.addAll(llllIIIIll1.this.IIIlIllIlI1());
                synchronized (llllIIIIll1.this.f664lIllIIIlIl1) {
                    nanoTime = (long) (System.nanoTime() - (llllIIIIll1.this.f660IlIllIlllIllI1 * 1.5d));
                }
                Iterator<IlIllIlllIllI1> it = this.f668llllIIIIll1.iterator();
                while (it.hasNext()) {
                    llllIIIIll1.this.llllIIIIll1(it.next(), nanoTime);
                }
            } catch (Exception unused) {
            }
            this.f668llllIIIIll1.clear();
        }
    }

    public abstract Collection<IlIllIlllIllI1> IIIlIllIlI1();

    public int IlIlIIIlIlIlll1() {
        int seconds;
        synchronized (this.f664lIllIIIlIl1) {
            seconds = (int) TimeUnit.NANOSECONDS.toSeconds(this.f660IlIllIlllIllI1);
        }
        return seconds;
    }

    public final void IllIlIllll1() {
        lIllIlIll1();
        this.f662IllIIlIIII1 = Executors.newSingleThreadScheduledExecutor(new IlIIIlIlIlIII1.IllIIlIIII1("WebSocketConnectionLostChecker", this.f659IlIlIIlIII1));
        RunnableC0021llllIIIIll1 runnableC0021llllIIIIll1 = new RunnableC0021llllIIIIll1();
        ScheduledExecutorService scheduledExecutorService = this.f662IllIIlIIII1;
        long j = this.f660IlIllIlllIllI1;
        this.f661IlIlllIIlI1 = scheduledExecutorService.scheduleAtFixedRate(runnableC0021llllIIIIll1, j, j, TimeUnit.NANOSECONDS);
    }

    public void IllllIllllll1() {
        synchronized (this.f664lIllIIIlIl1) {
            if (this.f662IllIIlIIII1 != null || this.f661IlIlllIIlI1 != null) {
                this.f666llllllIlIIIlll1 = false;
                Log.v(f656IlIllll1, "Connection lost timer stopped");
                lIllIlIll1();
            }
        }
    }

    public boolean lIIlIIIIlIlII1() {
        return this.f663lIIIIlllllIlll1;
    }

    public void lIIlllIIIlllII1() {
        synchronized (this.f664lIllIIIlIl1) {
            if (this.f660IlIllIlllIllI1 <= 0) {
                Log.v(f656IlIllll1, "Connection lost timer deactivated");
                return;
            }
            Log.v(f656IlIllll1, "Connection lost timer started");
            this.f666llllllIlIIIlll1 = true;
            IllIlIllll1();
        }
    }

    public final void lIllIlIll1() {
        ScheduledExecutorService scheduledExecutorService = this.f662IllIIlIIII1;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.f662IllIIlIIII1 = null;
        }
        ScheduledFuture<?> scheduledFuture = this.f661IlIlllIIlI1;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            this.f661IlIlllIIlI1 = null;
        }
    }

    public boolean lIlllIIIII1() {
        return this.f665llllIllIl1;
    }

    public int llIIllIl1() {
        return this.f658IIlIllIIll1;
    }

    public boolean lllIlIIIlI1() {
        return this.f659IlIlIIlIII1;
    }

    public void llllIllIl1(boolean z) {
        this.f663lIIIIlllllIlll1 = z;
    }

    public void lIIIIlllllIlll1(int i) {
        synchronized (this.f664lIllIIIlIl1) {
            long nanos = TimeUnit.SECONDS.toNanos(i);
            this.f660IlIllIlllIllI1 = nanos;
            if (nanos <= 0) {
                Log.v(f656IlIllll1, "Connection lost timer stopped");
                lIllIlIll1();
                return;
            }
            if (this.f666llllllIlIIIlll1) {
                Log.v(f656IlIllll1, "Connection lost timer restarted");
                try {
                    Iterator it = new ArrayList(IIIlIllIlI1()).iterator();
                    while (it.hasNext()) {
                        IlIllIlllIllI1 ilIllIlllIllI1 = (IlIllIlllIllI1) it.next();
                        if (ilIllIlllIllI1 instanceof lIllIIIlIl1) {
                            ((lIllIIIlIl1) ilIllIlllIllI1).IllIlIllll1();
                        }
                    }
                } catch (Exception e) {
                    Log.e(f656IlIllll1, "Exception during connection lost restart", e);
                }
                IllIlIllll1();
            }
        }
    }

    public void llllIllIl1(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("buffer size < 0");
        }
        this.f658IIlIllIIll1 = i;
    }

    public final void llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1, long j) {
        if (ilIllIlllIllI1 instanceof lIllIIIlIl1) {
            lIllIIIlIl1 lilliiilil1 = (lIllIIIlIl1) ilIllIlllIllI1;
            if (lilliiilil1.llIIllIl1() < j) {
                Log.v(f656IlIllll1, "Closing connection due to no pong received: " + lilliiilil1);
                lilliiilil1.lIIIIlllllIlll1(1006, "The connection was closed because the other endpoint did not respond with a pong in time. For more information check: https://github.com/TooTallNate/Java-WebSocket/wiki/Lost-connection-detection");
            } else if (lilliiilil1.isOpen()) {
                lilliiilil1.llllIllIl1();
            } else {
                Log.v(f656IlIllll1, "Trying to ping a non open connection: " + lilliiilil1);
            }
        }
    }

    public void llllIIIIll1(boolean z) {
        this.f659IlIlIIlIII1 = z;
    }

    public void lIIIIlllllIlll1(boolean z) {
        this.f665llllIllIl1 = z;
    }
}

package lllllIllIl1;

import android.content.Context;
import c13.nim5.ez8.h5_proto.Log;
import c13.nim5.ez8.h5_proto.UpdateLogRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class llllIllIl1 {

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public static final llllIllIl1 f729IlIllIlllIllI1 = new llllIllIl1();

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public boolean f733llllIIIIll1 = false;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final ConcurrentLinkedQueue<Log> f732lIIIIlllllIlll1 = new ConcurrentLinkedQueue<>();

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final ScheduledExecutorService f734llllIllIl1 = Executors.newScheduledThreadPool(1);

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public final ExecutorService f731IllIIlIIII1 = Executors.newSingleThreadExecutor();

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public volatile boolean f730IlIlllIIlI1 = true;

    public class lIIIIlllllIlll1 implements Runnable {

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ List f736llllIIIIll1;

        public lIIIIlllllIlll1(List list) {
            this.f736llllIIIIll1 = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                UpdateLogRequest updateLogRequest = new UpdateLogRequest();
                updateLogRequest.setAtom(IlIlllIIlI1.lIIIIlllllIlll1.lIIIIlllllIlll1());
                updateLogRequest.setLog(this.f736llllIIIIll1);
            } catch (Exception unused) {
                llllIllIl1.this.f732lIIIIlllllIlll1.addAll(this.f736llllIIIIll1);
            } catch (Throwable unused2) {
                llllIllIl1.this.f732lIIIIlllllIlll1.clear();
            }
        }
    }

    public class llllIIIIll1 implements Runnable {
        public llllIIIIll1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            llllIllIl1.this.lIIIIlllllIlll1();
        }
    }

    public final void lIIIIlllllIlll1() {
        if (this.f732lIIIIlllllIlll1.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        while (!this.f732lIIIIlllllIlll1.isEmpty()) {
            Log poll = this.f732lIIIIlllllIlll1.poll();
            if (poll != null) {
                arrayList.add(poll);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        llllIIIIll1(arrayList);
    }

    public void llllIIIIll1(Context context) {
        if (this.f733llllIIIIll1) {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            android.util.Log.w(lllliiiill1.llllIIIIll1(new byte[]{70, -5, 95, 69, -34, -12, -69, 3, 126, -15, 74}, new byte[]{10, -108, 56, 23, -69, -124, -44, 113}), lllliiiill1.llllIIIIll1(new byte[]{-30, 104, 16, 18, -119, 55, -12, -45, -38, 98, 5, 96, -115, 43, -23, -60, -49, 99, 14, 96, -123, 41, -14, -43, -57, 102, 27, 41, -106, 34, -1}, new byte[]{-82, 7, 119, 64, -20, 71, -101, -95}));
        } else {
            this.f733llllIIIIll1 = true;
            this.f734llllIllIl1.scheduleWithFixedDelay(new llllIIIIll1(), 0L, 30L, TimeUnit.SECONDS);
        }
    }

    public void llllIIIIll1(Log log) {
        this.f732lIIIIlllllIlll1.add(log);
    }

    public final void llllIIIIll1(List<Log> list) {
        new Thread(new lIIIIlllllIlll1(list)).start();
    }

    public void llllIIIIll1() {
        this.f734llllIllIl1.shutdown();
        this.f730IlIlllIIlI1 = false;
        this.f731IllIIlIIII1.shutdown();
        try {
            if (this.f731IllIIlIIII1.awaitTermination(3L, TimeUnit.SECONDS)) {
                return;
            }
            this.f731IllIIlIIII1.shutdownNow();
        } catch (InterruptedException unused) {
            this.f731IllIIlIIII1.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

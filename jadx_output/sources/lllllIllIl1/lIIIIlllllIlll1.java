package lllllIllIl1;

import android.content.Context;
import android.util.Log;
import c13.nim5.ez8.h5_proto.Event;
import c13.nim5.ez8.h5_proto.UpdateEventRequest;
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
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes.dex */
public class lIIIIlllllIlll1 {

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public static final lIIIIlllllIlll1 f705llllllIlIIIlll1 = new lIIIIlllllIlll1();

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public File f710llllIIIIll1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final ConcurrentLinkedQueue<Event> f709lIIIIlllllIlll1 = new ConcurrentLinkedQueue<>();

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final BlockingQueue<Event> f711llllIllIl1 = new LinkedBlockingQueue();

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public final ScheduledExecutorService f708IllIIlIIII1 = Executors.newScheduledThreadPool(1);

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public final ExecutorService f707IlIlllIIlI1 = Executors.newSingleThreadExecutor();

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public volatile boolean f706IlIllIlllIllI1 = true;

    /* renamed from: lllllIllIl1.lIIIIlllllIlll1$lIIIIlllllIlll1, reason: collision with other inner class name */
    public class RunnableC0023lIIIIlllllIlll1 implements Runnable {

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ List f713llllIIIIll1;

        public RunnableC0023lIIIIlllllIlll1(List list) {
            this.f713llllIIIIll1 = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                UpdateEventRequest updateEventRequest = new UpdateEventRequest();
                updateEventRequest.setAtom(IlIlllIIlI1.lIIIIlllllIlll1.lIIIIlllllIlll1());
                updateEventRequest.setEvents(this.f713llllIIIIll1);
                lIIIIlllllIlll1.this.llllIIIIll1();
            } catch (Exception unused) {
                lIIIIlllllIlll1.this.f709lIIIIlllllIlll1.addAll(this.f713llllIIIIll1);
            } catch (Throwable unused2) {
                lIIIIlllllIlll1.this.f709lIIIIlllllIlll1.clear();
            }
        }
    }

    public class llllIIIIll1 implements Runnable {
        public llllIIIIll1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            lIIIIlllllIlll1.this.llllIllIl1();
        }
    }

    public void lIIIIlllllIlll1() {
        this.f708IllIIlIIII1.shutdown();
        this.f706IlIllIlllIllI1 = false;
        this.f707IlIlllIIlI1.shutdown();
        try {
            if (this.f707IlIlllIIlI1.awaitTermination(3L, TimeUnit.SECONDS)) {
                return;
            }
            this.f707IlIlllIIlI1.shutdownNow();
        } catch (InterruptedException unused) {
            this.f707IlIlllIIlI1.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public void llllIIIIll1(Context context) {
        this.f710llllIIIIll1 = new File(context.getFilesDir(), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{5, -24, 51, 100, -14, -9, -2, 66, 27, -72, 62, ByteCompanionObject.MAX_VALUE}, new byte[]{109, -35, 80, 11, ByteCompanionObject.MIN_VALUE, -110, -48, 39}));
        this.f708IllIIlIIII1.scheduleWithFixedDelay(new llllIIIIll1(), 0L, 10L, TimeUnit.SECONDS);
    }

    public final void llllIllIl1() {
        if (this.f709lIIIIlllllIlll1.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        while (!this.f709lIIIIlllllIlll1.isEmpty()) {
            Event poll = this.f709lIIIIlllllIlll1.poll();
            if (poll != null) {
                arrayList.add(poll);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        llllIIIIll1(arrayList);
    }

    public final synchronized void llllIIIIll1() {
        String parent = this.f710llllIIIIll1.getParent();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        File file = new File(parent, lllliiiill1.llllIIIIll1(new byte[]{61, 7, 68, 46, -8, -35, 38, 37, 57, 18, 74, 53, -4, ByteCompanionObject.MIN_VALUE, 13, 63, 44}, new byte[]{88, 113, 33, 64, -116, -82, 121, 71}));
        if (this.f710llllIIIIll1.exists() && this.f710llllIIIIll1.renameTo(file)) {
            Log.d(lllliiiill1.llllIIIIll1(new byte[]{-14, 2, 74, 0, -55, -100, 94, 50, -40, 6, 91, 11, -49}, new byte[]{-73, 116, 47, 110, -67, -50, 59, 66}), lllliiiill1.llllIIIIll1(new byte[]{45, 58, 104, 22, 2, -6, -38, 97, 65, 52, 125, 85, 12, -6, -64, 97, 5, 117, 124, 67, 7, -16, -45, 119, 18, 51, 122, 90, 8, -22, -104}, new byte[]{97, 85, 15, 54, 100, -109, -74, 4}));
        } else {
            Log.e(lllliiiill1.llllIIIIll1(new byte[]{-39, 33, 106, -118, -125, 112, -12, 4, -13, 37, 123, -127, -123}, new byte[]{-100, 87, 15, -28, -9, 34, -111, 116}), lllliiiill1.llllIIIIll1(new byte[]{124, 55, ByteCompanionObject.MIN_VALUE, -69, -119, -65, 100, -99, 85, 118, -120, -91, -113, -77, 45, -97, 95, 118, -116, -95, -119, -75, 48, -55, 92, 63, -123, -78, -62}, new byte[]{58, 86, -23, -41, -20, -37, 68, -23}));
        }
    }

    public void llllIIIIll1(String str, String str2) {
        Event event = new Event();
        event.setName(str);
        event.setDesc(str2);
        event.setTimestamp(Long.valueOf(new Date().getTime()));
        this.f709lIIIIlllllIlll1.add(event);
        this.f711llllIllIl1.offer(event);
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        Log.d(lllliiiill1.llllIIIIll1(new byte[]{23, 44, -54, -7, 7, -25, 47, 25, 61, 40, -37, -14, 1}, new byte[]{82, 90, -81, -105, 115, -75, 74, 105}), lllliiiill1.llllIIIIll1(new byte[]{-14, -77, 51, -57, -58, 101, 21, 102, -38, -4, 32, -120, -121, 108, 20, 110, -47, -82, 45, -57, -42, 116, 20, 118, -37, -4, 53, -119, -61, 33, 6, 113, -41, -88, 49, -57, -42, 116, 20, 118, -37}, new byte[]{-66, -36, 84, -25, -89, 1, 113, 3}));
    }

    public final void llllIIIIll1(List<Event> list) {
        new Thread(new RunnableC0023lIIIIlllllIlll1(list)).start();
    }
}

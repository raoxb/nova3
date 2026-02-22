package IlIlllIIlI1;

import android.content.Context;
import c13.nim5.ez8.h5_proto.Atom;
import c13.nim5.ez8.h5_proto.Log;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes.dex */
public class lIIIIlllllIlll1 {

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public static String f246IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public static final String f247lIIIIlllllIlll1 = IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{18, -5, 28, 41, 81, 96, -70, 3, 19, -11, 5, 62}, new byte[]{118, -105, 112, 89, 53, 7, -27, 103});

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public static llllIllIl1 f248llllIIIIll1 = new llllIllIl1();

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public static int f249llllIllIl1 = -1;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public static String f245IlIlllIIlI1 = "";

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public static String f244IlIllIlllIllI1 = "";

    /* renamed from: IlIlllIIlI1.lIIIIlllllIlll1$lIIIIlllllIlll1, reason: collision with other inner class name */
    public class RunnableC0008lIIIIlllllIlll1 implements Runnable {

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public final /* synthetic */ CountDownLatch f250lIIIIlllllIlll1;

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ Runnable f251llllIIIIll1;

        public RunnableC0008lIIIIlllllIlll1(Runnable runnable, CountDownLatch countDownLatch) {
            this.f251llllIIIIll1 = runnable;
            this.f250lIIIIlllllIlll1 = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f251llllIIIIll1.run();
            } finally {
                try {
                } finally {
                }
            }
        }
    }

    public class llllIIIIll1 implements Runnable {

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ Runnable f252llllIIIIll1;

        public llllIIIIll1(Runnable runnable) {
            this.f252llllIIIIll1 = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f252llllIIIIll1.run();
            } catch (Throwable th) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{ByteCompanionObject.MAX_VALUE, 84, -25, -80, 118, 28, 58, 111, 101, 83, -20, -98, 124, 105, 54, 73, ByteCompanionObject.MAX_VALUE, 78, -5, -33, 96, 115, 115}, new byte[]{13, 33, -119, -1, 24, 73, 83, 59}) + th);
            }
        }
    }

    public static boolean IlIlIIlIII1() {
        return f248llllIIIIll1.lIllIIIlIl1();
    }

    public static llllIllIl1 IlIllIlllIllI1() {
        return f248llllIIIIll1;
    }

    public static String IlIlllIIlI1() {
        return f245IlIlllIIlI1;
    }

    public static String IllIIlIIII1() {
        return llllllIlIIIlll1.llllIIIIll1.llllIIIIll1().getDeviceId();
    }

    public static Atom lIIIIlllllIlll1() {
        return llllllIlIIIlll1.llllIIIIll1.llllIIIIll1();
    }

    public static String llllIIIIll1() {
        if (f248llllIIIIll1.llllIllIl1().isEmpty()) {
            return f246IllIIlIIII1;
        }
        return f246IllIIlIIII1 + IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{24}, new byte[]{71, 11, 70, -68, 110, -121, 69, 101}) + f248llllIIIIll1.llllIllIl1();
    }

    public static Context llllIllIl1() {
        return f248llllIIIIll1.IllIIlIIII1();
    }

    public static String llllllIlIIIlll1() {
        return f246IllIIlIIII1;
    }

    public static void lIIIIlllllIlll1(Runnable runnable) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        f248llllIIIIll1.f269lIllIIIlIl1.post(new RunnableC0008lIIIIlllllIlll1(runnable, countDownLatch));
        try {
            countDownLatch.await(5L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    public static void llllIIIIll1(String str) {
        f245IlIlllIIlI1 = str;
    }

    public static void llllIIIIll1(llllIllIl1 llllillil1) {
        f248llllIIIIll1 = llllillil1;
        f246IllIIlIIII1 = llllillil1.IllIIlIIII1().getPackageName();
    }

    public static void llllIIIIll1(Runnable runnable) {
        f248llllIIIIll1.f269lIllIIIlIl1.post(new llllIIIIll1(runnable));
    }
}

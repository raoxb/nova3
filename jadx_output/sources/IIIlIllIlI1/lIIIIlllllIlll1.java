package IIIlIllIlI1;

import android.os.Handler;
import android.os.Looper;
import c13.nim5.ez8.h5_proto.Log;

/* loaded from: classes.dex */
public class lIIIIlllllIlll1 {

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public static Handler f17llllIIIIll1 = new Handler(Looper.getMainLooper());

    public static void lIIIIlllllIlll1(Runnable runnable) {
        Handler handler = f17llllIIIIll1;
        if (handler != null) {
            handler.post(runnable);
            return;
        }
        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-101, -86, -12, -87, -121, 59, -48, 93, -85, -111, -12, -113, -119, 33, -108, 66, -85, -105}, new byte[]{-50, -29, -44, -31, -26, 85, -76, 49}));
    }

    public static void llllIIIIll1(Runnable runnable) {
        new Thread(runnable).start();
    }

    public static void llllIllIl1(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
    }
}

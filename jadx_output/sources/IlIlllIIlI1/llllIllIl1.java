package IlIlllIIlI1;

import IlIlIIIlIlIlll1.IIlIllIIll1;
import IlIlIIIlIlIlll1.IlIlIIlIII1;
import IlIlIIlIII1.lIIIIlllllIlll1;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.webkit.WebView;
import c13.nim5.ez8.h5_proto.Atom;
import c13.nim5.ez8.h5_proto.Log;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Pattern;
import kotlin.jvm.internal.ByteCompanionObject;
import lIllIlIll1.IlIlllIIlI1;

/* loaded from: classes.dex */
public class llllIllIl1 {

    /* renamed from: IlIlIIlIII1, reason: collision with root package name */
    public IlIlIIlIII1.lIIIIlllllIlll1 f264IlIlIIlIII1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public Context f270llllIIIIll1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public boolean f268lIIIIlllllIlll1 = false;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public String f271llllIllIl1 = "";

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public boolean f267IllIIlIIII1 = false;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public String f266IlIlllIIlI1 = "";

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public String f265IlIllIlllIllI1 = "";

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public String f272llllllIlIIIlll1 = "";

    /* renamed from: lIllIIIlIl1, reason: collision with root package name */
    public final Handler f269lIllIIIlIl1 = new Handler(Looper.getMainLooper());

    public class IllIIlIIII1 implements lIIIIlllllIlll1.IllIIlIIII1 {

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ CountDownLatch f274llllIIIIll1;

        public IllIIlIIII1(CountDownLatch countDownLatch) {
            this.f274llllIIIIll1 = countDownLatch;
        }

        /* JADX WARN: Code restructure failed: missing block: B:18:0x00c7, code lost:
        
            r6 = r6.getMessage();
         */
        @Override // IlIlIIlIII1.lIIIIlllllIlll1.IllIIlIIII1
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void llllIIIIll1(c13.nim5.ez8.h5_proto.signaling.CheckSignalingPluginStartResponse r6, java.lang.Exception r7) {
            /*
                r5 = this;
                java.lang.String r0 = ""
                if (r7 == 0) goto L12
                java.lang.String r6 = r7.getMessage()     // Catch: java.lang.Throwable -> Lf
                c13.nim5.ez8.h5_proto.Log$LogLevel r7 = c13.nim5.ez8.h5_proto.Log.LogLevel.ERROR     // Catch: java.lang.Throwable -> Lf
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(r7, r0, r6)     // Catch: java.lang.Throwable -> Lf
                goto Le3
            Lf:
                r6 = move-exception
                goto Le9
            L12:
                r7 = 8
                if (r6 == 0) goto Lc5
                long r1 = r6.getCode()     // Catch: java.lang.Throwable -> Lf
                r3 = 0
                int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
                if (r1 == 0) goto L22
                goto Lc5
            L22:
                IlIlllIIlI1.llllIllIl1 r1 = IlIlllIIlI1.llllIllIl1.this     // Catch: java.lang.Throwable -> Lf
                boolean r2 = r6.isRun()     // Catch: java.lang.Throwable -> Lf
                r1.f267IllIIlIIII1 = r2     // Catch: java.lang.Throwable -> Lf
                IlIlllIIlI1.llllIllIl1 r1 = IlIlllIIlI1.llllIllIl1.this     // Catch: java.lang.Throwable -> Lf
                java.lang.String r2 = r6.getOfferId()     // Catch: java.lang.Throwable -> Lf
                r1.f266IlIlllIIlI1 = r2     // Catch: java.lang.Throwable -> Lf
                IlIlllIIlI1.llllIllIl1 r1 = IlIlllIIlI1.llllIllIl1.this     // Catch: java.lang.Throwable -> Lf
                java.lang.String r6 = r6.getJobId()     // Catch: java.lang.Throwable -> Lf
                r1.f265IlIllIlllIllI1 = r6     // Catch: java.lang.Throwable -> Lf
                IlIlllIIlI1.llllIllIl1 r6 = IlIlllIIlI1.llllIllIl1.this     // Catch: java.lang.Throwable -> Lf
                boolean r6 = r6.f267IllIIlIIII1     // Catch: java.lang.Throwable -> Lf
                if (r6 != 0) goto L59
                r6 = 18
                byte[] r6 = new byte[r6]     // Catch: java.lang.Throwable -> Lf
                r6 = {x00f0: FILL_ARRAY_DATA , data: [13, -108, -20, 61, -16, -123, -64, -122, 76, -21, -19, 88, -124, -92, -125, -26, 108, -116} // fill-array     // Catch: java.lang.Throwable -> Lf
                byte[] r7 = new byte[r7]     // Catch: java.lang.Throwable -> Lf
                r7 = {x00fe: FILL_ARRAY_DATA , data: [-21, 3, 76, -44, 108, 5, 38, 15} // fill-array     // Catch: java.lang.Throwable -> Lf
                IllIIlIIII1.llllIIIIll1 r1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1     // Catch: java.lang.Throwable -> Lf
                java.lang.String r6 = r1.llllIIIIll1(r6, r7)     // Catch: java.lang.Throwable -> Lf
                c13.nim5.ez8.h5_proto.Log$LogLevel r7 = c13.nim5.ez8.h5_proto.Log.LogLevel.INFO     // Catch: java.lang.Throwable -> Lf
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(r7, r0, r6)     // Catch: java.lang.Throwable -> Lf
                goto Le3
            L59:
                lllllIllIl1.lIIIIlllllIlll1 r6 = lllllIllIl1.lIIIIlllllIlll1.f705llllllIlIIIlll1     // Catch: java.lang.Throwable -> Lf
                r0 = 14
                byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> Lf
                r0 = {x0106: FILL_ARRAY_DATA , data: [-109, -61, 50, -61, -88, -66, 24, 27, -76, -52, 55, -34, -103, -86} // fill-array     // Catch: java.lang.Throwable -> Lf
                byte[] r1 = new byte[r7]     // Catch: java.lang.Throwable -> Lf
                r1 = {x0112: FILL_ARRAY_DATA , data: [-38, -83, 91, -73, -9, -51, 113, 124} // fill-array     // Catch: java.lang.Throwable -> Lf
                IllIIlIIII1.llllIIIIll1 r2 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1     // Catch: java.lang.Throwable -> Lf
                java.lang.String r0 = r2.llllIIIIll1(r0, r1)     // Catch: java.lang.Throwable -> Lf
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lf
                r1.<init>()     // Catch: java.lang.Throwable -> Lf
                r3 = 22
                byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> Lf
                r3 = {x011a: FILL_ARRAY_DATA , data: [-37, 8, -88, -73, -6, -109, -104, -91, -55, 68, -68, -111, -5, -101, -100, -69, -23, 78, -7, -28, -67, -33} // fill-array     // Catch: java.lang.Throwable -> Lf
                byte[] r4 = new byte[r7]     // Catch: java.lang.Throwable -> Lf
                r4 = {x012a: FILL_ARRAY_DATA , data: [-96, 42, -37, -34, -99, -3, -7, -55} // fill-array     // Catch: java.lang.Throwable -> Lf
                java.lang.String r3 = r2.llllIIIIll1(r3, r4)     // Catch: java.lang.Throwable -> Lf
                java.lang.StringBuilder r1 = r1.append(r3)     // Catch: java.lang.Throwable -> Lf
                IlIlllIIlI1.llllIllIl1 r3 = IlIlllIIlI1.llllIllIl1.this     // Catch: java.lang.Throwable -> Lf
                java.lang.String r3 = r3.f266IlIlllIIlI1     // Catch: java.lang.Throwable -> Lf
                java.lang.StringBuilder r1 = r1.append(r3)     // Catch: java.lang.Throwable -> Lf
                r3 = 22
                byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> Lf
                r3 = {x0132: FILL_ARRAY_DATA , data: [-80, 23, 79, 43, -8, -51, -76, 90, -13, 87, 6, 103, -20, -18, -68, 86, -37, 95, 77, 51, -85, -122} // fill-array     // Catch: java.lang.Throwable -> Lf
                byte[] r4 = new byte[r7]     // Catch: java.lang.Throwable -> Lf
                r4 = {x0142: FILL_ARRAY_DATA , data: [-110, 59, 111, 9, -117, -92, -45, 52} // fill-array     // Catch: java.lang.Throwable -> Lf
                java.lang.String r3 = r2.llllIIIIll1(r3, r4)     // Catch: java.lang.Throwable -> Lf
                java.lang.StringBuilder r1 = r1.append(r3)     // Catch: java.lang.Throwable -> Lf
                IlIlllIIlI1.llllIllIl1 r3 = IlIlllIIlI1.llllIllIl1.this     // Catch: java.lang.Throwable -> Lf
                java.lang.String r3 = r3.f265IlIllIlllIllI1     // Catch: java.lang.Throwable -> Lf
                java.lang.StringBuilder r1 = r1.append(r3)     // Catch: java.lang.Throwable -> Lf
                r3 = 2
                byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> Lf
                r3 = {x014a: FILL_ARRAY_DATA , data: [-73, -93} // fill-array     // Catch: java.lang.Throwable -> Lf
                byte[] r7 = new byte[r7]     // Catch: java.lang.Throwable -> Lf
                r7 = {x0150: FILL_ARRAY_DATA , data: [-107, -34, 43, 97, 10, 88, 77, 9} // fill-array     // Catch: java.lang.Throwable -> Lf
                java.lang.String r7 = r2.llllIIIIll1(r3, r7)     // Catch: java.lang.Throwable -> Lf
                java.lang.StringBuilder r7 = r1.append(r7)     // Catch: java.lang.Throwable -> Lf
                java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> Lf
                r6.llllIIIIll1(r0, r7)     // Catch: java.lang.Throwable -> Lf
                goto Le3
            Lc5:
                if (r6 == 0) goto Lcc
                java.lang.String r6 = r6.getMessage()     // Catch: java.lang.Throwable -> Lf
                goto Lde
            Lcc:
                r6 = 13
                byte[] r6 = new byte[r6]     // Catch: java.lang.Throwable -> Lf
                r6 = {x0158: FILL_ARRAY_DATA , data: [99, 116, -39, -71, 41, -75, -69, 59, 125, 110, -37, -90, 108} // fill-array     // Catch: java.lang.Throwable -> Lf
                byte[] r7 = new byte[r7]     // Catch: java.lang.Throwable -> Lf
                r7 = {x0164: FILL_ARRAY_DATA , data: [13, 1, -75, -43, 9, -57, -34, 72} // fill-array     // Catch: java.lang.Throwable -> Lf
                IllIIlIIII1.llllIIIIll1 r1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1     // Catch: java.lang.Throwable -> Lf
                java.lang.String r6 = r1.llllIIIIll1(r6, r7)     // Catch: java.lang.Throwable -> Lf
            Lde:
                c13.nim5.ez8.h5_proto.Log$LogLevel r7 = c13.nim5.ez8.h5_proto.Log.LogLevel.ERROR     // Catch: java.lang.Throwable -> Lf
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(r7, r0, r6)     // Catch: java.lang.Throwable -> Lf
            Le3:
                java.util.concurrent.CountDownLatch r6 = r5.f274llllIIIIll1
                r6.countDown()
                return
            Le9:
                java.util.concurrent.CountDownLatch r7 = r5.f274llllIIIIll1
                r7.countDown()
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: IlIlllIIlI1.llllIllIl1.IllIIlIIII1.llllIIIIll1(c13.nim5.ez8.h5_proto.signaling.CheckSignalingPluginStartResponse, java.lang.Exception):void");
        }
    }

    public class lIIIIlllllIlll1 implements Runnable {
        public lIIIIlllllIlll1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                IIlIllIIll1.llllIIIIll1();
            } catch (Throwable th) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{1, 1, 124, -66, -93, -90, 126, 60, 42, 64, 69, -3, -47, -86, 118, 52, 61, 20, 73, -25, -108, -96, 115, 28, 39, 80, 110, -32, -122, -83, 116, 50, 40, 80, 107, -26, -68, -84, 124, 56, 37, 20, 79, -3, -125, -84, 106, 103, 105}, new byte[]{73, 52, 42, -113, -15, -61, 24, 93}) + th.getMessage());
            }
        }
    }

    public class llllIIIIll1 implements Runnable {

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ WebView[] f277llllIIIIll1;

        public llllIIIIll1(WebView[] webViewArr) {
            this.f277llllIIIIll1 = webViewArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f277llllIIIIll1[0] = new WebView(IlIlllIIlI1.lIIIIlllllIlll1.llllIllIl1());
        }
    }

    /* renamed from: IlIlllIIlI1.llllIllIl1$llllIllIl1, reason: collision with other inner class name */
    public class C0009llllIllIl1 extends TimerTask {

        /* renamed from: IlIlllIIlI1.llllIllIl1$llllIllIl1$lIIIIlllllIlll1 */
        public class lIIIIlllllIlll1 implements Runnable {

            /* renamed from: llllIIIIll1, reason: collision with root package name */
            public final /* synthetic */ WebView[] f280llllIIIIll1;

            public lIIIIlllllIlll1(WebView[] webViewArr) {
                this.f280llllIIIIll1 = webViewArr;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f280llllIIIIll1[0] = new WebView(IlIlllIIlI1.lIIIIlllllIlll1.llllIllIl1());
            }
        }

        /* renamed from: IlIlllIIlI1.llllIllIl1$llllIllIl1$llllIIIIll1 */
        public class llllIIIIll1 implements Runnable {
            public llllIIIIll1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    IIlIllIIll1.llllIIIIll1();
                } catch (Throwable th) {
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-104, 27, -118, 29, 14, -5, -68, 99, -77, 90, -77, 94, 124, -9, -76, 107, -92, 14, -65, 68, 57, -3, -79, 67, -66, 74, -104, 67, 43, -16, -74, 109, -79, 74, -99, 69, 17, -15, -66, 103, -68, 14, -71, 94, 46, -15, -88, 56, -16}, new byte[]{-48, 46, -36, 44, 92, -98, -38, 2}) + th.getMessage());
                }
            }
        }

        public C0009llllIllIl1() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            try {
                new Thread(new llllIIIIll1()).start();
                IlIlllIIlI1 ilIlllIIlI1 = new IlIlllIIlI1();
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-27, 110, 82, -77, 28, -34, -90, -123, -50, 47, 107, -16, 110, -49, -95, -105, -58, 123, 119, -10, 47, -55, -76, -69, -14, 4, 91, -35}, new byte[]{-83, 91, 4, -126, 78, -69, -64, -28}));
                WebView[] webViewArr = new WebView[1];
                IlIlllIIlI1.lIIIIlllllIlll1.llllIIIIll1(new lIIIIlllllIlll1(webViewArr));
                SystemClock.sleep(2000L);
                WebView webView = webViewArr[0];
                if (webView != null) {
                    ilIlllIIlI1.llllIIIIll1(webView);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public boolean IIlIllIIll1() {
        return this.f267IllIIlIIII1;
    }

    public final void IlIlIIlIII1() {
        Context context = this.f270llllIIIIll1;
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        IlIlIIlIII1.lIIIIlllllIlll1 liiiilllllilll1 = new IlIlIIlIII1.lIIIIlllllIlll1(context, new lIIIIlllllIlll1.IlIlllIIlI1(lllliiiill1.llllIIIIll1(new byte[]{97, 51, 80, 109, 11, 63, -82, 50, 109, 43, 72, 109, 31, 97, -81, 126, 101, 46, 71, 118}, new byte[]{9, 71, 36, 29, 120, 5, -127, 29}), lllliiiill1.llllIIIIll1(new byte[]{-99, 79, -49, -26, 48, 76, -114, 22, -122, 76, -37, -72, 49, 0, -122, 19, -119, 87, -109, -81, 118, 4, -124, 27, -122, 85, -46, -69, 64, 20, -103}, new byte[]{-22, 60, -68, -36, 31, 99, -22, 122}), "", true, 30L));
        liiiilllllilll1.llllIllIl1();
        this.f264IlIlIIlIII1 = liiiilllllilll1;
    }

    public String IlIllIlllIllI1() {
        return this.f265IlIllIlllIllI1;
    }

    public IlIlIIlIII1.lIIIIlllllIlll1 IlIlllIIlI1() {
        return this.f264IlIlIIlIII1;
    }

    public Context IllIIlIIII1() {
        return this.f270llllIIIIll1;
    }

    public boolean lIllIIIlIl1() {
        return this.f268lIIIIlllllIlll1;
    }

    public String llllllIlIIIlll1() {
        return this.f266IlIlllIIlI1;
    }

    public String llllIllIl1() {
        return this.f272llllllIlIIIlll1;
    }

    public final void lIIIIlllllIlll1() {
        if (IIlIllIIll1.llllIllIl1()) {
            try {
                this.f267IllIIlIIII1 = false;
                CountDownLatch countDownLatch = new CountDownLatch(1);
                this.f264IlIlIIlIII1.llllIIIIll1(new IllIIlIIII1(countDownLatch));
                countDownLatch.await();
                if (this.f267IllIIlIIII1) {
                    return;
                }
                this.f264IlIlIIlIII1.llllIIIIll1();
            } catch (Exception e) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-14, -29, 59, 120, ByteCompanionObject.MAX_VALUE, -41, 0, -123, -62, -30, 57, 117, 117, -23, 28, -123, -10, -85, 59, 105, 102, -22, 7, -47, -79}, new byte[]{-111, -117, 94, 27, 20, -123, 117, -21}))));
            }
        }
    }

    public void llllIIIIll1(Context context, String str) {
        llllIIIIll1(context, str, null);
    }

    public void llllIIIIll1(Context context, String str, Atom atom) {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        android.util.Log.i(lllliiiill1.llllIIIIll1(new byte[]{-24, -53, -91, -59, -117, 81}, new byte[]{-84, -89, -55, -75, -20, 53, 15, 109}), llllIIIIll1.llllIIIIll1.llllIIIIll1(lllliiiill1, new byte[]{67, 57, -105, 7, -31, -115, 60, 95, 104, 120, -82, 68, -109, -127, 52, 87, ByteCompanionObject.MAX_VALUE, 54}, new byte[]{11, 12, -63, 54, -77, -24, 90, 62}, new StringBuilder(), str));
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                WebView.setDataDirectorySuffix(Application.getProcessName().replace(lllliiiill1.llllIIIIll1(new byte[]{115}, new byte[]{73, 106, 39, -48, 10, 16, 94, -20}), lllliiiill1.llllIIIIll1(new byte[]{87}, new byte[]{8, -35, 29, 18, 26, -85, 7, -127})));
            }
        } catch (Throwable unused) {
            System.out.println(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{29, 81, -55, 92, 95, 103, -33, 53, 57, 106, -9, 118, 105, 125, -117, 28, 120, 106, -11, 122, 121, 52, -98, 31, 49, 121, -2, 51, 90, 56, -99, 37, 49, 102, -20}, new byte[]{88, 3, -101, 19, 13, 93, -1, 115}));
        }
        IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        android.util.Log.i(lllliiiill12.llllIIIIll1(new byte[]{-92, 67, -11, 106, 123, 46}, new byte[]{-32, 47, -103, 26, 28, 74, 95, -80}), lllliiiill12.llllIIIIll1(new byte[]{-83, -108, 32, -19, 110, -42, -111, -75, -122, -43, 25, -82, 28, -64, -110, -96, -95, -64, 2, -67, 120, -38, -123, -79, -122, -43, 25, -82, 69, -32, -126, -78, -125, -56, 14, -4, 83, -59, -110, -90}, new byte[]{-27, -95, 118, -36, 60, -77, -9, -44}));
        this.f272llllllIlIIIlll1 = str;
        try {
            String llllIIIIll12 = lllliiiill12.llllIIIIll1(new byte[]{4, 126, 0, -10, 99, 114, 68, 34, 47, 63, 57, -75, 17, 126, 76, 42, 56, 20, 9, -104, 110}, new byte[]{76, 75, 86, -57, 49, 23, 34, 67});
            Log.LogLevel logLevel = Log.LogLevel.INFO;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, "", llllIIIIll12);
            Context applicationContext = context.getApplicationContext();
            SystemClock.sleep(500L);
            this.f270llllIIIIll1 = applicationContext;
            IIlIllIIll1.f145lIllIlIll1 = applicationContext;
            this.f268lIIIIlllllIlll1 = IIlIllIIll1.IIlIllIIll1();
            IlIlllIIlI1.lIIIIlllllIlll1.f248llllIIIIll1 = this;
            IlIlllIIlI1.lIIIIlllllIlll1.f246IllIIlIIII1 = IllIIlIIII1().getPackageName();
            lllllIllIl1.llllIllIl1.f729IlIllIlllIllI1.llllIIIIll1(applicationContext);
            lllllIllIl1.lIIIIlllllIlll1.f705llllllIlIIIlll1.llllIIIIll1(applicationContext);
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, "", lllliiiill12.llllIIIIll1(new byte[]{85, 95, 31, -48, -6, 67, 24, -14, 126, 30, 38, -109, -120, 79, 16, -6, 105}, new byte[]{29, 106, 73, -31, -88, 38, 126, -109}));
            llllllIlIIIlll1.llllIIIIll1.lIIIIlllllIlll1(applicationContext, str);
            if (atom != null) {
                llllllIlIIIlll1.llllIIIIll1.f742lIIIIlllllIlll1 = atom;
            }
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, "", lllliiiill12.llllIIIIll1(new byte[]{16, 71, -91, 56, -54, -65, 54, -41, 59, 6, -100, 123, -72, -69, 36, -39, 53, 72, -45}, new byte[]{88, 114, -13, 9, -104, -38, 80, -74}) + llllllIlIIIlll1.llllIIIIll1.llllIIIIll1().toJSONObject());
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, "", lllliiiill12.llllIIIIll1(new byte[]{123, -89, 103, 83, -87, -78, 124, -23, 15, -3, 79, 42, -57, -75, 45, -78, 30, -101, 16, 14, -102, -48, 39, -50}, new byte[]{-98, 26, -12, -74, 32, 63, -101, 84}).concat(IlIlIIlIII1.llllIIIIll1(applicationContext)));
            llllIIIIll1(applicationContext);
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, "", lllliiiill12.llllIIIIll1(new byte[]{40, 120, -77, 124, -36, 80, -19, -95, 46, 104, -101, 110, -58, 30, -40, -67, 37}, new byte[]{65, 11, -2, 29, -75, 62, -67, -45}));
            IlIllll1.lIIIIlllllIlll1.llllIIIIll1(applicationContext);
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, "", lllliiiill12.llllIIIIll1(new byte[]{-115, 21, 27, -6, 87, 109, -123, 20}, new byte[]{-27, 122, 116, -111, 119, 8, -21, 112}));
            WebView[] webViewArr = new WebView[1];
            IlIlllIIlI1.lIIIIlllllIlll1.llllIIIIll1(new llllIIIIll1(webViewArr));
            SystemClock.sleep(2000L);
            if (webViewArr[0] == null) {
                return;
            }
            if (!IIIlIllIlI1.IlIlIIlIII1.IllIIlIIII1()) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, "", lllliiiill12.llllIIIIll1(new byte[]{-50, 97, 20, -70, -88, -60, -37, -10, -27, 32, 45, -7, -38, 68, 45, 56, 99, -34, -22, 110, 94, 16, 85, 35, 35, -69, -2, 7, 30, 25, 48, 113, 18, -5, -92, 7, 123, 72, 32, 14, 111, -53, -15}, new byte[]{-122, 84, 66, -117, -6, -95, -67, -105}));
                return;
            }
            if (!llllIIIIll1()) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", lllliiiill12.llllIIIIll1(new byte[]{98, 45, -112, 112, 7, -109, -104, 123, 73, 108, -87, 51, 117, -97, -112, 115, 94, 56, -99, 96, 54, -98, -101, 121, 65, 91, -89, 47, 7, -125, -112, 50, 3, 69}, new byte[]{42, 24, -58, 65, 85, -10, -2, 26}));
                return;
            }
            IlIlIIlIII1();
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, "", lllliiiill12.llllIIIIll1(new byte[]{80, 49, 69, 35, -48, 52, 101, 62, 90, 45, 115, 41, -36, 122, 109, 60, 90, 55, 71, 19, -33, ByteCompanionObject.MAX_VALUE, 44, 53, 93, 61}, new byte[]{51, 89, 32, 64, -69, 20, 12, 80}));
            lIIIIlllllIlll1();
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, "", lllliiiill12.llllIIIIll1(new byte[]{-82, -52, 20, 84, -41, -112, 122, 29, -98, -51, 22, 89, -35, -82, 102, 29, -86, -124, 20, 89, -40}, new byte[]{-51, -92, 113, 55, -68, -62, 15, 115}));
            new Thread(new lIIIIlllllIlll1()).start();
            IlIlllIIlI1 ilIlllIIlI1 = new IlIlllIIlI1();
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, "", lllliiiill12.llllIIIIll1(new byte[]{85, 101, -59, -120, 69, -21, 112, -99, 126, 36, -4, -53, 55, -6, 119, -113, 118, 112, -32, -51, 118, -4, 98, -93, 66, 15, -52, -26}, new byte[]{29, 80, -109, -71, 23, -114, 22, -4}));
            ilIlllIIlI1.llllIIIIll1(webViewArr[0]);
            new Timer().schedule(new C0009llllIllIl1(), 1800000L, 1800000L);
        } catch (Throwable th) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{3, 76, 33, -90, 56, 63, 40, -97, 40, 13, 24, -27, 74, 51, 32, -105, 63, 89, 18, -27, 24, 53, 60, -60, 107}, new byte[]{75, 121, 119, -105, 106, 90, 78, -2}) + th.getMessage());
        }
    }

    public final boolean llllIIIIll1() {
        if (IlIlllIIlI1.lIIIIlllllIlll1.IlIlIIlIII1()) {
            return true;
        }
        Context context = this.f270llllIIIIll1;
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        String llllIIIIll12 = IIlIllIIll1.llllIIIIll1(context, lllliiiill1.llllIIIIll1(new byte[]{29, 78, -42, -47, -68, 121, 64, 10, 35, 123, -52, -56, -106, 84, 67, 22}, new byte[]{81, 47, -91, -91, -13, 31, 38, 111}));
        if (llllIIIIll12 == null) {
            return true;
        }
        try {
            if (llllIIIIll12.isEmpty()) {
                return true;
            }
            String trim = llllIIIIll12.trim();
            if (!Pattern.matches(lllliiiill1.llllIIIIll1(new byte[]{-120, 14, -75}, new byte[]{-44, 106, -98, -101, -40, 53, -78, -89}), trim)) {
                return true;
            }
            long parseLong = Long.parseLong(trim);
            boolean z = new Date().getTime() - parseLong >= 21600000;
            if (!z) {
                lllllIllIl1.lIIIIlllllIlll1.f705llllllIlIIIlll1.llllIIIIll1(lllllIllIl1.llllIIIIll1.f726lllllIllIl1.f728llllIIIIll1, lllliiiill1.llllIIIIll1(new byte[]{86, 116, -43, 14, -70, -21, 57, -48, 125, 53, -20, 77, -56, -19, 55, -44, 125, 42, -93, 75, -127, -29, 58, -43, 62, 46, -10, 75, -60, -82, 48, -35, 122, 123, -93}, new byte[]{30, 65, -125, 63, -24, -114, 95, -79}) + parseLong + lllliiiill1.llllIIIIll1(new byte[]{-105, -1, 21, 26, -24, 108, -10}, new byte[]{-69, -33, 123, 117, -97, 86, -42, -47}) + new Date().getTime());
            }
            return z;
        } catch (Exception e) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{55, -69, 37, 119, 36, 91, -100, 75, 28, -6, 28, 52, 86, 93, -110, 79, 28, -27, 48, 39, 24, 108, -113, 68, 95, -21, 1, 52, 25, 76, -64, 10}, new byte[]{ByteCompanionObject.MAX_VALUE, -114, 115, 70, 118, 62, -6, 42}))));
            return true;
        }
    }

    public final boolean llllIIIIll1(Context context) {
        String str;
        int myPid = Process.myPid();
        Iterator<ActivityManager.RunningAppProcessInfo> it = ((ActivityManager) context.getSystemService(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-69, -49, -13, 106, -104, 35, -54, -83}, new byte[]{-38, -84, -121, 3, -18, 74, -66, -44}))).getRunningAppProcesses().iterator();
        while (true) {
            if (!it.hasNext()) {
                str = "";
                break;
            }
            ActivityManager.RunningAppProcessInfo next = it.next();
            if (next.pid == myPid) {
                str = next.processName;
                break;
            }
        }
        String packageName = context.getPackageName();
        StringBuilder sb = new StringBuilder();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, "", llllIIIIll1.llllIIIIll1.llllIIIIll1(lllliiiill1, new byte[]{73, 13, 119, 113, -31, 86, 57, -115, 26, 2, 25, 96, -2, 92, 96, -56}, new byte[]{105, 113, 87, 1, -109, 57, 90, -24}, sb.append(lllliiiill1.llllIIIIll1(new byte[]{37, 42, 47, 77, -77, -55, 102, -19, 45, 56, 53, 109, -126, -42, 108, -76, 104}, new byte[]{72, 75, 70, 35, -29, -69, 9, -114})).append(packageName), str));
        this.f271llllIllIl1 = str;
        return str.equals(packageName);
    }
}

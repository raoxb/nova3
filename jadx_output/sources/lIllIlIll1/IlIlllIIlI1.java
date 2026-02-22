package lIllIlIll1;

import IlIlIIIlIlIlll1.IIlIllIIll1;
import IlIllIlllIllI1.lIIIIlllllIlll1;
import android.content.Context;
import android.os.SystemClock;
import android.webkit.WebView;
import c13.nim5.ez8.h5_proto.Log;
import kotlin.jvm.internal.ByteCompanionObject;
import lIllIIIlIl1.lIllIlIll1;
import lIllIIIlIl1.llllllIlIIIlll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class IlIlllIIlI1 {

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public static final String f462IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public static final String f463lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public static final String f464llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public static final String f465llllIllIl1;

    public class lIIIIlllllIlll1 implements Runnable {

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ WebView[] f467llllIIIIll1;

        public lIIIIlllllIlll1(WebView[] webViewArr) {
            this.f467llllIIIIll1 = webViewArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (IIlIllIIll1.f145lIllIlIll1 != null) {
                this.f467llllIIIIll1[0] = new WebView(IIlIllIIll1.f145lIllIlIll1);
            }
        }
    }

    public class llllIIIIll1 implements Runnable {

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ WebView f469llllIIIIll1;

        public class lIIIIlllllIlll1 implements Runnable {

            /* renamed from: llllIIIIll1, reason: collision with root package name */
            public final /* synthetic */ lIllIlIll1.llllIIIIll1 f471llllIIIIll1;

            public lIIIIlllllIlll1(lIllIlIll1.llllIIIIll1 lllliiiill1) {
                this.f471llllIIIIll1 = lllliiiill1;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    this.f471llllIIIIll1.lIllIIIlIl1();
                } catch (Exception e) {
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-84, -64, 24, 1, 7, -18, 59, -86, ByteCompanionObject.MIN_VALUE, -34, 15, 78, 6, -70, 45, -80, -99, -37, 4, 9, 85, -81, 40, -118, -122, -34, 14, 11, 7, -12, 108}, new byte[]{-23, -78, 106, 110, 117, -50, 76, -62}) + e);
                }
            }
        }

        /* renamed from: lIllIlIll1.IlIlllIIlI1$llllIIIIll1$llllIIIIll1, reason: collision with other inner class name */
        public class RunnableC0018llllIIIIll1 implements Runnable {
            public RunnableC0018llllIIIIll1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                IlIlllIIlI1.this.llllIllIl1();
            }
        }

        public llllIIIIll1(WebView webView) {
            this.f469llllIIIIll1 = webView;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                String llllIIIIll12 = lllliiiill1.llllIIIIll1(new byte[]{113, 41, 103, -49, 72, -91, 7, -102, 113, 54}, new byte[]{2, 93, 6, -67, 60, -123, 115, -5});
                Log.LogLevel logLevel = Log.LogLevel.INFO;
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, "", llllIIIIll12);
                IlIlllIIlI1.this.llllllIlIIIlll1();
                String lIIIIlllllIlll12 = IlIlllIIlI1.lIIIIlllllIlll1.f248llllIIIIll1.IIlIllIIll1() ? IlIlllIIlI1.this.lIIIIlllllIlll1() : IlIlllIIlI1.this.llllIIIIll1();
                JSONObject IlIlllIIlI12 = IlIlllIIlI1.lIIIIlllllIlll1.f248llllIIIIll1.IIlIllIIll1() ? IlIlllIIlI1.this.IlIlllIIlI1() : IlIlllIIlI1.this.IlIllIlllIllI1();
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, "", lllliiiill1.llllIIIIll1(new byte[]{-79, -80, 61, 87, 87, 94, 26, -39, -20, -11}, new byte[]{-42, -43, 73, 119, 35, 63, 105, -78}) + IlIlllIIlI12);
                IlIlllIIlI1.lIIIIlllllIlll1.llllIIIIll1(new lIIIIlllllIlll1(IlIlllIIlI1.lIIIIlllllIlll1.f248llllIIIIll1.IIlIllIIll1() ? new IllIIlIIII1(this.f469llllIIIIll1, IlIlllIIlI12, lIIIIlllllIlll12) : new llllIllIl1(this.f469llllIIIIll1, IlIlllIIlI12, lIIIIlllllIlll12, new RunnableC0018llllIIIIll1())));
            } catch (IIlIllIIll1.llllIIIIll1 e) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.WARN, "", e.getMessage());
            } catch (Exception e2) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.WARN, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{2, -17, 74, -40, -76, 96, -100, 17, 2, -16, 11, -52, -95, 41, -124, 21, 21, -95, 11}, new byte[]{113, -101, 43, -86, -64, 64, -24, 112}) + e2);
            } catch (Throwable th) {
                StringBuilder sb = new StringBuilder();
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    sb.append(stackTraceElement.toString()).append("\n");
                }
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{92, 5, 15, -13, 11, -29, -76, -93, 56, 97, 1, -83, -71}, new byte[]{-70, -124, -71, 21, -117, 68, 81, 31}) + th.getMessage() + sb.toString());
            }
        }
    }

    static {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        f464llllIIIIll1 = lllliiiill1.llllIIIIll1(new byte[]{-41, -95, -124, -122, 79, -35, -14, -37, -46, -68}, new byte[]{-99, -14, -37, -48, 10, -113, -95, -110});
        f463lIIIIlllllIlll1 = lllliiiill1.llllIIIIll1(new byte[]{-62, 57, -57, 105, 126, -10, 33, -10, -57, 36}, new byte[]{-120, 106, -104, 63, 59, -92, 114, -65});
        f465llllIllIl1 = lllliiiill1.llllIIIIll1(new byte[]{71, 50, 95, 2, 16, -40, 123, 24, 75, 55, 95}, new byte[]{34, 91, 58, 102, ByteCompanionObject.MAX_VALUE, -9, 11, 126});
        f462IllIIlIIII1 = lllliiiill1.llllIIIIll1(new byte[]{-39, 43, 57, -25, 70, 104, 40, -99, -52, 36, 53, -17, 76}, new byte[]{-68, 66, 92, -125, 41, 71, 91, -62});
    }

    public IlIlllIIlI1() {
        IlIllIlllIllI1.lIIIIlllllIlll1.llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-69, 42, 5, -8, 15, -111, 85, -109, -86, 42, 9, -80, 19, -111, 82, -111, -72, 20, 39, -7, 27, -82, 70, -70, -82, 42, 39, -76, 25, -127, 69, -126, -81, 42, 35, -75, 16, -106, 85, -126, -81, 57, 43, -68}, new byte[]{-31, 110, 98, -127, 65, -5, 16, -5}));
    }

    public final void llllllIlIIIlll1() throws IIlIllIIll1.llllIIIIll1 {
        lIllIIIlIl1.IIlIllIIll1 iIlIllIIll1;
        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{90, -33, 114, 55, -10, 84, 77, 67, 83}, new byte[]{61, -70, 6, 23, -126, 59, 38, 38}));
        try {
            iIlIllIIll1 = IlIllIlllIllI1.llllIIIIll1.IlIllIlllIllI1();
        } catch (lIIIIlllllIlll1.llllIIIIll1 e) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-122, 62, -126, 86, 35, -95, -82, 47, -120, 37, -114, 24, 100, -95, -88, 9, -120, 60, -47, 86}, new byte[]{-25, 78, -21, 118, 68, -60, -38, 123}) + e);
            iIlIllIIll1 = null;
        }
        if (iIlIllIIll1 == null) {
            byte[] bArr = {120, -67, 22, 96, 16, ByteCompanionObject.MAX_VALUE, 79, 113};
            byte[] bArr2 = {31, -40, 98, 52, ByteCompanionObject.MAX_VALUE, 20, 42, 31};
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            throw new IIlIllIIll1.llllIIIIll1(lllliiiill1.llllIIIIll1(bArr, bArr2), -1, lllliiiill1.llllIIIIll1(new byte[]{72, -111, -92, 91, 65, 99, -105, -11, 26, -102, -94, 71, 66}, new byte[]{58, -12, -41, 43, 46, 13, -28, -112}));
        }
        if (iIlIllIIll1.llllIIIIll1() == 0) {
            IlIlllIIlI1.lIIIIlllllIlll1.f244IlIllIlllIllI1 = iIlIllIIll1.llllIllIl1();
        } else {
            throw new IIlIllIIll1.llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-14, -83, -126, -91, -57, 56, -6, -26}, new byte[]{-107, -56, -10, -15, -88, 83, -97, -120}), iIlIllIIll1.llllIIIIll1(), iIlIllIIll1.lIIIIlllllIlll1());
        }
    }

    public static IlIlllIIlI1.llllIllIl1 IllIIlIIII1() {
        return IlIlllIIlI1.lIIIIlllllIlll1.IlIllIlllIllI1();
    }

    public final JSONObject IlIllIlllIllI1() throws IIlIllIIll1.llllIIIIll1, JSONException {
        lIllIlIll1 lillilill1;
        try {
            lillilill1 = IlIllIlllIllI1.llllIIIIll1.IlIlllIIlI1();
        } catch (lIIIIlllllIlll1.llllIIIIll1 e) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{55, -118, 45, -46, -23, -34, -93, -3, 55, -119, 47, -46, -21, -55, -91, -58, 36, -64, 100}, new byte[]{86, -6, 68, -14, -114, -69, -41, -87}) + e);
            lillilill1 = null;
        }
        if (lillilill1 == null) {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            throw new IIlIllIIll1.llllIIIIll1(lllliiiill1.llllIIIIll1(new byte[]{-74, -26, 32, 99, -78, -4, 28}, new byte[]{-47, -125, 84, 55, -45, -113, 119, 68}), -1, lllliiiill1.llllIIIIll1(new byte[]{25, -58, 62, 27, 101, -65, 7, 25, 75, -51, 56, 7, 102}, new byte[]{107, -93, 77, 107, 10, -47, 116, 124}));
        }
        if (lillilill1.llllIIIIll1() == 0) {
            return new JSONObject(lillilill1.llllIllIl1());
        }
        throw new IIlIllIIll1.llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-70, 24, -73, 12, 16, -48, 30}, new byte[]{-35, 125, -61, 88, 113, -93, 117, 2}), lillilill1.llllIIIIll1(), lillilill1.lIIIIlllllIlll1());
    }

    public final JSONObject IlIlllIIlI1() throws IIlIllIIll1.llllIIIIll1, JSONException {
        lIllIlIll1 lillilill1;
        try {
            lillilill1 = IlIllIlllIllI1.llllIIIIll1.llllIIIIll1(IlIlllIIlI1.lIIIIlllllIlll1.IlIllIlllIllI1().llllllIlIIIlll1(), IlIlllIIlI1.lIIIIlllllIlll1.f248llllIIIIll1.IlIllIlllIllI1());
        } catch (lIIIIlllllIlll1.llllIIIIll1 e) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{29, -42, 106, 8, -35, -43, 78, -93, 21, -63, 109, 73, -42, -39, 84, -105, 40, -57, 112, 67, -102, -43, 72, -126, 19, -44, 57, 8}, new byte[]{124, -90, 3, 40, -70, -80, 58, -16}) + e);
            lillilill1 = null;
        }
        if (lillilill1 == null) {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            throw new IIlIllIIll1.llllIIIIll1(lllliiiill1.llllIIIIll1(new byte[]{-20, -8, 15, -101, ByteCompanionObject.MIN_VALUE, 60, -76, -4, -25, -12, 21, -81, -67, 58, -87, -10}, new byte[]{-117, -99, 123, -56, -23, 91, -38, -99}), -1, lllliiiill1.llllIIIIll1(new byte[]{118, -60, 36, -43, -75, 2, 123, -42, 36, -49, 34, -55, -74}, new byte[]{4, -95, 87, -91, -38, 108, 8, -77}));
        }
        if (lillilill1.llllIIIIll1() == 0) {
            return new JSONObject(lillilill1.llllIllIl1());
        }
        throw new IIlIllIIll1.llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-121, 28, 80, -64, -97, -2, -85, -87, -116, 16, 74, -12, -94, -8, -74, -93}, new byte[]{-32, 121, 36, -109, -10, -103, -59, -56}), lillilill1.llllIIIIll1(), lillilill1.lIIIIlllllIlll1());
    }

    public final String lIIIIlllllIlll1() throws IIlIllIIll1.llllIIIIll1 {
        llllllIlIIIlll1 lllllliliiilll1;
        String str;
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        String llllIIIIll12 = lllliiiill1.llllIIIIll1(new byte[]{17, 46, -110, -22, -101, 73, -38, 66, 28, 39, -101, -32, -98, 125, -7, 86, 36, 35, -123, -6, -103, 117, -35, 5, 1, 50, -106, -5, -124}, new byte[]{114, 70, -9, -119, -16, 26, -77, 37});
        Log.LogLevel logLevel = Log.LogLevel.INFO;
        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, "", llllIIIIll12);
        String llllIIIIll13 = IIlIllIIll1.llllIIIIll1(IlIlllIIlI1.lIIIIlllllIlll1.f248llllIIIIll1.IllIIlIIII1(), f464llllIIIIll1);
        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, "", llllIIIIll1.llllIIIIll1.llllIIIIll1(lllliiiill1, new byte[]{43, 74, 90, 46, -120, -44, 83, 30, 102, 26, 114, 79, -62, -59, 62, 100, 82, 81, 14, 83, -118, 118, -106}, new byte[]{-50, -3, -24, -53, 37, 76, -74, -126}, new StringBuilder(), llllIIIIll13));
        try {
            lllllliliiilll1 = IlIllIlllIllI1.llllIIIIll1.llllIIIIll1(llllIIIIll13 != null ? llllIIIIll13 : "");
        } catch (lIIIIlllllIlll1.llllIIIIll1 e) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{99, -121, -23, 91, 97, 55, 61, 21, 107, -101, -27, 40, 111, 53, 39, 50, 110, -98, -18, 28, 74, 61, 46, 58, 97, -41, -27, 9, 116, 61, 59, 105, 34}, new byte[]{2, -9, ByteCompanionObject.MIN_VALUE, 123, 6, 82, 73, 83}) + e);
            lllllliliiilll1 = null;
        }
        if (lllllliliiilll1 == null) {
            IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            throw new IIlIllIIll1.llllIIIIll1(lllliiiill12.llllIIIIll1(new byte[]{-77, 4, -74, 98, 83, -9, -120, 21, -79, 19, -79, 77, 85, -11}, new byte[]{-44, 97, -62, 36, 58, -101, -19, 67}), -1, lllliiiill12.llllIIIIll1(new byte[]{36, -111, -44, 121, -10, 59, 7, 95, 118, -102, -46, 101, -11}, new byte[]{86, -12, -89, 9, -103, 85, 116, 58}));
        }
        if (lllllliliiilll1.llllIIIIll1() != 0) {
            throw new IIlIllIIll1.llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-1, -52, 74, 68, -77, -111, -4, -40, -15, -50, 80, 99, -74, -108, -9, -20, -44, -58, 89, 107, -71}, new byte[]{-104, -87, 62, 2, -38, -3, -103, -117}), lllllliliiilll1.llllIIIIll1(), lllllliliiilll1.llllIllIl1());
        }
        Context IllIIlIIII12 = IlIlllIIlI1.lIIIIlllllIlll1.f248llllIIIIll1.IllIIlIIII1();
        try {
            str = IIlIllIIll1.lIIIIlllllIlll1(IllIIlIIII12, f462IllIIlIIII1);
        } catch (Exception e2) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{85, -67, 23, -54, 76, -125, 42, -62, 121, -93, 0, -123, 76, -58, 60, -50, 121, -95, 2, -123, 91, -51, 62, -40, 105, -65, 17, -64, 90, -125, 59, -61, 124, -86, 95, -123}, new byte[]{16, -49, 101, -91, 62, -93, 93, -86}) + e2);
            str = null;
        }
        if (llllIIIIll13 == null || llllIIIIll13.isEmpty() || !llllIIIIll13.equals(lllllliliiilll1.IllIIlIIII1()) || str == null || str.isEmpty()) {
            if (!IIlIllIIll1.llllIllIl1(IllIIlIIII12, f462IllIIlIIII1, lllllliliiilll1.lIIIIlllllIlll1())) {
                throw new RuntimeException(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-12, -121, -39, 100, 90, -44, 93, 17, -9, -105, -57, 101, 100, -57, -14, -26, -96, -23, -12, 36}, new byte[]{17, 1, 64, -127, -33, 113, 23, 66}));
            }
            IIlIllIIll1.lIIIIlllllIlll1(IllIIlIIII12, f464llllIIIIll1, lllllliliiilll1.IllIIlIIII1());
            str = lllllliliiilll1.lIIIIlllllIlll1();
        }
        if (str.isEmpty()) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-97, -45, 63, 74, 94, -96, 6, 32, -123, -44, 102}, new byte[]{-11, -96, 31, 35, 45, ByteCompanionObject.MIN_VALUE, 99, 77}));
        }
        return str;
    }

    public void llllIIIIll1(WebView webView) {
        new Thread(new llllIIIIll1(webView)).start();
    }

    public final void llllIllIl1() {
        try {
            WebView[] webViewArr = new WebView[1];
            IlIlllIIlI1.lIIIIlllllIlll1.llllIIIIll1(new lIIIIlllllIlll1(webViewArr));
            SystemClock.sleep(2000L);
            IlIlllIIlI1 ilIlllIIlI1 = new IlIlllIIlI1();
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-85, 12, -127, -66, 71, 41, 68, -111, ByteCompanionObject.MIN_VALUE, 77, -72, -3, 53, 56, 67, -125, -120, 25, -92, -5, 116, 62, 86}, new byte[]{-29, 57, -41, -113, 21, 76, 34, -16}));
            WebView webView = webViewArr[0];
            if (webView != null) {
                ilIlllIIlI1.llllIIIIll1(webView);
            }
        } catch (Exception unused) {
        }
    }

    public final String llllIIIIll1() throws IIlIllIIll1.llllIIIIll1 {
        lIllIIIlIl1.IllIIlIIII1 illIIlIIII1;
        String str;
        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-23, -45, -115, 113, -105, -33, -12, 122, -17, -55, -101, 123, -109, -5, -89, 95, -2, -38, -102, 102}, new byte[]{-118, -69, -24, 18, -4, -107, -121, 44}));
        lIllIIIlIl1.IlIlllIIlI1 ilIlllIIlI1 = null;
        try {
            illIIlIIII1 = IlIllIlllIllI1.llllIIIIll1.IllIIlIIII1();
        } catch (lIIIIlllllIlll1.llllIIIIll1 e) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-45, -116, -114, 97, -100, -5, 122, -33, -110, -103, -107, 51, -105, -26, 46, -102}, new byte[]{-78, -4, -25, 65, -8, -108, 20, -70}) + e);
            illIIlIIII1 = null;
        }
        if (illIIlIIII1 != null) {
            if (illIIlIIII1.llllIIIIll1() == 0) {
                String llllIIIIll12 = IIlIllIIll1.llllIIIIll1(IlIlllIIlI1.lIIIIlllllIlll1.f248llllIIIIll1.IllIIlIIII1(), f464llllIIIIll1);
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, "", llllIIIIll1.llllIIIIll1.llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1, new byte[]{-74, 88, -31, -62, -24, 118, 96, 102, -5, 8, -55, -93, -94, 103, 13, 28, -49, 67, -75, -65, -22, -44, -91}, new byte[]{83, -17, 83, 39, 69, -18, -123, -6}, new StringBuilder(), llllIIIIll12));
                Context IllIIlIIII12 = IlIlllIIlI1.lIIIIlllllIlll1.f248llllIIIIll1.IllIIlIIII1();
                try {
                    str = IIlIllIIll1.lIIIIlllllIlll1(IllIIlIIII12, f465llllIllIl1);
                } catch (Exception e2) {
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{11, -29, 104, -70, -16, 108, 15, 110, 39, -3, ByteCompanionObject.MAX_VALUE, -11, -16, 41, 25, 98, 39, -1, 125, -11, -25, 34, 27, 116, 55, -31, 110, -80, -26, 108, 30, 111, 34, -12, 32, -11}, new byte[]{78, -111, 26, -43, -126, 76, 120, 6}) + e2);
                    str = null;
                }
                if (llllIIIIll12 == null || llllIIIIll12.isEmpty() || !llllIIIIll12.equals(illIIlIIII1.llllIllIl1()) || str == null || str.isEmpty()) {
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{71, -26, 101, 79, 31, -63, -118, -101, 41, -78, 88, 23, -46, 57}, new byte[]{-94, 90, -27, -86, -72, 74, 110, 35}));
                    try {
                        ilIlllIIlI1 = IlIllIlllIllI1.llllIIIIll1.llllIllIl1();
                    } catch (lIIIIlllllIlll1.llllIIIIll1 e3) {
                        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{75, -27, 28, -7, 7, -97, -21, -22, 67, -7, 16, -7, 5, -120, -19, -61, 88, -81, 85}, new byte[]{42, -107, 117, -39, 96, -6, -97, -84}) + e3);
                    }
                    if (ilIlllIIlI1 != null) {
                        if (ilIlllIIlI1.llllIIIIll1() == 0) {
                            if (IIlIllIIll1.llllIllIl1(IllIIlIIII12, f465llllIllIl1, ilIlllIIlI1.lIIIIlllllIlll1())) {
                                IIlIllIIll1.lIIIIlllllIlll1(IllIIlIIII12, f464llllIIIIll1, illIIlIIII1.llllIllIl1());
                                str = ilIlllIIlI1.lIIIIlllllIlll1();
                            } else {
                                throw new RuntimeException(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-63, 68, 82, 51, 117, -120, -53, -60, -62, 84, 76, 50, 75, -101, 100, 51, -107, 42, ByteCompanionObject.MAX_VALUE, 115}, new byte[]{36, -62, -53, -42, -16, 45, -127, -105}));
                            }
                        } else {
                            throw new IIlIllIIll1.llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-105, -93, 6, 70, -5, 13, 73}, new byte[]{-16, -58, 114, 0, -110, 97, 44, 112}), ilIlllIIlI1.llllIIIIll1(), ilIlllIIlI1.llllIllIl1());
                        }
                    } else {
                        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                        throw new IIlIllIIll1.llllIIIIll1(lllliiiill1.llllIIIIll1(new byte[]{-114, 10, 1, -121, -55, ByteCompanionObject.MAX_VALUE, -81}, new byte[]{-23, 111, 117, -63, -96, 19, -54, 40}), -1, lllliiiill1.llllIIIIll1(new byte[]{84, -112, -104, -57, 79, -64, -73, -118, 6, -101, -98, -37, 76}, new byte[]{38, -11, -21, -73, 32, -82, -60, -17}));
                    }
                }
                if (str.isEmpty()) {
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{ByteCompanionObject.MIN_VALUE, 40, -72, 40, -124, 4, 1, -106, -102, 47, -31}, new byte[]{-22, 91, -104, 65, -9, 36, 100, -5}));
                }
                return str;
            }
            throw new IIlIllIIll1.llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-9, -36, 108, 73, -47, -100, -113, -1, -11, -53, 107, 102, -41, -98}, new byte[]{-112, -71, 24, 15, -72, -16, -22, -87}), illIIlIIII1.llllIIIIll1(), illIIlIIII1.lIIIIlllllIlll1());
        }
        IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        throw new IIlIllIIll1.llllIIIIll1(lllliiiill12.llllIIIIll1(new byte[]{-39, -61, 9, 123, -113, -34, 14, 32, -37, -44, 14, 84, -119, -36}, new byte[]{-66, -90, 125, 61, -26, -78, 107, 118}), -1, lllliiiill12.llllIIIIll1(new byte[]{87, 45, 18, -115, 53, 22, -99, 75, 5, 38, 20, -111, 54}, new byte[]{37, 72, 97, -3, 90, 120, -18, 46}));
    }
}

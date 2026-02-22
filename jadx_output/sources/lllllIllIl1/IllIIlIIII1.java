package lllllIllIl1;

import c13.nim5.ez8.h5_proto.Log;
import java.util.Date;

/* loaded from: classes.dex */
public class IllIIlIIII1 {

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public static boolean f702lIIIIlllllIlll1 = false;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public static boolean f703llllIIIIll1 = false;

    public static /* synthetic */ class llllIIIIll1 {

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public static final /* synthetic */ int[] f704llllIIIIll1;

        static {
            int[] iArr = new int[Log.LogLevel.values().length];
            f704llllIIIIll1 = iArr;
            try {
                iArr[Log.LogLevel.INFO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f704llllIIIIll1[Log.LogLevel.DEBUG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f704llllIIIIll1[Log.LogLevel.WARN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f704llllIIIIll1[Log.LogLevel.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static void IllIIlIIII1(String str, String str2) {
        lIIIIlllllIlll1(Log.LogLevel.WARN, str, str2);
    }

    public static void lIIIIlllllIlll1(String str, String str2) {
        lIIIIlllllIlll1(Log.LogLevel.ERROR, str, str2);
    }

    public static void llllIIIIll1(String str, String str2) {
    }

    public static void llllIllIl1(String str, String str2) {
        lIIIIlllllIlll1(Log.LogLevel.INFO, str, str2);
    }

    public static void IllIIlIIII1(String str) {
        lIIIIlllllIlll1(Log.LogLevel.WARN, "", str);
    }

    public static void lIIIIlllllIlll1(String str) {
        lIIIIlllllIlll1(Log.LogLevel.ERROR, "", str);
    }

    public static void llllIIIIll1(String str) {
        lIIIIlllllIlll1(Log.LogLevel.DEBUG, "", str);
    }

    public static void llllIllIl1(String str) {
        lIIIIlllllIlll1(Log.LogLevel.INFO, "", str);
    }

    public static void lIIIIlllllIlll1(Log.LogLevel logLevel, String str, String str2) {
        if (IlIlllIIlI1.lIIIIlllllIlll1.IlIlIIlIII1() && logLevel.getIntValue() == 1) {
            Log log = new Log();
            log.setLevel(logLevel);
            log.setTag(str);
            log.setMessage(str2);
            log.setTimestamp(Long.valueOf(new Date().getTime()));
            llllIllIl1.f729IlIllIlllIllI1.llllIIIIll1(log);
        }
        if (!f702lIIIIlllllIlll1) {
            f702lIIIIlllllIlll1 = true;
            IlIlllIIlI1.lIIIIlllllIlll1.f248llllIIIIll1.getClass();
            f703llllIIIIll1 = true;
        }
        if (f703llllIIIIll1) {
            llllIIIIll1(logLevel, str, str2);
        }
    }

    public static void llllIIIIll1(Log.LogLevel logLevel, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        String llllIIIIll12 = llllIIIIll1.llllIIIIll1.llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1, new byte[]{-50, 7, -105, 82, -13, -1, -66, 125, -50, 11, -87, 99}, new byte[]{-107, 67, -5, 62, -125, -104, -38, 32}, sb, str);
        int i = llllIIIIll1.f704llllIIIIll1[logLevel.ordinal()];
        if (i == 1) {
            android.util.Log.i(llllIIIIll12, str2);
            return;
        }
        if (i == 2) {
            android.util.Log.d(llllIIIIll12, str2);
        } else if (i == 3) {
            android.util.Log.w(llllIIIIll12, str2);
        } else {
            if (i != 4) {
                return;
            }
            android.util.Log.e(llllIIIIll12, str2);
        }
    }
}

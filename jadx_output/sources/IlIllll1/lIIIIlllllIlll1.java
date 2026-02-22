package IlIllll1;

import android.app.Application;
import android.content.Context;
import c13.nim5.ez8.h5_proto.Log;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class lIIIIlllllIlll1 {

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public static boolean f284lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public static final String f285llllIIIIll1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-27, -96, -2, -114, 34, 38, 67, -40, -56, -67}, new byte[]{-83, -49, -111, -27, 106, 67, 47, -88});

    public static class llllIIIIll1 extends RuntimeException {
        public llllIIIIll1(String str) {
            super(str);
        }
    }

    public static boolean IllIIlIIII1() {
        return f284lIIIIlllllIlll1;
    }

    public static void lIIIIlllllIlll1() throws Throwable {
        Application application = (Application) IlIlllIIlI1.lIIIIlllllIlll1.llllIllIl1().getApplicationContext();
        if (application == null) {
            throw new llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{57, 64, -90, 123, 54, -56, -27, 78, 49, 95, -72, 55, 49, -60, -16, 26, 62, 95, -93, 121, 59, -123}, new byte[]{88, 48, -42, 23, 95, -85, -124, 58}));
        }
        Class<?> cls = application.getClass();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        Field llllIIIIll12 = llllIIIIll1(cls, lllliiiill1.llllIIIIll1(new byte[]{81, -54, 43, 83, -55}, new byte[]{60, -120, 74, 32, -84, -40, 122, -120}));
        if (llllIIIIll12 == null) {
            throw new llllIIIIll1(lllliiiill1.llllIIIIll1(new byte[]{70, 7, -117, -74, 49, 103, 37, -27, 78, 41, -114, -27, 58, 40, 55, -84, 77, 42, -97, -85, 48, 105}, new byte[]{43, 69, -22, -59, 84, 71, 67, -116}));
        }
        Object obj = llllIIIIll12.get(application);
        if (!(obj instanceof Context)) {
            throw new llllIIIIll1(lllliiiill1.llllIIIIll1(new byte[]{-41, 65, -43, -120, -36, -68, -89, 34, -63, 69, -34, -103, -36, -79, -89, 56, -107, 70, -55, -104, -110, -69, -26}, new byte[]{-75, 32, -90, -19, -4, -33, -56, 76}));
        }
        llllIIIIll12.set(application, new IllIIlIIII1((Context) obj));
        f284lIIIIlllllIlll1 = llllIIIIll12.get(application) instanceof IllIIlIIII1;
    }

    public static Application llllIIIIll1() {
        return (Application) IlIlllIIlI1.lIIIIlllllIlll1.llllIllIl1().getApplicationContext();
    }

    public static void llllIllIl1() {
        try {
            lIIIIlllllIlll1();
        } catch (Throwable unused) {
        }
    }

    public static Field llllIIIIll1(Class cls, String str) {
        Field field;
        try {
            field = cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.WARN, f285llllIIIIll1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-42, -38, 117, -116, 121, 53, -121, -117, -111, -15, 110, -103, 101, 51, -125, -87, -40, -38, 109, -82, 85, 40, -120, -118, -63, -53, 104, -91, 126, 106, -53}, new byte[]{-79, -65, 1, -54, 16, 80, -21, -17}) + e.getMessage());
            field = null;
        }
        if (field == null && cls.getSuperclass() != null) {
            return llllIIIIll1(cls.getSuperclass(), str);
        }
        if (field != null && !field.isAccessible()) {
            field.setAccessible(true);
        }
        return field;
    }

    public static final void llllIIIIll1(Context context) {
        try {
            IlIllll1.llllIIIIll1.llllIIIIll1(context);
            llllIllIl1();
        } catch (Exception unused) {
        }
    }
}

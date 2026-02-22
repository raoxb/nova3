package IIIlIllIlI1;

import android.os.Build;
import android.webkit.WebView;
import c13.nim5.ez8.h5_proto.Log;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes.dex */
public class IlIlIIlIII1 {

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public static final String f4lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public static final String f5llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public static final String f6llllIllIl1;

    static {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        f5llllIIIIll1 = lllliiiill1.llllIIIIll1(new byte[]{27, -48, 92, -99, -70, 75, -36, 44, 57, -63, 91, -98, -89, 71, -57, 18}, new byte[]{76, -75, 62, -53, -45, 46, -85, 97});
        f4lIIIIlllllIlll1 = lllliiiill1.llllIIIIll1(new byte[]{52, 24, -48, 36, 65, 55, -90, 67, 54, 3, -62, 103, 12, 44, -95, 92, 43, 5, -59, 126, 125, 51, -67, 78, 4, 13, -37, ByteCompanionObject.MAX_VALUE, 71, 113, -121, 89, 43, 26, -40, 120, 86, 19, -67, 78, 9, 15, -47, 102, 71, 60, -96, 69, 52, 4, -30, 126, 75, 51}, new byte[]{91, 106, -73, 10, 34, 95, -44, 44});
        f6llllIllIl1 = lllliiiill1.llllIIIIll1(new byte[]{-64, -47, 78, -14, 30, 30, -56, 56, -63, -11, 66, -10, 29, 43, -19, 50, -43, -54, 79, -10, 24, 61, -2, 62, -41, -52, 89, -22}, new byte[]{-93, -93, 43, -109, 106, 123, -97, 93});
    }

    public static boolean IllIIlIIII1() {
        try {
            InvocationHandler llllIIIIll12 = llllIIIIll1();
            Object obj = llllIIIIll12.getClass().getFields()[0].get(llllIIIIll12);
            Class<?> cls = obj.getClass();
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            Object invoke = cls.getMethod(lllliiiill1.llllIIIIll1(new byte[]{-111, 95, 95, 40, -42, 53, 120, -63, -124, 78, 78, 31, -27, 32, 105, -38, -125, 72, 78, 8}, new byte[]{-10, 58, 43, 123, -93, 69, 8, -82}), null).invoke(obj, null);
            if (invoke instanceof String[]) {
                return Arrays.asList((String[]) invoke).contains(lllliiiill1.llllIIIIll1(new byte[]{-62, 15, -124, 109, 35, -70, 39, 116, -58, 21}, new byte[]{-113, 90, -48, 40, 124, -5, 114, 48}));
            }
            return false;
        } catch (Throwable th) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.WARN, f5llllIIIIll1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{98, 101, -71, -100, 114, 44, 105, 107, 117, 104, -15, -103, 124, 109, 112, 107, 115, 104, -4, -116, 108, 124, 116, 113, 115, 121, -4, -103, 120, 101, 104, 123, 101, 45, -85, -106, 109, 100, 36, 123, 121, 110, -71, -113, 109, 101, 107, 112}, new byte[]{1, 13, -36, -1, 25, 12, 4, 30}) + th);
            return false;
        }
    }

    public static ClassLoader lIIIIlllllIlll1() {
        return Build.VERSION.SDK_INT >= 28 ? WebView.getWebViewClassLoader() : llllIllIl1().getClass().getClassLoader();
    }

    public static InvocationHandler llllIIIIll1() throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException {
        return (InvocationHandler) Class.forName(f4lIIIIlllllIlll1, false, lIIIIlllllIlll1()).getDeclaredMethod(f6llllIllIl1, null).invoke(null, null);
    }

    public static Object llllIllIl1() {
        try {
            Method declaredMethod = WebView.class.getDeclaredMethod(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{118, -108, -5, 7, -54, 43, -68, 68, 99, -120}, new byte[]{17, -15, -113, 65, -85, 72, -56, 43}), null);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(null, null);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    public static void llllIIIIll1(WebView webView, llllllIlIIIlll1 lllllliliiilll1) {
        String str = f5llllIIIIll1;
        StringBuilder sb = new StringBuilder();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        String sb2 = sb.append(lllliiiill1.llllIIIIll1(new byte[]{39, 123, 84, -88, 120, 113, -24, -110, 106}, new byte[]{74, 14, 32, -51, 88, 30, -122, -88})).append(webView).toString();
        Log.LogLevel logLevel = Log.LogLevel.INFO;
        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, str, sb2);
        try {
            InvocationHandler llllIIIIll12 = llllIIIIll1();
            Object obj = llllIIIIll12.getClass().getFields()[0].get(llllIIIIll12);
            Object invoke = obj.getClass().getMethod(lllliiiill1.llllIIIIll1(new byte[]{116, 118, -62, -93, 9, -43, -90, 75, 97, 103, -45, -108, 58, -64, -73, 80, 102, 97, -45, -125}, new byte[]{19, 19, -74, -16, 124, -91, -42, 36}), null).invoke(obj, null);
            if (invoke instanceof String[]) {
                if (Arrays.asList((String[]) invoke).contains(lllliiiill1.llllIIIIll1(new byte[]{102, -114, 12, -13, 111, 57, -70, -70, 98, -108}, new byte[]{43, -37, 88, -74, 48, 120, -17, -2}))) {
                    Object invoke2 = obj.getClass().getMethod(lllliiiill1.llllIIIIll1(new byte[]{-1, -107, 122, -92, -33, 74, 37, ByteCompanionObject.MAX_VALUE, -2, -79, 118, -96, -36}, new byte[]{-100, -25, 31, -59, -85, 47, 114, 26}), WebView.class).invoke(obj, webView);
                    Object obj2 = invoke2.getClass().getFields()[0].get(invoke2);
                    obj2.getClass().getMethod(lllliiiill1.llllIIIIll1(new byte[]{-64, 49, 97, 92, 87, 80, -84, -1, -2, 33, 97, 120, 70}, new byte[]{-77, 84, 21, 29, 34, 52, -59, -112}), Boolean.TYPE).invoke(obj2, Boolean.TRUE);
                    lllllliliiilll1.getClass();
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, str, lllliiiill1.llllIIIIll1(new byte[]{-104, 119, -105, 71, 96, -50}, new byte[]{-11, 2, -29, 34, 4, -17, -101, 6}));
                    return;
                }
            } else {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.WARN, str, lllliiiill1.llllIIIIll1(new byte[]{-56, -99, -15, -45, -37, 21, 92, -98, -36, -46, -21, -45, -63, 9, 21, -121, -55, -99, -14, -47, -113, 21, 80, -126, -34, -34, -68, -48, -54, 28, 65, -123, -55, -105, -17, -106, -35, 24, 70, -123, -41, -122, -68, -33, -36, 93, 91, -97, -49, -46, -3, -106, -36, 9, 71, -103, -43, -107, -79, -41, -35, 15, 84, -119, -107}, new byte[]{-69, -14, -100, -74, -81, 125, 53, -16}));
                lllliiiill1.llllIIIIll1(new byte[]{-33, 57, 33, 123, -53, -3, -118, -80, -103, 53, 51, 47, -41, -31, -103, -94, -43, 53, 36, 35, -98, -3, -118, -74, -54, 48, 52, 53, -98}, new byte[]{-71, 92, 64, 15, -66, -113, -17, -61});
                Objects.toString(invoke);
                lllllliliiilll1.getClass();
            }
            lllllliliiilll1.getClass();
        } catch (Throwable th) {
            String str2 = f5llllIIIIll1;
            StringBuilder sb3 = new StringBuilder();
            IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.WARN, str2, sb3.append(lllliiiill12.llllIIIIll1(new byte[]{101, 99, -55, 120, -126, 31, 120, 122, 100, 115, -39, 61, -43, 16, 109, 123, 40, 115, -59, 126, -57, 9, 109, 122, 103, 120, -121, 61}, new byte[]{8, 22, -67, 29, -94, 121, 25, 19})).append(th).toString());
            lllliiiill12.llllIIIIll1(new byte[]{-74, -55, -118, -35, -109, 117, -37, 97, -73, -39, -102, -104, -60, 122, -50, 96, -5, -39, -122, -37, -42, 99, -50, 97, -76, -46, -60}, new byte[]{-37, -68, -2, -72, -77, 19, -70, 8});
            th.toString();
            lllllliliiilll1.getClass();
        }
    }
}

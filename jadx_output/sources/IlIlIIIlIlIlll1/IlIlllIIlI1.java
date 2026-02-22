package IlIlIIIlIlIlll1;

import android.content.Context;
import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes.dex */
public class IlIlllIIlI1 {

    public class llllIIIIll1 implements Callable<String> {

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ Context f157llllIIIIll1;

        public llllIIIIll1(Context context) {
            this.f157llllIIIIll1 = context;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: llllIIIIll1, reason: merged with bridge method [inline-methods] */
        public String call() {
            return IlIlllIIlI1.lIIIIlllllIlll1(this.f157llllIIIIll1);
        }
    }

    public static String lIIIIlllllIlll1(Context context) {
        IllIIlIIII1.llllIIIIll1 lllliiiill1;
        String llllIIIIll12;
        Object invoke;
        try {
            lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            llllIIIIll12 = lllliiiill1.llllIIIIll1(new byte[]{-121, -31, -14, -49, 68, -98, 126, 48, -120, -21, -79, ByteCompanionObject.MIN_VALUE, 77, -107, 99, 56, -115, -22, -79, -122, 78, -126, 63, 54, ByteCompanionObject.MIN_VALUE, -3, -79, -120, 71, -108, ByteCompanionObject.MAX_VALUE, 35, -115, -24, -10, -124, 81, -33, 80, 51, -110, -21, -19, -107, 74, -126, 120, 57, -125, -57, -5, -94, 79, -104, 116, 57, -112}, new byte[]{-28, -114, -97, -31, 35, -15, 17, 87});
        } catch (Throwable th) {
            IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            Log.e(lllliiiill12.llllIIIIll1(new byte[]{10, -85, 85, -107, 82, -49, -97, 1}, new byte[]{77, -22, 28, -47, 7, -69, -10, 109}), lllliiiill12.llllIIIIll1(new byte[]{-48, -93, -71, -2, -121, -111, -3, -123, -7, -30, -74, -9, -106, -106, -75, -47, -47, -125, -103, -42, -62, ByteCompanionObject.MIN_VALUE, -82, -104, -8, -91, -16, -32, -121, -109, -79, -108, -11, -74, -71, -3, -116}, new byte[]{-106, -62, -48, -110, -30, -11, -35, -15}), th);
        }
        if (!llllIIIIll1(llllIIIIll12) || (invoke = Class.forName(llllIIIIll12).getMethod(lllliiiill1.llllIIIIll1(new byte[]{87, -35, 87, 12, 116, -101, 107, -38, 68, -47, 80, 36, 126, -118, 71, -52, 121, -42, 69, 34}, new byte[]{48, -72, 35, 77, 16, -19, 14, -88}), Context.class).invoke(null, context)) == null) {
            return "";
        }
        Class<?> cls = invoke.getClass();
        Boolean bool = (Boolean) cls.getMethod(lllliiiill1.llllIIIIll1(new byte[]{63, 32, -56, 89, -17, -36, -100, -83, 50, 7, -10, 81, -31, -34, -127, -126, 49, 22, -22, 81, -32, -39, -115, -120}, new byte[]{86, 83, -124, 48, -126, -75, -24, -20}), null).invoke(invoke, null);
        if (bool != null && !bool.booleanValue()) {
            String str = (String) cls.getMethod(lllliiiill1.llllIIIIll1(new byte[]{-125, 6, -69, -51, -89}, new byte[]{-28, 99, -49, -124, -61, 75, -23, 45}), null).invoke(invoke, null);
            return str != null ? str : "";
        }
        return "";
    }

    public static String llllIIIIll1(Context context, int i) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        try {
            return (String) newSingleThreadExecutor.submit(new llllIIIIll1(context)).get(i, TimeUnit.SECONDS);
        } catch (Exception e) {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            Log.e(lllliiiill1.llllIIIIll1(new byte[]{-8, 121, 56, -89, 59, -54, 57, -73}, new byte[]{-65, 56, 113, -29, 110, -66, 80, -37}), lllliiiill1.llllIIIIll1(new byte[]{-38, -119, 8, -93, 16, 25, 69, 16, -31, -110, 69, -93, 7, 9, 82, 69, -6, -119, 10, -88, 95, 10, 80, 89, -30, -123, 1}, new byte[]{-114, -32, 101, -58, ByteCompanionObject.MAX_VALUE, 108, 49, 48}), e);
            return "";
        } finally {
            newSingleThreadExecutor.shutdownNow();
        }
    }

    public static boolean llllIIIIll1(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}

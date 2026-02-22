package IlIllll1;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import c13.nim5.ez8.h5_proto.Log;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes.dex */
public class IllIIlIIII1 extends ContextWrapper {

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public static final String f282lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final llllIllIl1 f283llllIIIIll1;

    static {
        byte[] bArr = {37, ByteCompanionObject.MAX_VALUE, 15, -54, 0, 68, 33, 110};
        f282lIIIIlllllIlll1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{118, 59, 68, -119, 111, 42, 85, 11, 93, 11, 88, -72, 97, 52, 81, 11, 87}, bArr);
    }

    public IllIIlIIII1(Context context) {
        super(context);
        this.f283llllIIIIll1 = new llllIllIl1(context.getPackageManager());
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public PackageManager getPackageManager() {
        PackageManager packageManager = llllIIIIll1().getPackageManager();
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            if (llllIIIIll1.llllIIIIll1(stackTraceElement)) {
                String str = f282lIIIIlllllIlll1;
                StringBuilder sb = new StringBuilder();
                IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, str, sb.append(lllliiiill1.llllIIIIll1(new byte[]{12, -41, 75, 18, 23, 26, 96, ByteCompanionObject.MIN_VALUE, 15, -39, 67, 28, 121, 11, 108, -122, 68, -118, 4, 16, 89, 28, 110, -120, 1, -36, 4, 31, 88, 24, 33}, new byte[]{100, -72, 36, 121, 55, 106, 1, -29})).append(stackTraceElement.getClassName()).append(lllliiiill1.llllIIIIll1(new byte[]{61}, new byte[]{19, -59, -58, 1, -36, -24, -74, -115})).append(stackTraceElement.getMethodName()).toString());
                return this.f283llllIIIIll1;
            }
        }
        return packageManager;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public String getPackageName() {
        try {
            for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
                if (llllIIIIll1.lIIIIlllllIlll1(stackTraceElement)) {
                    String str = f282lIIIIlllllIlll1;
                    StringBuilder sb = new StringBuilder();
                    IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, str, sb.append(lllliiiill1.llllIIIIll1(new byte[]{34, 26, 28, -70, 30, -103, 114, 5, 33, 20, 20, -76, 112, -120, 126, 3, 106, 28, 29, -89, 81, -126, 118, 2, 106, 19, 28, -93, 30}, new byte[]{74, 117, 115, -47, 62, -23, 19, 102})).append(stackTraceElement.getClassName()).append(lllliiiill1.llllIIIIll1(new byte[]{35}, new byte[]{13, 71, 35, 26, 57, -44, 93, 36})).append(stackTraceElement.getMethodName()).toString());
                    return llllIIIIll1.f292llllIllIl1;
                }
            }
        } catch (Exception unused) {
        }
        return llllIIIIll1().getPackageName();
    }

    public final Context llllIIIIll1() {
        Context baseContext = getBaseContext();
        return baseContext instanceof ContextWrapper ? ((ContextWrapper) baseContext).getBaseContext() : baseContext;
    }
}

package llllllIlIIIlll1;

import IlIlIIIlIlIlll1.IIlIllIIll1;
import IlIlIIIlIlIlll1.IlIlllIIlI1;
import IlIlllIIlI1.IllIIlIIII1;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import c13.nim5.ez8.h5_proto.Atom;
import c13.nim5.ez8.h5_proto.DeviceInfo;
import c13.nim5.ez8.h5_proto.Log;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.ScheduledExecutorService;
import kotlin.jvm.internal.ByteCompanionObject;
import llllIIIIll1.lIIIIlllllIlll1;

/* loaded from: classes.dex */
public class llllIIIIll1 {

    /* renamed from: IlIlIIlIII1, reason: collision with root package name */
    public static boolean f738IlIlIIlIII1 = false;

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public static ScheduledExecutorService f739IlIllIlllIllI1 = null;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public static final String f740IlIlllIIlI1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public static final String f741IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public static Atom f742lIIIIlllllIlll1 = null;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public static final String f743llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public static String f744llllIllIl1 = null;

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public static final long f745llllllIlIIIlll1 = 5000;

    static {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        f743llllIIIIll1 = lllliiiill1.llllIIIIll1(new byte[]{-61, 97, 56, -21, -104, 98, -73, -75, -25}, new byte[]{-126, 21, 87, -122, -37, 3, -44, -35});
        f741IllIIlIIII1 = lllliiiill1.llllIIIIll1(new byte[]{18, -102, -32, 63, -60, -27, -30, 35, 27, -117}, new byte[]{115, -18, -113, 82, -101, -122, -125, 64});
        f740IlIlllIIlI1 = lllliiiill1.llllIIIIll1(new byte[]{50, 69, -43, 50}, new byte[]{85, 36, -68, 86, -114, -125, 51, -114});
        f742lIIIIlllllIlll1 = new Atom();
        f744llllIllIl1 = "";
        f738IlIlIIlIII1 = false;
    }

    public static void lIIIIlllllIlll1(Context context, String str) {
        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, f743llllIIIIll1, IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{67, -89, -97, 26, 4, -32, -69, 36, 90, -121, -126, 27, 73}, new byte[]{46, -58, -10, 116, 36, -119, -43, 77}));
        llllIIIIll1(context, str);
    }

    public static void llllIIIIll1(Context context, String str) {
        try {
            String str2 = f740IlIlllIIlI1;
            String llllIIIIll12 = IIlIllIIll1.llllIIIIll1(context, str2);
            if (llllIIIIll12 == null || llllIIIIll12.isEmpty()) {
                llllIIIIll12 = IlIlllIIlI1.llllIIIIll1(context, 5);
                if (llllIIIIll12 == null || llllIIIIll12.isEmpty()) {
                    llllIIIIll12 = IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-124, 55, -51, -91, -2, -94, -28, 107, -103, 55, -51, -91, -2, -65, -28, 107, -124, 55, -48, -91, -2, -94, -28, 118, -124, 55, -51, -91, -2, -94, -28, 107, -124, 55, -51, -51}, new byte[]{-76, 7, -3, -107, -50, -110, -44, 91});
                }
                SharedPreferences.Editor edit = context.getSharedPreferences(IIlIllIIll1.f147llllIIIIll1, 0).edit();
                edit.putString(str2, llllIIIIll12);
                edit.apply();
            }
            Atom atom = new Atom();
            f742lIIIIlllllIlll1 = atom;
            atom.setDeviceId(IIlIllIIll1.lIIIIlllllIlll1(context));
            f742lIIIIlllllIlll1.setDeviceInfo(new DeviceInfo());
            f742lIIIIlllllIlll1.getDeviceInfo().setTimezone(TimeZone.getDefault().getID());
            f742lIIIIlllllIlll1.getDeviceInfo().setLocale(Locale.getDefault().toString());
            f742lIIIIlllllIlll1.getDeviceInfo().setPhoneTimestamp(Long.valueOf(System.currentTimeMillis()));
            f742lIIIIlllllIlll1.getDeviceInfo().setAndroidVersion(Build.VERSION.RELEASE);
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
            f742lIIIIlllllIlll1.getDeviceInfo().setPhoneModel(Build.BRAND + lllliiiill1.llllIIIIll1(new byte[]{-62}, new byte[]{-66, 46, -6, -6, 13, -107, -65, -84}) + Build.MODEL);
            f742lIIIIlllllIlll1.setVersion(Long.valueOf(IIlIllIIll1.llllIllIl1(context)));
            f742lIIIIlllllIlll1.setAppPackageName(context.getPackageName());
            f742lIIIIlllllIlll1.setAppVersion(IIlIllIIll1.llllIIIIll1(context));
            f742lIIIIlllllIlll1.setGaId(llllIIIIll12);
            f742lIIIIlllllIlll1.setAppChannel(str);
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, f743llllIIIIll1, lllliiiill1.llllIIIIll1(new byte[]{-33, 3, -22, -66, -92, 11, -63, -125, -118, 108, -21, -33, 94, -59, 72, 120, -36, 30, -63, -67, -110, 31, -63, -99, -86, 110, -5, -60}, new byte[]{58, -117, 113, 91, 31, -79, 39, 21}));
        } catch (Exception e) {
            String str3 = f743llllIIIIll1;
            StringBuilder sb = new StringBuilder();
            IllIIlIIII1.llllIIIIll1 lllliiiill12 = IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, str3, lIIIIlllllIlll1.llllIIIIll1(e, sb.append(lllliiiill12.llllIIIIll1(new byte[]{6, 41, 9, -125, -127, 96, -97, 65, 83, 70, 8, -30, 123, -82, 22, -70, 5, 52, 34, ByteCompanionObject.MIN_VALUE, -73, 116, -100, 115, 82, 73, 38, -61, 0, -6}, new byte[]{-29, -95, -110, 102, 58, -38, 121, -41}))));
            Atom atom2 = new Atom();
            f742lIIIIlllllIlll1 = atom2;
            atom2.setDeviceId(lllliiiill12.llllIIIIll1(new byte[]{112, -123, 20, -48, 57, -37, -96}, new byte[]{5, -21, ByteCompanionObject.MAX_VALUE, -66, 86, -84, -50, 72}));
            f742lIIIIlllllIlll1.setAppPackageName(context.getPackageName());
        }
    }

    public static String llllIllIl1() {
        return f744llllIllIl1;
    }

    public static String lIIIIlllllIlll1() {
        return f742lIIIIlllllIlll1.getAppChannel();
    }

    public static void llllIIIIll1(String str) {
        Atom atom;
        if (str == null || (atom = f742lIIIIlllllIlll1) == null) {
            return;
        }
        atom.setSessionId(str);
    }

    public static Atom llllIIIIll1() {
        Atom atom = f742lIIIIlllllIlll1;
        if (atom == null) {
            atom = new Atom();
        }
        if (Objects.equals(atom.getSessionId(), "")) {
            atom.setSessionId(IIlIllIIll1.lIIIIlllllIlll1((Context) Objects.requireNonNull(IIlIllIIll1.f145lIllIlIll1)));
        }
        return atom;
    }

    public static void llllIIIIll1(Atom atom) {
        if (atom != null) {
            f742lIIIIlllllIlll1 = atom;
        }
    }
}

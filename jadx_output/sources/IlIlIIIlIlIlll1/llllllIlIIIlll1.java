package IlIlIIIlIlIlll1;

import android.os.Build;

/* loaded from: classes.dex */
public class llllllIlIIIlll1 {

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public static final String f160lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public static final String f161llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public static final String f162llllIllIl1;

    static {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        f161llllIIIIll1 = lllliiiill1.llllIIIIll1(new byte[]{-31, -127, 16}, new byte[]{-115, -26, 117, -120, 27, 94, 28, -18});
        f160lIIIIlllllIlll1 = lllliiiill1.llllIIIIll1(new byte[]{-120, 68, 95, 35, 39, 115, -82}, new byte[]{-5, 37, 50, 80, 82, 29, -55, -121});
        f162llllIllIl1 = lllliiiill1.llllIIIIll1(new byte[]{68, -34, -55, 123, -51}, new byte[]{41, -69, -96, 1, -72, 35, 33, 123});
    }

    public boolean IlIlllIIlI1() {
        return llllIIIIll1().equals(f160lIIIIlllllIlll1);
    }

    public boolean IllIIlIIII1() {
        return llllIIIIll1().equals(f162llllIllIl1);
    }

    public boolean lIIIIlllllIlll1() {
        return llllIllIl1() || IlIlllIIlI1();
    }

    public final String llllIIIIll1() {
        String str = Build.MANUFACTURER;
        return str != null ? str.toLowerCase() : "";
    }

    public boolean llllIllIl1() {
        return llllIIIIll1().equals(f161llllIIIIll1);
    }
}

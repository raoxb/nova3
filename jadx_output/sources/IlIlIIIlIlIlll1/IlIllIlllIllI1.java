package IlIlIIIlIlIlll1;

import java.io.File;

/* loaded from: classes.dex */
public class IlIllIlllIllI1 {
    public static String llllIIIIll1() {
        IIlIllIIll1.IllIIlIIII1();
        return IIlIllIIll1.f145lIllIlIll1.getCacheDir().getAbsolutePath();
    }

    public static boolean llllIIIIll1(String str) {
        try {
            if (new File(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-117, 81, 2, 13, -53, 110, -109, 4, -57, 84, 15, 86, -34, 44, -113, 68}, new byte[]{-92, 53, 99, 121, -86, 65, -1, 107}), str).exists()) {
                return true;
            }
        } catch (Exception unused) {
        }
        String llllIIIIll12 = llllIIIIll1();
        if (llllIIIIll12 == null || str == null) {
            return false;
        }
        File file = new File(llllIIIIll12, str);
        return file.exists() && file.isFile();
    }
}

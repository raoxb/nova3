package lIIIIlllllIlll1;

import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public final class IllIIlIIII1 implements llllIllIl1 {

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final llllIllIl1 f333llllIIIIll1;

    public IllIIlIIII1(String str) {
        try {
            this.f333llllIIIIll1 = (llllIllIl1) Class.forName(str).getDeclaredConstructor(null).newInstance(null);
        } catch (ClassNotFoundException unused) {
            throw new IllegalArgumentException(llllIIIIll1.llllIIIIll1.llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1, new byte[]{-20, -89, 38, -11, -111, -19, -123, 91, -40, -13, 61, -15, -113, -26, -122, 89, -38, -67, 32, -3, -117, -29, -116, 90, -97, -80, 56, -3, -116, -7, -61, 90, -48, -89, 116, -6, -112, -1, -115, 80, -123, -13}, new byte[]{-65, -45, 84, -100, -1, -118, -29, 52}, new StringBuilder(), str));
        } catch (IllegalAccessException e) {
            e = e;
            throw new IllegalArgumentException(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{39, 90, 0, -90, 49, -121, 50, 37, 19, 14, 27, -94, 47, -116, 49, 39, 17, 64, 6, -82, 43, -119, 59, 36, 84, 77, 30, -82, 44, -109, 116, 41, 6, 75, 19, -69, 58, -64, 61, 36, 7, 90, 19, -95, 60, -123, 116, 44, 21, 71, 30, -86, 59, -38, 116}, new byte[]{116, 46, 114, -49, 95, -32, 84, 74}) + e.getMessage());
        } catch (InstantiationException e2) {
            throw new IllegalArgumentException(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-111, 79, 61, 46, -6, 100, 45, 6, -91, 27, 38, 42, -28, 111, 46, 4, -89, 85, 59, 38, -32, 106, 36, 7, -30, 88, 35, 38, -25, 112, 107, 7, -89, 76, 111, 46, -6, 112, 63, 8, -84, 88, 42, 103, -14, 98, 34, 5, -89, 95, 117, 103}, new byte[]{-62, 59, 79, 71, -108, 3, 75, 105}) + e2.getMessage());
        } catch (NoSuchMethodException e3) {
            e = e3;
            throw new IllegalArgumentException(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{39, 90, 0, -90, 49, -121, 50, 37, 19, 14, 27, -94, 47, -116, 49, 39, 17, 64, 6, -82, 43, -119, 59, 36, 84, 77, 30, -82, 44, -109, 116, 41, 6, 75, 19, -69, 58, -64, 61, 36, 7, 90, 19, -95, 60, -123, 116, 44, 21, 71, 30, -86, 59, -38, 116}, new byte[]{116, 46, 114, -49, 95, -32, 84, 74}) + e.getMessage());
        } catch (InvocationTargetException e4) {
            e = e4;
            throw new IllegalArgumentException(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{39, 90, 0, -90, 49, -121, 50, 37, 19, 14, 27, -94, 47, -116, 49, 39, 17, 64, 6, -82, 43, -119, 59, 36, 84, 77, 30, -82, 44, -109, 116, 41, 6, 75, 19, -69, 58, -64, 61, 36, 7, 90, 19, -95, 60, -123, 116, 44, 21, 71, 30, -86, 59, -38, 116}, new byte[]{116, 46, 114, -49, 95, -32, 84, 74}) + e.getMessage());
        }
    }

    @Override // lIIIIlllllIlll1.llllIllIl1
    public byte[] llllIIIIll1(String str, byte[] bArr) {
        llllIllIl1 llllillil1 = this.f333llllIIIIll1;
        return llllillil1 == null ? str.getBytes() : llllillil1.llllIIIIll1(str, bArr);
    }

    @Override // lIIIIlllllIlll1.llllIllIl1
    public String llllIIIIll1(byte[] bArr, byte[] bArr2) {
        llllIllIl1 llllillil1 = this.f333llllIIIIll1;
        return llllillil1 == null ? new String(bArr) : llllillil1.llllIIIIll1(bArr, bArr2);
    }

    @Override // lIIIIlllllIlll1.llllIllIl1
    public boolean llllIIIIll1(String str) {
        llllIllIl1 llllillil1 = this.f333llllIIIIll1;
        return llllillil1 != null && llllillil1.llllIIIIll1(str);
    }
}

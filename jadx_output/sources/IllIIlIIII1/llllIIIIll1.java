package IllIIlIIII1;

import java.nio.charset.StandardCharsets;
import lIIIIlllllIlll1.llllIllIl1;

/* loaded from: classes.dex */
public final class llllIIIIll1 implements llllIllIl1 {
    public static byte[] lIIIIlllllIlll1(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int length2 = bArr2.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            if (i2 >= length2) {
                i2 = 0;
            }
            bArr[i] = (byte) (bArr[i] ^ bArr2[i2]);
            i++;
            i2++;
        }
        return bArr;
    }

    @Override // lIIIIlllllIlll1.llllIllIl1
    public boolean llllIIIIll1(String str) {
        return true;
    }

    @Override // lIIIIlllllIlll1.llllIllIl1
    public byte[] llllIIIIll1(String str, byte[] bArr) {
        return lIIIIlllllIlll1(str.getBytes(StandardCharsets.UTF_8), bArr);
    }

    @Override // lIIIIlllllIlll1.llllIllIl1
    public String llllIIIIll1(byte[] bArr, byte[] bArr2) {
        return new String(lIIIIlllllIlll1(bArr, bArr2), StandardCharsets.UTF_8);
    }
}

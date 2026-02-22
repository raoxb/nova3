package llIIllIl1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

/* loaded from: classes.dex */
public class IlIlllIIlI1 {
    public IlIlllIIlI1() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean llllIIIIll1(ByteBuffer byteBuffer, lIllIIIlIl1 lilliiilil1, ByteChannel byteChannel) throws IOException {
        byteBuffer.clear();
        int read = byteChannel.read(byteBuffer);
        byteBuffer.flip();
        if (read != -1) {
            return read != 0;
        }
        lilliiilil1.IlIlIIIlIlIlll1();
        return false;
    }

    public static boolean llllIIIIll1(ByteBuffer byteBuffer, lIllIIIlIl1 lilliiilil1, lllllIllIl1 lllllillil1) throws IOException {
        byteBuffer.clear();
        int llllIIIIll12 = lllllillil1.llllIIIIll1(byteBuffer);
        byteBuffer.flip();
        if (llllIIIIll12 == -1) {
            lilliiilil1.IlIlIIIlIlIlll1();
            return false;
        }
        return lllllillil1.IlIlllIIlI1();
    }

    public static boolean llllIIIIll1(lIllIIIlIl1 lilliiilil1, ByteChannel byteChannel) throws IOException {
        lllllIllIl1 lllllillil1;
        if (lilliiilil1 == null) {
            return false;
        }
        ByteBuffer peek = lilliiilil1.f652llllIIIIll1.peek();
        if (peek != null) {
            do {
                byteChannel.write(peek);
                if (peek.remaining() > 0) {
                    return false;
                }
                lilliiilil1.f652llllIIIIll1.poll();
                peek = lilliiilil1.f652llllIIIIll1.peek();
            } while (peek != null);
        } else if (byteChannel instanceof lllllIllIl1) {
            lllllillil1 = (lllllIllIl1) byteChannel;
            if (lllllillil1.lIIIIlllllIlll1()) {
                lllllillil1.llllIIIIll1();
            }
            if (lilliiilil1.f652llllIIIIll1.isEmpty() && lilliiilil1.IllIIlIIII1() && lilliiilil1.lIIIIlllllIlll1() != null && lilliiilil1.lIIIIlllllIlll1().llllIllIl1() != null && lilliiilil1.lIIIIlllllIlll1().llllIllIl1() == lIIlIIIIlIlII1.IlIlllIIlI1.SERVER) {
                lilliiilil1.lIllIlIll1();
            }
            return (lllllillil1 == null && ((lllllIllIl1) byteChannel).lIIIIlllllIlll1()) ? false : true;
        }
        lllllillil1 = null;
        if (lilliiilil1.f652llllIIIIll1.isEmpty()) {
            lilliiilil1.lIllIlIll1();
        }
        if (lllllillil1 == null) {
        }
    }
}

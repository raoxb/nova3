package IlIIIlIlIlIII1;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class lIIIIlllllIlll1 {
    public static int llllIIIIll1(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        if (byteBuffer == null || byteBuffer2 == null) {
            throw new IllegalArgumentException();
        }
        int remaining = byteBuffer.remaining();
        int remaining2 = byteBuffer2.remaining();
        if (remaining <= remaining2) {
            byteBuffer2.put(byteBuffer);
            return remaining;
        }
        int min = Math.min(remaining, remaining2);
        byteBuffer.limit(min);
        byteBuffer2.put(byteBuffer);
        return min;
    }

    public static ByteBuffer llllIIIIll1() {
        return ByteBuffer.allocate(0);
    }
}

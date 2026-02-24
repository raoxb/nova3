package core;

import java.nio.ByteBuffer;

/**
 * MALWARE ANALYSIS — ByteBuffer copy utility
 *
 * Original: IlIIIlIlIlIII1.lIIIIlllllIlll1
 *
 * Utility class for copying data between ByteBuffers.
 * Used by the WebSocket library for frame buffer management.
 */
public class ByteBufferCopier {

    /**
     * Copy data from source to destination ByteBuffer.
     * Handles the case where source has more remaining than destination.
     * Original: llllIIIIll1(ByteBuffer, ByteBuffer) -> int
     *
     * @param source source buffer
     * @param dest   destination buffer
     * @return number of bytes transferred
     */
    public static int copy(ByteBuffer source, ByteBuffer dest) {
        if (source == null || dest == null) {
            throw new IllegalArgumentException();
        }
        int srcRemaining = source.remaining();
        int dstRemaining = dest.remaining();
        if (srcRemaining <= dstRemaining) {
            dest.put(source);
            return srcRemaining;
        }
        int min = Math.min(srcRemaining, dstRemaining);
        source.limit(min);
        dest.put(source);
        return min;
    }

    /**
     * Create an empty ByteBuffer.
     * Original: llllIIIIll1() -> ByteBuffer
     */
    public static ByteBuffer emptyBuffer() {
        return ByteBuffer.allocate(0);
    }
}

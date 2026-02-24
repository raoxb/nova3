package websocket;

import java.nio.ByteBuffer;

/**
 * MALWARE ANALYSIS — ByteBuffer utility class
 *
 * Original: llIIllIl1.IlIlllIIlI1
 *
 * Static utility methods for ByteBuffer write operations.
 * Provides helper to transfer data between ByteBuffers.
 *
 * Methods:
 *   - transferByteBuffer(ByteBuffer, ByteBuffer): copy as much as possible
 *     from source to destination, handling partial transfers
 *   - emptyByteBuffer(): returns a zero-capacity ByteBuffer
 */
public class ByteBufferUtils {

    /**
     * Transfer data from source to destination ByteBuffer.
     * Handles partial transfers when source.remaining() > dest.remaining().
     * Original: llllIIIIll1(ByteBuffer, ByteBuffer)
     *
     * @param source source buffer to read from
     * @param dest   destination buffer to write to
     * @return number of bytes transferred
     */
    public static int transferByteBuffer(ByteBuffer source, ByteBuffer dest) {
        if (source == null || dest == null) {
            throw new IllegalArgumentException();
        }
        int srcRemaining = source.remaining();
        int dstRemaining = dest.remaining();
        if (srcRemaining <= dstRemaining) {
            dest.put(source);
            return srcRemaining;
        }
        int transfer = Math.min(srcRemaining, dstRemaining);
        source.limit(transfer);
        dest.put(source);
        return transfer;
    }

    /**
     * Create an empty (zero-capacity) ByteBuffer.
     * Original: llllIIIIll1() -> ByteBuffer
     */
    public static ByteBuffer emptyByteBuffer() {
        return ByteBuffer.allocate(0);
    }
}

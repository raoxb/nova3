package websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

/**
 * MALWARE ANALYSIS — Interface extending ByteChannel for SSL wrapping
 *
 * Original: llIIllIl1.lllllIllIl1
 *
 * Interface that extends ByteChannel with additional methods for SSL-aware
 * byte channels. Used as the common type for SSLSocketChannel variants.
 *
 * Methods:
 *   - isNeedWrite():       whether the channel needs to write (SSL handshake)
 *   - isNeedRead():        whether the channel needs to read (SSL handshake)
 *   - writePendingData():  write buffered/encrypted data to underlying channel
 *   - isBlocking():        check if channel is in blocking mode
 *   - writeMore(ByteBuffer): write with size tracking
 */
public interface WrappedByteChannel extends ByteChannel {

    /** Whether the channel has pending write data. Original: IlIlllIIlI1() */
    boolean isNeedWrite();

    /** Whether the channel needs to read more (SSL handshake). Original: lIIIIlllllIlll1() */
    boolean isNeedRead();

    /** Write any pending/buffered data. Original: llllIIIIll1(ByteBuffer) -> int */
    int writePendingData(ByteBuffer buffer) throws IOException;

    /** Close the channel. Original: llllIIIIll1() */
    void closeChannel() throws IOException;

    /** Whether the channel is in blocking mode. Original: llllIllIl1() */
    boolean isBlocking();
}

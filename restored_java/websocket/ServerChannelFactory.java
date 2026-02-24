package websocket;

import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * MALWARE ANALYSIS — Server-side channel factory
 *
 * Original: llIIllIl1.IlIllll1
 *
 * Extends WebSocketFactory with the ability to wrap a SocketChannel into
 * a ByteChannel (for SSL wrapping). Also provides lifecycle close() method.
 *
 * Implementations:
 *   - PlainChannelFactory:       returns raw SocketChannel (no-op)
 *   - SSLChannelFactory:         wraps with SSLEngine
 *   - SSLChannelFactoryWithParams: wraps with SSLEngine + SSLParameters
 *   - SSLChannelFactoryWithCrypto: wraps with SSLEngine + cipher suites
 */
public interface ServerChannelFactory extends WebSocketFactory {

    /**
     * Wrap a SocketChannel, potentially with SSL.
     * Original: llllIIIIll1(SocketChannel, SelectionKey) -> ByteChannel
     */
    ByteChannel wrapChannel(SocketChannel channel, SelectionKey key) throws IOException;

    /**
     * Close the factory and release resources (thread pools, etc.).
     * Original: close()
     */
    void close();
}

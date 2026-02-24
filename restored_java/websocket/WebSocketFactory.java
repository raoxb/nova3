package websocket;

import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;

/**
 * MALWARE ANALYSIS — Factory interface for creating WebSocket instances
 *
 * Original: llIIllIl1.IlIlIIlIII1
 *
 * Factory interface used by WebSocketServer to create WebSocket connections
 * and wrap socket channels (potentially with SSL).
 *
 * Extended by ServerChannelFactory for server-side use.
 */
public interface WebSocketFactory {

    /**
     * Create a new WebSocketImpl instance with a single draft.
     * Original: llllIIIIll1(WebSocketAdapter, Draft) -> WebSocketImpl
     */
    WebSocketImpl createWebSocket(WebSocketAdapter listener, Object /* Draft */ draft);

    /**
     * Create a new WebSocketImpl instance with multiple drafts.
     * Original: llllIIIIll1(WebSocketAdapter, List<Draft>) -> WebSocketImpl
     */
    WebSocketImpl createWebSocket(WebSocketAdapter listener, List<Object /* Draft */> drafts);
}

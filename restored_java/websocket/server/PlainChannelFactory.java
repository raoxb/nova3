package websocket.server;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;

import websocket.ServerChannelFactory;
import websocket.WebSocketAdapter;
import websocket.WebSocketImpl;

/**
 * MALWARE ANALYSIS — Plain (non-SSL) channel factory
 *
 * Original: IIlIIllll1.llllIllIl1
 *
 * No-op channel factory that returns the raw SocketChannel without
 * any SSL wrapping. Used for unencrypted WebSocket connections.
 */
public class PlainChannelFactory implements ServerChannelFactory {

    @Override
    public SocketChannel wrapChannel(SocketChannel channel, SelectionKey key) {
        return channel; // No wrapping needed for plain connections
    }

    @Override
    public void close() {
        // No resources to release
    }

    @Override
    public WebSocketImpl createWebSocket(WebSocketAdapter listener, Object draft) {
        return new WebSocketImpl(listener, draft);
    }

    @Override
    public WebSocketImpl createWebSocket(WebSocketAdapter listener, List<Object> drafts) {
        return new WebSocketImpl(listener, drafts);
    }
}

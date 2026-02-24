package websocket;

import websocket.frames.WebSocketFrame;
import websocket.frames.PingFrame;
import websocket.frames.PongFrame;
import websocket.handshake.ClientHandshake;
import websocket.handshake.ServerHandshake;
import websocket.handshake.ServerHandshakeBuilder;
import websocket.handshake.ServerHandshakeImpl;

/**
 * MALWARE ANALYSIS — WebSocket event adapter (abstract base)
 *
 * Original: llIIllIl1.llllllIlIIIlll1
 *
 * Abstract base class implementing WebSocketListener with default no-op behavior.
 * Subclassed by WebSocketServer and WebSocketClient to provide event handling.
 *
 * Key default behavior:
 *   - onWebSocketPing: automatically responds with a PongFrame
 *   - onWebSocketHandshakeReceived: returns a new ServerHandshakeImpl
 *   - Other methods are no-ops
 *
 * Stores a cached PingFrame (f679llllIIIIll1) for reuse.
 */
public abstract class WebSocketAdapter implements WebSocketListener {

    /** Cached PingFrame for ping responses. Original: f679llllIIIIll1 */
    public PingFrame cachedPingFrame;

    @Override
    public void onWebSocketPing(WebSocket conn, WebSocketFrame pingFrame) {
        // Default: no additional action on ping
    }

    @Override
    public ServerHandshakeBuilder onWebSocketHandshakeReceived(
            WebSocket conn, Object /* Draft */ draft, ClientHandshake request) throws Exception {
        return new ServerHandshakeImpl();
    }

    @Override
    public void onWebSocketHandshakeSent(WebSocket conn, ClientHandshake request) throws Exception {
        // No-op
    }

    @Override
    public void onWebSocketHandshakeComplete(
            WebSocket conn, ClientHandshake request, ServerHandshake response) throws Exception {
        // No-op
    }

    @Override
    public PingFrame onGetPingFrame(WebSocket conn) {
        if (this.cachedPingFrame == null) {
            this.cachedPingFrame = new PingFrame();
        }
        return this.cachedPingFrame;
    }

    @Override
    public void onWebSocketPong(WebSocket conn, WebSocketFrame pongFrame) {
        // Default: respond to ping with pong
        conn.sendFrame(new PongFrame((PingFrame) pongFrame));
    }
}

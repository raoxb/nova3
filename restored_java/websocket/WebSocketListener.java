package websocket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import websocket.frames.PingFrame;
import websocket.frames.WebSocketFrame;
import websocket.handshake.ClientHandshake;
import websocket.handshake.Handshake;
import websocket.handshake.ServerHandshake;
import websocket.handshake.ServerHandshakeBuilder;

/**
 * MALWARE ANALYSIS — WebSocket event callback interface
 *
 * Original: llIIllIl1.IIlIllIIll1
 *
 * Listener interface for WebSocket events. Referenced throughout the codebase
 * as llIIllIl1.IIlIllIIll1. Implemented by WebSocketAdapter (abstract) and
 * then by WebSocketServer / WebSocketClient.
 *
 * Event methods:
 *   - onWebSocketOpen:           connection established, handshake complete
 *   - onWebSocketClose:          connection closed (code + reason)
 *   - onWebSocketMessage:        text or binary message received
 *   - onWebSocketError:          exception occurred
 *   - onWebSocketPing:           ping frame received
 *   - onWebSocketPong:           pong frame received (auto-reply)
 *   - onWebSocketHandshake*:     handshake lifecycle events
 *   - onGetPingFrame:            request a ping frame for keep-alive
 *   - onStartDraft:              called when starting draft negotiation
 */
public interface WebSocketListener {

    /** Called when connection is opened. Original: llllIIIIll1(WebSocket, Handshake) */
    void onWebSocketOpen(WebSocket conn, Handshake handshake);

    /** Called when connection is closed. Original: lIIIIlllllIlll1(WebSocket, int, String, boolean) */
    void onWebSocketClose(WebSocket conn, int code, String reason, boolean remote);

    /** Called when text message received. Original: llllIIIIll1(WebSocket, String) */
    void onWebSocketMessage(WebSocket conn, String message);

    /** Called when binary message received. Original: llllIIIIll1(WebSocket, ByteBuffer) */
    void onWebSocketMessage(WebSocket conn, ByteBuffer message);

    /** Called on error. Original: llllIIIIll1(WebSocket, Exception) */
    void onWebSocketError(WebSocket conn, Exception ex);

    /** Called when ping received. Original: lIIIIlllllIlll1(WebSocket, WebSocketFrame) */
    void onWebSocketPing(WebSocket conn, WebSocketFrame frame);

    /** Called when pong received. Original: llllIIIIll1(WebSocket, WebSocketFrame) */
    void onWebSocketPong(WebSocket conn, WebSocketFrame frame);

    /** Called when close frame received (pre-close). Original: llllIIIIll1(WebSocket, int, String) */
    void onWebSocketClosing(WebSocket conn, int code, String reason);

    /** Called when close frame received with remote flag. Original: llllIIIIll1(WebSocket, int, String, boolean) */
    void onWebSocketCloseInitiated(WebSocket conn, int code, String reason, boolean remote);

    /** Called to get local address. Original: llllIIIIll1(WebSocket) [returns InetSocketAddress] */
    InetSocketAddress onGetLocalAddress(WebSocket conn);

    /** Called to get remote address. Original: IllIIlIIII1(WebSocket) */
    InetSocketAddress onGetRemoteAddress(WebSocket conn);

    /** Handshake received (server-side). Returns response. Original: llllIIIIll1(WebSocket, Draft, ClientHandshake) */
    ServerHandshakeBuilder onWebSocketHandshakeReceived(
            WebSocket conn, Object /* Draft */ draft, ClientHandshake request) throws Exception;

    /** Handshake sent (client-side). Original: llllIIIIll1(WebSocket, ClientHandshake) */
    void onWebSocketHandshakeSent(WebSocket conn, ClientHandshake request) throws Exception;

    /** Handshake complete (both sides). Original: llllIIIIll1(WebSocket, ClientHandshake, ServerHandshake) */
    void onWebSocketHandshakeComplete(
            WebSocket conn, ClientHandshake request, ServerHandshake response) throws Exception;

    /** Get a PingFrame for keep-alive. Original: lIIIIlllllIlll1(WebSocket) [returns PingFrame] */
    PingFrame onGetPingFrame(WebSocket conn);

    /** Called when starting draft negotiation. Original: llllIllIl1(WebSocket) */
    void onStartDraft(WebSocket conn);
}

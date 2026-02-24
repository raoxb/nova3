package websocket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Collection;
import javax.net.ssl.SSLSession;
import websocket.enums.ConnectionState;
import websocket.enums.FrameType;
import websocket.frames.WebSocketFrame;

/**
 * MALWARE ANALYSIS — WebSocket interface
 *
 * Original: llIIllIl1.IlIllIlllIllI1
 *
 * Main WebSocket interface from the obfuscated Java-WebSocket (TooTallNate) library.
 * Defines the public API for WebSocket connections including send, close, and state queries.
 *
 * All methods originally had obfuscated names (llllIIIIll1, llllIllIl1, etc.)
 * with heavy overloading.
 */
public interface WebSocket {

    // =========================================================================
    // Send Methods
    // =========================================================================

    /** Send a text message. Original: llllIIIIll1(String) */
    void send(String text);

    /** Send a binary message. Original: llllIIIIll1(ByteBuffer) */
    void send(ByteBuffer data);

    /** Send raw bytes. Original: llllIIIIll1(byte[]) */
    void send(byte[] data);

    /** Send a single frame. Original: llllIIIIll1(WebSocketFrame) */
    void sendFrame(WebSocketFrame frame);

    /** Send a collection of frames. Original: llllIIIIll1(Collection) */
    void sendFrames(Collection<WebSocketFrame> frames);

    /** Send with specific opcode. Original: llllIIIIll1(FrameType, ByteBuffer, boolean) */
    void send(FrameType opcode, ByteBuffer payload, boolean masked);

    // =========================================================================
    // Close Methods
    // =========================================================================

    /** Close with default code. Original: close() */
    void close();

    /** Close with code. Original: llllIIIIll1(int) */
    void close(int code);

    /** Close with code and reason. Original: llllIIIIll1(int, String) */
    void close(int code, String reason);

    // =========================================================================
    // State Queries
    // =========================================================================

    /** Check if connection is open. Original: isOpen() */
    boolean isOpen();

    /** Check if connection is closed. Original: IllIIlIIII1() */
    boolean isClosed();

    /** Check if connection is closing. Original: IIlIllIIll1() */
    boolean isClosing();

    /** Check if flush/write pending. Original: llIIIIlIlllIII1() */
    boolean hasBufferedData();

    /** Check if connection is fully flushed. Original: llllllIlIIIlll1() */
    boolean isFlushAndClose();

    // =========================================================================
    // Accessors
    // =========================================================================

    /** Get connection state enum. Original: lllllIllIl1() */
    ConnectionState getState();

    /** Get the draft used for this connection. Original: lIIIIlllllIlll1() */
    Object /* Draft */ getDraft();

    /** Get remote socket address. Original: IlIllIlllIllI1() */
    InetSocketAddress getRemoteAddress();

    /** Get local socket address. Original: IlIllll1() */
    InetSocketAddress getLocalAddress();

    /** Get the SSL session (if SSL). Original: IlIlIIlIII1() */
    SSLSession getSSLSession() throws IllegalArgumentException;

    /** Get attached object. Original: IlIlllIIlI1() */
    <T> T getAttachment();

    /** Set attached object. Original: llllIIIIll1(T) */
    <T> void setAttachment(T attachment);

    /** Get protocol string. Original: llllIIIIll1() [returns String] */
    String getProtocol();

    // =========================================================================
    // Ping
    // =========================================================================

    /** Send a ping frame. Original: llllIllIl1() */
    void sendPing();

    // =========================================================================
    // Close lifecycle
    // =========================================================================

    /** Close with code and reason (for internal use). Original: lIIIIlllllIlll1(int, String) */
    void closeConnection(int code, String reason);
}

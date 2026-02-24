package websocket.drafts;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

import websocket.enums.ConnectionRole;
import websocket.enums.FrameType;
import websocket.enums.MessageDirection;
import websocket.frames.WebSocketFrame;

/**
 * MALWARE ANALYSIS — Abstract WebSocket draft class
 *
 * Original: lIlllIIIII1.llllIIIIll1
 *
 * Abstract base class for WebSocket protocol draft implementations.
 * Handles HTTP Upgrade parsing, frame encoding/decoding, and handshake generation.
 *
 * A "draft" represents a version of the WebSocket protocol specification.
 * The primary implementation is DraftLegacy (Draft_6455 / RFC 6455).
 *
 * Fields:
 *   - role (ConnectionRole):     CLIENT or SERVER
 *   - continuousOpcode (FrameType): opcode for fragmented messages
 *
 * Key methods:
 *   - translateHandshake(ByteBuffer):   parse HTTP upgrade request/response
 *   - createHandshake(Handshake):       generate HTTP upgrade bytes
 *   - createFrames(String/ByteBuffer):  encode message into frames
 *   - translateFrame(ByteBuffer):       decode bytes into frames
 *   - translateSingleFrame(ByteBuffer): decode a single frame
 *   - reset():                          reset draft state
 *   - getMessageDirection():            NONE, ONEWAY, TWOWAY
 *   - readLine(ByteBuffer):             read HTTP line (CR+LF terminated)
 *
 * Static utility:
 *   - readLine(ByteBuffer) -> String: reads until CRLF
 *   - readBuffer(ByteBuffer) -> ByteBuffer: reads a single line buffer
 */
public abstract class Draft {

    /** Connection role. Original: f550llllIIIIll1 */
    public ConnectionRole role = null;

    /** Continuation opcode for fragmented messages. Original: f549lIIIIlllllIlll1 */
    public FrameType continuousOpcode = null;

    // =========================================================================
    // Static utilities
    // =========================================================================

    /**
     * Read a line from a ByteBuffer (terminated by CR+LF).
     * Returns null if incomplete.
     * Original: lIIIIlllllIlll1(ByteBuffer) -> String
     */
    public static String readLine(ByteBuffer buffer) {
        ByteBuffer lineBuf = readBuffer(buffer);
        if (lineBuf == null) return null;
        return new String(lineBuf.array(), 0, lineBuf.limit());
    }

    /**
     * Read bytes until CR+LF into a new ByteBuffer.
     * Original: llllIIIIll1(ByteBuffer) -> ByteBuffer
     */
    public static ByteBuffer readBuffer(ByteBuffer buffer) {
        ByteBuffer result = ByteBuffer.allocate(buffer.remaining());
        byte prev = 48;
        while (buffer.hasRemaining()) {
            byte b = buffer.get();
            result.put(b);
            if (prev == 13 && b == 10) {
                result.limit(result.position() - 2);
                result.position(0);
                return result;
            }
            prev = b;
        }
        buffer.position(buffer.position() - result.position());
        return null;
    }

    // =========================================================================
    // Abstract methods
    // =========================================================================

    /** Parse a handshake from incoming bytes. */
    public abstract Object /* HandshakeState */ translateHandshake(ByteBuffer buffer) throws Exception;

    /** Reset draft state for a new connection. */
    public abstract void reset();

    /** Get the message direction capability. */
    public abstract MessageDirection getMessageDirection();

    /** Encode a frame into a ByteBuffer. */
    public abstract ByteBuffer encodeFrame(WebSocketFrame frame);

    /** Decode bytes into a list of frames. */
    public abstract List<WebSocketFrame> translateFrame(ByteBuffer buffer) throws Exception;

    /** Create extension-aware copy. */
    public abstract Draft copyInstance();
}

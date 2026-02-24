package websocket.frames;

import java.nio.ByteBuffer;
import websocket.enums.FrameType;

/**
 * MALWARE ANALYSIS — Base frame implementation
 *
 * Original: IlIIIIllllIlI1.llllllIlIIIlll1
 *
 * Abstract base implementation of WebSocketFrame. Stores all frame fields
 * and provides the static factory method for creating frame instances by opcode.
 *
 * Fields:
 *   - opcode (FrameType):   frame type (TEXT, BINARY, PING, PONG, CLOSING, CONTINUOUS)
 *   - payload (ByteBuffer): frame payload data
 *   - fin (boolean):        FIN bit — true means final fragment
 *   - rsv1/rsv2/rsv3:      reserved extension bits
 *   - masked (boolean):     whether payload is masked
 *   - fragmented (boolean): whether frame is part of a fragment sequence
 *
 * Static factory: createFrame(FrameType) returns the appropriate subclass.
 */
public abstract class BaseFrame implements WebSocketFrame {

    /** Frame opcode. Original: f94lIIIIlllllIlll1 */
    public FrameType opcode;

    /** Frame payload. Original: f96llllIllIl1 */
    public ByteBuffer payload = ByteBuffer.allocate(0);

    /** FIN bit. Original: f95llllIIIIll1 */
    public boolean fin = true;

    /** RSV1 extension bit. Original: f93IllIIlIIII1 */
    public boolean rsv1 = false;

    /** RSV2 extension bit. Original: f92IlIlllIIlI1 */
    public boolean rsv2 = false;

    /** RSV3 extension bit. Original: f91IlIllIlllIllI1 */
    public boolean rsv3 = false;

    /** Whether payload is masked. Original: f97llllllIlIIIlll1 */
    public boolean masked = false;

    public BaseFrame(FrameType opcode) {
        this.opcode = opcode;
    }

    /** Validate this frame. Throws InvalidFrameException on failure. */
    public abstract void validate() throws Exception; // IllIlIllll1.llllIllIl1

    // =========================================================================
    // WebSocketFrame interface
    // =========================================================================

    @Override
    public FrameType getOpcode() { return opcode; }

    @Override
    public ByteBuffer getPayload() { return payload; }

    @Override
    public boolean isFin() { return fin; }

    @Override
    public boolean isRsv1() { return rsv1; }

    @Override
    public boolean isRsv2() { return rsv2; }

    @Override
    public boolean isRsv3() { return rsv3; }

    @Override
    public boolean isMasked() { return masked; }

    // =========================================================================
    // Setters
    // =========================================================================

    public void setRsv1(boolean rsv1) { this.rsv1 = rsv1; }
    public void setRsv2(boolean rsv2) { this.rsv2 = rsv2; }
    public void setRsv3(boolean rsv3) { this.rsv3 = rsv3; }
    public void setMasked(boolean masked) { this.masked = masked; }
    public void setFin(boolean fin) { this.fin = fin; }
    public void setPayload(ByteBuffer payload) { this.payload = payload; }

    // =========================================================================
    // Append
    // =========================================================================

    @Override
    public void append(WebSocketFrame frame) {
        ByteBuffer otherPayload = frame.getPayload();
        if (this.payload == null) {
            this.payload = ByteBuffer.allocate(otherPayload.remaining());
            otherPayload.mark();
            this.payload.put(otherPayload);
            otherPayload.reset();
        } else {
            otherPayload.mark();
            this.payload.position(this.payload.limit());
            this.payload.limit(this.payload.capacity());
            if (otherPayload.remaining() > this.payload.remaining()) {
                ByteBuffer newBuf = ByteBuffer.allocate(this.payload.capacity() + otherPayload.remaining());
                this.payload.flip();
                newBuf.put(this.payload);
                newBuf.put(otherPayload);
                this.payload = newBuf;
            } else {
                this.payload.put(otherPayload);
            }
            this.payload.rewind();
            otherPayload.reset();
        }
        this.fin = frame.isFin();
    }

    // =========================================================================
    // Object methods
    // =========================================================================

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BaseFrame other = (BaseFrame) obj;
        if (fin != other.fin || rsv1 != other.rsv1 || rsv2 != other.rsv2
                || rsv3 != other.rsv3 || masked != other.masked || opcode != other.opcode) {
            return false;
        }
        return payload != null ? payload.equals(other.payload) : other.payload == null;
    }

    @Override
    public int hashCode() {
        int result = (opcode.hashCode() + ((fin ? 1 : 0) * 31)) * 31;
        result += (payload != null ? payload.hashCode() : 0);
        result = ((result * 31) + (rsv1 ? 1 : 0)) * 31;
        result = (result + (rsv2 ? 1 : 0)) * 31;
        result = (result + (rsv3 ? 1 : 0)) * 31;
        result += (masked ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Framedata{ opcode:" + getOpcode()
                + ", fin:" + isFin()
                + ", rsv1:" + isRsv1()
                + ", rsv2:" + isRsv2()
                + ", rsv3:" + isRsv3()
                + ", payload length:[pos:" + payload.position()
                + ", len:" + payload.remaining() + "]"
                + ", payload:" + (payload.remaining() > 1000
                    ? "(too big to display)"
                    : new String(payload.array()))
                + '}';
    }

    // =========================================================================
    // Static factory
    // =========================================================================

    /**
     * Create a frame of the appropriate subclass for the given opcode.
     * Original: llllIIIIll1(FrameType) -> BaseFrame
     */
    public static BaseFrame createFrame(FrameType opcode) {
        if (opcode == null) {
            throw new IllegalArgumentException("Supplied opcode cannot be null");
        }
        switch (opcode) {
            case PING:       return new PingFrame();
            case PONG:       return new PongFrame();
            case TEXT:        return new TextFrame();
            case BINARY:     return new BinaryFrame();
            case CLOSING:    return new CloseFrame();
            case CONTINUOUS: return new ContinuousFrame();
            default:
                throw new IllegalArgumentException("Supplied opcode is invalid");
        }
    }
}

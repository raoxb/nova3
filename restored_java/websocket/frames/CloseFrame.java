package websocket.frames;

import java.nio.ByteBuffer;
import websocket.enums.FrameType;

/**
 * MALWARE ANALYSIS — Close frame
 *
 * Original: IlIIIIllllIlI1.lIIIIlllllIlll1
 *
 * Represents a CLOSE control frame (opcode 0x8).
 * Carries a close code (2 bytes) and an optional UTF-8 reason string.
 *
 * Close code constants (RFC 6455 Section 7.4.1):
 */
public class CloseFrame extends ControlFrame {

    // =========================================================================
    // Close code constants
    // =========================================================================

    /** Normal closure. */
    public static final int NORMAL          = 1000;
    /** Going away (browser navigating, server shutting down). */
    public static final int GOING_AWAY      = 1001;
    /** Protocol error. */
    public static final int PROTOCOL_ERROR  = 1002;
    /** Unsupported data type. */
    public static final int REFUSE          = 1003;
    /** Reserved (no status code present). */
    public static final int NOCODE          = 1005;
    /** Abnormal closure (no close frame received). */
    public static final int ABNORMAL_CLOSE  = 1006;
    /** Invalid UTF-8 in text frame. */
    public static final int NO_UTF8         = 1007;
    /** Policy violation. */
    public static final int POLICY_VIOLATION = 1008;
    /** Message too big. */
    public static final int TOOBIG          = 1009;
    /** Missing expected extension. */
    public static final int EXTENSION       = 1010;
    /** Unexpected condition. */
    public static final int UNEXPECTED_CONDITION = 1011;
    /** Service restart. */
    public static final int SERVICE_RESTART = 1012;
    /** Try again later. */
    public static final int TRY_AGAIN_LATER = 1013;
    /** Bad gateway. */
    public static final int BAD_GATEWAY     = 1014;
    /** TLS handshake failure (never sent on wire). */
    public static final int TLS_ERROR       = 1015;

    /** Internal marker: no code. */
    public static final int NEVER_CONNECTED = -1;
    /** Internal marker: flashpolicy. */
    public static final int FLASHPOLICY     = -2;
    /** Internal marker: buggy close. */
    public static final int BUGGYCLOSE      = -3;

    // =========================================================================
    // Fields
    // =========================================================================

    /** Close code. Original: f89IlIlIIlIII1 */
    public int closeCode;

    /** Close reason string. Original: f90lIllIIIlIl1 */
    public String closeReason;

    // =========================================================================
    // Constructors
    // =========================================================================

    public CloseFrame() {
        super(FrameType.CLOSING);
        setCloseReason("");
        setCloseCode(NORMAL);
    }

    // =========================================================================
    // Accessors
    // =========================================================================

    public int getCloseCode() {
        return closeCode;
    }

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseCode(int code) {
        this.closeCode = code;
        if (code == TLS_ERROR) {
            this.closeCode = NOCODE;
            this.closeReason = "";
        }
        updatePayload();
    }

    public void setCloseReason(String reason) {
        if (reason == null) {
            reason = "";
        }
        this.closeReason = reason;
        updatePayload();
    }

    // =========================================================================
    // Payload encoding
    // =========================================================================

    /** Re-encode the close code + reason into the payload ByteBuffer. */
    private void updatePayload() {
        if (closeReason == null) return;
        byte[] reasonBytes = closeReason.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        ByteBuffer buf = ByteBuffer.allocate(reasonBytes.length + 2);
        ByteBuffer codeBuf = ByteBuffer.allocate(4);
        codeBuf.putInt(closeCode);
        codeBuf.position(2);
        buf.put(codeBuf);
        buf.put(reasonBytes);
        buf.rewind();
        this.payload = buf;
    }

    // =========================================================================
    // Overrides
    // =========================================================================

    @Override
    public void validate() throws Exception {
        super.validate();
        if (closeCode == NO_UTF8 && closeReason.isEmpty()) {
            throw new Exception("Received text is no valid utf8 string!");
        }
        if (closeCode == NOCODE && closeReason.length() > 0) {
            throw new Exception("A close frame must have a closecode if it has a reason");
        }
        if (closeCode > 1015 && closeCode < 3000) {
            throw new Exception("Trying to send an illegal close code!");
        }
        if (closeCode == ABNORMAL_CLOSE || closeCode == TLS_ERROR || closeCode == NOCODE
                || closeCode > 4999 || closeCode < 1000 || closeCode == 1004) {
            throw new IllegalArgumentException("closecode must not be sent over the wire: " + closeCode);
        }
    }

    @Override
    public ByteBuffer getPayload() {
        return closeCode == NOCODE ? ByteBuffer.allocate(0) : this.payload;
    }

    @Override
    public void setPayload(ByteBuffer buf) {
        // Parse close code and reason from incoming payload
        this.closeCode = NOCODE;
        this.closeReason = "";
        buf.mark();
        if (buf.remaining() == 0) {
            this.closeCode = NORMAL;
            return;
        }
        if (buf.remaining() == 1) {
            this.closeCode = PROTOCOL_ERROR;
            return;
        }
        if (buf.remaining() >= 2) {
            ByteBuffer codeBuf = ByteBuffer.allocate(4);
            codeBuf.position(2);
            codeBuf.putShort(buf.getShort());
            codeBuf.position(0);
            this.closeCode = codeBuf.getInt();
        }
        buf.reset();
        // Remaining bytes after code are the reason string
        if (buf.remaining() > 2) {
            int pos = buf.position();
            buf.position(pos + 2);
            byte[] reasonBytes = new byte[buf.remaining()];
            buf.get(reasonBytes);
            this.closeReason = new String(reasonBytes, java.nio.charset.StandardCharsets.UTF_8);
            buf.position(pos);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) return false;
        CloseFrame other = (CloseFrame) obj;
        if (closeCode != other.closeCode) return false;
        return closeReason != null ? closeReason.equals(other.closeReason) : other.closeReason == null;
    }

    @Override
    public int hashCode() {
        int result = ((super.hashCode() * 31) + closeCode) * 31;
        return result + (closeReason != null ? closeReason.hashCode() : 0);
    }

    @Override
    public String toString() {
        return super.toString() + "code: " + closeCode;
    }
}

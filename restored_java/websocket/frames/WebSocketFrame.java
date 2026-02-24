package websocket.frames;

import java.nio.ByteBuffer;
import websocket.enums.FrameType;

/**
 * MALWARE ANALYSIS — WebSocket frame interface
 *
 * Original: IlIIIIllllIlI1.IlIllIlllIllI1
 *
 * Interface defining a WebSocket protocol frame. All frame types
 * (text, binary, control, continuation) implement this interface.
 *
 * Corresponds to RFC 6455 Section 5.2 frame format.
 */
public interface WebSocketFrame {

    /** Get the frame opcode. Original: llllIIIIll1() -> FrameType */
    FrameType getOpcode();

    /** Get the frame payload. Original: llllIllIl1() -> ByteBuffer */
    ByteBuffer getPayload();

    /** Whether the FIN bit is set. Original: llllllIlIIIlll1() -> boolean */
    boolean isFin();

    /** Whether RSV1 bit is set. Original: IlIlllIIlI1() -> boolean */
    boolean isRsv1();

    /** Whether RSV2 bit is set. Original: lIIIIlllllIlll1() -> boolean */
    boolean isRsv2();

    /** Whether RSV3 bit is set. Original: IlIllIlllIllI1() -> boolean */
    boolean isRsv3();

    /** Whether the frame is masked. Original: IllIIlIIII1() -> boolean */
    boolean isMasked();

    /** Append another frame's payload to this one. Original: llllIIIIll1(WebSocketFrame) */
    void append(WebSocketFrame frame);
}

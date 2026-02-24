package websocket.frames;

import websocket.enums.FrameType;

/**
 * MALWARE ANALYSIS — Pong frame
 *
 * Original: IlIIIIllllIlI1.lIllIIIlIl1
 *
 * Represents a PONG control frame (opcode 0xA).
 * Sent in response to a PING frame, echoing its payload.
 */
public class PongFrame extends ControlFrame {

    public PongFrame() {
        super(FrameType.PONG);
    }

    /** Create a PONG response to a PING frame, copying its payload. */
    public PongFrame(PingFrame pingFrame) {
        super(FrameType.PONG);
        setPayload(pingFrame.getPayload());
    }
}

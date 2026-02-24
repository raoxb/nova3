package websocket.frames;

import websocket.enums.FrameType;

/**
 * MALWARE ANALYSIS — Ping frame
 *
 * Original: IlIIIIllllIlI1.IlIlIIlIII1
 *
 * Represents a PING control frame (opcode 0x9).
 * The receiver should respond with a PONG frame.
 */
public class PingFrame extends ControlFrame {

    public PingFrame() {
        super(FrameType.PING);
    }
}

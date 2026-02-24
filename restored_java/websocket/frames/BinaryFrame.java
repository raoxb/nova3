package websocket.frames;

import websocket.enums.FrameType;

/**
 * MALWARE ANALYSIS — Binary frame
 *
 * Original: IlIIIIllllIlI1.llllIIIIll1
 *
 * Represents a BINARY opcode frame (opcode 0x2).
 */
public class BinaryFrame extends DataFrame {

    public BinaryFrame() {
        super(FrameType.BINARY);
    }
}

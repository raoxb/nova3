package websocket.frames;

import websocket.enums.FrameType;

/**
 * MALWARE ANALYSIS — Continuation frame
 *
 * Original: IlIIIIllllIlI1.llllIllIl1
 *
 * Represents a CONTINUOUS opcode frame (opcode 0x0).
 * Used in fragmented messages.
 */
public class ContinuousFrame extends DataFrame {

    public ContinuousFrame() {
        super(FrameType.CONTINUOUS);
    }
}

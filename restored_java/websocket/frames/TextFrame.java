package websocket.frames;

import websocket.enums.FrameType;

/**
 * MALWARE ANALYSIS — Text frame
 *
 * Original: IlIIIIllllIlI1.IIlIllIIll1
 *
 * Represents a TEXT opcode frame (opcode 0x1).
 * Payload should contain valid UTF-8 text.
 */
public class TextFrame extends DataFrame {

    public TextFrame() {
        super(FrameType.TEXT);
    }
}

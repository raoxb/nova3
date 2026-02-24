package websocket.frames;

import websocket.enums.FrameType;

/**
 * MALWARE ANALYSIS — Abstract control frame base
 *
 * Original: IlIIIIllllIlI1.IllIIlIIII1
 *
 * Base class for control frames (Ping, Pong, Close).
 * Control frames must have FIN=true and no RSV bits set.
 */
public abstract class ControlFrame extends BaseFrame {

    public ControlFrame(FrameType opcode) {
        super(opcode);
    }

    @Override
    public void validate() throws Exception {
        if (!isFin()) {
            throw new IllegalArgumentException("Control frame can't have fin==false set");
        }
        if (isRsv1()) {
            throw new IllegalArgumentException("Control frame can't have rsv1==true set");
        }
        if (isRsv2()) {
            throw new IllegalArgumentException("Control frame can't have rsv2==true set");
        }
        if (isRsv3()) {
            throw new IllegalArgumentException("Control frame can't have rsv3==true set");
        }
    }
}

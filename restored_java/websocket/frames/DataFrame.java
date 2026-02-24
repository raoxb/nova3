package websocket.frames;

import websocket.enums.FrameType;

/**
 * MALWARE ANALYSIS — Abstract data frame base
 *
 * Original: IlIIIIllllIlI1.IlIlllIIlI1
 *
 * Base class for data frames (Text, Binary, Continuous).
 * Data frames have no special validation beyond the base.
 */
public abstract class DataFrame extends BaseFrame {

    public DataFrame(FrameType opcode) {
        super(opcode);
    }

    @Override
    public void validate() throws Exception {
        // Data frames have no additional validation requirements
    }
}

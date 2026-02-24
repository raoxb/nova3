package websocket.extensions;

import websocket.frames.WebSocketFrame;

/**
 * MALWARE ANALYSIS — Draft extension with RSV validation
 *
 * Original: lIIlllIIIlllII1.llllIIIIll1
 *
 * Abstract extension that validates RSV bits. Allows RSV1 on data frames
 * (for per-message compression) but disallows RSV2/RSV3 on data frames
 * and all RSV bits on control frames.
 */
public abstract class DraftExtension extends BaseExtension {

    @Override
    public void validateFrame(WebSocketFrame frame) throws Exception {
        // Data frames: allow RSV1, disallow RSV2/RSV3
        // Control frames: disallow all RSV bits
        if (frame.isRsv2() || frame.isRsv3()) {
            throw new Exception("bad rsv RSV1: " + frame.isRsv1()
                    + " RSV2: " + frame.isRsv2()
                    + " RSV3: " + frame.isRsv3());
        }
        // Control frames cannot have any RSV bits
        if (isControlFrame(frame)) {
            if (frame.isRsv1() || frame.isRsv2() || frame.isRsv3()) {
                throw new Exception("bad rsv RSV1: " + frame.isRsv1()
                        + " RSV2: " + frame.isRsv2()
                        + " RSV3: " + frame.isRsv3());
            }
        }
    }

    private boolean isControlFrame(WebSocketFrame frame) {
        switch (frame.getOpcode()) {
            case PING:
            case PONG:
            case CLOSING:
                return true;
            default:
                return false;
        }
    }
}

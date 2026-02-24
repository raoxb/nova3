package websocket.extensions;

import websocket.frames.WebSocketFrame;

/**
 * MALWARE ANALYSIS — Default extension implementation
 *
 * Original: lIIlllIIIlllII1.lIIIIlllllIlll1
 *
 * Base implementation of WebSocketExtension with default no-op behavior.
 * Validates that no RSV bits are set (standard behavior without extensions).
 */
public class BaseExtension implements WebSocketExtension {

    @Override
    public void decodeFrame(WebSocketFrame frame) throws Exception {
        // No transformation
    }

    @Override
    public void encodeFrame(WebSocketFrame frame) throws Exception {
        // No transformation
    }

    @Override
    public boolean acceptExtension(String extensionName) {
        return true;
    }

    @Override
    public boolean acceptProvidedExtension(String extensionParams) {
        return true;
    }

    @Override
    public String getExtensionName() {
        return "";
    }

    @Override
    public String getProvidedExtension() {
        return "";
    }

    @Override
    public WebSocketExtension copyInstance() {
        return new BaseExtension();
    }

    @Override
    public void reset() {
        // No state to reset
    }

    @Override
    public void validateFrame(WebSocketFrame frame) throws Exception {
        if (frame.isRsv1() || frame.isRsv2() || frame.isRsv3()) {
            throw new Exception("bad rsv RSV1: " + frame.isRsv1()
                    + " RSV2: " + frame.isRsv2()
                    + " RSV3: " + frame.isRsv3());
        }
    }

    @Override
    public void processFrame(WebSocketFrame frame) {
        // No processing
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj != null && getClass() == obj.getClass());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

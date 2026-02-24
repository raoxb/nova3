package websocket.extensions;

import websocket.frames.WebSocketFrame;

/**
 * MALWARE ANALYSIS — WebSocket extension interface
 *
 * Original: lIIlllIIIlllII1.IllIIlIIII1
 *
 * Interface for WebSocket protocol extensions (RFC 6455 Section 9).
 * Extensions can modify frames during encoding/decoding.
 *
 * Methods:
 *   - decodeFrame:       process incoming frame (e.g. decompress)
 *   - encodeFrame:       process outgoing frame (e.g. compress)
 *   - validateIncoming:  validate RSV bits on incoming frames
 *   - validateOutgoing:  validate RSV bits on outgoing frames
 *   - acceptExtension:   check if extension name matches
 *   - getExtensionName:  return extension name string
 *   - getProvidedExtension: return extension parameters string
 *   - copyInstance:       create a copy of this extension
 *   - reset:             reset extension state
 */
public interface WebSocketExtension {

    /** Decode/process incoming frame. Original: llllIllIl1(WebSocketFrame) */
    void decodeFrame(WebSocketFrame frame) throws Exception;

    /** Encode/process outgoing frame. Original: lIIIIlllllIlll1(WebSocketFrame) */
    void encodeFrame(WebSocketFrame frame) throws Exception;

    /** Validate incoming frame RSV bits. Original: llllIIIIll1(String) -> boolean */
    boolean acceptExtension(String extensionName);

    /** Check if offered extension is supported. Original: lIIIIlllllIlll1(String) -> boolean */
    boolean acceptProvidedExtension(String extensionParams);

    /** Get extension name. Original: llllIllIl1() -> String */
    String getExtensionName();

    /** Get extension negotiation parameters. Original: lIIIIlllllIlll1() -> String */
    String getProvidedExtension();

    /** Create a copy of this extension. Original: llllIIIIll1() -> WebSocketExtension */
    WebSocketExtension copyInstance();

    /** Reset extension state. Original: IllIIlIIII1() */
    void reset();

    /** Modify incoming frame (validation). Original: llllIllIl1(WebSocketFrame) */
    void validateFrame(WebSocketFrame frame) throws Exception;

    /** Modify outgoing frame (validation). Original: llllIIIIll1(WebSocketFrame) */
    void processFrame(WebSocketFrame frame);
}

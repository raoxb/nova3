package websocket.handshake;

/**
 * MALWARE ANALYSIS — Mutable server handshake builder
 *
 * Original: lllIlIlllI1.lIllIIIlIl1
 *
 * Extends HandshakeBuilder and ServerHandshake with mutable setters
 * for HTTP status code and status message.
 */
public interface ServerHandshakeBuilder extends HandshakeBuilder, ServerHandshake {

    /** Set HTTP status message. Original: IllIIlIIII1(String) */
    void setHttpStatusMessage(String message);

    /** Set HTTP status code. Original: llllIIIIll1(short) */
    void setHttpStatus(short status);
}

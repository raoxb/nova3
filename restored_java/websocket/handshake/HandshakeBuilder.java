package websocket.handshake;

/**
 * MALWARE ANALYSIS — Mutable handshake builder interface
 *
 * Original: lllIlIlllI1.llllIllIl1
 *
 * Extends Handshake with mutable operations for building HTTP handshake data.
 */
public interface HandshakeBuilder extends Handshake {

    /** Set a header field. Original: llllIIIIll1(String, String) */
    void putField(String name, String value);

    /** Set the content body. Original: llllIIIIll1(byte[]) */
    void setContent(byte[] content);
}

package websocket.handshake;

/**
 * MALWARE ANALYSIS — Client handshake interface (read-only)
 *
 * Original: lllIlIlllI1.llllIIIIll1
 *
 * Extends Handshake with the HTTP resource descriptor (URI path).
 */
public interface ClientHandshake extends Handshake {

    /** Get the HTTP resource descriptor (e.g. "/ws"). Original: llllIIIIll1() */
    String getResourceDescriptor();
}

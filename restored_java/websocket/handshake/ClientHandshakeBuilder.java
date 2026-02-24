package websocket.handshake;

/**
 * MALWARE ANALYSIS — Mutable client handshake builder
 *
 * Original: lllIlIlllI1.lIIIIlllllIlll1
 *
 * Extends HandshakeBuilder and ClientHandshake with a setter for the
 * resource descriptor.
 */
public interface ClientHandshakeBuilder extends HandshakeBuilder, ClientHandshake {

    /** Set the HTTP resource descriptor. Original: lIIIIlllllIlll1(String) */
    void setResourceDescriptor(String descriptor);
}

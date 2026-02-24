package websocket.handshake;

/**
 * MALWARE ANALYSIS — Client handshake implementation
 *
 * Original: lllIlIlllI1.IllIIlIIII1
 *
 * Concrete implementation of ClientHandshakeBuilder.
 * Stores the HTTP resource descriptor (URI path) for the WebSocket upgrade request.
 */
public class ClientHandshakeImpl extends HandshakeImpl implements ClientHandshakeBuilder {

    /** HTTP resource descriptor. Original: f699llllIllIl1. Default: "*" (match all) */
    public String resourceDescriptor = "*";

    @Override
    public String getResourceDescriptor() {
        return resourceDescriptor;
    }

    @Override
    public void setResourceDescriptor(String descriptor) {
        if (descriptor == null) {
            throw new IllegalArgumentException("http resource descriptor must not be null");
        }
        this.resourceDescriptor = descriptor;
    }
}

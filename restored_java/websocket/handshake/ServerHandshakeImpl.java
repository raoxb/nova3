package websocket.handshake;

/**
 * MALWARE ANALYSIS — Server handshake implementation
 *
 * Original: lllIlIlllI1.IlIlllIIlI1
 *
 * Concrete implementation of ServerHandshakeBuilder.
 * Stores HTTP status code and message for the 101 Switching Protocols response.
 */
public class ServerHandshakeImpl extends HandshakeImpl implements ServerHandshakeBuilder {

    /** HTTP status message. Original: f697IllIIlIIII1 */
    public String httpStatusMessage;

    /** HTTP status code. Original: f698llllIllIl1 */
    public short httpStatus;

    @Override
    public short getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getHttpStatusMessage() {
        return httpStatusMessage;
    }

    @Override
    public void setHttpStatus(short status) {
        this.httpStatus = status;
    }

    @Override
    public void setHttpStatusMessage(String message) {
        this.httpStatusMessage = message;
    }
}

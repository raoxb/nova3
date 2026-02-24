package websocket.handshake;

/**
 * MALWARE ANALYSIS — Server handshake interface (read-only)
 *
 * Original: lllIlIlllI1.IlIlIIlIII1
 *
 * Extends Handshake with HTTP status code and status message.
 */
public interface ServerHandshake extends Handshake {

    /** Get HTTP status code (e.g. 101). Original: lIIIIlllllIlll1() */
    short getHttpStatus();

    /** Get HTTP status message (e.g. "Switching Protocols"). Original: llllIllIl1() */
    String getHttpStatusMessage();
}

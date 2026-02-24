package websocket.handshake;

import java.util.Iterator;

/**
 * MALWARE ANALYSIS — HTTP handshake interface (read-only)
 *
 * Original: lllIlIlllI1.IlIllIlllIllI1
 *
 * Base interface for WebSocket HTTP Upgrade handshake data.
 * Read-only view of HTTP headers and content.
 */
public interface Handshake {

    /** Get HTTP body content bytes. Original: IlIlllIIlI1() */
    byte[] getContent();

    /** Iterate over header field names. Original: IllIIlIIII1() */
    Iterator<String> iterateHttpFields();

    /** Check if header field exists. Original: llllIIIIll1(String) */
    boolean hasFieldValue(String name);

    /** Get header field value (empty string if absent). Original: llllIllIl1(String) */
    String getFieldValue(String name);
}

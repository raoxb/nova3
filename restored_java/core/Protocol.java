package core;

import java.util.regex.Pattern;

/**
 * MALWARE ANALYSIS — Protocol/subprotocol interface
 *
 * Original: IIlllllIlll1.llllIIIIll1
 *
 * Interface for WebSocket protocol/subprotocol matching.
 * Used during handshake to negotiate subprotocols.
 */
public interface Protocol {

    /** Get the protocol name string. Original: lIIIIlllllIlll1() */
    String getProtocolName();

    /** Check if this protocol matches a header value. Original: llllIIIIll1(String) */
    boolean acceptProvidedProtocol(String protocolHeader);

    /** Create a copy of this protocol. Original: llllIIIIll1() -> Protocol */
    Protocol copyInstance();

    /** String representation. */
    String toString();
}

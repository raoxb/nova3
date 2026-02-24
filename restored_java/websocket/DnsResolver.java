package websocket;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

/**
 * MALWARE ANALYSIS -- DNS resolver interface for WebSocket connections
 *
 * Original: lllIlIIIlI1.llllIIIIll1
 *
 * Simple interface for resolving URIs to InetAddresses.
 * The default implementation in {@link WebSocketClientBase} resolves
 * via {@code InetAddress.getByName(uri.getHost())}.
 *
 * This interface allows custom DNS resolution strategies (e.g., DoH,
 * hardcoded IPs, or DNS pinning) to be injected into the WebSocket client.
 */
public interface DnsResolver {

    /**
     * Resolves the host from the given URI to an InetAddress.
     *
     * Original: llllIIIIll1(URI) -> InetAddress
     *
     * @param uri the URI whose host should be resolved
     * @return the resolved InetAddress
     * @throws UnknownHostException if the host cannot be resolved
     */
    InetAddress resolve(URI uri) throws UnknownHostException;
}

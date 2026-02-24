package websocket;

import javax.net.ssl.SSLEngine;

/**
 * MALWARE ANALYSIS — SSL socket factory interface
 *
 * Original: llIlIIlll1.llllIIIIll1
 *
 * Simple interface for creating SSLEngine instances.
 * Used by the WebSocket client-side SSL setup.
 */
public interface SSLSocketFactory {

    /**
     * Create and return an SSLEngine instance.
     * Original: IllIIlIIII1() -> SSLEngine
     */
    SSLEngine createSSLEngine();
}

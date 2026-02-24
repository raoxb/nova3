package websocket.server;

import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/**
 * MALWARE ANALYSIS — SSL channel factory with cipher/protocol suites
 *
 * Original: IIlIIllll1.llllIIIIll1
 *
 * Extends SSLChannelFactory to configure SSLEngine with explicit
 * cipher suite and protocol lists.
 */
public class SSLChannelFactoryWithCrypto extends SSLChannelFactory {

    /** Enabled protocols (e.g. TLSv1.2). Original: f64llllIllIl1 */
    public final String[] protocols;

    /** Enabled cipher suites. Original: f63IllIIlIIII1 */
    public final String[] cipherSuites;

    public SSLChannelFactoryWithCrypto(SSLContext sslContext, String[] protocols,
                                        String[] cipherSuites) {
        super(sslContext, Executors.newSingleThreadScheduledExecutor());
        this.protocols = protocols;
        this.cipherSuites = cipherSuites;
    }

    public SSLChannelFactoryWithCrypto(SSLContext sslContext, ExecutorService executor,
                                        String[] protocols, String[] cipherSuites) {
        super(sslContext, executor);
        this.protocols = protocols;
        this.cipherSuites = cipherSuites;
    }

    @Override
    public ByteChannel wrapChannel(SocketChannel channel, SelectionKey key) throws IOException {
        SSLEngine engine = sslContext.createSSLEngine();
        if (protocols != null) {
            engine.setEnabledProtocols(protocols);
        }
        if (cipherSuites != null) {
            engine.setEnabledCipherSuites(cipherSuites);
        }
        engine.setUseClientMode(false);
        return new websocket.SSLSocketChannel2(channel, engine, executorService, key);
    }
}

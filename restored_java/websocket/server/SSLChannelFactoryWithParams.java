package websocket.server;

import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;

/**
 * MALWARE ANALYSIS — SSL channel factory with SSLParameters
 *
 * Original: IIlIIllll1.IllIIlIIII1
 *
 * Extends SSLChannelFactory to configure SSLEngine with explicit SSLParameters.
 */
public class SSLChannelFactoryWithParams extends SSLChannelFactory {

    /** SSL parameters. Original: f60llllIllIl1 */
    public final SSLParameters sslParameters;

    public SSLChannelFactoryWithParams(SSLContext sslContext, SSLParameters sslParameters) {
        this(sslContext, Executors.newSingleThreadScheduledExecutor(), sslParameters);
    }

    public SSLChannelFactoryWithParams(SSLContext sslContext, ExecutorService executor,
                                        SSLParameters sslParameters) {
        super(sslContext, executor);
        if (sslParameters == null) {
            throw new IllegalArgumentException();
        }
        this.sslParameters = sslParameters;
    }

    @Override
    public ByteChannel wrapChannel(SocketChannel channel, SelectionKey key) throws IOException {
        SSLEngine engine = sslContext.createSSLEngine();
        engine.setUseClientMode(false);
        engine.setSSLParameters(sslParameters);
        return new websocket.SSLSocketChannel2(channel, engine, executorService, key);
    }
}

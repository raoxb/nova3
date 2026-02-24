package websocket.server;

import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

import websocket.ServerChannelFactory;
import websocket.WebSocket;
import websocket.WebSocketAdapter;
import websocket.WebSocketImpl;

/**
 * MALWARE ANALYSIS — Base SSL channel factory
 *
 * Original: IIlIIllll1.lIIIIlllllIlll1
 *
 * Creates SSL-wrapped ByteChannels for the WebSocket server.
 * Uses SSLContext to create SSLEngine instances.
 *
 * By default removes TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 cipher.
 */
public class SSLChannelFactory implements ServerChannelFactory {

    /** SSL context. Original: f62llllIIIIll1 */
    public SSLContext sslContext;

    /** Executor for SSL tasks. Original: f61lIIIIlllllIlll1 */
    public ExecutorService executorService;

    public SSLChannelFactory(SSLContext sslContext) {
        this(sslContext, Executors.newSingleThreadScheduledExecutor());
    }

    public SSLChannelFactory(SSLContext sslContext, ExecutorService executorService) {
        if (sslContext == null || executorService == null) {
            throw new IllegalArgumentException();
        }
        this.sslContext = sslContext;
        this.executorService = executorService;
    }

    @Override
    public ByteChannel wrapChannel(SocketChannel channel, SelectionKey key) throws IOException {
        SSLEngine engine = sslContext.createSSLEngine();
        ArrayList<String> ciphers = new ArrayList<>(Arrays.asList(engine.getEnabledCipherSuites()));
        ciphers.remove("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
        engine.setEnabledCipherSuites(ciphers.toArray(new String[0]));
        engine.setUseClientMode(false);
        return new websocket.SSLSocketChannel2(channel, engine, executorService, key);
    }

    @Override
    public void close() {
        executorService.shutdown();
    }

    @Override
    public WebSocketImpl createWebSocket(WebSocketAdapter listener, Object draft) {
        return new WebSocketImpl(listener, draft);
    }

    @Override
    public WebSocketImpl createWebSocket(WebSocketAdapter listener, List<Object> drafts) {
        return new WebSocketImpl(listener, drafts);
    }
}

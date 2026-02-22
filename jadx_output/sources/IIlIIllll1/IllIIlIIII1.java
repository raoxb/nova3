package IIlIIllll1;

import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;

/* loaded from: classes.dex */
public class IllIIlIIII1 extends lIIIIlllllIlll1 {

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final SSLParameters f60llllIllIl1;

    public IllIIlIIII1(SSLContext sSLContext, SSLParameters sSLParameters) {
        this(sSLContext, Executors.newSingleThreadScheduledExecutor(), sSLParameters);
    }

    @Override // IIlIIllll1.lIIIIlllllIlll1, llIIllIl1.IlIllll1
    public ByteChannel llllIIIIll1(SocketChannel socketChannel, SelectionKey selectionKey) throws IOException {
        SSLEngine createSSLEngine = this.f62llllIIIIll1.createSSLEngine();
        createSSLEngine.setUseClientMode(false);
        createSSLEngine.setSSLParameters(this.f60llllIllIl1);
        return new llIIllIl1.IllIIlIIII1(socketChannel, createSSLEngine, this.f61lIIIIlllllIlll1, selectionKey);
    }

    public IllIIlIIII1(SSLContext sSLContext, ExecutorService executorService, SSLParameters sSLParameters) {
        super(sSLContext, executorService);
        if (sSLParameters == null) {
            throw new IllegalArgumentException();
        }
        this.f60llllIllIl1 = sSLParameters;
    }
}

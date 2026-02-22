package IIlIIllll1;

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
import llIIllIl1.IlIllIlllIllI1;
import llIIllIl1.IlIllll1;
import llIIllIl1.lIllIIIlIl1;
import llIIllIl1.llllllIlIIIlll1;

/* loaded from: classes.dex */
public class lIIIIlllllIlll1 implements IlIllll1 {

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public ExecutorService f61lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public SSLContext f62llllIIIIll1;

    public lIIIIlllllIlll1(SSLContext sSLContext) {
        this(sSLContext, Executors.newSingleThreadScheduledExecutor());
    }

    @Override // llIIllIl1.IlIllll1
    public void close() {
        this.f61lIIIIlllllIlll1.shutdown();
    }

    @Override // llIIllIl1.IlIllll1, llIIllIl1.IlIlIIlIII1
    public /* bridge */ /* synthetic */ IlIllIlllIllI1 llllIIIIll1(llllllIlIIIlll1 lllllliliiilll1, List list) {
        return llllIIIIll1(lllllliliiilll1, (List<lIlllIIIII1.llllIIIIll1>) list);
    }

    public lIIIIlllllIlll1(SSLContext sSLContext, ExecutorService executorService) {
        if (sSLContext == null || executorService == null) {
            throw new IllegalArgumentException();
        }
        this.f62llllIIIIll1 = sSLContext;
        this.f61lIIIIlllllIlll1 = executorService;
    }

    @Override // llIIllIl1.IlIllll1
    public ByteChannel llllIIIIll1(SocketChannel socketChannel, SelectionKey selectionKey) throws IOException {
        SSLEngine createSSLEngine = this.f62llllIIIIll1.createSSLEngine();
        ArrayList arrayList = new ArrayList(Arrays.asList(createSSLEngine.getEnabledCipherSuites()));
        arrayList.remove("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
        createSSLEngine.setEnabledCipherSuites((String[]) arrayList.toArray(new String[arrayList.size()]));
        createSSLEngine.setUseClientMode(false);
        return new llIIllIl1.IllIIlIIII1(socketChannel, createSSLEngine, this.f61lIIIIlllllIlll1, selectionKey);
    }

    @Override // llIIllIl1.IlIllll1, llIIllIl1.IlIlIIlIII1
    public lIllIIIlIl1 llllIIIIll1(llllllIlIIIlll1 lllllliliiilll1, lIlllIIIII1.llllIIIIll1 lllliiiill1) {
        return new lIllIIIlIl1(lllllliliiilll1, lllliiiill1);
    }

    @Override // llIIllIl1.IlIllll1, llIIllIl1.IlIlIIlIII1
    public lIllIIIlIl1 llllIIIIll1(llllllIlIIIlll1 lllllliliiilll1, List<lIlllIIIII1.llllIIIIll1> list) {
        return new lIllIIIlIl1(lllllliliiilll1, list);
    }
}

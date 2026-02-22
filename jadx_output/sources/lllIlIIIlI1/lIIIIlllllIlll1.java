package lllIlIIIlI1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import lIIlIIIIlIlII1.llllIllIl1;
import lIIlllIIIlllII1.IllIIlIIII1;
import llIIllIl1.IlIllIlllIllI1;
import llIIllIl1.lIllIIIlIl1;
import lllIlIlllI1.IlIlIIlIII1;

/* loaded from: classes.dex */
public abstract class lIIIIlllllIlll1 extends llIIllIl1.llllIIIIll1 implements Runnable, IlIllIlllIllI1 {

    /* renamed from: IIIlIllIlI1, reason: collision with root package name */
    public OutputStream f680IIIlIllIlI1;

    /* renamed from: IlIIIIllllIlI1, reason: collision with root package name */
    public int f681IlIIIIllllIlI1;

    /* renamed from: IlIIlllllI1, reason: collision with root package name */
    public URI f682IlIIlllllI1;

    /* renamed from: IlIlIIIlIlIlll1, reason: collision with root package name */
    public SocketFactory f683IlIlIIIlIlIlll1;

    /* renamed from: IllIlIllll1, reason: collision with root package name */
    public Map<String, String> f684IllIlIllll1;

    /* renamed from: IllllIllllll1, reason: collision with root package name */
    public CountDownLatch f685IllllIllllll1;

    /* renamed from: lIIlIIIIlIlII1, reason: collision with root package name */
    public lIlllIIIII1.llllIIIIll1 f686lIIlIIIIlIlII1;

    /* renamed from: lIIlllIIIlllII1, reason: collision with root package name */
    public CountDownLatch f687lIIlllIIIlllII1;

    /* renamed from: lIllIlIll1, reason: collision with root package name */
    public Socket f688lIllIlIll1;

    /* renamed from: lIlllIIIII1, reason: collision with root package name */
    public Thread f689lIlllIIIII1;

    /* renamed from: llIIIIlIlllIII1, reason: collision with root package name */
    public lIllIIIlIl1 f690llIIIIlIlllIII1;

    /* renamed from: llIIllIl1, reason: collision with root package name */
    public Proxy f691llIIllIl1;

    /* renamed from: lllIlIIIlI1, reason: collision with root package name */
    public Thread f692lllIlIIIlI1;

    /* renamed from: lllIlIlllI1, reason: collision with root package name */
    public lllIlIIIlI1.llllIIIIll1 f693lllIlIlllI1;

    /* renamed from: lllIlIIIlI1.lIIIIlllllIlll1$lIIIIlllllIlll1, reason: collision with other inner class name */
    public class RunnableC0022lIIIIlllllIlll1 implements Runnable {

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final lIIIIlllllIlll1 f695llllIIIIll1;

        public RunnableC0022lIIIIlllllIlll1(lIIIIlllllIlll1 liiiilllllilll1) {
            this.f695llllIIIIll1 = liiiilllllilll1;
        }

        public final void lIIIIlllllIlll1() throws IOException {
            while (!Thread.interrupted()) {
                try {
                    ByteBuffer take = lIIIIlllllIlll1.this.f690llIIIIlIlllIII1.f652llllIIIIll1.take();
                    lIIIIlllllIlll1.this.f680IIIlIllIlI1.write(take.array(), 0, take.limit());
                    lIIIIlllllIlll1.this.f680IIIlIllIlI1.flush();
                } catch (InterruptedException unused) {
                    for (ByteBuffer byteBuffer : lIIIIlllllIlll1.this.f690llIIIIlIlllIII1.f652llllIIIIll1) {
                        lIIIIlllllIlll1.this.f680IIIlIllIlI1.write(byteBuffer.array(), 0, byteBuffer.limit());
                        lIIIIlllllIlll1.this.f680IIIlIllIlI1.flush();
                    }
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        public final void llllIIIIll1() {
            try {
                Socket socket = lIIIIlllllIlll1.this.f688lIllIlIll1;
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                lIIIIlllllIlll1.this.llllIIIIll1((Exception) e);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread.currentThread().setName("WebSocketWriteThread-" + Thread.currentThread().getId());
            try {
                try {
                    lIIIIlllllIlll1();
                } catch (IOException e) {
                    lIIIIlllllIlll1.this.llllIIIIll1(e);
                }
            } finally {
                llllIIIIll1();
            }
        }
    }

    public class llllIIIIll1 implements lllIlIIIlI1.llllIIIIll1 {
        public llllIIIIll1() {
        }

        @Override // lllIlIIIlI1.llllIIIIll1
        public InetAddress llllIIIIll1(URI uri) throws UnknownHostException {
            return InetAddress.getByName(uri.getHost());
        }
    }

    public lIIIIlllllIlll1(URI uri) {
        this(uri, new lIlllIIIII1.lIIIIlllllIlll1((List<IllIIlIIII1>) Collections.emptyList()), null, 0);
    }

    private int IlIIIlIlIlIII1() {
        int port = this.f682IlIIlllllI1.getPort();
        String scheme = this.f682IlIIlllllI1.getScheme();
        if ("wss".equals(scheme)) {
            return port == -1 ? lIllIIIlIl1.f632IllIlIllll1 : port;
        }
        if (!"ws".equals(scheme)) {
            throw new IllegalArgumentException("unknown scheme: " + scheme);
        }
        if (port == -1) {
            return 80;
        }
        return port;
    }

    public final void IIIlIllIl1() throws NoSuchAlgorithmException, KeyManagementException, IOException {
        SocketFactory socketFactory = this.f683IlIlIIIlIlIlll1;
        this.f688lIllIlIll1 = (socketFactory instanceof SSLSocketFactory ? (SSLSocketFactory) socketFactory : (SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket(this.f688lIllIlIll1, this.f682IlIIlllllI1.getHost(), IlIIIlIlIlIII1(), true);
    }

    @Override // llIIllIl1.llllIIIIll1
    public Collection<IlIllIlllIllI1> IIIlIllIlI1() {
        return Collections.singletonList(this.f690llIIIIlIlllIII1);
    }

    public IlIllIlllIllI1 IIlIIllll1() {
        return this.f690llIIIIlIlllIII1;
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public boolean IIlIllIIll1() {
        return this.f688lIllIlIll1 instanceof SSLSocket;
    }

    public final boolean IIlIlllllllI1() throws IOException {
        if (this.f691llIIllIl1 != Proxy.NO_PROXY) {
            this.f688lIllIlIll1 = new Socket(this.f691llIIllIl1);
            return true;
        }
        SocketFactory socketFactory = this.f683IlIlIIIlIlIlll1;
        if (socketFactory != null) {
            this.f688lIllIlIll1 = socketFactory.createSocket();
        } else {
            Socket socket = this.f688lIllIlIll1;
            if (socket == null) {
                this.f688lIllIlIll1 = new Socket(this.f691llIIllIl1);
                return true;
            }
            if (socket.isClosed()) {
                throw new IOException();
            }
        }
        return false;
    }

    public boolean IIlllllIlll1() throws InterruptedException {
        llIlIIlll1();
        this.f687lIIlllIIIlllII1.await();
        return this.f690llIIIIlIlllIII1.isOpen();
    }

    public Socket IlIIIIIlll1() {
        return this.f688lIllIlIll1;
    }

    public void IlIIIIllllIlI1() {
        this.f684IllIlIllll1 = null;
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public boolean IlIIlllllI1() {
        return this.f690llIIIIlIlllIII1.IlIIlllllI1();
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public SSLSession IlIlIIlIII1() {
        if (IIlIllIIll1()) {
            return ((SSLSocket) this.f688lIllIlIll1).getSession();
        }
        throw new IllegalArgumentException("This websocket uses ws instead of wss. No SSLSession available.");
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public InetSocketAddress IlIllIlllIllI1() {
        return this.f690llIIIIlIlllIII1.IlIllIlllIllI1();
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public <T> T IlIlllIIlI1() {
        return (T) this.f690llIIIIlIlllIII1.IlIlllIIlI1();
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public InetSocketAddress IlIllll1() {
        return this.f690llIIIIlIlllIII1.IlIllll1();
    }

    @Override // llIIllIl1.IIlIllIIll1
    public InetSocketAddress IllIIlIIII1(IlIllIlllIllI1 ilIllIlllIllI1) {
        Socket socket = this.f688lIllIlIll1;
        if (socket != null) {
            return (InetSocketAddress) socket.getLocalSocketAddress();
        }
        return null;
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void close() {
        if (this.f692lllIlIIIlI1 != null) {
            this.f690llIIIIlIlllIII1.llllIIIIll1(1000);
        }
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public boolean isOpen() {
        return this.f690llIIIIlIlllIII1.isOpen();
    }

    public void lIIIIlllllIlll1(int i, String str, boolean z) {
    }

    public abstract void lIIIIlllllIlll1(String str);

    public void lIIIIlllllIlll1(ByteBuffer byteBuffer) {
    }

    public final void lIIIllllllIIII1() {
        Socket socket;
        Thread currentThread = Thread.currentThread();
        if (currentThread == this.f692lllIlIIIlI1 || currentThread == this.f689lIlllIIIII1) {
            throw new IllegalStateException("You cannot initialize a reconnect out of the websocket thread. Use reconnect in another thread to ensure a successful cleanup.");
        }
        try {
            if (this.f690llIIIIlIlllIII1.lllllIllIl1() == lIIlIIIIlIlII1.IllIIlIIII1.NOT_YET_CONNECTED && (socket = this.f688lIllIlIll1) != null) {
                socket.close();
            }
            lllIlIlllI1();
            Thread thread = this.f692lllIlIIIlI1;
            if (thread != null) {
                thread.interrupt();
                this.f692lllIlIIIlI1.join();
                this.f692lllIlIIIlI1 = null;
            }
            Thread thread2 = this.f689lIlllIIIII1;
            if (thread2 != null) {
                thread2.interrupt();
                this.f689lIlllIIIII1.join();
                this.f689lIlllIIIII1 = null;
            }
            this.f686lIIlIIIIlIlII1.IllIIlIIII1();
            Socket socket2 = this.f688lIllIlIll1;
            if (socket2 != null) {
                socket2.close();
                this.f688lIllIlIll1 = null;
            }
            this.f687lIIlllIIIlllII1 = new CountDownLatch(1);
            this.f685IllllIllllll1 = new CountDownLatch(1);
            this.f690llIIIIlIlllIII1 = new lIllIIIlIl1(this, this.f686lIIlIIIIlIlII1);
        } catch (Exception e) {
            llllIIIIll1(e);
            this.f690llIIIIlIlllIII1.lIIIIlllllIlll1(1006, e.getMessage());
        }
    }

    public void lIlIIIllll1() {
        lIIIllllllIIII1();
        llIlIIlll1();
    }

    public final void lIlIlIlI1() throws IllIlIllll1.IlIllIlllIllI1 {
        String rawPath = this.f682IlIIlllllI1.getRawPath();
        String rawQuery = this.f682IlIIlllllI1.getRawQuery();
        if (rawPath == null || rawPath.length() == 0) {
            rawPath = "/";
        }
        if (rawQuery != null) {
            rawPath = rawPath + '?' + rawQuery;
        }
        int IlIIIlIlIlIII12 = IlIIIlIlIlIII1();
        String str = this.f682IlIIlllllI1.getHost() + ((IlIIIlIlIlIII12 == 80 || IlIIIlIlIlIII12 == 443) ? "" : ":" + IlIIIlIlIlIII12);
        lllIlIlllI1.IllIIlIIII1 illIIlIIII1 = new lllIlIlllI1.IllIIlIIII1();
        illIIlIIII1.lIIIIlllllIlll1(rawPath);
        illIIlIIII1.f700lIIIIlllllIlll1.put("Host", str);
        Map<String, String> map = this.f684IllIlIllll1;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                illIIlIIII1.f700lIIIIlllllIlll1.put(entry.getKey(), entry.getValue());
            }
        }
        this.f690llIIIIlIlllIII1.llllIIIIll1((lllIlIlllI1.lIIIIlllllIlll1) illIIlIIII1);
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public IIlllllIlll1.llllIIIIll1 lIllIIIlIl1() {
        return this.f690llIIIIlIlllIII1.lIllIIIlIl1();
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public boolean llIIIIlIlllIII1() {
        return this.f690llIIIIlIlllIII1.llIIIIlIlllIII1();
    }

    public URI llIIIlIIIlIII1() {
        return this.f682IlIIlllllI1;
    }

    public void llIlIIlll1() {
        if (this.f689lIlllIIIII1 != null) {
            throw new IllegalStateException("WebSocketClient objects are not reuseable");
        }
        Thread thread = new Thread(this);
        this.f689lIlllIIIII1 = thread;
        thread.setDaemon(lllIlIIIlI1());
        this.f689lIlllIIIII1.setName("WebSocketConnectReadThread-" + this.f689lIlllIIIII1.getId());
        this.f689lIlllIIIII1.start();
    }

    public boolean llIllllIlI1() throws InterruptedException {
        lIIIllllllIIII1();
        return IIlllllIlll1();
    }

    public void lllIlIlllI1() throws InterruptedException {
        close();
        this.f685IllllIllllll1.await();
    }

    public abstract void llllIIIIll1(int i, String str, boolean z);

    public abstract void llllIIIIll1(Exception exc);

    @Override // llIIllIl1.IIlIllIIll1
    public void llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1, int i, String str) {
    }

    @Override // llIIllIl1.IIlIllIIll1
    public void llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1, int i, String str, boolean z) {
    }

    @Override // llIIllIl1.IIlIllIIll1
    public final void llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1, ByteBuffer byteBuffer) {
    }

    public abstract void llllIIIIll1(IlIlIIlIII1 ilIlIIlIII1);

    public void llllIllIl1(int i, String str) {
    }

    @Override // llIIllIl1.IIlIllIIll1
    public final void llllIllIl1(IlIllIlllIllI1 ilIllIlllIllI1) {
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public lIIlIIIIlIlII1.IllIIlIIII1 lllllIllIl1() {
        return this.f690llIIIIlIlllIII1.lllllIllIl1();
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public boolean llllllIlIIIlll1() {
        return this.f690llIIIIlIlllIII1.llllllIlIIIlll1();
    }

    @Override // java.lang.Runnable
    public void run() {
        int read;
        try {
            boolean IIlIlllllllI1 = IIlIlllllllI1();
            this.f688lIllIlIll1.setTcpNoDelay(lIIlIIIIlIlII1());
            this.f688lIllIlIll1.setReuseAddress(lIlllIIIII1());
            int llIIllIl12 = llIIllIl1();
            if (llIIllIl12 > 0) {
                this.f688lIllIlIll1.setReceiveBufferSize(llIIllIl12);
            }
            if (!this.f688lIllIlIll1.isConnected()) {
                this.f688lIllIlIll1.connect(this.f693lllIlIlllI1 == null ? InetSocketAddress.createUnresolved(this.f682IlIIlllllI1.getHost(), IlIIIlIlIlIII1()) : new InetSocketAddress(this.f693lllIlIlllI1.llllIIIIll1(this.f682IlIIlllllI1), IlIIIlIlIlIII1()), this.f681IlIIIIllllIlI1);
            }
            if (IIlIlllllllI1 && "wss".equals(this.f682IlIIlllllI1.getScheme())) {
                IIIlIllIl1();
            }
            Socket socket = this.f688lIllIlIll1;
            if (socket instanceof SSLSocket) {
                SSLSocket sSLSocket = (SSLSocket) socket;
                SSLParameters sSLParameters = sSLSocket.getSSLParameters();
                llllIIIIll1(sSLParameters);
                sSLSocket.setSSLParameters(sSLParameters);
            }
            InputStream inputStream = this.f688lIllIlIll1.getInputStream();
            this.f680IIIlIllIlI1 = this.f688lIllIlIll1.getOutputStream();
            lIlIlIlI1();
            Thread thread = this.f692lllIlIIIlI1;
            if (thread != null) {
                thread.interrupt();
                try {
                    this.f692lllIlIIIlI1.join();
                } catch (InterruptedException unused) {
                }
            }
            Thread thread2 = new Thread(new RunnableC0022lIIIIlllllIlll1(this));
            this.f692lllIlIIIlI1 = thread2;
            thread2.setDaemon(lllIlIIIlI1());
            this.f692lllIlIIIlI1.start();
            int llIIllIl13 = llIIllIl1();
            if (llIIllIl13 <= 0) {
                llIIllIl13 = llIIllIl1.llllIIIIll1.f657lllllIllIl1;
            }
            byte[] bArr = new byte[llIIllIl13];
            while (!llllllIlIIIlll1() && !llIIIIlIlllIII1() && (read = inputStream.read(bArr)) != -1) {
                try {
                    this.f690llIIIIlIlllIII1.lIIIIlllllIlll1(ByteBuffer.wrap(bArr, 0, read));
                } catch (IOException e) {
                    llllIIIIll1(e);
                    return;
                } catch (RuntimeException e2) {
                    llllIIIIll1((Exception) e2);
                    this.f690llIIIIlIlllIII1.lIIIIlllllIlll1(1006, e2.getMessage());
                    return;
                }
            }
            this.f690llIIIIlIlllIII1.IlIlIIIlIlIlll1();
        } catch (Exception e3) {
            llllIIIIll1(e3);
            this.f690llIIIIlIlllIII1.lIIIIlllllIlll1(-1, e3.getMessage());
        } catch (InternalError e4) {
            if (!(e4.getCause() instanceof InvocationTargetException) || !(e4.getCause().getCause() instanceof IOException)) {
                throw e4;
            }
            IOException iOException = (IOException) e4.getCause().getCause();
            llllIIIIll1((Exception) iOException);
            this.f690llIIIIlIlllIII1.lIIIIlllllIlll1(-1, iOException.getMessage());
        }
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public lIlllIIIII1.llllIIIIll1 lIIIIlllllIlll1() {
        return this.f686lIIlIIIIlIlII1;
    }

    public String llllIllIl1(String str) {
        Map<String, String> map = this.f684IllIlIllll1;
        if (map == null) {
            return null;
        }
        return map.remove(str);
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public boolean IllIIlIIII1() {
        return this.f690llIIIIlIlllIII1.IllIIlIIII1();
    }

    public boolean lIIIIlllllIlll1(long j, TimeUnit timeUnit) throws InterruptedException {
        lIIIllllllIIII1();
        return llllIIIIll1(j, timeUnit);
    }

    public void llllIIIIll1(String str, String str2) {
        if (this.f684IllIlIllll1 == null) {
            this.f684IllIlIllll1 = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        }
        this.f684IllIlIllll1.put(str, str2);
    }

    public lIIIIlllllIlll1(URI uri, lIlllIIIII1.llllIIIIll1 lllliiiill1) {
        this(uri, lllliiiill1, null, 0);
    }

    public lIIIIlllllIlll1(URI uri, Map<String, String> map) {
        this(uri, new lIlllIIIII1.lIIIIlllllIlll1((List<IllIIlIIII1>) Collections.emptyList()), map, 0);
    }

    @Override // llIIllIl1.IIlIllIIll1
    public final void lIIIIlllllIlll1(IlIllIlllIllI1 ilIllIlllIllI1, int i, String str, boolean z) {
        IllllIllllll1();
        Thread thread = this.f692lllIlIIIlI1;
        if (thread != null) {
            thread.interrupt();
        }
        llllIIIIll1(i, str, z);
        this.f687lIIlllIIIlllII1.countDown();
        this.f685IllllIllllll1.countDown();
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void llllIllIl1() {
        this.f690llIIIIlIlllIII1.llllIllIl1();
    }

    public void llllIIIIll1(lllIlIIIlI1.llllIIIIll1 lllliiiill1) {
        this.f693lllIlIlllI1 = lllliiiill1;
    }

    public lIIIIlllllIlll1(URI uri, lIlllIIIII1.llllIIIIll1 lllliiiill1, Map<String, String> map) {
        this(uri, lllliiiill1, map, 0);
    }

    public boolean llllIIIIll1(long j, TimeUnit timeUnit) throws InterruptedException {
        llIlIIlll1();
        boolean await = this.f687lIIlllIIIlllII1.await(j, timeUnit);
        if (!await) {
            lIIIllllllIIII1();
        }
        return await && this.f690llIIIIlIlllIII1.isOpen();
    }

    public lIIIIlllllIlll1(URI uri, lIlllIIIII1.llllIIIIll1 lllliiiill1, Map<String, String> map, int i) {
        this.f682IlIIlllllI1 = null;
        this.f690llIIIIlIlllIII1 = null;
        this.f688lIllIlIll1 = null;
        this.f683IlIlIIIlIlIlll1 = null;
        this.f691llIIllIl1 = Proxy.NO_PROXY;
        this.f687lIIlllIIIlllII1 = new CountDownLatch(1);
        this.f685IllllIllllll1 = new CountDownLatch(1);
        this.f681IlIIIIllllIlI1 = 0;
        this.f693lllIlIlllI1 = null;
        if (uri == null) {
            throw new IllegalArgumentException();
        }
        if (lllliiiill1 != null) {
            this.f682IlIIlllllI1 = uri;
            this.f686lIIlIIIIlIlII1 = lllliiiill1;
            this.f693lllIlIlllI1 = new llllIIIIll1();
            if (map != null) {
                TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
                this.f684IllIlIllll1 = treeMap;
                treeMap.putAll(map);
            }
            this.f681IlIIIIllllIlI1 = i;
            llllIllIl1(false);
            lIIIIlllllIlll1(false);
            this.f690llIIIIlIlllIII1 = new lIllIIIlIl1(this, lllliiiill1);
            return;
        }
        throw new IllegalArgumentException("null as draft is permitted for `WebSocketServer` only!");
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void lIIIIlllllIlll1(int i, String str) {
        this.f690llIIIIlIlllIII1.lIIIIlllllIlll1(i, str);
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void llllIIIIll1(String str) {
        this.f690llIIIIlIlllIII1.llllIIIIll1(str);
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void llllIIIIll1(byte[] bArr) {
        this.f690llIIIIlIlllIII1.llllIIIIll1(bArr);
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public <T> void llllIIIIll1(T t) {
        this.f690llIIIIlIlllIII1.llllIIIIll1((lIllIIIlIl1) t);
    }

    public void llllIIIIll1(SSLParameters sSLParameters) {
        sSLParameters.setEndpointIdentificationAlgorithm("HTTPS");
    }

    @Override // llIIllIl1.IIlIllIIll1
    public final void llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1, String str) {
        lIIIIlllllIlll1(str);
    }

    @Override // llIIllIl1.IIlIllIIll1
    public final void llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1, lllIlIlllI1.IlIllIlllIllI1 ilIllIlllIllI12) {
        lIIlllIIIlllII1();
        llllIIIIll1((IlIlIIlIII1) ilIllIlllIllI12);
        this.f687lIIlllIIIlllII1.countDown();
    }

    @Override // llIIllIl1.IIlIllIIll1
    public final void llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1, Exception exc) {
        llllIIIIll1(exc);
    }

    @Override // llIIllIl1.IIlIllIIll1
    public InetSocketAddress llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1) {
        Socket socket = this.f688lIllIlIll1;
        if (socket != null) {
            return (InetSocketAddress) socket.getRemoteSocketAddress();
        }
        return null;
    }

    public void llllIIIIll1(Proxy proxy) {
        if (proxy != null) {
            this.f691llIIllIl1 = proxy;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Deprecated
    public void llllIIIIll1(Socket socket) {
        if (this.f688lIllIlIll1 == null) {
            this.f688lIllIlIll1 = socket;
            return;
        }
        throw new IllegalStateException("socket has already been set");
    }

    public void llllIIIIll1(SocketFactory socketFactory) {
        this.f683IlIlIIIlIlIlll1 = socketFactory;
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void llllIIIIll1(llllIllIl1 llllillil1, ByteBuffer byteBuffer, boolean z) {
        this.f690llIIIIlIlllIII1.llllIIIIll1(llllillil1, byteBuffer, z);
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void llllIIIIll1(int i) {
        this.f690llIIIIlIlllIII1.llllIIIIll1(i);
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void llllIIIIll1(int i, String str) {
        this.f690llIIIIlIlllIII1.llllIIIIll1(i, str);
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void llllIIIIll1(ByteBuffer byteBuffer) {
        this.f690llIIIIlIlllIII1.llllIIIIll1(byteBuffer);
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void llllIIIIll1(IlIIIIllllIlI1.IlIllIlllIllI1 ilIllIlllIllI1) {
        this.f690llIIIIlIlllIII1.llllIIIIll1(ilIllIlllIllI1);
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void llllIIIIll1(Collection<IlIIIIllllIlI1.IlIllIlllIllI1> collection) {
        this.f690llIIIIlIlllIII1.llllIIIIll1(collection);
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public String llllIIIIll1() {
        return this.f682IlIIlllllI1.getPath();
    }

    public final void llllIIIIll1(IOException iOException) {
        if (iOException instanceof SSLException) {
            llllIIIIll1((Exception) iOException);
        }
        this.f690llIIIIlIlllIII1.IlIlIIIlIlIlll1();
    }
}

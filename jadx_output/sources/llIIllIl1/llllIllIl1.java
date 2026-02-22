package llIIllIl1;

import android.util.Log;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;

/* loaded from: classes.dex */
public class llllIllIl1 implements lllllIllIl1, ByteChannel, llIlIIlll1.llllIIIIll1 {

    /* renamed from: IlIlIIlIII1, reason: collision with root package name */
    public static final String f669IlIlIIlIII1 = "SSLSocketChannel";

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public ByteBuffer f670IlIllIlllIllI1;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public ByteBuffer f671IlIlllIIlI1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public ByteBuffer f672IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final SSLEngine f673lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final SocketChannel f674llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public ByteBuffer f675llllIllIl1;

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public ExecutorService f676llllllIlIIIlll1;

    public static /* synthetic */ class llllIIIIll1 {

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public static final /* synthetic */ int[] f677lIIIIlllllIlll1;

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public static final /* synthetic */ int[] f678llllIIIIll1;

        static {
            int[] iArr = new int[SSLEngineResult.HandshakeStatus.values().length];
            f677lIIIIlllllIlll1 = iArr;
            try {
                iArr[SSLEngineResult.HandshakeStatus.FINISHED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f677lIIIIlllllIlll1[SSLEngineResult.HandshakeStatus.NEED_UNWRAP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f677lIIIIlllllIlll1[SSLEngineResult.HandshakeStatus.NEED_WRAP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f677lIIIIlllllIlll1[SSLEngineResult.HandshakeStatus.NEED_TASK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f677lIIIIlllllIlll1[SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[SSLEngineResult.Status.values().length];
            f678llllIIIIll1 = iArr2;
            try {
                iArr2[SSLEngineResult.Status.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f678llllIIIIll1[SSLEngineResult.Status.BUFFER_UNDERFLOW.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f678llllIIIIll1[SSLEngineResult.Status.BUFFER_OVERFLOW.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f678llllIIIIll1[SSLEngineResult.Status.CLOSED.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public llllIllIl1(SocketChannel socketChannel, SSLEngine sSLEngine, ExecutorService executorService, SelectionKey selectionKey) throws IOException {
        if (socketChannel == null || sSLEngine == null || this.f676llllllIlIIIlll1 == executorService) {
            throw new IllegalArgumentException("parameter must not be null");
        }
        this.f674llllIIIIll1 = socketChannel;
        this.f673lIIIIlllllIlll1 = sSLEngine;
        this.f676llllllIlIIIlll1 = executorService;
        this.f672IllIIlIIII1 = ByteBuffer.allocate(sSLEngine.getSession().getPacketBufferSize());
        this.f670IlIllIlllIllI1 = ByteBuffer.allocate(sSLEngine.getSession().getPacketBufferSize());
        sSLEngine.beginHandshake();
        if (llllllIlIIIlll1()) {
            if (selectionKey != null) {
                selectionKey.interestOps(selectionKey.interestOps() | 4);
            }
        } else {
            try {
                socketChannel.close();
            } catch (IOException e) {
                Log.e(f669IlIlIIlIII1, "Exception during the closing of the channel", e);
            }
        }
    }

    public final void IlIlIIlIII1() throws IOException {
        try {
            this.f673lIIIIlllllIlll1.closeInbound();
        } catch (Exception unused) {
            Log.e(f669IlIlIIlIII1, "This engine was forced to close inbound, without having received the proper SSL/TLS close notification message from the peer, due to end of stream.");
        }
        IlIllIlllIllI1();
    }

    public final void IlIllIlllIllI1() throws IOException {
        this.f673lIIIIlllllIlll1.closeOutbound();
        try {
            llllllIlIIIlll1();
        } catch (IOException unused) {
        }
        this.f674llllIIIIll1.close();
    }

    @Override // llIIllIl1.lllllIllIl1
    public boolean IlIlllIIlI1() {
        return this.f670IlIllIlllIllI1.hasRemaining() || this.f671IlIlllIIlI1.hasRemaining();
    }

    public final ByteBuffer IllIIlIIII1(ByteBuffer byteBuffer) {
        if (this.f673lIIIIlllllIlll1.getSession().getPacketBufferSize() < byteBuffer.limit()) {
            return byteBuffer;
        }
        ByteBuffer llllIIIIll12 = llllIIIIll1(byteBuffer, this.f673lIIIIlllllIlll1.getSession().getPacketBufferSize());
        byteBuffer.flip();
        llllIIIIll12.put(byteBuffer);
        return llllIIIIll12;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        IlIllIlllIllI1();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return this.f674llllIIIIll1.isOpen();
    }

    public final ByteBuffer lIIIIlllllIlll1(ByteBuffer byteBuffer) {
        return llllIIIIll1(byteBuffer, this.f673lIIIIlllllIlll1.getSession().getApplicationBufferSize());
    }

    @Override // llIIllIl1.lllllIllIl1
    public boolean lIIIIlllllIlll1() {
        return false;
    }

    public final ByteBuffer llllIIIIll1(ByteBuffer byteBuffer, int i) {
        return i > byteBuffer.capacity() ? ByteBuffer.allocate(i) : ByteBuffer.allocate(byteBuffer.capacity() * 2);
    }

    @Override // llIIllIl1.lllllIllIl1
    public void llllIIIIll1() throws IOException {
    }

    public final ByteBuffer llllIllIl1(ByteBuffer byteBuffer) {
        return llllIIIIll1(byteBuffer, this.f673lIIIIlllllIlll1.getSession().getPacketBufferSize());
    }

    public final boolean llllllIlIIIlll1() throws IOException {
        SSLEngineResult.HandshakeStatus handshakeStatus;
        int applicationBufferSize = this.f673lIIIIlllllIlll1.getSession().getApplicationBufferSize();
        this.f675llllIllIl1 = ByteBuffer.allocate(applicationBufferSize);
        this.f671IlIlllIIlI1 = ByteBuffer.allocate(applicationBufferSize);
        this.f672IllIIlIIII1.clear();
        this.f670IlIllIlllIllI1.clear();
        SSLEngineResult.HandshakeStatus handshakeStatus2 = this.f673lIIIIlllllIlll1.getHandshakeStatus();
        boolean z = false;
        while (!z) {
            int i = llllIIIIll1.f677lIIIIlllllIlll1[handshakeStatus2.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        this.f672IllIIlIIII1.clear();
                        try {
                            SSLEngineResult wrap = this.f673lIIIIlllllIlll1.wrap(this.f675llllIllIl1, this.f672IllIIlIIII1);
                            handshakeStatus = wrap.getHandshakeStatus();
                            int i2 = llllIIIIll1.f678llllIIIIll1[wrap.getStatus().ordinal()];
                            if (i2 == 1) {
                                this.f672IllIIlIIII1.flip();
                                while (this.f672IllIIlIIII1.hasRemaining()) {
                                    this.f674llllIIIIll1.write(this.f672IllIIlIIII1);
                                }
                            } else {
                                if (i2 == 2) {
                                    throw new SSLException("Buffer underflow occurred after a wrap. I don't think we should ever get here.");
                                }
                                if (i2 == 3) {
                                    this.f672IllIIlIIII1 = llllIIIIll1(this.f672IllIIlIIII1, this.f673lIIIIlllllIlll1.getSession().getPacketBufferSize());
                                } else {
                                    if (i2 != 4) {
                                        throw new IllegalStateException("Invalid SSL status: " + wrap.getStatus());
                                    }
                                    try {
                                        this.f672IllIIlIIII1.flip();
                                        while (this.f672IllIIlIIII1.hasRemaining()) {
                                            this.f674llllIIIIll1.write(this.f672IllIIlIIII1);
                                        }
                                        this.f670IlIllIlllIllI1.clear();
                                    } catch (Exception unused) {
                                        handshakeStatus2 = this.f673lIIIIlllllIlll1.getHandshakeStatus();
                                    }
                                }
                            }
                        } catch (SSLException unused2) {
                            this.f673lIIIIlllllIlll1.closeOutbound();
                            handshakeStatus2 = this.f673lIIIIlllllIlll1.getHandshakeStatus();
                        }
                    } else if (i == 4) {
                        while (true) {
                            Runnable delegatedTask = this.f673lIIIIlllllIlll1.getDelegatedTask();
                            if (delegatedTask == null) {
                                break;
                            }
                            this.f676llllllIlIIIlll1.execute(delegatedTask);
                        }
                        handshakeStatus2 = this.f673lIIIIlllllIlll1.getHandshakeStatus();
                    } else if (i != 5) {
                        throw new IllegalStateException("Invalid SSL status: " + handshakeStatus2);
                    }
                } else if (this.f674llllIIIIll1.read(this.f670IlIllIlllIllI1) >= 0) {
                    this.f670IlIllIlllIllI1.flip();
                    try {
                        SSLEngineResult unwrap = this.f673lIIIIlllllIlll1.unwrap(this.f670IlIllIlllIllI1, this.f671IlIlllIIlI1);
                        this.f670IlIllIlllIllI1.compact();
                        handshakeStatus = unwrap.getHandshakeStatus();
                        int i3 = llllIIIIll1.f678llllIIIIll1[unwrap.getStatus().ordinal()];
                        if (i3 != 1) {
                            if (i3 == 2) {
                                this.f670IlIllIlllIllI1 = IllIIlIIII1(this.f670IlIllIlllIllI1);
                            } else if (i3 == 3) {
                                this.f671IlIlllIIlI1 = llllIIIIll1(this.f671IlIlllIIlI1, this.f673lIIIIlllllIlll1.getSession().getApplicationBufferSize());
                            } else {
                                if (i3 != 4) {
                                    throw new IllegalStateException("Invalid SSL status: " + unwrap.getStatus());
                                }
                                if (this.f673lIIIIlllllIlll1.isOutboundDone()) {
                                    return false;
                                }
                                this.f673lIIIIlllllIlll1.closeOutbound();
                                handshakeStatus2 = this.f673lIIIIlllllIlll1.getHandshakeStatus();
                            }
                        }
                    } catch (SSLException unused3) {
                        this.f673lIIIIlllllIlll1.closeOutbound();
                        handshakeStatus2 = this.f673lIIIIlllllIlll1.getHandshakeStatus();
                    }
                } else {
                    if (this.f673lIIIIlllllIlll1.isInboundDone() && this.f673lIIIIlllllIlll1.isOutboundDone()) {
                        return false;
                    }
                    try {
                        this.f673lIIIIlllllIlll1.closeInbound();
                    } catch (SSLException unused4) {
                    }
                    this.f673lIIIIlllllIlll1.closeOutbound();
                    handshakeStatus2 = this.f673lIIIIlllllIlll1.getHandshakeStatus();
                }
                handshakeStatus2 = handshakeStatus;
            } else {
                boolean hasRemaining = this.f670IlIllIlllIllI1.hasRemaining();
                boolean z2 = !hasRemaining;
                if (!hasRemaining) {
                    return true;
                }
                this.f674llllIIIIll1.write(this.f670IlIllIlllIllI1);
                z = z2;
            }
        }
        return true;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public synchronized int read(ByteBuffer byteBuffer) throws IOException {
        if (!byteBuffer.hasRemaining()) {
            return 0;
        }
        if (this.f671IlIlllIIlI1.hasRemaining()) {
            this.f671IlIlllIIlI1.flip();
            return IlIIIlIlIlIII1.lIIIIlllllIlll1.llllIIIIll1(this.f671IlIlllIIlI1, byteBuffer);
        }
        this.f670IlIllIlllIllI1.compact();
        int read = this.f674llllIIIIll1.read(this.f670IlIllIlllIllI1);
        if (read <= 0 && !this.f670IlIllIlllIllI1.hasRemaining()) {
            if (read < 0) {
                IlIlIIlIII1();
            }
            IlIIIlIlIlIII1.lIIIIlllllIlll1.llllIIIIll1(this.f671IlIlllIIlI1, byteBuffer);
            return read;
        }
        this.f670IlIllIlllIllI1.flip();
        if (this.f670IlIllIlllIllI1.hasRemaining()) {
            this.f671IlIlllIIlI1.compact();
            try {
                SSLEngineResult unwrap = this.f673lIIIIlllllIlll1.unwrap(this.f670IlIllIlllIllI1, this.f671IlIlllIIlI1);
                int i = llllIIIIll1.f678llllIIIIll1[unwrap.getStatus().ordinal()];
                if (i == 1) {
                    this.f671IlIlllIIlI1.flip();
                    return IlIIIlIlIlIII1.lIIIIlllllIlll1.llllIIIIll1(this.f671IlIlllIIlI1, byteBuffer);
                }
                if (i == 2) {
                    this.f671IlIlllIIlI1.flip();
                    return IlIIIlIlIlIII1.lIIIIlllllIlll1.llllIIIIll1(this.f671IlIlllIIlI1, byteBuffer);
                }
                if (i == 3) {
                    this.f671IlIlllIIlI1 = llllIIIIll1(this.f671IlIlllIIlI1, this.f673lIIIIlllllIlll1.getSession().getApplicationBufferSize());
                    return read(byteBuffer);
                }
                if (i != 4) {
                    throw new IllegalStateException("Invalid SSL status: " + unwrap.getStatus());
                }
                IlIllIlllIllI1();
                byteBuffer.clear();
                return -1;
            } catch (SSLException e) {
                Log.e(f669IlIlIIlIII1, "SSLException during unwrap", e);
                throw e;
            }
        }
        IlIIIlIlIlIII1.lIIIIlllllIlll1.llllIIIIll1(this.f671IlIlllIIlI1, byteBuffer);
        return read;
    }

    @Override // java.nio.channels.WritableByteChannel
    public synchronized int write(ByteBuffer byteBuffer) throws IOException {
        int i = 0;
        while (byteBuffer.hasRemaining()) {
            this.f672IllIIlIIII1.clear();
            SSLEngineResult wrap = this.f673lIIIIlllllIlll1.wrap(byteBuffer, this.f672IllIIlIIII1);
            int i2 = llllIIIIll1.f678llllIIIIll1[wrap.getStatus().ordinal()];
            if (i2 == 1) {
                this.f672IllIIlIIII1.flip();
                while (this.f672IllIIlIIII1.hasRemaining()) {
                    i += this.f674llllIIIIll1.write(this.f672IllIIlIIII1);
                }
            } else {
                if (i2 == 2) {
                    throw new SSLException("Buffer underflow occurred after a wrap. I don't think we should ever get here.");
                }
                if (i2 != 3) {
                    if (i2 != 4) {
                        throw new IllegalStateException("Invalid SSL status: " + wrap.getStatus());
                    }
                    IlIllIlllIllI1();
                    return 0;
                }
                this.f672IllIIlIIII1 = llllIIIIll1(this.f672IllIIlIIII1, this.f673lIIIIlllllIlll1.getSession().getPacketBufferSize());
            }
        }
        return i;
    }

    @Override // llIIllIl1.lllllIllIl1
    public boolean llllIllIl1() {
        return this.f674llllIIIIll1.isBlocking();
    }

    @Override // llIlIIlll1.llllIIIIll1
    public SSLEngine IllIIlIIII1() {
        return this.f673lIIIIlllllIlll1;
    }

    @Override // llIIllIl1.lllllIllIl1
    public int llllIIIIll1(ByteBuffer byteBuffer) throws IOException {
        return read(byteBuffer);
    }
}

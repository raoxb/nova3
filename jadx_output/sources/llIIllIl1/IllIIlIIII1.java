package llIIllIl1;

import android.util.Log;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

/* loaded from: classes.dex */
public class IllIIlIIII1 implements ByteChannel, lllllIllIl1, llIlIIlll1.llllIIIIll1 {

    /* renamed from: IlIIlllllI1, reason: collision with root package name */
    public static ByteBuffer f616IlIIlllllI1 = ByteBuffer.allocate(0);

    /* renamed from: lIllIlIll1, reason: collision with root package name */
    public static final /* synthetic */ boolean f617lIllIlIll1 = true;

    /* renamed from: llIIIIlIlllIII1, reason: collision with root package name */
    public static final String f618llIIIIlIlllIII1 = "SSLSocketChannel2";

    /* renamed from: IIlIllIIll1, reason: collision with root package name */
    public SSLEngineResult f619IIlIllIIll1;

    /* renamed from: IlIlIIlIII1, reason: collision with root package name */
    public SSLEngine f620IlIlIIlIII1;

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public SocketChannel f621IlIllIlllIllI1;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public ByteBuffer f622IlIlllIIlI1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public ByteBuffer f624IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public List<Future<?>> f625lIIIIlllllIlll1;

    /* renamed from: lIllIIIlIl1, reason: collision with root package name */
    public SSLEngineResult f626lIllIIIlIl1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public ExecutorService f627llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public ByteBuffer f628llllIllIl1;

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public SelectionKey f630llllllIlIIIlll1;

    /* renamed from: IlIllll1, reason: collision with root package name */
    public int f623IlIllll1 = 0;

    /* renamed from: lllllIllIl1, reason: collision with root package name */
    public byte[] f629lllllIllIl1 = null;

    public IllIIlIIII1(SocketChannel socketChannel, SSLEngine sSLEngine, ExecutorService executorService, SelectionKey selectionKey) throws IOException {
        if (socketChannel == null || sSLEngine == null || executorService == null) {
            throw new IllegalArgumentException("parameter must not be null");
        }
        this.f621IlIllIlllIllI1 = socketChannel;
        this.f620IlIlIIlIII1 = sSLEngine;
        this.f627llllIIIIll1 = executorService;
        SSLEngineResult sSLEngineResult = new SSLEngineResult(SSLEngineResult.Status.BUFFER_UNDERFLOW, sSLEngine.getHandshakeStatus(), 0, 0);
        this.f619IIlIllIIll1 = sSLEngineResult;
        this.f626lIllIIIlIl1 = sSLEngineResult;
        this.f625lIIIIlllllIlll1 = new ArrayList(3);
        if (selectionKey != null) {
            selectionKey.interestOps(selectionKey.interestOps() | 4);
            this.f630llllllIlIIIlll1 = selectionKey;
        }
        llllIIIIll1(sSLEngine.getSession());
        this.f621IlIllIlllIllI1.write(llllIllIl1(f616IlIIlllllI1));
        lIIIIlllllIlll1(false);
    }

    public boolean IIlIllIIll1() {
        return this.f620IlIlIIlIII1.isInboundDone();
    }

    public final void IlIIlllllI1() {
        if (this.f629lllllIllIl1 != null) {
            this.f622IlIlllIIlI1.clear();
            this.f622IlIlllIIlI1.put(this.f629lllllIllIl1);
            this.f622IlIlllIIlI1.flip();
            this.f629lllllIllIl1 = null;
        }
    }

    public boolean IlIlIIlIII1() {
        return this.f621IlIllIlllIllI1.isConnected();
    }

    public void IlIllIlllIllI1() {
        while (true) {
            Runnable delegatedTask = this.f620IlIlIIlIII1.getDelegatedTask();
            if (delegatedTask == null) {
                return;
            } else {
                this.f625lIIIIlllllIlll1.add(this.f627llllIIIIll1.submit(delegatedTask));
            }
        }
    }

    @Override // llIIllIl1.lllllIllIl1
    public boolean IlIlllIIlI1() {
        return (this.f629lllllIllIl1 == null && !this.f628llllIllIl1.hasRemaining() && (!this.f622IlIlllIIlI1.hasRemaining() || this.f626lIllIIIlIl1.getStatus() == SSLEngineResult.Status.BUFFER_UNDERFLOW || this.f626lIllIIIlIl1.getStatus() == SSLEngineResult.Status.CLOSED)) ? false : true;
    }

    public final void IlIllll1() {
        ByteBuffer byteBuffer = this.f622IlIlllIIlI1;
        if (byteBuffer == null || byteBuffer.remaining() <= 0) {
            return;
        }
        byte[] bArr = new byte[this.f622IlIlllIIlI1.remaining()];
        this.f629lllllIllIl1 = bArr;
        this.f622IlIlllIIlI1.get(bArr);
    }

    @Override // llIlIIlll1.llllIIIIll1
    public SSLEngine IllIIlIIII1() {
        return this.f620IlIlIIlIII1;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f620IlIlIIlIII1.closeOutbound();
        this.f620IlIlIIlIII1.getSession().invalidate();
        try {
            if (this.f621IlIllIlllIllI1.isOpen()) {
                this.f621IlIllIlllIllI1.write(llllIllIl1(f616IlIIlllllI1));
            }
        } finally {
            this.f621IlIllIlllIllI1.close();
        }
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return this.f621IlIllIlllIllI1.isOpen();
    }

    public final synchronized void lIIIIlllllIlll1(boolean z) throws IOException {
        if (this.f620IlIlIIlIII1.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) {
            return;
        }
        if (!this.f625lIIIIlllllIlll1.isEmpty()) {
            Iterator<Future<?>> it = this.f625lIIIIlllllIlll1.iterator();
            while (it.hasNext()) {
                Future<?> next = it.next();
                if (!next.isDone()) {
                    if (llllIllIl1()) {
                        llllIIIIll1(next);
                    }
                    return;
                }
                it.remove();
            }
        }
        if (z && this.f620IlIlIIlIII1.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NEED_UNWRAP) {
            if (!llllIllIl1() || this.f626lIllIIIlIl1.getStatus() == SSLEngineResult.Status.BUFFER_UNDERFLOW) {
                this.f622IlIlllIIlI1.compact();
                if (this.f621IlIllIlllIllI1.read(this.f622IlIlllIIlI1) == -1) {
                    throw new IOException("connection closed unexpectedly by peer");
                }
                this.f622IlIlllIIlI1.flip();
            }
            this.f628llllIllIl1.compact();
            llIIIIlIlllIII1();
            if (this.f626lIllIIIlIl1.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                llllIIIIll1(this.f620IlIlIIlIII1.getSession());
                return;
            }
        }
        IlIllIlllIllI1();
        if (this.f625lIIIIlllllIlll1.isEmpty() || this.f620IlIlIIlIII1.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NEED_WRAP) {
            this.f621IlIllIlllIllI1.write(llllIllIl1(f616IlIIlllllI1));
            if (this.f619IIlIllIIll1.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                llllIIIIll1(this.f620IlIlIIlIII1.getSession());
                return;
            }
        }
        if (!f617lIllIlIll1 && this.f620IlIlIIlIII1.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) {
            throw new AssertionError();
        }
        this.f623IlIllll1 = 1;
    }

    public final boolean lIllIIIlIl1() {
        SSLEngineResult.HandshakeStatus handshakeStatus = this.f620IlIlIIlIII1.getHandshakeStatus();
        return handshakeStatus == SSLEngineResult.HandshakeStatus.FINISHED || handshakeStatus == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
    }

    public final synchronized ByteBuffer llIIIIlIlllIII1() throws SSLException {
        if (this.f626lIllIIIlIl1.getStatus() == SSLEngineResult.Status.CLOSED && this.f620IlIlIIlIII1.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) {
            try {
                close();
            } catch (IOException unused) {
            }
        }
        while (true) {
            int remaining = this.f628llllIllIl1.remaining();
            SSLEngineResult unwrap = this.f620IlIlIIlIII1.unwrap(this.f622IlIlllIIlI1, this.f628llllIllIl1);
            this.f626lIllIIIlIl1 = unwrap;
            if (unwrap.getStatus() != SSLEngineResult.Status.OK || (remaining == this.f628llllIllIl1.remaining() && this.f620IlIlIIlIII1.getHandshakeStatus() != SSLEngineResult.HandshakeStatus.NEED_UNWRAP)) {
                break;
            }
        }
        this.f628llllIllIl1.flip();
        return this.f628llllIllIl1;
    }

    public final void llllIIIIll1(Future<?> future) {
        while (true) {
            try {
                try {
                    future.get();
                    return;
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public final synchronized ByteBuffer llllIllIl1(ByteBuffer byteBuffer) throws SSLException {
        this.f624IllIIlIIII1.compact();
        this.f619IIlIllIIll1 = this.f620IlIlIIlIII1.wrap(byteBuffer, this.f624IllIIlIIII1);
        this.f624IllIIlIIII1.flip();
        return this.f624IllIIlIIII1;
    }

    public Socket lllllIllIl1() {
        return this.f621IlIllIlllIllI1.socket();
    }

    public boolean llllllIlIIIlll1() throws IOException {
        return this.f621IlIllIlllIllI1.finishConnect();
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        IlIIlllllI1();
        while (byteBuffer.hasRemaining()) {
            if (!lIllIIIlIl1()) {
                if (llllIllIl1()) {
                    while (!lIllIIIlIl1()) {
                        lIIIIlllllIlll1(true);
                    }
                } else {
                    lIIIIlllllIlll1(true);
                    if (!lIllIIIlIl1()) {
                        return 0;
                    }
                }
            }
            int lIIIIlllllIlll12 = lIIIIlllllIlll1(byteBuffer);
            if (lIIIIlllllIlll12 != 0) {
                return lIIIIlllllIlll12;
            }
            if (!f617lIllIlIll1 && this.f628llllIllIl1.position() != 0) {
                throw new AssertionError();
            }
            this.f628llllIllIl1.clear();
            if (this.f622IlIlllIIlI1.hasRemaining()) {
                this.f622IlIlllIIlI1.compact();
            } else {
                this.f622IlIlllIIlI1.clear();
            }
            if ((llllIllIl1() || this.f626lIllIIIlIl1.getStatus() == SSLEngineResult.Status.BUFFER_UNDERFLOW) && this.f621IlIllIlllIllI1.read(this.f622IlIlllIIlI1) == -1) {
                return -1;
            }
            this.f622IlIlllIIlI1.flip();
            llIIIIlIlllIII1();
            int llllIIIIll12 = llllIIIIll1(this.f628llllIllIl1, byteBuffer);
            if (llllIIIIll12 != 0 || !llllIllIl1()) {
                return llllIIIIll12;
            }
        }
        return 0;
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        if (!lIllIIIlIl1()) {
            lIIIIlllllIlll1(false);
            return 0;
        }
        int write = this.f621IlIllIlllIllI1.write(llllIllIl1(byteBuffer));
        if (this.f619IIlIllIIll1.getStatus() != SSLEngineResult.Status.CLOSED) {
            return write;
        }
        throw new EOFException("Connection is closed");
    }

    @Override // llIIllIl1.lllllIllIl1
    public boolean llllIllIl1() {
        return this.f621IlIllIlllIllI1.isBlocking();
    }

    public void llllIIIIll1(SSLSession sSLSession) {
        IlIllll1();
        int packetBufferSize = sSLSession.getPacketBufferSize();
        int max = Math.max(sSLSession.getApplicationBufferSize(), packetBufferSize);
        ByteBuffer byteBuffer = this.f628llllIllIl1;
        if (byteBuffer == null) {
            this.f628llllIllIl1 = ByteBuffer.allocate(max);
            this.f624IllIIlIIII1 = ByteBuffer.allocate(packetBufferSize);
            this.f622IlIlllIIlI1 = ByteBuffer.allocate(packetBufferSize);
        } else {
            if (byteBuffer.capacity() != max) {
                this.f628llllIllIl1 = ByteBuffer.allocate(max);
            }
            if (this.f624IllIIlIIII1.capacity() != packetBufferSize) {
                this.f624IllIIlIIII1 = ByteBuffer.allocate(packetBufferSize);
            }
            if (this.f622IlIlllIIlI1.capacity() != packetBufferSize) {
                this.f622IlIlllIIlI1 = ByteBuffer.allocate(packetBufferSize);
            }
        }
        if (this.f628llllIllIl1.remaining() != 0) {
            Log.v(f618llIIIIlIlllIII1, new String(this.f628llllIllIl1.array(), this.f628llllIllIl1.position(), this.f628llllIllIl1.remaining()));
        }
        this.f628llllIllIl1.rewind();
        this.f628llllIllIl1.flip();
        if (this.f622IlIlllIIlI1.remaining() != 0) {
            Log.v(f618llIIIIlIlllIII1, new String(this.f622IlIlllIIlI1.array(), this.f622IlIlllIIlI1.position(), this.f622IlIlllIIlI1.remaining()));
        }
        this.f622IlIlllIIlI1.rewind();
        this.f622IlIlllIIlI1.flip();
        this.f624IllIIlIIII1.rewind();
        this.f624IllIIlIIII1.flip();
        this.f623IlIllll1++;
    }

    public SelectableChannel llllIIIIll1(boolean z) throws IOException {
        return this.f621IlIllIlllIllI1.configureBlocking(z);
    }

    public boolean llllIIIIll1(SocketAddress socketAddress) throws IOException {
        return this.f621IlIllIlllIllI1.connect(socketAddress);
    }

    @Override // llIIllIl1.lllllIllIl1
    public void llllIIIIll1() throws IOException {
        write(this.f624IllIIlIIII1);
    }

    @Override // llIIllIl1.lllllIllIl1
    public int llllIIIIll1(ByteBuffer byteBuffer) throws SSLException {
        return lIIIIlllllIlll1(byteBuffer);
    }

    public final int llllIIIIll1(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        int remaining = byteBuffer.remaining();
        int remaining2 = byteBuffer2.remaining();
        if (remaining > remaining2) {
            int min = Math.min(remaining, remaining2);
            for (int i = 0; i < min; i++) {
                byteBuffer2.put(byteBuffer.get());
            }
            return min;
        }
        byteBuffer2.put(byteBuffer);
        return remaining;
    }

    public final int lIIIIlllllIlll1(ByteBuffer byteBuffer) throws SSLException {
        if (this.f628llllIllIl1.hasRemaining()) {
            return llllIIIIll1(this.f628llllIllIl1, byteBuffer);
        }
        if (!this.f628llllIllIl1.hasRemaining()) {
            this.f628llllIllIl1.clear();
        }
        IlIIlllllI1();
        if (!this.f622IlIlllIIlI1.hasRemaining()) {
            return 0;
        }
        llIIIIlIlllIII1();
        int llllIIIIll12 = llllIIIIll1(this.f628llllIllIl1, byteBuffer);
        if (this.f626lIllIIIlIl1.getStatus() == SSLEngineResult.Status.CLOSED) {
            return -1;
        }
        if (llllIIIIll12 > 0) {
            return llllIIIIll12;
        }
        return 0;
    }

    @Override // llIIllIl1.lllllIllIl1
    public boolean lIIIIlllllIlll1() {
        return this.f624IllIIlIIII1.hasRemaining() || !lIllIIIlIl1();
    }
}

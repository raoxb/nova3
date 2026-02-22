package llIIllIl1;

import IIlIIllll1.IlIlllIIlI1;
import android.util.Log;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.net.ssl.SSLSession;

/* loaded from: classes.dex */
public class lIllIIIlIl1 implements IlIllIlllIllI1 {

    /* renamed from: IllIlIllll1, reason: collision with root package name */
    public static final int f632IllIlIllll1 = 443;

    /* renamed from: IllllIllllll1, reason: collision with root package name */
    public static final /* synthetic */ boolean f633IllllIllllll1 = true;

    /* renamed from: lIIlIIIIlIlII1, reason: collision with root package name */
    public static final int f634lIIlIIIIlIlII1 = 80;

    /* renamed from: lIIlllIIIlllII1, reason: collision with root package name */
    public static final String f635lIIlllIIIlllII1 = "WebSocketImpl";

    /* renamed from: IIIlIllIlI1, reason: collision with root package name */
    public String f636IIIlIllIlI1;

    /* renamed from: IIlIllIIll1, reason: collision with root package name */
    public lIlllIIIII1.llllIIIIll1 f637IIlIllIIll1;

    /* renamed from: IlIIlllllI1, reason: collision with root package name */
    public lllIlIlllI1.llllIIIIll1 f638IlIIlllllI1;

    /* renamed from: IlIlIIIlIlIlll1, reason: collision with root package name */
    public Boolean f639IlIlIIIlIlIlll1;

    /* renamed from: IlIlIIlIII1, reason: collision with root package name */
    public volatile lIIlIIIIlIlII1.IllIIlIIII1 f640IlIlIIlIII1;

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public IlIlllIIlI1.llllIIIIll1 f641IlIllIlllIllI1;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public ByteChannel f642IlIlllIIlI1;

    /* renamed from: IlIllll1, reason: collision with root package name */
    public lIIlIIIIlIlII1.IlIlllIIlI1 f643IlIllll1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public SelectionKey f644IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final BlockingQueue<ByteBuffer> f645lIIIIlllllIlll1;

    /* renamed from: lIllIIIlIl1, reason: collision with root package name */
    public List<lIlllIIIII1.llllIIIIll1> f646lIllIIIlIl1;

    /* renamed from: lIllIlIll1, reason: collision with root package name */
    public Integer f647lIllIlIll1;

    /* renamed from: lIlllIIIII1, reason: collision with root package name */
    public Object f648lIlllIIIII1;

    /* renamed from: llIIIIlIlllIII1, reason: collision with root package name */
    public String f649llIIIIlIlllIII1;

    /* renamed from: llIIllIl1, reason: collision with root package name */
    public long f650llIIllIl1;

    /* renamed from: lllIlIIIlI1, reason: collision with root package name */
    public final Object f651lllIlIIIlI1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final BlockingQueue<ByteBuffer> f652llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final IIlIllIIll1 f653llllIllIl1;

    /* renamed from: lllllIllIl1, reason: collision with root package name */
    public ByteBuffer f654lllllIllIl1;

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public boolean f655llllllIlIIIlll1;

    public lIllIIIlIl1(IIlIllIIll1 iIlIllIIll1, List<lIlllIIIII1.llllIIIIll1> list) {
        this(iIlIllIIll1, (lIlllIIIII1.llllIIIIll1) null);
        this.f643IlIllll1 = lIIlIIIIlIlII1.IlIlllIIlI1.SERVER;
        if (list != null && !list.isEmpty()) {
            this.f646lIllIIIlIl1 = list;
            return;
        }
        ArrayList arrayList = new ArrayList();
        this.f646lIllIIIlIl1 = arrayList;
        arrayList.add(new lIlllIIIII1.lIIIIlllllIlll1((List<lIIlllIIIlllII1.IllIIlIIII1>) Collections.emptyList()));
    }

    public ByteChannel IIIlIllIlI1() {
        return this.f642IlIlllIIlI1;
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public boolean IIlIllIIll1() {
        return this.f642IlIlllIIlI1 instanceof llIlIIlll1.llllIIIIll1;
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public boolean IlIIlllllI1() {
        return !this.f652llllIIIIll1.isEmpty();
    }

    public void IlIlIIIlIlIlll1() {
        if (this.f640IlIlIIlIII1 == lIIlIIIIlIlII1.IllIIlIIII1.NOT_YET_CONNECTED) {
            llllIIIIll1(-1, true);
            return;
        }
        if (this.f655llllllIlIIIlll1) {
            lIIIIlllllIlll1(this.f647lIllIlIll1.intValue(), this.f649llIIIIlIlllIII1, this.f639IlIlIIIlIlIlll1.booleanValue());
            return;
        }
        if (this.f637IIlIllIIll1.lIIIIlllllIlll1() == lIIlIIIIlIlII1.llllIIIIll1.NONE) {
            llllIIIIll1(1000, true);
            return;
        }
        if (this.f637IIlIllIIll1.lIIIIlllllIlll1() != lIIlIIIIlIlII1.llllIIIIll1.ONEWAY) {
            llllIIIIll1(1006, true);
        } else if (this.f643IlIllll1 == lIIlIIIIlIlII1.IlIlllIIlI1.SERVER) {
            llllIIIIll1(1006, true);
        } else {
            llllIIIIll1(1000, true);
        }
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public SSLSession IlIlIIlIII1() {
        if (IIlIllIIll1()) {
            return ((llIlIIlll1.llllIIIIll1) this.f642IlIlllIIlI1).IllIIlIIII1().getSession();
        }
        throw new IllegalArgumentException("This websocket uses ws instead of wss. No SSLSession available.");
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public InetSocketAddress IlIllIlllIllI1() {
        return this.f653llllIllIl1.llllIIIIll1(this);
    }

    public final void IlIlllIIlI1(ByteBuffer byteBuffer) {
        Log.v(f635lIIlllIIIlllII1, "write(" + byteBuffer.remaining() + "): " + (byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array())));
        this.f652llllIIIIll1.add(byteBuffer);
        this.f653llllIllIl1.llllIllIl1(this);
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public InetSocketAddress IlIllll1() {
        return this.f653llllIllIl1.IllIIlIIII1(this);
    }

    public final boolean IllIIlIIII1(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2;
        lIIlIIIIlIlII1.IlIlllIIlI1 ilIlllIIlI1;
        lllIlIlllI1.llllIllIl1 llllIIIIll12;
        if (this.f654lllllIllIl1.capacity() == 0) {
            byteBuffer2 = byteBuffer;
        } else {
            if (this.f654lllllIllIl1.remaining() < byteBuffer.remaining()) {
                ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.remaining() + this.f654lllllIllIl1.capacity());
                this.f654lllllIllIl1.flip();
                allocate.put(this.f654lllllIllIl1);
                this.f654lllllIllIl1 = allocate;
            }
            this.f654lllllIllIl1.put(byteBuffer);
            this.f654lllllIllIl1.flip();
            byteBuffer2 = this.f654lllllIllIl1;
        }
        byteBuffer2.mark();
        try {
            try {
                ilIlllIIlI1 = this.f643IlIllll1;
            } catch (IllIlIllll1.IlIllIlllIllI1 e) {
                Log.v(f635lIIlllIIIlllII1, "Closing due to invalid handshake", e);
                llllIIIIll1(e);
            }
        } catch (IllIlIllll1.lIIIIlllllIlll1 e2) {
            if (this.f654lllllIllIl1.capacity() == 0) {
                byteBuffer2.reset();
                int llllIIIIll13 = e2.llllIIIIll1();
                if (llllIIIIll13 == 0) {
                    llllIIIIll13 = byteBuffer2.capacity() + 16;
                } else if (!f633IllllIllllll1 && e2.llllIIIIll1() < byteBuffer2.remaining()) {
                    throw new AssertionError();
                }
                ByteBuffer allocate2 = ByteBuffer.allocate(llllIIIIll13);
                this.f654lllllIllIl1 = allocate2;
                allocate2.put(byteBuffer);
            } else {
                ByteBuffer byteBuffer3 = this.f654lllllIllIl1;
                byteBuffer3.position(byteBuffer3.limit());
                ByteBuffer byteBuffer4 = this.f654lllllIllIl1;
                byteBuffer4.limit(byteBuffer4.capacity());
            }
        }
        if (ilIlllIIlI1 != lIIlIIIIlIlII1.IlIlllIIlI1.SERVER) {
            if (ilIlllIIlI1 == lIIlIIIIlIlII1.IlIlllIIlI1.CLIENT) {
                this.f637IIlIllIIll1.llllIIIIll1(ilIlllIIlI1);
                lllIlIlllI1.IlIllIlllIllI1 IllIIlIIII12 = this.f637IIlIllIIll1.IllIIlIIII1(byteBuffer2);
                if (!(IllIIlIIII12 instanceof lllIlIlllI1.IlIlIIlIII1)) {
                    Log.v(f635lIIlllIIIlllII1, "Closing due to protocol error: wrong http function");
                    llllIllIl1(1002, "wrong http function", false);
                    return false;
                }
                lllIlIlllI1.IlIlIIlIII1 ilIlIIlIII1 = (lllIlIlllI1.IlIlIIlIII1) IllIIlIIII12;
                if (this.f637IIlIllIIll1.llllIIIIll1(this.f638IlIIlllllI1, ilIlIIlIII1) == lIIlIIIIlIlII1.lIIIIlllllIlll1.MATCHED) {
                    try {
                        this.f653llllIllIl1.getClass();
                        llllIIIIll1((lllIlIlllI1.IlIllIlllIllI1) ilIlIIlIII1);
                        return true;
                    } catch (IllIlIllll1.llllIllIl1 e3) {
                        Log.v(f635lIIlllIIIlllII1, "Closing due to invalid data exception. Possible handshake rejection", e3);
                        llllIllIl1(e3.llllIIIIll1(), e3.getMessage(), false);
                        return false;
                    } catch (RuntimeException e4) {
                        Log.e(f635lIIlllIIIlllII1, "Closing since client was never connected", e4);
                        this.f653llllIllIl1.llllIIIIll1(this, e4);
                        llllIllIl1(-1, e4.getMessage(), false);
                        return false;
                    }
                }
                Log.v(f635lIIlllIIIlllII1, "Closing due to protocol error: draft " + this.f637IIlIllIIll1 + " refuses handshake");
                llllIIIIll1(1002, "draft " + this.f637IIlIllIIll1 + " refuses handshake");
            }
            return false;
        }
        lIlllIIIII1.llllIIIIll1 lllliiiill1 = this.f637IIlIllIIll1;
        if (lllliiiill1 != null) {
            lllIlIlllI1.IlIllIlllIllI1 IllIIlIIII13 = lllliiiill1.IllIIlIIII1(byteBuffer2);
            if (!(IllIIlIIII13 instanceof lllIlIlllI1.llllIIIIll1)) {
                Log.v(f635lIIlllIIIlllII1, "Closing due to protocol error: wrong http function");
                llllIllIl1(1002, "wrong http function", false);
                return false;
            }
            lllIlIlllI1.llllIIIIll1 lllliiiill12 = (lllIlIlllI1.llllIIIIll1) IllIIlIIII13;
            if (this.f637IIlIllIIll1.llllIIIIll1(lllliiiill12) == lIIlIIIIlIlII1.lIIIIlllllIlll1.MATCHED) {
                llllIIIIll1((lllIlIlllI1.IlIllIlllIllI1) lllliiiill12);
                return true;
            }
            Log.v(f635lIIlllIIIlllII1, "Closing due to protocol error: the handshake did finally not match");
            llllIIIIll1(1002, "the handshake did finally not match");
            return false;
        }
        Iterator<lIlllIIIII1.llllIIIIll1> it = this.f646lIllIIIlIl1.iterator();
        while (it.hasNext()) {
            lIlllIIIII1.llllIIIIll1 llllIIIIll14 = it.next().llllIIIIll1();
            try {
                llllIIIIll14.f550llllIIIIll1 = this.f643IlIllll1;
                byteBuffer2.reset();
                llllIIIIll12 = lIlllIIIII1.llllIIIIll1.llllIIIIll1(byteBuffer2, llllIIIIll14.f550llllIIIIll1);
            } catch (IllIlIllll1.IlIllIlllIllI1 unused) {
            }
            if (!(llllIIIIll12 instanceof lllIlIlllI1.llllIIIIll1)) {
                Log.v(f635lIIlllIIIlllII1, "Closing due to wrong handshake");
                lIIIIlllllIlll1(new IllIlIllll1.llllIllIl1(1002, "wrong http function"));
                return false;
            }
            lllIlIlllI1.llllIIIIll1 lllliiiill13 = (lllIlIlllI1.llllIIIIll1) llllIIIIll12;
            if (llllIIIIll14.llllIIIIll1(lllliiiill13) == lIIlIIIIlIlII1.lIIIIlllllIlll1.MATCHED) {
                this.f636IIIlIllIlI1 = lllliiiill13.llllIIIIll1();
                try {
                    llllIIIIll1(llllIIIIll14.llllIIIIll1((lllIlIlllI1.IlIllIlllIllI1) llllIIIIll14.llllIIIIll1(lllliiiill13, this.f653llllIllIl1.llllIIIIll1(this, llllIIIIll14, lllliiiill13)), true));
                    this.f637IIlIllIIll1 = llllIIIIll14;
                    llllIIIIll1((lllIlIlllI1.IlIllIlllIllI1) lllliiiill13);
                    return true;
                } catch (IllIlIllll1.llllIllIl1 e5) {
                    Log.v(f635lIIlllIIIlllII1, "Closing due to wrong handshake. Possible handshake rejection", e5);
                    lIIIIlllllIlll1(e5);
                    return false;
                } catch (RuntimeException e6) {
                    Log.e(f635lIIlllIIIlllII1, "Closing due to internal server error", e6);
                    this.f653llllIllIl1.llllIIIIll1(this, e6);
                    llllIIIIll1(e6);
                    return false;
                }
            }
        }
        if (this.f637IIlIllIIll1 == null) {
            Log.v(f635lIIlllIIIlllII1, "Closing due to protocol error: no draft matches");
            lIIIIlllllIlll1(new IllIlIllll1.llllIllIl1(1002, "no draft matches"));
        }
        return false;
    }

    public void IllIlIllll1() {
        this.f650llIIllIl1 = System.nanoTime();
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void close() {
        llllIIIIll1(1000);
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public boolean isOpen() {
        return this.f640IlIlIIlIII1 == lIIlIIIIlIlII1.IllIIlIIII1.OPEN;
    }

    public void lIIIIlllllIlll1(ByteBuffer byteBuffer) {
        boolean z = f633IllllIllllll1;
        if (!z && !byteBuffer.hasRemaining()) {
            throw new AssertionError();
        }
        Log.v(f635lIIlllIIIlllII1, "process(" + byteBuffer.remaining() + "): (" + (byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining())) + ")");
        if (this.f640IlIlIIlIII1 != lIIlIIIIlIlII1.IllIIlIIII1.NOT_YET_CONNECTED) {
            if (this.f640IlIlIIlIII1 == lIIlIIIIlIlII1.IllIIlIIII1.OPEN) {
                llllIllIl1(byteBuffer);
            }
        } else {
            if (!IllIIlIIII1(byteBuffer) || llllllIlIIIlll1() || llIIIIlIlllIII1()) {
                return;
            }
            if (!z && this.f654lllllIllIl1.hasRemaining() == byteBuffer.hasRemaining() && byteBuffer.hasRemaining()) {
                throw new AssertionError();
            }
            if (byteBuffer.hasRemaining()) {
                llllIllIl1(byteBuffer);
            } else if (this.f654lllllIllIl1.hasRemaining()) {
                llllIllIl1(this.f654lllllIllIl1);
            }
        }
    }

    public IlIlllIIlI1.llllIIIIll1 lIIlIIIIlIlII1() {
        return this.f641IlIllIlllIllI1;
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public IIlllllIlll1.llllIIIIll1 lIllIIIlIl1() {
        lIlllIIIII1.llllIIIIll1 lllliiiill1 = this.f637IIlIllIIll1;
        if (lllliiiill1 == null) {
            return null;
        }
        if (lllliiiill1 instanceof lIlllIIIII1.lIIIIlllllIlll1) {
            return ((lIlllIIIII1.lIIIIlllllIlll1) lllliiiill1).IlIIlllllI1();
        }
        throw new IllegalArgumentException("This draft does not support Sec-WebSocket-Protocol");
    }

    public void lIllIlIll1() {
        if (this.f639IlIlIIIlIlIlll1 == null) {
            throw new IllegalStateException("this method must be used in conjunction with flushAndClose");
        }
        lIIIIlllllIlll1(this.f647lIllIlIll1.intValue(), this.f649llIIIIlIlllIII1, this.f639IlIlIIIlIlIlll1.booleanValue());
    }

    public IIlIllIIll1 lIlllIIIII1() {
        return this.f653llllIllIl1;
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public boolean llIIIIlIlllIII1() {
        return this.f640IlIlIIlIII1 == lIIlIIIIlIlII1.IllIIlIIII1.CLOSED;
    }

    public long llIIllIl1() {
        return this.f650llIIllIl1;
    }

    public SelectionKey lllIlIIIlI1() {
        return this.f644IllIIlIIII1;
    }

    public final void llllIIIIll1(RuntimeException runtimeException) {
        IlIlllIIlI1(lIIIIlllllIlll1(500));
        llllIllIl1(-1, runtimeException.getMessage(), false);
    }

    public final void llllIllIl1(ByteBuffer byteBuffer) {
        try {
            for (IlIIIIllllIlI1.IlIllIlllIllI1 ilIllIlllIllI1 : this.f637IIlIllIIll1.llllIllIl1(byteBuffer)) {
                Log.v(f635lIIlllIIIlllII1, "matched frame: " + ilIllIlllIllI1);
                this.f637IIlIllIIll1.llllIIIIll1(this, ilIllIlllIllI1);
            }
        } catch (IllIlIllll1.llllllIlIIIlll1 e) {
            if (e.lIIIIlllllIlll1() == Integer.MAX_VALUE) {
                Log.e(f635lIIlllIIIlllII1, "Closing due to invalid size of frame", e);
                this.f653llllIllIl1.llllIIIIll1(this, e);
            }
            llllIIIIll1((IllIlIllll1.llllIllIl1) e);
        } catch (IllIlIllll1.llllIllIl1 e2) {
            Log.e(f635lIIlllIIIlllII1, "Closing due to invalid data in frame", e2);
            this.f653llllIllIl1.llllIIIIll1(this, e2);
            llllIIIIll1(e2);
        } catch (LinkageError e3) {
            e = e3;
            Log.e(f635lIIlllIIIlllII1, "Got fatal error during frame processing");
            throw e;
        } catch (ThreadDeath e4) {
            e = e4;
            Log.e(f635lIIlllIIIlllII1, "Got fatal error during frame processing");
            throw e;
        } catch (VirtualMachineError e5) {
            e = e5;
            Log.e(f635lIIlllIIIlllII1, "Got fatal error during frame processing");
            throw e;
        } catch (Error e6) {
            Log.e(f635lIIlllIIIlllII1, "Closing web socket due to an error during frame processing");
            this.f653llllIllIl1.llllIIIIll1(this, new Exception(e6));
            llllIIIIll1(1011, "Got error ".concat(e6.getClass().getName()));
        }
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public lIIlIIIIlIlII1.IllIIlIIII1 lllllIllIl1() {
        return this.f640IlIlIIlIII1;
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public boolean llllllIlIIIlll1() {
        return this.f640IlIlIIlIII1 == lIIlIIIIlIlII1.IllIIlIIII1.CLOSING;
    }

    public String toString() {
        return super.toString();
    }

    public synchronized void llllIIIIll1(int i, String str, boolean z) {
        lIIlIIIIlIlII1.IllIIlIIII1 illIIlIIII1 = this.f640IlIlIIlIII1;
        lIIlIIIIlIlII1.IllIIlIIII1 illIIlIIII12 = lIIlIIIIlIlII1.IllIIlIIII1.CLOSING;
        if (illIIlIIII1 == illIIlIIII12 || this.f640IlIlIIlIII1 == lIIlIIIIlIlII1.IllIIlIIII1.CLOSED) {
            return;
        }
        if (this.f640IlIlIIlIII1 == lIIlIIIIlIlII1.IllIIlIIII1.OPEN) {
            if (i == 1006) {
                if (!f633IllllIllllll1 && z) {
                    throw new AssertionError();
                }
                this.f640IlIlIIlIII1 = illIIlIIII12;
                llllIllIl1(i, str, false);
                return;
            }
            if (this.f637IIlIllIIll1.lIIIIlllllIlll1() != lIIlIIIIlIlII1.llllIIIIll1.NONE) {
                try {
                    if (!z) {
                        try {
                            this.f653llllIllIl1.llllIIIIll1(this, i, str);
                        } catch (RuntimeException e) {
                            this.f653llllIllIl1.llllIIIIll1(this, e);
                        }
                    }
                    if (isOpen()) {
                        IlIIIIllllIlI1.lIIIIlllllIlll1 liiiilllllilll1 = new IlIIIIllllIlI1.lIIIIlllllIlll1();
                        liiiilllllilll1.llllIIIIll1(str);
                        liiiilllllilll1.llllIIIIll1(i);
                        liiiilllllilll1.IlIlIIlIII1();
                        llllIIIIll1((IlIIIIllllIlI1.IlIllIlllIllI1) liiiilllllilll1);
                    }
                } catch (IllIlIllll1.llllIllIl1 e2) {
                    Log.e(f635lIIlllIIIlllII1, "generated frame is invalid", e2);
                    this.f653llllIllIl1.llllIIIIll1(this, e2);
                    llllIllIl1(1006, "generated frame is invalid", false);
                }
            }
            llllIllIl1(i, str, z);
        } else if (i == -3) {
            if (!f633IllllIllllll1 && !z) {
                throw new AssertionError();
            }
            llllIllIl1(-3, str, true);
        } else if (i == 1002) {
            llllIllIl1(i, str, z);
        } else {
            llllIllIl1(-1, str, false);
        }
        this.f640IlIlIIlIII1 = lIIlIIIIlIlII1.IllIIlIIII1.CLOSING;
        this.f654lllllIllIl1 = null;
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public <T> T IlIlllIIlI1() {
        return (T) this.f648lIlllIIIII1;
    }

    public lIllIIIlIl1(IIlIllIIll1 iIlIllIIll1, lIlllIIIII1.llllIIIIll1 lllliiiill1) {
        this.f655llllllIlIIIlll1 = false;
        this.f640IlIlIIlIII1 = lIIlIIIIlIlII1.IllIIlIIII1.NOT_YET_CONNECTED;
        this.f637IIlIllIIll1 = null;
        this.f654lllllIllIl1 = ByteBuffer.allocate(0);
        this.f638IlIIlllllI1 = null;
        this.f649llIIIIlIlllIII1 = null;
        this.f647lIllIlIll1 = null;
        this.f639IlIlIIIlIlIlll1 = null;
        this.f636IIIlIllIlI1 = null;
        this.f650llIIllIl1 = System.nanoTime();
        this.f651lllIlIIIlI1 = new Object();
        if (iIlIllIIll1 != null && (lllliiiill1 != null || this.f643IlIllll1 != lIIlIIIIlIlII1.IlIlllIIlI1.SERVER)) {
            this.f652llllIIIIll1 = new LinkedBlockingQueue();
            this.f645lIIIIlllllIlll1 = new LinkedBlockingQueue();
            this.f653llllIllIl1 = iIlIllIIll1;
            this.f643IlIllll1 = lIIlIIIIlIlII1.IlIlllIIlI1.CLIENT;
            if (lllliiiill1 != null) {
                this.f637IIlIllIIll1 = lllliiiill1.llllIIIIll1();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("parameters must not be null");
    }

    public final void lIIIIlllllIlll1(IllIlIllll1.llllIllIl1 llllillil1) {
        IlIlllIIlI1(lIIIIlllllIlll1(404));
        llllIllIl1(llllillil1.llllIIIIll1(), llllillil1.getMessage(), false);
    }

    public final ByteBuffer lIIIIlllllIlll1(int i) {
        String str;
        if (i != 404) {
            str = "500 Internal Server Error";
        } else {
            str = "404 WebSocket Upgrade Failure";
        }
        return ByteBuffer.wrap(IlIIIlIlIlIII1.llllIllIl1.llllIIIIll1("HTTP/1.1 " + str + "\r\nContent-Type: text/html\r\nServer: TooTallNate Java-WebSocket\r\nContent-Length: " + (str.length() + 48) + "\r\n\r\n<html><head></head><body><h1>" + str + "</h1></body></html>"));
    }

    public synchronized void lIIIIlllllIlll1(int i, String str, boolean z) {
        if (this.f640IlIlIIlIII1 == lIIlIIIIlIlII1.IllIIlIIII1.CLOSED) {
            return;
        }
        if (this.f640IlIlIIlIII1 == lIIlIIIIlIlII1.IllIIlIIII1.OPEN && i == 1006) {
            this.f640IlIlIIlIII1 = lIIlIIIIlIlII1.IllIIlIIII1.CLOSING;
        }
        SelectionKey selectionKey = this.f644IllIIlIIII1;
        if (selectionKey != null) {
            selectionKey.cancel();
        }
        ByteChannel byteChannel = this.f642IlIlllIIlI1;
        if (byteChannel != null) {
            try {
                byteChannel.close();
            } catch (IOException e) {
                if (e.getMessage() != null && e.getMessage().equals("Broken pipe")) {
                    Log.v(f635lIIlllIIIlllII1, "Caught IOException: Broken pipe during closeConnection()", e);
                } else {
                    Log.e(f635lIIlllIIIlllII1, "Exception during channel.close()", e);
                    this.f653llllIllIl1.llllIIIIll1(this, e);
                }
            }
        }
        try {
            this.f653llllIllIl1.lIIIIlllllIlll1(this, i, str, z);
        } catch (RuntimeException e2) {
            this.f653llllIllIl1.llllIIIIll1(this, e2);
        }
        lIlllIIIII1.llllIIIIll1 lllliiiill1 = this.f637IIlIllIIll1;
        if (lllliiiill1 != null) {
            lllliiiill1.IllIIlIIII1();
        }
        this.f638IlIIlllllI1 = null;
        this.f640IlIlIIlIII1 = lIIlIIIIlIlII1.IllIIlIIII1.CLOSED;
    }

    public synchronized void llllIllIl1(int i, String str, boolean z) {
        if (this.f655llllllIlIIIlll1) {
            return;
        }
        this.f647lIllIlIll1 = Integer.valueOf(i);
        this.f649llIIIIlIlllIII1 = str;
        this.f639IlIlIIIlIlIlll1 = Boolean.valueOf(z);
        this.f655llllllIlIIIlll1 = true;
        this.f653llllIllIl1.llllIllIl1(this);
        try {
            this.f653llllIllIl1.llllIIIIll1(this, i, str, z);
        } catch (RuntimeException e) {
            Log.e(f635lIIlllIIIlllII1, "Exception in onWebsocketClosing", e);
            this.f653llllIllIl1.llllIIIIll1(this, e);
        }
        lIlllIIIII1.llllIIIIll1 lllliiiill1 = this.f637IIlIllIIll1;
        if (lllliiiill1 != null) {
            lllliiiill1.IllIIlIIII1();
        }
        this.f638IlIIlllllI1 = null;
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void llllIIIIll1(int i, String str) {
        llllIIIIll1(i, str, false);
    }

    public void llllIIIIll1(int i, boolean z) {
        lIIIIlllllIlll1(i, "", z);
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void llllIIIIll1(int i) {
        llllIIIIll1(i, "", false);
    }

    public void llllIIIIll1(IllIlIllll1.llllIllIl1 llllillil1) {
        llllIIIIll1(llllillil1.llllIIIIll1(), llllillil1.getMessage(), false);
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void llllIIIIll1(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
        }
        lIIIIlllllIlll1(this.f637IIlIllIIll1.llllIIIIll1(str, this.f643IlIllll1 == lIIlIIIIlIlII1.IlIlllIIlI1.CLIENT));
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void llllIIIIll1(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
        }
        lIIIIlllllIlll1(this.f637IIlIllIIll1.llllIIIIll1(byteBuffer, this.f643IlIllll1 == lIIlIIIIlIlII1.IlIlllIIlI1.CLIENT));
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void llllIIIIll1(byte[] bArr) {
        llllIIIIll1(ByteBuffer.wrap(bArr));
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void llllIIIIll1(lIIlIIIIlIlII1.llllIllIl1 llllillil1, ByteBuffer byteBuffer, boolean z) {
        lIIIIlllllIlll1(this.f637IIlIllIIll1.llllIIIIll1(llllillil1, byteBuffer, z));
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void llllIIIIll1(Collection<IlIIIIllllIlI1.IlIllIlllIllI1> collection) {
        lIIIIlllllIlll1(collection);
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void llllIIIIll1(IlIIIIllllIlI1.IlIllIlllIllI1 ilIllIlllIllI1) {
        lIIIIlllllIlll1(Collections.singletonList(ilIllIlllIllI1));
    }

    public void llllIIIIll1(lllIlIlllI1.lIIIIlllllIlll1 liiiilllllilll1) throws IllIlIllll1.IlIllIlllIllI1 {
        this.f638IlIIlllllI1 = this.f637IIlIllIIll1.llllIIIIll1(liiiilllllilll1);
        String llllIIIIll12 = liiiilllllilll1.llllIIIIll1();
        this.f636IIIlIllIlI1 = llllIIIIll12;
        if (!f633IllllIllllll1 && llllIIIIll12 == null) {
            throw new AssertionError();
        }
        try {
            this.f653llllIllIl1.getClass();
            llllIIIIll1(this.f637IIlIllIIll1.lIIIIlllllIlll1(this.f638IlIIlllllI1));
        } catch (IllIlIllll1.llllIllIl1 unused) {
            throw new IllIlIllll1.IlIllIlllIllI1("Handshake data rejected by client.");
        } catch (RuntimeException e) {
            Log.e(f635lIIlllIIIlllII1, "Exception in startHandshake", e);
            this.f653llllIllIl1.llllIIIIll1(this, e);
            throw new IllIlIllll1.IlIllIlllIllI1("rejected because of " + e);
        }
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void llllIllIl1() throws NullPointerException {
        IlIIIIllllIlI1.IlIlIIlIII1 lIIIIlllllIlll12 = this.f653llllIllIl1.lIIIIlllllIlll1(this);
        if (lIIIIlllllIlll12 != null) {
            llllIIIIll1((IlIIIIllllIlI1.IlIllIlllIllI1) lIIIIlllllIlll12);
            return;
        }
        throw new NullPointerException("onPreparePing(WebSocket) returned null. PingFrame to sent can't be null.");
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public void lIIIIlllllIlll1(int i, String str) {
        lIIIIlllllIlll1(i, str, false);
    }

    public final void lIIIIlllllIlll1(Collection<IlIIIIllllIlI1.IlIllIlllIllI1> collection) {
        if (!isOpen()) {
            throw new IllIlIllll1.lIllIIIlIl1();
        }
        if (collection != null) {
            ArrayList arrayList = new ArrayList();
            for (IlIIIIllllIlI1.IlIllIlllIllI1 ilIllIlllIllI1 : collection) {
                Log.v(f635lIIlllIIIlllII1, "send frame: " + ilIllIlllIllI1);
                arrayList.add(this.f637IIlIllIIll1.llllIIIIll1(ilIllIlllIllI1));
            }
            llllIIIIll1((List<ByteBuffer>) arrayList);
            return;
        }
        throw new IllegalArgumentException();
    }

    public final void llllIIIIll1(List<ByteBuffer> list) {
        synchronized (this.f651lllIlIIIlI1) {
            Iterator<ByteBuffer> it = list.iterator();
            while (it.hasNext()) {
                IlIlllIIlI1(it.next());
            }
        }
    }

    public final void llllIIIIll1(lllIlIlllI1.IlIllIlllIllI1 ilIllIlllIllI1) {
        Log.v(f635lIIlllIIIlllII1, "open using draft: " + this.f637IIlIllIIll1);
        this.f640IlIlIIlIII1 = lIIlIIIIlIlII1.IllIIlIIII1.OPEN;
        IllIlIllll1();
        try {
            this.f653llllIllIl1.llllIIIIll1(this, ilIllIlllIllI1);
        } catch (RuntimeException e) {
            this.f653llllIllIl1.llllIIIIll1(this, e);
        }
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public lIlllIIIII1.llllIIIIll1 lIIIIlllllIlll1() {
        return this.f637IIlIllIIll1;
    }

    public void llllIIIIll1(SelectionKey selectionKey) {
        this.f644IllIIlIIII1 = selectionKey;
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public String llllIIIIll1() {
        return this.f636IIIlIllIlI1;
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public <T> void llllIIIIll1(T t) {
        this.f648lIlllIIIII1 = t;
    }

    public void llllIIIIll1(ByteChannel byteChannel) {
        this.f642IlIlllIIlI1 = byteChannel;
    }

    public void llllIIIIll1(IlIlllIIlI1.llllIIIIll1 lllliiiill1) {
        this.f641IlIllIlllIllI1 = lllliiiill1;
    }

    @Override // llIIllIl1.IlIllIlllIllI1
    public boolean IllIIlIIII1() {
        return this.f655llllllIlIIIlll1;
    }
}

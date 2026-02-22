package IIlIIllll1;

import IllIlIllll1.IIlIllIIll1;
import android.util.Log;
import java.io.IOException;
import java.lang.Thread;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import llIIllIl1.IlIlIIlIII1;
import llIIllIl1.IlIllIlllIllI1;
import llIIllIl1.IlIllll1;
import llIIllIl1.lIllIIIlIl1;
import llIIllIl1.lllllIllIl1;
import llIIllIl1.llllllIlIIIlll1;

/* loaded from: classes.dex */
public abstract class IlIlllIIlI1 extends llIIllIl1.llllIIIIll1 implements Runnable {

    /* renamed from: IIlIIllll1, reason: collision with root package name */
    public static final /* synthetic */ boolean f38IIlIIllll1 = true;

    /* renamed from: IIlllllIlll1, reason: collision with root package name */
    public static final String f39IIlllllIlll1 = "WebSocketServer";

    /* renamed from: llIlIIlll1, reason: collision with root package name */
    public static final int f40llIlIIlll1 = Runtime.getRuntime().availableProcessors();

    /* renamed from: IIIlIllIlI1, reason: collision with root package name */
    public List<lIlllIIIII1.llllIIIIll1> f41IIIlIllIlI1;

    /* renamed from: IlIIIIllllIlI1, reason: collision with root package name */
    public IlIllll1 f42IlIIIIllllIlI1;

    /* renamed from: IlIIlllllI1, reason: collision with root package name */
    public final Collection<IlIllIlllIllI1> f43IlIIlllllI1;

    /* renamed from: IlIlIIIlIlIlll1, reason: collision with root package name */
    public Selector f44IlIlIIIlIlIlll1;

    /* renamed from: IllIlIllll1, reason: collision with root package name */
    public BlockingQueue<ByteBuffer> f45IllIlIllll1;

    /* renamed from: IllllIllllll1, reason: collision with root package name */
    public final AtomicInteger f46IllllIllllll1;

    /* renamed from: lIIlIIIIlIlII1, reason: collision with root package name */
    public List<lIllIIIlIl1> f47lIIlIIIIlIlII1;

    /* renamed from: lIIlllIIIlllII1, reason: collision with root package name */
    public int f48lIIlllIIIlllII1;

    /* renamed from: lIllIlIll1, reason: collision with root package name */
    public ServerSocketChannel f49lIllIlIll1;

    /* renamed from: lIlllIIIII1, reason: collision with root package name */
    public List<llllIIIIll1> f50lIlllIIIII1;

    /* renamed from: llIIIIlIlllIII1, reason: collision with root package name */
    public final InetSocketAddress f51llIIIIlIlllIII1;

    /* renamed from: llIIllIl1, reason: collision with root package name */
    public Thread f52llIIllIl1;

    /* renamed from: lllIlIIIlI1, reason: collision with root package name */
    public final AtomicBoolean f53lllIlIIIlI1;

    /* renamed from: lllIlIlllI1, reason: collision with root package name */
    public int f54lllIlIlllI1;

    public class llllIIIIll1 extends Thread {

        /* renamed from: llllIllIl1, reason: collision with root package name */
        public static final /* synthetic */ boolean f55llllIllIl1 = true;

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public BlockingQueue<lIllIIIlIl1> f57llllIIIIll1 = new LinkedBlockingQueue();

        /* renamed from: IIlIIllll1.IlIlllIIlI1$llllIIIIll1$llllIIIIll1, reason: collision with other inner class name */
        public class C0000llllIIIIll1 implements Thread.UncaughtExceptionHandler {

            /* renamed from: llllIIIIll1, reason: collision with root package name */
            public final /* synthetic */ IlIlllIIlI1 f59llllIIIIll1;

            public C0000llllIIIIll1(IlIlllIIlI1 ilIlllIIlI1) {
                this.f59llllIIIIll1 = ilIlllIIlI1;
            }

            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                Log.e(IlIlllIIlI1.f39IIlllllIlll1, "Uncaught exception in thread " + thread.getName() + ": " + th.getMessage(), th);
            }
        }

        public llllIIIIll1() {
            setName("WebSocketWorker-" + getId());
            setUncaughtExceptionHandler(new C0000llllIIIIll1(IlIlllIIlI1.this));
        }

        public void llllIIIIll1(lIllIIIlIl1 lilliiilil1) throws InterruptedException {
            this.f57llllIIIIll1.put(lilliiilil1);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            lIllIIIlIl1 lilliiilil1;
            Throwable e;
            while (true) {
                lIllIIIlIl1 lilliiilil12 = null;
                try {
                    try {
                        lilliiilil1 = this.f57llllIIIIll1.take();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                } catch (LinkageError e2) {
                    e = e2;
                    Throwable th = e;
                    lilliiilil1 = null;
                    e = th;
                    Log.e(IlIlllIIlI1.f39IIlllllIlll1, "Got fatal error in worker thread " + getName());
                    IlIlllIIlI1.this.lIIIIlllllIlll1(lilliiilil1, new Exception(e));
                    return;
                } catch (ThreadDeath e3) {
                    e = e3;
                    Throwable th2 = e;
                    lilliiilil1 = null;
                    e = th2;
                    Log.e(IlIlllIIlI1.f39IIlllllIlll1, "Got fatal error in worker thread " + getName());
                    IlIlllIIlI1.this.lIIIIlllllIlll1(lilliiilil1, new Exception(e));
                    return;
                } catch (VirtualMachineError e4) {
                    e = e4;
                    Throwable th22 = e;
                    lilliiilil1 = null;
                    e = th22;
                    Log.e(IlIlllIIlI1.f39IIlllllIlll1, "Got fatal error in worker thread " + getName());
                    IlIlllIIlI1.this.lIIIIlllllIlll1(lilliiilil1, new Exception(e));
                    return;
                } catch (Throwable th3) {
                    th = th3;
                }
                try {
                    ByteBuffer poll = lilliiilil1.f645lIIIIlllllIlll1.poll();
                    if (!f55llllIllIl1 && poll == null) {
                        break;
                    }
                    llllIIIIll1(lilliiilil1, poll);
                } catch (LinkageError e5) {
                    e = e5;
                    Log.e(IlIlllIIlI1.f39IIlllllIlll1, "Got fatal error in worker thread " + getName());
                    IlIlllIIlI1.this.lIIIIlllllIlll1(lilliiilil1, new Exception(e));
                    return;
                } catch (ThreadDeath e6) {
                    e = e6;
                    Log.e(IlIlllIIlI1.f39IIlllllIlll1, "Got fatal error in worker thread " + getName());
                    IlIlllIIlI1.this.lIIIIlllllIlll1(lilliiilil1, new Exception(e));
                    return;
                } catch (VirtualMachineError e7) {
                    e = e7;
                    Log.e(IlIlllIIlI1.f39IIlllllIlll1, "Got fatal error in worker thread " + getName());
                    IlIlllIIlI1.this.lIIIIlllllIlll1(lilliiilil1, new Exception(e));
                    return;
                } catch (Throwable th4) {
                    th = th4;
                    lilliiilil12 = lilliiilil1;
                    Log.e(IlIlllIIlI1.f39IIlllllIlll1, "Uncaught exception in thread " + getName() + ": " + th.getMessage(), th);
                    if (lilliiilil12 != null) {
                        IlIlllIIlI1.this.llllIllIl1(lilliiilil12, new Exception(th));
                        lilliiilil12.close();
                        return;
                    }
                    return;
                }
            }
            throw new AssertionError();
        }

        public final void llllIIIIll1(lIllIIIlIl1 lilliiilil1, ByteBuffer byteBuffer) throws InterruptedException {
            try {
                try {
                    lilliiilil1.lIIIIlllllIlll1(byteBuffer);
                } catch (Exception e) {
                    Log.e(IlIlllIIlI1.f39IIlllllIlll1, "Error while reading from remote connection", e);
                }
            } finally {
                IlIlllIIlI1.this.llllIllIl1(byteBuffer);
            }
        }
    }

    public IlIlllIIlI1() {
        this(new InetSocketAddress(80), f40llIlIIlll1, null, new HashSet());
    }

    public final ByteBuffer IIIlIllIl1() throws InterruptedException {
        return this.f45IllIlIllll1.take();
    }

    @Override // llIIllIl1.llllIIIIll1
    public Collection<IlIllIlllIllI1> IIIlIllIlI1() {
        Collection<IlIllIlllIllI1> unmodifiableCollection;
        synchronized (this.f43IlIIlllllI1) {
            unmodifiableCollection = Collections.unmodifiableCollection(new ArrayList(this.f43IlIIlllllI1));
        }
        return unmodifiableCollection;
    }

    public final boolean IIlIIllll1() {
        this.f52llIIllIl1.setName("WebSocketSelector-" + this.f52llIIllIl1.getId());
        try {
            if (this.f49lIllIlIll1 == null) {
                this.f49lIllIlIll1 = ServerSocketChannel.open();
            }
            this.f49lIllIlIll1.configureBlocking(false);
            ServerSocket socket = this.f49lIllIlIll1.socket();
            int llIIllIl12 = llIIllIl1();
            if (llIIllIl12 > 0) {
                socket.setReceiveBufferSize(llIIllIl12);
            }
            socket.setReuseAddress(lIlllIIIII1());
            if (!socket.isBound()) {
                socket.bind(this.f51llIIIIlIlllIII1, llIIIlIIIlIII1());
            }
            Selector open = Selector.open();
            this.f44IlIlIIIlIlIlll1 = open;
            ServerSocketChannel serverSocketChannel = this.f49lIllIlIll1;
            serverSocketChannel.register(open, serverSocketChannel.validOps());
            lIIlllIIIlllII1();
            Iterator<llllIIIIll1> it = this.f50lIlllIIIII1.iterator();
            while (it.hasNext()) {
                it.next().start();
            }
            llIllllIlI1();
            return true;
        } catch (IOException e) {
            lIIIIlllllIlll1((IlIllIlllIllI1) null, e);
            return false;
        }
    }

    public int IIlIlllllllI1() {
        ServerSocketChannel serverSocketChannel;
        int port = IlIIIlIlIlIII1().getPort();
        return (port != 0 || (serverSocketChannel = this.f49lIllIlIll1) == null) ? port : serverSocketChannel.socket().getLocalPort();
    }

    public final void IIlllllIlll1() {
        IllllIllllll1();
        List<llllIIIIll1> list = this.f50lIlllIIIII1;
        if (list != null) {
            Iterator<llllIIIIll1> it = list.iterator();
            while (it.hasNext()) {
                it.next().interrupt();
            }
        }
        Selector selector = this.f44IlIlIIIlIlIlll1;
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                Log.e(f39IIlllllIlll1, "IOException during selector.close", e);
                llllIllIl1((IlIllIlllIllI1) null, e);
            }
        }
        ServerSocketChannel serverSocketChannel = this.f49lIllIlIll1;
        if (serverSocketChannel != null) {
            try {
                serverSocketChannel.close();
            } catch (IOException e2) {
                Log.e(f39IIlllllIlll1, "IOException during server.close", e2);
                llllIllIl1((IlIllIlllIllI1) null, e2);
            }
        }
    }

    public List<lIlllIIIII1.llllIIIIll1> IlIIIIIlll1() {
        return Collections.unmodifiableList(this.f41IIIlIllIlI1);
    }

    public ByteBuffer IlIIIIllllIlI1() {
        int llIIllIl12 = llIIllIl1();
        if (llIIllIl12 <= 0) {
            llIIllIl12 = llIIllIl1.llllIIIIll1.f657lllllIllIl1;
        }
        return ByteBuffer.allocate(llIIllIl12);
    }

    public InetSocketAddress IlIIIlIlIlIII1() {
        return this.f51llIIIIlIlllIII1;
    }

    public void IlIlIIlIII1(IlIllIlllIllI1 ilIllIlllIllI1) throws InterruptedException {
    }

    public void IlIllIlllIllI1(IlIllIlllIllI1 ilIllIlllIllI1) throws InterruptedException {
        if (this.f46IllllIllllll1.get() >= (this.f50lIlllIIIII1.size() * 2) + 1) {
            return;
        }
        this.f46IllllIllllll1.incrementAndGet();
        this.f45IllIlIllll1.put(IlIIIIllllIlI1());
    }

    public void IlIlllIIlI1(int i) throws InterruptedException {
        llllIllIl1(i, "");
    }

    public void IllIIlIIII1(int i) {
        this.f54lllIlIlllI1 = i;
    }

    public void IllIIlIIII1(IlIllIlllIllI1 ilIllIlllIllI1, int i, String str, boolean z) {
    }

    public void lIIIIlllllIlll1(IlIllIlllIllI1 ilIllIlllIllI1, int i, String str) {
    }

    public abstract void lIIIIlllllIlll1(IlIllIlllIllI1 ilIllIlllIllI1, String str);

    public void lIIIIlllllIlll1(IlIllIlllIllI1 ilIllIlllIllI1, ByteBuffer byteBuffer) {
    }

    public abstract void lIIIIlllllIlll1(IlIllIlllIllI1 ilIllIlllIllI1, lllIlIlllI1.llllIIIIll1 lllliiiill1);

    public boolean lIIIIlllllIlll1(SelectionKey selectionKey) {
        return true;
    }

    public final boolean lIIIIlllllIlll1(SelectionKey selectionKey, Iterator<SelectionKey> it) throws InterruptedException, IIlIllIIll1 {
        lIllIIIlIl1 lilliiilil1 = (lIllIIIlIl1) selectionKey.attachment();
        ByteBuffer take = this.f45IllIlIllll1.take();
        if (lilliiilil1.IIIlIllIlI1() == null) {
            selectionKey.cancel();
            llllIIIIll1(selectionKey, lilliiilil1, new IOException());
            return false;
        }
        try {
            if (!llIIllIl1.IlIlllIIlI1.llllIIIIll1(take, lilliiilil1, lilliiilil1.IIIlIllIlI1())) {
                llllIllIl1(take);
                return true;
            }
            if (!take.hasRemaining()) {
                llllIllIl1(take);
                return true;
            }
            lilliiilil1.f645lIIIIlllllIlll1.put(take);
            llllIIIIll1(lilliiilil1);
            it.remove();
            if (!(lilliiilil1.IIIlIllIlI1() instanceof lllllIllIl1) || !((lllllIllIl1) lilliiilil1.IIIlIllIlI1()).IlIlllIIlI1()) {
                return true;
            }
            this.f47lIIlIIIIlIlII1.add(lilliiilil1);
            return true;
        } catch (IOException e) {
            llllIllIl1(take);
            throw new IIlIllIIll1(lilliiilil1, e);
        }
    }

    public void lIIIllllllIIII1() {
        if (this.f52llIIllIl1 != null) {
            throw new IllegalStateException(getClass().getName().concat(" can only be started once."));
        }
        Thread thread = new Thread(this);
        thread.setDaemon(lllIlIIIlI1());
        thread.start();
    }

    public final IlIlIIlIII1 lIlIIIllll1() {
        return this.f42IlIIIIllllIlI1;
    }

    public void lIlIlIlI1() throws InterruptedException {
        IlIlllIIlI1(0);
    }

    public boolean lIllIIIlIl1(IlIllIlllIllI1 ilIllIlllIllI1) {
        boolean z;
        synchronized (this.f43IlIIlllllI1) {
            if (this.f43IlIIlllllI1.contains(ilIllIlllIllI1)) {
                z = this.f43IlIIlllllI1.remove(ilIllIlllIllI1);
            } else {
                Log.v(f39IIlllllIlll1, "Removing connection which is not in the connections collection! Possible no handshake received! " + ilIllIlllIllI1.toString());
                z = false;
            }
        }
        if (this.f53lllIlIIIlI1.get() && this.f43IlIIlllllI1.isEmpty()) {
            this.f52llIIllIl1.interrupt();
        }
        return z;
    }

    public int llIIIlIIIlIII1() {
        return this.f54lllIlIlllI1;
    }

    public final boolean llIlIIlll1() {
        synchronized (this) {
            if (this.f52llIIllIl1 != null) {
                throw new IllegalStateException(getClass().getName().concat(" can only be started once."));
            }
            this.f52llIIllIl1 = Thread.currentThread();
            return !this.f53lllIlIIIlI1.get();
        }
    }

    public abstract void llIllllIlI1();

    public final void lllIlIlllI1() throws InterruptedException, IOException {
        while (!this.f47lIIlIIIIlIlII1.isEmpty()) {
            lIllIIIlIl1 remove = this.f47lIIlIIIIlIlII1.remove(0);
            lllllIllIl1 lllllillil1 = (lllllIllIl1) remove.IIIlIllIlI1();
            ByteBuffer take = this.f45IllIlIllll1.take();
            try {
                if (llIIllIl1.IlIlllIIlI1.llllIIIIll1(take, remove, lllllillil1)) {
                    this.f47lIIlIIIIlIlII1.add(remove);
                }
                if (take.hasRemaining()) {
                    remove.f645lIIIIlllllIlll1.put(take);
                    llllIIIIll1(remove);
                } else {
                    llllIllIl1(take);
                }
            } catch (IOException e) {
                llllIllIl1(take);
                throw e;
            }
        }
    }

    @Override // llIIllIl1.IIlIllIIll1
    public void llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1, int i, String str) {
    }

    @Override // llIIllIl1.IIlIllIIll1
    public void llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1, int i, String str, boolean z) {
    }

    @Override // llIIllIl1.IIlIllIIll1
    public final void llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1, ByteBuffer byteBuffer) {
    }

    public void llllIllIl1(int i, String str) throws InterruptedException {
        ArrayList arrayList;
        Selector selector;
        if (this.f53lllIlIIIlI1.compareAndSet(false, true)) {
            synchronized (this.f43IlIIlllllI1) {
                arrayList = new ArrayList(this.f43IlIIlllllI1);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((IlIllIlllIllI1) it.next()).llllIIIIll1(1001, str);
            }
            this.f42IlIIIIllllIlI1.close();
            synchronized (this) {
                if (this.f52llIIllIl1 != null && (selector = this.f44IlIlIIIlIlIlll1) != null) {
                    selector.wakeup();
                    this.f52llIIllIl1.join(i);
                }
            }
        }
    }

    public abstract void llllIllIl1(IlIllIlllIllI1 ilIllIlllIllI1, int i, String str, boolean z);

    public abstract void llllIllIl1(IlIllIlllIllI1 ilIllIlllIllI1, Exception exc);

    public final Socket llllllIlIIIlll1(IlIllIlllIllI1 ilIllIlllIllI1) {
        return ((SocketChannel) ((lIllIIIlIl1) ilIllIlllIllI1).lllIlIIIlI1().channel()).socket();
    }

    @Override // java.lang.Runnable
    public void run() {
        SelectionKey selectionKey;
        if (llIlIIlll1() && IIlIIllll1()) {
            int i = 0;
            int i2 = 5;
            while (!this.f52llIIllIl1.isInterrupted() && i2 != 0) {
                try {
                    try {
                        try {
                            if (this.f53lllIlIIIlI1.get()) {
                                i = 5;
                            }
                            if (this.f44IlIlIIIlIlIlll1.select(i) == 0 && this.f53lllIlIIIlI1.get()) {
                                i2--;
                            }
                            Iterator<SelectionKey> it = this.f44IlIlIIIlIlIlll1.selectedKeys().iterator();
                            selectionKey = null;
                            while (it.hasNext()) {
                                try {
                                    SelectionKey next = it.next();
                                    try {
                                        if (next.isValid()) {
                                            if (next.isAcceptable()) {
                                                llllIIIIll1(next, it);
                                            } else if ((!next.isReadable() || lIIIIlllllIlll1(next, it)) && next.isWritable()) {
                                                llllIIIIll1(next);
                                            }
                                        }
                                        selectionKey = next;
                                    } catch (IIlIllIIll1 e) {
                                        e = e;
                                        selectionKey = next;
                                        llllIIIIll1(selectionKey, e.llllIIIIll1(), e.lIIIIlllllIlll1());
                                    } catch (IOException e2) {
                                        e = e2;
                                        selectionKey = next;
                                        llllIIIIll1(selectionKey, (IlIllIlllIllI1) null, e);
                                    }
                                } catch (IIlIllIIll1 e3) {
                                    e = e3;
                                } catch (IOException e4) {
                                    e = e4;
                                }
                            }
                            lllIlIlllI1();
                        } catch (IIlIllIIll1 e5) {
                            e = e5;
                            selectionKey = null;
                        } catch (IOException e6) {
                            e = e6;
                            selectionKey = null;
                        }
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    } catch (CancelledKeyException unused2) {
                    } catch (ClosedByInterruptException unused3) {
                        return;
                    }
                } catch (RuntimeException e7) {
                    lIIIIlllllIlll1((IlIllIlllIllI1) null, e7);
                    return;
                } finally {
                    IIlllllIlll1();
                }
            }
        }
    }

    public boolean IlIlllIIlI1(IlIllIlllIllI1 ilIllIlllIllI1) {
        boolean add;
        if (this.f53lllIlIIIlI1.get()) {
            ilIllIlllIllI1.llllIIIIll1(1001);
            return true;
        }
        synchronized (this.f43IlIIlllllI1) {
            add = this.f43IlIIlllllI1.add(ilIllIlllIllI1);
        }
        return add;
    }

    public IlIlllIIlI1(InetSocketAddress inetSocketAddress) {
        this(inetSocketAddress, f40llIlIIlll1, null, new HashSet());
    }

    public static InetSocketAddress llllIIIIll1(ServerSocketChannel serverSocketChannel) {
        if (!f38IIlIIllll1 && !serverSocketChannel.isOpen()) {
            throw new AssertionError();
        }
        try {
            SocketAddress localAddress = serverSocketChannel.getLocalAddress();
            if (localAddress != null) {
                return (InetSocketAddress) localAddress;
            }
            throw new IllegalArgumentException("Could not get address of channel passed to WebSocketServer, make sure it is bound");
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not get address of channel passed to WebSocketServer, make sure it is bound", e);
        }
    }

    @Override // llIIllIl1.IIlIllIIll1
    public InetSocketAddress IllIIlIIII1(IlIllIlllIllI1 ilIllIlllIllI1) {
        return (InetSocketAddress) llllllIlIIIlll1(ilIllIlllIllI1).getLocalSocketAddress();
    }

    public IlIlllIIlI1(InetSocketAddress inetSocketAddress, List<lIlllIIIII1.llllIIIIll1> list) {
        this(inetSocketAddress, f40llIlIIlll1, list, new HashSet());
    }

    public IlIlllIIlI1(InetSocketAddress inetSocketAddress, int i) {
        this(inetSocketAddress, i, null, new HashSet());
    }

    public IlIlllIIlI1(InetSocketAddress inetSocketAddress, int i, List<lIlllIIIII1.llllIIIIll1> list) {
        this(inetSocketAddress, i, list, new HashSet());
    }

    public IlIlllIIlI1(ServerSocketChannel serverSocketChannel) {
        this(llllIIIIll1(serverSocketChannel));
        this.f49lIllIlIll1 = serverSocketChannel;
    }

    public IlIlllIIlI1(InetSocketAddress inetSocketAddress, int i, List<lIlllIIIII1.llllIIIIll1> list, Collection<IlIllIlllIllI1> collection) {
        this.f53lllIlIIIlI1 = new AtomicBoolean(false);
        this.f48lIIlllIIIlllII1 = 0;
        this.f46IllllIllllll1 = new AtomicInteger(0);
        this.f42IlIIIIllllIlI1 = new llllIllIl1();
        this.f54lllIlIlllI1 = -1;
        if (inetSocketAddress != null && i >= 1 && collection != null) {
            if (list == null) {
                this.f41IIIlIllIlI1 = Collections.emptyList();
            } else {
                this.f41IIIlIllIlI1 = list;
            }
            this.f51llIIIIlIlllIII1 = inetSocketAddress;
            this.f43IlIIlllllI1 = collection;
            llllIllIl1(false);
            lIIIIlllllIlll1(false);
            this.f47lIIlIIIIlIlII1 = new LinkedList();
            this.f50lIlllIIIII1 = new ArrayList(i);
            this.f45IllIlIllll1 = new LinkedBlockingQueue();
            for (int i2 = 0; i2 < i; i2++) {
                this.f50lIlllIIIII1.add(new llllIIIIll1());
            }
            return;
        }
        throw new IllegalArgumentException("address and connectionscontainer must not be null and you need at least 1 decoder");
    }

    @Override // llIIllIl1.llllIIIIll1
    public void llllIIIIll1(boolean z) {
        this.f659IlIlIIlIII1 = z;
        for (llllIIIIll1 lllliiiill1 : this.f50lIlllIIIII1) {
            if (!lllliiiill1.isAlive()) {
                lllliiiill1.setDaemon(z);
            } else {
                throw new IllegalStateException("Cannot call setDaemon after server is already started!");
            }
        }
    }

    public final void llllIIIIll1(SelectionKey selectionKey, Iterator<SelectionKey> it) throws IOException, InterruptedException {
        SocketChannel accept = this.f49lIllIlIll1.accept();
        if (accept == null) {
            return;
        }
        accept.configureBlocking(false);
        Socket socket = accept.socket();
        socket.setTcpNoDelay(lIIlIIIIlIlII1());
        socket.setKeepAlive(true);
        lIllIIIlIl1 llllIIIIll12 = this.f42IlIIIIllllIlI1.llllIIIIll1((llllllIlIIIlll1) this, this.f41IIIlIllIlI1);
        llllIIIIll12.llllIIIIll1(accept.register(this.f44IlIlIIIlIlIlll1, 1, llllIIIIll12));
        try {
            llllIIIIll12.llllIIIIll1(this.f42IlIIIIllllIlI1.llllIIIIll1(accept, llllIIIIll12.lllIlIIIlI1()));
            it.remove();
            IlIllIlllIllI1(llllIIIIll12);
        } catch (IOException e) {
            if (llllIIIIll12.lllIlIIIlI1() != null) {
                llllIIIIll12.lllIlIIIlI1().cancel();
            }
            llllIIIIll1(llllIIIIll12.lllIlIIIlI1(), (IlIllIlllIllI1) null, e);
        }
    }

    public final void llllIllIl1(ByteBuffer byteBuffer) throws InterruptedException {
        if (this.f45IllIlIllll1.size() > this.f46IllllIllllll1.intValue()) {
            return;
        }
        this.f45IllIlIllll1.put(byteBuffer);
    }

    public final void lIIIIlllllIlll1(IlIllIlllIllI1 ilIllIlllIllI1, Exception exc) {
        Log.e(f39IIlllllIlll1, "Shutdown due to fatal error", exc);
        llllIllIl1(ilIllIlllIllI1, exc);
        try {
            llllIllIl1(0, "Got error on server side: " + exc.getClass().getName() + (exc.getCause() != null ? " caused by ".concat(exc.getCause().getClass().getName()) : ""));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Log.e(f39IIlllllIlll1, "Interrupt during stop", exc);
            llllIllIl1((IlIllIlllIllI1) null, e);
        }
        List<llllIIIIll1> list = this.f50lIlllIIIII1;
        if (list != null) {
            Iterator<llllIIIIll1> it = list.iterator();
            while (it.hasNext()) {
                it.next().interrupt();
            }
        }
        Thread thread = this.f52llIIllIl1;
        if (thread != null) {
            thread.interrupt();
        }
    }

    @Override // llIIllIl1.IIlIllIIll1
    public final void llllIllIl1(IlIllIlllIllI1 ilIllIlllIllI1) {
        lIllIIIlIl1 lilliiilil1 = (lIllIIIlIl1) ilIllIlllIllI1;
        try {
            lilliiilil1.lllIlIIIlI1().interestOps(5);
        } catch (CancelledKeyException unused) {
            lilliiilil1.f652llllIIIIll1.clear();
        }
        this.f44IlIlIIIlIlIlll1.wakeup();
    }

    public final void llllIIIIll1(SelectionKey selectionKey) throws IIlIllIIll1 {
        lIllIIIlIl1 lilliiilil1 = (lIllIIIlIl1) selectionKey.attachment();
        try {
            if (llIIllIl1.IlIlllIIlI1.llllIIIIll1(lilliiilil1, lilliiilil1.IIIlIllIlI1()) && selectionKey.isValid()) {
                selectionKey.interestOps(1);
            }
        } catch (IOException e) {
            throw new IIlIllIIll1(lilliiilil1, e);
        }
    }

    @Override // llIIllIl1.IIlIllIIll1
    public final void lIIIIlllllIlll1(IlIllIlllIllI1 ilIllIlllIllI1, int i, String str, boolean z) {
        this.f44IlIlIIIlIlIlll1.wakeup();
        if (lIllIIIlIl1(ilIllIlllIllI1)) {
            llllIllIl1(ilIllIlllIllI1, i, str, z);
        }
    }

    public void llllIIIIll1(lIllIIIlIl1 lilliiilil1) throws InterruptedException {
        if (lilliiilil1.lIIlIIIIlIlII1() == null) {
            List<llllIIIIll1> list = this.f50lIlllIIIII1;
            lilliiilil1.llllIIIIll1(list.get(this.f48lIIlllIIIlllII1 % list.size()));
            this.f48lIIlllIIIlllII1++;
        }
        lilliiilil1.lIIlIIIIlIlII1().llllIIIIll1(lilliiilil1);
    }

    public final void llllIIIIll1(SelectionKey selectionKey, IlIllIlllIllI1 ilIllIlllIllI1, IOException iOException) {
        SelectableChannel channel;
        if (selectionKey != null) {
            selectionKey.cancel();
        }
        if (ilIllIlllIllI1 != null) {
            ilIllIlllIllI1.lIIIIlllllIlll1(1006, iOException.getMessage());
        } else {
            if (selectionKey == null || (channel = selectionKey.channel()) == null || !channel.isOpen()) {
                return;
            }
            try {
                channel.close();
            } catch (IOException unused) {
            }
            Log.v(f39IIlllllIlll1, "Connection closed because of exception", iOException);
        }
    }

    public void lIIIIlllllIlll1(String str) {
        llllIIIIll1(str, this.f43IlIIlllllI1);
    }

    public void lIIIIlllllIlll1(byte[] bArr) {
        llllIIIIll1(bArr, this.f43IlIIlllllI1);
    }

    public void lIIIIlllllIlll1(ByteBuffer byteBuffer) {
        llllIIIIll1(byteBuffer, this.f43IlIIlllllI1);
    }

    @Override // llIIllIl1.IIlIllIIll1
    public final void llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1, String str) {
        lIIIIlllllIlll1(ilIllIlllIllI1, str);
    }

    @Override // llIIllIl1.IIlIllIIll1
    public final void llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1, lllIlIlllI1.IlIllIlllIllI1 ilIllIlllIllI12) {
        if (IlIlllIIlI1(ilIllIlllIllI1)) {
            lIIIIlllllIlll1(ilIllIlllIllI1, (lllIlIlllI1.llllIIIIll1) ilIllIlllIllI12);
        }
    }

    @Override // llIIllIl1.IIlIllIIll1
    public final void llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1, Exception exc) {
        llllIllIl1(ilIllIlllIllI1, exc);
    }

    public final void llllIIIIll1(IlIllll1 ilIllll1) {
        IlIllll1 ilIllll12 = this.f42IlIIIIllllIlI1;
        if (ilIllll12 != null) {
            ilIllll12.close();
        }
        this.f42IlIIIIllllIlI1 = ilIllll1;
    }

    @Override // llIIllIl1.IIlIllIIll1
    public InetSocketAddress llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1) {
        return (InetSocketAddress) llllllIlIIIlll1(ilIllIlllIllI1).getRemoteSocketAddress();
    }

    public void llllIIIIll1(byte[] bArr, Collection<IlIllIlllIllI1> collection) {
        if (bArr != null && collection != null) {
            llllIIIIll1(ByteBuffer.wrap(bArr), collection);
            return;
        }
        throw new IllegalArgumentException();
    }

    public void llllIIIIll1(ByteBuffer byteBuffer, Collection<IlIllIlllIllI1> collection) {
        if (byteBuffer != null && collection != null) {
            llllIIIIll1((Object) byteBuffer, collection);
            return;
        }
        throw new IllegalArgumentException();
    }

    public void llllIIIIll1(String str, Collection<IlIllIlllIllI1> collection) {
        if (str != null && collection != null) {
            llllIIIIll1((Object) str, collection);
            return;
        }
        throw new IllegalArgumentException();
    }

    public final void llllIIIIll1(Object obj, Collection<IlIllIlllIllI1> collection) {
        ArrayList arrayList;
        String str = obj instanceof String ? (String) obj : null;
        ByteBuffer byteBuffer = obj instanceof ByteBuffer ? (ByteBuffer) obj : null;
        if (str == null && byteBuffer == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        synchronized (collection) {
            arrayList = new ArrayList(collection);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            IlIllIlllIllI1 ilIllIlllIllI1 = (IlIllIlllIllI1) it.next();
            if (ilIllIlllIllI1 != null) {
                lIlllIIIII1.llllIIIIll1 lIIIIlllllIlll12 = ilIllIlllIllI1.lIIIIlllllIlll1();
                llllIIIIll1(lIIIIlllllIlll12, hashMap, str, byteBuffer);
                try {
                    ilIllIlllIllI1.llllIIIIll1((Collection<IlIIIIllllIlI1.IlIllIlllIllI1>) hashMap.get(lIIIIlllllIlll12));
                } catch (IllIlIllll1.lIllIIIlIl1 unused) {
                }
            }
        }
    }

    public final void llllIIIIll1(lIlllIIIII1.llllIIIIll1 lllliiiill1, Map<lIlllIIIII1.llllIIIIll1, List<IlIIIIllllIlI1.IlIllIlllIllI1>> map, String str, ByteBuffer byteBuffer) {
        if (map.containsKey(lllliiiill1)) {
            return;
        }
        List<IlIIIIllllIlI1.IlIllIlllIllI1> llllIIIIll12 = str != null ? lllliiiill1.llllIIIIll1(str, false) : null;
        if (byteBuffer != null) {
            llllIIIIll12 = lllliiiill1.llllIIIIll1(byteBuffer, false);
        }
        if (llllIIIIll12 != null) {
            map.put(lllliiiill1, llllIIIIll12);
        }
    }
}

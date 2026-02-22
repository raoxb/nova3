package lIlllIIIII1;

import IlIIIIllllIlI1.IIlIllIIll1;
import IlIIIIllllIlI1.IlIllIlllIllI1;
import IllIlIllll1.llllIllIl1;
import IllIlIllll1.llllllIlIIIlll1;
import android.util.Log;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.jvm.internal.ByteCompanionObject;
import lIIlIIIIlIlII1.IlIlllIIlI1;
import lIIlllIIIlllII1.IllIIlIIII1;
import llIIllIl1.lIllIIIlIl1;
import lllIlIlllI1.IlIlIIlIII1;

/* loaded from: classes.dex */
public class lIIIIlllllIlll1 extends lIlllIIIII1.llllIIIIll1 {

    /* renamed from: IIIlIllIlI1, reason: collision with root package name */
    public static final String f527IIIlIllIlI1 = "Sec-WebSocket-Accept";

    /* renamed from: IlIlIIIlIlIlll1, reason: collision with root package name */
    public static final String f528IlIlIIIlIlIlll1 = "Sec-WebSocket-Extensions";

    /* renamed from: lIIlIIIIlIlII1, reason: collision with root package name */
    public static final /* synthetic */ boolean f529lIIlIIIIlIlII1 = true;

    /* renamed from: lIllIlIll1, reason: collision with root package name */
    public static final String f530lIllIlIll1 = "Sec-WebSocket-Protocol";

    /* renamed from: lIlllIIIII1, reason: collision with root package name */
    public static final String f531lIlllIIIII1 = "Draft_6455";

    /* renamed from: llIIIIlIlllIII1, reason: collision with root package name */
    public static final String f532llIIIIlIlllIII1 = "Sec-WebSocket-Key";

    /* renamed from: llIIllIl1, reason: collision with root package name */
    public static final String f533llIIllIl1 = "Upgrade";

    /* renamed from: lllIlIIIlI1, reason: collision with root package name */
    public static final String f534lllIlIIIlI1 = "Connection";

    /* renamed from: IIlIllIIll1, reason: collision with root package name */
    public final List<ByteBuffer> f535IIlIllIIll1;

    /* renamed from: IlIIlllllI1, reason: collision with root package name */
    public int f536IlIIlllllI1;

    /* renamed from: IlIlIIlIII1, reason: collision with root package name */
    public List<IIlllllIlll1.llllIIIIll1> f537IlIlIIlIII1;

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public IllIIlIIII1 f538IlIllIlllIllI1;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public List<IllIIlIIII1> f539IlIlllIIlI1;

    /* renamed from: IlIllll1, reason: collision with root package name */
    public ByteBuffer f540IlIllll1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public IllIIlIIII1 f541IllIIlIIII1;

    /* renamed from: lIllIIIlIl1, reason: collision with root package name */
    public IlIllIlllIllI1 f542lIllIIIlIl1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public IllIIlIIII1 f543llllIllIl1;

    /* renamed from: lllllIllIl1, reason: collision with root package name */
    public final SecureRandom f544lllllIllIl1;

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public IIlllllIlll1.llllIIIIll1 f545llllllIlIIIlll1;

    public class llllIIIIll1 {

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public int f546lIIIIlllllIlll1;

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public int f547llllIIIIll1;

        public llllIIIIll1(int i, int i2) {
            this.f547llllIIIIll1 = i;
            this.f546lIIIIlllllIlll1 = i2;
        }

        public static int lIIIIlllllIlll1(llllIIIIll1 lllliiiill1) {
            return lllliiiill1.f546lIIIIlllllIlll1;
        }

        public static int llllIIIIll1(llllIIIIll1 lllliiiill1) {
            return lllliiiill1.f547llllIIIIll1;
        }

        public final int lIIIIlllllIlll1() {
            return this.f546lIIIIlllllIlll1;
        }

        public final int llllIIIIll1() {
            return this.f547llllIIIIll1;
        }
    }

    public lIIIIlllllIlll1() {
        this((List<IllIIlIIII1>) Collections.emptyList());
    }

    public List<IIlllllIlll1.llllIIIIll1> IIlIllIIll1() {
        return this.f537IlIlIIlIII1;
    }

    public IIlllllIlll1.llllIIIIll1 IlIIlllllI1() {
        return this.f545llllllIlIIIlll1;
    }

    public IllIIlIIII1 IlIlIIlIII1() {
        return this.f543llllIllIl1;
    }

    public final int IlIllIlllIllI1(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() <= 125) {
            return 1;
        }
        return byteBuffer.remaining() <= 65535 ? 2 : 8;
    }

    public final void IlIlllIIlI1(lIllIIIlIl1 lilliiilil1, IlIllIlllIllI1 ilIllIlllIllI1) throws llllIllIl1 {
        try {
            lilliiilil1.lIlllIIIII1().llllIIIIll1(lilliiilil1, IlIIIlIlIlIII1.llllIllIl1.lIIIIlllllIlll1(ilIllIlllIllI1.llllIllIl1()));
        } catch (RuntimeException e) {
            llllIIIIll1(lilliiilil1, e);
        }
    }

    public int IlIllll1() {
        return this.f536IlIIlllllI1;
    }

    @Override // lIlllIIIII1.llllIIIIll1
    public void IllIIlIIII1() {
        this.f540IlIllll1 = null;
        this.f543llllIllIl1 = new lIIlllIIIlllII1.lIIIIlllllIlll1();
        this.f545llllllIlIIIlll1 = null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        lIIIIlllllIlll1 liiiilllllilll1 = (lIIIIlllllIlll1) obj;
        if (this.f536IlIIlllllI1 != liiiilllllilll1.IlIllll1()) {
            return false;
        }
        IllIIlIIII1 illIIlIIII1 = this.f543llllIllIl1;
        if (illIIlIIII1 == null ? liiiilllllilll1.IlIlIIlIII1() != null : !illIIlIIII1.equals(liiiilllllilll1.IlIlIIlIII1())) {
            return false;
        }
        IIlllllIlll1.llllIIIIll1 lllliiiill1 = this.f545llllllIlIIIlll1;
        IIlllllIlll1.llllIIIIll1 IlIIlllllI12 = liiiilllllilll1.IlIIlllllI1();
        return lllliiiill1 != null ? lllliiiill1.equals(IlIIlllllI12) : IlIIlllllI12 == null;
    }

    public int hashCode() {
        IllIIlIIII1 illIIlIIII1 = this.f543llllIllIl1;
        int hashCode = (illIIlIIII1 != null ? illIIlIIII1.hashCode() : 0) * 31;
        IIlllllIlll1.llllIIIIll1 lllliiiill1 = this.f545llllllIlIIIlll1;
        int hashCode2 = (hashCode + (lllliiiill1 != null ? lllliiiill1.hashCode() : 0)) * 31;
        int i = this.f536IlIIlllllI1;
        return hashCode2 + (i ^ (i >>> 32));
    }

    public final byte lIIIIlllllIlll1(int i) {
        if (i == 1) {
            return (byte) 64;
        }
        if (i != 2) {
            return i != 3 ? (byte) 0 : (byte) 16;
        }
        return (byte) 32;
    }

    public final ByteBuffer lIIIIlllllIlll1(IlIllIlllIllI1 ilIllIlllIllI1) {
        ByteBuffer llllIllIl12 = ilIllIlllIllI1.llllIllIl1();
        int i = 0;
        boolean z = this.f550llllIIIIll1 == IlIlllIIlI1.CLIENT;
        int IlIllIlllIllI12 = IlIllIlllIllI1(llllIllIl12);
        ByteBuffer allocate = ByteBuffer.allocate(llllIllIl12.remaining() + (IlIllIlllIllI12 > 1 ? IlIllIlllIllI12 + 1 : IlIllIlllIllI12) + 1 + (z ? 4 : 0));
        byte llllIIIIll12 = (byte) (llllIIIIll1(ilIllIlllIllI1.llllIIIIll1()) | ((byte) (ilIllIlllIllI1.llllllIlIIIlll1() ? -128 : 0)));
        if (ilIllIlllIllI1.IlIlllIIlI1()) {
            llllIIIIll12 = (byte) (llllIIIIll12 | 64);
        }
        if (ilIllIlllIllI1.lIIIIlllllIlll1()) {
            llllIIIIll12 = (byte) (llllIIIIll12 | 32);
        }
        if (ilIllIlllIllI1.IlIllIlllIllI1()) {
            llllIIIIll12 = (byte) (llllIIIIll12 | 16);
        }
        allocate.put(llllIIIIll12);
        byte[] llllIIIIll13 = llllIIIIll1(llllIllIl12.remaining(), IlIllIlllIllI12);
        if (!f529lIIlIIIIlIlII1 && llllIIIIll13.length != IlIllIlllIllI12) {
            throw new AssertionError();
        }
        if (IlIllIlllIllI12 == 1) {
            allocate.put((byte) (llllIIIIll13[0] | llllIIIIll1(z)));
        } else if (IlIllIlllIllI12 == 2) {
            allocate.put((byte) (llllIIIIll1(z) | 126));
            allocate.put(llllIIIIll13);
        } else {
            if (IlIllIlllIllI12 != 8) {
                throw new IllegalStateException("Size representation not supported/specified");
            }
            allocate.put((byte) (llllIIIIll1(z) | ByteCompanionObject.MAX_VALUE));
            allocate.put(llllIIIIll13);
        }
        if (z) {
            ByteBuffer allocate2 = ByteBuffer.allocate(4);
            allocate2.putInt(this.f544lllllIllIl1.nextInt());
            allocate.put(allocate2.array());
            while (llllIllIl12.hasRemaining()) {
                allocate.put((byte) (llllIllIl12.get() ^ allocate2.get(i % 4)));
                i++;
            }
        } else {
            allocate.put(llllIllIl12);
            llllIllIl12.flip();
        }
        if (!f529lIIlIIIIlIlII1 && allocate.remaining() != 0) {
            throw new AssertionError(allocate.remaining());
        }
        allocate.flip();
        return allocate;
    }

    public List<IllIIlIIII1> lIllIIIlIl1() {
        return this.f539IlIlllIIlI1;
    }

    public final String llIIIIlIlllIII1() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(calendar.getTime());
    }

    public final byte llllIIIIll1(boolean z) {
        if (z) {
            return ByteCompanionObject.MIN_VALUE;
        }
        return (byte) 0;
    }

    @Override // lIlllIIIII1.llllIIIIll1
    public lIIlIIIIlIlII1.lIIIIlllllIlll1 llllIIIIll1(lllIlIlllI1.llllIIIIll1 lllliiiill1) throws IllIlIllll1.IlIllIlllIllI1 {
        if (llllIllIl1(lllliiiill1) != 13) {
            Log.v(f531lIlllIIIII1, "acceptHandshakeAsServer - Wrong websocket version.");
            return lIIlIIIIlIlII1.lIIIIlllllIlll1.NOT_MATCHED;
        }
        lIIlIIIIlIlII1.lIIIIlllllIlll1 liiiilllllilll1 = lIIlIIIIlIlII1.lIIIIlllllIlll1.NOT_MATCHED;
        String llllIllIl12 = lllliiiill1.llllIllIl1(f528IlIlIIIlIlIlll1);
        Iterator<IllIIlIIII1> it = this.f539IlIlllIIlI1.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            IllIIlIIII1 next = it.next();
            if (next.llllIIIIll1(llllIllIl12)) {
                this.f543llllIllIl1 = next;
                liiiilllllilll1 = lIIlIIIIlIlII1.lIIIIlllllIlll1.MATCHED;
                Log.v(f531lIlllIIIII1, "acceptHandshakeAsServer - Matching extension found: " + this.f543llllIllIl1);
                break;
            }
        }
        lIIlIIIIlIlII1.lIIIIlllllIlll1 llllIIIIll12 = llllIIIIll1(lllliiiill1.llllIllIl1(f530lIllIlIll1));
        lIIlIIIIlIlII1.lIIIIlllllIlll1 liiiilllllilll12 = lIIlIIIIlIlII1.lIIIIlllllIlll1.MATCHED;
        if (llllIIIIll12 == liiiilllllilll12 && liiiilllllilll1 == liiiilllllilll12) {
            return liiiilllllilll12;
        }
        Log.v(f531lIlllIIIII1, "acceptHandshakeAsServer - No matching extension or protocol found.");
        return lIIlIIIIlIlII1.lIIIIlllllIlll1.NOT_MATCHED;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0095, code lost:
    
        if (r6.hasRemaining() == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0097, code lost:
    
        r6.mark();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x009a, code lost:
    
        r0.add(llllllIlIIIlll1(r6));
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00a2, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00a3, code lost:
    
        r6.reset();
        r1 = java.nio.ByteBuffer.allocate(llllIIIIll1(r1.llllIIIIll1()));
        r5.f540IlIllll1 = r1;
        r1.put(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00b7, code lost:
    
        return r0;
     */
    @Override // lIlllIIIII1.llllIIIIll1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<IlIIIIllllIlI1.IlIllIlllIllI1> llllIllIl1(java.nio.ByteBuffer r6) throws IllIlIllll1.llllIllIl1 {
        /*
            r5 = this;
        L0:
            java.util.LinkedList r0 = new java.util.LinkedList
            r0.<init>()
            java.nio.ByteBuffer r1 = r5.f540IlIllll1
            if (r1 == 0) goto L91
            r6.mark()     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            int r1 = r6.remaining()     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            java.nio.ByteBuffer r2 = r5.f540IlIllll1     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            int r2 = r2.remaining()     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            if (r2 <= r1) goto L32
            java.nio.ByteBuffer r0 = r5.f540IlIllll1     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            byte[] r2 = r6.array()     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            int r3 = r6.position()     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            r0.put(r2, r3, r1)     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            int r0 = r6.position()     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            int r0 = r0 + r1
            r6.position(r0)     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            java.util.List r6 = java.util.Collections.emptyList()     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            return r6
        L32:
            java.nio.ByteBuffer r1 = r5.f540IlIllll1     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            byte[] r3 = r6.array()     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            int r4 = r6.position()     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            r1.put(r3, r4, r2)     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            int r1 = r6.position()     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            int r1 = r1 + r2
            r6.position(r1)     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            java.nio.ByteBuffer r1 = r5.f540IlIllll1     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            java.nio.ByteBuffer r1 = r1.duplicate()     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            r2 = 0
            java.nio.Buffer r1 = r1.position(r2)     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            java.nio.ByteBuffer r1 = (java.nio.ByteBuffer) r1     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            IlIIIIllllIlI1.IlIllIlllIllI1 r1 = r5.llllllIlIIIlll1(r1)     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            r0.add(r1)     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            r1 = 0
            r5.f540IlIllll1 = r1     // Catch: IllIlIllll1.llllIIIIll1 -> L5f
            goto L91
        L5f:
            r0 = move-exception
            int r0 = r0.llllIIIIll1()
            int r0 = r5.llllIIIIll1(r0)
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r0)
            boolean r1 = lIlllIIIII1.lIIIIlllllIlll1.f529lIIlIIIIlIlII1
            if (r1 != 0) goto L83
            int r1 = r0.limit()
            java.nio.ByteBuffer r2 = r5.f540IlIllll1
            int r2 = r2.limit()
            if (r1 <= r2) goto L7d
            goto L83
        L7d:
            java.lang.AssertionError r6 = new java.lang.AssertionError
            r6.<init>()
            throw r6
        L83:
            java.nio.ByteBuffer r1 = r5.f540IlIllll1
            r1.rewind()
            java.nio.ByteBuffer r1 = r5.f540IlIllll1
            r0.put(r1)
            r5.f540IlIllll1 = r0
            goto L0
        L91:
            boolean r1 = r6.hasRemaining()
            if (r1 == 0) goto Lb7
            r6.mark()
            IlIIIIllllIlI1.IlIllIlllIllI1 r1 = r5.llllllIlIIIlll1(r6)     // Catch: IllIlIllll1.llllIIIIll1 -> La2
            r0.add(r1)     // Catch: IllIlIllll1.llllIIIIll1 -> La2
            goto L91
        La2:
            r1 = move-exception
            r6.reset()
            int r1 = r1.llllIIIIll1()
            int r1 = r5.llllIIIIll1(r1)
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r1)
            r5.f540IlIllll1 = r1
            r1.put(r6)
        Lb7:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: lIlllIIIII1.lIIIIlllllIlll1.llllIllIl1(java.nio.ByteBuffer):java.util.List");
    }

    public final ByteBuffer lllllIllIl1() throws llllllIlIIIlll1 {
        ByteBuffer allocate;
        synchronized (this.f535IIlIllIIll1) {
            long j = 0;
            while (this.f535IIlIllIIll1.iterator().hasNext()) {
                j += r1.next().limit();
            }
            IlIlllIIlI1();
            allocate = ByteBuffer.allocate((int) j);
            Iterator<ByteBuffer> it = this.f535IIlIllIIll1.iterator();
            while (it.hasNext()) {
                allocate.put(it.next());
            }
        }
        allocate.flip();
        return allocate;
    }

    public final IlIllIlllIllI1 llllllIlIIIlll1(ByteBuffer byteBuffer) throws IllIlIllll1.llllIIIIll1, llllIllIl1 {
        if (byteBuffer == null) {
            throw new IllegalArgumentException();
        }
        int remaining = byteBuffer.remaining();
        int i = 2;
        llllIIIIll1(remaining, 2);
        byte b = byteBuffer.get();
        boolean z = (b >> 8) != 0;
        boolean z2 = (b & 64) != 0;
        boolean z3 = (b & 32) != 0;
        boolean z4 = (b & 16) != 0;
        byte b2 = byteBuffer.get();
        boolean z5 = (b2 & ByteCompanionObject.MIN_VALUE) != 0;
        int i2 = (byte) (b2 & ByteCompanionObject.MAX_VALUE);
        lIIlIIIIlIlII1.llllIllIl1 llllIIIIll12 = llllIIIIll1((byte) (b & 15));
        if (i2 < 0 || i2 > 125) {
            llllIIIIll1 llllIIIIll13 = llllIIIIll1(byteBuffer, llllIIIIll12, i2, remaining, 2);
            i2 = llllIIIIll13.f547llllIIIIll1;
            i = llllIIIIll13.f546lIIIIlllllIlll1;
        }
        llllIIIIll1(i2);
        llllIIIIll1(remaining, i + (z5 ? 4 : 0) + i2);
        ByteBuffer allocate = ByteBuffer.allocate(llllIIIIll1(i2));
        if (z5) {
            byte[] bArr = new byte[4];
            byteBuffer.get(bArr);
            for (int i3 = 0; i3 < i2; i3++) {
                allocate.put((byte) (byteBuffer.get() ^ bArr[i3 % 4]));
            }
        } else {
            allocate.put(byteBuffer.array(), byteBuffer.position(), allocate.limit());
            byteBuffer.position(allocate.limit() + byteBuffer.position());
        }
        IlIIIIllllIlI1.llllllIlIIIlll1 llllIIIIll14 = IlIIIIllllIlI1.llllllIlIIIlll1.llllIIIIll1(llllIIIIll12);
        llllIIIIll14.llllIIIIll1(z);
        llllIIIIll14.lIIIIlllllIlll1(z2);
        llllIIIIll14.llllIllIl1(z3);
        llllIIIIll14.IllIIlIIII1(z4);
        allocate.flip();
        llllIIIIll14.llllIIIIll1(allocate);
        if (llllIIIIll14.llllIIIIll1() != lIIlIIIIlIlII1.llllIllIl1.CONTINUOUS) {
            if (llllIIIIll14.IlIlllIIlI1() || llllIIIIll14.lIIIIlllllIlll1() || llllIIIIll14.IlIllIlllIllI1()) {
                this.f538IlIllIlllIllI1 = IlIlIIlIII1();
            } else {
                this.f538IlIllIlllIllI1 = this.f541IllIIlIIII1;
            }
        }
        if (this.f538IlIllIlllIllI1 == null) {
            this.f538IlIllIlllIllI1 = this.f541IllIIlIIII1;
        }
        this.f538IlIllIlllIllI1.llllIllIl1(llllIIIIll14);
        this.f538IlIllIlllIllI1.lIIIIlllllIlll1(llllIIIIll14);
        Log.v(f531lIlllIIIII1, "afterDecoding(" + llllIIIIll14.llllIllIl1().remaining() + "): " + (llllIIIIll14.llllIllIl1().remaining() > 1000 ? "too big to display" : new String(llllIIIIll14.llllIllIl1().array())));
        llllIIIIll14.IlIlIIlIII1();
        return llllIIIIll14;
    }

    @Override // lIlllIIIII1.llllIIIIll1
    public String toString() {
        String simpleName = getClass().getSimpleName();
        if (IlIlIIlIII1() != null) {
            simpleName = simpleName + " extension: " + IlIlIIlIII1().toString();
        }
        if (IlIIlllllI1() != null) {
            simpleName = simpleName + " protocol: " + IlIIlllllI1().toString();
        }
        return simpleName + " max frame size: " + this.f536IlIIlllllI1;
    }

    public lIIIIlllllIlll1(IllIIlIIII1 illIIlIIII1) {
        this((List<IllIIlIIII1>) Collections.singletonList(illIIlIIII1));
    }

    public lIIIIlllllIlll1(List<IllIIlIIII1> list) {
        this(list, Collections.singletonList(new IIlllllIlll1.lIIIIlllllIlll1("")), Integer.MAX_VALUE);
    }

    public final void IlIllIlllIllI1() {
        synchronized (this.f535IIlIllIIll1) {
            this.f535IIlIllIIll1.clear();
        }
    }

    public lIIIIlllllIlll1(List<IllIIlIIII1> list, List<IIlllllIlll1.llllIIIIll1> list2) {
        this(list, list2, Integer.MAX_VALUE);
    }

    public final void IlIlllIIlI1(ByteBuffer byteBuffer) {
        synchronized (this.f535IIlIllIIll1) {
            this.f535IIlIllIIll1.add(byteBuffer);
        }
    }

    public lIIIIlllllIlll1(List<IllIIlIIII1> list, int i) {
        this(list, Collections.singletonList(new IIlllllIlll1.lIIIIlllllIlll1("")), i);
    }

    public lIIIIlllllIlll1(List<IllIIlIIII1> list, List<IIlllllIlll1.llllIIIIll1> list2, int i) {
        this.f543llllIllIl1 = new lIIlllIIIlllII1.lIIIIlllllIlll1();
        this.f541IllIIlIIII1 = new lIIlllIIIlllII1.lIIIIlllllIlll1();
        this.f544lllllIllIl1 = new SecureRandom();
        if (list != null && list2 != null && i >= 1) {
            this.f539IlIlllIIlI1 = new ArrayList(list.size());
            this.f537IlIlIIlIII1 = new ArrayList(list2.size());
            this.f535IIlIllIIll1 = new ArrayList();
            Iterator<IllIIlIIII1> it = list.iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (it.next().getClass().equals(lIIlllIIIlllII1.lIIIIlllllIlll1.class)) {
                    z = true;
                }
            }
            this.f539IlIlllIIlI1.addAll(list);
            if (!z) {
                List<IllIIlIIII1> list3 = this.f539IlIlllIIlI1;
                list3.add(list3.size(), this.f543llllIllIl1);
            }
            this.f537IlIlIIlIII1.addAll(list2);
            this.f536IlIIlllllI1 = i;
            this.f538IlIllIlllIllI1 = null;
            return;
        }
        throw new IllegalArgumentException();
    }

    public final void IllIIlIIII1(lIllIIIlIl1 lilliiilil1, IlIllIlllIllI1 ilIllIlllIllI1) throws llllIllIl1 {
        if (this.f542lIllIIIlIl1 != null) {
            IlIlllIIlI1(ilIllIlllIllI1.llllIllIl1());
            IlIlllIIlI1();
            if (this.f542lIllIIIlIl1.llllIIIIll1() == lIIlIIIIlIlII1.llllIllIl1.TEXT) {
                ((IlIIIIllllIlI1.llllllIlIIIlll1) this.f542lIllIIIlIl1).llllIIIIll1(lllllIllIl1());
                ((IlIIIIllllIlI1.llllllIlIIIlll1) this.f542lIllIIIlIl1).IlIlIIlIII1();
                try {
                    lilliiilil1.lIlllIIIII1().llllIIIIll1(lilliiilil1, IlIIIlIlIlIII1.llllIllIl1.lIIIIlllllIlll1(this.f542lIllIIIlIl1.llllIllIl1()));
                } catch (RuntimeException e) {
                    llllIIIIll1(lilliiilil1, e);
                }
            } else if (this.f542lIllIIIlIl1.llllIIIIll1() == lIIlIIIIlIlII1.llllIllIl1.BINARY) {
                ((IlIIIIllllIlI1.llllllIlIIIlll1) this.f542lIllIIIlIl1).llllIIIIll1(lllllIllIl1());
                ((IlIIIIllllIlI1.llllllIlIIIlll1) this.f542lIllIIIlIl1).IlIlIIlIII1();
                try {
                    lilliiilil1.lIlllIIIII1().llllIIIIll1(lilliiilil1, this.f542lIllIIIlIl1.llllIllIl1());
                } catch (RuntimeException e2) {
                    llllIIIIll1(lilliiilil1, e2);
                }
            }
            this.f542lIllIIIlIl1 = null;
            IlIllIlllIllI1();
            return;
        }
        Log.v(f531lIlllIIIII1, "Protocol error: Previous continuous frame sequence not completed.");
        throw new llllIllIl1(1002, "Continuous frame sequence was not started.");
    }

    public final void IlIlllIIlI1() throws llllllIlIIIlll1 {
        long llllllIlIIIlll12 = llllllIlIIIlll1();
        if (llllllIlIIIlll12 <= this.f536IlIIlllllI1) {
            return;
        }
        IlIllIlllIllI1();
        Log.v(f531lIlllIIIII1, "Payload limit reached. Allowed: " + this.f536IlIIlllllI1 + " Current: " + llllllIlIIIlll12);
        throw new llllllIlIIIlll1(this.f536IlIIlllllI1);
    }

    public final lIIlIIIIlIlII1.lIIIIlllllIlll1 llllIIIIll1(String str) {
        for (IIlllllIlll1.llllIIIIll1 lllliiiill1 : this.f537IlIlIIlIII1) {
            if (lllliiiill1.llllIIIIll1(str)) {
                this.f545llllllIlIIIlll1 = lllliiiill1;
                Log.v(f531lIlllIIIII1, "acceptHandshake - Matching protocol found: " + this.f545llllllIlIIIlll1);
                return lIIlIIIIlIlII1.lIIIIlllllIlll1.MATCHED;
            }
        }
        return lIIlIIIIlIlII1.lIIIIlllllIlll1.NOT_MATCHED;
    }

    @Override // lIlllIIIII1.llllIIIIll1
    public lIIlIIIIlIlII1.lIIIIlllllIlll1 llllIIIIll1(lllIlIlllI1.llllIIIIll1 lllliiiill1, IlIlIIlIII1 ilIlIIlIII1) throws IllIlIllll1.IlIllIlllIllI1 {
        if (!llllIIIIll1(ilIlIIlIII1)) {
            Log.v(f531lIlllIIIII1, "acceptHandshakeAsClient - Missing/wrong upgrade or connection in handshake.");
            return lIIlIIIIlIlII1.lIIIIlllllIlll1.NOT_MATCHED;
        }
        if (lllliiiill1.llllIIIIll1(f532llIIIIlIlllIII1) && ilIlIIlIII1.llllIIIIll1(f527IIIlIllIlI1)) {
            if (!lIIIIlllllIlll1(lllliiiill1.llllIllIl1(f532llIIIIlIlllIII1)).equals(ilIlIIlIII1.llllIllIl1(f527IIIlIllIlI1))) {
                Log.v(f531lIlllIIIII1, "acceptHandshakeAsClient - Wrong key for Sec-WebSocket-Key.");
                return lIIlIIIIlIlII1.lIIIIlllllIlll1.NOT_MATCHED;
            }
            lIIlIIIIlIlII1.lIIIIlllllIlll1 liiiilllllilll1 = lIIlIIIIlIlII1.lIIIIlllllIlll1.NOT_MATCHED;
            String llllIllIl12 = ilIlIIlIII1.llllIllIl1(f528IlIlIIIlIlIlll1);
            Iterator<IllIIlIIII1> it = this.f539IlIlllIIlI1.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                IllIIlIIII1 next = it.next();
                if (next.lIIIIlllllIlll1(llllIllIl12)) {
                    this.f543llllIllIl1 = next;
                    liiiilllllilll1 = lIIlIIIIlIlII1.lIIIIlllllIlll1.MATCHED;
                    Log.v(f531lIlllIIIII1, "acceptHandshakeAsClient - Matching extension found: " + this.f543llllIllIl1);
                    break;
                }
            }
            lIIlIIIIlIlII1.lIIIIlllllIlll1 llllIIIIll12 = llllIIIIll1(ilIlIIlIII1.llllIllIl1(f530lIllIlIll1));
            lIIlIIIIlIlII1.lIIIIlllllIlll1 liiiilllllilll12 = lIIlIIIIlIlII1.lIIIIlllllIlll1.MATCHED;
            if (llllIIIIll12 == liiiilllllilll12 && liiiilllllilll1 == liiiilllllilll12) {
                return liiiilllllilll12;
            }
            Log.v(f531lIlllIIIII1, "acceptHandshakeAsClient - No matching extension or protocol found.");
            return lIIlIIIIlIlII1.lIIIIlllllIlll1.NOT_MATCHED;
        }
        Log.v(f531lIlllIIIII1, "acceptHandshakeAsClient - Missing Sec-WebSocket-Key or Sec-WebSocket-Accept");
        return lIIlIIIIlIlII1.lIIIIlllllIlll1.NOT_MATCHED;
    }

    public final void llllIllIl1(IlIllIlllIllI1 ilIllIlllIllI1) throws llllIllIl1 {
        if (this.f542lIllIIIlIl1 == null) {
            this.f542lIllIIIlIl1 = ilIllIlllIllI1;
            IlIlllIIlI1(ilIllIlllIllI1.llllIllIl1());
            IlIlllIIlI1();
            return;
        }
        Log.v(f531lIlllIIIII1, "Protocol error: Previous continuous frame sequence not completed.");
        throw new llllIllIl1(1002, "Previous continuous frame sequence not completed.");
    }

    public final String lIIIIlllllIlll1(String str) {
        try {
            return IlIIIlIlIlIII1.llllIIIIll1.llllIIIIll1(MessageDigest.getInstance("SHA1").digest((str.trim() + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    public final void llllIllIl1(lIllIIIlIl1 lilliiilil1, IlIllIlllIllI1 ilIllIlllIllI1) {
        int i;
        String str;
        if (!(ilIllIlllIllI1 instanceof IlIIIIllllIlI1.lIIIIlllllIlll1)) {
            i = IlIIIIllllIlI1.lIIIIlllllIlll1.f83llIIIIlIlllIII1;
            str = "";
        } else {
            IlIIIIllllIlI1.lIIIIlllllIlll1 liiiilllllilll1 = (IlIIIIllllIlI1.lIIIIlllllIlll1) ilIllIlllIllI1;
            i = liiiilllllilll1.lIllIIIlIl1();
            str = liiiilllllilll1.IIlIllIIll1();
        }
        if (lilliiilil1.lllllIllIl1() == lIIlIIIIlIlII1.IllIIlIIII1.CLOSING) {
            lilliiilil1.lIIIIlllllIlll1(i, str, true);
        } else if (lIIIIlllllIlll1() == lIIlIIIIlIlII1.llllIIIIll1.TWOWAY) {
            lilliiilil1.llllIIIIll1(i, str, true);
        } else {
            lilliiilil1.llllIllIl1(i, str, false);
        }
    }

    public final void lIIIIlllllIlll1(lIllIIIlIl1 lilliiilil1, IlIllIlllIllI1 ilIllIlllIllI1) {
        try {
            lilliiilil1.lIlllIIIII1().llllIIIIll1(lilliiilil1, ilIllIlllIllI1.llllIllIl1());
        } catch (RuntimeException e) {
            llllIIIIll1(lilliiilil1, e);
        }
    }

    @Override // lIlllIIIII1.llllIIIIll1
    public lIIlIIIIlIlII1.llllIIIIll1 lIIIIlllllIlll1() {
        return lIIlIIIIlIlII1.llllIIIIll1.TWOWAY;
    }

    public final long llllllIlIIIlll1() {
        long j;
        synchronized (this.f535IIlIllIIll1) {
            j = 0;
            while (this.f535IIlIllIIll1.iterator().hasNext()) {
                j += r1.next().limit();
            }
        }
        return j;
    }

    @Override // lIlllIIIII1.llllIIIIll1
    public lllIlIlllI1.lIIIIlllllIlll1 llllIIIIll1(lllIlIlllI1.lIIIIlllllIlll1 liiiilllllilll1) {
        liiiilllllilll1.llllIIIIll1(f533llIIllIl1, "websocket");
        liiiilllllilll1.llllIIIIll1(f534lllIlIIIlI1, f533llIIllIl1);
        byte[] bArr = new byte[16];
        this.f544lllllIllIl1.nextBytes(bArr);
        liiiilllllilll1.llllIIIIll1(f532llIIIIlIlllIII1, IlIIIlIlIlIII1.llllIIIIll1.llllIIIIll1(bArr));
        liiiilllllilll1.llllIIIIll1("Sec-WebSocket-Version", "13");
        StringBuilder sb = new StringBuilder();
        for (IllIIlIIII1 illIIlIIII1 : this.f539IlIlllIIlI1) {
            illIIlIIII1.lIIIIlllllIlll1();
            if (illIIlIIII1.lIIIIlllllIlll1().length() != 0) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(illIIlIIII1.lIIIIlllllIlll1());
            }
        }
        if (sb.length() != 0) {
            liiiilllllilll1.llllIIIIll1(f528IlIlIIIlIlIlll1, sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        for (IIlllllIlll1.llllIIIIll1 lllliiiill1 : this.f537IlIlIIlIII1) {
            if (lllliiiill1.lIIIIlllllIlll1().length() != 0) {
                if (sb2.length() > 0) {
                    sb2.append(", ");
                }
                sb2.append(lllliiiill1.lIIIIlllllIlll1());
            }
        }
        if (sb2.length() != 0) {
            liiiilllllilll1.llllIIIIll1(f530lIllIlIll1, sb2.toString());
        }
        return liiiilllllilll1;
    }

    @Override // lIlllIIIII1.llllIIIIll1
    public lllIlIlllI1.llllIllIl1 llllIIIIll1(lllIlIlllI1.llllIIIIll1 lllliiiill1, lllIlIlllI1.lIllIIIlIl1 lilliiilil1) throws IllIlIllll1.IlIllIlllIllI1 {
        lilliiilil1.llllIIIIll1(f533llIIllIl1, "websocket");
        lilliiilil1.llllIIIIll1(f534lllIlIIIlI1, lllliiiill1.llllIllIl1(f534lllIlIIIlI1));
        String llllIllIl12 = lllliiiill1.llllIllIl1(f532llIIIIlIlllIII1);
        if (!"".equals(llllIllIl12)) {
            lilliiilil1.llllIIIIll1(f527IIIlIllIlI1, lIIIIlllllIlll1(llllIllIl12));
            if (IlIlIIlIII1().llllIllIl1().length() != 0) {
                lilliiilil1.llllIIIIll1(f528IlIlIIIlIlIlll1, IlIlIIlIII1().llllIllIl1());
            }
            if (IlIIlllllI1() != null && IlIIlllllI1().lIIIIlllllIlll1().length() != 0) {
                lilliiilil1.llllIIIIll1(f530lIllIlIll1, IlIIlllllI1().lIIIIlllllIlll1());
            }
            lilliiilil1.IllIIlIIII1("Web Socket Protocol Handshake");
            lilliiilil1.llllIIIIll1("Server", "TooTallNate Java-WebSocket");
            lilliiilil1.llllIIIIll1("Date", llIIIIlIlllIII1());
            return lilliiilil1;
        }
        throw new IllIlIllll1.IlIllIlllIllI1("missing Sec-WebSocket-Key");
    }

    @Override // lIlllIIIII1.llllIIIIll1
    public lIlllIIIII1.llllIIIIll1 llllIIIIll1() {
        ArrayList arrayList = new ArrayList();
        Iterator<IllIIlIIII1> it = lIllIIIlIl1().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().llllIIIIll1());
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<IIlllllIlll1.llllIIIIll1> it2 = IIlIllIIll1().iterator();
        while (it2.hasNext()) {
            arrayList2.add(it2.next().llllIIIIll1());
        }
        return new lIIIIlllllIlll1(arrayList, arrayList2, this.f536IlIIlllllI1);
    }

    @Override // lIlllIIIII1.llllIIIIll1
    public ByteBuffer llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1) {
        IlIlIIlIII1().llllIIIIll1(ilIllIlllIllI1);
        Log.v(f531lIlllIIIII1, "afterEnconding(" + ilIllIlllIllI1.llllIllIl1().remaining() + "): " + (ilIllIlllIllI1.llllIllIl1().remaining() > 1000 ? "too big to display" : new String(ilIllIlllIllI1.llllIllIl1().array())));
        return lIIIIlllllIlll1(ilIllIlllIllI1);
    }

    public final llllIIIIll1 llllIIIIll1(ByteBuffer byteBuffer, lIIlIIIIlIlII1.llllIllIl1 llllillil1, int i, int i2, int i3) throws IllIlIllll1.IlIlllIIlI1, IllIlIllll1.llllIIIIll1, llllllIlIIIlll1 {
        int i4;
        int i5;
        if (llllillil1 != lIIlIIIIlIlII1.llllIllIl1.PING && llllillil1 != lIIlIIIIlIlII1.llllIllIl1.PONG && llllillil1 != lIIlIIIIlIlII1.llllIllIl1.CLOSING) {
            if (i == 126) {
                i4 = i3 + 2;
                llllIIIIll1(i2, i4);
                i5 = new BigInteger(new byte[]{0, byteBuffer.get(), byteBuffer.get()}).intValue();
            } else {
                i4 = i3 + 8;
                llllIIIIll1(i2, i4);
                byte[] bArr = new byte[8];
                for (int i6 = 0; i6 < 8; i6++) {
                    bArr[i6] = byteBuffer.get();
                }
                long longValue = new BigInteger(bArr).longValue();
                llllIIIIll1(longValue);
                i5 = (int) longValue;
            }
            return new llllIIIIll1(i5, i4);
        }
        Log.v(f531lIlllIIIII1, "Invalid frame: more than 125 octets");
        throw new IllIlIllll1.IlIlllIIlI1("more than 125 octets");
    }

    public final void llllIIIIll1(long j) throws llllllIlIIIlll1 {
        if (j > 2147483647L) {
            Log.v(f531lIlllIIIII1, "Limit exedeed: Payloadsize is to big...");
            throw new llllllIlIIIlll1("Payloadsize is to big...");
        }
        if (j > this.f536IlIIlllllI1) {
            Log.v(f531lIlllIIIII1, "Payload limit reached. Allowed: " + this.f536IlIIlllllI1 + " Current: " + j);
            throw new llllllIlIIIlll1("Payload limit reached.", this.f536IlIIlllllI1);
        }
        if (j >= 0) {
            return;
        }
        Log.v(f531lIlllIIIII1, "Limit underflow: Payloadsize is to little...");
        throw new llllllIlIIIlll1("Payloadsize is to little...");
    }

    public final void llllIIIIll1(int i, int i2) throws IllIlIllll1.llllIIIIll1 {
        if (i >= i2) {
            return;
        }
        Log.v(f531lIlllIIIII1, "Incomplete frame: maxpacketsize < realpacketsize");
        throw new IllIlIllll1.llllIIIIll1(i2);
    }

    @Override // lIlllIIIII1.llllIIIIll1
    public List<IlIllIlllIllI1> llllIIIIll1(ByteBuffer byteBuffer, boolean z) {
        IlIIIIllllIlI1.llllIIIIll1 lllliiiill1 = new IlIIIIllllIlI1.llllIIIIll1();
        lllliiiill1.f96llllIllIl1 = byteBuffer;
        lllliiiill1.f93IllIIlIIII1 = z;
        return Collections.singletonList(lllliiiill1);
    }

    @Override // lIlllIIIII1.llllIIIIll1
    public List<IlIllIlllIllI1> llllIIIIll1(String str, boolean z) {
        IIlIllIIll1 iIlIllIIll1 = new IIlIllIIll1();
        iIlIllIIll1.f96llllIllIl1 = ByteBuffer.wrap(IlIIIlIlIlIII1.llllIllIl1.lIIIIlllllIlll1(str));
        iIlIllIIll1.f93IllIIlIIII1 = z;
        try {
            iIlIllIIll1.IlIlIIlIII1();
            return Collections.singletonList(iIlIllIIll1);
        } catch (llllIllIl1 e) {
            throw new IllIlIllll1.IlIlIIlIII1(e);
        }
    }

    public final byte[] llllIIIIll1(long j, int i) {
        byte[] bArr = new byte[i];
        int i2 = (i * 8) - 8;
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) (j >>> (i2 - (i3 * 8)));
        }
        return bArr;
    }

    public final byte llllIIIIll1(lIIlIIIIlIlII1.llllIllIl1 llllillil1) {
        if (llllillil1 == lIIlIIIIlIlII1.llllIllIl1.CONTINUOUS) {
            return (byte) 0;
        }
        if (llllillil1 == lIIlIIIIlIlII1.llllIllIl1.TEXT) {
            return (byte) 1;
        }
        if (llllillil1 == lIIlIIIIlIlII1.llllIllIl1.BINARY) {
            return (byte) 2;
        }
        if (llllillil1 == lIIlIIIIlIlII1.llllIllIl1.CLOSING) {
            return (byte) 8;
        }
        if (llllillil1 == lIIlIIIIlIlII1.llllIllIl1.PING) {
            return (byte) 9;
        }
        if (llllillil1 == lIIlIIIIlIlII1.llllIllIl1.PONG) {
            return (byte) 10;
        }
        throw new IllegalArgumentException("Don't know how to handle " + llllillil1.toString());
    }

    public final lIIlIIIIlIlII1.llllIllIl1 llllIIIIll1(byte b) throws IllIlIllll1.IlIlllIIlI1 {
        if (b == 0) {
            return lIIlIIIIlIlII1.llllIllIl1.CONTINUOUS;
        }
        if (b == 1) {
            return lIIlIIIIlIlII1.llllIllIl1.TEXT;
        }
        if (b != 2) {
            switch (b) {
                case 8:
                    return lIIlIIIIlIlII1.llllIllIl1.CLOSING;
                case 9:
                    return lIIlIIIIlIlII1.llllIllIl1.PING;
                case 10:
                    return lIIlIIIIlIlII1.llllIllIl1.PONG;
                default:
                    throw new IllIlIllll1.IlIlllIIlI1("Unknown opcode " + ((int) b));
            }
        }
        return lIIlIIIIlIlII1.llllIllIl1.BINARY;
    }

    @Override // lIlllIIIII1.llllIIIIll1
    public void llllIIIIll1(lIllIIIlIl1 lilliiilil1, IlIllIlllIllI1 ilIllIlllIllI1) throws llllIllIl1 {
        lIIlIIIIlIlII1.llllIllIl1 llllIIIIll12 = ilIllIlllIllI1.llllIIIIll1();
        if (llllIIIIll12 == lIIlIIIIlIlII1.llllIllIl1.CLOSING) {
            llllIllIl1(lilliiilil1, ilIllIlllIllI1);
            return;
        }
        if (llllIIIIll12 == lIIlIIIIlIlII1.llllIllIl1.PING) {
            lilliiilil1.lIlllIIIII1().llllIIIIll1(lilliiilil1, ilIllIlllIllI1);
            return;
        }
        if (llllIIIIll12 == lIIlIIIIlIlII1.llllIllIl1.PONG) {
            lilliiilil1.IllIlIllll1();
            lilliiilil1.lIlllIIIII1().getClass();
            return;
        }
        if (ilIllIlllIllI1.llllllIlIIIlll1() && llllIIIIll12 != lIIlIIIIlIlII1.llllIllIl1.CONTINUOUS) {
            if (this.f542lIllIIIlIl1 == null) {
                if (llllIIIIll12 == lIIlIIIIlIlII1.llllIllIl1.TEXT) {
                    IlIlllIIlI1(lilliiilil1, ilIllIlllIllI1);
                    return;
                } else if (llllIIIIll12 == lIIlIIIIlIlII1.llllIllIl1.BINARY) {
                    lIIIIlllllIlll1(lilliiilil1, ilIllIlllIllI1);
                    return;
                } else {
                    Log.e(f531lIlllIIIII1, "non control or continious frame expected");
                    throw new llllIllIl1(1002, "non control or continious frame expected");
                }
            }
            Log.e(f531lIlllIIIII1, "Protocol error: Continuous frame sequence not completed.");
            throw new llllIllIl1(1002, "Continuous frame sequence not completed.");
        }
        llllIIIIll1(lilliiilil1, ilIllIlllIllI1, llllIIIIll12);
    }

    public final void llllIIIIll1(lIllIIIlIl1 lilliiilil1, IlIllIlllIllI1 ilIllIlllIllI1, lIIlIIIIlIlII1.llllIllIl1 llllillil1) throws llllIllIl1 {
        lIIlIIIIlIlII1.llllIllIl1 llllillil12 = lIIlIIIIlIlII1.llllIllIl1.CONTINUOUS;
        if (llllillil1 != llllillil12) {
            llllIllIl1(ilIllIlllIllI1);
        } else if (ilIllIlllIllI1.llllllIlIIIlll1()) {
            IllIIlIIII1(lilliiilil1, ilIllIlllIllI1);
        } else if (this.f542lIllIIIlIl1 == null) {
            Log.e(f531lIlllIIIII1, "Protocol error: Continuous frame sequence was not started.");
            throw new llllIllIl1(1002, "Continuous frame sequence was not started.");
        }
        if (llllillil1 == lIIlIIIIlIlII1.llllIllIl1.TEXT && !IlIIIlIlIlIII1.llllIllIl1.llllIIIIll1(ilIllIlllIllI1.llllIllIl1())) {
            Log.e(f531lIlllIIIII1, "Protocol error: Payload is not UTF8");
            throw new llllIllIl1(1007);
        }
        if (llllillil1 != llllillil12 || this.f542lIllIIIlIl1 == null) {
            return;
        }
        IlIlllIIlI1(ilIllIlllIllI1.llllIllIl1());
    }

    public final void llllIIIIll1(lIllIIIlIl1 lilliiilil1, RuntimeException runtimeException) {
        Log.e(f531lIlllIIIII1, "Runtime exception during onWebsocketMessage", runtimeException);
        lilliiilil1.lIlllIIIII1().llllIIIIll1(lilliiilil1, runtimeException);
    }
}

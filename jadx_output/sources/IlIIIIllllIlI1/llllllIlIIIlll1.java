package IlIIIIllllIlI1;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public abstract class llllllIlIIIlll1 implements IlIllIlllIllI1 {

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public lIIlIIIIlIlII1.llllIllIl1 f94lIIIIlllllIlll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public ByteBuffer f96llllIllIl1 = ByteBuffer.allocate(0);

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public boolean f95llllIIIIll1 = true;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public boolean f93IllIIlIIII1 = false;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public boolean f92IlIlllIIlI1 = false;

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public boolean f91IlIllIlllIllI1 = false;

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public boolean f97llllllIlIIIlll1 = false;

    public static /* synthetic */ class llllIIIIll1 {

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public static final /* synthetic */ int[] f98llllIIIIll1;

        static {
            int[] iArr = new int[lIIlIIIIlIlII1.llllIllIl1.values().length];
            f98llllIIIIll1 = iArr;
            try {
                iArr[lIIlIIIIlIlII1.llllIllIl1.PING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f98llllIIIIll1[lIIlIIIIlIlII1.llllIllIl1.PONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f98llllIIIIll1[lIIlIIIIlIlII1.llllIllIl1.TEXT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f98llllIIIIll1[lIIlIIIIlIlII1.llllIllIl1.BINARY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f98llllIIIIll1[lIIlIIIIlIlII1.llllIllIl1.CLOSING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f98llllIIIIll1[lIIlIIIIlIlII1.llllIllIl1.CONTINUOUS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public llllllIlIIIlll1(lIIlIIIIlIlII1.llllIllIl1 llllillil1) {
        this.f94lIIIIlllllIlll1 = llllillil1;
    }

    public abstract void IlIlIIlIII1() throws IllIlIllll1.llllIllIl1;

    @Override // IlIIIIllllIlI1.IlIllIlllIllI1
    public boolean IlIllIlllIllI1() {
        return this.f97llllllIlIIIlll1;
    }

    @Override // IlIIIIllllIlI1.IlIllIlllIllI1
    public boolean IlIlllIIlI1() {
        return this.f92IlIlllIIlI1;
    }

    @Override // IlIIIIllllIlI1.IlIllIlllIllI1
    public boolean IllIIlIIII1() {
        return this.f93IllIIlIIII1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        llllllIlIIIlll1 lllllliliiilll1 = (llllllIlIIIlll1) obj;
        if (this.f95llllIIIIll1 != lllllliliiilll1.f95llllIIIIll1 || this.f93IllIIlIIII1 != lllllliliiilll1.f93IllIIlIIII1 || this.f92IlIlllIIlI1 != lllllliliiilll1.f92IlIlllIIlI1 || this.f91IlIllIlllIllI1 != lllllliliiilll1.f91IlIllIlllIllI1 || this.f97llllllIlIIIlll1 != lllllliliiilll1.f97llllllIlIIIlll1 || this.f94lIIIIlllllIlll1 != lllllliliiilll1.f94lIIIIlllllIlll1) {
            return false;
        }
        ByteBuffer byteBuffer = this.f96llllIllIl1;
        return byteBuffer != null ? byteBuffer.equals(lllllliliiilll1.f96llllIllIl1) : lllllliliiilll1.f96llllIllIl1 == null;
    }

    public int hashCode() {
        int hashCode = (this.f94lIIIIlllllIlll1.hashCode() + ((this.f95llllIIIIll1 ? 1 : 0) * 31)) * 31;
        ByteBuffer byteBuffer = this.f96llllIllIl1;
        return ((((((((hashCode + (byteBuffer != null ? byteBuffer.hashCode() : 0)) * 31) + (this.f93IllIIlIIII1 ? 1 : 0)) * 31) + (this.f92IlIlllIIlI1 ? 1 : 0)) * 31) + (this.f91IlIllIlllIllI1 ? 1 : 0)) * 31) + (this.f97llllllIlIIIlll1 ? 1 : 0);
    }

    @Override // IlIIIIllllIlI1.IlIllIlllIllI1
    public boolean lIIIIlllllIlll1() {
        return this.f91IlIllIlllIllI1;
    }

    @Override // IlIIIIllllIlI1.IlIllIlllIllI1
    public lIIlIIIIlIlII1.llllIllIl1 llllIIIIll1() {
        return this.f94lIIIIlllllIlll1;
    }

    @Override // IlIIIIllllIlI1.IlIllIlllIllI1
    public ByteBuffer llllIllIl1() {
        return this.f96llllIllIl1;
    }

    @Override // IlIIIIllllIlI1.IlIllIlllIllI1
    public boolean llllllIlIIIlll1() {
        return this.f95llllIIIIll1;
    }

    public String toString() {
        return "Framedata{ opcode:" + llllIIIIll1() + ", fin:" + llllllIlIIIlll1() + ", rsv1:" + IlIlllIIlI1() + ", rsv2:" + lIIIIlllllIlll1() + ", rsv3:" + IlIllIlllIllI1() + ", payload length:[pos:" + this.f96llllIllIl1.position() + ", len:" + this.f96llllIllIl1.remaining() + "], payload:" + (this.f96llllIllIl1.remaining() > 1000 ? "(too big to display)" : new String(this.f96llllIllIl1.array())) + '}';
    }

    public void IlIlllIIlI1(boolean z) {
        this.f93IllIIlIIII1 = z;
    }

    public void IllIIlIIII1(boolean z) {
        this.f97llllllIlIIIlll1 = z;
    }

    public void lIIIIlllllIlll1(boolean z) {
        this.f92IlIlllIIlI1 = z;
    }

    @Override // IlIIIIllllIlI1.IlIllIlllIllI1
    public void llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1) {
        ByteBuffer llllIllIl12 = ilIllIlllIllI1.llllIllIl1();
        if (this.f96llllIllIl1 == null) {
            this.f96llllIllIl1 = ByteBuffer.allocate(llllIllIl12.remaining());
            llllIllIl12.mark();
            this.f96llllIllIl1.put(llllIllIl12);
            llllIllIl12.reset();
        } else {
            llllIllIl12.mark();
            ByteBuffer byteBuffer = this.f96llllIllIl1;
            byteBuffer.position(byteBuffer.limit());
            ByteBuffer byteBuffer2 = this.f96llllIllIl1;
            byteBuffer2.limit(byteBuffer2.capacity());
            if (llllIllIl12.remaining() > this.f96llllIllIl1.remaining()) {
                ByteBuffer allocate = ByteBuffer.allocate(this.f96llllIllIl1.capacity() + llllIllIl12.remaining());
                this.f96llllIllIl1.flip();
                allocate.put(this.f96llllIllIl1);
                allocate.put(llllIllIl12);
                this.f96llllIllIl1 = allocate;
            } else {
                this.f96llllIllIl1.put(llllIllIl12);
            }
            this.f96llllIllIl1.rewind();
            llllIllIl12.reset();
        }
        this.f95llllIIIIll1 = ilIllIlllIllI1.llllllIlIIIlll1();
    }

    public void llllIllIl1(boolean z) {
        this.f91IlIllIlllIllI1 = z;
    }

    public void llllIIIIll1(ByteBuffer byteBuffer) {
        this.f96llllIllIl1 = byteBuffer;
    }

    public void llllIIIIll1(boolean z) {
        this.f95llllIIIIll1 = z;
    }

    public static llllllIlIIIlll1 llllIIIIll1(lIIlIIIIlIlII1.llllIllIl1 llllillil1) {
        if (llllillil1 != null) {
            switch (llllIIIIll1.f98llllIIIIll1[llllillil1.ordinal()]) {
                case 1:
                    return new IlIlIIlIII1();
                case 2:
                    return new lIllIIIlIl1();
                case 3:
                    return new IIlIllIIll1();
                case 4:
                    return new IlIIIIllllIlI1.llllIIIIll1();
                case 5:
                    return new lIIIIlllllIlll1();
                case 6:
                    return new llllIllIl1();
                default:
                    throw new IllegalArgumentException("Supplied opcode is invalid");
            }
        }
        throw new IllegalArgumentException("Supplied opcode cannot be null");
    }
}

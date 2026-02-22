package IlIIIIllllIlI1;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class lIIIIlllllIlll1 extends IllIIlIIII1 {

    /* renamed from: IIIlIllIlI1, reason: collision with root package name */
    public static final int f71IIIlIllIlI1 = 1008;

    /* renamed from: IIlIllIIll1, reason: collision with root package name */
    public static final int f72IIlIllIIll1 = 1000;

    /* renamed from: IlIIIIllllIlI1, reason: collision with root package name */
    public static final int f73IlIIIIllllIlI1 = -1;

    /* renamed from: IlIIlllllI1, reason: collision with root package name */
    public static final int f74IlIIlllllI1 = 1003;

    /* renamed from: IlIlIIIlIlIlll1, reason: collision with root package name */
    public static final int f75IlIlIIIlIlIlll1 = 1007;

    /* renamed from: IlIllll1, reason: collision with root package name */
    public static final int f76IlIllll1 = 1001;

    /* renamed from: IllIlIllll1, reason: collision with root package name */
    public static final int f77IllIlIllll1 = 1013;

    /* renamed from: IllllIllllll1, reason: collision with root package name */
    public static final int f78IllllIllllll1 = 1015;

    /* renamed from: lIIlIIIIlIlII1, reason: collision with root package name */
    public static final int f79lIIlIIIIlIlII1 = 1012;

    /* renamed from: lIIlllIIIlllII1, reason: collision with root package name */
    public static final int f80lIIlllIIIlllII1 = 1014;

    /* renamed from: lIllIlIll1, reason: collision with root package name */
    public static final int f81lIllIlIll1 = 1006;

    /* renamed from: lIlllIIIII1, reason: collision with root package name */
    public static final int f82lIlllIIIII1 = 1011;

    /* renamed from: llIIIIlIlllIII1, reason: collision with root package name */
    public static final int f83llIIIIlIlllIII1 = 1005;

    /* renamed from: llIIllIl1, reason: collision with root package name */
    public static final int f84llIIllIl1 = 1009;

    /* renamed from: llIlIIlll1, reason: collision with root package name */
    public static final int f85llIlIIlll1 = -3;

    /* renamed from: lllIlIIIlI1, reason: collision with root package name */
    public static final int f86lllIlIIIlI1 = 1010;

    /* renamed from: lllIlIlllI1, reason: collision with root package name */
    public static final int f87lllIlIlllI1 = -2;

    /* renamed from: lllllIllIl1, reason: collision with root package name */
    public static final int f88lllllIllIl1 = 1002;

    /* renamed from: IlIlIIlIII1, reason: collision with root package name */
    public int f89IlIlIIlIII1;

    /* renamed from: lIllIIIlIl1, reason: collision with root package name */
    public String f90lIllIIIlIl1;

    public lIIIIlllllIlll1() {
        super(lIIlIIIIlIlII1.llllIllIl1.CLOSING);
        llllIIIIll1("");
        llllIIIIll1(1000);
    }

    public String IIlIllIIll1() {
        return this.f90lIllIIIlIl1;
    }

    @Override // IlIIIIllllIlI1.IllIIlIIII1, IlIIIIllllIlI1.llllllIlIIIlll1
    public void IlIlIIlIII1() throws IllIlIllll1.llllIllIl1 {
        super.IlIlIIlIII1();
        if (this.f89IlIlIIlIII1 == 1007 && this.f90lIllIIIlIl1.isEmpty()) {
            throw new IllIlIllll1.llllIllIl1(1007, "Received text is no valid utf8 string!");
        }
        if (this.f89IlIlIIlIII1 == 1005 && this.f90lIllIIIlIl1.length() > 0) {
            throw new IllIlIllll1.llllIllIl1(1002, "A close frame must have a closecode if it has a reason");
        }
        int i = this.f89IlIlIIlIII1;
        if (i > 1015 && i < 3000) {
            throw new IllIlIllll1.llllIllIl1(1002, "Trying to send an illegal close code!");
        }
        if (i == 1006 || i == 1015 || i == 1005 || i > 4999 || i < 1000 || i == 1004) {
            throw new IllIlIllll1.IlIlllIIlI1("closecode must not be sent over the wire: " + this.f89IlIlIIlIII1);
        }
    }

    public final void IlIllll1() {
        byte[] lIIIIlllllIlll12 = IlIIIlIlIlIII1.llllIllIl1.lIIIIlllllIlll1(this.f90lIllIIIlIl1);
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(this.f89IlIlIIlIII1);
        allocate.position(2);
        ByteBuffer allocate2 = ByteBuffer.allocate(lIIIIlllllIlll12.length + 2);
        allocate2.put(allocate);
        allocate2.put(lIIIIlllllIlll12);
        allocate2.rewind();
        this.f96llllIllIl1 = allocate2;
    }

    @Override // IlIIIIllllIlI1.llllllIlIIIlll1
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        lIIIIlllllIlll1 liiiilllllilll1 = (lIIIIlllllIlll1) obj;
        if (this.f89IlIlIIlIII1 != liiiilllllilll1.f89IlIlIIlIII1) {
            return false;
        }
        String str = this.f90lIllIIIlIl1;
        String str2 = liiiilllllilll1.f90lIllIIIlIl1;
        return str != null ? str.equals(str2) : str2 == null;
    }

    @Override // IlIIIIllllIlI1.llllllIlIIIlll1
    public int hashCode() {
        int hashCode = ((super.hashCode() * 31) + this.f89IlIlIIlIII1) * 31;
        String str = this.f90lIllIIIlIl1;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public int lIllIIIlIl1() {
        return this.f89IlIlIIlIII1;
    }

    public void llllIIIIll1(int i) {
        this.f89IlIlIIlIII1 = i;
        if (i == 1015) {
            this.f89IlIlIIlIII1 = f83llIIIIlIlllIII1;
            this.f90lIllIIIlIl1 = "";
        }
        IlIllll1();
    }

    @Override // IlIIIIllllIlI1.llllllIlIIIlll1, IlIIIIllllIlI1.IlIllIlllIllI1
    public ByteBuffer llllIllIl1() {
        return this.f89IlIlIIlIII1 == 1005 ? ByteBuffer.allocate(0) : this.f96llllIllIl1;
    }

    @Override // IlIIIIllllIlI1.llllllIlIIIlll1
    public String toString() {
        return super.toString() + "code: " + this.f89IlIlIIlIII1;
    }

    public void llllIIIIll1(String str) {
        if (str == null) {
            str = "";
        }
        this.f90lIllIIIlIl1 = str;
        IlIllll1();
    }

    @Override // IlIIIIllllIlI1.llllllIlIIIlll1
    public void llllIIIIll1(ByteBuffer byteBuffer) {
        this.f89IlIlIIlIII1 = f83llIIIIlIlllIII1;
        this.f90lIllIIIlIl1 = "";
        byteBuffer.mark();
        if (byteBuffer.remaining() == 0) {
            this.f89IlIlIIlIII1 = 1000;
            return;
        }
        if (byteBuffer.remaining() == 1) {
            this.f89IlIlIIlIII1 = 1002;
            return;
        }
        if (byteBuffer.remaining() >= 2) {
            ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.position(2);
            allocate.putShort(byteBuffer.getShort());
            allocate.position(0);
            this.f89IlIlIIlIII1 = allocate.getInt();
        }
        byteBuffer.reset();
        try {
            llllIIIIll1(byteBuffer, byteBuffer.position());
        } catch (IllIlIllll1.llllIllIl1 unused) {
            this.f89IlIlIIlIII1 = 1007;
            this.f90lIllIIIlIl1 = null;
        }
    }

    public final void llllIIIIll1(ByteBuffer byteBuffer, int i) throws IllIlIllll1.llllIllIl1 {
        try {
            try {
                byteBuffer.position(byteBuffer.position() + 2);
                this.f90lIllIIIlIl1 = IlIIIlIlIlIII1.llllIllIl1.lIIIIlllllIlll1(byteBuffer);
            } catch (IllegalArgumentException unused) {
                throw new IllIlIllll1.llllIllIl1(1007);
            }
        } finally {
            byteBuffer.position(i);
        }
    }
}

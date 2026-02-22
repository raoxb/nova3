package lIlllIIIII1;

import IlIIIIllllIlI1.IIlIllIIll1;
import IlIIIIllllIlI1.llllllIlIIIlll1;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import lIIlIIIIlIlII1.IlIlllIIlI1;
import lIIlIIIIlIlII1.llllIllIl1;
import lllIlIlllI1.IlIlIIlIII1;
import lllIlIlllI1.IlIllIlllIllI1;
import lllIlIlllI1.IllIIlIIII1;
import lllIlIlllI1.lIllIIIlIl1;

/* loaded from: classes.dex */
public abstract class llllIIIIll1 {

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public IlIlllIIlI1 f550llllIIIIll1 = null;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public llllIllIl1 f549lIIIIlllllIlll1 = null;

    public static String lIIIIlllllIlll1(ByteBuffer byteBuffer) {
        ByteBuffer llllIIIIll12 = llllIIIIll1(byteBuffer);
        if (llllIIIIll12 == null) {
            return null;
        }
        return IlIIIlIlIlIII1.llllIllIl1.llllIIIIll1(llllIIIIll12.array(), 0, llllIIIIll12.limit());
    }

    public static ByteBuffer llllIIIIll1(ByteBuffer byteBuffer) {
        ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.remaining());
        byte b = 48;
        while (byteBuffer.hasRemaining()) {
            byte b2 = byteBuffer.get();
            allocate.put(b2);
            if (b == 13 && b2 == 10) {
                allocate.limit(allocate.position() - 2);
                allocate.position(0);
                return allocate;
            }
            b = b2;
        }
        byteBuffer.position(byteBuffer.position() - allocate.position());
        return null;
    }

    public IlIllIlllIllI1 IllIIlIIII1(ByteBuffer byteBuffer) throws IllIlIllll1.IlIllIlllIllI1 {
        return llllIIIIll1(byteBuffer, this.f550llllIIIIll1);
    }

    public abstract void IllIIlIIII1();

    public abstract lIIlIIIIlIlII1.llllIIIIll1 lIIIIlllllIlll1();

    public abstract ByteBuffer llllIIIIll1(IlIIIIllllIlI1.IlIllIlllIllI1 ilIllIlllIllI1);

    public abstract List<IlIIIIllllIlI1.IlIllIlllIllI1> llllIIIIll1(String str, boolean z);

    public abstract List<IlIIIIllllIlI1.IlIllIlllIllI1> llllIIIIll1(ByteBuffer byteBuffer, boolean z);

    public abstract lIIlIIIIlIlII1.lIIIIlllllIlll1 llllIIIIll1(lllIlIlllI1.llllIIIIll1 lllliiiill1) throws IllIlIllll1.IlIllIlllIllI1;

    public abstract lIIlIIIIlIlII1.lIIIIlllllIlll1 llllIIIIll1(lllIlIlllI1.llllIIIIll1 lllliiiill1, IlIlIIlIII1 ilIlIIlIII1) throws IllIlIllll1.IlIllIlllIllI1;

    public abstract llllIIIIll1 llllIIIIll1();

    public abstract lllIlIlllI1.lIIIIlllllIlll1 llllIIIIll1(lllIlIlllI1.lIIIIlllllIlll1 liiiilllllilll1) throws IllIlIllll1.IlIllIlllIllI1;

    public abstract lllIlIlllI1.llllIllIl1 llllIIIIll1(lllIlIlllI1.llllIIIIll1 lllliiiill1, lIllIIIlIl1 lilliiilil1) throws IllIlIllll1.IlIllIlllIllI1;

    public abstract void llllIIIIll1(llIIllIl1.lIllIIIlIl1 lilliiilil1, IlIIIIllllIlI1.IlIllIlllIllI1 ilIllIlllIllI1) throws IllIlIllll1.llllIllIl1;

    public int llllIllIl1(IlIllIlllIllI1 ilIllIlllIllI1) {
        String llllIllIl12 = ilIllIlllIllI1.llllIllIl1("Sec-WebSocket-Version");
        if (llllIllIl12.length() > 0) {
            try {
                return Integer.parseInt(llllIllIl12.trim());
            } catch (NumberFormatException unused) {
            }
        }
        return -1;
    }

    public abstract List<IlIIIIllllIlI1.IlIllIlllIllI1> llllIllIl1(ByteBuffer byteBuffer) throws IllIlIllll1.llllIllIl1;

    public String toString() {
        return getClass().getSimpleName();
    }

    public static lllIlIlllI1.llllIllIl1 lIIIIlllllIlll1(String[] strArr, String str) throws IllIlIllll1.IlIllIlllIllI1 {
        if ("GET".equalsIgnoreCase(strArr[0])) {
            if ("HTTP/1.1".equalsIgnoreCase(strArr[2])) {
                IllIIlIIII1 illIIlIIII1 = new IllIIlIIII1();
                illIIlIIII1.lIIIIlllllIlll1(strArr[1]);
                return illIIlIIII1;
            }
            throw new IllIlIllll1.IlIllIlllIllI1(String.format("Invalid status line received: %s Status line: %s", strArr[2], str));
        }
        throw new IllIlIllll1.IlIllIlllIllI1(String.format("Invalid request method received: %s Status line: %s", strArr[0], str));
    }

    public IlIlllIIlI1 llllIllIl1() {
        return this.f550llllIIIIll1;
    }

    public static lllIlIlllI1.llllIllIl1 llllIIIIll1(ByteBuffer byteBuffer, IlIlllIIlI1 ilIlllIIlI1) throws IllIlIllll1.IlIllIlllIllI1 {
        lllIlIlllI1.llllIllIl1 lIIIIlllllIlll12;
        String lIIIIlllllIlll13 = lIIIIlllllIlll1(byteBuffer);
        if (lIIIIlllllIlll13 != null) {
            String[] split = lIIIIlllllIlll13.split(" ", 3);
            if (split.length == 3) {
                if (ilIlllIIlI1 == IlIlllIIlI1.CLIENT) {
                    lIIIIlllllIlll12 = llllIIIIll1(split, lIIIIlllllIlll13);
                } else {
                    lIIIIlllllIlll12 = lIIIIlllllIlll1(split, lIIIIlllllIlll13);
                }
                String lIIIIlllllIlll14 = lIIIIlllllIlll1(byteBuffer);
                while (lIIIIlllllIlll14 != null && lIIIIlllllIlll14.length() > 0) {
                    String[] split2 = lIIIIlllllIlll14.split(":", 2);
                    if (split2.length == 2) {
                        if (lIIIIlllllIlll12.llllIIIIll1(split2[0])) {
                            lIIIIlllllIlll12.llllIIIIll1(split2[0], lIIIIlllllIlll12.llllIllIl1(split2[0]) + "; " + split2[1].replaceFirst("^ +", ""));
                        } else {
                            lIIIIlllllIlll12.llllIIIIll1(split2[0], split2[1].replaceFirst("^ +", ""));
                        }
                        lIIIIlllllIlll14 = lIIIIlllllIlll1(byteBuffer);
                    } else {
                        throw new IllIlIllll1.IlIllIlllIllI1("not an http header");
                    }
                }
                if (lIIIIlllllIlll14 != null) {
                    return lIIIIlllllIlll12;
                }
                throw new IllIlIllll1.lIIIIlllllIlll1();
            }
            throw new IllIlIllll1.IlIllIlllIllI1();
        }
        throw new IllIlIllll1.lIIIIlllllIlll1(byteBuffer.capacity() + 128);
    }

    public List<ByteBuffer> lIIIIlllllIlll1(IlIllIlllIllI1 ilIllIlllIllI1) {
        return llllIIIIll1(ilIllIlllIllI1, true);
    }

    public static lllIlIlllI1.llllIllIl1 llllIIIIll1(String[] strArr, String str) throws IllIlIllll1.IlIllIlllIllI1 {
        if ("101".equals(strArr[1])) {
            if ("HTTP/1.1".equalsIgnoreCase(strArr[0])) {
                lllIlIlllI1.IlIlllIIlI1 ilIlllIIlI1 = new lllIlIlllI1.IlIlllIIlI1();
                ilIlllIIlI1.f698llllIllIl1 = Short.parseShort(strArr[1]);
                ilIlllIIlI1.f697IllIIlIIII1 = strArr[2];
                return ilIlllIIlI1;
            }
            throw new IllIlIllll1.IlIllIlllIllI1(String.format("Invalid status line received: %s Status line: %s", strArr[0], str));
        }
        throw new IllIlIllll1.IlIllIlllIllI1(String.format("Invalid status code received: %s Status line: %s", strArr[1], str));
    }

    public boolean llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1) {
        return ilIllIlllIllI1.llllIllIl1(lIIIIlllllIlll1.f533llIIllIl1).equalsIgnoreCase("websocket") && ilIllIlllIllI1.llllIllIl1(lIIIIlllllIlll1.f534lllIlIIIlI1).toLowerCase(Locale.ENGLISH).contains("upgrade");
    }

    public List<IlIIIIllllIlI1.IlIllIlllIllI1> llllIIIIll1(llllIllIl1 llllillil1, ByteBuffer byteBuffer, boolean z) {
        llllllIlIIIlll1 iIlIllIIll1;
        llllIllIl1 llllillil12 = llllIllIl1.BINARY;
        if (llllillil1 != llllillil12 && llllillil1 != llllIllIl1.TEXT) {
            throw new IllegalArgumentException("Only Opcode.BINARY or  Opcode.TEXT are allowed");
        }
        if (this.f549lIIIIlllllIlll1 != null) {
            iIlIllIIll1 = new IlIIIIllllIlI1.llllIllIl1();
        } else {
            this.f549lIIIIlllllIlll1 = llllillil1;
            if (llllillil1 == llllillil12) {
                iIlIllIIll1 = new IlIIIIllllIlI1.llllIIIIll1();
            } else {
                iIlIllIIll1 = llllillil1 == llllIllIl1.TEXT ? new IIlIllIIll1() : null;
            }
        }
        iIlIllIIll1.llllIIIIll1(byteBuffer);
        iIlIllIIll1.llllIIIIll1(z);
        try {
            iIlIllIIll1.IlIlIIlIII1();
            if (z) {
                this.f549lIIIIlllllIlll1 = null;
            } else {
                this.f549lIIIIlllllIlll1 = llllillil1;
            }
            return Collections.singletonList(iIlIllIIll1);
        } catch (IllIlIllll1.llllIllIl1 e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Deprecated
    public List<ByteBuffer> llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1, IlIlllIIlI1 ilIlllIIlI1) {
        return lIIIIlllllIlll1(ilIllIlllIllI1);
    }

    @Deprecated
    public List<ByteBuffer> llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1, IlIlllIIlI1 ilIlllIIlI1, boolean z) {
        return llllIIIIll1(ilIllIlllIllI1, z);
    }

    public List<ByteBuffer> llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1, boolean z) {
        StringBuilder sb = new StringBuilder(100);
        if (ilIllIlllIllI1 instanceof lllIlIlllI1.llllIIIIll1) {
            sb.append("GET ").append(((lllIlIlllI1.llllIIIIll1) ilIllIlllIllI1).llllIIIIll1()).append(" HTTP/1.1");
        } else if (ilIllIlllIllI1 instanceof IlIlIIlIII1) {
            sb.append("HTTP/1.1 101 ").append(((IlIlIIlIII1) ilIllIlllIllI1).llllIllIl1());
        } else {
            throw new IllegalArgumentException("unknown role");
        }
        sb.append("\r\n");
        Iterator<String> IllIIlIIII12 = ilIllIlllIllI1.IllIIlIIII1();
        while (IllIIlIIII12.hasNext()) {
            String next = IllIIlIIII12.next();
            String llllIllIl12 = ilIllIlllIllI1.llllIllIl1(next);
            sb.append(next);
            sb.append(": ");
            sb.append(llllIllIl12);
            sb.append("\r\n");
        }
        sb.append("\r\n");
        byte[] llllIIIIll12 = IlIIIlIlIlIII1.llllIllIl1.llllIIIIll1(sb.toString());
        byte[] IlIlllIIlI12 = z ? ilIllIlllIllI1.IlIlllIIlI1() : null;
        ByteBuffer allocate = ByteBuffer.allocate((IlIlllIIlI12 == null ? 0 : IlIlllIIlI12.length) + llllIIIIll12.length);
        allocate.put(llllIIIIll12);
        if (IlIlllIIlI12 != null) {
            allocate.put(IlIlllIIlI12);
        }
        allocate.flip();
        return Collections.singletonList(allocate);
    }

    public int llllIIIIll1(int i) throws IllIlIllll1.llllIllIl1 {
        if (i >= 0) {
            return i;
        }
        throw new IllIlIllll1.llllIllIl1(1002, "Negative count");
    }

    public void llllIIIIll1(IlIlllIIlI1 ilIlllIIlI1) {
        this.f550llllIIIIll1 = ilIlllIIlI1;
    }
}

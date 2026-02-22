package IllllIllllll1;

import IlIIIIllllIlI1.IlIllIlllIllI1;
import IlIIIIllllIlI1.IlIlllIIlI1;
import IlIIIIllllIlI1.llllllIlIIIlll1;
import IllIlIllll1.llllIllIl1;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import lIIlllIIIlllII1.IllIIlIIII1;

/* loaded from: classes.dex */
public class llllIIIIll1 extends lIIlllIIIlllII1.llllIIIIll1 {

    /* renamed from: IIlIllIIll1, reason: collision with root package name */
    public static final String f313IIlIllIIll1 = "client_no_context_takeover";

    /* renamed from: IlIIlllllI1, reason: collision with root package name */
    public static final int f314IlIIlllllI1 = 32768;

    /* renamed from: IlIlIIIlIlIlll1, reason: collision with root package name */
    public static final int f315IlIlIIIlIlIlll1 = 1024;

    /* renamed from: IlIlIIlIII1, reason: collision with root package name */
    public static final String f316IlIlIIlIII1 = "permessage-deflate";

    /* renamed from: IlIllll1, reason: collision with root package name */
    public static final String f317IlIllll1 = "server_max_window_bits";

    /* renamed from: lIllIIIlIl1, reason: collision with root package name */
    public static final String f318lIllIIIlIl1 = "server_no_context_takeover";

    /* renamed from: lIllIlIll1, reason: collision with root package name */
    public static final byte[] f319lIllIlIll1 = {0, 0, -1, -1};

    /* renamed from: llIIIIlIlllIII1, reason: collision with root package name */
    public static final int f320llIIIIlIlllIII1 = 32768;

    /* renamed from: lllllIllIl1, reason: collision with root package name */
    public static final String f321lllllIllIl1 = "client_max_window_bits";

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public final Inflater f322IlIllIlllIllI1;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public final int f323IlIlllIIlI1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public Map<String, String> f324IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public boolean f325lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public int f326llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public boolean f327llllIllIl1;

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public final Deflater f328llllllIlIIIlll1;

    public llllIIIIll1() {
        this(-1);
    }

    public boolean IlIlIIlIII1() {
        return this.f325lIIIIlllllIlll1;
    }

    public int IlIllIlllIllI1() {
        return this.f326llllIIIIll1;
    }

    public int IlIlllIIlI1() {
        return this.f323IlIlllIIlI1;
    }

    public void lIIIIlllllIlll1(boolean z) {
        this.f325lIIIIlllllIlll1 = z;
    }

    public void llllIIIIll1(int i) {
        this.f326llllIIIIll1 = i;
    }

    @Override // lIIlllIIIlllII1.lIIIIlllllIlll1, lIIlllIIIlllII1.IllIIlIIII1
    public String llllIllIl1() {
        return "permessage-deflate; server_no_context_takeover".concat(this.f327llllIllIl1 ? "; client_no_context_takeover" : "");
    }

    public boolean llllllIlIIIlll1() {
        return this.f327llllIllIl1;
    }

    @Override // lIIlllIIIlllII1.lIIIIlllllIlll1, lIIlllIIIlllII1.IllIIlIIII1
    public String toString() {
        return "PerMessageDeflateExtension";
    }

    public llllIIIIll1(int i) {
        this.f326llllIIIIll1 = 1024;
        this.f325lIIIIlllllIlll1 = true;
        this.f327llllIllIl1 = false;
        this.f324IllIIlIIII1 = new LinkedHashMap();
        this.f323IlIlllIIlI1 = i;
        this.f328llllllIlIIIlll1 = new Deflater(i, true);
        this.f322IlIllIlllIllI1 = new Inflater(true);
    }

    @Override // lIIlllIIIlllII1.lIIIIlllllIlll1, lIIlllIIIlllII1.IllIIlIIII1
    public void lIIIIlllllIlll1(IlIllIlllIllI1 ilIllIlllIllI1) throws llllIllIl1 {
        if (ilIllIlllIllI1 instanceof IlIlllIIlI1) {
            if (ilIllIlllIllI1.IlIlllIIlI1() || ilIllIlllIllI1.llllIIIIll1() == lIIlIIIIlIlII1.llllIllIl1.CONTINUOUS) {
                if (ilIllIlllIllI1.llllIIIIll1() == lIIlIIIIlIlII1.llllIllIl1.CONTINUOUS && ilIllIlllIllI1.IlIlllIIlI1()) {
                    throw new llllIllIl1(1008, "RSV1 bit can only be set for the first frame.");
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    llllIIIIll1(ilIllIlllIllI1.llllIllIl1().array(), byteArrayOutputStream);
                    if (this.f322IlIllIlllIllI1.getRemaining() > 0) {
                        this.f322IlIllIlllIllI1.reset();
                        llllIIIIll1(ilIllIlllIllI1.llllIllIl1().array(), byteArrayOutputStream);
                    }
                    if (ilIllIlllIllI1.llllllIlIIIlll1()) {
                        llllIIIIll1(f319lIllIlIll1, byteArrayOutputStream);
                        if (this.f327llllIllIl1) {
                            this.f322IlIllIlllIllI1.reset();
                        }
                    }
                    ((llllllIlIIIlll1) ilIllIlllIllI1).llllIIIIll1(ByteBuffer.wrap(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size()));
                } catch (DataFormatException e) {
                    throw new llllIllIl1(1008, e.getMessage());
                }
            }
        }
    }

    public void llllIIIIll1(boolean z) {
        this.f327llllIllIl1 = z;
    }

    @Override // lIIlllIIIlllII1.llllIIIIll1, lIIlllIIIlllII1.lIIIIlllllIlll1, lIIlllIIIlllII1.IllIIlIIII1
    public void llllIllIl1(IlIllIlllIllI1 ilIllIlllIllI1) throws llllIllIl1 {
        if ((ilIllIlllIllI1 instanceof IlIIIIllllIlI1.llllIllIl1) && (ilIllIlllIllI1.IlIlllIIlI1() || ilIllIlllIllI1.lIIIIlllllIlll1() || ilIllIlllIllI1.IlIllIlllIllI1())) {
            throw new IllIlIllll1.IlIlllIIlI1("bad rsv RSV1: " + ilIllIlllIllI1.IlIlllIIlI1() + " RSV2: " + ilIllIlllIllI1.lIIIIlllllIlll1() + " RSV3: " + ilIllIlllIllI1.IlIllIlllIllI1());
        }
        super.llllIllIl1(ilIllIlllIllI1);
    }

    public final void llllIIIIll1(byte[] bArr, ByteArrayOutputStream byteArrayOutputStream) throws DataFormatException {
        this.f322IlIllIlllIllI1.setInput(bArr);
        byte[] bArr2 = new byte[1024];
        while (true) {
            int inflate = this.f322IlIllIlllIllI1.inflate(bArr2);
            if (inflate <= 0) {
                return;
            } else {
                byteArrayOutputStream.write(bArr2, 0, inflate);
            }
        }
    }

    @Override // lIIlllIIIlllII1.lIIIIlllllIlll1, lIIlllIIIlllII1.IllIIlIIII1
    public void llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1) {
        if (ilIllIlllIllI1 instanceof IlIlllIIlI1) {
            byte[] array = ilIllIlllIllI1.llllIllIl1().array();
            if (array.length < this.f326llllIIIIll1) {
                return;
            }
            if (!(ilIllIlllIllI1 instanceof IlIIIIllllIlI1.llllIllIl1)) {
                ((IlIlllIIlI1) ilIllIlllIllI1).lIIIIlllllIlll1(true);
            }
            this.f328llllllIlIIIlll1.setInput(array);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int deflate = this.f328llllllIlIIIlll1.deflate(bArr, 0, 1024, 2);
                if (deflate <= 0) {
                    break;
                } else {
                    byteArrayOutputStream.write(bArr, 0, deflate);
                }
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            int length = byteArray.length;
            if (ilIllIlllIllI1.llllllIlIIIlll1()) {
                if (llllIIIIll1(byteArray)) {
                    length -= f319lIllIlIll1.length;
                }
                if (this.f325lIIIIlllllIlll1) {
                    this.f328llllllIlIIIlll1.reset();
                }
            }
            ((llllllIlIIIlll1) ilIllIlllIllI1).llllIIIIll1(ByteBuffer.wrap(byteArray, 0, length));
        }
    }

    @Override // lIIlllIIIlllII1.lIIIIlllllIlll1, lIIlllIIIlllII1.IllIIlIIII1
    public boolean lIIIIlllllIlll1(String str) {
        for (String str2 : str.split(",")) {
            if (f316IlIlIIlIII1.equalsIgnoreCase(lIIlllIIIlllII1.llllIllIl1.llllIIIIll1(str2).f381lIIIIlllllIlll1)) {
                return true;
            }
        }
        return false;
    }

    public static boolean llllIIIIll1(byte[] bArr) {
        if (bArr.length < 4) {
            return false;
        }
        int length = bArr.length;
        int i = 0;
        while (true) {
            byte[] bArr2 = f319lIllIlIll1;
            if (i >= bArr2.length) {
                return true;
            }
            if (bArr2[i] != bArr[(length - bArr2.length) + i]) {
                return false;
            }
            i++;
        }
    }

    @Override // lIIlllIIIlllII1.lIIIIlllllIlll1, lIIlllIIIlllII1.IllIIlIIII1
    public String lIIIIlllllIlll1() {
        this.f324IllIIlIIII1.put(f313IIlIllIIll1, "");
        this.f324IllIIlIIII1.put(f318lIllIIIlIl1, "");
        return "permessage-deflate; server_no_context_takeover; client_no_context_takeover";
    }

    @Override // lIIlllIIIlllII1.lIIIIlllllIlll1, lIIlllIIIlllII1.IllIIlIIII1
    public boolean llllIIIIll1(String str) {
        for (String str2 : str.split(",")) {
            lIIlllIIIlllII1.llllIllIl1 llllIIIIll12 = lIIlllIIIlllII1.llllIllIl1.llllIIIIll1(str2);
            if (f316IlIlIIlIII1.equalsIgnoreCase(llllIIIIll12.f381lIIIIlllllIlll1)) {
                this.f324IllIIlIIII1.putAll(llllIIIIll12.f382llllIIIIll1);
                if (this.f324IllIIlIIII1.containsKey(f313IIlIllIIll1)) {
                    this.f327llllIllIl1 = true;
                }
                return true;
            }
        }
        return false;
    }

    @Override // lIIlllIIIlllII1.lIIIIlllllIlll1, lIIlllIIIlllII1.IllIIlIIII1
    public IllIIlIIII1 llllIIIIll1() {
        llllIIIIll1 lllliiiill1 = new llllIIIIll1(IlIlllIIlI1());
        lllliiiill1.f326llllIIIIll1 = IlIllIlllIllI1();
        lllliiiill1.f327llllIllIl1 = llllllIlIIIlll1();
        lllliiiill1.f325lIIIIlllllIlll1 = IlIlIIlIII1();
        return lllliiiill1;
    }
}

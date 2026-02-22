package IlIIIlIlIlIII1;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import kotlin.UByte;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* loaded from: classes.dex */
public class llllIIIIll1 {

    /* renamed from: IIlIllIIll1, reason: collision with root package name */
    public static final String f104IIlIllIIll1 = "US-ASCII";

    /* renamed from: IlIlIIlIII1, reason: collision with root package name */
    public static final byte f107IlIlIIlIII1 = 61;

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public static final int f108IlIllIlllIllI1 = 32;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public static final int f109IlIlllIIlI1 = 16;

    /* renamed from: IlIllll1, reason: collision with root package name */
    public static final byte f110IlIllll1 = -5;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public static final int f111IllIIlIIII1 = 8;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public static final int f112lIIIIlllllIlll1 = 1;

    /* renamed from: lIllIIIlIl1, reason: collision with root package name */
    public static final byte f113lIllIIIlIl1 = 10;

    /* renamed from: llIIllIl1, reason: collision with root package name */
    public static final /* synthetic */ boolean f116llIIllIl1 = true;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public static final int f117llllIIIIll1 = 0;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public static final int f118llllIllIl1 = 2;

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public static final int f120llllllIlIIIlll1 = 76;

    /* renamed from: lllllIllIl1, reason: collision with root package name */
    public static final byte[] f119lllllIllIl1 = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    /* renamed from: IlIIlllllI1, reason: collision with root package name */
    public static final byte[] f105IlIIlllllI1 = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: llIIIIlIlllIII1, reason: collision with root package name */
    public static final byte[] f115llIIIIlIlllIII1 = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};

    /* renamed from: lIllIlIll1, reason: collision with root package name */
    public static final byte[] f114lIllIlIll1 = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: IlIlIIIlIlIlll1, reason: collision with root package name */
    public static final byte[] f106IlIlIIIlIlIlll1 = {45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122};

    /* renamed from: IIIlIllIlI1, reason: collision with root package name */
    public static final byte[] f103IIIlIllIlI1 = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: IlIIIlIlIlIII1.llllIIIIll1$llllIIIIll1, reason: collision with other inner class name */
    public static class C0001llllIIIIll1 extends FilterOutputStream {

        /* renamed from: IIlIllIIll1, reason: collision with root package name */
        public byte[] f121IIlIllIIll1;

        /* renamed from: IlIlIIlIII1, reason: collision with root package name */
        public boolean f122IlIlIIlIII1;

        /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
        public boolean f123IlIllIlllIllI1;

        /* renamed from: IlIlllIIlI1, reason: collision with root package name */
        public int f124IlIlllIIlI1;

        /* renamed from: IllIIlIIII1, reason: collision with root package name */
        public int f125IllIIlIIII1;

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public int f126lIIIIlllllIlll1;

        /* renamed from: lIllIIIlIl1, reason: collision with root package name */
        public int f127lIllIIIlIl1;

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public boolean f128llllIIIIll1;

        /* renamed from: llllIllIl1, reason: collision with root package name */
        public byte[] f129llllIllIl1;

        /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
        public byte[] f130llllllIlIIIlll1;

        public C0001llllIIIIll1(OutputStream outputStream) {
            this(outputStream, 1);
        }

        public void IllIIlIIII1() throws IOException {
            int i = this.f126lIIIIlllllIlll1;
            if (i > 0) {
                if (!this.f128llllIIIIll1) {
                    throw new IOException("Base64 input not properly padded.");
                }
                ((FilterOutputStream) this).out.write(llllIIIIll1.llllIIIIll1(this.f130llllllIlIIIlll1, this.f129llllIllIl1, i, this.f127lIllIIIlIl1));
                this.f126lIIIIlllllIlll1 = 0;
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            IllIIlIIII1();
            super.close();
            this.f129llllIllIl1 = null;
            ((FilterOutputStream) this).out = null;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i) throws IOException {
            if (this.f122IlIlIIlIII1) {
                ((FilterOutputStream) this).out.write(i);
                return;
            }
            if (!this.f128llllIIIIll1) {
                byte b = this.f121IIlIllIIll1[i & WorkQueueKt.MASK];
                if (b <= -5) {
                    if (b != -5) {
                        throw new IOException("Invalid character in Base64 data.");
                    }
                    return;
                }
                byte[] bArr = this.f129llllIllIl1;
                int i2 = this.f126lIIIIlllllIlll1;
                int i3 = i2 + 1;
                this.f126lIIIIlllllIlll1 = i3;
                bArr[i2] = (byte) i;
                if (i3 >= this.f125IllIIlIIII1) {
                    ((FilterOutputStream) this).out.write(this.f130llllllIlIIIlll1, 0, llllIIIIll1.lIIIIlllllIlll1(bArr, 0, this.f130llllllIlIIIlll1, 0, this.f127lIllIIIlIl1));
                    this.f126lIIIIlllllIlll1 = 0;
                    return;
                }
                return;
            }
            byte[] bArr2 = this.f129llllIllIl1;
            int i4 = this.f126lIIIIlllllIlll1;
            int i5 = i4 + 1;
            this.f126lIIIIlllllIlll1 = i5;
            bArr2[i4] = (byte) i;
            int i6 = this.f125IllIIlIIII1;
            if (i5 >= i6) {
                ((FilterOutputStream) this).out.write(llllIIIIll1.llllIIIIll1(this.f130llllllIlIIIlll1, bArr2, i6, this.f127lIllIIIlIl1));
                int i7 = this.f124IlIlllIIlI1 + 4;
                this.f124IlIlllIIlI1 = i7;
                if (this.f123IlIllIlllIllI1 && i7 >= 76) {
                    ((FilterOutputStream) this).out.write(10);
                    this.f124IlIlllIIlI1 = 0;
                }
                this.f126lIIIIlllllIlll1 = 0;
            }
        }

        public C0001llllIIIIll1(OutputStream outputStream, int i) {
            super(outputStream);
            this.f123IlIllIlllIllI1 = (i & 8) != 0;
            boolean z = (i & 1) != 0;
            this.f128llllIIIIll1 = z;
            int i2 = z ? 3 : 4;
            this.f125IllIIlIIII1 = i2;
            this.f129llllIllIl1 = new byte[i2];
            this.f126lIIIIlllllIlll1 = 0;
            this.f124IlIlllIIlI1 = 0;
            this.f122IlIlIIlIII1 = false;
            this.f130llllllIlIIIlll1 = new byte[4];
            this.f127lIllIIIlIl1 = i;
            this.f121IIlIllIIll1 = llllIIIIll1.llllIllIl1(i);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (this.f122IlIlIIlIII1) {
                ((FilterOutputStream) this).out.write(bArr, i, i2);
                return;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                write(bArr[i + i3]);
            }
        }
    }

    public static final byte[] lIIIIlllllIlll1(int i) {
        return (i & 16) == 16 ? f115llIIIIlIlllIII1 : (i & 32) == 32 ? f106IlIlIIIlIlIlll1 : f119lllllIllIl1;
    }

    public static final byte[] llllIllIl1(int i) {
        return (i & 16) == 16 ? f114lIllIlIll1 : (i & 32) == 32 ? f103IIIlIllIlI1 : f105IlIIlllllI1;
    }

    public static byte[] llllIIIIll1(byte[] bArr, byte[] bArr2, int i, int i2) {
        llllIIIIll1(bArr2, 0, i, bArr, 0, i2);
        return bArr;
    }

    public static byte[] llllIIIIll1(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        byte[] lIIIIlllllIlll12 = lIIIIlllllIlll1(i4);
        int i5 = (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0) | (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0) | (i2 > 2 ? (bArr[i + 2] << 24) >>> 24 : 0);
        if (i2 == 1) {
            bArr2[i3] = lIIIIlllllIlll12[i5 >>> 18];
            bArr2[i3 + 1] = lIIIIlllllIlll12[(i5 >>> 12) & 63];
            bArr2[i3 + 2] = 61;
            bArr2[i3 + 3] = 61;
            return bArr2;
        }
        if (i2 == 2) {
            bArr2[i3] = lIIIIlllllIlll12[i5 >>> 18];
            bArr2[i3 + 1] = lIIIIlllllIlll12[(i5 >>> 12) & 63];
            bArr2[i3 + 2] = lIIIIlllllIlll12[(i5 >>> 6) & 63];
            bArr2[i3 + 3] = 61;
            return bArr2;
        }
        if (i2 != 3) {
            return bArr2;
        }
        bArr2[i3] = lIIIIlllllIlll12[i5 >>> 18];
        bArr2[i3 + 1] = lIIIIlllllIlll12[(i5 >>> 12) & 63];
        bArr2[i3 + 2] = lIIIIlllllIlll12[(i5 >>> 6) & 63];
        bArr2[i3 + 3] = lIIIIlllllIlll12[i5 & 63];
        return bArr2;
    }

    public static byte[] lIIIIlllllIlll1(byte[] bArr, byte[] bArr2, int i, int i2) {
        llllIIIIll1(bArr2, 0, i, bArr, 0, i2);
        return bArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0064 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x005f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x005a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11, types: [java.io.OutputStream, java.util.zip.GZIPOutputStream] */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] lIIIIlllllIlll1(byte[] r18, int r19, int r20, int r21) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 291
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: IlIIIlIlIlIII1.llllIIIIll1.lIIIIlllllIlll1(byte[], int, int, int):byte[]");
    }

    public static String llllIIIIll1(byte[] bArr) {
        String str;
        try {
            str = llllIIIIll1(bArr, 0, bArr.length, 0);
        } catch (IOException e) {
            if (!f116llIIllIl1) {
                throw new AssertionError(e.getMessage());
            }
            str = null;
        }
        if (f116llIIllIl1 || str != null) {
            return str;
        }
        throw new AssertionError();
    }

    public static String llllIIIIll1(byte[] bArr, int i, int i2, int i3) throws IOException {
        byte[] lIIIIlllllIlll12 = lIIIIlllllIlll1(bArr, i, i2, i3);
        try {
            return new String(lIIIIlllllIlll12, f104IIlIllIIll1);
        } catch (UnsupportedEncodingException unused) {
            return new String(lIIIIlllllIlll12);
        }
    }

    public static int lIIIIlllllIlll1(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        int i4;
        int i5;
        if (bArr == null) {
            throw new IllegalArgumentException("Source array was null.");
        }
        if (bArr2 != null) {
            if (i >= 0 && (i4 = i + 3) < bArr.length) {
                if (i2 >= 0 && (i5 = i2 + 2) < bArr2.length) {
                    byte[] llllIllIl12 = llllIllIl1(i3);
                    byte b = bArr[i + 2];
                    if (b == 61) {
                        bArr2[i2] = (byte) ((((llllIllIl12[bArr[i + 1]] & UByte.MAX_VALUE) << 12) | ((llllIllIl12[bArr[i]] & UByte.MAX_VALUE) << 18)) >>> 16);
                        return 1;
                    }
                    byte b2 = bArr[i4];
                    if (b2 == 61) {
                        int i6 = ((llllIllIl12[bArr[i + 1]] & UByte.MAX_VALUE) << 12) | ((llllIllIl12[bArr[i]] & UByte.MAX_VALUE) << 18) | ((llllIllIl12[b] & UByte.MAX_VALUE) << 6);
                        bArr2[i2] = (byte) (i6 >>> 16);
                        bArr2[i2 + 1] = (byte) (i6 >>> 8);
                        return 2;
                    }
                    int i7 = ((llllIllIl12[bArr[i + 1]] & UByte.MAX_VALUE) << 12) | ((llllIllIl12[bArr[i]] & UByte.MAX_VALUE) << 18) | ((llllIllIl12[b] & UByte.MAX_VALUE) << 6) | (llllIllIl12[b2] & UByte.MAX_VALUE);
                    bArr2[i2] = (byte) (i7 >> 16);
                    bArr2[i2 + 1] = (byte) (i7 >> 8);
                    bArr2[i5] = (byte) i7;
                    return 3;
                }
                throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", Integer.valueOf(bArr2.length), Integer.valueOf(i2)));
            }
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", Integer.valueOf(bArr.length), Integer.valueOf(i)));
        }
        throw new IllegalArgumentException("Destination array was null.");
    }
}

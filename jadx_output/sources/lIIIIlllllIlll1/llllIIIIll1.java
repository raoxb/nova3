package lIIIIlllllIlll1;

import kotlin.UByte;

/* loaded from: classes.dex */
public final class llllIIIIll1 {

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public static final int f334IlIlllIIlI1 = 8;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public static final int f335IllIIlIIII1 = 4;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public static final int f336lIIIIlllllIlll1 = 1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public static final int f337llllIIIIll1 = 0;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public static final int f338llllIllIl1 = 2;

    public static class IllIIlIIII1 extends lIIIIlllllIlll1 {

        /* renamed from: IIlIllIIll1, reason: collision with root package name */
        public static final int f339IIlIllIIll1 = 19;

        /* renamed from: IlIllll1, reason: collision with root package name */
        public static final byte[] f340IlIllll1 = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

        /* renamed from: lllllIllIl1, reason: collision with root package name */
        public static final byte[] f341lllllIllIl1 = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};

        /* renamed from: IlIlIIlIII1, reason: collision with root package name */
        public final boolean f342IlIlIIlIII1;

        /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
        public final boolean f343IlIllIlllIllI1;

        /* renamed from: IlIlllIIlI1, reason: collision with root package name */
        public int f344IlIlllIIlI1;

        /* renamed from: IllIIlIIII1, reason: collision with root package name */
        public int f345IllIIlIIII1;

        /* renamed from: lIllIIIlIl1, reason: collision with root package name */
        public final byte[] f346lIllIIIlIl1;

        /* renamed from: llllIllIl1, reason: collision with root package name */
        public final byte[] f347llllIllIl1;

        /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
        public final boolean f348llllllIlIIIlll1;

        @Override // lIIIIlllllIlll1.llllIIIIll1.lIIIIlllllIlll1
        public int llllIIIIll1(int i) {
            return ((i * 8) / 5) + 10;
        }

        public IllIIlIIII1(int i, byte[] bArr) {
            this.f350llllIIIIll1 = bArr;
            this.f343IlIllIlllIllI1 = (i & 1) == 0;
            boolean z = (i & 2) == 0;
            this.f348llllllIlIIIlll1 = z;
            this.f342IlIlIIlIII1 = (i & 4) != 0;
            this.f346lIllIIIlIl1 = (i & 8) == 0 ? f340IlIllll1 : f341lllllIllIl1;
            this.f347llllIllIl1 = new byte[2];
            this.f345IllIIlIIII1 = 0;
            this.f344IlIlllIIlI1 = z ? 19 : -1;
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        /* JADX WARN: Removed duplicated region for block: B:19:0x0091  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00e3 A[SYNTHETIC] */
        @Override // lIIIIlllllIlll1.llllIIIIll1.lIIIIlllllIlll1
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean llllIIIIll1(byte[] r19, int r20, int r21, boolean r22) {
            /*
                Method dump skipped, instructions count: 472
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: lIIIIlllllIlll1.llllIIIIll1.IllIIlIIII1.llllIIIIll1(byte[], int, int, boolean):boolean");
        }
    }

    public static abstract class lIIIIlllllIlll1 {

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public int f349lIIIIlllllIlll1;

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public byte[] f350llllIIIIll1;

        public abstract int llllIIIIll1(int i);

        public abstract boolean llllIIIIll1(byte[] bArr, int i, int i2, boolean z);
    }

    public static class llllIllIl1 extends lIIIIlllllIlll1 {

        /* renamed from: IlIlIIlIII1, reason: collision with root package name */
        public static final int f351IlIlIIlIII1 = -1;

        /* renamed from: lIllIIIlIl1, reason: collision with root package name */
        public static final int f353lIllIIIlIl1 = -2;

        /* renamed from: IlIlllIIlI1, reason: collision with root package name */
        public final int[] f355IlIlllIIlI1;

        /* renamed from: IllIIlIIII1, reason: collision with root package name */
        public int f356IllIIlIIII1;

        /* renamed from: llllIllIl1, reason: collision with root package name */
        public int f357llllIllIl1;

        /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
        public static final int[] f352IlIllIlllIllI1 = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

        /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
        public static final int[] f354llllllIlIIIlll1 = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

        @Override // lIIIIlllllIlll1.llllIIIIll1.lIIIIlllllIlll1
        public int llllIIIIll1(int i) {
            return ((i * 3) / 4) + 10;
        }

        public llllIllIl1(int i, byte[] bArr) {
            this.f350llllIIIIll1 = bArr;
            this.f355IlIlllIIlI1 = (i & 8) == 0 ? f352IlIllIlllIllI1 : f354llllllIlIIIlll1;
            this.f357llllIllIl1 = 0;
            this.f356IllIIlIIII1 = 0;
        }

        @Override // lIIIIlllllIlll1.llllIIIIll1.lIIIIlllllIlll1
        public boolean llllIIIIll1(byte[] bArr, int i, int i2, boolean z) {
            int i3 = this.f357llllIllIl1;
            if (i3 == 6) {
                return false;
            }
            int i4 = i2 + i;
            int i5 = this.f356IllIIlIIII1;
            byte[] bArr2 = this.f350llllIIIIll1;
            int[] iArr = this.f355IlIlllIIlI1;
            int i6 = 0;
            int i7 = i5;
            int i8 = i3;
            int i9 = i;
            while (i9 < i4) {
                if (i8 == 0) {
                    while (true) {
                        int i10 = i9 + 4;
                        if (i10 > i4 || (i7 = (iArr[bArr[i9] & UByte.MAX_VALUE] << 18) | (iArr[bArr[i9 + 1] & UByte.MAX_VALUE] << 12) | (iArr[bArr[i9 + 2] & UByte.MAX_VALUE] << 6) | iArr[bArr[i9 + 3] & UByte.MAX_VALUE]) < 0) {
                            break;
                        }
                        bArr2[i6 + 2] = (byte) i7;
                        bArr2[i6 + 1] = (byte) (i7 >> 8);
                        bArr2[i6] = (byte) (i7 >> 16);
                        i6 += 3;
                        i9 = i10;
                    }
                    if (i9 >= i4) {
                        break;
                    }
                }
                int i11 = i9 + 1;
                int i12 = iArr[bArr[i9] & UByte.MAX_VALUE];
                if (i8 != 0) {
                    if (i8 == 1) {
                        if (i12 < 0) {
                            if (i12 != -1) {
                                this.f357llllIllIl1 = 6;
                                return false;
                            }
                        }
                        i7 = (i7 << 6) | i12;
                    } else if (i8 == 2) {
                        if (i12 < 0) {
                            if (i12 == -2) {
                                bArr2[i6] = (byte) (i7 >> 4);
                                i6++;
                                i8 = 4;
                            } else if (i12 != -1) {
                                this.f357llllIllIl1 = 6;
                                return false;
                            }
                        }
                        i7 = (i7 << 6) | i12;
                    } else if (i8 != 3) {
                        if (i8 != 4) {
                            if (i8 == 5 && i12 != -1) {
                                this.f357llllIllIl1 = 6;
                                return false;
                            }
                        } else if (i12 != -2) {
                            if (i12 != -1) {
                                this.f357llllIllIl1 = 6;
                                return false;
                            }
                        }
                    } else if (i12 >= 0) {
                        i7 = (i7 << 6) | i12;
                        bArr2[i6 + 2] = (byte) i7;
                        bArr2[i6 + 1] = (byte) (i7 >> 8);
                        bArr2[i6] = (byte) (i7 >> 16);
                        i6 += 3;
                        i8 = 0;
                    } else if (i12 == -2) {
                        bArr2[i6 + 1] = (byte) (i7 >> 2);
                        bArr2[i6] = (byte) (i7 >> 10);
                        i6 += 2;
                        i8 = 5;
                    } else if (i12 != -1) {
                        this.f357llllIllIl1 = 6;
                        return false;
                    }
                    i8++;
                } else if (i12 >= 0) {
                    i8++;
                    i7 = i12;
                } else if (i12 != -1) {
                    this.f357llllIllIl1 = 6;
                    return false;
                }
                i9 = i11;
            }
            if (!z) {
                this.f357llllIllIl1 = i8;
                this.f356IllIIlIIII1 = i7;
                this.f349lIIIIlllllIlll1 = i6;
                return true;
            }
            if (i8 == 1) {
                this.f357llllIllIl1 = 6;
                return false;
            }
            if (i8 == 2) {
                bArr2[i6] = (byte) (i7 >> 4);
                i6++;
            } else if (i8 == 3) {
                int i13 = i6 + 1;
                bArr2[i6] = (byte) (i7 >> 10);
                i6 += 2;
                bArr2[i13] = (byte) (i7 >> 2);
            } else if (i8 == 4) {
                this.f357llllIllIl1 = 6;
                return false;
            }
            this.f357llllIllIl1 = i8;
            this.f349lIIIIlllllIlll1 = i6;
            return true;
        }
    }

    public static byte[] lIIIIlllllIlll1(String str, int i) {
        byte[] bytes = str.getBytes();
        return lIIIIlllllIlll1(bytes, 0, bytes.length, i);
    }

    public static byte[] llllIIIIll1(String str, int i) {
        byte[] bytes = str.getBytes();
        return llllIIIIll1(bytes, 0, bytes.length, i);
    }

    public static byte[] lIIIIlllllIlll1(byte[] bArr, int i) {
        return lIIIIlllllIlll1(bArr, 0, bArr.length, i);
    }

    public static byte[] llllIIIIll1(byte[] bArr, int i) {
        return llllIIIIll1(bArr, 0, bArr.length, i);
    }

    public static byte[] lIIIIlllllIlll1(byte[] bArr, int i, int i2, int i3) {
        IllIIlIIII1 illIIlIIII1 = new IllIIlIIII1(i3, null);
        int i4 = (i2 / 3) * 4;
        if (illIIlIIII1.f343IlIllIlllIllI1) {
            if (i2 % 3 > 0) {
                i4 += 4;
            }
        } else {
            int i5 = i2 % 3;
            if (i5 == 1) {
                i4 += 2;
            } else if (i5 == 2) {
                i4 += 3;
            }
        }
        if (illIIlIIII1.f348llllllIlIIIlll1 && i2 > 0) {
            i4 += (((i2 - 1) / 57) + 1) * (illIIlIIII1.f342IlIlIIlIII1 ? 2 : 1);
        }
        illIIlIIII1.f350llllIIIIll1 = new byte[i4];
        illIIlIIII1.llllIIIIll1(bArr, i, i2, true);
        return illIIlIIII1.f350llllIIIIll1;
    }

    public static byte[] llllIIIIll1(byte[] bArr, int i, int i2, int i3) {
        llllIllIl1 llllillil1 = new llllIllIl1(i3, new byte[(i2 * 3) / 4]);
        if (llllillil1.llllIIIIll1(bArr, i, i2, true)) {
            int i4 = llllillil1.f349lIIIIlllllIlll1;
            byte[] bArr2 = llllillil1.f350llllIIIIll1;
            if (i4 == bArr2.length) {
                return bArr2;
            }
            byte[] bArr3 = new byte[i4];
            System.arraycopy(bArr2, 0, bArr3, 0, i4);
            return bArr3;
        }
        throw new IllegalArgumentException(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-125, -111, -85, -104, 92, 122, 100, 46, -52, -58, -5}, new byte[]{-31, -16, -49, -72, 62, 27, 23, 75}));
    }
}

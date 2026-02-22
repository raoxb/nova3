package IIlllllIlll1;

import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class lIIIIlllllIlll1 implements llllIIIIll1 {

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public static final Pattern f68lIIIIlllllIlll1 = Pattern.compile(" ");

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public static final Pattern f69llllIllIl1 = Pattern.compile(",");

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final String f70llllIIIIll1;

    public lIIIIlllllIlll1(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        this.f70llllIIIIll1 = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f70llllIIIIll1.equals(((lIIIIlllllIlll1) obj).f70llllIIIIll1);
    }

    public int hashCode() {
        return this.f70llllIIIIll1.hashCode();
    }

    @Override // IIlllllIlll1.llllIIIIll1
    public String lIIIIlllllIlll1() {
        return this.f70llllIIIIll1;
    }

    @Override // IIlllllIlll1.llllIIIIll1
    public boolean llllIIIIll1(String str) {
        if ("".equals(this.f70llllIIIIll1)) {
            return true;
        }
        for (String str2 : f69llllIllIl1.split(f68lIIIIlllllIlll1.matcher(str).replaceAll(""))) {
            if (this.f70llllIIIIll1.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    @Override // IIlllllIlll1.llllIIIIll1
    public String toString() {
        return lIIIIlllllIlll1();
    }

    @Override // IIlllllIlll1.llllIIIIll1
    public llllIIIIll1 llllIIIIll1() {
        return new lIIIIlllllIlll1(lIIIIlllllIlll1());
    }
}

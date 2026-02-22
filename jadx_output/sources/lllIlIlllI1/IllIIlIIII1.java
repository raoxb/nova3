package lllIlIlllI1;

import androidx.webkit.ProxyConfig;

/* loaded from: classes.dex */
public class IllIIlIIII1 extends llllllIlIIIlll1 implements lIIIIlllllIlll1 {

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public String f699llllIllIl1 = ProxyConfig.MATCH_ALL_SCHEMES;

    @Override // lllIlIlllI1.lIIIIlllllIlll1
    public void lIIIIlllllIlll1(String str) {
        if (str == null) {
            throw new IllegalArgumentException("http resource descriptor must not be null");
        }
        this.f699llllIllIl1 = str;
    }

    @Override // lllIlIlllI1.llllIIIIll1
    public String llllIIIIll1() {
        return this.f699llllIllIl1;
    }
}

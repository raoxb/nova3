package lIIlllIIIlllII1;

import IlIIIIllllIlI1.IlIllIlllIllI1;
import IlIIIIllllIlI1.IlIlllIIlI1;

/* loaded from: classes.dex */
public abstract class llllIIIIll1 extends lIIIIlllllIlll1 {
    @Override // lIIlllIIIlllII1.lIIIIlllllIlll1, lIIlllIIIlllII1.IllIIlIIII1
    public void llllIllIl1(IlIllIlllIllI1 ilIllIlllIllI1) throws IllIlIllll1.llllIllIl1 {
        if ((ilIllIlllIllI1 instanceof IlIlllIIlI1) && (ilIllIlllIllI1.lIIIIlllllIlll1() || ilIllIlllIllI1.IlIllIlllIllI1())) {
            throw new IllIlIllll1.IlIlllIIlI1("bad rsv RSV1: " + ilIllIlllIllI1.IlIlllIIlI1() + " RSV2: " + ilIllIlllIllI1.lIIIIlllllIlll1() + " RSV3: " + ilIllIlllIllI1.IlIllIlllIllI1());
        }
        if (ilIllIlllIllI1 instanceof IlIIIIllllIlI1.IllIIlIIII1) {
            if (ilIllIlllIllI1.IlIlllIIlI1() || ilIllIlllIllI1.lIIIIlllllIlll1() || ilIllIlllIllI1.IlIllIlllIllI1()) {
                throw new IllIlIllll1.IlIlllIIlI1("bad rsv RSV1: " + ilIllIlllIllI1.IlIlllIIlI1() + " RSV2: " + ilIllIlllIllI1.lIIIIlllllIlll1() + " RSV3: " + ilIllIlllIllI1.IlIllIlllIllI1());
            }
        }
    }
}

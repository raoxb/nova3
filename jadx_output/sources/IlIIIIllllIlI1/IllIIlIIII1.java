package IlIIIIllllIlI1;

/* loaded from: classes.dex */
public abstract class IllIIlIIII1 extends llllllIlIIIlll1 {
    public IllIIlIIII1(lIIlIIIIlIlII1.llllIllIl1 llllillil1) {
        super(llllillil1);
    }

    @Override // IlIIIIllllIlI1.llllllIlIIIlll1
    public void IlIlIIlIII1() throws IllIlIllll1.llllIllIl1 {
        if (!llllllIlIIIlll1()) {
            throw new IllIlIllll1.IlIlllIIlI1("Control frame can't have fin==false set");
        }
        if (IlIlllIIlI1()) {
            throw new IllIlIllll1.IlIlllIIlI1("Control frame can't have rsv1==true set");
        }
        if (lIIIIlllllIlll1()) {
            throw new IllIlIllll1.IlIlllIIlI1("Control frame can't have rsv2==true set");
        }
        if (IlIllIlllIllI1()) {
            throw new IllIlIllll1.IlIlllIIlI1("Control frame can't have rsv3==true set");
        }
    }
}

package IIlIIllll1;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;
import llIIllIl1.IlIllIlllIllI1;
import llIIllIl1.IlIllll1;
import llIIllIl1.lIllIIIlIl1;
import llIIllIl1.llllllIlIIIlll1;

/* loaded from: classes.dex */
public class llllIllIl1 implements IlIllll1 {
    @Override // llIIllIl1.IlIllll1
    public void close() {
    }

    @Override // llIIllIl1.IlIllll1
    /* renamed from: lIIIIlllllIlll1, reason: merged with bridge method [inline-methods] */
    public SocketChannel llllIIIIll1(SocketChannel socketChannel, SelectionKey selectionKey) {
        return socketChannel;
    }

    @Override // llIIllIl1.IlIllll1, llIIllIl1.IlIlIIlIII1
    public /* bridge */ /* synthetic */ IlIllIlllIllI1 llllIIIIll1(llllllIlIIIlll1 lllllliliiilll1, List list) {
        return llllIIIIll1(lllllliliiilll1, (List<lIlllIIIII1.llllIIIIll1>) list);
    }

    @Override // llIIllIl1.IlIllll1, llIIllIl1.IlIlIIlIII1
    public lIllIIIlIl1 llllIIIIll1(llllllIlIIIlll1 lllllliliiilll1, lIlllIIIII1.llllIIIIll1 lllliiiill1) {
        return new lIllIIIlIl1(lllllliliiilll1, lllliiiill1);
    }

    @Override // llIIllIl1.IlIllll1, llIIllIl1.IlIlIIlIII1
    public lIllIIIlIl1 llllIIIIll1(llllllIlIIIlll1 lllllliliiilll1, List<lIlllIIIII1.llllIIIIll1> list) {
        return new lIllIIIlIl1(lllllliliiilll1, list);
    }
}

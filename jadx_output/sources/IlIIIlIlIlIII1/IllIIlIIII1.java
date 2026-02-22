package IlIIIlIlIlIII1;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class IllIIlIIII1 implements ThreadFactory {

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public final boolean f99IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final AtomicInteger f100lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final ThreadFactory f101llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final String f102llllIllIl1;

    public IllIIlIIII1(String str) {
        this.f101llllIIIIll1 = Executors.defaultThreadFactory();
        this.f100lIIIIlllllIlll1 = new AtomicInteger(1);
        this.f102llllIllIl1 = str;
        this.f99IllIIlIIII1 = false;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread newThread = this.f101llllIIIIll1.newThread(runnable);
        newThread.setDaemon(this.f99IllIIlIIII1);
        newThread.setName(this.f102llllIllIl1 + "-" + this.f100lIIIIlllllIlll1);
        return newThread;
    }

    public IllIIlIIII1(String str, boolean z) {
        this.f101llllIIIIll1 = Executors.defaultThreadFactory();
        this.f100lIIIIlllllIlll1 = new AtomicInteger(1);
        this.f102llllIllIl1 = str;
        this.f99IllIIlIIII1 = z;
    }
}

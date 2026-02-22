package lIllIlIll1;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.webkit.WebView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import c13.nim5.ez8.h5_proto.Log;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes.dex */
public class lIIIIlllllIlll1 implements Runnable {

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public final PointF f482IlIlllIIlI1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public final long f483IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final PointF f484lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final WebView f485llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final PointF f486llllIllIl1;

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public final Random f481IlIllIlllIllI1 = new Random(SystemClock.elapsedRealtime());

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public boolean f487llllllIlIIIlll1 = true;

    public class llllIIIIll1 implements Runnable {

        /* renamed from: IllIIlIIII1, reason: collision with root package name */
        public final /* synthetic */ float f489IllIIlIIII1;

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public final /* synthetic */ int f490lIIIIlllllIlll1;

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ AtomicReference f491llllIIIIll1;

        /* renamed from: llllIllIl1, reason: collision with root package name */
        public final /* synthetic */ float f492llllIllIl1;

        public llllIIIIll1(AtomicReference atomicReference, int i, float f, float f2) {
            this.f491llllIIIIll1 = atomicReference;
            this.f490lIIIIlllllIlll1 = i;
            this.f492llllIllIl1 = f;
            this.f489IllIIlIIII1 = f2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                this.f491llllIIIIll1.set(MotionEvent.obtain(currentTimeMillis, 2 + currentTimeMillis, this.f490lIIIIlllllIlll1, this.f492llllIllIl1, this.f489IllIIlIIII1, 0));
                lIIIIlllllIlll1.this.f485llllIIIIll1.dispatchTouchEvent((MotionEvent) this.f491llllIIIIll1.get());
            } catch (Exception e) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-63, -7, 95, -42, -108, -85, -99, -85, -15, -1, 89, -59, -99, -102, -120, -90, -53, -28, 12, -55, -106, -68, -117, -79, -123, -11, 94, -44, -102, -83, -60, -29}, new byte[]{-91, -112, 44, -90, -11, -33, -2, -61}))));
            }
        }
    }

    public lIIIIlllllIlll1(WebView webView, PointF pointF, PointF pointF2, long j) {
        this.f485llllIIIIll1 = webView;
        this.f484lIIIIlllllIlll1 = pointF;
        this.f486llllIllIl1 = pointF2;
        this.f483IllIIlIIII1 = j;
        this.f482IlIlllIIlI1 = new PointF(((r0.nextInt(TypedValues.TYPE_TARGET) / 100.0f) * (pointF2.x - pointF.x)) + pointF.x, ((r0.nextInt(TypedValues.TYPE_TARGET) / 100.0f) * (pointF2.y - pointF.y)) + pointF.y);
    }

    @Override // java.lang.Runnable
    public void run() {
        int i;
        String str;
        long j;
        char c;
        int i2;
        this.f481IlIllIlllIllI1.setSeed(SystemClock.elapsedRealtime());
        AccelerateDecelerateInterpolator accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
        Path path = new Path();
        PointF pointF = this.f484lIIIIlllllIlll1;
        path.rMoveTo(pointF.x, pointF.y);
        PointF pointF2 = this.f482IlIlllIIlI1;
        float f = pointF2.x;
        float f2 = pointF2.y;
        PointF pointF3 = this.f486llllIllIl1;
        path.quadTo(f, f2, pointF3.x, pointF3.y);
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        float[] fArr = new float[2];
        float[] fArr2 = new float[2];
        AtomicReference atomicReference = new AtomicReference();
        float f3 = 1.0f;
        int ceil = (int) Math.ceil((this.f483IllIIlIIII1 * 1.0f) / 20);
        StringBuilder sb = new StringBuilder();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        String sb2 = sb.append(lllliiiill1.llllIIIIll1(new byte[]{-86, 51, -93, -74, -41, -64, -34, 15}, new byte[]{-15, 71, -54, -37, -78, -77, -125, 53})).append(ceil).append(lllliiiill1.llllIIIIll1(new byte[]{-43, 20, 60, 20, -20, 10, -42, 90, -102, 33, 5, 91}, new byte[]{-11, 79, 88, 97, -98, 107, -94, 51})).append(this.f483IllIIlIIII1).toString();
        String str2 = "";
        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.DEBUG, "", sb2);
        long j2 = 0;
        int i3 = 0;
        while (i3 < ceil) {
            int i4 = ceil;
            float interpolation = accelerateDecelerateInterpolator.getInterpolation((j2 * f3) / this.f483IllIIlIIII1) * length;
            boolean posTan = pathMeasure.getPosTan(interpolation, fArr, fArr2);
            StringBuilder sb3 = new StringBuilder();
            long j3 = j2;
            IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.DEBUG, str2, sb3.append(lllliiiill12.llllIIIIll1(new byte[]{27, 104, -46, -97, -37, 39}, new byte[]{64, 24, -67, -20, -122, 29, -46, 71})).append(fArr[0]).append(lllliiiill12.llllIIIIll1(new byte[]{-89, 86, -45}, new byte[]{-121, 46, -13, 103, -4, -3, -93, 83})).append(fArr[1]).append(lllliiiill12.llllIIIIll1(new byte[]{74, 21, -47, 21, 92, 126, -120}, new byte[]{102, 53, -73, 121, 61, 25, -78, 104})).append(posTan).append(lllliiiill12.llllIIIIll1(new byte[]{48, -53, 107, -98, -60, 123, 29, -91, ByteCompanionObject.MAX_VALUE, -114, 53, -41}, new byte[]{28, -21, 15, -9, -73, 15, 124, -53})).append(interpolation).toString());
            if (posTan) {
                if (i3 == 0) {
                    c = 0;
                    i2 = 0;
                } else {
                    c = 0;
                    i2 = i3 == i4 + (-1) ? 1 : 2;
                }
                i = i3;
                j = 20;
                str = str2;
                IlIlllIIlI1.lIIIIlllllIlll1.llllIIIIll1(new llllIIIIll1(atomicReference, i2, fArr[c], fArr[1]));
            } else {
                i = i3;
                str = str2;
                j = 20;
            }
            j2 = j3 + j;
            if (i < i4 - 1) {
                SystemClock.sleep(j);
            }
            i3 = i + 1;
            str2 = str;
            ceil = i4;
            f3 = 1.0f;
        }
        if (atomicReference.get() != null) {
            ((MotionEvent) atomicReference.get()).recycle();
        }
        this.f487llllllIlIIIlll1 = false;
    }

    public boolean llllIIIIll1() {
        return this.f487llllllIlIIIlll1;
    }
}

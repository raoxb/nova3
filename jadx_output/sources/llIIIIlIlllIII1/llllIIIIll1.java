package llIIIIlIlllIII1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.view.Surface;
import kotlin.Unit;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import org.webrtc.CapturerObserver;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoFrame;
import org.webrtc.VideoSink;

/* loaded from: classes.dex */
public class llllIIIIll1 implements VideoCapturer {

    /* renamed from: IlIlIIlIII1, reason: collision with root package name */
    public Surface f596IlIlIIlIII1;

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public int f597IlIllIlllIllI1;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public int f598IlIlllIIlI1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public int f599IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public CapturerObserver f600lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public SurfaceTextureHelper f601llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public boolean f602llllIllIl1;

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public final Object f603llllllIlIIIlll1 = new Object();

    public void changeCaptureFormat(int i, int i2, int i3) {
    }

    public void dispose() {
        synchronized (this.f603llllllIlIIIlll1) {
            if (this.f602llllIllIl1) {
                return;
            }
            stopCapture();
            Surface surface = this.f596IlIlIIlIII1;
            if (surface != null) {
                surface.release();
            }
            this.f602llllIllIl1 = true;
            Unit unit = Unit.INSTANCE;
        }
    }

    public void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, CapturerObserver capturerObserver) {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        Intrinsics.checkNotNullParameter(surfaceTextureHelper, lllliiiill1.llllIIIIll1(new byte[]{75, -30, -108, 27, -51, 115, -32, 76, 93, -17, -110, 8, -34, 117, -51, 125, 84, -25, -125, 15}, new byte[]{56, -105, -26, 125, -84, 16, -123, 24}));
        Intrinsics.checkNotNullParameter(context, lllliiiill1.llllIIIIll1(new byte[]{-97, -62, -9, 21, 0, -58, 96}, new byte[]{-4, -83, -103, 97, 101, -66, 20, -101}));
        Intrinsics.checkNotNullParameter(capturerObserver, lllliiiill1.llllIIIIll1(new byte[]{106, 122, -81, 33, 57, 71, 34, 122}, new byte[]{5, 24, -36, 68, 75, 49, 71, 8}));
        synchronized (this.f603llllllIlIIIlll1) {
            this.f601llllIIIIll1 = surfaceTextureHelper;
            this.f600lIIIIlllllIlll1 = capturerObserver;
            this.f596IlIlIIlIII1 = new Surface(surfaceTextureHelper.getSurfaceTexture());
            Unit unit = Unit.INSTANCE;
        }
    }

    public boolean isScreencast() {
        return false;
    }

    public final void llllIIIIll1(final Bitmap bitmap, int i) {
        Handler handler;
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        Intrinsics.checkNotNullParameter(bitmap, lllliiiill1.llllIIIIll1(new byte[]{46, -85, -46, 31, 28, -46}, new byte[]{76, -62, -90, 114, 125, -94, -95, -9}));
        synchronized (this.f603llllllIlIIIlll1) {
            if (this.f602llllIllIl1) {
                return;
            }
            SurfaceTextureHelper surfaceTextureHelper = this.f601llllIIIIll1;
            if (surfaceTextureHelper == null) {
                throw new IllegalStateException(lllliiiill1.llllIIIIll1(new byte[]{-59, -44, 48, 10, 13, 86, -45, 72, -73, -57, 32, 19, 17, 65, -106, 91, -10, -62, 97, 17, 17, 72, -38, 2}, new byte[]{-105, -79, 65, ByteCompanionObject.MAX_VALUE, 100, 36, -74, 44}).toString());
            }
            if (this.f596IlIlIIlIII1 == null) {
                throw new IllegalStateException(lllliiiill1.llllIIIIll1(new byte[]{87, -23, -87, 87, -1, -83, -50, 85, 37, -6, -71, 78, -29, -70, -117, 70, 100, -1, -8, 76, -29, -77, -57, 31}, new byte[]{5, -116, -40, 34, -106, -33, -85, 49}).toString());
            }
            if (this.f599IllIIlIIII1 != i) {
                if (surfaceTextureHelper != null) {
                    surfaceTextureHelper.setFrameRotation(i);
                }
                this.f599IllIIlIIII1 = i;
            }
            if (this.f598IlIlllIIlI1 != bitmap.getWidth() || this.f597IlIllIlllIllI1 != bitmap.getHeight()) {
                SurfaceTextureHelper surfaceTextureHelper2 = this.f601llllIIIIll1;
                if (surfaceTextureHelper2 != null) {
                    surfaceTextureHelper2.setTextureSize(bitmap.getWidth(), bitmap.getHeight());
                }
                this.f598IlIlllIIlI1 = bitmap.getWidth();
                this.f597IlIllIlllIllI1 = bitmap.getHeight();
            }
            SurfaceTextureHelper surfaceTextureHelper3 = this.f601llllIIIIll1;
            if (surfaceTextureHelper3 != null && (handler = surfaceTextureHelper3.getHandler()) != null) {
                handler.post(new Runnable() { // from class: llIIIIlIlllIII1.llllIIIIll1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        llllIIIIll1.llllIIIIll1(llllIIIIll1.this, bitmap);
                    }
                });
            }
        }
    }

    public void startCapture(int i, int i2, int i3) {
        synchronized (this.f603llllllIlIIIlll1) {
            llllIIIIll1();
            if (this.f601llllIIIIll1 == null) {
                throw new IllegalStateException(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-1, -121, -106, -65, -111, -11, -62, -7, -36, -125, -121, -111, -111, -11, -16, -2, -49, -117, -112, -14, -99, -16, -9, -1, -99, -116, -121, -14, -103, -21, -19, -1, -44, -113, -114, -69, -118, -32, -32, -85, -33, -117, -124, -67, -126, -32, -92, -24, -36, -126, -114, -69, -98, -30, -92, -8, -55, -113, -112, -90, -77, -28, -12, -1, -56, -100, -121, -4}, new byte[]{-67, -18, -30, -46, -16, -123, -124, -117}).toString());
            }
            CapturerObserver capturerObserver = this.f600lIIIIlllllIlll1;
            if (capturerObserver != null) {
                capturerObserver.onCapturerStarted(true);
            }
            SurfaceTextureHelper surfaceTextureHelper = this.f601llllIIIIll1;
            if (surfaceTextureHelper != null) {
                surfaceTextureHelper.startListening(new VideoSink() { // from class: llIIIIlIlllIII1.llllIIIIll1$$ExternalSyntheticLambda1
                    public final void onFrame(VideoFrame videoFrame) {
                        llllIIIIll1.llllIIIIll1(llllIIIIll1.this, videoFrame);
                    }
                });
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public void stopCapture() {
        synchronized (this.f603llllllIlIIIlll1) {
            SurfaceTextureHelper surfaceTextureHelper = this.f601llllIIIIll1;
            if (surfaceTextureHelper != null) {
                surfaceTextureHelper.stopListening();
            }
            CapturerObserver capturerObserver = this.f600lIIIIlllllIlll1;
            if (capturerObserver != null) {
                capturerObserver.onCapturerStopped();
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public final void llllIIIIll1() {
        if (this.f602llllIllIl1) {
            throw new IllegalStateException(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{125, 12, 113, 111, -32, -99, 110, -90, 30, 4, 114, 59, -15, -122, 120, -92, 81, 30, 100, ByteCompanionObject.MAX_VALUE, -69}, new byte[]{62, 109, 1, 27, -107, -17, 11, -44}).toString());
        }
    }

    public static final void llllIIIIll1(llllIIIIll1 lllliiiill1, VideoFrame videoFrame) {
        CapturerObserver capturerObserver = lllliiiill1.f600lIIIIlllllIlll1;
        if (capturerObserver != null) {
            capturerObserver.onFrameCaptured(videoFrame);
        }
    }

    public static final void llllIIIIll1(llllIIIIll1 lllliiiill1, Bitmap bitmap) {
        try {
            Surface surface = lllliiiill1.f596IlIlIIlIII1;
            Canvas lockHardwareCanvas = surface != null ? surface.lockHardwareCanvas() : null;
            if (lockHardwareCanvas != null) {
                lockHardwareCanvas.drawBitmap(bitmap, new Matrix(), new Paint());
                Surface surface2 = lllliiiill1.f596IlIlIIlIII1;
                if (surface2 != null) {
                    surface2.unlockCanvasAndPost(lockHardwareCanvas);
                }
            }
        } catch (Throwable unused) {
        }
    }
}

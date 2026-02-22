package llIIIIlIlllIII1;

import android.app.Presentation;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.PixelCopy;
import android.view.Surface;
import android.webkit.WebView;
import c13.nim5.ez8.h5_proto.Log;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.webrtc.CapturerObserver;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoFrame;
import org.webrtc.VideoSink;

/* loaded from: classes.dex */
public final class llllIllIl1 implements VideoCapturer {

    /* renamed from: IlIlIIlIII1, reason: collision with root package name */
    public Surface f604IlIlIIlIII1;

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public Surface f605IlIllIlllIllI1;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public llllIIIIll1 f606IlIlllIIlI1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public boolean f607IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public CapturerObserver f608lIIIIlllllIlll1;

    /* renamed from: lIllIIIlIl1, reason: collision with root package name */
    public final int f609lIllIIIlIl1 = 15;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public SurfaceTextureHelper f610llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public VirtualDisplay f611llllIllIl1;

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public ImageReader f612llllllIlIIIlll1;

    public static final class lIIIIlllllIlll1 implements Runnable {

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public final /* synthetic */ Handler f613lIIIIlllllIlll1;

        public lIIIIlllllIlll1(Handler handler) {
            this.f613lIIIIlllllIlll1 = handler;
        }

        @Override // java.lang.Runnable
        public void run() {
            llllIllIl1 llllillil1 = llllIllIl1.this;
            if (llllillil1.f607IllIIlIIII1) {
                return;
            }
            SurfaceTextureHelper surfaceTextureHelper = llllillil1.f610llllIIIIll1;
            if (surfaceTextureHelper != null) {
                surfaceTextureHelper.forceFrame();
            }
            this.f613lIIIIlllllIlll1.postDelayed(this, 1000 / llllIllIl1.this.f609lIllIIIlIl1);
        }
    }

    public static final class llllIIIIll1 extends Presentation {

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final WebView f615llllIIIIll1;

        public llllIIIIll1(Context context, Display display, WebView webView) {
            super(context, display);
            this.f615llllIIIIll1 = webView;
        }

        @Override // android.app.Dialog
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            WebView webView = this.f615llllIIIIll1;
            if (webView != null) {
                webView.setFocusable(true);
            }
            WebView webView2 = this.f615llllIIIIll1;
            if (webView2 != null) {
                webView2.setFocusableInTouchMode(true);
            }
            WebView.setWebContentsDebuggingEnabled(true);
            WebView webView3 = this.f615llllIIIIll1;
            Intrinsics.checkNotNull(webView3);
            setContentView(webView3);
        }
    }

    public static final void IlIlllIIlI1(llllIllIl1 llllillil1) {
        llllIIIIll1 lllliiiill1 = llllillil1.f606IlIlllIIlI1;
        if (lllliiiill1 != null) {
            lllliiiill1.dismiss();
        }
        llllillil1.f606IlIlllIIlI1 = null;
    }

    public static final void IllIIlIIII1(llllIllIl1 llllillil1) {
        try {
            SurfaceTextureHelper surfaceTextureHelper = llllillil1.f610llllIIIIll1;
            if (surfaceTextureHelper != null) {
                surfaceTextureHelper.forceFrame();
            }
            byte[] bArr = {117, ByteCompanionObject.MIN_VALUE, -100, 32, 1, -69, -57, 119};
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllliiiill1.llllIIIIll1(new byte[]{35, -23, -18, 84, 116, -38, -85, 51, 28, -13, -20, 76, 96, -62, -124, 22, 5, -12, -23, 82, 100, -55}, bArr);
            lllliiiill1.llllIIIIll1(new byte[]{-91, 36, -106, -65, -30, -53, 123, -126, 112, -14, 83, -78, -51, -38, -86, 10, -5, 122, -86, -1, -75, -13, -24, 87, -49, 14, 70, 56, 47, 55, 43, -12, 50, -2, 77, 50}, new byte[]{64, -97, 32, 87, 93, 84, 78, -78});
        } catch (Throwable unused) {
        }
    }

    public void changeCaptureFormat(int i, int i2, int i3) {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        lllliiiill1.llllIIIIll1(new byte[]{-34, 125, -107, 58, 64, 66, -34, -85, -31, 103, -105, 34, 84, 90, -15, -114, -8, 96, -110, 60, 80, 81}, new byte[]{-120, 20, -25, 78, 53, 35, -78, -17});
        lllliiiill1.llllIIIIll1(new byte[]{39, -105, 123, 103, -45, 80, 77, -107, 52, -117, 111, 123, -47, 115, 97, -122, 41, -98, 110, 51, -108}, new byte[]{68, -1, 26, 9, -76, 53, 14, -12});
        lllliiiill1.llllIIIIll1(new byte[]{63, -16, -25}, new byte[]{31, -120, -57, 100, -10, -30, 126, -34});
        lllliiiill1.llllIIIIll1(new byte[]{25, -104, -41}, new byte[]{57, -40, -9, 50, -104, -4, -37, 31});
        lllliiiill1.llllIIIIll1(new byte[]{-29, -122, 110, 3}, new byte[]{-61, -32, 30, 112, -104, -36, 46, -1});
        Context llllIllIl12 = IlIlllIIlI1.lIIIIlllllIlll1.llllIllIl1();
        Intrinsics.checkNotNull(llllIllIl12);
        DisplayMetrics displayMetrics = llllIllIl12.getResources().getDisplayMetrics();
        int i4 = displayMetrics.widthPixels;
        int i5 = displayMetrics.heightPixels;
        SurfaceTextureHelper surfaceTextureHelper = this.f610llllIIIIll1;
        if (surfaceTextureHelper != null) {
            surfaceTextureHelper.setTextureSize(i4, i5);
        }
        lllliiiill1.llllIIIIll1(new byte[]{65, -41, 27, 118, 76, -97, 55, 38, 126, -51, 25, 110, 88, -121, 24, 3, 103, -54, 28, 112, 92, -116}, new byte[]{23, -66, 105, 2, 57, -2, 91, 98});
        lllliiiill1.llllIIIIll1(new byte[]{-33, 89, -10, -28, 103, -38, -84, -44, 91, -112, 22, 65, 22, -42, 109, 86, -103, 67, -89, -118, 119, -126, 69, 25, -34, 76, -59, -26, 73, -48, 30, 0, -74, 39, -5, -105, 20, -60, 101, 88, -96, 71, -89, -90, 86, -113, 75, 62, 3, -30}, new byte[]{57, -62, 66, 2, -15, 106, -5, -79});
    }

    public void dispose() {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        lllliiiill1.llllIIIIll1(new byte[]{-37, 79, -126, ByteCompanionObject.MAX_VALUE, 54, -93, -79, -39, -28, 85, ByteCompanionObject.MIN_VALUE, 103, 34, -69, -98, -4, -3, 82, -123, 121, 38, -80}, new byte[]{-115, 38, -16, 11, 67, -62, -35, -99});
        lllliiiill1.llllIIIIll1(new byte[]{32, -100, 44, -11, -106, -57, -6, -37, 39, -108, 51, -23, -100, -48}, new byte[]{68, -11, 95, -123, -7, -76, -97, -5});
        if (this.f607IllIIlIIII1) {
            return;
        }
        stopCapture();
        this.f607IllIIlIIII1 = true;
    }

    public void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, CapturerObserver capturerObserver) {
        byte[] bArr = {0, -7, -90, -123, ByteCompanionObject.MIN_VALUE, -42, -58, -66};
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        Intrinsics.checkNotNullParameter(surfaceTextureHelper, lllliiiill1.llllIIIIll1(new byte[]{104, -100, -54, -11, -27, -92}, bArr));
        Intrinsics.checkNotNullParameter(context, lllliiiill1.llllIIIIll1(new byte[]{2, 44, 94, 17, -50, 84, -3}, new byte[]{97, 67, 48, 101, -85, 44, -119, 92}));
        Intrinsics.checkNotNullParameter(capturerObserver, lllliiiill1.llllIIIIll1(new byte[]{18, 76, 92, -96, 29, -62, 115, 98}, new byte[]{125, 46, 47, -59, 111, -76, 22, 16}));
        lllliiiill1.llllIIIIll1(new byte[]{59, 99, 62, 48, 63, 88, -16, 106, 4, 121, 60, 40, 43, 64, -33, 79, 29, 126, 57, 54, 47, 75}, new byte[]{109, 10, 76, 68, 74, 57, -100, 46});
        lllliiiill1.llllIIIIll1(new byte[]{70, 21, -91, 112, -68, 10, -80, -70, 85, 30, -20, 103, -76, 7, -80, -74, 75}, new byte[]{47, 123, -52, 4, -43, 107, -36, -45});
        this.f610llllIIIIll1 = surfaceTextureHelper;
        this.f608lIIIIlllllIlll1 = capturerObserver;
        lllliiiill1.llllIIIIll1(new byte[]{-50, 12, 116, -74, -108, -60, -82, 42, -20, 6}, new byte[]{-118, 105, 2, -33, -9, -95, -25, 68});
        StringsKt.trimIndent(lllliiiill1.llllIIIIll1(new byte[]{21, 30, 69, -73, -45, 93, -50, -46, 63, 30, 69, -73, -45, 58, -66, -89, 37, 30}, new byte[]{31, 62, 101, -105, -13, 125, -18, -14}) + GLES20.glGetString(7937) + "\n            ");
    }

    public boolean isScreencast() {
        return true;
    }

    public void startCapture(int i, int i2, int i3) {
        SurfaceTexture surfaceTexture;
        IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.getClass();
        byte[] lIIIIlllllIlll12 = IllIIlIIII1.llllIIIIll1.lIIIIlllllIlll1(new byte[]{95, 66, 45, 104, 23, -3, 117, -51, 96, 88, 47, 112, 3, -27, 90, -24, 121, 95, 42, 110, 7, -18}, new byte[]{9, 43, 95, 28, 98, -100, 25, -119});
        Charset charset = StandardCharsets.UTF_8;
        new String(lIIIIlllllIlll12, charset);
        new String(IllIIlIIII1.llllIIIIll1.lIIIIlllllIlll1(new byte[]{36, 120, -108, 116, 82, 89, -26, -89, 35, 121, -121, 99, 6, 121, -26, -69, 59, 105, -111, 60, 6}, new byte[]{87, 12, -11, 6, 38, 26, -121, -41}), charset);
        new String(IllIIlIIII1.llllIIIIll1.lIIIIlllllIlll1(new byte[]{-116, 105, -50}, new byte[]{-84, 17, -18, 31, 16, -102, -55, 39}), charset);
        new String(IllIIlIIII1.llllIIIIll1.lIIIIlllllIlll1(new byte[]{-124, 10, -53}, new byte[]{-92, 74, -21, 103, 51, 57, 88, -127}), charset);
        new String(IllIIlIIII1.llllIIIIll1.lIIIIlllllIlll1(new byte[]{-15, 6, -106, -82}, new byte[]{-47, 96, -26, -35, -58, -96, 55, -100}), charset);
        if (this.f610llllIIIIll1 == null) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, new String(IllIIlIIII1.llllIIIIll1.lIIIIlllllIlll1(new byte[]{-62, -93, -106, -45, 94, -12, -35, -78, -3, -71, -108, -53, 74, -20, -14, -105, -28, -66, -111, -43, 78, -25}, new byte[]{-108, -54, -28, -89, 43, -107, -79, -10}), charset), new String(IllIIlIIII1.llllIIIIll1.lIIIIlllllIlll1(new byte[]{125, 113, -69, -77, -50, -32, 61, 85, 52, 3, -111, -3, -101, -8, 77, 45, 21, 81, -12, -23, -25, 6, -83, -73, -3, -121, 120, 48, 41, 16, -96, -79, -18, -108, 126, 29, 24, 25, -88, -96, -23, 2, -93, -17, 19, 0, -76, -87}, new byte[]{-101, -26, 27, 85, 125, 117, -40, -59}), charset));
            CapturerObserver capturerObserver = this.f608lIIIIlllllIlll1;
            if (capturerObserver != null) {
                capturerObserver.onCapturerStarted(false);
                return;
            }
            return;
        }
        Context llllIllIl12 = IlIlllIIlI1.lIIIIlllllIlll1.llllIllIl1();
        Intrinsics.checkNotNull(llllIllIl12);
        DisplayMetrics displayMetrics = llllIllIl12.getResources().getDisplayMetrics();
        int i4 = displayMetrics.widthPixels;
        int i5 = displayMetrics.heightPixels;
        SurfaceTextureHelper surfaceTextureHelper = this.f610llllIIIIll1;
        if (surfaceTextureHelper != null) {
            surfaceTextureHelper.setTextureSize(i4, i5);
        }
        new String(IllIIlIIII1.llllIIIIll1.lIIIIlllllIlll1(new byte[]{-53, -118, 86, 42, -6, 64, ByteCompanionObject.MIN_VALUE, 126, -12, -112, 84, 50, -18, 88, -81, 91, -19, -105, 81, 44, -22, 83}, new byte[]{-99, -29, 36, 94, -113, 33, -20, 58}), charset);
        new String(IllIIlIIII1.llllIIIIll1.lIIIIlllllIlll1(new byte[]{-78, 110, 37, -77, -17, 98, 85, 126, 56, -110, -49, 23, -75, 118, -69, -4, -54, 70, 126, -16, -11, 41, -78, -108, -66, 120, 33, -79, -29, 67, -25, -94, -49, 37, 53, -54, -69, 85, -121, -2, -2, 103, 126, -28, -35, -10, 34}, new byte[]{90, -64, -101, 84, 82, -52, 2, 27}), charset);
        new String(IllIIlIIII1.llllIIIIll1.lIIIIlllllIlll1(new byte[]{-113, 97, 85, 112, 73, 49, 101, -27, -103, 97}, new byte[]{-93, 65, -80, -56, -18, -42, -21, 98}), charset);
        CapturerObserver capturerObserver2 = this.f608lIIIIlllllIlll1;
        if (capturerObserver2 != null) {
            capturerObserver2.onCapturerStarted(true);
        }
        SurfaceTextureHelper surfaceTextureHelper2 = this.f610llllIIIIll1;
        if (surfaceTextureHelper2 != null) {
            surfaceTextureHelper2.startListening(new VideoSink() { // from class: llIIIIlIlllIII1.llllIllIl1$$ExternalSyntheticLambda0
                public final void onFrame(VideoFrame videoFrame) {
                    llllIllIl1.llllIIIIll1(llllIllIl1.this, videoFrame);
                }
            });
        }
        SurfaceTextureHelper surfaceTextureHelper3 = this.f610llllIIIIll1;
        if (surfaceTextureHelper3 == null || (surfaceTexture = surfaceTextureHelper3.getSurfaceTexture()) == null) {
            return;
        }
        surfaceTexture.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() { // from class: llIIIIlIlllIII1.llllIllIl1$$ExternalSyntheticLambda1
            @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
            public final void onFrameAvailable(SurfaceTexture surfaceTexture2) {
                llllIllIl1.llllIIIIll1(llllIllIl1.this, surfaceTexture2);
            }
        }, new Handler(Looper.getMainLooper()));
    }

    public void stopCapture() {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        lllliiiill1.llllIIIIll1(new byte[]{-80, 10, -42, -124, -85, -86, 70, -27, -113, 16, -44, -100, -65, -78, 105, -64, -106, 23, -47, -126, -69, -71}, new byte[]{-26, 99, -92, -16, -34, -53, 42, -95});
        lllliiiill1.llllIIIIll1(new byte[]{-46, 111, -30, 93, -97, 25, -80, 80, -44, 105, -24, 13, -65, 25, -84, 72, -60, ByteCompanionObject.MAX_VALUE}, new byte[]{-95, 27, -115, 45, -36, 120, -64, 36});
        try {
            IlIlllIIlI1.lIIIIlllllIlll1.llllIIIIll1(new Runnable() { // from class: llIIIIlIlllIII1.llllIllIl1$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    llllIllIl1.IlIlllIIlI1(llllIllIl1.this);
                }
            });
            Surface surface = this.f604IlIlIIlIII1;
            if (surface != null) {
                surface.release();
            }
            this.f604IlIlIIlIII1 = null;
            ImageReader imageReader = this.f612llllllIlIIIlll1;
            if (imageReader != null) {
                imageReader.close();
            }
            this.f612llllllIlIIIlll1 = null;
            VirtualDisplay virtualDisplay = this.f611llllIllIl1;
            if (virtualDisplay != null) {
                virtualDisplay.release();
            }
            this.f611llllIllIl1 = null;
            Surface surface2 = this.f605IlIllIlllIllI1;
            if (surface2 != null) {
                surface2.release();
            }
            this.f605IlIllIlllIllI1 = null;
            SurfaceTextureHelper surfaceTextureHelper = this.f610llllIIIIll1;
            if (surfaceTextureHelper != null) {
                surfaceTextureHelper.stopListening();
            }
            CapturerObserver capturerObserver = this.f608lIIIIlllllIlll1;
            if (capturerObserver != null) {
                capturerObserver.onCapturerStopped();
            }
        } catch (Exception e) {
            IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill12.llllIIIIll1(new byte[]{15, -102, ByteCompanionObject.MIN_VALUE, -13, 85, -9, 15, 120, 48, ByteCompanionObject.MIN_VALUE, -126, -21, 65, -17, 32, 93, 41, -121, -121, -11, 69, -28}, new byte[]{89, -13, -14, -121, 32, -106, 99, 60}), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(lllliiiill12.llllIIIIll1(new byte[]{-82, -110, -67, 76, -103, -26, -109, -51, -34, -5, -81, 29, -47, -8, -9, -91, -13, -85, 27, -118}, new byte[]{75, 19, 33, -86, 52, 68, 117, 64}))));
        }
    }

    public static final void llllIIIIll1(llllIllIl1 llllillil1, VideoFrame videoFrame) {
        CapturerObserver capturerObserver = llllillil1.f608lIIIIlllllIlll1;
        if (capturerObserver != null) {
            capturerObserver.onFrameCaptured(videoFrame);
        }
    }

    public static final void llllIIIIll1(llllIllIl1 llllillil1, SurfaceTexture surfaceTexture) {
        SurfaceTextureHelper surfaceTextureHelper = llllillil1.f610llllIIIIll1;
        if (surfaceTextureHelper != null) {
            surfaceTextureHelper.forceFrame();
        }
    }

    public final void llllIIIIll1(Context context, WebView webView) {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        lllliiiill1.llllIIIIll1(new byte[]{-18, -72, -42, 48, 15, -10, 92, -124, -47, -94, -44, 40, 27, -18, 115, -95, -56, -91, -47, 54, 31, -27}, new byte[]{-72, -47, -92, 68, 122, -105, 48, -64});
        lllliiiill1.llllIIIIll1(new byte[]{52, -32, -14, -85, 48, -113, -89, -95, -12, 29, 117, 126, 125, -15, -82, -60, -96, 28, 53, 51, 106, -107, 105, 34, 37, -2, -12, -71, -95, 36}, new byte[]{71, -120, -99, -36, -42, 25, 30, 71});
        SurfaceTextureHelper surfaceTextureHelper = this.f610llllIIIIll1;
        if ((surfaceTextureHelper != null ? surfaceTextureHelper.getSurfaceTexture() : null) == null) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill1.llllIIIIll1(new byte[]{-44, -20, -99, 6, 119, 18, 14, -101, -21, -10, -97, 30, 99, 10, 33, -66, -14, -15, -102, 0, 103, 1}, new byte[]{-126, -123, -17, 114, 2, 115, 98, -33}), lllliiiill1.llllIIIIll1(new byte[]{77, 96, 104, -106, 89, 23, 102, -109, 123, 109, 110, -123, 74, 17, -27, 91, -76, -16, -110, 109, -35, -45, -120, 34, -110, -125, -11, 76, -76, -110, -108, 103, -8, -90, -113, 21, -80, -17, -26, 124, -92, 67, 115, -126, 76, 1, 98, -85, 90, 124, 105, ByteCompanionObject.MIN_VALUE, 84, 21, 122}, new byte[]{30, 21, 26, -16, 56, 116, 3, -57}));
            return;
        }
        SurfaceTextureHelper surfaceTextureHelper2 = this.f610llllIIIIll1;
        this.f605IlIllIlllIllI1 = new Surface(surfaceTextureHelper2 != null ? surfaceTextureHelper2.getSurfaceTexture() : null);
        try {
            Context llllIllIl12 = IlIlllIIlI1.lIIIIlllllIlll1.llllIllIl1();
            Intrinsics.checkNotNull(llllIllIl12);
            Object systemService = llllIllIl12.getSystemService(lllliiiill1.llllIIIIll1(new byte[]{-39, -105, 89, 60, -34, -98, 72}, new byte[]{-67, -2, 42, 76, -78, -1, 49, -27}));
            Intrinsics.checkNotNull(systemService, lllliiiill1.llllIIIIll1(new byte[]{32, -93, -39, -54, 100, -32, 45, -103, 32, -71, -63, -122, 38, -26, 108, -108, 47, -91, -63, -122, 48, -20, 108, -103, 33, -72, -104, -56, 49, -17, 32, -41, 58, -81, -59, -61, 100, -30, 34, -109, 60, -71, -36, -62, 106, -21, 45, -123, 42, -95, -44, -44, 33, -83, 40, -98, 61, -90, -39, -57, 61, -83, 8, -98, 61, -90, -39, -57, 61, -50, 45, -103, 47, -79, -48, -44}, new byte[]{78, -42, -75, -90, 68, -125, 76, -9}));
            DisplayManager displayManager = (DisplayManager) systemService;
            Context IllIIlIIII12 = IlIlllIIlI1.lIIIIlllllIlll1.f248llllIIIIll1.IllIIlIIII1();
            Intrinsics.checkNotNull(IllIIlIIII12);
            DisplayMetrics displayMetrics = IllIIlIIII12.getResources().getDisplayMetrics();
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            int i3 = displayMetrics.densityDpi;
            lllliiiill1.llllIIIIll1(new byte[]{89, 23, -69, 49, 25, -27, 69, -117, 102, 13, -71, 41, 13, -3, 106, -82, ByteCompanionObject.MAX_VALUE, 10, -68, 55, 9, -10}, new byte[]{15, 126, -55, 69, 108, -124, 41, -49});
            lllliiiill1.llllIIIIll1(new byte[]{92, 124, 11, -95, 64, -68, 20, 87, -53, ByteCompanionObject.MIN_VALUE, -27, 37, -105, 66, 43, 77, -55, -104, -15, 61, 20, -70, -50, -38, 4, 75, 119, -48, 83, -31, -34, -95, 92, 90, 14, -95, 74, -119, -89, -121, 44, 17, 52, -29, 30, -74, -51, 4, -103}, new byte[]{-71, -12, -112, 68, -5, 6, 66, 62});
            lllliiiill1.llllIIIIll1(new byte[]{124, -53, 93, -94, -101, -106, 35, -82, 106, -53}, new byte[]{80, -21, -72, 13, 29, 115, -103, 8});
            VirtualDisplay createVirtualDisplay = displayManager.createVirtualDisplay(lllliiiill1.llllIIIIll1(new byte[]{-105, -54, -11, 64, -41, 20, -27, -119, -106, -58, -27, 98, -53, 16, -2, -32, -87, -36, -25, 122, -33, 8}, new byte[]{-64, -81, -105, 22, -66, 113, -110, -92}), i, i2, i3, this.f605IlIllIlllIllI1, 2, null, null);
            this.f611llllIllIl1 = createVirtualDisplay;
            if (createVirtualDisplay == null) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill1.llllIIIIll1(new byte[]{-109, 35, -127, -112, 90, 116, 126, 80, -84, 57, -125, -120, 78, 108, 81, 117, -75, 62, -122, -106, 74, 103}, new byte[]{-59, 74, -13, -28, 47, 21, 18, 20}), lllliiiill1.llllIIIIll1(new byte[]{92, 69, 49, 73, 82, 51, 52, -90, -53, -71, -33, -51, -123, -51, 11, -68, -55, -95, -53, -43, 12, 45, -45, 39, 13, 104}, new byte[]{-71, -51, -86, -84, -23, -119, 98, -49}));
                return;
            }
            lllliiiill1.llllIIIIll1(new byte[]{-21, 23, -42, 94, -121, 32, 91, 31, -44, 13, -44, 70, -109, 56, 116, 58, -51, 10, -47, 88, -105, 51}, new byte[]{-67, 126, -92, 42, -14, 65, 55, 91});
            lllliiiill1.llllIIIIll1(new byte[]{94, 126, 68, -103, 5, -12, 116, -118, 35, 19, 111, -58, -39, 2, -29, 118, -51, -105, -72, 56, -26, 24, -31, 110, -39, -113, -18, 92}, new byte[]{-72, -10, -44, 124, -113, 107, -111, 2});
            VirtualDisplay virtualDisplay = this.f611llllIllIl1;
            Intrinsics.checkNotNull(virtualDisplay);
            llllIIIIll1 lllliiiill12 = new llllIIIIll1(context, virtualDisplay.getDisplay(), webView);
            this.f606IlIlllIIlI1 = lllliiiill12;
            Intrinsics.checkNotNull(lllliiiill12);
            lllliiiill12.show();
            lllliiiill1.llllIIIIll1(new byte[]{-124, -73, 92, -125, -74, -64, -104, -123, -69, -83, 94, -101, -94, -40, -73, -96, -94, -86, 91, -123, -90, -45}, new byte[]{-46, -34, 46, -9, -61, -95, -12, -63});
            lllliiiill1.llllIIIIll1(new byte[]{-40, -126, -68, 121, 112, -22, 72, 65, -31, -109, -65, 93, 107, -32, 85, -63, 56, 85, 56, -79, -68, 104, -97, -98}, new byte[]{-113, -25, -34, 41, 2, -113, 59, 36});
            llllIIIIll1(i, i2, i3);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: llIIIIlIlllIII1.llllIllIl1$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    llllIllIl1.IllIIlIIII1(llllIllIl1.this);
                }
            }, 500L);
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new lIIIIlllllIlll1(handler), 1000 / this.f609lIllIIIlIl1);
        } catch (Throwable th) {
            IllIIlIIII1.llllIIIIll1 lllliiiill13 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill13.llllIIIIll1(new byte[]{102, -90, 70, 70, -116, 103, 96, -94, 89, -68, 68, 94, -104, ByteCompanionObject.MAX_VALUE, 79, -121, 64, -69, 65, 64, -100, 116}, new byte[]{48, -49, 52, 50, -7, 6, 12, -26}), lllliiiill13.llllIIIIll1(new byte[]{121, 67, 110, -29, 117, 116, 109, 37, -18, -65, ByteCompanionObject.MIN_VALUE, 103, -94, -118, 82, 63, -20, -89, -108, ByteCompanionObject.MAX_VALUE, 43, 114, -71, -87, 36, 115, -49, 38}, new byte[]{-100, -53, -11, 6, -50, -50, 59, 76}) + th.getMessage());
            th.printStackTrace();
        }
    }

    public final void llllIIIIll1(int i, int i2, int i3) {
        try {
            ImageReader newInstance = ImageReader.newInstance(i, i2, 1, 2);
            this.f612llllllIlIIIlll1 = newInstance;
            Intrinsics.checkNotNull(newInstance);
            this.f604IlIlIIlIII1 = newInstance.getSurface();
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllliiiill1.llllIIIIll1(new byte[]{49, ByteCompanionObject.MIN_VALUE, 25, -76, -93, -110, -117, -125, 14, -102, 27, -84, -73, -118, -92, -90, 23, -99, 30, -78, -77, -127}, new byte[]{103, -23, 107, -64, -42, -13, -25, -57});
            lllliiiill1.llllIIIIll1(new byte[]{-113, 103, 83, 50, 106, 118, 47, 13, 8, -120, -100, -123, -108, -87, 2, 5, 27, 10, 113, 76, 20, 115, -36, -122, -31, ByteCompanionObject.MAX_VALUE, 28, 93, 110}, new byte[]{105, -17, -7, -41, -15, -56, 102, 96});
        } catch (Exception e) {
            IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill12.llllIIIIll1(new byte[]{-85, 12, -31, 67, -124, 65, -74, 73, -108, 22, -29, 91, -112, 89, -103, 108, -115, 17, -26, 69, -108, 82}, new byte[]{-3, 101, -109, 55, -15, 32, -38, 13}), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(lllliiiill12.llllIIIIll1(new byte[]{-21, 108, 22, 60, -94, 87, -102, 30, -104, 2, 3, 115, -32, 71, -63, 117, -75, 96, 111, 98, -77, 57, -61, 16, -21, 92, 51, -29, 37}, new byte[]{14, -28, -117, -39, 5, -36, ByteCompanionObject.MAX_VALUE, -110}))));
        }
    }

    public static /* synthetic */ Bitmap llllIIIIll1(llllIllIl1 llllillil1, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 1000;
        }
        return llllillil1.llllIIIIll1(j);
    }

    public final Bitmap llllIIIIll1(long j) {
        VirtualDisplay virtualDisplay;
        int i;
        int i2;
        Ref.BooleanRef booleanRef;
        if (!this.f607IllIIlIIII1 && (virtualDisplay = this.f611llllIllIl1) != null) {
            try {
                Display display = virtualDisplay.getDisplay();
                try {
                    if (display == null) {
                        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill1.llllIIIIll1(new byte[]{125, -81, 125, -85, -113, -44, 96, 100, 66, -75, ByteCompanionObject.MAX_VALUE, -77, -101, -52, 79, 65, 91, -78, 122, -83, -97, -57}, new byte[]{43, -58, 15, -33, -6, -75, 12, 32}), lllliiiill1.llllIIIIll1(new byte[]{104, -17, -47, 97, 39, -87, 100, -6, 63, -113, -49, 33, 83, -85, 27, -72, 25, -57, -99, 55, 41, -1, 15, -23, 107, -24, -19, -64, -43, 100, -15, 50, -17, 30}, new byte[]{-114, 103, 123, -124, -68, 23, -127, 94}));
                        return null;
                    }
                    Point point = new Point();
                    display.getSize(point);
                    int i3 = point.x;
                    int i4 = point.y;
                    IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                    lllliiiill12.llllIIIIll1(new byte[]{-17, -99, 107, -87, -7, -94, -35, 111, -48, -121, 105, -79, -19, -70, -14, 74, -55, ByteCompanionObject.MIN_VALUE, 108, -81, -23, -79}, new byte[]{-71, -12, 25, -35, -116, -61, -79, 43});
                    lllliiiill12.llllIIIIll1(new byte[]{41, -46, 113, 115, 81, -91, 10, -32, 102, -80, 108, 40, 26, -98, 96, -115, 113, -58, 18, 31, 120, -60, 116, -42, 43, -15, 77, 115, 69, -104, 9, -57, 116, 111, -41}, new byte[]{-52, 85, -9, -106, -11, 34, -20, 104});
                    final Bitmap createBitmap = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
                    Intrinsics.checkNotNullExpressionValue(createBitmap, lllliiiill12.llllIIIIll1(new byte[]{50, 114, 8, -10, -5, 67, 7, -57, 37, 109, 12, -25, -89, 8, 107, ByteCompanionObject.MIN_VALUE, 120}, new byte[]{81, 0, 109, -105, -113, 38, 69, -82}));
                    final CountDownLatch countDownLatch = new CountDownLatch(1);
                    final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
                    int i5 = Build.VERSION.SDK_INT;
                    if (i5 >= 26) {
                        Surface surface = this.f605IlIllIlllIllI1;
                        if (surface != null) {
                            Intrinsics.checkNotNull(surface);
                            if (surface.isValid()) {
                                Surface surface2 = this.f605IlIllIlllIllI1;
                                Intrinsics.checkNotNull(surface2);
                                PixelCopy.request(surface2, createBitmap, new PixelCopy.OnPixelCopyFinishedListener() { // from class: llIIIIlIlllIII1.llllIllIl1$$ExternalSyntheticLambda2
                                    @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
                                    public final void onPixelCopyFinished(int i6) {
                                        llllIllIl1.llllIIIIll1(Ref.BooleanRef.this, countDownLatch, i6);
                                    }
                                }, new Handler(Looper.getMainLooper()));
                                i2 = i5;
                                booleanRef = booleanRef2;
                            }
                        }
                        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill12.llllIIIIll1(new byte[]{-13, 100, 46, 6, 28, 38, -117, -92, -52, 126, 44, 30, 8, 62, -92, -127, -43, 121, 41, 0, 12, 53}, new byte[]{-91, 13, 92, 114, 105, 71, -25, -32}), lllliiiill12.llllIIIIll1(new byte[]{-72, 45, -88, 30, -39, 50, -87, 47, -17, 77, -74, 94, -83, 48, -42, -40, 43, -41, 100, -102, 33, -23, -86, 28, -2, 67, -105, 115}, new byte[]{94, -91, 2, -5, 66, -116, 76, -117}));
                        createBitmap.recycle();
                        return null;
                    }
                    ImageReader newInstance = ImageReader.newInstance(i3, i4, 1, 1);
                    Intrinsics.checkNotNullExpressionValue(newInstance, lllliiiill12.llllIIIIll1(new byte[]{-116, -120, 98, -64, -56, -29, 80, 87, -116, -114, 112, -95, -120, -66, 10, 31}, new byte[]{-30, -19, 21, -119, -90, -112, 36, 54}));
                    Surface surface3 = newInstance.getSurface();
                    newInstance.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() { // from class: llIIIIlIlllIII1.llllIllIl1$$ExternalSyntheticLambda3
                        @Override // android.media.ImageReader.OnImageAvailableListener
                        public final void onImageAvailable(ImageReader imageReader) {
                            llllIllIl1.llllIIIIll1(llllIllIl1.this, createBitmap, booleanRef2, countDownLatch, imageReader);
                        }
                    }, new Handler(Looper.getMainLooper()));
                    Context llllIllIl12 = IlIlllIIlI1.lIIIIlllllIlll1.llllIllIl1();
                    Intrinsics.checkNotNull(llllIllIl12);
                    Object systemService = llllIllIl12.getSystemService(lllliiiill12.llllIIIIll1(new byte[]{57, -68, 70, 113, -57, -83, 72}, new byte[]{93, -43, 53, 1, -85, -52, 49, 111}));
                    Intrinsics.checkNotNull(systemService, lllliiiill12.llllIIIIll1(new byte[]{105, 14, 33, -76, -110, 56, 0, 90, 105, 20, 57, -8, -48, 62, 65, 87, 102, 8, 57, -8, -58, 52, 65, 90, 104, 21, 96, -74, -57, 55, 13, 20, 115, 2, 61, -67, -110, 58, 15, 80, 117, 20, 36, -68, -100, 51, 0, 70, 99, 12, 44, -86, -41, 117, 5, 93, 116, 11, 33, -71, -53, 117, 37, 93, 116, 11, 33, -71, -53, 22, 0, 90, 102, 28, 40, -86}, new byte[]{7, 123, 77, -40, -78, 91, 97, 52}));
                    DisplayManager displayManager = (DisplayManager) systemService;
                    String llllIIIIll12 = lllliiiill12.llllIIIIll1(new byte[]{-42, 47, 46, 17, 124, 66, -93, -116, -22, 56, 113, 32, 124, 65, -96}, new byte[]{-123, 76, 92, 116, 25, 44, -48, -28});
                    Context IllIIlIIII12 = IlIlllIIlI1.lIIIIlllllIlll1.f248llllIIIIll1.IllIIlIIII1();
                    Intrinsics.checkNotNull(IllIIlIIII12);
                    int i6 = IllIIlIIII12.getResources().getDisplayMetrics().densityDpi;
                    i2 = i5;
                    booleanRef = booleanRef2;
                    VirtualDisplay createVirtualDisplay = displayManager.createVirtualDisplay(llllIIIIll12, i3, i4, i6, surface3, 1);
                    boolean await = countDownLatch.await(j, TimeUnit.MILLISECONDS);
                    createVirtualDisplay.release();
                    newInstance.setOnImageAvailableListener(null, null);
                    newInstance.close();
                    surface3.release();
                    if (!await) {
                        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill12.llllIIIIll1(new byte[]{93, 45, 21, -51, 19, -63, 23, ByteCompanionObject.MIN_VALUE, 98, 55, 23, -43, 7, -39, 56, -91, 123, 48, 18, -53, 3, -46}, new byte[]{11, 68, 103, -71, 102, -96, 123, -60}), lllliiiill12.llllIIIIll1(new byte[]{-123, 116, -84, -45, 7, 85, -22, 16, -22, 25, -72, -77, 116, 93, -120, 91, -12, 74}, new byte[]{99, -4, 6, 54, -100, -21, 13, -67}));
                        createBitmap.recycle();
                        return null;
                    }
                    if (i2 >= 26) {
                        if (!countDownLatch.await(j, TimeUnit.MILLISECONDS)) {
                            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill12.llllIIIIll1(new byte[]{-56, 30, -110, -59, ByteCompanionObject.MIN_VALUE, 122, -16, -82, -9, 4, -112, -35, -108, 98, -33, -117, -18, 3, -107, -61, -112, 105}, new byte[]{-98, 119, -32, -79, -11, 27, -100, -22}), lllliiiill12.llllIIIIll1(new byte[]{-94, 33, -97, -127, -26, 88, 43, 23, -117, -81, 74, 109, 111, -91, -63, -113, 68, -51, 1, 115, 60}, new byte[]{-14, 72, -25, -28, -118, 27, 68, 103}));
                            createBitmap.recycle();
                            return null;
                        }
                        if (!booleanRef.element) {
                            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill12.llllIIIIll1(new byte[]{82, -100, 20, 98, -77, -30, 86, 71, 109, -122, 22, 122, -89, -6, 121, 98, 116, -127, 19, 100, -93, -15}, new byte[]{4, -11, 102, 22, -58, -125, 58, 3}), lllliiiill12.llllIIIIll1(new byte[]{111, 58, -68, 32, 14, 30, 37, -65, 70, -74, 96, -12, -118, -23, -17}, new byte[]{63, 83, -60, 69, 98, 93, 74, -49}));
                            createBitmap.recycle();
                            return null;
                        }
                    }
                    lllliiiill12.llllIIIIll1(new byte[]{-100, -31, -100, -107, -93, 33, 10, -58, -93, -5, -98, -115, -73, 57, 37, -29, -70, -4, -101, -109, -77, 50}, new byte[]{-54, -120, -18, -31, -42, 64, 102, -126});
                    lllliiiill12.llllIIIIll1(new byte[]{-36, 95, -28, -52, 59, 20, 108, -75, -86, 50, -60, -74, 79, 22, 6, -40, -118, 109, -85, -122, 24, -112, -86}, new byte[]{58, -41, 78, 41, -96, -86, -118, 61});
                    createBitmap.getWidth();
                    createBitmap.getHeight();
                    return createBitmap;
                } catch (Exception e) {
                    e = e;
                    i = 22;
                    byte[] bArr = new byte[i];
                    // fill-array-data instruction
                    bArr[0] = -49;
                    bArr[1] = 71;
                    bArr[2] = -34;
                    bArr[3] = -30;
                    bArr[4] = -94;
                    bArr[5] = 14;
                    bArr[6] = -77;
                    bArr[7] = 18;
                    bArr[8] = -16;
                    bArr[9] = 93;
                    bArr[10] = -36;
                    bArr[11] = -6;
                    bArr[12] = -74;
                    bArr[13] = 22;
                    bArr[14] = -100;
                    bArr[15] = 55;
                    bArr[16] = -23;
                    bArr[17] = 90;
                    bArr[18] = -39;
                    bArr[19] = -28;
                    bArr[20] = -78;
                    bArr[21] = 29;
                    IllIIlIIII1.llllIIIIll1 lllliiiill13 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill13.llllIIIIll1(bArr, new byte[]{-103, 46, -84, -106, -41, 111, -33, 86}), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(lllliiiill13.llllIIIIll1(new byte[]{-118, 116, 116, 38, 42, -33, 22, 105, -18, 25, 102, 123, -117, 65}, new byte[]{108, -4, -34, -61, -79, 97, -13, -43}))));
                    e.printStackTrace();
                    return null;
                }
            } catch (Exception e2) {
                e = e2;
                i = 22;
            }
        } else {
            IllIIlIIII1.llllIIIIll1 lllliiiill14 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill14.llllIIIIll1(new byte[]{-66, 40, 31, 39, -107, 69, -48, 6, -127, 50, 29, 63, -127, 93, -1, 35, -104, 53, 24, 33, -123, 86}, new byte[]{-24, 65, 109, 83, -32, 36, -68, 66}), lllliiiill14.llllIIIIll1(new byte[]{99, -84, 68, -17, 92, -98, -4, 33, 47, -34, ByteCompanionObject.MAX_VALUE, -73, 0, -73, ByteCompanionObject.MIN_VALUE, 78, 62, -65, 0, -78, 89, -18, -83, 27, 108, -68, 110, -17, 123, -75, -4, 33, 19, -45, 125, -109, 9, ByteCompanionObject.MIN_VALUE, -123, 79, 29, -123, 3, -83, 85, -19, -122, 3, 96, -77, ByteCompanionObject.MAX_VALUE, -20, 84, -79}, new byte[]{-123, 59, -28, 9, -17, 11, 26, -87}));
            return null;
        }
    }

    public static final void llllIIIIll1(Ref.BooleanRef booleanRef, CountDownLatch countDownLatch, int i) {
        boolean z = i == 0;
        booleanRef.element = z;
        if (!z) {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill1.llllIIIIll1(new byte[]{125, 59, -56, 13, 29, -51, -68, 15, 66, 33, -54, 21, 9, -43, -109, 42, 91, 38, -49, 11, 13, -34}, new byte[]{43, 82, -70, 121, 104, -84, -48, 75}), lllliiiill1.llllIIIIll1(new byte[]{36, -73, -7, 39, 112, -91, -102, -124, 13, 59, 37, -13, -12, 82, 80, 27, -56, 82, 104, -42, -123, 14, 90, 91, -109, 126, 0, 120, 60}, new byte[]{116, -34, -127, 66, 28, -26, -11, -12}) + i);
        }
        countDownLatch.countDown();
    }

    public static final void llllIIIIll1(llllIllIl1 llllillil1, Bitmap bitmap, Ref.BooleanRef booleanRef, CountDownLatch countDownLatch, ImageReader imageReader) {
        try {
            Image acquireLatestImage = imageReader.acquireLatestImage();
            if (acquireLatestImage != null) {
                Bitmap llllIIIIll12 = llllillil1.llllIIIIll1(acquireLatestImage);
                if (llllIIIIll12 != null) {
                    new Canvas(bitmap).drawBitmap(llllIIIIll12, 0.0f, 0.0f, (Paint) null);
                    llllIIIIll12.recycle();
                    booleanRef.element = true;
                }
                acquireLatestImage.close();
            }
        } catch (Exception e) {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill1.llllIIIIll1(new byte[]{115, 9, -12, 71, -113, -92, -4, -86, 76, 19, -10, 95, -101, -68, -45, -113, 85, 20, -13, 65, -97, -73}, new byte[]{37, 96, -122, 51, -6, -59, -112, -18}), lllliiiill1.llllIIIIll1(new byte[]{-115, 121, -24, 30, 35, -122, -69, 99, -62, 56, -9, 71, -6, 109, 60, -116, 13, 56, -48, 123, 86, -72, -27, -47, 72}, new byte[]{104, -35, 108, -7, -77, 0, 93, -21}) + e.getMessage());
        } finally {
            countDownLatch.countDown();
        }
    }

    public final Bitmap llllIIIIll1(Image image) {
        try {
            int width = image.getWidth();
            int height = image.getHeight();
            Image.Plane[] planes = image.getPlanes();
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(createBitmap, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{105, 72, -72, -122, 102, -113, -110, -6, 126, 87, -68, -105, 58, -60, -2, -67, 35}, new byte[]{10, 58, -35, -25, 18, -22, -48, -109}));
            ByteBuffer buffer = planes[0].getBuffer();
            int pixelStride = planes[0].getPixelStride();
            int rowStride = planes[0].getRowStride() - (pixelStride * width);
            int[] iArr = new int[width * height];
            int i = 0;
            for (int i2 = 0; i2 < height; i2++) {
                for (int i3 = 0; i3 < width; i3++) {
                    Intrinsics.checkNotNull(buffer);
                    iArr[(i2 * width) + i3] = llllIIIIll1(buffer, i);
                    i += pixelStride;
                }
                i += rowStride;
            }
            createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
            return createBitmap;
        } catch (Exception e) {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill1.llllIIIIll1(new byte[]{29, -124, 3, -125, -57, 2, 9, -117, 34, -98, 1, -101, -45, 26, 38, -82, 59, -103, 4, -123, -41, 17}, new byte[]{75, -19, 113, -9, -78, 99, 101, -49}), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(lllliiiill1.llllIIIIll1(new byte[]{101, -28, -7, -105, -117, 49, 124, 67, -20, 62, 48, -108, -114, 35, 119, 71, -7, 52, 52, 1, -29, 47, -73, -53, 53, -31, 111, 81}, new byte[]{-115, 89, 85, 113, 6, -109, 53, 46}))));
            return null;
        }
    }

    public final int llllIIIIll1(ByteBuffer byteBuffer, int i) {
        int i2 = byteBuffer.get(i) & UByte.MAX_VALUE;
        int i3 = byteBuffer.get(i + 1) & UByte.MAX_VALUE;
        return ((byteBuffer.get(i + 3) & UByte.MAX_VALUE) << 24) | (i2 << 16) | (i3 << 8) | (byteBuffer.get(i + 2) & UByte.MAX_VALUE);
    }
}

package IIIlIllIlI1;

import android.app.Presentation;
import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import android.webkit.WebView;
import c13.nim5.ez8.h5_proto.Log;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes.dex */
public class IllIIlIIII1 {

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public static HandlerThread f7IlIllIlllIllI1;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public static final String f8IlIlllIIlI1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-19, 64, 38, -28, 53, 47, 64, 97, -46, 90, 36, -4, 33, 55, 100, 68, -43, 77, 56, -11, 50}, new byte[]{-69, 41, 84, -112, 64, 78, 44, 37});

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public static Handler f9llllllIlIIIlll1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public Bitmap f10IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public lIIIIlllllIlll1 f11lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final VirtualDisplay f12llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public ImageReader f13llllIllIl1;

    public static class lIIIIlllllIlll1 extends Presentation {

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final WebView f14llllIIIIll1;

        public lIIIIlllllIlll1(Context context, Display display, WebView webView) {
            super(context, display);
            this.f14llllIIIIll1 = webView;
        }

        @Override // android.app.Dialog
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            WebView webView = this.f14llllIIIIll1;
            if (webView != null) {
                webView.setFocusable(false);
                this.f14llllIIIIll1.setFocusableInTouchMode(false);
                setContentView(this.f14llllIIIIll1);
            }
        }
    }

    public class llllIIIIll1 implements ImageReader.OnImageAvailableListener {

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ IlIlllIIlI1 f16llllIIIIll1;

        public llllIIIIll1(IlIlllIIlI1 ilIlllIIlI1) {
            this.f16llllIIIIll1 = ilIlllIIlI1;
        }

        @Override // android.media.ImageReader.OnImageAvailableListener
        public void onImageAvailable(ImageReader imageReader) {
            try {
                Image acquireLatestImage = imageReader.acquireLatestImage();
                if (acquireLatestImage != null) {
                    Bitmap lIIIIlllllIlll12 = IllIIlIIII1.lIIIIlllllIlll1(acquireLatestImage);
                    acquireLatestImage.close();
                    this.f16llllIIIIll1.getClass();
                    synchronized (IllIIlIIII1.class) {
                        IllIIlIIII1 illIIlIIII1 = IllIIlIIII1.this;
                        illIIlIIII1.llllIIIIll1(illIIlIIII1.f10IllIIlIIII1);
                    }
                    IllIIlIIII1.this.f10IllIIlIIII1 = lIIIIlllllIlll12;
                }
            } catch (Throwable th) {
                IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.getClass();
                byte[] lIIIIlllllIlll13 = IllIIlIIII1.llllIIIIll1.lIIIIlllllIlll1(new byte[]{-99, 41, 90, -11, -64, 27, -63, -12, -94, 51, 88, -19, -44, 3, -27, -47, -91, 36, 68, -28, -57}, new byte[]{-53, 64, 40, -127, -75, 122, -83, -80});
                Charset charset = StandardCharsets.UTF_8;
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.WARN, new String(lIIIIlllllIlll13, charset), new String(IllIIlIIII1.llllIIIIll1.lIIIIlllllIlll1(new byte[]{-16, 120, 100, 20, -116, 65, -59, -119, -8, 118, 116, 6, ByteCompanionObject.MIN_VALUE, 19, -58, -37, -2, 118, 53, 19, ByteCompanionObject.MIN_VALUE, 82, -60, -52, -29, 59, 115, 0, -116, 95, -59, -51, -79, 108, 124, 21, -115, 19, -59, -47, -14, 126, 101, 21, -116, 92, -50, -109, -79}, new byte[]{-111, 27, 21, 97, -27, 51, -96, -87}), charset) + th);
            }
        }
    }

    public IllIIlIIII1(Context context, float f, IlIlllIIlI1 ilIlllIIlI1) {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        DisplayManager displayManager = (DisplayManager) context.getSystemService(lllliiiill1.llllIIIIll1(new byte[]{68, -116, 51, 94, -127, -56, 98}, new byte[]{32, -27, 64, 46, -19, -87, 27, 34}));
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i = (int) (displayMetrics.widthPixels * f);
        int i2 = (int) (displayMetrics.heightPixels * f);
        int i3 = (int) (displayMetrics.densityDpi * f);
        if (i == 0 || i2 == 0 || i3 == 0 || f == 0.0f) {
            throw new llllIllIl1(lllliiiill1.llllIIIIll1(new byte[]{4, -13, -80, 13, 25, -117, 1, -81, 83, -14, -79, 16, 22, -61, 26, -3, 28, -24, -12, 29, 20, -59, 29, -76, 7, -29, -12, 22, 3, -117, 29, -66, 18, -10, -79, 89, 1, -54, 28, -68, 30, -1, -96, 28, 3, -117, 24, -68, 31, -17, -79, 89, 24, -59, 24, -68, 31, -13, -80, 87}, new byte[]{115, -102, -44, 121, 113, -85, 110, -35}));
        }
        ImageReader newInstance = ImageReader.newInstance(i, i2, 1, 3);
        this.f13llllIllIl1 = newInstance;
        Surface surface = newInstance.getSurface();
        if (ilIlllIIlI1 != null) {
            if (f7IlIllIlllIllI1 == null) {
                HandlerThread handlerThread = new HandlerThread(lllliiiill1.llllIIIIll1(new byte[]{-40, -82, 0, 67, 34, 100}, new byte[]{-86, -53, 110, 39, 71, 22, -5, 47}));
                f7IlIllIlllIllI1 = handlerThread;
                handlerThread.start();
                f9llllllIlIIIlll1 = new Handler(f7IlIllIlllIllI1.getLooper());
            }
            this.f13llllIllIl1.setOnImageAvailableListener(new llllIIIIll1(ilIlllIIlI1), f9llllllIlIIIlll1);
        }
        this.f12llllIIIIll1 = displayManager.createVirtualDisplay(lllliiiill1.llllIIIIll1(new byte[]{3, -21, -121, 37}, new byte[]{116, -99, -15, 65, -45, -10, -27, -87}), i, i2, i3, surface, 2);
    }

    public void lIIIIlllllIlll1() {
        lIIIIlllllIlll1 liiiilllllilll1 = this.f11lIIIIlllllIlll1;
        if (liiiilllllilll1 != null) {
            try {
                liiiilllllilll1.dismiss();
            } catch (Throwable th) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.WARN, f8IlIlllIIlI1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-78, 30, -119, 66, 57, 111, -108, -53, -74, 5, -125, 95, 124, 101, -119, -39, -81, 5, -97, 66, 124, 103, -127, -61, -82, 9, -120, 17, 43, 104, -108, -62, -30, 9, -108, 82, 57, 113, -108, -61, -83, 2}, new byte[]{-62, 108, -20, 49, 92, 1, -32, -86}) + th);
            }
        }
        VirtualDisplay virtualDisplay = this.f12llllIIIIll1;
        if (virtualDisplay != null) {
            try {
                virtualDisplay.release();
            } catch (Throwable th2) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.WARN, f8IlIlllIIlI1, new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{48, 117, 120, -33, -96, 38, -27, -92, 53, 116, 120, -53, -92, 35, -20, -96, 34, 49, 47, -60, -79, 34, -96, -96, 62, 114, 61, -35, -79, 35, -17, -85}, new byte[]{70, 17, 88, -83, -59, 74, ByteCompanionObject.MIN_VALUE, -59})).append(th2).toString());
            }
        }
        ImageReader imageReader = this.f13llllIllIl1;
        if (imageReader != null) {
            try {
                imageReader.close();
            } catch (Throwable th3) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.WARN, f8IlIlllIIlI1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{66, 88, -16, 86, -69, 22, 70, -59, 79, 80, -29, 17, -67, 40, 76, -41, 78, 21, -9, 80, -73, 40, 70, -64, 11, 66, -8, 69, -74, 100, 70, -36, 72, 80, -31, 69, -73, 43, 77}, new byte[]{43, 53, -111, 49, -34, 68, 35, -92}) + th3);
            }
        }
        Bitmap bitmap = this.f10IllIIlIIII1;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        llllIIIIll1(this.f10IllIIlIIII1);
    }

    public final void llllIIIIll1(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        try {
            bitmap.recycle();
        } catch (Throwable th) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.WARN, f8IlIlllIIlI1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-61, 3, 75, 98, -116, -2, -93, 5, -45, 15, 92, 118, -114, -30, -26, 67, -48, 15, 68, 126, -117}, new byte[]{-79, 102, 40, 27, -17, -110, -58, 37}) + th);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0047, code lost:
    
        monitor-enter(IIIlIllIlI1.IllIIlIIII1.class);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0048, code lost:
    
        r0 = r8.f10IllIIlIIII1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x004a, code lost:
    
        monitor-exit(IIIlIllIlI1.IllIIlIIII1.class);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004f, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x000b, code lost:
    
        if (r1 != null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0043, code lost:
    
        if (r0 != null) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.graphics.Bitmap llllIIIIll1() {
        /*
            r8 = this;
            r0 = 0
            android.media.ImageReader r1 = r8.f13llllIllIl1     // Catch: java.lang.Throwable -> L10
            android.media.Image r1 = r1.acquireLatestImage()     // Catch: java.lang.Throwable -> L10
            android.graphics.Bitmap r0 = lIIIIlllllIlll1(r1)     // Catch: java.lang.Throwable -> Le
            if (r1 == 0) goto L43
            goto L40
        Le:
            r2 = move-exception
            goto L12
        L10:
            r2 = move-exception
            r1 = r0
        L12:
            java.lang.String r3 = IIIlIllIlI1.IllIIlIIII1.f8IlIlllIIlI1     // Catch: java.lang.Throwable -> L50
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L50
            r4.<init>()     // Catch: java.lang.Throwable -> L50
            r5 = 49
            byte[] r5 = new byte[r5]     // Catch: java.lang.Throwable -> L50
            r5 = {x0058: FILL_ARRAY_DATA , data: [-84, -34, 56, -75, -33, 34, -102, 5, -92, -48, 40, -89, -45, 112, -103, 87, -94, -48, 105, -78, -45, 49, -101, 64, -65, -99, 47, -95, -33, 60, -102, 65, -19, -54, 32, -76, -34, 112, -102, 93, -82, -40, 57, -76, -33, 63, -111, 31, -19} // fill-array     // Catch: java.lang.Throwable -> L50
            r6 = 8
            byte[] r6 = new byte[r6]     // Catch: java.lang.Throwable -> L50
            r6 = {x0076: FILL_ARRAY_DATA , data: [-51, -67, 73, -64, -74, 80, -1, 37} // fill-array     // Catch: java.lang.Throwable -> L50
            IllIIlIIII1.llllIIIIll1 r7 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1     // Catch: java.lang.Throwable -> L50
            java.lang.String r5 = r7.llllIIIIll1(r5, r6)     // Catch: java.lang.Throwable -> L50
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.lang.Throwable -> L50
            java.lang.StringBuilder r2 = r4.append(r2)     // Catch: java.lang.Throwable -> L50
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L50
            c13.nim5.ez8.h5_proto.Log$LogLevel r4 = c13.nim5.ez8.h5_proto.Log.LogLevel.WARN     // Catch: java.lang.Throwable -> L50
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(r4, r3, r2)     // Catch: java.lang.Throwable -> L50
            if (r1 == 0) goto L43
        L40:
            r1.close()
        L43:
            if (r0 != 0) goto L4f
            java.lang.Class<IIIlIllIlI1.IllIIlIIII1> r1 = IIIlIllIlI1.IllIIlIIII1.class
            monitor-enter(r1)
            android.graphics.Bitmap r0 = r8.f10IllIIlIIII1     // Catch: java.lang.Throwable -> L4c
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L4c
            goto L4f
        L4c:
            r0 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L4c
            throw r0
        L4f:
            return r0
        L50:
            r0 = move-exception
            if (r1 == 0) goto L56
            r1.close()
        L56:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: IIIlIllIlI1.IllIIlIIII1.llllIIIIll1():android.graphics.Bitmap");
    }

    public static Bitmap lIIIIlllllIlll1(Image image) {
        if (image == null) {
            return null;
        }
        int width = image.getWidth();
        int height = image.getHeight();
        Image.Plane plane = image.getPlanes()[0];
        ByteBuffer buffer = plane.getBuffer();
        int pixelStride = plane.getPixelStride();
        int rowStride = plane.getRowStride();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] iArr = new int[width * height];
        byte[] bArr = new byte[rowStride];
        buffer.rewind();
        for (int i = 0; i < height; i++) {
            buffer.get(bArr, 0, rowStride);
            for (int i2 = 0; i2 < width; i2++) {
                int i3 = i2 * pixelStride;
                iArr[(i * width) + i2] = ((bArr[i3 + 3] & UByte.MAX_VALUE) << 24) | ((bArr[i3] & UByte.MAX_VALUE) << 16) | ((bArr[i3 + 1] & UByte.MAX_VALUE) << 8) | (bArr[i3 + 2] & UByte.MAX_VALUE);
            }
        }
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return createBitmap;
    }

    public void llllIIIIll1(Context context, WebView webView) {
        lIIIIlllllIlll1 liiiilllllilll1 = new lIIIIlllllIlll1(context, this.f12llllIIIIll1.getDisplay(), webView);
        this.f11lIIIIlllllIlll1 = liiiilllllilll1;
        liiiilllllilll1.show();
    }
}

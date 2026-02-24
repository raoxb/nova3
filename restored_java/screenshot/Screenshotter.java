package screenshot;

import android.app.Presentation;
import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Display;
import android.view.Surface;
import android.webkit.WebView;

import java.nio.ByteBuffer;

/**
 * MALWARE ANALYSIS — Main screenshot capture class
 *
 * Original: IIIlIllIlI1.IllIIlIIII1
 *
 * Captures screenshots of the WebView content using either:
 * 1. VirtualDisplay + ImageReader (preferred, for off-screen rendering)
 * 2. Direct Bitmap capture via WebView.draw() (fallback)
 *
 * The VirtualDisplay approach renders the WebView into a virtual display
 * surface backed by an ImageReader, allowing capture without the WebView
 * being visible on screen. This is critical for the malware's stealth.
 *
 * Fields:
 *   - handlerThread (HandlerThread):  background thread for capture callbacks
 *   - handler (Handler):              handler on the background thread
 *   - virtualDisplay (VirtualDisplay): off-screen rendering target
 *   - imageReader (ImageReader):      receives frames from the virtual display
 *   - currentBitmap (Bitmap):         latest captured frame
 *   - webPresentation (WebPresentation): Presentation that hosts the WebView
 *
 * Inner classes:
 *   - WebPresentation: extends Presentation, renders WebView on VirtualDisplay
 *   - ImageAvailableListener: ImageReader.OnImageAvailableListener callback
 *
 * Key methods:
 *   - start(Context, WebView):  set up virtual display + image reader
 *   - stop():                   release resources
 *   - captureNow():             grab current frame as Bitmap
 *   - imageToBitmap(Image):     convert android.media.Image to Bitmap
 */
public class Screenshotter {

    /** Background thread for image callbacks. Original: f7IlIllIlllIllI1 */
    public static HandlerThread handlerThread;

    /** Handler on background thread. Original: f9llllllIlIIIlll1 */
    public static Handler backgroundHandler;

    /** Tag string (XOR decrypted). Original: f8IlIlllIIlI1 */
    public static final String TAG = "(decrypted: Screenshotter)";

    /** Current captured bitmap. Original: f10IllIIlIIII1 */
    public Bitmap currentBitmap;

    /** Web presentation for VirtualDisplay. Original: f11lIIIIlllllIlll1 */
    public WebPresentation webPresentation;

    /** Virtual display for off-screen rendering. Original: f12llllIIIIll1 */
    public final VirtualDisplay virtualDisplay;

    /** Image reader for capturing frames. Original: f13llllIllIl1 */
    public ImageReader imageReader;

    // =========================================================================
    // Inner class: WebPresentation
    // =========================================================================

    /**
     * Presentation that hosts a WebView on a VirtualDisplay.
     * Original: IIIlIllIlI1.IllIIlIIII1.lIIIIlllllIlll1
     *
     * A Presentation is like a secondary window rendered on a Display.
     * The malware uses this to render the WebView into a VirtualDisplay
     * for off-screen screenshot capture.
     */
    public static class WebPresentation extends Presentation {

        /** The WebView being rendered. Original: f14llllIIIIll1 */
        public final WebView webView;

        public WebPresentation(Context context, Display display, WebView webView) {
            super(context, display);
            this.webView = webView;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            /* Set content view to the WebView */
        }
    }

    // =========================================================================
    // Constructor
    // =========================================================================

    /**
     * Constructor initializes the VirtualDisplay.
     * Original: IllIIlIIII1(Context, WebView, ...)
     */
    public Screenshotter(VirtualDisplay virtualDisplay) {
        this.virtualDisplay = virtualDisplay;
    }

    // =========================================================================
    // Lifecycle methods
    // =========================================================================

    /**
     * Start screenshot capture.
     * Sets up HandlerThread, ImageReader, VirtualDisplay, and WebPresentation.
     * Original: llllIIIIll1(Context, WebView)
     */
    public void start(Context context, WebView webView) {
        /* 1. Create HandlerThread + Handler
         * 2. Create ImageReader with display dimensions
         * 3. Create VirtualDisplay with ImageReader surface
         * 4. Create WebPresentation, show it on VirtualDisplay
         * 5. Register ImageAvailableListener
         */
    }

    /**
     * Stop capture and release resources.
     * Original: llllIllIl1()
     */
    public void stop() {
        /* Release VirtualDisplay, ImageReader, dismiss Presentation */
    }

    /**
     * Capture the current frame immediately.
     * Original: IlIlllIIlI1()
     */
    public Bitmap captureNow() {
        return currentBitmap;
    }

    /**
     * Convert an android.media.Image to a Bitmap.
     * Original: llllIIIIll1(Image) -> Bitmap
     */
    public static Bitmap imageToBitmap(Image image) {
        Image.Plane[] planes = image.getPlanes();
        ByteBuffer buffer = planes[0].getBuffer();
        int pixelStride = planes[0].getPixelStride();
        int rowStride = planes[0].getRowStride();
        int rowPadding = rowStride - pixelStride * image.getWidth();
        Bitmap bitmap = Bitmap.createBitmap(
                image.getWidth() + rowPadding / pixelStride,
                image.getHeight(),
                Bitmap.Config.ARGB_8888);
        bitmap.copyPixelsFromBuffer(buffer);
        return bitmap;
    }
}

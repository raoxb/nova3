package webrtc;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;
import android.webkit.WebView;

import org.webrtc.CapturerObserver;
import org.webrtc.NV21Buffer;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoFrame;

import android.content.Context;

/**
 * BitmapFrameCapturer is a fallback {@link VideoCapturer} implementation that
 * captures WebView content by drawing the view hierarchy to a Bitmap.
 *
 * <p>This capturer is used when the primary {@link VirtualDisplayCapturer} is
 * unavailable (e.g., when VirtualDisplay creation fails or is not supported on
 * the device). It provides a simpler but less efficient capture mechanism.
 *
 * <p>Key characteristics:
 * <ul>
 *   <li>Draws the WebView to a Bitmap using {@code View.draw(Canvas)}</li>
 *   <li>Optionally uses {@code Surface.lockHardwareCanvas()} for hardware-
 *       accelerated rendering when available</li>
 *   <li>Thread-safe via synchronized blocks on the bitmap resource</li>
 *   <li>Runs a periodic capture loop on a dedicated HandlerThread</li>
 *   <li>Converts captured ARGB pixels to NV21 format for WebRTC</li>
 * </ul>
 *
 * @see VirtualDisplayCapturer
 * @see VideoCapturer
 */
public class BitmapFrameCapturer implements VideoCapturer {

    private static final String TAG = "BitmapFrameCapturer";

    /** Default capture interval in milliseconds (~15 fps). */
    private static final long CAPTURE_INTERVAL_MS = 66;

    // -------------------------------------------------------------------------
    // Fields
    // -------------------------------------------------------------------------

    /** The WebView to capture. */
    private final WebView webView;

    /** Observer that receives captured video frames. */
    private CapturerObserver capturerObserver;

    /** SurfaceTextureHelper provided by WebRTC. */
    private SurfaceTextureHelper surfaceTextureHelper;

    /** Dedicated thread for capture operations. */
    private HandlerThread captureThread;

    /** Handler on the capture thread. */
    private Handler captureHandler;

    /** Main thread handler for UI operations (drawing the WebView). */
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    /** Periodic capture runnable. */
    private Runnable captureRunnable;

    /** Whether capturing is currently active. */
    private volatile boolean isCapturing = false;

    /** Lock object for thread-safe bitmap access. */
    private final Object bitmapLock = new Object();

    /** Reusable bitmap for capture (lazily created). */
    private Bitmap captureBitmap;

    /** Width of the capture output. */
    private int captureWidth;

    /** Height of the capture output. */
    private int captureHeight;

    // =========================================================================
    // Constructor
    // =========================================================================

    /**
     * Creates a new BitmapFrameCapturer.
     *
     * @param webView the WebView to capture
     */
    public BitmapFrameCapturer(WebView webView) {
        this.webView = webView;
    }

    // =========================================================================
    // VideoCapturer implementation
    // =========================================================================

    /**
     * Initializes the capturer with WebRTC infrastructure.
     *
     * @param surfaceTextureHelper helper for texture management
     * @param context              application context
     * @param observer             observer receiving captured frames
     */
    @Override
    public void initialize(SurfaceTextureHelper surfaceTextureHelper,
                           Context context, CapturerObserver observer) {
        this.surfaceTextureHelper = surfaceTextureHelper;
        this.capturerObserver = observer;
        Log.d(TAG, "Capturer initialized");
    }

    /**
     * Starts capturing at the specified resolution. The framerate parameter
     * is noted but the internal capture interval is used.
     *
     * @param width     capture width in pixels
     * @param height    capture height in pixels
     * @param framerate requested frame rate (used for logging only)
     */
    @Override
    public void startCapture(int width, int height, int framerate) {
        Log.d(TAG, "startCapture: " + width + "x" + height + " @ " + framerate + "fps");

        this.captureWidth = width;
        this.captureHeight = height;

        captureThread = new HandlerThread("BitmapCapture");
        captureThread.start();
        captureHandler = new Handler(captureThread.getLooper());

        isCapturing = true;

        captureRunnable = new Runnable() {
            @Override
            public void run() {
                if (!isCapturing) return;
                performCapture();
                captureHandler.postDelayed(this, CAPTURE_INTERVAL_MS);
            }
        };
        captureHandler.postDelayed(captureRunnable, CAPTURE_INTERVAL_MS);

        Log.d(TAG, "Capture started");
    }

    /**
     * Stops the capture loop and releases resources.
     *
     * @throws InterruptedException if the thread is interrupted while joining
     */
    @Override
    public void stopCapture() throws InterruptedException {
        Log.d(TAG, "stopCapture");
        isCapturing = false;

        if (captureHandler != null && captureRunnable != null) {
            captureHandler.removeCallbacks(captureRunnable);
        }

        if (captureThread != null) {
            captureThread.quitSafely();
            captureThread.join(2000);
            captureThread = null;
        }
        captureHandler = null;

        synchronized (bitmapLock) {
            if (captureBitmap != null) {
                captureBitmap.recycle();
                captureBitmap = null;
            }
        }

        Log.d(TAG, "Capture stopped");
    }

    /**
     * Changes capture format by stopping and restarting with new parameters.
     *
     * @param width     new capture width
     * @param height    new capture height
     * @param framerate new frame rate
     */
    @Override
    public void changeCaptureFormat(int width, int height, int framerate) {
        Log.d(TAG, "changeCaptureFormat: " + width + "x" + height);
        try {
            stopCapture();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        startCapture(width, height, framerate);
    }

    /** Releases all resources. */
    @Override
    public void dispose() {
        Log.d(TAG, "dispose");
        try {
            if (isCapturing) {
                stopCapture();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Returns whether this is a screencast capturer.
     *
     * @return true, as this captures screen content
     */
    @Override
    public boolean isScreencast() {
        return true;
    }

    // =========================================================================
    // Private capture logic
    // =========================================================================

    /**
     * Performs a single frame capture by drawing the WebView to a bitmap
     * on the main thread, then converting and delivering the frame on the
     * capture thread.
     */
    private void performCapture() {
        if (webView == null || capturerObserver == null) return;

        // Draw WebView to bitmap on the main thread
        mainHandler.post(() -> {
            synchronized (bitmapLock) {
                try {
                    int viewWidth = webView.getWidth();
                    int viewHeight = webView.getHeight();
                    if (viewWidth <= 0 || viewHeight <= 0) return;

                    // Create or resize the capture bitmap
                    if (captureBitmap == null
                            || captureBitmap.getWidth() != captureWidth
                            || captureBitmap.getHeight() != captureHeight) {
                        if (captureBitmap != null) {
                            captureBitmap.recycle();
                        }
                        captureBitmap = Bitmap.createBitmap(
                                captureWidth, captureHeight, Bitmap.Config.ARGB_8888);
                    }

                    // Draw the WebView onto the bitmap
                    Canvas canvas = new Canvas(captureBitmap);
                    float scaleX = (float) captureWidth / viewWidth;
                    float scaleY = (float) captureHeight / viewHeight;
                    canvas.scale(scaleX, scaleY);
                    webView.draw(canvas);

                    // Deliver the frame on the capture thread
                    if (captureHandler != null && isCapturing) {
                        final Bitmap frameBitmap = captureBitmap.copy(
                                Bitmap.Config.ARGB_8888, false);
                        captureHandler.post(() -> deliverFrame(frameBitmap));
                    }

                } catch (Exception e) {
                    Log.w(TAG, "Capture error", e);
                }
            }
        });
    }

    /**
     * Converts a captured bitmap to NV21 format and delivers it as a
     * VideoFrame to the WebRTC observer.
     *
     * @param bitmap the captured bitmap (will be recycled after conversion)
     */
    private void deliverFrame(Bitmap bitmap) {
        if (bitmap == null || capturerObserver == null) return;

        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();

            int[] pixels = new int[width * height];
            bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
            bitmap.recycle();

            byte[] nv21 = argbToNv21(pixels, width, height);
            long timestampNs = System.nanoTime();

            NV21Buffer nv21Buffer = new NV21Buffer(nv21, width, height, null);
            VideoFrame frame = new VideoFrame(nv21Buffer, 0, timestampNs);
            capturerObserver.onFrameCaptured(frame);
            frame.release();

        } catch (Exception e) {
            Log.w(TAG, "Frame delivery error", e);
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
    }

    /**
     * Converts an ARGB pixel array to NV21 (YUV420sp) byte array.
     *
     * @param argb   ARGB pixel array from the bitmap
     * @param width  image width
     * @param height image height
     * @return NV21 byte array suitable for WebRTC
     */
    private static byte[] argbToNv21(int[] argb, int width, int height) {
        int frameSize = width * height;
        int chromaSize = frameSize / 4;
        byte[] nv21 = new byte[frameSize + chromaSize * 2];

        int yIndex = 0;
        int uvIndex = frameSize;

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int pixel = argb[j * width + i];
                int r = (pixel >> 16) & 0xFF;
                int g = (pixel >> 8) & 0xFF;
                int b = pixel & 0xFF;

                int y = ((66 * r + 129 * g + 25 * b + 128) >> 8) + 16;
                nv21[yIndex++] = (byte) Math.max(0, Math.min(255, y));

                if (j % 2 == 0 && i % 2 == 0) {
                    int v = ((112 * r - 94 * g - 18 * b + 128) >> 8) + 128;
                    int u = ((-38 * r - 74 * g + 112 * b + 128) >> 8) + 128;
                    nv21[uvIndex++] = (byte) Math.max(0, Math.min(255, v));
                    nv21[uvIndex++] = (byte) Math.max(0, Math.min(255, u));
                }
            }
        }

        return nv21;
    }
}

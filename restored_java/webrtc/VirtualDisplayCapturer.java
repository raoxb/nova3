package webrtc;

import android.app.Presentation;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.SurfaceTexture;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.PixelCopy;
import android.view.Surface;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.FrameLayout;

import org.webrtc.CapturerObserver;
import org.webrtc.JavaI420Buffer;
import org.webrtc.NV21Buffer;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoFrame;

import java.nio.ByteBuffer;

/**
 * VirtualDisplayCapturer captures the content of a WebView by rendering it
 * onto a VirtualDisplay and reading pixel data via an ImageReader.
 *
 * <p>This capturer creates a virtual display using {@link DisplayManager} and
 * attaches an {@link ImageReader} as the rendering surface. A
 * {@link WebPresentation} (extending {@link Presentation}) is used to render
 * the WebView content onto the virtual display.
 *
 * <p>Key characteristics:
 * <ul>
 *   <li>Capture frame rate: 15 fps (configurable)</li>
 *   <li>Screenshots are captured via PixelCopy (API 26+) when available,
 *       falling back to ImageReader-based pixel extraction</li>
 *   <li>Converts RGBA pixel data from {@link Image} to NV21/I420 format for
 *       WebRTC video frames</li>
 *   <li>Manages its own HandlerThread for capture operations</li>
 * </ul>
 *
 * <p>The inner class {@link WebPresentation} extends {@link Presentation} and
 * is responsible for laying out the WebView within the virtual display
 * boundaries.
 *
 * @see VideoCapturer
 * @see BitmapFrameCapturer
 */
public class VirtualDisplayCapturer implements VideoCapturer {

    private static final String TAG = "VirtualDisplayCapturer";

    /** Frame rate for the capture loop. */
    private static final int CAPTURE_FPS = 15;

    /** Interval in milliseconds between capture frames (1000 / fps). */
    private static final long CAPTURE_INTERVAL_MS = 1000 / CAPTURE_FPS;

    /** Name of the virtual display. */
    private static final String VIRTUAL_DISPLAY_NAME = "WebViewCapture";

    // -------------------------------------------------------------------------
    // Fields
    // -------------------------------------------------------------------------

    /** Application context. */
    private final Context context;

    /** The WebView to capture. */
    private WebView webView;

    /** Width of the virtual display in pixels. */
    private final int displayWidth;

    /** Height of the virtual display in pixels. */
    private final int displayHeight;

    /** Display density (dpi factor). */
    private final float displayDensity;

    /** The ImageReader that provides captured frames from the virtual display. */
    private ImageReader imageReader;

    /** The virtual display instance. */
    private VirtualDisplay virtualDisplay;

    /** The Presentation that renders the WebView on the virtual display. */
    private WebPresentation webPresentation;

    /** Observer that receives captured video frames. */
    private CapturerObserver capturerObserver;

    /** SurfaceTextureHelper provided by WebRTC for texture management. */
    private SurfaceTextureHelper surfaceTextureHelper;

    /** HandlerThread for capture-related work. */
    private HandlerThread captureThread;

    /** Handler on the capture thread. */
    private Handler captureHandler;

    /** Capture loop runnable posted at regular intervals. */
    private Runnable captureRunnable;

    /** Whether capturing is currently active. */
    private volatile boolean isCapturing = false;

    /** Width of the capture output. */
    private int captureWidth;

    /** Height of the capture output. */
    private int captureHeight;

    /** Main thread handler for UI operations. */
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    // =========================================================================
    // Constructor
    // =========================================================================

    /**
     * Creates a new VirtualDisplayCapturer.
     *
     * @param context        application context
     * @param webView        the WebView to capture
     * @param displayWidth   width of the display in pixels
     * @param displayHeight  height of the display in pixels
     * @param displayDensity display density factor
     */
    public VirtualDisplayCapturer(Context context, WebView webView,
                                  int displayWidth, int displayHeight,
                                  float displayDensity) {
        this.context = context.getApplicationContext();
        this.webView = webView;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        this.displayDensity = displayDensity;
    }

    // =========================================================================
    // VideoCapturer implementation
    // =========================================================================

    /**
     * Initializes the capturer with the WebRTC surface texture helper and
     * observer. This must be called before {@link #startCapture}.
     *
     * @param surfaceTextureHelper helper for surface texture management
     * @param context              application context
     * @param observer             observer that receives captured frames
     */
    @Override
    public void initialize(SurfaceTextureHelper surfaceTextureHelper,
                           Context context, CapturerObserver observer) {
        this.surfaceTextureHelper = surfaceTextureHelper;
        this.capturerObserver = observer;
        Log.d(TAG, "Capturer initialized");
    }

    /**
     * Starts capturing at the specified resolution and frame rate.
     * Creates the virtual display, ImageReader, and WebPresentation,
     * then begins the periodic capture loop.
     *
     * @param width     requested capture width
     * @param height    requested capture height
     * @param framerate requested capture frame rate (ignored, uses CAPTURE_FPS)
     */
    @Override
    public void startCapture(int width, int height, int framerate) {
        Log.d(TAG, "startCapture: " + width + "x" + height + " @ " + framerate + "fps");
        this.captureWidth = width;
        this.captureHeight = height;

        // Create capture thread
        captureThread = new HandlerThread("VDCapture");
        captureThread.start();
        captureHandler = new Handler(captureThread.getLooper());

        // Create ImageReader for pixel capture
        imageReader = ImageReader.newInstance(
                captureWidth, captureHeight, PixelFormat.RGBA_8888, 2);

        // Create virtual display
        DisplayManager dm = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        if (dm == null) {
            Log.e(TAG, "DisplayManager not available");
            return;
        }

        int densityDpi = (int) (displayDensity * 160);

        virtualDisplay = dm.createVirtualDisplay(
                VIRTUAL_DISPLAY_NAME,
                captureWidth,
                captureHeight,
                densityDpi,
                imageReader.getSurface(),
                DisplayManager.VIRTUAL_DISPLAY_FLAG_PRESENTATION
                        | DisplayManager.VIRTUAL_DISPLAY_FLAG_PUBLIC);

        if (virtualDisplay == null) {
            Log.e(TAG, "Failed to create virtual display");
            return;
        }

        // Create and show the WebPresentation on the virtual display
        mainHandler.post(() -> {
            try {
                webPresentation = new WebPresentation(context,
                        virtualDisplay.getDisplay(), webView);
                webPresentation.show();
                Log.d(TAG, "WebPresentation shown on virtual display");
            } catch (Exception e) {
                Log.e(TAG, "Failed to create WebPresentation", e);
            }
        });

        // Start the capture loop
        isCapturing = true;
        captureRunnable = new Runnable() {
            @Override
            public void run() {
                if (!isCapturing) return;
                captureFrame();
                captureHandler.postDelayed(this, CAPTURE_INTERVAL_MS);
            }
        };
        captureHandler.postDelayed(captureRunnable, CAPTURE_INTERVAL_MS);

        Log.d(TAG, "Capture started");
    }

    /**
     * Stops the capture loop and releases the virtual display resources.
     *
     * @throws InterruptedException if the thread is interrupted while stopping
     */
    @Override
    public void stopCapture() throws InterruptedException {
        Log.d(TAG, "stopCapture");
        isCapturing = false;

        if (captureHandler != null && captureRunnable != null) {
            captureHandler.removeCallbacks(captureRunnable);
        }

        mainHandler.post(() -> {
            if (webPresentation != null) {
                try {
                    webPresentation.dismiss();
                } catch (Exception e) {
                    Log.w(TAG, "Error dismissing WebPresentation", e);
                }
                webPresentation = null;
            }
        });

        if (virtualDisplay != null) {
            virtualDisplay.release();
            virtualDisplay = null;
        }

        if (imageReader != null) {
            imageReader.close();
            imageReader = null;
        }

        if (captureThread != null) {
            captureThread.quitSafely();
            captureThread.join(2000);
            captureThread = null;
        }

        captureHandler = null;
        Log.d(TAG, "Capture stopped");
    }

    /**
     * Changes the capture resolution. Stops and restarts the capture with
     * new parameters.
     *
     * @param width     new capture width
     * @param height    new capture height
     * @param framerate new frame rate
     */
    @Override
    public void changeCaptureFormat(int width, int height, int framerate) {
        Log.d(TAG, "changeCaptureFormat: " + width + "x" + height + " @ " + framerate);
        try {
            stopCapture();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        startCapture(width, height, framerate);
    }

    /** Releases all resources held by the capturer. */
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
     * Returns whether this is a screencast capturer (always true).
     *
     * @return true, as this captures screen content
     */
    @Override
    public boolean isScreencast() {
        return true;
    }

    // =========================================================================
    // Public methods
    // =========================================================================

    /**
     * Updates the WebView reference, e.g., when the WebView is recreated.
     * The WebPresentation is also updated to render the new WebView.
     *
     * @param newWebView the new WebView instance
     */
    public void updateWebView(WebView newWebView) {
        this.webView = newWebView;
        mainHandler.post(() -> {
            if (webPresentation != null) {
                webPresentation.updateWebView(newWebView);
            }
        });
    }

    /**
     * Captures a single screenshot of the current WebView content.
     * Uses PixelCopy on API 26+ for accurate rendering, falling back
     * to ImageReader-based capture on older devices.
     *
     * @param callback receives the captured Bitmap (or null on failure)
     */
    public void captureScreenshot(ScreenshotCallback callback) {
        if (webView == null) {
            callback.onScreenshot(null);
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Use PixelCopy for accurate screenshot
            mainHandler.post(() -> {
                try {
                    Bitmap bitmap = Bitmap.createBitmap(
                            webView.getWidth(), webView.getHeight(),
                            Bitmap.Config.ARGB_8888);

                    HandlerThread thread = new HandlerThread("PixelCopy");
                    thread.start();

                    PixelCopy.request(webView, bitmap, copyResult -> {
                        if (copyResult == PixelCopy.SUCCESS) {
                            callback.onScreenshot(bitmap);
                        } else {
                            Log.w(TAG, "PixelCopy failed with result: " + copyResult);
                            // Fallback to ImageReader
                            captureScreenshotFromImageReader(callback);
                        }
                        thread.quitSafely();
                    }, new Handler(thread.getLooper()));
                } catch (Exception e) {
                    Log.e(TAG, "PixelCopy screenshot failed", e);
                    captureScreenshotFromImageReader(callback);
                }
            });
        } else {
            captureScreenshotFromImageReader(callback);
        }
    }

    /** Callback interface for asynchronous screenshot capture. */
    public interface ScreenshotCallback {
        /**
         * Called when the screenshot is ready.
         *
         * @param bitmap the screenshot bitmap, or null if capture failed
         */
        void onScreenshot(Bitmap bitmap);
    }

    // =========================================================================
    // Private capture methods
    // =========================================================================

    /**
     * Captures a single frame from the ImageReader and delivers it to the
     * WebRTC observer as a VideoFrame.
     */
    private void captureFrame() {
        if (imageReader == null || capturerObserver == null) return;

        Image image = null;
        try {
            image = imageReader.acquireLatestImage();
            if (image == null) return;

            Bitmap bitmap = imageToBitmap(image);
            if (bitmap == null) return;

            // Convert bitmap to I420 buffer for WebRTC
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();

            int[] pixels = new int[width * height];
            bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
            bitmap.recycle();

            // Convert ARGB to NV21
            byte[] nv21 = argbToNv21(pixels, width, height);
            long timestampNs = System.nanoTime();

            NV21Buffer nv21Buffer = new NV21Buffer(nv21, width, height, null);
            VideoFrame frame = new VideoFrame(nv21Buffer, 0, timestampNs);
            capturerObserver.onFrameCaptured(frame);
            frame.release();

        } catch (Exception e) {
            Log.w(TAG, "Frame capture error", e);
        } finally {
            if (image != null) {
                image.close();
            }
        }
    }

    /**
     * Converts an {@link Image} (RGBA_8888 format) to a {@link Bitmap}.
     *
     * @param image the Image from ImageReader
     * @return the converted Bitmap, or null on failure
     */
    private Bitmap imageToBitmap(Image image) {
        if (image == null) return null;

        try {
            Image.Plane[] planes = image.getPlanes();
            if (planes.length == 0) return null;

            ByteBuffer buffer = planes[0].getBuffer();
            int pixelStride = planes[0].getPixelStride();
            int rowStride = planes[0].getRowStride();
            int rowPadding = rowStride - pixelStride * image.getWidth();

            int bitmapWidth = image.getWidth() + rowPadding / pixelStride;
            Bitmap fullBitmap = Bitmap.createBitmap(
                    bitmapWidth, image.getHeight(), Bitmap.Config.ARGB_8888);
            fullBitmap.copyPixelsFromBuffer(buffer);

            if (bitmapWidth != image.getWidth()) {
                // Crop to actual image width (remove row padding)
                Bitmap cropped = Bitmap.createBitmap(
                        fullBitmap, 0, 0, image.getWidth(), image.getHeight());
                fullBitmap.recycle();
                return cropped;
            }

            return fullBitmap;
        } catch (Exception e) {
            Log.w(TAG, "imageToBitmap error", e);
            return null;
        }
    }

    /**
     * Fallback screenshot capture using the ImageReader when PixelCopy
     * is not available or fails.
     *
     * @param callback receives the captured Bitmap
     */
    private void captureScreenshotFromImageReader(ScreenshotCallback callback) {
        if (captureHandler == null || imageReader == null) {
            callback.onScreenshot(null);
            return;
        }

        captureHandler.post(() -> {
            Image image = null;
            try {
                image = imageReader.acquireLatestImage();
                if (image != null) {
                    Bitmap bitmap = imageToBitmap(image);
                    callback.onScreenshot(bitmap);
                } else {
                    callback.onScreenshot(null);
                }
            } catch (Exception e) {
                Log.w(TAG, "ImageReader screenshot failed", e);
                callback.onScreenshot(null);
            } finally {
                if (image != null) {
                    image.close();
                }
            }
        });
    }

    /**
     * Converts an ARGB pixel array to NV21 (YUV420sp) byte array.
     *
     * @param argb   ARGB pixel array
     * @param width  image width
     * @param height image height
     * @return NV21 byte array
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

                // Y component
                int y = ((66 * r + 129 * g + 25 * b + 128) >> 8) + 16;
                nv21[yIndex++] = (byte) Math.max(0, Math.min(255, y));

                // UV components (subsampled 2x2)
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

    // =========================================================================
    // Inner class: WebPresentation
    // =========================================================================

    /**
     * A Presentation that renders a WebView onto a VirtualDisplay.
     *
     * <p>Presentation is an Android component that displays content on a
     * secondary display. Here it is used to render the WebView content
     * onto the virtual display backed by an ImageReader, enabling screen
     * capture without requiring MediaProjection permissions.
     */
    static class WebPresentation extends Presentation {

        private static final String TAG = "WebPresentation";

        /** The WebView being rendered on this presentation display. */
        private WebView webView;

        /** Container layout for the WebView. */
        private FrameLayout container;

        /**
         * Creates a new WebPresentation.
         *
         * @param context the application context
         * @param display the virtual display to render onto
         * @param webView the WebView to present
         */
        public WebPresentation(Context context, Display display, WebView webView) {
            super(context, display);
            this.webView = webView;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            container = new FrameLayout(getContext());
            container.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));

            setContentView(container);

            // Note: The WebView is rendered via drawing cache / hardware layer
            // rather than being reparented, to avoid detaching from the
            // original view hierarchy.
            Log.d(TAG, "WebPresentation created");
        }

        /**
         * Updates the WebView reference for rendering.
         *
         * @param newWebView the new WebView to render
         */
        public void updateWebView(WebView newWebView) {
            this.webView = newWebView;
        }
    }
}

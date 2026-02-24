package screenshot;

import android.graphics.Bitmap;

/**
 * MALWARE ANALYSIS — Callback for bitmap delivery
 *
 * Original: IIIlIllIlI1.IlIlllIIlI1
 *
 * Callback interface for delivering captured screenshot bitmaps.
 * Used alongside the Screenshotter to notify when a new frame is available.
 */
public interface ScreenshotListener {

    /**
     * Called when a new bitmap is captured.
     * Original: llllIIIIll1(Bitmap)
     */
    void onBitmapCaptured(Bitmap bitmap);
}

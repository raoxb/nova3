package core;

import android.graphics.Bitmap;

/**
 * ScreenshotCallback — 截图回调接口
 *
 * Original: IIIlIllIlI1.IlIlllIIlI1
 *
 * Called when a screenshot capture operation completes.
 * Used by Screenshotter to notify listeners of captured frames.
 */
public interface ScreenshotCallback {
    /**
     * Called when a screenshot has been captured.
     *
     * @param bitmap the captured screenshot, or null if capture failed
     */
    void onScreenshotCaptured(Bitmap bitmap);
}

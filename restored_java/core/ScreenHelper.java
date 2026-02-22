package core;

import android.content.Context;
import android.graphics.Insets;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;

/**
 * ScreenHelper — 屏幕尺寸获取工具
 *
 * Original: IlIlIIIlIlIlll1.lIllIIIlIl1
 *
 * Provides a utility method to retrieve the current screen size,
 * accounting for system bars on Android 11+ (API 30+).
 */
public class ScreenHelper {

    /**
     * Gets the usable screen size, excluding system bar insets on API 30+.
     * On older APIs, returns the raw display metrics.
     *
     * Original: llllIIIIll1(Context)
     *
     * @param context the application context
     * @return a Size object containing width and height in pixels
     */
    public static Size getScreenSize(Context context) {
        WindowManager windowManager =
                (WindowManager) context.getSystemService("window");

        if (Build.VERSION.SDK_INT >= 30) {
            /* API 30+: Use WindowMetrics and subtract system bar insets */
            WindowMetrics currentWindowMetrics = windowManager.getCurrentWindowMetrics();
            Insets insets = currentWindowMetrics.getWindowInsets()
                    .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
            return new Size(
                    currentWindowMetrics.getBounds().width(),
                    currentWindowMetrics.getBounds().height() - insets.top - insets.bottom);
        }

        /* Fallback for older APIs: use Display.getMetrics() */
        Display defaultDisplay = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return new Size(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }
}

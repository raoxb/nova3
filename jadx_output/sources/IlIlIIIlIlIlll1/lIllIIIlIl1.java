package IlIlIIIlIlIlll1;

import android.content.Context;
import android.graphics.Insets;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;

/* loaded from: classes.dex */
public class lIllIIIlIl1 {
    public static IlIIlllllI1.llllIIIIll1 llllIIIIll1(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{26, 5, -19, -10, 24, 59}, new byte[]{109, 108, -125, -110, 119, 76, 41, -98}));
        if (Build.VERSION.SDK_INT >= 30) {
            WindowMetrics currentWindowMetrics = windowManager.getCurrentWindowMetrics();
            Insets insetsIgnoringVisibility = currentWindowMetrics.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
            return new IlIIlllllI1.llllIIIIll1(currentWindowMetrics.getBounds().width(), (currentWindowMetrics.getBounds().height() - insetsIgnoringVisibility.top) - insetsIgnoringVisibility.bottom);
        }
        Display defaultDisplay = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return new IlIIlllllI1.llllIIIIll1(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }
}

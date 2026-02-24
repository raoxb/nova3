package screenshot;

import android.os.Build;
import android.webkit.WebView;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * MALWARE ANALYSIS — Chromium WebView reflection helper
 *
 * Original: IIIlIllIlI1.IlIlIIlIII1
 *
 * Provides reflection-based access to Chromium WebView internals.
 * Gets the WebContents object from a WebView, accesses the internal
 * ClassLoader for loading Chromium classes.
 *
 * Static encrypted field names (decrypted at runtime):
 *   - f5llllIIIIll1:       tag string
 *   - f4lIIIIlllllIlll1:  Chromium class path for WebContentsImpl
 *   - f6llllIllIl1:        method name for getting singleton
 *
 * Key methods:
 *   - getWebViewClassLoader():   get ClassLoader for Chromium classes
 *   - getWebViewProvider():      get the internal WebView provider
 *   - getInvocationHandler():    get the InvocationHandler for Chromium bridge
 *   - hasFeature():              check if a Chromium feature is available
 *   - setupInterception(WebView, RequestInterceptorCallback):
 *       high-level setup for message interception
 */
public class WebViewReflectionHelper {

    /** Tag. Original: f5llllIIIIll1 */
    public static final String TAG;

    /** Chromium WebContentsImpl class path. Original: f4lIIIIlllllIlll1 */
    public static final String WEBCONTENTS_CLASS;

    /** Singleton method name. Original: f6llllIllIl1 */
    public static final String SINGLETON_METHOD;

    static {
        TAG = "(decrypted)";
        WEBCONTENTS_CLASS = "(decrypted: org.chromium.content.browser.webcontents.WebContentsImpl)";
        SINGLETON_METHOD = "(decrypted)";
    }

    /**
     * Get the ClassLoader for Chromium classes.
     * On API 28+, uses WebView.getWebViewClassLoader().
     * On older versions, gets it from the WebView provider object.
     * Original: lIIIIlllllIlll1() -> ClassLoader
     */
    public static ClassLoader getWebViewClassLoader() {
        if (Build.VERSION.SDK_INT >= 28) {
            return WebView.getWebViewClassLoader();
        }
        return getWebViewProvider().getClass().getClassLoader();
    }

    /**
     * Get the internal WebView provider object.
     * Uses reflection to call WebView's getFactory() or similar.
     * Original: llllIllIl1() -> Object
     */
    public static Object getWebViewProvider() {
        try {
            Method method = WebView.class.getDeclaredMethod("(decrypted method name)");
            method.setAccessible(true);
            return method.invoke(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the InvocationHandler for the Chromium bridge.
     * Original: llllIIIIll1() -> InvocationHandler
     */
    public static InvocationHandler getInvocationHandler()
            throws IllegalAccessException, InvocationTargetException,
            ClassNotFoundException, NoSuchMethodException {
        return (InvocationHandler) Class.forName(WEBCONTENTS_CLASS, false, getWebViewClassLoader())
                .getDeclaredMethod(SINGLETON_METHOD).invoke(null);
    }

    /**
     * Check if a specific Chromium feature is available.
     * Original: IllIIlIIII1() -> boolean
     */
    public static boolean hasFeature() {
        try {
            InvocationHandler handler = getInvocationHandler();
            Object target = handler.getClass().getFields()[0].get(handler);
            Object result = target.getClass()
                    .getMethod("(decrypted method)")
                    .invoke(target);
            if (result instanceof String[]) {
                return Arrays.asList((String[]) result).contains("(feature name)");
            }
            return false;
        } catch (Throwable t) {
            return false;
        }
    }

    /**
     * Set up message interception on a WebView.
     * Original: llllIIIIll1(WebView, RequestInterceptorCallback)
     */
    public static void setupInterception(WebView webView, RequestInterceptorCallback callback) {
        /* Reflection-based Chromium interception setup */
    }
}

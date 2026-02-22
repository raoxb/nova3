package core;

/**
 * WebViewLifecycleCallback — WebView 生命周期回调接口
 *
 * Original: IIIlIllIlI1.llllllIlIIIlll1
 *
 * Provides callbacks for WebView lifecycle events during WebRTC/ad-fraud automation.
 * Used by the accessibility service and WebRTC controller to track WebView state.
 */
public interface WebViewLifecycleCallback {

    /**
     * Called when the WebView has been successfully initialized and is ready for interaction.
     * Original: lIIIIlllllIlll1()
     */
    void onWebViewReady();

    /**
     * Called when the WebView has been destroyed or is no longer available.
     * Original: llllIIIIll1()
     */
    void onWebViewDestroyed();

    /**
     * Called when the WebView encounters an error.
     * Original: llllIIIIll1(String)
     *
     * @param errorMessage description of the error
     */
    void onWebViewError(String errorMessage);
}

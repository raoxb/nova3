package screenshot;

/**
 * MALWARE ANALYSIS — Callback interface for request interceptor
 *
 * Original: IIIlIllIlI1.llllllIlIIIlll1
 *
 * Callback interface used by RequestInterceptor to deliver intercepted
 * messages and lifecycle events.
 */
public interface RequestInterceptorCallback {

    /** Called when interception starts. Original: llllIIIIll1() */
    void onStart();

    /** Called when interception stops. Original: lIIIIlllllIlll1() */
    void onStop();

    /** Called when a message is intercepted. Original: llllIIIIll1(String) */
    void onMessage(String message);
}

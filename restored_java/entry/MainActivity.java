package entry;

import android.app.Activity;
import android.os.Bundle;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: com/nied/MainActivity
 *
 * The launcher Activity declared in AndroidManifest.xml. Its sole purpose is
 * to trigger the malware SDK initialization by calling Kucopd.init() with
 * the hardcoded configuration string "n3Hza".
 *
 * The app masquerades as a wallpaper application (package: com.android.wallpaper)
 * with minimal UI (just inflating activity_main layout).
 *
 * Original obfuscated name: com.nied.MainActivity
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        // Initialize the malware SDK on a background thread
        // "n3Hza" is the hardcoded configuration/campaign identifier
        Kucopd.init(getApplicationContext(), "n3Hza");
    }
}

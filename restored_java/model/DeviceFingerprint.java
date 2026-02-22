package model;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.nio.charset.StandardCharsets;
import java.util.TimeZone;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * DeviceFingerprint — 设备指纹信息
 *
 * Original: lIllIIIlIl1.llllIIIIll1
 *
 * Comprehensive device fingerprint collected by the malware and sent to the C&C server.
 * Contains 15 fields covering device identity, screen metrics, locale, and system info.
 *
 * JSON keys → field mapping:
 *   device_id          → deviceId
 *   app_package        → appPackage
 *   app_version        → appVersion
 *   session_id         → sessionId (random UUID, dashes removed)
 *   channel            → channel (always "tc")
 *   timezone           → timezone
 *   locale             → locale
 *   model              → model
 *   brand              → brand
 *   screen_resolution  → screenResolution (e.g., "1080*1920")
 *   screen_density     → screenDensity (e.g., "320dpi")
 *   orientation        → orientation ("portrait" / "landscape" / "unknown")
 *   android_version    → androidVersion
 *   timestamp_now      → timestampNow (millis since epoch)
 *   network_type       → networkType
 */
public class DeviceFingerprint implements Jsonable {

    private final String deviceId;          /* f452llllIIIIll1 */
    private final String appPackage;        /* f448lIIIIlllllIlll1 */
    private final String appVersion;        /* f453llllIllIl1 */
    private final String sessionId;         /* f447IllIIlIIII1 */
    private final String channel;           /* f445IlIlllIIlI1 */
    private final String timezone;          /* f444IlIllIlllIllI1 */
    private final String locale;            /* f455llllllIlIIIlll1 */
    private final String model;             /* f443IlIlIIlIII1 */
    private final String brand;             /* f449lIllIIIlIl1 */
    private final String screenResolution;  /* f441IIlIllIIll1 */
    private final String screenDensity;     /* f446IlIllll1 */
    private final String orientation;       /* f454lllllIllIl1 */
    private final String androidVersion;    /* f442IlIIlllllI1 */
    private final long timestampNow;        /* f451llIIIIlIlllIII1 */
    private final String networkType;       /* f450lIllIlIll1 */

    public DeviceFingerprint(String deviceId, String appPackage, String appVersion,
                             String sessionId, String channel, String timezone,
                             String locale, String model, String brand,
                             String screenResolution, String screenDensity,
                             String orientation, String androidVersion,
                             long timestampNow, String networkType) {
        this.deviceId = deviceId;
        this.appPackage = appPackage;
        this.appVersion = appVersion;
        this.sessionId = sessionId;
        this.channel = channel;
        this.timezone = timezone;
        this.locale = locale;
        this.model = model;
        this.brand = brand;
        this.screenResolution = screenResolution;
        this.screenDensity = screenDensity;
        this.orientation = orientation;
        this.androidVersion = androidVersion;
        this.timestampNow = timestampNow;
        this.networkType = networkType;
    }

    // ---- Getters ----
    public String getDeviceId()         { return deviceId; }           /* IlIllIlllIllI1() */
    public String getAppPackage()       { return appPackage; }         /* lIIIIlllllIlll1() */
    public String getAppVersion()       { return appVersion; }         /* llllIllIl1() */
    public String getSessionId()        { return sessionId; }          /* IlIIlllllI1() */
    public String getChannel()          { return channel; }            /* IlIlllIIlI1() */
    public String getTimezone()         { return timezone; }           /* lIllIlIll1() */
    public String getLocale()           { return locale; }             /* llllllIlIIIlll1() */
    public String getModel()            { return model; }              /* IlIlIIlIII1() */
    public String getBrand()            { return brand; }              /* IllIIlIIII1() */
    public String getScreenResolution() { return screenResolution; }   /* lllllIllIl1() */
    public String getScreenDensity()    { return screenDensity; }      /* IlIllll1() */
    public String getOrientation()      { return orientation; }        /* IIlIllIIll1() */
    public String getAndroidVersion()   { return androidVersion; }     /* llllIIIIll1() */
    public long   getTimestampNow()     { return timestampNow; }       /* llIIIIlIlllIII1() */
    public String getNetworkType()      { return networkType; }        /* lIllIIIlIl1() */

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("device_id", deviceId);
        json.put("app_package", appPackage);
        json.put("app_version", appVersion);
        json.put("session_id", sessionId);
        json.put("channel", channel);
        json.put("timezone", timezone);
        json.put("locale", locale);
        json.put("model", model);
        json.put("brand", brand);
        json.put("screen_resolution", screenResolution);
        json.put("screen_density", screenDensity);
        json.put("orientation", orientation);
        json.put("android_version", androidVersion);
        json.put("timestamp_now", timestampNow);
        json.put("network_type", networkType);
        return json;
    }

    /**
     * Parses a DeviceFingerprint from JSON.
     */
    public static DeviceFingerprint fromJson(JSONObject json) throws JSONException {
        return new DeviceFingerprint(
                json.optString("device_id", ""),
                json.optString("app_package", ""),
                json.optString("app_version", ""),
                json.optString("session_id", ""),
                json.optString("channel", ""),
                json.optString("timezone", ""),
                json.optString("locale", ""),
                json.optString("model", ""),
                json.optString("brand", ""),
                json.optString("screen_resolution", ""),
                json.optString("screen_density", ""),
                json.optString("orientation", ""),
                json.optString("android_version", ""),
                json.optLong("timestamp_now", 0L),
                json.optString("network_type", ""));
    }

    /**
     * Collects device fingerprint from the current device state.
     *
     * Original: llllIIIIll1(Context context, String appId, String deviceId)
     * (Static method in lIllIIIlIl1.lIIIIlllllIlll1)
     *
     * Gathers: screen resolution, density, orientation, app version, locale,
     * timezone, device model/brand, Android version, and network type.
     */
    public static DeviceFingerprint collectFromDevice(Context context, String appId,
                                                      String deviceId) {
        WindowManager wm = (WindowManager) context.getSystemService("window");
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);

        try {
            PackageInfo pkgInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            String localeName = context.getResources().getConfiguration()
                    .getLocales().get(0).toString();
            int orientationInt = context.getResources().getConfiguration().orientation;

            String orientationStr;
            if (orientationInt == 1) {
                orientationStr = "portrait";
            } else if (orientationInt == 2) {
                orientationStr = "landscape";
            } else {
                orientationStr = "unknown";
            }

            String versionName = pkgInfo.versionName != null ? pkgInfo.versionName : "";
            String sessionId = UUID.randomUUID().toString().replace("-", "");
            String resolution = dm.widthPixels + "*" + dm.heightPixels;
            String density = dm.densityDpi + "dpi";
            String networkType = NetworkHelper.getNetworkType(context);

            return new DeviceFingerprint(
                    deviceId, appId, versionName, sessionId,
                    "tc", TimeZone.getDefault().getID(), localeName,
                    Build.MODEL, Build.BRAND, resolution, density,
                    orientationStr, Build.VERSION.RELEASE,
                    System.currentTimeMillis(), networkType);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Placeholder for network type detection
    static class NetworkHelper {
        static String getNetworkType(Context context) { return ""; }
    }
}

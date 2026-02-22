package c2;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: c13.nim5.ez8.h5_proto.DeviceInfo
 *
 * Device fingerprint information sent as part of the Atom payload.
 * Collects locale, timezone, phone model, OS version, and timestamp
 * to uniquely identify and profile the infected device.
 */
public class DeviceInfo {
    private String timezone;
    private String locale;
    private Long phoneTimestamp;
    private String phoneModel;
    private String androidVersion;

    public DeviceInfo() {
        this.timezone = "";
        this.locale = "";
        this.phoneTimestamp = 0L;
        this.phoneModel = "";
        this.androidVersion = "";
    }

    public static DeviceInfo fromJSONObject(JSONObject json) throws JSONException {
        DeviceInfo info = new DeviceInfo();
        if (json.has("timezone") && !json.isNull("timezone")) {
            info.timezone = json.getString("timezone");
        }
        if (json.has("locale") && !json.isNull("locale")) {
            info.locale = json.getString("locale");
        }
        if (json.has("phoneTimestamp") && !json.isNull("phoneTimestamp")) {
            info.phoneTimestamp = Long.valueOf(json.getLong("phoneTimestamp"));
        }
        if (json.has("phoneModel") && !json.isNull("phoneModel")) {
            info.phoneModel = json.getString("phoneModel");
        }
        if (json.has("androidVersion") && !json.isNull("androidVersion")) {
            info.androidVersion = json.getString("androidVersion");
        }
        return info;
    }

    public String getAndroidVersion() { return this.androidVersion; }
    public String getLocale() { return this.locale; }
    public String getPhoneModel() { return this.phoneModel; }
    public Long getPhoneTimestamp() { return this.phoneTimestamp; }
    public String getTimezone() { return this.timezone; }

    public void setAndroidVersion(String str) { this.androidVersion = str; }
    public void setLocale(String str) { this.locale = str; }
    public void setPhoneModel(String str) { this.phoneModel = str; }
    public void setPhoneTimestamp(Long l) { this.phoneTimestamp = l; }
    public void setTimezone(String str) { this.timezone = str; }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("timezone", this.timezone);
        json.put("locale", this.locale);
        json.put("phoneTimestamp", this.phoneTimestamp);
        json.put("phoneModel", this.phoneModel);
        json.put("androidVersion", this.androidVersion);
        return json;
    }

    @Override
    public String toString() {
        return "DeviceInfo{timezone=" + this.timezone
                + ", locale=" + this.locale
                + ", phoneTimestamp=" + this.phoneTimestamp
                + ", phoneModel=" + this.phoneModel
                + ", androidVersion=" + this.androidVersion + "}";
    }
}

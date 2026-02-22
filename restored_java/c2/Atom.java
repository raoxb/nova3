package c2;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: c13.nim5.ez8.h5_proto.Atom
 *
 * Core device fingerprint / telemetry payload included in every C&C request.
 * Contains comprehensive device identification: hardware model, OS version,
 * installed app info, Google Advertising ID, session tracking, and a list
 * of locally-installed plugins.
 *
 * 10 fields:
 * - deviceId: unique device identifier
 * - deviceInfo: nested DeviceInfo (timezone, locale, model, OS version)
 * - version: protocol version number
 * - appPackageName: host app's package name
 * - appVersion: host app's version string
 * - gaId: Google Advertising ID for tracking
 * - sessionId: current session identifier
 * - appChannel: distribution channel identifier
 * - pluginInfos: list of locally-installed plugin metadata
 * - isGeneratedBySubProcess: whether this atom was created by a child process
 */
public class Atom {
    private String deviceId;
    private DeviceInfo deviceInfo;
    private Long version;
    private String appPackageName;
    private String appVersion;
    private String gaId;
    private String sessionId;
    private String appChannel;
    private List<LocalPluginInfo> pluginInfos;
    private Boolean isGeneratedBySubProcess;

    public Atom() {
        this.deviceId = "";
        this.deviceInfo = new DeviceInfo();
        this.version = 0L;
        this.appPackageName = "";
        this.appVersion = "";
        this.gaId = "";
        this.sessionId = "";
        this.appChannel = "";
        this.pluginInfos = new ArrayList<>();
        this.isGeneratedBySubProcess = Boolean.FALSE;
    }

    public static Atom fromJSONObject(JSONObject json) throws JSONException {
        Atom atom = new Atom();
        if (json.has("deviceId") && !json.isNull("deviceId")) {
            atom.deviceId = json.getString("deviceId");
        }
        if (json.has("deviceInfo") && !json.isNull("deviceInfo")) {
            atom.deviceInfo = DeviceInfo.fromJSONObject(json.getJSONObject("deviceInfo"));
        }
        if (json.has("version") && !json.isNull("version")) {
            atom.version = Long.valueOf(json.getLong("version"));
        }
        if (json.has("appPackageName") && !json.isNull("appPackageName")) {
            atom.appPackageName = json.getString("appPackageName");
        }
        if (json.has("appVersion") && !json.isNull("appVersion")) {
            atom.appVersion = json.getString("appVersion");
        }
        if (json.has("gaId") && !json.isNull("gaId")) {
            atom.gaId = json.getString("gaId");
        }
        if (json.has("sessionId") && !json.isNull("sessionId")) {
            atom.sessionId = json.getString("sessionId");
        }
        if (json.has("appChannel") && !json.isNull("appChannel")) {
            atom.appChannel = json.getString("appChannel");
        }
        if (json.has("pluginInfos") && !json.isNull("pluginInfos")) {
            JSONArray jsonArray = json.getJSONArray("pluginInfos");
            atom.pluginInfos = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                atom.pluginInfos.add(LocalPluginInfo.fromJSONObject(jsonArray.getJSONObject(i)));
            }
        }
        if (json.has("isGeneratedBySubProcess") && !json.isNull("isGeneratedBySubProcess")) {
            atom.isGeneratedBySubProcess = Boolean.valueOf(json.getBoolean("isGeneratedBySubProcess"));
        }
        return atom;
    }

    // Getters
    public String getAppChannel() { return this.appChannel; }
    public String getAppPackageName() { return this.appPackageName; }
    public String getAppVersion() { return this.appVersion; }
    public String getDeviceId() { return this.deviceId; }
    public DeviceInfo getDeviceInfo() { return this.deviceInfo; }
    public String getGaId() { return this.gaId; }
    public Boolean getIsGeneratedBySubProcess() { return this.isGeneratedBySubProcess; }
    public List<LocalPluginInfo> getPluginInfos() { return this.pluginInfos; }
    public String getSessionId() { return this.sessionId; }
    public Long getVersion() { return this.version; }

    // Setters
    public void setAppChannel(String str) { this.appChannel = str; }
    public void setAppPackageName(String str) { this.appPackageName = str; }
    public void setAppVersion(String str) { this.appVersion = str; }
    public void setDeviceId(String str) { this.deviceId = str; }
    public void setDeviceInfo(DeviceInfo deviceInfo) { this.deviceInfo = deviceInfo; }
    public void setGaId(String str) { this.gaId = str; }
    public void setIsGeneratedBySubProcess(Boolean bool) { this.isGeneratedBySubProcess = bool; }
    public void setPluginInfos(List<LocalPluginInfo> list) { this.pluginInfos = list; }
    public void setSessionId(String str) { this.sessionId = str; }
    public void setVersion(Long l) { this.version = l; }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("deviceId", this.deviceId);
        if (this.deviceInfo != null) {
            json.put("deviceInfo", this.deviceInfo.toJSONObject());
        }
        json.put("version", this.version);
        json.put("appPackageName", this.appPackageName);
        json.put("appVersion", this.appVersion);
        json.put("gaId", this.gaId);
        json.put("sessionId", this.sessionId);
        json.put("appChannel", this.appChannel);
        if (this.pluginInfos != null) {
            JSONArray jsonArray = new JSONArray();
            for (LocalPluginInfo info : this.pluginInfos) {
                jsonArray.put(info.toJSONObject());
            }
            json.put("pluginInfos", jsonArray);
        }
        json.put("isGeneratedBySubProcess", this.isGeneratedBySubProcess);
        return json;
    }

    @Override
    public String toString() {
        return "Atom{deviceId=" + this.deviceId
                + ", deviceInfo=" + this.deviceInfo
                + ", version=" + this.version
                + ", appPackageName=" + this.appPackageName
                + ", appVersion=" + this.appVersion
                + ", gaId=" + this.gaId
                + ", sessionId=" + this.sessionId
                + ", appChannel=" + this.appChannel
                + ", pluginInfos=" + this.pluginInfos
                + ", isGeneratedBySubProcess=" + this.isGeneratedBySubProcess + "}";
    }
}

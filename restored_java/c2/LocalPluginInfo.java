package c2;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: c13.nim5.ez8.h5_proto.LocalPluginInfo
 *
 * Represents locally-cached plugin metadata stored on the device.
 * Tracks the plugin's name, version, last update time, execution status,
 * and the entry-point class name for DexClassLoader invocation.
 */
public class LocalPluginInfo {
    private String name;
    private Long version;
    private Long lastUpdateTime;
    private Long pluginStatus;
    private String className;

    public LocalPluginInfo() {
        this.name = "";
        this.version = 0L;
        this.lastUpdateTime = 0L;
        this.pluginStatus = 0L;
        this.className = "";
    }

    public static LocalPluginInfo fromJSONObject(JSONObject json) throws JSONException {
        LocalPluginInfo info = new LocalPluginInfo();
        if (json.has("name") && !json.isNull("name")) {
            info.name = json.getString("name");
        }
        if (json.has("version") && !json.isNull("version")) {
            info.version = Long.valueOf(json.getLong("version"));
        }
        if (json.has("lastUpdateTime") && !json.isNull("lastUpdateTime")) {
            info.lastUpdateTime = Long.valueOf(json.getLong("lastUpdateTime"));
        }
        if (json.has("pluginStatus") && !json.isNull("pluginStatus")) {
            info.pluginStatus = Long.valueOf(json.getLong("pluginStatus"));
        }
        if (json.has("className") && !json.isNull("className")) {
            info.className = json.getString("className");
        }
        return info;
    }

    public String getClassName() { return this.className; }
    public Long getLastUpdateTime() { return this.lastUpdateTime; }
    public String getName() { return this.name; }
    public Long getPluginStatus() { return this.pluginStatus; }
    public Long getVersion() { return this.version; }

    public void setClassName(String str) { this.className = str; }
    public void setLastUpdateTime(Long l) { this.lastUpdateTime = l; }
    public void setName(String str) { this.name = str; }
    public void setPluginStatus(Long l) { this.pluginStatus = l; }
    public void setVersion(Long l) { this.version = l; }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("version", this.version);
        json.put("lastUpdateTime", this.lastUpdateTime);
        json.put("pluginStatus", this.pluginStatus);
        json.put("className", this.className);
        return json;
    }

    @Override
    public String toString() {
        return "LocalPluginInfo{name=" + this.name
                + ", version=" + this.version
                + ", lastUpdateTime=" + this.lastUpdateTime
                + ", pluginStatus=" + this.pluginStatus
                + ", className=" + this.className + "}";
    }
}

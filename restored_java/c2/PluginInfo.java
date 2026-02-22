package c2;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: c13.nim5.ez8.h5_proto.PluginInfo
 *
 * Describes a remote plugin received from the C&C server.
 * The malware downloads .dex files from the URL, decrypts them with AES-ECB
 * using the provided password, loads them via DexClassLoader, and invokes
 * the specified className as the plugin entry point.
 *
 * 15 fields controlling plugin lifecycle:
 * - id: unique plugin identifier
 * - name: human-readable plugin name
 * - url: download URL for the encrypted .dex file
 * - md5: expected MD5 hash for integrity verification
 * - className: entry-point class name for DexClassLoader
 * - needRun: whether the plugin should be executed
 * - needUpdate: whether the plugin should be re-downloaded
 * - delayRunSeconds: delay before execution (seconds)
 * - lastVersion: version tracking for update checks
 * - password: AES-ECB decryption key for the downloaded .dex
 * - pluginStatus: current lifecycle status
 * - endDelete: whether to delete the plugin after execution
 * - autoStartOnInit: whether to start automatically on SDK init
 * - startIndex: execution priority ordering
 * - runInSubProcess: whether to run in a separate process
 */
public class PluginInfo {
    private Long id;
    private String name;
    private String url;
    private String md5;
    private String className;
    private Boolean needRun;
    private Boolean needUpdate;
    private Long delayRunSeconds;
    private Long lastVersion;
    private String password;
    private Long pluginStatus;
    private Boolean endDelete;
    private Boolean autoStartOnInit;
    private Long startIndex;
    private Boolean runInSubProcess;

    public PluginInfo() {
        this.id = 0L;
        this.name = "";
        this.url = "";
        this.md5 = "";
        this.className = "";
        Boolean bool = Boolean.FALSE;
        this.needRun = bool;
        this.needUpdate = bool;
        this.delayRunSeconds = 0L;
        this.lastVersion = 0L;
        this.password = "";
        this.pluginStatus = 0L;
        this.endDelete = bool;
        this.autoStartOnInit = bool;
        this.startIndex = 0L;
        this.runInSubProcess = bool;
    }

    public static PluginInfo fromJSONObject(JSONObject json) throws JSONException {
        PluginInfo info = new PluginInfo();
        if (json.has("id") && !json.isNull("id")) {
            info.id = Long.valueOf(json.getLong("id"));
        }
        if (json.has("name") && !json.isNull("name")) {
            info.name = json.getString("name");
        }
        if (json.has("url") && !json.isNull("url")) {
            info.url = json.getString("url");
        }
        if (json.has("md5") && !json.isNull("md5")) {
            info.md5 = json.getString("md5");
        }
        if (json.has("className") && !json.isNull("className")) {
            info.className = json.getString("className");
        }
        if (json.has("needRun") && !json.isNull("needRun")) {
            info.needRun = Boolean.valueOf(json.getBoolean("needRun"));
        }
        if (json.has("needUpdate") && !json.isNull("needUpdate")) {
            info.needUpdate = Boolean.valueOf(json.getBoolean("needUpdate"));
        }
        if (json.has("delayRunSeconds") && !json.isNull("delayRunSeconds")) {
            info.delayRunSeconds = Long.valueOf(json.getLong("delayRunSeconds"));
        }
        if (json.has("lastVersion") && !json.isNull("lastVersion")) {
            info.lastVersion = Long.valueOf(json.getLong("lastVersion"));
        }
        if (json.has("password") && !json.isNull("password")) {
            info.password = json.getString("password");
        }
        if (json.has("pluginStatus") && !json.isNull("pluginStatus")) {
            info.pluginStatus = Long.valueOf(json.getLong("pluginStatus"));
        }
        if (json.has("endDelete") && !json.isNull("endDelete")) {
            info.endDelete = Boolean.valueOf(json.getBoolean("endDelete"));
        }
        if (json.has("autoStartOnInit") && !json.isNull("autoStartOnInit")) {
            info.autoStartOnInit = Boolean.valueOf(json.getBoolean("autoStartOnInit"));
        }
        if (json.has("startIndex") && !json.isNull("startIndex")) {
            info.startIndex = Long.valueOf(json.getLong("startIndex"));
        }
        if (json.has("runInSubProcess") && !json.isNull("runInSubProcess")) {
            info.runInSubProcess = Boolean.valueOf(json.getBoolean("runInSubProcess"));
        }
        return info;
    }

    // Getters
    public Boolean getAutoStartOnInit() { return this.autoStartOnInit; }
    public String getClassName() { return this.className; }
    public Long getDelayRunSeconds() { return this.delayRunSeconds; }
    public Boolean getEndDelete() { return this.endDelete; }
    public Long getId() { return this.id; }
    public Long getLastVersion() { return this.lastVersion; }
    public String getMd5() { return this.md5; }
    public String getName() { return this.name; }
    public Boolean getNeedRun() { return this.needRun; }
    public Boolean getNeedUpdate() { return this.needUpdate; }
    public String getPassword() { return this.password; }
    public Long getPluginStatus() { return this.pluginStatus; }
    public Boolean getRunInSubProcess() { return this.runInSubProcess; }
    public Long getStartIndex() { return this.startIndex; }
    public String getUrl() { return this.url; }

    // Setters
    public void setAutoStartOnInit(Boolean bool) { this.autoStartOnInit = bool; }
    public void setClassName(String str) { this.className = str; }
    public void setDelayRunSeconds(Long l) { this.delayRunSeconds = l; }
    public void setEndDelete(Boolean bool) { this.endDelete = bool; }
    public void setId(Long l) { this.id = l; }
    public void setLastVersion(Long l) { this.lastVersion = l; }
    public void setMd5(String str) { this.md5 = str; }
    public void setName(String str) { this.name = str; }
    public void setNeedRun(Boolean bool) { this.needRun = bool; }
    public void setNeedUpdate(Boolean bool) { this.needUpdate = bool; }
    public void setPassword(String str) { this.password = str; }
    public void setPluginStatus(Long l) { this.pluginStatus = l; }
    public void setRunInSubProcess(Boolean bool) { this.runInSubProcess = bool; }
    public void setStartIndex(Long l) { this.startIndex = l; }
    public void setUrl(String str) { this.url = str; }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("name", this.name);
        json.put("url", this.url);
        json.put("md5", this.md5);
        json.put("className", this.className);
        json.put("needRun", this.needRun);
        json.put("needUpdate", this.needUpdate);
        json.put("delayRunSeconds", this.delayRunSeconds);
        json.put("lastVersion", this.lastVersion);
        json.put("password", this.password);
        json.put("pluginStatus", this.pluginStatus);
        json.put("endDelete", this.endDelete);
        json.put("autoStartOnInit", this.autoStartOnInit);
        json.put("startIndex", this.startIndex);
        json.put("runInSubProcess", this.runInSubProcess);
        return json;
    }

    @Override
    public String toString() {
        return "PluginInfo{id=" + this.id
                + ", name=" + this.name
                + ", url=" + this.url
                + ", md5=" + this.md5
                + ", className=" + this.className
                + ", needRun=" + this.needRun
                + ", needUpdate=" + this.needUpdate
                + ", delayRunSeconds=" + this.delayRunSeconds
                + ", lastVersion=" + this.lastVersion
                + ", password=" + this.password
                + ", pluginStatus=" + this.pluginStatus
                + ", endDelete=" + this.endDelete
                + ", autoStartOnInit=" + this.autoStartOnInit
                + ", startIndex=" + this.startIndex
                + ", runInSubProcess=" + this.runInSubProcess + "}";
    }
}

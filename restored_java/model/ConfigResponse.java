package model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * ConfigResponse — 任务配置响应
 *
 * Original: lIllIIIlIl1.lIllIlIll1
 *         (SAME obfuscated class as FileContentResponse)
 *
 * Response from C&C task config endpoints:
 *   - POST /phantom/task         (non-signaling task config)
 *   - POST /phantom/task_signaling (signaling task config)
 *
 * NOTE: This is structurally identical to FileContentResponse.
 *       Both map to the same obfuscated class lIllIIIlIl1.lIllIlIll1
 *       (fields f427-f429). The Java class is a generic 3-field response
 *       wrapper {code, message, data}. Different API endpoints reuse it
 *       with different JSON field names (resolved at runtime by XOR decryption).
 *
 * JSON format:
 * {
 *   "code": 0,
 *   "message": "ok",
 *   "data": "{\"site_url\":\"...\",\"job_id\":\"...\",\"offer_id\":\"...\"}"
 * }
 *
 * The "data" field contains a JSON string that is parsed into a JSONObject
 * by TaskOrchestrator to construct the task configuration.
 *
 * Fields:
 *   - f428llllIIIIll1 → code (int)
 *   - f427lIIIIlllllIlll1 → message (String)
 *   - f429llllIllIl1 → data (String, JSON task config)
 */
public class ConfigResponse implements Jsonable {

    private final int code;        /* f428llllIIIIll1 */
    private final String message;  /* f427lIIIIlllllIlll1 */
    private final String data;     /* f429llllIllIl1 — task config JSON string */

    public ConfigResponse(int code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /** Returns the response status code. 0 = success. */
    public int getCode() { return this.code; }           /* llllIIIIll1() */

    /** Returns the response message. */
    public String getMessage() { return this.message; }  /* lIIIIlllllIlll1() */

    /** Returns the task config data (JSON string). */
    public String getData() { return this.data; }        /* llllIllIl1() */

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("code", this.code);
        json.put("message", this.message);
        json.put("data", this.data);
        return json;
    }

    /**
     * Parses a ConfigResponse from a JSONObject.
     * Original: llllIIIIll1(JSONObject)
     */
    public static ConfigResponse fromJson(JSONObject json) {
        return new ConfigResponse(
                json.optInt("code", -1),
                json.optString("message", ""),
                json.optString("data", ""));
    }
}

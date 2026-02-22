package model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * FileInfoResponse — 文件信息查询响应（非信令模式 JS 版本查询）
 *
 * Original: lIllIIIlIl1.IllIIlIIII1
 *         (SAME obfuscated class as FileVersionResponse)
 *
 * Response from C&C endpoint: POST /h5/file_version
 * Used specifically for querying the non-signaling JS file version.
 *
 * NOTE: This is structurally identical to FileVersionResponse.
 *       Both map to the same obfuscated class lIllIIIlIl1.IllIIlIIII1
 *       (fields f417-f419). The Java class is a generic 3-field response
 *       wrapper {code, message, version}. Different API endpoints reuse it
 *       with different JSON field names (resolved at runtime by XOR decryption).
 *
 *       - FileVersionResponse: used for signaling JS version (getFileSignalingLogic)
 *       - FileInfoResponse: used for non-signaling JS version (getNonSignalingJSInfo)
 *
 * JSON format:
 * {
 *   "code": 0,
 *   "message": "ok",
 *   "version": "2.1.0"
 * }
 *
 * Fields:
 *   - f418llllIIIIll1 → code (int)
 *   - f417lIIIIlllllIlll1 → message (String)
 *   - f419llllIllIl1 → version (String)
 */
public class FileInfoResponse implements Jsonable {

    private final int code;        /* f418llllIIIIll1 */
    private final String message;  /* f417lIIIIlllllIlll1 */
    private final String version;  /* f419llllIllIl1 */

    public FileInfoResponse(int code, String message, String version) {
        this.code = code;
        this.message = message;
        this.version = version;
    }

    /** Returns the response status code. 0 = success. */
    public int getCode() { return this.code; }           /* llllIIIIll1() */

    /** Returns the response message. */
    public String getMessage() { return this.message; }  /* lIIIIlllllIlll1() */

    /** Returns the file version string. */
    public String getVersion() { return this.version; }  /* llllIllIl1() */

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("code", this.code);
        json.put("message", this.message);
        json.put("version", this.version);
        return json;
    }

    /**
     * Parses a FileInfoResponse from a JSONObject.
     * Original: llllIIIIll1(JSONObject)
     */
    public static FileInfoResponse fromJson(JSONObject json) throws JSONException {
        return new FileInfoResponse(
                json.optInt("code", -1),
                json.optString("message", ""),
                json.optString("version", ""));
    }
}

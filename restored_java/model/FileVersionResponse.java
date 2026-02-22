package model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * FileVersionResponse — 文件版本查询响应
 *
 * Original: lIllIIIlIl1.IllIIlIIII1
 *
 * Response from C&C: POST /phantom/file_version
 *
 * JSON format:
 * {
 *   "code": 0,
 *   "message": "ok",
 *   "version": "1.2.3"  ← the file version string
 * }
 *
 * Fields:
 *   - f418llllIIIIll1 → code (int)
 *   - f417lIIIIlllllIlll1 → message (String)
 *   - f419llllIllIl1 → version (String)
 */
public class FileVersionResponse implements Jsonable {

    private final int code;          /* f418llllIIIIll1 */
    private final String message;    /* f417lIIIIlllllIlll1 */
    private final String version;    /* f419llllIllIl1 */

    public FileVersionResponse(int code, String message, String version) {
        this.code = code;
        this.message = message;
        this.version = version;
    }

    public int getCode() { return this.code; }
    public String getMessage() { return this.message; }
    public String getVersion() { return this.version; }     /* lIIIIlllllIlll1() */

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("code", this.code);
        json.put("message", this.message);
        json.put("version", this.version);
        return json;
    }

    public static FileVersionResponse fromJson(JSONObject json) throws JSONException {
        return new FileVersionResponse(
                json.optInt("code", -1),
                json.optString("message", ""),
                json.optString("version", ""));
    }
}

package model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * FileContentResponse — 文件内容下载响应
 *
 * Original: lIllIIIlIl1.lIllIlIll1
 *
 * Response from C&C: POST /phantom/file
 * Used to download JS injection scripts and other remote content.
 *
 * JSON format:
 * {
 *   "code": 0,
 *   "message": "ok",
 *   "task": "function inject() { ... }"  ← the file content
 * }
 *
 * Fields:
 *   - f428llllIIIIll1 → code (int)
 *   - f427lIIIIlllllIlll1 → message (String)
 *   - f429llllIllIl1 → taskContent (String, the actual file data)
 */
public class FileContentResponse implements Jsonable {

    private final int code;           /* f428llllIIIIll1 */
    private final String message;     /* f427lIIIIlllllIlll1 */
    private final String taskContent; /* f429llllIllIl1 */

    public FileContentResponse(int code, String message, String taskContent) {
        this.code = code;
        this.message = message;
        this.taskContent = taskContent;
    }

    public int getCode() { return this.code; }
    public String getMessage() { return this.message; }
    public String getTaskContent() { return this.taskContent; }  /* lIIIIlllllIlll1() */

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("code", this.code);
        json.put("message", this.message);
        json.put("task", this.taskContent);
        return json;
    }

    public static FileContentResponse fromJson(JSONObject json) {
        return new FileContentResponse(
                json.optInt("code", -1),
                json.optString("message", ""),
                json.optString("task", ""));
    }
}

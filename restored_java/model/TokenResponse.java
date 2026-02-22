package model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * TokenResponse — Token 认证响应
 *
 * Original: lIllIIIlIl1.IlIlllIIlI1
 *
 * Response from the C&C token endpoint: POST /phantom/token
 *
 * JSON format:
 * {
 *   "code": 0,         ← 0 = success
 *   "message": "ok",
 *   "content": "eyJ..."  ← the auth token string
 * }
 *
 * Fields:
 *   - f415llllIIIIll1 → code (int)
 *   - f414lIIIIlllllIlll1 → message (String)
 *   - f416llllIllIl1 → content (String, the token value)
 */
public class TokenResponse implements Jsonable {

    private final int code;          /* f415llllIIIIll1 */
    private final String message;    /* f414lIIIIlllllIlll1 */
    private final String content;    /* f416llllIllIl1 — the auth token */

    public TokenResponse(int code, String message, String content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    /** Returns the response status code. 0 = success. */
    public int getCode() { return this.code; }               /* llllIIIIll1() */

    /** Returns the response message. */
    public String getMessage() { return this.message; }      /* llllIllIl1() */

    /** Returns the token content string. */
    public String getContent() { return this.content; }      /* lIIIIlllllIlll1() */

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("code", this.code);
        json.put("message", this.message);
        json.put("content", this.content);
        return json;
    }

    /**
     * Parses a TokenResponse from a JSONObject.
     * Original: llllIIIIll1(JSONObject)
     */
    public static TokenResponse fromJson(JSONObject json) throws JSONException {
        return new TokenResponse(
                json.optInt("code", -1),
                json.optString("message", ""),
                json.optString("content", ""));
    }
}

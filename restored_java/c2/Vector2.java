package c2;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: c13.nim5.ez8.h5_proto.Vector2
 *
 * Simple 2D vector with x/y coordinates.
 * Used in C&C protocol for position/coordinate data.
 */
public class Vector2 {
    private Long x;
    private Long y;

    public Vector2() {
        this.x = 0L;
        this.y = 0L;
    }

    public static Vector2 fromJSONObject(JSONObject json) throws JSONException {
        Vector2 vector2 = new Vector2();
        if (json.has("x") && !json.isNull("x")) {
            vector2.x = Long.valueOf(json.getLong("x"));
        }
        if (json.has("y") && !json.isNull("y")) {
            vector2.y = Long.valueOf(json.getLong("y"));
        }
        return vector2;
    }

    public Long getX() { return this.x; }
    public Long getY() { return this.y; }
    public void setX(Long l) { this.x = l; }
    public void setY(Long l) { this.y = l; }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("x", this.x);
        json.put("y", this.y);
        return json;
    }

    @Override
    public String toString() {
        return "Vector2{x=" + this.x + ", y=" + this.y + "}";
    }
}

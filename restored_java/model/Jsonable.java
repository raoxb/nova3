package model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Jsonable — JSON 序列化接口
 *
 * Original: lIllIIIlIl1.IlIllll1
 *
 * All request/response model classes implement this interface.
 */
public interface Jsonable {
    JSONObject toJSONObject() throws JSONException;
}

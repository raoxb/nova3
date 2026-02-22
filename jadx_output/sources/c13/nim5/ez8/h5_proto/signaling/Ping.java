package c13.nim5.ez8.h5_proto.signaling;

import IlIlllIIlI1.IllIIlIIII1;
import lIllIIIlIl1.IlIllll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Ping implements IlIllll1 {
    private final String message;

    public Ping(String str) {
        this.message = str;
    }

    public static Ping fromJSONObject(JSONObject jSONObject) throws JSONException {
        return new Ping(jSONObject.optString(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-8, 31, 10, 121, -119, 7, 35}, new byte[]{-107, 122, 121, 10, -24, 96, 70, 18}), ""));
    }

    public String getMessage() {
        return this.message;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{28, -43, 73, 65, -40, -81, 97}, new byte[]{113, -80, 58, 50, -71, -56, 4, -22}), this.message);
        return jSONObject;
    }
}

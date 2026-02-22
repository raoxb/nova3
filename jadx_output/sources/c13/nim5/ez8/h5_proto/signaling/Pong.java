package c13.nim5.ez8.h5_proto.signaling;

import IlIlllIIlI1.IllIIlIIII1;
import lIllIIIlIl1.IlIllll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Pong implements IlIllll1 {
    private final String message;

    public Pong(String str) {
        this.message = str;
    }

    public static Pong fromJSONObject(JSONObject jSONObject) throws JSONException {
        return new Pong(jSONObject.optString(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-67, -125, -41, 43, 120, -111, 61}, new byte[]{-48, -26, -92, 88, 25, -10, 88, -101}), ""));
    }

    public String getMessage() {
        return this.message;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{92, -118, -23, -118, 66, 77, -12}, new byte[]{49, -17, -102, -7, 35, 42, -111, -104}), this.message);
        return jSONObject;
    }
}

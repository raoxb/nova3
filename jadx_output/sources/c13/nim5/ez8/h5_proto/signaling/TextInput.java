package c13.nim5.ez8.h5_proto.signaling;

import IlIlllIIlI1.IllIIlIIII1;
import lIllIIIlIl1.IlIllll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class TextInput implements IlIllll1 {
    private final String text;

    public TextInput(String str) {
        this.text = str;
    }

    public static TextInput fromJSONObject(JSONObject jSONObject) throws JSONException {
        return new TextInput(jSONObject.optString(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{51, -10, 110, -85}, new byte[]{71, -109, 22, -33, 4, -66, -48, 49}), ""));
    }

    public String getText() {
        return this.text;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-62, -32, -118, 37}, new byte[]{-74, -123, -14, 81, -89, 53, -29, -70}), this.text);
        return jSONObject;
    }
}

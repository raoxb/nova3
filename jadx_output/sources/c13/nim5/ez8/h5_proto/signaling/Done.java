package c13.nim5.ez8.h5_proto.signaling;

import lIllIIIlIl1.IlIllll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Done implements IlIllll1 {
    public static Done fromJSONObject(JSONObject jSONObject) throws JSONException {
        return new Done();
    }

    public boolean equals(Object obj) {
        return obj instanceof Done;
    }

    public int hashCode() {
        return Done.class.hashCode();
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        return new JSONObject();
    }
}

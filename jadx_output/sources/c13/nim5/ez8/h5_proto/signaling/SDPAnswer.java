package c13.nim5.ez8.h5_proto.signaling;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import lIllIIIlIl1.IlIllll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SDPAnswer implements IlIllll1 {
    private final String sdp;
    private final String type;

    public SDPAnswer(String str, String str2) {
        this.type = str;
        this.sdp = str2;
    }

    public static SDPAnswer fromJSONObject(JSONObject jSONObject) throws JSONException {
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return new SDPAnswer(jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-116, 53, 72, -100}, new byte[]{-8, 76, 56, -7, 11, -61, -63, -111}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-92, 2, 30}, new byte[]{-41, 102, 110, -87, 118, -28, -92, 38}), ""));
    }

    public String getSdp() {
        return this.sdp;
    }

    public String getType() {
        return this.type;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{0, 105, 68, -18}, new byte[]{116, 16, 52, -117, 61, 86, 101, 96}), this.type);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-57, 70, -110}, new byte[]{-76, 34, -30, -43, 39, -28, -66, 7}), this.sdp);
        return jSONObject;
    }
}

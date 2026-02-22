package c13.nim5.ez8.h5_proto.signaling;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import lIllIIIlIl1.IlIllll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SDPOffer implements IlIllll1 {
    private final String sdp;
    private final String type;

    public SDPOffer(String str, String str2) {
        this.type = str;
        this.sdp = str2;
    }

    public static SDPOffer fromJSONObject(JSONObject jSONObject) throws JSONException {
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return new SDPOffer(jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-11, 108, -113, -33}, new byte[]{-127, 21, -1, -70, 29, 87, -29, -46}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{2, -105, 13}, new byte[]{113, -13, 125, -23, -51, -119, -36, 110}), ""));
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
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{56, 0, 126, -26}, new byte[]{76, 121, 14, -125, 25, 20, 56, -119}), this.type);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-31, 85, -1}, new byte[]{-110, 49, -113, 18, 67, -112, 60, -16}), this.sdp);
        return jSONObject;
    }
}

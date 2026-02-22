package c13.nim5.ez8.h5_proto.signaling;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import lIllIIIlIl1.IlIllll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ICECandidate implements IlIllll1 {
    private final String candidate;
    private final String sdpMid;
    private final int sdpMlineIndex;

    public ICECandidate(String str, String str2, int i) {
        this.candidate = str;
        this.sdpMid = str2;
        this.sdpMlineIndex = i;
    }

    public static ICECandidate fromJSONObject(JSONObject jSONObject) throws JSONException {
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return new ICECandidate(jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-121, -62, -23, -29, -63, -42, -23, 94, -127}, new byte[]{-28, -93, -121, -121, -88, -78, -120, 42}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{34, -100, -35, -118, -38, -1}, new byte[]{81, -8, -83, -57, -77, -101, 7, 83}), ""), jSONObject.optInt(lllliiiill1.llllIIIIll1(new byte[]{87, 49, -122, -15, 47, -120, -92, 8, 109, 59, -110, -39, 59}, new byte[]{36, 85, -10, -68, 67, -31, -54, 109}), 0));
    }

    public String getCandidate() {
        return this.candidate;
    }

    public String getSdpMid() {
        return this.sdpMid;
    }

    public int getSdpMlineIndex() {
        return this.sdpMlineIndex;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-85, 25, -37, 15, 74, -31, -117, 42, -83}, new byte[]{-56, 120, -75, 107, 35, -123, -22, 94}), this.candidate);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-126, -104, -35, 36, -2, -11}, new byte[]{-15, -4, -83, 105, -105, -111, -75, 29}), this.sdpMid);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{5, 109, 82, 90, -26, -106, 21, 109, 63, 103, 70, 114, -14}, new byte[]{118, 9, 34, 23, -118, -1, 123, 8}), this.sdpMlineIndex);
        return jSONObject;
    }
}

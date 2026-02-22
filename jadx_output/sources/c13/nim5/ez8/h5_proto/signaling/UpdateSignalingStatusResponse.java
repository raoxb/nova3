package c13.nim5.ez8.h5_proto.signaling;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import lIllIIIlIl1.IlIllll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UpdateSignalingStatusResponse implements IlIllll1 {
    private final long code;
    private final String message;

    public UpdateSignalingStatusResponse(long j, String str) {
        this.code = j;
        this.message = str;
    }

    public static UpdateSignalingStatusResponse fromJSONObject(JSONObject jSONObject) throws JSONException {
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return new UpdateSignalingStatusResponse(jSONObject.optLong(lllliiiill1.llllIIIIll1(new byte[]{-102, -57, 84, -18}, new byte[]{-7, -88, 48, -117, 89, -102, 78, 109}), -1L), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-63, 56, -107, 95, 32, -16, 35}, new byte[]{-84, 93, -26, 44, 65, -105, 70, -11}), ""));
    }

    public long getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{36, -109, -10, -90}, new byte[]{71, -4, -110, -61, 68, -16, -106, -76}), this.code);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{16, 47, -66, -48, -43, 44, -109}, new byte[]{125, 74, -51, -93, -76, 75, -10, -99}), this.message);
        return jSONObject;
    }
}

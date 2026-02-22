package c13.nim5.ez8.h5_proto.signaling;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import lIllIIIlIl1.IlIllll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Error implements IlIllll1 {
    private final int code;
    private final String message;

    public Error(int i, String str) {
        this.code = i;
        this.message = str;
    }

    public static Error fromJSONObject(JSONObject jSONObject) throws JSONException {
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return new Error(jSONObject.optInt(lllliiiill1.llllIIIIll1(new byte[]{17, 124, 42, -11}, new byte[]{114, 19, 78, -112, 24, -24, -123, 98}), -1), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-95, 2, -68, 84, -11, -47, 120}, new byte[]{-52, 103, -49, 39, -108, -74, 29, -52}), ""));
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-117, -26, 2, 64}, new byte[]{-24, -119, 102, 37, -123, -101, 118, 24}), this.code);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{49, -73, -21, -35, -106, -63, 79}, new byte[]{92, -46, -104, -82, -9, -90, 42, -23}), this.message);
        return jSONObject;
    }
}

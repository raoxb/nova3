package c13.nim5.ez8.h5_proto;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CommonResponse {
    private Long code;
    private String message;

    public CommonResponse() {
        this.code = 0L;
        this.message = "";
        this.code = 0L;
        this.message = "";
    }

    public static CommonResponse fromJSONObject(JSONObject jSONObject) throws JSONException {
        CommonResponse commonResponse = new CommonResponse();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{-99, -71, 9, -86}, new byte[]{-2, -42, 109, -49, -82, 108, -98, 38})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{-59, -58, 73, -72}, new byte[]{-90, -87, 45, -35, -62, 108, -116, -18}))) {
            commonResponse.code = Long.valueOf(jSONObject.getLong(lllliiiill1.llllIIIIll1(new byte[]{103, 3, -36, 41}, new byte[]{4, 108, -72, 76, -104, -103, 88, -44})));
        }
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{93, -22, 13, 83, -52, -45, 14}, new byte[]{48, -113, 126, 32, -83, -76, 107, -104})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{118, -86, 62, 106, -78, -90, -17}, new byte[]{27, -49, 77, 25, -45, -63, -118, -3}))) {
            commonResponse.message = jSONObject.getString(lllliiiill1.llllIIIIll1(new byte[]{-125, 97, -34, -50, 58, -19, 73}, new byte[]{-18, 4, -83, -67, 91, -118, 44, 12}));
        }
        return commonResponse;
    }

    public Long getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(Long l) {
        this.code = l;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-6, -114, 12, 6}, new byte[]{-103, -31, 104, 99, 126, -89, 88, 90}), this.code);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{66, 57, 23, -102, -67, -83, -13}, new byte[]{47, 92, 100, -23, -36, -54, -106, -38}), this.message);
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return sb.append(lllliiiill1.llllIIIIll1(new byte[]{18, 58, -98, 102, 94, 112, 76, -39, 34, 37, -100, 101, 66, 123, 101, -33, 62, 49, -106, 54}, new byte[]{81, 85, -13, 11, 49, 30, 30, -68})).append(this.code).append(lllliiiill1.llllIIIIll1(new byte[]{-43, -54, 75, -50, -111, -127, 73, 113, -100, -41}, new byte[]{-7, -22, 38, -85, -30, -14, 40, 22})).append(this.message).append(lllliiiill1.llllIIIIll1(new byte[]{96}, new byte[]{29, 23, -74, 58, 36, 123, 98, 2})).toString();
    }
}

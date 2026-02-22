package c13.nim5.ez8.h5_proto;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class GetConfigResponse {
    private Long code;
    private DllpgdConfig dllpgdConfig;
    private String message;

    public GetConfigResponse() {
        this.code = 0L;
        this.message = "";
        this.dllpgdConfig = new DllpgdConfig();
        this.code = 0L;
        this.message = "";
        this.dllpgdConfig = new DllpgdConfig();
    }

    public static GetConfigResponse fromJSONObject(JSONObject jSONObject) throws JSONException {
        GetConfigResponse getConfigResponse = new GetConfigResponse();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{-44, -65, -43, -83}, new byte[]{-73, -48, -79, -56, 79, -93, -54, -100})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{-71, 12, -55, -42}, new byte[]{-38, 99, -83, -77, 85, -46, -88, 84}))) {
            getConfigResponse.code = Long.valueOf(jSONObject.getLong(lllliiiill1.llllIIIIll1(new byte[]{0, -71, -121, 58}, new byte[]{99, -42, -29, 95, 124, -106, 12, 53})));
        }
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{-11, -60, 35, 71, 108, -24, 6}, new byte[]{-104, -95, 80, 52, 13, -113, 99, 93})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{-29, 12, 71, -2, 71, -112, 69}, new byte[]{-114, 105, 52, -115, 38, -9, 32, -71}))) {
            getConfigResponse.message = jSONObject.getString(lllliiiill1.llllIIIIll1(new byte[]{0, -1, 117, 86, -48, -69, -88}, new byte[]{109, -102, 6, 37, -79, -36, -51, 71}));
        }
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{-107, 19, 0, -102, -27, -89, 3, 67, -97, 25, 5, -115}, new byte[]{-15, ByteCompanionObject.MAX_VALUE, 108, -22, -126, -61, 64, 44})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{-121, -125, 69, 14, -58, 87, -46, -38, -115, -119, 64, 25}, new byte[]{-29, -17, 41, 126, -95, 51, -111, -75}))) {
            getConfigResponse.dllpgdConfig = DllpgdConfig.fromJSONObject(jSONObject.getJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-69, -32, -94, 26, -46, 119, -61, 66, -79, -22, -89, 13}, new byte[]{-33, -116, -50, 106, -75, 19, ByteCompanionObject.MIN_VALUE, 45})));
        }
        return getConfigResponse;
    }

    public Long getCode() {
        return this.code;
    }

    public DllpgdConfig getDllpgdConfig() {
        return this.dllpgdConfig;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(Long l) {
        this.code = l;
    }

    public void setDllpgdConfig(DllpgdConfig dllpgdConfig) {
        this.dllpgdConfig = dllpgdConfig;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{117, -21, 6, -66}, new byte[]{22, -124, 98, -37, -97, 105, -74, -27}), this.code);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-27, 29, -101, 93, 9, 61, -23}, new byte[]{-120, 120, -24, 46, 104, 90, -116, 40}), this.message);
        if (this.dllpgdConfig != null) {
            jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-30, 61, -33, 105, -72, 43, 52, -65, -24, 55, -38, 126}, new byte[]{-122, 81, -77, 25, -33, 79, 119, -48}), this.dllpgdConfig.toJSONObject());
        }
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return sb.append(lllliiiill1.llllIIIIll1(new byte[]{-14, 22, -3, -91, -100, 102, 67, -74, -46, 33, -20, -107, -125, 103, 75, -84, -48, 8, -22, -119, -105, 109, 24}, new byte[]{-75, 115, -119, -26, -13, 8, 37, -33})).append(this.code).append(lllliiiill1.llllIIIIll1(new byte[]{8, 45, 45, -45, 90, -15, -27, -1, 65, 48}, new byte[]{36, 13, 64, -74, 41, -126, -124, -104})).append(this.message).append(lllliiiill1.llllIIIIll1(new byte[]{-110, -67, 12, -48, -69, 83, 62, -87, -3, -14, 6, -38, -66, 68, 100}, new byte[]{-66, -99, 104, -68, -41, 35, 89, -51})).append(this.dllpgdConfig).append(lllliiiill1.llllIIIIll1(new byte[]{118}, new byte[]{11, 30, 121, -113, -63, -65, -88, -31})).toString();
    }
}

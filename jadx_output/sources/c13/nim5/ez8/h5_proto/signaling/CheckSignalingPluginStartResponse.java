package c13.nim5.ez8.h5_proto.signaling;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import kotlin.jvm.internal.ByteCompanionObject;
import lIllIIIlIl1.IlIllll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CheckSignalingPluginStartResponse implements IlIllll1 {
    private final long code;
    private final String jobId;
    private final String message;
    private final String offerId;
    private final boolean run;

    public CheckSignalingPluginStartResponse(long j, String str, boolean z, String str2, String str3) {
        this.code = j;
        this.message = str;
        this.run = z;
        this.offerId = str2;
        this.jobId = str3;
    }

    public static CheckSignalingPluginStartResponse fromJSONObject(JSONObject jSONObject) throws JSONException {
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return new CheckSignalingPluginStartResponse(jSONObject.optLong(lllliiiill1.llllIIIIll1(new byte[]{-53, 37, 18, 35}, new byte[]{-88, 74, 118, 70, 81, -73, 54, -122}), -1L), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-111, 80, -29, -118, -87, 110, -22}, new byte[]{-4, 53, -112, -7, -56, 9, -113, -62}), ""), jSONObject.optBoolean(lllliiiill1.llllIIIIll1(new byte[]{-3, -107, 40}, new byte[]{-113, -32, 70, -48, -114, -105, -33, -79}), false), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-20, ByteCompanionObject.MIN_VALUE, 69, -64, 51, -54, 16}, new byte[]{-125, -26, 35, -91, 65, -125, 116, 116}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{37, -51, 61, -95, 53}, new byte[]{79, -94, 95, -24, 81, -21, 76, 19}), ""));
    }

    public long getCode() {
        return this.code;
    }

    public String getJobId() {
        return this.jobId;
    }

    public String getMessage() {
        return this.message;
    }

    public String getOfferId() {
        return this.offerId;
    }

    public boolean isRun() {
        return this.run;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-57, 105, 97, -72}, new byte[]{-92, 6, 5, -35, 0, 90, 18, 90}), this.code);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{22, 112, -27, 88, 102, -61, -62}, new byte[]{123, 21, -106, 43, 7, -92, -89, 98}), this.message);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{25, -3, 114}, new byte[]{107, -120, 28, -116, -4, -6, 71, -88}), this.run);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{99, 86, -89, 6, 86, -125, 81}, new byte[]{12, 48, -63, 99, 36, -54, 53, 24}), this.offerId);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-71, 7, -31, -127, 79}, new byte[]{-45, 104, -125, -56, 43, -89, -92, -102}), this.jobId);
        return jSONObject;
    }
}

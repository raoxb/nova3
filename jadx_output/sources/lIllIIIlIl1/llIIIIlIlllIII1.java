package lIllIIIlIl1;

import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class llIIIIlIlllIII1 implements IlIllll1 {

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final String f430lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final String f431llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final String f432llllIllIl1;

    public llIIIIlIlllIII1(String str, String str2, String str3) {
        this.f431llllIIIIll1 = str;
        this.f430lIIIIlllllIlll1 = str2;
        this.f432llllIllIl1 = str3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        llIIIIlIlllIII1 lliiiilillliii1 = (llIIIIlIlllIII1) obj;
        String str = this.f431llllIIIIll1;
        if (str == null ? lliiiilillliii1.f431llllIIIIll1 == null : str.equals(lliiiilillliii1.f431llllIIIIll1)) {
            String str2 = this.f430lIIIIlllllIlll1;
            if (str2 == null ? lliiiilillliii1.f430lIIIIlllllIlll1 == null : str2.equals(lliiiilillliii1.f430lIIIIlllllIlll1)) {
                String str3 = this.f432llllIllIl1;
                if (str3 != null) {
                    if (str3.equals(lliiiilillliii1.f432llllIllIl1)) {
                        return true;
                    }
                } else if (lliiiilillliii1.f432llllIllIl1 == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.f431llllIIIIll1;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.f430lIIIIlllllIlll1;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.f432llllIllIl1;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String lIIIIlllllIlll1() {
        return this.f432llllIllIl1;
    }

    public String llllIIIIll1() {
        return this.f430lIIIIlllllIlll1;
    }

    public String llllIllIl1() {
        return this.f431llllIIIIll1;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{90, 76, 126, 55, 26, 3, 101, -83}, new byte[]{41, 37, 10, 82, 69, 118, 23, -63}), this.f431llllIIIIll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-69, -103, 23, 5, -114, -76}, new byte[]{-47, -10, 117, 90, -25, -48, 116, 78}), this.f430lIIIIlllllIlll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-97, 36, 34, -54, 17, -8, -28, 8}, new byte[]{-16, 66, 68, -81, 99, -89, -115, 108}), this.f432llllIllIl1);
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        return sb.append(lllliiiill1.llllIIIIll1(new byte[]{88, -5, 57, 47, 68, 125, 4, 62, 99, -8, 10, 56, 90, 59, 80}, new byte[]{23, -99, 95, 74, 54, 6, 119, 87})).append(this.f431llllIIIIll1).append('\'').append(lllliiiill1.llllIIIIll1(new byte[]{-58, 6, 52, -32, -55, 11, -1, 122, -51}, new byte[]{-22, 38, 94, -113, -85, 66, -101, 71})).append(this.f430lIIIIlllllIlll1).append('\'').append(lllliiiill1.llllIIIIll1(new byte[]{105, 95, 8, 125, -109, -115, -39, -88, 33, 66, 64}, new byte[]{69, ByteCompanionObject.MAX_VALUE, 103, 27, -11, -24, -85, -31})).append(this.f432llllIllIl1).append("'}").toString();
    }

    public static llIIIIlIlllIII1 llllIIIIll1(JSONObject jSONObject) {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        return new llIIIIlIlllIII1(jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-15, 62, 0, -127, 93, 78, -109, 70}, new byte[]{-126, 87, 116, -28, 2, 59, -31, 42}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{ByteCompanionObject.MIN_VALUE, -79, 10, -56, -71, 34}, new byte[]{-22, -34, 104, -105, -48, 70, 82, -116}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-68, 40, -103, -63, -21, -7, -48, -15}, new byte[]{-45, 78, -1, -92, -103, -90, -71, -107}), ""));
    }
}

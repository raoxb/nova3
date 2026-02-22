package lIllIIIlIl1;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class llllIllIl1 implements IlIllll1 {

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final String f456lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final int f457llllIIIIll1;

    public llllIllIl1(int i, String str) {
        this.f457llllIIIIll1 = i;
        this.f456lIIIIlllllIlll1 = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        llllIllIl1 llllillil1 = (llllIllIl1) obj;
        if (this.f457llllIIIIll1 == llllillil1.f457llllIIIIll1) {
            String str = this.f456lIIIIlllllIlll1;
            if (str != null) {
                if (str.equals(llllillil1.f456lIIIIlllllIlll1)) {
                    return true;
                }
            } else if (llllillil1.f456lIIIIlllllIlll1 == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = this.f457llllIIIIll1 * 31;
        String str = this.f456lIIIIlllllIlll1;
        return i + (str != null ? str.hashCode() : 0);
    }

    public String lIIIIlllllIlll1() {
        return this.f456lIIIIlllllIlll1;
    }

    public int llllIIIIll1() {
        return this.f457llllIIIIll1;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{87, -25, 118, 109}, new byte[]{52, -120, 18, 8, -22, -88, -65, -104}), this.f457llllIIIIll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{25, -55, 31, -58, 81, -46, 86}, new byte[]{116, -84, 108, -75, 48, -75, 51, 5}), this.f456lIIIIlllllIlll1);
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        return sb.append(lllliiiill1.llllIIIIll1(new byte[]{3, -71, 53, -32, -63, 88, 26, 51, 51, -90, 55, -29, -35, 83, 51, 53, 47, -78, 61, -80}, new byte[]{64, -42, 88, -115, -82, 54, 72, 86})).append(this.f457llllIIIIll1).append(lllliiiill1.llllIIIIll1(new byte[]{94, 38, -64, 10, -61, 35, -27, -112, 23, 59, -118}, new byte[]{114, 6, -83, 111, -80, 80, -124, -9})).append(this.f456lIIIIlllllIlll1).append("'}").toString();
    }

    public static llllIllIl1 llllIIIIll1(JSONObject jSONObject) throws JSONException {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        return new llllIllIl1(jSONObject.optInt(lllliiiill1.llllIIIIll1(new byte[]{81, -11, 65, -65}, new byte[]{50, -102, 37, -38, -19, 78, 71, -50}), -1), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{59, -104, 15, 121, -59, -33, 13}, new byte[]{86, -3, 124, 10, -92, -72, 104, -105}), ""));
    }
}

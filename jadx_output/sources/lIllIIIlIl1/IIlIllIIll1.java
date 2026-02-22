package lIllIIIlIl1;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class IIlIllIIll1 implements IlIllll1 {

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final String f390lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final int f391llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public String f392llllIllIl1;

    public IIlIllIIll1(int i, String str, String str2) {
        this.f391llllIIIIll1 = i;
        this.f390lIIIIlllllIlll1 = str;
        this.f392llllIllIl1 = str2;
    }

    public String lIIIIlllllIlll1() {
        return this.f390lIIIIlllllIlll1;
    }

    public int llllIIIIll1() {
        return this.f391llllIIIIll1;
    }

    public String llllIllIl1() {
        return this.f392llllIllIl1;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{104, -40, -107, 119}, new byte[]{11, -73, -15, 18, 86, -111, -33, 43}), this.f391llllIIIIll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-73, -50, -44, -126, 90, 112, -32}, new byte[]{-38, -85, -89, -15, 59, 23, -123, 39}), this.f390lIIIIlllllIlll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{98, 61, -1, 46, -123}, new byte[]{22, 82, -108, 75, -21, -21, 41, 29}), this.f392llllIllIl1);
        return jSONObject;
    }

    public void llllIIIIll1(String str) {
        this.f392llllIllIl1 = str;
    }

    public static IIlIllIIll1 llllIIIIll1(JSONObject jSONObject) {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        return new IIlIllIIll1(jSONObject.optInt(lllliiiill1.llllIIIIll1(new byte[]{105, 47, 30, 78}, new byte[]{10, 64, 122, 43, -3, -38, 44, -21}), -1), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{16, 109, 102, -19, -112, 60, -106}, new byte[]{125, 8, 21, -98, -15, 91, -13, 57}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{97, -126, 82, 24, -83}, new byte[]{21, -19, 57, 125, -61, 58, -78, 32}), ""));
    }
}

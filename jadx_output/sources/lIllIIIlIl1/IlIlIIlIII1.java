package lIllIIIlIl1;

import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class IlIlIIlIII1 implements IlIllll1 {

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public final String f401IlIllIlllIllI1;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public final String f402IlIlllIIlI1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public final String f403IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final String f404lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final String f405llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final String f406llllIllIl1;

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public final llllIIIIll1 f407llllllIlIIIlll1;

    public IlIlIIlIII1(String str, String str2, String str3, String str4, String str5, String str6, llllIIIIll1 lllliiiill1) {
        this.f405llllIIIIll1 = str;
        this.f404lIIIIlllllIlll1 = str2;
        this.f406llllIllIl1 = str3;
        this.f403IllIIlIIII1 = str4;
        this.f402IlIlllIIlI1 = str5;
        if (str6 == null) {
            str6 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-50, 79}, new byte[]{-70, 44, -2, 59, -28, -107, -38, 73});
        }
        this.f401IlIllIlllIllI1 = str6;
        this.f407llllllIlIIIlll1 = lllliiiill1;
    }

    public String IlIllIlllIllI1() {
        return this.f405llllIIIIll1;
    }

    public String IlIlllIIlI1() {
        return this.f404lIIIIlllllIlll1;
    }

    public String IllIIlIIII1() {
        return this.f402IlIlllIIlI1;
    }

    public llllIIIIll1 lIIIIlllllIlll1() {
        return this.f407llllllIlIIIlll1;
    }

    public String llllIIIIll1() {
        return this.f403IllIIlIIII1;
    }

    public String llllIllIl1() {
        return this.f401IlIllIlllIllI1;
    }

    public String llllllIlIIIlll1() {
        return this.f406llllIllIl1;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{103, 57, -29, -15, 37, 33, 125, 90}, new byte[]{8, 95, -123, -108, 87, 126, 20, 62}), this.f405llllIIIIll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{123, -22, 14, 38, -93, 35}, new byte[]{17, -123, 108, 121, -54, 71, 90, 68}), this.f404lIIIIlllllIlll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{26, 56, -9, 52, -44}, new byte[]{110, 87, -100, 81, -70, 14, -9, 123}), this.f406llllIllIl1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{58, 116, -54, 14, -36, 37}, new byte[]{91, 4, -70, 81, -75, 65, 33, -5}), this.f403IllIIlIIII1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{35, -60, 35, -39, 40, 22, -117, -109, 35}, new byte[]{71, -95, 85, -80, 75, 115, -44, -6}), this.f402IlIlllIIlI1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{112, -54, 75, -36, -92, -114, 98}, new byte[]{19, -94, 42, -78, -54, -21, 14, -66}), this.f401IlIllIlllIllI1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{49, 70, 74, -107}, new byte[]{80, 50, 37, -8, 35, -113, 105, 60}), this.f407llllllIlIIIlll1.toJSONObject());
        return jSONObject;
    }

    public static IlIlIIlIII1 llllIIIIll1(JSONObject jSONObject) throws JSONException {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        return new IlIlIIlIII1(jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{86, -106, -4, 121, 107, 34, 28, -66}, new byte[]{57, -16, -102, 28, 25, 125, 117, -38}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-77, -82, 42, 55, -42, -107}, new byte[]{-39, -63, 72, 104, -65, -15, -77, -112}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-49, 2, 58, 67, -122}, new byte[]{-69, 109, 81, 38, -24, -54, -106, -87}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{40, 68, -82, 82, -50, -97}, new byte[]{73, 52, -34, 13, -89, -5, 93, -58}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-44, 102, -95, -58, -89, 72, -32, 44, -44}, new byte[]{-80, 3, -41, -81, -60, 45, -65, 69}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-125, -13, 24, -59, -114, 13, -5}, new byte[]{-32, -101, 121, -85, -32, 104, -105, -27}), lllliiiill1.llllIIIIll1(new byte[]{98, 101}, new byte[]{22, 6, 107, 74, 109, 11, 104, -107})), llllIIIIll1.llllIIIIll1(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{51, 75, -123, 5}, new byte[]{82, 63, -22, 104, -110, 114, 70, 101})) != null ? (JSONObject) Objects.requireNonNull(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{77, -45, 75, -11}, new byte[]{44, -89, 36, -104, 105, -75, -8, 101}))) : new JSONObject()));
    }
}

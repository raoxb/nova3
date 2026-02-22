package lIllIIIlIl1;

import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class IlIllIlllIllI1 implements IlIllll1 {

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public final llllIIIIll1 f408IlIllIlllIllI1;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public final String f409IlIlllIIlI1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public final String f410IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final String f411lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final String f412llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final String f413llllIllIl1;

    public IlIllIlllIllI1(String str, String str2, String str3, String str4, String str5, llllIIIIll1 lllliiiill1) {
        this.f412llllIIIIll1 = str;
        this.f411lIIIIlllllIlll1 = str2;
        this.f413llllIllIl1 = str3;
        this.f410IllIIlIIII1 = str4;
        if (str5 == null) {
            str5 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-44, -126}, new byte[]{-96, -31, -43, -40, -47, -22, 101, 71});
        }
        this.f409IlIlllIIlI1 = str5;
        this.f408IlIllIlllIllI1 = lllliiiill1;
    }

    public String IlIllIlllIllI1() {
        return this.f412llllIIIIll1;
    }

    public String IlIlllIIlI1() {
        return this.f411lIIIIlllllIlll1;
    }

    public String IllIIlIIII1() {
        return this.f410IllIIlIIII1;
    }

    public llllIIIIll1 lIIIIlllllIlll1() {
        return this.f408IlIllIlllIllI1;
    }

    public String llllIIIIll1() {
        return this.f413llllIllIl1;
    }

    public String llllIllIl1() {
        return this.f409IlIlllIIlI1;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{49, 27, 84, 51, 16}, new byte[]{69, 116, 63, 86, 126, -12, -19, 12}), this.f411lIIIIlllllIlll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-7, -42, 25, 69, 53, 117, -30}, new byte[]{-113, -77, 107, 54, 92, 26, -116, 10}), this.f412llllIIIIll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{34, -102, 86, 116, -67, 99}, new byte[]{67, -22, 38, 43, -44, 7, -28, 22}), this.f413llllIllIl1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{40, 120, 62, 76, -96, 98, 58, 36, 40}, new byte[]{76, 29, 72, 37, -61, 7, 101, 77}), this.f410IllIIlIIII1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-11, -42, -4, 37, -3, 108, -51}, new byte[]{-106, -66, -99, 75, -109, 9, -95, 71}), this.f409IlIlllIIlI1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{24, -44, -65, -116}, new byte[]{121, -96, -48, -31, 120, -119, -32, 9}), this.f408IlIllIlllIllI1.toJSONObject());
        return jSONObject;
    }

    public static IlIllIlllIllI1 llllIIIIll1(JSONObject jSONObject) throws JSONException {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        return new IlIllIlllIllI1(jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{106, 87, 69, 24, -99, -125, 115}, new byte[]{28, 50, 55, 107, -12, -20, 29, -31}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-119, 16, 105, 1, -108}, new byte[]{-3, ByteCompanionObject.MAX_VALUE, 2, 100, -6, 16, -98, 27}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-84, 67, -8, -3, -102, 14}, new byte[]{-51, 51, -120, -94, -13, 106, -19, 20}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{58, 24, 62, -14, -59, -31, 109, -28, 58}, new byte[]{94, 125, 72, -101, -90, -124, 50, -115}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-122, 14, 33, -95, 105, -86, -24}, new byte[]{-27, 102, 64, -49, 7, -49, -124, 74}), lllliiiill1.llllIIIIll1(new byte[]{76, -83}, new byte[]{56, -50, 6, 111, 46, -110, -34, 120})), llllIIIIll1.llllIIIIll1(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-94, -123, 5, 121}, new byte[]{-61, -15, 106, 20, -37, 56, -3, -43})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{81, -119, -120, 29}, new byte[]{48, -3, -25, 112, -113, 112, 36, -17})) : new JSONObject()));
    }
}

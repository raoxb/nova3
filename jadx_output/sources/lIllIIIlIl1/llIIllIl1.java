package lIllIIIlIl1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class llIIllIl1 implements IlIllll1 {

    /* renamed from: IlIlIIlIII1, reason: collision with root package name */
    public final llllIIIIll1 f433IlIlIIlIII1;

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public final String f434IlIllIlllIllI1;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public final String f435IlIlllIIlI1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public final String f436IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final String f437lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final String f438llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final String f439llllIllIl1;

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public final List<String> f440llllllIlIIIlll1;

    public llIIllIl1(String str, String str2, String str3, String str4, String str5, String str6, List<String> list, llllIIIIll1 lllliiiill1) {
        this.f438llllIIIIll1 = str;
        this.f437lIIIIlllllIlll1 = str2;
        this.f439llllIllIl1 = str3;
        if (str4 == null) {
            str4 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-4, 122}, new byte[]{-120, 25, 66, 121, -113, -94, -81, -8});
        }
        this.f436IllIIlIIII1 = str4;
        this.f435IlIlllIIlI1 = str5;
        this.f434IlIllIlllIllI1 = str6;
        this.f440llllllIlIIIlll1 = list;
        this.f433IlIlIIlIII1 = lllliiiill1;
    }

    public String IlIlIIlIII1() {
        return this.f438llllIIIIll1;
    }

    public List<String> IlIllIlllIllI1() {
        return this.f440llllllIlIIIlll1;
    }

    public String IlIlllIIlI1() {
        return this.f434IlIllIlllIllI1;
    }

    public String IllIIlIIII1() {
        return this.f439llllIllIl1;
    }

    public llllIIIIll1 lIIIIlllllIlll1() {
        return this.f433IlIlIIlIII1;
    }

    public String llllIIIIll1() {
        return this.f437lIIIIlllllIlll1;
    }

    public String llllIllIl1() {
        return this.f436IllIIlIIII1;
    }

    public String llllllIlIIIlll1() {
        return this.f435IlIlllIIlI1;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{116, -41, 5, 4, -8}, new byte[]{0, -72, 110, 97, -106, -105, 2, 112}), this.f438llllIIIIll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{97, 41, -23, -6, 30, 105}, new byte[]{0, 89, -103, -91, 119, 13, -62, 69}), this.f437lIIIIlllllIlll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-76, 110, -113, 24, -124, -47, 18, 89, -76}, new byte[]{-48, 11, -7, 113, -25, -76, 77, 48}), this.f439llllIllIl1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{42, -11, 123, 60, 29, 93, 61}, new byte[]{73, -99, 26, 82, 115, 56, 81, 60}), this.f436IllIIlIIII1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-53, -112, 78, -55, -56, -44, 2, -58}, new byte[]{-92, -10, 40, -84, -70, -117, 107, -94}), this.f435IlIlllIIlI1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{95, 44, -52, 59, 73, -94}, new byte[]{53, 67, -82, 100, 32, -58, -45, -12}), this.f434IlIllIlllIllI1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-28, -51, -42, -25}, new byte[]{-120, -94, -79, -108, 39, 35, 103, 55}), new JSONArray((Collection) this.f440llllllIlIIIlll1));
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{16, 121, -22, 51}, new byte[]{113, 13, -123, 94, -27, 84, 104, -73}), this.f433IlIlIIlIII1.toJSONObject());
        return jSONObject;
    }

    public static llIIllIl1 llllIIIIll1(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{65, -2, -127, 81}, new byte[]{45, -111, -26, 34, -65, 23, -63, 58}));
        ArrayList arrayList = new ArrayList();
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(optJSONArray.optString(i, ""));
            }
        }
        byte[] bArr = {-121, 59, -69, 39, ByteCompanionObject.MAX_VALUE, -71, -14, 60};
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        return new llIIllIl1(jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-13, 84, -48, 66, 17}, bArr), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{106, 40, -105, 0, 92, 81}, new byte[]{11, 88, -25, 95, 53, 53, -98, -80}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-59, -89, 65, 20, -55, -106, -8, 30, -59}, new byte[]{-95, -62, 55, 125, -86, -13, -89, 119}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{120, -34, -95, -44, 29, -86, 1}, new byte[]{27, -74, -64, -70, 115, -49, 109, -118}), lllliiiill1.llllIIIIll1(new byte[]{95, 93}, new byte[]{43, 62, -93, 63, 108, 91, -123, -70})), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-83, 4, -82, 3, -73, -63, 12, 36}, new byte[]{-62, 98, -56, 102, -59, -98, 101, 64}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-63, 79, 99, 110, 12, -21}, new byte[]{-85, 32, 1, 49, 101, -113, -28, -52}), ""), arrayList, llllIIIIll1.llllIIIIll1(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{92, -13, -30, -71}, new byte[]{61, -121, -115, -44, 82, -93, 106, 51})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-71, 77, 56, -33}, new byte[]{-40, 57, 87, -78, -94, -112, 56, -14})) : new JSONObject()));
    }
}

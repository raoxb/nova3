package lIllIIIlIl1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class IlIlIIIlIlIlll1 implements IlIllll1 {

    /* renamed from: IlIlIIlIII1, reason: collision with root package name */
    public final llllIIIIll1 f393IlIlIIlIII1;

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public final String f394IlIllIlllIllI1;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public final String f395IlIlllIIlI1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public final String f396IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final String f397lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final String f398llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final String f399llllIllIl1;

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public final List<String> f400llllllIlIIIlll1;

    public IlIlIIIlIlIlll1(String str, String str2, String str3, String str4, String str5, String str6, List<String> list, llllIIIIll1 lllliiiill1) {
        this.f398llllIIIIll1 = str;
        this.f397lIIIIlllllIlll1 = str2;
        this.f399llllIllIl1 = str3;
        if (str4 == null) {
            str4 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-100, -21}, new byte[]{-24, -120, 68, 97, 54, -103, -93, 29});
        }
        this.f396IllIIlIIII1 = str4;
        this.f395IlIlllIIlI1 = str5;
        this.f394IlIllIlllIllI1 = str6;
        this.f400llllllIlIIIlll1 = list;
        this.f393IlIlIIlIII1 = lllliiiill1;
    }

    public String IlIlIIlIII1() {
        return this.f398llllIIIIll1;
    }

    public String IlIllIlllIllI1() {
        return this.f394IlIllIlllIllI1;
    }

    public List<String> IlIlllIIlI1() {
        return this.f400llllllIlIIIlll1;
    }

    public String IllIIlIIII1() {
        return this.f399llllIllIl1;
    }

    public llllIIIIll1 lIIIIlllllIlll1() {
        return this.f393IlIlIIlIII1;
    }

    public String llllIIIIll1() {
        return this.f397lIIIIlllllIlll1;
    }

    public String llllIllIl1() {
        return this.f396IllIIlIIII1;
    }

    public String llllllIlIIIlll1() {
        return this.f395IlIlllIIlI1;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-96, -125, -51, -80, -3}, new byte[]{-44, -20, -90, -43, -109, -90, 73, 59}), this.f398llllIIIIll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{36, 30, -11, -71, -25, 58}, new byte[]{69, 110, -123, -26, -114, 94, -35, -117}), this.f397lIIIIlllllIlll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-72, 16, 84, 118, 55, -78, 19, -9, -72}, new byte[]{-36, 117, 34, 31, 84, -41, 76, -98}), this.f399llllIllIl1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{3, -88, -116, 21, 9, 111, -119}, new byte[]{96, -64, -19, 123, 103, 10, -27, -69}), this.f396IllIIlIIII1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-81, -94, 124, -5, -119, -116, 68, 123}, new byte[]{-64, -60, 26, -98, -5, -45, 45, 31}), this.f395IlIlllIIlI1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{57, -9, -54, 46, 17, -105}, new byte[]{83, -104, -88, 113, 120, -13, 30, 81}), this.f394IlIllIlllIllI1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{110, 15, 81, -93, 92, -74}, new byte[]{11, 121, 52, -51, 40, -59, -95, 87}), new JSONArray((Collection) this.f400llllllIlIIIlll1));
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-120, -109, -99, -76}, new byte[]{-23, -25, -14, -39, -126, -21, 63, 49}), this.f393IlIlIIlIII1.toJSONObject());
        return jSONObject;
    }

    public static IlIlIIIlIlIlll1 llllIIIIll1(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{25, 119, 66, -29, -99, -49}, new byte[]{124, 1, 39, -115, -23, -68, 93, 33}));
        ArrayList arrayList = new ArrayList();
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(optJSONArray.optString(i, ""));
            }
        }
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        return new IlIlIIIlIlIlll1(jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{117, -122, 106, -24, -82}, new byte[]{1, -23, 1, -115, -64, -120, -65, 67}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{107, -11, -120, -89, -5, 124}, new byte[]{10, -123, -8, -8, -110, 24, -67, 47}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{61, -20, 54, -127, 19, 3, -59, 87, 61}, new byte[]{89, -119, 64, -24, 112, 102, -102, 62}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{23, 101, -115, 26, -6, 5, -72}, new byte[]{116, 13, -20, 116, -108, 96, -44, 88}), lllliiiill1.llllIIIIll1(new byte[]{28, 14}, new byte[]{104, 109, 83, 91, 50, 93, -6, -40})), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{18, 120, 85, -27, 45, -94, 107, 73}, new byte[]{125, 30, 51, ByteCompanionObject.MIN_VALUE, 95, -3, 2, 45}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{45, -35, 53, 92, -91, 115}, new byte[]{71, -78, 87, 3, -52, 23, -8, 57}), ""), arrayList, llllIIIIll1.llllIIIIll1(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{77, -109, 32, 103}, new byte[]{44, -25, 79, 10, -101, -6, 123, -91})) != null ? (JSONObject) Objects.requireNonNull(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-83, -92, 47, -40}, new byte[]{-52, -48, 64, -75, 12, -89, -16, 25}))) : new JSONObject()));
    }
}

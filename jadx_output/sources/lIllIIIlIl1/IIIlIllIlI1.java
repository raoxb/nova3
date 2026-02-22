package lIllIIIlIl1;

import java.util.Objects;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class IIIlIllIlI1 implements IlIllll1 {

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public final String f383IlIllIlllIllI1;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public final String f384IlIlllIIlI1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public final String f385IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final String f386lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final String f387llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final String f388llllIllIl1;

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public final llllIIIIll1 f389llllllIlIIIlll1;

    public IIIlIllIlI1(String str, String str2, String str3, String str4, String str5, String str6, llllIIIIll1 lllliiiill1) {
        this.f387llllIIIIll1 = str;
        this.f386lIIIIlllllIlll1 = str2;
        this.f388llllIllIl1 = str3 == null ? "" : str3;
        this.f385IllIIlIIII1 = str4;
        this.f384IlIlllIIlI1 = str5;
        this.f383IlIllIlllIllI1 = str6;
        this.f389llllllIlIIIlll1 = lllliiiill1;
    }

    public String IlIllIlllIllI1() {
        return this.f383IlIllIlllIllI1;
    }

    public String IlIlllIIlI1() {
        return this.f385IllIIlIIII1;
    }

    public String IllIIlIIII1() {
        return this.f384IlIlllIIlI1;
    }

    public llllIIIIll1 lIIIIlllllIlll1() {
        return this.f389llllllIlIIIlll1;
    }

    public String llllIIIIll1() {
        return this.f387llllIIIIll1;
    }

    public String llllIllIl1() {
        return this.f386lIIIIlllllIlll1;
    }

    public String llllllIlIIIlll1() {
        return this.f388llllIllIl1;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{126, 19, -105, 118, -9, -84}, new byte[]{31, 99, -25, 41, -98, -56, 94, -101}), this.f387llllIIIIll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-89, 44, 72, -72, -74, 3, -54, 97, -89}, new byte[]{-61, 73, 62, -47, -43, 102, -107, 8}), this.f386lIIIIlllllIlll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-23, -44, -119, -26, 77}, new byte[]{-99, -69, -30, -125, 35, 20, 2, 20}), this.f388llllIllIl1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-96, -99, 31, -55, -40, 8, 40, 47}, new byte[]{-49, -5, 121, -84, -86, 87, 65, 75}), this.f385IllIIlIIII1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-107, 87, 8, -68, 33, -7}, new byte[]{-1, 56, 106, -29, 72, -99, -48, -93}), this.f384IlIlllIIlI1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{92, -113, 63, -98, 65, 31}, new byte[]{46, -22, 76, -21, 45, 107, -27, -91}), this.f383IlIllIlllIllI1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{22, 15, 113, -31}, new byte[]{119, 123, 30, -116, -20, 91, -28, 76}), this.f389llllllIlIIIlll1.toJSONObject());
        return jSONObject;
    }

    public static IIIlIllIlI1 llllIIIIll1(JSONObject jSONObject) throws JSONException {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        return new IIIlIllIlI1(jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-44, -67, 43, 40, -90, -38}, new byte[]{-75, -51, 91, 119, -49, -66, -78, -125}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{37, -105, 119, -84, 56, 31, -56, -67, 37}, new byte[]{65, -14, 1, -59, 91, 122, -105, -44}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{93, -5, 8, 74, 93}, new byte[]{41, -108, 99, 47, 51, -82, ByteCompanionObject.MIN_VALUE, 5}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-95, ByteCompanionObject.MAX_VALUE, -34, -89, -114, 7, -26, 99}, new byte[]{-50, 25, -72, -62, -4, 88, -113, 7}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{14, -31, 119, 35, 68, -124}, new byte[]{100, -114, 21, 124, 45, -32, -22, -85}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{55, 97, 92, -55, 43, -65}, new byte[]{69, 4, 47, -68, 71, -53, -107, -104}), ""), llllIIIIll1.llllIIIIll1(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{63, -29, -87, 77}, new byte[]{94, -105, -58, 32, 29, 39, -36, -86})) != null ? (JSONObject) Objects.requireNonNull(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{49, -20, -19, -11}, new byte[]{80, -104, -126, -104, -30, -19, 114, 38}))) : new JSONObject()));
    }
}

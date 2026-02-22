package lIllIIIlIl1;

import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class llllllIlIIIlll1 implements IlIllll1 {

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public final String f458IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final String f459lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final int f460llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final String f461llllIllIl1;

    public llllllIlIIIlll1(int i, String str, String str2, String str3) {
        this.f460llllIIIIll1 = i;
        this.f459lIIIIlllllIlll1 = str;
        this.f461llllIllIl1 = str2;
        this.f458IllIIlIIII1 = str3;
    }

    public String IllIIlIIII1() {
        return this.f458IllIIlIIII1;
    }

    public String lIIIIlllllIlll1() {
        return this.f461llllIllIl1;
    }

    public int llllIIIIll1() {
        return this.f460llllIIIIll1;
    }

    public String llllIllIl1() {
        return this.f459lIIIIlllllIlll1;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-127, 103, 87, 65}, new byte[]{-30, 8, 51, 36, -59, -41, 87, 27}), this.f460llllIIIIll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{65, -27, 99, -41, 66, -99, -32}, new byte[]{44, ByteCompanionObject.MIN_VALUE, 16, -92, 35, -6, -123, -48}), this.f459lIIIIlllllIlll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-90, 16, -82, -62, -67, -42, -68}, new byte[]{-59, ByteCompanionObject.MAX_VALUE, -64, -74, -40, -72, -56, 36}), this.f461llllIllIl1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{97, -75, 66, 31, -26, -114, 70}, new byte[]{23, -48, 48, 108, -113, -31, 40, 63}), this.f458IllIIlIIII1);
        return jSONObject;
    }

    public static llllllIlIIIlll1 llllIIIIll1(JSONObject jSONObject) throws JSONException {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        return new llllllIlIIIlll1(jSONObject.optInt(lllliiiill1.llllIIIIll1(new byte[]{-100, 71, -108, 34}, new byte[]{-1, 40, -16, 71, 6, -43, -119, 31}), -1), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{17, -97, -122, 102, -117, -71, 43}, new byte[]{124, -6, -11, 21, -22, -34, 78, 29}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-102, -6, -118, 18, 67, 35, 126}, new byte[]{-7, -107, -28, 102, 38, 77, 10, 48}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{85, 2, 65, 19, -115, -12, -15}, new byte[]{35, 103, 51, 96, -28, -101, -97, 39}), ""));
    }
}

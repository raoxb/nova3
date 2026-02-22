package lIllIIIlIl1;

import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class IlIlllIIlI1 implements IlIllll1 {

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final String f414lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final int f415llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final String f416llllIllIl1;

    public IlIlllIIlI1(int i, String str, String str2) {
        this.f415llllIIIIll1 = i;
        this.f414lIIIIlllllIlll1 = str;
        this.f416llllIllIl1 = str2;
    }

    public String lIIIIlllllIlll1() {
        return this.f416llllIllIl1;
    }

    public int llllIIIIll1() {
        return this.f415llllIIIIll1;
    }

    public String llllIllIl1() {
        return this.f414lIIIIlllllIlll1;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-114, 55, -70, 78}, new byte[]{-19, 88, -34, 43, 81, -72, -125, 68}), this.f415llllIIIIll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-41, -27, 69, 121, -59, 80, -34}, new byte[]{-70, ByteCompanionObject.MIN_VALUE, 54, 10, -92, 55, -69, 98}), this.f414lIIIIlllllIlll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{10, 45, 115, 52, 83, -37, -33}, new byte[]{105, 66, 29, 64, 54, -75, -85, -85}), this.f416llllIllIl1);
        return jSONObject;
    }

    public static IlIlllIIlI1 llllIIIIll1(JSONObject jSONObject) throws JSONException {
        byte[] bArr = {63, -26, -22, ByteCompanionObject.MAX_VALUE, 38, -18, -89, 23};
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        return new IlIlllIIlI1(jSONObject.optInt(lllliiiill1.llllIIIIll1(new byte[]{92, -119, -114, 26}, bArr), -1), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-55, -14, 81, -15, 36, -106, 88}, new byte[]{-92, -105, 34, -126, 69, -15, 61, 74}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-124, -72, 82, 103, -60, 14, -40}, new byte[]{-25, -41, 60, 19, -95, 96, -84, 6}), ""));
    }
}

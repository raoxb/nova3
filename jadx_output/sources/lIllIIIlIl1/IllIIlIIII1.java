package lIllIIIlIl1;

import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class IllIIlIIII1 implements IlIllll1 {

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final String f417lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final int f418llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final String f419llllIllIl1;

    public IllIIlIIII1(int i, String str, String str2) {
        this.f418llllIIIIll1 = i;
        this.f417lIIIIlllllIlll1 = str;
        this.f419llllIllIl1 = str2;
    }

    public String lIIIIlllllIlll1() {
        return this.f417lIIIIlllllIlll1;
    }

    public int llllIIIIll1() {
        return this.f418llllIIIIll1;
    }

    public String llllIllIl1() {
        return this.f419llllIllIl1;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        byte[] bArr = {27, -3, 118, 108, 59, ByteCompanionObject.MIN_VALUE, 59, 29};
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{120, -110, 18, 9}, bArr), this.f418llllIIIIll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-93, 38, -32, -45, 68, 82, 27}, new byte[]{-50, 67, -109, -96, 37, 53, 126, 115}), this.f417lIIIIlllllIlll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-116, 4, -110, 16, 35, 65, 11}, new byte[]{-6, 97, -32, 99, 74, 46, 101, 116}), this.f419llllIllIl1);
        return jSONObject;
    }

    public static IllIIlIIII1 llllIIIIll1(JSONObject jSONObject) throws JSONException {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        return new IllIIlIIII1(jSONObject.optInt(lllliiiill1.llllIIIIll1(new byte[]{-117, -108, 5, -118}, new byte[]{-24, -5, 97, -17, 74, -102, -72, 25}), -1), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{87, -106, -106, 79, 7, -114, 37}, new byte[]{58, -13, -27, 60, 102, -23, 64, -79}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-85, 91, -42, -71, 116, -107, -72}, new byte[]{-35, 62, -92, -54, 29, -6, -42, -114}), ""));
    }
}

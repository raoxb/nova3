package lIllIIIlIl1;

import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class lIllIlIll1 implements IlIllll1 {

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final String f427lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final int f428llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final String f429llllIllIl1;

    public lIllIlIll1(int i, String str, String str2) {
        this.f428llllIIIIll1 = i;
        this.f427lIIIIlllllIlll1 = str;
        this.f429llllIllIl1 = str2;
    }

    public String lIIIIlllllIlll1() {
        return this.f427lIIIIlllllIlll1;
    }

    public int llllIIIIll1() {
        return this.f428llllIIIIll1;
    }

    public String llllIllIl1() {
        return this.f429llllIllIl1;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-13, 26, 38, 107}, new byte[]{-112, 117, 66, 14, -17, 93, 0, -20}), this.f428llllIIIIll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-87, 19, -35, -54, 32, -117, ByteCompanionObject.MIN_VALUE}, new byte[]{-60, 118, -82, -71, 65, -20, -27, -101}), this.f427lIIIIlllllIlll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-36, -70, -63, -28}, new byte[]{-88, -37, -78, -113, 87, -88, -75, -44}), this.f429llllIllIl1);
        return jSONObject;
    }

    public static lIllIlIll1 llllIIIIll1(JSONObject jSONObject) {
        byte[] bArr = {8, -34, -32, -57, ByteCompanionObject.MIN_VALUE, 77, 111, 100};
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        return new lIllIlIll1(jSONObject.optInt(lllliiiill1.llllIIIIll1(new byte[]{107, -79, -124, -94}, bArr), -1), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{94, 22, -102, 98, -75, -94, 16}, new byte[]{51, 115, -23, 17, -44, -59, 117, 39}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-36, 13, 37, 66}, new byte[]{-88, 108, 86, 41, -88, 22, -36, -66}), ""));
    }
}

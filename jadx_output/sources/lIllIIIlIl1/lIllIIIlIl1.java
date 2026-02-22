package lIllIIIlIl1;

import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class lIllIIIlIl1 implements IlIllll1 {

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final String f424lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final int f425llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final JSONObject f426llllIllIl1;

    public lIllIIIlIl1(int i, String str, JSONObject jSONObject) {
        this.f425llllIIIIll1 = i;
        this.f424lIIIIlllllIlll1 = str;
        this.f426llllIllIl1 = jSONObject;
    }

    public String lIIIIlllllIlll1() {
        return this.f424lIIIIlllllIlll1;
    }

    public int llllIIIIll1() {
        return this.f425llllIIIIll1;
    }

    public JSONObject llllIllIl1() {
        return this.f426llllIllIl1;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{60, -21, -35, 105}, new byte[]{95, -124, -71, 12, -52, -122, 116, -54}), this.f425llllIIIIll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-98, -80, -109, 79, -49, 24, 70}, new byte[]{-13, -43, -32, 60, -82, ByteCompanionObject.MAX_VALUE, 35, -99}), this.f424lIIIIlllllIlll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-117, 40, -11, 4}, new byte[]{-1, 73, -122, 111, -12, 12, 85, -112}), this.f426llllIllIl1);
        return jSONObject;
    }

    public static lIllIIIlIl1 llllIIIIll1(JSONObject jSONObject) {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        return new lIllIIIlIl1(jSONObject.optInt(lllliiiill1.llllIIIIll1(new byte[]{-31, 65, -123, -5}, new byte[]{-126, 46, -31, -98, 55, 58, -13, 105}), -1), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{57, 99, -50, 92, 40, 56, -46}, new byte[]{84, 6, -67, 47, 73, 95, -73, -35}), ""), jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{54, 0, 84, -60}, new byte[]{66, 97, 39, -81, 14, 72, 65, -117})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{49, -10, 102, -121}, new byte[]{69, -105, 21, -20, 122, 78, 36, -84})) : new JSONObject());
    }
}

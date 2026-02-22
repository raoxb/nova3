package c13.nim5.ez8.h5_proto.signaling;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import kotlin.jvm.internal.ByteCompanionObject;
import lIllIIIlIl1.IlIllll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ClickEvent implements IlIllll1 {
    private final double normalizedX;
    private final double normalizedY;

    public ClickEvent(double d, double d2) {
        this.normalizedX = d;
        this.normalizedY = d2;
    }

    public static ClickEvent fromJSONObject(JSONObject jSONObject) throws JSONException {
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return new ClickEvent(jSONObject.optDouble(lllliiiill1.llllIIIIll1(new byte[]{-65, -124, -85, 100, -78, 101, -123, 11, -76, -113, -122, 113}, new byte[]{-47, -21, -39, 9, -45, 9, -20, 113}), 0.0d), jSONObject.optDouble(lllliiiill1.llllIIIIll1(new byte[]{-30, 35, -24, 100, -126, -82, -97, 118, -23, 40, -59, 112}, new byte[]{-116, 76, -102, 9, -29, -62, -10, 12}), 0.0d));
    }

    public double getNormalizedX() {
        return this.normalizedX;
    }

    public double getNormalizedY() {
        return this.normalizedY;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-73, -49, -37, 12, 2, -113, -58, -5, -68, -60, -10, 25}, new byte[]{-39, -96, -87, 97, 99, -29, -81, -127}), this.normalizedX);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-62, ByteCompanionObject.MIN_VALUE, -71, -93, 44, 65, -99, -23, -55, -117, -108, -73}, new byte[]{-84, -17, -53, -50, 77, 45, -12, -109}), this.normalizedY);
        return jSONObject;
    }
}

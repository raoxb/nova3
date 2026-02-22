package c13.nim5.ez8.h5_proto.signaling;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import lIllIIIlIl1.IlIllll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ScrollEvent implements IlIllll1 {
    private final double deltaX;
    private final double deltaY;

    public ScrollEvent(double d, double d2) {
        this.deltaX = d;
        this.deltaY = d2;
    }

    public static ScrollEvent fromJSONObject(JSONObject jSONObject) throws JSONException {
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return new ScrollEvent(jSONObject.optDouble(lllliiiill1.llllIIIIll1(new byte[]{-33, 97, -90, -74, -111, 62, -17}, new byte[]{-69, 4, -54, -62, -16, 97, -105, -5}), 0.0d), jSONObject.optDouble(lllliiiill1.llllIIIIll1(new byte[]{-60, 85, 25, 91, 4, -97, -85}, new byte[]{-96, 48, 117, 47, 101, -64, -46, -29}), 0.0d));
    }

    public double getDeltaX() {
        return this.deltaX;
    }

    public double getDeltaY() {
        return this.deltaY;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{31, 9, -16, 7, 91, 108, 2}, new byte[]{123, 108, -100, 115, 58, 51, 122, 12}), this.deltaX);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{90, 28, 90, 41, 14, 7, -83}, new byte[]{62, 121, 54, 93, 111, 88, -44, 53}), this.deltaY);
        return jSONObject;
    }
}

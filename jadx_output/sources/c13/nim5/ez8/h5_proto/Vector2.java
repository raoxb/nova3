package c13.nim5.ez8.h5_proto;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Vector2 {
    private Long x;
    private Long y;

    public Vector2() {
        this.x = 0L;
        this.y = 0L;
        this.x = 0L;
        this.y = 0L;
    }

    public static Vector2 fromJSONObject(JSONObject jSONObject) throws JSONException {
        Vector2 vector2 = new Vector2();
        byte[] bArr = {10};
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        if (jSONObject.has(lllliiiill1.llllIIIIll1(bArr, new byte[]{114, -13, -45, 8, -14, 88, -42, 101})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{-4}, new byte[]{-124, 51, 26, 116, -40, -12, -96, -7}))) {
            vector2.x = Long.valueOf(jSONObject.getLong(lllliiiill1.llllIIIIll1(new byte[]{19}, new byte[]{107, 91, 59, 89, 61, 61, -52, -70})));
        }
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{82}, new byte[]{43, 73, -64, 50, ByteCompanionObject.MIN_VALUE, 36, 24, -55})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{101}, new byte[]{28, 71, -33, 44, -53, -32, -48, -125}))) {
            vector2.y = Long.valueOf(jSONObject.getLong(lllliiiill1.llllIIIIll1(new byte[]{35}, new byte[]{90, 17, 27, 91, -104, 81, 46, -107})));
        }
        return vector2;
    }

    public Long getX() {
        return this.x;
    }

    public Long getY() {
        return this.y;
    }

    public void setX(Long l) {
        this.x = l;
    }

    public void setY(Long l) {
        this.y = l;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-3}, new byte[]{-123, -101, 68, 22, -56, 112, -18, 28}), this.x);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{58}, new byte[]{67, 53, -119, -28, 51, 37, 93, -65}), this.y);
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return sb.append(lllliiiill1.llllIIIIll1(new byte[]{102, 35, 71, -57, 45, 18, -72, -42, 72, 123}, new byte[]{48, 70, 36, -77, 66, 96, -118, -83})).append(this.x).append(lllliiiill1.llllIIIIll1(new byte[]{5, -65, -28, 90}, new byte[]{41, -97, -99, 103, 66, 48, -26, 87})).append(this.y).append(lllliiiill1.llllIIIIll1(new byte[]{93}, new byte[]{32, -5, -53, -37, 64, -18, -67, -37})).toString();
    }
}

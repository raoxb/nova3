package lIllIIIlIl1;

import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public interface IlIllll1 {
    static <T extends IlIllll1> T llllIIIIll1(JSONObject jSONObject, Class<T> cls) {
        try {
            Method declaredMethod = cls.getDeclaredMethod(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-99, 110, 6, 55, 126, 80, 41, 83, -76, 126, 3, 63, 87, 119}, new byte[]{-5, 28, 105, 90, 52, 3, 102, 29}), JSONObject.class);
            declaredMethod.setAccessible(true);
            return (T) declaredMethod.invoke(null, jSONObject);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            throw new IllegalArgumentException(sb.append(lllliiiill1.llllIIIIll1(new byte[]{112, -94, -49, 84, 43, 119, -62, -71, 44, -46, -34, 9, -72}, new byte[]{-106, 53, 111, -78, -104, -30, 38, 1})).append(cls.getSimpleName()).append(lllliiiill1.llllIIIIll1(new byte[]{85, 2, -79, -7, 45, 20, -30, -75, 19, -106, 87, 42, -126, -49, 29, -37, 58, -122, 82, 34, -85, -24, 114, 115, -29, 93, -34, -12, 93}, new byte[]{117, -28, 56, 71, -56, -100, 82, -107})).toString(), e);
        }
    }

    JSONObject toJSONObject() throws JSONException;
}

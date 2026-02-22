package lIllIIIlIl1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class IlIIlllllI1 {
    public static String lIIIIlllllIlll1(IlIllll1 ilIllll1) throws JSONException {
        return llllIIIIll1(ilIllll1, 2);
    }

    public static <T extends IlIllll1> T llllIIIIll1(String str, Class<T> cls) throws JSONException {
        if (cls == null) {
            return null;
        }
        return (T) IlIllll1.llllIIIIll1(new JSONObject(str), cls);
    }

    public static <T extends IlIllll1> T llllIllIl1(String str, Class<T> cls) throws JSONException {
        return (T) IlIllll1.llllIIIIll1(new JSONObject(str), cls);
    }

    public static <T extends IlIllll1> List<T> lIIIIlllllIlll1(String str, Class<T> cls) throws JSONException {
        JSONArray jSONArray = new JSONArray(str);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(IlIllll1.llllIIIIll1(jSONArray.getJSONObject(i), cls));
        }
        return arrayList;
    }

    public static String llllIIIIll1(IlIllll1 ilIllll1) throws JSONException {
        return ilIllll1.toJSONObject().toString();
    }

    public static String llllIIIIll1(IlIllll1 ilIllll1, int i) throws JSONException {
        try {
            return ilIllll1.toJSONObject().toString(i);
        } catch (Exception unused) {
            return ilIllll1.toJSONObject().toString();
        }
    }

    public static String llllIIIIll1(List<IlIllll1> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        Iterator<IlIllll1> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().toJSONObject());
        }
        return jSONArray.toString();
    }
}

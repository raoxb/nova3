package c13.nim5.ez8.h5_proto;

import IlIlllIIlI1.IllIIlIIII1;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import llllIIIIll1.lIIIIlllllIlll1;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JsonObjectUtils {
    private static Object convertPrimitive(Object obj, Class<?> cls) {
        return (cls == Boolean.TYPE || cls == Boolean.class) ? Boolean.valueOf(Boolean.parseBoolean(String.valueOf(obj))) : (cls == Integer.TYPE || cls == Integer.class) ? Integer.valueOf(Integer.parseInt(String.valueOf(obj))) : (cls == Long.TYPE || cls == Long.class) ? Long.valueOf(Long.parseLong(String.valueOf(obj))) : (cls == Float.TYPE || cls == Float.class) ? Float.valueOf(Float.parseFloat(String.valueOf(obj))) : (cls == Double.TYPE || cls == Double.class) ? Double.valueOf(Double.parseDouble(String.valueOf(obj))) : cls == String.class ? String.valueOf(obj) : obj;
    }

    public static <T> T fromJSONObject(JSONObject jSONObject, Class<T> cls) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        try {
            T newInstance = cls.newInstance();
            for (Field field : cls.getDeclaredFields()) {
                field.setAccessible(true);
                String name = field.getName();
                if (!jSONObject.has(name) || jSONObject.isNull(name)) {
                    field.set(newInstance, getDefaultValue(field.getType()));
                } else {
                    Object obj = jSONObject.get(name);
                    Class<?> type = field.getType();
                    if (!isPrimitiveOrWrapper(type) && type != String.class) {
                        if (type == List.class) {
                            if (obj instanceof JSONArray) {
                                JSONArray jSONArray = (JSONArray) obj;
                                ArrayList arrayList = new ArrayList();
                                for (int i = 0; i < jSONArray.length(); i++) {
                                    arrayList.add(jSONArray.get(i));
                                }
                                field.set(newInstance, arrayList);
                            }
                        } else if (obj instanceof JSONObject) {
                            field.set(newInstance, fromJSONObject((JSONObject) obj, type));
                        }
                    }
                    field.set(newInstance, convertPrimitive(obj, type));
                }
            }
            return newInstance;
        } catch (Exception e) {
            throw new JSONException(lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-80, -23, 93, 83, 39, 104, 46, -14, -103, -88, 87, 77, 39, 109, 122, -29, -42, -25, 86, 85, 39, 111, 122, -90, -112, -6, 91, 82, 98, 70, 93, -55, -72, -78, 20}, new byte[]{-10, -120, 52, 63, 66, 12, 14, -122}))));
        }
    }

    private static Object getDefaultValue(Class<?> cls) {
        if (cls == Boolean.TYPE || cls == Boolean.class) {
            return Boolean.FALSE;
        }
        if (cls == Byte.TYPE || cls == Byte.class) {
            return (byte) 0;
        }
        if (cls == Character.TYPE || cls == Character.class) {
            return (char) 0;
        }
        if (cls == Short.TYPE || cls == Short.class) {
            return (short) 0;
        }
        if (cls == Integer.TYPE || cls == Integer.class) {
            return 0;
        }
        if (cls == Long.TYPE || cls == Long.class) {
            return 0L;
        }
        if (cls == Float.TYPE || cls == Float.class) {
            return Float.valueOf(0.0f);
        }
        if (cls == Double.TYPE || cls == Double.class) {
            return Double.valueOf(0.0d);
        }
        if (cls == String.class) {
            return "";
        }
        if (cls == List.class) {
            return new ArrayList();
        }
        try {
            return cls.newInstance();
        } catch (Exception unused) {
            return null;
        }
    }

    private static boolean isPrimitiveOrWrapper(Class<?> cls) {
        return cls.isPrimitive() || cls == Boolean.class || cls == Byte.class || cls == Character.class || cls == Short.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class;
    }

    public static JSONObject toJSONObject(Object obj) throws JSONException {
        if (obj == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object obj2 = field.get(obj);
                String name = field.getName();
                if (obj2 != null) {
                    if (!isPrimitiveOrWrapper(obj2.getClass()) && !(obj2 instanceof String)) {
                        if (obj2 instanceof List) {
                            JSONArray jSONArray = new JSONArray();
                            for (Object obj3 : (List) obj2) {
                                if (!isPrimitiveOrWrapper(obj3.getClass()) && !(obj3 instanceof String)) {
                                    jSONArray.put(toJSONObject(obj3));
                                }
                                jSONArray.put(obj3);
                            }
                            jSONObject.put(name, jSONArray);
                        } else {
                            jSONObject.put(name, toJSONObject(obj2));
                        }
                    }
                    jSONObject.put(name, obj2);
                } else {
                    jSONObject.put(name, getDefaultValue(field.getType()));
                }
            }
            return jSONObject;
        } catch (IllegalAccessException e) {
            throw new JSONException(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-122, 51, -125, -39, 38, -125, 98, 34, -81, 114, -119, -38, 45, -111, 39, 36, -76, 114, -123, -41, 41, -126, 33, 34, -32, 38, -123, -107, 9, -76, 13, 24, -6, 114}, new byte[]{-64, 82, -22, -75, 67, -25, 66, 86}) + e.getMessage());
        }
    }
}

package c2;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: c13.nim5.ez8.h5_proto.JsonObjectUtils
 *
 * Reflection-based JSON serialization/deserialization utility.
 * Uses Java reflection to automatically map JSON fields to/from Java objects.
 * Supports primitives, wrapper types, Strings, Lists, and nested objects.
 */
public class JsonObjectUtils {

    /**
     * Converts a primitive JSON value to the target type.
     */
    private static Object convertPrimitive(Object obj, Class<?> cls) {
        if (cls == Boolean.TYPE || cls == Boolean.class) {
            return Boolean.valueOf(Boolean.parseBoolean(String.valueOf(obj)));
        }
        if (cls == Integer.TYPE || cls == Integer.class) {
            return Integer.valueOf(Integer.parseInt(String.valueOf(obj)));
        }
        if (cls == Long.TYPE || cls == Long.class) {
            return Long.valueOf(Long.parseLong(String.valueOf(obj)));
        }
        if (cls == Float.TYPE || cls == Float.class) {
            return Float.valueOf(Float.parseFloat(String.valueOf(obj)));
        }
        if (cls == Double.TYPE || cls == Double.class) {
            return Double.valueOf(Double.parseDouble(String.valueOf(obj)));
        }
        if (cls == String.class) {
            return String.valueOf(obj);
        }
        return obj;
    }

    /**
     * Deserializes a JSONObject into an instance of the given class using reflection.
     * Iterates over all declared fields, matching them by name to JSON keys.
     */
    public static <T> T fromJSONObject(JSONObject json, Class<T> cls) throws JSONException {
        if (json == null) {
            return null;
        }
        try {
            T instance = cls.newInstance();
            for (Field field : cls.getDeclaredFields()) {
                field.setAccessible(true);
                String name = field.getName();
                if (!json.has(name) || json.isNull(name)) {
                    field.set(instance, getDefaultValue(field.getType()));
                } else {
                    Object obj = json.get(name);
                    Class<?> type = field.getType();
                    if (!isPrimitiveOrWrapper(type) && type != String.class) {
                        if (type == List.class) {
                            if (obj instanceof JSONArray) {
                                JSONArray jsonArray = (JSONArray) obj;
                                ArrayList<Object> list = new ArrayList<>();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    list.add(jsonArray.get(i));
                                }
                                field.set(instance, list);
                            }
                        } else if (obj instanceof JSONObject) {
                            field.set(instance, fromJSONObject((JSONObject) obj, type));
                        }
                    }
                    field.set(instance, convertPrimitive(obj, type));
                }
            }
            return instance;
        } catch (Exception e) {
            throw new JSONException("Failed to create object from JSON: " + e);
        }
    }

    /**
     * Returns the default value for the given type (0 for numbers, false for boolean, "" for String, etc.)
     */
    private static Object getDefaultValue(Class<?> cls) {
        if (cls == Boolean.TYPE || cls == Boolean.class) return Boolean.FALSE;
        if (cls == Byte.TYPE || cls == Byte.class) return (byte) 0;
        if (cls == Character.TYPE || cls == Character.class) return (char) 0;
        if (cls == Short.TYPE || cls == Short.class) return (short) 0;
        if (cls == Integer.TYPE || cls == Integer.class) return 0;
        if (cls == Long.TYPE || cls == Long.class) return 0L;
        if (cls == Float.TYPE || cls == Float.class) return Float.valueOf(0.0f);
        if (cls == Double.TYPE || cls == Double.class) return Double.valueOf(0.0d);
        if (cls == String.class) return "";
        if (cls == List.class) return new ArrayList<>();
        try {
            return cls.newInstance();
        } catch (Exception unused) {
            return null;
        }
    }

    /**
     * Checks if the given class is a primitive type or its wrapper.
     */
    private static boolean isPrimitiveOrWrapper(Class<?> cls) {
        return cls.isPrimitive() || cls == Boolean.class || cls == Byte.class
                || cls == Character.class || cls == Short.class || cls == Integer.class
                || cls == Long.class || cls == Float.class || cls == Double.class;
    }

    /**
     * Serializes an arbitrary object to a JSONObject using reflection.
     * Iterates over all declared fields, writing their values as JSON keys.
     * Recursively serializes nested objects and lists.
     */
    public static JSONObject toJSONObject(Object obj) throws JSONException {
        if (obj == null) {
            return null;
        }
        JSONObject json = new JSONObject();
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(obj);
                String name = field.getName();
                if (value != null) {
                    if (!isPrimitiveOrWrapper(value.getClass()) && !(value instanceof String)) {
                        if (value instanceof List) {
                            JSONArray jsonArray = new JSONArray();
                            for (Object item : (List<?>) value) {
                                if (!isPrimitiveOrWrapper(item.getClass()) && !(item instanceof String)) {
                                    jsonArray.put(toJSONObject(item));
                                } else {
                                    jsonArray.put(item);
                                }
                            }
                            json.put(name, jsonArray);
                        } else {
                            json.put(name, toJSONObject(value));
                        }
                    } else {
                        json.put(name, value);
                    }
                } else {
                    json.put(name, getDefaultValue(field.getType()));
                }
            }
            return json;
        } catch (IllegalAccessException e) {
            throw new JSONException("Failed to convert object to JSON: " + e.getMessage());
        }
    }
}

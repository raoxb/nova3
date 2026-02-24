package screenshot;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * MALWARE ANALYSIS — Reflection utility class
 *
 * Original: IIIlIllIlI1.llllIIIIll1
 *
 * Provides static reflection helper methods for accessing private/hidden fields
 * and methods. Used extensively for accessing Chromium WebView internals.
 *
 * Methods:
 *   - getField(Class, String):           find field by name, traversing superclasses
 *   - getAccessibleField(Class, String): find + setAccessible(true)
 *   - getFieldValue(Object, String):     read field value from object
 *   - getStaticFieldValue(Class, String): read static field value
 *   - setFieldValue(Object, String, Object): write field value (throws)
 *   - setFieldValueSafe(Object, String, Object): write field value (catches)
 *   - findMethod(Class, String, Class...): find method, traversing superclasses
 *   - getAccessibleMethod(Class, String, Class...): find + setAccessible(true)
 *   - invokeConstructor(Class, Class[], Object...): reflective constructor call
 */
public class ReflectionUtils {

    /**
     * Set a field value (throws on failure).
     * Original: lIIIIlllllIlll1(Object, String, Object)
     */
    public static void setFieldValue(Object obj, String fieldName, Object value)
            throws IllegalAccessException {
        getAccessibleField(obj.getClass(), fieldName).set(obj, value);
    }

    /**
     * Set a field value (catches exceptions).
     * Original: llllIIIIll1(Object, String, Object)
     */
    public static void setFieldValueSafe(Object obj, String fieldName, Object value) {
        try {
            getAccessibleField(obj.getClass(), fieldName).set(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a static field value.
     * Original: llllIllIl1(Class, String) -> T
     */
    @SuppressWarnings("unchecked")
    public static <T> T getStaticFieldValue(Class<?> cls, String fieldName)
            throws IllegalAccessException {
        Field field = getAccessibleField(cls, fieldName);
        return field != null ? (T) field.get(null) : null;
    }

    /**
     * Get a field value from an object instance.
     * Original: llllIIIIll1(Object, String) -> T
     */
    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(Object obj, String fieldName)
            throws IllegalAccessException {
        Field field = getAccessibleField(obj.getClass(), fieldName);
        return field != null ? (T) field.get(obj) : null;
    }

    /**
     * Find a field and make it accessible.
     * Original: lIIIIlllllIlll1(Class, String) -> Field
     */
    public static Field getAccessibleField(Class<?> cls, String fieldName) {
        Field field = findField(cls, fieldName);
        if (field != null) {
            field.setAccessible(true);
        }
        return field;
    }

    /**
     * Find a field by name, traversing the class hierarchy.
     * Original: llllIIIIll1(Class, String) -> Field
     */
    public static Field findField(Class<?> cls, String fieldName) {
        while (cls != null) {
            try {
                return cls.getDeclaredField(fieldName);
            } catch (Exception unused) {
                cls = cls.getSuperclass();
            }
        }
        return null;
    }

    /**
     * Find a method by name and parameter types, traversing superclasses.
     * Original: lIIIIlllllIlll1(Class, String, Class...) -> Method
     */
    public static Method findMethod(Class<?> cls, String methodName, Class<?>... paramTypes) {
        while (cls != null) {
            try {
                return cls.getDeclaredMethod(methodName, paramTypes);
            } catch (Exception unused) {
                cls = cls.getSuperclass();
            }
        }
        return null;
    }

    /**
     * Find a method and make it accessible.
     * Original: llllIIIIll1(Class, String, Class...) -> Method
     */
    public static Method getAccessibleMethod(Class<?> cls, String methodName,
                                              Class<?>... paramTypes) {
        Method method = findMethod(cls, methodName, paramTypes);
        if (method != null) {
            method.setAccessible(true);
        }
        return method;
    }

    /**
     * Invoke a constructor via reflection.
     * Original: llllIIIIll1(Class, Class[], Object...) -> Object
     */
    public static Object invokeConstructor(Class<?> cls, Class<?>[] paramTypes, Object... args)
            throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        return cls.getDeclaredConstructor(paramTypes).newInstance(args);
    }
}

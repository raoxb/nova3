package IIIlIllIlI1;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class llllIIIIll1 {
    public static void lIIIIlllllIlll1(Object obj, String str, Object obj2) throws IllegalAccessException {
        lIIIIlllllIlll1(obj.getClass(), str).set(obj, obj2);
    }

    public static void llllIIIIll1(Object obj, String str, Object obj2) {
        try {
            lIIIIlllllIlll1(obj.getClass(), str).set(obj, obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T llllIllIl1(Class<?> cls, String str) throws IllegalAccessException {
        Field lIIIIlllllIlll12 = lIIIIlllllIlll1(cls, str);
        if (lIIIIlllllIlll12 != null) {
            return (T) lIIIIlllllIlll12.get(null);
        }
        return null;
    }

    public static Field lIIIIlllllIlll1(Class<?> cls, String str) {
        Field llllIIIIll12 = llllIIIIll1(cls, str);
        if (llllIIIIll12 != null) {
            llllIIIIll12.setAccessible(true);
        }
        return llllIIIIll12;
    }

    public static Field llllIIIIll1(Class<?> cls, String str) {
        while (cls != null) {
            try {
                return cls.getDeclaredField(str);
            } catch (Exception unused) {
                cls = cls.getSuperclass();
            }
        }
        return null;
    }

    public static Method lIIIIlllllIlll1(Class<?> cls, String str, Class<?>... clsArr) {
        while (cls != null) {
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (Exception unused) {
                cls = cls.getSuperclass();
            }
        }
        return null;
    }

    public static Object llllIIIIll1(Class<?> cls, Class<?>[] clsArr, Object... objArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return cls.getDeclaredConstructor(clsArr).newInstance(objArr);
    }

    public static Method llllIIIIll1(Class<?> cls, String str, Class<?>... clsArr) {
        Method lIIIIlllllIlll12 = lIIIIlllllIlll1(cls, str, clsArr);
        if (lIIIIlllllIlll12 != null) {
            lIIIIlllllIlll12.setAccessible(true);
        }
        return lIIIIlllllIlll12;
    }

    public static <T> T llllIIIIll1(Object obj, String str) throws IllegalAccessException {
        Field lIIIIlllllIlll12 = lIIIIlllllIlll1(obj.getClass(), str);
        if (lIIIIlllllIlll12 != null) {
            return (T) lIIIIlllllIlll12.get(obj);
        }
        return null;
    }
}

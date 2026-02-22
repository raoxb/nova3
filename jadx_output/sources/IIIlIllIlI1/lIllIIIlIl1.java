package IIIlIllIlI1;

import IIIlIllIlI1.lIllIIIlIl1;
import android.os.Build;
import android.webkit.WebView;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import c13.nim5.ez8.h5_proto.Log;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class lIllIIIlIl1 {

    /* renamed from: IlIlIIlIII1, reason: collision with root package name */
    public static final String f18IlIlIIlIII1;

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public static final String f19IlIllIlllIllI1;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public static final String f20IlIlllIIlI1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public static final String f21IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public static final String f22lIIIIlllllIlll1;

    /* renamed from: lIllIIIlIl1, reason: collision with root package name */
    public static final String f23lIllIIIlIl1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public static final String f24llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public static final String f25llllIllIl1;

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public static final String f26llllllIlIIIlll1;

    public static class lIIIIlllllIlll1 implements InvocationHandler {

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final String[] f27llllIIIIll1;

        public lIIIIlllllIlll1(String[] strArr) {
            this.f27llllIIIIll1 = strArr;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            char c;
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            if (lllliiiill1.llllIIIIll1(new byte[]{-92, 105, 105, 91, -56, 7, 0, -25, -72, 116, 88, 83, -34}, new byte[]{-53, 7, 57, 52, -69, 115, 77, -126}).equals(method.getName()) && objArr.length == 5) {
                try {
                    Object obj2 = objArr[1].getClass().getFields()[0].get(objArr[1]);
                    String str = (String) obj2.getClass().getMethod(lllliiiill1.llllIIIIll1(new byte[]{-101, -97, 88, 119, 3, -97, -117}, new byte[]{-4, -6, 44, 51, 98, -21, -22, 122}), null).invoke(obj2, null);
                    if (str != null && !str.isEmpty()) {
                        Boolean bool = (Boolean) objArr[3];
                        llllIllIl1.llllIIIIll1 llllIIIIll12 = llllIllIl1.llllIIIIll1(str);
                        if (llllIIIIll12 == null) {
                            return null;
                        }
                        String lIIIIlllllIlll12 = lIllIIIlIl1.lIIIIlllllIlll1(llllIIIIll12, bool.booleanValue());
                        if (!lIIIIlllllIlll12.isEmpty()) {
                            Object obj3 = objArr[4].getClass().getFields()[0].get(objArr[4]);
                            obj3.getClass().getMethod(lllliiiill1.llllIIIIll1(new byte[]{9, 56, 31, -28, -98, -115, 46, 113, 24, 48, 9}, new byte[]{121, 87, 108, -112, -45, -24, 93, 2}), String.class).invoke(obj3, lIIIIlllllIlll12);
                        }
                    }
                    return null;
                } catch (Exception e) {
                    IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill12.llllIIIIll1(new byte[]{-52, 115, -122, 99, 60, -77, 59, 26, -2, 116, -87, 80, 38, -91, 45, 42, -2, 67, -112, 92, 57, -91}, new byte[]{-101, 22, -28, 53, 85, -42, 76, 77}), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(lllliiiill12.llllIIIIll1(new byte[]{-111, 21, -24, 39, -101, 71, 121, 85, -72, 84, -23, 42, -112, 71, 53, 68, -9, 3, -28, 41, -34, 78, 60, 82, -92, 21, -26, 46, -60, 3}, new byte[]{-41, 116, -127, 75, -2, 35, 89, 33}))));
                }
            }
            String name = method.getName();
            int hashCode = name.hashCode();
            if (hashCode == -1776922004) {
                if (name.equals(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{106, -13, 0, -39, -117, 122, 55, -85}, new byte[]{30, -100, 83, -83, -7, 19, 89, -52}))) {
                    c = 0;
                }
                c = 65535;
            } else if (hashCode == -1295482945) {
                if (name.equals(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{28, -9, 23, -28, 65, 11}, new byte[]{121, -122, 98, -123, 45, 120, 85, -99}))) {
                    c = 2;
                }
                c = 65535;
            } else if (hashCode != 147696667) {
                if (hashCode == 1442649685 && name.equals(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-54, -97, -117, 98, -60, 88, -79, 71, -33, -114, -102, 85, -9, 77, -96, 92, -40, -120, -102, 66}, new byte[]{-83, -6, -1, 49, -79, 40, -63, 40}))) {
                    c = 3;
                }
                c = 65535;
            } else {
                if (name.equals(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-108, -123, 73, -15, 28, -69, -96, -117}, new byte[]{-4, -28, 58, -103, 95, -44, -60, -18}))) {
                    c = 1;
                }
                c = 65535;
            }
            if (c == 0) {
                return IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-40, 59, -37, 79, -105, 118, -41, -67, -6, 105, -29, 82, -116, 27, -44, -95, -5, 40, -45, 82, -94, 63, -62, -90, -19, 39, -47, 69, -64, 120, -97}, new byte[]{-120, 73, -76, 55, -18, 86, -79, -46});
            }
            if (c == 1) {
                return Integer.valueOf(System.identityHashCode(obj));
            }
            if (c == 2) {
                return Boolean.valueOf(obj == objArr[0]);
            }
            if (c != 3) {
                return null;
            }
            IllIIlIIII1.llllIIIIll1 lllliiiill13 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            return new String[]{lllliiiill13.llllIIIIll1(new byte[]{103, 124, 59, 87, -121, 113, -14, -59, 113, 126, 60, 87, -122, 125, -14, -62, 117, 119, 60, 90}, new byte[]{48, 57, 121, 8, -54, 52, -95, -106}), lllliiiill13.llllIIIIll1(new byte[]{-81, -47, -43, -59, -104, 68, -61, -20, -71, -45, -46, -59, -108, 83, -62, -2, -95, -53, -43, -49, -109, 71, -43, -19}, new byte[]{-8, -108, -105, -102, -43, 1, -112, -65})};
        }
    }

    public class llllIIIIll1 extends HashSet<String> {
        public llllIIIIll1() {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            add(lllliiiill1.llllIIIIll1(new byte[]{78, -45, 43, 61, 74, -100, 126, -56, 12, -119, 59, 34, 76, -60, 61, -126, 69, -53, 54, 46, 82, -120, 63, -126, 82}, new byte[]{38, -89, 95, 77, 57, -90, 81, -25}));
            add(lllliiiill1.llllIIIIll1(new byte[]{121, 22, -40, 99, 44, -6, -24, 5, 59, 76, -53, 124, 48, -89, -85, 79, 98, 27, -62, 119, 54, -93, -90, 94, 120, 13, -62, 61, 60, -81, -86}, new byte[]{17, 98, -84, 19, 95, -64, -57, 42}));
            add(lllliiiill1.llllIIIIll1(new byte[]{-4, ByteCompanionObject.MIN_VALUE, -14, 71, -78, 95, -18, -52, -25, -115, -24, 83, -88, 6, -96, -105, -15, -112, -11, 82, -96, 23, -94, -117, -70, -109, -23, 88, -90}, new byte[]{-108, -12, -122, 55, -63, 101, -63, -29}));
        }
    }

    static {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        f24llllIIIIll1 = lllliiiill1.llllIIIIll1(new byte[]{20, -120, -17, 52, -90, -38, -8, -48, 38, -113, -64, 7, -68, -52, -18, -32, 38, -72, -7, 11, -93, -52}, new byte[]{67, -19, -115, 98, -49, -65, -113, -121});
        f22lIIIIlllllIlll1 = lllliiiill1.llllIIIIll1(new byte[]{-57, -72, 1, 71, 117, -11, 117, -79, -59, -93, 19, 4, 56, -18, 114, -82, -40, -91, 20, 29, 73, -15, 110, -68, -9, -83, 10, 28, 115, -77, 84, -85, -40, -70, 9, 27, 98, -47, 110, -68, -6, -81, 0, 5, 115, -2, 115, -73, -57, -92, 51, 29, ByteCompanionObject.MAX_VALUE, -15}, new byte[]{-88, -54, 102, 105, 22, -99, 7, -34});
        f25llllIllIl1 = lllliiiill1.llllIIIIll1(new byte[]{-32, -76, -118, -59, -49, -126, 57, -28, -31, -112, -122, -63, -52, -73, 28, -18, -11, -81, -117, -63, -55, -95, 15, -30, -9, -87, -99, -35}, new byte[]{-125, -58, -17, -92, -69, -25, 110, -127});
        f21IllIIlIIII1 = lllliiiill1.llllIIIIll1(new byte[]{-78, 60, -77, 99, 113}, new byte[]{-37, 17, -63, 6, 0, 80, 108, -109});
        f20IlIlllIIlI1 = lllliiiill1.llllIIIIll1(new byte[]{72, 49, 51, -89, -41}, new byte[]{33, 28, 82, -43, -80, -91, 1, 90});
        f19IlIllIlllIllI1 = lllliiiill1.llllIIIIll1(new byte[]{-71, 46, -56, -19, 6}, new byte[]{-48, 3, -87, -125, 117, 8, 1, -11});
        f26llllllIlIIIlll1 = lllliiiill1.llllIIIIll1(new byte[]{-109, -108, 25, -93, 54}, new byte[]{-2, -71, 107, -58, 71, -121, -90, -1});
        f18IlIlIIlIII1 = lllliiiill1.llllIIIIll1(new byte[]{-126, 0, 5, -10, -25}, new byte[]{-17, 45, 100, -123, -116, -119, 48, 49});
        f23lIllIIIlIl1 = lllliiiill1.llllIIIIll1(new byte[]{10, -84, -118, -101, -62}, new byte[]{103, -127, -20, -14, -84, 88, -102, 83});
    }

    public static boolean IlIlllIIlI1() {
        return Arrays.asList(lIIIIlllllIlll1()).contains(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{42, 63, 54, -74, -23, -6, 29, -4, 60, 61, 49, -74, -24, -10, 29, -5, 56, 52, 49, -69}, new byte[]{125, 122, 116, -23, -92, -65, 78, -81}));
    }

    public static Object IllIIlIIII1() {
        try {
            Method declaredMethod = WebView.class.getDeclaredMethod(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-27, 32, 49, 17, -111, -91, 55, 76, -16, 60}, new byte[]{-126, 69, 69, 87, -16, -58, 67, 35}), null);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(null, null);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] lIIIIlllllIlll1() {
        String[] strArr = new String[0];
        try {
            InvocationHandler llllIIIIll12 = llllIIIIll1();
            Object obj = llllIIIIll12.getClass().getFields()[0].get(llllIIIIll12);
            Class<?> cls = obj.getClass();
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            Object invoke = cls.getMethod(lllliiiill1.llllIIIIll1(new byte[]{-30, 121, 57, -122, -53, 25, -87, -95, -9, 104, 40, -79, -8, 12, -72, -70, -16, 110, 40, -90}, new byte[]{-123, 28, 77, -43, -66, 105, -39, -50}), null).invoke(obj, null);
            if (invoke instanceof String[]) {
                strArr = (String[]) invoke;
            }
            if (strArr.length == 0) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, f24llllIIIIll1, lllliiiill1.llllIIIIll1(new byte[]{48, 47, 30, -125, -40, 50, 109, -114, 0, 47, 48, -89, -50, 53, 123, -127, 2, 56, 92, -89, -50, 97, 112, ByteCompanionObject.MIN_VALUE, 19, 106, 15, -69, -51, 49, 113, -99, 19, 47, 24, -18, -46, 47, 62, -101, 15, 35, 15, -18, -39, 36, 104, -122, 4, 47, 92, -95, -49, 97, 73, -118, 5, 28, 21, -85, -54, 97, 104, -118, 21, 57, 21, -95, -45, 111}, new byte[]{103, 74, 124, -50, -67, 65, 30, -17}));
            }
            return strArr;
        } catch (Throwable th) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.WARN, f24llllIIIIll1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-14, -33, 103, 126, -24, 56, 20, -55, -13, -102, 111, 120, -16, 107, 2, -53, -12, -102, 100, 120, -30, 108, 22, -34, -12, -105, 113, 104, -13, 104, 12, -34, -27, -46, 102, 61, -27, 121, 10, -64, -12, -45, 34, 106, -22, 108, 11, -116, -12, -49, 97, 120, -13, 108, 10, -61, -1, -105}, new byte[]{-111, -73, 2, 29, -125, 24, 99, -84}) + th);
            return strArr;
        }
    }

    public static ClassLoader llllIllIl1() {
        return Build.VERSION.SDK_INT >= 28 ? WebView.getWebViewClassLoader() : IllIIlIIII1().getClass().getClassLoader();
    }

    public static InvocationHandler llllIIIIll1() throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException {
        return (InvocationHandler) Class.forName(f22lIIIIlllllIlll1, false, llllIllIl1()).getDeclaredMethod(f25llllIllIl1, null).invoke(null, null);
    }

    public static class llllIllIl1 {

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public static final String f32llllIIIIll1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-25, 85, -7, -119}, new byte[]{-108, 33, -106, -7, 125, -2, -105, -23});

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public static final Integer f31lIIIIlllllIlll1 = Integer.valueOf(PathInterpolatorCompat.MAX_NUM_POINTS);

        /* renamed from: llllIllIl1, reason: collision with root package name */
        public static String f33llllIllIl1 = "";

        /* renamed from: IllIIlIIII1, reason: collision with root package name */
        public static List<JSONObject> f30IllIIlIIII1 = new ArrayList(20);

        /* renamed from: IlIlllIIlI1, reason: collision with root package name */
        public static boolean f29IlIlllIIlI1 = false;

        /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
        public static Map<String, Integer> f28IlIllIlllIllI1 = new ConcurrentHashMap(25);

        /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
        public static Map<String, Long> f34llllllIlIIIlll1 = new ConcurrentHashMap(25);

        public static class llllIIIIll1 {

            /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
            public String f35lIIIIlllllIlll1;

            /* renamed from: llllIIIIll1, reason: collision with root package name */
            public String f36llllIIIIll1;

            /* renamed from: llllIllIl1, reason: collision with root package name */
            public String f37llllIllIl1;

            public JSONObject llllIIIIll1() {
                JSONObject jSONObject = new JSONObject();
                try {
                    IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                    jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-97, -114, -11, -92}, new byte[]{-21, -9, -123, -63, 20, 56, -104, -97}), this.f36llllIIIIll1);
                    jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{25, -15, -54}, new byte[]{116, -126, -83, 46, 43, -39, 126, 70}), this.f35lIIIIlllllIlll1);
                    String llllIIIIll12 = lllliiiill1.llllIIIIll1(new byte[]{-124, -5}, new byte[]{-19, -97, -36, -79, -22, -9, 99, -124});
                    String str = this.f37llllIllIl1;
                    if (str == null) {
                        str = "";
                    }
                    jSONObject.put(llllIIIIll12, str);
                } catch (JSONException unused) {
                }
                return jSONObject;
            }
        }

        public static void lIIIIlllllIlll1() {
            f33llllIllIl1 = "";
            f30IllIIlIIII1.clear();
            f29IlIlllIIlI1 = false;
            f28IlIllIlllIllI1.clear();
            f34llllllIlIIIlll1.clear();
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.DEBUG, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-43, 21, 3, 25, -112, 49, -83, -58, -29, 31, 28, 9, -39, 98, -85, -125, -12, 21, 6, 76, -122, 54, -72, -110, -30, 94}, new byte[]{-121, 112, 114, 108, -11, 66, -39, -26}));
        }

        public static boolean llllIIIIll1(String str, Integer... numArr) {
            Integer orDefault = f28IlIllIlllIllI1.getOrDefault(str, -1);
            for (Integer num : numArr) {
                if (Objects.equals(orDefault, num)) {
                    return true;
                }
            }
            return false;
        }

        public static boolean llllIllIl1() {
            long currentTimeMillis = System.currentTimeMillis();
            for (Map.Entry<String, Integer> entry : f28IlIllIlllIllI1.entrySet()) {
                String key = entry.getKey();
                if (entry.getValue().intValue() == 1) {
                    Long orDefault = f34llllllIlIIIlll1.getOrDefault(key, 0L);
                    if (orDefault.longValue() > 0 && currentTimeMillis - orDefault.longValue() > f31lIIIIlllllIlll1.intValue()) {
                        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                        lllliiiill1.llllIIIIll1(new byte[]{84, 74, -90, -69, -15, 93, 33, -96, 102, 77, -119, -120, -21, 75, 55, -112, 102, 122, -80, -124, -12, 75}, new byte[]{3, 47, -60, -19, -104, 56, 86, -9});
                        lllliiiill1.llllIIIIll1(new byte[]{72, 45, -45, 102, -3, -45, 101}, new byte[]{33, 75, -95, 7, -112, -74, 69, -3});
                        lllliiiill1.llllIIIIll1(new byte[]{-86, -85, -69, 99, 119, -46, 30, -61, -90, -1, -95, 122, 115, -55, 14, -115, -86}, new byte[]{-118, -33, -46, 14, 18, -67, 107, -73});
                        return true;
                    }
                }
            }
            return false;
        }

        public static void llllIIIIll1(String str, Integer num) {
            f28IlIllIlllIllI1.put(str, num);
            if (num.intValue() == 1) {
                f34llllllIlIIIlll1.put(str, Long.valueOf(System.currentTimeMillis()));
            }
            if (num.intValue() == 2) {
                f34llllllIlIIIlll1.remove(str);
            }
            StringBuilder sb = new StringBuilder();
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.DEBUG, "", sb.append(lllliiiill1.llllIIIIll1(new byte[]{28, 71, 116, -51, 85, 123, 64, 38, 79, 1}, new byte[]{117, 33, 6, -84, 56, 30, 9, 66})).append(str).append(lllliiiill1.llllIIIIll1(new byte[]{112, -80, -104, 115, -52, -109, 77, ByteCompanionObject.MAX_VALUE, 61, -28, -114, 44, -104}, new byte[]{92, -112, -21, 22, -72, -77, 62, 11})).append(num).toString());
        }

        public static boolean llllIIIIll1() {
            return f28IlIllIlllIllI1.values().stream().filter(new Predicate() { // from class: IIIlIllIlI1.lIllIIIlIl1$llllIllIl1$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return lIllIIIlIl1.llllIllIl1.llllIIIIll1((Integer) obj);
                }
            }).count() == ((long) f28IlIllIlllIllI1.size());
        }

        public static /* synthetic */ boolean llllIIIIll1(Integer num) {
            return num.intValue() == 2 || num.intValue() == 0;
        }

        public static llllIIIIll1 llllIIIIll1(String str) {
            llllIIIIll1 lllliiiill1 = new llllIIIIll1();
            if (str != null && !str.isEmpty()) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                    lllliiiill1.f36llllIIIIll1 = jSONObject.get(lllliiiill12.llllIIIIll1(new byte[]{-111, 26, 68, 82}, new byte[]{-27, 99, 52, 55, 44, -46, 98, 22})).toString();
                    lllliiiill1.f35lIIIIlllllIlll1 = jSONObject.get(lllliiiill12.llllIIIIll1(new byte[]{47, 117, 43}, new byte[]{66, 6, 76, -72, 91, -39, 7, -99})).toString();
                    lllliiiill1.f37llllIllIl1 = jSONObject.has(lllliiiill12.llllIIIIll1(new byte[]{18, 104}, new byte[]{123, 12, 77, -117, 101, 26, -39, 2})) ? jSONObject.get(lllliiiill12.llllIIIIll1(new byte[]{26, -9}, new byte[]{115, -109, -26, 123, 86, 28, -91, 103})).toString() : "";
                    return lllliiiill1;
                } catch (Exception e) {
                    IllIIlIIII1.llllIIIIll1 lllliiiill13 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.WARN, lllliiiill13.llllIIIIll1(new byte[]{43, -77, -38, 91, -71, 104, 109, 61, 25, -76, -11, 104, -93, 126, 123, 13, 25, -125, -52, 100, -68, 126}, new byte[]{124, -42, -72, 13, -48, 13, 26, 106}), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(lllliiiill13.llllIIIIll1(new byte[]{82, 22, 48, -102, -48, -53, 100, -60, 123, 87, 41, -105, -57, -36, 33, -112, 100, 22, 62, -109, -107, -62, 33, -61, 103, 22, 62, -109, -113, -113}, new byte[]{20, 119, 89, -10, -75, -81, 68, -80}))));
                }
            }
            return null;
        }
    }

    public static void llllIIIIll1(WebView webView, Set<String> set, String str) {
        lllllIllIl1.IllIIlIIII1.llllIllIl1(f24llllIIIIll1, IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-60, 103, 14, -69, 85, 48, 10, 91, -56, 102, 25, -24, 67, 50, 13, 91, -55, 106, 25, -17, 71, 59, 13, 9, -123, 108, 4, -69, 85, 48, 10, 13, -52, 102, 29, -73, 2, 32, 26, 23, -97, 35}, new byte[]{-91, 3, 106, -101, 34, 85, 104, 123}) + str);
        try {
            String[] lIIIIlllllIlll12 = lIIIIlllllIlll1();
            if (Arrays.asList(lIIIIlllllIlll12).contains(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{112, -122, -119, -43, 124, -1, -84, -88, 102, -124, -114, -43, 125, -13, -84, -81, 98, -115, -114, -40}, new byte[]{39, -61, -53, -118, 49, -70, -1, -5}))) {
                InvocationHandler llllIIIIll12 = llllIIIIll1();
                Object obj = llllIIIIll12.getClass().getFields()[0].get(llllIIIIll12);
                Object invoke = obj.getClass().getMethod(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{30, -82, -45, -123, 16, -2, -65, -126, 31, -118, -33, -127, 19}, new byte[]{125, -36, -74, -28, 100, -101, -24, -25}), WebView.class).invoke(obj, webView);
                Object obj2 = invoke.getClass().getFields()[0].get(invoke);
                obj2.getClass().getMethod(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-74, 20, 25, -119, 113, -107, -121, -38, -92, 3, 28, -71, 113, -69, -93, -52, -93, 21, 19, -69, 102}, new byte[]{-41, 112, 125, -34, 20, -9, -54, -65}), String.class, String[].class, InvocationHandler.class).invoke(obj2, IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{3, -117, 41, -75}, new byte[]{112, -1, 70, -59, 106, 122, 98, -32}), (String[]) ((AbstractCollection) llllIIIIll1(set, str)).toArray(new String[0]), new lIIIIlllllIlll1(lIIIIlllllIlll12));
            }
        } catch (InvocationTargetException e) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(f24llllIIIIll1, IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{39, 27, 117, 109, -83, -121, 32, 63, 53, 12, 112, 93, -83, -87, 4, 41, 50, 26, ByteCompanionObject.MAX_VALUE, 95, -70, -59, 11, 59, 47, 19, 116, 94, -24, -110, 4, 46, 46, 95, 88, 84, -66, -118, 14, 59, 50, 22, 126, 84, -100, -124, 31, 61, 35, 11, 84, 66, -85, ByteCompanionObject.MIN_VALUE, 29, 46, 47, 16, ByteCompanionObject.MAX_VALUE, 0, -24}, new byte[]{70, ByteCompanionObject.MAX_VALUE, 17, 58, -56, -27, 109, 90}) + e.getTargetException().getMessage());
        } catch (Throwable th) {
            lllllIllIl1.IllIIlIIII1.IllIIlIIII1(f24llllIIIIll1, IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{71, 67, 38, 52, 97, 106, 101, 105, 85, 84, 35, 4, 97, 68, 65, ByteCompanionObject.MAX_VALUE, 82, 66, 44, 6, 118, 40, 78, 109, 79, 75, 39, 7, 36, ByteCompanionObject.MAX_VALUE, 65, 120, 78, 7, 39, 27, 103, 109, 88, 120, 79, 72, 44, 89, 36}, new byte[]{38, 39, 66, 99, 4, 8, 40, 12}) + th + IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-88, -59}, new byte[]{-109, -27, -32, 72, -122, -100, 79, -20}) + th.getCause());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0189  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String lIIIIlllllIlll1(IIIlIllIlI1.lIllIIIlIl1.llllIllIl1.llllIIIIll1 r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 556
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: IIIlIllIlI1.lIllIIIlIl1.lIIIIlllllIlll1(IIIlIllIlI1.lIllIIIlIl1$llllIllIl1$llllIIIIll1, boolean):java.lang.String");
    }

    public static Set<String> llllIIIIll1(Set<String> set, String str) {
        llllIIIIll1 lllliiiill1 = new llllIIIIll1();
        if (!set.isEmpty()) {
            lllliiiill1.addAll(set);
        }
        String llllIIIIll12 = llllIIIIll1(str);
        if (!llllIIIIll12.isEmpty()) {
            lllliiiill1.add(llllIIIIll12);
        }
        IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-96, 55, -62, -110, -82, 104, -21, -71, -114, 41, -57, -102, -80, 99, -81, -21, -108, 55, -53, -114, -7, 126, -26, -29, -124, 97, -114}, new byte[]{-31, 91, -82, -3, -39, 13, -113, -103});
        lllliiiill1.size();
        return lllliiiill1;
    }

    public static String llllIIIIll1(String str) {
        String str2;
        try {
            URL url = new URL(str);
            String protocol = url.getProtocol();
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            if (!protocol.equals(lllliiiill1.llllIIIIll1(new byte[]{92, 9, -91, -94}, new byte[]{52, 125, -47, -46, -22, 99, 12, -110})) && !protocol.equals(lllliiiill1.llllIIIIll1(new byte[]{65, 21, -87, 125, 16}, new byte[]{41, 97, -35, 13, 99, ByteCompanionObject.MIN_VALUE, 75, -96}))) {
                return "";
            }
            if (url.getPort() <= 0) {
                str2 = "";
            } else {
                str2 = lllliiiill1.llllIIIIll1(new byte[]{-42}, new byte[]{-20, -99, 27, -112, 40, -61, 54, 96}) + url.getPort();
            }
            return protocol + lllliiiill1.llllIIIIll1(new byte[]{-102, 117, 65}, new byte[]{-96, 90, 110, 81, 38, 29, 59, 124}) + url.getHost() + str2;
        } catch (MalformedURLException e) {
            StringBuilder sb = new StringBuilder();
            IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.DEBUG, "", sb.append(lllliiiill12.llllIIIIll1(new byte[]{ByteCompanionObject.MAX_VALUE, -28, -112, 61, -78, -73, -97, 68, 86, -91, -87, 9, -111, -1, -46}, new byte[]{50, -123, -4, 91, -35, -59, -14, 33})).append(str).append(lllliiiill12.llllIIIIll1(new byte[]{111, -89, 80, -22, 57, 22, -25, -83, 99}, new byte[]{67, -121, 53, -104, 75, 121, -107, -105})).append(e.getMessage()).toString());
            return "";
        }
    }
}

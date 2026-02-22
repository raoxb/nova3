package IlIllll1;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class llllIIIIll1 {

    /* renamed from: IlIlIIlIII1, reason: collision with root package name */
    public static final List<String> f286IlIlIIlIII1;

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public static List<String> f287IlIllIlllIllI1;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public static List<String> f288IlIlllIIlI1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public static String f289IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public static boolean f290lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public static final String f291llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public static String f292llllIllIl1;

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public static boolean f293llllllIlIIIlll1;

    static {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        f291llllIIIIll1 = lllliiiill1.llllIIIIll1(new byte[]{-28, 34, 45, -5, 76, 115, -64, -79, -59, 42}, new byte[]{-84, 77, 66, -112, 15, 28, -82, -41});
        f288IlIlllIIlI1 = new ArrayList();
        f287IlIllIlllIllI1 = new ArrayList();
        f286IlIlIIlIII1 = Arrays.asList(lllliiiill1.llllIIIIll1(new byte[]{-107, 73, -35, -44, 32, -24, -111, -55, -105, 82, -49, -105, 109, -30, -126, -43, -97, 21, -8, -113, 42, -20, -121, -17, -108, 93, -43}, new byte[]{-6, 59, -70, -6, 67, ByteCompanionObject.MIN_VALUE, -29, -90}), lllliiiill1.llllIIIIll1(new byte[]{41, -111, 52, -98, 107, -28, 62, -17, 43, -118, 38, -35, 38, -18, 45, -13, 35, -51, 18, -64, 99, -59, 34, -26, 41}, new byte[]{70, -29, 83, -80, 8, -116, 76, ByteCompanionObject.MIN_VALUE}));
    }

    public static boolean IllIIlIIII1() {
        return f293llllllIlIIIlll1;
    }

    public static String lIIIIlllllIlll1() {
        return f289IllIIlIIII1;
    }

    public static final void llllIIIIll1(Context context) {
        List<String> list = f286IlIlIIlIII1;
        f288IlIlllIIlI1 = list;
        f287IlIllIlllIllI1 = list;
        f289IllIIlIIII1 = context.getPackageName();
        List asList = Arrays.asList(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{107, -106, 56, 53, 94, -121, -8, -94, 117, -100, 96, 37, 30, -97, -10, -76, 109, -112, 32, 55, 94, -126, -10, -92, 114, -100, 60, 126, 23, -114, -6, -78, 117}, new byte[]{6, -7, 78, 80, 112, -17, -105, -41}), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-100, 94, 38, -111, 63, 19, 54, 122, -126, 92, 47, ByteCompanionObject.MIN_VALUE, 34, 89, 42, 59, -125, 95, 35, -117, 49, 89, 62, 53, -100, 78}, new byte[]{-15, 43, 74, -27, 86, 119, 89, 84}), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-38, 78, 27, -104, 28, -52, ByteCompanionObject.MIN_VALUE, 49, -103, 90, 92, -127, 22, -112, -113, 39, -42, 81, 65, -63, 6, -48, -123, 35, -46, 69, 70, -118, 93, -51, -123, 56, -62, 91, 84, -101, 28, -52, -62, 38, -61, 69, 84, -101, 22, -39, -107, 123, -48, 86, 88, -118, 0, -112, -118, 39, -46, 82}, new byte[]{-73, 55, 53, -17, 115, -66, -20, 85}), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-26, -8, -17, -49, -39, 47, -64, 28, -90, -2, -25, -50, -110, 61}, new byte[]{-120, -103, -122, -93, -9, 78, -78, 104}), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{55, 64, -125, -29, 31, -94, 29, -58, 50, 81, -98, -69, 59, -127, 24, -62, 60, 86, -39, -113, 43, -92, 27, -61, 60, 114, -106, -65}, new byte[]{89, 37, -9, -51, 94, -58, 121, -81}), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{98, -79, -108, -81, -91, -56, 118, 69, 120, -83, -109, -32, -85, -62, 121, 87, 109, -71, -123, -14, -23, -49, 113, 86, 34, -90, -119, -30, -81, -125, 112, 89, 126, -72, -109, -81, -76, -59, 120, 64, 124, -67, -114, -26}, new byte[]{12, -44, -32, -127, -57, -83, 23, 48}), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{67, 45, 81, -79, 15, -108, 75, 121, 84, 47, 68, -14, 2, -122, 21, 100, 72, 58, 66, -6, 0, -108, 86, 108}, new byte[]{45, 72, 37, -97, 103, -11, 59, 9}), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-87, -110, 49, 17, -43, 89, -75, -66, -96, -106, 40, 90, -106, ByteCompanionObject.MAX_VALUE, -77, -69, -94, -91, 48, 76, -48}, new byte[]{-57, -9, 69, 63, -72, 54, -41, -41}), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{108, -96, -122, -72, -50, -25, -8, 2, 101, -92, -97, -13, -51, -84, -12, 6, 96, -84, -98, -13, -112, -16, -20, 4, 111, -84, -36, -9, -48, -26, -21, 6, 107, -95}, new byte[]{2, -59, -14, -106, -66, -126, -103, 105}), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{98, 122, 78, 95, 68, -59, -41, -110, 110, 118, 78, 95, 87, -49, -64, -104}, new byte[]{12, 31, 58, 113, 51, -86, -91, -10}), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{47, 8, 41, -53, 90, 66, -40, -127, 111, 6, 47, -50, 81, 81, -44, -112, 46, 19, 100, -57, 85, 78, -36, -118, 38, 15, 43, -51, 81}, new byte[]{65, 97, 74, -96, 52, 35, -75, -28}), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{17, 117, -3, -45, -57, -46, 89, -2, 16, 124, -75, -56, -57, -38, 85, -2, 24, 123, -66, -63, -35, -102, 64, -91, 5, 96, -65, -63, ByteCompanionObject.MIN_VALUE, -45, 81, -67, 26, 105}, new byte[]{ByteCompanionObject.MAX_VALUE, 26, -45, -92, -82, -76, 48, -48}), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-56, -119, 78, 41, 71, -43, -23, 13, -37, -113, 69, 40, 71, -40, -23, 13, -53, -113, 91, 48, 12, -49, -4, 77, -33, -116, 76, 106, 82, -50, -25, 89, -44, -123, 7, 35, 67, -42, -8}, new byte[]{-72, -32, 41, 68, 34, -69, -99, 35}));
        f292llllIllIl1 = (String) asList.get(new Random().nextInt(asList.size()));
    }

    public static boolean llllIllIl1() {
        return (!f290lIIIIlllllIlll1 || TextUtils.isEmpty(f289IllIIlIIII1) || TextUtils.isEmpty(f292llllIllIl1) || f289IllIIlIIII1.equals(f292llllIllIl1)) ? false : true;
    }

    public static boolean lIIIIlllllIlll1(StackTraceElement stackTraceElement) {
        if (stackTraceElement == null || f288IlIlllIIlI1.size() <= 0) {
            return false;
        }
        Iterator<String> it = f288IlIlllIIlI1.iterator();
        while (it.hasNext()) {
            if (stackTraceElement.toString().contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static void llllIIIIll1(JSONArray jSONArray, List<String> list) throws JSONException {
        if (jSONArray == null || list == null) {
            return;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            String trim = jSONArray.getString(i).trim();
            if (trim.length() > 0) {
                list.add(trim);
            }
        }
    }

    public static String llllIIIIll1() {
        return f292llllIllIl1;
    }

    public static boolean llllIIIIll1(StackTraceElement stackTraceElement) {
        if (stackTraceElement == null || f287IlIllIlllIllI1.size() <= 0) {
            return false;
        }
        Iterator<String> it = f287IlIllIlllIllI1.iterator();
        while (it.hasNext()) {
            if (stackTraceElement.toString().contains(it.next())) {
                return true;
            }
        }
        return false;
    }
}

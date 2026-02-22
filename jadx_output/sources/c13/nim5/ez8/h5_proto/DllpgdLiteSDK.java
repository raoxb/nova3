package c13.nim5.ez8.h5_proto;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class DllpgdLiteSDK {
    public static final DllpgdLiteSDK INSTANCE = new DllpgdLiteSDK(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-23, 123, -35, -81, 94, -27, -90, -90, -31, 126, -46, -76}, new byte[]{-115, 23, -79, -33, 57, -127, -120, -59}));
    private final HttpGatewayClient httpClient;

    public DllpgdLiteSDK(String str) {
        this.httpClient = new HttpGatewayClient(str);
    }

    public static JSONObject createSampleAtom() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(IllIIlIIII1.llllIIIIll1(new byte[]{-57, 88, -127, 87, -104, 6, -28, 114}, new byte[]{-93, 61, -9, 62, -5, 99, -83, 22}), IllIIlIIII1.llllIIIIll1(new byte[]{42, 45, 110, 35, -118, -70, 117, 50, 60, 58, 106, 48, -125, ByteCompanionObject.MIN_VALUE, 67, 50}, new byte[]{89, 76, 3, 83, -26, -33, 42, 86}));
        jSONObject.put(IllIIlIIII1.llllIIIIll1(new byte[]{89, 24, -27, -39, 68, 10, -109}, new byte[]{47, 125, -105, -86, 45, 101, -3, 7}), 208);
        jSONObject.put(IllIIlIIII1.llllIIIIll1(new byte[]{81, 50, 43, -59, -25, -6, -32, -7, 87, 39, 21, -12, -21, -4}, new byte[]{48, 66, 91, -107, -122, -103, -117, -104}), IllIIlIIII1.llllIIIIll1(new byte[]{70, -80, 56, 68, 126, 61, 61, -115, 85, -77, 48, 68, 122, 53, 44}, new byte[]{37, -33, 85, 106, 27, 69, 92, -32}));
        jSONObject.put(IllIIlIIII1.llllIIIIll1(new byte[]{-16, 54, -35, -42, -109, -19, -50, -81, -2, 40}, new byte[]{-111, 70, -83, ByteCompanionObject.MIN_VALUE, -10, -97, -67, -58}), IllIIlIIII1.llllIIIIll1(new byte[]{-105, 105, -95, -52, -6}, new byte[]{-90, 71, -111, -30, -54, 68, -23, 123}));
        jSONObject.put(IllIIlIIII1.llllIIIIll1(new byte[]{93, 66, 11, -56}, new byte[]{58, 35, 66, -84, 126, -98, 63, 98}), IllIIlIIII1.llllIIIIll1(new byte[]{99, 78, -17, 12, 112, -22, 110, -14, 113, 112, -21, 24}, new byte[]{16, 47, -126, 124, 28, -113, 49, -107}));
        jSONObject.put(IllIIlIIII1.llllIIIIll1(new byte[]{58, -76, -5, -15, 82, -110, 61, -87, 45}, new byte[]{73, -47, -120, -126, 59, -3, 83, -32}), IllIIlIIII1.llllIIIIll1(new byte[]{24, 120, 65, 94, -113, 93, 36, -34, 14, 106, 95, 71, -116, 86, 36, -60, 15}, new byte[]{107, 25, 44, 46, -29, 56, 123, -83}));
        jSONObject.put(IllIIlIIII1.llllIIIIll1(new byte[]{28, 1, 55, 117, ByteCompanionObject.MIN_VALUE, -119, 47, -112, 24, 29}, new byte[]{125, 113, 71, 54, -24, -24, 65, -2}), IllIIlIIII1.llllIIIIll1(new byte[]{0, 41, -85, -26, 23, 94, -51}, new byte[]{100, 76, -51, -121, 98, 50, -71, 117}));
        jSONObject.put(IllIIlIIII1.llllIIIIll1(new byte[]{77, -66, 124, -101, 67, 69, -75, 62, 80, -88, 95, -68, 84, 115, -78, 61, 116, -65, 84, -99, 72, 83, -76}, new byte[]{36, -51, 59, -2, 45, 32, -57, 95}), false);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(IllIIlIIII1.llllIIIIll1(new byte[]{58, -123, -79, 57, -66, -56}, new byte[]{86, -22, -46, 88, -46, -83, -98, 100}), IllIIlIIII1.llllIIIIll1(new byte[]{56, -58, -98, 122, -48}, new byte[]{66, -82, -63, 57, -98, -116, 65, 6}));
        jSONObject2.put(IllIIlIIII1.llllIIIIll1(new byte[]{24, 36, 17, -19, 24, 47, -15, 24}, new byte[]{108, 77, 124, -120, 98, 64, -97, 125}), IllIIlIIII1.llllIIIIll1(new byte[]{19, -27, -104, -37, -116, 88, 42, -40, 60, -15, -103, -37, -54}, new byte[]{82, -106, -15, -70, -93, 11, 66, -71}));
        jSONObject2.put(IllIIlIIII1.llllIIIIll1(new byte[]{-107, 124, 100, -86, 74, 13, -105, -100, ByteCompanionObject.MIN_VALUE, 120}, new byte[]{-27, 20, 11, -60, 47, 64, -8, -8}), IllIIlIIII1.llllIIIIll1(new byte[]{-97, 9, 66, 79, -93, 53, 73, 76, -88, 9}, new byte[]{-53, 108, 49, 59, -25, 80, 63, 37}));
        jSONObject2.put(IllIIlIIII1.llllIIIIll1(new byte[]{-102, -109, 36, -73, -51, -121, -107, -62, -98, -113, 51, -84, -51, ByteCompanionObject.MIN_VALUE}, new byte[]{-5, -3, 64, -59, -94, -18, -15, -108}), IllIIlIIII1.llllIIIIll1(new byte[]{123, 60}, new byte[]{74, 15, 11, 88, -66, -3, -20, 40}));
        jSONObject2.put(IllIIlIIII1.llllIIIIll1(new byte[]{-38, 68, -45, -53, -107, -93, -72, 45, -49, 95, -56, -60, -99, -121}, new byte[]{-86, 44, -68, -91, -16, -9, -47, 64}), System.currentTimeMillis());
        jSONObject.put(IllIIlIIII1.llllIIIIll1(new byte[]{104, 20, -45, 104, 51, 3, 26, -25, 106, 30}, new byte[]{12, 113, -91, 1, 80, 102, 83, -119}), jSONObject2);
        jSONObject.put(IllIIlIIII1.llllIIIIll1(new byte[]{92, -27, 23, -25, -28, -101, 123, -124, 74, -26, 17}, new byte[]{44, -119, 98, ByteCompanionObject.MIN_VALUE, -115, -11, 50, -22}), new JSONArray());
        return jSONObject;
    }

    public static JSONArray createSampleEvents() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{ByteCompanionObject.MAX_VALUE, -116, 29, 54}, new byte[]{17, -19, 112, 83, -82, -103, -97, -115}), lllliiiill1.llllIIIIll1(new byte[]{19, -59, 11, -85, 22, -87, 62, -115, 22, -63, 8, -81}, new byte[]{96, -92, 102, -37, 122, -52, 97, -24}));
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-95, -15, -5, -63}, new byte[]{-59, -108, -120, -94, 103, -110, -12, 26}), lllliiiill1.llllIIIIll1(new byte[]{-1, -61, 11, -14, -67, -99, 115, -85, -38, -57, 8, -10, -15, -100, 54, -67, -49, -48, 15, -14, -91, -111, 60, -96}, new byte[]{-84, -94, 102, -126, -47, -8, 83, -50}));
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{112, 92, 20, 86, 119, -116, -121, -123, 116}, new byte[]{4, 53, 121, 51, 4, -8, -26, -24}), System.currentTimeMillis());
        jSONArray.put(jSONObject);
        return jSONArray;
    }

    public static JSONArray createSampleLogs() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{ByteCompanionObject.MAX_VALUE, 51, 109, 124, 104}, new byte[]{19, 86, 27, 25, 4, 111, 81, -25}), 1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-115, 14, -47, -73, ByteCompanionObject.MAX_VALUE, -96, 50}, new byte[]{-32, 107, -94, -60, 30, -57, 87, 51}), lllliiiill1.llllIIIIll1(new byte[]{-33, -62, -87, 76, -40, 111, 104, 21, -29, -60, -28, 81, -47, 121, 59, 24, -21, -58}, new byte[]{-116, -93, -60, 60, -76, 10, 72, 121}));
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-59, -39, -109}, new byte[]{-79, -72, -12, -4, 101, 71, 3, -69}), lllliiiill1.llllIIIIll1(new byte[]{29, ByteCompanionObject.MAX_VALUE, 46, -109, -65, -60, -4, -120, 45, 118, 17, -89, -109}, new byte[]{89, 19, 66, -29, -40, -96, -80, -31}));
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-97, 55, 81, 103, -25, 95, 109, -90, -101}, new byte[]{-21, 94, 60, 2, -108, 43, 12, -53}), System.currentTimeMillis());
        jSONArray.put(jSONObject);
        return jSONArray;
    }

    public JSONObject getConfig(JSONObject jSONObject) throws Exception {
        return this.httpClient.getConfig(jSONObject);
    }

    public CommonResponse updateEvent(UpdateEventRequest updateEventRequest) throws Exception {
        return this.httpClient.updateEvent(updateEventRequest);
    }

    public CommonResponse updateLog(UpdateLogRequest updateLogRequest) throws Exception {
        return this.httpClient.updateLog(updateLogRequest);
    }

    public DllpgdLiteSDK(String str, int i, boolean z) {
        this.httpClient = new HttpGatewayClient(str, i, z);
    }
}

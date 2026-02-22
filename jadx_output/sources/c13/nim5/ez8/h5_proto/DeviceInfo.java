package c13.nim5.ez8.h5_proto;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class DeviceInfo {
    private String androidVersion;
    private String locale;
    private String phoneModel;
    private Long phoneTimestamp;
    private String timezone;

    public DeviceInfo() {
        this.timezone = "";
        this.locale = "";
        this.phoneTimestamp = 0L;
        this.phoneModel = "";
        this.androidVersion = "";
        this.timezone = "";
        this.locale = "";
        this.phoneTimestamp = 0L;
        this.phoneModel = "";
        this.androidVersion = "";
    }

    public static DeviceInfo fromJSONObject(JSONObject jSONObject) throws JSONException {
        DeviceInfo deviceInfo = new DeviceInfo();
        byte[] bArr = {16, ByteCompanionObject.MIN_VALUE, 41, -62, 25, -33, 7, -90};
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{100, -23, 68, -89, 99, -80, 105, -61}, bArr)) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{13, -44, -4, 117, -86, -42, -67, -119}, new byte[]{121, -67, -111, 16, -48, -71, -45, -20}))) {
            deviceInfo.timezone = jSONObject.getString(lllliiiill1.llllIIIIll1(new byte[]{-29, 91, -29, -42, -30, -13, 41, -71}, new byte[]{-105, 50, -114, -77, -104, -100, 71, -36}));
        }
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{126, 54, -51, 15, -101, 27}, new byte[]{18, 89, -82, 110, -9, 126, 95, -28})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{-70, 54, 95, 75, 96, -51}, new byte[]{-42, 89, 60, 42, 12, -88, -115, -50}))) {
            deviceInfo.locale = jSONObject.getString(lllliiiill1.llllIIIIll1(new byte[]{-103, -95, -12, -24, -76, 26}, new byte[]{-11, -50, -105, -119, -40, ByteCompanionObject.MAX_VALUE, -11, 110}));
        }
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{-55, 99, 67, -45, -14, -39, -67, 98, -36, 120, 88, -36, -6, -3}, new byte[]{-71, 11, 44, -67, -105, -115, -44, 15})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{6, 92, 34, -50, -69, 50, -55, -125, 19, 71, 57, -63, -77, 22}, new byte[]{118, 52, 77, -96, -34, 102, -96, -18}))) {
            deviceInfo.phoneTimestamp = Long.valueOf(jSONObject.getLong(lllliiiill1.llllIIIIll1(new byte[]{102, -67, 45, -38, -125, -27, 35, 55, 115, -90, 54, -43, -117, -63}, new byte[]{22, -43, 66, -76, -26, -79, 74, 90})));
        }
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{-71, 122, 44, 6, 122, -77, -84, -6, -84, 126}, new byte[]{-55, 18, 67, 104, 31, -2, -61, -98})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{45, 14, 25, 54, -36, 3, -94, -29, 56, 10}, new byte[]{93, 102, 118, 88, -71, 78, -51, -121}))) {
            deviceInfo.phoneModel = jSONObject.getString(lllliiiill1.llllIIIIll1(new byte[]{-22, 66, -72, 79, 121, 35, -48, -48, -1, 70}, new byte[]{-102, 42, -41, 33, 28, 110, -65, -76}));
        }
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{-28, 83, -40, 109, 67, -72, 45, 72, -32, 79, -49, 118, 67, -65}, new byte[]{-123, 61, -68, 31, 44, -47, 73, 30})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{-100, -41, 52, -119, 93, -71, -110, ByteCompanionObject.MIN_VALUE, -104, -53, 35, -110, 93, -66}, new byte[]{-3, -71, 80, -5, 50, -48, -10, -42}))) {
            deviceInfo.androidVersion = jSONObject.getString(lllliiiill1.llllIIIIll1(new byte[]{30, -30, -120, 15, 14, 66, 17, 1, 26, -2, -97, 20, 14, 69}, new byte[]{ByteCompanionObject.MAX_VALUE, -116, -20, 125, 97, 43, 117, 87}));
        }
        return deviceInfo;
    }

    public String getAndroidVersion() {
        return this.androidVersion;
    }

    public String getLocale() {
        return this.locale;
    }

    public String getPhoneModel() {
        return this.phoneModel;
    }

    public Long getPhoneTimestamp() {
        return this.phoneTimestamp;
    }

    public String getTimezone() {
        return this.timezone;
    }

    public void setAndroidVersion(String str) {
        this.androidVersion = str;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    public void setPhoneModel(String str) {
        this.phoneModel = str;
    }

    public void setPhoneTimestamp(Long l) {
        this.phoneTimestamp = l;
    }

    public void setTimezone(String str) {
        this.timezone = str;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        byte[] bArr = {-59, -87, -38, ByteCompanionObject.MAX_VALUE, 103, -93, -80, -1};
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-79, -64, -73, 26, 29, -52, -34, -102}, bArr), this.timezone);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-70, 90, 122, -51, -38, -119}, new byte[]{-42, 53, 25, -84, -74, -20, -4, -83}), this.locale);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{40, -97, -95, 67, 19, -45, 63, -47, 61, -124, -70, 76, 27, -9}, new byte[]{88, -9, -50, 45, 118, -121, 86, -68}), this.phoneTimestamp);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{102, -119, 105, -89, 66, -19, ByteCompanionObject.MIN_VALUE, 98, 115, -115}, new byte[]{22, -31, 6, -55, 39, -96, -17, 6}), this.phoneModel);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{76, 113, -96, -34, -70, -45, 17, 64, 72, 109, -73, -59, -70, -44}, new byte[]{45, 31, -60, -84, -43, -70, 117, 22}), this.androidVersion);
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return sb.append(lllliiiill1.llllIIIIll1(new byte[]{1, 84, 57, -53, 0, -33, 43, -68, 35, 94, 52, -42, 10, -41, 7, -88, 42, 95, 42, -97}, new byte[]{69, 49, 79, -94, 99, -70, 98, -46})).append(this.timezone).append(lllliiiill1.llllIIIIll1(new byte[]{65, -71, 69, 93, 109, 42, -69, -93, 80}, new byte[]{109, -103, 41, 50, 14, 75, -41, -58})).append(this.locale).append(lllliiiill1.llllIIIIll1(new byte[]{23, 118, 85, -41, -33, -94, 0, 55, 82, 59, 64, -52, -60, -83, 8, 19, 6}, new byte[]{59, 86, 37, -65, -80, -52, 101, 99})).append(this.phoneTimestamp).append(lllliiiill1.llllIIIIll1(new byte[]{99, 126, 107, -56, 33, 22, -8, -116, 32, 58, 126, -52, 115}, new byte[]{79, 94, 27, -96, 78, 120, -99, -63})).append(this.phoneModel).append(lllliiiill1.llllIIIIll1(new byte[]{76, -23, -64, 18, 73, 84, 28, 3, 4, -97, -60, 14, 94, 79, 28, 4, 93}, new byte[]{96, -55, -95, 124, 45, 38, 115, 106})).append(this.androidVersion).append(lllliiiill1.llllIIIIll1(new byte[]{-104}, new byte[]{-27, -8, 121, 37, -35, -121, -5, -49})).toString();
    }
}

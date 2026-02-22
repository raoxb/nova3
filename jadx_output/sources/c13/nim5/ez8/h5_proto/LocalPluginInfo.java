package c13.nim5.ez8.h5_proto;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class LocalPluginInfo {
    private String className;
    private Long lastUpdateTime;
    private String name;
    private Long pluginStatus;
    private Long version;

    public LocalPluginInfo() {
        this.name = "";
        this.version = 0L;
        this.lastUpdateTime = 0L;
        this.pluginStatus = 0L;
        this.className = "";
        this.name = "";
        this.version = 0L;
        this.lastUpdateTime = 0L;
        this.pluginStatus = 0L;
        this.className = "";
    }

    public static LocalPluginInfo fromJSONObject(JSONObject jSONObject) throws JSONException {
        LocalPluginInfo localPluginInfo = new LocalPluginInfo();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{23, -13, 92, -26}, new byte[]{121, -110, 49, -125, -30, 18, 34, -34})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{94, 32, -37, -104}, new byte[]{48, 65, -74, -3, -41, 12, -42, 11}))) {
            localPluginInfo.name = jSONObject.getString(lllliiiill1.llllIIIIll1(new byte[]{-87, -77, 111, -53}, new byte[]{-57, -46, 2, -82, -117, -70, 126, 120}));
        }
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{62, -31, 94, 55, 40, 57, 53}, new byte[]{72, -124, 44, 68, 65, 86, 91, 121})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{-76, -74, -106, 124, -25, 8, 61}, new byte[]{-62, -45, -28, 15, -114, 103, 83, -32}))) {
            localPluginInfo.version = Long.valueOf(jSONObject.getLong(lllliiiill1.llllIIIIll1(new byte[]{115, -123, -118, 72, 47, -33, -117}, new byte[]{5, -32, -8, 59, 70, -80, -27, 2})));
        }
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{-7, -45, -45, 69, -121, -104, -18, -18, -31, -41, -12, 88, -65, -115}, new byte[]{-107, -78, -96, 49, -46, -24, -118, -113})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{42, -118, -47, 99, -17, 66, -83, -15, 50, -114, -10, 126, -41, 87}, new byte[]{70, -21, -94, 23, -70, 50, -55, -112}))) {
            localPluginInfo.lastUpdateTime = Long.valueOf(jSONObject.getLong(lllliiiill1.llllIIIIll1(new byte[]{-14, 13, 9, 27, -75, 75, 57, -69, -22, 9, 46, 6, -115, 94}, new byte[]{-98, 108, 122, 111, -32, 59, 93, -38})));
        }
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{105, -111, -34, -22, -64, 68, -107, 107, 120, -119, -34, -2}, new byte[]{25, -3, -85, -115, -87, 42, -58, 31})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{58, 99, 118, -39, -82, 36, -82, 93, 43, 123, 118, -51}, new byte[]{74, 15, 3, -66, -57, 74, -3, 41}))) {
            localPluginInfo.pluginStatus = Long.valueOf(jSONObject.getLong(lllliiiill1.llllIIIIll1(new byte[]{81, -99, 66, -7, -31, -85, 93, -43, 64, -123, 66, -19}, new byte[]{33, -15, 55, -98, -120, -59, 14, -95})));
        }
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{30, -7, 9, -58, -33, 69, ByteCompanionObject.MIN_VALUE, -7, 24}, new byte[]{125, -107, 104, -75, -84, 11, -31, -108})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{110, 32, -67, -65, 2, 60, 99, 74, 104}, new byte[]{13, 76, -36, -52, 113, 114, 2, 39}))) {
            localPluginInfo.className = jSONObject.getString(lllliiiill1.llllIIIIll1(new byte[]{-8, 120, 28, 113, 42, -71, -15, -122, -2}, new byte[]{-101, 20, 125, 2, 89, -9, -112, -21}));
        }
        return localPluginInfo;
    }

    public String getClassName() {
        return this.className;
    }

    public Long getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public String getName() {
        return this.name;
    }

    public Long getPluginStatus() {
        return this.pluginStatus;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setClassName(String str) {
        this.className = str;
    }

    public void setLastUpdateTime(Long l) {
        this.lastUpdateTime = l;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPluginStatus(Long l) {
        this.pluginStatus = l;
    }

    public void setVersion(Long l) {
        this.version = l;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        byte[] bArr = {106, -55, 126, -3, ByteCompanionObject.MAX_VALUE, -76, -101, -84};
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{4, -88, 19, -104}, bArr), this.name);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{70, -43, 50, 113, 90, -81, 114}, new byte[]{48, -80, 64, 2, 51, -64, 28, -52}), this.version);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{93, 21, -71, -100, -116, -72, 22, 80, 69, 17, -98, -127, -76, -83}, new byte[]{49, 116, -54, -24, -39, -56, 114, 49}), this.lastUpdateTime);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{105, -2, 63, -100, 105, -32, -70, -110, 120, -26, 63, -120}, new byte[]{25, -110, 74, -5, 0, -114, -23, -26}), this.pluginStatus);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{20, 38, 17, 27, -36, 4, -77, 40, 18}, new byte[]{119, 74, 112, 104, -81, 74, -46, 69}), this.className);
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return sb.append(lllliiiill1.llllIIIIll1(new byte[]{5, -23, -112, 19, -97, 62, -72, 92, 46, -17, -99, 59, -99, 8, -69, 82, 39, -25, -98, 23, -50}, new byte[]{73, -122, -13, 114, -13, 110, -44, 41})).append(this.name).append(lllliiiill1.llllIIIIll1(new byte[]{-32, 73, 24, -105, 49, 6, 50, -11, -94, 84}, new byte[]{-52, 105, 110, -14, 67, 117, 91, -102})).append(this.version).append(lllliiiill1.llllIIIIll1(new byte[]{-39, 27, -123, -56, -50, 109, -70, -32, -111, 90, -99, -52, -23, 112, -126, -11, -56}, new byte[]{-11, 59, -23, -87, -67, 25, -17, -112})).append(this.lastUpdateTime).append(lllliiiill1.llllIIIIll1(new byte[]{-65, -47, -102, -107, -47, 123, 75, -82, -64, -123, -117, -115, -47, 111, 31}, new byte[]{-109, -15, -22, -7, -92, 28, 34, -64})).append(this.pluginStatus).append(lllliiiill1.llllIIIIll1(new byte[]{77, -37, -34, 1, 73, 59, 36, -46, 0, -106, -40, 80}, new byte[]{97, -5, -67, 109, 40, 72, 87, -100})).append(this.className).append(lllliiiill1.llllIIIIll1(new byte[]{59}, new byte[]{70, -61, 76, 108, 34, 58, -16, 52})).toString();
    }
}

package c13.nim5.ez8.h5_proto;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class DllpgdConfig {
    private String fixPackageName;
    private List<String> hookPackageManagerStackTraces;
    private List<String> hookPkgNameStackTraces;
    private List<PluginInfo> plugins;
    private String sessionId;

    public DllpgdConfig() {
        this.plugins = new ArrayList();
        this.sessionId = "";
        this.hookPkgNameStackTraces = new ArrayList();
        this.hookPackageManagerStackTraces = new ArrayList();
        this.fixPackageName = "";
        this.plugins = new ArrayList();
        this.sessionId = "";
        this.hookPkgNameStackTraces = new ArrayList();
        this.hookPackageManagerStackTraces = new ArrayList();
        this.fixPackageName = "";
    }

    public static DllpgdConfig fromJSONObject(JSONObject jSONObject) throws JSONException {
        DllpgdConfig dllpgdConfig = new DllpgdConfig();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{70, -115, 75, -86, -17, 110, -77}, new byte[]{54, -31, 62, -51, -122, 0, -64, 121})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{-113, 31, 101, 53, 16, 44, 80}, new byte[]{-1, 115, 16, 82, 121, 66, 35, 84}))) {
            JSONArray jSONArray = jSONObject.getJSONArray(lllliiiill1.llllIIIIll1(new byte[]{-35, 34, -93, -96, 65, 40, 35}, new byte[]{-83, 78, -42, -57, 40, 70, 80, -32}));
            dllpgdConfig.plugins = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                dllpgdConfig.plugins.add(PluginInfo.fromJSONObject(jSONArray.getJSONObject(i)));
            }
        }
        llllIIIIll1 lllliiiill12 = IllIIlIIII1.f243llllIIIIll1;
        if (jSONObject.has(lllliiiill12.llllIIIIll1(new byte[]{100, 59, -45, -49, ByteCompanionObject.MAX_VALUE, 101, -74, 79, 115}, new byte[]{23, 94, -96, -68, 22, 10, -40, 6})) && !jSONObject.isNull(lllliiiill12.llllIIIIll1(new byte[]{-116, 69, -51, 113, -59, -99, -127, -18, -101}, new byte[]{-1, 32, -66, 2, -84, -14, -17, -89}))) {
            dllpgdConfig.sessionId = jSONObject.getString(lllliiiill12.llllIIIIll1(new byte[]{65, -107, 75, 95, 99, 9, -110, -120, 86}, new byte[]{50, -16, 56, 44, 10, 102, -4, -63}));
        }
        if (jSONObject.has(lllliiiill12.llllIIIIll1(new byte[]{-13, -29, 44, -83, -106, 47, 108, -118, -6, -31, 38, -107, -78, 37, 104, -81, -49, -2, 34, -91, -93, 55}, new byte[]{-101, -116, 67, -58, -58, 68, 11, -60})) && !jSONObject.isNull(lllliiiill12.llllIIIIll1(new byte[]{-78, -56, -97, -32, -19, 18, 31, 26, -69, -54, -107, -40, -55, 24, 27, 63, -114, -43, -111, -24, -40, 10}, new byte[]{-38, -89, -16, -117, -67, 121, 120, 84}))) {
            JSONArray jSONArray2 = jSONObject.getJSONArray(lllliiiill12.llllIIIIll1(new byte[]{74, -38, 115, 123, 43, -67, -6, -3, 67, -40, 121, 67, 15, -73, -2, -40, 118, -57, 125, 115, 30, -91}, new byte[]{34, -75, 28, 16, 123, -42, -99, -77}));
            dllpgdConfig.hookPkgNameStackTraces = new ArrayList();
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                dllpgdConfig.hookPkgNameStackTraces.add(jSONArray2.getString(i2));
            }
        }
        byte[] bArr = {-84, 50, 95, ByteCompanionObject.MAX_VALUE, -123, -115, -115, -127};
        llllIIIIll1 lllliiiill13 = IllIIlIIII1.f243llllIIIIll1;
        if (jSONObject.has(lllliiiill13.llllIIIIll1(new byte[]{-60, 93, 48, 20, -43, -20, -18, -22, -51, 85, 58, 50, -28, -29, -20, -26, -55, 64, 12, 11, -28, -18, -26, -43, -34, 83, 60, 26, -10}, bArr)) && !jSONObject.isNull(lllliiiill13.llllIIIIll1(new byte[]{-90, 85, 3, ByteCompanionObject.MAX_VALUE, 50, -46, -50, 78, -81, 93, 9, 89, 3, -35, -52, 66, -85, 72, 63, 96, 3, -48, -58, 113, -68, 91, 15, 113, 17}, new byte[]{-50, 58, 108, 20, 98, -77, -83, 37}))) {
            JSONArray jSONArray3 = jSONObject.getJSONArray(lllliiiill13.llllIIIIll1(new byte[]{-77, 98, -92, -26, -88, 118, -94, 50, -70, 106, -82, -64, -103, 121, -96, 62, -66, ByteCompanionObject.MAX_VALUE, -104, -7, -103, 116, -86, 13, -87, 108, -88, -24, -117}, new byte[]{-37, 13, -53, -115, -8, 23, -63, 89}));
            dllpgdConfig.hookPackageManagerStackTraces = new ArrayList();
            for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                dllpgdConfig.hookPackageManagerStackTraces.add(jSONArray3.getString(i3));
            }
        }
        byte[] bArr2 = {42, 116, -83, 83, 124, ByteCompanionObject.MAX_VALUE, 121, -57};
        llllIIIIll1 lllliiiill14 = IllIIlIIII1.f243llllIIIIll1;
        if (jSONObject.has(lllliiiill14.llllIIIIll1(new byte[]{76, 29, -43, 3, 29, 28, 18, -90, 77, 17, -29, 50, 17, 26}, bArr2)) && !jSONObject.isNull(lllliiiill14.llllIIIIll1(new byte[]{-77, 87, -123, 47, 52, -121, -108, -55, -78, 91, -77, 30, 56, -127}, new byte[]{-43, 62, -3, ByteCompanionObject.MAX_VALUE, 85, -28, -1, -88}))) {
            dllpgdConfig.fixPackageName = jSONObject.getString(lllliiiill14.llllIIIIll1(new byte[]{13, -119, -76, -49, 34, -97, -51, -83, 12, -123, -126, -2, 46, -103}, new byte[]{107, -32, -52, -97, 67, -4, -90, -52}));
        }
        return dllpgdConfig;
    }

    public String getFixPackageName() {
        return this.fixPackageName;
    }

    public List<String> getHookPackageManagerStackTraces() {
        return this.hookPackageManagerStackTraces;
    }

    public List<String> getHookPkgNameStackTraces() {
        return this.hookPkgNameStackTraces;
    }

    public List<PluginInfo> getPlugins() {
        return this.plugins;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setFixPackageName(String str) {
        this.fixPackageName = str;
    }

    public void setHookPackageManagerStackTraces(List<String> list) {
        this.hookPackageManagerStackTraces = list;
    }

    public void setHookPkgNameStackTraces(List<String> list) {
        this.hookPkgNameStackTraces = list;
    }

    public void setPlugins(List<PluginInfo> list) {
        this.plugins = list;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.plugins != null) {
            JSONArray jSONArray = new JSONArray();
            for (PluginInfo pluginInfo : this.plugins) {
                if (pluginInfo instanceof PluginInfo) {
                    jSONArray.put(pluginInfo.toJSONObject());
                } else {
                    jSONArray.put(pluginInfo);
                }
            }
            jSONObject.put(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-21, 32, 33, -109, 107, 107, 116}, new byte[]{-101, 76, 84, -12, 2, 5, 7, -67}), jSONArray);
        }
        jSONObject.put(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-61, -94, -15, 73, -55, -98, -37, -59, -44}, new byte[]{-80, -57, -126, 58, -96, -15, -75, -116}), this.sessionId);
        if (this.hookPkgNameStackTraces != null) {
            JSONArray jSONArray2 = new JSONArray();
            Iterator<String> it = this.hookPkgNameStackTraces.iterator();
            while (it.hasNext()) {
                jSONArray2.put(it.next());
            }
            jSONObject.put(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-50, -16, -99, -125, -1, -50, -10, -20, -57, -14, -105, -69, -37, -60, -14, -55, -14, -19, -109, -117, -54, -42}, new byte[]{-90, -97, -14, -24, -81, -91, -111, -94}), jSONArray2);
        }
        if (this.hookPackageManagerStackTraces != null) {
            JSONArray jSONArray3 = new JSONArray();
            Iterator<String> it2 = this.hookPackageManagerStackTraces.iterator();
            while (it2.hasNext()) {
                jSONArray3.put(it2.next());
            }
            jSONObject.put(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-9, ByteCompanionObject.MAX_VALUE, 58, 23, -48, -87, -60, -10, -2, 119, 48, 49, -31, -90, -58, -6, -6, 98, 6, 8, -31, -85, -52, -55, -19, 113, 54, 25, -13}, new byte[]{-97, 16, 85, 124, ByteCompanionObject.MIN_VALUE, -56, -89, -99}), jSONArray3);
        }
        jSONObject.put(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-5, -44, 65, -98, 34, -88, -79, -35, -6, -40, 119, -81, 46, -82}, new byte[]{-99, -67, 57, -50, 67, -53, -38, -68}), this.fixPackageName);
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return sb.append(lllliiiill1.llllIIIIll1(new byte[]{-7, -96, 50, 43, 88, 92, 85, 64, -45, -86, 55, 60, 68, 72, 122, 90, -38, -91, 48, 40, 2}, new byte[]{-67, -52, 94, 91, 63, 56, 22, 47})).append(this.plugins).append(lllliiiill1.llllIIIIll1(new byte[]{21, 72, -26, 73, -102, 50, 81, 103, 87, 33, -15, 17}, new byte[]{57, 104, -107, 44, -23, 65, 56, 8})).append(this.sessionId).append(lllliiiill1.llllIIIIll1(new byte[]{4, -58, 100, 57, -113, 46, 3, 81, 79, -88, 109, 59, -123, 22, 39, 91, 75, -115, 88, 36, -127, 38, 54, 73, 21}, new byte[]{40, -26, 12, 86, -32, 69, 83, 58})).append(this.hookPkgNameStackTraces).append(lllliiiill1.llllIIIIll1(new byte[]{73, -25, 111, 79, 3, 114, -24, 81, 6, -84, 102, 71, 9, 84, -39, 94, 4, -96, 98, 82, 63, 109, -39, 83, 14, -109, 117, 65, 15, 124, -53, 13}, new byte[]{101, -57, 7, 32, 108, 25, -72, 48})).append(this.hookPackageManagerStackTraces).append(lllliiiill1.llllIIIIll1(new byte[]{18, 55, -93, 87, 20, -48, 64, 126, 85, 118, -94, 91, 34, -31, 76, 120, 3}, new byte[]{62, 23, -59, 62, 108, ByteCompanionObject.MIN_VALUE, 33, 29})).append(this.fixPackageName).append(lllliiiill1.llllIIIIll1(new byte[]{65}, new byte[]{60, 105, 53, -23, -76, 64, -53, -79})).toString();
    }
}

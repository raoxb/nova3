package c13.nim5.ez8.h5_proto;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Atom {
    private String appChannel;
    private String appPackageName;
    private String appVersion;
    private String deviceId;
    private DeviceInfo deviceInfo;
    private String gaId;
    private Boolean isGeneratedBySubProcess;
    private List<LocalPluginInfo> pluginInfos;
    private String sessionId;
    private Long version;

    public Atom() {
        this.deviceId = "";
        this.deviceInfo = new DeviceInfo();
        this.version = 0L;
        this.appPackageName = "";
        this.appVersion = "";
        this.gaId = "";
        this.sessionId = "";
        this.appChannel = "";
        this.pluginInfos = new ArrayList();
        Boolean bool = Boolean.FALSE;
        this.isGeneratedBySubProcess = bool;
        this.deviceId = "";
        this.deviceInfo = new DeviceInfo();
        this.version = 0L;
        this.appPackageName = "";
        this.appVersion = "";
        this.gaId = "";
        this.sessionId = "";
        this.appChannel = "";
        this.pluginInfos = new ArrayList();
        this.isGeneratedBySubProcess = bool;
    }

    public static Atom fromJSONObject(JSONObject jSONObject) throws JSONException {
        Atom atom = new Atom();
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{-62, 115, -120, 77, -23, -106, -10, -103}, new byte[]{-90, 22, -2, 36, -118, -13, -65, -3})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{80, 34, -34, -10, -54, ByteCompanionObject.MAX_VALUE, 112, -112}, new byte[]{52, 71, -88, -97, -87, 26, 57, -12}))) {
            atom.deviceId = jSONObject.getString(IllIIlIIII1.llllIIIIll1(new byte[]{-10, -40, -13, 103, 67, 40, -70, 75}, new byte[]{-110, -67, -123, 14, 32, 77, -13, 47}));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{-92, 84, -122, -101, -50, 69, 50, -113, -90, 94}, new byte[]{-64, 49, -16, -14, -83, 32, 123, -31})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{-72, 1, -126, -45, 33, -122, -80, -75, -70, 11}, new byte[]{-36, 100, -12, -70, 66, -29, -7, -37}))) {
            atom.deviceInfo = DeviceInfo.fromJSONObject(jSONObject.getJSONObject(IllIIlIIII1.llllIIIIll1(new byte[]{-53, -26, 88, 9, -17, -19, -47, 8, -55, -20}, new byte[]{-81, -125, 46, 96, -116, -120, -104, 102})));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{-76, 8, 22, 86, 55, 64, -31}, new byte[]{-62, 109, 100, 37, 94, 47, -113, -86})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{-39, -71, -118, 31, 74, 57, 98}, new byte[]{-81, -36, -8, 108, 35, 86, 12, -124}))) {
            atom.version = Long.valueOf(jSONObject.getLong(IllIIlIIII1.llllIIIIll1(new byte[]{40, -41, 76, 117, -78, -53, 115}, new byte[]{94, -78, 62, 6, -37, -92, 29, -120})));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{-74, 42, -81, 45, -95, 22, -103, 124, -80, 63, -111, 28, -83, 16}, new byte[]{-41, 90, -33, 125, -64, 117, -14, 29})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{-42, 85, -10, -62, -35, 69, -40, -8, -48, 64, -56, -13, -47, 67}, new byte[]{-73, 37, -122, -110, -68, 38, -77, -103}))) {
            atom.appPackageName = jSONObject.getString(IllIIlIIII1.llllIIIIll1(new byte[]{57, 11, 89, 8, -95, -67, 81, -65, 63, 30, 103, 57, -83, -69}, new byte[]{88, 123, 41, 88, -64, -34, 58, -34}));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{-121, 7, -98, -78, -81, 11, 50, -62, -119, 25}, new byte[]{-26, 119, -18, -28, -54, 121, 65, -85})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{-78, 3, -96, 119, 37, 63, -32, 22, -68, 29}, new byte[]{-45, 115, -48, 33, 64, 77, -109, ByteCompanionObject.MAX_VALUE}))) {
            atom.appVersion = jSONObject.getString(IllIIlIIII1.llllIIIIll1(new byte[]{27, -3, -85, -39, 7, -86, 97, 97, 21, -29}, new byte[]{122, -115, -37, -113, 98, -40, 18, 8}));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{18, 20, 62, -119}, new byte[]{117, 117, 119, -19, -61, 3, -110, -52})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{85, 73, -64, -24}, new byte[]{50, 40, -119, -116, -101, -11, -93, -9}))) {
            atom.gaId = jSONObject.getString(IllIIlIIII1.llllIIIIll1(new byte[]{-101, 31, -11, -92}, new byte[]{-4, 126, -68, -64, -36, -5, 103, -83}));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{42, -107, 105, -52, 22, 33, -8, -102, 61}, new byte[]{89, -16, 26, -65, ByteCompanionObject.MAX_VALUE, 78, -106, -45})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{25, -19, -113, -4, -44, -47, -81, -99, 14}, new byte[]{106, -120, -4, -113, -67, -66, -63, -44}))) {
            atom.sessionId = jSONObject.getString(IllIIlIIII1.llllIIIIll1(new byte[]{13, -61, -67, -2, -94, 74, -8, -32, 26}, new byte[]{126, -90, -50, -115, -53, 37, -106, -87}));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{13, 12, 94, -32, 60, -74, 116, 125, 9, 16}, new byte[]{108, 124, 46, -93, 84, -41, 26, 19})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{33, -109, -80, 16, -58, 107, -109, -78, 37, -113}, new byte[]{64, -29, -64, 83, -82, 10, -3, -36}))) {
            atom.appChannel = jSONObject.getString(IllIIlIIII1.llllIIIIll1(new byte[]{51, -96, -9, 73, 22, -19, 96, -100, 55, -68}, new byte[]{82, -48, -121, 10, 126, -116, 14, -14}));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{-1, 67, 33, 104, 62, 72, 13, 84, -23, 64, 39}, new byte[]{-113, 47, 84, 15, 87, 38, 68, 58})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{-41, 50, 87, -89, 117, -88, 22, 96, -63, 49, 81}, new byte[]{-89, 94, 34, -64, 28, -58, 95, 14}))) {
            JSONArray jSONArray = jSONObject.getJSONArray(IllIIlIIII1.llllIIIIll1(new byte[]{-65, 100, -68, 79, -52, 2, -93, -42, -87, 103, -70}, new byte[]{-49, 8, -55, 40, -91, 108, -22, -72}));
            atom.pluginInfos = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                atom.pluginInfos.add(LocalPluginInfo.fromJSONObject(jSONArray.getJSONObject(i)));
            }
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{-32, -13, -74, 115, -45, 31, 103, -88, -3, -27, -107, 84, -60, 41, 96, -85, -39, -14, -98, 117, -40, 9, 102}, new byte[]{-119, ByteCompanionObject.MIN_VALUE, -15, 22, -67, 122, 21, -55})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{-96, -31, 56, -17, 115, -24, 23, -14, -67, -9, 27, -56, 100, -34, 16, -15, -103, -32, 16, -23, 120, -2, 22}, new byte[]{-55, -110, ByteCompanionObject.MAX_VALUE, -118, 29, -115, 101, -109}))) {
            atom.isGeneratedBySubProcess = Boolean.valueOf(jSONObject.getBoolean(IllIIlIIII1.llllIIIIll1(new byte[]{68, -53, -11, 123, 124, -31, 42, -61, 89, -35, -42, 92, 107, -41, 45, -64, 125, -54, -35, 125, 119, -9, 43}, new byte[]{45, -72, -78, 30, 18, -124, 88, -94})));
        }
        return atom;
    }

    public String getAppChannel() {
        return this.appChannel;
    }

    public String getAppPackageName() {
        return this.appPackageName;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    public String getGaId() {
        return this.gaId;
    }

    public Boolean getIsGeneratedBySubProcess() {
        return this.isGeneratedBySubProcess;
    }

    public List<LocalPluginInfo> getPluginInfos() {
        return this.pluginInfos;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setAppChannel(String str) {
        this.appChannel = str;
    }

    public void setAppPackageName(String str) {
        this.appPackageName = str;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public void setGaId(String str) {
        this.gaId = str;
    }

    public void setIsGeneratedBySubProcess(Boolean bool) {
        this.isGeneratedBySubProcess = bool;
    }

    public void setPluginInfos(List<LocalPluginInfo> list) {
        this.pluginInfos = list;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }

    public void setVersion(Long l) {
        this.version = l;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{122, 119, 56, 20, 88, 18, -5, 88}, new byte[]{30, 18, 78, 125, 59, 119, -78, 60}), this.deviceId);
        if (this.deviceInfo != null) {
            jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{35, -99, -26, 87, -121, 45, -90, -9, 33, -105}, new byte[]{71, -8, -112, 62, -28, 72, -17, -103}), this.deviceInfo.toJSONObject());
        }
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-34, -73, 38, 37, -78, -62, -78}, new byte[]{-88, -46, 84, 86, -37, -83, -36, 124}), this.version);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{53, -26, 28, -60, -51, -92, 95, -15, 51, -13, 34, -11, -63, -94}, new byte[]{84, -106, 108, -108, -84, -57, 52, -112}), this.appPackageName);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{104, -16, -87, -57, 84, 68, -124, 113, 102, -18}, new byte[]{9, ByteCompanionObject.MIN_VALUE, -39, -111, 49, 54, -9, 24}), this.appVersion);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{32, 80, -121, 9}, new byte[]{71, 49, -50, 109, 6, -42, 97, -23}), this.gaId);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{101, 22, 36, 45, -75, -24, -126, 83, 114}, new byte[]{22, 115, 87, 94, -36, -121, -20, 26}), this.sessionId);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-92, -88, 115, 24, -38, 18, 35, 45, -96, -76}, new byte[]{-59, -40, 3, 91, -78, 115, 77, 67}), this.appChannel);
        if (this.pluginInfos != null) {
            JSONArray jSONArray = new JSONArray();
            for (LocalPluginInfo localPluginInfo : this.pluginInfos) {
                if (localPluginInfo instanceof LocalPluginInfo) {
                    jSONArray.put(localPluginInfo.toJSONObject());
                } else {
                    jSONArray.put(localPluginInfo);
                }
            }
            jSONObject.put(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{69, -112, 92, 62, 14, 94, -16, 2, 83, -109, 90}, new byte[]{53, -4, 41, 89, 103, 48, -71, 108}), jSONArray);
        }
        jSONObject.put(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{116, 61, 117, -116, -29, 19, 73, 108, 105, 43, 86, -85, -12, 37, 78, 111, 77, 60, 93, -118, -24, 5, 72}, new byte[]{29, 78, 50, -23, -115, 118, 59, 13}), this.isGeneratedBySubProcess);
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return sb.append(lllliiiill1.llllIIIIll1(new byte[]{-2, -127, -107, -58, 97, -30, 68, 32, -42, -106, -97, -30, 126, -69}, new byte[]{-65, -11, -6, -85, 26, -122, 33, 86})).append(this.deviceId).append(lllliiiill1.llllIIIIll1(new byte[]{9, 71, -105, 19, 11, ByteCompanionObject.MAX_VALUE, 20, 56, 108, 9, -107, 25, 64}, new byte[]{37, 103, -13, 118, 125, 22, 119, 93})).append(this.deviceInfo).append(lllliiiill1.llllIIIIll1(new byte[]{-88, -84, -14, 1, 22, -16, 15, -3, -22, -79}, new byte[]{-124, -116, -124, 100, 100, -125, 102, -110})).append(this.version).append(lllliiiill1.llllIIIIll1(new byte[]{-24, 101, -80, 67, -30, 4, -26, -79, -81, 36, -74, 86, -36, 53, -22, -73, -7}, new byte[]{-60, 69, -47, 51, -110, 84, -121, -46})).append(this.appPackageName).append(lllliiiill1.llllIIIIll1(new byte[]{85, 72, -29, -77, 57, -22, 108, 11, 10, 1, -19, -83, 116}, new byte[]{121, 104, -126, -61, 73, -68, 9, 121})).append(this.appVersion).append(lllliiiill1.llllIIIIll1(new byte[]{-55, -13, -18, -4, -115, 59, -77}, new byte[]{-27, -45, -119, -99, -60, 95, -114, 16})).append(this.gaId).append(lllliiiill1.llllIIIIll1(new byte[]{-62, -13, -17, 106, -57, -7, -70, 116, ByteCompanionObject.MIN_VALUE, -102, -8, 50}, new byte[]{-18, -45, -100, 15, -76, -118, -45, 27})).append(this.sessionId).append(lllliiiill1.llllIIIIll1(new byte[]{-34, 118, -104, -78, 17, -108, -111, 90, -100, 56, -100, -82, 92}, new byte[]{-14, 86, -7, -62, 97, -41, -7, 59})).append(this.appChannel).append(lllliiiill1.llllIIIIll1(new byte[]{-72, -102, 29, -4, 111, -8, -86, 56, -35, -44, 11, -1, 105, -94}, new byte[]{-108, -70, 109, -112, 26, -97, -61, 86})).append(this.pluginInfos).append(lllliiiill1.llllIIIIll1(new byte[]{19, 10, 99, -17, 16, -67, 89, 2, 77, 75, 126, -7, 51, -102, 78, 52, 74, 72, 90, -18, 56, -69, 82, 20, 76, 23}, new byte[]{63, 42, 10, -100, 87, -40, 55, 103})).append(this.isGeneratedBySubProcess).append(lllliiiill1.llllIIIIll1(new byte[]{18}, new byte[]{111, -48, -52, -112, -41, 88, -63, -83})).toString();
    }
}

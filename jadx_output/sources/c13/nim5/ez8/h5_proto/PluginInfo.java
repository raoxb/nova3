package c13.nim5.ez8.h5_proto;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class PluginInfo {
    private Boolean autoStartOnInit;
    private String className;
    private Long delayRunSeconds;
    private Boolean endDelete;
    private Long id;
    private Long lastVersion;
    private String md5;
    private String name;
    private Boolean needRun;
    private Boolean needUpdate;
    private String password;
    private Long pluginStatus;
    private Boolean runInSubProcess;
    private Long startIndex;
    private String url;

    public PluginInfo() {
        this.id = 0L;
        this.name = "";
        this.url = "";
        this.md5 = "";
        this.className = "";
        Boolean bool = Boolean.FALSE;
        this.needRun = bool;
        this.needUpdate = bool;
        this.delayRunSeconds = 0L;
        this.lastVersion = 0L;
        this.password = "";
        this.pluginStatus = 0L;
        this.endDelete = bool;
        this.autoStartOnInit = bool;
        this.startIndex = 0L;
        this.runInSubProcess = bool;
        this.id = 0L;
        this.name = "";
        this.url = "";
        this.md5 = "";
        this.className = "";
        this.needRun = bool;
        this.needUpdate = bool;
        this.delayRunSeconds = 0L;
        this.lastVersion = 0L;
        this.password = "";
        this.pluginStatus = 0L;
        this.endDelete = bool;
        this.autoStartOnInit = bool;
        this.startIndex = 0L;
        this.runInSubProcess = bool;
    }

    public static PluginInfo fromJSONObject(JSONObject jSONObject) throws JSONException {
        PluginInfo pluginInfo = new PluginInfo();
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{40, 2}, new byte[]{65, 102, 7, -104, -18, -31, 123, 74})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{-83, 74}, new byte[]{-60, 46, 27, 4, -30, 86, 58, 100}))) {
            pluginInfo.id = Long.valueOf(jSONObject.getLong(IllIIlIIII1.llllIIIIll1(new byte[]{-90, -115}, new byte[]{-49, -23, -97, 22, 119, 45, 15, 1})));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{-37, -44, 34, -113}, new byte[]{-75, -75, 79, -22, -13, 111, 96, -50})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{36, 76, 113, -1}, new byte[]{74, 45, 28, -102, -127, -63, 126, -25}))) {
            pluginInfo.name = jSONObject.getString(IllIIlIIII1.llllIIIIll1(new byte[]{45, -96, -6, -96}, new byte[]{67, -63, -105, -59, 65, 32, -13, -96}));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{-50, 62, -102}, new byte[]{-69, 76, -10, -86, -21, 80, 85, 23})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{-8, -52, -9}, new byte[]{-115, -66, -101, -118, -47, ByteCompanionObject.MAX_VALUE, 11, ByteCompanionObject.MAX_VALUE}))) {
            pluginInfo.url = jSONObject.getString(IllIIlIIII1.llllIIIIll1(new byte[]{-92, 72, 83}, new byte[]{-47, 58, 63, 52, -30, -37, 9, 109}));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{-74, -49, 4}, new byte[]{-37, -85, 49, 80, 69, 21, -125, -27})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{-51, 16, 0}, new byte[]{-96, 116, 53, -40, 47, 37, -9, 60}))) {
            pluginInfo.md5 = jSONObject.getString(IllIIlIIII1.llllIIIIll1(new byte[]{-42, 25, 65}, new byte[]{-69, 125, 116, -44, 78, 0, 111, -103}));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{59, -44, 116, -82, 86, -114, 23, -36, 61}, new byte[]{88, -72, 21, -35, 37, -64, 118, -79})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{73, -80, 67, -66, 26, -99, 100, 73, 79}, new byte[]{42, -36, 34, -51, 105, -45, 5, 36}))) {
            pluginInfo.className = jSONObject.getString(IllIIlIIII1.llllIIIIll1(new byte[]{67, 34, 111, 26, 63, 21, 31, 105, 69}, new byte[]{32, 78, 14, 105, 76, 91, 126, 4}));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{10, -15, 38, -58, -117, 121, -83}, new byte[]{100, -108, 67, -94, -39, 12, -61, -26})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{80, -34, 7, -87, 14, 73, 38}, new byte[]{62, -69, 98, -51, 92, 60, 72, 53}))) {
            pluginInfo.needRun = Boolean.valueOf(jSONObject.getBoolean(IllIIlIIII1.llllIIIIll1(new byte[]{12, 32, 126, 66, -28, -46, -105}, new byte[]{98, 69, 27, 38, -74, -89, -7, -28})));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{30, 121, -104, -37, -65, -78, -6, 67, 4, 121}, new byte[]{112, 28, -3, -65, -22, -62, -98, 34})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{-93, -22, 77, -35, 68, 126, -24, -64, -71, -22}, new byte[]{-51, -113, 40, -71, 17, 14, -116, -95}))) {
            pluginInfo.needUpdate = Boolean.valueOf(jSONObject.getBoolean(IllIIlIIII1.llllIIIIll1(new byte[]{-33, 17, 82, 33, -91, 41, -84, 10, -59, 17}, new byte[]{-79, 116, 55, 69, -16, 89, -56, 107})));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{64, -24, -4, -63, 69, -33, 15, -76, 119, -24, -13, -49, 82, -23, 9}, new byte[]{36, -115, -112, -96, 60, -115, 122, -38})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{-74, -73, 83, ByteCompanionObject.MAX_VALUE, 28, -46, -64, 39, -127, -73, 92, 113, 11, -28, -58}, new byte[]{-46, -46, 63, 30, 101, ByteCompanionObject.MIN_VALUE, -75, 73}))) {
            pluginInfo.delayRunSeconds = Long.valueOf(jSONObject.getLong(IllIIlIIII1.llllIIIIll1(new byte[]{-17, -36, -126, -79, -109, -68, -89, -100, -40, -36, -115, -65, -124, -118, -95}, new byte[]{-117, -71, -18, -48, -22, -18, -46, -14})));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{-83, -39, -120, -119, 27, 84, -63, 83, -88, -41, -107}, new byte[]{-63, -72, -5, -3, 77, 49, -77, 32})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{-71, 61, 75, 78, 126, -87, 23, 55, -68, 51, 86}, new byte[]{-43, 92, 56, 58, 40, -52, 101, 68}))) {
            pluginInfo.lastVersion = Long.valueOf(jSONObject.getLong(IllIIlIIII1.llllIIIIll1(new byte[]{14, 115, -54, 92, 56, 34, -56, -54, 11, 125, -41}, new byte[]{98, 18, -71, 40, 110, 71, -70, -71})));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{82, 24, 38, 19, -51, 91, -3, 84}, new byte[]{34, 121, 85, 96, -70, 52, -113, 48})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{88, 55, -60, 121, 92, 93, -68, 89}, new byte[]{40, 86, -73, 10, 43, 50, -50, 61}))) {
            pluginInfo.password = jSONObject.getString(IllIIlIIII1.llllIIIIll1(new byte[]{-55, -100, 124, -18, 16, 62, -92, 110}, new byte[]{-71, -3, 15, -99, 103, 81, -42, 10}));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{62, 27, -66, -95, -22, -101, -114, 63, 47, 3, -66, -75}, new byte[]{78, 119, -53, -58, -125, -11, -35, 75})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{116, -56, 74, -47, 35, 110, -20, 45, 101, -48, 74, -59}, new byte[]{4, -92, 63, -74, 74, 0, -65, 89}))) {
            pluginInfo.pluginStatus = Long.valueOf(jSONObject.getLong(IllIIlIIII1.llllIIIIll1(new byte[]{-125, -25, -33, 0, -67, -15, -59, -57, -110, -1, -33, 20}, new byte[]{-13, -117, -86, 103, -44, -97, -106, -77})));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{-95, -119, -122, -81, -98, 31, 65, -94, -95}, new byte[]{-60, -25, -30, -21, -5, 115, 36, -42})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{-23, -125, -121, 89, -93, 56, 22, -67, -23}, new byte[]{-116, -19, -29, 29, -58, 84, 115, -55}))) {
            pluginInfo.endDelete = Boolean.valueOf(jSONObject.getBoolean(IllIIlIIII1.llllIIIIll1(new byte[]{32, -75, 5, 46, 56, -2, -54, 29, 32}, new byte[]{69, -37, 97, 106, 93, -110, -81, 105})));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{105, -40, -126, 43, 6, 114, 62, 98, 124, -30, -104, 13, 59, 111, 43}, new byte[]{8, -83, -10, 68, 85, 6, 95, 16})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{-104, -104, 105, -101, -15, -119, 46, -54, -115, -94, 115, -67, -52, -108, 59}, new byte[]{-7, -19, 29, -12, -94, -3, 79, -72}))) {
            pluginInfo.autoStartOnInit = Boolean.valueOf(jSONObject.getBoolean(IllIIlIIII1.llllIIIIll1(new byte[]{24, -21, 50, -42, 21, -75, 63, -96, 13, -47, 40, -16, 40, -88, 42}, new byte[]{121, -98, 70, -71, 70, -63, 94, -46})));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{9, -33, -12, 112, -18, -56, -110, -27, 31, -45}, new byte[]{122, -85, -107, 2, -102, -127, -4, -127})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{-36, 67, 102, -108, 21, -89, 50, 29, -54, 79}, new byte[]{-81, 55, 7, -26, 97, -18, 92, 121}))) {
            pluginInfo.startIndex = Long.valueOf(jSONObject.getLong(IllIIlIIII1.llllIIIIll1(new byte[]{-77, 52, -115, 96, -125, 79, -70, -62, -91, 56}, new byte[]{-64, 64, -20, 18, -9, 6, -44, -90})));
        }
        if (jSONObject.has(IllIIlIIII1.llllIIIIll1(new byte[]{111, -121, -67, 54, 80, -101, 12, 32, 77, ByteCompanionObject.MIN_VALUE, -68, 28, 91, -69, 10}, new byte[]{29, -14, -45, ByteCompanionObject.MAX_VALUE, 62, -56, 121, 66})) && !jSONObject.isNull(IllIIlIIII1.llllIIIIll1(new byte[]{99, 6, -12, 10, -35, -26, -68, -102, 65, 1, -11, 32, -42, -58, -70}, new byte[]{17, 115, -102, 67, -77, -75, -55, -8}))) {
            pluginInfo.runInSubProcess = Boolean.valueOf(jSONObject.getBoolean(IllIIlIIII1.llllIIIIll1(new byte[]{44, -97, 14, 59, -9, -99, -56, 93, 14, -104, 15, 17, -4, -67, -50}, new byte[]{94, -22, 96, 114, -103, -50, -67, 63})));
        }
        return pluginInfo;
    }

    public Boolean getAutoStartOnInit() {
        return this.autoStartOnInit;
    }

    public String getClassName() {
        return this.className;
    }

    public Long getDelayRunSeconds() {
        return this.delayRunSeconds;
    }

    public Boolean getEndDelete() {
        return this.endDelete;
    }

    public Long getId() {
        return this.id;
    }

    public Long getLastVersion() {
        return this.lastVersion;
    }

    public String getMd5() {
        return this.md5;
    }

    public String getName() {
        return this.name;
    }

    public Boolean getNeedRun() {
        return this.needRun;
    }

    public Boolean getNeedUpdate() {
        return this.needUpdate;
    }

    public String getPassword() {
        return this.password;
    }

    public Long getPluginStatus() {
        return this.pluginStatus;
    }

    public Boolean getRunInSubProcess() {
        return this.runInSubProcess;
    }

    public Long getStartIndex() {
        return this.startIndex;
    }

    public String getUrl() {
        return this.url;
    }

    public void setAutoStartOnInit(Boolean bool) {
        this.autoStartOnInit = bool;
    }

    public void setClassName(String str) {
        this.className = str;
    }

    public void setDelayRunSeconds(Long l) {
        this.delayRunSeconds = l;
    }

    public void setEndDelete(Boolean bool) {
        this.endDelete = bool;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setLastVersion(Long l) {
        this.lastVersion = l;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNeedRun(Boolean bool) {
        this.needRun = bool;
    }

    public void setNeedUpdate(Boolean bool) {
        this.needUpdate = bool;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setPluginStatus(Long l) {
        this.pluginStatus = l;
    }

    public void setRunInSubProcess(Boolean bool) {
        this.runInSubProcess = bool;
    }

    public void setStartIndex(Long l) {
        this.startIndex = l;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-24, -25}, new byte[]{-127, -125, 64, 59, 48, 124, 124, -43}), this.id);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{23, -21, -69, -61}, new byte[]{121, -118, -42, -90, 101, 63, 46, -10}), this.name);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-27, 45, 71}, new byte[]{-112, 95, 43, 125, -32, 39, 36, -48}), this.url);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-56, -3, -108}, new byte[]{-91, -103, -95, 29, -58, -62, 115, -54}), this.md5);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-3, -118, 51, -91, -82, 78, -98, 58, -5}, new byte[]{-98, -26, 82, -42, -35, 0, -1, 87}), this.className);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{101, 62, -33, -52, 58, 52, 86}, new byte[]{11, 91, -70, -88, 104, 65, 56, 60}), this.needRun);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{64, -1, -93, -80, -23, 19, -48, -19, 90, -1}, new byte[]{46, -102, -58, -44, -68, 99, -76, -116}), this.needUpdate);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-90, 40, 67, -110, -84, -108, 45, 73, -111, 40, 76, -100, -69, -94, 43}, new byte[]{-62, 77, 47, -13, -43, -58, 88, 39}), this.delayRunSeconds);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{122, -121, -125, 55, -85, -88, -5, 98, ByteCompanionObject.MAX_VALUE, -119, -98}, new byte[]{22, -26, -16, 67, -3, -51, -119, 17}), this.lastVersion);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-68, 61, -96, 72, 102, -45, -44, 81}, new byte[]{-52, 92, -45, 59, 17, -68, -90, 53}), this.password);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-94, -41, -86, 49, 113, 16, 52, -67, -77, -49, -86, 37}, new byte[]{-46, -69, -33, 86, 24, 126, 103, -55}), this.pluginStatus);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{14, -26, 37, 20, -24, -35, 20, 110, 14}, new byte[]{107, -120, 65, 80, -115, -79, 113, 26}), this.endDelete);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-88, 34, -7, 45, -63, -102, -34, -42, -67, 24, -29, 11, -4, -121, -53}, new byte[]{-55, 87, -115, 66, -110, -18, -65, -92}), this.autoStartOnInit);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{43, 121, 51, 40, -16, -44, -51, 26, 61, 117}, new byte[]{88, 13, 82, 90, -124, -99, -93, 126}), this.startIndex);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-82, 10, -36, -102, -19, 36, -44, 12, -116, 13, -35, -80, -26, 4, -46}, new byte[]{-36, ByteCompanionObject.MAX_VALUE, -78, -45, -125, 119, -95, 110}), this.runInSubProcess);
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return sb.append(lllliiiill1.llllIIIIll1(new byte[]{86, 38, -94, 57, -78, -29, 97, 9, 96, 37, -84, 55, -65, -80}, new byte[]{6, 74, -41, 94, -37, -115, 40, 103})).append(this.id).append(lllliiiill1.llllIIIIll1(new byte[]{-66, -78, -48, -122, 64, 104, -106}, new byte[]{-110, -110, -66, -25, 45, 13, -85, -21})).append(this.name).append(lllliiiill1.llllIIIIll1(new byte[]{-104, 31, 126, -26, 110, 30}, new byte[]{-76, 63, 11, -108, 2, 35, 97, -79})).append(this.url).append(lllliiiill1.llllIIIIll1(new byte[]{56, 62, -86, 80, -31, 33}, new byte[]{20, 30, -57, 52, -44, 28, -76, -97})).append(this.md5).append(lllliiiill1.llllIIIIll1(new byte[]{54, 114, 60, 33, 94, 70, 70, -100, 123, 63, 58, 112}, new byte[]{26, 82, 95, 77, 63, 53, 53, -46})).append(this.className).append(lllliiiill1.llllIIIIll1(new byte[]{112, 118, -77, 114, -53, -7, -127, -4, 50, 107}, new byte[]{92, 86, -35, 23, -82, -99, -45, -119})).append(this.needRun).append(lllliiiill1.llllIIIIll1(new byte[]{21, -105, -53, 22, 94, 62, -3, -99, 93, -42, -47, 22, 6}, new byte[]{57, -73, -91, 115, 59, 90, -88, -19})).append(this.needUpdate).append(lllliiiill1.llllIIIIll1(new byte[]{44, 8, 2, -113, 41, 81, 64, -17, 117, 70, 53, -113, 38, 95, 87, -39, 115, 21}, new byte[]{0, 40, 102, -22, 69, 48, 57, -67})).append(this.delayRunSeconds).append(lllliiiill1.llllIIIIll1(new byte[]{59, -117, -62, 13, -117, -98, 90, 40, 101, -40, -57, 3, -106, -41}, new byte[]{23, -85, -82, 108, -8, -22, 12, 77})).append(this.lastVersion).append(lllliiiill1.llllIIIIll1(new byte[]{-105, -24, -41, -5, 85, -50, -66, 68, -55, -84, -102}, new byte[]{-69, -56, -89, -102, 38, -67, -55, 43})).append(this.password).append(lllliiiill1.llllIIIIll1(new byte[]{-50, -104, 58, -98, -113, -38, 42, 25, -79, -52, 43, -122, -113, -50, 126}, new byte[]{-30, -72, 74, -14, -6, -67, 67, 119})).append(this.pluginStatus).append(lllliiiill1.llllIIIIll1(new byte[]{-123, -74, 85, 47, 118, 122, -8, 33, -52, -30, 85, 124}, new byte[]{-87, -106, 48, 65, 18, 62, -99, 77})).append(this.endDelete).append(lllliiiill1.llllIIIIll1(new byte[]{-22, -68, -66, -3, -17, -19, -115, 49, -89, -18, -85, -57, -11, -53, -80, 44, -78, -95}, new byte[]{-58, -100, -33, -120, -101, -126, -34, 69})).append(this.autoStartOnInit).append(lllliiiill1.llllIIIIll1(new byte[]{-122, 120, -10, -33, 55, -85, -31, ByteCompanionObject.MAX_VALUE, -60, 60, -32, -45, 107}, new byte[]{-86, 88, -123, -85, 86, -39, -107, 54})).append(this.startIndex).append(lllliiiill1.llllIIIIll1(new byte[]{96, 96, -87, -102, 65, -111, -121, 76, 57, 34, -117, -99, 64, -69, -116, 108, 63, 125}, new byte[]{76, 64, -37, -17, 47, -40, -23, 31})).append(this.runInSubProcess).append(lllliiiill1.llllIIIIll1(new byte[]{-7}, new byte[]{-124, 117, 118, 116, -106, 78, 36, 33})).toString();
    }
}

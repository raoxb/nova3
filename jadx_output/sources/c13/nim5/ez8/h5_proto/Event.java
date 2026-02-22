package c13.nim5.ez8.h5_proto;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Event {
    private String desc;
    private String name;
    private Long timestamp;

    public Event() {
        this.timestamp = 0L;
        this.name = "";
        this.desc = "";
        this.timestamp = 0L;
        this.name = "";
        this.desc = "";
    }

    public static Event fromJSONObject(JSONObject jSONObject) throws JSONException {
        Event event = new Event();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{-51, -12, 65, 68, -87, -2, 4, 39, -55}, new byte[]{-71, -99, 44, 33, -38, -118, 101, 74})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{52, 68, 53, 48, -96, 79, -5, 6, 48}, new byte[]{64, 45, 88, 85, -45, 59, -102, 107}))) {
            event.timestamp = Long.valueOf(jSONObject.getLong(lllliiiill1.llllIIIIll1(new byte[]{33, -120, 100, 95, 36, -118, 45, -30, 37}, new byte[]{85, -31, 9, 58, 87, -2, 76, -113})));
        }
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{-72, 39, 17, 12}, new byte[]{-42, 70, 124, 105, 38, 86, -52, 60})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{1, -66, 96, -50}, new byte[]{111, -33, 13, -85, -72, 30, -76, 35}))) {
            event.name = jSONObject.getString(lllliiiill1.llllIIIIll1(new byte[]{45, -42, -54, -88}, new byte[]{67, -73, -89, -51, 42, -73, -88, 68}));
        }
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{35, 31, 66, -13}, new byte[]{71, 122, 49, -112, 70, -5, -112, 7})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{-66, 117, 13, -118}, new byte[]{-38, 16, 126, -23, -93, -101, 64, 24}))) {
            event.desc = jSONObject.getString(lllliiiill1.llllIIIIll1(new byte[]{47, 27, -80, -96}, new byte[]{75, 126, -61, -61, 63, -84, 112, 113}));
        }
        return event;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getName() {
        return this.name;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{125, 27, 36, -16, 48, 43, 32, 70, 121}, new byte[]{9, 114, 73, -107, 67, 95, 65, 43}), this.timestamp);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-13, -19, 72, 19}, new byte[]{-99, -116, 37, 118, -56, 40, -116, -25}), this.name);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-120, -34, -31, -58}, new byte[]{-20, -69, -110, -91, -102, -24, -9, 40}), this.desc);
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return sb.append(lllliiiill1.llllIIIIll1(new byte[]{34, -34, 66, ByteCompanionObject.MAX_VALUE, 86, -106, 77, -122, 10, -51, 84, 101, 67, ByteCompanionObject.MIN_VALUE, 73, -46}, new byte[]{103, -88, 39, 17, 34, -19, 57, -17})).append(this.timestamp).append(lllliiiill1.llllIIIIll1(new byte[]{117, -54, -3, 98, 5, -8, -51}, new byte[]{89, -22, -109, 3, 104, -99, -16, -19})).append(this.name).append(lllliiiill1.llllIIIIll1(new byte[]{119, 12, -104, 24, -120, 106, -35}, new byte[]{91, 44, -4, 125, -5, 9, -32, -18})).append(this.desc).append(lllliiiill1.llllIIIIll1(new byte[]{103}, new byte[]{26, -34, -81, 7, 38, 47, 34, -14})).toString();
    }
}

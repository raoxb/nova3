package c13.nim5.ez8.h5_proto;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UpdateLogRequest {
    private Atom atom;
    private List<Log> log;

    public UpdateLogRequest() {
        this.atom = new Atom();
        this.log = new ArrayList();
        this.atom = new Atom();
        this.log = new ArrayList();
    }

    public static UpdateLogRequest fromJSONObject(JSONObject jSONObject) throws JSONException {
        UpdateLogRequest updateLogRequest = new UpdateLogRequest();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{-122, -1, -64, -122}, new byte[]{-25, -117, -81, -21, 34, 14, -36, 10})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{27, -56, -26, -4}, new byte[]{122, -68, -119, -111, 27, 66, -27, -6}))) {
            updateLogRequest.atom = Atom.fromJSONObject(jSONObject.getJSONObject(lllliiiill1.llllIIIIll1(new byte[]{23, -1, 32, 33}, new byte[]{118, -117, 79, 76, 101, 103, 4, -105})));
        }
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{-14, -45, -92}, new byte[]{-98, -68, -61, -23, 63, -48, -70, -29})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{-119, -81, 102}, new byte[]{-27, -64, 1, 54, 3, 8, -79, -107}))) {
            JSONArray jSONArray = jSONObject.getJSONArray(lllliiiill1.llllIIIIll1(new byte[]{48, -6, -120}, new byte[]{92, -107, -17, -93, -96, 72, 35, 109}));
            updateLogRequest.log = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                updateLogRequest.log.add(Log.fromJSONObject(jSONArray.getJSONObject(i)));
            }
        }
        return updateLogRequest;
    }

    public Atom getAtom() {
        return this.atom;
    }

    public List<Log> getLog() {
        return this.log;
    }

    public void setAtom(Atom atom) {
        this.atom = atom;
    }

    public void setLog(List<Log> list) {
        this.log = list;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.atom != null) {
            jSONObject.put(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{52, 54, 56, 77}, new byte[]{85, 66, 87, 32, 106, 22, 67, 33}), this.atom.toJSONObject());
        }
        if (this.log != null) {
            JSONArray jSONArray = new JSONArray();
            for (Log log : this.log) {
                if (log instanceof Log) {
                    jSONArray.put(log.toJSONObject());
                } else {
                    jSONArray.put(log);
                }
            }
            jSONObject.put(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{67, -81, 41}, new byte[]{47, -64, 78, -67, 121, 98, 52, -88}), jSONArray);
        }
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return sb.append(lllliiiill1.llllIIIIll1(new byte[]{40, 85, 70, 55, 39, -123, -56, 29, 26, 119, 71, 39, 38, -123, -9, 6, 6, 68, 86, 57, 62, -35}, new byte[]{125, 37, 34, 86, 83, -32, -124, 114})).append(this.atom).append(lllliiiill1.llllIIIIll1(new byte[]{5, 120, -63, -103, -11, 21}, new byte[]{41, 88, -83, -10, -110, 40, -100, 121})).append(this.log).append(lllliiiill1.llllIIIIll1(new byte[]{105}, new byte[]{20, -108, -7, -45, -71, -105, 65, 69})).toString();
    }
}

package c13.nim5.ez8.h5_proto;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CommonRequest {
    private Atom atom;

    public CommonRequest() {
        this.atom = new Atom();
        this.atom = new Atom();
    }

    public static CommonRequest fromJSONObject(JSONObject jSONObject) throws JSONException {
        CommonRequest commonRequest = new CommonRequest();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{-96, 83, -70, 100}, new byte[]{-63, 39, -43, 9, -61, 14, -83, 15})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{-36, 54, -23, 75}, new byte[]{-67, 66, -122, 38, 107, -15, 103, 78}))) {
            commonRequest.atom = Atom.fromJSONObject(jSONObject.getJSONObject(lllliiiill1.llllIIIIll1(new byte[]{66, -96, -2, -94}, new byte[]{35, -44, -111, -49, 4, 69, -37, 93})));
        }
        return commonRequest;
    }

    public Atom getAtom() {
        return this.atom;
    }

    public void setAtom(Atom atom) {
        this.atom = atom;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.atom != null) {
            jSONObject.put(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-125, 26, 63, -51}, new byte[]{-30, 110, 80, -96, 20, 4, 30, 42}), this.atom.toJSONObject());
        }
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return sb.append(lllliiiill1.llllIIIIll1(new byte[]{79, 43, -22, 15, -40, -88, -62, 104, 125, 49, -30, 17, -61, -67, -15, 121, 99, 41, -70}, new byte[]{12, 68, -121, 98, -73, -58, -112, 13})).append(this.atom).append(lllliiiill1.llllIIIIll1(new byte[]{35}, new byte[]{94, 104, 2, 125, -76, -65, -125, -39})).toString();
    }
}

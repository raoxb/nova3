package c13.nim5.ez8.h5_proto.signaling;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import c13.nim5.ez8.h5_proto.Atom;
import kotlin.jvm.internal.ByteCompanionObject;
import lIllIIIlIl1.IlIllll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CheckSignalingPluginStartRequest implements IlIllll1 {
    private final Atom atom;

    public CheckSignalingPluginStartRequest(Atom atom) {
        this.atom = atom;
    }

    public static CheckSignalingPluginStartRequest fromJSONObject(JSONObject jSONObject) throws JSONException {
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return new CheckSignalingPluginStartRequest(Atom.fromJSONObject(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{18, 91, 33, -87}, new byte[]{115, 47, 78, -60, -92, 4, 123, 3})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{92, 115, -63, 44}, new byte[]{61, 7, -82, 65, 61, ByteCompanionObject.MAX_VALUE, -86, -13})) : new JSONObject()));
    }

    public Atom getAtom() {
        return this.atom;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-124, -20, -56, -7}, new byte[]{-27, -104, -89, -108, 72, -120, 30, 4}), this.atom.toJSONObject());
        return jSONObject;
    }
}

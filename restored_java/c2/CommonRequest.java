package c2;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: c13.nim5.ez8.h5_proto.CommonRequest
 *
 * Generic request wrapper containing an Atom (device fingerprint payload).
 * Used as the base request format for C&C API calls.
 */
public class CommonRequest {
    private Atom atom;

    public CommonRequest() {
        this.atom = new Atom();
    }

    public static CommonRequest fromJSONObject(JSONObject json) throws JSONException {
        CommonRequest request = new CommonRequest();
        if (json.has("atom") && !json.isNull("atom")) {
            request.atom = Atom.fromJSONObject(json.getJSONObject("atom"));
        }
        return request;
    }

    public Atom getAtom() { return this.atom; }
    public void setAtom(Atom atom) { this.atom = atom; }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        if (this.atom != null) {
            json.put("atom", this.atom.toJSONObject());
        }
        return json;
    }

    @Override
    public String toString() {
        return "CommonRequest{atom=" + this.atom + "}";
    }
}

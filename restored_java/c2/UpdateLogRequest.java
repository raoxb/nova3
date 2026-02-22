package c2;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: c13.nim5.ez8.h5_proto.UpdateLogRequest
 *
 * Request payload for the /api/v1/dllpgd/updateLog C&C endpoint.
 * Contains the device fingerprint (Atom) and a list of log entries to upload.
 */
public class UpdateLogRequest {
    private Atom atom;
    private List<Log> log;

    public UpdateLogRequest() {
        this.atom = new Atom();
        this.log = new ArrayList<>();
    }

    public static UpdateLogRequest fromJSONObject(JSONObject json) throws JSONException {
        UpdateLogRequest request = new UpdateLogRequest();
        if (json.has("atom") && !json.isNull("atom")) {
            request.atom = Atom.fromJSONObject(json.getJSONObject("atom"));
        }
        if (json.has("log") && !json.isNull("log")) {
            JSONArray jsonArray = json.getJSONArray("log");
            request.log = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                request.log.add(Log.fromJSONObject(jsonArray.getJSONObject(i)));
            }
        }
        return request;
    }

    public Atom getAtom() { return this.atom; }
    public List<Log> getLog() { return this.log; }
    public void setAtom(Atom atom) { this.atom = atom; }
    public void setLog(List<Log> list) { this.log = list; }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        if (this.atom != null) {
            json.put("atom", this.atom.toJSONObject());
        }
        if (this.log != null) {
            JSONArray jsonArray = new JSONArray();
            for (Log logEntry : this.log) {
                jsonArray.put(logEntry.toJSONObject());
            }
            json.put("log", jsonArray);
        }
        return json;
    }

    @Override
    public String toString() {
        return "UpdateLogRequest{atom=" + this.atom
                + ", log=" + this.log + "}";
    }
}

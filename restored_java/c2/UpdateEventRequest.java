package c2;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: c13.nim5.ez8.h5_proto.UpdateEventRequest
 *
 * Request payload for the /api/v1/dllpgd/updateEvent C&C endpoint.
 * Contains the device fingerprint (Atom) and a list of events to report.
 */
public class UpdateEventRequest {
    private Atom atom;
    private List<Event> events;

    public UpdateEventRequest() {
        this.atom = new Atom();
        this.events = new ArrayList<>();
    }

    public static UpdateEventRequest fromJSONObject(JSONObject json) throws JSONException {
        UpdateEventRequest request = new UpdateEventRequest();
        if (json.has("atom") && !json.isNull("atom")) {
            request.atom = Atom.fromJSONObject(json.getJSONObject("atom"));
        }
        if (json.has("events") && !json.isNull("events")) {
            JSONArray jsonArray = json.getJSONArray("events");
            request.events = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                request.events.add(Event.fromJSONObject(jsonArray.getJSONObject(i)));
            }
        }
        return request;
    }

    public Atom getAtom() { return this.atom; }
    public List<Event> getEvents() { return this.events; }
    public void setAtom(Atom atom) { this.atom = atom; }
    public void setEvents(List<Event> list) { this.events = list; }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        if (this.atom != null) {
            json.put("atom", this.atom.toJSONObject());
        }
        if (this.events != null) {
            JSONArray jsonArray = new JSONArray();
            for (Event event : this.events) {
                jsonArray.put(event.toJSONObject());
            }
            json.put("events", jsonArray);
        }
        return json;
    }

    @Override
    public String toString() {
        return "UpdateEventRequest{atom=" + this.atom
                + ", events=" + this.events + "}";
    }
}

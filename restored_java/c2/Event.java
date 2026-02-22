package c2;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: c13.nim5.ez8.h5_proto.Event
 *
 * Represents a single telemetry event sent to the C&C server.
 * Events track specific actions or milestones during malware operation.
 *
 * Fields:
 * - timestamp: when the event occurred (epoch millis)
 * - name: event identifier (e.g. "sample_event")
 * - desc: human-readable event description
 */
public class Event {
    private Long timestamp;
    private String name;
    private String desc;

    public Event() {
        this.timestamp = 0L;
        this.name = "";
        this.desc = "";
    }

    public static Event fromJSONObject(JSONObject json) throws JSONException {
        Event event = new Event();
        if (json.has("timestamp") && !json.isNull("timestamp")) {
            event.timestamp = Long.valueOf(json.getLong("timestamp"));
        }
        if (json.has("name") && !json.isNull("name")) {
            event.name = json.getString("name");
        }
        if (json.has("desc") && !json.isNull("desc")) {
            event.desc = json.getString("desc");
        }
        return event;
    }

    public String getDesc() { return this.desc; }
    public String getName() { return this.name; }
    public Long getTimestamp() { return this.timestamp; }
    public void setDesc(String str) { this.desc = str; }
    public void setName(String str) { this.name = str; }
    public void setTimestamp(Long l) { this.timestamp = l; }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("timestamp", this.timestamp);
        json.put("name", this.name);
        json.put("desc", this.desc);
        return json;
    }

    @Override
    public String toString() {
        return "Event{timestamp=" + this.timestamp
                + ", name=" + this.name
                + ", desc=" + this.desc + "}";
    }
}

package signaling;

import org.json.JSONObject;

/**
 * Represents a "done" message in the signaling protocol.
 * Indicates that the remote session has completed its task
 * or that the signaling exchange has finished. Carries no payload.
 */
public class Done implements JsonSerializable {

    /**
     * Constructs a new Done message.
     */
    public Done() {
    }

    /**
     * Deserializes a Done from a JSON object.
     * Since Done carries no payload, the JSON content is ignored.
     *
     * @param json the JSON object (unused)
     * @return a new Done instance
     */
    public static Done fromJSONObject(JSONObject json) {
        return new Done();
    }

    @Override
    public JSONObject toJSONObject() {
        return new JSONObject();
    }
}

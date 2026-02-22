package signaling;

import org.json.JSONObject;

/**
 * Represents a ping message in the signaling protocol.
 * Used as a keep-alive mechanism to verify that the signaling
 * channel is still active. The remote peer should respond with a {@link Pong}.
 */
public class Ping implements JsonSerializable {

    private final String message;

    /**
     * Constructs a new Ping with the given message payload.
     *
     * @param message the ping message content
     */
    public Ping(String message) {
        this.message = message;
    }

    /**
     * Returns the ping message content.
     *
     * @return the message string
     */
    public String getMessage() {
        return message;
    }

    /**
     * Deserializes a Ping from a JSON object.
     *
     * @param json the JSON object containing ping data
     * @return a new Ping instance
     */
    public static Ping fromJSONObject(JSONObject json) {
        String message = json.optString("message", "");
        return new Ping(message);
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("message", message);
        return json;
    }
}

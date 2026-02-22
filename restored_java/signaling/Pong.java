package signaling;

import org.json.JSONObject;

/**
 * Represents a pong message in the signaling protocol.
 * Sent in response to a {@link Ping} to confirm that the signaling
 * channel is still alive and responsive.
 */
public class Pong implements JsonSerializable {

    private final String message;

    /**
     * Constructs a new Pong with the given message payload.
     *
     * @param message the pong message content (typically echoes the ping)
     */
    public Pong(String message) {
        this.message = message;
    }

    /**
     * Returns the pong message content.
     *
     * @return the message string
     */
    public String getMessage() {
        return message;
    }

    /**
     * Deserializes a Pong from a JSON object.
     *
     * @param json the JSON object containing pong data
     * @return a new Pong instance
     */
    public static Pong fromJSONObject(JSONObject json) {
        String message = json.optString("message", "");
        return new Pong(message);
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("message", message);
        return json;
    }
}

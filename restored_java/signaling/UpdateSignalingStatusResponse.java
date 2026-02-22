package signaling;

import org.json.JSONObject;

/**
 * Response message acknowledging a signaling status update.
 * Contains a result code and a descriptive message indicating
 * whether the status update was accepted by the server.
 */
public class UpdateSignalingStatusResponse implements JsonSerializable {

    private final int code;
    private final String message;

    /**
     * Constructs a new UpdateSignalingStatusResponse.
     *
     * @param code    the result code (0 typically means success)
     * @param message a human-readable result description
     */
    public UpdateSignalingStatusResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Returns the result code.
     *
     * @return the numeric result code
     */
    public int getCode() {
        return code;
    }

    /**
     * Returns the result message.
     *
     * @return a descriptive message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Deserializes an UpdateSignalingStatusResponse from a JSON object.
     *
     * @param json the JSON object containing the response data
     * @return a new UpdateSignalingStatusResponse instance
     */
    public static UpdateSignalingStatusResponse fromJSONObject(JSONObject json) {
        int code = json.optInt("code", 0);
        String message = json.optString("message", "");
        return new UpdateSignalingStatusResponse(code, message);
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("message", message);
        return json;
    }
}

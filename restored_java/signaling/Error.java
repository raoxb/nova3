package signaling;

import org.json.JSONObject;

/**
 * Represents an error message in the signaling protocol.
 * Carries a numeric error code and a human-readable message
 * describing what went wrong during signaling or session setup.
 */
public class Error implements JsonSerializable {

    private final int code;
    private final String message;

    /**
     * Constructs a new Error with the given code and message.
     *
     * @param code    the numeric error code
     * @param message a human-readable description of the error
     */
    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Returns the numeric error code.
     *
     * @return the error code
     */
    public int getCode() {
        return code;
    }

    /**
     * Returns the human-readable error message.
     *
     * @return the error description
     */
    public String getMessage() {
        return message;
    }

    /**
     * Deserializes an Error from a JSON object.
     *
     * @param json the JSON object containing error data
     * @return a new Error instance
     */
    public static Error fromJSONObject(JSONObject json) {
        int code = json.optInt("code", 0);
        String message = json.optString("message", "");
        return new Error(code, message);
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("message", message);
        return json;
    }
}

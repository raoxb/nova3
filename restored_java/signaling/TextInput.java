package signaling;

import org.json.JSONObject;

/**
 * Represents a text input event in the signaling protocol.
 * Carries a string of text that the user has typed, to be
 * delivered to the remote session's active input field.
 */
public class TextInput implements JsonSerializable {

    private final String text;

    /**
     * Constructs a new TextInput with the given text.
     *
     * @param text the text input string
     */
    public TextInput(String text) {
        this.text = text;
    }

    /**
     * Returns the text content.
     *
     * @return the input text string
     */
    public String getText() {
        return text;
    }

    /**
     * Deserializes a TextInput from a JSON object.
     *
     * @param json the JSON object containing text input data
     * @return a new TextInput instance
     */
    public static TextInput fromJSONObject(JSONObject json) {
        String text = json.optString("text", "");
        return new TextInput(text);
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("text", text);
        return json;
    }
}

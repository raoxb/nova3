package signaling;

import org.json.JSONObject;

/**
 * Request message to check whether the signaling plugin is ready to start.
 * Sent before initiating a signaling session to verify that the server-side
 * plugin infrastructure is available and configured for the given atom.
 */
public class CheckSignalingPluginStartRequest implements JsonSerializable {

    private final String atom;

    /**
     * Constructs a new request with the given atom identifier.
     *
     * @param atom the unique atom identifier for the signaling session
     */
    public CheckSignalingPluginStartRequest(String atom) {
        this.atom = atom;
    }

    /**
     * Returns the atom identifier.
     *
     * @return the atom string
     */
    public String getAtom() {
        return atom;
    }

    /**
     * Deserializes a CheckSignalingPluginStartRequest from a JSON object.
     *
     * @param json the JSON object containing the request data
     * @return a new CheckSignalingPluginStartRequest instance
     */
    public static CheckSignalingPluginStartRequest fromJSONObject(JSONObject json) {
        String atom = json.optString("atom", "");
        return new CheckSignalingPluginStartRequest(atom);
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("atom", atom);
        return json;
    }
}

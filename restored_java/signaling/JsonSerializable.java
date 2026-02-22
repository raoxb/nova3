package signaling;

import org.json.JSONObject;

/**
 * Interface for objects that can be serialized to a JSON representation.
 * All signaling protocol messages implement this interface to support
 * JSON-based communication over the signaling channel.
 */
public interface JsonSerializable {

    /**
     * Converts this object to its JSON representation.
     *
     * @return a {@link JSONObject} containing the serialized fields of this object
     */
    JSONObject toJSONObject();
}

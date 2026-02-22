package signaling;

import org.json.JSONObject;

/**
 * Represents an SDP (Session Description Protocol) offer in the WebRTC
 * signaling handshake. The offer is sent by the initiating peer to propose
 * media capabilities and transport parameters for the connection.
 */
public class SDPOffer implements JsonSerializable {

    private final String type;
    private final String sdp;

    /**
     * Constructs a new SDPOffer.
     *
     * @param type the SDP type (typically "offer")
     * @param sdp  the SDP body string
     */
    public SDPOffer(String type, String sdp) {
        this.type = type;
        this.sdp = sdp;
    }

    /**
     * Returns the SDP type.
     *
     * @return the type string (e.g. "offer")
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the SDP body.
     *
     * @return the full SDP string
     */
    public String getSdp() {
        return sdp;
    }

    /**
     * Deserializes an SDPOffer from a JSON object.
     *
     * @param json the JSON object containing SDP offer data
     * @return a new SDPOffer instance
     */
    public static SDPOffer fromJSONObject(JSONObject json) {
        String type = json.optString("type", "");
        String sdp = json.optString("sdp", "");
        return new SDPOffer(type, sdp);
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("type", type);
        json.put("sdp", sdp);
        return json;
    }
}

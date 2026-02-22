package signaling;

import org.json.JSONObject;

/**
 * Represents an SDP (Session Description Protocol) answer in the WebRTC
 * signaling handshake. The answer is sent by the receiving peer in response
 * to an offer, confirming the negotiated media and transport parameters.
 */
public class SDPAnswer implements JsonSerializable {

    private final String type;
    private final String sdp;

    /**
     * Constructs a new SDPAnswer.
     *
     * @param type the SDP type (typically "answer")
     * @param sdp  the SDP body string
     */
    public SDPAnswer(String type, String sdp) {
        this.type = type;
        this.sdp = sdp;
    }

    /**
     * Returns the SDP type.
     *
     * @return the type string (e.g. "answer")
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
     * Deserializes an SDPAnswer from a JSON object.
     *
     * @param json the JSON object containing SDP answer data
     * @return a new SDPAnswer instance
     */
    public static SDPAnswer fromJSONObject(JSONObject json) {
        String type = json.optString("type", "");
        String sdp = json.optString("sdp", "");
        return new SDPAnswer(type, sdp);
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("type", type);
        json.put("sdp", sdp);
        return json;
    }
}

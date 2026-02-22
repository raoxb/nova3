package signaling;

import org.json.JSONObject;

/**
 * Represents an ICE (Interactive Connectivity Establishment) candidate
 * in the WebRTC signaling protocol. ICE candidates describe potential
 * network paths (IP address, port, protocol) that can be used to
 * establish a peer-to-peer media connection.
 */
public class ICECandidate implements JsonSerializable {

    private final String candidate;
    private final String sdpMid;
    private final int sdpMlineIndex;

    /**
     * Constructs a new ICECandidate.
     *
     * @param candidate     the candidate attribute string
     * @param sdpMid        the media stream identification tag
     * @param sdpMlineIndex the zero-based index of the m-line in the SDP
     */
    public ICECandidate(String candidate, String sdpMid, int sdpMlineIndex) {
        this.candidate = candidate;
        this.sdpMid = sdpMid;
        this.sdpMlineIndex = sdpMlineIndex;
    }

    /**
     * Returns the ICE candidate attribute string.
     *
     * @return the candidate string
     */
    public String getCandidate() {
        return candidate;
    }

    /**
     * Returns the media stream identification tag.
     *
     * @return the sdpMid value
     */
    public String getSdpMid() {
        return sdpMid;
    }

    /**
     * Returns the zero-based index of the m-line in the SDP.
     *
     * @return the sdpMlineIndex value
     */
    public int getSdpMlineIndex() {
        return sdpMlineIndex;
    }

    /**
     * Deserializes an ICECandidate from a JSON object.
     *
     * @param json the JSON object containing ICE candidate data
     * @return a new ICECandidate instance
     */
    public static ICECandidate fromJSONObject(JSONObject json) {
        String candidate = json.optString("candidate", "");
        String sdpMid = json.optString("sdpMid", "");
        int sdpMlineIndex = json.optInt("sdpMlineIndex", 0);
        return new ICECandidate(candidate, sdpMid, sdpMlineIndex);
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("candidate", candidate);
        json.put("sdpMid", sdpMid);
        json.put("sdpMlineIndex", sdpMlineIndex);
        return json;
    }
}

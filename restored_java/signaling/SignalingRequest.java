package signaling;

import org.json.JSONObject;

/**
 * Represents an outbound signaling request sent from the client to the server.
 * Wraps a typed content payload (SDP offer, SDP answer, ICE candidate, control
 * command, or ping) together with the atom identifier. The content type is
 * recorded in a discriminator field so the server can route it appropriately.
 */
public class SignalingRequest implements JsonSerializable {

    /** Content type for an SDP offer payload. */
    public static final String CONTENT_TYPE_SDP_OFFER = "sdp_offer";

    /** Content type for an SDP answer payload. */
    public static final String CONTENT_TYPE_SDP_ANSWER = "sdp_answer";

    /** Content type for an ICE candidate payload. */
    public static final String CONTENT_TYPE_ICE_CANDIDATE = "ice_candidate";

    /** Content type for a remote control command payload. */
    public static final String CONTENT_TYPE_CONTROL = "control";

    /** Content type for a ping (keep-alive) payload. */
    public static final String CONTENT_TYPE_PING = "ping";

    private final String contentType;
    private final JsonSerializable content;
    private final String atom;

    /**
     * Constructs a new SignalingRequest.
     *
     * @param contentType the discriminator identifying the content type
     * @param content     the payload object (must be JsonSerializable)
     * @param atom        the atom identifier for this signaling session
     */
    public SignalingRequest(String contentType, JsonSerializable content, String atom) {
        this.contentType = contentType;
        this.content = content;
        this.atom = atom;
    }

    /**
     * Creates a SignalingRequest carrying an SDP offer.
     *
     * @param offer the SDP offer
     * @param atom  the atom identifier
     * @return a new SignalingRequest
     */
    public static SignalingRequest ofSDPOffer(SDPOffer offer, String atom) {
        return new SignalingRequest(CONTENT_TYPE_SDP_OFFER, offer, atom);
    }

    /**
     * Creates a SignalingRequest carrying an SDP answer.
     *
     * @param answer the SDP answer
     * @param atom   the atom identifier
     * @return a new SignalingRequest
     */
    public static SignalingRequest ofSDPAnswer(SDPAnswer answer, String atom) {
        return new SignalingRequest(CONTENT_TYPE_SDP_ANSWER, answer, atom);
    }

    /**
     * Creates a SignalingRequest carrying an ICE candidate.
     *
     * @param candidate the ICE candidate
     * @param atom      the atom identifier
     * @return a new SignalingRequest
     */
    public static SignalingRequest ofICECandidate(ICECandidate candidate, String atom) {
        return new SignalingRequest(CONTENT_TYPE_ICE_CANDIDATE, candidate, atom);
    }

    /**
     * Creates a SignalingRequest carrying a control command.
     *
     * @param command the control command
     * @param atom    the atom identifier
     * @return a new SignalingRequest
     */
    public static SignalingRequest ofControl(ControlCommand command, String atom) {
        return new SignalingRequest(CONTENT_TYPE_CONTROL, command, atom);
    }

    /**
     * Creates a SignalingRequest carrying a ping message.
     *
     * @param ping the ping message
     * @param atom the atom identifier
     * @return a new SignalingRequest
     */
    public static SignalingRequest ofPing(Ping ping, String atom) {
        return new SignalingRequest(CONTENT_TYPE_PING, ping, atom);
    }

    /**
     * Returns the content type discriminator.
     *
     * @return one of the CONTENT_TYPE_* constants
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Returns the payload content.
     *
     * @return the content object
     */
    public JsonSerializable getContent() {
        return content;
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
     * Deserializes a SignalingRequest from a JSON object.
     *
     * @param json the JSON object containing the request data
     * @return a new SignalingRequest instance, or null if the content type is unrecognized
     */
    public static SignalingRequest fromJSONObject(JSONObject json) {
        String atom = json.optString("atom", "");
        JSONObject contentJson = json.optJSONObject("content");
        if (contentJson == null) {
            return null;
        }

        String contentType = contentJson.optString("content_type", "");
        JsonSerializable content;

        switch (contentType) {
            case CONTENT_TYPE_SDP_OFFER:
                content = SDPOffer.fromJSONObject(contentJson);
                break;
            case CONTENT_TYPE_SDP_ANSWER:
                content = SDPAnswer.fromJSONObject(contentJson);
                break;
            case CONTENT_TYPE_ICE_CANDIDATE:
                content = ICECandidate.fromJSONObject(contentJson);
                break;
            case CONTENT_TYPE_CONTROL:
                content = ControlCommand.fromJSONObject(contentJson);
                break;
            case CONTENT_TYPE_PING:
                content = Ping.fromJSONObject(contentJson);
                break;
            default:
                return null;
        }

        return new SignalingRequest(contentType, content, atom);
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("atom", atom);

        if (content != null) {
            JSONObject contentJson = content.toJSONObject();
            contentJson.put("content_type", contentType);
            json.put("content", contentJson);
        }

        return json;
    }
}

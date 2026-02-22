package signaling;

import org.json.JSONObject;

/**
 * Represents an inbound signaling response received from the server.
 * Wraps a typed content payload (SDP offer, SDP answer, ICE candidate,
 * connection status, pong, or done) and an optional error. The content
 * type is identified by a discriminator field embedded in the content JSON.
 */
public class SignalingResponse {

    /** Content type for an SDP offer payload. */
    public static final String CONTENT_TYPE_SDP_OFFER = "sdp_offer";

    /** Content type for an SDP answer payload. */
    public static final String CONTENT_TYPE_SDP_ANSWER = "sdp_answer";

    /** Content type for an ICE candidate payload. */
    public static final String CONTENT_TYPE_ICE_CANDIDATE = "ice_candidate";

    /** Content type for a connection status update payload. */
    public static final String CONTENT_TYPE_STATUS = "status";

    /** Content type for a pong (keep-alive response) payload. */
    public static final String CONTENT_TYPE_PONG = "pong";

    /** Content type for a done (session complete) payload. */
    public static final String CONTENT_TYPE_DONE = "done";

    private final String contentType;
    private final Object content;
    private final Error error;

    /**
     * Constructs a new SignalingResponse.
     *
     * @param contentType the discriminator identifying the content type
     * @param content     the deserialized content object
     * @param error       an optional error, or null if no error occurred
     */
    public SignalingResponse(String contentType, Object content, Error error) {
        this.contentType = contentType;
        this.content = content;
        this.error = error;
    }

    /**
     * Returns the content type discriminator.
     *
     * @return one of the CONTENT_TYPE_* constants, or null/empty if only an error is present
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Returns the deserialized content object.
     * The caller should cast based on {@link #getContentType()}.
     *
     * @return the content object, or null
     */
    public Object getContent() {
        return content;
    }

    /**
     * Returns the content as an SDPOffer, if applicable.
     *
     * @return the SDPOffer, or null
     */
    public SDPOffer getSDPOffer() {
        return content instanceof SDPOffer ? (SDPOffer) content : null;
    }

    /**
     * Returns the content as an SDPAnswer, if applicable.
     *
     * @return the SDPAnswer, or null
     */
    public SDPAnswer getSDPAnswer() {
        return content instanceof SDPAnswer ? (SDPAnswer) content : null;
    }

    /**
     * Returns the content as an ICECandidate, if applicable.
     *
     * @return the ICECandidate, or null
     */
    public ICECandidate getICECandidate() {
        return content instanceof ICECandidate ? (ICECandidate) content : null;
    }

    /**
     * Returns the content as a ConnectionStatus, if applicable.
     *
     * @return the ConnectionStatus, or null
     */
    public ConnectionStatus getConnectionStatus() {
        return content instanceof ConnectionStatus ? (ConnectionStatus) content : null;
    }

    /**
     * Returns the content as a Pong, if applicable.
     *
     * @return the Pong, or null
     */
    public Pong getPong() {
        return content instanceof Pong ? (Pong) content : null;
    }

    /**
     * Returns the content as a Done, if applicable.
     *
     * @return the Done, or null
     */
    public Done getDone() {
        return content instanceof Done ? (Done) content : null;
    }

    /**
     * Returns the error, if one was present in the response.
     *
     * @return the Error object, or null if no error
     */
    public Error getError() {
        return error;
    }

    /**
     * Returns whether this response contains an error.
     *
     * @return true if an error is present
     */
    public boolean hasError() {
        return error != null;
    }

    /**
     * Deserializes a SignalingResponse from a JSON object.
     *
     * @param json the JSON object containing the response data
     * @return a new SignalingResponse instance
     */
    public static SignalingResponse fromJSONObject(JSONObject json) {
        Error error = null;
        JSONObject errorJson = json.optJSONObject("error");
        if (errorJson != null) {
            error = Error.fromJSONObject(errorJson);
        }

        JSONObject contentJson = json.optJSONObject("content");
        if (contentJson == null) {
            return new SignalingResponse(null, null, error);
        }

        String contentType = contentJson.optString("content_type", "");
        Object content;

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
            case CONTENT_TYPE_STATUS:
                int statusValue = contentJson.optInt("status", 2);
                content = ConnectionStatus.fromValue(statusValue);
                break;
            case CONTENT_TYPE_PONG:
                content = Pong.fromJSONObject(contentJson);
                break;
            case CONTENT_TYPE_DONE:
                content = Done.fromJSONObject(contentJson);
                break;
            default:
                content = null;
                break;
        }

        return new SignalingResponse(contentType, content, error);
    }
}

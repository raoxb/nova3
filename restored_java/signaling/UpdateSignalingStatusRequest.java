package signaling;

import org.json.JSONObject;

/**
 * Request message to update the current status of a signaling session.
 * Sent at various lifecycle points (start, landing page, completion) so
 * the server can track progress and coordinate with other components.
 */
public class UpdateSignalingStatusRequest implements JsonSerializable {

    /**
     * Enumerates the possible signaling session statuses.
     */
    public enum Status {
        /** Status is not yet known or has not been set. */
        UNKNOWN(0),
        /** The signaling session has started. */
        START(1),
        /** The session has reached the landing page. */
        IN_LANDING(2),
        /** The session has completed. */
        DONE(3);

        private final int value;

        Status(int value) {
            this.value = value;
        }

        /**
         * Returns the integer value associated with this status.
         *
         * @return the numeric status code
         */
        public int getValue() {
            return value;
        }

        /**
         * Resolves a Status from its integer value.
         *
         * @param value the numeric status code
         * @return the matching Status, or {@link #UNKNOWN} if not recognized
         */
        public static Status fromValue(int value) {
            for (Status s : values()) {
                if (s.value == value) {
                    return s;
                }
            }
            return UNKNOWN;
        }
    }

    private final String atom;
    private final String jobId;
    private final Status status;
    private final String url;

    /**
     * Constructs a new UpdateSignalingStatusRequest.
     *
     * @param atom   the unique atom identifier
     * @param jobId  the job identifier
     * @param status the current session status
     * @param url    an optional URL associated with the status update
     */
    public UpdateSignalingStatusRequest(String atom, String jobId, Status status, String url) {
        this.atom = atom;
        this.jobId = jobId;
        this.status = status;
        this.url = url;
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
     * Returns the job identifier.
     *
     * @return the job ID string
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * Returns the session status.
     *
     * @return the current Status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Returns the URL associated with this status update.
     *
     * @return the URL string, or empty if not applicable
     */
    public String getUrl() {
        return url;
    }

    /**
     * Deserializes an UpdateSignalingStatusRequest from a JSON object.
     *
     * @param json the JSON object containing the request data
     * @return a new UpdateSignalingStatusRequest instance
     */
    public static UpdateSignalingStatusRequest fromJSONObject(JSONObject json) {
        String atom = json.optString("atom", "");
        String jobId = json.optString("jobId", "");
        Status status = Status.fromValue(json.optInt("status", 0));
        String url = json.optString("url", "");
        return new UpdateSignalingStatusRequest(atom, jobId, status, url);
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("atom", atom);
        json.put("jobId", jobId);
        json.put("status", status.getValue());
        json.put("url", url);
        return json;
    }
}

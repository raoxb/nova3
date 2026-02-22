package signaling;

import org.json.JSONObject;

/**
 * Response message indicating whether the signaling plugin is ready to start.
 * Contains a result code, a descriptive message, a run flag, and identifiers
 * for the offer and job that were created (or matched) on the server side.
 */
public class CheckSignalingPluginStartResponse implements JsonSerializable {

    private final int code;
    private final String message;
    private final boolean run;
    private final String offerId;
    private final String jobId;

    /**
     * Constructs a new response with all fields.
     *
     * @param code    the result code (0 typically means success)
     * @param message a human-readable result description
     * @param run     whether the plugin should proceed to run
     * @param offerId the identifier of the signaling offer
     * @param jobId   the identifier of the associated job
     */
    public CheckSignalingPluginStartResponse(int code, String message, boolean run,
                                             String offerId, String jobId) {
        this.code = code;
        this.message = message;
        this.run = run;
        this.offerId = offerId;
        this.jobId = jobId;
    }

    /**
     * Returns the result code.
     *
     * @return the numeric result code
     */
    public int getCode() {
        return code;
    }

    /**
     * Returns the result message.
     *
     * @return a descriptive message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns whether the plugin should run.
     *
     * @return true if the plugin is cleared to start
     */
    public boolean isRun() {
        return run;
    }

    /**
     * Returns the offer identifier.
     *
     * @return the offer ID string
     */
    public String getOfferId() {
        return offerId;
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
     * Deserializes a CheckSignalingPluginStartResponse from a JSON object.
     *
     * @param json the JSON object containing the response data
     * @return a new CheckSignalingPluginStartResponse instance
     */
    public static CheckSignalingPluginStartResponse fromJSONObject(JSONObject json) {
        int code = json.optInt("code", 0);
        String message = json.optString("message", "");
        boolean run = json.optBoolean("run", false);
        String offerId = json.optString("offerId", "");
        String jobId = json.optString("jobId", "");
        return new CheckSignalingPluginStartResponse(code, message, run, offerId, jobId);
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("message", message);
        json.put("run", run);
        json.put("offerId", offerId);
        json.put("jobId", jobId);
        return json;
    }
}

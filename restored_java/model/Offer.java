package model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Offer — 广告任务配置 (C&C 任务分发)
 *
 * Original: lIllIIIlIl1.llIIIIlIlllIII1
 *
 * Represents an ad-fraud task offer received from the C&C server.
 * Contains the target URL, job ID, and offer ID for the automation task.
 *
 * JSON format:
 * {
 *   "site_url": "https://target-ad.example.com/landing",
 *   "job_id": "abc123",
 *   "offer_id": "offer_456"
 * }
 *
 * Fields:
 *   - f431llllIIIIll1 → siteUrl (String, the target website)
 *   - f430lIIIIlllllIlll1 → jobId (String)
 *   - f432llllIllIl1 → offerId (String)
 */
public class Offer implements Jsonable {

    private final String siteUrl;   /* f431llllIIIIll1 */
    private final String jobId;     /* f430lIIIIlllllIlll1 */
    private final String offerId;   /* f432llllIllIl1 */

    public Offer(String siteUrl, String jobId, String offerId) {
        this.siteUrl = siteUrl;
        this.jobId = jobId;
        this.offerId = offerId;
    }

    /** Returns the target ad landing page URL. Original: llllIllIl1() */
    public String getSiteUrl() { return this.siteUrl; }

    /** Returns the job ID for this task. Original: llllIIIIll1() */
    public String getJobId() { return this.jobId; }

    /** Returns the offer ID for tracking. Original: lIIIIlllllIlll1() */
    public String getOfferId() { return this.offerId; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Offer other = (Offer) obj;
        if (this.siteUrl == null ? other.siteUrl != null : !this.siteUrl.equals(other.siteUrl))
            return false;
        if (this.jobId == null ? other.jobId != null : !this.jobId.equals(other.jobId))
            return false;
        if (this.offerId != null) {
            return this.offerId.equals(other.offerId);
        }
        return other.offerId == null;
    }

    @Override
    public int hashCode() {
        int result = (this.siteUrl != null ? this.siteUrl.hashCode() : 0) * 31;
        result = (result + (this.jobId != null ? this.jobId.hashCode() : 0)) * 31;
        result += (this.offerId != null ? this.offerId.hashCode() : 0);
        return result;
    }

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("site_url", this.siteUrl);
        json.put("job_id", this.jobId);
        json.put("offer_id", this.offerId);
        return json;
    }

    @Override
    public String toString() {
        return "Offer{siteUrl='" + this.siteUrl + '\'' +
                ", jobId='" + this.jobId + '\'' +
                ", offerId='" + this.offerId + "'}";
    }

    /**
     * Parses an Offer from a JSONObject.
     * Original: llllIIIIll1(JSONObject)
     */
    public static Offer fromJson(JSONObject json) {
        return new Offer(
                json.optString("site_url", ""),
                json.optString("job_id", ""),
                json.optString("offer_id", ""));
    }
}

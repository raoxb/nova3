package model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * DeviceAuthRequest — 设备认证请求体
 *
 * Original: lIllIIIlIl1.lIIIIlllllIlll1
 *
 * Request payload sent to the C&C server for authentication.
 * Contains app identity, device identity, auth token, and device fingerprint (Atom).
 *
 * JSON format:
 * {
 *   "app_id": "com.example.app",
 *   "device_id": "abc123def456",
 *   "token": "eyJ...",
 *   "atom": { ...device fingerprint... }
 * }
 *
 * Fields:
 *   - f422llllIIIIll1 → appId (String, the host app package name)
 *   - f421lIIIIlllllIlll1 → deviceId (String)
 *   - f423llllIllIl1 → token (String, auth token)
 *   - f420IllIIlIIII1 → atom (DeviceFingerprint/Atom object)
 */
public class DeviceAuthRequest implements Jsonable {

    private final String appId;              /* f422llllIIIIll1 */
    private final String deviceId;           /* f421lIIIIlllllIlll1 */
    private final String token;              /* f423llllIllIl1 */
    private final DeviceFingerprint atom;    /* f420IllIIlIIII1 */

    public DeviceAuthRequest(String appId, String deviceId, String token,
                             DeviceFingerprint atom) {
        this.appId = appId;
        this.deviceId = deviceId;
        this.token = token == null ? "" : token;
        this.atom = atom;
    }

    /** Returns the host app package name. */
    public String getAppId() { return this.appId; }         /* llllIIIIll1() */

    /** Returns the unique device ID. */
    public String getDeviceId() { return this.deviceId; }   /* llllIllIl1() */

    /** Returns the auth token. */
    public String getToken() { return this.token; }         /* IllIIlIIII1() */

    /** Returns the device fingerprint. */
    public DeviceFingerprint getAtom() { return this.atom; } /* lIIIIlllllIlll1() */

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("app_id", this.appId);
        json.put("device_id", this.deviceId);
        json.put("token", this.token);
        json.put("atom", this.atom.toJSONObject());
        return json;
    }

    /**
     * Parses a DeviceAuthRequest from a JSONObject.
     * Original: llllIIIIll1(JSONObject)
     */
    public static DeviceAuthRequest fromJson(JSONObject json) throws JSONException {
        JSONObject atomJson = json.optJSONObject("atom");
        return new DeviceAuthRequest(
                json.optString("app_id", ""),
                json.optString("device_id", ""),
                json.optString("token", ""),
                DeviceFingerprint.fromJson(
                        atomJson != null ? atomJson : new JSONObject()));
    }

    /**
     * Factory method: creates a DeviceFingerprint from the device's current state.
     * Collects screen resolution, app version, locale, timezone, model, brand, etc.
     *
     * Original: llllIIIIll1(Context, String appId, String deviceId)
     * (Defined inline in lIllIIIlIl1.lIIIIlllllIlll1)
     */
    // See DeviceFingerprint.collectFromDevice() for the device info collection logic.
}

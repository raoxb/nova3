package c2;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * MALWARE ANALYSIS - RESTORED FROM OBFUSCATED CLASS: com/idlmlpugdw/h5_v1_refactor/H5V1Refactor
 *
 * H5 protocol version 1 refactor utility. Parses an Atom (device fingerprint
 * payload) from a JSON object. Used as part of the H5 (HTML5/WebView) protocol
 * upgrade path, where the C&C communication format was refactored from the
 * original version to a cleaner JSON-based format.
 *
 * Original obfuscated name: com.idlmlpugdw.h5_v1_refactor.H5V1Refactor
 */
public class H5V1Refactor {

    /**
     * Parses an Atom from the given JSON object.
     * The Atom contains device identification, app metadata, session info,
     * and locally-installed plugin data for C&C communication.
     *
     * @param json the JSON object containing atom fields
     * @return the parsed Atom instance
     * @throws JSONException if required fields are missing or malformed
     */
    public static Atom parseAtom(JSONObject json) throws JSONException {
        return Atom.fromJSONObject(json);
    }
}

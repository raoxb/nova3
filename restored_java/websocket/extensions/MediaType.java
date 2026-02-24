package websocket.extensions;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * MALWARE ANALYSIS — Media type / extension parameter parser
 *
 * Original: lIIlllIIIlllII1.llllIllIl1
 *
 * Parses extension parameter strings like "permessage-deflate; client_max_window_bits=15"
 * into a name and key-value parameter map.
 */
public class MediaType {

    /** Media type / extension name. Original: f381lIIIIlllllIlll1 */
    public String name;

    /** Parameters map. Original: f382llllIIIIll1 */
    public Map<String, String> parameters = new LinkedHashMap<>();

    /**
     * Parse a media type / extension parameter string.
     * Original: llllIIIIll1(String) -> MediaType
     */
    public static MediaType parse(String str) {
        MediaType mediaType = new MediaType();
        String[] parts = str.split(";");
        mediaType.name = parts[0].trim();
        for (int i = 1; i < parts.length; i++) {
            String[] kv = parts[i].split("=");
            String value;
            if (kv.length > 1) {
                value = kv[1].trim();
                // Strip quotes
                if ((value.startsWith("\"") && value.endsWith("\""))
                        || (value.startsWith("'") && value.endsWith("'") && value.length() > 2)) {
                    value = value.substring(1, value.length() - 1);
                }
            } else {
                value = "";
            }
            mediaType.parameters.put(kv[0].trim(), value);
        }
        return mediaType;
    }

    /** Get the media type name. Original: llllIIIIll1() */
    public String getName() {
        return name;
    }

    /** Get the parameters. Original: lIIIIlllllIlll1() */
    public Map<String, String> getParameters() {
        return parameters;
    }
}

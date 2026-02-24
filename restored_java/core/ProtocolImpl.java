package core;

import java.util.regex.Pattern;

/**
 * MALWARE ANALYSIS — Protocol/subprotocol implementation
 *
 * Original: IIlllllIlll1.lIIIIlllllIlll1
 *
 * Concrete implementation of Protocol interface.
 * Matches subprotocol names against comma-separated header values.
 */
public class ProtocolImpl implements Protocol {

    /** Pattern for splitting on spaces. Original: f68lIIIIlllllIlll1 */
    public static final Pattern SPACE_PATTERN = Pattern.compile(" ");

    /** Pattern for splitting on commas. Original: f69llllIllIl1 */
    public static final Pattern COMMA_PATTERN = Pattern.compile(",");

    /** Protocol name. Original: f70llllIIIIll1 */
    public final String protocolName;

    public ProtocolImpl(String protocolName) {
        if (protocolName == null) {
            throw new IllegalArgumentException();
        }
        this.protocolName = protocolName;
    }

    @Override
    public String getProtocolName() {
        return protocolName;
    }

    @Override
    public boolean acceptProvidedProtocol(String protocolHeader) {
        if ("".equals(protocolName)) {
            return true;
        }
        String normalized = SPACE_PATTERN.matcher(protocolHeader).replaceAll("");
        for (String proto : COMMA_PATTERN.split(normalized)) {
            if (protocolName.equals(proto)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Protocol copyInstance() {
        return new ProtocolImpl(protocolName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return protocolName.equals(((ProtocolImpl) obj).protocolName);
    }

    @Override
    public int hashCode() {
        return protocolName.hashCode();
    }

    @Override
    public String toString() {
        return getProtocolName();
    }
}

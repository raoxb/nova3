package websocket.handshake;

import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * MALWARE ANALYSIS — Concrete handshake implementation
 *
 * Original: lllIlIlllI1.llllllIlIIIlll1
 *
 * Base implementation of HandshakeBuilder using a case-insensitive TreeMap
 * for HTTP header storage.
 */
public class HandshakeImpl implements HandshakeBuilder {

    /** HTTP headers (case-insensitive). Original: f700lIIIIlllllIlll1 */
    public TreeMap<String, String> headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    /** HTTP body content. Original: f701llllIIIIll1 */
    public byte[] content;

    @Override
    public byte[] getContent() {
        return content;
    }

    @Override
    public Iterator<String> iterateHttpFields() {
        return Collections.unmodifiableSet(headers.keySet()).iterator();
    }

    @Override
    public boolean hasFieldValue(String name) {
        return headers.containsKey(name);
    }

    @Override
    public String getFieldValue(String name) {
        String value = headers.get(name);
        return value == null ? "" : value;
    }

    @Override
    public void putField(String name, String value) {
        headers.put(name, value);
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }
}

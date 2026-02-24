package websocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * MALWARE ANALYSIS — Deprecated SSL channel wrapper
 *
 * Original: llIIllIl1.lIIIIlllllIlll1
 *
 * Legacy SSL wrapper that uses a blocking SSLSocket (not SSLEngine).
 * This is the oldest SSL approach and is not used in the newer code paths.
 * Wraps I/O streams from an SSLSocket into a ByteChannel interface.
 *
 * Fields:
 *   - sslSocket (SSLSocket):     the blocking SSL socket
 *   - inputStream (InputStream): from SSLSocket
 *   - outputStream (OutputStream): from SSLSocket
 */
public class DeprecatedSSLChannel implements WrappedByteChannel {

    protected SSLSocket sslSocket;
    protected InputStream inputStream;
    protected OutputStream outputStream;

    public DeprecatedSSLChannel(SSLSocket socket) throws IOException {
        this.sslSocket = socket;
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
    }

    @Override
    public int read(ByteBuffer dst) throws IOException {
        byte[] buf = new byte[dst.remaining()];
        int len = inputStream.read(buf);
        if (len > 0) {
            dst.put(buf, 0, len);
        }
        return len;
    }

    @Override
    public int write(ByteBuffer src) throws IOException {
        byte[] buf = new byte[src.remaining()];
        src.get(buf);
        outputStream.write(buf);
        return buf.length;
    }

    @Override
    public boolean isOpen() {
        return !sslSocket.isClosed();
    }

    @Override
    public void close() throws IOException {
        sslSocket.close();
    }

    @Override
    public boolean isNeedWrite() { return false; }

    @Override
    public boolean isNeedRead() { return false; }

    @Override
    public int writePendingData(ByteBuffer buffer) { return 0; }

    @Override
    public void closeChannel() throws IOException { close(); }

    @Override
    public boolean isBlocking() { return true; }
}

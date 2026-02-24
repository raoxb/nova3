package websocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.net.SocketFactory;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * MALWARE ANALYSIS -- Abstract WebSocket client base class
 *
 * Original: lllIlIIIlI1.lIIIIlllllIlll1
 *     extends llIIllIl1.llllIIIIll1 (AbstractWebSocket)
 *     implements Runnable, IlIllIlllIllI1 (WebSocket interface)
 *
 * Large class (~695 lines) providing the core WebSocket client functionality:
 *
 *   - Socket creation with proxy support and SSL upgrade
 *   - Connection with configurable timeout
 *   - WebSocket handshake via the Draft protocol
 *   - Read loop in {@link #run()} method (runs on the connect/read thread)
 *   - Asynchronous write via an inner {@link WriteThread}
 *   - Connection/close synchronization via {@link CountDownLatch}es
 *   - DNS resolution via pluggable {@link DnsResolver}
 *   - Reconnect support ({@link #resetConnection()})
 *
 * Threading model:
 *   - Read thread ("WebSocketConnectReadThread-{id}"): created in {@link #connect()},
 *     runs the {@link #run()} method which performs the handshake and read loop
 *   - Write thread ("WebSocketWriteThread-{id}"): inner {@link WriteThread},
 *     takes ByteBuffers from a blocking queue and flushes to the output stream
 *
 * Abstract methods that subclasses must implement:
 *   - {@link #onMessage(String)} -- text message received
 *   - {@link #onClose(int, String, boolean)} -- connection closed
 *   - {@link #onError(Exception)} -- error occurred
 *   - {@link #onOpen(Object)} -- connection opened (ServerHandshake)
 *
 * Constructors:
 *   - (URI)
 *   - (URI, Draft)
 *   - (URI, headers Map)
 *   - (URI, Draft, headers Map, connectTimeout)
 */
public abstract class WebSocketClientBase extends AbstractWebSocket implements Runnable {

    // =========================================================================
    // Fields
    // =========================================================================

    /** Output stream for writing to the socket. Original: f680IIIlIllIlI1 */
    public OutputStream outputStream;

    /** Connection timeout in milliseconds. Original: f681IlIIIIllllIlI1 */
    public int connectTimeout;

    /** Target WebSocket URI. Original: f682IlIIlllllI1 */
    public URI uri;

    /** Custom socket factory (null = default). Original: f683IlIlIIIlIlIlll1 */
    public SocketFactory socketFactory;

    /** Extra HTTP headers for the handshake. Original: f684IllIlIllll1 */
    public Map<String, String> extraHeaders;

    /** Latch that counts down when the connection is fully closed. Original: f685IllllIllllll1 */
    public CountDownLatch closeLatch;

    /** WebSocket protocol draft. Original: f686lIIlIIIIlIlII1 */
    public Object draft;  /* lIlllIIIII1.llllIIIIll1 */

    /** Latch that counts down when the handshake completes (open or fail). Original: f687lIIlllIIIlllII1 */
    public CountDownLatch connectLatch;

    /** The underlying TCP socket. Original: f688lIllIlIll1 */
    public Socket socket;

    /** The connect/read thread. Original: f689lIlllIIIII1 */
    public Thread readThread;

    /** WebSocket implementation (frame encoder/decoder). Original: f690llIIIIlIlllIII1 */
    public Object webSocketImpl;  /* lIllIIIlIl1 */

    /** Proxy for the socket connection. Original: f691llIIllIl1 */
    public Proxy proxy;

    /** The write thread. Original: f692lllIlIIIlI1 */
    public Thread writeThread;

    /** DNS resolver. Original: f693lllIlIlllI1 */
    public DnsResolver dnsResolver;

    // =========================================================================
    // Constructors
    // =========================================================================

    /**
     * Creates a WebSocket client with defaults (empty extensions, no headers, no timeout).
     *
     * Original: lIIIIlllllIlll1(URI)
     *
     * @param uri the WebSocket server URI (ws:// or wss://)
     */
    public WebSocketClientBase(URI uri) {
        this(uri, null /* draft */, null /* headers */, 0);
    }

    /**
     * Creates a WebSocket client with a specific protocol draft.
     *
     * Original: lIIIIlllllIlll1(URI, lIlllIIIII1.llllIIIIll1)
     *
     * @param uri   the WebSocket server URI
     * @param draft the protocol draft
     */
    public WebSocketClientBase(URI uri, Object draft) {
        this(uri, draft, null, 0);
    }

    /**
     * Creates a WebSocket client with extra handshake headers.
     *
     * Original: lIIIIlllllIlll1(URI, Map)
     *
     * @param uri     the WebSocket server URI
     * @param headers extra HTTP headers for the handshake
     */
    public WebSocketClientBase(URI uri, Map<String, String> headers) {
        this(uri, null /* draft */, headers, 0);
    }

    /**
     * Creates a WebSocket client with a specific protocol draft, headers, and timeout.
     *
     * Original: lIIIIlllllIlll1(URI, lIlllIIIII1.llllIIIIll1, Map, int)
     *
     * @param uri            the WebSocket server URI
     * @param draft          the protocol draft (null uses default empty extensions)
     * @param headers        extra HTTP headers (may be null)
     * @param connectTimeout connection timeout in milliseconds (0 = no timeout)
     */
    public WebSocketClientBase(URI uri, Object draft, Map<String, String> headers, int connectTimeout) {
        this.uri = null;
        this.webSocketImpl = null;
        this.socket = null;
        this.socketFactory = null;
        this.proxy = Proxy.NO_PROXY;
        this.connectLatch = new CountDownLatch(1);
        this.closeLatch = new CountDownLatch(1);
        this.connectTimeout = 0;
        this.dnsResolver = null;

        if (uri == null) {
            throw new IllegalArgumentException("URI must not be null");
        }
        if (draft == null) {
            /* Use default empty-extension draft */
        }
        this.uri = uri;
        this.draft = draft;
        this.dnsResolver = new DefaultDnsResolver();
        if (headers != null) {
            TreeMap<String, String> sorted = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
            this.extraHeaders = sorted;
            sorted.putAll(headers);
        }
        this.connectTimeout = connectTimeout;
        setTcpNoDelay(false);
        setReuseAddr(false);
        /* this.webSocketImpl = new WebSocketImpl(this, draft); */
    }

    // =========================================================================
    // Connection Lifecycle
    // =========================================================================

    /**
     * Starts the WebSocket connection on a new daemon thread.
     * The thread runs {@link #run()} which handles socket creation,
     * handshake, and the read loop.
     *
     * Original: llIlIIlll1()
     *
     * @throws IllegalStateException if this client has already been connected
     */
    public void connect() {
        if (this.readThread != null) {
            throw new IllegalStateException("WebSocketClient objects are not reuseable");
        }
        Thread thread = new Thread(this);
        this.readThread = thread;
        thread.setDaemon(true);
        this.readThread.setName("WebSocketConnectReadThread-" + this.readThread.getId());
        this.readThread.start();
    }

    /**
     * Initiates a close handshake (sends close frame with code 1000).
     *
     * Original: close() override
     */
    public void close() {
        if (this.writeThread != null) {
            /* webSocketImpl.close(1000); */
        }
    }

    /**
     * Returns whether the WebSocket is in the OPEN state.
     *
     * Original: isOpen() override
     */
    public boolean isOpen() {
        /* return webSocketImpl.isOpen(); */
        return false;
    }

    /**
     * Blocking connect: waits for the handshake to complete.
     *
     * Original: IIlllllIlll1() -> boolean
     *
     * @return true if the connection was opened successfully
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public boolean connectBlocking() throws InterruptedException {
        connect();
        this.connectLatch.await();
        return isOpen();
    }

    /**
     * Blocking connect with timeout.
     *
     * Original: llllIIIIll1(long, TimeUnit) -> boolean
     *
     * @param timeout the maximum time to wait
     * @param unit    the time unit
     * @return true if connected within the timeout
     * @throws InterruptedException if the thread is interrupted
     */
    public boolean connectBlocking(long timeout, TimeUnit unit) throws InterruptedException {
        connect();
        boolean connected = this.connectLatch.await(timeout, unit);
        if (!connected) {
            resetConnection();
        }
        return connected && isOpen();
    }

    /**
     * Blocking close: sends close frame and waits for the connection to terminate.
     *
     * Original: lllIlIlllI1()
     *
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public void closeBlocking() throws InterruptedException {
        close();
        this.closeLatch.await();
    }

    /**
     * Reconnect: resets the connection state and connects again.
     *
     * Original: lIlIIIllll1()
     */
    public void reconnect() {
        resetConnection();
        connect();
    }

    /**
     * Blocking reconnect with timeout.
     *
     * Original: lIIIIlllllIlll1(long, TimeUnit) -> boolean
     */
    public boolean reconnectBlocking(long timeout, TimeUnit unit) throws InterruptedException {
        resetConnection();
        return connectBlocking(timeout, unit);
    }

    /**
     * Blocking reconnect (no timeout).
     *
     * Original: llIllllIlI1() -> boolean
     */
    public boolean reconnectBlocking() throws InterruptedException {
        resetConnection();
        return connectBlocking();
    }

    // =========================================================================
    // Sending
    // =========================================================================

    /**
     * Sends a text message.
     *
     * Original: llllIIIIll1(String) [send override]
     *
     * @param text the text message to send
     */
    public void send(String text) {
        /* webSocketImpl.send(text); */
    }

    /**
     * Sends a binary message.
     *
     * Original: llllIIIIll1(byte[]) [send override]
     *
     * @param data the binary data to send
     */
    public void send(byte[] data) {
        /* webSocketImpl.send(data); */
    }

    /**
     * Sends a ping frame.
     *
     * Original: llllIllIl1() [sendPing]
     */
    public void sendPing() {
        /* webSocketImpl.sendPing(); */
    }

    // =========================================================================
    // Abstract Methods
    // =========================================================================

    /**
     * Called when a text message is received from the server.
     *
     * Original: lIIIIlllllIlll1(String) [abstract]
     *
     * @param message the received text message
     */
    public abstract void onMessage(String message);

    /**
     * Called when the WebSocket connection is closed.
     *
     * Original: llllIIIIll1(int, String, boolean) [abstract]
     *
     * @param code   the close code
     * @param reason the close reason
     * @param remote true if closed by the remote end
     */
    public abstract void onClose(int code, String reason, boolean remote);

    /**
     * Called when an error occurs.
     *
     * Original: llllIIIIll1(Exception) [abstract]
     *
     * @param exception the error
     */
    public abstract void onError(Exception exception);

    /**
     * Called when the WebSocket connection is successfully opened.
     *
     * Original: llllIIIIll1(IlIlIIlIII1) [abstract, ServerHandshake parameter]
     *
     * @param serverHandshake the server's handshake response
     */
    public abstract void onOpen(Object serverHandshake);

    /**
     * Called when a binary message is received (optional override).
     *
     * Original: lIIIIlllllIlll1(ByteBuffer) [non-abstract, empty default]
     *
     * @param bytes the received binary data
     */
    public void onMessage(ByteBuffer bytes) {
        /* default no-op */
    }

    /**
     * Called when a close frame with code/reason is received (optional override).
     *
     * Original: lIIIIlllllIlll1(int, String, boolean)
     */
    public void onCloseInitiated(int code, String reason, boolean remote) {
        /* default no-op */
    }

    /**
     * Called to allow subclasses to customize SSL parameters.
     *
     * Original: llllIIIIll1(SSLParameters)
     *
     * @param sslParameters the SSL parameters to customize
     */
    public void onSetSSLParameters(SSLParameters sslParameters) {
        sslParameters.setEndpointIdentificationAlgorithm("HTTPS");
    }

    // =========================================================================
    // Socket / Connection Internals
    // =========================================================================

    /**
     * Returns the underlying TCP socket.
     *
     * Original: IlIIIIIlll1() -> Socket
     */
    public Socket getSocket() {
        return this.socket;
    }

    /**
     * Returns the target URI.
     *
     * Original: llIIIlIIIlIII1() -> URI
     */
    public URI getURI() {
        return this.uri;
    }

    /**
     * Returns whether the connection uses SSL.
     *
     * Original: IIlIllIIll1() -> boolean
     */
    public boolean isSSL() {
        return this.socket instanceof SSLSocket;
    }

    /**
     * Returns the SSL session if available.
     *
     * Original: IlIlIIlIII1() -> SSLSession
     *
     * @throws IllegalArgumentException if not using SSL
     */
    public SSLSession getSSLSession() {
        if (isSSL()) {
            return ((SSLSocket) this.socket).getSession();
        }
        throw new IllegalArgumentException("This websocket uses ws instead of wss. No SSLSession available.");
    }

    /**
     * Sets a custom proxy for the connection.
     *
     * Original: llllIIIIll1(Proxy)
     */
    public void setProxy(Proxy proxy) {
        if (proxy == null) {
            throw new IllegalArgumentException("Proxy must not be null");
        }
        this.proxy = proxy;
    }

    /**
     * Sets a custom socket factory (for SSL or other purposes).
     *
     * Original: llllIIIIll1(SocketFactory)
     */
    public void setSocketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    /**
     * Sets a pre-created socket.
     *
     * Original: llllIIIIll1(Socket) [@Deprecated]
     */
    @Deprecated
    public void setSocket(Socket socket) {
        if (this.socket != null) {
            throw new IllegalStateException("socket has already been set");
        }
        this.socket = socket;
    }

    /**
     * Sets a custom DNS resolver.
     *
     * Original: llllIIIIll1(DnsResolver)
     */
    public void setDnsResolver(DnsResolver dnsResolver) {
        this.dnsResolver = dnsResolver;
    }

    /**
     * Adds an extra HTTP header for the handshake.
     *
     * Original: llllIIIIll1(String, String) [addHeader]
     */
    public void addHeader(String key, String value) {
        if (this.extraHeaders == null) {
            this.extraHeaders = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        }
        this.extraHeaders.put(key, value);
    }

    /**
     * Removes and returns an extra header.
     *
     * Original: llllIllIl1(String) -> String
     */
    public String removeHeader(String key) {
        Map<String, String> headers = this.extraHeaders;
        if (headers == null) {
            return null;
        }
        return headers.remove(key);
    }

    /**
     * Clears all extra headers.
     *
     * Original: IlIIIIllllIlI1()
     */
    public void clearHeaders() {
        this.extraHeaders = null;
    }

    // =========================================================================
    // Internal: Port Resolution
    // =========================================================================

    /**
     * Resolves the port from the URI, defaulting to 443 for wss:// and 80 for ws://.
     *
     * Original: IlIIIlIlIlIII1() -> int
     */
    private int resolvePort() {
        int port = this.uri.getPort();
        String scheme = this.uri.getScheme();
        if ("wss".equals(scheme)) {
            return port == -1 ? 443 : port;
        }
        if (!"ws".equals(scheme)) {
            throw new IllegalArgumentException("unknown scheme: " + scheme);
        }
        return port == -1 ? 80 : port;
    }

    // =========================================================================
    // Internal: SSL Upgrade
    // =========================================================================

    /**
     * Upgrades the plain socket to an SSL socket (for wss:// connections).
     *
     * Original: IIIlIllIl1()
     */
    private void upgradeToSSL() throws NoSuchAlgorithmException, KeyManagementException, IOException {
        SSLSocketFactory sslFactory = (this.socketFactory instanceof SSLSocketFactory)
                ? (SSLSocketFactory) this.socketFactory
                : (SSLSocketFactory) SSLSocketFactory.getDefault();
        this.socket = sslFactory.createSocket(this.socket, this.uri.getHost(), resolvePort(), true);
    }

    // =========================================================================
    // Internal: Socket Creation
    // =========================================================================

    /**
     * Creates the TCP socket, using a proxy or socket factory if configured.
     * Returns true if a new socket was created (vs. reusing an existing one).
     *
     * Original: IIlIlllllllI1() -> boolean
     */
    private boolean createSocket() throws IOException {
        if (this.proxy != Proxy.NO_PROXY) {
            this.socket = new Socket(this.proxy);
            return true;
        }
        SocketFactory factory = this.socketFactory;
        if (factory != null) {
            this.socket = factory.createSocket();
        } else {
            Socket existing = this.socket;
            if (existing == null) {
                this.socket = new Socket(this.proxy);
                return true;
            }
            if (existing.isClosed()) {
                throw new IOException("Socket is already closed");
            }
        }
        return false;
    }

    // =========================================================================
    // Internal: Reset (for reconnection)
    // =========================================================================

    /**
     * Resets the connection state for reconnection.
     * Closes existing socket and threads, creates fresh latches and impl.
     *
     * Original: lIIIllllllIIII1()
     *
     * MUST be called from a thread other than the read or write thread.
     */
    public final void resetConnection() {
        Thread currentThread = Thread.currentThread();
        if (currentThread == this.writeThread || currentThread == this.readThread) {
            throw new IllegalStateException(
                    "You cannot initialize a reconnect out of the websocket thread. "
                    + "Use reconnect in another thread to ensure a successful cleanup.");
        }
        try {
            /* Close socket if not yet connected */
            closeBlocking();

            Thread wt = this.writeThread;
            if (wt != null) {
                wt.interrupt();
                this.writeThread.join();
                this.writeThread = null;
            }
            Thread rt = this.readThread;
            if (rt != null) {
                rt.interrupt();
                this.readThread.join();
                this.readThread = null;
            }

            Socket sock = this.socket;
            if (sock != null) {
                sock.close();
                this.socket = null;
            }
            this.connectLatch = new CountDownLatch(1);
            this.closeLatch = new CountDownLatch(1);
            /* this.webSocketImpl = new WebSocketImpl(this, this.draft); */
        } catch (Exception e) {
            onError(e);
        }
    }

    // =========================================================================
    // Runnable: Connect + Read Loop
    // =========================================================================

    /**
     * Main connection and read loop, executed on the read thread.
     *
     * Steps:
     *   1. Create socket (with proxy/factory if configured)
     *   2. Set socket options (TCP_NODELAY, SO_REUSEADDR, receive buffer)
     *   3. Connect to server (with DNS resolver and timeout)
     *   4. Upgrade to SSL if wss://
     *   5. Set SSL parameters
     *   6. Get I/O streams
     *   7. Send handshake
     *   8. Start the write thread
     *   9. Enter read loop until closed or error
     *  10. Process end of stream
     *
     * Original: run() override
     */
    @Override
    public void run() {
        try {
            boolean newSocket = createSocket();
            this.socket.setTcpNoDelay(isTcpNoDelay());
            this.socket.setReuseAddress(isReuseAddr());

            if (!this.socket.isConnected()) {
                InetSocketAddress address = this.dnsResolver == null
                        ? InetSocketAddress.createUnresolved(this.uri.getHost(), resolvePort())
                        : new InetSocketAddress(this.dnsResolver.resolve(this.uri), resolvePort());
                this.socket.connect(address, this.connectTimeout);
            }

            if (newSocket && "wss".equals(this.uri.getScheme())) {
                upgradeToSSL();
            }

            if (this.socket instanceof SSLSocket) {
                SSLSocket sslSocket = (SSLSocket) this.socket;
                SSLParameters params = sslSocket.getSSLParameters();
                onSetSSLParameters(params);
                sslSocket.setSSLParameters(params);
            }

            InputStream inputStream = this.socket.getInputStream();
            this.outputStream = this.socket.getOutputStream();

            /* Send handshake */
            /* sendHandshake(); */

            /* Start write thread */
            Thread existingWriter = this.writeThread;
            if (existingWriter != null) {
                existingWriter.interrupt();
                try { this.writeThread.join(); } catch (InterruptedException ignored) {}
            }
            Thread wt = new Thread(new WriteThread(this));
            this.writeThread = wt;
            wt.setDaemon(true);
            this.writeThread.start();

            /* Read loop */
            byte[] buffer = new byte[16384];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                /* webSocketImpl.decode(ByteBuffer.wrap(buffer, 0, read)); */
            }
            /* webSocketImpl.eot(); */
        } catch (Exception e) {
            onError(e);
        } catch (InternalError e) {
            if (e.getCause() instanceof InvocationTargetException
                    && e.getCause().getCause() instanceof IOException) {
                IOException ioException = (IOException) e.getCause().getCause();
                onError(ioException);
            } else {
                throw e;
            }
        }
    }

    // =========================================================================
    // Inner Class: WriteThread
    // =========================================================================

    /**
     * Thread that flushes outbound WebSocket frames from a blocking queue
     * to the socket output stream.
     *
     * Original: RunnableC0022lIIIIlllllIlll1 (WriteThread)
     */
    public class WriteThread implements Runnable {

        /** Reference to the enclosing WebSocket client. Original: f695llllIIIIll1 */
        public final WebSocketClientBase client;

        public WriteThread(WebSocketClientBase client) {
            this.client = client;
        }

        /**
         * Main write loop: takes ByteBuffers from the blocking queue and writes them.
         *
         * Original: lIIIIlllllIlll1() -> void
         */
        public final void writeLoop() throws IOException {
            while (!Thread.interrupted()) {
                try {
                    /* ByteBuffer buffer = blockingQueue.take();
                     * client.outputStream.write(buffer.array(), 0, buffer.limit());
                     * client.outputStream.flush(); */
                } catch (Exception e) {
                    /* Drain remaining and flush on interrupt */
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        /**
         * Closes the socket on write thread termination.
         *
         * Original: llllIIIIll1() -> void
         */
        public final void closeSocket() {
            try {
                Socket socket = client.socket;
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                client.onError(e);
            }
        }

        @Override
        public void run() {
            Thread.currentThread().setName("WebSocketWriteThread-" + Thread.currentThread().getId());
            try {
                try {
                    writeLoop();
                } catch (IOException e) {
                    client.onError(e);
                }
            } finally {
                closeSocket();
            }
        }
    }

    // =========================================================================
    // Inner Class: DefaultDnsResolver
    // =========================================================================

    /**
     * Default DNS resolver implementation using {@link InetAddress#getByName}.
     *
     * Original: lllIlIIIlI1.lIIIIlllllIlll1$llllIIIIll1
     *     implements lllIlIIIlI1.llllIIIIll1 (DnsResolver)
     */
    public class DefaultDnsResolver implements DnsResolver {

        @Override
        public InetAddress resolve(URI uri) throws UnknownHostException {
            return InetAddress.getByName(uri.getHost());
        }
    }
}

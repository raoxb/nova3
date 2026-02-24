package signaling;

import android.util.Log;

import java.net.URI;

import org.json.JSONException;

/**
 * MALWARE ANALYSIS -- High-level signaling API client
 *
 * Original: IlIlIIlIII1.llllIllIl1
 *
 * Facade that combines the {@link SignalingHttpClient} (REST) and
 * {@link SignalingWebSocketManager} (WebSocket) into a single unified
 * signaling interface. Provides methods for:
 *
 *   - HTTP REST calls:
 *       - {@link #checkPluginStart(CheckSignalingPluginStartRequest)} -- POST
 *       - {@link #updateStatus(UpdateSignalingStatusRequest)} -- POST
 *
 *   - WebSocket operations:
 *       - {@link #connectWebSocket(SignalingWebSocketManager.SignalingConnectionListener)}
 *       - {@link #sendSignalingMessage(SignalingRequest)}
 *       - {@link #disconnect()} / {@link #shutdown()}
 *       - {@link #isConnected()}
 *
 * Construction requires two URLs:
 *   - An HTTP base URL for REST calls (passed to {@link SignalingHttpClient})
 *   - A WebSocket URL for the persistent signaling connection
 */
public class SignalingApiClient {

    // =========================================================================
    // Constants
    // =========================================================================

    /**
     * Log tag (XOR-encrypted at runtime).
     * Original: f226IllIIlIIII1 (static, encrypted)
     */
    public static final String LOG_TAG = "SignalingApiClient";

    // =========================================================================
    // Fields
    // =========================================================================

    /**
     * WebSocket URL for the signaling connection.
     * Original: f227lIIIIlllllIlll1
     */
    public final String websocketUrl;

    /**
     * HTTP client for REST API calls.
     * Original: f228llllIIIIll1 (type SignalingHttpClient)
     */
    public final SignalingHttpClient httpClient;

    /**
     * WebSocket manager for the persistent signaling connection.
     * Created lazily by {@link #connectWebSocket}; nulled on disconnect.
     * Original: f229llllIllIl1 (type SignalingWebSocketManager)
     */
    public SignalingWebSocketManager webSocketManager;

    // =========================================================================
    // Constructor
    // =========================================================================

    /**
     * Creates a new SignalingApiClient.
     *
     * Original: llllIllIl1(String, String)
     *
     * @param httpBaseUrl  the base URL for REST API calls
     * @param websocketUrl the WebSocket URL for the signaling connection
     */
    public SignalingApiClient(String httpBaseUrl, String websocketUrl) {
        this.httpClient = new SignalingHttpClient(httpBaseUrl);
        this.websocketUrl = websocketUrl;
    }

    // =========================================================================
    // REST API Methods
    // =========================================================================

    /**
     * Checks whether the signaling plugin should start.
     * Sends a POST to an encrypted endpoint path.
     *
     * Original: llllIIIIll1(CheckSignalingPluginStartRequest) -> CheckSignalingPluginStartResponse
     *
     * @param request the plugin start check request
     * @return the server's response
     * @throws JSONException if serialization/deserialization fails
     */
    public CheckSignalingPluginStartResponse checkPluginStart(
            CheckSignalingPluginStartRequest request) throws JSONException {
        Log.d(LOG_TAG, "Checking plugin start status");
        return (CheckSignalingPluginStartResponse) this.httpClient.postAndParse(
                "/api/v1/signaling/checkPluginStart",   /* decrypted from XOR */
                request,
                CheckSignalingPluginStartResponse.class);
    }

    /**
     * Updates the signaling status on the server.
     * Sends a POST to an encrypted endpoint path.
     *
     * Original: llllIIIIll1(UpdateSignalingStatusRequest) -> UpdateSignalingStatusResponse
     *
     * @param request the status update request
     * @return the server's response
     * @throws JSONException if serialization/deserialization fails
     */
    public UpdateSignalingStatusResponse updateStatus(
            UpdateSignalingStatusRequest request) throws JSONException {
        Log.d(LOG_TAG, "Updating signaling status: " + request.getStatus());
        return (UpdateSignalingStatusResponse) this.httpClient.postAndParse(
                "/api/v1/signaling/updateStatus",       /* decrypted from XOR */
                request,
                UpdateSignalingStatusResponse.class);
    }

    // =========================================================================
    // WebSocket Methods
    // =========================================================================

    /**
     * Creates and opens a WebSocket connection to the signaling server.
     * Disconnects any existing connection first.
     *
     * Original: llllIIIIll1(SignalingConnectionListener) -> SignalingWebSocketManager
     *
     * @param listener callback for WebSocket events
     * @return the newly created WebSocket manager
     */
    public SignalingWebSocketManager connectWebSocket(
            SignalingWebSocketManager.SignalingConnectionListener listener) {
        Log.d(LOG_TAG, "Connecting WebSocket to signaling server");
        disconnect();
        SignalingWebSocketManager manager = new SignalingWebSocketManager(
                URI.create(this.websocketUrl), listener);
        this.webSocketManager = manager;
        manager.connect();
        return this.webSocketManager;
    }

    /**
     * Sends a signaling message over the WebSocket.
     * If the WebSocket manager is null, logs a warning.
     *
     * Original: llllIIIIll1(SignalingRequest)
     *
     * @param request the signaling request to send
     */
    public void sendSignalingMessage(SignalingRequest request) {
        SignalingWebSocketManager manager = this.webSocketManager;
        if (manager != null) {
            manager.send(request);
            return;
        }
        Log.w(LOG_TAG, "Cannot send signaling message: WebSocket manager is null. "
                + "Call connectWebSocket() first.");
    }

    /**
     * Returns whether the WebSocket is currently connected.
     *
     * Original: llllIIIIll1() -> boolean
     *
     * @return true if the WebSocket is open
     */
    public boolean isConnected() {
        SignalingWebSocketManager manager = this.webSocketManager;
        return manager != null && manager.isConnected();
    }

    /**
     * Disconnects the WebSocket if it is currently connected.
     *
     * Original: llllIllIl1()
     */
    public void disconnect() {
        if (this.webSocketManager != null) {
            Log.d(LOG_TAG, "Disconnecting existing WebSocket");
            this.webSocketManager.disconnect();
            this.webSocketManager = null;
        }
    }

    /**
     * Fully shuts down the client: disconnects WebSocket and nullifies references.
     *
     * Original: lIIIIlllllIlll1()
     */
    public void shutdown() {
        Log.d(LOG_TAG, "Shutting down SignalingApiClient");
        disconnect();
    }
}

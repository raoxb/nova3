package webrtc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.webrtc.DataChannel;
import org.webrtc.DefaultVideoDecoderFactory;
import org.webrtc.DefaultVideoEncoderFactory;
import org.webrtc.EglBase;
import org.webrtc.IceCandidate;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.RtpReceiver;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;
import org.webrtc.SoftwareVideoDecoderFactory;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * WebRTCController is the main controller class that orchestrates a WebRTC session
 * for remote control of an Android WebView.
 *
 * <p>This class manages the full lifecycle of a WebRTC peer connection including:
 * <ul>
 *   <li>Initializing the PeerConnectionFactory with EGL context and codec factories</li>
 *   <li>Creating and configuring the PeerConnection with TURN/STUN servers</li>
 *   <li>Handling SDP offer/answer exchange for session negotiation</li>
 *   <li>Processing ICE candidates for connectivity establishment</li>
 *   <li>Capturing the WebView screen via VirtualDisplay or Bitmap fallback</li>
 *   <li>Streaming video to the remote peer over a VideoTrack</li>
 *   <li>Receiving remote control commands over a DataChannel (click, scroll, drag, paste, etc.)</li>
 *   <li>Injecting JavaScript into the WebView for text input operations</li>
 *   <li>Managing heartbeat ping/pong for connection health monitoring</li>
 *   <li>Reconnection logic when connectivity is lost</li>
 * </ul>
 *
 * <p>The signaling layer is abstracted via the {@link SignalingCallback} interface,
 * which the host activity/service must implement to relay SDP and ICE messages
 * to the remote peer through a signaling server.
 *
 * <p>TURN servers used:
 * <pre>
 *   turn:101.36.120.3:3478  (user: wumitech, pass: wumitech.com@123)
 *   turn:106.75.153.105:3478 (user: wumitech, pass: wumitech.com@123)
 * </pre>
 */
public class WebRTCController {

    private static final String TAG = "WebRTCController";

    // -------------------------------------------------------------------------
    // TURN / STUN server configuration
    // -------------------------------------------------------------------------
    private static final String TURN_SERVER_1 = "turn:101.36.120.3:3478";
    private static final String TURN_SERVER_2 = "turn:106.75.153.105:3478";
    private static final String TURN_USERNAME = "wumitech";
    private static final String TURN_PASSWORD = "wumitech.com@123";

    // -------------------------------------------------------------------------
    // Heartbeat / timing constants
    // -------------------------------------------------------------------------
    private static final long HEARTBEAT_INTERVAL_MS = 5000;
    private static final long HEARTBEAT_TIMEOUT_MS = 15000;
    private static final long RECONNECT_DELAY_MS = 3000;
    private static final int MAX_RECONNECT_ATTEMPTS = 5;

    // -------------------------------------------------------------------------
    // Video constants
    // -------------------------------------------------------------------------
    private static final int VIDEO_WIDTH = 720;
    private static final int VIDEO_HEIGHT = 1280;
    private static final int VIDEO_FPS = 15;

    private static final String VIDEO_TRACK_ID = "video_track_0";
    private static final String MEDIA_STREAM_ID = "media_stream_0";
    private static final String DATA_CHANNEL_LABEL = "control";

    // -------------------------------------------------------------------------
    // DataChannel action constants
    // -------------------------------------------------------------------------
    private static final String ACTION_CLICK = "click";
    private static final String ACTION_DRAG = "drag";
    private static final String ACTION_DRAG_START = "dragStart";
    private static final String ACTION_DRAG_END = "dragEnd";
    private static final String ACTION_SCROLL = "scroll";
    private static final String ACTION_PASTE = "paste";
    private static final String ACTION_KEY_INPUT = "keyInput";
    private static final String ACTION_GO_BACK = "goBack";
    private static final String ACTION_CLOSE = "close";
    private static final String ACTION_PING = "ping";
    private static final String ACTION_PONG = "pong";
    private static final String ACTION_SCREENSHOT = "screenshot";

    // -------------------------------------------------------------------------
    // Core fields
    // -------------------------------------------------------------------------

    /** Application context, used for system service access. */
    private final Context context;

    /** The WebView being remotely controlled. */
    private WebView webView;

    /** Callback interface for relaying signaling messages (SDP, ICE). */
    private final SignalingCallback signalingCallback;

    /** EGL base context shared across video pipeline components. */
    private EglBase eglBaseContext;

    /** Factory for creating PeerConnections, VideoSources, etc. */
    private PeerConnectionFactory peerConnectionFactory;

    /** The active peer connection to the remote controller. */
    private PeerConnection peerConnection;

    /** Bidirectional data channel for control commands and heartbeat. */
    private DataChannel dataChannel;

    /** Video source backed by a screen capturer. */
    private VideoSource videoSource;

    /** Video track added to the peer connection's media stream. */
    private VideoTrack videoTrack;

    /** Screen capturer implementation (VirtualDisplay or Bitmap fallback). */
    private VideoCapturer videoCapturer;

    /** Helper for surface texture management on the video pipeline thread. */
    private SurfaceTextureHelper surfaceTextureHelper;

    /** Main thread handler for UI operations (touch injection, JS evaluation). */
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    /** Background executor for non-UI work (encoding, network). */
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    /** Timer for periodic heartbeat pings. */
    private Timer heartbeatTimer;

    /** Timestamp (ms) of the last received pong from the remote peer. */
    private long lastPongTimestamp = 0;

    /** Number of reconnection attempts since the last successful connection. */
    private int reconnectAttempts = 0;

    /** Whether the controller has been fully initialized and is active. */
    private volatile boolean isActive = false;

    /** Whether cleanup has already been performed. */
    private volatile boolean isCleanedUp = false;

    /** Display metrics for coordinate scaling between remote and local displays. */
    private int displayWidth;
    private int displayHeight;
    private float displayDensity;

    /** Width and height of the video stream as negotiated. */
    private int streamWidth = VIDEO_WIDTH;
    private int streamHeight = VIDEO_HEIGHT;

    /** Pending ICE candidates received before remote description is set. */
    private final List<IceCandidate> pendingIceCandidates = new ArrayList<>();

    /** Flag indicating whether the remote description has been set. */
    private volatile boolean isRemoteDescriptionSet = false;

    /** Session ID for correlating signaling messages. */
    private String sessionId;

    /** Flag indicating a drag operation is in progress. */
    private volatile boolean isDragging = false;

    /** Last known drag coordinates for continued drag events. */
    private float lastDragX = 0f;
    private float lastDragY = 0f;

    // =========================================================================
    // Signaling callback interface
    // =========================================================================

    /**
     * Interface that the host must implement to relay signaling messages
     * (SDP offers/answers and ICE candidates) to the remote peer via a
     * signaling server (e.g., WebSocket, HTTP).
     */
    public interface SignalingCallback {

        /**
         * Called when a local SDP answer has been created and should be sent
         * to the remote peer.
         *
         * @param sdp the session description (type + sdp string)
         */
        void onLocalSdpAnswer(SessionDescription sdp);

        /**
         * Called when a local ICE candidate has been gathered and should be
         * sent to the remote peer.
         *
         * @param candidate the ICE candidate
         */
        void onLocalIceCandidate(IceCandidate candidate);

        /**
         * Called when the peer connection has been fully established and
         * media is flowing.
         */
        void onConnectionEstablished();

        /**
         * Called when the peer connection is disconnected or failed.
         *
         * @param reason human-readable reason for the disconnection
         */
        void onConnectionDisconnected(String reason);

        /**
         * Called when a "close" command is received from the remote peer.
         */
        void onRemoteCloseRequested();

        /**
         * Called when a screenshot has been captured and is ready to send.
         *
         * @param bitmap the captured screenshot
         */
        void onScreenshotCaptured(Bitmap bitmap);
    }

    // =========================================================================
    // Constructor
    // =========================================================================

    /**
     * Creates a new WebRTCController.
     *
     * @param context           application or activity context
     * @param webView           the WebView to capture and control
     * @param signalingCallback callback for outgoing signaling messages
     */
    public WebRTCController(Context context, WebView webView, SignalingCallback signalingCallback) {
        this.context = context.getApplicationContext();
        this.webView = webView;
        this.signalingCallback = signalingCallback;

        readDisplayMetrics();
    }

    // =========================================================================
    // Public API
    // =========================================================================

    /**
     * Initializes the WebRTC stack: EGL context, PeerConnectionFactory,
     * video capturer, video source/track, and prepares the PeerConnection.
     * Must be called before handling any signaling messages.
     *
     * @param sessionId unique session identifier for this connection
     */
    public void initWebRTC(String sessionId) {
        this.sessionId = sessionId;
        Log.d(TAG, "initWebRTC: sessionId=" + sessionId);

        if (isActive) {
            Log.w(TAG, "initWebRTC called while already active; cleaning up first");
            cleanup();
        }

        try {
            initEglContext();
            initPeerConnectionFactory();
            initVideoCapturer();
            initVideoSourceAndTrack();
            createPeerConnection();
            isActive = true;
            isCleanedUp = false;
            reconnectAttempts = 0;
            Log.i(TAG, "WebRTC initialization complete");
        } catch (Exception e) {
            Log.e(TAG, "Failed to initialize WebRTC", e);
            cleanup();
        }
    }

    /**
     * Handles a remote SDP offer received via the signaling channel.
     * Creates an SDP answer and sets both remote and local descriptions.
     *
     * @param type the SDP type string ("offer")
     * @param sdp  the SDP body
     */
    public void handleRemoteOffer(String type, String sdp) {
        if (peerConnection == null) {
            Log.w(TAG, "handleRemoteOffer: peerConnection is null, ignoring");
            return;
        }

        Log.d(TAG, "handleRemoteOffer: setting remote description");
        SessionDescription remoteDesc = new SessionDescription(
                SessionDescription.Type.OFFER, sdp);

        peerConnection.setRemoteDescription(new RemoteDescriptionObserver(), remoteDesc);

        MediaConstraints answerConstraints = new MediaConstraints();
        answerConstraints.mandatory.add(
                new MediaConstraints.KeyValuePair("OfferToReceiveAudio", "false"));
        answerConstraints.mandatory.add(
                new MediaConstraints.KeyValuePair("OfferToReceiveVideo", "false"));

        peerConnection.createAnswer(new SdpAnswerObserver(), answerConstraints);
    }

    /**
     * Handles a remote ICE candidate received via the signaling channel.
     * If the remote description has not yet been set, the candidate is queued.
     *
     * @param sdpMid        the media stream identification tag
     * @param sdpMLineIndex the index of the media description
     * @param candidateSdp  the candidate SDP string
     */
    public void handleRemoteIceCandidate(String sdpMid, int sdpMLineIndex, String candidateSdp) {
        IceCandidate candidate = new IceCandidate(sdpMid, sdpMLineIndex, candidateSdp);

        if (isRemoteDescriptionSet && peerConnection != null) {
            Log.d(TAG, "Adding ICE candidate immediately");
            peerConnection.addIceCandidate(candidate);
        } else {
            Log.d(TAG, "Queueing ICE candidate (remote description not yet set)");
            synchronized (pendingIceCandidates) {
                pendingIceCandidates.add(candidate);
            }
        }
    }

    /**
     * Replaces the current WebView reference. Used when the WebView is
     * recreated (e.g., after navigation to a new page or activity restart).
     *
     * @param newWebView the new WebView instance
     */
    public void setWebView(WebView newWebView) {
        this.webView = newWebView;
        if (videoCapturer instanceof VirtualDisplayCapturer) {
            ((VirtualDisplayCapturer) videoCapturer).updateWebView(newWebView);
        }
    }

    /**
     * Releases all WebRTC resources and cancels timers. Safe to call
     * multiple times.
     */
    public void cleanup() {
        if (isCleanedUp) {
            return;
        }
        isCleanedUp = true;
        isActive = false;
        Log.i(TAG, "cleanup: releasing WebRTC resources");

        stopHeartbeat();

        if (videoCapturer != null) {
            try {
                videoCapturer.stopCapture();
            } catch (InterruptedException e) {
                Log.w(TAG, "Interrupted while stopping video capturer", e);
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                Log.w(TAG, "Error stopping video capturer", e);
            }
            videoCapturer.dispose();
            videoCapturer = null;
        }

        if (videoSource != null) {
            videoSource.dispose();
            videoSource = null;
        }

        if (videoTrack != null) {
            videoTrack.dispose();
            videoTrack = null;
        }

        if (surfaceTextureHelper != null) {
            surfaceTextureHelper.dispose();
            surfaceTextureHelper = null;
        }

        if (dataChannel != null) {
            dataChannel.close();
            dataChannel.dispose();
            dataChannel = null;
        }

        if (peerConnection != null) {
            peerConnection.close();
            peerConnection.dispose();
            peerConnection = null;
        }

        if (peerConnectionFactory != null) {
            peerConnectionFactory.dispose();
            peerConnectionFactory = null;
        }

        if (eglBaseContext != null) {
            eglBaseContext.release();
            eglBaseContext = null;
        }

        isRemoteDescriptionSet = false;
        synchronized (pendingIceCandidates) {
            pendingIceCandidates.clear();
        }

        Log.i(TAG, "cleanup: done");
    }

    /**
     * Returns whether the controller is currently active with a live connection.
     */
    public boolean isActive() {
        return isActive;
    }

    // =========================================================================
    // Initialization helpers
    // =========================================================================

    /** Reads the device display metrics for coordinate translation. */
    private void readDisplayMetrics() {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            Display display = wm.getDefaultDisplay();
            DisplayMetrics metrics = new DisplayMetrics();
            display.getRealMetrics(metrics);
            displayWidth = metrics.widthPixels;
            displayHeight = metrics.heightPixels;
            displayDensity = metrics.density;
        } else {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            displayWidth = metrics.widthPixels;
            displayHeight = metrics.heightPixels;
            displayDensity = metrics.density;
        }
        Log.d(TAG, "Display: " + displayWidth + "x" + displayHeight + " density=" + displayDensity);
    }

    /** Creates the shared EGL base context for hardware-accelerated video. */
    private void initEglContext() {
        eglBaseContext = EglBase.create();
        Log.d(TAG, "EGL base context created");
    }

    /**
     * Initializes the PeerConnectionFactory with video encoder/decoder
     * factories. Uses {@link SafeVideoDecoderFactory} for device-safe decoding.
     */
    private void initPeerConnectionFactory() {
        PeerConnectionFactory.InitializationOptions initOptions =
                PeerConnectionFactory.InitializationOptions.builder(context)
                        .setEnableInternalTracer(false)
                        .createInitializationOptions();
        PeerConnectionFactory.initialize(initOptions);

        DefaultVideoEncoderFactory encoderFactory = new DefaultVideoEncoderFactory(
                eglBaseContext.getEglBaseContext(), true, true);

        SafeVideoDecoderFactory decoderFactory = new SafeVideoDecoderFactory(
                new DefaultVideoDecoderFactory(eglBaseContext.getEglBaseContext()));

        PeerConnectionFactory.Options options = new PeerConnectionFactory.Options();

        peerConnectionFactory = PeerConnectionFactory.builder()
                .setOptions(options)
                .setVideoEncoderFactory(encoderFactory)
                .setVideoDecoderFactory(decoderFactory)
                .createPeerConnectionFactory();

        Log.d(TAG, "PeerConnectionFactory created");
    }

    /**
     * Initializes the video capturer. Tries VirtualDisplayCapturer first;
     * falls back to BitmapFrameCapturer if VirtualDisplay is not supported.
     */
    private void initVideoCapturer() {
        try {
            videoCapturer = new VirtualDisplayCapturer(context, webView,
                    displayWidth, displayHeight, displayDensity);
            Log.d(TAG, "Using VirtualDisplayCapturer");
        } catch (Exception e) {
            Log.w(TAG, "VirtualDisplayCapturer unavailable, falling back to BitmapFrameCapturer", e);
            videoCapturer = new BitmapFrameCapturer(webView);
        }
    }

    /** Creates the VideoSource from the capturer and adds a VideoTrack. */
    private void initVideoSourceAndTrack() {
        surfaceTextureHelper = SurfaceTextureHelper.create(
                "CaptureThread", eglBaseContext.getEglBaseContext());

        videoSource = peerConnectionFactory.createVideoSource(videoCapturer.isScreencast());
        videoCapturer.initialize(surfaceTextureHelper, context, videoSource.getCapturerObserver());
        videoCapturer.startCapture(streamWidth, streamHeight, VIDEO_FPS);

        videoTrack = peerConnectionFactory.createVideoTrack(VIDEO_TRACK_ID, videoSource);
        videoTrack.setEnabled(true);

        Log.d(TAG, "Video source and track initialized (" + streamWidth + "x" + streamHeight
                + " @ " + VIDEO_FPS + " fps)");
    }

    // =========================================================================
    // PeerConnection setup
    // =========================================================================

    /**
     * Creates the PeerConnection with TURN server configuration and attaches
     * the local video track and data channel.
     */
    private void createPeerConnection() {
        List<PeerConnection.IceServer> iceServers = new ArrayList<>();

        // TURN server 1
        iceServers.add(PeerConnection.IceServer.builder(TURN_SERVER_1)
                .setUsername(TURN_USERNAME)
                .setPassword(TURN_PASSWORD)
                .createIceServer());

        // TURN server 2
        iceServers.add(PeerConnection.IceServer.builder(TURN_SERVER_2)
                .setUsername(TURN_USERNAME)
                .setPassword(TURN_PASSWORD)
                .createIceServer());

        PeerConnection.RTCConfiguration rtcConfig =
                new PeerConnection.RTCConfiguration(iceServers);
        rtcConfig.sdpSemantics = PeerConnection.SdpSemantics.UNIFIED_PLAN;
        rtcConfig.continualGatheringPolicy =
                PeerConnection.ContinualGatheringPolicy.GATHER_CONTINUALLY;
        rtcConfig.tcpCandidatePolicy =
                PeerConnection.TcpCandidatePolicy.ENABLED;
        rtcConfig.bundlePolicy = PeerConnection.BundlePolicy.MAXBUNDLE;
        rtcConfig.rtcpMuxPolicy = PeerConnection.RtcpMuxPolicy.REQUIRE;
        rtcConfig.keyType = PeerConnection.KeyType.ECDSA;

        peerConnection = peerConnectionFactory.createPeerConnection(
                rtcConfig, new PeerConnectionObserver());

        if (peerConnection == null) {
            Log.e(TAG, "Failed to create PeerConnection");
            return;
        }

        // Add local video track to peer connection
        if (videoTrack != null) {
            MediaStream localStream = peerConnectionFactory.createLocalMediaStream(MEDIA_STREAM_ID);
            localStream.addTrack(videoTrack);
            peerConnection.addTrack(videoTrack, List.of(MEDIA_STREAM_ID));
        }

        // Create the data channel for control commands
        DataChannel.Init dcInit = new DataChannel.Init();
        dcInit.ordered = true;
        dcInit.negotiated = false;
        dataChannel = peerConnection.createDataChannel(DATA_CHANNEL_LABEL, dcInit);
        dataChannel.registerObserver(new DataChannelObserver());

        Log.d(TAG, "PeerConnection created with TURN servers and data channel");
    }

    // =========================================================================
    // Heartbeat
    // =========================================================================

    /** Starts the periodic heartbeat timer that sends ping messages. */
    private void startHeartbeat() {
        stopHeartbeat();
        lastPongTimestamp = System.currentTimeMillis();

        heartbeatTimer = new Timer("WebRTC-Heartbeat", true);
        heartbeatTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!isActive) {
                    cancel();
                    return;
                }
                sendHeartbeatPing();
                checkHeartbeatTimeout();
            }
        }, HEARTBEAT_INTERVAL_MS, HEARTBEAT_INTERVAL_MS);

        Log.d(TAG, "Heartbeat started (interval=" + HEARTBEAT_INTERVAL_MS + "ms)");
    }

    /** Stops the heartbeat timer. */
    private void stopHeartbeat() {
        if (heartbeatTimer != null) {
            heartbeatTimer.cancel();
            heartbeatTimer.purge();
            heartbeatTimer = null;
        }
    }

    /** Sends a ping message over the data channel. */
    private void sendHeartbeatPing() {
        try {
            JSONObject pingMsg = new JSONObject();
            pingMsg.put("action", ACTION_PING);
            pingMsg.put("timestamp", System.currentTimeMillis());
            sendDataChannelMessage(pingMsg.toString());
        } catch (JSONException e) {
            Log.w(TAG, "Failed to create ping message", e);
        }
    }

    /**
     * Checks whether the last pong was received within the timeout window.
     * If not, triggers reconnection logic.
     */
    private void checkHeartbeatTimeout() {
        long elapsed = System.currentTimeMillis() - lastPongTimestamp;
        if (elapsed > HEARTBEAT_TIMEOUT_MS) {
            Log.w(TAG, "Heartbeat timeout (" + elapsed + "ms since last pong)");
            handleConnectionLost("Heartbeat timeout");
        }
    }

    // =========================================================================
    // DataChannel messaging
    // =========================================================================

    /**
     * Sends a UTF-8 string message over the data channel.
     *
     * @param message the message to send
     */
    private void sendDataChannelMessage(String message) {
        if (dataChannel == null || dataChannel.state() != DataChannel.State.OPEN) {
            Log.w(TAG, "sendDataChannelMessage: data channel not open, dropping message");
            return;
        }
        try {
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));
            DataChannel.Buffer dcBuffer = new DataChannel.Buffer(buffer, false);
            boolean success = dataChannel.send(dcBuffer);
            if (!success) {
                Log.w(TAG, "sendDataChannelMessage: send returned false");
            }
        } catch (Exception e) {
            Log.e(TAG, "sendDataChannelMessage: error", e);
        }
    }

    /**
     * Processes a control message received from the remote peer via the data channel.
     * Dispatches to the appropriate handler based on the "action" field.
     *
     * @param message raw JSON string received
     */
    private void handleControlMessage(String message) {
        try {
            JSONObject json = new JSONObject(message);
            String action = json.optString("action", "");

            switch (action) {
                case ACTION_CLICK:
                    handleClick(json);
                    break;
                case ACTION_DRAG:
                    handleDrag(json);
                    break;
                case ACTION_DRAG_START:
                    handleDragStart(json);
                    break;
                case ACTION_DRAG_END:
                    handleDragEnd(json);
                    break;
                case ACTION_SCROLL:
                    handleScroll(json);
                    break;
                case ACTION_PASTE:
                    handlePaste(json);
                    break;
                case ACTION_KEY_INPUT:
                    handleKeyInput(json);
                    break;
                case ACTION_GO_BACK:
                    handleGoBack();
                    break;
                case ACTION_CLOSE:
                    handleClose();
                    break;
                case ACTION_PONG:
                    handlePong(json);
                    break;
                case ACTION_PING:
                    handlePing(json);
                    break;
                case ACTION_SCREENSHOT:
                    handleScreenshot();
                    break;
                default:
                    Log.w(TAG, "Unknown control action: " + action);
                    break;
            }
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse control message: " + message, e);
        }
    }

    // =========================================================================
    // Control command handlers
    // =========================================================================

    /**
     * Dispatches a tap/click event at the specified coordinates.
     * Coordinates in the JSON are normalized (0..1) relative to the stream
     * resolution and must be scaled to actual display coordinates.
     *
     * @param json JSON with "x" and "y" fields (normalized 0..1)
     */
    private void handleClick(JSONObject json) {
        double normX = json.optDouble("x", -1);
        double normY = json.optDouble("y", -1);
        if (normX < 0 || normY < 0) {
            Log.w(TAG, "handleClick: invalid coordinates");
            return;
        }

        float x = (float) (normX * displayWidth);
        float y = (float) (normY * displayHeight);

        Log.d(TAG, "handleClick: (" + x + ", " + y + ")");

        mainHandler.post(() -> dispatchClick(x, y));
    }

    /**
     * Injects a tap event (ACTION_DOWN followed by ACTION_UP) at the given
     * screen coordinates into the WebView.
     */
    private void dispatchClick(float x, float y) {
        if (webView == null) return;

        long downTime = SystemClock.uptimeMillis();
        long eventTime = downTime;

        MotionEvent downEvent = MotionEvent.obtain(
                downTime, eventTime, MotionEvent.ACTION_DOWN, x, y, 0);
        downEvent.setSource(InputDevice.SOURCE_TOUCHSCREEN);
        webView.dispatchTouchEvent(downEvent);
        downEvent.recycle();

        eventTime = downTime + 50;
        MotionEvent upEvent = MotionEvent.obtain(
                downTime, eventTime, MotionEvent.ACTION_UP, x, y, 0);
        upEvent.setSource(InputDevice.SOURCE_TOUCHSCREEN);
        webView.dispatchTouchEvent(upEvent);
        upEvent.recycle();
    }

    /**
     * Handles a drag/move event (finger moving while pressed).
     *
     * @param json JSON with "x" and "y" fields (normalized)
     */
    private void handleDrag(JSONObject json) {
        double normX = json.optDouble("x", -1);
        double normY = json.optDouble("y", -1);
        if (normX < 0 || normY < 0) return;

        float x = (float) (normX * displayWidth);
        float y = (float) (normY * displayHeight);

        mainHandler.post(() -> {
            if (webView == null || !isDragging) return;

            long now = SystemClock.uptimeMillis();
            MotionEvent moveEvent = MotionEvent.obtain(
                    now, now, MotionEvent.ACTION_MOVE, x, y, 0);
            moveEvent.setSource(InputDevice.SOURCE_TOUCHSCREEN);
            webView.dispatchTouchEvent(moveEvent);
            moveEvent.recycle();

            lastDragX = x;
            lastDragY = y;
        });
    }

    /**
     * Handles the start of a drag gesture (finger down).
     *
     * @param json JSON with "x" and "y" fields (normalized)
     */
    private void handleDragStart(JSONObject json) {
        double normX = json.optDouble("x", -1);
        double normY = json.optDouble("y", -1);
        if (normX < 0 || normY < 0) return;

        float x = (float) (normX * displayWidth);
        float y = (float) (normY * displayHeight);

        mainHandler.post(() -> {
            if (webView == null) return;

            isDragging = true;
            lastDragX = x;
            lastDragY = y;

            long now = SystemClock.uptimeMillis();
            MotionEvent downEvent = MotionEvent.obtain(
                    now, now, MotionEvent.ACTION_DOWN, x, y, 0);
            downEvent.setSource(InputDevice.SOURCE_TOUCHSCREEN);
            webView.dispatchTouchEvent(downEvent);
            downEvent.recycle();
        });
    }

    /**
     * Handles the end of a drag gesture (finger up).
     *
     * @param json JSON with "x" and "y" fields (normalized)
     */
    private void handleDragEnd(JSONObject json) {
        double normX = json.optDouble("x", -1);
        double normY = json.optDouble("y", -1);

        float x = (normX >= 0) ? (float) (normX * displayWidth) : lastDragX;
        float y = (normY >= 0) ? (float) (normY * displayHeight) : lastDragY;

        mainHandler.post(() -> {
            if (webView == null) return;

            isDragging = false;

            long now = SystemClock.uptimeMillis();
            MotionEvent upEvent = MotionEvent.obtain(
                    now, now, MotionEvent.ACTION_UP, x, y, 0);
            upEvent.setSource(InputDevice.SOURCE_TOUCHSCREEN);
            webView.dispatchTouchEvent(upEvent);
            upEvent.recycle();
        });
    }

    /**
     * Dispatches a scroll event at the specified coordinates.
     *
     * @param json JSON with "x", "y" (position), "deltaX", "deltaY" (scroll amount)
     */
    private void handleScroll(JSONObject json) {
        double normX = json.optDouble("x", 0.5);
        double normY = json.optDouble("y", 0.5);
        double deltaX = json.optDouble("deltaX", 0);
        double deltaY = json.optDouble("deltaY", 0);

        float x = (float) (normX * displayWidth);
        float y = (float) (normY * displayHeight);
        int scrollX = (int) (deltaX * displayDensity);
        int scrollY = (int) (deltaY * displayDensity);

        Log.d(TAG, "handleScroll: (" + x + ", " + y + ") delta=(" + scrollX + ", " + scrollY + ")");

        mainHandler.post(() -> dispatchScroll(x, y, scrollX, scrollY));
    }

    /**
     * Injects a scroll gesture at the given screen position.
     * Simulates a short drag in the scroll direction.
     */
    private void dispatchScroll(float x, float y, int scrollX, int scrollY) {
        if (webView == null) return;

        long downTime = SystemClock.uptimeMillis();

        MotionEvent downEvent = MotionEvent.obtain(
                downTime, downTime, MotionEvent.ACTION_DOWN, x, y, 0);
        downEvent.setSource(InputDevice.SOURCE_TOUCHSCREEN);
        webView.dispatchTouchEvent(downEvent);
        downEvent.recycle();

        // Simulate scroll by a series of move events
        int steps = 5;
        float stepX = (float) scrollX / steps;
        float stepY = (float) scrollY / steps;
        float currentX = x;
        float currentY = y;

        for (int i = 1; i <= steps; i++) {
            currentX -= stepX;
            currentY -= stepY;
            long moveTime = downTime + (i * 16L); // ~60fps timing
            MotionEvent moveEvent = MotionEvent.obtain(
                    downTime, moveTime, MotionEvent.ACTION_MOVE, currentX, currentY, 0);
            moveEvent.setSource(InputDevice.SOURCE_TOUCHSCREEN);
            webView.dispatchTouchEvent(moveEvent);
            moveEvent.recycle();
        }

        long upTime = downTime + ((steps + 1) * 16L);
        MotionEvent upEvent = MotionEvent.obtain(
                downTime, upTime, MotionEvent.ACTION_UP, currentX, currentY, 0);
        upEvent.setSource(InputDevice.SOURCE_TOUCHSCREEN);
        webView.dispatchTouchEvent(upEvent);
        upEvent.recycle();
    }

    /**
     * Handles a paste command by injecting text into the currently focused
     * element in the WebView via JavaScript.
     *
     * @param json JSON with "text" field containing the text to paste
     */
    private void handlePaste(JSONObject json) {
        String text = json.optString("text", "");
        if (text.isEmpty()) {
            Log.w(TAG, "handlePaste: empty text");
            return;
        }

        Log.d(TAG, "handlePaste: injecting text of length " + text.length());
        mainHandler.post(() -> injectText(text));
    }

    /**
     * Injects text into the currently focused input element in the WebView.
     * Uses a JavaScript snippet that:
     * <ol>
     *   <li>Finds the active element (document.activeElement)</li>
     *   <li>Sets its value property</li>
     *   <li>Dispatches 'input' and 'change' events to trigger framework bindings</li>
     *   <li>For contentEditable elements, uses document.execCommand('insertText')</li>
     * </ol>
     *
     * @param text the text to inject
     */
    private void injectText(String text) {
        if (webView == null) return;

        // Escape the text for safe inclusion in JavaScript
        String escapedText = text
                .replace("\\", "\\\\")
                .replace("'", "\\'")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");

        String javascript = "(function() {" +
                "  var el = document.activeElement;" +
                "  if (!el) return 'no_active_element';" +
                "  if (el.tagName === 'INPUT' || el.tagName === 'TEXTAREA') {" +
                "    var nativeSetter = Object.getOwnPropertyDescriptor(" +
                "      window.HTMLInputElement.prototype, 'value');" +
                "    if (el.tagName === 'TEXTAREA') {" +
                "      nativeSetter = Object.getOwnPropertyDescriptor(" +
                "        window.HTMLTextAreaElement.prototype, 'value');" +
                "    }" +
                "    if (nativeSetter && nativeSetter.set) {" +
                "      nativeSetter.set.call(el, '" + escapedText + "');" +
                "    } else {" +
                "      el.value = '" + escapedText + "';" +
                "    }" +
                "    el.dispatchEvent(new Event('input', { bubbles: true }));" +
                "    el.dispatchEvent(new Event('change', { bubbles: true }));" +
                "    return 'value_set';" +
                "  } else if (el.isContentEditable) {" +
                "    document.execCommand('insertText', false, '" + escapedText + "');" +
                "    return 'content_editable';" +
                "  } else {" +
                "    return 'unsupported_element:' + el.tagName;" +
                "  }" +
                "})();";

        webView.evaluateJavascript(javascript, value ->
                Log.d(TAG, "injectText result: " + value));
    }

    /**
     * Handles a key input command (single character or special key).
     *
     * @param json JSON with "key" field (key name) and optional "code" field
     */
    private void handleKeyInput(JSONObject json) {
        String key = json.optString("key", "");
        int keyCode = json.optInt("code", -1);

        if (key.isEmpty() && keyCode < 0) {
            Log.w(TAG, "handleKeyInput: no key or keyCode specified");
            return;
        }

        Log.d(TAG, "handleKeyInput: key=" + key + " code=" + keyCode);

        mainHandler.post(() -> {
            if (webView == null) return;

            if ("Backspace".equals(key) || keyCode == 8) {
                // Dispatch backspace key via JavaScript
                String js = "(function() {" +
                        "  var el = document.activeElement;" +
                        "  if (el && (el.tagName === 'INPUT' || el.tagName === 'TEXTAREA')) {" +
                        "    var start = el.selectionStart;" +
                        "    var end = el.selectionEnd;" +
                        "    if (start === end && start > 0) {" +
                        "      el.value = el.value.substring(0, start - 1) + el.value.substring(end);" +
                        "      el.selectionStart = el.selectionEnd = start - 1;" +
                        "    } else if (start !== end) {" +
                        "      el.value = el.value.substring(0, start) + el.value.substring(end);" +
                        "      el.selectionStart = el.selectionEnd = start;" +
                        "    }" +
                        "    el.dispatchEvent(new Event('input', { bubbles: true }));" +
                        "  } else if (el && el.isContentEditable) {" +
                        "    document.execCommand('delete', false, null);" +
                        "  }" +
                        "})();";
                webView.evaluateJavascript(js, null);
            } else if ("Enter".equals(key) || keyCode == 13) {
                // Submit or newline depending on context
                String js = "(function() {" +
                        "  var el = document.activeElement;" +
                        "  if (el && el.tagName === 'TEXTAREA') {" +
                        "    document.execCommand('insertText', false, '\\n');" +
                        "  } else if (el && el.tagName === 'INPUT') {" +
                        "    var form = el.closest('form');" +
                        "    if (form) { form.submit(); }" +
                        "  } else if (el && el.isContentEditable) {" +
                        "    document.execCommand('insertText', false, '\\n');" +
                        "  }" +
                        "})();";
                webView.evaluateJavascript(js, null);
            } else if (key.length() == 1) {
                // Single character input
                injectText(key);
            }
        });
    }

    /**
     * Handles the "goBack" command by navigating the WebView back.
     */
    private void handleGoBack() {
        Log.d(TAG, "handleGoBack");
        mainHandler.post(() -> {
            if (webView != null && webView.canGoBack()) {
                webView.goBack();
            }
        });
    }

    /**
     * Handles the "close" command by notifying the callback and cleaning up.
     */
    private void handleClose() {
        Log.i(TAG, "handleClose: remote requested close");
        mainHandler.post(() -> {
            if (signalingCallback != null) {
                signalingCallback.onRemoteCloseRequested();
            }
            cleanup();
        });
    }

    /** Handles a pong response from the remote peer (heartbeat reply). */
    private void handlePong(JSONObject json) {
        lastPongTimestamp = System.currentTimeMillis();
    }

    /** Handles a ping from the remote peer by replying with a pong. */
    private void handlePing(JSONObject json) {
        try {
            JSONObject pongMsg = new JSONObject();
            pongMsg.put("action", ACTION_PONG);
            pongMsg.put("timestamp", System.currentTimeMillis());
            sendDataChannelMessage(pongMsg.toString());
        } catch (JSONException e) {
            Log.w(TAG, "Failed to create pong message", e);
        }
    }

    /** Handles a screenshot request by capturing the current WebView state. */
    private void handleScreenshot() {
        Log.d(TAG, "handleScreenshot");
        if (videoCapturer instanceof VirtualDisplayCapturer) {
            ((VirtualDisplayCapturer) videoCapturer).captureScreenshot(bitmap -> {
                if (bitmap != null && signalingCallback != null) {
                    signalingCallback.onScreenshotCaptured(bitmap);
                }
            });
        }
    }

    // =========================================================================
    // Reconnection logic
    // =========================================================================

    /**
     * Called when the connection is lost. Attempts reconnection up to
     * {@link #MAX_RECONNECT_ATTEMPTS} times with a delay between attempts.
     *
     * @param reason description of why the connection was lost
     */
    private void handleConnectionLost(String reason) {
        if (!isActive) return;

        Log.w(TAG, "Connection lost: " + reason);

        if (reconnectAttempts >= MAX_RECONNECT_ATTEMPTS) {
            Log.e(TAG, "Max reconnect attempts reached, giving up");
            isActive = false;
            if (signalingCallback != null) {
                mainHandler.post(() ->
                        signalingCallback.onConnectionDisconnected(
                                "Max reconnect attempts reached: " + reason));
            }
            return;
        }

        if (!isNetworkAvailable()) {
            Log.w(TAG, "Network not available, cannot reconnect");
            isActive = false;
            if (signalingCallback != null) {
                mainHandler.post(() ->
                        signalingCallback.onConnectionDisconnected("Network unavailable"));
            }
            return;
        }

        reconnectAttempts++;
        Log.i(TAG, "Scheduling reconnection attempt " + reconnectAttempts
                + "/" + MAX_RECONNECT_ATTEMPTS + " in " + RECONNECT_DELAY_MS + "ms");

        mainHandler.postDelayed(() -> {
            if (isActive && signalingCallback != null) {
                signalingCallback.onConnectionDisconnected("Reconnecting: " + reason);
            }
        }, RECONNECT_DELAY_MS);
    }

    /**
     * Checks whether the device currently has network connectivity.
     *
     * @return true if a network connection is available
     */
    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) return false;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    /**
     * Drains any ICE candidates that were received before the remote
     * description was set.
     */
    private void drainPendingIceCandidates() {
        synchronized (pendingIceCandidates) {
            if (peerConnection != null && !pendingIceCandidates.isEmpty()) {
                Log.d(TAG, "Draining " + pendingIceCandidates.size() + " pending ICE candidates");
                for (IceCandidate candidate : pendingIceCandidates) {
                    peerConnection.addIceCandidate(candidate);
                }
                pendingIceCandidates.clear();
            }
        }
    }

    // =========================================================================
    // Inner class: SdpAnswerObserver
    // =========================================================================

    /**
     * Observer for the SDP answer creation process. When the answer is
     * successfully created, it sets the local description and sends the
     * answer to the remote peer via the signaling callback.
     */
    private class SdpAnswerObserver implements SdpObserver {

        @Override
        public void onCreateSuccess(SessionDescription sdp) {
            Log.d(TAG, "SDP answer created successfully");
            if (peerConnection != null) {
                peerConnection.setLocalDescription(new LocalDescriptionObserver(), sdp);
            }
            if (signalingCallback != null) {
                signalingCallback.onLocalSdpAnswer(sdp);
            }
        }

        @Override
        public void onSetSuccess() {
            // Not used for answer creation
        }

        @Override
        public void onCreateFailure(String error) {
            Log.e(TAG, "SDP answer creation failed: " + error);
        }

        @Override
        public void onSetFailure(String error) {
            // Not used for answer creation
        }
    }

    // =========================================================================
    // Inner class: LocalDescriptionObserver
    // =========================================================================

    /**
     * Observer for setting the local SDP description. Logs success or failure.
     */
    private class LocalDescriptionObserver implements SdpObserver {

        @Override
        public void onCreateSuccess(SessionDescription sdp) {
            // Not used for set operations
        }

        @Override
        public void onSetSuccess() {
            Log.d(TAG, "Local description set successfully");
        }

        @Override
        public void onCreateFailure(String error) {
            // Not used for set operations
        }

        @Override
        public void onSetFailure(String error) {
            Log.e(TAG, "Failed to set local description: " + error);
        }
    }

    // =========================================================================
    // Inner class: RemoteDescriptionObserver
    // =========================================================================

    /**
     * Observer for setting the remote SDP description. On success, drains
     * any pending ICE candidates that arrived before the remote description
     * was ready.
     */
    private class RemoteDescriptionObserver implements SdpObserver {

        @Override
        public void onCreateSuccess(SessionDescription sdp) {
            // Not used for set operations
        }

        @Override
        public void onSetSuccess() {
            Log.d(TAG, "Remote description set successfully");
            isRemoteDescriptionSet = true;
            drainPendingIceCandidates();
        }

        @Override
        public void onCreateFailure(String error) {
            // Not used for set operations
        }

        @Override
        public void onSetFailure(String error) {
            Log.e(TAG, "Failed to set remote description: " + error);
        }
    }

    // =========================================================================
    // Inner class: DataChannelObserver
    // =========================================================================

    /**
     * Observer for data channel events. Processes incoming control messages
     * and manages the heartbeat lifecycle.
     */
    private class DataChannelObserver implements DataChannel.Observer {

        @Override
        public void onBufferedAmountChange(long previousAmount) {
            // No-op: flow control not needed for low-frequency control messages
        }

        @Override
        public void onStateChange() {
            if (dataChannel == null) return;

            DataChannel.State state = dataChannel.state();
            Log.d(TAG, "DataChannel state changed: " + state);

            switch (state) {
                case OPEN:
                    Log.i(TAG, "DataChannel OPEN, starting heartbeat");
                    startHeartbeat();
                    break;
                case CLOSED:
                    Log.i(TAG, "DataChannel CLOSED");
                    stopHeartbeat();
                    break;
                case CLOSING:
                    Log.d(TAG, "DataChannel CLOSING");
                    break;
                case CONNECTING:
                    Log.d(TAG, "DataChannel CONNECTING");
                    break;
            }
        }

        @Override
        public void onMessage(DataChannel.Buffer buffer) {
            if (buffer == null || buffer.data == null) return;

            ByteBuffer data = buffer.data;
            byte[] bytes = new byte[data.remaining()];
            data.get(bytes);

            String message = new String(bytes, StandardCharsets.UTF_8);
            Log.d(TAG, "DataChannel message received: "
                    + (message.length() > 200 ? message.substring(0, 200) + "..." : message));

            // Process on background thread to avoid blocking the data channel
            executor.execute(() -> handleControlMessage(message));
        }
    }

    // =========================================================================
    // Inner class: PeerConnectionObserver
    // =========================================================================

    /**
     * Observer for PeerConnection events: ICE state changes, connectivity
     * changes, and ICE candidate gathering.
     */
    private class PeerConnectionObserver implements PeerConnection.Observer {

        @Override
        public void onSignalingChange(PeerConnection.SignalingState signalingState) {
            Log.d(TAG, "Signaling state: " + signalingState);
        }

        @Override
        public void onIceConnectionChange(PeerConnection.IceConnectionState iceConnectionState) {
            Log.d(TAG, "ICE connection state: " + iceConnectionState);

            switch (iceConnectionState) {
                case CONNECTED:
                    Log.i(TAG, "ICE CONNECTED");
                    reconnectAttempts = 0;
                    if (signalingCallback != null) {
                        mainHandler.post(signalingCallback::onConnectionEstablished);
                    }
                    break;
                case COMPLETED:
                    Log.i(TAG, "ICE COMPLETED (all candidates checked)");
                    break;
                case DISCONNECTED:
                    Log.w(TAG, "ICE DISCONNECTED");
                    handleConnectionLost("ICE disconnected");
                    break;
                case FAILED:
                    Log.e(TAG, "ICE FAILED");
                    handleConnectionLost("ICE failed");
                    break;
                case CLOSED:
                    Log.i(TAG, "ICE CLOSED");
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onIceConnectionReceivingChange(boolean receiving) {
            Log.d(TAG, "ICE receiving change: " + receiving);
        }

        @Override
        public void onIceGatheringChange(PeerConnection.IceGatheringState state) {
            Log.d(TAG, "ICE gathering state: " + state);
        }

        @Override
        public void onIceCandidate(IceCandidate candidate) {
            Log.d(TAG, "Local ICE candidate: " + candidate.sdpMid
                    + " line=" + candidate.sdpMLineIndex);
            if (signalingCallback != null) {
                signalingCallback.onLocalIceCandidate(candidate);
            }
        }

        @Override
        public void onIceCandidatesRemoved(IceCandidate[] candidates) {
            Log.d(TAG, "ICE candidates removed: " + candidates.length);
        }

        @Override
        public void onAddStream(MediaStream stream) {
            Log.d(TAG, "Remote stream added: " + stream.getId());
        }

        @Override
        public void onRemoveStream(MediaStream stream) {
            Log.d(TAG, "Remote stream removed: " + stream.getId());
        }

        @Override
        public void onDataChannel(DataChannel dc) {
            Log.d(TAG, "Remote data channel opened: " + dc.label());
            // If the remote peer created the data channel, use it instead
            if (dataChannel != null) {
                dataChannel.close();
                dataChannel.dispose();
            }
            dataChannel = dc;
            dataChannel.registerObserver(new DataChannelObserver());
        }

        @Override
        public void onRenegotiationNeeded() {
            Log.d(TAG, "Renegotiation needed");
        }

        @Override
        public void onAddTrack(RtpReceiver receiver, MediaStream[] mediaStreams) {
            Log.d(TAG, "Track added from remote peer");
        }
    }
}

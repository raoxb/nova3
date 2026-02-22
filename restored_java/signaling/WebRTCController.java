package signaling;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;
import org.webrtc.CandidatePairChangeEvent;
import org.webrtc.CapturerObserver;
import org.webrtc.DataChannel;
import org.webrtc.DefaultVideoDecoderFactory;
import org.webrtc.DefaultVideoEncoderFactory;
import org.webrtc.EglBase;
import org.webrtc.IceCandidate;
import org.webrtc.IceCandidateErrorEvent;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.MediaStreamTrack;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.RtpReceiver;
import org.webrtc.RtpSender;
import org.webrtc.RtpTransceiver;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;

/**
 * WebRTCController — WebRTC 远程控制器
 *
 * Original: llIIIIlIlllIII1.IllIIlIIII1
 *
 * Full WebRTC controller that implements remote control of the device's WebView.
 * Handles PeerConnection lifecycle, DataChannel-based command processing,
 * video streaming (screen capture), and touch/keyboard event simulation.
 *
 * Features:
 *   - PeerConnectionFactory init with native library loading fallback
 *   - EglBase context creation via 3-fallback reflection strategies
 *   - Video capture from screen at 15fps
 *   - DataChannel messaging for remote commands
 *   - Touch event simulation (click, drag, scroll, release)
 *   - Keyboard event dispatching
 *   - JavaScript injection for text input/paste
 *   - Connection health monitoring with heartbeat
 *   - ICE candidate handling with STUN/TURN servers
 *
 * Remote Control Commands (via DataChannel):
 *   - "click"    → tap at (x, y)
 *   - "drag"     → begin drag at (x, y)
 *   - "dragEnd"  → end drag at (x, y)
 *   - "scroll"   → scroll/move to (x, y) with velocity
 *   - "done"     → touch done
 *   - "paste"    → paste text at location
 *   - "input"    → type text at position
 *   - "inputAt"  → type text at specific coordinates
 *   - "keyInput" → send key events
 *   - "goBack"   → navigate back
 *   - "close"    → close/disconnect
 *   - "release"  → release touch
 *
 * STUN/TURN Servers:
 *   - turn:101.36.120.3:3478   (user: wumitech, pass: wumitech.com@123)
 *   - turn:106.75.153.105:3478 (user: wumitech, pass: wumitech.com@123)
 *
 * ┌──────────────────────────────────────────────────────────────────────┐
 * │ Constants                                                            │
 * ├──────────────────────────────────────────────────────────────────────┤
 * │ LOG_TAG             = "control"                                      │
 * │ SCROLL_THRESHOLD    = 50 (pixels)                                    │
 * │ TOUCH_STATE_IDLE    = 0 (llIIllIl1)                                  │
 * │ TOUCH_STATE_DOWN    = 1 (lllIlIIIlI1 = SCROLLING_ACTIVE)            │
 * │ TOUCH_STATE_MOVE    = 2 (lIlllIIIII1 = SCROLLING)                   │
 * │ CAPTURE_FPS         = 15                                             │
 * │ PING_INTERVAL       = configured from config constants               │
 * │ MAX_MISSED_PINGS    = 10                                             │
 * └──────────────────────────────────────────────────────────────────────┘
 */
public final class WebRTCController implements SignalingEventListener {

    // =========================================================================
    // Constants
    // =========================================================================

    /** Log tag — "control" */
    public static final String LOG_TAG = "control";   /* lIIlIIIllII1 */

    /** Scroll distance threshold before dispatching a move event (pixels) */
    public final int SCROLL_THRESHOLD;                 /* f551IIIlIllIlI1 = 50 */

    // Touch state constants
    public static final int STATE_IDLE = 0;            /* IllllIl1 */
    public static final int STATE_DOWN = 1;            /* IIllllIll1 */
    public static final int STATE_MOVE = 2;            /* IIIllllllIIl1 */
    public static final int STATE_CANCEL = 3;          /* IIlIlIIIllIIl1 */
    public static final int CAPTURE_FPS = 0;           /* lIlIlIlIIl1 — unused/config */

    // =========================================================================
    // Fields
    // =========================================================================

    /** Scheduled executor for periodic tasks (ping, health check) */
    public final ScheduledExecutorService scheduledExecutor;     /* f552IIlIIllll1 */

    /** EGL base context for video encoding */
    public EglBase.Context eglBaseContext;                        /* f553IIlIllIIll1 */

    /** Missed ping counter */
    public int missedPingCount;                                   /* IIlIlllllllI1 */

    /** Handler on main looper for UI operations */
    public final Handler mainHandler;                             /* f554IIlllllIlll1 */

    /** Connection duration / keepalive timer */
    public final long keepAliveInterval;                          /* IlIIIIIlll1 */

    /** Last touch event time (for debouncing) */
    public long lastTouchTime;                                    /* f555IlIIIIllllIlI1 */

    /** Health check scheduled future */
    public ScheduledFuture<?> healthCheckFuture;                  /* f556IlIIIlIlIlIII1 */

    /** Whether a scroll/drag is in progress */
    public boolean isDragging;                                    /* f557IlIIlllllI1 */

    /** Scroll start timestamp */
    public long scrollStartTime;                                  /* f558IlIlIIIlIlIlll1 */

    /** Video track for screen streaming */
    public VideoTrack videoTrack;                                 /* f559IlIlIIlIII1 */

    /** Flag: whether video is ready to stream */
    public final AtomicBoolean isVideoReady;                     /* f560IlIllIlllIllI1 */

    /** WebRTC data channel for command exchange */
    public DataChannel dataChannel;                               /* f561IlIlllIIlI1 */

    /** The WebView being remotely controlled */
    public final WebView webView;                                 /* f562IlIllll1 */

    /** The peer connection */
    public PeerConnection peerConnection;                         /* f563IllIIlIIII1 */

    /** Last X position during drag */
    public float lastDragX;                                       /* f564IllIlIllll1 */

    /** Current touch state (IDLE/DOWN/MOVE/CANCEL) */
    public int touchState;                                        /* f565IllllIllllll1 */

    /** Signaling task holder reference */
    public final Object /*lIllIlIll1.llllIIIIll1*/ taskHolder;  /* f566lIIIIlllllIlll1 */

    /** Connection established time */
    public long connectionEstablishedTime;                        /* lIIIllllllIIII1 */

    /** Cumulative scroll distance */
    public float scrollDistance;                                   /* f567lIIlIIIIlIlII1 */

    /** Last Y position during drag */
    public float lastDragY;                                       /* f568lIIlllIIIlllII1 */

    /** Ping scheduled future */
    public ScheduledFuture<?> pingFuture;                         /* lIlIIIllll1 */

    /** Connection timer scheduled future */
    public ScheduledFuture<?> connectionTimerFuture;              /* lIlIlIlI1 */

    /** Screen capturer for video streaming */
    public Object /*llIIIIlIlllIII1.llllIllIl1*/ screenCapturer; /* f569lIllIIIlIl1 */

    /** Scroll origin Y */
    public float scrollOriginY;                                    /* f570lIllIlIll1 */

    /** Touch state value for SCROLLING */
    public final int TOUCH_STATE_SCROLLING;                        /* f571lIlllIIIII1 = 2 */

    /** Scroll origin X */
    public float scrollOriginX;                                    /* f572llIIIIlIlllIII1 */

    /** Max missed pings before disconnect */
    public final int maxMissedPings;                               /* llIIIlIIIlIII1 = 10 */

    /** Touch state value for IDLE */
    public final int TOUCH_STATE_IDLE;                             /* f573llIIllIl1 = 0 */

    /** Flag: whether connection is destroyed */
    public final AtomicBoolean isDestroyed;                        /* f574llIlIIlll1 */

    /** Connection timeout duration (ms) */
    public final long connectionTimeout;                           /* llIllllIlI1 */

    /** Touch state value for SCROLL_START */
    public final int TOUCH_STATE_SCROLL_START;                     /* f575lllIlIIIlI1 = 1 */

    /** Flag: whether cleanup has been performed */
    public final AtomicBoolean isCleanedUp;                        /* f576lllIlIlllI1 */

    /** Session/offer ID */
    public final String sessionId;                                 /* f577llllIIIIll1 */

    /** PeerConnection factory */
    public PeerConnectionFactory peerConnectionFactory;            /* f578llllIllIl1 */

    /** Buffered ICE candidates (before remote description set) */
    public final CopyOnWriteArrayList<IceCandidate> pendingIceCandidates; /* f579lllllIllIl1 */

    /** Video source for screen capture */
    public VideoSource videoSource;                                /* f580llllllIlIIIlll1 */

    // =========================================================================
    // Constructor
    // =========================================================================

    /**
     * Creates a WebRTCController for remote control of the given WebView.
     *
     * Original: IllIIlIIII1(String str, lIllIlIll1.llllIIIIll1 taskHolder)
     *
     * @param sessionId  the session/offer ID
     * @param taskHolder the signaling task holder with WebView reference
     */
    public WebRTCController(String sessionId, Object /*lIllIlIll1.llllIIIIll1*/ taskHolder) {
        this.sessionId = sessionId;
        this.taskHolder = taskHolder;
        this.isVideoReady = new AtomicBoolean(false);
        // webView = taskHolder.webView
        this.webView = null; // placeholder — set from taskHolder.f508llllIIIIll1
        this.pendingIceCandidates = new CopyOnWriteArrayList<>();
        this.SCROLL_THRESHOLD = 50;
        this.TOUCH_STATE_SCROLL_START = 1;
        this.TOUCH_STATE_SCROLLING = 2;
        this.TOUCH_STATE_IDLE = 0;
        this.touchState = this.TOUCH_STATE_IDLE;
        this.isCleanedUp = new AtomicBoolean(false);
        this.isDestroyed = new AtomicBoolean(false);
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.scheduledExecutor = new ScheduledThreadPoolExecutor(1);
        this.keepAliveInterval = 0; // from config: llllllIlIIIlll1.llllIIIIll1.f745llllllIlIIIlll1
        this.maxMissedPings = 10;
        this.connectionTimeout = 0; // from config: IlIlIIlIII1.IllIIlIIII1.f164IlIIlllllI1
        this.connectionEstablishedTime = SystemClock.elapsedRealtime();

        // Register with SignalingConnection
        // signalingConnection.registerWebRTCController(this);

        enumerateCodecsAndInit();
        startConnectionHealthMonitor();
        startPingScheduler();
    }

    // =========================================================================
    // Logging Helper
    // =========================================================================

    /**
     * Returns the LOG_TAG for logging.
     * Original: IlIllll1() — returns LOG_TAG
     */
    public final String getLogTag() {
        return LOG_TAG;
    }

    // =========================================================================
    // PeerConnectionFactory Initialization
    // =========================================================================

    /**
     * Initializes PeerConnectionFactory with native library loading fallback.
     *
     * Original: IIlIllIIll1(IllIIlIIII1)
     *
     * Flow:
     *   1. Try PeerConnectionFactory.initialize() with app context
     *   2. On UnsatisfiedLinkError: manually System.loadLibrary("jingle_peerconnection_so")
     *   3. Re-try initialize with tracing enabled
     *   4. Sleep 100ms for native lib to settle
     *   5. Create EglBase context via reflection (3 fallback strategies)
     *   6. If context is null, abort
     *   7. Create PeerConnectionFactory, PeerConnection, VideoSource/Track
     */
    public static final void initializePeerConnectionFactory(WebRTCController controller) {
        try {
            try {
                Context appContext = null; // AppContext.getContext()
                PeerConnectionFactory.initialize(
                        PeerConnectionFactory.InitializationOptions.builder(appContext)
                                .setEnableInternalTracer(false)
                                .createInitializationOptions());
                logDebug(controller, "PeerConnectionFactory初始化成功(直接加载)");
            } catch (UnsatisfiedLinkError e) {
                logWarn(controller,
                        "本地webrtc库加载失败，尝试直接加载so库: " + e.getMessage());
                try {
                    // Fallback: manually load native library
                    System.loadLibrary("jingle_peerconnection_so");
                    logDebug(controller,
                            "直接加载so库成功，重新初始化PeerConnectionFactory");

                    Context fallbackContext = null; // AppContext.getContext()
                    PeerConnectionFactory.initialize(
                            PeerConnectionFactory.InitializationOptions.builder(fallbackContext)
                                    .setEnableInternalTracer(true)
                                    .createInitializationOptions());
                    logDebug(controller,
                            "PeerConnectionFactory重新初始化成功(fallback加载)");
                } catch (Exception e3) {
                    logError(controller,
                            "fallback加载so库也失败: " + e3.getMessage());
                    return;
                }
            } catch (Exception e) {
                logError(controller,
                        "PeerConnectionFactory初始化异常: " + e.getMessage());
                return;
            }

            Thread.sleep(100L);

            // Create EglBase context via reflection (3 fallback strategies)
            EglBase.Context eglContext = createEglBaseContext(controller);
            if (eglContext == null) {
                logError("WebRTC",
                        "无法创建EglBase.Context,WebRTC将无法工作");
                return;
            }

            logDebug(controller, "EglBase.Context: " + eglContext);
            controller.eglBaseContext = eglContext;

            try {
                controller.createPeerConnectionFactory();
                controller.createPeerConnection();
                controller.createSdpAnswer();
                controller.setupVideoCapture();
            } catch (Exception e4) {
                logError(controller,
                        "WebRTC组件创建失败: " + e4.getMessage());
                controller.reportConnectionFailed();
            }
        } catch (Exception e5) {
            logError(controller,
                    "WebRTC初始化总体异常: " + e5.getMessage());
            controller.reportConnectionFailed();
        }
    }

    // =========================================================================
    // EglBase Context Creation (3 fallback strategies via reflection)
    // =========================================================================

    /**
     * Creates EglBase.Context using reflection with 3 fallback strategies:
     *   1. org.webrtc.EglBase$-CC.create()
     *   2. org.webrtc.EglBase$CC.create()
     *   3. org.webrtc.EglBase.create()
     *
     * Original: IIlIllIIll1() — returns EglBase.Context
     *
     * @return EglBase.Context, or null if all strategies fail
     */
    private EglBase.Context createEglBaseContext() {
        // Strategy 1: EglBase$-CC
        try {
            Class<?> clazz = Class.forName("org.webrtc.EglBase$-CC");
            Object eglBase = clazz.getMethod("create").invoke(null);
            if (eglBase != null) {
                logDebug(this, "EglBase创建成功(策略1: EglBase$-CC)");
                return ((EglBase) eglBase).getEglBaseContext();
            }
        } catch (Exception e) {
            logWarn(this, "EglBase策略1失败: " + e.getMessage());
        }

        // Strategy 2: EglBase$CC
        try {
            Class<?> clazz = Class.forName("org.webrtc.EglBase$CC");
            Object eglBase = clazz.getMethod("create").invoke(null);
            if (eglBase != null) {
                logDebug(this, "EglBase创建成功(策略2: EglBase$CC)");
                return ((EglBase) eglBase).getEglBaseContext();
            }
        } catch (Exception e) {
            logWarn(this, "EglBase策略2失败: " + e.getMessage());
        }

        // Strategy 3: EglBase.create()
        try {
            Class<?> clazz = Class.forName("org.webrtc.EglBase");
            Object eglBase = clazz.getMethod("create").invoke(null);
            if (eglBase != null) {
                logDebug(this, "EglBase创建成功(策略3: EglBase)");
                return ((EglBase) eglBase).getEglBaseContext();
            }
        } catch (Exception e) {
            logWarn(this, "EglBase策略3也失败: " + e.getMessage());
        }

        return null;
    }

    // Static wrapper
    private static EglBase.Context createEglBaseContext(WebRTCController controller) {
        return controller.createEglBaseContext();
    }

    // =========================================================================
    // PeerConnectionFactory Creation
    // =========================================================================

    /**
     * Creates the PeerConnectionFactory with video encoder/decoder.
     *
     * Original: llIIIIlIlllIII1()
     */
    public final void createPeerConnectionFactory() {
        DefaultVideoDecoderFactory decoderFactory =
                new DefaultVideoDecoderFactory(this.eglBaseContext);
        DefaultVideoEncoderFactory encoderFactory =
                new DefaultVideoEncoderFactory(this.eglBaseContext, true, true);
        PeerConnectionFactory.Options options = new PeerConnectionFactory.Options();

        try {
            logDebug(this, "开始创建PeerConnectionFactory...");
            logDebug(this, "eglContext: " + this.eglBaseContext);
            logDebug(this, "videoEncoderFactory: " + encoderFactory);

            this.peerConnectionFactory = PeerConnectionFactory.builder()
                    .setOptions(options)
                    .setVideoEncoderFactory(encoderFactory)
                    .setVideoDecoderFactory(decoderFactory)
                    .createPeerConnectionFactory();

            logDebug(this, "PeerConnectionFactory创建成功");
        } catch (Exception e) {
            logError(this,
                    "PeerConnectionFactory创建失败: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // =========================================================================
    // PeerConnection Creation (with STUN/TURN)
    // =========================================================================

    /**
     * Creates PeerConnection with STUN/TURN server configuration.
     *
     * Original: lIllIIIlIl1()
     *
     * TURN Servers:
     *   - turn:101.36.120.3:3478   user=wumitech pass=wumitech.com@123
     *   - turn:106.75.153.105:3478 user=wumitech pass=wumitech.com@123
     *
     * Config: UNIFIED_PLAN, MAXBUNDLE, GATHER_CONTINUALLY, IPv6 disabled on WiFi
     */
    public final void createPeerConnection() {
        List<PeerConnection.IceServer> iceServers = java.util.Arrays.asList(
                PeerConnection.IceServer.builder("turn:101.36.120.3:3478")
                        .setUsername("wumitech")
                        .setPassword("wumitech.com@123")
                        .createIceServer(),
                PeerConnection.IceServer.builder("turn:106.75.153.105:3478")
                        .setUsername("wumitech")
                        .setPassword("wumitech.com@123")
                        .createIceServer()
        );

        PeerConnection.RTCConfiguration rtcConfig =
                new PeerConnection.RTCConfiguration(iceServers);
        rtcConfig.bundlePolicy = PeerConnection.BundlePolicy.MAXBUNDLE;
        rtcConfig.rtcpMuxPolicy = PeerConnection.RtcpMuxPolicy.REQUIRE;
        rtcConfig.sdpSemantics = PeerConnection.SdpSemantics.UNIFIED_PLAN;
        rtcConfig.continualGatheringPolicy =
                PeerConnection.ContinualGatheringPolicy.GATHER_CONTINUALLY;
        rtcConfig.disableIPv6OnWifi = true;
        rtcConfig.iceCandidatePoolSize = 0;
        rtcConfig.iceTransportsType = PeerConnection.IceTransportsType.ALL;

        logDebug(this,
                "ICE配置完成，使用UNIFIED_PLAN，TURN: 101.36.120.3, 106.75.153.105");

        // Optional constraints
        MediaConstraints constraints = new MediaConstraints();
        constraints.optional.add(
                new MediaConstraints.KeyValuePair("DtlsSrtpKeyAgreement", "true"));
        constraints.mandatory.add(
                new MediaConstraints.KeyValuePair("IceTransportsType", "all"));

        // Clean up existing connection if any
        if (this.peerConnection != null) {
            try {
                this.peerConnection.close();
            } catch (Exception e) {
                logError(this, "关闭旧PeerConnection失败: " + e.getMessage());
            }
            this.peerConnection = null;
            logDebug(this, "已清理旧的PeerConnection");
        }

        if (this.dataChannel != null) {
            try {
                this.dataChannel.close();
            } catch (Exception e2) {
                logError(this, "关闭旧DataChannel失败: " + e2.getMessage());
            }
            this.dataChannel = null;
            logDebug(this, "已清理旧的DataChannel");
        }

        try {
            PeerConnection pc = this.peerConnectionFactory
                    .createPeerConnection(rtcConfig, new PeerConnectionObserver());
            this.peerConnection = pc;
            if (pc == null) {
                logError(this, "PeerConnection创建失败，返回null");
                reportConnectionFailed();
            } else {
                logDebug(this, "PeerConnection创建成功，等待SDP协商");
            }
        } catch (Exception e3) {
            logError(this,
                    "创建PeerConnection异常: " + e3.getMessage());
            e3.printStackTrace();
            reportConnectionFailed();
        }
    }

    // =========================================================================
    // Video Capture Setup
    // =========================================================================

    /**
     * Sets up video source, track, and screen capture at 15fps.
     *
     * Original: IlIIlllllI1()
     *
     * Flow:
     *   1. Create llIIIIlIlllIII1.llllIllIl1 (ScreenCapturer)
     *   2. Create VideoSource (not a camera)
     *   3. Create SurfaceTextureHelper
     *   4. Initialize capturer with context + observer
     *   5. Configure screen dimensions
     *   6. Start capture at display resolution @ 15fps
     *   7. Create VideoTrack ("webview-video")
     *   8. Add track to PeerConnection with stream ID "webview-stream"
     *   9. Set video ready flag
     */
    public final void setupVideoCapture() {
        // this.screenCapturer = new ScreenCapturer();
        this.videoSource = this.peerConnectionFactory.createVideoSource(false);

        SurfaceTextureHelper surfaceHelper =
                SurfaceTextureHelper.create("CaptureThread", this.eglBaseContext);

        // capturer.initialize(surfaceHelper, context, videoSource.getCapturerObserver());
        // capturer.configure(context, webView);

        Context ctx = null; // AppContext.getContext()
        DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
        // capturer.startCapture(metrics.widthPixels, metrics.heightPixels, 15);

        this.videoTrack = this.peerConnectionFactory
                .createVideoTrack("webview-video", this.videoSource);

        logDebug(this, "视频轨道创建完成，尝试添加到PeerConnection");

        try {
            List<String> streamIds = java.util.Collections.singletonList("webview-stream");
            RtpSender sender = null;
            if (this.peerConnection != null) {
                sender = this.peerConnection.addTrack(this.videoTrack, streamIds);
            }
            logDebug(this,
                    "视频轨道已添加到PeerConnection, sender: " + sender);
        } catch (Exception e) {
            logError(this,
                    "添加视频轨道到PeerConnection失败: " + e.getMessage());
            e.printStackTrace();
        }

        setVideoReadyFlag();
    }

    // =========================================================================
    // DataChannel Management
    // =========================================================================

    /**
     * Registers observer on the DataChannel and initializes communication.
     *
     * Original: lIIlIIIIlIlII1()
     *
     * If DataChannel is null, logs error and returns.
     * Unregisters old observer, registers new DataChannelObserver.
     * If DataChannel is already OPEN, sends ready message immediately.
     */
    public final void setupDataChannel() {
        if (this.dataChannel == null) {
            logError(this,
                    "DataChannel为null，无法设置观察者和发送就绪消息");
            return;
        }

        logDebug(this,
                "设置DataChannel观察者, 当前状态: " + this.dataChannel.state());

        try {
            this.dataChannel.unregisterObserver();
        } catch (Exception e) {
            logDebug(this,
                    "取消旧DataChannel观察者失败(可忽略): " + e.getMessage());
        }

        this.dataChannel.registerObserver(new DataChannelObserver());

        if (this.dataChannel.state() == DataChannel.State.OPEN) {
            logDebug(this,
                    "DataChannel已经是OPEN状态，立即发送就绪消息并开始心跳检测");
            sendReadyMessage();
        }
    }

    /**
     * Sends a "ready" message via DataChannel to signal readiness.
     *
     * Original: lIlllIIIII1()
     *
     * Sends JSON: {"type": "test", "message": "Channel test from Android"}
     */
    public final void sendReadyMessage() {
        try {
            if (this.dataChannel != null && this.dataChannel.state() == DataChannel.State.OPEN) {
                JSONObject json = new JSONObject();
                json.put("type", "test");
                json.put("message", "Channel test from Android");

                byte[] bytes = json.toString().getBytes("UTF-8");
                this.dataChannel.send(new DataChannel.Buffer(ByteBuffer.wrap(bytes), false));

                logDebug(this, "已发送就绪(test)消息到DataChannel");
            }
        } catch (Exception e) {
            logError(this,
                    "发送DataChannel就绪消息失败: " + e.getMessage());
        }
    }

    /**
     * Sends a message over the DataChannel with type and content.
     *
     * Original: llllIIIIll1(String str, String str2)
     *
     * @param type    message type key
     * @param content message content
     */
    public final void sendDataChannelMessage(String type, String content) {
        try {
            if (this.dataChannel != null &&
                    this.dataChannel.state() == DataChannel.State.OPEN) {
                JSONObject json = new JSONObject();
                json.put("type", type);
                json.put("message", content);

                byte[] bytes = json.toString().getBytes("UTF-8");
                this.dataChannel.send(new DataChannel.Buffer(ByteBuffer.wrap(bytes), false));

                logDebug(this, "已发送DataChannel消息: " + type);
            }
        } catch (Exception e) {
            logError(this,
                    "发送DataChannel消息失败: " + e.getMessage());
        }
    }

    // =========================================================================
    // SDP Answer Creation
    // =========================================================================

    /**
     * Creates an SDP answer with media constraints.
     *
     * Original: IlIlIIlIII1()
     *
     * Constraints:
     *   - OfferToReceiveAudio = true (mandatory)
     *   - OfferToReceiveVideo = false (mandatory)
     */
    public final void createSdpAnswer() {
        logDebug(this, "开始创建SDP Answer...");

        MediaConstraints constraints = new MediaConstraints();
        constraints.mandatory.add(
                new MediaConstraints.KeyValuePair("OfferToReceiveAudio", "true"));
        constraints.mandatory.add(
                new MediaConstraints.KeyValuePair("OfferToReceiveVideo", "false"));

        if (this.peerConnection != null) {
            this.peerConnection.createAnswer(new AnswerSdpObserver(), constraints);
        }
    }

    // =========================================================================
    // SDP Offer Handling
    // =========================================================================

    /**
     * Sets the remote SDP offer description.
     *
     * Original: llllIIIIll1(String sdp)
     *
     * @param sdpOffer the SDP offer string
     */
    public final void setRemoteSdpOffer(final String sdpOffer) {
        try {
            logDebug(this, "收到SDP Offer, 设置RemoteDescription");
            SessionDescription remoteSdp =
                    new SessionDescription(SessionDescription.Type.OFFER, sdpOffer);
            if (this.peerConnection != null) {
                this.peerConnection.setRemoteDescription(
                        new RemoteDescriptionObserver(), remoteSdp);
            }
        } catch (Exception e) {
            logError(this,
                    "设置RemoteDescription失败: " + e.getMessage());
            reportConnectionFailed();
        }
    }

    // =========================================================================
    // ICE Candidate Handling
    // =========================================================================

    /**
     * Adds an ICE candidate from the signaling server.
     *
     * Original: llllIIIIll1(ICECandidate)
     *
     * If remote description is not yet set, buffers the candidate.
     * Otherwise, adds it to the PeerConnection immediately.
     */
    public void addIceCandidate(IceCandidate iceCandidate) {
        try {
            if (iceCandidate == null) return;
            logDebug(this, "收到ICE Candidate, 添加到PeerConnection");
            if (this.peerConnection != null) {
                this.peerConnection.addIceCandidate(iceCandidate);
            }
        } catch (Exception e) {
            logError(this,
                    "添加ICE Candidate异常: " + e.getMessage());
        }
    }

    // =========================================================================
    // Touch Event Simulation
    // =========================================================================

    /**
     * Simulates a click at the given relative coordinates.
     *
     * Original: lIIIIlllllIlll1(double relX, double relY)
     * Posts to WebView's UI thread.
     *
     * @param relX relative X (0.0-1.0)
     * @param relY relative Y (0.0-1.0)
     */
    public final void click(final double relX, final double relY) {
        this.webView.post(() -> performClick(relX, relY));
    }

    /**
     * Performs a click at the given coordinates (on UI thread).
     *
     * Original: lIIIIlllllIlll1(IllIIlIIII1, double, double)
     *
     * Dispatches ACTION_DOWN then ACTION_UP with 50ms gap.
     */
    private void performClick(double relX, double relY) {
        int x = (int) (relX * this.webView.getWidth());
        int y = (int) (relY * this.webView.getHeight());
        long now = SystemClock.uptimeMillis();

        MotionEvent down = MotionEvent.obtain(now, now, MotionEvent.ACTION_DOWN, x, y, 0);
        this.webView.dispatchTouchEvent(down);
        down.recycle();

        MotionEvent up = MotionEvent.obtain(now, now + 50, MotionEvent.ACTION_UP, x, y, 0);
        this.webView.dispatchTouchEvent(up);
        up.recycle();

        logDebug(this, "远程点击事件已分发");
    }

    /**
     * Initializes a scroll/drag at the given coordinates.
     *
     * Original: IlIlllIIlI1(double, double)
     */
    private static void initScrollPosition(WebRTCController controller,
                                            double relX, double relY) {
        int width = controller.webView.getWidth();
        int height = controller.webView.getHeight();
        float x = (float) (relX * width);
        float y = (float) (relY * height);
        controller.scrollOriginX = x;
        controller.scrollOriginY = y;
        controller.lastDragX = x;
        controller.lastDragY = y;
        controller.scrollDistance = 0.0f;
        controller.scrollStartTime = SystemClock.uptimeMillis();
        controller.isDragging = true;
    }

    /**
     * Performs a scroll/move event during drag.
     *
     * Original: IllIIlIIII1(IllIIlIIII1, double, double)
     *
     * Dispatches ACTION_DOWN (if first), then ACTION_MOVE events
     * once cumulative distance exceeds SCROLL_THRESHOLD (50px).
     */
    private static void performScroll(WebRTCController controller,
                                       double relX, double relY) {
        if (!controller.isDragging) {
            logDebug(controller,
                    "scroll event ignored: not in dragging state, treating as click");
            controller.postScrollAction(relX, relY);
            return;
        }

        float x = (float) (relX * controller.webView.getWidth());
        float y = (float) (relY * controller.webView.getHeight());
        float totalDist = controller.scrollDistance
                + Math.abs(y - controller.lastDragY)
                + Math.abs(x - controller.lastDragX);
        controller.scrollDistance = totalDist;

        if (totalDist > controller.SCROLL_THRESHOLD) {
            if (controller.touchState == controller.TOUCH_STATE_SCROLL_START) {
                // Initial ACTION_DOWN
                long downTime = controller.scrollStartTime;
                MotionEvent down = MotionEvent.obtain(downTime, downTime,
                        MotionEvent.ACTION_DOWN,
                        controller.scrollOriginX, controller.scrollOriginY, 0);
                controller.webView.dispatchTouchEvent(down);
                down.recycle();
                controller.touchState = controller.TOUCH_STATE_SCROLLING;
            }
            if (controller.touchState == controller.TOUCH_STATE_SCROLLING) {
                // ACTION_MOVE
                MotionEvent move = MotionEvent.obtain(
                        controller.scrollStartTime, SystemClock.uptimeMillis(),
                        MotionEvent.ACTION_MOVE, x, y, 0);
                controller.webView.dispatchTouchEvent(move);
                move.recycle();
            }
        }
        controller.lastDragX = x;
        controller.lastDragY = y;
    }

    /**
     * Performs touch release (drag end).
     *
     * Original: llllIllIl1(IllIIlIIII1, double, double)
     *
     * Dispatches ACTION_CANCEL to end the touch sequence.
     */
    private static void performTouchRelease(WebRTCController controller,
                                             double relX, double relY) {
        if (!controller.isDragging) {
            logDebug(controller,
                    "release event ignored: not in dragging state, skipping touch release event dispatch");
            return;
        }

        float x = (float) (relX * controller.webView.getWidth());
        float y = (float) (relY * controller.webView.getHeight());
        controller.scrollDistance += Math.abs(y - controller.lastDragY)
                + Math.abs(x - controller.lastDragX);

        if (controller.touchState == controller.TOUCH_STATE_SCROLLING) {
            MotionEvent cancel = MotionEvent.obtain(
                    controller.scrollStartTime, SystemClock.uptimeMillis(),
                    MotionEvent.ACTION_CANCEL, x, y, 0);
            controller.webView.dispatchTouchEvent(cancel);
            cancel.recycle();
        }
    }

    /**
     * Posts a scroll action to the WebView's UI thread.
     *
     * Original: IllIIlIIII1(double, double)
     */
    public final void postScrollAction(final double relX, final double relY) {
        this.webView.post(() -> initScrollPosition(WebRTCController.this, relX, relY));
    }

    /**
     * Posts a touch release to the WebView's UI thread.
     *
     * Original: llllIllIl1(double, double)
     */
    public final void postTouchRelease(final double relX, final double relY) {
        this.webView.post(() -> performTouchRelease(WebRTCController.this, relX, relY));
    }

    // =========================================================================
    // Key Event Simulation
    // =========================================================================

    /**
     * Dispatches a key event (keyCode) to the WebView.
     *
     * Original: llllIIIIll1(int keyCode)
     *
     * @param keyCode the Android keyCode (e.g., KeyEvent.KEYCODE_DEL)
     */
    public final void dispatchKeyCode(int keyCode) {
        long now = SystemClock.uptimeMillis();
        long end = now + 10;
        this.webView.dispatchKeyEvent(
                new KeyEvent(now, end, KeyEvent.ACTION_DOWN, keyCode, 0, 0, -1, 0, 2));
        this.webView.dispatchKeyEvent(
                new KeyEvent(now, end + 10, KeyEvent.ACTION_UP, keyCode, 0, 0, -1, 0, 2));
        logDebug(this, "已分发keyCode事件: " + keyCode);
    }

    /**
     * Handles special char input (tab=61, enter=66).
     *
     * Original: llllIIIIll1(char c)
     *
     * Maps: '\t' → KEYCODE_TAB(61), '\n' → KEYCODE_ENTER(66),
     * otherwise sends ACTION_MULTIPLE with the character.
     */
    public final void dispatchCharInput(char c) {
        int keyCode = -1;
        if (c == '\t') {
            keyCode = 61;  // KEYCODE_TAB
        } else if (c == '\n') {
            keyCode = 66;  // KEYCODE_ENTER
        }

        long now = SystemClock.uptimeMillis();
        long end = now + 10;

        if (keyCode != -1) {
            // Known key — dispatch down+up
            this.webView.dispatchKeyEvent(
                    new KeyEvent(now, end, KeyEvent.ACTION_DOWN, keyCode, 1, 0, -1, c, 2));
            this.webView.dispatchKeyEvent(
                    new KeyEvent(now, end + 10, KeyEvent.ACTION_UP, keyCode, 1, 0, -1, c, 2));
            logDebug(this, "已分发特殊字符键事件(keyCode=" + keyCode + ")");
        } else {
            // Send as ACTION_MULTIPLE
            this.webView.dispatchKeyEvent(
                    new KeyEvent(now, end, KeyEvent.ACTION_MULTIPLE, 0, 1, 0, -1, c, 2));
            logDebug(this, "已分发ACTION_MULTIPLE字符事件");
        }
    }

    /**
     * Handles text input from remote — maps special strings to keyCodes,
     * or injects text via JavaScript.
     *
     * Original: llllIIIIll1(String, IllIIlIIII1)
     *
     * Special keys:
     *   - "\b"  → KEYCODE_DEL (67)
     *   - "←"   → KEYCODE_DPAD_LEFT (21)
     *   - "↑"   → KEYCODE_DPAD_UP (19)
     *   - "→"   → KEYCODE_DPAD_RIGHT (22)
     *   - "↓"   → KEYCODE_DPAD_DOWN (20)
     *   - "⌫"   → KEYCODE_FORWARD_DEL (112)
     *   - Single letter/digit/space → inject via JavaScript
     *   - Control chars → dispatch as keyCode
     *   - Otherwise → inject via JavaScript
     */
    public static void handleKeyInput(String key, WebRTCController controller) {
        if (key.equals("\b")) {
            controller.dispatchKeyCode(67); // KEYCODE_DEL
            return;
        }
        if (key.equals("⌫")) {
            controller.dispatchKeyCode(112); // KEYCODE_FORWARD_DEL
            return;
        }
        if (key.equals("←")) {
            controller.dispatchKeyCode(21); // KEYCODE_DPAD_LEFT
            return;
        }
        if (key.equals("↑")) {
            controller.dispatchKeyCode(19); // KEYCODE_DPAD_UP
            return;
        }
        if (key.equals("→")) {
            controller.dispatchKeyCode(22); // KEYCODE_DPAD_RIGHT
            return;
        }
        if (key.equals("↓")) {
            controller.dispatchKeyCode(20); // KEYCODE_DPAD_DOWN
            return;
        }

        if (key.length() == 1) {
            char c = key.charAt(0);
            if (Character.isLetterOrDigit(c) || c == ' ') {
                controller.injectTextViaJavascript(key);
                return;
            } else if (c < ' ') {
                controller.dispatchCharInput(c);
                return;
            }
        }
        controller.injectTextViaJavascript(key);
    }

    // =========================================================================
    // JavaScript Injection for Text Input
    // =========================================================================

    /**
     * Injects text into the focused element via JavaScript.
     *
     * Original: IllIIlIIII1(String)
     *
     * JavaScript template:
     *   (function() {
     *     var el = document.activeElement;
     *     if (el && (el.tagName === 'INPUT' || el.tagName === 'TEXTAREA' || el.isContentEditable)) {
     *       var event = new InputEvent('insertText', { inputType: 'insertText', data: '<text>', bubbles: true });
     *       el.dispatchEvent(event);
     *     }
     *   })();
     */
    public final void injectTextViaJavascript(final String text) {
        // The actual JS is a complex XOR-encrypted template that:
        // 1. Finds document.activeElement
        // 2. Checks if it's an INPUT/TEXTAREA/contentEditable
        // 3. Uses execCommand('insertText') or InputEvent to inject text
        // 4. Falls back to direct value modification if needed
        String js = buildTextInjectionJs(text);
        this.webView.evaluateJavascript(js, null);
    }

    /**
     * Injects text via JavaScript at specific coordinates (click first, then type).
     *
     * Original: lIIIIlllllIlll1(String text, double relX, double relY)
     *
     * First evaluates JS to check if element at (x,y) is an input field.
     * If "true", uses keyboard-based input. Otherwise, uses JS injection.
     */
    public final void injectTextAtCoordinates(final String text,
                                               final double relX, final double relY) {
        this.webView.post(() -> {
            // First: JS checks if elementFromPoint(x,y) is an input
            // If "true" → use llllIllIl1 (keyboard input)
            // If not → use JS injection at coordinates
            performTextInputAtCoordinates(text, relX, relY);
        });
    }

    private void performTextInputAtCoordinates(String text, double relX, double relY) {
        // Simplified — the actual implementation evaluates JS to determine
        // element type, then either types or injects
        int x = (int) (relX * this.webView.getWidth());
        int y = (int) (relY * this.webView.getHeight());

        // Click at position, then inject text after 100ms delay
        long now = SystemClock.uptimeMillis();
        this.webView.dispatchTouchEvent(
                MotionEvent.obtain(now, now, MotionEvent.ACTION_DOWN, x, y, 0));
        this.webView.dispatchTouchEvent(
                MotionEvent.obtain(now, now + 50, MotionEvent.ACTION_UP, x, y, 0));

        logDebug(this, "远程输入: 先点击位置，再输入文字");

        this.webView.postDelayed(() -> {
            // Evaluate JS for text injection after click
            injectTextViaJavascript(text);
        }, 100L);
    }

    /**
     * Builds JavaScript for text injection.
     * The actual encrypted JS template handles:
     *   - Finding focused/active element
     *   - execCommand('insertText', text) for input fields
     *   - DataTransfer/clipboard paste simulation
     *   - Fallback to direct value modification
     */
    private String buildTextInjectionJs(String text) {
        // The XOR-encrypted JS is very large (~400+ bytes when decrypted).
        // It creates an InputEvent with insertText type and dispatches it.
        return "(function(){var el=document.activeElement;"
                + "if(el&&(el.tagName==='INPUT'||el.tagName==='TEXTAREA'"
                + "||el.isContentEditable)){"
                + "document.execCommand('insertText',false,'" + escapeJs(text) + "');"
                + "}})();";
    }

    private static String escapeJs(String s) {
        return s.replace("\\", "\\\\")
                .replace("'", "\\'")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }

    // =========================================================================
    // DataChannel Message Processing (Remote Commands)
    // =========================================================================

    /**
     * Processes an incoming DataChannel message buffer.
     *
     * Original: llllIIIIll1(DataChannel.Buffer)
     *
     * Parses JSON with keys: action, x, y, scroll_x, scroll_y, text
     * Dispatches to appropriate handler based on "action" value:
     *   - "click"    → click(x, y)
     *   - "drag"     → begin drag
     *   - "dragEnd"  → end drag
     *   - "scroll"   → scroll/move
     *   - "done"     → done (touch complete)
     *   - "paste"    → paste text
     *   - "input"    → text input
     *   - "inputAt"  → text input at coordinates
     *   - "keyInput" → keyboard input
     *   - "goBack"   → navigate back
     *   - "close"    → close connection
     *   - "release"  → release touch
     */
    public final void onDataChannelMessage(DataChannel.Buffer buffer) {
        try {
            ByteBuffer byteBuffer = buffer.data;
            byte[] data = new byte[byteBuffer.remaining()];
            byteBuffer.get(data);
            JSONObject json = new JSONObject(new String(data, "UTF-8"));

            String action = json.getString("action");
            long now = SystemClock.uptimeMillis();

            // Debounce: skip if action is "drag" or "dragEnd" within 500ms
            // but only if action is "dragEnd" and state changed
            if (now - this.lastTouchTime > 500
                    && ("drag".equals(action) || "dragEnd".equals(action))
                    && "dragEnd".equals(action)
                    && this.touchState != this.TOUCH_STATE_SCROLLING) {
                logDebug(this,
                        "忽略重复的drag/dragEnd事件(debounce, 状态不匹配)");
                // Convert to click
                double cx = getJsonDouble(json, "x", 0.5);
                double cy = getJsonDouble(json, "y", 0.5);
                click(cx, cy);
                this.isDragging = false;
                this.touchState = this.TOUCH_STATE_IDLE;
                return;
            }
            this.lastTouchTime = now;

            double x = getJsonDouble(json, "x", 0.5);
            double y = getJsonDouble(json, "y", 0.5);
            double scrollX = getJsonDouble(json, "scroll_x", 0.0);
            double scrollY = getJsonDouble(json, "scroll_y", 0.0);
            String text = getJsonString(json, "text", "");

            if (action == null) {
                logWarn(this, "DataChannel消息缺少action字段: " + action);
                return;
            }

            switch (action) {
                case "goBack":
                    logDebug(this, "远程命令: goBack — 导航返回");
                    goBack();
                    break;

                case "drag":
                    logDebug(this, "远程命令: drag — 开始拖拽");
                    this.touchState = this.TOUCH_STATE_SCROLL_START;
                    postScrollAction(x, y);
                    break;

                case "done":
                    // Touch done — process scroll completion
                    click(x, y); // fall through to done logic
                    break;

                case "click":
                    logDebug(this, "远程命令: click — 点击");
                    click(x, y);
                    this.touchState = this.TOUCH_STATE_IDLE;
                    break;

                case "close":
                    logWarn(this, "远程命令: close — 关闭连接");
                    // signalingConnection.disconnect()
                    break;

                case "input":
                    logDebug(this, "远程命令: input — 文本输入");
                    injectTextAtCoordinates(text, x, y);
                    break;

                case "inputAt":
                    logDebug(this, "远程命令: inputAt — 指定位置文本输入");
                    handleInputAt(text);
                    break;

                case "paste":
                    logDebug(this, "远程命令: paste — 粘贴文本");
                    handleKeyInput(text, this);
                    break;

                case "release":
                    logDebug(this, "远程命令: release — 释放触摸");
                    postTouchRelease(x, y);
                    this.touchState = this.TOUCH_STATE_IDLE;
                    break;

                case "keyInput":
                    logDebug(this, "远程命令: keyInput — 键盘输入");
                    handleKeyInput(text, this);
                    this.touchState = this.TOUCH_STATE_IDLE;
                    break;

                default:
                    logWarn(this,
                            "未知的DataChannel action: " + action);
                    break;
            }
        } catch (Exception e) {
            logError(this,
                    "处理DataChannel消息异常: " + e.getMessage());
        }
    }

    // =========================================================================
    // JSON Helper Methods
    // =========================================================================

    /**
     * Gets a double value from JSON, with default.
     * Original: llllIIIIll1(JSONObject, String, double)
     */
    public final double getJsonDouble(JSONObject json, String key, double defaultVal) {
        try {
            return json.has(key) ? json.getDouble(key) : defaultVal;
        } catch (Exception e) {
            return defaultVal;
        }
    }

    /**
     * Gets a string value from JSON, with default.
     * Original: llllIIIIll1(JSONObject, String, String)
     */
    public final String getJsonString(JSONObject json, String key, String defaultVal) {
        try {
            return json.has(key) ? json.getString(key) : defaultVal;
        } catch (Exception e) {
            return defaultVal;
        }
    }

    // =========================================================================
    // Navigation
    // =========================================================================

    /**
     * Navigates the WebView back (or sends "go_back_failed" if at first page).
     *
     * Original: IlIllll1(IllIIlIIII1)
     */
    public static void handleGoBack(WebRTCController controller) {
        if (controller.webView.canGoBack()) {
            controller.webView.goBack();
            logDebug(controller, "WebView已执行goBack()导航返回");
        } else {
            logDebug(controller,
                    "WebView已经是第一页，无法返回，发送go_back_failed");
            controller.sendDataChannelMessage(
                    "go_back_failed",
                    "WebView已经是第一页，无法返回");
        }
    }

    /**
     * Posts goBack to WebView's UI thread.
     * Original: IlIlIIIlIlIlll1()
     */
    public final void goBack() {
        this.webView.post(() -> handleGoBack(WebRTCController.this));
    }

    /**
     * Handles text input at position.
     * Original: IlIlllIIlI1(String)
     */
    private void handleInputAt(String text) {
        // Delegates to llllIllIl1 (keyboard input)
        handleKeyInput(text, this);
    }

    // =========================================================================
    // Connection Health Monitoring
    // =========================================================================

    /**
     * Starts connection health monitoring on a schedule.
     *
     * Original: IllllIllllll1()
     *
     * Cancels any existing monitor, then schedules periodic checks
     * at the configured interval.
     */
    public final void startConnectionHealthMonitor() {
        logInfo(this, "开始连接健康监控，定期检查ICE连接状态");
        cancelHealthMonitor();

        Runnable healthCheck = () -> checkConnectionHealth(WebRTCController.this);
        long interval = this.connectionTimeout;
        this.pingFuture = this.scheduledExecutor.scheduleWithFixedDelay(
                healthCheck, interval, interval, TimeUnit.MILLISECONDS);
    }

    /**
     * Checks connection health (ICE state, timeout).
     *
     * Original: lIllIlIll1(IllIIlIIII1)
     *
     * If destroyed, skips. Otherwise creates SDP answer.
     */
    private static void checkConnectionHealth(WebRTCController controller) {
        if (controller.isCleanedUp.get()) return;
        controller.createSdpAnswer();
    }

    /**
     * Cancels the health monitor scheduled future.
     * Original: lllIlIlllI1()
     */
    public final void cancelHealthMonitor() {
        ScheduledFuture<?> future = this.pingFuture;
        if (future != null) {
            future.cancel(false);
        }
        this.pingFuture = null;
    }

    // =========================================================================
    // Ping Scheduler
    // =========================================================================

    /**
     * Starts periodic ping check via ScheduledExecutorService.
     *
     * Original: lIIlllIIIlllII1()
     *
     * Checks DataChannel health periodically; if too many pings missed,
     * considers connection dead.
     */
    public final void startPingScheduler() {
        ScheduledFuture<?> existing = this.connectionTimerFuture;
        if (existing != null) {
            existing.cancel(false);
        }
        long interval = 0; // from config constant
        this.connectionTimerFuture = this.scheduledExecutor.scheduleWithFixedDelay(
                () -> checkDataChannelHealth(WebRTCController.this),
                interval, interval, TimeUnit.MILLISECONDS);
    }

    /**
     * Checks DataChannel health — if state is not CONNECTED/COMPLETED,
     * triggers reconnection.
     *
     * Original: llIIIIlIlllIII1(IllIIlIIII1)
     */
    private static void checkDataChannelHealth(WebRTCController controller) {
        // Checks ICE connection state; if DISCONNECTED or FAILED, takes action
    }

    // =========================================================================
    // Connection Signaling
    // =========================================================================

    /**
     * Starts the signaling connection flow.
     *
     * Original: lIllIlIll1()
     */
    public final void connectSignaling() {
        logInfo(this, "开始信令连接");
        try {
            // signalingConnection.startSignaling()
            logInfo(this,
                    "信令连接已建立，等待SDP Offer和ICE Candidates");
        } catch (Exception e) {
            logError(this,
                    "信令连接失败，开始清理: " + e);
            reportConnectionFailed();
        }
    }

    // =========================================================================
    // Codec Enumeration & Init
    // =========================================================================

    /**
     * Enumerates media codecs and starts WebRTC initialization.
     *
     * Original: lllllIllIl1()
     *
     * Lists all hardware encoders/decoders, then starts PeerConnectionFactory init.
     */
    public final void enumerateCodecsAndInit() {
        if (this.isCleanedUp.get()) {
            logWarn(this,
                    "WebRTC已清理，跳过初始化。如果持续出现此日志，请检查生命周期管理");
            return;
        }

        MediaCodecList codecList = new MediaCodecList(MediaCodecList.ALL_CODECS);
        for (MediaCodecInfo codecInfo : codecList.getCodecInfos()) {
            if (codecInfo.isEncoder()) {
                logInfo("RTCE", "编码器(Encoder): " + codecInfo.getName());
            } else {
                logInfo("RTCD", "解码器(Decoder): " + codecInfo.getName());
            }
        }

        logInfo(this, "编解码器枚举完成");
        connectSignaling();

        // Run PeerConnectionFactory init on background thread
        // AppContext.runOnBackgroundThread(() -> initializePeerConnectionFactory(this));
    }

    // =========================================================================
    // Native Library Check
    // =========================================================================

    /**
     * Checks if a native library is loaded (2 strategies).
     *
     * Original: lIIIIlllllIlll1(String libName)
     *
     * Strategy 1: Reflection to read Runtime.loadedLibraries set
     * Strategy 2: Parse /proc/<pid>/maps for the library filename
     *
     * @param libName the library name (without "lib" prefix / ".so" suffix)
     * @return true if loaded
     */
    public final boolean isNativeLibraryLoaded(String libName) {
        // Strategy 1: Reflection
        try {
            Runtime runtime = Runtime.getRuntime();
            Field field = runtime.getClass().getDeclaredField("loadedLibraries");
            field.setAccessible(true);
            Object obj = field.get(runtime);
            if (obj instanceof java.util.Set) {
                java.util.Set<?> set = (java.util.Set<?>) obj;
                if (set.contains(System.mapLibraryName(libName))) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            logDebug(this,
                    "反射检查loadedLibraries失败，尝试读取/proc/<pid>/maps");
        }

        // Strategy 2: Parse /proc/<pid>/maps
        try {
            String mappedName = System.mapLibraryName(libName);
            java.io.Process proc = Runtime.getRuntime().exec(
                    "ls /proc/" + Process.myPid() + "/maps");
            InputStream is = proc.getInputStream();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, "UTF-8"));
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(mappedName)) {
                        return true;
                    }
                }
            } finally {
                reader.close();
            }
        } catch (Exception e) {
            logError(this,
                    "读取/proc maps检查库加载状态也失败: " + e.getMessage());
        }
        return false;
    }

    // =========================================================================
    // Network Capability Check
    // =========================================================================

    /**
     * Checks if the device has active network capability (INTERNET).
     *
     * Original: IlIlllIIlI1() — static method checking ConnectivityManager
     *
     * @return true if network is available with INTERNET capability
     */
    public static boolean hasNetworkCapability(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                android.net.Network network = cm.getActiveNetwork();
                if (network != null) {
                    NetworkCapabilities caps = cm.getNetworkCapabilities(network);
                    return caps != null
                            && caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
                }
            }
        } catch (Exception e) {
            // ignore
        }
        return false;
    }

    // =========================================================================
    // Cleanup & Destroy
    // =========================================================================

    /**
     * Destroys the WebRTC controller, releasing all resources.
     *
     * Original: IIIlIllIlI1()
     *
     * Cleanup order:
     *   1. Cancel health check scheduled future
     *   2. Cancel heartbeat timer
     *   3. Reset video ready flag
     *   4. Close DataChannel
     *   5. Close PeerConnection
     *   6. Dispose VideoTrack and VideoSource
     *   7. Dispose PeerConnectionFactory
     *   8. Disconnect SignalingConnection
     *   9. Shutdown ScheduledExecutorService
     *   10. Cancel connection timer
     */
    public final void destroy() {
        try {
            if (this.isCleanedUp.compareAndSet(false, true)) {
                logDebug(this, "开始清理WebRTC资源...");

                // Cancel health check
                try {
                    if (this.healthCheckFuture != null) {
                        this.healthCheckFuture.cancel(false);
                    }
                    this.healthCheckFuture = null;
                } catch (Throwable ignored) {}

                // Cancel heartbeat
                try { cancelHealthMonitor(); } catch (Throwable ignored) {}

                // Reset video ready
                try { this.isVideoReady.set(false); } catch (Throwable ignored) {}

                // Close DataChannel
                try {
                    if (this.dataChannel != null) {
                        this.dataChannel.close();
                    }
                    this.dataChannel = null;
                } catch (Exception e) {
                    logError(this,
                            "关闭DataChannel失败: " + e.getMessage());
                }

                // Close PeerConnection
                try {
                    if (this.peerConnection != null) {
                        this.peerConnection.close();
                    }
                    this.peerConnection = null;
                } catch (Exception e2) {
                    logError(this,
                            "关闭PeerConnection失败: " + e2.getMessage());
                }

                // Dispose video track and source
                try {
                    if (this.videoTrack != null) {
                        this.videoTrack.dispose();
                    }
                    this.videoTrack = null;
                    if (this.videoSource != null) {
                        this.videoSource.dispose();
                    }
                    this.videoSource = null;
                } catch (Exception e3) {
                    logError(this,
                            "释放视频轨道/源失败: " + e3.getMessage());
                }

                // Dispose PeerConnectionFactory
                try {
                    if (this.peerConnectionFactory != null) {
                        this.peerConnectionFactory.dispose();
                    }
                } catch (Exception e4) {
                    logError(this,
                            "释放PeerConnectionFactory失败: " + e4.getMessage());
                }

                // Disconnect signaling
                try {
                    // signalingConnection.disconnect()
                } catch (Exception e5) {
                    logError(this,
                            "断开信令连接失败: " + e5.getMessage());
                }

                // Shutdown executor
                try {
                    this.scheduledExecutor.shutdownNow();
                } catch (Exception ignored) {}

                // Cancel connection timer
                if (this.connectionTimerFuture != null) {
                    this.connectionTimerFuture.cancel(false);
                }
                this.connectionTimerFuture = null;
            }
        } catch (Throwable ignored) {}
    }

    /**
     * Cleans up existing PeerConnection and DataChannel before reconnect.
     *
     * Original: IlIllIlllIllI1()
     */
    public final void cleanupExistingConnection() {
        // Remove all senders
        if (this.peerConnection != null) {
            List<RtpSender> senders = this.peerConnection.getSenders();
            if (senders != null) {
                for (RtpSender sender : senders) {
                    try {
                        this.peerConnection.removeTrack(sender);
                    } catch (Exception e) {
                        logError(this,
                                "移除RtpSender失败: " + e.getMessage());
                    }
                }
            }
        }

        // Dispose PeerConnection
        try {
            if (this.peerConnection != null) {
                this.peerConnection.dispose();
            }
        } catch (Exception e2) {
            logError(this,
                    "dispose PeerConnection失败: " + e2.getMessage());
        }
        this.peerConnection = null;

        // Close DataChannel
        try {
            if (this.dataChannel != null) {
                this.dataChannel.close();
            }
        } catch (Exception e3) {
            logError(this,
                    "关闭DataChannel失败: " + e3.getMessage());
        }
        this.dataChannel = null;
        this.pendingIceCandidates.clear();
    }

    /**
     * Reports connection failure via signaling.
     * Original: llIIllIl1()
     */
    public final void reportConnectionFailed() {
        logDebug(this, "WebRTC连接失败，上报错误状态");
        // Sends failure status via signaling
    }

    /**
     * Sets the video ready flag.
     * Original: IllIlIllll1()
     */
    public final void setVideoReadyFlag() {
        this.isVideoReady.compareAndSet(false, true);
    }

    /**
     * Resets the video ready flag.
     * Original: IlIIIIllllIlI1()
     */
    public final void resetVideoReadyFlag() {
        this.isVideoReady.set(false);
    }

    /**
     * Captures a bitmap of the current WebView content.
     *
     * Original: llllIIIIll1(long timeout) — returns Bitmap
     *
     * @param timeout capture timeout in milliseconds
     * @return the captured Bitmap, or null on failure
     */
    public final Bitmap captureScreen(long timeout) {
        if (this.screenCapturer == null) {
            logError(this,
                    "ScreenCapturer为null,无法截图。请检查是否已正确初始化视频捕获模块。");
            return null;
        }
        if (this.webView.getWidth() <= 0 || this.webView.getHeight() <= 0) {
            logError(this,
                    "WebView尺寸无效(width或height<=0),无法截图");
            return null;
        }

        // capturer.capture(timeout)
        Bitmap bitmap = null; // = screenCapturer.capture(timeout);
        if (bitmap == null) {
            logError(this, "截图返回null");
        } else {
            logDebug(this, "截图成功，准备发送帧数据");
        }
        return bitmap;
    }

    // =========================================================================
    // SignalingEventListener Implementation
    // =========================================================================

    /**
     * Called when signaling connection is established.
     * Resets ping counter and starts signaling flow.
     *
     * Original: lIIIIlllllIlll1() [onConnected]
     */
    @Override
    public void onConnected() {
        this.missedPingCount = 0;
        logInfo(this, "信令连接已建立，开始WebRTC协商");
        // taskHolder.startTask()
    }

    /**
     * Called when a pong is received from signaling.
     * Resets missed ping counter.
     *
     * Original: llllIIIIll1(Pong pong) [onPong]
     */
    public void onPong(Object pong) {
        this.missedPingCount = 0;
        logDebug(this, "收到Pong响应，重置丢失计数");
    }

    /**
     * Called when an SDP offer is received.
     *
     * Original: llllIIIIll1(SDPOffer) [onSdpOffer]
     */
    public void onSdpOffer(String sdp) {
        this.missedPingCount = 0;
        if (sdp == null || sdp.length() == 0) {
            logError(this, "收到空的SDP Offer，忽略");
        } else {
            logDebug(this, "收到SDP Offer, 开始处理");
            setRemoteSdpOffer(sdp);
        }
    }

    /**
     * Called on signaling error.
     * Original: llllIIIIll1(Exception) [onError]
     */
    public void onError(Exception exc) {
        logError(this,
                "信令连接错误: " + exc.getMessage());
        reportConnectionFailed();
    }

    // =========================================================================
    // Logging Level Control
    // =========================================================================

    /**
     * Logs a message at the specified level.
     *
     * Original: llllIIIIll1(int level, String msg, Object extra)
     *
     * Levels: 0=DEBUG, 1=INFO, 2=WARN, 3=ERROR
     */
    public final void log(int level, String msg, Object extra) {
        String prefix;
        switch (level) {
            case 0: prefix = "[WebRTC-DEBUG]"; break;
            case 1: prefix = "[WebRTC-INFO]"; break;
            case 2: prefix = "[WebRTC-WARN]"; break;
            case 3: prefix = "[WebRTC-ERROR]"; break;
            default: prefix = "[WebRTC-???]"; break;
        }
        // Logger.log(level, getLogTag(), prefix + ": " + msg + (extra != null ? " " + extra : ""));
    }

    // =========================================================================
    // Inner Class: DataChannelObserver
    // =========================================================================

    /**
     * DataChannel.Observer implementation.
     *
     * Original: IlIlllIIlI1 (inner class)
     *
     * Handles:
     *   - onMessage: delegates to onDataChannelMessage()
     *   - onStateChange: OPEN → send ready + start heartbeat;
     *                     CLOSED/CLOSING → schedule reconnect after 1s
     */
    public final class DataChannelObserver implements DataChannel.Observer {

        @Override
        public void onBufferedAmountChange(long amount) {}

        @Override
        public void onMessage(DataChannel.Buffer buffer) {
            WebRTCController.this.onDataChannelMessage(buffer);
        }

        @Override
        public void onStateChange() {
            DataChannel dc = WebRTCController.this.dataChannel;
            DataChannel.State state = dc != null ? dc.state() : null;
            logDebug(WebRTCController.this,
                    "DataChannel状态变更: " + state);

            if (state == DataChannel.State.OPEN) {
                logDebug(WebRTCController.this,
                        "DataChannel已打开，发送就绪消息");
                WebRTCController.this.missedPingCount = 0;
                WebRTCController.this.sendReadyMessage();
            } else if (state == DataChannel.State.CLOSED
                    || state == DataChannel.State.CLOSING) {
                logDebug(WebRTCController.this,
                        "DataChannel关闭/正在关闭，1秒后检查ICE状态");
                if (!WebRTCController.this.isCleanedUp.get()) {
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        checkDataChannelClosed(WebRTCController.this);
                    }, 1000L);
                }
            }
        }

        /**
         * Checks ICE state after DataChannel closes.
         * If not CONNECTED/COMPLETED, triggers cleanup.
         */
        private static void checkDataChannelClosed(WebRTCController controller) {
            if (controller.isCleanedUp.get()) return;

            PeerConnection pc = controller.peerConnection;
            PeerConnection.IceConnectionState iceState =
                    pc != null ? pc.iceConnectionState() : null;

            logDebug(controller,
                    "数据通道关闭后检查ICE连接状态: " + iceState);

            if (iceState != PeerConnection.IceConnectionState.CONNECTED
                    && iceState != PeerConnection.IceConnectionState.COMPLETED) {
                logDebug(controller,
                        "ICE连接状态不是CONNECTED/COMPLETED，触发重连或清理流程");
                controller.reportConnectionFailed();
            }
        }
    }

    // =========================================================================
    // Inner Class: PeerConnectionObserver
    // =========================================================================

    /**
     * PeerConnection.Observer implementation.
     *
     * Original: llllIllIl1 (inner class)
     *
     * Handles ICE connection state changes:
     *   - CONNECTED/COMPLETED → log success
     *   - FAILED → restart ICE, schedule cleanup
     *   - DISCONNECTED → schedule reconnect
     *
     * Also handles:
     *   - onIceCandidate → send to signaling server
     *   - onIceCandidateError → restart ICE on error 701/702
     *   - onDataChannel → register + setup
     *   - onTrack → log track info via reflection
     */
    public final class PeerConnectionObserver implements PeerConnection.Observer {

        @Override
        public void onSignalingChange(PeerConnection.SignalingState state) {
            logDebug(WebRTCController.this,
                    "PeerConnection信令状态变更: " + state);
        }

        @Override
        public void onIceConnectionChange(PeerConnection.IceConnectionState state) {
            logDebug(WebRTCController.this,
                    "ICE连接状态变更: " + state);

            if (state == PeerConnection.IceConnectionState.CONNECTED
                    || state == PeerConnection.IceConnectionState.COMPLETED) {
                logDebug(WebRTCController.this,
                        "ICE连接成功: " + state);
            } else if (state == PeerConnection.IceConnectionState.FAILED) {
                logDebug(WebRTCController.this,
                        "ICE连接失败，尝试restartIce并计划重连检查");
                if (peerConnection != null) {
                    peerConnection.restartIce();
                }
                // Schedule failed check
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    checkIceFailed(WebRTCController.this);
                }, 30000L); // 30s timeout
            } else if (state == PeerConnection.IceConnectionState.DISCONNECTED) {
                // Schedule reconnect attempt
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    checkIceDisconnected(WebRTCController.this);
                }, 10000L); // from config
            }
        }

        @Override
        public void onConnectionChange(PeerConnection.PeerConnectionState state) {
            logDebug(WebRTCController.this,
                    "PeerConnection连接状态变更: " + state);
        }

        @Override
        public void onIceConnectionReceivingChange(boolean receiving) {
            logDebug(WebRTCController.this,
                    "ICE接收状态变更: receiving=" + receiving);
        }

        @Override
        public void onIceGatheringChange(PeerConnection.IceGatheringState state) {
            logDebug(WebRTCController.this,
                    "ICE收集状态变更: " + state);
        }

        @Override
        public void onIceCandidate(IceCandidate candidate) {
            if (candidate == null) return;
            logDebug(WebRTCController.this,
                    "生成本地ICE Candidate，发送到信令服务器");
            try {
                // signalingConnection.sendIceCandidate(candidate.sdp, candidate.sdpMid, candidate.sdpMLineIndex)
                logDebug(WebRTCController.this,
                        "ICE Candidate已发送到信令服务器");
            } catch (Exception e) {
                logError(WebRTCController.this,
                        "发送ICE Candidate失败: " + e.getMessage());
            }
        }

        @Override
        public void onIceCandidateError(IceCandidateErrorEvent event) {
            logError(WebRTCController.this,
                    "ICE Candidate错误: code="
                            + (event != null ? event.errorCode : null)
                            + ", " + (event != null ? event.errorText : null));
            if (event != null && (event.errorCode == 701 || event.errorCode == 702)) {
                logDebug(WebRTCController.this,
                        "TURN/STUN错误(701/702)，触发ICE重启以尝试恢复连接");
                if (peerConnection != null) {
                    peerConnection.restartIce();
                }
            }
        }

        @Override
        public void onIceCandidatesRemoved(IceCandidate[] candidates) {
            logDebug(WebRTCController.this,
                    "ICE Candidates已移除");
        }

        @Override
        public void onAddStream(MediaStream stream) {}

        @Override
        public void onRemoveStream(MediaStream stream) {}

        @Override
        public void onDataChannel(DataChannel dc) {
            logDebug(WebRTCController.this,
                    "收到远程DataChannel: label=" + (dc != null ? dc.label() : null)
                            + ", state=" + (dc != null ? dc.state() : null));
            if (dc != null) {
                WebRTCController.this.dataChannel = dc;
                WebRTCController.this.setupDataChannel();
            }
        }

        @Override
        public void onRenegotiationNeeded() {
            logDebug(WebRTCController.this,
                    "需要重新协商(onRenegotiationNeeded)");
        }

        @Override
        public void onAddTrack(RtpReceiver receiver, MediaStream[] streams) {
            logDebug(WebRTCController.this, "添加远程轨道");
            if (receiver != null && receiver.track() != null) {
                receiver.track().kind();
            }
        }

        @Override
        public void onRemoveTrack(RtpReceiver receiver) {
            logDebug(WebRTCController.this, "移除远程轨道");
            if (receiver != null && receiver.track() != null) {
                receiver.track().kind();
            }
        }

        @Override
        public void onSelectedCandidatePairChanged(CandidatePairChangeEvent event) {
            try {
                logDebug(WebRTCController.this,
                        "选中的候选对已变更: " + event);
            } catch (Exception e) {
                logError(WebRTCController.this,
                        "处理候选对变更事件异常: " + e.getMessage());
            }
        }

        public void onStandardizedIceConnectionChange(
                PeerConnection.IceConnectionState state) {
            logDebug(WebRTCController.this,
                    "标准化ICE连接状态变更: " + state);
            onIceConnectionChange(state);
        }

        /**
         * Handles RTP transceiver track via reflection.
         * Tries to get direction via transceiver.getDirection().
         */
        public void onTrack(RtpTransceiver transceiver) {
            try {
                // Try reflection: transceiver.getClass().getMethod("direction").invoke(transceiver)
                String direction = null;
                try {
                    direction = transceiver.getClass()
                            .getMethod("direction")
                            .invoke(transceiver).toString();
                } catch (Exception e) {
                    direction = "unknown";
                }

                logDebug(WebRTCController.this,
                        "收到RTP Transceiver track");

                if (transceiver != null) {
                    RtpReceiver receiver = transceiver.getReceiver();
                    if (receiver != null) {
                        MediaStreamTrack track = receiver.track();
                        if (track != null) {
                            logDebug(WebRTCController.this,
                                    "track kind: " + track.kind());
                        }
                    }
                }
            } catch (Exception e) {
                logError(WebRTCController.this,
                        "处理onTrack事件异常: " + e.getMessage());
            }
        }

        /** Checks if ICE is still DISCONNECTED after timeout. */
        private static void checkIceDisconnected(WebRTCController controller) {
            try {
                PeerConnection pc = controller.peerConnection;
                if (pc != null && pc.iceConnectionState()
                        == PeerConnection.IceConnectionState.DISCONNECTED) {
                    logDebug(controller,
                            "ICE仍然处于DISCONNECTED状态，尝试restartIce");
                    pc.restartIce();
                }
            } catch (Throwable ignored) {}
        }

        /** Checks if ICE is still FAILED after timeout. */
        private static void checkIceFailed(WebRTCController controller) {
            try {
                PeerConnection pc = controller.peerConnection;
                if (pc != null && pc.iceConnectionState()
                        == PeerConnection.IceConnectionState.FAILED) {
                    logDebug(controller,
                            "ICE连接仍然处于FAILED状态，连接超时，开始清理");
                    controller.reportConnectionFailed();
                }
            } catch (Throwable ignored) {}
        }
    }

    // =========================================================================
    // Inner Class: AnswerSdpObserver (creates SDP answer)
    // =========================================================================

    /**
     * SDP observer for answer creation flow.
     *
     * Original: lIIIIlllllIlll1 (inner class implementing SdpObserver)
     *
     * onCreateSuccess → set local description with LocalDescriptionObserver
     * onCreateFailure → report failure and cleanup
     */
    public final class AnswerSdpObserver implements SdpObserver {

        @Override
        public void onCreateSuccess(SessionDescription sdp) {
            if (sdp == null) {
                logError(WebRTCController.this,
                        "SDP Answer创建返回null，流程异常终止");
                return;
            }
            logDebug(WebRTCController.this,
                    "SDP Answer创建成功, type=" + sdp.type);
            if (peerConnection != null) {
                peerConnection.setLocalDescription(
                        new LocalDescriptionObserver(WebRTCController.this, sdp), sdp);
            }
        }

        @Override
        public void onSetSuccess() {}

        @Override
        public void onCreateFailure(String error) {
            logError(WebRTCController.this,
                    "创建SDP Answer失败: " + error);
            reportConnectionFailed();
        }

        @Override
        public void onSetFailure(String error) {
            logError(WebRTCController.this,
                    "设置SDP失败(AnswerObserver): " + error);
            reportConnectionFailed();
        }
    }

    // =========================================================================
    // Inner Class: LocalDescriptionObserver
    // =========================================================================

    /**
     * SDP observer for setting local description.
     *
     * Original: lIIIIlllllIlll1.llllIIIIll1 (inner inner class)
     *
     * onSetSuccess → send SDP answer to signaling server, then add buffered ICE candidates
     */
    public static final class LocalDescriptionObserver implements SdpObserver {
        private final WebRTCController controller;
        private final SessionDescription sessionDescription;

        public LocalDescriptionObserver(WebRTCController controller,
                                         SessionDescription sdp) {
            this.controller = controller;
            this.sessionDescription = sdp;
        }

        @Override
        public void onSetSuccess() {
            logDebug(controller,
                    "LocalDescription设置成功，发送SDP Answer到信令服务器");
            // signalingConnection.sendSdpAnswer("answer", sdp.description)

            // Add all buffered ICE candidates
            if (controller.pendingIceCandidates.isEmpty()) {
                logDebug(controller, "没有缓存的ICE Candidates需要添加");
            } else {
                logDebug(controller,
                        "开始添加缓存的ICE Candidates");
                for (IceCandidate candidate : controller.pendingIceCandidates) {
                    try {
                        PeerConnection pc = controller.peerConnection;
                        if (pc != null) {
                            Boolean added = pc.addIceCandidate(candidate);
                            if (!Boolean.TRUE.equals(added)) {
                                logWarn(controller,
                                        "添加缓存ICE失败: " + candidate.sdp);
                            }
                        }
                    } catch (Exception e) {
                        logError(controller,
                                "添加缓存ICE异常: " + e.getMessage()
                                        + ", sdp=" + candidate.sdp);
                    }
                }
                logDebug(controller,
                        "缓存的ICE Candidates全部处理完成，已清空缓存");
                controller.pendingIceCandidates.clear();
            }
            controller.connectionEstablishedTime = SystemClock.elapsedRealtime();
        }

        @Override
        public void onCreateSuccess(SessionDescription sdp) {}

        @Override
        public void onCreateFailure(String error) {
            logError(controller,
                    "LocalDesc创建失败: " + error);
            controller.reportConnectionFailed();
        }

        @Override
        public void onSetFailure(String error) {
            logError(controller,
                    "LocalDesc设置失败: " + error);
            controller.reportConnectionFailed();
        }
    }

    // =========================================================================
    // Inner Class: RemoteDescriptionObserver
    // =========================================================================

    /**
     * SDP observer for setting remote description.
     *
     * Original: C0020IllIIlIIII1 (inner class implementing SdpObserver)
     *
     * onSetSuccess → create SDP answer, add buffered ICE candidates
     * onSetFailure → report failure
     */
    public final class RemoteDescriptionObserver implements SdpObserver {

        @Override
        public void onSetSuccess() {
            logDebug(WebRTCController.this,
                    "RemoteDescription设置成功，开始创建SDP Answer");
            createSdpAnswer();

            // Add all buffered ICE candidates
            if (pendingIceCandidates.isEmpty()) {
                logDebug(WebRTCController.this,
                        "RemoteDescription设置后无缓存的ICE Candidates");
            } else {
                logDebug(WebRTCController.this,
                        "开始添加RemoteDescription后缓存的ICE Candidates");
                for (IceCandidate candidate : pendingIceCandidates) {
                    try {
                        PeerConnection pc = peerConnection;
                        if (pc != null) {
                            Boolean added = pc.addIceCandidate(candidate);
                            if (!Boolean.TRUE.equals(added)) {
                                logWarn(WebRTCController.this,
                                        "添加缓存ICE失败: " + candidate.sdp);
                            }
                        }
                    } catch (Exception e) {
                        logError(WebRTCController.this,
                                "添加缓存ICE异常: " + e.getMessage()
                                        + ", sdp=" + candidate.sdp);
                    }
                }
                logDebug(WebRTCController.this,
                        "缓存ICE处理完成，清空缓存");
                pendingIceCandidates.clear();
            }
        }

        @Override
        public void onCreateSuccess(SessionDescription sdp) {}

        @Override
        public void onCreateFailure(String error) {
            logError(WebRTCController.this,
                    "Remote SDP创建失败: " + error);
            reportConnectionFailed();
        }

        @Override
        public void onSetFailure(String error) {
            logError(WebRTCController.this,
                    "RemoteDescription设置失败: " + error);
            reportConnectionFailed();
        }
    }

    // =========================================================================
    // Placeholder: SignalingEventListener interface
    // =========================================================================

    /*
     * SignalingEventListener → IlIlIIlIII1.lIIIIlllllIlll1.IlIllIlllIllI1
     *
     * Methods implemented:
     *   - onConnected() → lIIIIlllllIlll1()
     *   - onPong(Pong) → llllIIIIll1(Pong)
     *   - onSdpOffer(SDPOffer) → llllIIIIll1(SDPOffer)
     *   - onError(Exception) → llllIIIIll1(Exception)
     *   - onIceCandidate(ICECandidate) → see addIceCandidate
     */

    // =========================================================================
    // Logging Helpers (static)
    // =========================================================================

    private static void logDebug(WebRTCController c, String msg) {
        // Logger.debug(c.getLogTag(), msg)
    }

    private static void logDebug(String tag, String msg) {}
    private static void logInfo(WebRTCController c, String msg) {}
    private static void logInfo(String tag, String msg) {}
    private static void logWarn(WebRTCController c, String msg) {}
    private static void logError(WebRTCController c, String msg) {}
    private static void logError(String tag, String msg) {}
}

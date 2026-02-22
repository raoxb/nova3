package llIIIIlIlllIII1;

import IlIlIIlIII1.lIIIIlllllIlll1;
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
import c13.nim5.ez8.h5_proto.Log;
import c13.nim5.ez8.h5_proto.signaling.ICECandidate;
import c13.nim5.ez8.h5_proto.signaling.Pong;
import c13.nim5.ez8.h5_proto.signaling.SDPOffer;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import llIIIIlIlllIII1.IllIIlIIII1;
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
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;

/* loaded from: classes.dex */
public final class IllIIlIIII1 implements lIIIIlllllIlll1.IlIllIlllIllI1 {
    public static final int IIIllllllIIl1 = 2;
    public static final int IIlIlIIIllIIl1 = 3;
    public static final int IIllllIll1 = 1;
    public static final int IllllIl1 = 0;
    public static final int lIlIlIlIIl1 = 0;
    public static final int llIllIIIlI1 = 0;
    public static final boolean lllIlIllI1 = false;

    /* renamed from: IIIlIllIlI1, reason: collision with root package name */
    public final int f551IIIlIllIlI1;

    /* renamed from: IIlIIllll1, reason: collision with root package name */
    public final ScheduledExecutorService f552IIlIIllll1;

    /* renamed from: IIlIllIIll1, reason: collision with root package name */
    public EglBase.Context f553IIlIllIIll1;
    public int IIlIlllllllI1;

    /* renamed from: IIlllllIlll1, reason: collision with root package name */
    public final Handler f554IIlllllIlll1;
    public final long IlIIIIIlll1;

    /* renamed from: IlIIIIllllIlI1, reason: collision with root package name */
    public long f555IlIIIIllllIlI1;

    /* renamed from: IlIIIlIlIlIII1, reason: collision with root package name */
    public ScheduledFuture<?> f556IlIIIlIlIlIII1;

    /* renamed from: IlIIlllllI1, reason: collision with root package name */
    public boolean f557IlIIlllllI1;

    /* renamed from: IlIlIIIlIlIlll1, reason: collision with root package name */
    public long f558IlIlIIIlIlIlll1;

    /* renamed from: IlIlIIlIII1, reason: collision with root package name */
    public VideoTrack f559IlIlIIlIII1;

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public final AtomicBoolean f560IlIllIlllIllI1;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public DataChannel f561IlIlllIIlI1;

    /* renamed from: IlIllll1, reason: collision with root package name */
    public final WebView f562IlIllll1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public PeerConnection f563IllIIlIIII1;

    /* renamed from: IllIlIllll1, reason: collision with root package name */
    public float f564IllIlIllll1;

    /* renamed from: IllllIllllll1, reason: collision with root package name */
    public int f565IllllIllllll1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final lIllIlIll1.llllIIIIll1 f566lIIIIlllllIlll1;
    public long lIIIllllllIIII1;

    /* renamed from: lIIlIIIIlIlII1, reason: collision with root package name */
    public float f567lIIlIIIIlIlII1;

    /* renamed from: lIIlllIIIlllII1, reason: collision with root package name */
    public float f568lIIlllIIIlllII1;
    public ScheduledFuture<?> lIlIIIllll1;
    public ScheduledFuture<?> lIlIlIlI1;

    /* renamed from: lIllIIIlIl1, reason: collision with root package name */
    public llIIIIlIlllIII1.llllIllIl1 f569lIllIIIlIl1;

    /* renamed from: lIllIlIll1, reason: collision with root package name */
    public float f570lIllIlIll1;

    /* renamed from: lIlllIIIII1, reason: collision with root package name */
    public final int f571lIlllIIIII1;

    /* renamed from: llIIIIlIlllIII1, reason: collision with root package name */
    public float f572llIIIIlIlllIII1;
    public final int llIIIlIIIlIII1;

    /* renamed from: llIIllIl1, reason: collision with root package name */
    public final int f573llIIllIl1;

    /* renamed from: llIlIIlll1, reason: collision with root package name */
    public final AtomicBoolean f574llIlIIlll1;
    public final long llIllllIlI1;

    /* renamed from: lllIlIIIlI1, reason: collision with root package name */
    public final int f575lllIlIIIlI1;

    /* renamed from: lllIlIlllI1, reason: collision with root package name */
    public final AtomicBoolean f576lllIlIlllI1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final String f577llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public PeerConnectionFactory f578llllIllIl1;

    /* renamed from: lllllIllIl1, reason: collision with root package name */
    public final CopyOnWriteArrayList<IceCandidate> f579lllllIllIl1;

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public VideoSource f580llllllIlIIIlll1;
    public static final String lIIlIIIllII1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-102, -39, 90, 38, 74, -70, -97}, new byte[]{-7, -74, 52, 82, 56, -43, -13, 102});
    public static final llllIIIIll1 IIIlIllIl1 = new llllIIIIll1(null);

    public static final class IlIlllIIlI1 implements DataChannel.Observer {

        public /* synthetic */ class llllIIIIll1 {

            /* renamed from: llllIIIIll1, reason: collision with root package name */
            public static final /* synthetic */ int[] f582llllIIIIll1;

            static {
                int[] iArr = new int[DataChannel.State.values().length];
                try {
                    iArr[DataChannel.State.OPEN.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[DataChannel.State.CLOSED.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[DataChannel.State.CLOSING.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                f582llllIIIIll1 = iArr;
            }
        }

        public IlIlllIIlI1() {
        }

        public static final void llllIIIIll1(IllIIlIIII1 illIIlIIII1) {
            if (illIIlIIII1.f576lllIlIlllI1.get()) {
                return;
            }
            PeerConnection peerConnection = illIIlIIII1.f563IllIIlIIII1;
            PeerConnection.IceConnectionState iceConnectionState = peerConnection != null ? peerConnection.iceConnectionState() : null;
            illIIlIIII1.IlIllll1();
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllliiiill1.llllIIIIll1(new byte[]{32, -48, 116, -115, -126, 103, -76, -73, 92, -84, 69, -8, -22, 76, -18, -34, 81, -24, 33, -5, -127, 47, -2, -73, 32, -38, 97, 34, 76, -116, -75, -120, 88, -93, 74, -50, -24, 67, -21, -47, 70, -60, -2, 75}, new byte[]{-58, 69, -60, 107, 15, -55, 93, 55});
            Objects.toString(iceConnectionState);
            if (iceConnectionState == PeerConnection.IceConnectionState.CONNECTED || iceConnectionState == PeerConnection.IceConnectionState.COMPLETED) {
                return;
            }
            illIIlIIII1.IlIllll1();
            lllliiiill1.llllIIIIll1(new byte[]{-27, -90, 70, -119, 87, 20, -20, 123, -103, -38, 119, -4, 63, 13, -73, 30, -122, ByteCompanionObject.MIN_VALUE, 31, -8, 119, 94, -67, 111, -21, -116, 104, -119, 84, 31, -30, 113, -75, -43, 118, -18, 63, 6, -121, 30, -69, -117, 25, -45, 86, 95, -126, 125, -26, -105, 113, -122, 93, 55, -19, 68, -99}, new byte[]{3, 51, -10, 111, -38, -70, 5, -5});
            illIIlIIII1.llIIllIl1();
        }

        public void onBufferedAmountChange(long j) {
        }

        public void onMessage(DataChannel.Buffer buffer) {
            Intrinsics.checkNotNullParameter(buffer, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{116, -97, 66, -61, 73, 60}, new byte[]{22, -22, 36, -91, 44, 78, -86, -90}));
            IllIIlIIII1.this.llllIIIIll1(buffer);
        }

        public void onStateChange() {
            DataChannel dataChannel = IllIIlIIII1.this.f561IlIlllIIlI1;
            DataChannel.State state = dataChannel != null ? dataChannel.state() : null;
            IllIIlIIII1.this.IlIllll1();
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllliiiill1.llllIIIIll1(new byte[]{-23, 19, -101, -31, 47, 22, -11, 48, -107, 111, -86, -108, 69, 50, -86, 86, -113, 7, -50, -120, 58, 93, -112, 38, 53, -90}, new byte[]{15, -122, 43, 7, -94, -72, 28, -80});
            Objects.toString(state);
            int i = state == null ? -1 : llllIIIIll1.f582llllIIIIll1[state.ordinal()];
            if (i == 1) {
                IllIIlIIII1.this.IlIllll1();
                lllliiiill1.llllIIIIll1(new byte[]{28, 66, -2, 97, -100, -14, 22, 94, 96, 62, -49, 20, -12, -21, 77, 56, 115, 68, -85, 59, -111}, new byte[]{-6, -41, 78, -121, 17, 92, -1, -34});
                IllIIlIIII1 illIIlIIII1 = IllIIlIIII1.this;
                illIIlIIII1.IIlIlllllllI1 = 0;
                illIIlIIII1.lIlllIIIII1();
                return;
            }
            if (i == 2 || i == 3) {
                IllIIlIIII1.this.IlIllll1();
                lllliiiill1.llllIIIIll1(new byte[]{-121, 44, 12, 45, 54, -13, 60, 72, -5, 80, 61, 88, 94, -22, 103, 45, -28, 10, 85, 92, 22, -69, 93, 94, -121, 20, 31, 46, 39, -11, 48, 77, -46, 80, 43, 102}, new byte[]{97, -71, -68, -53, -69, 93, -43, -56});
                if (IllIIlIIII1.this.f576lllIlIlllI1.get()) {
                    return;
                }
                Handler handler = new Handler(Looper.getMainLooper());
                final IllIIlIIII1 illIIlIIII12 = IllIIlIIII1.this;
                handler.postDelayed(new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$IlIlllIIlI1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        IllIIlIIII1.IlIlllIIlI1.llllIIIIll1(IllIIlIIII1.this);
                    }
                }, 1000L);
            }
        }
    }

    /* renamed from: llIIIIlIlllIII1.IllIIlIIII1$IllIIlIIII1, reason: collision with other inner class name */
    public static final class C0020IllIIlIIII1 implements SdpObserver {
        public C0020IllIIlIIII1() {
        }

        public void onCreateFailure(String str) {
            String IlIllll12 = IllIIlIIII1.this.IlIllll1();
            StringBuilder sb = new StringBuilder();
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll12, llllIIIIll1.llllIIIIll1.llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1, new byte[]{19, -47, -47, 68, 119, -18, 77, 108, -123, 46, 47, -45, 41, -16, -67, -22, 66, -4, 112, -127}, new byte[]{-10, 89, 74, -95, -52, 84, 12, 2}, sb, str));
            IllIIlIIII1.this.llIIllIl1();
        }

        public void onCreateSuccess(SessionDescription sessionDescription) {
        }

        public void onSetFailure(String str) {
            String IlIllll12 = IllIIlIIII1.this.IlIllll1();
            StringBuilder sb = new StringBuilder();
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll12, llllIIIIll1.llllIIIIll1.llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1, new byte[]{-109, -37, 30, 64, 40, 34, 123, -76, -25, -110, 8, 44, 115, 3, 28, -29, -60, -59, 69, 3, 36, 100, 39, -82, 65, 85}, new byte[]{123, 117, -96, -89, -107, -116, -109, 11}, sb, str));
            IllIIlIIII1.this.llIIllIl1();
        }

        public void onSetSuccess() {
            IllIIlIIII1.this.IlIllll1();
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllliiiill1.llllIIIIll1(new byte[]{12, 31, 96, -120, 81, -62, 47, 63, 56, 28, 104, -107, 5, -44, 106, 36, 126, 9, 120, -124, 70, -62, 124, 35, 56, 15, 97, -117, 92}, new byte[]{94, 122, 13, -25, 37, -89, 15, 80});
            IllIIlIIII1.this.IlIlIIlIII1();
            if (IllIIlIIII1.this.f579lllllIllIl1.isEmpty()) {
                IllIIlIIII1.this.IlIllll1();
                lllliiiill1.llllIIIIll1(new byte[]{-121, 87, -5, -92, 55, 119, -37, -44, -14, 0, -9, -38, 76, 100, -72, 33, 34, -96, -65, -62, 50, 23, -68, -31, -119, 101, -33, -85, 55, 126, -44, -50, -32, 0, -32, -42, 76, 106, -108}, new byte[]{97, -27, 90, 66, -85, -2, 60, 104});
                return;
            }
            IllIIlIIII1.this.IlIllll1();
            lllliiiill1.llllIIIIll1(new byte[]{-40, -73, -122, 24, 88, -36, 22, 18, -87, -20, -110, 85, -33}, new byte[]{61, 11, 6, -3, -1, 87, -13, -88});
            IllIIlIIII1.this.f579lllllIllIl1.size();
            lllliiiill1.llllIIIIll1(new byte[]{-23, -71, 101, 45, -95, -119, -95, 113, 100, -59, 58, 29, -62, 124, 113, -47, 44, -35, 68, 110, -58, -68, -38, 20, 76}, new byte[]{-55, 93, -35, -121, 70, 53, 50, -108});
            IllIIlIIII1 illIIlIIII1 = IllIIlIIII1.this;
            for (IceCandidate iceCandidate : illIIlIIII1.f579lllllIllIl1) {
                try {
                    PeerConnection peerConnection = illIIlIIII1.f563IllIIlIIII1;
                    if (!Intrinsics.areEqual(peerConnection != null ? Boolean.valueOf(peerConnection.addIceCandidate(iceCandidate)) : null, Boolean.TRUE)) {
                        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.WARN, illIIlIIII1.IlIllll1(), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-44, 67, 43, 121, -49, -46, -80, 98, 119, 17, 16, 5, -84, -14, 112, -55, -78, 113, 117, 56, -12, -102, 77, -124, 8, -44}, new byte[]{50, -12, -112, -100, 69, 114, -7, 33}) + iceCandidate.sdp);
                    }
                } catch (Exception e) {
                    String IlIllll12 = illIIlIIII1.IlIllll1();
                    StringBuilder sb = new StringBuilder();
                    IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll12, sb.append(lllliiiill12.llllIIIIll1(new byte[]{-14, 36, -126, 10, 56, -64, 115, -122, 81, 118, -71, 118, 91, -32, -77, 45, -108, 22, -36, 83, 48, -123, -126, 125, 46, -77}, new byte[]{20, -109, 57, -17, -78, 96, 58, -59})).append(e.getMessage()).append(lllliiiill12.llllIIIIll1(new byte[]{36, 117, -11, -17, 116, -104, 18, 11, -32, -43, -107, 85, -51}, new byte[]{8, 85, 16, 111, -19, 113, -110, -126})).append(iceCandidate.sdp).toString());
                }
            }
            IllIIlIIII1.this.IlIllll1();
            IllIIlIIII1.llllIIIIll1 lllliiiill13 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllliiiill13.llllIIIIll1(new byte[]{-81, -118, -52, -112, -88, 58, -118, 18, -39, -43, -11, -17, 117, -47, 40, 75, -54, -87, -79, -9, -75, 122, -19, 43, -81, -98, -44, -111, -76, 2, 87, -114, -84, -72, -56, -110, -74, 13, 80}, new byte[]{74, 48, 88, 119, 60, -110, 109, -82});
            lllliiiill13.llllIIIIll1(new byte[]{95, 97, -99, -95, -113, 84, -35, -121, 78}, new byte[]{115, 65, 120, 5, 62, -68, 105, 34});
            IllIIlIIII1.this.f579lllllIllIl1.clear();
        }
    }

    public static final class lIIIIlllllIlll1 implements SdpObserver {

        public static final class llllIIIIll1 implements SdpObserver {

            /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
            public final /* synthetic */ SessionDescription f585lIIIIlllllIlll1;

            /* renamed from: llllIIIIll1, reason: collision with root package name */
            public final /* synthetic */ IllIIlIIII1 f586llllIIIIll1;

            public llllIIIIll1(IllIIlIIII1 illIIlIIII1, SessionDescription sessionDescription) {
                this.f586llllIIIIll1 = illIIlIIII1;
                this.f585lIIIIlllllIlll1 = sessionDescription;
            }

            public void onCreateFailure(String str) {
                String IlIllll12 = this.f586llllIIIIll1.IlIllll1();
                StringBuilder sb = new StringBuilder();
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll12, llllIIIIll1.llllIIIIll1.llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1, new byte[]{-101, 33, -110, -121, 125, 60, -20, -88, -46, 76, -107, -46, 32, 9, -123, -36, -63, 25, -20, -58, 119, 110, -66, -111, 68, -119}, new byte[]{126, -87, 9, 98, -58, -122, 10, 52}, sb, str));
                this.f586llllIIIIll1.llIIllIl1();
            }

            public void onCreateSuccess(SessionDescription sessionDescription) {
            }

            public void onSetFailure(String str) {
                String IlIllll12 = this.f586llllIIIIll1.IlIllll1();
                StringBuilder sb = new StringBuilder();
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll12, llllIIIIll1.llllIIIIll1.llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1, new byte[]{53, 90, -91, -28, 83, -119, 60, 87, 113, 17, -121, -77, 8, -88, 85, 35, 98, 68, -2, -89, 95, -49, 110, 110, -25, -44}, new byte[]{-35, -12, 27, 3, -18, 39, -38, -53}, sb, str));
                this.f586llllIIIIll1.llIIllIl1();
            }

            public void onSetSuccess() {
                this.f586llllIIIIll1.IlIllll1();
                IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                lllliiiill1.llllIIIIll1(new byte[]{-80, -93, 51, 114, -69, 36, 90, -31, -113, -69, 53, 97, -9, 119, 94, -5, -36, -65, 37, 112, -76, 97, 72, -4, -102, -71, 60, ByteCompanionObject.MAX_VALUE, -82}, new byte[]{-4, -52, 80, 19, -41, 4, 59, -113});
                IlIlIIlIII1.lIIIIlllllIlll1 IlIlllIIlI12 = IlIlllIIlI1.lIIIIlllllIlll1.IlIllIlllIllI1().IlIlllIIlI1();
                if (IlIlllIIlI12 != null) {
                    IlIlllIIlI12.llllIIIIll1(lllliiiill1.llllIIIIll1(new byte[]{64, 38, -4, -87, 9, 74}, new byte[]{33, 72, -113, -34, 108, 56, 101, -15}), this.f585lIIIIlllllIlll1.description);
                }
                this.f586llllIIIIll1.IlIllll1();
                lllliiiill1.llllIIIIll1(new byte[]{43, ByteCompanionObject.MIN_VALUE, 25, 96, -4, -75, -2, -79, 25, -117, 4, 115, -16, -87}, new byte[]{120, -27, 119, 4, -107, -37, -103, -111});
                this.f586llllIIIIll1.lIIIllllllIIII1 = SystemClock.elapsedRealtime();
            }
        }

        public lIIIIlllllIlll1() {
        }

        public void onCreateFailure(String str) {
            String IlIllll12 = IllIIlIIII1.this.IlIllll1();
            StringBuilder sb = new StringBuilder();
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll12, llllIIIIll1.llllIIIIll1.llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1, new byte[]{-107, -50, 75, 79, 79, -123, 66, 11, 3, 49, -75, -40, 17, -101, -78, -115, -60, -29, -22, -118}, new byte[]{112, 70, -48, -86, -12, 63, 3, 101}, sb, str));
            IllIIlIIII1.this.llIIllIl1();
        }

        public void onCreateSuccess(SessionDescription sessionDescription) {
            if (sessionDescription == null) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IllIIlIIII1.this.IlIllll1(), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{55, 101, 119, -57, -125, 70, 83, 91, -19, -18, -65, 10, 3, -112, 7, 59, -62, -82, 62, -112, 1, -113, 37, 53, -24, -105, -32, 8, 92, 90, -61, -65, 26}, new byte[]{118, 11, 4, -80, -26, 52, -74, -45}));
                return;
            }
            IllIIlIIII1.this.IlIllll1();
            IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{54, 9, 16, -125, ByteCompanionObject.MAX_VALUE, 107, -103, 27, -20, -126, -40, 78, -4, -111, -20, 118, -3, -8, 89, -44}, new byte[]{119, 103, 99, -12, 26, 25, 124, -109});
            Objects.toString(sessionDescription.type);
            IllIIlIIII1 illIIlIIII1 = IllIIlIIII1.this;
            PeerConnection peerConnection = illIIlIIII1.f563IllIIlIIII1;
            if (peerConnection != null) {
                peerConnection.setLocalDescription(new llllIIIIll1(illIIlIIII1, sessionDescription), sessionDescription);
            }
        }

        public void onSetFailure(String str) {
            String IlIllll12 = IllIIlIIII1.this.IlIllll1();
            StringBuilder sb = new StringBuilder();
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll12, llllIIIIll1.llllIIIIll1.llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1, new byte[]{66, -76, 113, -97, 50, -59, -24, 120, -39, 109, -86, 10, 106, -49, 24, -2, 30, -65, -11, 88}, new byte[]{-86, 26, -49, 120, -113, 107, -87, 22}, sb, str));
            IllIIlIIII1.this.llIIllIl1();
        }

        public void onSetSuccess() {
        }
    }

    public static final class llllIIIIll1 {
        public llllIIIIll1() {
        }

        public llllIIIIll1(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public static final class llllIllIl1 implements PeerConnection.Observer {

        public /* synthetic */ class llllIIIIll1 {

            /* renamed from: llllIIIIll1, reason: collision with root package name */
            public static final /* synthetic */ int[] f588llllIIIIll1;

            static {
                int[] iArr = new int[PeerConnection.IceConnectionState.values().length];
                try {
                    iArr[PeerConnection.IceConnectionState.CONNECTED.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[PeerConnection.IceConnectionState.COMPLETED.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[PeerConnection.IceConnectionState.FAILED.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[PeerConnection.IceConnectionState.DISCONNECTED.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                f588llllIIIIll1 = iArr;
            }
        }

        public llllIllIl1() {
        }

        public static final void lIIIIlllllIlll1(IllIIlIIII1 illIIlIIII1) {
            try {
                PeerConnection peerConnection = illIIlIIII1.f563IllIIlIIII1;
                if ((peerConnection != null ? peerConnection.iceConnectionState() : null) == PeerConnection.IceConnectionState.DISCONNECTED) {
                    illIIlIIII1.IlIllll1();
                    IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{50, -71, -47, -116, -45, -25, -109, -58, -34, 30, 47, -23, -117, -3, -61, -82, -19, 87, 113, -40, -20, -106, -55, -60, -98, 74, 9, -116, -61, -20, -100, -49, -10, 31, 4, -53, 37, 58, 48}, new byte[]{123, -6, -108, 100, 108, 121, 117, 72});
                    PeerConnection peerConnection2 = illIIlIIII1.f563IllIIlIIII1;
                    if (peerConnection2 != null) {
                        peerConnection2.restartIce();
                    }
                }
            } catch (Throwable unused) {
            }
        }

        public static final void llllIIIIll1(IllIIlIIII1 illIIlIIII1) {
            try {
                PeerConnection peerConnection = illIIlIIII1.f563IllIIlIIII1;
                if ((peerConnection != null ? peerConnection.iceConnectionState() : null) == PeerConnection.IceConnectionState.FAILED) {
                    illIIlIIII1.IlIllll1();
                    IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{34, -42, -111, -41, 122, -93, 2, -47, -50, 113, 111, -78, 34, -71, 82, -70, -49, 36, 60, -117, 96, -46, 88, -45, -114, 59, 93, -39, 75, -81, 1, -15, -25, 112, 81, -105, 44, -70, 105, -73, -44, 11}, new byte[]{107, -107, -44, 63, -59, 61, -28, 95});
                    illIIlIIII1.llIIllIl1();
                }
            } catch (Throwable unused) {
            }
        }

        public void onAddStream(MediaStream mediaStream) {
        }

        public void onAddTrack(RtpReceiver rtpReceiver, MediaStream[] mediaStreamArr) {
            MediaStreamTrack track;
            IllIIlIIII1.this.IlIllll1();
            IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-63, -111, -14, 41, 99, 46, -30, -19, -113, -49, -56, 95, -45, -82}, new byte[]{39, 38, 73, -52, -23, -114, 10, 80});
            if (rtpReceiver == null || (track = rtpReceiver.track()) == null) {
                return;
            }
            track.kind();
        }

        public void onConnectionChange(PeerConnection.PeerConnectionState peerConnectionState) {
            IllIIlIIII1.this.IlIllll1();
            IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-51, 21, 23, -94, -13, -109, -31, -83, -8, 19, 6, -71, -33, -110, -81, 36, 23, -58, -108, 80, 49, 25, 0, 91, 120, -4, -28, -22, -112}, new byte[]{-99, 112, 114, -48, -80, -4, -113, -61});
            Objects.toString(peerConnectionState);
        }

        public void onDataChannel(DataChannel dataChannel) {
            IllIIlIIII1.this.IlIllll1();
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllliiiill1.llllIIIIll1(new byte[]{-67, 62, 35, -93, 73, 116, -18, 120, -57, 77, 61, -51, 39, 81, -74, 33, -42, 4, 124, -58, 91, 45, -121, 84, 97, -118}, new byte[]{91, -86, -107, 70, -63, -60, 6, -57});
            if (dataChannel != null) {
                dataChannel.label();
            }
            lllliiiill1.llllIIIIll1(new byte[]{-98, 106, -12, -43, -110, 101, 68, -60, -124, 98}, new byte[]{-66, 66, 19, 95, 36, -125, -60, 69});
            Objects.toString(dataChannel != null ? dataChannel.state() : null);
            if (dataChannel != null) {
                IllIIlIIII1 illIIlIIII1 = IllIIlIIII1.this;
                illIIlIIII1.f561IlIlllIIlI1 = dataChannel;
                illIIlIIII1.lIIlIIIIlIlII1();
            }
        }

        public void onIceCandidate(IceCandidate iceCandidate) {
            if (iceCandidate == null) {
                return;
            }
            IllIIlIIII1.this.IlIllll1();
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllliiiill1.llllIIIIll1(new byte[]{-75, -72, -8, 105, 103, -109, -98, 46, -1, -55, -46, 60, -49, 81, 29, -34, 50, 85, 110, -59, -84, 102, 88, 87, -45, -75, -89, 12, 102, 25, 88}, new byte[]{83, 44, 78, -116, -17, 35, 120, -78});
            try {
                IlIlIIlIII1.lIIIIlllllIlll1 IlIlllIIlI12 = IlIlllIIlI1.lIIIIlllllIlll1.IlIllIlllIllI1().IlIlllIIlI1();
                if (IlIlllIIlI12 != null) {
                    IlIlllIIlI12.llllIIIIll1(iceCandidate.sdp, iceCandidate.sdpMid, iceCandidate.sdpMLineIndex);
                }
                IllIIlIIII1.this.IlIllll1();
                lllliiiill1.llllIIIIll1(new byte[]{76, -100, -5, 112, -45, 75, 10, 6, 59, -3, -21, 20, 121, -90, -118, -27, -53, 109, 75, -36, 26, -111, -49, 108, 42, -115, -126, 21, -48, 60, 111, 12, 79, -100, -37, 113, -26, 117, 11, 50, 14, -14, -9, 24, -68, 94, 78, 108, 51, -68}, new byte[]{-86, 20, 107, -107, 89, -44, -17, -119});
            } catch (Exception e) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IllIIlIIII1.this.IlIllll1(), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-31, -46, -81, -11, 55, -95, -77, -53, 71, 24, 30, -7, 55, -71, 122, 2, -115, -75, -66, -103, 82, -124, 34, 106, -80, -8, 4, 60}, new byte[]{4, 93, 62, 28, -73, 32, -109, -126}))));
            }
        }

        public void onIceCandidateError(IceCandidateErrorEvent iceCandidateErrorEvent) {
            String IlIllll12 = IllIIlIIII1.this.IlIllll1();
            StringBuilder sb = new StringBuilder();
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll12, sb.append(lllliiiill1.llllIIIIll1(new byte[]{59, 40, 41, 7, -20, 66, -121, 27, -5, -125, -20, 103, -123, 79, -9, 115, -35, -60, 86, -62}, new byte[]{114, 107, 108, -30, 108, -37, 110, -101})).append(iceCandidateErrorEvent != null ? Integer.valueOf(iceCandidateErrorEvent.errorCode) : null).append(lllliiiill1.llllIIIIll1(new byte[]{17, -101, -31}, new byte[]{49, -74, -63, 76, 21, -111, 20, 98})).append(iceCandidateErrorEvent != null ? iceCandidateErrorEvent.errorText : null).toString());
            if ((iceCandidateErrorEvent == null || iceCandidateErrorEvent.errorCode != 701) && (iceCandidateErrorEvent == null || iceCandidateErrorEvent.errorCode != 702)) {
                return;
            }
            IllIIlIIII1.this.IlIllll1();
            lllliiiill1.llllIIIIll1(new byte[]{3, -126, 86, 57, 1, 2, 14, 43, 85, -60, 74, 111, 81, 20, 107, 68, 126, -103, 51, 90, 7, 96, ByteCompanionObject.MAX_VALUE, 58, 13, -114, 121, 48, 8, 5, 14, 19, 120, -55, 121, 74, 93, 14, 102, 70, 117, -114, -97, -100, -15}, new byte[]{-27, 33, -42, -33, -76, -119, -21, -93});
            PeerConnection peerConnection = IllIIlIIII1.this.f563IllIIlIIII1;
            if (peerConnection != null) {
                peerConnection.restartIce();
            }
        }

        public void onIceCandidatesRemoved(IceCandidate[] iceCandidateArr) {
            IllIIlIIII1.this.IlIllll1();
            IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{45, 80, -45, 72, 55, -106, -58, 6, -28, -102, 126, -54, 121, -15, -8, 84, -115, -118, 50}, new byte[]{100, 19, -106, 104, -46, 22, 95, -17});
        }

        public void onIceConnectionChange(PeerConnection.IceConnectionState iceConnectionState) {
            IllIIlIIII1.this.IlIllll1();
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllliiiill1.llllIIIIll1(new byte[]{-104, 81, 61, -102, 16, 47, 38, -47, 95, -73, -97, 48, 78, 118, 56, -74, 52, -99, -32, 95, 116, 6, -126, 23}, new byte[]{-47, 18, 120, -70, -8, -112, -72, 55});
            Objects.toString(iceConnectionState);
            int i = iceConnectionState == null ? -1 : llllIIIIll1.f588llllIIIIll1[iceConnectionState.ordinal()];
            if (i == 1 || i == 2) {
                IllIIlIIII1.this.IlIllll1();
                lllliiiill1.llllIIIIll1(new byte[]{-9, 111, 25, -22, 70, 85, 102, -102, 48, -119, -70, 66, 62, 15, 114, -29, -98, 4}, new byte[]{-66, 44, 92, -54, -82, -22, -8, 124});
                Objects.toString(iceConnectionState);
                return;
            }
            if (i != 3) {
                if (i != 4) {
                    return;
                }
                Handler handler = new Handler(Looper.getMainLooper());
                final IllIIlIIII1 illIIlIIII1 = IllIIlIIII1.this;
                handler.postDelayed(new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$llllIllIl1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        IllIIlIIII1.llllIllIl1.lIIIIlllllIlll1(IllIIlIIII1.this);
                    }
                }, IlIlIIlIII1.IllIIlIIII1.f165IlIllll1);
                return;
            }
            IllIIlIIII1.this.IlIllll1();
            lllliiiill1.llllIIIIll1(new byte[]{117, -125, -58, 58, 48, ByteCompanionObject.MIN_VALUE, -15, -109, -103, 37, 39, 99, 103, -86, -78, -14, ByteCompanionObject.MIN_VALUE, 76, 102, 98, 18, -10, -72, -120, -43, 71, 14, 55, 31, -79, 94, 94, 121}, new byte[]{60, -64, -125, -46, -113, 30, 23, 29});
            PeerConnection peerConnection = IllIIlIIII1.this.f563IllIIlIIII1;
            if (peerConnection != null) {
                peerConnection.restartIce();
            }
            Handler handler2 = new Handler(Looper.getMainLooper());
            final IllIIlIIII1 illIIlIIII12 = IllIIlIIII1.this;
            handler2.postDelayed(new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$llllIllIl1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    IllIIlIIII1.llllIllIl1.llllIIIIll1(IllIIlIIII1.this);
                }
            }, llllllIlIIIlll1.llllIIIIll1.f745llllllIlIIIlll1);
        }

        public void onIceConnectionReceivingChange(boolean z) {
            IllIIlIIII1.this.IlIllll1();
            IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-86, -42, 20, 1, 0, -117, 24, -8, 109, 48, -73, -81, 77, -46, 18, -88, 4, 31, -25, -57, 104, -75, 99, -111, 123, 112, -35, -73, -46, 20}, new byte[]{-29, -107, 81, 33, -24, 52, -122, 30});
        }

        public void onIceGatheringChange(PeerConnection.IceGatheringState iceGatheringState) {
            IllIIlIIII1.this.IlIllll1();
            IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{91, 105, 28, -57, 57, 55, -59, -89, 119, 88, 48, -119, 57, 118, -62, -69, 115, 94, 60, -57, 61, 62, -48, -95, 117, 79, 61, -35, 126}, new byte[]{18, 42, 89, -25, 94, 86, -79, -49});
            Objects.toString(iceGatheringState);
        }

        public void onRemoveStream(MediaStream mediaStream) {
        }

        public void onRemoveTrack(RtpReceiver rtpReceiver) {
            MediaStreamTrack track;
            IllIIlIIII1.this.IlIllll1();
            IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{40, -60, -75, -86, -12, -16, -2, 120, 103, -118, -113, -48, 87, 116}, new byte[]{-49, 99, 14, 67, 109, 84, 22, -59});
            if (rtpReceiver == null || (track = rtpReceiver.track()) == null) {
                return;
            }
            track.kind();
        }

        public void onRenegotiationNeeded() {
            IllIIlIIII1.this.IlIllll1();
            IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{97, 123, 26, -1, 96, 95, 124, 33, 82, 106, 29, -11, 105, 16, 102, 45, 86, 122, 17, -2}, new byte[]{51, 30, 116, -102, 7, 48, 8, 72});
        }

        public void onSelectedCandidatePairChanged(CandidatePairChangeEvent candidatePairChangeEvent) {
            try {
                IllIIlIIII1.this.IlIllll1();
                IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                lllliiiill1.llllIIIIll1(new byte[]{-70, 19, -67, 93, -117, 101, -90, -31, -41, 118, -76, 32, -38, 72, -56, -109, -45, 22, -47, 22, -118, 45, -50, -22, -76, 7, -85, 92, -68, 80, -92, -9, -59, -87, 20}, new byte[]{83, -109, 52, -71, 51, -56, 65, 123});
                lllliiiill1.llllIIIIll1(new byte[]{-119, -25, -31, ByteCompanionObject.MAX_VALUE}, new byte[]{-87, -54, -33, 95, -8, ByteCompanionObject.MIN_VALUE, -87, 3});
            } catch (Exception e) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IllIIlIIII1.this.IlIllll1(), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-30, -48, 54, 102, -89, 106, 99, 114, -98, -99, 50, 8, -33, 108, 3, 23, -88, -51, 87, 14, -81, 9, 10, 100, -31, -29, 4, 100, -80, 86, 111, 102, -98, 78, -110}, new byte[]{7, 116, -78, -127, 55, -20, -122, -14}))));
            }
        }

        public void onSignalingChange(PeerConnection.SignalingState signalingState) {
            IllIIlIIII1.this.IlIllll1();
            IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-127, 67, 35, -33, -123, -67, -38, 72, -75, 10, 55, -59, -123, -91, -42, 6, -79, 66, 37, -33, -125, -76, -41, 28, -14}, new byte[]{-46, 42, 68, -79, -28, -47, -77, 38});
            Objects.toString(signalingState);
        }

        public void onStandardizedIceConnectionChange(PeerConnection.IceConnectionState iceConnectionState) {
            IllIIlIIII1.this.IlIllll1();
            IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-73, 83, -102, 55, -73, 101, 68, 59, -57, -45, 84, -111, 117, -61, 73, 8, -49, 21, -109, 119, -41, 105, 23, 81, -47, 114, -8, 93, -88, 6, 45, 33, 107, -45}, new byte[]{81, -13, 29, -46, 48, -29, -95, -73});
            Objects.toString(iceConnectionState);
            onIceConnectionChange(iceConnectionState);
        }

        /* JADX WARN: Code restructure failed: missing block: B:31:0x002b, code lost:
        
            if (r2.toString() != null) goto L14;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onTrack(org.webrtc.RtpTransceiver r7) {
            /*
                r6 = this;
                r0 = 0
                r1 = 8
                if (r7 == 0) goto L2d
                java.lang.Class r2 = r7.getClass()     // Catch: java.lang.Exception -> L3e
                r3 = 9
                byte[] r3 = new byte[r3]     // Catch: java.lang.Exception -> L3e
                r3 = {x00ec: FILL_ARRAY_DATA , data: [100, -81, -95, 87, 36, 125, 8, -127, 108} // fill-array     // Catch: java.lang.Exception -> L3e
                byte[] r4 = new byte[r1]     // Catch: java.lang.Exception -> L3e
                r4 = {x00f6: FILL_ARRAY_DATA , data: [9, -54, -59, 62, 69, 41, 113, -15} // fill-array     // Catch: java.lang.Exception -> L3e
                IllIIlIIII1.llllIIIIll1 r5 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1     // Catch: java.lang.Exception -> L3e
                java.lang.String r3 = r5.llllIIIIll1(r3, r4)     // Catch: java.lang.Exception -> L3e
                java.lang.reflect.Method r2 = r2.getMethod(r3, r0)     // Catch: java.lang.Exception -> L3e
                if (r2 == 0) goto L2d
                java.lang.Object r2 = r2.invoke(r7, r0)     // Catch: java.lang.Exception -> L3e
                if (r2 == 0) goto L2d
                java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> L3e
                if (r2 != 0) goto L4e
            L2d:
                r2 = 7
                byte[] r2 = new byte[r2]     // Catch: java.lang.Exception -> L3e
                r2 = {x00fe: FILL_ARRAY_DATA , data: [-75, 58, 78, 122, 112, 29, 16} // fill-array     // Catch: java.lang.Exception -> L3e
                byte[] r3 = new byte[r1]     // Catch: java.lang.Exception -> L3e
                r3 = {x0106: FILL_ARRAY_DATA , data: [-64, 84, 37, 20, 31, 106, 126, 51} // fill-array     // Catch: java.lang.Exception -> L3e
                IllIIlIIII1.llllIIIIll1 r4 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1     // Catch: java.lang.Exception -> L3e
                r4.llllIIIIll1(r2, r3)     // Catch: java.lang.Exception -> L3e
                goto L4e
            L3e:
                r2 = 7
                byte[] r2 = new byte[r2]     // Catch: java.lang.Exception -> Lc0
                r2 = {x010e: FILL_ARRAY_DATA , data: [115, -110, 126, -2, -88, 75, -42} // fill-array     // Catch: java.lang.Exception -> Lc0
                byte[] r3 = new byte[r1]     // Catch: java.lang.Exception -> Lc0
                r3 = {x0116: FILL_ARRAY_DATA , data: [6, -4, 21, -112, -57, 60, -72, 92} // fill-array     // Catch: java.lang.Exception -> Lc0
                IllIIlIIII1.llllIIIIll1 r4 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1     // Catch: java.lang.Exception -> Lc0
                r4.llllIIIIll1(r2, r3)     // Catch: java.lang.Exception -> Lc0
            L4e:
                llIIIIlIlllIII1.IllIIlIIII1 r2 = llIIIIlIlllIII1.IllIIlIIII1.this     // Catch: java.lang.Exception -> Lc0
                llIIIIlIlllIII1.IllIIlIIII1.IlIlllIIlI1(r2)     // Catch: java.lang.Exception -> Lc0
                r2 = 26
                byte[] r2 = new byte[r2]     // Catch: java.lang.Exception -> Lc0
                r2 = {x011e: FILL_ARRAY_DATA , data: [-8, 101, -125, -69, 65, -1, 55, -82, -82, 22, -81, -38, 44, -27, 67, -36, -93, 98, -35, -29, 97, -90, 80, -85, 36, -47} // fill-array     // Catch: java.lang.Exception -> Lc0
                byte[] r3 = new byte[r1]     // Catch: java.lang.Exception -> Lc0
                r3 = {x0130: FILL_ARRAY_DATA , data: [30, -15, 53, 94, -55, 79, -47, 56} // fill-array     // Catch: java.lang.Exception -> Lc0
                IllIIlIIII1.llllIIIIll1 r4 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1     // Catch: java.lang.Exception -> Lc0
                r4.llllIIIIll1(r2, r3)     // Catch: java.lang.Exception -> Lc0
                if (r7 == 0) goto L6d
                org.webrtc.RtpReceiver r0 = r7.getReceiver()     // Catch: java.lang.Exception -> L6b
                goto L6d
            L6b:
                r7 = move-exception
                goto L8d
            L6d:
                if (r0 == 0) goto Leb
                org.webrtc.MediaStreamTrack r7 = r0.track()     // Catch: java.lang.Exception -> L6b
                if (r7 == 0) goto Leb
                llIIIIlIlllIII1.IllIIlIIII1 r0 = llIIIIlIlllIII1.IllIIlIIII1.this     // Catch: java.lang.Exception -> L6b
                r0.IlIllll1()     // Catch: java.lang.Exception -> L6b
                r0 = 14
                byte[] r0 = new byte[r0]     // Catch: java.lang.Exception -> L6b
                r0 = {x0138: FILL_ARRAY_DATA , data: [46, 97, -76, 39, 70, 48, -10, -122, 125, 57, -126, 69, -3, -125} // fill-array     // Catch: java.lang.Exception -> L6b
                byte[] r2 = new byte[r1]     // Catch: java.lang.Exception -> L6b
                r2 = {x0144: FILL_ARRAY_DATA , data: [-58, -36, 28, -50, -57, -93, 17, 55} // fill-array     // Catch: java.lang.Exception -> L6b
                r4.llllIIIIll1(r0, r2)     // Catch: java.lang.Exception -> L6b
                r7.kind()     // Catch: java.lang.Exception -> L6b
                goto Leb
            L8d:
                llIIIIlIlllIII1.IllIIlIIII1 r0 = llIIIIlIlllIII1.IllIIlIIII1.this     // Catch: java.lang.Exception -> Lc0
                java.lang.String r0 = r0.IlIllll1()     // Catch: java.lang.Exception -> Lc0
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lc0
                r2.<init>()     // Catch: java.lang.Exception -> Lc0
                r3 = 23
                byte[] r3 = new byte[r3]     // Catch: java.lang.Exception -> Lc0
                r3 = {x014c: FILL_ARRAY_DATA , data: [-103, -50, -77, -81, -12, 64, 72, 100, -44, -125, -74, -37, -126, 81, 22, 60, -5, -48, -34, -36, -3, -4, -128} // fill-array     // Catch: java.lang.Exception -> Lc0
                byte[] r4 = new byte[r1]     // Catch: java.lang.Exception -> Lc0
                r4 = {x015c: FILL_ARRAY_DATA , data: [124, 106, 55, 72, 100, -58, -96, -39} // fill-array     // Catch: java.lang.Exception -> Lc0
                IllIIlIIII1.llllIIIIll1 r5 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1     // Catch: java.lang.Exception -> Lc0
                java.lang.String r3 = r5.llllIIIIll1(r3, r4)     // Catch: java.lang.Exception -> Lc0
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Exception -> Lc0
                java.lang.String r7 = r7.getMessage()     // Catch: java.lang.Exception -> Lc0
                java.lang.StringBuilder r7 = r2.append(r7)     // Catch: java.lang.Exception -> Lc0
                java.lang.String r7 = r7.toString()     // Catch: java.lang.Exception -> Lc0
                c13.nim5.ez8.h5_proto.Log$LogLevel r2 = c13.nim5.ez8.h5_proto.Log.LogLevel.ERROR     // Catch: java.lang.Exception -> Lc0
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(r2, r0, r7)     // Catch: java.lang.Exception -> Lc0
                goto Leb
            Lc0:
                r7 = move-exception
                llIIIIlIlllIII1.IllIIlIIII1 r0 = llIIIIlIlllIII1.IllIIlIIII1.this
                java.lang.String r0 = llIIIIlIlllIII1.IllIIlIIII1.IlIlllIIlI1(r0)
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                r3 = 22
                byte[] r3 = new byte[r3]
                r3 = {x0164: FILL_ARRAY_DATA , data: [23, 74, -111, -29, -47, -85, 71, 78, -99, -128, 65, 118, 32, 78, -55, -46, -6, -63, 125, 41, -118, -24} // fill-array
                byte[] r1 = new byte[r1]
                r1 = {x0174: FILL_ARRAY_DATA , data: [120, 36, -59, -111, -80, -56, 44, 110} // fill-array
                IllIIlIIII1.llllIIIIll1 r4 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1
                java.lang.String r1 = r4.llllIIIIll1(r3, r1)
                java.lang.StringBuilder r1 = r2.append(r1)
                java.lang.String r7 = llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(r7, r1)
                c13.nim5.ez8.h5_proto.Log$LogLevel r1 = c13.nim5.ez8.h5_proto.Log.LogLevel.ERROR
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(r1, r0, r7)
            Leb:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: llIIIIlIlllIII1.IllIIlIIII1.llllIllIl1.onTrack(org.webrtc.RtpTransceiver):void");
        }
    }

    public IllIIlIIII1(String str, lIllIlIll1.llllIIIIll1 lllliiiill1) {
        byte[] bArr = {74, ByteCompanionObject.MIN_VALUE, 62, 84, 7, -39, 33, 119};
        IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        Intrinsics.checkNotNullParameter(str, lllliiiill12.llllIIIIll1(new byte[]{35, -28}, bArr));
        Intrinsics.checkNotNullParameter(lllliiiill1, lllliiiill12.llllIIIIll1(new byte[]{-73, 26, 72, 120, 19, -79, 105, -54}, new byte[]{-42, 126, 0, 23, ByteCompanionObject.MAX_VALUE, -43, 12, -72}));
        this.f577llllIIIIll1 = str;
        this.f566lIIIIlllllIlll1 = lllliiiill1;
        this.f560IlIllIlllIllI1 = new AtomicBoolean(false);
        WebView webView = lllliiiill1.f508llllIIIIll1;
        Intrinsics.checkNotNullExpressionValue(webView, lllliiiill12.llllIIIIll1(new byte[]{-99, -65, -76, 69, 75, -119, -83}, new byte[]{-22, -38, -42, 19, 34, -20, -38, 25}));
        this.f562IlIllll1 = webView;
        this.f579lllllIllIl1 = new CopyOnWriteArrayList<>();
        this.f551IIIlIllIlI1 = 50;
        this.f575lllIlIIIlI1 = 1;
        this.f571lIlllIIIII1 = 2;
        this.f565IllllIllllll1 = this.f573llIIllIl1;
        this.f576lllIlIlllI1 = new AtomicBoolean(false);
        this.f574llIlIIlll1 = new AtomicBoolean(false);
        this.f554IIlllllIlll1 = new Handler(Looper.getMainLooper());
        this.f552IIlIIllll1 = new ScheduledThreadPoolExecutor(1);
        this.IlIIIIIlll1 = llllllIlIIIlll1.llllIIIIll1.f745llllllIlIIIlll1;
        this.llIIIlIIIlIII1 = 10;
        this.llIllllIlI1 = IlIlIIlIII1.IllIIlIIII1.f164IlIIlllllI1;
        this.lIIIllllllIIII1 = SystemClock.elapsedRealtime();
        IlIlIIlIII1.lIIIIlllllIlll1 IlIlllIIlI12 = IlIlllIIlI1.lIIIIlllllIlll1.IlIllIlllIllI1().IlIlllIIlI1();
        if (IlIlllIIlI12 != null) {
            IlIlllIIlI12.llllIIIIll1(this);
        }
        lllllIllIl1();
        IllllIllllll1();
        lIIlllIIIlllII1();
    }

    public static final void IIlIllIIll1(IllIIlIIII1 illIIlIIII1) {
        try {
            try {
                Context llllIllIl12 = IlIlllIIlI1.lIIIIlllllIlll1.llllIllIl1();
                Intrinsics.checkNotNull(llllIllIl12);
                PeerConnectionFactory.initialize(PeerConnectionFactory.InitializationOptions.builder(llllIllIl12).setEnableInternalTracer(false).createInitializationOptions());
                illIIlIIII1.IlIllll1();
                IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{68, 11, 79, -42, 62, 74, -102, 108, 113, 13, 94, -51, 18, 75, -78, 99, 119, 26, 69, -42, 4, -64, 124, -97, -15, -55, -95, 65, -15, -77, 18, -118, -124, -117, -96, 59}, new byte[]{20, 110, 42, -92, 125, 37, -12, 2});
            } catch (Exception e) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, illIIlIIII1.IlIllll1(), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{96, -83, 90, -71, -87, 104, 79, 23, -86, 45, -97, 96, 24, -89, 60, 122, -109, 121, -48, 95, 88, 17, -118}, new byte[]{55, -56, 56, -21, -3, 43, -86, -97}) + e.getMessage());
                return;
            } catch (UnsatisfiedLinkError e2) {
                String IlIllll12 = illIIlIIII1.IlIllll1();
                StringBuilder sb = new StringBuilder();
                IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.WARN, IlIllll12, sb.append(lllliiiill1.llllIIIIll1(new byte[]{-76, 33, 118, -64, 33, 122, -44, 19, -127, 39, 103, -37, 13, 123, -4, 28, -121, 48, 124, -64, 27, -16, 50, -32, 1, -29, -104, 87, -18, -125, 95, -39, 85, -84, -89, 23, -115, -87, 54, -104, 84, -39, -5, 29, -9, -13, 51, -10, 1, -50, -69, 87, -24, -75, 82, -64, 89, -95, -87, 33, 88, 53}, new byte[]{-28, 68, 19, -78, 98, 21, -70, 125})).append(e2.getMessage()).toString());
                try {
                    System.loadLibrary(lllliiiill1.llllIIIIll1(new byte[]{-82, -76, -18, -122, 113, 123, -45, 86, -95, -72, -14, -126, 114, 112, -30, 67, -89, -87, -23, -114, 115, 65, -1, 73}, new byte[]{-60, -35, ByteCompanionObject.MIN_VALUE, -31, 29, 30, -116, 38}));
                    illIIlIIII1.IlIllll1();
                    lllliiiill1.llllIIIIll1(new byte[]{100, -7, -18, 40, -58, -117, 33, 88, 34, -104, -40, 112, 38, 74, -86, -75, -18, 21, 58, -67, 41, 70, -74, -79, -19, 30, 11, -88, 47, 87, -83, -67, -20, 47, 22, -94, -86, -85, 84, 55, 8, -17}, new byte[]{-126, 112, 101, -51, 76, 35, -60, -46});
                    Context IllIIlIIII12 = IlIlllIIlI1.lIIIIlllllIlll1.f248llllIIIIll1.IllIIlIIII1();
                    Intrinsics.checkNotNull(IllIIlIIII12);
                    PeerConnectionFactory.initialize(PeerConnectionFactory.InitializationOptions.builder(IllIIlIIII12).setEnableInternalTracer(true).createInitializationOptions());
                    illIIlIIII1.IlIllll1();
                    lllliiiill1.llllIIIIll1(new byte[]{-115, -73, -2, 25, 35, 97, 16, 48, -7, -43, -44, 116, 80, 93, 99, -24, 1, 85, 1, -68, -38, -65, -101, -35, 7, 68, 26, -112, -37, -105, -108, -37, 16, 95, 1, -122, 83, 89, 101, 93, -18, -81}, new byte[]{100, 48, 115, -1, -75, -47, -11, -72});
                } catch (Exception e3) {
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, illIIlIIII1.IlIllll1(), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{0, -110, -59, 0, -41, 85, 68, 45, 70, -13, -13, 88, -72, 71, 50, 66, 66, -86, -90, 81, -8, -57, -127}, new byte[]{-26, 27, 78, -27, 93, -3, -95, -89}) + e3.getMessage());
                    return;
                }
            }
            Thread.sleep(100L);
            EglBase.Context IIlIllIIll12 = illIIlIIII1.IIlIllIIll1();
            if (IIlIllIIll12 == null) {
                IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill12.llllIIIIll1(new byte[]{26, -123, -87, -49, -1, 47}, new byte[]{77, -32, -53, -99, -85, 108, 10, -47}), lllliiiill12.llllIIIIll1(new byte[]{2, 97, 77, -40, -97, -104, -111, 88, 34, 94, 117, -40, 20, 121, 72, -55, -56, -80, -28, 92, 77, 31, 75, -119, -88, -102, ByteCompanionObject.MIN_VALUE}, new byte[]{71, 38, 1, -8, -4, -9, -1, 44}));
                return;
            }
            IllIIlIIII1.llllIIIIll1 lllliiiill13 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllliiiill13.llllIIIIll1(new byte[]{-27, 85, 41, -113, 4, 13}, new byte[]{-78, 48, 75, -35, 80, 78, -100, 31});
            lllliiiill13.llllIIIIll1(new byte[]{-55, 86, 91, 1, -82, -70, 71, 3, -104, 59, 68, 114, 4, 96, -24, -63, 15, -67, -92, -118, 80, 64, -41, -7, 21, -2}, new byte[]{47, -34, -53, -28, 36, 37, -81, -115});
            IIlIllIIll12.toString();
            illIIlIIII1.f553IIlIllIIll1 = IIlIllIIll12;
            try {
                illIIlIIII1.llIIIIlIlllIII1();
                illIIlIIII1.lIllIIIlIl1();
                illIIlIIII1.lllIlIIIlI1();
                illIIlIIII1.IlIIlllllI1();
            } catch (Exception e4) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, illIIlIIII1.IlIllll1(), new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{40, -37, -90, 44, -72, 115, 116, -68, -60, 58, 32, -59, 90, -43, -36, -58, -102, 25, 79, -101, 96, -90, -79, -1, -50, 86, 112, -37, -42, 16}, new byte[]{ByteCompanionObject.MAX_VALUE, -66, -60, 126, -20, 48, 84, 91})).append(e4.getMessage()).toString());
                illIIlIIII1.llIIllIl1();
            }
        } catch (Exception e5) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, illIIlIIII1.IlIllll1(), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e5, new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{3, -43, 83, 37, 4, -119, -42, -109, 112, 10, -85, -94, -15, 86, 112, -6, 66, -20, 38, 116, 6, 56, 19}, new byte[]{-26, 93, -50, -64, -93, 2, 51, 31}))));
            illIIlIIII1.llIIllIl1();
        }
    }

    public static final void IlIllll1(IllIIlIIII1 illIIlIIII1) {
        if (illIIlIIII1.f562IlIllll1.canGoBack()) {
            illIIlIIII1.f562IlIllll1.goBack();
            illIIlIIII1.IlIllll1();
            IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{116, -84, -1, -95, -127, 21, -85, 53, -59, 64, 58, 31, 73, -4, 52, -86, -73, 44, 6, 105, 14, -29, 81, -15, -98, 85}, new byte[]{35, -55, -99, -9, -24, 112, -36, 21});
            return;
        }
        illIIlIIII1.IlIllll1();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        lllliiiill1.llllIIIIll1(new byte[]{-39, -29, -107, 12, -94, 123, 84, -7, 104, 17, 87, -68, 120, -117, -53, 102, 26, 99, 108, -60, 36, -94, -81, 60, 57, 52, 16, -31, 68, -8, -69, 118, 105, 42, 91, -66, 115, -98, -54, 120, 59}, new byte[]{-114, -122, -9, 90, -53, 30, 35, -39});
        illIIlIIII1.llllIIIIll1(lllliiiill1.llllIIIIll1(new byte[]{18, 34, -37, ByteCompanionObject.MAX_VALUE, 20, -43, 22, -100, 19, 44, -19, 113, 16, -46}, new byte[]{117, 77, -124, 29, 117, -74, 125, -61}), lllliiiill1.llllIIIIll1(new byte[]{-95, -60, 15, -22, -96, -18, -106, 62, 65, 19, -118, 7, 70, 109, 121, 116, 17, 13, -63, 88, 113, 11, 8, 122, 67, 78, -47, 48, 47, 28, 65, 61, 69, 52, -123, 3, 93, 110, 122, 69}, new byte[]{-10, -95, 109, -68, -55, -117, -31, -37}));
    }

    public static final void lIllIlIll1(IllIIlIIII1 illIIlIIII1) {
        if (illIIlIIII1.f576lllIlIlllI1.get()) {
            return;
        }
        illIIlIIII1.lllIlIIIlI1();
    }

    public final void IIIlIllIlI1() {
        try {
            if (this.f576lllIlIlllI1.compareAndSet(false, true)) {
                IlIllll1();
                IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{23, 2, -89, ByteCompanionObject.MIN_VALUE, -119, -12, -60, -80, -100, -13, 68, 3, 106, 24, -25, -74, 22, 48, -87, ByteCompanionObject.MIN_VALUE, -89, -38}, new byte[]{-2, -123, 45, 102, 29, 74, -109, -43});
                try {
                    ScheduledFuture<?> scheduledFuture = this.f556IlIIIlIlIlIII1;
                    if (scheduledFuture != null) {
                        scheduledFuture.cancel(false);
                    }
                    this.f556IlIIIlIlIlIII1 = null;
                } catch (Throwable unused) {
                }
                try {
                    lllIlIlllI1();
                } catch (Throwable unused2) {
                }
                try {
                    this.f560IlIllIlllIllI1.set(false);
                } catch (Throwable unused3) {
                }
                try {
                    DataChannel dataChannel = this.f561IlIlllIIlI1;
                    if (dataChannel != null) {
                        dataChannel.close();
                    }
                    this.f561IlIlllIIlI1 = null;
                } catch (Exception e) {
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll1(), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{87, -23, -117, -110, 24, -82, -72, -102, 2, -118, -75, -43, 102, -125, -60, -26, 51, -1, -35, -33, 62, -21, -22, -86, -120, 76}, new byte[]{-78, 108, 56, 123, -113, 3, 94, 15}) + e.getMessage());
                }
                try {
                    PeerConnection peerConnection = this.f563IllIIlIIII1;
                    if (peerConnection != null) {
                        peerConnection.close();
                    }
                    this.f563IllIIlIIII1 = null;
                } catch (Exception e2) {
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll1(), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-97, 46, 74, -92, 123, -46, -75, 44, 31, -39, -70, 34, -126, 17, ByteCompanionObject.MIN_VALUE, 42, 14, -62, -106, 35, 9, -37, 84, -95, -50, 14, -61, 109}, new byte[]{122, -85, -7, 77, -20, ByteCompanionObject.MAX_VALUE, -27, 73}) + e2.getMessage());
                }
                try {
                    VideoTrack videoTrack = this.f559IlIlIIlIII1;
                    if (videoTrack != null) {
                        videoTrack.dispose();
                    }
                    this.f559IlIlIIlIII1 = null;
                    VideoSource videoSource = this.f580llllllIlIIIlll1;
                    if (videoSource != null) {
                        videoSource.dispose();
                    }
                    this.f580llllllIlIIIlll1 = null;
                } catch (Exception e3) {
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll1(), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-93, 21, -29, -85, 79, 101, -53, -75, -52, 123, -53, -36, 51, 110, -89, -12, -16, 2, -116, -23, 106, 51, -105, -73, 112, -78}, new byte[]{74, -110, 105, 77, -37, -37, 35, 18}) + e3.getMessage());
                }
                try {
                    PeerConnectionFactory peerConnectionFactory = this.f578llllIllIl1;
                    if (peerConnectionFactory != null) {
                        peerConnectionFactory.dispose();
                    }
                } catch (Exception e4) {
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll1(), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-90, 62, 21, 71, 108, -50, 96, -16, 42, -53, -36, -50, -106, 30, 85, -10, 59, -48, -16, -49, -66, 17, 83, -31, 32, -53, -26, 68, 92, -63, -40, 33, -22, -125, -65}, new byte[]{79, -71, -97, -95, -8, 112, 48, -107}) + e4.getMessage());
                }
                try {
                    IlIlIIlIII1.lIIIIlllllIlll1 IlIlllIIlI12 = IlIlllIIlI1.lIIIIlllllIlll1.IlIllIlllIllI1().IlIlllIIlI1();
                    if (IlIlllIIlI12 != null) {
                        IlIlllIIlI12.llllIIIIll1();
                    }
                } catch (Exception e5) {
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll1(), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-51, -11, -69, 52, -114, 81, -36, -34, 120, 51, -18, 104, -104, 25, 63, 61, -64, -60, -83, -25, 57}, new byte[]{40, 112, 8, -35, 25, -4, -101, -116}) + e5.getMessage());
                }
                try {
                    this.f552IIlIIllll1.shutdownNow();
                } catch (Exception unused4) {
                }
                ScheduledFuture<?> scheduledFuture2 = this.lIlIlIlI1;
                if (scheduledFuture2 != null) {
                    scheduledFuture2.cancel(false);
                }
                this.lIlIlIlI1 = null;
            }
        } catch (Throwable unused5) {
        }
    }

    public final void IlIIIIllllIlI1() {
        this.f560IlIllIlllIllI1.set(false);
    }

    public final void IlIIlllllI1() {
        this.f569lIllIIIlIl1 = new llIIIIlIlllIII1.llllIllIl1();
        PeerConnectionFactory peerConnectionFactory = this.f578llllIllIl1;
        RtpSender rtpSender = null;
        if (peerConnectionFactory == null) {
            Intrinsics.throwUninitializedPropertyAccessException(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-23, -86, 18, -82, -4, 54, -35, -27, -4, -84, 3, -75, -48, 55, -11, -22, -6, -69, 24, -82, -58}, new byte[]{-103, -49, 119, -36, -65, 89, -77, -117}));
            peerConnectionFactory = null;
        }
        this.f580llllllIlIIIlll1 = peerConnectionFactory.createVideoSource(false);
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        String llllIIIIll12 = lllliiiill1.llllIIIIll1(new byte[]{111, 57, -110, 98, 36, 95, -114, 88, 68, 42, -121, 119, 53}, new byte[]{44, 88, -30, 22, 81, 45, -21, 12});
        EglBase.Context context = this.f553IIlIllIIll1;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException(lllliiiill1.llllIIIIll1(new byte[]{4, -94, 98, -127, -24, 22, 12, 75, 14, -85, 122, -90, -15, 17}, new byte[]{97, -59, 14, -61, -119, 101, 105, 8}));
            context = null;
        }
        SurfaceTextureHelper create = SurfaceTextureHelper.create(llllIIIIll12, context);
        llIIIIlIlllIII1.llllIllIl1 llllillil1 = this.f569lIllIIIlIl1;
        Intrinsics.checkNotNull(llllillil1);
        Intrinsics.checkNotNull(create);
        Context llllIllIl12 = IlIlllIIlI1.lIIIIlllllIlll1.llllIllIl1();
        Intrinsics.checkNotNull(llllIllIl12);
        VideoSource videoSource = this.f580llllllIlIIIlll1;
        Intrinsics.checkNotNull(videoSource);
        CapturerObserver capturerObserver = videoSource.getCapturerObserver();
        Intrinsics.checkNotNullExpressionValue(capturerObserver, lllliiiill1.llllIIIIll1(new byte[]{-94, -102, -11, -50, -117, 63, -66, -106, -73, -102, -13, -62, -120, 60, -81, -111, -77, -102, -13, -91, -60, 97, -28, -54}, new byte[]{-59, -1, -127, -115, -22, 79, -54, -29}));
        llllillil1.initialize(create, llllIllIl12, capturerObserver);
        llIIIIlIlllIII1.llllIllIl1 llllillil12 = this.f569lIllIIIlIl1;
        Intrinsics.checkNotNull(llllillil12);
        Context IllIIlIIII12 = IlIlllIIlI1.lIIIIlllllIlll1.f248llllIIIIll1.IllIIlIIII1();
        Intrinsics.checkNotNull(IllIIlIIII12);
        llllillil12.llllIIIIll1(IllIIlIIII12, this.f562IlIllll1);
        Context IllIIlIIII13 = IlIlllIIlI1.lIIIIlllllIlll1.f248llllIIIIll1.IllIIlIIII1();
        Intrinsics.checkNotNull(IllIIlIIII13);
        DisplayMetrics displayMetrics = IllIIlIIII13.getResources().getDisplayMetrics();
        llIIIIlIlllIII1.llllIllIl1 llllillil13 = this.f569lIllIIIlIl1;
        Intrinsics.checkNotNull(llllillil13);
        llllillil13.startCapture(displayMetrics.widthPixels, displayMetrics.heightPixels, 15);
        PeerConnectionFactory peerConnectionFactory2 = this.f578llllIllIl1;
        if (peerConnectionFactory2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(lllliiiill1.llllIIIIll1(new byte[]{123, 100, -8, -109, -33, 93, -23, 46, 110, 98, -23, -120, -13, 92, -63, 33, 104, 117, -14, -109, -27}, new byte[]{11, 1, -99, -31, -100, 50, -121, 64}));
            peerConnectionFactory2 = null;
        }
        this.f559IlIlIIlIII1 = peerConnectionFactory2.createVideoTrack(lllliiiill1.llllIIIIll1(new byte[]{-31, ByteCompanionObject.MIN_VALUE, -48, 53, -18, -43, 4, 99, -32, -116, -42, 38, -24}, new byte[]{-106, -27, -78, 67, -121, -80, 115, 78}), this.f580llllllIlIIIlll1);
        IlIllll1();
        lllliiiill1.llllIIIIll1(new byte[]{-110, 20, -65, -40, -68, 95, -115, -64, -15, 117, -122, -84, -17, 88, -51, -114, -10, 15, -53, -127, -99}, new byte[]{119, -100, 36, 61, 7, -27, 101, 103});
        try {
            List listOf = CollectionsKt.listOf(lllliiiill1.llllIIIIll1(new byte[]{-93, 39, 67, 18, 10, -33, -82, -86, -89, 54, 83, 1, 2, -41}, new byte[]{-44, 66, 33, 100, 99, -70, -39, -121}));
            PeerConnection peerConnection = this.f563IllIIlIIII1;
            if (peerConnection != null) {
                VideoTrack videoTrack = this.f559IlIlIIlIII1;
                Intrinsics.checkNotNull(videoTrack);
                rtpSender = peerConnection.addTrack(videoTrack, listOf);
            }
            IlIllll1();
            lllliiiill1.llllIIIIll1(new byte[]{-8, -60, 36, 22, 51, -95, -48, -86, -104, -102, 61, 98, 81, -68, -112, -28, -97, -32, 122, 123, 9, 81, 93, 104, 108, 48, -16, -99, -41, 100, 91, 121, 119, 28, -15, 20, 2, -110, -34, -109, -126, -100, 35, 105}, new byte[]{30, 115, -97, -13, -71, 1, 56, 13});
            Objects.toString(rtpSender);
        } catch (Exception e) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll1(), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{84, 1, 78, -3, 88, 77, 42, 104, 52, 95, 87, -119, 58, 80, 106, 38, 51, 37, 16, -68, 99, 5, 118, 106, -120, -106}, new byte[]{-78, -74, -11, 24, -46, -19, -62, -49}))));
            e.printStackTrace();
        }
        IllIlIllll1();
    }

    public final void IlIlIIIlIlIlll1() {
        this.f562IlIllll1.post(new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                IllIIlIIII1.IlIllll1(IllIIlIIII1.this);
            }
        });
    }

    public final void IllIlIllll1() {
        this.f560IlIllIlllIllI1.compareAndSet(false, true);
    }

    public final void IllllIllllll1() {
        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, IlIllll1(), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-108, -30, 34, -4, -36, -84, -73, -98, -21, -72, 53, -81, -98, -104, -47, -40, -58, -19, 70, -94, -64, -62, -40, -111}, new byte[]{113, 94, -94, 25, 123, 39, 82, 48}));
        lllIlIlllI1();
        ScheduledExecutorService scheduledExecutorService = this.f552IIlIIllll1;
        Runnable runnable = new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                IllIIlIIII1.lIllIlIll1(IllIIlIIII1.this);
            }
        };
        long j = this.llIllllIlI1;
        this.lIlIIIllll1 = scheduledExecutorService.scheduleWithFixedDelay(runnable, j, j, TimeUnit.MILLISECONDS);
    }

    public final void lIIlIIIIlIlII1() {
        if (this.f561IlIlllIIlI1 == null) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll1(), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-26, 59, -104, -3, -77, 76, ByteCompanionObject.MAX_VALUE, -61, -102, 71, -87, -120, -38, 90, 44, -92, -87, 20, -57, -89, -78, 4, 1, -29, -26, 29, -67, -13, -112, 92, 113, -2, -82}, new byte[]{0, -82, 40, 27, 62, -30, -106, 67}));
            return;
        }
        IlIllll1();
        IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{52, -24, -114, 81, 85, 80, -113, 103, 108, -96, -67, 24, 1, 126, -13, 27, 93, -43, -33, 10, 100, 27, -44, 97, 57, -49, -67, 81, 98, 72, -113, 114, 93, 124, 16}, new byte[]{-36, 70, 48, -74, -24, -2, 105, -14});
        DataChannel dataChannel = this.f561IlIlllIIlI1;
        Objects.toString(dataChannel != null ? dataChannel.state() : null);
        try {
            DataChannel dataChannel2 = this.f561IlIlllIIlI1;
            if (dataChannel2 != null) {
                dataChannel2.unregisterObserver();
            }
        } catch (Exception e) {
            IlIllll1();
            IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-115, 123, 48, -119, 29, 14, -28, 30, -51, -109, -23, 19, -31, -40, 116, -20, 24, 57, 47, -47, 108, 30, -89, 102, -42, 80, 110, -17, 43, 66, -127, 52, -116, 68, 36, -119, 34, 60, -28, 37, -53, 52, 37, -34, 99, 23, -84, -77, 74}, new byte[]{106, -36, -117, 96, -124, -86, 2, -119});
            e.getMessage();
        }
        DataChannel dataChannel3 = this.f561IlIlllIIlI1;
        if (dataChannel3 != null) {
            dataChannel3.registerObserver(new IlIlllIIlI1());
        }
        DataChannel dataChannel4 = this.f561IlIlllIIlI1;
        if ((dataChannel4 != null ? dataChannel4.state() : null) == DataChannel.State.OPEN) {
            IlIllll1();
            IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{105, 14, 112, -41, 84, 18, 30, 102, 21, 114, 65, -94, 60, 11, 69, 1, 52, 20, 38, -87, 118, 90, 126, 117, 106, 39, 64, -42, 83, 10, 17, 102, 14, 116, 124, -67, 60, 51, 102, 15, 15, 26, 38, -124, 82, 84, 88, 115, 105, 45, 72, -41, 88, 19}, new byte[]{-113, -101, -64, 49, -39, -68, -9, -26});
            lIlllIIIII1();
        }
    }

    public final void lIIlllIIIlllII1() {
        ScheduledFuture<?> scheduledFuture = this.lIlIlIlI1;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.lIlIlIlI1 = this.f552IIlIIllll1.scheduleWithFixedDelay(new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                IllIIlIIII1.llIIIIlIlllIII1(IllIIlIIII1.this);
            }
        }, llllllIlIIIlll1.llllIIIIll1.f745llllllIlIIIlll1, llllllIlIIIlll1.llllIIIIll1.f745llllllIlIIIlll1, TimeUnit.MILLISECONDS);
    }

    public final void lIlllIIIII1() {
        try {
            DataChannel dataChannel = this.f561IlIlllIIlI1;
            if ((dataChannel != null ? dataChannel.state() : null) == DataChannel.State.OPEN) {
                JSONObject jSONObject = new JSONObject();
                IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-56, -116, -11, -42}, new byte[]{-68, -11, -123, -77, -54, -49, -44, -125}), lllliiiill1.llllIIIIll1(new byte[]{62, -99, -108, -107}, new byte[]{74, -8, -25, -31, -8, 45, -78, -83}));
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-108, 9, 27, -117, 62, 73, -4}, new byte[]{-7, 108, 104, -8, 95, 46, -103, -79}), lllliiiill1.llllIIIIll1(new byte[]{-108, -61, -125, -70, -35, 66, -91, -5, -93, -50, -111, -96, -109, 65, -69, -76, -70, -117, -93, -70, -41, 85, -90, -78, -77}, new byte[]{-41, -85, -30, -44, -77, 39, -55, -37}));
                String jSONObject2 = jSONObject.toString();
                Intrinsics.checkNotNullExpressionValue(jSONObject2, lllliiiill1.llllIIIIll1(new byte[]{-47, 6, 78, -86, -23, -9, 96, -109, -115, 71, 51, -16, -78}, new byte[]{-91, 105, 29, -34, -101, -98, 14, -12}));
                byte[] bytes = jSONObject2.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, lllliiiill1.llllIIIIll1(new byte[]{40, 65, -89, -112, -32, -32, 87, 76, 103, 10, -3, -4, -80}, new byte[]{79, 36, -45, -46, -103, -108, 50, 63}));
                ByteBuffer wrap = ByteBuffer.wrap(bytes);
                DataChannel dataChannel2 = this.f561IlIlllIIlI1;
                if (dataChannel2 != null) {
                    dataChannel2.send(new DataChannel.Buffer(wrap, false));
                }
                IlIllll1();
                lllliiiill1.llllIIIIll1(new byte[]{-3, 8, 73, 8, 32, 100, -113, -65, -109, 111, 119, 116, 70, 83, -31, -20, -103, 40, 62, 105, 48, 0, -29, -107}, new byte[]{24, -121, -40, -31, -96, -27, 105, 10});
            }
        } catch (Exception e) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll1(), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-106, 27, -119, -65, -90, 2, 60, 19, -8, 124, -73, -61, -64, 53, 82, 64, -14, 59, -3, -14, -105, 107, 110, 3, 73, -76}, new byte[]{115, -108, 24, 86, 38, -125, -38, -90}))));
        }
    }

    public final void llIIIIlIlllIII1() {
        EglBase.Context context = this.f553IIlIllIIll1;
        EglBase.Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{73, -117, -19, 113, 75, -114, 91, -123, 67, -126, -11, 86, 82, -119}, new byte[]{44, -20, -127, 51, 42, -3, 62, -58}));
            context = null;
        }
        DefaultVideoDecoderFactory defaultVideoDecoderFactory = new DefaultVideoDecoderFactory(context);
        EglBase.Context context3 = this.f553IIlIllIIll1;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{72, -71, -29, 88, -87, 58, -33, 58, 66, -80, -5, ByteCompanionObject.MAX_VALUE, -80, 61}, new byte[]{45, -34, -113, 26, -56, 73, -70, 121}));
            context3 = null;
        }
        llIIIIlIlllIII1.lIIIIlllllIlll1 liiiilllllilll1 = new llIIIIlIlllIII1.lIIIIlllllIlll1(defaultVideoDecoderFactory, context3);
        PeerConnectionFactory.Options options = new PeerConnectionFactory.Options();
        EglBase.Context context4 = this.f553IIlIllIIll1;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{81, -79, 118, -32, 86, 20, -26, -46, 91, -72, 110, -57, 79, 19}, new byte[]{52, -42, 26, -94, 55, 103, -125, -111}));
            context4 = null;
        }
        DefaultVideoEncoderFactory defaultVideoEncoderFactory = new DefaultVideoEncoderFactory(context4, true, true);
        try {
            IlIllll1();
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllliiiill1.llllIIIIll1(new byte[]{0, 53, -59, 105, -31, -34, 112, -4, 126, 108, -2, 54, 22, 48, -16, 6, -90, -26, 43, -30, 35, 54, -31, 29, -118, -25, 3, -19, 37, 33, -6, 6, -100}, new byte[]{-27, -119, 69, -116, 70, 85, -107, 116});
            IlIllll1();
            lllliiiill1.llllIIIIll1(new byte[]{-3, -27, -33, -62, 123, -72, -9, -115, -35, -38, -25, -40, 24}, new byte[]{-72, -94, -109, -30, 56, -41, -103, -7});
            EglBase.Context context5 = this.f553IIlIllIIll1;
            if (context5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(lllliiiill1.llllIIIIll1(new byte[]{-113, -24, -74, 113, -120, -101, 36, 82, -123, -31, -82, 86, -111, -100}, new byte[]{-22, -113, -38, 51, -23, -24, 65, 17}));
            } else {
                context2 = context5;
            }
            Objects.toString(context2);
            IlIllll1();
            lllliiiill1.llllIIIIll1(new byte[]{100, -82, -31, -51, -28, 32, -102, -37, 103, -95, -31, -42, -17, 55, -111, -63, 1}, new byte[]{33, -64, -126, -94, ByteCompanionObject.MIN_VALUE, 69, -24, -5});
            defaultVideoEncoderFactory.toString();
            IlIllll1();
            lllliiiill1.llllIIIIll1(new byte[]{49, -79, -21, -127, -113, 94, 19, 110, 51, -75, -21, -102, -124, 73, 24, 116, 85}, new byte[]{117, -44, -120, -18, -21, 59, 97, 78});
            this.f578llllIllIl1 = PeerConnectionFactory.builder().setOptions(options).setVideoEncoderFactory(defaultVideoEncoderFactory).setVideoDecoderFactory(liiiilllllilll1).createPeerConnectionFactory();
            IlIllll1();
            lllliiiill1.llllIIIIll1(new byte[]{22, 50, -46, -110, 110, -47, 124, 81, 35, 52, -61, -119, 66, -48, 84, 94, 37, 35, -40, -110, 84, 91, -102, -92, -93, -20, 13, 6, -91, 46, -9, -75, -39}, new byte[]{70, 87, -73, -32, 45, -66, 18, 63});
        } catch (Exception e) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll1(), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-102, 73, 36, 101, -19, 58, -32, -98, 26, -92, -51, -61, 57, -18, -82, -85, 28, -75, -42, -17, 56, -58, -95, -83, 11, -82, -51, -7, 118, 101, 100, ByteCompanionObject.MAX_VALUE, -105, 117, 26, -70, 118}, new byte[]{ByteCompanionObject.MAX_VALUE, -63, -65, ByteCompanionObject.MIN_VALUE, 86, ByteCompanionObject.MIN_VALUE, -64, -50}))));
            e.printStackTrace();
            throw e;
        }
    }

    public final void llIIllIl1() {
        llllIIIIll1(1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{37, 39, -84, -50, -1, 83, -115, -61, 64, 72, -71, -75, -112, 76, -42, -93, 107, 33, -31, -65, -35}, new byte[]{-51, -96, 6, 43, 117, -5, 100, 68}), (Object) null);
    }

    public final void lllIlIIIlI1() {
        this.IIlIlllllllI1 = 0;
        IlIllll1();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        lllliiiill1.llllIIIIll1(new byte[]{36, 56, 87, 81, 86, 86, 68, 21, 80, 109, 87, 53, 20, 98, 34, 114, 118, 55}, new byte[]{-63, -124, -41, -76, -15, -35, -95, -102});
        try {
            IlIlIIlIII1.lIIIIlllllIlll1 IlIlllIIlI12 = IlIlllIIlI1.lIIIIlllllIlll1.IlIllIlllIllI1().IlIlllIIlI1();
            if (IlIlllIIlI12 != null) {
                IlIlllIIlI12.IlIllIlllIllI1();
            }
            IlIllll1();
            lllliiiill1.llllIIIIll1(new byte[]{-43, -98, -68, -9, 94, -123, 90, -103, -77, -7, -102, -83, 56, -116, 47, -61, -70, -114}, new byte[]{48, 17, 45, 30, -34, 4, -65, 38});
        } catch (Exception e) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll1(), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-62, -91, -110, -24, -78, -107, -94, -72, 73, 77, -27, -73, -70, -14, 115, 126, -62, -114, -78, -23, -122, -79, -56, -15}, new byte[]{39, 42, 3, 1, 50, 20, -14, -47}))));
            llIIllIl1();
        }
    }

    public final void lllIlIlllI1() {
        ScheduledFuture<?> scheduledFuture = this.lIlIIIllll1;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.lIlIIIllll1 = null;
    }

    @Override // IlIlIIlIII1.lIIIIlllllIlll1.IlIllIlllIllI1
    public void llllIIIIll1(Exception exc) {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        Intrinsics.checkNotNullParameter(exc, lllliiiill1.llllIIIIll1(new byte[]{-99, 1, 81, 65, -23}, new byte[]{-8, 115, 35, 46, -101, -120, -94, 89}));
        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll1(), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(exc, new StringBuilder().append(lllliiiill1.llllIIIIll1(new byte[]{15, -96, 123, 85, -9, 53, -76, -93, 106, -95, 96, 93, -21, 116, -79, -92, 36, -75, 51, 26}, new byte[]{74, -46, 9, 58, -123, 21, -35, -51}))));
        llIIllIl1();
    }

    public final void llllIllIl1() {
    }

    public final void lllllIllIl1() {
        if (this.f576lllIlIlllI1.get()) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.WARN, IlIllll1(), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-107, 9, -108, 30, -83, 56, 78, 97, -19, 92, -82, 125, -25, 33, 61, 13, -56, 57, -19, 78, -88, 72, 28, 91, -103, 62, -125, 16, -106, 19, 76, 115, -12, -18, 108, -108, 116, -60, -50, -98, 34, -51, 106, 19, -84, 51, 79, 87, -5}, new byte[]{112, -71, 9, -10, 2, -83, -85, -23}));
            return;
        }
        Iterator it = ArrayIteratorKt.iterator(new MediaCodecList(1).getCodecInfos());
        while (it.hasNext()) {
            MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) it.next();
            if (mediaCodecInfo.isEncoder()) {
                IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, lllliiiill1.llllIIIIll1(new byte[]{-11, 39, 108, 5}, new byte[]{-67, 21, 90, 49, -62, -37, 23, -58}), lllliiiill1.llllIIIIll1(new byte[]{51, -105, 21, -84, 104, -51, 46, 109, 16, -106, 3, -83, 104, -110, 124}, new byte[]{118, -7, 118, -61, 12, -88, 92, 77}) + mediaCodecInfo.getName());
            } else {
                byte[] bArr = {-51, 91, ByteCompanionObject.MAX_VALUE, 0, -52, 17, -7, -112};
                IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, lllliiiill12.llllIIIIll1(new byte[]{-123, 105, 73, 52}, bArr), lllliiiill12.llllIIIIll1(new byte[]{26, 43, -124, 31, -86, 51, -58, -99, 56, 33, -110, 30, -86, 108, -108}, new byte[]{94, 78, -25, 112, -50, 86, -76, -67}) + mediaCodecInfo.getName());
            }
        }
        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, IlIllll1(), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-76, -77, -40, -120, -36, -9, 11, 123, -119, -98}, new byte[]{-35, -35, -79, -4, -117, -110, 105, 41}));
        lIllIlIll1();
        IlIlllIIlI1.lIIIIlllllIlll1.llllIIIIll1(new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda22
            @Override // java.lang.Runnable
            public final void run() {
                IllIIlIIII1.IIlIllIIll1(IllIIlIIII1.this);
            }
        });
    }

    public static final void IlIlllIIlI1(IllIIlIIII1 illIIlIIII1, double d, double d2) {
        int width = illIIlIIII1.f562IlIllll1.getWidth();
        int height = illIIlIIII1.f562IlIllll1.getHeight();
        float f = (float) (d * width);
        illIIlIIII1.f572llIIIIlIlllIII1 = f;
        float f2 = (float) (d2 * height);
        illIIlIIII1.f570lIllIlIll1 = f2;
        illIIlIIII1.f564IllIlIllll1 = f;
        illIIlIIII1.f568lIIlllIIIlllII1 = f2;
        illIIlIIII1.f567lIIlIIIIlIlII1 = 0.0f;
        illIIlIIII1.f558IlIlIIIlIlIlll1 = SystemClock.uptimeMillis();
        illIIlIIII1.f557IlIIlllllI1 = true;
    }

    public final void IlIlIIlIII1() {
        IlIllll1();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        lllliiiill1.llllIIIIll1(new byte[]{32, 51, 121, 84, -19, 18, 80, -70, 94, 106, 66, 11, 11, -9, -58, 69, -96, -3}, new byte[]{-59, -113, -7, -79, 74, -103, -75, 50});
        MediaConstraints mediaConstraints = new MediaConstraints();
        mediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair(lllliiiill1.llllIIIIll1(new byte[]{-116, -6, 80, 79, 62, -80, -55, 0, -90, -1, 83, 67, 58, -127, -16, 59, -89, -7, 89}, new byte[]{-61, -100, 54, 42, 76, -28, -90, 82}), lllliiiill1.llllIIIIll1(new byte[]{-40, 9, -8, 57}, new byte[]{-84, 123, -115, 92, 88, -26, -55, 116})));
        mediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair(lllliiiill1.llllIIIIll1(new byte[]{-3, -14, 18, 8, 90, -107, 2, -88, -41, -9, 17, 4, 94, -92, 44, -113, -42, -3, 27}, new byte[]{-78, -108, 116, 109, 40, -63, 109, -6}), lllliiiill1.llllIIIIll1(new byte[]{-7, 69, ByteCompanionObject.MIN_VALUE, 36, 90}, new byte[]{-97, 36, -20, 87, 63, -54, -37, 51})));
        PeerConnection peerConnection = this.f563IllIIlIIII1;
        if (peerConnection != null) {
            peerConnection.createAnswer(new lIIIIlllllIlll1(), mediaConstraints);
        }
    }

    public final void IlIllIlllIllI1() {
        List<RtpSender> senders;
        PeerConnection peerConnection = this.f563IllIIlIIII1;
        if (peerConnection != null && (senders = peerConnection.getSenders()) != null) {
            for (RtpSender rtpSender : senders) {
                try {
                    PeerConnection peerConnection2 = this.f563IllIIlIIII1;
                    if (peerConnection2 != null) {
                        peerConnection2.removeTrack(rtpSender);
                    }
                } catch (Exception e) {
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll1(), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-79, -82, 7, -46, -79, 67, -91, 49, -2, -32, 61, -88, -51, 67, -4, 100, -30, -84, -122, 27}, new byte[]{86, 9, -68, 59, 40, -25, 77, -116}))));
                }
            }
        }
        try {
            PeerConnection peerConnection3 = this.f563IllIIlIIII1;
            if (peerConnection3 != null) {
                peerConnection3.dispose();
            }
        } catch (Exception e2) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll1(), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e2, new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{28, -83, 82, -97, 89, 11, 99, 26, -100, 77, -109, 53, -95, -56, 45, 47, -102, 92, -120, 25, -96, -122, -90, -18, 72, -64, 85, -45, -12, -122}, new byte[]{-7, 40, -31, 118, -50, -90, 67, 74}))));
        }
        this.f563IllIIlIIII1 = null;
        try {
            DataChannel dataChannel = this.f561IlIlllIIlI1;
            if (dataChannel != null) {
                dataChannel.close();
            }
        } catch (Exception e3) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll1(), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e3, new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{104, -59, -102, 41, -85, 51, -124, -52, 61, -90, -92, 110, -43, 30, -8, -80, 12, -45, -52, 100, -115, 118, -42, -4, -73, 96}, new byte[]{-115, 64, 41, -64, 60, -98, 98, 89}))));
        }
        this.f561IlIlllIIlI1 = null;
        this.f579lllllIllIl1.clear();
    }

    public final void IllIIlIIII1(final double d, final double d2) {
        this.f562IlIllll1.post(new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                IllIIlIIII1.IlIlllIIlI1(IllIIlIIII1.this, d, d2);
            }
        });
    }

    public final boolean lIIIIlllllIlll1(String str) {
        boolean z = true;
        try {
            Runtime runtime = Runtime.getRuntime();
            Field declaredField = runtime.getClass().getDeclaredField(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{23, -31, 78, 121, -40, -67, 34, -68, 25, -4, 78, 111, -44, -68, 29}, new byte[]{123, -114, 47, 29, -67, -39, 110, -43}));
            declaredField.setAccessible(true);
            Object obj = declaredField.get(runtime);
            Set set = TypeIntrinsics.isMutableSet(obj) ? (Set) obj : null;
            if (set != null) {
                if (set.contains(System.mapLibraryName(str))) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            IlIllll1();
            byte[] bArr = {41, -60, 46, 0, -37, 124, ByteCompanionObject.MIN_VALUE, -79};
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllliiiill1.llllIIIIll1(new byte[]{-64, 68, -76, -24, 100, -5, 101, 62, -92, 33, -98, -124, 61, -33, 0, 87, -74, 97, -53, -70, 72, -103, 10, 17, -63, 121, -109, -25, 81, -54, 102, 49, -88, 33, -118, -79, 51, -56, 37, 94, -107, 72, -53, -80, 70, -108, 47, 36, -52, 65, -104, -28, 96, -22, 102, 39, -112, 33, -110, -113}, bArr);
            try {
                String mapLibraryName = System.mapLibraryName(str);
                InputStream inputStream = Runtime.getRuntime().exec(lllliiiill1.llllIIIIll1(new byte[]{-47, -107, -6, -70, 88, 114, -117, 90, -110}, new byte[]{-67, -26, -38, -107, 40, 0, -28, 57}) + Process.myPid() + lllliiiill1.llllIIIIll1(new byte[]{-28, 112, 6, -49, 57}, new byte[]{-53, 29, 103, -65, 74, 67, -73, -59})).getInputStream();
                Intrinsics.checkNotNullExpressionValue(inputStream, lllliiiill1.llllIIIIll1(new byte[]{46, 44, 63, 26, -63, -50, -111, 1, 26, 61, 57, 54, -50, -45, -52, 91, 103, 103, 98}, new byte[]{73, 73, 75, 83, -81, -66, -28, 117}));
                Reader inputStreamReader = new InputStreamReader(inputStream, Charsets.UTF_8);
                BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                try {
                    Iterator<String> it = TextStreamsKt.lineSequence(bufferedReader).iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        }
                        String next = it.next();
                        Intrinsics.checkNotNull(mapLibraryName);
                        if (StringsKt.contains$default((CharSequence) next, (CharSequence) mapLibraryName, false, 2, (Object) null)) {
                            break;
                        }
                    }
                    CloseableKt.closeFinally(bufferedReader, null);
                    return z;
                } finally {
                }
            } catch (Exception e) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll1(), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-35, -20, 69, 29, 87, 123, -100, -14, 79, 38, -77, -98, 45, 100, 97, 118, -79, -17, 45, 70, 117, 57, 120, 37, -35, -49, 68, 30, 108, 111, 26, 39, -98, 117, -27}, new byte[]{59, 79, -59, -5, -56, -34, -14, -109}))));
                return false;
            }
        }
    }

    public final void lIllIIIlIl1() {
        PeerConnection.RTCConfiguration rTCConfiguration = new PeerConnection.RTCConfiguration(CollectionsKt.listOf((Object[]) new PeerConnection.IceServer[]{PeerConnection.IceServer.builder(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{103, 54, 90, -124, -19, 11, 23, 13, 61, 112, 30, -60, -26, 8, 23, 18, 32, 121, 27, -34, -32, 2}, new byte[]{19, 67, 40, -22, -41, 58, 39, 60})).setUsername(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{84, -126, 43, ByteCompanionObject.MIN_VALUE, 51, -69, -80, 58}, new byte[]{35, -9, 70, -23, 71, -34, -45, 82})).setPassword(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-45, 78, 57, 16, -14, 74, 22, -17, -118, 88, 59, 20, -58, 30, 71, -76}, new byte[]{-92, 59, 84, 121, -122, 47, 117, -121})).createIceServer(), PeerConnection.IceServer.builder(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{121, -59, -104, -38, -27, 65, -88, -61, 35, -121, -33, -102, -18, 69, -85, -37, 60, ByteCompanionObject.MIN_VALUE, -33, -114, -20, 68, -81, -51}, new byte[]{13, -80, -22, -76, -33, 112, -104, -11})).setUsername(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{58, 114, 87, 36, 111, -69, 114, -28}, new byte[]{77, 7, 58, 77, 27, -34, 17, -116})).setPassword(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-35, 72, -111, 12, -109, 87, 36, -118, -124, 94, -109, 8, -89, 3, 117, -47}, new byte[]{-86, 61, -4, 101, -25, 50, 71, -30})).createIceServer()}));
        rTCConfiguration.bundlePolicy = PeerConnection.BundlePolicy.MAXBUNDLE;
        rTCConfiguration.rtcpMuxPolicy = PeerConnection.RtcpMuxPolicy.REQUIRE;
        rTCConfiguration.sdpSemantics = PeerConnection.SdpSemantics.UNIFIED_PLAN;
        rTCConfiguration.continualGatheringPolicy = PeerConnection.ContinualGatheringPolicy.GATHER_CONTINUALLY;
        rTCConfiguration.disableIPv6OnWifi = true;
        rTCConfiguration.iceCandidatePoolSize = 0;
        rTCConfiguration.iceTransportsType = PeerConnection.IceTransportsType.ALL;
        IlIllll1();
        IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-105, 77, 31, -2, -25, -123, -81, -110, -14, 32, 8, -115, -69, -74, -64, 28, 32, -111, -57, 75, 57, 90, 58, ByteCompanionObject.MAX_VALUE, 29, -85, -22, 126, 63, 75, 33, 83, 28, -27, 109, -98, -47, -40, -11, -110}, new byte[]{114, -59, -124, 27, 92, 63, 72, 60});
        MediaConstraints mediaConstraints = new MediaConstraints();
        mediaConstraints.optional.add(new MediaConstraints.KeyValuePair(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-112, -81, 81, 105, 40, -14, 74, 61, -97, -66, 68, 91, 28, -14, 91, 40, -71, -66, 83, 110}, new byte[]{-44, -37, 61, 26, 123, ByteCompanionObject.MIN_VALUE, 62, 77}), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{7, 25, 57, 57}, new byte[]{115, 107, 76, 92, 58, -37, -65, 26})));
        mediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{58, -25, 54, -68, 59, -37, 67, -116, 3, -21, 33, -100, 58, -18, 84, -113, 22}, new byte[]{115, -124, 83, -24, 73, -70, 45, -1}), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-85, -5, -84}, new byte[]{-54, -105, -64, 56, 80, 116, 101, -12})));
        PeerConnection peerConnection = this.f563IllIIlIIII1;
        if (peerConnection != null) {
            if (peerConnection != null) {
                try {
                    peerConnection.close();
                } catch (Exception e) {
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(IlIllll1(), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-67, -11, 70, -107, 17, 23, -34, 77, -1, -105, 111, -8, -90, -22, 93, -65, 42, 51, -102, 18, -24, -33, 91, -82, 49, 31, -101, 92, 99, 30, -119, 50, -20, -43, -49, 92}, new byte[]{88, 112, -11, 124, -122, -70, 56, -38}) + e.getMessage());
                }
            }
            this.f563IllIIlIIII1 = null;
            IlIllll1();
            IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{101, -15, 4, -33, -14, -66, -120, -101, 39, -109, 45, -78, 69, 67, 11, 105, -14, 55, -40, 88, 11, 118, 13, 120, -23, 27, -39}, new byte[]{ByteCompanionObject.MIN_VALUE, 116, -73, 54, 101, 19, 110, 12});
        }
        DataChannel dataChannel = this.f561IlIlllIIlI1;
        if (dataChannel != null) {
            if (dataChannel != null) {
                try {
                    dataChannel.close();
                } catch (Exception e2) {
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(IlIllll1(), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{51, -108, -43, 109, -62, -96, -5, 111, 113, -10, -4, 0, -77, -104, -83, 30, 91, -65, -113, 4, -49, -28, -100, 107, 51, -75, -41, 108, -31, -88, 39, -40}, new byte[]{-42, 17, 102, -124, 85, 13, 29, -8}) + e2.getMessage());
                }
            }
            this.f561IlIlllIIlI1 = null;
            IlIllll1();
            IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-17, -52, -95, 112, 66, -107, -52, -57, -83, -82, -120, 29, 51, -83, -102, -74, -121, -25, -5, 25, 79, -47, -85, -61}, new byte[]{10, 73, 18, -103, -43, 56, 42, 80});
        }
        try {
            PeerConnectionFactory peerConnectionFactory = this.f578llllIllIl1;
            if (peerConnectionFactory == null) {
                Intrinsics.throwUninitializedPropertyAccessException(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{98, 61, -84, ByteCompanionObject.MAX_VALUE, 58, -89, 0, -43, 119, 59, -67, 100, 22, -90, 40, -38, 113, 44, -90, ByteCompanionObject.MAX_VALUE, 0}, new byte[]{18, 88, -55, 13, 121, -56, 110, -69}));
                peerConnectionFactory = null;
            }
            PeerConnection createPeerConnection = peerConnectionFactory.createPeerConnection(rTCConfiguration, new llllIllIl1());
            this.f563IllIIlIIII1 = createPeerConnection;
            if (createPeerConnection == null) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(IlIllll1(), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-50, 77, -100, -115, -10, 123, 100, 73, -25, 12, -106, -109, -10, 126, 48, 88, -88, 124, -112, -124, -31, 92, 43, 83, -26, 73, -106, -107, -6, 112, 42}, new byte[]{-120, 44, -11, -31, -109, 31, 68, 61}));
                llIIllIl1();
            } else {
                IlIllll1();
                IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{42, 91, -95, 114, -27, 58, 63, -112, 31, 93, -80, 105, -55, 59, 113, 27, -14, -91, 33, -69, 28, -77, -39, 110, -97, -76, 91}, new byte[]{122, 62, -60, 0, -90, 85, 81, -2});
            }
        } catch (Exception e3) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(IlIllll1(), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{113, 50, -101, 29, -41, 27, 65, -5, -15, -33, 114, -69, 3, -49, 15, -50, -9, -50, 105, -105, 2, -127, -124, 15, 37, 82, -76, 93, 86, -127}, new byte[]{-108, -70, 0, -8, 108, -95, 97, -85}) + e3.getMessage());
            e3.printStackTrace();
            llIIllIl1();
        }
    }

    public final void llllllIlIIIlll1() {
        IIIlIllIlI1();
    }

    public static final void IllIIlIIII1(IllIIlIIII1 illIIlIIII1, double d, double d2) {
        if (!illIIlIIII1.f557IlIIlllllI1) {
            illIIlIIII1.IlIllll1();
            IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-59, 115, 32, 126, -17, 116, 71, 96, -73, 114, 49, 122, -31, 34, 79, 107, -31, 115, 99, 108, -17, 118, 74, 107, -30, 98, 99, ByteCompanionObject.MAX_VALUE, -12, 99, 69, 36, -28, 98, 34, 105, -14, 46, 2, 118, -14, 101, 38, 111, -14, 107, 76, 99, -73, 114, 49, 122, -31, 34, 81, 112, -10, 98, 38}, new byte[]{-105, 22, 67, 27, -122, 2, 34, 4});
            illIIlIIII1.IllIIlIIII1(d, d2);
            return;
        }
        float width = (float) (d * illIIlIIII1.f562IlIllll1.getWidth());
        float height = (float) (d2 * illIIlIIII1.f562IlIllll1.getHeight());
        float abs = illIIlIIII1.f567lIIlIIIIlIlII1 + Math.abs(height - illIIlIIII1.f568lIIlllIIIlllII1) + Math.abs(width - illIIlIIII1.f564IllIlIllll1);
        illIIlIIII1.f567lIIlIIIIlIlII1 = abs;
        if (abs > illIIlIIII1.f551IIIlIllIlI1) {
            if (illIIlIIII1.f565IllllIllllll1 == illIIlIIII1.f575lllIlIIIlI1) {
                long j = illIIlIIII1.f558IlIlIIIlIlIlll1;
                MotionEvent obtain = MotionEvent.obtain(j, j, 0, illIIlIIII1.f572llIIIIlIlllIII1, illIIlIIII1.f570lIllIlIll1, 0);
                illIIlIIII1.f562IlIllll1.dispatchTouchEvent(obtain);
                obtain.recycle();
                illIIlIIII1.f565IllllIllllll1 = illIIlIIII1.f571lIlllIIIII1;
            }
            if (illIIlIIII1.f565IllllIllllll1 == illIIlIIII1.f571lIlllIIIII1) {
                MotionEvent obtain2 = MotionEvent.obtain(illIIlIIII1.f558IlIlIIIlIlIlll1, SystemClock.uptimeMillis(), 2, width, height, 0);
                illIIlIIII1.f562IlIllll1.dispatchTouchEvent(obtain2);
                obtain2.recycle();
            }
        }
        illIIlIIII1.f564IllIlIllll1 = width;
        illIIlIIII1.f568lIIlllIIIlllII1 = height;
    }

    public final void lIllIlIll1() {
        String IlIllll12 = IlIllll1();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        String llllIIIIll12 = lllliiiill1.llllIIIIll1(new byte[]{19, 92, 21, 75, -127, 82, 70, -114, 104, 6, 27, 11}, new byte[]{-10, -32, -107, -82, 38, -39, -82, 49});
        Log.LogLevel logLevel = Log.LogLevel.INFO;
        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, IlIllll12, llllIIIIll12);
        try {
            IlIlIIlIII1.lIIIIlllllIlll1 IlIlllIIlI12 = IlIlllIIlI1.lIIIIlllllIlll1.f248llllIIIIll1.IlIlllIIlI1();
            if (IlIlllIIlI12 != null) {
                IlIlllIIlI12.lIIIIlllllIlll1();
            }
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, IlIllll1(), lllliiiill1.llllIIIIll1(new byte[]{67, 41, -47, -32, -46, -95, 39, 103, 10, 114, -12, -94, -70, -104, 78, 61, 33, 55, -87, -102, -47, -31, 73, 121, 77, 30, -33, -29, -42, -101, 44, 100, 42}, new byte[]{-85, -106, 79, 6, 92, 4, -61, -40}));
        } catch (Exception e) {
            String IlIllll13 = IlIllll1();
            StringBuilder sb = new StringBuilder();
            byte[] bArr = {67, 83, -83, 3, -69, ByteCompanionObject.MAX_VALUE, 68, -101};
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll13, sb.append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-85, -20, 51, -27, 53, -38, 3, -55, 19, 16, 75, -97, 54, -102, -50, 58, -90, -9, 28, -21, 15, -38, 126, -69}, bArr)).append(e).toString());
            llIIllIl1();
        }
    }

    public final void llllIllIl1(final double d, final double d2) {
        this.f562IlIllll1.post(new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda19
            @Override // java.lang.Runnable
            public final void run() {
                IllIIlIIII1.llllIllIl1(IllIIlIIII1.this, d, d2);
            }
        });
    }

    public static final void llllIllIl1(IllIIlIIII1 illIIlIIII1, double d, double d2) {
        if (!illIIlIIII1.f557IlIIlllllI1) {
            illIIlIIII1.IlIllll1();
            IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-69, 78, 123, -22, 49, 59, -5, 43, 60, -67, -120, 97, -35, 111, 34, -33, -69, 104, 108, -23, 37, 2, 122, -10, -28, 63, 119, -101, 94, 17, 27, 61, 47, -69, -86, 92, -51, -22, -19, 45, -78, 102, 65, -22, 6, 54, 120, -52, -8, 60, 96, -85, 93, 49, 20, -67, -26, 108}, new byte[]{93, -38, -51, 15, -71, -117, -97, 89});
            return;
        }
        float width = (float) (d * illIIlIIII1.f562IlIllll1.getWidth());
        float height = (float) (d2 * illIIlIIII1.f562IlIllll1.getHeight());
        illIIlIIII1.f567lIIlIIIIlIlII1 += Math.abs(height - illIIlIIII1.f568lIIlllIIIlllII1) + Math.abs(width - illIIlIIII1.f564IllIlIllll1);
        int i = illIIlIIII1.f565IllllIllllll1;
        if (i == illIIlIIII1.f571lIlllIIIII1) {
            MotionEvent obtain = MotionEvent.obtain(illIIlIIII1.f558IlIlIIIlIlIlll1, SystemClock.uptimeMillis(), 3, width, height, 0);
            illIIlIIII1.f562IlIllll1.dispatchTouchEvent(obtain);
            obtain.recycle();
            illIIlIIII1.IlIllll1();
            IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-116, -109, 61, 94, 56, -109, -61, 126, -7, -2, 54, 36, 93, -121, -66, 32, -27, -119, 66, 59, 51, 122, 103, -111, 35, 87, -27, -28, -15, 122, 106, -122, 47, 84, 79, 1, 57, -33, -97, 115}, new byte[]{106, 24, -85, -69, -78, 59, 36, -59});
        } else if (i == illIIlIIII1.f575lllIlIIIlI1) {
            illIIlIIII1.IlIllll1();
            IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-84, 69, 92, 21, -89, 93, 18, -118, -35, 63, 123, 91, -38, 100, 103, -25, -42, 69, 30, 79, -89, 59, 76, -116, -93, 125, 87, 22, -78, 78, 16, -70, -16, 62, 76, 102, -39, 101, ByteCompanionObject.MAX_VALUE, -27, -16, 108, 30, 79, -79, 54, 117, -66, -82, 95, 124, 27, -110, 112, 28, -90, -19}, new byte[]{75, -38, -15, -13, 61, -33, -12, 1});
        }
        illIIlIIII1.f557IlIIlllllI1 = false;
        illIIlIIII1.f565IllllIllllll1 = illIIlIIII1.f573llIIllIl1;
        illIIlIIII1.IlIllll1();
        IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{15, 109, -33, 74, 13, -21, -73, 38, 101, 3, -52, 7, 96, -8, -63, 110, 116, 121, -90, 19, 11, -86, -43, 5, 14, 91, -25, 72, 13, -11, -76, 8, 104}, new byte[]{-23, -26, 73, -81, -121, 67, 82, -120});
    }

    public final String IlIllll1() {
        return IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{14, 5, -33, 114, 53, -115, -57, 58, 6, 49, -56, 117, 6, -118, -47, 37, 58, 38, -23, 117, 13, ByteCompanionObject.MIN_VALUE, -57, 63, 111}, new byte[]{85, 82, -70, 16, 99, -28, -94, 77}) + this.f577llllIIIIll1 + ']';
    }

    public final void IlIlllIIlI1(final String str) {
        this.f562IlIllll1.post(new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                IllIIlIIII1.llllIIIIll1(str, this);
            }
        });
    }

    public final void IlIlllIIlI1() {
        try {
            Context llllIllIl12 = IlIlllIIlI1.lIIIIlllllIlll1.llllIllIl1();
            Object systemService = llllIllIl12 != null ? llllIllIl12.getSystemService(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-62, 19, -43, -33, 42, -90, -49, -124, -41, 21, -49, -56}, new byte[]{-95, 124, -69, -79, 79, -59, -69, -19})) : null;
            ConnectivityManager connectivityManager = systemService instanceof ConnectivityManager ? (ConnectivityManager) systemService : null;
            NetworkCapabilities networkCapabilities = connectivityManager != null ? connectivityManager.getNetworkCapabilities(connectivityManager != null ? connectivityManager.getActiveNetwork() : null) : null;
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            llllIIIIll1(1, lllliiiill1.llllIIIIll1(new byte[]{-35, -16, -87, 100, 90, -122, -6, -11, -116, -85, -72, 2}, new byte[]{58, 77, 56, -125, -31, 26, 29, ByteCompanionObject.MAX_VALUE}), MapsKt.mapOf(TuplesKt.to(lllliiiill1.llllIIIIll1(new byte[]{-78, 33, 114, 60, -96, 5, 43, 29, -81, 55, 85}, new byte[]{-37, 82, 49, 83, -50, 107, 78, 126}), Boolean.valueOf(networkCapabilities != null)), TuplesKt.to(lllliiiill1.llllIIIIll1(new byte[]{59, 98, -96, -5, 55, -61, 11, -104, 61, 102, -89}, new byte[]{83, 3, -45, -78, 89, -73, 110, -22}), Boolean.valueOf(networkCapabilities != null && networkCapabilities.hasCapability(12))), TuplesKt.to(lllliiiill1.llllIIIIll1(new byte[]{119, 4, -107, 29, 87, -73, -9, 14, 115, 4, -108}, new byte[]{31, 101, -26, 94, 50, -37, -101, 123}), Boolean.valueOf(networkCapabilities != null && networkCapabilities.hasTransport(0))), TuplesKt.to(lllliiiill1.llllIIIIll1(new byte[]{65, -81, 56, -118, 58, -79, 11}, new byte[]{41, -50, 75, -35, 83, -41, 98, 60}), Boolean.valueOf(networkCapabilities != null && networkCapabilities.hasTransport(1))), TuplesKt.to(lllliiiill1.llllIIIIll1(new byte[]{5, -19, 39, 101, -111, -83, -75, 11, 3, -23, 32}, new byte[]{109, -116, 84, 32, -27, -59, -48, 121}), Boolean.valueOf(networkCapabilities != null && networkCapabilities.hasTransport(3))), TuplesKt.to(lllliiiill1.llllIIIIll1(new byte[]{-78, 55, -119, -68, -79, -121}, new byte[]{-38, 86, -6, -22, -63, -23, -41, 95}), Boolean.valueOf(networkCapabilities != null && networkCapabilities.hasTransport(4))), TuplesKt.to(lllliiiill1.llllIIIIll1(new byte[]{47, 6, 77, -5, -54, -89, 117, 3, 42, 4, 120, -12, -41, -73, 112, 15, 47, 29, 82}, new byte[]{75, 105, 58, -107, -71, -45, 7, 102}), networkCapabilities != null ? Integer.valueOf(networkCapabilities.getLinkDownstreamBandwidthKbps()) : null), TuplesKt.to(lllliiiill1.llllIIIIll1(new byte[]{98, 111, -17, -100, 106, 56, 71, 85, 85, 126, -14, -116, 111, 52, 66, 76, ByteCompanionObject.MAX_VALUE}, new byte[]{23, 31, -100, -24, 24, 93, 38, 56}), networkCapabilities != null ? Integer.valueOf(networkCapabilities.getLinkUpstreamBandwidthKbps()) : null), TuplesKt.to(lllliiiill1.llllIIIIll1(new byte[]{122, -37, 68, -75, 12, -46, -94, 50, 123, -41, 77, -68, 25, -42}, new byte[]{9, -78, 35, -37, 109, -66, -15, 70}), networkCapabilities != null ? Integer.valueOf(networkCapabilities.getSignalStrength()) : null)));
        } catch (Exception e) {
            llllIIIIll1(3, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-91, 106, -50, 83, 107, 56, -29, 118, -46, 46, -11, 41, 19, 23, -78, 45, -61, 72, -85, 17, 69, 117, -80, 110}, new byte[]{67, -55, 78, -75, -12, -99, 4, -53}), e.getMessage());
        }
    }

    public static final void lllllIllIl1(final IllIIlIIII1 illIIlIIII1) {
        illIIlIIII1.IIlIlllllllI1++;
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        illIIlIIII1.llllIIIIll1(1, lllliiiill1.llllIIIIll1(new byte[]{61, -87, 103, 46, 90, -3, -84, -70, 85, -15, 69, 88, -43, 63, 32, 95, -82, 112, -97, -79, -89, 28, 38}, new byte[]{-40, 25, -6, -58, -11, 104, 69, 61}), lllliiiill1.llllIIIIll1(new byte[]{-117, -66, 50, -45}, new byte[]{108, 18, -98, -13, -64, 77, 31, 17}) + illIIlIIII1.IIlIlllllllI1 + lllliiiill1.llllIIIIll1(new byte[]{16, 69, -123, -86, 46, 31, 87, 68, -97, 54}, new byte[]{48, -93, 41, 11, -53, -81, -54, -84}));
        illIIlIIII1.f554IIlllllIlll1.post(new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                IllIIlIIII1.IlIIlllllI1(IllIIlIIII1.this);
            }
        });
    }

    public final void llllIllIl1(final String str) {
        this.f562IlIllll1.post(new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                IllIIlIIII1.llllIllIl1(IllIIlIIII1.this, str);
            }
        });
    }

    public static final void llllIllIl1(final IllIIlIIII1 illIIlIIII1, final String str) {
        illIIlIIII1.f562IlIllll1.evaluateJavascript(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-38, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 103, -118, -50, 112, -20, 42, -11, -65, 33, -60, -110, 62, -12, 84, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -51, ByteCompanionObject.MAX_VALUE, -3, 126, -3, -77, 59, -123, -51, 123, -54, 50, -7, -67, 42, -126, -49, 62, -78, 126, -8, -65, 44, -103, -42, 123, -31, 42, -78, -79, 44, -104, -46, 104, -22, 27, -16, -75, 34, -119, -43, 106, -76, 84, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -46, 120, -81, 118, -3, -77, 59, -123, -51, 123, -54, 50, -7, -67, 42, -126, -49, 62, -87, 120, -68, -8, 46, -113, -49, 119, -7, 59, -39, -68, 42, -127, -34, 112, -5, 112, -24, -79, 40, -94, -38, 115, -22, 126, -95, -19, 114, -52, -100, 87, -63, 14, -55, -124, 104, -52, -57, 98, -81, 84, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -79, 44, -104, -46, 104, -22, 27, -16, -75, 34, -119, -43, 106, -95, 42, -3, -73, 1, -115, -42, 123, -81, 99, -95, -19, 111, -53, -17, 91, -41, 10, -35, -126, 10, -83, -100, 62, -13, 34, -68, -38, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -115, -40, 106, -26, 40, -7, -107, 35, -119, -42, 123, -31, 42, -78, -71, 60, -81, -44, 112, -5, 59, -14, -92, 10, -120, -46, 106, -18, 60, -16, -75, 102, -59, -101, 101, -123, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 113, -77, -16, -86, 74, 57, -8, 17, -62, 122, 76, -58, 4, 5, -115, 106, -37, 57, 54, -18, 106, 83, -68, 36, -74, 29, 74, -88, 104, 29, -15, 51, -46, 122, 120, -18, 10, 48, -127, 104, -20, 4, 56, -5, 88, 93, -115, 2, -70, 33, 76, 69, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -123, -35, 62, -89, 63, -1, -92, 38, -102, -34, 91, -29, 59, -15, -75, 33, -104, -107, 119, -4, 29, -13, -66, 59, -119, -43, 106, -54, 58, -11, -92, 46, -114, -41, 123, -90, 126, -25, -38, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -96, 113, -68, 53, -32, 85, 95, -92, 1, 61, -13, -66, 59, -119, -43, 106, -54, 58, -11, -92, 46, -114, -41, 123, 106, -37, 31, 55, -5, 76, -79, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -90, 46, -98, -101, 109, -22, 50, -7, -77, 59, -123, -44, 112, -81, 99, -68, -89, 38, -126, -33, 113, -8, 112, -5, -75, 59, -65, -34, 114, -22, 61, -24, -71, 32, -126, -109, 55, -76, 84, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -51, ByteCompanionObject.MAX_VALUE, -3, 126, -18, -79, 33, -117, -34, 62, -78, 126, -17, -75, 35, -119, -40, 106, -26, 49, -14, -2, 40, -119, -49, 76, -18, 48, -5, -75, 14, -104, -109, 46, -90, 101, -106, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 62, -81, 126, -68, -16, 111, -52, -101, 104, -18, 44, -68, -92, 42, -108, -49, 80, -32, 58, -7, -16, 114, -52, -33, 113, -20, 43, -15, -75, 33, -104, -107, 125, -3, 59, -3, -92, 42, -72, -34, 102, -5, 16, -13, -76, 42, -60, -100}, new byte[]{-48, 79, -20, -69, 30, -113, 94, -100}) + str + IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{48, 36, 18, -26, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 101, 108, 71, -117, 36, 121, -18, 88, 123, 104, 93, -119, 2, 56, -28, 73, 114, 99, 93, -97, 105, 126, -79, 29, 56, 34, 9, 9, -55, -9, 99, -92, -77, -24, -98, 94, -88, -41, 3, -39, -81, -96, -50, 118, -59, -78, 12, -72, -14, -93, -112, -26, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 101, 108, 71, -117, 36, 121, -29, 83, 100, 104, 91, -104, 15, 56, -18, 88, 63, 121, 76, -108, 53, 25, -27, 89, 114, 36, 18, -26, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 101, 108, 71, -117, 36, 121, -7, 88, 99, 94, 93, -115, 51, 35, -53, 91, 99, 104, 91, -60, 53, 50, -14, 73, 89, 98, 77, -119, 104, 108, ByteCompanionObject.MIN_VALUE, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -98, 32, 57, -19, 88, 57, 126, 76, -104, 4, 57, -18, 124, 113, 121, 76, -98, 105, 35, -17, 69, 99, 67, 70, -120, 36, 126, -79, 55, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 50, 50, -26, 88, 116, 121, 64, -125, 47, 121, -8, 88, 122, 98, 95, -119, 0, 59, -26, 111, 118, 99, 78, -119, 50, ByteCompanionObject.MAX_VALUE, -93, 6, 29, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 36, -17, 81, 114, 110, 93, -123, 46, 57, -92, 92, 115, 105, 123, -115, 47, 48, -17, 21, 101, 108, 71, -117, 36, 126, -79, 55, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 75, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 34, 6, -52, -87, -16, 44, -40, -104, -100, 64, -126, 49, 34, -2, -39, -83, -122, -51, 87, -9, 93, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 95, -115, 51, 119, -17, 75, 114, 99, 93, -52, 124, 119, -28, 88, 96, 45, 108, -102, 36, 57, -2, 21, 48, 100, 71, -100, 52, 35, -83, 17, 55, 118, 9, -114, 52, 53, -24, 81, 114, 126, 19, -52, 53, 37, -1, 88, 55, 112, 0, -41, 75, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 108, 74, -104, 40, 33, -17, 120, 123, 104, 68, -119, 47, 35, -92, 89, 126, 126, 89, -115, 53, 52, -30, 120, 97, 104, 71, -104, 105, 50, -4, 88, 121, 121, 0, -41, 75, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, ByteCompanionObject.MAX_VALUE, 76, -104, 52, 37, -28, 29, 99, ByteCompanionObject.MAX_VALUE, 92, -119, 122, 93, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -9, 29, 114, 97, 90, -119, 97, 44, ByteCompanionObject.MIN_VALUE, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -61, 110, 119, 111, -110, -82, -23, -109, 98, -89, -50, 36, -44, -105, -105, -63, 82, -46, -78, 15, -104, -15, -84, -81, -26, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 97, 108, 91, -52, 46, 59, -18, 107, 118, 97, 92, -119, 97, 106, -86, 92, 116, 121, 64, -102, 36, 18, -26, 88, 122, 104, 71, -104, 111, 33, -21, 81, 98, 104, 9, -112, 61, 119, -83, 26, 44, 7, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -4, 92, 101, 45, 71, -119, 54, 4, -2, 92, 101, 121, 9, -47, 97, 54, -23, 73, 126, 123, 76, -87, 45, 50, -25, 88, 121, 121, 7, -97, 36, 59, -17, 94, 99, 100, 70, -126, 18, 35, -21, 79, 99, 45, 85, -112, 97, 56, -26, 89, 65, 108, 69, -103, 36, 121, -26, 88, 121, 106, 93, -124, 122, 93, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 95, -115, 51, 119, -28, 88, 96, 72, 71, -120, 97, 106, -86, 92, 116, 121, 64, -102, 36, 18, -26, 88, 122, 104, 71, -104, 111, 36, -17, 81, 114, 110, 93, -123, 46, 57, -49, 83, 115, 45, 85, -112, 97, 56, -26, 89, 65, 108, 69, -103, 36, 121, -26, 88, 121, 106, 93, -124, 122, 93, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 9, -52, 97, 119, -86, 29, 55, 45, 95, -115, 51, 119, -28, 88, 
        96, 91, 72, ByteCompanionObject.MIN_VALUE, 52, 50, -86, 0, 55, 98, 69, -120, 23, 54, -26, 72, 114, 35, 90, -103, 35, 36, -2, 79, 126, 99, 78, -60, 113, 123, -86, 83, 114, 122, 122, -104, 32, 37, -2, 20, 55, 38, 9, -53}, new byte[]{23, 13, 41, -20, 65, 87, -118, 61}) + str + IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-47, -17, -126, -4, -113, 83, 91, -24, -105, -93, -36, -71, -50, 76, 74, -36, -123, -69, -37, -75, -114, 88, 23, -48, -109, -72, -20, -78, -124, 22, 4, -76, -42, -17, -119, -4, -64, 31, 31, -98, -42, -17, -119, -4, -64, 31, 31, -98, -42, -17, -119, -4, -64, 31, 31, -98, -42, -17, -119, -4, -127, 92, 75, -41, ByteCompanionObject.MIN_VALUE, -86, -20, -80, -123, 82, 90, -48, -126, -31, -33, -67, -116, 74, 90, -98, -53, -17, -57, -71, -105, 105, 94, -46, -125, -86, -110, -42, -64, 31, 31, -98, -42, -17, -119, -4, -64, 31, 31, -98, -42, -17, -119, -4, -64, 31, 31, -98, -42, -17, -119, -4, -64, 31, 31, -98, -105, -84, -35, -75, -106, 90, 122, -46, -109, -94, -52, -78, -108, 17, 76, -37, -102, -86, -54, -88, -119, 80, 81, -19, -126, -82, -37, -88, -64, 2, 31, -33, -107, -69, -64, -86, -123, 122, 83, -37, -101, -86, -57, -88, -50, 76, 90, -46, -109, -84, -35, -75, -113, 81, 122, -48, -110, -17, -108, -4, -114, 90, 72, -19, -126, -82, -37, -88, -64, 20, 31, -103}, new byte[]{-10, -49, -87, -36, -32, 63, 63, -66}) + str + IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{93, -71, 80, -12, 80, -37, 51, 90, 65, -99, 28, -79, 30, -100, 103, 18, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 28, -79, 30, -100, 77, 18, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 28, -66, 17, -100, -81, -107, -36, 114, -77, 0, 87, -46, 55, 71, 14, 115, -122, 26, -38, 7, -15, 56, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 28, -79, 72, -35, 53, 18, 31, -31, 89, -1, 74, -100, 122, 18, 20, -14, 75, -79, 123, -54, 34, 92, 14, -65, 27, -8, 80, -52, 50, 70, 93, -69, 28, -22, 30, -34, 50, 80, 24, -5, 89, -30, 4, -100, 51, 64, 15, -14, 28, -20, 23, -121, 77, 18, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 28, -16, 93, -56, 46, 68, 31, -46, 80, -12, 83, -39, 41, 70, 84, -13, 85, -30, 78, -35, 51, 81, 18, -46, 74, -12, 80, -56, 111, 87, 12, -14, 82, -27, 23, -121, 77, 18, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 28, -29, 91, -56, 50, 64, 20, -73, 72, -29, 75, -39, 124, 56, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 28, -79, 30, -100, 103, 18, 7, -99, 28, -79, 30, -100, 103, 18, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 28, -79, 30, -100, 58, 56, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 28, -79, 76, -39, 51, 71, 8, -7, 28, -9, 95, -48, 52, 87, 65, -99, 28, -79, 30, -100, 103, 18, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 65, -72, 22, -107, 124, 56, 90, -73, 28, -79, 30, -100, 103, 18, 90, -73, 28, -79, 30, -100, 103, 18}, new byte[]{122, -105, 60, -111, 62, -68, 71, 50}), new ValueCallback() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda20
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                IllIIlIIII1.llllIIIIll1(IllIIlIIII1.this, str, (String) obj);
            }
        });
    }

    public static final void llIIIIlIlllIII1(IllIIlIIII1 illIIlIIII1) {
        if (illIIlIIII1.f576lllIlIlllI1.get()) {
            return;
        }
        illIIlIIII1.IllIIlIIII1();
    }

    public final void IllIIlIIII1(final String str) {
        this.f562IlIllll1.evaluateJavascript(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-66, 35, 1, -11, -78, 75, 117, -55, -108, 35, 1, -11, -78, 67, 51, -100, -38, 96, 85, -68, -3, 5, 125, -64, -108, 120, 43, -11, -78, 75, 117, -55, -108, 35, 1, -11, -78, 75, 117, -55, -108, 35, 1, -93, -13, 25, 117, -99, -47, 123, 85, -11, -81, 75, 114}, new byte[]{-76, 3, 33, -43, -110, 107, 85, -23}) + StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(str, IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{57}, new byte[]{101, 28, 94, -77, -1, 35, -102, 22}), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{97, -2}, new byte[]{61, -94, -123, 12, 104, -6, -59, 117}), false, 4, (Object) null), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{86}, new byte[]{113, -51, -85, -123, -75, 52, -80, -1}), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{59, -103}, new byte[]{103, -66, -52, -75, 62, -64, 2, 36}), false, 4, (Object) null), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{10}, new byte[]{40, -9, -6, 79, -52, 58, -118, -111}), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{97, -89}, new byte[]{61, -123, -93, -115, 96, -110, -11, -2}), false, 4, (Object) null), "\n", IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{120, 21}, new byte[]{36, 123, -71, -111, -62, 77, 120, -99}), false, 4, (Object) null), "\r", IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{90, -1}, new byte[]{6, -115, -86, 108, -32, -48, -73, 12}), false, 4, (Object) null), "\t", IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{75, 22}, new byte[]{23, 98, -4, 58, -81, -20, -87, -66}), false, 4, (Object) null) + IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-29, 45, 83, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 11, 3, 46, -127, 61, -89, 98, 48, 11, 7, 25, -51, 57, -87, 115, 55, 9, 66, 97, -127, 56, -85, 117, 44, 16, 7, 50, -43, 114, -91, 117, 45, 20, 20, 57, -28, 48, -95, 123, 60, 19, 22, 103, -85, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 86, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -83, 112, 121, 85, 3, 63, -43, 53, -78, 115, 28, 17, 7, 49, -60, 50, -80, 54, ByteCompanionObject.MAX_VALUE, 91, 66, 116, -64, 63, -80, ByteCompanionObject.MAX_VALUE, 47, 24, 39, 48, -60, 49, -95, 120, 45, 83, 22, 61, -58, 18, -91, 123, 60, 93, 95, 97, -100, 124, -29, 95, 23, 45, 55, 8, -122, 124, -72, 106, 121, 119, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 119, 58, 9, 11, 42, -60, 25, -88, 115, 52, 24, 12, 40, -113, 40, -91, 113, 23, 28, 15, 57, -127, 97, -7, 43, 121, 90, 54, 25, -7, 8, -123, 68, 28, 60, 69, 124, -35, 32, -28, 28, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 61, -89, 98, 48, 11, 7, 25, -51, 57, -87, 115, 55, 9, 76, 53, -46, 31, -85, 120, 45, 24, 12, 40, -28, 56, -83, 98, 56, 31, 14, 57, -120, 117, -28, 109, 83, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 86, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 11, 58, -127, 116, -91, 117, 45, 20, 20, 57, -28, 48, -95, 123, 60, 19, 22, 114, -56, 47, -121, 121, 55, 9, 7, 50, -43, 25, -96, ByteCompanionObject.MAX_VALUE, 45, 28, 0, 48, -60, 117, -28, 109, 83, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 82, 77, 124, 68, -8, 64, -15, -55, -5, -121, -45, 14, -69, 120, ByteCompanionObject.MIN_VALUE, -79, -61, -13, 56, -56, 42, 33, -109, -38, -102, -42, -4, -85, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 42, -91, 100, 121, 14, 7, 48, -60, 63, -80, ByteCompanionObject.MAX_VALUE, 54, 19, 66, 97, -127, 43, -83, 120, 61, 18, 21, 114, -58, 57, -80, 69, 60, 17, 7, 63, -43, 53, -85, 120, 113, 84, 89, 86, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -41, 61, -74, 54, 43, 28, 12, 59, -60, 103, -50, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 28, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 48, 27, 66, 116, -46, 57, -88, 115, 58, 9, 11, 51, -49, 114, -74, 119, 55, 26, 7, 31, -50, 41, -86, 98, 121, 67, 66, 108, -120, 124, -65, 28, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -45, 61, -86, 113, 60, 93, 95, 124, -46, 57, -88, 115, 58, 9, 11, 51, -49, 114, -93, 115, 45, 47, 3, 50, -58, 57, -123, 98, 113, 77, 75, 103, -85, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 33, -28, 115, 53, 14, 7, 124, -38, 86, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 77, 115, -127, -71, 98, -108, -65, -29, -2, -70, 19, -3, 34, -118, -48, -108, -30, -43, 71, -41, 109, -2, -43, -2, -121, -57, 21, -77, 120, -102, -68, -11, -7, -71, 26, -26, 32, -82, -39, -103, -38, -10, 68, -64, 108, -13, -36, -2, -123, -24, 1, -70, 88, -67, -68, -51, -36, -69, 59, -40, 44, -102, -38, -104, -7, -24, -85, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 15, 3, 50, -58, 57, -28, 43, 121, 25, 13, 63, -44, 49, -95, 120, 45, 83, 1, 46, -60, 61, -80, 115, 11, 28, 12, 59, -60, 116, -19, 45, 83, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 46, -91, 120, 62, 24, 76, 47, -60, 48, -95, 117, 45, 51, 13, 56, -60, 31, -85, 120, 45, 24, 12, 40, -46, 116, -91, 117, 45, 20, 20, 57, -28, 48, -95, 123, 60, 19, 22, 117, -102, 86, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 16, 61, -49, 59, -95, 56, 58, 18, 14, 48, -64, 44, -73, 115, 113, 27, 3, 48, -46, 57, -19, 45, 83, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, 
        -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 0, 104, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 86, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -114, 115, -28, -13, -47, -35, -117, -59, 5, -75, 68, -97, -67, -59, -49, -69, 59, -40, 33, -112, -36, -104, -52, -27, -85, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 46, -91, 120, 62, 24, 76, 56, -60, 48, -95, 98, 60, 62, 13, 50, -43, 57, -86, 98, 42, 85, 75, 103, -85, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 86, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -21, 57, 121, -101, -19, -50, 68, -39, 97, -16, -49, -6, -124, -64, 13, -76, 78, -108, -66, -1, -37, 86, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -41, 61, -74, 54, 45, 24, 26, 40, -17, 51, -96, 115, 121, 64, 66, 56, -50, 63, -79, 123, 60, 19, 22, 114, -62, 46, -95, 119, 45, 24, 54, 57, -39, 40, -118, 121, 61, 24, 74, 40, -60, 36, -80, 63, 98, 119, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 16, 61, -49, 59, -95, 56, 48, 19, 17, 57, -45, 40, -118, 121, 61, 24, 74, 40, -60, 36, -80, 88, 54, 25, 7, 117, -102, 86, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -50, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 57, 118, 93, -118, -14, 31, -69, 121, -72, -68, -8, -21, -70, 1, -37, 33, -98, -23, -101, -12, -37, 71, -64, 104, -13, -55, -13, -117, -63, 3, 86, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -74, 119, 55, 26, 7, 114, -46, 57, -80, 69, 45, 28, 16, 40, -32, 58, -80, 115, 43, 85, 22, 57, -39, 40, -118, 121, 61, 24, 75, 103, -85, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 46, -91, 120, 62, 24, 76, 47, -60, 40, -127, 120, 61, 60, 4, 40, -60, 46, -20, 98, 60, 5, 22, 18, -50, 56, -95, 63, 98, 119, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 17, 57, -51, 57, -89, 98, 48, 18, 12, 114, -45, 57, -87, 121, 47, 24, 35, 48, -51, 14, -91, 120, 62, 24, 17, 116, -120, 103, -50, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 101, 60, 17, 7, 63, -43, 53, -85, 120, 119, 28, 6, 56, -13, 61, -86, 113, 60, 85, 16, 61, -49, 59, -95, 63, 98, 119, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 104, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 115, -114, 124, 44, -79, -1, -104, -19, -51, -56, 50, -76, 99, 45, -103, -40, -41, 69, -25, 114, 28, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 47, 28, 16, 124, -56, 50, -76, 99, 45, 56, 20, 57, -49, 40, -28, 43, 121, 19, 7, 43, -127, 25, -78, 115, 55, 9, 74, 123, -56, 50, -76, 99, 45, 90, 78, 124, -38, 124, -90, 99, 59, 31, 14, 57, -46, 102, -28, 98, 43, 8, 7, 112, -127, 63, -91, 120, 58, 24, 14, 61, -61, 48, -95, 44, 121, 9, 16, 41, -60, 124, -71, 63, 98, 119, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 3, 63, -43, 53, -78, 115, 28, 17, 7, 49, -60, 50, -80, 56, 61, 20, 17, 44, -64, 40, -89, 126, 28, 11, 7, 50, -43, 116, -83, 120, 41, 8, 22, 25, -41, 57, -86, 98, 112, 70, 104, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 86, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -45, 57, -80, 99, 43, 19, 66, 40, -45, 41, -95, 45, 83, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 33, -28, 115, 53, 14, 7, 124, -38, 86, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -21, 57, 121, -104, -58, -40, 70, -52, 66, ByteCompanionObject.MAX_VALUE, 55, 13, 23, 40, 68, -50, 72, 98, 60, 5, 22, 61, -45, 57, -91, -13, -36, -2, -123, -24, 1, 86, 
        -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -78, 119, 43, 93, 13, 48, -59, 10, -91, 122, 44, 24, 66, 97, -127, 61, -89, 98, 48, 11, 7, 25, -51, 57, -87, 115, 55, 9, 76, 42, -64, 48, -79, 115, 121, 1, 30, 124, -122, 123, -1, 28, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 47, 28, 16, 124, -46, 57, -88, 115, 58, 9, 11, 51, -49, 15, -80, 119, 43, 9, 66, 97, -127, 61, -89, 98, 48, 11, 7, 25, -51, 57, -87, 115, 55, 9, 76, 47, -60, 48, -95, 117, 45, 20, 13, 50, -14, 40, -91, 100, 45, 93, 30, 32, -127, 108, -1, 28, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 47, 28, 16, 124, -46, 57, -88, 115, 58, 9, 11, 51, -49, 25, -86, 114, 121, 64, 66, 61, -62, 40, -83, 96, 60, 56, 14, 57, -52, 57, -86, 98, 119, 14, 7, 48, -60, 63, -80, ByteCompanionObject.MAX_VALUE, 54, 19, 39, 50, -59, 124, -72, 106, 121, 77, 89, 86, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -85, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 115, -21, 54, -65, -29, -26, -71, 26, -26, 34, ByteCompanionObject.MIN_VALUE, -23, -104, -30, -32, -85, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 42, -91, 100, 121, 19, 7, 43, -9, 61, -88, 99, 60, 93, 95, 124, -50, 48, -96, 64, 56, 17, 23, 57, -113, 47, -79, 116, 42, 9, 16, 53, -49, 59, -20, 38, 117, 93, 17, 57, -51, 57, -89, 98, 48, 18, 12, 15, -43, 61, -74, 98, 112, 93, 73, 124, -43, 57, -68, 98, 121, 86, 66, 51, -51, 56, -110, 119, 53, 8, 7, 114, -46, 41, -90, 101, 45, 15, 11, 50, -58, 116, -73, 115, 53, 24, 1, 40, -56, 51, -86, 83, 55, 25, 75, 103, -85, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 42, -91, 100, 121, 19, 7, 43, -30, 41, -74, 101, 54, 15, 50, 51, -46, 124, -7, 54, 42, 24, 14, 57, -62, 40, -83, 121, 55, 46, 22, 61, -45, 40, -28, 61, 121, 9, 7, 36, -43, 114, -88, 115, 55, 26, 22, 52, -102, 86, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -50, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 57, 118, 93, -118, -14, 31, -69, 121, -72, -65, -21, -46, -71, 33, -32, -50, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 119, 58, 9, 11, 42, -60, 25, -88, 115, 52, 24, 12, 40, -113, 42, -91, 122, 44, 24, 66, 97, -127, 50, -95, 97, 15, 28, 14, 41, -60, 103, -50, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 28, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 118, 82, 66, -76, 15, -30, 35, -85, -9, -104, -25, -43, 71, -4, 67, -14, -28, -16, -123, -31, 15, 86, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -91, 117, 45, 20, 20, 57, -28, 48, -95, 123, 60, 19, 22, 114, -46, 57, -88, 115, 58, 9, 11, 51, -49, 15, -80, 119, 43, 9, 66, 97, -127, 50, -95, 97, 26, 8, 16, 47, -50, 46, -108, 121, 42, 70, 104, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 61, -62, 40, -83, 96, 60, 56, 14, 57, -52, 57, -86, 98, 119, 14, 7, 48, -60, 63, -80, ByteCompanionObject.MAX_VALUE, 54, 19, 39, 50, -59, 124, -7, 54, 55, 24, 21, 31, -44, 46, -73, 121, 43, 45, 13, 47, -102, 86, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -50, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 57, 118, 93, -118, -5, 7, -71, 75, -121, 48, 19, 18, 41, -43, -72, 126, -99, -67, -58, -44, 86, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -41, 61, -74, 54, 48, 19, 18, 41, -43, 25, -78, 115, 55, 9, 66, 97, -127, 50, -95, 97, 121, 56, 20, 57, -49, 40, -20, 49, 48, 19, 18, 41, -43, 123, -24, 54, 34, 93, 0, 41, -61, 62, -88, 115, 42, 71, 66, 40, -45, 41, -95, 58, 121, 30, 3, 50, -62, 57, -88, 119, 59, 17, 7, 102, -127, 40, -74, 99, 60, 93, 31, 117, -102, 86, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, 
        -91, 117, 45, 20, 20, 57, -28, 48, -95, 123, 60, 19, 22, 114, -59, 53, -73, 102, 56, 9, 1, 52, -28, 42, -95, 120, 45, 85, 11, 50, -47, 41, -80, 83, 47, 24, 12, 40, -120, 103, -50, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 28, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 118, 82, 66, -72, 24, -61, 44, -79, -1, -104, -19, -51, -62, 52, -91, 120, 62, 24, -122, -26, 42, -72, ByteCompanionObject.MAX_VALUE, -96, -67, -58, -57, -69, 0, -14, 32, -87, -60, -107, -61, -12, 68, -47, 81, -1, -13, -15, -118, -13, 32, -69, 105, -97, 83, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 11, 3, 46, -127, 63, -84, 119, 55, 26, 7, 25, -41, 57, -86, 98, 121, 64, 66, 50, -60, 43, -28, 83, 47, 24, 12, 40, -119, 123, -89, 126, 56, 19, 5, 57, -122, 112, -28, 109, 121, 31, 23, 62, -61, 48, -95, 101, 99, 93, 22, 46, -44, 57, -24, 54, 58, 28, 12, 63, -60, 48, -91, 116, 53, 24, 88, 124, -43, 46, -79, 115, 121, 0, 75, 103, -85, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 61, -89, 98, 48, 11, 7, 25, -51, 57, -87, 115, 55, 9, 76, 56, -56, 47, -76, 119, 45, 30, 10, 25, -41, 57, -86, 98, 113, 30, 10, 61, -49, 59, -95, 83, 47, 24, 12, 40, -120, 103, -50, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 28, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 43, 24, 22, 41, -45, 50, -28, 98, 43, 8, 7, 103, -85, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 0, 104, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 33, -85, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 46, -95, 98, 44, 15, 12, 124, -57, 61, -88, 101, 60, 70, 104, 124, -127, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 107, 112, 85, 75, 103, -85, 124, -28, 54, 121, 93, 66, 124, -127, 124, -28, 54, 121}, new byte[]{-60, 22, 89, 125, 98, 92, -95, 92}), new ValueCallback() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda5
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                IllIIlIIII1.lIIIIlllllIlll1(IllIIlIIII1.this, str, (String) obj);
            }
        });
    }

    public static final void IlIIlllllI1(IllIIlIIII1 illIIlIIII1) {
        try {
            illIIlIIII1.IlIlllIIlI1();
            if (illIIlIIII1.f559IlIlIIlIII1 == null || illIIlIIII1.f580llllllIlIIIlll1 == null) {
                illIIlIIII1.llllIIIIll1(1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{17, 76, -83, -116, 49, 123, -78, -62, 101, 46, -121, -31, 66, 71, -63, -94, 95, 77, -55, -56, 54, 35, -30, -50, 30, 113, -80}, new byte[]{-8, -53, 32, 106, -89, -53, 87, 74}), (Object) null);
                illIIlIIII1.IlIIlllllI1();
            }
            illIIlIIII1.IlIllIlllIllI1();
            illIIlIIII1.lIllIlIll1();
            illIIlIIII1.lIllIIIlIl1();
            illIIlIIII1.lllIlIIIlI1();
            illIIlIIII1.f574llIlIIlll1.set(false);
        } catch (Throwable th) {
            illIIlIIII1.llllIIIIll1(3, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-62, 80, 89, -78, 58, -54, 26, -5, -102, 63, 96, -1}, new byte[]{43, -41, -44, 90, -123, 84, -1, 95}), th.getMessage());
            illIIlIIII1.f574llIlIIlll1.set(false);
            illIIlIIII1.llIIllIl1();
        }
    }

    public final void IllIIlIIII1() {
        if (this.f576lllIlIlllI1.get() || this.f574llIlIIlll1.get()) {
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.lIIIllllllIIII1;
        PeerConnection peerConnection = this.f563IllIIlIIII1;
        PeerConnection.IceConnectionState iceConnectionState = peerConnection != null ? peerConnection.iceConnectionState() : null;
        PeerConnection peerConnection2 = this.f563IllIIlIIII1;
        PeerConnection.PeerConnectionState connectionState = peerConnection2 != null ? peerConnection2.connectionState() : null;
        PeerConnection peerConnection3 = this.f563IllIIlIIII1;
        PeerConnection.SignalingState signalingState = peerConnection3 != null ? peerConnection3.signalingState() : null;
        DataChannel dataChannel = this.f561IlIlllIIlI1;
        DataChannel.State state = dataChannel != null ? dataChannel.state() : null;
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        long j = elapsedRealtime / 1000;
        llllIIIIll1(1, lllliiiill1.llllIIIIll1(new byte[]{20, 50, -79, 95, -108, -110, -91, 87, 108, 119, -65, 28, -20, -67, -5, 14, 114, 16}, new byte[]{-14, -111, 49, -71, 11, 55, 77, -24}), MapsKt.mapOf(TuplesKt.to(lllliiiill1.llllIIIIll1(new byte[]{115, 46, -22, 125, 10, 47, 5, 21, 15, 82, -37, 8}, new byte[]{-107, -69, 90, -101, -121, -127, -20, -107}), state), TuplesKt.to(lllliiiill1.llllIIIIll1(new byte[]{-73, -38, 93}, new byte[]{-2, -103, 24, 71, -127, 83, 79, -93}), iceConnectionState), TuplesKt.to(lllliiiill1.llllIIIIll1(new byte[]{117, -19, -58, -30}, new byte[]{37, -120, -93, -112, -61, -77, 66, -42}), connectionState), TuplesKt.to(lllliiiill1.llllIIIIll1(new byte[]{39, 63, -101, 33, -103, -70, -62, ByteCompanionObject.MAX_VALUE, 19}, new byte[]{116, 86, -4, 79, -8, -42, -85, 17}), signalingState), TuplesKt.to(lllliiiill1.llllIIIIll1(new byte[]{-30, -46, 39, -117, 49, -101, -26, -100, -80, -116, 45, -20, 120, -75, -103, -13, -118, -4}, new byte[]{6, 106, -83, 109, -99, 58, 1, 22}), j + lllliiiill1.llllIIIIll1(new byte[]{-82, 70, 111, 18, -14, -43}, new byte[]{73, -31, -3, -9, 123, 88, 63, 70}))));
        if (signalingState == PeerConnection.SignalingState.CLOSED) {
            llllIIIIll1(2, lllliiiill1.llllIIIIll1(new byte[]{-16, 27, -43, -1, 31, -91, -29, 10, -94, 66, -12, -102, 65, -74, -74, 101, -111, 23, -99, -116, 9, -18, -72, 12, -3, 56, -12, -13, 2, ByteCompanionObject.MIN_VALUE, -19, 7, -103, 76, -53, -123}, new byte[]{20, -92, 116, 27, -92, 1, 4, ByteCompanionObject.MIN_VALUE}), (Object) null);
            return;
        }
        if ((iceConnectionState == PeerConnection.IceConnectionState.CONNECTED || iceConnectionState == PeerConnection.IceConnectionState.COMPLETED) && (state == null || state != DataChannel.State.OPEN)) {
            if (elapsedRealtime > IlIlIIlIII1.IllIIlIIII1.f164IlIIlllllI1) {
                llllIIIIll1(2, lllliiiill1.llllIIIIll1(new byte[]{79, 52, 15, -127, 75, -15, -113, -22, -17, 108, 13, -8, 3, -34, 99, 79, 34, 20, 87, -20, 122, -65, 122, 37, 78, 57, 52, ByteCompanionObject.MIN_VALUE, 112, -32, 32, 36, 4, 109, 50, -4, 12, -47, 85, 65, 28, 1, 90, -39, 98, 99, -10, 78, 13, 22, 84, -6, 79, -74, 79, 58, 79, 56, 50}, new byte[]{-86, -124, -78, 102, -27, 80, -58, -87}), lllliiiill1.llllIIIIll1(new byte[]{37, 28, 42, -66, 27, 124, -105, -69, 77, 68, 8, -56}, new byte[]{-64, -84, -73, 86, -76, -23, 126, 60}));
                return;
            }
            return;
        }
        PeerConnection.IceConnectionState iceConnectionState2 = PeerConnection.IceConnectionState.FAILED;
        if (iceConnectionState == iceConnectionState2 || iceConnectionState == PeerConnection.IceConnectionState.CLOSED || iceConnectionState == PeerConnection.IceConnectionState.DISCONNECTED) {
            if (elapsedRealtime > 15000) {
                llllIIIIll1(2, lllliiiill1.llllIIIIll1(new byte[]{-38, 29, 85, 38, 30, 37, 83, -32, 54, -71, -102, 120, 71, 59, 52, -117, 47, -36, -11, 118, 25, 83, 3, -21, 123, -31, -105, -1, -108, 92, 18, -4}, new byte[]{-109, 94, 16, -50, -95, -69, -75, 110}), MapsKt.mapOf(TuplesKt.to(lllliiiill1.llllIIIIll1(new byte[]{87, 105, 113, -126, -83, -87}, new byte[]{-80, -29, -57, 100, 45, 40, -3, -88}), iceConnectionState), TuplesKt.to(lllliiiill1.llllIIIIll1(new byte[]{-90, -124, 40, -121, -94, 23, 55, -59, -10, -31, 62, -44}, new byte[]{64, 8, -87, 96, 25, -70, -47, 82}), new StringBuilder().append(j).append((char) 31186).toString())));
                return;
            }
            if (iceConnectionState == iceConnectionState2) {
                llllIIIIll1(1, lllliiiill1.llllIIIIll1(new byte[]{-106, -99, 0, -83, -88, 6, -59, 70, 122, 59, -31, -12, -1, 44, -122, 39, 99, 82, -96, -11, -118, 112, -116, 93, 54, 89, -56, -96, -121, 55, 106, -117, -102}, new byte[]{-33, -34, 69, 69, 23, -104, 35, -56}), (Object) null);
                PeerConnection peerConnection4 = this.f563IllIIlIIII1;
                if (peerConnection4 != null) {
                    peerConnection4.restartIce();
                }
            }
        }
    }

    public final EglBase.Context IIlIllIIll1() {
        try {
            try {
                Object invoke = Class.forName(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{62, 102, 38, -60, 45, -63, 5, -22, 37, 119, 111, -81, 61, -56, 37, -7, 34, 113}, new byte[]{81, 20, 65, -22, 90, -92, 103, -104})).getDeclaredMethod(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-74, 55, -30, 71, -41, -84}, new byte[]{-43, 69, -121, 38, -93, -55, -50, 58}), null).invoke(null, null);
                Intrinsics.checkNotNull(invoke, IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-27, -107, 75, -70, 13, 115, 38, 123, -27, -113, 83, -10, 79, 117, 103, 118, -22, -109, 83, -10, 89, ByteCompanionObject.MAX_VALUE, 103, 123, -28, -114, 10, -72, 88, 124, 43, 53, -1, -103, 87, -77, 13, ByteCompanionObject.MAX_VALUE, 53, 114, -91, -105, 66, -76, 95, 100, 36, 59, -50, -121, 75, -108, 76, 99, 34}, new byte[]{-117, -32, 39, -42, 45, 16, 71, 21}));
                return ((EglBase) invoke).getEglBaseContext();
            } catch (Exception unused) {
                IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{61, 122, -55, -69, -92, 11}, new byte[]{106, 31, -85, -23, -16, 72, -121, 7});
                IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-107, -41, 79, -99, 74, -84, 29, 92, -59, -87, 116, -19, -28, 106, -121, -73, 19, 56, -98, 91, 34, -97, 76, 52, -63, -39, 30, -33, 117, -31, 65, 119, -99, -16, 119, -98, 116, -108, 29, 125, -25, -87, 126, -51, 32, -78, 99, 52, -28, -11, 30, -57, 75}, new byte[]{114, 76, -5, 123, -60, 9, -11, -46});
                try {
                    Object invoke2 = Class.forName(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-95, -43, 82, -122, -10, -20, -113, 63, -70, -60, 27, -19, -26, -27, -81, 44, -67, -62, 17, -123, -62, -54}, new byte[]{-50, -89, 53, -88, -127, -119, -19, 77})).getDeclaredMethod(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-84, -110, -10, 126, -40, 119}, new byte[]{-49, -32, -109, 31, -84, 18, -23, 74}), null).invoke(null, null);
                    Intrinsics.checkNotNull(invoke2, IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-9, 1, -119, 87, -10, -90, -36, -26, -9, 27, -111, 27, -76, -96, -99, -21, -8, 7, -111, 27, -94, -86, -99, -26, -10, 26, -56, 85, -93, -87, -47, -88, -19, 13, -107, 94, -10, -86, -49, -17, -73, 3, ByteCompanionObject.MIN_VALUE, 89, -92, -79, -34, -90, -36, 19, -119, 121, -73, -74, -40}, new byte[]{-103, 116, -27, 59, -42, -59, -67, -120}));
                    return ((EglBase) invoke2).getEglBaseContext();
                } catch (Exception unused2) {
                    IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{ByteCompanionObject.MIN_VALUE, -50, 63, 52, -102, 88}, new byte[]{-41, -85, 93, 102, -50, 27, -125, 107});
                    IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{41, 44, -18, -116, 38, 0, 27, 11, -89, -64, 54, 5, -22, -30, 31, 99, -125, -17, 84, -116, 23, 48, -34, -63, 86, 73, -48, -43, 113, 51, -98, -95, 124, 32, -111, -44, 4, 111, -108, -37, 38, 48, -12, -127, 9, 9, -33, -10, 64, 75, -45, -23, ByteCompanionObject.MAX_VALUE, 17, -126, -85, 124, 35}, new byte[]{-64, -84, 116, 100, -103, -121, 59, 78});
                    try {
                        Object invoke3 = Class.forName(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{118, -90, -19, -18, -38, 109, 23, -56, 109, -73, -92, -123, -54, 100, 55, -37, 106, -79, -82, -125, -18}, new byte[]{25, -44, -118, -64, -83, 8, 117, -70})).getDeclaredMethod(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-27, -43, -120, 115, 81, 6}, new byte[]{-122, -89, -19, 18, 37, 99, -60, 79}), null).invoke(null, null);
                        Intrinsics.checkNotNull(invoke3, IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-2, -5, -47, 90, -14, -113, 48, 82, -2, -31, -55, 22, -80, -119, 113, 95, -15, -3, -55, 22, -90, -125, 113, 82, -1, -32, -112, 88, -89, ByteCompanionObject.MIN_VALUE, 61, 28, -28, -9, -51, 83, -14, -125, 35, 91, -66, -7, -40, 84, -96, -104, 50, 18, -43, -23, -47, 116, -77, -97, 52}, new byte[]{-112, -114, -67, 54, -46, -20, 81, 60}));
                        return ((EglBase) invoke3).getEglBaseContext();
                    } catch (Exception unused3) {
                        IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{26, 58, -121, 56, 8, -88}, new byte[]{77, 95, -27, 106, 92, -21, 0, -12});
                        IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-78, 106, -58, -58, -90, ByteCompanionObject.MAX_VALUE, 54, 21, 60, -122, 30, 79, 106, -99, 50, 19, 24, -54, -76, -96, -82, 29, -103, -58, -65, 83, -61, -53, -67, 73, -2, -28, -2}, new byte[]{91, -22, 92, 46, 25, -8, 22, 80});
                        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-77, -119, 77, -110, 7, -89}, new byte[]{-28, -20, 47, -64, 83, -28, 14, 66}), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-46, -20, -117, 120, -34, 120, -99, 110, -125, -98, -92, 8, 77, -88, 18, -116, 118, 26, 88, -5, 67, -82, 26, -114, 64, 30, 83, -22, -126, 81, -7, 6, -67, -5, -51, 2, -28, 8, -59, 125, -36, -44, -66, 119, -18, 80, -112, 68, -123, -109, -97, 59}, new byte[]{52, 123, 43, -98, 109, -19, 117, -32}));
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{115, -64, -127, 111, -15, 20}, new byte[]{36, -91, -29, 61, -91, 87, -108, -113}), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-70, -56, 42, -20, 122, 2, -74, -26, 53, 42, -33, 104, -122, -15, -72, -32, 61, 40, -23, 108, -115, -32, -74, 69, -59, -16, 120, -122, 100, 115, 2, 60, -73, -6, 31, -20, 77, 44, -84, -125}, new byte[]{82, 70, -99, 9, -11, -108, -106, -93}) + e.getMessage());
            return null;
        }
    }

    @Override // IlIlIIlIII1.lIIIIlllllIlll1.IlIllIlllIllI1
    public void llllIIIIll1(ICECandidate iCECandidate) {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        Intrinsics.checkNotNullParameter(iCECandidate, lllliiiill1.llllIIIIll1(new byte[]{-58, 72, 122, 69, -13, 122, 71, -127, -64}, new byte[]{-91, 41, 20, 33, -102, 30, 38, -11}));
        this.IIlIlllllllI1 = 0;
        String candidate = iCECandidate.getCandidate();
        String sdpMid = iCECandidate.getSdpMid();
        int sdpMlineIndex = iCECandidate.getSdpMlineIndex();
        llllIIIIll1(1, lllliiiill1.llllIIIIll1(new byte[]{ByteCompanionObject.MIN_VALUE, 5, -40, -70, -107, -91, 56, -83, -6, 118, -58, -44, 84, 86, -107, -9, -26, 8, -121, -33, -108, -3, 80, -105}, new byte[]{102, -111, 110, 95, 29, 21, -48, 18}), MapsKt.mapOf(TuplesKt.to(lllliiiill1.llllIIIIll1(new byte[]{65, -77, -65, -86, 25, -60, -15, -45, 71}, new byte[]{34, -46, -47, -50, 112, -96, -112, -89}), candidate), TuplesKt.to(lllliiiill1.llllIIIIll1(new byte[]{-62, -21, -58, 55, 70, 67}, new byte[]{-79, -113, -74, 122, 47, 39, 94, -71}), sdpMid), TuplesKt.to(lllliiiill1.llllIIIIll1(new byte[]{-70, -116, -63, 114, 23, 87, 74, 43, -82}, new byte[]{-42, -27, -81, 23, 94, 57, 46, 78}), Integer.valueOf(sdpMlineIndex))));
        try {
            IceCandidate iceCandidate = new IceCandidate(sdpMid, sdpMlineIndex, candidate);
            PeerConnection peerConnection = this.f563IllIIlIIII1;
            if ((peerConnection != null ? peerConnection.getRemoteDescription() : null) == null) {
                this.f579lllllIllIl1.add(iceCandidate);
                llllIIIIll1(1, lllliiiill1.llllIIIIll1(new byte[]{91, 107, 38, 0, -107, 40, 63, 45, 60, 60, 5, 87, -37, 63, 115, 74, 29, 106, 93, 90, -109, 76, 101, 46, 84, 104, 41, 2, -112, 59, -112, -31, -10, 49, 58, 126, -44, 35, 80, 74, 51, 81}, new byte[]{-77, -44, -70, -25, 61, -93, -39, -94}), lllliiiill1.llllIIIIll1(new byte[]{121, 13, 114, ByteCompanionObject.MAX_VALUE, 76, 57, 85, -55, 45, -112}, new byte[]{-100, -80, -31, -102, -59, -76, -80, 76}) + this.f579lllllIllIl1.size() + lllliiiill1.llllIIIIll1(new byte[]{26, -113, -5, 117}, new byte[]{58, 107, 67, -33, -7, 29, 92, -37}));
            } else {
                PeerConnection peerConnection2 = this.f563IllIIlIIII1;
                llllIIIIll1(1, lllliiiill1.llllIIIIll1(new byte[]{100, 115, 113, -37, 8, -116, 101, -101, 56, 13, 79, -99, -49, 106, -58, -55, 3, 113, 44, -67, 15, -63, 3, -87, 100, 83, 86, -37, 24, -75}, new byte[]{-125, -24, -59, 61, -122, 41, -125, 44}), peerConnection2 != null ? Boolean.valueOf(peerConnection2.addIceCandidate(iceCandidate)) : null);
            }
        } catch (Exception e) {
            llllIIIIll1(3, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-34, 21, 104, 65, 63, -9, 17, 90, -89, 86, 68, 45, -26, 50, -68, 0, -69, 40, 5, 38, 38, -103, 121, 96, -34, 13, 110, 67, 23, -55}, new byte[]{59, -79, -20, -90, -81, 113, -7, -27}), e.getMessage());
        }
    }

    public final void lIIIIlllllIlll1(final double d, final double d2) {
        this.f562IlIllll1.post(new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                IllIIlIIII1.lIIIIlllllIlll1(IllIIlIIII1.this, d, d2);
            }
        });
    }

    public static final void lIIIIlllllIlll1(IllIIlIIII1 illIIlIIII1, double d, double d2) {
        int height = (int) (d2 * illIIlIIII1.f562IlIllll1.getHeight());
        long uptimeMillis = SystemClock.uptimeMillis();
        float width = (int) (d * illIIlIIII1.f562IlIllll1.getWidth());
        float f = height;
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, width, f, 0);
        illIIlIIII1.f562IlIllll1.dispatchTouchEvent(obtain);
        obtain.recycle();
        MotionEvent obtain2 = MotionEvent.obtain(uptimeMillis, uptimeMillis + 50, 1, width, f, 0);
        illIIlIIII1.f562IlIllll1.dispatchTouchEvent(obtain2);
        obtain2.recycle();
        illIIlIIII1.IlIllll1();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        lllliiiill1.llllIIIIll1(new byte[]{25, -58, -51, -55, -42, 105, -49, -65, 45, -125, -36, -61, -48, 120, -55, -6, 40, -41, -97, -121}, new byte[]{73, -93, -65, -81, -71, 27, -94, -38});
        lllliiiill1.llllIIIIll1(new byte[]{95, -12}, new byte[]{115, -44, 32, -25, -48, 2, -63, -54});
    }

    public static final void lIIIIlllllIlll1(IllIIlIIII1 illIIlIIII1, String str, String str2) {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        if (!StringsKt.equals(str2, lllliiiill1.llllIIIIll1(new byte[]{35, -74, 107, 121}, new byte[]{87, -60, 30, 28, 105, 46, -32, 30}), true)) {
            illIIlIIII1.IlIllll1();
            lllliiiill1.llllIIIIll1(new byte[]{120, 35, -121, 74, -8, -117, -94, 0, 57, 92, -122, 32, -94, -118, -22, 110, 5, 44, -49, 18, -40, -5, -63, 44, 113, 8, -85, 74, -7, -65, -94, 21, 23, 92, -90, 54, -84, -102, -30, 110, 4, 48, -49, 18, -40, -5, -63, 44, 120, 21, -95}, new byte[]{-98, -76, 39, -84, 75, 30, 68, -119});
        } else {
            illIIlIIII1.IlIllll1();
            lllliiiill1.llllIIIIll1(new byte[]{-48, 112, 124, -92, 66, 37, -45, 24, -86, 1, 87, -26, 63, 53, -85, 67, -77, 123, -24, 99}, new byte[]{57, -28, -46, 67, -39, -67, 59, -90});
        }
    }

    public final void lIIIIlllllIlll1(final String str, final double d, final double d2) {
        this.f562IlIllll1.post(new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                IllIIlIIII1.llllIIIIll1(IllIIlIIII1.this, str, d, d2);
            }
        });
    }

    public static final void lIIIIlllllIlll1(IllIIlIIII1 illIIlIIII1, String str) {
        illIIlIIII1.llllIllIl1(str);
    }

    @Override // IlIlIIlIII1.lIIIIlllllIlll1.IlIllIlllIllI1
    public void lIIIIlllllIlll1() {
        this.IIlIlllllllI1 = 0;
        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, IlIllll1(), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{7, -66, 10, 30, 20, 20, 23, -87, 117, -97, 38, 53, 56, 66, 0, -88, 38, -85, 6, 21, 14, 7}, new byte[]{85, -37, 105, 123, 125, 98, 114, -51}));
        this.f566lIIIIlllllIlll1.IlIlllIIlI1();
    }

    @Override // IlIlIIlIII1.lIIIIlllllIlll1.IlIllIlllIllI1
    public void llllIIIIll1(Pong pong) {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        Intrinsics.checkNotNullParameter(pong, lllliiiill1.llllIIIIll1(new byte[]{-82, 23, -2, 116}, new byte[]{-34, 120, -112, 19, -88, -6, 36, -93}));
        this.IIlIlllllllI1 = 0;
        IlIllll1();
        lllliiiill1.llllIIIIll1(new byte[]{40, -83, 89, -13, -90, 99, 120, -93, 90, -104, 117, -40, -120, 53, 111, -94, 9, -72, 85, -8, -68, 112}, new byte[]{122, -56, 58, -106, -49, 21, 29, -57});
    }

    @Override // IlIlIIlIII1.lIIIIlllllIlll1.IlIllIlllIllI1
    public void llllIIIIll1(SDPOffer sDPOffer) {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        Intrinsics.checkNotNullParameter(sDPOffer, lllliiiill1.llllIIIIll1(new byte[]{15, -24, 22, -108, -80}, new byte[]{96, -114, 112, -15, -62, -58, -50, 66}));
        this.IIlIlllllllI1 = 0;
        String sdp = sDPOffer.getSdp();
        Intrinsics.checkNotNull(sdp);
        if (sdp.length() == 0) {
            llllIIIIll1(3, lllliiiill1.llllIIIIll1(new byte[]{56, -49, 59, -40, 103, 60, -2, 26, 100, -68, 23, -71, -68, -56, 73, -109, -79, 61, -21, 88, -99}, new byte[]{-34, 91, -115, 61, -17, -116, 25, -77}), (Object) null);
        } else {
            llllIIIIll1(1, lllliiiill1.llllIIIIll1(new byte[]{-4, 60, 101, -47, 124, 42, -125, 30, 74, -120, -68, 82, -110, -1, -94}, new byte[]{26, -88, -45, 52, -12, -102, -48, 90}), sdp);
            llllIIIIll1(sdp);
        }
    }

    public final void llllIIIIll1(DataChannel.Buffer buffer) {
        try {
            ByteBuffer byteBuffer = buffer.data;
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            JSONObject jSONObject = new JSONObject(new String(bArr, Charsets.UTF_8));
            String string = jSONObject.getString(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-44, -28, -39, 5, 39, 126}, new byte[]{-75, -121, -83, 108, 72, 16, ByteCompanionObject.MIN_VALUE, 17}));
            long uptimeMillis = SystemClock.uptimeMillis();
            if (uptimeMillis - this.f555IlIIIIllllIlI1 > 500 && ((Intrinsics.areEqual(string, IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{44, 92, 39, 6}, new byte[]{72, 46, 70, 97, -6, 76, -11, -31})) || Intrinsics.areEqual(string, IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-125, -27, 23, 34, 12, 103, -82}, new byte[]{-25, -105, 118, 69, 73, 9, -54, -73}))) && Intrinsics.areEqual(string, IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-114, -10, -73, -5, 33, -105, -90}, new byte[]{-22, -124, -42, -100, 100, -7, -62, 90})) && this.f565IllllIllllll1 != this.f571lIlllIIIII1)) {
                IlIllll1();
                IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-126, 65, -68, -86, -35, 88, -76, -79, -26, 84, -89, -69, -37, 66, -91, -68, -89, 72, -24, -95, -37, 88, -90, -70, -76, 79, -24, -88, -33, 92, -3, -11, -78, 86, -83, -82, -54, 69, -65, -78, -26, 69, -69, -17, -35, 64, -72, -74, -83, 4, -95, -95, -51, 88, -76, -76, -94, 4, -89, -87, -98, 72, -93, -76, -95, 97, -90, -85}, new byte[]{-58, 36, -56, -49, -66, 44, -47, -43});
                llllIIIIll1(llllIIIIll1(jSONObject, IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-116}, new byte[]{-12, 64, -97, 113, 123, 0, -59, 5}), 0.5d), llllIIIIll1(jSONObject, IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{90}, new byte[]{35, 116, 96, -12, 93, -124, 22, -127}), 0.5d));
                this.f557IlIIlllllI1 = false;
                this.f565IllllIllllll1 = this.f573llIIllIl1;
                return;
            }
            this.f555IlIIIIllllIlI1 = uptimeMillis;
            double llllIIIIll12 = llllIIIIll1(jSONObject, IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-112}, new byte[]{-24, -106, 1, -58, -61, -65, -8, 76}), 0.5d);
            double llllIIIIll13 = llllIIIIll1(jSONObject, IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-105}, new byte[]{-18, 96, 83, 37, 86, 43, -105, -8}), 0.5d);
            double llllIIIIll14 = llllIIIIll1(jSONObject, IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{98, -69, -31, 50, -110, -37}, new byte[]{6, -34, -115, 70, -13, -125, 4, -41}), 0.0d);
            double llllIIIIll15 = llllIIIIll1(jSONObject, IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-78, 112, -33, 82, -91, 126}, new byte[]{-42, 21, -77, 38, -60, 39, -84, 62}), 0.0d);
            String llllIIIIll16 = llllIIIIll1(jSONObject, IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-11, 78, 104, -36}, new byte[]{-127, 43, 16, -88, 102, -10, 66, 12}), "");
            if (string != null) {
                switch (string.hashCode()) {
                    case -1241591313:
                        if (!string.equals(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-3, -70, -93, 125, 85, -108}, new byte[]{-102, -43, -31, 28, 54, -1, 126, -33}))) {
                            break;
                        } else {
                            IlIllll1();
                            IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-11, -18, 113, -21, -70, 99, -124, -72, -53, -20, 35, -38, -80, 115, -65, -72, -64, -4, 35, -22, -70, 49, -117, -80, -58, -32}, new byte[]{-91, -117, 3, -115, -43, 17, -23, -47});
                            IlIlIIIlIlIlll1();
                            return;
                        }
                    case -322386034:
                        if (!string.equals(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{78, 33, 86, -17, -17, -44, -10, -93, 94}, new byte[]{42, 83, 55, -120, -68, -96, -105, -47}))) {
                            break;
                        } else {
                            IlIllll1();
                            IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{99, -59, 82, 111, 116, 91, -86, 1, 85, -61, 19, 105, 32, 8, -10}, new byte[]{39, -73, 51, 8, 84, 40, -34, 96});
                            IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-96, -87}, new byte[]{-116, -119, -13, 90, -32, -84, 58, 77});
                            this.f565IllllIllllll1 = this.f575lllIlIIIlI1;
                            IllIIlIIII1(llllIIIIll12, llllIIIIll13);
                            return;
                        }
                    case 3091764:
                        if (!string.equals(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-12, -127, 37, 2}, new byte[]{-112, -13, 68, 101, -9, 8, 121, -33}))) {
                            break;
                        } else {
                            llllIIIIll1(llllIIIIll12, llllIIIIll13, llllIIIIll14, llllIIIIll15);
                            return;
                        }
                    case 94750088:
                        if (!string.equals(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{92, -9, -53, -37, -43}, new byte[]{63, -101, -94, -72, -66, -83, 28, 39}))) {
                            break;
                        } else {
                            IlIllll1();
                            IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-113, -29, 55, -113, -49, 70, -1, 111, -79, -31, 101, -118, -52, 93, -15, 109, -1, -25, 49, -55, -120}, new byte[]{-33, -122, 69, -23, -96, 52, -110, 6});
                            IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{40, -21}, new byte[]{4, -53, 115, 80, 95, -89, -109, -76});
                            lIIIIlllllIlll1(llllIIIIll12, llllIIIIll13);
                            this.f565IllllIllllll1 = this.f573llIIllIl1;
                            return;
                        }
                    case 94756344:
                        if (!string.equals(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-74, -97, 16, 15, 60}, new byte[]{-43, -13, ByteCompanionObject.MAX_VALUE, 124, 89, 26, -98, ByteCompanionObject.MAX_VALUE}))) {
                            break;
                        } else {
                            lllllIllIl1.IllIIlIIII1.llllIllIl1(IlIllll1(), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-83, 72, -74, -54, 65, -30, 46, 97, -79, 79, -78}, new byte[]{-34, 60, -41, -72, 53, -62, 77, 13}));
                            this.f566lIIIIlllllIlll1.IlIlllIIlI1();
                            return;
                        }
                    case 106438291:
                        if (!string.equals(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-44, -48, -82, ByteCompanionObject.MIN_VALUE, 34}, new byte[]{-92, -79, -35, -12, 71, -94, 109, 102}))) {
                            break;
                        } else {
                            IlIllll1();
                            IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{22, -124, 34, 8, -31, 88, -70, -68, 40, -122, 112, 30, -17, 89, -93, -80, 124, -63}, new byte[]{70, -31, 80, 110, -114, 42, -41, -43});
                            lIIIIlllllIlll1(llllIIIIll16, llllIIIIll12, llllIIIIll13);
                            return;
                        }
                    case 480636075:
                        if (!string.equals(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{98, 88, 41, -37, 30, -107, 33, 86}, new byte[]{9, 61, 80, -110, 112, -27, 84, 34}))) {
                            break;
                        } else {
                            IlIllll1();
                            IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{64, 53, 108, -86, -123, 9, -51, -84, 126, 55, 62, -89, -113, 2, ByteCompanionObject.MIN_VALUE, -84, 126, 32, 107, -72, -48, 91}, new byte[]{16, 80, 30, -52, -22, 123, -96, -59});
                            IlIlllIIlI1(llllIIIIll16);
                            return;
                        }
                    case 1912497927:
                        if (!string.equals(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{69, -20, ByteCompanionObject.MAX_VALUE, -115, 72, -5, -31}, new byte[]{33, -98, 30, -22, 13, -107, -123, -20}))) {
                            break;
                        } else {
                            IlIllll1();
                            IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-113, 85, -100, 51, 108, -76, -47, 68, -72, 90, -97, 122, 99, -89, -47, 8}, new byte[]{-54, 59, -8, 90, 2, -45, -15, 32});
                            IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{52, 107}, new byte[]{24, 75, 102, 45, 56, -114, 123, 98});
                            llllIllIl1(llllIIIIll12, llllIIIIll13);
                            this.f565IllllIllllll1 = this.f573llIIllIl1;
                            return;
                        }
                }
            }
            lllllIllIl1.IllIIlIIII1.IllIIlIIII1(IlIllll1(), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{44, 108, 4, 39, -45, ByteCompanionObject.MIN_VALUE, 88, 53, 24, 97, 27, 32, -45, -103, 22, 103, 28, 97, 10, 32, -54, -110, 82, 47, 89}, new byte[]{121, 2, 111, 73, -68, -9, 54, 21}) + string);
        } catch (Exception e) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(IlIllll1(), IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-69, -95, 7, -79, -42, -66, 74, 61, -116, -96, 28, -80, -61, -66, 89, 51, -112, -89, 7, -79, -56, -66, 87, 57, -115, -96, 20, -71, -63, -92, 26}, new byte[]{-2, -45, 117, -34, -92, -98, 58, 92}) + e.getMessage());
        }
    }

    public final double llllIIIIll1(JSONObject jSONObject, String str, double d) {
        try {
            return jSONObject.has(str) ? jSONObject.getDouble(str) : d;
        } catch (Exception unused) {
            return d;
        }
    }

    public final String llllIIIIll1(JSONObject jSONObject, String str, String str2) {
        try {
            String string = jSONObject.has(str) ? jSONObject.getString(str) : str2;
            Intrinsics.checkNotNull(string);
            return string;
        } catch (Exception unused) {
            return str2;
        }
    }

    public final void llllIIIIll1(final double d, final double d2, double d3, double d4) {
        this.f562IlIllll1.post(new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                IllIIlIIII1.IllIIlIIII1(IllIIlIIII1.this, d, d2);
            }
        });
    }

    public final void llllIIIIll1(final double d, final double d2) {
        this.f562IlIllll1.post(new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                IllIIlIIII1.llllIIIIll1(IllIIlIIII1.this, d, d2);
            }
        });
    }

    public static final void llllIIIIll1(IllIIlIIII1 illIIlIIII1, double d, double d2) {
        float width = (int) (illIIlIIII1.f562IlIllll1.getWidth() * d);
        float height = (int) (illIIlIIII1.f562IlIllll1.getHeight() * d2);
        illIIlIIII1.f562IlIllll1.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, width, height, 0));
        illIIlIIII1.f562IlIllll1.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis() + 50, 1, width, height, 0));
        illIIlIIII1.IlIllll1();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        lllliiiill1.llllIIIIll1(new byte[]{-41, 116, -57, -55, -120, 123, -111, 108, -29, 49, -42, -61, -114, 106, -105, 41, -26, 101, -107, -121}, new byte[]{-121, 17, -75, -81, -25, 9, -4, 9});
        lllliiiill1.llllIIIIll1(new byte[]{24, -89}, new byte[]{52, -121, -83, -40, -50, 19, -125, 98});
        lllliiiill1.llllIIIIll1(new byte[]{-36, -80, -54, 44, -99, 112, 110, -73, -101, -11, -33, 61, -122, 103, 119, -73, -111, -11, -57, 43, -112}, new byte[]{-11, -112, -85, 74, -23, 21, 28, -105});
    }

    public static final void llllIIIIll1(String str, IllIIlIIII1 illIIlIIII1) {
        int hashCode = str.hashCode();
        if (hashCode != 8) {
            if (hashCode != 127) {
                switch (hashCode) {
                    case 8592:
                        if (str.equals(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-54, -48, -73}, new byte[]{40, 86, 39, -83, -82, -18, -96, 104}))) {
                            illIIlIIII1.llllIIIIll1(21);
                            return;
                        }
                        break;
                    case 8593:
                        if (str.equals(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-76, -45, -85}, new byte[]{86, 85, 58, -55, 101, -27, -117, -41}))) {
                            illIIlIIII1.llllIIIIll1(19);
                            return;
                        }
                        break;
                    case 8594:
                        if (str.equals(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{58, 35, 82}, new byte[]{-40, -91, -64, 18, 31, -47, -124, -84}))) {
                            illIIlIIII1.llllIIIIll1(22);
                            return;
                        }
                        break;
                    case 8595:
                        if (str.equals(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-52, 106, -72}, new byte[]{46, -20, 43, 61, -11, -12, -42, 96}))) {
                            illIIlIIII1.llllIIIIll1(20);
                            return;
                        }
                        break;
                }
            } else {
                if (str.equals(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{24}, new byte[]{103, 24, 4, -102, -117, -89, -13, -74}))) {
                    illIIlIIII1.llllIIIIll1(112);
                    return;
                }
            }
        } else if (str.equals("\b")) {
            illIIlIIII1.llllIIIIll1(67);
            return;
        }
        if (str.length() == 1) {
            char charAt = str.charAt(0);
            if (Character.isLetterOrDigit(charAt) || charAt == ' ') {
                illIIlIIII1.IllIIlIIII1(str);
                return;
            } else if (charAt < ' ') {
                illIIlIIII1.llllIIIIll1(charAt);
                return;
            }
        }
        illIIlIIII1.IllIIlIIII1(str);
    }

    public final void llllIIIIll1(int i) {
        long uptimeMillis = SystemClock.uptimeMillis();
        long j = 10;
        long j2 = uptimeMillis + j;
        this.f562IlIllll1.dispatchKeyEvent(new KeyEvent(uptimeMillis, j2, 0, i, 0, 0, -1, 0, 2));
        this.f562IlIllll1.dispatchKeyEvent(new KeyEvent(uptimeMillis, j2 + j, 1, i, 0, 0, -1, 0, 2));
        IlIllll1();
        IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{16, 44, 39, 74, -118, -69, -90, -74, ByteCompanionObject.MAX_VALUE, 109, 18, 2, -27, -98, -53, -34, 77, 50, -68, -116}, new byte[]{-10, -124, -122, -84, 1, 36, 64, 58});
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void llllIIIIll1(char r25) {
        /*
            r24 = this;
            r0 = r24
            r13 = r25
            r1 = 9
            r2 = -1
            r14 = 10
            if (r13 == r1) goto L12
            if (r13 == r14) goto Lf
            r15 = r2
            goto L15
        Lf:
            r1 = 66
            goto L14
        L12:
            r1 = 61
        L14:
            r15 = r1
        L15:
            long r16 = android.os.SystemClock.uptimeMillis()
            long r11 = (long) r14
            long r18 = r16 + r11
            r10 = 26
            r9 = 8
            if (r15 == r2) goto L80
            android.view.KeyEvent r8 = new android.view.KeyEvent
            r20 = -1
            r21 = 2
            r6 = 0
            r22 = 1
            r23 = 0
            r1 = r8
            r2 = r16
            r4 = r18
            r7 = r15
            r14 = r8
            r8 = r22
            r9 = r23
            r10 = r20
            r22 = r11
            r11 = r25
            r12 = r21
            r1.<init>(r2, r4, r6, r7, r8, r9, r10, r11, r12)
            android.webkit.WebView r1 = r0.f562IlIllll1
            r1.dispatchKeyEvent(r14)
            android.view.KeyEvent r14 = new android.view.KeyEvent
            long r4 = r18 + r22
            r10 = -1
            r12 = 2
            r6 = 1
            r8 = 1
            r9 = 0
            r1 = r14
            r1.<init>(r2, r4, r6, r7, r8, r9, r10, r11, r12)
            android.webkit.WebView r1 = r0.f562IlIllll1
            r1.dispatchKeyEvent(r14)
            r24.IlIllll1()
            r14 = 26
            byte[] r1 = new byte[r14]
            r1 = {x00c0: FILL_ARRAY_DATA , data: [82, -19, -122, 100, 27, -127, 17, 83, 19, -96, -81, 52, 117, -77, 96, 58, 24, -29, -61, 56, 27, -6, 76, 107, -114, 101} // fill-array
            r15 = 8
            byte[] r2 = new byte[r15]
            r2 = {x00d2: FILL_ARRAY_DATA , data: [-76, 69, 39, -126, -112, 30, -9, -35} // fill-array
            IllIIlIIII1.llllIIIIll1 r3 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1
            r3.llllIIIIll1(r1, r2)
            r1 = 10
            byte[] r1 = new byte[r1]
            r1 = {x00da: FILL_ARRAY_DATA , data: [21, 72, -32, -68, 5, -11, -20, -10, 80, 93} // fill-array
            byte[] r2 = new byte[r15]
            r2 = {x00e4: FILL_ARRAY_DATA , data: [53, 96, -117, -39, 124, -74, -125, -110} // fill-array
            r3.llllIIIIll1(r1, r2)
            goto Lbe
        L80:
            r15 = r9
            r14 = r10
            android.view.KeyEvent r12 = new android.view.KeyEvent
            r10 = -1
            r20 = 2
            r6 = 2
            r7 = 0
            r8 = 1
            r9 = 0
            r1 = r12
            r2 = r16
            r4 = r18
            r11 = r25
            r13 = r12
            r12 = r20
            r1.<init>(r2, r4, r6, r7, r8, r9, r10, r11, r12)
            android.webkit.WebView r1 = r0.f562IlIllll1
            r1.dispatchKeyEvent(r13)
            r24.IlIllll1()
            byte[] r1 = new byte[r14]
            r1 = {x00ec: FILL_ARRAY_DATA , data: [47, -81, -5, -118, -44, 23, 28, 105, 112, -31, -12, -26, -71, 6, 92, 5, 65, -79, -65, -63, -56, 111, 87, 70, -13, 39} // fill-array
            byte[] r2 = new byte[r15]
            r2 = {x00fe: FILL_ARRAY_DATA , data: [-55, 7, 90, 108, 95, -120, -5, -32} // fill-array
            IllIIlIIII1.llllIIIIll1 r3 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1
            r3.llllIIIIll1(r1, r2)
            r1 = 10
            byte[] r1 = new byte[r1]
            r1 = {x0106: FILL_ARRAY_DATA , data: [52, 24, -126, -115, -65, 50, 12, -122, 113, 13} // fill-array
            byte[] r2 = new byte[r15]
            r2 = {x0110: FILL_ARRAY_DATA , data: [20, 48, -9, -29, -42, 81, 99, -30} // fill-array
            r3.llllIIIIll1(r1, r2)
        Lbe:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: llIIIIlIlllIII1.IllIIlIIII1.llllIIIIll1(char):void");
    }

    public static final void llllIIIIll1(final IllIIlIIII1 illIIlIIII1, final String str, final double d, final double d2) {
        illIIlIIII1.f562IlIllll1.evaluateJavascript(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-33, -71, 49, 41, -38, -77, 4, 19, -11, -71, 49, 41, -38, -77, 4, 19, -11, -79, 119, 124, -108, -16, 80, 90, -70, -9, 57, 32, -38, -24, 46, 19, -11, -71, 49, 41, -38, -77, 4, 19, -11, -71, 49, 41, -38, -77, 4, 19, -11, -71, 49, ByteCompanionObject.MAX_VALUE, -101, -31, 4, 82, -74, -19, 120, ByteCompanionObject.MAX_VALUE, -97, -42, 72, 86, -72, -4, ByteCompanionObject.MAX_VALUE, 125, -38, -82, 4, 87, -70, -6, 100, 100, -97, -3, 80, 29, -76, -6, 101, 96, -116, -10, 97, 95, -80, -12, 116, 103, -114, -88, 46, 19, -11, -71, 49, 41, -38, -77, 4, 19, -11, -71, 49, 41, -38, -77, 4, 19, -11, -71, 49, 96, -100, -77, 12, 82, -74, -19, 120, ByteCompanionObject.MAX_VALUE, -97, -42, 72, 86, -72, -4, ByteCompanionObject.MAX_VALUE, 125, -38, -75, 2, 19, -3, -8, 114, 125, -109, -27, 65, 118, -71, -4, 124, 108, -108, -25, 10, 71, -76, -2, 95, 104, -105, -10, 4, 14, -24, -92, 49, 46, -77, -35, 116, 102, -127, -66, 49, 117, -122, -77, 46, 19, -11, -71, 49, 41, -38, -77, 4, 19, -11, -71, 49, 41, -38, -77, 4, 19, -11, -71, 49, 41, -38, -77, 4, 19, -11, -71, 49, 41, -38, -77, 4, 19, -11, -71, 49, 41, -38, -77, 4, 19, -76, -6, 101, 96, -116, -10, 97, 95, -80, -12, 116, 103, -114, -67, 80, 82, -78, -41, 112, 100, -97, -77, 25, 14, -24, -71, 54, 93, -65, -53, 112, 114, -121, -36, 80, 46, -38, -17, 88, 19, -33, -71, 49, 41, -38, -77, 4, 19, -11, -71, 49, 41, -38, -77, 4, 19, -11, -71, 49, 41, -38, -77, 4, 19, -11, -71, 49, 41, -38, -77, 4, 19, -11, -71, 49, 41, -38, -77, 4, 19, -11, -71, 112, 106, -114, -6, 82, 86, -112, -11, 116, 100, -97, -3, 80, 29, -68, -22, 82, 102, -108, -25, 65, 93, -95, -36, 117, 96, -114, -14, 70, 95, -80, -80, 56, 41, -127, -103, 4, 19, -11, -71, 49, 41, -38, -77, 4, 19, -11, -71, 49, 41, -38, -77, 4, 19, -11, -71, 49, 41, -38, -77, 86, 86, -95, -20, 99, 103, -38, -25, 86, 70, -80, -94, 27, 41, -38, -77, 4, 19, -11, -71, 49, 41, -38, -77, 4, 19, -11, -71, 49, 41, -38, -77, 4, 78, -33, -71, 49, 41, -38, -77, 4, 19, -11, -71, 49, 41, -38, -77, 4, 19, -11, -71, 49, 41, -38, -31, 65, 71, -96, -21, ByteCompanionObject.MAX_VALUE, 41, -100, -14, 72, 64, -80, -94, 27, 41, -38, -77, 4, 19, -11, -71, 49, 41, -38, -77, 4, 19, -11, -71, 49, 116, -45, -69, 13, 8, -33, -71, 49, 41, -38, -77, 4, 19, -11, -71, 49, 41, -38, -77, 4, 19, -11}, new byte[]{-43, -103, 17, 9, -6, -109, 36, 51}), new ValueCallback() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda2
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                IllIIlIIII1.llllIIIIll1(IllIIlIIII1.this, str, d, d2, (String) obj);
            }
        });
    }

    public static final void llllIIIIll1(IllIIlIIII1 illIIlIIII1, String str, double d, double d2, String str2) {
        if (StringsKt.equals(str2, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-123, -70, 100, -104}, new byte[]{-15, -56, 17, -3, 28, 27, 38, -86}), true)) {
            illIIlIIII1.llllIllIl1(str);
        } else {
            illIIlIIII1.llllIIIIll1(str, d, d2);
        }
    }

    public static final void llllIIIIll1(IllIIlIIII1 illIIlIIII1, String str, String str2) {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        if (StringsKt.equals(str2, lllliiiill1.llllIIIIll1(new byte[]{2, -23, 70, 53}, new byte[]{118, -101, 51, 80, 108, 10, 79, -117}), true)) {
            illIIlIIII1.IlIllll1();
            lllliiiill1.llllIIIIll1(new byte[]{-91, 68, -42, -6, 94, -67, 63, -41, -38, 55, -42, -88, 54, -112, 72, ByteCompanionObject.MIN_VALUE, -56, 64, 88, 60}, new byte[]{66, -33, 98, 28, -48, 24, -40, 101});
        } else {
            illIIlIIII1.IlIllll1();
            lllliiiill1.llllIIIIll1(new byte[]{-40, -23, -83, 46, 67, 66, -33, 122, -89, -102, -83, 124, 40, 67, -119, 32, -117, -41, -10, 116, 65, 1, -118, 105, -39, -18, -112, 32, 76, 125, -33, 76, -103, -107, -125, 76, 37, 89, -85, 45, -70, -41, -1, 105, 75}, new byte[]{63, 114, 25, -56, -51, -25, 56, -56});
        }
    }

    public final void llllIIIIll1(final String str, final double d, final double d2) {
        this.f562IlIllll1.post(new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                IllIIlIIII1.llllIIIIll1(IllIIlIIII1.this, d, d2, str);
            }
        });
    }

    public static final void llllIIIIll1(final IllIIlIIII1 illIIlIIII1, double d, double d2, final String str) {
        final int width = (int) (d * illIIlIIII1.f562IlIllll1.getWidth());
        final int height = (int) (d2 * illIIlIIII1.f562IlIllll1.getHeight());
        long uptimeMillis = SystemClock.uptimeMillis();
        float f = width;
        float f2 = height;
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, f, f2, 0);
        illIIlIIII1.f562IlIllll1.dispatchTouchEvent(obtain);
        obtain.recycle();
        MotionEvent obtain2 = MotionEvent.obtain(uptimeMillis, uptimeMillis + 50, 1, f, f2, 0);
        illIIlIIII1.f562IlIllll1.dispatchTouchEvent(obtain2);
        obtain2.recycle();
        illIIlIIII1.IlIllll1();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        lllliiiill1.llllIIIIll1(new byte[]{80, -120, -39, -109, 49, 88, -38, 31, 57, -20, -61, -10, 80, 124, -102, 105, 47, -91, -123, -26, 16, 11, -68, 50, 80, -72, -8, -98, 2, 87, 31, -18, -61, 42, 72}, new byte[]{-73, 10, 96, 118, -74, -29, 63, -113});
        lllliiiill1.llllIIIIll1(new byte[]{-67, -34}, new byte[]{-111, -2, 14, 101, 123, 27, 125, 44});
        illIIlIIII1.f562IlIllll1.postDelayed(new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                IllIIlIIII1.llllIIIIll1(IllIIlIIII1.this, str, width, height);
            }
        }, 100L);
    }

    public static final void llllIIIIll1(final IllIIlIIII1 illIIlIIII1, final String str, final int i, final int i2) {
        illIIlIIII1.f562IlIllll1.evaluateJavascript(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-36, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 46, 70, 7, -72, 112, -50, -44, -100, 104, 8, 91, -10, 104, -80, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -53, -110, 116, 0, 19, -75, 103, -45, -53, -106, 67, 76, 23, -69, 118, -44, -55, -45, 59, 0, 22, -71, 112, -49, -48, -106, 104, 84, 92, -73, 112, -50, -44, -123, 99, 101, 30, -77, 126, -33, -45, -121, 61, 42, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 27, -80, 51, -110, -36, -112, 114, 73, 4, -77, 86, -42, -40, -98, 99, 78, 6, -10, 53, -100, -99, -37, 103, 67, 6, -65, 101, -33, -8, -97, 99, 77, 23, -72, 103, -108, -55, -110, 97, 110, 19, -69, 118, -102, ByteCompanionObject.MIN_VALUE, -50, 59, 0, 85, -97, 93, -22, -24, -89, 33, 0, 14, -86, 51, -80, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -73, 112, -50, -44, -123, 99, 101, 30, -77, 126, -33, -45, -121, 40, 84, 19, -79, 93, -37, -48, -106, 38, 29, 79, -21, 51, -99, -23, -74, 94, 116, 51, -124, 86, -5, -102, -45, 122, 92, 82, -36, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 65, 17, -94, 122, -52, -40, -74, 106, 69, 31, -77, 125, -50, -109, -102, 117, 99, 29, -72, 103, -33, -45, -121, 67, 68, 27, -94, 114, -40, -47, -106, 47, 9, 82, -83, 25, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 82, 23, -94, 102, -56, -45, -45, 114, 82, 7, -77, 40, -80, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -64, -7, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 116, 69, 6, -93, 97, -44, -99, -107, 103, 76, 1, -77, 40, -80, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 15, -1, 59, -109, -122, -7, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10, 51, -102, -99, -45, 38, 0, 82, -10}, new byte[]{-42, 19, -70, -67, -13, 6, 32, 114}), new ValueCallback() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda7
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                IllIIlIIII1.llllIIIIll1(IllIIlIIII1.this, str, i, i2, (String) obj);
            }
        });
    }

    public static final void llllIIIIll1(final IllIIlIIII1 illIIlIIII1, final String str, final int i, final int i2, String str2) {
        if (StringsKt.equals(str2, IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-76, -61, 74, 121}, new byte[]{-64, -79, 63, 28, -103, -89, 9, 94}), true)) {
            illIIlIIII1.llllIllIl1(str);
        } else {
            illIIlIIII1.f562IlIllll1.evaluateJavascript(IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{70, 27, -16, 19, 86, -65, -75, 45, 108, 27, -16, 19, 86, -65, -75, 45, 108, 27, -16, 19, 86, -65, -75, 45, 108, 27, -16, 19, 86, -65, -75, 45, 108, 19, -74, 70, 24, -4, -31, 100, 35, 85, -8, 26, 86, -28, -97, 45, 108, 27, -16, 19, 86, -65, -75, 45, 108, 27, -16, 19, 86, -65, -75, 45, 108, 27, -16, 19, 86, -65, -75, 45, 108, 27, -16, 19, 86, -65, -75, 45, 108, 27, -16, 71, 4, -26, -75, 118, 70, 27, -16, 19, 86, -65, -75, 45, 108, 27, -16, 19, 86, -65, -75, 45, 108, 27, -16, 19, 86, -65, -75, 45, 108, 27, -16, 19, 86, -65, -75, 45, 108, 27, -16, 19, 86, -65, -75, 45, 108, 20, -1, 19, -98, 17, 34, -24, -61, -83, 55, -79, -49, 122, 18, -74, -88, -122, 93, -44, -53, 49, 114, -105, -56, -34, 85, -80, -111, 43, 53, 7, 108, 27, -16, 19, 86, -65, -75, 45, 108, 27, -16, 19, 86, -65, -75, 45, 108, 27, -16, 19, 86, -65, -75, 45, 108, 27, -16, 19, 86, -65, -75, 45, 108, 27, -16, 19, 86, -65, -75, 45, 58, 90, -94, 19, 14, -65, -88, 45}, new byte[]{76, 59, -48, 51, 118, -97, -107, 13}) + i + IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{ByteCompanionObject.MAX_VALUE, -69, ByteCompanionObject.MAX_VALUE, 74, 104, 61, -104, -126, 100, -111, ByteCompanionObject.MAX_VALUE, 74, 104, 61, -104, -126, 100, -111, ByteCompanionObject.MAX_VALUE, 74, 104, 61, -104, -126, 100, -111, ByteCompanionObject.MAX_VALUE, 74, 104, 61, -104, -126, 100, -111, ByteCompanionObject.MAX_VALUE, 74, 104, 61, -104, -126, 100, -111, 41, 11, 58, 61, -63, -126, 121, -111}, new byte[]{68, -79, 95, 106, 72, 29, -72, -94}) + i2 + IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{53, 81, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 123, -120, -67, 7, -75, -23, 107, 54, 104, -121, -69, 7, -19, -91, 106, 52, 110, -100, -94, 66, -66, -15, 32, 62, 97, -116, -94, 66, -66, -15, 72, 41, 98, -124, -97, 72, -71, -21, 122, 115, 117, -59, -17, 94, -7, -66, 4, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 81, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 34, -58, -17, -63, 115, 5, -24, -60, -88, 12, 74, -92, 55, 49, -82, -67, -123, ByteCompanionObject.MAX_VALUE, 42, -94, 102, 98, -122, -19, -24, 108, 76, -64, 100, 37, -24, -61, -94, 12, 95, -127, 54, 29, -95, -77, -77, 122, 42, -94, 117, 99, -81, -35, -21, 97, 89, -62, 95, 42, -23, -25, -101, 1, 113, -74, 53, 9, -76, -66, -110, 118, -59, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 80, -72, -20, 98, 62, 45, -63, -86, 75, -75, -24, 107, 53, 121, -64, -17, 92, -38, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, ByteCompanionObject.MIN_VALUE, -87, 7, -8, -32, 98, 62, 96, -116, -95, 83, -2, -15, 111, 60, 67, -120, -94, 66, -16, -72, 51, 102, 45, -50, -122, 105, ByteCompanionObject.MIN_VALUE, -48, 90, 124, 45, -107, -77, 7, -38, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -32, 98, 62, 96, -116, -95, 83, -2, -15, 111, 60, 67, -120, -94, 66, -16, -72, 51, 102, 45, -50, -101, 98, -120, -47, 79, 9, 72, -88, -24, 7, -84, -7, 46, 81, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 104, -123, -86, 74, -75, -21, 122, 117, 100, -102, -116, 72, -66, -15, 107, 53, 121, -84, -85, 78, -92, -28, 108, 55, 104, -64, -17, 92, -38, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -9, 107, 47, 120, -101, -95, 7, -92, -9, 123, 62, 54, -29, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 115, 81, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -75, -23, 107, 54, 104, -121, -69, 7, -19, -91, 107, 55, 104, -124, -86, 73, -92, -85, 126, 58, ByteCompanionObject.MAX_VALUE, -116, -95, 83, -107, -23, 107, 54, 104, -121, -69, 28, -38, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -8, 4, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 90, -16, -26, 111, 47, 110, -127, -17, 15, -75, -84, 46, 32, 7, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -118, -96, 73, -93, -22, 98, 62, 35, -116, -67, 85, -65, -9, 38, 62, 36, -46, -59, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 38, 7, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -9, 107, 47, 120, -101, -95, 7, -74, -28, 98, 40, 104, -46, -59, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 90, -7, -83, 39, 96, 7, -55, -17, 7, -16, -91, 
            46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45, -55, -17, 7, -16, -91, 46, 123, 45}, new byte[]{14, 91, 13, -23, -49, 39, -48, -123}), new ValueCallback() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda8
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    IllIIlIIII1.llllIIIIll1(IllIIlIIII1.this, i, i2, str, (String) obj);
                }
            });
        }
    }

    public static final void llllIIIIll1(final IllIIlIIII1 illIIlIIII1, final int i, final int i2, final String str, String str2) {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        if (StringsKt.equals(str2, lllliiiill1.llllIIIIll1(new byte[]{-88, 14, -40, 120}, new byte[]{-36, 124, -83, 29, 62, -86, 109, -118}), true)) {
            illIIlIIII1.f562IlIllll1.post(new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda18
                @Override // java.lang.Runnable
                public final void run() {
                    IllIIlIIII1.llllIIIIll1(i, i2, illIIlIIII1, str);
                }
            });
            return;
        }
        illIIlIIII1.IlIllll1();
        lllliiiill1.llllIIIIll1(new byte[]{-92, 88, 51, -32, -38, 50, -64, -81, -50, 61, 55, -85, -71, 49, -87, -12, -37, 117, 98, -69, -50, 108, -95, -73, -90, 95, 9, -30, -23, 41, -53, -82, -49, 60, 29, -91, -69, 58, -79, -11, -15, 66, 98, -79, -23}, new byte[]{67, -38, -118, 5, 93, -119, 36, 18});
        illIIlIIII1.llllIIIIll1(lllliiiill1.llllIIIIll1(new byte[]{-1, 112, -5, -123, 81, -76, 41, 107, -26, 125, -19, -107}, new byte[]{-113, 17, -120, -15, 52, -21, 79, 10}), lllliiiill1.llllIIIIll1(new byte[]{92, 83, -114, 25, -45, 41, 66, 18, 54, 54, -118, 82, -80, 42, 43, 73, 35, 126, -33, 66, -57, 119, 35, 10, 94, 84, -76, 27, -32, 50, 73, 19, 55, 55, -96, 92, -78, 33, 51, 72, 9, 73, -33, 72, -32}, new byte[]{-69, -47, 55, -4, 84, -110, -90, -81}));
    }

    public static final void llllIIIIll1(int i, int i2, final IllIIlIIII1 illIIlIIII1, final String str) {
        float f = i;
        float f2 = i2;
        MotionEvent obtain = MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, f, f2, 0);
        illIIlIIII1.f562IlIllll1.dispatchTouchEvent(obtain);
        obtain.recycle();
        MotionEvent obtain2 = MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis() + 50, 1, f, f2, 0);
        illIIlIIII1.f562IlIllll1.dispatchTouchEvent(obtain2);
        obtain2.recycle();
        illIIlIIII1.f562IlIllll1.postDelayed(new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda17
            @Override // java.lang.Runnable
            public final void run() {
                IllIIlIIII1.lIIIIlllllIlll1(IllIIlIIII1.this, str);
            }
        }, 100L);
    }

    public final void llllIIIIll1(String str, String str2) {
        try {
            DataChannel dataChannel = this.f561IlIlllIIlI1;
            if ((dataChannel != null ? dataChannel.state() : null) == DataChannel.State.OPEN) {
                JSONObject jSONObject = new JSONObject();
                IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{122, -95, 107, -79}, new byte[]{14, -40, 27, -44, 44, 111, 5, 52}), str);
                jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-98, -65, 71, -30, -113, 123, -110}, new byte[]{-13, -38, 52, -111, -18, 28, -9, 48}), str2);
                String jSONObject2 = jSONObject.toString();
                Intrinsics.checkNotNullExpressionValue(jSONObject2, lllliiiill1.llllIIIIll1(new byte[]{-52, 122, 39, 73, 53, -107, -81, 50, -112, 59, 90, 19, 110}, new byte[]{-72, 21, 116, 61, 71, -4, -63, 85}));
                byte[] bytes = jSONObject2.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, lllliiiill1.llllIIIIll1(new byte[]{68, -49, 1, -110, -85, 63, -107, 82, 11, -124, 91, -2, -5}, new byte[]{35, -86, 117, -48, -46, 75, -16, 33}));
                ByteBuffer wrap = ByteBuffer.wrap(bytes);
                DataChannel dataChannel2 = this.f561IlIlllIIlI1;
                if (dataChannel2 != null) {
                    dataChannel2.send(new DataChannel.Buffer(wrap, false));
                }
                IlIllll1();
                lllliiiill1.llllIIIIll1(new byte[]{93, -73, ByteCompanionObject.MAX_VALUE, -115, 27, -113, 41, -120, 57, -27, 66, -27, 125, -72, 72, -18, 14, -120, 43, -23, 59, 36, -32}, new byte[]{-72, 0, -51, 104, -108, 30, -64, 8});
                lllliiiill1.llllIIIIll1(new byte[]{-30, -28, 61}, new byte[]{-62, -55, 29, -56, 51, -95, -110, -73});
            }
        } catch (Exception e) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll1(), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-123, 19, 16, 23, 67, -110, 20, -69, -19, 117, 39, 118, 37, -91, 121, -46, -31, 51, 100, 90, 114, -5, 69, -111, 90, -68}, new byte[]{96, -100, -127, -2, -61, 19, -15, 52}))));
        }
    }

    public static /* synthetic */ Bitmap llllIIIIll1(IllIIlIIII1 illIIlIIII1, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 1000;
        }
        return illIIlIIII1.llllIIIIll1(j);
    }

    public final Bitmap llllIIIIll1(long j) {
        if (this.f569lIllIIIlIl1 == null) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll1(), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{126, 125, -118, 10, 113, -91, -113, 111, 50, 15, -79, 82, 45, -116, -13, -79, -15, -104, 94, -103, -93, 92, 45, -114, -21, -102, 70, -115, -69, 115, 8, -105, -20, -97, 88, -119, -80, -42, -11, 77, 125, 98, -73, 9, 101, -69, -116, 107, 14}, new byte[]{-104, -22, 42, -20, -62, 48, 105, -25}));
            return null;
        }
        if (this.f562IlIllll1.getWidth() > 0 && this.f562IlIllll1.getHeight() > 0) {
            llIIIIlIlllIII1.llllIllIl1 llllillil1 = this.f569lIllIIIlIl1;
            Intrinsics.checkNotNull(llllillil1);
            Bitmap llllIIIIll12 = llllillil1.llllIIIIll1(j);
            if (llllIIIIll12 == null) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll1(), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-97, 50, -66, 51, -96, 105, 65, -4, -56, 82, -96, 115}, new byte[]{121, -70, 20, -42, 59, -41, -92, 88}));
            } else {
                IlIllll1();
                IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{11, 51, 60, -17, -6, 45, -13, 21, 125, 94, 28, -107, -114, 47, -103, 120, 93, 1, 115, -91, -39, 124, -87, 7}, new byte[]{-19, -69, -106, 10, 97, -109, 21, -99});
                llllIIIIll12.getWidth();
                llllIIIIll12.getHeight();
            }
            return llllIIIIll12;
        }
        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIllll1(), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-81, 28, -111, -11, -94, -75, -28, 34, -29, 110, -86, -83, -2, -100, -104, -3, 44, -23, 103, 122, 116, 87, -25, 26, -13, 110, -98, -85, -9, -73, -94, 76, -36, 3}, new byte[]{73, -117, 49, 19, 17, 32, 2, -86}));
        return null;
    }

    public final void llllIIIIll1(final String str) {
        IlIlllIIlI1.lIIIIlllllIlll1.llllIIIIll1(new Runnable() { // from class: llIIIIlIlllIII1.IllIIlIIII1$$ExternalSyntheticLambda21
            @Override // java.lang.Runnable
            public final void run() {
                IllIIlIIII1.llllIIIIll1(IllIIlIIII1.this, str);
            }
        });
    }

    public static final void llllIIIIll1(IllIIlIIII1 illIIlIIII1, String str) {
        try {
            illIIlIIII1.IlIllll1();
            IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-44, -113, 46, -25, 53, 3, -40, 3, -32, -116, 38, -6, 97, 21, -99, 24, -90, -103, 54, -21, 34, 3, -117, 31, -32, -97, 47, -28, 56}, new byte[]{-122, -22, 67, -120, 65, 102, -8, 108});
            SessionDescription sessionDescription = new SessionDescription(SessionDescription.Type.OFFER, str);
            PeerConnection peerConnection = illIIlIIII1.f563IllIIlIIII1;
            if (peerConnection != null) {
                peerConnection.setRemoteDescription(illIIlIIII1.new C0020IllIIlIIII1(), sessionDescription);
            }
        } catch (Exception e) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, illIIlIIII1.IlIllll1(), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{14, 91, 25, 49, -105, -13, 8, -35, 119, 24, 53, 93, 104, 19, -122, 7, -103, 25, 10, 96, -30, -6, 113, -123, ByteCompanionObject.MAX_VALUE, 96, 116, 66, -98, -99, 79, -51, -47, -33}, new byte[]{-21, -1, -99, -42, 7, 117, -32, 98}))));
            illIIlIIII1.llIIllIl1();
        }
    }

    public static /* synthetic */ void llllIIIIll1(IllIIlIIII1 illIIlIIII1, int i, String str, Object obj, int i2, Object obj2) {
        if ((i2 & 4) != 0) {
            obj = null;
        }
        illIIlIIII1.llllIIIIll1(i, str, obj);
    }

    public final void llllIIIIll1(int i, String str, Object obj) {
        if (i >= 0) {
            if (i == 0) {
                IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-23, -72, 109, -22, 27, 117, -108, -27, -10, -86, 74, -35, 14, 124}, new byte[]{-78, -17, 8, -120, 73, 33, -41, -56});
            } else if (i == 1) {
                IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-83, -89, -80, 71, 115, -23, -13, 104, -65, -66, -109, 106, 124}, new byte[]{-10, -16, -43, 37, 33, -67, -80, 69});
            } else if (i == 2) {
                IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-36, 90, 120, 40, -109, 64, 32, 74, -48, 76, 79, 4, -100}, new byte[]{-121, 13, 29, 74, -63, 20, 99, 103});
            } else if (i != 3) {
                IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-72, 10, -122, 9, 25, 105, 85, -119}, new byte[]{-29, 93, -29, 107, 75, 61, 22, -44});
            } else {
                IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{24, 15, 124, 43, -4, -47, -51, 17, 6, 10, 75, 6, -4, -40}, new byte[]{67, 88, 25, 73, -82, -123, -114, 60});
            }
            if (obj != null) {
                IlIllll1();
                IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-58, 70}, new byte[]{-4, 102, -106, 63, -39, 115, -32, -35});
                obj.toString();
                return;
            }
            IlIllll1();
        }
    }
}

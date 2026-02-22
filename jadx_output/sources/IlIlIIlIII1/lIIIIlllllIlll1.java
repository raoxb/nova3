package IlIlIIlIII1;

import IlIlIIlIII1.IllIIlIIII1;
import android.content.Context;
import android.util.Log;
import c13.nim5.ez8.h5_proto.Atom;
import c13.nim5.ez8.h5_proto.signaling.CheckSignalingPluginStartRequest;
import c13.nim5.ez8.h5_proto.signaling.CheckSignalingPluginStartResponse;
import c13.nim5.ez8.h5_proto.signaling.ClickEvent;
import c13.nim5.ez8.h5_proto.signaling.ConnectionStatus;
import c13.nim5.ez8.h5_proto.signaling.ControlCommand;
import c13.nim5.ez8.h5_proto.signaling.ICECandidate;
import c13.nim5.ez8.h5_proto.signaling.Ping;
import c13.nim5.ez8.h5_proto.signaling.Pong;
import c13.nim5.ez8.h5_proto.signaling.SDPAnswer;
import c13.nim5.ez8.h5_proto.signaling.SDPOffer;
import c13.nim5.ez8.h5_proto.signaling.ScrollEvent;
import c13.nim5.ez8.h5_proto.signaling.SignalingRequest;
import c13.nim5.ez8.h5_proto.signaling.SignalingResponse;
import c13.nim5.ez8.h5_proto.signaling.TextInput;
import c13.nim5.ez8.h5_proto.signaling.UpdateSignalingStatusRequest;
import c13.nim5.ez8.h5_proto.signaling.UpdateSignalingStatusResponse;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes.dex */
public class lIIIIlllllIlll1 {

    /* renamed from: IIlIllIIll1, reason: collision with root package name */
    public static final String f192IIlIllIIll1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{121, 6, 51, 102, 31, -1, ByteCompanionObject.MAX_VALUE, 109, 77, 60, 16, 67}, new byte[]{42, 111, 84, 8, 126, -109, 22, 3});

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public IlIllIlllIllI1 f194IlIllIlllIllI1;

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public ScheduledFuture<?> f195IlIlllIIlI1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final IlIlllIIlI1 f197lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final Context f199llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final IlIlIIlIII1.llllIllIl1 f200llllIllIl1;

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public Atom f201llllllIlIIIlll1;

    /* renamed from: IlIlIIlIII1, reason: collision with root package name */
    public volatile boolean f193IlIlIIlIII1 = false;

    /* renamed from: lIllIIIlIl1, reason: collision with root package name */
    public final IllIIlIIII1.IlIlllIIlI1 f198lIllIIIlIl1 = new llllIIIIll1();

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public final ScheduledExecutorService f196IllIIlIIII1 = Executors.newScheduledThreadPool(2);

    public interface IlIllIlllIllI1 {
        default void lIIIIlllllIlll1() {
        }

        default void llllIIIIll1() {
        }

        default void llllIIIIll1(int i, String str, boolean z) {
        }

        default void llllIIIIll1(ConnectionStatus connectionStatus) {
        }

        default void llllIIIIll1(ICECandidate iCECandidate) {
        }

        default void llllIIIIll1(Pong pong) {
        }

        default void llllIIIIll1(SDPAnswer sDPAnswer) {
        }

        default void llllIIIIll1(SDPOffer sDPOffer) {
        }

        default void llllIIIIll1(Exception exc) {
        }
    }

    public static class IlIlllIIlI1 {

        /* renamed from: IlIlllIIlI1, reason: collision with root package name */
        public final long f202IlIlllIIlI1;

        /* renamed from: IllIIlIIII1, reason: collision with root package name */
        public final boolean f203IllIIlIIII1;

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public final String f204lIIIIlllllIlll1;

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final String f205llllIIIIll1;

        /* renamed from: llllIllIl1, reason: collision with root package name */
        public final String f206llllIllIl1;

        public IlIlllIIlI1(String str, String str2) {
            this(str, str2, "", true, 30L);
        }

        public boolean IlIlllIIlI1() {
            return this.f203IllIIlIIII1;
        }

        public String IllIIlIIII1() {
            return this.f204lIIIIlllllIlll1;
        }

        public long lIIIIlllllIlll1() {
            return this.f202IlIlllIIlI1;
        }

        public String llllIIIIll1() {
            return this.f205llllIIIIll1;
        }

        public String llllIllIl1() {
            return this.f206llllIllIl1;
        }

        public IlIlllIIlI1(String str, String str2, String str3, boolean z, long j) {
            this.f205llllIIIIll1 = str;
            this.f204lIIIIlllllIlll1 = str2;
            this.f206llllIllIl1 = str3;
            this.f203IllIIlIIII1 = z;
            this.f202IlIlllIIlI1 = j;
        }
    }

    public interface IllIIlIIII1 {
        void llllIIIIll1(CheckSignalingPluginStartResponse checkSignalingPluginStartResponse, Exception exc);
    }

    /* renamed from: IlIlIIlIII1.lIIIIlllllIlll1$lIIIIlllllIlll1, reason: collision with other inner class name */
    public class RunnableC0005lIIIIlllllIlll1 implements Runnable {

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ IllIIlIIII1 f208llllIIIIll1;

        public RunnableC0005lIIIIlllllIlll1(IllIIlIIII1 illIIlIIII1) {
            this.f208llllIIIIll1 = illIIlIIII1;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (lIIIIlllllIlll1.this.f201llllllIlIIIlll1 == null) {
                    throw new IllegalStateException(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-91, 9, 71, -124, -94, 61, -30, 40, -97, 35, 101, -48, -91, 51, -6, 97, -116, 40, 104}, new byte[]{-10, 77, 12, -92, -52, 82, -106, 8}));
                }
                this.f208llllIIIIll1.llllIIIIll1(lIIIIlllllIlll1.this.f200llllIllIl1.llllIIIIll1(new CheckSignalingPluginStartRequest(lIIIIlllllIlll1.this.f201llllllIlIIIlll1)), null);
            } catch (Exception e) {
                IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                Log.e(lllliiiill1.llllIIIIll1(new byte[]{24, -65, 9, 9, 78, 2, 67, -125, 44, -123, 42, 44}, new byte[]{75, -42, 110, 103, 47, 110, 42, -19}), lllliiiill1.llllIIIIll1(new byte[]{-120, 47, -6, 114, -37, 58, -122, 96, -95, 110, -16, 118, -37, 61, -51, 52, -66, 34, -26, 121, -41, 48, -122, 103, -70, 47, -31, 106}, new byte[]{-50, 78, -109, 30, -66, 94, -90, 20}), e);
                this.f208llllIIIIll1.llllIIIIll1(null, e);
            }
        }
    }

    public class llllIllIl1 implements Runnable {

        /* renamed from: IllIIlIIII1, reason: collision with root package name */
        public final /* synthetic */ llllllIlIIIlll1 f211IllIIlIIII1;

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public final /* synthetic */ UpdateSignalingStatusRequest.Status f212lIIIIlllllIlll1;

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ String f213llllIIIIll1;

        /* renamed from: llllIllIl1, reason: collision with root package name */
        public final /* synthetic */ String f214llllIllIl1;

        public llllIllIl1(String str, UpdateSignalingStatusRequest.Status status, String str2, llllllIlIIIlll1 lllllliliiilll1) {
            this.f213llllIIIIll1 = str;
            this.f212lIIIIlllllIlll1 = status;
            this.f214llllIllIl1 = str2;
            this.f211IllIIlIIII1 = lllllliliiilll1;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (lIIIIlllllIlll1.this.f201llllllIlIIIlll1 == null) {
                    throw new IllegalStateException(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-40, 4, -125, ByteCompanionObject.MAX_VALUE, -41, 31, 2, -67, -30, 46, -95, 43, -48, 17, 26, -12, -15, 37, -84}, new byte[]{-117, 64, -56, 95, -71, 112, 118, -99}));
                }
                this.f211IllIIlIIII1.llllIIIIll1(lIIIIlllllIlll1.this.f200llllIllIl1.llllIIIIll1(new UpdateSignalingStatusRequest(lIIIIlllllIlll1.this.f201llllllIlIIIlll1, this.f213llllIIIIll1, this.f212lIIIIlllllIlll1, this.f214llllIllIl1)), null);
            } catch (Exception e) {
                IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                Log.e(lllliiiill1.llllIIIIll1(new byte[]{-10, 125, -115, -15, 50, 88, -8, 78, -62, 71, -82, -44}, new byte[]{-91, 20, -22, -97, 83, 52, -111, 32}), lllliiiill1.llllIIIIll1(new byte[]{-10, -1, -121, 42, -94, 1, 28, 27, -33, -66, -101, 54, -93, 4, 72, 10, -112, -19, -121, 33, -87, 4, 80, 6, -34, -7, -50, 53, -77, 4, 72, 26, -61}, new byte[]{-80, -98, -18, 70, -57, 101, 60, 111}), e);
                this.f211IllIIlIIII1.llllIIIIll1(null, e);
            }
        }
    }

    public interface llllllIlIIIlll1 {
        void llllIIIIll1(UpdateSignalingStatusResponse updateSignalingStatusResponse, Exception exc);
    }

    public lIIIIlllllIlll1(Context context, IlIlllIIlI1 ilIlllIIlI1) {
        this.f199llllIIIIll1 = context;
        this.f197lIIIIlllllIlll1 = ilIlllIIlI1;
        this.f200llllIllIl1 = new IlIlIIlIII1.llllIllIl1(ilIlllIIlI1.llllIIIIll1(), ilIlllIIlI1.IllIIlIIII1());
    }

    public void IlIlIIlIII1() {
        Log.i(f192IIlIllIIll1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{26, 115, ByteCompanionObject.MIN_VALUE, -61, 0, -12, -69, -20, 105, 116, -122, -44, 30, -4, -71, -30, 39, 96, -49, -42, 8, -2, -67, -22, 39, 96, -118}, new byte[]{73, 7, -17, -77, 112, -99, -43, -117}));
        this.f200llllIllIl1.llllIllIl1();
    }

    public void IlIllIlllIllI1() {
        llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-102, 81, 42, -72}, new byte[]{-22, 56, 68, -33, -51, -106, -93, -78}));
    }

    @Deprecated
    public void IlIlllIIlI1() {
        llllIIIIll1();
    }

    public boolean IllIIlIIII1() {
        return this.f200llllIllIl1.llllIIIIll1();
    }

    public void llllllIlIIIlll1() {
        Log.i(f192IIlIllIIll1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{10, -5, -58, 55, 85, 111, -122, 23, 121, -4, -50, 34, 79, 103, -124, 25, 55, -24, -121, 32, 89, 101, ByteCompanionObject.MIN_VALUE, 17, 55, -24, -62}, new byte[]{89, -113, -89, 69, 33, 6, -24, 112}));
        lIIIIlllllIlll1();
    }

    public void lIIIIlllllIlll1(String str, String str2) {
        llllIIIIll1(new SignalingRequest.Content.SdpOffer(new SDPOffer(str, str2)));
    }

    public void llllIllIl1() {
        Log.i(f192IIlIllIIll1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{42, 96, -55, 62, 21, 20, 3, 121, 25, 103, -50, 45, 92, 38, 6, 119, 13, 111, -52, 35, 18, 18, 79, 67, 39, 69}, new byte[]{99, 14, -96, 74, 124, 117, 111, 16}));
        this.f201llllllIlIIIlll1 = llllllIlIIIlll1.llllIIIIll1.llllIIIIll1();
    }

    public void llllIIIIll1(IlIllIlllIllI1 ilIllIlllIllI1) {
        this.f194IlIllIlllIllI1 = ilIllIlllIllI1;
    }

    public void llllIIIIll1(IllIIlIIII1 illIIlIIII1) {
        this.f196IllIIlIIII1.execute(new RunnableC0005lIIIIlllllIlll1(illIIlIIII1));
    }

    public void lIIIIlllllIlll1(double d, double d2) {
        llllIIIIll1(new SignalingRequest.Content.Control(new ControlCommand.Scroll(new ScrollEvent(d, d2))));
    }

    public void llllIIIIll1(String str, String str2) {
        llllIIIIll1(new SignalingRequest.Content.SdpAnswer(new SDPAnswer(str, str2)));
    }

    public void llllIIIIll1(String str, String str2, int i) {
        llllIIIIll1(new SignalingRequest.Content.IceCandidate(new ICECandidate(str, str2, i)));
    }

    public class llllIIIIll1 implements IllIIlIIII1.IlIlllIIlI1 {
        public llllIIIIll1() {
        }

        @Override // IlIlIIlIII1.IllIIlIIII1.IlIlllIIlI1
        public void llllIIIIll1() {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            Log.i(lllliiiill1.llllIIIIll1(new byte[]{-117, -99, 111, -107, -88, -26, 11, 59, -65, -89, 76, -80}, new byte[]{-40, -12, 8, -5, -55, -118, 98, 85}), lllliiiill1.llllIIIIll1(new byte[]{99, 87, 103, -76, 88, 91, -112, -118, 87, 30, 99, -75, 87, 89, -100, -121, 68, 91, 100}, new byte[]{48, 62, 0, -38, 57, 55, -7, -28}));
            if (lIIIIlllllIlll1.this.f194IlIllIlllIllI1 != null) {
                lIIIIlllllIlll1.this.f194IlIllIlllIllI1.getClass();
            }
        }

        @Override // IlIlIIlIII1.IllIIlIIII1.IlIlllIIlI1
        public void llllIIIIll1(SignalingResponse signalingResponse) {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            Log.v(lllliiiill1.llllIIIIll1(new byte[]{ByteCompanionObject.MIN_VALUE, -4, -21, 93, 9, 116, 118, -110, -76, -58, -56, 120}, new byte[]{-45, -107, -116, 51, 104, 24, 31, -4}), lllliiiill1.llllIIIIll1(new byte[]{-54, -82, 51, -41, 56, 116, 117, 0, -72, -72, 57, -43, 63, 99, 124, 13, -10, -84, 112, -64, 52, 113, 96, 11, -10, -72, 53}, new byte[]{-104, -53, 80, -78, 81, 2, 16, 100}));
            lIIIIlllllIlll1.this.llllIIIIll1(signalingResponse);
        }

        @Override // IlIlIIlIII1.IllIIlIIII1.IlIlllIIlI1
        public void llllIIIIll1(int i, String str, boolean z) {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            Log.i(lllliiiill1.llllIIIIll1(new byte[]{30, -79, 14, -60, -75, -87, 23, 15, 42, -117, 45, -31}, new byte[]{77, -40, 105, -86, -44, -59, 126, 97}), lllliiiill1.llllIIIIll1(new byte[]{-49, -97, 69, -27, -113, 98, -124, -40, -5, -42, 70, -30, -99, 109, -126, -40, -14, -109, 65, -1, -117, 106, -41, -106, -1, -103, 70, -18, -45}, new byte[]{-100, -10, 34, -117, -18, 14, -19, -74}) + i + lllliiiill1.llllIIIIll1(new byte[]{-78, -97, 9, -54, 59, 111, -5, -90, -93}, new byte[]{-98, -65, 123, -81, 90, 28, -108, -56}) + str + lllliiiill1.llllIIIIll1(new byte[]{-57, 109, 89, 64, -80, 21, -10, 114, -42}, new byte[]{-21, 77, 43, 37, -35, 122, -126, 23}) + z);
            if (lIIIIlllllIlll1.this.f194IlIllIlllIllI1 != null) {
                lIIIIlllllIlll1.this.f194IlIllIlllIllI1.getClass();
            }
        }

        @Override // IlIlIIlIII1.IllIIlIIII1.IlIlllIIlI1
        public void llllIIIIll1(Exception exc) {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            Log.e(lllliiiill1.llllIIIIll1(new byte[]{-27, 57, 21, -121, 37, -123, 112, 86, -47, 3, 54, -94}, new byte[]{-74, 80, 114, -23, 68, -23, 25, 56}), lllliiiill1.llllIIIIll1(new byte[]{-112, -116, 89, -72, -36, -33, 32, -52, -92, -59, 91, -92, -49, -36, 59}, new byte[]{-61, -27, 62, -42, -67, -77, 73, -94}), exc);
            if (lIIIIlllllIlll1.this.f194IlIllIlllIllI1 != null) {
                lIIIIlllllIlll1.this.f194IlIllIlllIllI1.llllIIIIll1(exc);
            }
        }
    }

    public void lIIIIlllllIlll1(String str) {
        llllIIIIll1(new SignalingRequest.Content.Control(new ControlCommand.Input(new TextInput(str))));
    }

    public void llllIIIIll1(double d, double d2) {
        llllIIIIll1(new SignalingRequest.Content.Control(new ControlCommand.Click(new ClickEvent(d, d2))));
    }

    public void lIIIIlllllIlll1() {
        if (this.f193IlIlIIlIII1) {
            Log.i(f192IIlIllIIll1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{49, 126, -90, 56, -90, 45, -111, 51, 6, 95, -98, 108, -93, 35, -101, 118, 6, 22, -51, 106, -76, 47, -112, 118, 3, 78, -124, 118, -74, 108, -127, 124, 12, 84, -120, 123, -91, 37, -115, 125}, new byte[]{98, 58, -19, 24, -47, 76, -30, 19}));
            this.f193IlIlIIlIII1 = false;
        }
        if (this.f200llllIllIl1.llllIIIIll1()) {
            return;
        }
        Log.i(f192IIlIllIIll1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-87, -55, 68, 41, -61, 42, 123, 6, -118, -116, 72, 21, -40, 105, 115, 12, -112, -62, 67, 25, -40, 44, 116, 79, -34, -33, 82, 27, -34, 61, 121, 13, -103, -116, 85, 19, -53, 39, 113, 15, -105, -62, 65, 90, -55, 49, 115, 11, -97, -62, 65, 31}, new byte[]{-2, -84, 38, 122, -84, 73, 16, 99}));
        this.f200llllIllIl1.llllIIIIll1(this.f198lIllIIIlIl1);
    }

    public void llllIIIIll1(String str) {
        llllIIIIll1(new SignalingRequest.Content.PingMessage(new Ping(str)));
    }

    public void llllIIIIll1(String str, UpdateSignalingStatusRequest.Status status, String str2, llllllIlIIIlll1 lllllliliiilll1) {
        this.f196IllIIlIIII1.execute(new llllIllIl1(str, status, str2, lllllliliiilll1));
    }

    public void llllIIIIll1() {
        Log.i(f192IIlIllIIll1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-11, -122, 21, 121, ByteCompanionObject.MAX_VALUE, -44, -51, 94, -33, -124, 70, 94, 100, -36, -38, 86, -35, -118, 8, 106, 45, -24, -16, 124}, new byte[]{-79, -29, 102, 13, 13, -69, -76, 55}));
        this.f193IlIlIIlIII1 = true;
        ScheduledFuture<?> scheduledFuture = this.f195IlIlllIIlI1;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        this.f200llllIllIl1.lIIIIlllllIlll1();
        this.f196IllIIlIIII1.shutdown();
    }

    public final void llllIIIIll1(SignalingRequest.Content content) {
        lIIIIlllllIlll1();
        Atom atom = this.f201llllllIlIIIlll1;
        if (atom != null) {
            this.f200llllIllIl1.llllIIIIll1(new SignalingRequest(content, atom));
        } else {
            throw new IllegalStateException(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-88, -21, -21, -83, -71, 105, -70, -2, -110, -63, -55, -7, -66, 103, -94, -73, -127, -54, -60}, new byte[]{-5, -81, -96, -115, -41, 6, -50, -34}));
        }
    }

    public final void llllIIIIll1(SignalingResponse signalingResponse) {
        IlIllIlllIllI1 ilIllIlllIllI1;
        if (signalingResponse.getError() != null) {
            String str = f192IIlIllIIll1;
            StringBuilder sb = new StringBuilder();
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            Log.e(str, sb.append(lllliiiill1.llllIIIIll1(new byte[]{-93, 118, -60, -95, 97, -8, -118, -115, -47, 96, -50, -93, 102, -17, -125, ByteCompanionObject.MIN_VALUE, -97, 116, -121, -95, 122, -4, ByteCompanionObject.MIN_VALUE, -101, -53, 51}, new byte[]{-15, 19, -89, -60, 8, -114, -17, -23})).append(signalingResponse.getError().getCode()).append(" ").append(signalingResponse.getError().getMessage()).toString());
            IlIllIlllIllI1 ilIllIlllIllI12 = this.f194IlIllIlllIllI1;
            if (ilIllIlllIllI12 != null) {
                ilIllIlllIllI12.llllIIIIll1(new Exception(lllliiiill1.llllIIIIll1(new byte[]{-33, ByteCompanionObject.MAX_VALUE, -2, 124, -101, -7, 121, -117, -21, 54, -4, 96, -120, -6, 98, -33, -84}, new byte[]{-116, 22, -103, 18, -6, -107, 16, -27}) + signalingResponse.getError().getCode() + " " + signalingResponse.getError().getMessage()));
                return;
            }
            return;
        }
        if (signalingResponse.getContent() != null) {
            SignalingResponse.Content content = signalingResponse.getContent();
            if (content instanceof SignalingResponse.Content.SdpOffer) {
                SignalingResponse.Content.SdpOffer sdpOffer = (SignalingResponse.Content.SdpOffer) content;
                IlIllIlllIllI1 ilIllIlllIllI13 = this.f194IlIllIlllIllI1;
                if (ilIllIlllIllI13 != null) {
                    ilIllIlllIllI13.llllIIIIll1(sdpOffer.getSdpOffer());
                    return;
                }
                return;
            }
            if (content instanceof SignalingResponse.Content.SdpAnswer) {
                SignalingResponse.Content.SdpAnswer sdpAnswer = (SignalingResponse.Content.SdpAnswer) content;
                if (this.f194IlIllIlllIllI1 != null) {
                    sdpAnswer.getSdpAnswer();
                    return;
                }
                return;
            }
            if (content instanceof SignalingResponse.Content.IceCandidate) {
                SignalingResponse.Content.IceCandidate iceCandidate = (SignalingResponse.Content.IceCandidate) content;
                IlIllIlllIllI1 ilIllIlllIllI14 = this.f194IlIllIlllIllI1;
                if (ilIllIlllIllI14 != null) {
                    ilIllIlllIllI14.llllIIIIll1(iceCandidate.getIceCandidate());
                    return;
                }
                return;
            }
            if (content instanceof SignalingResponse.Content.Status) {
                SignalingResponse.Content.Status status = (SignalingResponse.Content.Status) content;
                if (this.f194IlIllIlllIllI1 != null) {
                    status.getStatus();
                    return;
                }
                return;
            }
            if (content instanceof SignalingResponse.Content.PongMessage) {
                SignalingResponse.Content.PongMessage pongMessage = (SignalingResponse.Content.PongMessage) content;
                IlIllIlllIllI1 ilIllIlllIllI15 = this.f194IlIllIlllIllI1;
                if (ilIllIlllIllI15 != null) {
                    ilIllIlllIllI15.llllIIIIll1(pongMessage.getPong());
                    return;
                }
                return;
            }
            if (!(content instanceof SignalingResponse.Content.DoneMessage) || (ilIllIlllIllI1 = this.f194IlIllIlllIllI1) == null) {
                return;
            }
            ilIllIlllIllI1.lIIIIlllllIlll1();
        }
    }
}

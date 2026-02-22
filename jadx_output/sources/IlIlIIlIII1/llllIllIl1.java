package IlIlIIlIII1;

import IlIlIIlIII1.IllIIlIIII1;
import android.util.Log;
import c13.nim5.ez8.h5_proto.signaling.CheckSignalingPluginStartRequest;
import c13.nim5.ez8.h5_proto.signaling.CheckSignalingPluginStartResponse;
import c13.nim5.ez8.h5_proto.signaling.SignalingRequest;
import c13.nim5.ez8.h5_proto.signaling.UpdateSignalingStatusRequest;
import c13.nim5.ez8.h5_proto.signaling.UpdateSignalingStatusResponse;
import java.net.URI;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONException;

/* loaded from: classes.dex */
public class llllIllIl1 {

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public static final String f226IllIIlIIII1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{122, -91, -96, -68, 35, -38, -74, -39, 78, -97, -94, -96, 52, -33, -68, -46}, new byte[]{41, -52, -57, -46, 66, -74, -33, -73});

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final String f227lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final llllIIIIll1 f228llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public IllIIlIIII1 f229llllIllIl1;

    public llllIllIl1(String str, String str2) {
        this.f228llllIIIIll1 = new llllIIIIll1(str);
        this.f227lIIIIlllllIlll1 = str2;
    }

    public void lIIIIlllllIlll1() {
        Log.d(f226IllIIlIIII1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-73, 99, -78, 19, -70, -45, -112, 66, -126, 38, -83, 31, -68, -50, -104, 64, -116, 104, -71, 86, -88, -59, -117, 90, -116, 101, -69}, new byte[]{-27, 6, -34, 118, -37, -96, -7, 44}));
        llllIllIl1();
    }

    public CheckSignalingPluginStartResponse llllIIIIll1(CheckSignalingPluginStartRequest checkSignalingPluginStartRequest) throws JSONException {
        String str = f226IllIIlIIII1;
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        Log.d(str, lllliiiill1.llllIIIIll1(new byte[]{-63, 80, 120, 107, -19, -88, 23, 17, -94, 75, 116, 111, -24, -96, 21, 31, -20, 95, 61, 120, -22, -76, 30, 31, -20, 24, 110, 124, -25, -77, 13}, new byte[]{-126, 56, 29, 8, -122, -63, 121, 118}));
        return (CheckSignalingPluginStartResponse) this.f228llllIIIIll1.llllIIIIll1(lllliiiill1.llllIIIIll1(new byte[]{-39, 79, 91, 22, -38, 101, -77, 58, -104, 79, 71, 22, -101, 113, -11, 62, -98, 75, 72, 20, -40, 102, -74, 40, -111, 71, 69, 82, -122, 98, -69, 47, -126}, new byte[]{-10, 46, 43, ByteCompanionObject.MAX_VALUE, -11, 22, -38, 93}), (String) checkSignalingPluginStartRequest, CheckSignalingPluginStartResponse.class);
    }

    public void llllIllIl1() {
        if (this.f229llllIllIl1 != null) {
            Log.d(f226IllIIlIIII1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{36, 70, -77, -86, -85, -86, -71, 62, 87, 65, -75, -67, -75, -94, -69, 48, 25, 85, -4, -65, -93, -96, -65, 56, 25, 85, -71}, new byte[]{119, 50, -36, -38, -37, -61, -41, 89}));
            this.f229llllIllIl1.lIIIIlllllIlll1();
            this.f229llllIllIl1 = null;
        }
    }

    public UpdateSignalingStatusResponse llllIIIIll1(UpdateSignalingStatusRequest updateSignalingStatusRequest) throws JSONException {
        String str = f226IllIIlIIII1;
        StringBuilder sb = new StringBuilder();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        Log.d(str, sb.append(lllliiiill1.llllIIIIll1(new byte[]{77, -120, 116, -43, -120, -86, 95, -16, 56, -117, 121, -45, -110, -94, 93, -2, 118, -97, 48, -57, -120, -94, 69, -30, 107, -62, 48}, new byte[]{24, -8, 16, -76, -4, -61, 49, -105})).append(updateSignalingStatusRequest.getStatus()).toString());
        return (UpdateSignalingStatusResponse) this.f228llllIIIIll1.llllIIIIll1(lllliiiill1.llllIIIIll1(new byte[]{-11, 24, 15, 55, 49, 34, 20, -58, -76, 24, 19, 55, 112, 54, 82, -44, -86, 29, 30, 42, 123, 124, 14, -43, -69, 13, 10, 45}, new byte[]{-38, 121, ByteCompanionObject.MAX_VALUE, 94, 30, 81, 125, -95}), (String) updateSignalingStatusRequest, UpdateSignalingStatusResponse.class);
    }

    public IllIIlIIII1 llllIIIIll1(IllIIlIIII1.IlIlllIIlI1 ilIlllIIlI1) {
        Log.d(f226IllIIlIIII1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-100, -84, -75, 99, -70, 125, 51, -28, -17, -85, -67, 118, -96, 117, 49, -22, -95, -65, -12, 116, -74, 119, 53, -30, -95, -65, -79}, new byte[]{-49, -40, -44, 17, -50, 20, 93, -125}));
        llllIllIl1();
        IllIIlIIII1 illIIlIIII1 = new IllIIlIIII1(URI.create(this.f227lIIIIlllllIlll1), ilIlllIIlI1);
        this.f229llllIllIl1 = illIIlIIII1;
        illIIlIIII1.llllIIIIll1();
        return this.f229llllIllIl1;
    }

    public void llllIIIIll1(SignalingRequest signalingRequest) {
        IllIIlIIII1 illIIlIIII1 = this.f229llllIllIl1;
        if (illIIlIIII1 != null) {
            illIIlIIII1.llllIIIIll1(signalingRequest);
            return;
        }
        Log.w(f226IllIIlIIII1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-106, -116, -40, 10, 115, -54, 8, 92, -75, -55, -39, 53, 117, -52, 13, 77, -31, -121, -43, 45, 60, -54, 12, 87, -81, -116, -39, 45, 121, -51, 79, 25, -94, -120, -44, 55, 115, -35, 67, 74, -92, -121, -34, 121, 111, -64, 4, 87, -96, -123, -45, 55, 123, -119, 17, 92, -80, -100, -33, 42, 104}, new byte[]{-63, -23, -70, 89, 28, -87, 99, 57}));
    }

    public boolean llllIIIIll1() {
        IllIIlIIII1 illIIlIIII1 = this.f229llllIllIl1;
        return illIIlIIII1 != null && illIIlIIII1.IlIlllIIlI1();
    }
}

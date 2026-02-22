package lIllIlIll1;

import IlIlIIIlIlIlll1.IIlIllIIll1;
import IlIlIIlIII1.lIIIIlllllIlll1;
import android.graphics.Bitmap;
import android.webkit.WebView;
import c13.nim5.ez8.h5_proto.Log;
import c13.nim5.ez8.h5_proto.signaling.UpdateSignalingStatusRequest;
import c13.nim5.ez8.h5_proto.signaling.UpdateSignalingStatusResponse;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class IllIIlIIII1 extends lIllIlIll1.llllIIIIll1 {

    /* renamed from: lIIlIIIIlIlII1, reason: collision with root package name */
    public boolean f473lIIlIIIIlIlII1;

    /* renamed from: lIlllIIIII1, reason: collision with root package name */
    public llIIIIlIlllIII1.IllIIlIIII1 f474lIlllIIIII1;

    public class lIIIIlllllIlll1 implements Runnable {

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public final /* synthetic */ String[] f475lIIIIlllllIlll1;

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ UpdateSignalingStatusRequest.Status f476llllIIIIll1;

        public lIIIIlllllIlll1(UpdateSignalingStatusRequest.Status status, String[] strArr) {
            this.f476llllIIIIll1 = status;
            this.f475lIIIIlllllIlll1 = strArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f476llllIIIIll1 == UpdateSignalingStatusRequest.Status.IN_LANDING) {
                this.f475lIIIIlllllIlll1[0] = IllIIlIIII1.this.f508llllIIIIll1.getUrl() != null ? IllIIlIIII1.this.f508llllIIIIll1.getUrl() : "";
            } else {
                this.f475lIIIIlllllIlll1[0] = "";
            }
        }
    }

    public class llllIIIIll1 implements Runnable {
        public llllIIIIll1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            IllIIlIIII1.super.IlIlllIIlI1();
            try {
                llIIIIlIlllIII1.IllIIlIIII1 illIIlIIII1 = IllIIlIIII1.this.f474lIlllIIIII1;
                if (illIIlIIII1 != null) {
                    illIIlIIII1.IIIlIllIlI1();
                }
            } catch (Throwable th) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{16, 33, -48, -109, -75, 103, -36, 26, 17, 54, -47, -120, -75, 58, -97, 26}, new byte[]{116, 68, -93, -25, -57, 8, -91, 58}) + th);
            }
        }
    }

    public class llllIllIl1 implements lIIIIlllllIlll1.llllllIlIIIlll1 {

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ UpdateSignalingStatusRequest.Status f480llllIIIIll1;

        public llllIllIl1(UpdateSignalingStatusRequest.Status status) {
            this.f480llllIIIIll1 = status;
        }

        @Override // IlIlIIlIII1.lIIIIlllllIlll1.llllllIlIIIlll1
        public void llllIIIIll1(UpdateSignalingStatusResponse updateSignalingStatusResponse, Exception exc) {
            if (exc != null) {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(exc, new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{25, -81, -78, -72, -69, -106, -24, -65, 11, -79, -73, -75, -90, -99, -36, -123, 24, -66, -94, -84, -68, -45, -34, -92, 30, -80, -92, -29, -17}, new byte[]{108, -33, -42, -39, -49, -13, -69, -42}))));
            } else {
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{89, -116, -109, 57, -113, 24, -14, 78, 75, -110, -106, 52, -110, 19, -58, 116, 88, -99, -125, 45, -120, 93, -46, 82, 79, -97, -110, 43, -120}, new byte[]{44, -4, -9, 88, -5, 125, -95, 39}));
            }
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{65, 27, 9, -10, 95, 30, 95, 64, 83, 5, 12, -5, 66, 21, 107, 122, 64, 10, 25, -30, 88, 91, 99, 71, 119, 4, 0, -25, 71, 30, 120, 76, 80}, new byte[]{52, 107, 109, -105, 43, 123, 12, 41}));
            if (this.f480llllIIIIll1 == UpdateSignalingStatusRequest.Status.IN_LANDING) {
                IllIIlIIII1.this.f473lIIlIIIIlIlII1 = true;
            }
        }
    }

    public IllIIlIIII1(WebView webView, JSONObject jSONObject, String str) throws JSONException {
        super(webView, jSONObject, str);
        this.f473lIIlIIIIlIlII1 = false;
    }

    @Override // lIllIlIll1.llllIIIIll1
    public boolean IlIlIIlIII1() {
        return !this.f473lIIlIIIIlIlII1;
    }

    @Override // lIllIlIll1.llllIIIIll1
    public void IlIlllIIlI1() {
        IlIlllIIlI1.lIIIIlllllIlll1.llllIIIIll1(new llllIIIIll1());
    }

    @Override // lIllIlIll1.llllIIIIll1
    public boolean lIIIIlllllIlll1() {
        return IlIlllIIlI1.lIIIIlllllIlll1.IlIllIlllIllI1().IIlIllIIll1();
    }

    @Override // lIllIlIll1.llllIIIIll1
    public void llllIIIIll1() {
        this.f474lIlllIIIII1 = new llIIIIlIlllIII1.IllIIlIIII1(UUID.randomUUID().toString(), this);
        IIlIllIIll1.llllIIIIll1(500L);
    }

    @Override // lIllIlIll1.llllIIIIll1
    public Bitmap llllIIIIll1(WebView webView) {
        llIIIIlIlllIII1.IllIIlIIII1 illIIlIIII1 = this.f474lIlllIIIII1;
        if (illIIlIIII1 != null) {
            return illIIlIIII1.llllIIIIll1(1000L);
        }
        return null;
    }

    @Override // lIllIlIll1.llllIIIIll1
    public void llllIIIIll1(int i) {
        try {
            UpdateSignalingStatusRequest.Status fromValue = UpdateSignalingStatusRequest.Status.fromValue(i);
            if (this.f473lIIlIIIIlIlII1 && fromValue == UpdateSignalingStatusRequest.Status.IN_LANDING) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            String sb2 = sb.append(lllliiiill1.llllIIIIll1(new byte[]{91, -42, 38, 125, -78, 105, 105, -4, 73, -56, 35, 112, -107, 120, 91, -31, 91, -43, 120, 60}, new byte[]{46, -90, 66, 28, -58, 12, 58, -107})).append(i).toString();
            Log.LogLevel logLevel = Log.LogLevel.INFO;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, "", sb2);
            String[] strArr = new String[1];
            IlIlllIIlI1.lIIIIlllllIlll1.lIIIIlllllIlll1(new lIIIIlllllIlll1(fromValue, strArr));
            String str = strArr[0];
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, "", lllliiiill1.llllIIIIll1(new byte[]{-56, -41, 4, 68, 31, -58, 14, -79, -38, -55, 1, 73, 2, -51, 58, -117, -55, -58, 20, 80, 24, -103, 125}, new byte[]{-67, -89, 96, 37, 107, -93, 93, -40}) + i + lllliiiill1.llllIIIIll1(new byte[]{-20, 64, -116, 1, -41, -60, 101}, new byte[]{-64, 96, -7, 115, -69, -2, 69, -112}) + str);
            IlIlIIlIII1.lIIIIlllllIlll1 IlIlllIIlI12 = IlIlllIIlI1.lIIIIlllllIlll1.f248llllIIIIll1.IlIlllIIlI1();
            if (IlIlllIIlI12 != null) {
                IlIlllIIlI12.llllIIIIll1(IlIllIlllIllI1(), fromValue, str, new llllIllIl1(fromValue));
            }
        } catch (Exception e) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{29, 83, -32, -7, -91, 28, -90, -122, 15, 77, -27, -12, -72, 23, -110, -68, 28, 66, -16, -19, -94, 89, -112, -99, 26, 76, -10, -71, -16, 67, -43}, new byte[]{104, 35, -124, -104, -47, 121, -11, -17}))));
        }
    }
}

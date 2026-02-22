package lIllIlIll1;

import IlIlIIIlIlIlll1.IIlIllIIll1;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.webkit.WebView;
import c13.nim5.ez8.h5_proto.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class llllIllIl1 extends lIllIlIll1.llllIIIIll1 {

    /* renamed from: lIIlIIIIlIlII1, reason: collision with root package name */
    public final Runnable f524lIIlIIIIlIlII1;

    /* renamed from: lIlllIIIII1, reason: collision with root package name */
    public IIIlIllIlI1.IllIIlIIII1 f525lIlllIIIII1;

    public class llllIIIIll1 implements IIIlIllIlI1.IlIlllIIlI1 {
        public llllIIIIll1() {
        }

        @Override // IIIlIllIlI1.IlIlllIIlI1
        public void llllIIIIll1(Bitmap bitmap) {
        }
    }

    public llllIllIl1(WebView webView, JSONObject jSONObject, String str, Runnable runnable) throws JSONException {
        super(webView, jSONObject, str);
        this.f524lIIlIIIIlIlII1 = runnable;
    }

    @Override // lIllIlIll1.llllIIIIll1
    public boolean IlIlIIlIII1() {
        return true;
    }

    @Override // lIllIlIll1.llllIIIIll1
    public void IlIlllIIlI1() {
        super.IlIlllIIlI1();
        try {
            this.f525lIlllIIIII1.lIIIIlllllIlll1();
        } catch (Exception e) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{1, 122, 60, 82, -20, 37, -73, 81, 0, 109, 61, 73, -20, 120, -12, 81}, new byte[]{101, 31, 79, 38, -98, 74, -50, 113}) + e);
        }
        SystemClock.sleep(1000L);
        this.f524lIIlIIIIlIlII1.run();
    }

    @Override // lIllIlIll1.llllIIIIll1
    public boolean lIIIIlllllIlll1() {
        return false;
    }

    @Override // lIllIlIll1.llllIIIIll1
    public void llllIIIIll1() {
        IIIlIllIlI1.IllIIlIIII1 illIIlIIII1 = new IIIlIllIlI1.IllIIlIIII1(IlIlllIIlI1.lIIIIlllllIlll1.llllIllIl1(), 1.0f, new llllIIIIll1());
        this.f525lIlllIIIII1 = illIIlIIII1;
        illIIlIIII1.llllIIIIll1(IlIlllIIlI1.lIIIIlllllIlll1.f248llllIIIIll1.IllIIlIIII1(), this.f508llllIIIIll1);
        IIlIllIIll1.llllIIIIll1(500L);
    }

    @Override // lIllIlIll1.llllIIIIll1
    public void llllIIIIll1(int i) {
    }

    @Override // lIllIlIll1.llllIIIIll1
    public Bitmap llllIIIIll1(WebView webView) {
        return this.f525lIlllIIIII1.llllIIIIll1();
    }
}

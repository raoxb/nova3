package lIllIIIlIl1;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class lIIIIlllllIlll1 implements IlIllll1 {

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public final llllIIIIll1 f420IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final String f421lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final String f422llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final String f423llllIllIl1;

    public lIIIIlllllIlll1(String str, String str2, String str3, llllIIIIll1 lllliiiill1) {
        this.f422llllIIIIll1 = str;
        this.f421lIIIIlllllIlll1 = str2;
        this.f423llllIllIl1 = str3 == null ? "" : str3;
        this.f420IllIIlIIII1 = lllliiiill1;
    }

    public String IllIIlIIII1() {
        return this.f423llllIllIl1;
    }

    public llllIIIIll1 lIIIIlllllIlll1() {
        return this.f420IllIIlIIII1;
    }

    public String llllIIIIll1() {
        return this.f422llllIIIIll1;
    }

    public String llllIllIl1() {
        return this.f421lIIIIlllllIlll1;
    }

    @Override // lIllIIIlIl1.IlIllll1
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{117, -100, 63, 55, 94, -107}, new byte[]{20, -20, 79, 104, 55, -15, -81, -101}), this.f422llllIIIIll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{97, 106, -106, -95, 60, -72, 45, 13, 97}, new byte[]{5, 15, -32, -56, 95, -35, 114, 100}), this.f421lIIIIlllllIlll1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-102, -105, -48, -46, 93}, new byte[]{-18, -8, -69, -73, 51, -113, -3, 78}), this.f423llllIllIl1);
        jSONObject.put(lllliiiill1.llllIIIIll1(new byte[]{-103, -101, -33, 26}, new byte[]{-8, -17, -80, 119, 82, -32, -100, -115}), this.f420IllIIlIIII1.toJSONObject());
        return jSONObject;
    }

    public static lIIIIlllllIlll1 llllIIIIll1(JSONObject jSONObject) throws JSONException {
        byte[] bArr = {115, -112, -19, -20, 95, ByteCompanionObject.MIN_VALUE, -102, 13};
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        return new lIIIIlllllIlll1(jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{18, -32, -99, -77, 54, -28}, bArr), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{-61, 52, -54, -118, -61, -63, -26, -43, -61}, new byte[]{-89, 81, -68, -29, -96, -92, -71, -68}), ""), jSONObject.optString(lllliiiill1.llllIIIIll1(new byte[]{37, -59, 55, -85, 84}, new byte[]{81, -86, 92, -50, 58, -126, -87, -6}), ""), llllIIIIll1.llllIIIIll1(jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-101, -14, 28, 98}, new byte[]{-6, -122, 115, 15, 68, 72, 46, -107})) != null ? jSONObject.optJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-10, 29, -54, 54}, new byte[]{-105, 105, -91, 91, 15, -85, -16, -58})) : new JSONObject()));
    }

    public static llllIIIIll1 llllIIIIll1(Context context, String str, String str2) {
        String str3;
        IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.getClass();
        byte[] lIIIIlllllIlll12 = IllIIlIIII1.llllIIIIll1.lIIIIlllllIlll1(new byte[]{70, 72, -74, 59, -8, 113}, new byte[]{49, 33, -40, 95, -105, 6, 85, 32});
        Charset charset = StandardCharsets.UTF_8;
        WindowManager windowManager = (WindowManager) context.getSystemService(new String(lIIIIlllllIlll12, charset));
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String locale = context.getResources().getConfiguration().getLocales().get(0).toString();
            int i = context.getResources().getConfiguration().orientation;
            if (i == 1) {
                str3 = new String(IllIIlIIII1.llllIIIIll1.lIIIIlllllIlll1(new byte[]{-93, -2, 30, -78, 60, -68, 1, -87}, new byte[]{-45, -111, 108, -58, 78, -35, 104, -35}), charset);
            } else if (i != 2) {
                str3 = new String(IllIIlIIII1.llllIIIIll1.lIIIIlllllIlll1(new byte[]{-33, -107, 118, -17, 72, -121, 77}, new byte[]{-86, -5, 29, -127, 39, -16, 35, 77}), charset);
            } else {
                str3 = new String(IllIIlIIII1.llllIIIIll1.lIIIIlllllIlll1(new byte[]{-85, -22, -48, 118, 67, 16, 2, -43, -94}, new byte[]{-57, -117, -66, 18, 48, 115, 99, -91}), charset);
            }
            String str4 = str3;
            String str5 = packageInfo.versionName;
            return new llllIIIIll1(str2, str, str5 != null ? str5 : "", UUID.randomUUID().toString().replace(new String(IllIIlIIII1.llllIIIIll1.lIIIIlllllIlll1(new byte[]{106}, new byte[]{71, -22, -6, 21, 103, 48, 51, 108}), charset), ""), new String(IllIIlIIII1.llllIIIIll1.lIIIIlllllIlll1(new byte[]{45, 43}, new byte[]{89, 72, 23, 41, 73, -71, -62, 79}), charset), TimeZone.getDefault().getID(), locale, Build.MODEL, Build.BRAND, displayMetrics.widthPixels + new String(IllIIlIIII1.llllIIIIll1.lIIIIlllllIlll1(new byte[]{-37}, new byte[]{-15, -77, 118, 102, -110, -23, -49, 103}), charset) + displayMetrics.heightPixels, displayMetrics.densityDpi + new String(IllIIlIIII1.llllIIIIll1.lIIIIlllllIlll1(new byte[]{102, 24, -50}, new byte[]{2, 104, -89, 25, 88, 113, -67, -116}), charset), str4, Build.VERSION.RELEASE, System.currentTimeMillis(), IlIlIIIlIlIlll1.IlIlIIlIII1.llllIIIIll1(IlIlllIIlI1.lIIIIlllllIlll1.llllIllIl1()));
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

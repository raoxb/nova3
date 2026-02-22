package IlIlIIlIII1;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.ByteCompanionObject;
import lIllIIIlIl1.IlIllll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class llllIIIIll1 {

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public static final String f215IlIlllIIlI1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public static final String f216IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final int f217lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final String f218llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final int f219llllIllIl1;

    public static class lIIIIlllllIlll1 {

        /* renamed from: IllIIlIIII1, reason: collision with root package name */
        public final Map<String, List<String>> f220IllIIlIIII1;

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public final String f221lIIIIlllllIlll1;

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final int f222llllIIIIll1;

        /* renamed from: llllIllIl1, reason: collision with root package name */
        public final String f223llllIllIl1;

        public lIIIIlllllIlll1(int i, String str, String str2, Map<String, List<String>> map) {
            this.f222llllIIIIll1 = i;
            this.f221lIIIIlllllIlll1 = str;
            this.f223llllIllIl1 = str2;
            this.f220IllIIlIIII1 = map;
        }

        public boolean IlIlllIIlI1() {
            int i = this.f222llllIIIIll1;
            return i >= 200 && i <= 299;
        }

        public String IllIIlIIII1() {
            return this.f221lIIIIlllllIlll1;
        }

        public int lIIIIlllllIlll1() {
            return this.f222llllIIIIll1;
        }

        public String llllIIIIll1() {
            return this.f223llllIllIl1;
        }

        public Map<String, List<String>> llllIllIl1() {
            return this.f220IllIIlIIII1;
        }
    }

    static {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        f216IllIIlIIII1 = lllliiiill1.llllIIIIll1(new byte[]{-79, -31, -56, 121, 31, 72, 41, -64, -105, -31}, new byte[]{-7, -107, -68, 9, 92, 36, 64, -91});
        f215IlIlllIIlI1 = lllliiiill1.llllIIIIll1(new byte[]{-7, 108, -98, -96, -51, 88, 96, -45, -15, 115, ByteCompanionObject.MIN_VALUE, -29, -50, 72, 110, -55, -93, 60, -115, -92, -59, 73, 114, -62, -20, 33, -101, -72, -62, 22, 57}, new byte[]{-104, 28, -18, -52, -92, 59, 1, -89});
    }

    public llllIIIIll1(String str) {
        this.f218llllIIIIll1 = str;
        this.f217lIIIIlllllIlll1 = 10000;
        this.f219llllIllIl1 = 30000;
    }

    public <T extends IlIllll1> lIIIIlllllIlll1 llllIIIIll1(String str, T t, Map<String, String> map) throws JSONException {
        String jSONObject = t.toJSONObject().toString();
        HashMap hashMap = new HashMap(map);
        byte[] bArr = {-33, 114, ByteCompanionObject.MIN_VALUE, -47, 19, 118, -101, 110};
        hashMap.put(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-100, 29, -18, -91, 118, 24, -17, 67, -117, 11, -16, -76}, bArr), f215IlIlllIIlI1);
        return llllIIIIll1(str, jSONObject, hashMap);
    }

    /* renamed from: IlIlIIlIII1.llllIIIIll1$llllIIIIll1, reason: collision with other inner class name */
    public static class C0006llllIIIIll1 extends RuntimeException {

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public final String f224lIIIIlllllIlll1;

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final int f225llllIIIIll1;

        public C0006llllIIIIll1(String str, int i, String str2) {
            super(str, null);
            this.f225llllIIIIll1 = i;
            this.f224lIIIIlllllIlll1 = str2;
        }

        public int lIIIIlllllIlll1() {
            return this.f225llllIIIIll1;
        }

        public String llllIIIIll1() {
            return this.f224lIIIIlllllIlll1;
        }

        public C0006llllIIIIll1(String str, int i, String str2, Throwable th) {
            super(str, th);
            this.f225llllIIIIll1 = i;
            this.f224lIIIIlllllIlll1 = str2;
        }
    }

    public llllIIIIll1(String str, int i, int i2) {
        this.f218llllIIIIll1 = str;
        this.f217lIIIIlllllIlll1 = i;
        this.f219llllIllIl1 = i2;
    }

    public lIIIIlllllIlll1 llllIIIIll1(String str) {
        return llllIIIIll1(str, new HashMap());
    }

    public lIIIIlllllIlll1 llllIIIIll1(String str, Map<String, String> map) {
        return llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-117, -34, -58}, new byte[]{-52, -101, -110, 31, 102, 25, -123, -85}), str, (String) null, map);
    }

    public lIIIIlllllIlll1 llllIIIIll1(String str, String str2) {
        return llllIIIIll1(str, str2, new HashMap());
    }

    public lIIIIlllllIlll1 llllIIIIll1(String str, String str2, Map<String, String> map) {
        return llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{77, 36, 37, -123}, new byte[]{29, 107, 118, -47, 27, 81, -68, 8}), str, str2, map);
    }

    public <T extends IlIllll1> lIIIIlllllIlll1 llllIIIIll1(String str, T t) throws JSONException {
        return llllIIIIll1(str, (String) t, (Map<String, String>) new HashMap());
    }

    public <REQ extends IlIllll1, RESP extends IlIllll1> RESP llllIIIIll1(String str, REQ req, Class<RESP> cls) throws JSONException {
        return (RESP) llllIIIIll1(str, (String) req, (Class) cls, (Map<String, String>) new HashMap());
    }

    public <REQ extends IlIllll1, RESP extends IlIllll1> RESP llllIIIIll1(String str, REQ req, Class<RESP> cls, Map<String, String> map) throws JSONException {
        lIIIIlllllIlll1 llllIIIIll12 = llllIIIIll1(str, (String) req, map);
        if (llllIIIIll12.IlIlllIIlI1()) {
            if (llllIIIIll12.llllIIIIll1() != null && !llllIIIIll12.llllIIIIll1().isEmpty()) {
                try {
                    return (RESP) IlIllll1.llllIIIIll1(new JSONObject(llllIIIIll12.llllIIIIll1()), cls);
                } catch (Exception e) {
                    String str2 = f216IllIIlIIII1;
                    IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                    Log.e(str2, lllliiiill1.llllIIIIll1(new byte[]{103, -89, 6, 40, 23, 21, 107, 79, 78, -26, 31, 37, 0, 2, 46, 27, 83, -93, 28, 52, 29, 31, 56, 94, 1, -116, 60, 11, 60}, new byte[]{33, -58, 111, 68, 114, 113, 75, 59}), e);
                    throw new C0006llllIIIIll1(llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(lllliiiill1.llllIIIIll1(new byte[]{32, 109, -34, 111, 70, 50, -125, -111, 9, 44, -57, 98, 81, 37, -58, -59, 20, 105, -60, 115, 76, 56, -48, ByteCompanionObject.MIN_VALUE, 92, 44}, new byte[]{102, 12, -73, 3, 35, 86, -93, -27}))), llllIIIIll12.lIIIIlllllIlll1(), llllIIIIll12.llllIIIIll1());
                }
            }
            throw new C0006llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{60, 45, 61, 103, 80, 98, 124, -93, 10, 48, 34, 125, 90, 39, 46, -92, 22, 36, 52}, new byte[]{121, 64, 77, 19, 41, 66, 14, -58}), llllIIIIll12.lIIIIlllllIlll1(), null);
        }
        throw new C0006llllIIIIll1(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{5, -86, -123, -79, 27, -15, 15, -70, 56, -101, -94, -107, 27, -27, 11, -94, 33, -101, -75, -37, 27}, new byte[]{77, -2, -47, -31, 59, -125, 106, -53}) + llllIIIIll12.lIIIIlllllIlll1() + " " + llllIIIIll12.IllIIlIIII1(), llllIIIIll12.lIIIIlllllIlll1(), llllIIIIll12.llllIIIIll1());
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x01b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final IlIlIIlIII1.llllIIIIll1.lIIIIlllllIlll1 llllIIIIll1(java.lang.String r9, java.lang.String r10, java.lang.String r11, java.util.Map<java.lang.String, java.lang.String> r12) {
        /*
            Method dump skipped, instructions count: 588
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: IlIlIIlIII1.llllIIIIll1.llllIIIIll1(java.lang.String, java.lang.String, java.lang.String, java.util.Map):IlIlIIlIII1.llllIIIIll1$lIIIIlllllIlll1");
    }

    public final String llllIIIIll1(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                sb.append(new String(bArr, 0, read, StandardCharsets.UTF_8));
            } else {
                return sb.toString();
            }
        }
    }
}

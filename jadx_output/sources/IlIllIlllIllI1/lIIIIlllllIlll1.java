package IlIllIlllIllI1;

import IlIlIIIlIlIlll1.IIlIllIIll1;
import IlIlllIIlI1.IllIIlIIII1;
import android.util.Base64;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.jvm.internal.ByteCompanionObject;
import lIllIIIlIl1.IlIllll1;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class lIIIIlllllIlll1 {

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public static final int f232lIIIIlllllIlll1 = 15000;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public static final int f234llllIllIl1 = 15000;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public static final String f233llllIIIIll1 = IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-36, -70, -109, -58, -79, 66, 60, -97, -6, -70}, new byte[]{-108, -50, -25, -74, -14, 46, 85, -6});

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public static final ExecutorService f231IllIIlIIII1 = Executors.newCachedThreadPool();

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public static byte[] f230IlIlllIIlI1 = null;

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'llllIIIIll1' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:293)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:266)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* renamed from: IlIllIlllIllI1.lIIIIlllllIlll1$lIIIIlllllIlll1, reason: collision with other inner class name */
    public static final class EnumC0007lIIIIlllllIlll1 {

        /* renamed from: IlIlllIIlI1, reason: collision with root package name */
        public static final /* synthetic */ EnumC0007lIIIIlllllIlll1[] f235IlIlllIIlI1;

        /* renamed from: IllIIlIIII1, reason: collision with root package name */
        public static final EnumC0007lIIIIlllllIlll1 f236IllIIlIIII1;

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public static final EnumC0007lIIIIlllllIlll1 f237lIIIIlllllIlll1;

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public static final EnumC0007lIIIIlllllIlll1 f238llllIIIIll1;

        /* renamed from: llllIllIl1, reason: collision with root package name */
        public static final EnumC0007lIIIIlllllIlll1 f239llllIllIl1;

        static {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
            f238llllIIIIll1 = new EnumC0007lIIIIlllllIlll1(lllliiiill1.llllIIIIll1(new byte[]{-54, -126, 47}, new byte[]{-115, -57, 123, -65, -44, -58, -112, -109}), 0);
            f237lIIIIlllllIlll1 = new EnumC0007lIIIIlllllIlll1(lllliiiill1.llllIIIIll1(new byte[]{-77, 54, 56, 125}, new byte[]{-29, 121, 107, 41, 94, 18, 54, -31}), 1);
            f239llllIllIl1 = new EnumC0007lIIIIlllllIlll1(lllliiiill1.llllIIIIll1(new byte[]{-58, -48, 37}, new byte[]{-106, -123, 113, 42, 7, 82, -42, 74}), 2);
            f236IllIIlIIII1 = new EnumC0007lIIIIlllllIlll1(lllliiiill1.llllIIIIll1(new byte[]{72, 61, -65, 92, -78, 83}, new byte[]{12, 120, -13, 25, -26, 22, 78, 84}), 3);
            f235IlIlllIIlI1 = llllIIIIll1();
        }

        public EnumC0007lIIIIlllllIlll1(String str, int i) {
        }

        public static /* synthetic */ EnumC0007lIIIIlllllIlll1[] llllIIIIll1() {
            return new EnumC0007lIIIIlllllIlll1[]{f238llllIIIIll1, f237lIIIIlllllIlll1, f239llllIllIl1, f236IllIIlIIII1};
        }

        public static EnumC0007lIIIIlllllIlll1 valueOf(String str) {
            return (EnumC0007lIIIIlllllIlll1) Enum.valueOf(EnumC0007lIIIIlllllIlll1.class, str);
        }

        public static EnumC0007lIIIIlllllIlll1[] values() {
            return (EnumC0007lIIIIlllllIlll1[]) f235IlIlllIIlI1.clone();
        }
    }

    public interface llllIllIl1<T> {
        T llllIIIIll1(JSONObject jSONObject) throws JSONException;
    }

    public static String lIIIIlllllIlll1(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    bufferedReader.close();
                    return sb.toString();
                }
                sb.append(readLine);
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    public static void llllIIIIll1(String str) {
        f230IlIlllIIlI1 = Base64.decode(str, 2);
    }

    public static <T extends IlIllll1, R extends IlIllll1> T llllIIIIll1(String str, EnumC0007lIIIIlllllIlll1 enumC0007lIIIIlllllIlll1, R r, llllIllIl1<T> llllillil1) throws llllIIIIll1 {
        return (T) llllIIIIll1(str, enumC0007lIIIIlllllIlll1, r, llllillil1, null);
    }

    public static class llllIIIIll1 extends Exception {

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public final String f240lIIIIlllllIlll1;

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final int f241llllIIIIll1;

        public llllIIIIll1(String str) {
            super(str);
            this.f241llllIIIIll1 = 0;
            this.f240lIIIIlllllIlll1 = "";
        }

        public llllIIIIll1(String str, Throwable th) {
            super(str, th);
            this.f241llllIIIIll1 = 0;
            this.f240lIIIIlllllIlll1 = "";
        }

        public llllIIIIll1(String str, int i, String str2) {
            super(str);
            this.f241llllIIIIll1 = i;
            this.f240lIIIIlllllIlll1 = str2;
        }
    }

    public static <T extends IlIllll1, R extends IlIllll1> T llllIIIIll1(String str, EnumC0007lIIIIlllllIlll1 enumC0007lIIIIlllllIlll1, R r, llllIllIl1<T> llllillil1, Map<String, String> map) throws llllIIIIll1 {
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2 = null;
        try {
            try {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        } catch (Exception e2) {
            e = e2;
        }
        try {
            httpURLConnection.setRequestMethod(enumC0007lIIIIlllllIlll1.name());
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(15000);
            llllIIIIll1(httpURLConnection, map);
            if (r != null && (enumC0007lIIIIlllllIlll1 == EnumC0007lIIIIlllllIlll1.f237lIIIIlllllIlll1 || enumC0007lIIIIlllllIlll1 == EnumC0007lIIIIlllllIlll1.f239llllIllIl1)) {
                httpURLConnection.setDoOutput(true);
                String jSONObject = r.toJSONObject().toString();
                String str2 = f233llllIIIIll1;
                lllllIllIl1.IllIIlIIII1.llllIllIl1(str2, IllIIlIIII1.llllIIIIll1(new byte[]{-48, -75, -27, -89, 14, 95, 53}, new byte[]{-117, -64, -105, -53, 83, 101, 21, -62}) + str + IllIIlIIII1.llllIIIIll1(new byte[]{-119, -97, -39, 51, -125, -77, -115, 56, -35, -103, -111, 118}, new byte[]{-87, -60, -85, 86, -14, -58, -24, 75}) + jSONObject);
                if (f230IlIlllIIlI1 != null) {
                    jSONObject = new String(Base64.encode(IlIlIIIlIlIlll1.lIIIIlllllIlll1.lIIIIlllllIlll1(jSONObject.getBytes(StandardCharsets.UTF_8), f230IlIlllIIlI1), 2));
                }
                lllllIllIl1.IllIIlIIII1.llllIllIl1(str2, IllIIlIIII1.llllIIIIll1(new byte[]{-76, -82, 46, 88, 72, -43, -11}, new byte[]{-17, -37, 92, 52, 21, -17, -43, -4}) + str + IllIIlIIII1.llllIIIIll1(new byte[]{60, -89, -123, -45, 76, 63, -61, 85, 104, -95, -51, -106}, new byte[]{28, -4, -9, -74, 61, 74, -90, 38}) + jSONObject);
                llllIIIIll1(httpURLConnection, jSONObject);
            }
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode >= 200 && responseCode <= 299) {
                String lIIIIlllllIlll12 = lIIIIlllllIlll1(httpURLConnection.getInputStream());
                lllllIllIl1.IllIIlIIII1.llllIllIl1(IllIIlIIII1.llllIIIIll1(new byte[]{-29, -48, -62, -127, -19, 101, 117, 26, -85, -107}, new byte[]{-111, -75, -79, -15, -126, 11, 6, ByteCompanionObject.MAX_VALUE}) + lIIIIlllllIlll12);
                if (f230IlIlllIIlI1 != null) {
                    String str3 = new String(IlIlIIIlIlIlll1.lIIIIlllllIlll1.llllIIIIll1(Base64.decode(lIIIIlllllIlll12, 2), f230IlIlllIIlI1));
                    lllllIllIl1.IllIIlIIII1.llllIllIl1(f233llllIIIIll1, IllIIlIIII1.llllIIIIll1(new byte[]{-70, -89, -53, 87, 18, -49, 98}, new byte[]{-31, -46, -71, 59, 79, -11, 66, 31}) + str + IllIIlIIII1.llllIIIIll1(new byte[]{-80, -21, -103, -108, -84, 98, -76, -80, -29, -43, -74, -53, -1}, new byte[]{-112, -80, -21, -15, -33, 18, -37, -34}) + str3);
                    T llllIIIIll12 = llllillil1.llllIIIIll1(new JSONObject(str3));
                    httpURLConnection.disconnect();
                    return llllIIIIll12;
                }
                T llllIIIIll13 = llllillil1.llllIIIIll1(new JSONObject(lIIIIlllllIlll12));
                httpURLConnection.disconnect();
                return llllIIIIll13;
            }
            throw new llllIIIIll1(IllIIlIIII1.llllIIIIll1(new byte[]{-26, -27, -68, -7, 123, -85, -37, 67, 1, 30, 7, 21, 8}, new byte[]{-82, -79, -24, -87, -110, 63, 66, -85}) + responseCode, responseCode, lIIIIlllllIlll1(httpURLConnection.getErrorStream()));
        } catch (IOException e3) {
            e = e3;
            throw new llllIIIIll1(IllIIlIIII1.llllIIIIll1(new byte[]{28, 112, -101, -125, 19, -41, -107, -17, 98, 37, -91, -53, -110, 107}, new byte[]{-5, -51, 10, 100, -88, 75, 124, 123}) + e.getMessage(), e);
        } catch (Exception e4) {
            e = e4;
            throw new llllIIIIll1(IllIIlIIII1.llllIIIIll1(new byte[]{57, 92, -78, 110, -84, -124, -97, -63, -2, -83, 20, -76, -35, -47, -100, -120, 73, 47}, new byte[]{115, 15, -3, 32, 68, 57, 51, 39}) + e.getMessage(), e);
        } catch (Throwable th2) {
            th = th2;
            httpURLConnection2 = httpURLConnection;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th;
        }
    }

    public static void llllIIIIll1(HttpURLConnection httpURLConnection, Map<String, String> map) {
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        httpURLConnection.setRequestProperty(lllliiiill1.llllIIIIll1(new byte[]{123, -35, 90, -127, 68, -6, 42, -124, 108, -53, 68, -112}, new byte[]{56, -78, 52, -11, 33, -108, 94, -87}), lllliiiill1.llllIIIIll1(new byte[]{63, -6, -83, 105, -116, -93, 75, -94, 55, -27, -77, 42, -113, -77, 69, -72, 101, -86, -66, 109, -124, -78, 89, -77, 42, -73, -88, 113, -125, -19, 18}, new byte[]{94, -118, -35, 5, -27, -64, 42, -42}));
        httpURLConnection.setRequestProperty(lllliiiill1.llllIIIIll1(new byte[]{29, -121, -11, 122, -71, 5}, new byte[]{92, -28, -106, 31, -55, 113, -97, -124}), lllliiiill1.llllIIIIll1(new byte[]{-117, 80, 87, -1, -51, 15, -75, 31, -125, 79, 73, -68, -50, 31, -69, 5}, new byte[]{-22, 32, 39, -109, -92, 108, -44, 107}));
        httpURLConnection.setRequestProperty(lllliiiill1.llllIIIIll1(new byte[]{-14, -52, 67, 79, 4, 92, 106, -81, -55, -53}, new byte[]{-89, -65, 38, 61, 41, 29, 13, -54}), IIlIllIIll1.IlIlIIlIII1());
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
    }

    public static void llllIIIIll1(HttpURLConnection httpURLConnection, String str) throws IOException {
        OutputStream outputStream = httpURLConnection.getOutputStream();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            try {
                bufferedWriter.write(str);
                bufferedWriter.flush();
                bufferedWriter.close();
                if (outputStream != null) {
                    outputStream.close();
                }
            } finally {
            }
        } catch (Throwable th) {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static byte[] llllIIIIll1(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[1048576];
        byte[] bArr2 = null;
        while (true) {
            int read = inputStream.read(bArr);
            if (read <= 0) {
                break;
            }
            if (bArr2 == null) {
                bArr2 = new byte[read];
                System.arraycopy(bArr, 0, bArr2, 0, read);
            } else {
                int length = bArr2.length;
                byte[] bArr3 = new byte[length + read];
                System.arraycopy(bArr2, 0, bArr3, 0, length);
                System.arraycopy(bArr, 0, bArr3, length, read);
                bArr2 = bArr3;
            }
        }
        return bArr2 != null ? bArr2 : new byte[0];
    }
}

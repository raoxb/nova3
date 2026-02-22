package c13.nim5.ez8.h5_proto;

import IlIlIIIlIlIlll1.IIlIllIIll1;
import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class HttpGatewayClient {
    private static final String AES_KEY;
    private static final String CONTENT_TYPE;
    private static final String USER_AGENT;
    private final String baseUrl;

    static {
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        AES_KEY = lllliiiill1.llllIIIIll1(new byte[]{-13, 111, 91, -7, 30, 86, -86, 116}, new byte[]{-76, 29, 62, -100, 112, 18, -53, 13});
        CONTENT_TYPE = lllliiiill1.llllIIIIll1(new byte[]{-26, -117, 79, 93, 100, 56, -113, 37, -18, -108, 81, 30, 103, 40, -127, 63}, new byte[]{-121, -5, 63, 49, 13, 91, -18, 81});
        USER_AGENT = lllliiiill1.llllIIIIll1(new byte[]{-95, -105, -28, 31, 57, -65, 115, 8, -111, -98, -53, 3, 55, -66, 81, 21, -54, -55, -90, 95}, new byte[]{-27, -5, -120, 111, 94, -37, 63, 97});
    }

    public HttpGatewayClient(String str) {
        StringBuilder sb = new StringBuilder();
        this.baseUrl = llllIIIIll1.llllIIIIll1.llllIIIIll1(IllIIlIIII1.f243llllIIIIll1, new byte[]{29, 19, -98, -77, -106, 37, -78, 98}, new byte[]{117, 103, -22, -61, -27, 31, -99, 77}, sb, str);
    }

    private byte[] aesDecrypt(byte[] bArr) throws Exception {
        byte[] bArr2;
        String IlIllIlllIllI12 = IIlIllIIll1.IlIllIlllIllI1(AES_KEY);
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        byte[] bytes = IlIllIlllIllI12.getBytes(lllliiiill1.llllIIIIll1(new byte[]{-61, -59, -117, -101, -94}, new byte[]{-106, -111, -51, -74, -102, 61, 119, 69}));
        if (bytes.length <= 32) {
            if (bytes.length < 32) {
                bArr2 = new byte[32];
                System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, lllliiiill1.llllIIIIll1(new byte[]{-124, 92, -115}, new byte[]{-59, 25, -34, 66, -34, 83, 16, -84}));
            byte[] bArr3 = new byte[16];
            System.arraycopy(bArr, 0, bArr3, 0, 16);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
            int length = bArr.length - 16;
            byte[] bArr4 = new byte[length];
            System.arraycopy(bArr, 16, bArr4, 0, length);
            Cipher cipher = Cipher.getInstance(lllliiiill1.llllIIIIll1(new byte[]{-68, 8, 5, -25, -20, 77, 52, 87, -77, 34, 6, -87, -53, 111, 31, 22, -102}, new byte[]{-3, 77, 86, -56, -81, 11, 118, 120}));
            cipher.init(2, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(bArr4);
        }
        bArr2 = new byte[32];
        System.arraycopy(bytes, 0, bArr2, 0, 32);
        bytes = bArr2;
        SecretKeySpec secretKeySpec2 = new SecretKeySpec(bytes, lllliiiill1.llllIIIIll1(new byte[]{-124, 92, -115}, new byte[]{-59, 25, -34, 66, -34, 83, 16, -84}));
        byte[] bArr32 = new byte[16];
        System.arraycopy(bArr, 0, bArr32, 0, 16);
        IvParameterSpec ivParameterSpec2 = new IvParameterSpec(bArr32);
        int length2 = bArr.length - 16;
        byte[] bArr42 = new byte[length2];
        System.arraycopy(bArr, 16, bArr42, 0, length2);
        Cipher cipher2 = Cipher.getInstance(lllliiiill1.llllIIIIll1(new byte[]{-68, 8, 5, -25, -20, 77, 52, 87, -77, 34, 6, -87, -53, 111, 31, 22, -102}, new byte[]{-3, 77, 86, -56, -81, 11, 118, 120}));
        cipher2.init(2, secretKeySpec2, ivParameterSpec2);
        return cipher2.doFinal(bArr42);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String aesDecryptString(java.lang.String r10) throws java.lang.Exception {
        /*
            java.lang.String r0 = c13.nim5.ez8.h5_proto.HttpGatewayClient.AES_KEY
            java.lang.String r0 = IlIlIIIlIlIlll1.IIlIllIIll1.IlIllIlllIllI1(r0)
            r1 = 5
            byte[] r2 = new byte[r1]
            r2 = {x00a8: FILL_ARRAY_DATA , data: [118, -101, 63, -24, -27} // fill-array
            r3 = 8
            byte[] r4 = new byte[r3]
            r4 = {x00b0: FILL_ARRAY_DATA , data: [35, -49, 121, -59, -35, -97, 30, -126} // fill-array
            IllIIlIIII1.llllIIIIll1 r5 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1
            java.lang.String r2 = r5.llllIIIIll1(r2, r4)
            byte[] r0 = r0.getBytes(r2)
            int r2 = r0.length
            r4 = 0
            r6 = 32
            if (r2 <= r6) goto L2a
            byte[] r2 = new byte[r6]
            java.lang.System.arraycopy(r0, r4, r2, r4, r6)
        L28:
            r0 = r2
            goto L34
        L2a:
            int r2 = r0.length
            if (r2 >= r6) goto L34
            byte[] r2 = new byte[r6]
            int r6 = r0.length
            java.lang.System.arraycopy(r0, r4, r2, r4, r6)
            goto L28
        L34:
            javax.crypto.spec.SecretKeySpec r2 = new javax.crypto.spec.SecretKeySpec
            r6 = 3
            byte[] r6 = new byte[r6]
            r6 = {x00b8: FILL_ARRAY_DATA , data: [102, 34, -7} // fill-array
            byte[] r7 = new byte[r3]
            r7 = {x00be: FILL_ARRAY_DATA , data: [39, 103, -86, 86, -11, -16, 20, 37} // fill-array
            java.lang.String r6 = r5.llllIIIIll1(r6, r7)
            r2.<init>(r0, r6)
            r0 = 2
            byte[] r10 = android.util.Base64.decode(r10, r0)
            int r6 = r10.length
            r7 = 16
            if (r6 < r7) goto L92
            byte[] r6 = new byte[r7]
            java.lang.System.arraycopy(r10, r4, r6, r4, r7)
            javax.crypto.spec.IvParameterSpec r8 = new javax.crypto.spec.IvParameterSpec
            r8.<init>(r6)
            int r6 = r10.length
            int r6 = r6 - r7
            byte[] r9 = new byte[r6]
            java.lang.System.arraycopy(r10, r7, r9, r4, r6)
            r10 = 17
            byte[] r10 = new byte[r10]
            r10 = {x00c6: FILL_ARRAY_DATA , data: [-32, 82, -46, 60, -104, 30, 80, -59, -17, 120, -47, 114, -65, 60, 123, -124, -58} // fill-array
            byte[] r4 = new byte[r3]
            r4 = {x00d4: FILL_ARRAY_DATA , data: [-95, 23, -127, 19, -37, 88, 18, -22} // fill-array
            java.lang.String r10 = r5.llllIIIIll1(r10, r4)
            javax.crypto.Cipher r10 = javax.crypto.Cipher.getInstance(r10)
            r10.init(r0, r2, r8)
            byte[] r10 = r10.doFinal(r9)
            java.lang.String r0 = new java.lang.String
            byte[] r1 = new byte[r1]
            r1 = {x00dc: FILL_ARRAY_DATA , data: [82, -95, -6, -64, 72} // fill-array
            byte[] r2 = new byte[r3]
            r2 = {x00e4: FILL_ARRAY_DATA , data: [7, -11, -68, -19, 112, -100, 50, -28} // fill-array
            java.lang.String r1 = r5.llllIIIIll1(r1, r2)
            r0.<init>(r10, r1)
            return r0
        L92:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            r0 = 41
            byte[] r0 = new byte[r0]
            r0 = {x00ec: FILL_ARRAY_DATA , data: [-53, -104, -93, 113, -60, 7, -89, -71, -98, -12, -114, 58, -126, 20, -2, -55, -108, -76, -25, 44, -26, 105, -9, -97, -63, -82, -113, 114, -4, 33, -89, -97, -69, -12, -116, 4, -114, 14, -41, 101, 120} // fill-array
            byte[] r1 = new byte[r3]
            r1 = {x0106: FILL_ARRAY_DATA , data: [46, 18, 3, -108, 107, -127, 65, 44} // fill-array
            java.lang.String r0 = r5.llllIIIIll1(r0, r1)
            r10.<init>(r0)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: c13.nim5.ez8.h5_proto.HttpGatewayClient.aesDecryptString(java.lang.String):java.lang.String");
    }

    private byte[] aesEncrypt(byte[] bArr) throws Exception {
        byte[] bArr2;
        String IlIllIlllIllI12 = IIlIllIIll1.IlIllIlllIllI1(AES_KEY);
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        byte[] bytes = IlIllIlllIllI12.getBytes(lllliiiill1.llllIIIIll1(new byte[]{-46, -45, 109, 89, 41}, new byte[]{-121, -121, 43, 116, 17, 65, 14, 119}));
        if (bytes.length <= 32) {
            if (bytes.length < 32) {
                bArr2 = new byte[32];
                System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, lllliiiill1.llllIIIIll1(new byte[]{-64, 112, -111}, new byte[]{-127, 53, -62, -123, -120, -79, -96, 82}));
            byte[] bArr3 = new byte[16];
            new SecureRandom().nextBytes(bArr3);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
            Cipher cipher = Cipher.getInstance(lllliiiill1.llllIIIIll1(new byte[]{-16, 80, -17, -55, 37, 94, 73, 43, -1, 122, -20, -121, 2, 124, 98, 106, -42}, new byte[]{-79, 21, -68, -26, 102, 24, 11, 4}));
            cipher.init(1, secretKeySpec, ivParameterSpec);
            byte[] doFinal = cipher.doFinal(bArr);
            byte[] bArr4 = new byte[doFinal.length + 16];
            System.arraycopy(bArr3, 0, bArr4, 0, 16);
            System.arraycopy(doFinal, 0, bArr4, 16, doFinal.length);
            return bArr4;
        }
        bArr2 = new byte[32];
        System.arraycopy(bytes, 0, bArr2, 0, 32);
        bytes = bArr2;
        SecretKeySpec secretKeySpec2 = new SecretKeySpec(bytes, lllliiiill1.llllIIIIll1(new byte[]{-64, 112, -111}, new byte[]{-127, 53, -62, -123, -120, -79, -96, 82}));
        byte[] bArr32 = new byte[16];
        new SecureRandom().nextBytes(bArr32);
        IvParameterSpec ivParameterSpec2 = new IvParameterSpec(bArr32);
        Cipher cipher2 = Cipher.getInstance(lllliiiill1.llllIIIIll1(new byte[]{-16, 80, -17, -55, 37, 94, 73, 43, -1, 122, -20, -121, 2, 124, 98, 106, -42}, new byte[]{-79, 21, -68, -26, 102, 24, 11, 4}));
        cipher2.init(1, secretKeySpec2, ivParameterSpec2);
        byte[] doFinal2 = cipher2.doFinal(bArr);
        byte[] bArr42 = new byte[doFinal2.length + 16];
        System.arraycopy(bArr32, 0, bArr42, 0, 16);
        System.arraycopy(doFinal2, 0, bArr42, 16, doFinal2.length);
        return bArr42;
    }

    public static String aesEncryptString(String str) throws Exception {
        byte[] bArr;
        String IlIllIlllIllI12 = IIlIllIIll1.IlIllIlllIllI1(AES_KEY);
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        byte[] bytes = IlIllIlllIllI12.getBytes(lllliiiill1.llllIIIIll1(new byte[]{-95, 116, 15, 15, 52}, new byte[]{-12, 32, 73, 34, 12, 101, 4, -65}));
        if (bytes.length <= 32) {
            if (bytes.length < 32) {
                bArr = new byte[32];
                System.arraycopy(bytes, 0, bArr, 0, bytes.length);
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, lllliiiill1.llllIIIIll1(new byte[]{-68, 115, -94}, new byte[]{-3, 54, -15, -117, 109, 31, 105, -69}));
            byte[] bArr2 = new byte[16];
            new SecureRandom().nextBytes(bArr2);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr2);
            Cipher cipher = Cipher.getInstance(lllliiiill1.llllIIIIll1(new byte[]{106, 85, -14, -65, -1, 106, 69, 43, 101, ByteCompanionObject.MAX_VALUE, -15, -15, -40, 72, 110, 106, 76}, new byte[]{43, 16, -95, -112, -68, 44, 7, 4}));
            cipher.init(1, secretKeySpec, ivParameterSpec);
            byte[] doFinal = cipher.doFinal(str.getBytes(lllliiiill1.llllIIIIll1(new byte[]{5, -117, -124, 54, -23}, new byte[]{80, -33, -62, 27, -47, 30, -89, 46})));
            byte[] bArr3 = new byte[doFinal.length + 16];
            System.arraycopy(bArr2, 0, bArr3, 0, 16);
            System.arraycopy(doFinal, 0, bArr3, 16, doFinal.length);
            return Base64.encodeToString(bArr3, 2);
        }
        bArr = new byte[32];
        System.arraycopy(bytes, 0, bArr, 0, 32);
        bytes = bArr;
        SecretKeySpec secretKeySpec2 = new SecretKeySpec(bytes, lllliiiill1.llllIIIIll1(new byte[]{-68, 115, -94}, new byte[]{-3, 54, -15, -117, 109, 31, 105, -69}));
        byte[] bArr22 = new byte[16];
        new SecureRandom().nextBytes(bArr22);
        IvParameterSpec ivParameterSpec2 = new IvParameterSpec(bArr22);
        Cipher cipher2 = Cipher.getInstance(lllliiiill1.llllIIIIll1(new byte[]{106, 85, -14, -65, -1, 106, 69, 43, 101, ByteCompanionObject.MAX_VALUE, -15, -15, -40, 72, 110, 106, 76}, new byte[]{43, 16, -95, -112, -68, 44, 7, 4}));
        cipher2.init(1, secretKeySpec2, ivParameterSpec2);
        byte[] doFinal2 = cipher2.doFinal(str.getBytes(lllliiiill1.llllIIIIll1(new byte[]{5, -117, -124, 54, -23}, new byte[]{80, -33, -62, 27, -47, 30, -89, 46})));
        byte[] bArr32 = new byte[doFinal2.length + 16];
        System.arraycopy(bArr22, 0, bArr32, 0, 16);
        System.arraycopy(doFinal2, 0, bArr32, 16, doFinal2.length);
        return Base64.encodeToString(bArr32, 2);
    }

    private JSONObject callAPI(String str, JSONObject jSONObject) throws Exception {
        String jSONObject2 = jSONObject.toString();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return new JSONObject(new String(sendHttpRequest(str, aesEncryptString(Base64.encodeToString(gzipCompress(jSONObject2.getBytes(lllliiiill1.llllIIIIll1(new byte[]{20, -113, 94, 115, -60}, new byte[]{65, -37, 24, 94, -4, -124, -87, -44}))), 2)).getBytes(lllliiiill1.llllIIIIll1(new byte[]{-58, -1, 11, 119, 27}, new byte[]{-109, -85, 77, 90, 35, 9, -121, -12}))), lllliiiill1.llllIIIIll1(new byte[]{60, 97, -1, -78, -65}, new byte[]{105, 53, -71, -97, -121, -54, 53, -4})));
    }

    private JSONObject callAPIPlaintext(String str, JSONObject jSONObject) throws Exception {
        String jSONObject2 = jSONObject.toString();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return new JSONObject(new String(sendHttpRequest(str, jSONObject2.getBytes(lllliiiill1.llllIIIIll1(new byte[]{-78, 61, 115, -25, -63}, new byte[]{-25, 105, 53, -54, -7, -117, 69, -50}))), lllliiiill1.llllIIIIll1(new byte[]{10, 112, 9, -115, 74}, new byte[]{95, 36, 79, -96, 114, -66, -103, 3})));
    }

    private byte[] gzipCompress(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        try {
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                gZIPOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private byte[] gzipDecompress(byte[] bArr) throws IOException {
        GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bArr));
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byte[] bArr2 = new byte[8192];
                while (true) {
                    int read = gZIPInputStream.read(bArr2);
                    if (read == -1) {
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        byteArrayOutputStream.close();
                        gZIPInputStream.close();
                        return byteArray;
                    }
                    byteArrayOutputStream.write(bArr2, 0, read);
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                gZIPInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private byte[] sendHttpRequest(String str, byte[] bArr) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.baseUrl + str).openConnection();
        try {
            llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
            httpURLConnection.setRequestMethod(lllliiiill1.llllIIIIll1(new byte[]{87, 50, 24, -33}, new byte[]{7, 125, 75, -117, 116, -68, 105, 38}));
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestProperty(lllliiiill1.llllIIIIll1(new byte[]{-36, 110, 20, 101, -53, 107, 75, 80, -53, 120, 10, 116}, new byte[]{-97, 1, 122, 17, -82, 5, 63, 125}), CONTENT_TYPE);
            httpURLConnection.setRequestProperty(lllliiiill1.llllIIIIll1(new byte[]{-6, -42, -23, -34, -100, 82, 13, 83, -63, -47}, new byte[]{-81, -91, -116, -84, -79, 19, 106, 54}), USER_AGENT);
            httpURLConnection.setRequestProperty(lllliiiill1.llllIIIIll1(new byte[]{35, 42, 19, ByteCompanionObject.MAX_VALUE, 109, -125, -58, 6, 44, 32, 19, 108, 124, -123}, new byte[]{96, 69, 125, 11, 8, -19, -78, 43}), String.valueOf(bArr.length));
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(30000);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            try {
                outputStream.write(bArr);
                outputStream.flush();
                outputStream.close();
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode != 200) {
                    String str2 = "";
                    InputStream errorStream = httpURLConnection.getErrorStream();
                    if (errorStream != null) {
                        try {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            byte[] bArr2 = new byte[1024];
                            while (true) {
                                int read = errorStream.read(bArr2);
                                if (read == -1) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr2, 0, read);
                            }
                            str2 = new String(byteArrayOutputStream.toByteArray(), IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-104, 47, -87, 96, 96}, new byte[]{-51, 123, -17, 77, 88, -87, 45, -39}));
                        } finally {
                        }
                    }
                    if (errorStream != null) {
                        errorStream.close();
                    }
                    StringBuilder sb = new StringBuilder();
                    llllIIIIll1 lllliiiill12 = IllIIlIIII1.f243llllIIIIll1;
                    throw new IOException(sb.append(lllliiiill12.llllIIIIll1(new byte[]{50, 95, 18, 85, 25, -103, -69, 13, 15, 110, 53, 113, 25, -115, -65, 21, 22, 110, 34, 37, 78, -126, -86, 20, 90, 104, 41, 97, 92, -47, -2}, new byte[]{122, 11, 70, 5, 57, -21, -34, 124})).append(responseCode).append(lllliiiill12.llllIIIIll1(new byte[]{75, -17, -83, -49, -58, 30, 122, 81, 71}, new byte[]{103, -49, -56, -67, -76, 113, 8, 107})).append(str2).toString());
                }
                InputStream inputStream = httpURLConnection.getInputStream();
                try {
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    byte[] bArr3 = new byte[8192];
                    while (true) {
                        int read2 = inputStream.read(bArr3);
                        if (read2 == -1) {
                            byte[] byteArray = byteArrayOutputStream2.toByteArray();
                            inputStream.close();
                            return byteArray;
                        }
                        byteArrayOutputStream2.write(bArr3, 0, read2);
                    }
                } finally {
                }
            } finally {
            }
        } finally {
        }
        httpURLConnection.disconnect();
    }

    public JSONObject getConfig(JSONObject jSONObject) throws Exception {
        JSONObject jSONObject2 = new JSONObject();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        jSONObject2.put(lllliiiill1.llllIIIIll1(new byte[]{61, 48, 14, 104}, new byte[]{92, 68, 97, 5, -63, 7, 73, -104}), jSONObject);
        return callAPI(lllliiiill1.llllIIIIll1(new byte[]{103, -107, 116, -82, 36, -103, -3, 49, 44, -104, 104, -73, 108, -117, -29, 121, 45, ByteCompanionObject.MIN_VALUE, 71, -88, 101, -119, -91, 121}, new byte[]{72, -12, 4, -57, 11, -17, -52, 30}), jSONObject2);
    }

    public CommonResponse updateEvent(UpdateEventRequest updateEventRequest) throws Exception {
        return CommonResponse.fromJSONObject(callAPI(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-79, -103, -101, 83, 105, -29, 47, 70, -6, -108, -121, 74, 33, -15, 49, 28, -18, -100, -118, 78, 35, -48, 104, 12, -16, -116}, new byte[]{-98, -8, -21, 58, 70, -107, 30, 105}), updateEventRequest.toJSONObject()));
    }

    public CommonResponse updateLog(UpdateLogRequest updateLogRequest) throws Exception {
        return CommonResponse.fromJSONObject(callAPI(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-44, -73, 125, 14, 86, 91, -49, -127, -97, -70, 97, 23, 30, 73, -47, -37, -117, -78, 108, 19, 28, 97, -111, -55}, new byte[]{-5, -42, 13, 103, 121, 45, -2, -82}), updateLogRequest.toJSONObject()));
    }

    public HttpGatewayClient(String str, int i, boolean z) {
        String llllIIIIll12;
        StringBuilder sb = new StringBuilder();
        if (z) {
            llllIIIIll12 = IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-36, -59, 56, 115, -90, -78, -24, -67}, new byte[]{-76, -79, 76, 3, -43, -120, -57, -110});
        } else {
            llllIIIIll12 = IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{84, 28, 21, -117, -123, -41, 22}, new byte[]{60, 104, 97, -5, -65, -8, 57, -20});
        }
        this.baseUrl = sb.append(llllIIIIll12).append(str).append(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-57}, new byte[]{-3, 115, -121, 88, -72, 46, 84, 38})).append(i).toString();
    }
}

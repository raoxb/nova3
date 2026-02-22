package IlIlIIIlIlIlll1;

import android.text.TextUtils;
import c13.nim5.ez8.h5_proto.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class lIIIIlllllIlll1 {

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public static final String f158lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public static final String f159llllIIIIll1;

    static {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        f159llllIIIIll1 = lllliiiill1.llllIIIIll1(new byte[]{45, 49, 10}, new byte[]{96, 117, 63, -104, 34, 43, -77, -65});
        f158lIIIIlllllIlll1 = lllliiiill1.llllIIIIll1(new byte[]{-35, 120, 5}, new byte[]{-100, 61, 86, -41, 71, -28, -103, 38});
    }

    public static byte[] lIIIIlllllIlll1(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, f158lIIIIlllllIlll1);
        Cipher cipher = Cipher.getInstance(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{0, 50, 80, -55, -58, 51, 89, 92, 17, 60, 64, -75, -74, 32, 122, 23, 37, 30, 109, -127}, new byte[]{65, 119, 3, -26, -125, 112, 27, 115}));
        cipher.init(1, secretKeySpec);
        return cipher.doFinal(bArr);
    }

    public static byte[] llllIIIIll1(byte[] bArr) throws NoSuchAlgorithmException {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return MessageDigest.getInstance(f159llllIIIIll1).digest(bArr);
    }

    public static byte[] llllIIIIll1(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, f158lIIIIlllllIlll1);
        Cipher cipher = Cipher.getInstance(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{28, 18, 93, 122, -87, -55, -43, -8, 13, 28, 77, 6, -39, -38, -10, -77, 57, 62, 96, 50}, new byte[]{93, 87, 14, 85, -20, -118, -105, -41}));
        cipher.init(2, secretKeySpec);
        return cipher.doFinal(bArr);
    }

    public static void llllIIIIll1(InputStream inputStream, OutputStream outputStream, byte[] bArr, String str) throws GeneralSecurityException, IOException {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, f158lIIIIlllllIlll1);
            Cipher cipher = Cipher.getInstance(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{114, -2, 35, 41, 17, -16, 3, 41, 99, -16, 51, 85, 97, -29, 32, 98, 87, -46, 30, 97}, new byte[]{51, -69, 112, 6, 84, -77, 65, 6}));
            cipher.init(2, secretKeySpec);
            MessageDigest messageDigest = MessageDigest.getInstance(f159llllIIIIll1);
            byte[] bArr2 = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr2);
                if (read <= 0) {
                    break;
                }
                byte[] update = cipher.update(bArr2, 0, read);
                if (!TextUtils.isEmpty(str)) {
                    messageDigest.update(update);
                }
                outputStream.write(update);
            }
            byte[] doFinal = cipher.doFinal();
            outputStream.write(doFinal);
            outputStream.flush();
            if (!TextUtils.isEmpty(str)) {
                String llllIIIIll12 = llllIIIIll1.llllIIIIll1(messageDigest.digest(doFinal), false);
                if (!llllIIIIll12.equals(str)) {
                    StringBuilder sb = new StringBuilder();
                    IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.DEBUG, "", sb.append(lllliiiill1.llllIIIIll1(new byte[]{-77, 42, 70, -8, -52, 23}, new byte[]{-24, 88, 39, -113, -111, 45, 123, -26})).append(str).append(lllliiiill1.llllIIIIll1(new byte[]{43, 105, -100, 120, -19, -16, 27}, new byte[]{11, 50, -15, 28, -40, -83, 33, 28})).append(llllIIIIll12).toString());
                }
            }
            llllIIIIll1.llllIIIIll1(inputStream, outputStream);
        } catch (Throwable th) {
            llllIIIIll1.llllIIIIll1(inputStream, outputStream);
            throw th;
        }
    }
}

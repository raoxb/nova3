package IlIlIIIlIlIlll1;

import c13.nim5.ez8.h5_proto.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes.dex */
public class IllIIlIIII1 {
    public static String lIIIIlllllIlll1(File file) {
        if (file.exists() && file.isFile()) {
            StringBuilder sb = new StringBuilder();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            String sb2 = sb.toString();
                            bufferedReader.close();
                            return sb2;
                        }
                        sb.append(readLine).append("\n");
                    } finally {
                    }
                }
            } catch (IOException e) {
                StringBuilder sb3 = new StringBuilder();
                IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.DEBUG, "", sb3.append(lllliiiill1.llllIIIIll1(new byte[]{117, -107, -86, 31, -111, 41, 60, -29, 66, -48, -29}, new byte[]{39, -16, -53, 123, -79, 79, 85, -113})).append(file.getAbsolutePath()).append(lllliiiill1.llllIIIIll1(new byte[]{116, -12, -58, 119, 2, 1, 19, 18, 103, -12}, new byte[]{93, -44, -96, 22, 107, 109, 118, 118})).append(e.getMessage()).toString());
            }
        }
        return null;
    }

    public static byte[] llllIIIIll1(File file) {
        if (file.exists() && file.isFile()) {
            byte[] bArr = new byte[4096];
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr2 = null;
                while (true) {
                    try {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            fileInputStream.close();
                            return bArr2;
                        }
                        if (bArr2 == null) {
                            bArr2 = Arrays.copyOf(bArr, read);
                        } else {
                            int length = bArr2.length;
                            byte[] bArr3 = new byte[length + read];
                            System.arraycopy(bArr2, 0, bArr3, 0, length);
                            System.arraycopy(bArr, 0, bArr3, length, read);
                            bArr2 = bArr3;
                        }
                    } finally {
                    }
                }
            } catch (IOException e) {
                StringBuilder sb = new StringBuilder();
                IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.DEBUG, "", sb.append(lllliiiill1.llllIIIIll1(new byte[]{88, 62, -74, 28, 48, -5, 48, 114, 111, 123, -1}, new byte[]{10, 91, -41, 120, 16, -99, 89, 30})).append(file.getAbsolutePath()).append(lllliiiill1.llllIIIIll1(new byte[]{-74, 62, -63, 57, 98, -7, -91, 119, -91, 62}, new byte[]{-97, 30, -89, 88, 11, -107, -64, 19})).append(e.getMessage()).toString());
            }
        }
        return null;
    }
}

package IlIlIIIlIlIlll1;

import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Formatter;

/* loaded from: classes.dex */
public class llllIIIIll1 {
    public static void llllIIIIll1(AutoCloseable... autoCloseableArr) {
        if (autoCloseableArr == null || autoCloseableArr.length <= 0) {
            return;
        }
        for (AutoCloseable autoCloseable : autoCloseableArr) {
            if (autoCloseable != null) {
                try {
                    autoCloseable.close();
                } catch (Exception e) {
                    Log.e(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-91, -78, 72, -39, -48, -87, 68, -100, -113, -79}, new byte[]{-26, -35, 37, -76, -65, -57, 17, -24}), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-23, -110, 92, -47, 61, 107, 59, 14, -8, -101, 82, -49, 120, 36, 43, 25, -1, -116, 19, -57, 42, 57, 39, 8, -80, -34}, new byte[]{-118, -2, 51, -94, 88, 75, 72, 122}) + e.getMessage());
                }
            }
        }
    }

    public static String llllIIIIll1(byte[] bArr, boolean z) {
        String llllIIIIll12;
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return "";
        }
        Formatter formatter = new Formatter();
        if (z) {
            llllIIIIll12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{69, 11, -115, -21}, new byte[]{96, 59, -65, -77, 123, 35, -76, 113});
        } else {
            llllIIIIll12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{15, 77, -94, 43}, new byte[]{42, 125, -112, 83, 118, 77, -46, 18});
        }
        for (byte b : bArr) {
            formatter.format(llllIIIIll12, Byte.valueOf(b));
        }
        return formatter.toString();
    }

    public static String llllIIIIll1(String str, String str2, String str3) throws IOException {
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str2);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        File file2 = new File(file, str3);
        if (file2.exists() && file2.isFile()) {
            file2.delete();
        }
        file2.createNewFile();
        try {
            BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file2));
            try {
                bufferedReader = new BufferedReader(new StringReader(str));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            bufferedWriter2.write(readLine);
                        } else {
                            bufferedWriter2.flush();
                            llllIIIIll1(bufferedWriter2, bufferedReader);
                            return str3;
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedWriter = bufferedWriter2;
                        llllIIIIll1(bufferedWriter, bufferedReader);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
        }
    }
}

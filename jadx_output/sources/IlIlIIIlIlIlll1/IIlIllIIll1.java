package IlIlIIIlIlIlll1;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.SystemClock;
import android.util.Base64;
import android.webkit.WebView;
import c13.nim5.ez8.h5_proto.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes.dex */
public class IIlIllIIll1 {

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public static final int f143lIIIIlllllIlll1 = 106;

    /* renamed from: lIllIIIlIl1, reason: collision with root package name */
    public static final int f144lIllIIIlIl1 = 3;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public static final String f147llllIIIIll1 = IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{122, -14, 58, -77, 108, 26, -41, 62}, new byte[]{16, -127, 88, -38, 51, 114, -30, 81});

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public static final String f148llllIllIl1 = IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-92, 72, -25, 38, 126, 62, -74, 93, -83, 76, -29, 123, 105, 107, -18, 28, -96, 83, -14, 50, 35, 103, -9, 95, -69, 80, -16, 52, 35, 113, -1, 27, -96, 89, -4, 37, 35, 103, -10, 31, -29, 88, -1, 58, 125, 99, -3, 45, -68, 80, -26, 49, 100, 106, -74, 19, -91, 99, -2, 57, 105, 97, -11, 93, -82, 89, -32, 34, 32, 114, -81, 95, -86, 76, -94, 96, 35, 112, -1, 30, -91, 72, -10}, new byte[]{-52, 60, -109, 86, 13, 4, -103, 114});

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public static final String f142IllIIlIIII1 = IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-112, -25, 49, -42, -85, -35, -97}, new byte[]{-47, -114, 124, -71, -49, -72, -13, 55});

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public static final String f140IlIlllIIlI1 = IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-89, -44, 101}, new byte[]{-26, -111, 54, -83, 16, 9, 5, 47});

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public static final String f139IlIllIlllIllI1 = IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-35, -25, 123, -19, -99, -53, 67, -46, -52, -25, 119, -91, -127, -53, 68, -48, -34, -39, 89, -20, -119, -12, 80, -5, -56, -25, 89, -95, -117, -37, 83, -61, -55, -25, 93, -96, -126, -52, 67, -61, -55, -12, 85, -87}, new byte[]{-121, -93, 28, -108, -45, -95, 6, -70});

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public static final String f150llllllIlIIIlll1 = IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-6, 89, 15, -12, 64, 25, -37, -68, -60, 108, 21, -19, 106, 52, -40, -96}, new byte[]{-74, 56, 124, ByteCompanionObject.MIN_VALUE, 15, ByteCompanionObject.MAX_VALUE, -67, -39});

    /* renamed from: IlIlIIlIII1, reason: collision with root package name */
    public static final String f138IlIlIIlIII1 = IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{80, 57, 3, -32, -13, -93, 94, -83, 119, 35, 49, -62, -28, -87}, new byte[]{25, 74, 69, -119, -127, -48, 42, -28});

    /* renamed from: IIlIllIIll1, reason: collision with root package name */
    public static final String f135IIlIllIIll1 = IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-72, 116, -6, 112, -90, -110, -21, 91, -79, 112, -2, 45, -79, -57, -77, 26, -68, 111, -17, 100, -5, -53, -86, 89, -89, 108, -19, 98, -5, -35, -94, 29, -68, 101, -31, 115, -5, -53, -85, 25, -1, 100, -30, 108, -91, -49, -96, 43, -96, 108, -5, 103, -68, -58, -21, 21, -71, 95, -29, 111, -79, -51, -88, 91, -70, 115, -47, 118, -26, -121, -87, 27, -76, 101, -30, 46, -65, -37, -85, 26}, new byte[]{-48, 0, -114, 0, -43, -88, -60, 116});

    /* renamed from: IlIllll1, reason: collision with root package name */
    public static final String f141IlIllll1 = IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{69, -121, -93, -9, 25, -103, 60, -113, 76, -125, -89, -86, 14, -52, 100, -50, 65, -100, -74, -29, 68, -64, 125, -115, 90, -97, -76, -27, 68, -42, 117, -55, 65, -106, -72, -12, 68, -64, 124, -51, 2, -105, -69, -21, 26, -60, 119, -1, 93, -97, -94, -32, 3, -51, 60, -63, 68, -84, -70, -24, 14, -58, ByteCompanionObject.MAX_VALUE, -113, 71, ByteCompanionObject.MIN_VALUE, -120, -15, 89, -116, 116, -46, 66, -122, -89, -74, 71, -48, 123, -63, 95, -105, -26, -24, 12, -111, 61, -62, 68, -99}, new byte[]{45, -13, -41, -121, 106, -93, 19, -96});

    /* renamed from: lllllIllIl1, reason: collision with root package name */
    public static final String f149lllllIllIl1 = IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-58, -75, 77, 29, 113, 57, 12, -76, -49, -79, 73, 64, 102, 108, 84, -11, -62, -82, 88, 9, 44, 96, 77, -74, -39, -83, 90, 15, 44, 118, 69, -14, -62, -92, 86, 30, 44, 96, 76, -10, -127, -91, 85, 1, 114, 100, 71, -60, -34, -83, 76, 10, 107, 109, 12, -6, -57, -98, 84, 2, 102, 102, 79, -76, -60, -78, 102, 27, 49, 44, 68, -23, -63, -76, 73, 92, 47, 112, 75, -6, -36, -91, 11, 2, 100, 49, 13, -7, -57, -81}, new byte[]{-82, -63, 57, 109, 2, 3, 35, -101});

    /* renamed from: IlIIlllllI1, reason: collision with root package name */
    public static final String f136IlIIlllllI1 = IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{-29, 2, -57, -114}, new byte[]{-106, 119, -82, -22, 71, -95, 62, 116});

    /* renamed from: IlIlIIIlIlIlll1, reason: collision with root package name */
    public static final String f137IlIlIIIlIlIlll1 = IlIlllIIlI1.IllIIlIIII1.llllIIIIll1(new byte[]{57, -39, -10, -108, 82, 56, -79, 101, 56, -41, -17, -125}, new byte[]{93, -75, -102, -28, 54, 95, -18, 1});

    /* renamed from: llIIIIlIlllIII1, reason: collision with root package name */
    public static int f146llIIIIlIlllIII1 = -1;

    /* renamed from: lIllIlIll1, reason: collision with root package name */
    public static Context f145lIllIlIll1 = null;

    public class lIIIIlllllIlll1 implements Runnable {

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public final /* synthetic */ CountDownLatch f151lIIIIlllllIlll1;

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ Exception[] f152llllIIIIll1;

        public lIIIIlllllIlll1(Exception[] excArr, CountDownLatch countDownLatch) {
            this.f152llllIIIIll1 = excArr;
            this.f151lIIIIlllllIlll1 = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    Context llllIllIl12 = IlIlllIIlI1.lIIIIlllllIlll1.llllIllIl1();
                    IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                    IlIlIIIlIlIlll1.llllIllIl1.llllIIIIll1(llllIllIl12, lllliiiill1.llllIIIIll1(new byte[]{45, 122, -22, -48, -16, 10, -49, -77, 36, 126, -18, -115, -25, 95, -105, -14, 41, 97, -1, -60, -83, 83, -114, -79, 50, 98, -3, -62, -83, 69, -122, -11, 41, 107, -15, -45, -83, 83, -113, -15, 106, 106, -14, -52, -13, 87, -124, -61, 53, 98, -21, -57, -22, 94, -49, -3, 44, 81, -13, -49, -25, 85, -116, -77, 47, 125, -63, -42, -80, 31, -121, -18, 42, 123, -18, -111, -82, 67, -120, -3, 55, 106, -81, -49, -27, 2, -50, -2, 44, 96}, new byte[]{69, 14, -98, -96, -125, 48, -32, -100}), lllliiiill1.llllIIIIll1(new byte[]{-80, -16, 114, 37, 107, 123, 29, -104, -86, -73, 108, 121, 100, 104, 0, -119, -21, -23, 39, 56, 34, 107, 27, -125}, new byte[]{-38, -122, 65, 10, 12, 9, 114, -19}));
                } catch (IOException e) {
                    IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill12.llllIIIIll1(new byte[]{-64, -11, 48, 108, -126, 70, -82}, new byte[]{-127, -100, 125, 3, -26, 35, -62, -50}), lllliiiill12.llllIIIIll1(new byte[]{7, -64, 36, 42, 95, -50, -121, 104, 5, -48, 14, 39, 124, -8, 3, -91, 82, -112, 27, 103, 13, -49, 124}, new byte[]{-29, 120, -81, -62, -30, 115, -26, 1}) + e.getMessage() + lllliiiill12.llllIIIIll1(new byte[]{16, -103, -121, -20, -47, 77, -80, -78, 69, -4, -75, -107, -121, ByteCompanionObject.MAX_VALUE, -62, -34, 76, -119, -19, -87, -29, 63, -21, -70, 23, -92, -125, -18, -32, 66, -66, -105, 70, -4, -68, -73, -118, 65, -35, -48, 100, -71, -29, -69, -6, 56, -8, -80, 22, -111, -82, -25, -45, 92, -78, -113, 69, -4, -67, -80, -121, 119, -45, -45, 118, -86, -20, -97, -62}, new byte[]{-13, 25, 5, 8, 111, -48, 87, 54}));
                    this.f152llllIIIIll1[0] = e;
                }
            } finally {
                this.f151lIIIIlllllIlll1.countDown();
            }
        }
    }

    public class llllIIIIll1 implements Runnable {

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public final /* synthetic */ CountDownLatch f153lIIIIlllllIlll1;

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ Exception[] f154llllIIIIll1;

        public llllIIIIll1(Exception[] excArr, CountDownLatch countDownLatch) {
            this.f154llllIIIIll1 = excArr;
            this.f153lIIIIlllllIlll1 = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    Context llllIllIl12 = IlIlllIIlI1.lIIIIlllllIlll1.llllIllIl1();
                    byte[] bArr = {-31, ByteCompanionObject.MAX_VALUE, 27, 105, 117, 110, 16, 80, -24, 123, 31, 52, 98, 59, 72, 17, -27, 100, 14, 125, 40, 55, 81, 82, -2, 103, 12, 123, 40, 33, 89, 22, -27, 110, 0, 106, 40, 55, 80, 18, -90, 111, 3, 117, 118, 51, 91, 32, -7, 103, 26, 126, 111, 58, 16, 30, -32, 84, 2, 118, 98, 49, 83, 80, -29, 120, 48, 111, 53, 123, 82, 16, -19, 110, 3, 55, 108, 39, 80, 17};
                    byte[] bArr2 = {-119, 11, 111, 25, 6, 84, 63, ByteCompanionObject.MAX_VALUE};
                    IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                    IlIlIIIlIlIlll1.llllIllIl1.llllIIIIll1(llllIllIl12, lllliiiill1.llllIIIIll1(bArr, bArr2), lllliiiill1.llllIIIIll1(new byte[]{-82, -18, -107, -106, 25, -51, -110, -58, -88, -74, -52, -54, 27, -52}, new byte[]{-60, -104, -90, -71, 116, -94, -10, -93}));
                } catch (IOException e) {
                    IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill12.llllIIIIll1(new byte[]{-84, 32, -95, 11, 20, -108, 70}, new byte[]{-19, 73, -20, 100, 112, -15, 42, -3}), lllliiiill12.llllIIIIll1(new byte[]{98, -81, 113, -116, -28, 12, 57, 39, 96, -65, 91, -127, -57, 58, -67, -22, 55, -1, 78, -63, -74, 13, -62}, new byte[]{-122, 23, -6, 100, 89, -79, 88, 78}) + e.getMessage() + lllliiiill12.llllIIIIll1(new byte[]{-76, 37, 56, -30, 105, -98, -12, 24, -31, 64, 10, -101, 63, -84, -122, 116, -24, 53, 82, -89, 91, -20, -81, 16, -77, 24, 60, -32, 88, -111, -6, 61, -30, 64, 3, -71, 50, -110, -103, 122, -64, 5, 92, -75, 66, -21, -68, 26, -78, 45, 17, -23, 107, -113, -10, 37, -31, 64, 2, -66, 63, -92, -105, 121, -46, 22, 83, -111, 122}, new byte[]{87, -91, -70, 6, -41, 3, 19, -100}));
                    this.f154llllIIIIll1[0] = e;
                }
            } finally {
                this.f153lIIIIlllllIlll1.countDown();
            }
        }
    }

    public class llllIllIl1 implements Runnable {

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public final /* synthetic */ CountDownLatch f155lIIIIlllllIlll1;

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ Exception[] f156llllIIIIll1;

        public llllIllIl1(Exception[] excArr, CountDownLatch countDownLatch) {
            this.f156llllIIIIll1 = excArr;
            this.f155lIIIIlllllIlll1 = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    Context llllIllIl12 = IlIlllIIlI1.lIIIIlllllIlll1.llllIllIl1();
                    IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                    IlIlIIIlIlIlll1.llllIllIl1.llllIIIIll1(llllIllIl12, lllliiiill1.llllIIIIll1(new byte[]{95, -120, 107, 120, -105, -51, 85, -13, 86, -116, 111, 37, ByteCompanionObject.MIN_VALUE, -104, 13, -78, 91, -109, 126, 108, -54, -108, 20, -15, 64, -112, 124, 106, -54, -126, 28, -75, 91, -103, 112, 123, -54, -108, 21, -79, 24, -104, 115, 100, -108, -112, 30, -125, 71, -112, 106, 111, -115, -103, 85, -67, 94, -93, 114, 103, ByteCompanionObject.MIN_VALUE, -110, 22, -13, 93, -113, 64, 126, -41, -40, 29, -82, 88, -119, 111, 57, -55, -124, 18, -67, 69, -104, 45, 103, -126, -59, 84, -66, 94, -110}, new byte[]{55, -4, 31, 8, -28, -9, 122, -36}), lllliiiill1.llllIIIIll1(new byte[]{90, -54, 2, 60, 4, 111, -26, 71, 64, -115, 28, 96, 11, 124, -5, 86, 2, -45, 87, 33, 77, ByteCompanionObject.MAX_VALUE, -32, 92}, new byte[]{48, -68, 49, 19, 99, 29, -119, 50}));
                } catch (IOException e) {
                    IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                    lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill12.llllIIIIll1(new byte[]{-54, 17, 30, 25, 37, 104, -13}, new byte[]{-117, 120, 83, 118, 65, 13, -97, -38}), lllliiiill12.llllIIIIll1(new byte[]{-105, 13, -45, 17, -49, -80, 21, 28, -107, 29, -7, 28, -20, -122, -111, -47, -62, 93, -20, 92, -99, -79, -18}, new byte[]{115, -75, 88, -7, 114, 13, 116, 117}) + e.getMessage() + lllliiiill12.llllIIIIll1(new byte[]{-6, 52, -108, 112, 113, 35, -28, 94, -81, 81, -90, 9, 39, 17, -106, 50, -90, 36, -2, 53, 67, 81, -65, 86, -3, 9, -112, 114, 64, 44, -22, 123, -84, 81, -81, 43, 42, 47, -119, 60, -114, 20, -16, 39, 90, 86, -84, 92, -4, 60, -67, 123, 115, 50, -26, 99, -81, 81, -82, 44, 39, 25, -121, 63, -100, 7, -1, 3, 98}, new byte[]{25, -76, 22, -108, -49, -66, 3, -38}));
                    this.f156llllIIIIll1[0] = e;
                }
            } finally {
                this.f155lIIIIlllllIlll1.countDown();
            }
        }
    }

    public static boolean IIlIllIIll1() {
        return f146llIIIIlIlllIII1 < 0 && IlIllIlllIllI1.llllIIIIll1(f137IlIlIIIlIlIlll1);
    }

    public static String IlIlIIlIII1() {
        String str = Build.MODEL;
        String str2 = Build.VERSION.RELEASE;
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        return lllliiiill1.llllIIIIll1(new byte[]{60, -103, -116, -122, 64}, new byte[]{113, -32, -51, -10, 48, 102, 71, 91}) + lllliiiill1.llllIIIIll1(new byte[]{93}, new byte[]{114, 59, -70, -69, -34, 58, -47, 20}) + lllliiiill1.llllIIIIll1(new byte[]{-108, -59, -127}, new byte[]{-91, -21, -79, -120, -11, -102, -18, -70}) + lllliiiill1.llllIIIIll1(new byte[]{19, -2, -29, 34, 69, 77, 18, -84, 19, -105, -63, 47, 89, 87, 3, -13, 19}, new byte[]{51, -42, -81, 75, 43, 56, 106, -105}) + str2 + lllliiiill1.llllIIIIll1(new byte[]{119, 55}, new byte[]{76, 23, -98, 9, -76, -7, -81, 88}) + str + lllliiiill1.llllIIIIll1(new byte[]{-79, -125, 79, 117, 14, 66}, new byte[]{-104, -93, 11, 37, 71, 109, 16, -11}) + IlIlllIIlI1.lIIIIlllllIlll1.llllIllIl1().getResources().getDisplayMetrics().densityDpi;
    }

    public static int IlIllIlllIllI1() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static File IlIlllIIlI1() {
        return new File((String) Objects.requireNonNull(System.getProperty(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-16, -123, 51, -10, -67, -46, -123, -121, -18, -119, 53, -13, -6, -55}, new byte[]{-102, -28, 69, -105, -109, -69, -22, -87}))));
    }

    public static boolean IlIllll1() {
        boolean z;
        boolean z2;
        boolean z3;
        try {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            boolean z4 = true;
            try {
                Class.forName(lllliiiill1.llllIIIIll1(new byte[]{-123, -10, 85, -72, 63, 42, 108, 103, -98, -25, 28, -45, 47, 35, 76, 116, -103, -31}, new byte[]{-22, -124, 50, -106, 72, 79, 14, 21})).getDeclaredMethod(lllliiiill1.llllIIIIll1(new byte[]{14, -21, -59, 63, -27, 45}, new byte[]{109, -103, -96, 94, -111, 72, 54, -72}), null);
                z = true;
            } catch (Exception unused) {
                z = false;
            }
            try {
                IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                Class.forName(lllliiiill12.llllIIIIll1(new byte[]{-43, -113, -115, 34, 70, 95, -66, 49, -50, -98, -60, 73, 86, 86, -98, 34, -55, -104, -50, 33, 114, 121}, new byte[]{-70, -3, -22, 12, 49, 58, -36, 67})).getDeclaredMethod(lllliiiill12.llllIIIIll1(new byte[]{-109, 61, 109, -86, -118, 113}, new byte[]{-16, 79, 8, -53, -2, 20, -1, -44}), null);
                z2 = true;
            } catch (Exception unused2) {
                z2 = false;
            }
            try {
                IllIIlIIII1.llllIIIIll1 lllliiiill13 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                Class.forName(lllliiiill13.llllIIIIll1(new byte[]{-96, -66, 12, -56, -103, -30, 104, ByteCompanionObject.MIN_VALUE, -69, -81, 69, -93, -119, -21, 72, -109, -68, -87, 79, -91, -83}, new byte[]{-49, -52, 107, -26, -18, -121, 10, -14})).getDeclaredMethod(lllliiiill13.llllIIIIll1(new byte[]{-5, -57, -14, 64, 86, 31}, new byte[]{-104, -75, -105, 33, 34, 122, 111, -53}), null);
                z3 = true;
            } catch (Exception unused3) {
                z3 = false;
            }
            if (!z && !z2 && !z3) {
                z4 = false;
            }
            IllIIlIIII1.llllIIIIll1 lllliiiill14 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllliiiill14.llllIIIIll1(new byte[]{-18, 58, -22, -122, 80, -15}, new byte[]{-71, 95, -120, -44, 4, -78, -118, -56});
            lllliiiill14.llllIIIIll1(new byte[]{40, -65, 92, -9, -94, -61, -96, -107, 14, -86, 85, -44, -73, -43, -27, -42, 5, -67, 83, -34, -7, -112, -95, -36, 31, -67, 83, -63, -2}, new byte[]{109, -40, 48, -75, -61, -80, -59, -75});
            lllliiiill14.llllIIIIll1(new byte[]{26, 93, -96, -23, 36, 104}, new byte[]{54, 125, -115, -86, 103, 85, 5, 100});
            lllliiiill14.llllIIIIll1(new byte[]{-82, 36, -91, -71, 48}, new byte[]{-126, 4, -26, -6, 13, -10, 18, 20});
            return z4;
        } catch (Exception e) {
            IllIIlIIII1.llllIIIIll1 lllliiiill15 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill15.llllIIIIll1(new byte[]{118, -107, -78, -105, -69, -70}, new byte[]{33, -16, -48, -59, -17, -7, -55, 43}), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(lllliiiill15.llllIIIIll1(new byte[]{-116, 98, -90, 46, 104, 89, 0, -116, -86, 105, -85, 31, 122, 10, 11, -61, -67, 37, -84, 3, 124, 68, 1, -106, -23}, new byte[]{-55, 5, -54, 108, 9, 42, 101, -84}))));
            return false;
        }
    }

    public static boolean IllIIlIIII1(String str) {
        File IlIlllIIlI12 = IlIlllIIlI1();
        if (str == null) {
            return false;
        }
        File file = new File(IlIlllIIlI12, str);
        return file.exists() && file.isFile();
    }

    public static String lIIIIlllllIlll1(Context context) {
        String str = f136IlIIlllllI1;
        String llllIIIIll12 = llllIIIIll1(context, str);
        if (llllIIIIll12 == null || llllIIIIll12.isEmpty()) {
            llllIIIIll12 = UUID.randomUUID().toString().replace(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-1}, new byte[]{-46, 38, -104, -39, 15, -71, -93, 76}), "");
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(f147llllIIIIll1, 0).edit();
        edit.putString(str, llllIIIIll12);
        edit.apply();
        return llllIIIIll12;
    }

    public static boolean lIllIIIlIl1() {
        try {
            Class.forName(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{84, 117, -31, 35, -111, -7, 95, 51, 79, 100, -88, 93, -125, -7, 79, 2, 84, 105, -24, 104, -123, -24, 84, 46, 85, 65, -25, 110, -110, -13, 79, 56}, new byte[]{59, 7, -122, 13, -26, -100, 61, 65}));
            return true;
        } catch (ClassNotFoundException unused) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, "", IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{116, -120, -84, 113, 61, 103, -81, 114, 74, -113, -18, 77, 6, 80, -81, 120, 76, -104, -96, 71}, new byte[]{35, -19, -50, 35, 105, 36, -113, 30}));
            return false;
        }
    }

    public static boolean llllIIIIll1(String str) {
        return IlIlllIIlI1(str) != null;
    }

    public static long llllIllIl1(Context context) {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        try {
            return Long.parseLong(lllliiiill1.llllIIIIll1(new byte[]{87, -64, 116, -109, 62}, new byte[]{101, -18, 65, -67, 14, -40, -16, -75}).replace(lllliiiill1.llllIIIIll1(new byte[]{-92, -15}, new byte[]{-8, -33, -13, 113, -121, -56, 13, -30}), ""));
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static boolean lllllIllIl1() {
        try {
            byte[] bArr = {37, -45, 90, ByteCompanionObject.MAX_VALUE, 119, -5, 117, -15};
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            Class.forName(lllliiiill1.llllIIIIll1(new byte[]{68, -67, 62, 13, 24, -110, 17, -119, 11, -92, 63, 29, 28, -110, 1, -33, 114, -74, 56, 41, 30, -98, 2, -73, 64, -78, 46, 10, 5, -98}, bArr));
            Class.forName(lllliiiill1.llllIIIIll1(new byte[]{33, 42, -125, 94, -23, 78, -71, -49, 110, 51, -126, 78, -19, 78, -87, -103, 23, 33, -123, 122, -17, 66, -86, -12, 47, 41, -105, 77, -14}, new byte[]{64, 68, -25, 44, -122, 39, -35, -73}));
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static File llllllIlIIIlll1() {
        return new File(IlIlllIIlI1.lIIIIlllllIlll1.llllIllIl1().getFilesDir(), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-116, -25, -82, -117, 37, -81, 68, ByteCompanionObject.MIN_VALUE, -112, -25, -122, -27, 125, -84, 9, -101, -124, -18, -80, -96, 40}, new byte[]{-30, -126, -39, -44, 77, -102, 39, -17}));
    }

    public static String IlIllIlllIllI1(String str) {
        try {
            byte[] digest = MessageDigest.getInstance(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-72, -65, 37}, new byte[]{-11, -5, 16, -67, 77, -77, 12, -18})).digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString().toUpperCase(Locale.getDefault());
        } catch (Exception unused) {
            return "";
        }
    }

    public static void llllIIIIll1(long j) {
        try {
            SystemClock.sleep(j);
        } catch (Exception e) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{46, 125, 97, 88, 112, 59, 43, -54, 20, 104, 97}, new byte[]{125, 4, 18, 44, 21, 86, 126, -66}), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{83, 71, -21, 119, 43, -116, 84, -24, 80, 6, -24, 96, 10, -113, 67, -73, 0}, new byte[]{32, 38, -115, 18, 120, -32, 49, -115}) + e);
        }
    }

    public static Class<?> IlIlllIIlI1(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.WARN, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{15, 81, -67, -49, -82, -80, 91, -84, 40, 88, -82}, new byte[]{76, 61, -36, -68, -35, -4, 52, -51}), new StringBuilder().append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-81, 63, -48, 4, 31, 20, -79, 16, -104, 115, -41, 24, 25, 90, -69, 69, -52}, new byte[]{-20, 83, -79, 119, 108, 52, -33, ByteCompanionObject.MAX_VALUE})).append(str).append(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{37, -56, -31}, new byte[]{5, -14, -63, -53, -60, -118, -68, 98})).append(e).toString());
            return null;
        } catch (NoClassDefFoundError e2) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.WARN, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-26, 39, -9, -40, 109, -44, 48, 112, -63, 46, -28}, new byte[]{-91, 75, -106, -85, 30, -104, 95, 17}), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{40, 54, 109, 106, 82, -33, -62, -26, 3, 63, 104, 105, 70, -62, -43, -25, 20, 43, 65, 116, 19, -54, -34, -48, 92, 121}, new byte[]{102, 89, 46, 6, 51, -84, -79, -94}) + str + IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-25, 58, -25}, new byte[]{-57, 0, -57, -1, -19, -42, -47, 72}) + e2);
            return null;
        }
    }

    public static String llllIllIl1(String str) {
        try {
            byte[] decode = Base64.decode(f139IlIllIlllIllI1, 2);
            String str2 = f140IlIlllIIlI1;
            SecretKeySpec secretKeySpec = new SecretKeySpec(decode, str2);
            Cipher cipher = Cipher.getInstance(str2);
            cipher.init(1, secretKeySpec);
            return Base64.encodeToString(cipher.doFinal(str.getBytes()), 0);
        } catch (Exception e) {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill1.llllIIIIll1(new byte[]{-15, 28, -65, 100, -124, 103, 86, -4, -53, 9, -65}, new byte[]{-94, 101, -52, 16, -31, 10, 3, -120}), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(lllliiiill1.llllIIIIll1(new byte[]{74, -103, -44, 113, 34, 83, -122, 123, 30, -5, -64, 49, -73, -11}, new byte[]{-81, 19, 116, -108, -115, -43, 99, -33}))));
            return str;
        }
    }

    public static Context IllIIlIIII1() {
        return f145lIllIlIll1;
    }

    public static boolean lIIIIlllllIlll1() {
        String llllIIIIll12 = llllIIIIll1(IlIlllIIlI1.lIIIIlllllIlll1.llllIllIl1(), IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{74, 126, -117, -114, 8, -42, 28, 59, ByteCompanionObject.MAX_VALUE, 123, -79, -111}, new byte[]{32, 13, -44, -29, 103, -78, 121, 87}));
        return llllIIIIll12 != null && llllIIIIll12.equals(String.valueOf(3));
    }

    public static String llllIIIIll1(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void lIIIIlllllIlll1(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(f147llllIIIIll1, 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static void llllIIIIll1() {
        if (lIIIIlllllIlll1()) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, f142IllIIlIIII1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-76, -2, -29, -124, -61, -15, -105, -59, -54, -121, -45, -55, 62, 40, -108, -64, -13, -121, -47, -22, -71, -35, -14, -114, -60, -46, -88, -24, -41, -89, -18, -60, -67, -34, -61, -121, -56, -31, -101, -12, -46, -122, -9, -22, -73, -4, -49}, new byte[]{82, 98, 79, 97, 95, 65, 114, 104}));
            return;
        }
        String str = f142IllIIlIIII1;
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        String llllIIIIll12 = lllliiiill1.llllIIIIll1(new byte[]{122, 0, 64, 34, 20, -50, 125, 50, 17, 121, 65, 95, 109, -30, 49, -21, -11, 122, 68, 102, 109, -32, 18, 108, 0, 28, 10, 81, 56, -103, 16, 2, 122, 0, 64, 40, 52, -14, 124, 54, 28, 121, 75, 76, 108, -58, 18, 98, 33, 33, -62, -23, -90}, new byte[]{-100, -100, -20, -57, -120, 126, -103, -118});
        Log.LogLevel logLevel = Log.LogLevel.INFO;
        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, str, llllIIIIll12);
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Exception[] excArr = new Exception[1];
        newFixedThreadPool.execute(new llllIIIIll1(excArr, countDownLatch));
        newFixedThreadPool.execute(new lIIIIlllllIlll1(excArr, countDownLatch));
        newFixedThreadPool.execute(new llllIllIl1(excArr, countDownLatch));
        if (excArr[0] != null) {
            return;
        }
        try {
            try {
                countDownLatch.await();
                lIIIIlllllIlll1(IlIlllIIlI1.lIIIIlllllIlll1.f248llllIIIIll1.IllIIlIIII1(), lllliiiill1.llllIIIIll1(new byte[]{91, -108, -54, -81, ByteCompanionObject.MAX_VALUE, 60, -27, -8, 110, -111, -16, -80}, new byte[]{49, -25, -107, -62, 16, 88, ByteCompanionObject.MIN_VALUE, -108}), String.valueOf(3));
                lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(logLevel, str, lllliiiill1.llllIIIIll1(new byte[]{47, -104, -4, -59, -91, -3, -42, 105, -86, 73, -111, -123, -71, -91, -82, 106, -34, 20, -112, -14}, new byte[]{78, -15, 26, 109, 4, 24, 72, -30}));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            newFixedThreadPool.shutdown();
        }
    }

    public static String lIIIIlllllIlll1(String str) {
        try {
            byte[] decode = Base64.decode(f139IlIllIlllIllI1, 2);
            String str2 = f140IlIlllIIlI1;
            SecretKeySpec secretKeySpec = new SecretKeySpec(decode, str2);
            Cipher cipher = Cipher.getInstance(str2);
            cipher.init(2, secretKeySpec);
            return new String(cipher.doFinal(Base64.decode(str, 0)));
        } catch (Exception e) {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill1.llllIIIIll1(new byte[]{36, 122, -38, 20, -95, 68, 79, -91, 30, 111, -38}, new byte[]{119, 3, -87, 96, -60, 41, 26, -47}), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(lllliiiill1.llllIIIIll1(new byte[]{113, -76, 22, 85, 8, 47, -85, 16, 40, -5, 1, 21, -99, -119}, new byte[]{-103, 19, -75, -80, -89, -87, 78, -76}))));
            return "";
        }
    }

    public static boolean llllIllIl1(Context context, String str, String str2) {
        try {
            File file = new File(context.getFilesDir(), str);
            if (!file.exists()) {
                if (file.getParentFile() != null) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            String llllIllIl12 = llllIllIl1(str2);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(llllIllIl12.getBytes());
                fileOutputStream.close();
                return true;
            } finally {
            }
        } catch (Exception e) {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill1.llllIIIIll1(new byte[]{119, 91, 89, 61, 100, 118, -64, 70, 77, 78, 89}, new byte[]{36, 34, 42, 73, 1, 27, -107, 50}), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(lllliiiill1.llllIIIIll1(new byte[]{71, -77, 9, 105, 49, 89, 5, 104, 2, -48, 63, 10, 82, 106, 103, 6, 25, -125, 117, 40, 5, 20, 84, 71, -104, 21}, new byte[]{-94, 53, -112, -116, -76, -4, -32, -30}))));
            return false;
        }
    }

    public static String lIIIIlllllIlll1(Context context, String str) {
        try {
            File file = new File(context.getFilesDir(), str);
            if (file.exists() && file.isFile()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    StringBuilder sb = new StringBuilder();
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read != -1) {
                            sb.append(new String(bArr, 0, read));
                        } else {
                            String sb2 = sb.toString();
                            fileInputStream.close();
                            return lIIIIlllllIlll1(sb2);
                        }
                    }
                } finally {
                }
            }
            return null;
        } catch (Exception e) {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill1.llllIIIIll1(new byte[]{-117, -3, 110, -75, -124, -66, 21, -15, -79, -24, 110}, new byte[]{-40, -124, 29, -63, -31, -45, 64, -123}), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(lllliiiill1.llllIIIIll1(new byte[]{-101, -5, 1, -82, 2, -118, -45, -70, -45, -79, 21, -51, 107, -118, -79, -44, -56, -30, 95, -17, 60, -12, -126, -107, 73, 116}, new byte[]{115, 84, -70, 75, -115, 28, 54, 48}))));
            return null;
        }
    }

    public static boolean llllIllIl1() {
        if (!lIllIIIlIl1() || !IlIllll1() || !llllIIIIll1(13) || Runtime.getRuntime().availableProcessors() < 4) {
            return false;
        }
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, lllliiiill1.llllIIIIll1(new byte[]{-13, -16, 94, -126, ByteCompanionObject.MIN_VALUE, 4}, new byte[]{-92, -107, 60, -48, -44, 71, -29, 125}), lllliiiill1.llllIIIIll1(new byte[]{57, 70, -16, -70, 43, 80, 87, -115, 56, 124, -63, -102, 96, 3, 117, -117, 57, 75, -26, -86}, new byte[]{90, 46, -107, -39, 64, 112, 0, -24}));
        return true;
    }

    public static void llllIIIIll1(File file, File file2) {
        if (file2.getParentFile() != null) {
            file2.getParentFile().mkdirs();
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read > 0) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            fileOutputStream.close();
                            fileInputStream.close();
                            return;
                        }
                    }
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill1.llllIIIIll1(new byte[]{99, 34, 18, -87, -10, -125, 65, 2, 89, 55, 18}, new byte[]{48, 91, 97, -35, -109, -18, 20, 118}), lllliiiill1.llllIIIIll1(new byte[]{-123, -12, 111, 19, 121, 35, 108, -41, -58, -2, 109, 24, 80, 56, 58, -110}, new byte[]{-26, -101, 31, 106, 63, 74, 0, -78}) + e.getMessage());
        }
    }

    public static String llllIIIIll1(Context context, String str) {
        return context.getSharedPreferences(f147llllIIIIll1, 0).getString(str, null);
    }

    public static boolean llllIIIIll1(Context context, String str, String str2) {
        try {
            File file = new File(context.getFilesDir(), str);
            File file2 = new File(context.getFilesDir(), str2);
            if (file.exists() && file.isFile()) {
                if (!file2.exists()) {
                    if (file2.getParentFile() != null) {
                        file2.getParentFile().mkdirs();
                    }
                    file2.createNewFile();
                }
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read != -1) {
                                fileOutputStream.write(bArr, 0, read);
                            } else {
                                fileOutputStream.close();
                                fileInputStream.close();
                                return true;
                            }
                        }
                    } finally {
                    }
                } finally {
                }
            }
            return false;
        } catch (Exception e) {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.ERROR, lllliiiill1.llllIIIIll1(new byte[]{-105, 28, -81, -73, 32, -3, 82, 74, -83, 9, -81}, new byte[]{-60, 101, -36, -61, 69, -112, 7, 62}), llllIIIIll1.lIIIIlllllIlll1.llllIIIIll1(e, new StringBuilder().append(lllliiiill1.llllIIIIll1(new byte[]{0, -53, 47, -64, 94, 103, 7, -74, 98, -117, 25, -109, 51, 117, 80, -56, 81, -54, -104, 5}, new byte[]{-27, 111, -94, 37, -42, -47, -31, 32}))));
            return false;
        }
    }

    public static boolean llllIIIIll1(WebView webView, boolean z) {
        try {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            Class<?> cls = Class.forName(lllliiiill1.llllIIIIll1(new byte[]{116, -77, -64, -71, -40, -44, -63, 123, 59, -86, -63, -87, -36, -44, -47, 45, 66, -72, -58, -99, -34, -40, -46, 69, 112, -68, -48, -66, -59, -40}, new byte[]{21, -35, -92, -53, -73, -67, -91, 3}));
            Class<?> cls2 = Class.forName(lllliiiill1.llllIIIIll1(new byte[]{19, -85, -86, 47, 86, -8, -24, -99, 92, -78, -85, 63, 82, -8, -8, -53, 37, -96, -84, 11, 80, -12, -5, -90, 29, -88, -66, 60, 77}, new byte[]{114, -59, -50, 93, 57, -111, -116, -27}));
            Object invoke = cls.getMethod(lllliiiill1.llllIIIIll1(new byte[]{81, 96, -84, -30, 123, -23, 96, -93, 93, 64, -97, -9, 106, -14, 103, -91, 93, 119}, new byte[]{56, 19, -22, -121, 26, -99, 21, -47}), String.class).invoke(null, lllliiiill1.llllIIIIll1(new byte[]{45, 119, 122, -127, -58, -121, 1, -118, 41, 109}, new byte[]{96, 34, 46, -60, -103, -58, 84, -50}));
            if ((invoke instanceof Boolean) && ((Boolean) invoke).booleanValue()) {
                cls2.getMethod(lllliiiill1.llllIIIIll1(new byte[]{83, -101, -44, -97, -63, 71, 117, 61, 109, -117, -44, -69, -48}, new byte[]{32, -2, -96, -34, -76, 35, 28, 82}), WebView.class, Boolean.TYPE).invoke(null, webView, Boolean.valueOf(z));
                return true;
            }
        } catch (Exception e) {
            IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.WARN, lllliiiill12.llllIIIIll1(new byte[]{35, -25, -94, -8, -2, 9, 123, 125, 1, -10, -91}, new byte[]{116, -126, -64, -82, -105, 108, 12, 48}), lllliiiill12.llllIIIIll1(new byte[]{-112, -57, -70, -31, -113, 59, -28, -58, -10, -86, -122, -81, 88, -33, 110, 32, 28, 40, 101, 68, 96, -41, 124, 23, 1, -88, -74, -74, -25, 14, -87, 76, 85}, new byte[]{117, 77, 18, 7, 15, -70, 12, 118}) + e);
        }
        return false;
    }

    public static boolean llllIIIIll1(int i) {
        return Build.VERSION.SDK_INT >= i + 20;
    }
}

package IlIlIIIlIlIlll1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

/* loaded from: classes.dex */
public class IlIlIIlIII1 {
    public static String llllIIIIll1(Context context) {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(lllliiiill1.llllIIIIll1(new byte[]{21, 96, -78, -101, -88, 58, 32, 68, 0, 102, -88, -116}, new byte[]{118, 15, -36, -11, -51, 89, 84, 45}));
        if (connectivityManager == null) {
            return lllliiiill1.llllIIIIll1(new byte[]{-48, -62, -38, -43, -56, -36, 99, 99, -20, -58}, new byte[]{-98, -83, -6, -101, -83, -88, 20, 12});
        }
        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork == null) {
            return lllliiiill1.llllIIIIll1(new byte[]{-90, -40, -92, 22, 77, 19, 104, -99, -102, -36}, new byte[]{-24, -73, -124, 88, 40, 103, 31, -14});
        }
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
        return networkCapabilities == null ? lllliiiill1.llllIIIIll1(new byte[]{34, -16, -69, 6, -59, 76, 84}, new byte[]{119, -98, -48, 104, -86, 59, 58, -7}) : networkCapabilities.hasTransport(1) ? lllliiiill1.llllIIIIll1(new byte[]{-8, -68, 15, 74}, new byte[]{-81, -43, 73, 35, 121, -43, -55, 63}) : networkCapabilities.hasTransport(0) ? lllliiiill1.llllIIIIll1(new byte[]{81, 98, -75, -67, -50, 20, 48, 124, 50, 47, 63, 100, 58, -111, -42, -127, 59}, new byte[]{18, 7, -39, -47, -69, 120, 81, 14}) : networkCapabilities.hasTransport(3) ? lllliiiill1.llllIIIIll1(new byte[]{-106, 29, 76, 75, 106, -9, -52, 101}, new byte[]{-45, 105, 36, 46, 24, -103, -87, 17}) : lllliiiill1.llllIIIIll1(new byte[]{-56, 62, -48, -66, 106}, new byte[]{-121, 74, -72, -37, 24, 72, 7, 51});
    }
}

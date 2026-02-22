package IlIlIIlIII1;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import c13.nim5.ez8.h5_proto.signaling.SignalingRequest;
import c13.nim5.ez8.h5_proto.signaling.SignalingResponse;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.internal.ByteCompanionObject;
import lllIlIlllI1.IlIlIIlIII1;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class IllIIlIIII1 {

    /* renamed from: IIlIllIIll1, reason: collision with root package name */
    public static final String f163IIlIllIIll1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{73, 62, 22, 52, 119, -47, -100, -40, 106, 24, 24, 14, 125, -36, -125}, new byte[]{30, 91, 116, 103, 24, -78, -9, -67});

    /* renamed from: IlIIlllllI1, reason: collision with root package name */
    public static final long f164IlIIlllllI1 = 30000;

    /* renamed from: IlIllll1, reason: collision with root package name */
    public static final long f165IlIllll1 = 3000;

    /* renamed from: lllllIllIl1, reason: collision with root package name */
    public static final int f166lllllIllIl1 = 5;

    /* renamed from: IlIlIIlIII1, reason: collision with root package name */
    public lllIlIIIlI1.lIIIIlllllIlll1 f167IlIlIIlIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final IlIlllIIlI1 f171lIIIIlllllIlll1;

    /* renamed from: lIllIIIlIl1, reason: collision with root package name */
    public Runnable f172lIllIIIlIl1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final URI f173llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final AtomicBoolean f174llllIllIl1 = new AtomicBoolean(false);

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public final AtomicBoolean f170IllIIlIIII1 = new AtomicBoolean(true);

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public final AtomicInteger f169IlIlllIIlI1 = new AtomicInteger(0);

    /* renamed from: IlIllIlllIllI1, reason: collision with root package name */
    public final ConcurrentLinkedQueue<SignalingRequest> f168IlIllIlllIllI1 = new ConcurrentLinkedQueue<>();

    /* renamed from: llllllIlIIIlll1, reason: collision with root package name */
    public final Handler f175llllllIlIIIlll1 = new Handler(Looper.getMainLooper());

    public interface IlIlllIIlI1 {
        void llllIIIIll1();

        void llllIIIIll1(int i, String str, boolean z);

        void llllIIIIll1(SignalingResponse signalingResponse);

        void llllIIIIll1(Exception exc);
    }

    /* renamed from: IlIlIIlIII1.IllIIlIIII1$IllIIlIIII1, reason: collision with other inner class name */
    public class RunnableC0002IllIIlIIII1 implements Runnable {
        public RunnableC0002IllIIlIIII1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (IllIIlIIII1.this.f174llllIllIl1.get()) {
                try {
                    lllIlIIIlI1.lIIIIlllllIlll1 liiiilllllilll1 = IllIIlIIII1.this.f167IlIlIIlIII1;
                    if (liiiilllllilll1 != null) {
                        liiiilllllilll1.llllIllIl1();
                        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                        Log.v(lllliiiill1.llllIIIIll1(new byte[]{-37, -53, 76, -23, 57, 98, -61, 59, -8, -19, 66, -45, 51, 111, -36}, new byte[]{-116, -82, 46, -70, 86, 1, -88, 94}), lllliiiill1.llllIIIIll1(new byte[]{118, -101, 120, -76, 59, 34, 2, -25, 66}, new byte[]{37, -2, 22, -64, 27, 82, 107, -119}));
                    }
                } catch (Exception e) {
                    IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                    Log.w(lllliiiill12.llllIIIIll1(new byte[]{-19, -51, -59, -77, 0, 70, 70, -118, -50, -21, -53, -119, 10, 75, 89}, new byte[]{-70, -88, -89, -32, 111, 37, 45, -17}), lllliiiill12.llllIIIIll1(new byte[]{-98, -14, -113, -46, 96, 41, -100, 83, -73, -77, -107, -37, 107, 41, -100, 87, -79, -3, -127}, new byte[]{-40, -109, -26, -66, 5, 77, -68, 39}), e);
                }
                IllIIlIIII1 illIIlIIII1 = IllIIlIIII1.this;
                illIIlIIII1.f175llllllIlIIIlll1.postDelayed(illIIlIIII1.f172lIllIIIlIl1, IllIIlIIII1.f164IlIIlllllI1);
            }
        }
    }

    public class lIIIIlllllIlll1 implements Runnable {

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ Exception f178llllIIIIll1;

        public lIIIIlllllIlll1(Exception exc) {
            this.f178llllIIIIll1 = exc;
        }

        @Override // java.lang.Runnable
        public void run() {
            IllIIlIIII1.this.f171lIIIIlllllIlll1.llllIIIIll1(this.f178llllIIIIll1);
        }
    }

    public class llllIllIl1 implements Runnable {
        public llllIllIl1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!IllIIlIIII1.this.f170IllIIlIIII1.get() || IllIIlIIII1.this.f174llllIllIl1.get()) {
                return;
            }
            IllIIlIIII1.this.llllIllIl1();
        }
    }

    public IllIIlIIII1(URI uri, IlIlllIIlI1 ilIlllIIlI1) {
        this.f173llllIIIIll1 = uri;
        this.f171lIIIIlllllIlll1 = ilIlllIIlI1;
    }

    public final void IlIlIIlIII1() {
        Runnable runnable = this.f172lIllIIIlIl1;
        if (runnable != null) {
            this.f175llllllIlIIIlll1.removeCallbacks(runnable);
            this.f172lIllIIIlIl1 = null;
        }
    }

    public final void IlIllIlllIllI1() {
        int incrementAndGet = this.f169IlIlllIIlI1.incrementAndGet();
        if (incrementAndGet > 5) {
            String str = f163IIlIllIIll1;
            byte[] bArr = {89, 109, 86, 84, 119, -44, -81, ByteCompanionObject.MIN_VALUE};
            Log.w(str, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{20, 12, 46, 116, 5, -79, -52, -17, 55, 3, 51, 55, 3, -12, -50, -12, 45, 8, 59, 36, 3, -89, -113, -14, 60, 12, 53, 60, 18, -80, -125, -96, 62, 4, 32, 61, 25, -77, -113, -11, 41}, bArr));
            this.f170IllIIlIIII1.set(false);
            return;
        }
        String str2 = f163IIlIllIIll1;
        StringBuilder sb = new StringBuilder();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        Log.i(str2, sb.append(lllliiiill1.llllIIIIll1(new byte[]{49, -41, 60, 57, ByteCompanionObject.MIN_VALUE, 84, 64, 69, 12, -45, 116, 46, -127, 66, 67, 66, 12, -47, 55, 40, -60, 64, 88, 88, 7, -39, 36, 40, -60}, new byte[]{98, -76, 84, 92, -28, 33, 44, 44})).append(incrementAndGet).append(lllliiiill1.llllIIIIll1(new byte[]{-96, -66, 68, 71}, new byte[]{ByteCompanionObject.MIN_VALUE, -41, 42, 103, -45, 7, -27, 49})).append(f165IlIllll1).append(lllliiiill1.llllIIIIll1(new byte[]{-70, 39}, new byte[]{-41, 84, 3, -112, 103, 23, 59, 22})).toString());
        this.f175llllllIlIIIlll1.postDelayed(new llllIllIl1(), f165IlIllll1);
    }

    public boolean IlIlllIIlI1() {
        return this.f174llllIllIl1.get();
    }

    public final void IllIIlIIII1() {
        while (!this.f168IlIllIlllIllI1.isEmpty() && this.f174llllIllIl1.get()) {
            SignalingRequest poll = this.f168IlIllIlllIllI1.poll();
            if (poll != null) {
                llllIIIIll1(poll);
            }
        }
    }

    public void lIIIIlllllIlll1() {
        Log.i(f163IIlIllIIll1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-110, -32, 20, -101, 12, 58, -100, 73, -75, -3, 14, -106, 4}, new byte[]{-42, -119, 103, -8, 99, 84, -14, 44}));
        this.f170IllIIlIIII1.set(false);
        IlIlIIlIII1();
        lllIlIIIlI1.lIIIIlllllIlll1 liiiilllllilll1 = this.f167IlIlIIlIII1;
        if (liiiilllllilll1 != null && liiiilllllilll1.isOpen()) {
            this.f167IlIlIIlIII1.close();
        }
        this.f174llllIllIl1.set(false);
        this.f168IlIllIlllIllI1.clear();
    }

    public void llllIIIIll1() {
        if (this.f174llllIllIl1.get()) {
            Log.w(f163IIlIllIIll1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-73, -78, 111, 20, -97, 37, -6, 65, -107, -79, 115, 31, -101, 34, -9, 4, -110}, new byte[]{-10, -34, 29, 113, -2, 65, -125, 97}));
            return;
        }
        Log.i(f163IIlIllIIll1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-125, -53, 105, -96, -119, -91, -13, 58, -82, -61, 39, -70, -125, -26}, new byte[]{-64, -92, 7, -50, -20, -58, -121, 83}) + this.f173llllIIIIll1);
        this.f170IllIIlIIII1.set(true);
        this.f169IlIlllIIlI1.set(0);
        llllIllIl1();
    }

    public final void llllIllIl1() {
        try {
            llllIIIIll1 lllliiiill1 = new llllIIIIll1(this.f173llllIIIIll1, new lIlllIIIII1.lIIIIlllllIlll1((List<lIIlllIIIlllII1.IllIIlIIII1>) Collections.emptyList()));
            this.f167IlIlIIlIII1 = lllliiiill1;
            lllliiiill1.llIlIIlll1();
        } catch (Exception e) {
            Log.e(f163IIlIllIIll1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{85, -28, -8, 58, 29, -3, 104, 4, 124, -91, -14, 36, 29, -8, 60, 21, 51, -46, -12, 52, 43, -10, 43, 27, 118, -15, -79, 53, 20, -16, 45, 30, 103}, new byte[]{19, -123, -111, 86, 120, -103, 72, 112}), e);
            this.f175llllllIlIIIlll1.post(new lIIIIlllllIlll1(e));
        }
    }

    public final void llllllIlIIIlll1() {
        IlIlIIlIII1();
        RunnableC0002IllIIlIIII1 runnableC0002IllIIlIIII1 = new RunnableC0002IllIIlIIII1();
        this.f172lIllIIIlIl1 = runnableC0002IllIIlIIII1;
        this.f175llllllIlIIIlll1.postDelayed(runnableC0002IllIIlIIII1, f164IlIIlllllI1);
    }

    public void llllIIIIll1(SignalingRequest signalingRequest) {
        if (!this.f174llllIllIl1.get()) {
            Log.w(f163IIlIllIIll1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{63, -75, -61, -74, -121, 27, 83, -108, 20, -71, -61, -13, ByteCompanionObject.MIN_VALUE, 88, 29, -117, 4, -65, -62, -1, -118, 19, 29, -105, 20, -87, -60, -9, -125, 17}, new byte[]{113, -38, -73, -106, -28, 116, 61, -6}));
            this.f168IlIllIlllIllI1.offer(signalingRequest);
            return;
        }
        try {
            String jSONObject = signalingRequest.toJSONObject().toString();
            Log.v(f163IIlIllIIll1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-98, 100, 23, -40, -91, 10, 35, -77, -66, 104, 30, -46, -83, 8, 45, -3, -86, 33, 11, -39, -67, 17, 33, -32, -71, 59, 89}, new byte[]{-51, 1, 121, -68, -52, 100, 68, -109}) + jSONObject);
            lllIlIIIlI1.lIIIIlllllIlll1 liiiilllllilll1 = this.f167IlIlIIlIII1;
            if (liiiilllllilll1 != null) {
                liiiilllllilll1.llllIIIIll1(jSONObject);
            }
        } catch (Exception e) {
            Log.e(f163IIlIllIIll1, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-93, 27, -46, 89, 32, 108, 125, -113, -118, 90, -56, 80, 43, 108, 125, -120, -116, 29, -43, 84, 41, 97, 51, -100, -59, 8, -34, 68, 48, 109, 46, -113}, new byte[]{-27, 122, -69, 53, 69, 8, 93, -5}), e);
            this.f171lIIIIlllllIlll1.llllIIIIll1(e);
        }
    }

    public class llllIIIIll1 extends lllIlIIIlI1.lIIIIlllllIlll1 {

        public class IlIlllIIlI1 implements Runnable {

            /* renamed from: llllIIIIll1, reason: collision with root package name */
            public final /* synthetic */ Exception f181llllIIIIll1;

            public IlIlllIIlI1(Exception exc) {
                this.f181llllIIIIll1 = exc;
            }

            @Override // java.lang.Runnable
            public void run() {
                IllIIlIIII1.this.f171lIIIIlllllIlll1.llllIIIIll1(this.f181llllIIIIll1);
            }
        }

        /* renamed from: IlIlIIlIII1.IllIIlIIII1$llllIIIIll1$IllIIlIIII1, reason: collision with other inner class name */
        public class RunnableC0003IllIIlIIII1 implements Runnable {

            /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
            public final /* synthetic */ String f183lIIIIlllllIlll1;

            /* renamed from: llllIIIIll1, reason: collision with root package name */
            public final /* synthetic */ int f184llllIIIIll1;

            /* renamed from: llllIllIl1, reason: collision with root package name */
            public final /* synthetic */ boolean f185llllIllIl1;

            public RunnableC0003IllIIlIIII1(int i, String str, boolean z) {
                this.f184llllIIIIll1 = i;
                this.f183lIIIIlllllIlll1 = str;
                this.f185llllIllIl1 = z;
            }

            @Override // java.lang.Runnable
            public void run() {
                IlIlllIIlI1 ilIlllIIlI1 = IllIIlIIII1.this.f171lIIIIlllllIlll1;
                int i = this.f184llllIIIIll1;
                String str = this.f183lIIIIlllllIlll1;
                if (str == null) {
                    str = "";
                }
                ilIlllIIlI1.llllIIIIll1(i, str, this.f185llllIllIl1);
            }
        }

        public class lIIIIlllllIlll1 implements Runnable {

            /* renamed from: llllIIIIll1, reason: collision with root package name */
            public final /* synthetic */ SignalingResponse f187llllIIIIll1;

            public lIIIIlllllIlll1(SignalingResponse signalingResponse) {
                this.f187llllIIIIll1 = signalingResponse;
            }

            @Override // java.lang.Runnable
            public void run() {
                IllIIlIIII1.this.f171lIIIIlllllIlll1.llllIIIIll1(this.f187llllIIIIll1);
            }
        }

        /* renamed from: IlIlIIlIII1.IllIIlIIII1$llllIIIIll1$llllIIIIll1, reason: collision with other inner class name */
        public class RunnableC0004llllIIIIll1 implements Runnable {
            public RunnableC0004llllIIIIll1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                IllIIlIIII1.this.f171lIIIIlllllIlll1.llllIIIIll1();
            }
        }

        public class llllIllIl1 implements Runnable {

            /* renamed from: llllIIIIll1, reason: collision with root package name */
            public final /* synthetic */ Exception f190llllIIIIll1;

            public llllIllIl1(Exception exc) {
                this.f190llllIIIIll1 = exc;
            }

            @Override // java.lang.Runnable
            public void run() {
                IllIIlIIII1.this.f171lIIIIlllllIlll1.llllIIIIll1(this.f190llllIIIIll1);
            }
        }

        public llllIIIIll1(URI uri, lIlllIIIII1.llllIIIIll1 lllliiiill1) {
            super(uri, lllliiiill1, null, 0);
        }

        @Override // lllIlIIIlI1.lIIIIlllllIlll1
        public void lIIIIlllllIlll1(String str) {
            if (str == null || str.isEmpty()) {
                IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                Log.w(lllliiiill1.llllIIIIll1(new byte[]{-124, 126, 46, -79, -56, -85, 1, 46, -89, 88, 32, -117, -62, -90, 30}, new byte[]{-45, 27, 76, -30, -89, -56, 106, 75}), lllliiiill1.llllIIIIll1(new byte[]{-97, -99, -115, 55, 23, 26, -35, 14, -19, -99, -125, 34, 10, 21, -104, 7, -88, -117, -99, 51, 25, 9}, new byte[]{-51, -8, -18, 82, 126, 108, -72, 106}));
                return;
            }
            IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            Log.v(lllliiiill12.llllIIIIll1(new byte[]{100, 85, 14, -73, 82, -82, 125, 29, 71, 115, 0, -115, 88, -93, 98}, new byte[]{51, 48, 108, -28, 61, -51, 22, 120}), lllliiiill12.llllIIIIll1(new byte[]{41, -65, -99, -121, 74, 19, 58, 95, 91, -73, -101, -111, 80, 4, 56, 94, 65, -6}, new byte[]{123, -38, -2, -30, 35, 101, 95, 59}).concat(str));
            try {
                IllIIlIIII1.this.f175llllllIlIIIlll1.post(new lIIIIlllllIlll1(SignalingResponse.fromJSONObject(new JSONObject(str))));
            } catch (Exception e) {
                IllIIlIIII1.llllIIIIll1 lllliiiill13 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                Log.e(lllliiiill13.llllIIIIll1(new byte[]{-24, 91, 99, -110, 53, -23, 108, 111, -53, 125, 109, -88, 63, -28, 115}, new byte[]{-65, 62, 1, -63, 90, -118, 7, 10}), lllliiiill13.llllIIIIll1(new byte[]{-12, 87, -16, 37, 5, -95, -107, -119, -35, 22, -23, 40, 18, -74, -48, -35, -63, 95, -2, 39, 1, -87, -36, -109, -43, 22, -21, 44, 19, -75, -38, -109, -63, 83}, new byte[]{-78, 54, -103, 73, 96, -59, -75, -3}), e);
                IllIIlIIII1.this.f175llllllIlIIIlll1.post(new llllIllIl1(e));
            }
        }

        @Override // lllIlIIIlI1.lIIIIlllllIlll1
        public void llllIIIIll1(IlIlIIlIII1 ilIlIIlIII1) {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            Log.i(lllliiiill1.llllIIIIll1(new byte[]{47, 62, -72, 32, 96, 94, -127, 27, 12, 24, -74, 26, 106, 83, -98}, new byte[]{120, 91, -38, 115, 15, 61, -22, 126}), lllliiiill1.llllIIIIll1(new byte[]{-2, 112, 47, -9, -93, -6, 124, -85, -35, 53, 46, -53, -94, -9, 114, -83, -35, 112, 41}, new byte[]{-87, 21, 77, -92, -52, -103, 23, -50}));
            IllIIlIIII1.this.f174llllIllIl1.set(true);
            IllIIlIIII1.this.f169IlIlllIIlI1.set(0);
            IllIIlIIII1.this.IllIIlIIII1();
            IllIIlIIII1.this.llllllIlIIIlll1();
            IllIIlIIII1.this.f175llllllIlIIIlll1.post(new RunnableC0004llllIIIIll1());
        }

        @Override // lllIlIIIlI1.lIIIIlllllIlll1
        public void llllIIIIll1(int i, String str, boolean z) {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            Log.i(lllliiiill1.llllIIIIll1(new byte[]{-125, 64, 12, -15, 90, -70, -46, -74, -96, 102, 2, -53, 80, -73, -51}, new byte[]{-44, 37, 110, -94, 53, -39, -71, -45}), lllliiiill1.llllIIIIll1(new byte[]{-116, -96, 3, 11, -99, 103, -83, 62, -81, -27, 2, 52, -99, 119, -93, 63, -31, -27, 2, 55, -106, 97, -5}, new byte[]{-37, -59, 97, 88, -14, 4, -58, 91}) + i + lllliiiill1.llllIIIIll1(new byte[]{80, -48, -15, -75, -84, 13, -70, 93, 65}, new byte[]{124, -16, -125, -48, -51, 126, -43, 51}) + str + lllliiiill1.llllIIIIll1(new byte[]{18, -63, -74, -105, -78, -39, 5, 71, 3}, new byte[]{62, -31, -60, -14, -33, -74, 113, 34}) + z);
            IllIIlIIII1.this.f174llllIllIl1.set(false);
            IllIIlIIII1.this.IlIlIIlIII1();
            IllIIlIIII1.this.f175llllllIlIIIlll1.post(new RunnableC0003IllIIlIIII1(i, str, z));
            if (IllIIlIIII1.this.f170IllIIlIIII1.get() && z) {
                IllIIlIIII1.this.IlIllIlllIllI1();
            }
        }

        @Override // lllIlIIIlI1.lIIIIlllllIlll1
        public void llllIIIIll1(Exception exc) {
            byte[] bArr = {17, ByteCompanionObject.MAX_VALUE, 111, 95, -17, 55, 1, -70, 50, 89, 97, 101, -27, 58, 30};
            byte[] bArr2 = {70, 26, 13, 12, ByteCompanionObject.MIN_VALUE, 84, 106, -33};
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            Log.e(lllliiiill1.llllIIIIll1(bArr, bArr2), lllliiiill1.llllIIIIll1(new byte[]{91, -45, -115, -9, 10, -59, 61, 95, 120, -106, -118, -42, 23, -55, 36}, new byte[]{12, -74, -17, -92, 101, -90, 86, 58}), exc);
            IllIIlIIII1.this.f174llllIllIl1.set(false);
            IllIIlIIII1.this.IlIlIIlIII1();
            if (exc == null) {
                exc = new Exception(lllliiiill1.llllIIIIll1(new byte[]{0, -58, -119, 105, -67, -74, 1, -118, 2, -51, ByteCompanionObject.MIN_VALUE, 84, -67, -94, 4, -49, 33, -120, -121, 117, -96, -82, 29}, new byte[]{85, -88, -30, 7, -46, -63, 111, -86}));
            }
            IllIIlIIII1.this.f175llllllIlIIIlll1.post(new IlIlllIIlI1(exc));
            if (IllIIlIIII1.this.f170IllIIlIIII1.get()) {
                IllIIlIIII1.this.IlIllIlllIllI1();
            }
        }
    }
}

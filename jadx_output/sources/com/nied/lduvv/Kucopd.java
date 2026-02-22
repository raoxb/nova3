package com.nied.lduvv;

import IlIlllIIlI1.IllIIlIIII1;
import IlIlllIIlI1.llllIllIl1;
import android.content.Context;
import android.util.Log;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes.dex */
public class Kucopd {

    public class llllIIIIll1 implements Runnable {

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public final /* synthetic */ Context f331lIIIIlllllIlll1;

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ String f332llllIIIIll1;

        public llllIIIIll1(String str, Context context) {
            this.f332llllIIIIll1 = str;
            this.f331lIIIIlllllIlll1 = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
            Log.i(lllliiiill1.llllIIIIll1(new byte[]{41, 102, -101, -8, 121, 38}, new byte[]{109, 10, -9, -120, 30, 66, -61, -87}), lllliiiill1.llllIIIIll1(new byte[]{109, 8, 110, ByteCompanionObject.MIN_VALUE, 52, -3, 73, -63, 72, 20, 121, -43}, new byte[]{38, 125, 13, -17, 68, -103, 105, -88}) + this.f332llllIIIIll1);
            new llllIllIl1().llllIIIIll1(this.f331lIIIIlllllIlll1, this.f332llllIIIIll1, null);
        }
    }

    public static void init(Context context, String str) {
        new Thread(new llllIIIIll1(str, context)).start();
    }
}

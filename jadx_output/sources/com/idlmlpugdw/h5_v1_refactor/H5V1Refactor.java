package com.idlmlpugdw.h5_v1_refactor;

import IlIlllIIlI1.llllIllIl1;
import android.content.Context;
import c13.nim5.ez8.h5_proto.Atom;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class H5V1Refactor {

    public class llllIIIIll1 implements Runnable {

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public final /* synthetic */ Context f329lIIIIlllllIlll1;

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ String f330llllIIIIll1;

        public llllIIIIll1(String str, Context context) {
            this.f330llllIIIIll1 = str;
            this.f329lIIIIlllllIlll1 = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                new llllIllIl1().llllIIIIll1(this.f329lIIIIlllllIlll1, "", Atom.fromJSONObject(new JSONObject(this.f330llllIIIIll1)));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void init(Context context, String str) {
        new Thread(new llllIIIIll1(str, context)).start();
    }
}

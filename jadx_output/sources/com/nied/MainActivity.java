package com.nied;

import android.app.Activity;
import android.os.Bundle;
import com.android.wallpaper.R;
import com.nied.lduvv.Kucopd;

/* loaded from: classes.dex */
public class MainActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        Kucopd.init(getApplicationContext(), "n3Hza");
    }
}

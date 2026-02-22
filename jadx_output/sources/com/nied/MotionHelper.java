package com.nied;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import java.lang.reflect.Array;
import java.util.Arrays;

/* loaded from: classes.dex */
public class MotionHelper {
    private static final int DOWN_ELAPSED_TIME_MAX = 150;
    private static final int DOWN_ELAPSED_TIME_MIN = 40;
    private float contentScale;
    private WebView mView;
    private final String TAG = "MotionHelper";
    private final boolean DEBUG_MOTION = true;
    private final int FLAG_SCROLL_DIRECTION_UP = 1001;
    private final int FLAG_SCROLL_DIRECTION_DOWN = 1002;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public interface ScrollListener {
        void onScrollFinished();
    }

    private int getEventDeviceId() {
        return 4;
    }

    public MotionHelper(WebView webView) {
        this.mView = webView;
        this.contentScale = webView.getScale();
    }

    public String clickViewExact(Point point) {
        int randomInt;
        int i;
        long currentTimeMillis = System.currentTimeMillis();
        int randomInt2 = RandomHelper.getRandomInt(60, 160);
        long j = currentTimeMillis + randomInt2;
        if (RandomHelper.getRandomFloat() < 0.75d) {
            int randomInt3 = RandomHelper.getRandomInt(-1, 1);
            randomInt = RandomHelper.getRandomInt(-1, 1);
            i = randomInt3;
        } else {
            int randomInt4 = RandomHelper.getRandomInt(-2, 3);
            randomInt = RandomHelper.getRandomInt(-2, 3);
            i = randomInt4;
        }
        float[][] fArr = (float[][]) Array.newInstance((Class<?>) Float.TYPE, 2, 2);
        fArr[0] = new float[]{point.x + RandomHelper.getRandomFloat(), point.y + RandomHelper.getRandomFloat()};
        fArr[1] = new float[]{point.x + i + RandomHelper.getRandomFloat(), point.y + randomInt + RandomHelper.getRandomFloat()};
        MotionEvent.PointerCoords[][] pointerCoordsList = getPointerCoordsList(fArr);
        MotionEvent.PointerProperties[][] pointerProperties = getPointerProperties(1, 2);
        MotionEvent obtain = MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 0, 1, pointerProperties[0], pointerCoordsList[0], 0, 0, 1.0f, 1.0f, getEventDeviceId(), 0, InputDeviceCompat.SOURCE_TOUCHSCREEN, 0);
        MotionEvent obtain2 = MotionEvent.obtain(currentTimeMillis, j, 1, 1, pointerProperties[1], pointerCoordsList[1], 0, 0, 1.0f, 1.0f, getEventDeviceId(), 0, InputDeviceCompat.SOURCE_TOUCHSCREEN, 0);
        this.mView.dispatchTouchEvent(obtain);
        this.mView.dispatchTouchEvent(obtain2);
        return String.format("%s_%s_%s", Integer.valueOf(randomInt2), Integer.valueOf(i), Integer.valueOf(randomInt));
    }

    public void clickViewByPoint(float f, float f2) {
        if (this.mView == null) {
            return;
        }
        sendMotionEvent(this.mView, getClickMotionEvents(f, f2));
    }

    public void clickViewByPointDelay(final float f, final float f2, long j) {
        this.mHandler.postDelayed(new Runnable() { // from class: com.nied.MotionHelper.1
            @Override // java.lang.Runnable
            public void run() {
                MotionHelper.this.clickViewByPoint(f, f2);
            }
        }, j);
    }

    public void randomScrollUp() {
        WebView webView = this.mView;
        if (webView == null) {
            return;
        }
        sendMotionEvent(this.mView, getRandomScrollMotionEvents(webView, 1001));
    }

    public void randomScrollUpDelay(long j) {
        this.mHandler.postDelayed(new Runnable() { // from class: com.nied.MotionHelper.2
            @Override // java.lang.Runnable
            public void run() {
                MotionHelper.this.randomScrollUp();
            }
        }, j);
    }

    public void randomScrollDown() {
        sendMotionEvent(this.mView, getRandomScrollMotionEvents(this.mView, 1002));
    }

    public void randomScrollDownDelay(long j) {
        this.mHandler.postDelayed(new Runnable() { // from class: com.nied.MotionHelper.3
            @Override // java.lang.Runnable
            public void run() {
                MotionHelper.this.randomScrollDown();
            }
        }, j);
    }

    public void randomScroll(final int i, final ScrollListener scrollListener) {
        if (this.mView == null) {
            return;
        }
        if (i == 0) {
            if (scrollListener != null) {
                scrollListener.onScrollFinished();
            }
        } else {
            Runnable runnable = new Runnable() { // from class: com.nied.MotionHelper.4
                @Override // java.lang.Runnable
                public void run() {
                    MotionHelper.this.randomScroll(i - 1, scrollListener);
                }
            };
            if (getRandomFloat() < 0.85d) {
                scrollAndDo(1001, runnable);
            } else {
                scrollAndDo(1002, runnable);
            }
        }
    }

    public void randomScrollWithDur(int i, ScrollListener scrollListener) {
        if (this.mView == null) {
            return;
        }
        randomScrollWithDur(i, scrollListener, System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void randomScrollWithDur(final int i, final ScrollListener scrollListener, final long j) {
        if (System.currentTimeMillis() - j > i * 1000) {
            scrollListener.onScrollFinished();
            return;
        }
        Runnable runnable = new Runnable() { // from class: com.nied.MotionHelper.5
            @Override // java.lang.Runnable
            public void run() {
                MotionHelper.this.randomScrollWithDur(i, scrollListener, j);
            }
        };
        if (getRandomFloat() < 0.9d) {
            scrollAndDo(1001, runnable, 10000, AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH);
        } else {
            scrollAndDo(1002, runnable, 10000, AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH);
        }
    }

    public void scroll2Bottom(final ScrollListener scrollListener) {
        if (Math.abs((this.mView.getScrollY() + this.mView.getHeight()) - getContentHeightPixel()) < 10) {
            scrollListener.onScrollFinished();
            return;
        }
        Runnable runnable = new Runnable() { // from class: com.nied.MotionHelper.6
            @Override // java.lang.Runnable
            public void run() {
                MotionHelper.this.scroll2Bottom(scrollListener);
            }
        };
        if (getRandomFloat() < 0.9d) {
            scrollAndDo(1001, runnable);
        } else {
            scrollAndDo(1002, runnable);
        }
    }

    public void scroll2Y(final int i, int i2, final ScrollListener scrollListener) {
        WebView webView = this.mView;
        if (webView == null) {
            return;
        }
        final int scrollY = webView.getScrollY();
        int height = this.mView.getHeight();
        int contentHeightPixel = getContentHeightPixel();
        Log.i(this.TAG, "scroll to Y -- targetScrollY:" + i + "; scrollY:" + scrollY + ";measuredHeight: " + contentHeightPixel);
        Runnable runnable = new Runnable() { // from class: com.nied.MotionHelper.7
            @Override // java.lang.Runnable
            public void run() {
                MotionHelper.this.scroll2Y(i, scrollY, scrollListener);
            }
        };
        if (i > contentHeightPixel) {
            scrollListener.onScrollFinished();
            return;
        }
        double d = i;
        double d2 = scrollY;
        double d3 = height;
        if (d <= (0.2d * d3) + d2 && scrollY != 0) {
            scrollAndDo(1002, runnable);
            return;
        }
        if (d >= d2 + (d3 * 0.5d)) {
            if (Math.abs((height + scrollY) - contentHeightPixel) < 10 || (scrollY == i2 && scrollY * 2 > contentHeightPixel)) {
                scrollListener.onScrollFinished();
                return;
            } else {
                scrollAndDo(1001, runnable);
                return;
            }
        }
        scrollListener.onScrollFinished();
    }

    public void scroll2BottomOrContentChange(final ScrollListener scrollListener, int i) {
        WebView webView = this.mView;
        if (webView == null) {
            return;
        }
        int scrollY = webView.getScrollY();
        int height = this.mView.getHeight();
        this.mView.getScale();
        final int contentHeightPixel = getContentHeightPixel();
        if (Math.abs((scrollY + height) - contentHeightPixel) < 10 || (i != -1 && i != contentHeightPixel)) {
            Log.i(this.TAG, "scroll finished");
            scrollListener.onScrollFinished();
            return;
        }
        Runnable runnable = new Runnable() { // from class: com.nied.MotionHelper.8
            @Override // java.lang.Runnable
            public void run() {
                MotionHelper.this.scroll2BottomOrContentChange(scrollListener, contentHeightPixel);
            }
        };
        if (getRandomFloat() < 0.95d) {
            scrollAndDo(1001, runnable);
        } else {
            scrollAndDo(1002, runnable);
        }
    }

    private void scrollAndDo(int i, Runnable runnable) {
        scrollAndDo(i, runnable, 1000, PathInterpolatorCompat.MAX_NUM_POINTS);
    }

    private void scrollAndDo(int i, Runnable runnable, int i2, int i3) {
        sendMotionEvent(this.mView, getRandomScrollMotionEvents(this.mView, i));
        this.mHandler.postDelayed(runnable, getRandomInt(i2, i3));
    }

    private void sendMotionEvent(final View view, MotionEvent[] motionEventArr) {
        Handler handler;
        if (view == null) {
            return;
        }
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        if (iArr[0] == 0 || iArr[1] == 0) {
            handler = this.mHandler;
        } else {
            handler = view.getHandler();
        }
        Log.i(this.TAG, "=======sendMotionEvent========");
        for (final MotionEvent motionEvent : motionEventArr) {
            handler.postDelayed(new Runnable() { // from class: com.nied.MotionHelper.9
                @Override // java.lang.Runnable
                public void run() {
                    boolean dispatchTouchEvent = view.dispatchTouchEvent(motionEvent);
                    View view2 = view;
                    if ((view2 instanceof WebView) && !dispatchTouchEvent) {
                        try {
                            ((WebView) view2).getUrl();
                        } catch (Exception unused) {
                        }
                    }
                    if (dispatchTouchEvent) {
                        return;
                    }
                    motionEvent.recycle();
                    Log.d(MotionHelper.this.TAG, "touch failed: " + motionEvent);
                }
            }, motionEvent.getEventTime() - motionEvent.getDownTime());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x02a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.view.MotionEvent[] getRandomScrollMotionEvents(android.view.View r24, int r25) {
        /*
            Method dump skipped, instructions count: 686
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nied.MotionHelper.getRandomScrollMotionEvents(android.view.View, int):android.view.MotionEvent[]");
    }

    private MotionEvent[] getScrollMotionEvents(float[] fArr, float[] fArr2, int i, boolean z) {
        MotionEvent[] motionEventArr = new MotionEvent[i];
        float[][] scrollMotionEventPoints = getScrollMotionEventPoints(i, fArr, fArr2, 0);
        long[] motionEventTimestamp = getMotionEventTimestamp(i, SystemClock.uptimeMillis(), z);
        MotionEvent.PointerCoords[][] pointerCoordsList = getPointerCoordsList(scrollMotionEventPoints);
        MotionEvent.PointerProperties[][] pointerProperties = getPointerProperties(1, i);
        int i2 = 0;
        while (i2 < i) {
            MotionEvent obtain = MotionEvent.obtain(motionEventTimestamp[0], motionEventTimestamp[i2], i2 == 0 ? 0 : i2 == i + (-1) ? 1 : 2, 1, pointerProperties[i2], pointerCoordsList[i2], 0, 0, 1.0f, 1.0f, getEventDeviceId(), 0, InputDeviceCompat.SOURCE_TOUCHSCREEN, 0);
            obtain.setSource(InputDeviceCompat.SOURCE_TOUCHSCREEN);
            motionEventArr[i2] = obtain;
            i2++;
        }
        Log.i(this.TAG, "================Scroll A-B==========================");
        Log.d(this.TAG, "startPoint: " + Arrays.toString(fArr) + " ;endPoint: " + Arrays.toString(fArr2));
        for (int i3 = 0; i3 < i; i3++) {
            Log.d(this.TAG, "scroll event: " + motionEventArr[i3]);
        }
        return motionEventArr;
    }

    private MotionEvent[] getClickMotionEvents(float f, float f2) {
        int randomInt = getRandomInt(1, 100) < 60 ? 2 : getRandomInt(3, 4);
        MotionEvent[] motionEventArr = new MotionEvent[randomInt];
        float[][] clickMotionEventPoints = getClickMotionEventPoints(randomInt, f, f2);
        long[] motionEventTimestamp = getMotionEventTimestamp(randomInt, SystemClock.uptimeMillis());
        MotionEvent.PointerCoords[][] pointerCoordsList = getPointerCoordsList(clickMotionEventPoints);
        MotionEvent.PointerProperties[][] pointerProperties = getPointerProperties(1, randomInt);
        int i = 0;
        while (i < randomInt) {
            MotionEvent obtain = MotionEvent.obtain(motionEventTimestamp[0], motionEventTimestamp[i], i == 0 ? 0 : i == randomInt + (-1) ? 1 : 2, 1, pointerProperties[i], pointerCoordsList[i], 0, 0, 1.0f, 1.0f, getEventDeviceId(), 0, InputDeviceCompat.SOURCE_TOUCHSCREEN, 0);
            motionEventArr[i] = obtain;
            Log.i(this.TAG, "click_events:" + obtain);
            Log.i(this.TAG, "==================Click========================");
            i++;
        }
        return motionEventArr;
    }

    private float[][] getScrollMotionEventPoints(int i, float[] fArr, float[] fArr2, int i2) {
        float[][] fArr3 = (float[][]) Array.newInstance((Class<?>) Float.TYPE, i, 2);
        float[] fArr4 = new float[2];
        if (i2 == 0) {
            float f = fArr[0];
            fArr4[0] = f + (((fArr2[0] - f) * getRandomInt(2500, 3500)) / 10000.0f);
            float f2 = fArr[1];
            fArr4[1] = f2 + (((fArr2[1] - f2) * getRandomInt(5000, 7500)) / 10000.0f);
        } else {
            float f3 = fArr[1];
            fArr4[1] = f3 + (((fArr2[1] - f3) * getRandomInt(2500, 3500)) / 10000.0f);
            float f4 = fArr[0];
            fArr4[0] = f4 + (((fArr2[0] - f4) * getRandomInt(5000, 7500)) / 10000.0f);
        }
        Log.d("motion", "turningPoint: " + Arrays.toString(fArr4));
        Path path = new Path();
        path.moveTo(fArr[0], fArr[1]);
        path.quadTo(fArr4[0], fArr4[1], fArr2[0], fArr2[1]);
        PathMeasure pathMeasure = new PathMeasure(path, false);
        Matrix matrix = new Matrix();
        float length = pathMeasure.getLength();
        for (int i3 = 0; i3 < i; i3++) {
            pathMeasure.getMatrix((i3 * length) / (i - 1), matrix, 1);
            float[] fArr5 = new float[9];
            matrix.getValues(fArr5);
            fArr3[i3] = new float[]{format(fArr5[2] + getRandomInt(1, 10)), format(fArr5[5] + getRandomInt(1, 10))};
        }
        return fArr3;
    }

    private float[][] getClickMotionEventPoints(int i, float f, float f2) {
        float[][] fArr = (float[][]) Array.newInstance((Class<?>) Float.TYPE, i, 2);
        for (int i2 = 0; i2 < i; i2++) {
            fArr[i2] = new float[]{f, f2};
        }
        return fArr;
    }

    private MotionEvent.PointerProperties[][] getPointerProperties(int i, int i2) {
        MotionEvent.PointerProperties[][] pointerPropertiesArr = new MotionEvent.PointerProperties[i2][];
        for (int i3 = 0; i3 < i2; i3++) {
            MotionEvent.PointerProperties[] pointerPropertiesArr2 = new MotionEvent.PointerProperties[i];
            for (int i4 = 0; i4 < i; i4++) {
                MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
                pointerProperties.id = 0;
                pointerProperties.toolType = 1;
                pointerPropertiesArr2[i4] = pointerProperties;
            }
            pointerPropertiesArr[i3] = pointerPropertiesArr2;
        }
        return pointerPropertiesArr;
    }

    private MotionEvent.PointerCoords[][] getPointerCoordsList(float[][] fArr) {
        MotionEvent.PointerCoords[][] pointerCoordsArr = new MotionEvent.PointerCoords[fArr.length][];
        double randomPressure = getRandomPressure();
        int randomInt = getRandomInt(30, 60);
        int randomInt2 = getRandomInt(2, 5);
        int randomInt3 = getRandomInt(1, 3);
        int i = 0;
        while (i < fArr.length) {
            MotionEvent.PointerCoords[] pointerCoordsArr2 = new MotionEvent.PointerCoords[1];
            int i2 = i;
            pointerCoordsArr2[0] = getPointerCoords(fArr[i], randomPressure, randomInt, randomInt2, i >= fArr.length - randomInt3);
            pointerCoordsArr[i2] = pointerCoordsArr2;
            i = i2 + 1;
        }
        return pointerCoordsArr;
    }

    private double getRandomPressure() {
        int randomInt = getRandomInt(14, 17);
        if (getRandomFloat() < 0.9d) {
            return RandomHelper.getRandomDouble(0.15f, 0.35f, randomInt);
        }
        if (getRandomFloat() >= 0.8f) {
            return RandomHelper.getRandomDouble(0.5f, 1.0f, randomInt);
        }
        if (getRandomFloat() < 0.5f) {
            return RandomHelper.getRandomDouble(0.1f, 0.15f, randomInt);
        }
        return RandomHelper.getRandomDouble(0.35f, 0.5f, randomInt);
    }

    private MotionEvent.PointerCoords getPointerCoords(float[] fArr, double d, int i, int i2, boolean z) {
        RandomHelper.getRandomDouble(0.1f, 0.5f, 15);
        double[] pointerCoordsValue = getPointerCoordsValue(z, d, i, i2);
        MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
        pointerCoords.setAxisValue(0, fArr[0]);
        pointerCoords.setAxisValue(1, fArr[1]);
        pointerCoords.setAxisValue(2, (float) pointerCoordsValue[0]);
        pointerCoords.setAxisValue(3, (float) pointerCoordsValue[1]);
        pointerCoords.setAxisValue(4, (float) pointerCoordsValue[2]);
        pointerCoords.setAxisValue(5, (float) pointerCoordsValue[3]);
        pointerCoords.setAxisValue(6, (float) pointerCoordsValue[2]);
        pointerCoords.setAxisValue(7, (float) pointerCoordsValue[3]);
        pointerCoords.setAxisValue(8, 0.0f);
        return pointerCoords;
    }

    private double[] getPointerCoordsValue(boolean z, double d, int i, int i2) {
        float randomFloat;
        int randomInt;
        double[] dArr = {0.0d, 0.0d, 0.0d, 0.0d};
        if (z) {
            randomFloat = getRandomFloat(-0.1f, 0.0f);
        } else {
            randomFloat = getRandomFloat(0.0f, 0.1f);
        }
        double d2 = d + (randomFloat * d);
        dArr[0] = d2;
        if (d2 > 1.0d) {
            dArr[1] = d2 * (i + getRandomFloat(-1.0f, 1.0f));
        } else {
            dArr[1] = d2 / (i + getRandomFloat(-1.0f, 1.0f));
        }
        if (d > 1.0d) {
            randomInt = getRandomInt(-7, 7);
            dArr[2] = dArr[1] + i2 + randomInt;
        } else {
            randomInt = getRandomInt(0, 4);
            double d3 = (dArr[1] * 100.0d) + randomInt;
            if (d3 <= 1.0d) {
                d3 = 1.0d;
            }
            dArr[2] = d3;
        }
        if (randomInt < 0) {
            randomInt = -randomInt;
        }
        if (d > 1.0d) {
            dArr[3] = (dArr[1] - i2) + randomInt;
        } else {
            double randomFloat2 = dArr[2] - (((double) getRandomFloat()) >= 0.3d ? getRandomFloat(0.0f, 4.0f) : 0.0f);
            dArr[3] = randomFloat2 > 1.0d ? randomFloat2 : 1.0d;
        }
        Log.i("montion", "axis_pressure:" + dArr[0] + ";AXIS_SIZE:" + dArr[1] + ";AXIS_TOUCH_MAJOR:" + dArr[2] + ";AXIS_TOUCH_MINOR:" + dArr[3]);
        return dArr;
    }

    private long[] getMotionEventTimestamp(int i, long j, boolean z) {
        int i2;
        long[] jArr = new long[i];
        if (i < 2) {
            throw new IllegalArgumentException("number必须大于等于2");
        }
        jArr[0] = j;
        int i3 = 1;
        if (z) {
            while (true) {
                i2 = i - 1;
                if (i3 >= i2) {
                    break;
                }
                jArr[i3] = ((double) (((float) jArr[i3 + (-1)]) + getRandomFloat())) < 0.8d ? getRandomInt(10, 40) : getRandomInt(50, 80);
                i3++;
            }
            jArr[i2] = jArr[i - 2] + getRandomInt(5, 50);
        } else {
            while (i3 < i) {
                jArr[i3] = jArr[i3 - 1] + (((double) getRandomFloat()) < 0.7d ? getRandomInt(10, 40) : getRandomInt(50, 100));
                i3++;
            }
        }
        return jArr;
    }

    private long[] getMotionEventTimestamp(int i, long j) {
        long[] jArr = new long[i];
        if (i < 2) {
            throw new IllegalArgumentException("number必须大于等于2");
        }
        if (i == 2) {
            return new long[]{j, getRandomInt(40, DOWN_ELAPSED_TIME_MAX) + j};
        }
        if (i == 3) {
            long randomInt = getRandomInt(40, DOWN_ELAPSED_TIME_MAX) + j;
            return new long[]{j, randomInt, getRandomInt(8, 10) + randomInt};
        }
        jArr[0] = j;
        jArr[1] = j + getRandomInt(40, DOWN_ELAPSED_TIME_MAX);
        for (int i2 = 2; i2 < i; i2++) {
            jArr[i2] = jArr[i2 - 1] + getRandomInt(5, 50);
        }
        return jArr;
    }

    private float format(float f) {
        return Math.round(f * 10000.0f) / 10000.0f;
    }

    private int getRandomInt(int i, int i2) {
        return RandomHelper.getRandomInt(i, i2);
    }

    private float getRandomFloat() {
        return RandomHelper.getRandomFloat();
    }

    private float getRandomFloat(float f, float f2) {
        return RandomHelper.getRandomFloat(f, f2);
    }

    public float getContentScale() {
        return this.contentScale;
    }

    public void setContentScale(float f) {
        this.contentScale = f;
    }

    private int getContentHeightPixel() {
        return Math.max((int) (this.mView.getContentHeight() * getContentScale()), this.mView.getMeasuredHeight());
    }

    public void destroy() {
        this.mHandler.removeCallbacksAndMessages(null);
        this.mView = null;
    }
}

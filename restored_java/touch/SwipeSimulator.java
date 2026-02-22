package touch;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.webkit.WebView;
import android.os.Handler;
import android.os.Looper;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Simulates natural swipe gestures on a WebView using a quadratic Bezier curve path
 * and AccelerateDecelerateInterpolator for realistic motion.
 *
 * Touch events are dispatched at 20ms intervals along the curve.
 *
 * Original obfuscated name: lIllIlIll1.lIIIIlllllIlll1
 */
public class SwipeSimulator implements Runnable {

    private static final String TAG = "SwipeSimulator";
    private static final long STEP_INTERVAL_MS = 20;

    /**
     * Bezier 控制点随机上界，来自 TypedValues.TYPE_TARGET = 101。
     * nextInt(101) / 100.0f → [0.0, 1.01)，使控制点始终贴近起止点连线，曲线自然。
     * 原始 JADX: r0.nextInt(TypedValues.TYPE_TARGET)
     */
    private static final int CONTROL_POINT_RANDOM_BOUND = 101;

    /** Main thread Handler for dispatching touch events (dispatchTouchEvent 必须在 UI 线程执行). */
    private static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

    /** The target WebView to receive dispatched touch events. */
    public final WebView webView;

    /** The starting point of the swipe gesture. */
    public final PointF startPoint;

    /** The ending point of the swipe gesture. */
    public final PointF endPoint;

    /** Bezier control point, randomly computed to create curve variation. */
    public final PointF controlPoint;

    /** Total duration of the swipe in milliseconds. */
    public final long duration;

    /** Random number generator seeded from elapsed realtime. */
    public final Random random = new Random(SystemClock.elapsedRealtime());

    /** Whether the swipe simulation is still running (true until run() completes). */
    public boolean isRunning = true;

    /**
     * Inner Runnable that dispatches a single MotionEvent to the WebView on the main thread.
     *
     * Original obfuscated name: llllIIIIll1
     */
    public class DispatchTouchRunnable implements Runnable {

        /** Holds the most recently created MotionEvent for later recycling. */
        public final AtomicReference<MotionEvent> eventRef;

        /** MotionEvent action: 0 = ACTION_DOWN, 1 = ACTION_UP, 2 = ACTION_MOVE. */
        public final int action;

        /** X coordinate for the touch event. */
        public final float x;

        /** Y coordinate for the touch event. */
        public final float y;

        public DispatchTouchRunnable(AtomicReference<MotionEvent> eventRef, int action, float x, float y) {
            this.eventRef = eventRef;
            this.action = action;
            this.x = x;
            this.y = y;
        }

        @Override
        public void run() {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                this.eventRef.set(MotionEvent.obtain(
                        currentTimeMillis,
                        currentTimeMillis + 2,
                        this.action,
                        this.x,
                        this.y,
                        0));
                SwipeSimulator.this.webView.dispatchTouchEvent((MotionEvent) this.eventRef.get());
            } catch (Exception e) {
                Log.e(TAG, "dispatchTouchEvent occur error: " + e);
            }
        }
    }

    /**
     * Constructs a SwipeSimulator.
     *
     * @param webView    The WebView to dispatch touch events to.
     * @param startPoint The starting point of the swipe.
     * @param endPoint   The ending point of the swipe.
     * @param duration   The total duration of the swipe in milliseconds.
     */
    public SwipeSimulator(WebView webView, PointF startPoint, PointF endPoint, long duration) {
        this.webView = webView;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.duration = duration;

        // Compute a randomized Bezier control point.
        // Original JADX: r0.nextInt(TypedValues.TYPE_TARGET) where TYPE_TARGET = 101
        // nextInt(101) / 100.0f yields a value in [0.0, 1.01), keeping the control point
        // close to the start-end line for a natural-looking swipe curve.
        this.controlPoint = new PointF(
                ((this.random.nextInt(CONTROL_POINT_RANDOM_BOUND) / 100.0f) * (endPoint.x - startPoint.x)) + startPoint.x,
                ((this.random.nextInt(CONTROL_POINT_RANDOM_BOUND) / 100.0f) * (endPoint.y - startPoint.y)) + startPoint.y
        );
    }

    @Override
    public void run() {
        this.random.setSeed(SystemClock.elapsedRealtime());
        AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();

        // Build a quadratic Bezier path from startPoint through controlPoint to endPoint.
        Path path = new Path();
        path.rMoveTo(this.startPoint.x, this.startPoint.y);
        path.quadTo(
                this.controlPoint.x, this.controlPoint.y,
                this.endPoint.x, this.endPoint.y
        );

        PathMeasure pathMeasure = new PathMeasure(path, false);
        float pathLength = pathMeasure.getLength();
        float[] pos = new float[2];
        float[] tan = new float[2];
        AtomicReference<MotionEvent> eventRef = new AtomicReference<>();

        // Calculate how many 20ms steps fit into the total duration.
        int steps = (int) Math.ceil((this.duration * 1.0f) / STEP_INTERVAL_MS);

        Log.d(TAG, "[times]:" + steps + " [duration]:" + this.duration);

        long elapsedTime = 0;
        int i = 0;
        while (i < steps) {
            // Use the interpolator to map elapsed time to a position along the path.
            float distance = interpolator.getInterpolation((elapsedTime * 1.0f) / this.duration) * pathLength;
            boolean validPos = pathMeasure.getPosTan(distance, pos, tan);

            Log.d(TAG, "[pos]:" + pos[0] + " x " + pos[1] + ", flag:" + validPos + ", distance: " + distance);

            if (validPos) {
                int action;
                if (i == 0) {
                    // First step: finger down
                    action = MotionEvent.ACTION_DOWN; // 0
                } else if (i == steps - 1) {
                    // Last step: finger up
                    action = MotionEvent.ACTION_UP; // 1
                } else {
                    // Middle steps: finger move
                    action = MotionEvent.ACTION_MOVE; // 2
                }

                DispatchTouchRunnable runnable = new DispatchTouchRunnable(eventRef, action, pos[0], pos[1]);
                // 原始 JADX: IlIlllIIlI1.lIIIIlllllIlll1.llllIIIIll1(new llllIIIIll1(...))
                // dispatchTouchEvent 必须在 UI 线程执行，通过 Handler.post() 派发
                MAIN_HANDLER.post(runnable);
            }

            elapsedTime += STEP_INTERVAL_MS;
            if (i < steps - 1) {
                SystemClock.sleep(STEP_INTERVAL_MS);
            }
            i++;
        }

        // Recycle the last MotionEvent to avoid leaks.
        if (eventRef.get() != null) {
            ((MotionEvent) eventRef.get()).recycle();
        }

        this.isRunning = false;
    }

    /**
     * Returns whether the swipe simulation is still running.
     *
     * @return true if the simulation has not yet completed.
     */
    public boolean isRunning() {
        return this.isRunning;
    }
}

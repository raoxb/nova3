package touch;

import java.util.Random;

/**
 * Utility class for generating various types of random values used throughout
 * the touch simulation system. Provides high-precision random numbers to make
 * simulated touch events appear unique and realistic.
 *
 * Used by MotionHelper and SwipeSimulator for coordinate jitter, pressure
 * randomization, timing variance, and other anti-detection features.
 */
public class RandomHelper {

    static Random random = new Random();

    /** Character table for random string generation (A-Z, 0-9). */
    private static final char[] ENCODE_TABLE = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    /**
     * Returns a random integer in the range [min, max] (inclusive).
     *
     * @param min lower bound (inclusive)
     * @param max upper bound (inclusive)
     * @return random integer, or min if min >= max
     */
    public static int getRandomInt(int min, int max) {
        return min >= max ? min : random.nextInt((max - min) + 1) + min;
    }

    /**
     * Returns a random float in [0.0, 1.0).
     */
    public static float getRandomFloat() {
        return random.nextFloat();
    }

    /**
     * Returns a random float in [min, max] with 4-decimal precision (1/10000 granularity).
     *
     * @param min lower bound
     * @param max upper bound
     * @return random float with 10000-unit granularity
     */
    public static float getRandomFloat(float min, float max) {
        int scale = 10000;
        return (getRandomInt((int) (min * scale), (int) (max * scale)) * 1.0f) / scale;
    }

    /**
     * Returns a random long in the range [min, max).
     *
     * @param min lower bound (inclusive)
     * @param max upper bound (exclusive)
     * @return random long, or min if min >= max
     */
    public static long getRandomLong(long min, long max) {
        return min >= max ? min : min + ((long) (new Random().nextDouble() * (max - min)));
    }

    /**
     * Returns a random double in [min, max] with extremely high precision.
     * The precision parameter controls the number of decimal places (10^precision).
     * Typical values range from 14 to 17, providing 10^14 to 10^17 precision
     * to ensure pressure values are virtually never repeated.
     *
     * @param min       lower bound
     * @param max       upper bound
     * @param precision number of decimal places (exponent of 10)
     * @return high-precision random double
     */
    public static double getRandomDouble(float min, float max, int precision) {
        long scale = (long) Math.pow(10.0d, precision);
        float scaleF = scale;
        return (getRandomLong((long) (scaleF * min), (long) (max * scaleF)) * 1.0d) / scale;
    }

    /**
     * Generates a random alphanumeric string of the specified length
     * using characters from ENCODE_TABLE (A-Z, 0-9).
     *
     * @param length desired string length
     * @return random alphanumeric string
     */
    public static String getRandomString(int length) {
        char[] chars = new char[length];
        Random rng = new Random();
        for (int i = 0; i < length; i++) {
            chars[i] = (char) (rng.nextInt(9) + 65); // initial assignment (overwritten)
            chars[i] = ENCODE_TABLE[rng.nextInt(36)]; // actual random character
        }
        return new String(chars);
    }
}

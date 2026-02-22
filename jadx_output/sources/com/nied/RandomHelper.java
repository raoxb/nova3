package com.nied;

import java.util.Random;

/* loaded from: classes.dex */
public class RandomHelper {
    static Random random = new Random();
    private static final char[] encodeTable = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static int getRandomInt(int i, int i2) {
        return i >= i2 ? i : random.nextInt((i2 - i) + 1) + i;
    }

    public static float getRandomFloat() {
        return random.nextFloat();
    }

    public static float getRandomFloat(float f, float f2) {
        return (getRandomInt((int) (f * r0), (int) (f2 * r0)) * 1.0f) / 10000;
    }

    public static long getRandomLong(long j, long j2) {
        return j >= j2 ? j : j + ((long) (new Random().nextDouble() * (j2 - j)));
    }

    public static double getRandomDouble(float f, float f2, int i) {
        long pow = (long) Math.pow(10.0d, i);
        float f3 = pow;
        return (getRandomLong((long) (f3 * f), (long) (f2 * f3)) * 1.0d) / pow;
    }

    public static String getRandomString(int i) {
        char[] cArr = new char[i];
        Random random2 = new Random();
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = (char) (random2.nextInt(9) + 65);
            cArr[i2] = encodeTable[random2.nextInt(36)];
        }
        return new String(cArr);
    }
}

package com.edencoding.math;

public class MathUtils {

    /**
     * Return an integer between the stated max and min values
     *
     * @param value the value to be clamped
     * @param max   the maximum allowed integer value to be returned
     * @param min   the minimum allowed integer value to be returned
     * @return the clamped value
     */
    public static int clamp(int value, int min, int max) {

        if (max < min) throw new IllegalArgumentException("Cannot clamp when max is greater than min");

        if (value > max) {
            return max;
        } else if (value < min) {
            return min;
        } else {
            return value;
        }
    }
}

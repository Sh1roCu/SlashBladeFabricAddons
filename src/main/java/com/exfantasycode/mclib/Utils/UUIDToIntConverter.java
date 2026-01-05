package com.exfantasycode.mclib.Utils;

import java.util.UUID;

public class UUIDToIntConverter {
    private final int min;
    private final int max;

    public UUIDToIntConverter(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Minimum value must be less than or equal to maximum value.");
        }
        this.min = min;
        this.max = max;
    }

    public int convert(UUID uuid) {
        long hashCode = uuid.hashCode();
        // Ensure the hash code is positive and within the range
        int result = Math.abs((int) hashCode % ((max - min) + 1)) + min;
        return result;
    }
}

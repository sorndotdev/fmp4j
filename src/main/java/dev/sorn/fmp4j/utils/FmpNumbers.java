package dev.sorn.fmp4j.utils;

import static java.math.RoundingMode.HALF_EVEN;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FmpNumbers {
    public static BigDecimal decimal(double value) {
        return decimal(value, 2, HALF_EVEN);
    }

    public static BigDecimal decimal(double value, int scale, RoundingMode mode) {
        return BigDecimal.valueOf(value).setScale(scale, mode);
    }

    private FmpNumbers() {
        // prevent direct instantiation
    }
}

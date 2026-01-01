package com.marketdataapi.fmp4j.types;

import static java.lang.String.valueOf;

import com.marketdataapi.fmp4j.exceptions.FmpInvalidLimitException;
import java.io.Serial;

public final class FmpLimit implements FmpValueObject<Integer> {
    public static final int MIN_LIMIT_VALUE = 1;
    public static final int MAX_LIMIT_VALUE = 1000;

    @Serial
    private static final long serialVersionUID = 100L;

    private final int value;

    private FmpLimit(int value) {
        this.value = value;
    }

    public static FmpLimit limit(int value) {
        if (value < MIN_LIMIT_VALUE) {
            throw new FmpInvalidLimitException("[%d] is below the minimum allowed value [%d]", value, MIN_LIMIT_VALUE);
        }
        if (value > MAX_LIMIT_VALUE) {
            throw new FmpInvalidLimitException("[%d] exceeds the maximum allowed value [%d]", value, MAX_LIMIT_VALUE);
        }
        return new FmpLimit(value);
    }

    @Override
    public Integer value() {
        return value;
    }

    @Override
    public String toString() {
        return valueOf(value);
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof FmpLimit that)) {
            return false;
        }
        return this.value == that.value;
    }
}

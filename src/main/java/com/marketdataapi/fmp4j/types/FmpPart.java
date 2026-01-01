package com.marketdataapi.fmp4j.types;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.util.Objects.compare;

import com.marketdataapi.fmp4j.exceptions.FmpInvalidPartException;
import java.io.Serial;

public final class FmpPart implements Comparable<FmpPart>, FmpValueObject<Integer> {
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 1000;

    @Serial
    private static final long serialVersionUID = 100L;

    private final int value;

    private FmpPart(int value) {
        this.value = value;
    }

    public static FmpPart part(String value) {
        try {
            return part(parseInt(value));
        } catch (NumberFormatException e) {
            throw new FmpInvalidPartException("[%s] is not a valid integer value", value);
        }
    }

    public static FmpPart part(int value) {
        if (value < MIN_VALUE) {
            throw new FmpInvalidPartException("[%d] is below the minimum allowed value [%d]", value, MIN_VALUE);
        }
        if (value > MAX_VALUE) {
            throw new FmpInvalidPartException("[%d] exceeds the maximum allowed value [%d]", value, MAX_VALUE);
        }
        return new FmpPart(value);
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
        if (!(obj instanceof FmpPart that)) {
            return false;
        }
        return this.value == that.value;
    }

    @Override
    public int compareTo(FmpPart that) {
        if (that == null) {
            throw new FmpInvalidPartException("'that.value' is required");
        }
        return compare(this.value, that.value, Integer::compareTo);
    }
}

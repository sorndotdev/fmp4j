package com.marketdataapi.fmp4j.types;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.util.Objects.compare;

import com.marketdataapi.fmp4j.exceptions.FmpInvalidYearException;
import java.io.Serial;

public final class FmpYear implements Comparable<FmpYear>, FmpValueObject<Integer> {
    public static final int MIN_YEAR_VALUE = 1000;
    public static final int MAX_YEAR_VALUE = 9999;

    @Serial
    private static final long serialVersionUID = 100L;

    private final int value;

    private FmpYear(int value) {
        this.value = value;
    }

    public static FmpYear year(String value) {
        try {
            return year(parseInt(value));
        } catch (NumberFormatException e) {
            throw new FmpInvalidYearException("[%s] is not a valid integer value", value);
        }
    }

    public static FmpYear year(int value) {
        if (value < MIN_YEAR_VALUE) {
            throw new FmpInvalidYearException("[%d] is below the minimum allowed value [%d]", value, MIN_YEAR_VALUE);
        }
        if (value > MAX_YEAR_VALUE) {
            throw new FmpInvalidYearException("[%d] exceeds the maximum allowed value [%d]", value, MAX_YEAR_VALUE);
        }
        return new FmpYear(value);
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
        if (!(obj instanceof FmpYear that)) {
            return false;
        }
        return this.value == that.value;
    }

    @Override
    public int compareTo(FmpYear that) {
        if (that == null) {
            throw new FmpInvalidYearException("'that.value' is required");
        }
        return compare(this.value, that.value, Integer::compareTo);
    }
}

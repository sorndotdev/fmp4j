package com.marketdataapi.fmp4j.types;

import static java.lang.String.valueOf;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.marketdataapi.fmp4j.exceptions.FmpInvalidQuarterException;
import java.io.Serial;

public class FmpQuarter implements Comparable<FmpQuarter>, FmpValueObject<Integer> {
    public static final int MIN_QUARTER_VALUE = 1;
    public static final int MAX_QUARTER_VALUE = 4;
    public static final FmpQuarter Q1 = new FmpQuarter(1);
    public static final FmpQuarter Q2 = new FmpQuarter(2);
    public static final FmpQuarter Q3 = new FmpQuarter(3);
    public static final FmpQuarter Q4 = new FmpQuarter(4);
    private final int value;

    @Serial
    private static final long serialVersionUID = 100L;

    private FmpQuarter(int value) {
        if (value < MIN_QUARTER_VALUE) {
            throw new FmpInvalidQuarterException(
                    "[%d] is below the minimum allowed value [%d]", value, MIN_QUARTER_VALUE);
        }
        if (value > MAX_QUARTER_VALUE) {
            throw new FmpInvalidQuarterException(
                    "[%d] exceeds the maximum allowed value [%d]", value, MAX_QUARTER_VALUE);
        }
        this.value = value;
    }

    @JsonCreator
    public static FmpQuarter quarter(int value) {
        return new FmpQuarter(value);
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
        if (!(obj instanceof FmpQuarter that)) {
            return false;
        }
        return this.value == that.value;
    }

    @Override
    public int compareTo(FmpQuarter that) {
        if (that == null) {
            throw new FmpInvalidQuarterException("'that.value' is required");
        }
        return Integer.compare(this.value, that.value);
    }
}

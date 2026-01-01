package com.marketdataapi.fmp4j.types;

import static java.lang.String.valueOf;

import com.marketdataapi.fmp4j.exceptions.FmpInvalidMinorUnitException;

public final class FmpMinorUnit implements FmpValueObject<Integer> {
    public static final int MIN_MINOR_UNIT_VALUE = 0;
    public static final int MAX_MINOR_UNIT_VALUE = 24;
    private final int value;

    private FmpMinorUnit(int value) {
        if (value < 0) {
            throw new FmpInvalidMinorUnitException(
                    "[%d] is below the minimum allowed value [%d]", value, MIN_MINOR_UNIT_VALUE);
        }
        if (value > MAX_MINOR_UNIT_VALUE) {
            throw new FmpInvalidMinorUnitException(
                    "[%d] exceeds the maximum allowed value [%d]", value, MAX_MINOR_UNIT_VALUE);
        }
        this.value = value;
    }

    public static FmpMinorUnit minorUnit(int value) {
        return new FmpMinorUnit(value);
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
        if (!(obj instanceof FmpMinorUnit that)) {
            return false;
        }
        return this.value == that.value;
    }
}

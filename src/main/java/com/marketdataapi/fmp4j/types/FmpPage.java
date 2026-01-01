package com.marketdataapi.fmp4j.types;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.util.Objects.compare;

import com.marketdataapi.fmp4j.exceptions.FmpInvalidPageException;
import java.io.Serial;

public final class FmpPage implements Comparable<FmpPage>, FmpValueObject<Integer> {
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 10000;

    @Serial
    private static final long serialVersionUID = 100L;

    private final int value;

    private FmpPage(int value) {
        this.value = value;
    }

    public static FmpPage page(String value) {
        try {
            return page(parseInt(value));
        } catch (NumberFormatException e) {
            throw new FmpInvalidPageException("[%s] is not a valid integer value", value);
        }
    }

    public static FmpPage page(int value) {
        if (value < MIN_VALUE) {
            throw new FmpInvalidPageException("[%d] is below the minimum allowed value [%d]", value, MIN_VALUE);
        }
        if (value > MAX_VALUE) {
            throw new FmpInvalidPageException("[%d] exceeds the maximum allowed value [%d]", value, MAX_VALUE);
        }
        return new FmpPage(value);
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
        if (!(obj instanceof FmpPage that)) {
            return false;
        }
        return this.value == that.value;
    }

    @Override
    public int compareTo(FmpPage that) {
        if (that == null) {
            throw new FmpInvalidPageException("'that.value' is required");
        }
        return compare(this.value, that.value, Integer::compareTo);
    }
}

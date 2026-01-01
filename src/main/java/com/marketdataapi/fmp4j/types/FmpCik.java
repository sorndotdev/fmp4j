package com.marketdataapi.fmp4j.types;

import static java.util.Objects.compare;
import static java.util.regex.Pattern.compile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.marketdataapi.fmp4j.exceptions.FmpInvalidCikException;
import java.util.regex.Pattern;

/**
 * @author chat.deepseek.com
 */
public final class FmpCik implements Comparable<FmpCik>, FmpValueObject<String> {
    public static final Pattern FMP_CIK_PATTERN = compile("^[0-9]{1,10}$");

    private final String value;

    private FmpCik(String value) {
        this.value = value;
    }

    @JsonCreator
    public static FmpCik cik(String value) {
        if (value == null) {
            throw new FmpInvalidCikException("'value' is required");
        }
        if (!FMP_CIK_PATTERN.matcher(value).matches()) {
            throw new FmpInvalidCikException(
                    "'value' [%s] does not match pattern [%s]", value, FMP_CIK_PATTERN.pattern());
        }
        return new FmpCik(String.format("%010d", Long.parseLong(value)));
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value();
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof FmpCik that)) {
            return false;
        }
        return value.equals(that.value);
    }

    @Override
    public int compareTo(FmpCik that) {
        if (that == null) {
            throw new FmpInvalidCikException("'that.value' is required");
        }
        return compare(this.value, that.value, String::compareTo);
    }
}

package com.marketdataapi.fmp4j.types;

import static java.util.Objects.compare;
import static java.util.regex.Pattern.compile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.marketdataapi.fmp4j.exceptions.FmpInvalidCusipException;
import java.util.regex.Pattern;

/**
 * @author chat.deepseek.com
 */
public final class FmpCusip implements Comparable<FmpCusip>, FmpValueObject<String> {
    public static final Pattern FMP_CUSIP_PATTERN = compile("^[A-Z0-9]{9}$");

    private final String value;

    private FmpCusip(String value) {
        this.value = value;
    }

    @JsonCreator
    public static FmpCusip cusip(String value) {
        if (value == null) {
            throw new FmpInvalidCusipException("'value' is required");
        }
        if (!FMP_CUSIP_PATTERN.matcher(value).matches()) {
            throw new FmpInvalidCusipException(
                    "'value' [%s] does not match pattern [%s]", value, FMP_CUSIP_PATTERN.pattern());
        }
        return new FmpCusip(value);
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
        if (!(obj instanceof FmpCusip that)) {
            return false;
        }
        return value.equals(that.value);
    }

    @Override
    public int compareTo(FmpCusip that) {
        if (that == null) {
            throw new FmpInvalidCusipException("'that.value' is required");
        }
        return compare(this.value, that.value, String::compareTo);
    }
}

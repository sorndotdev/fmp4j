package com.marketdataapi.fmp4j.types;

import static java.lang.String.format;

import com.marketdataapi.fmp4j.exceptions.FmpInvalidApiKey;
import java.util.regex.Pattern;

public class FmpApiKey implements FmpValueObject<String> {
    public static final Pattern FMP_API_KEY_PATTERN = Pattern.compile("^[A-Za-z\\d]{32}$");

    private final transient String value;

    public FmpApiKey(String value) {
        if (value == null || value.isBlank()) {
            throw new FmpInvalidApiKey("'value' must not be null or blank");
        }
        if (!FMP_API_KEY_PATTERN.matcher(value).matches()) {
            throw new FmpInvalidApiKey(
                    format("'value' [%s] does not match pattern [%s]", value, FMP_API_KEY_PATTERN.pattern()));
        }
        this.value = value;
    }

    @Override
    public String value() {
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
        if (!(obj instanceof FmpApiKey that)) {
            return false;
        }
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.substring(0, 2) + "*".repeat(value.length() - 4) + value.substring(value.length() - 2);
    }
}

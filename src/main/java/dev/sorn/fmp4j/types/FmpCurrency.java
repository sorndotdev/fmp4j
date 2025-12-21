package dev.sorn.fmp4j.types;

import static java.util.Objects.compare;
import static java.util.regex.Pattern.compile;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.sorn.fmp4j.exceptions.FmpInvalidCurrencyException;
import java.util.regex.Pattern;

/**
 * @author chat.deepseek.com
 */
public final class FmpCurrency implements Comparable<FmpCurrency>, FmpValueObject<String> {
    public static final Pattern FMP_CURRENCY_PATTERN = compile("^[A-Z]{3}$");
    public static final FmpCurrency EUR = currency("EUR");
    public static final FmpCurrency USD = currency("USD");

    private final String value;

    private FmpCurrency(String value) {
        this.value = value;
    }

    @JsonCreator
    public static FmpCurrency currency(String value) {
        if (value == null) {
            throw new FmpInvalidCurrencyException("'value' is required");
        }
        final var normalized = value.toUpperCase();
        if (!FMP_CURRENCY_PATTERN.matcher(normalized).matches()) {
            throw new FmpInvalidCurrencyException(
                    "'value' [%s] does not match pattern [%s]", value, FMP_CURRENCY_PATTERN.pattern());
        }
        return new FmpCurrency(normalized);
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
        if (!(obj instanceof FmpCurrency that)) {
            return false;
        }
        return value.equals(that.value);
    }

    @Override
    public int compareTo(FmpCurrency that) {
        if (that == null) {
            throw new FmpInvalidCurrencyException("'that.value' is required");
        }
        return compare(this.value, that.value, String::compareTo);
    }
}

package com.marketdataapi.fmp4j.types;

import static java.util.Objects.compare;
import static java.util.regex.Pattern.compile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.marketdataapi.fmp4j.exceptions.FmpInvalidIsinException;
import java.util.regex.Pattern;

/**
 * @author chat.deepseek.com
 */
public final class FmpIsin implements Comparable<FmpIsin>, FmpValueObject<String> {
    public static final Pattern FMP_ISIN_PATTERN = compile("^[A-Z]{2}[A-Z0-9]{9}[0-9]$");

    private final String value;

    private FmpIsin(String value) {
        this.value = value;
    }

    @JsonCreator
    public static FmpIsin isin(String value) {
        if (value == null) {
            throw new FmpInvalidIsinException("'value' is required");
        }
        if (!FMP_ISIN_PATTERN.matcher(value).matches()) {
            throw new FmpInvalidIsinException(
                    "'value' [%s] does not match pattern [%s]", value, FMP_ISIN_PATTERN.pattern());
        }
        if (!isValidLuhn(value)) {
            throw new FmpInvalidIsinException("'value' [%s] has invalid check digit", value);
        }
        return new FmpIsin(value);
    }

    private static boolean isValidLuhn(String isin) {
        final var numericString = new StringBuilder();
        for (final char c : isin.substring(0, 11).toCharArray()) {
            if (Character.isLetter(c)) {
                numericString.append(Character.getNumericValue(c));
            } else {
                numericString.append(c);
            }
        }
        int sum = 0;
        boolean alternate = true;
        for (int i = numericString.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(numericString.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit % 10) + 1;
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        final int checkDigit = Character.getNumericValue(isin.charAt(11));
        return (sum % 10 == 0) ? (checkDigit == 0) : (checkDigit == 10 - (sum % 10));
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
        if (!(obj instanceof FmpIsin that)) {
            return false;
        }
        return value.equals(that.value);
    }

    @Override
    public int compareTo(FmpIsin that) {
        if (that == null) {
            throw new FmpInvalidIsinException("'that.value' is required");
        }
        return compare(this.value, that.value, String::compareTo);
    }
}

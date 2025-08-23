package dev.sorn.fmp4j.types;

import static java.util.Objects.compare;

import dev.sorn.fmp4j.exceptions.FmpInvalidSymbolException;
import java.io.Serial;
import java.util.regex.Pattern;

public final class FmpSymbol implements Comparable<FmpSymbol>, FmpValueObject<String> {
    public static final Pattern FMP_SYMBOL_PATTERN =
            Pattern.compile("^([A-Z]{1,6}:)?[A-Z0-9]{1,6}([./-][A-Z][A-Z0-9]{0,3})?$");

    @Serial
    private static final long serialVersionUID = 1L;

    private final String value;

    private FmpSymbol(String value) {
        this.value = value;
    }

    public static FmpSymbol symbol(String value) {
        if (value == null) {
            throw new FmpInvalidSymbolException("'value' is required");
        }
        if (!FMP_SYMBOL_PATTERN.matcher(value).matches()) {
            throw new FmpInvalidSymbolException(
                    "'value' [%s] does not match pattern [%s]", value, FMP_SYMBOL_PATTERN.pattern());
        }
        return new FmpSymbol(value);
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
        if (!(obj instanceof FmpSymbol that)) {
            return false;
        }
        return value.equals(that.value);
    }

    @Override
    public int compareTo(FmpSymbol that) {
        if (that == null) {
            throw new FmpInvalidSymbolException("'that.value' is required");
        }
        return compare(this.value, that.value, String::compareTo);
    }
}

package com.marketdataapi.fmp4j.types;

import static java.util.Objects.compare;
import static java.util.regex.Pattern.compile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.marketdataapi.fmp4j.exceptions.FmpInvalidSymbolException;
import java.io.Serial;
import java.util.regex.Pattern;

public final class FmpSymbol implements Comparable<FmpSymbol>, FmpValueObject<String> {
    // 1. Optional exchange prefix (1-5 alphanumeric characters followed by colon)
    // 2. Main symbol (1-10 alphanumeric characters or ampersand)
    // 3. Start group for optional segments
    // 4. Dash or slash separator followed by segment (1-10 characters, starting with letter)
    // 5. OR
    // 6. Dot separator followed by segment (1-4 characters, starting with letter)
    // 7. End group, repeated zero or more times
    public static final Pattern FMP_SYMBOL_PATTERN = compile("^(?:[A-Z0-9]{1,5}:)?"
            + "[A-Z0-9&]{1,16}"
            + "(?:"
            + "(?:[-/][A-Z][A-Z0-9]{0,9})"
            + "|"
            + "(?:\\.[A-Z][A-Z0-9]{0,3})"
            + ")*$");

    @Serial
    private static final long serialVersionUID = 100L;

    private final String value;

    private FmpSymbol(String value) {
        this.value = value;
    }

    @JsonCreator
    public static FmpSymbol symbol(String value) {
        if (value == null) {
            throw new FmpInvalidSymbolException("'value' is required");
        }
        if (!FMP_SYMBOL_PATTERN.matcher(value.trim()).matches()) {
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

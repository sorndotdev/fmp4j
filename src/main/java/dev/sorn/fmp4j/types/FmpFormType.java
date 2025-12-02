package dev.sorn.fmp4j.types;

import static java.lang.String.format;
import static java.util.Objects.compare;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.sorn.fmp4j.exceptions.FmpInvalidFormTypeException;
import java.io.Serial;
import java.util.regex.Pattern;

public class FmpFormType implements FmpValueObject<String>, Comparable<FmpFormType> {

    public static final Pattern FMP_FORM_TYPE_PATTERN = Pattern.compile("^[0-9A-Z]+(?:[- ][0-9A-Z]+)*(?:/A)?$");
    public static final FmpFormType FORM_10K = formType("10-K");
    public static final FmpFormType FORM_10Q = formType("10-Q");
    public static final FmpFormType FORM_8K = formType("8-K");

    @Serial
    private static final long serialVersionUID = -1L;

    private final String value;

    private FmpFormType(String value) {
        if (value == null || value.isBlank()) {
            throw new FmpInvalidFormTypeException("Form type must not be null or blank.");
        }
        if (!FMP_FORM_TYPE_PATTERN.matcher(value).matches()) {
            throw new FmpInvalidFormTypeException(
                    format("'value' [%s] does not match pattern [%s]", value, FMP_FORM_TYPE_PATTERN.pattern()));
        }
        this.value = value;
    }

    @JsonCreator
    public static FmpFormType formType(String value) {
        return new FmpFormType(value);
    }

    @Override
    public String value() {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof FmpFormType that)) {
            return false;
        }
        return value.equalsIgnoreCase(that.value);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public int compareTo(FmpFormType that) {
        if (that == null) {
            throw new FmpInvalidFormTypeException("'that.value' is required");
        }
        return compare(this.value, that.value, String::compareTo);
    }

    @Override
    public String toString() {
        return value;
    }
}

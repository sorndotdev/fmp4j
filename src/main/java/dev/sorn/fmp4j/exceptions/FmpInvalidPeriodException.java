package dev.sorn.fmp4j.exceptions;

import static java.lang.String.format;

public class FmpInvalidPeriodException extends RuntimeException {
    public FmpInvalidPeriodException(String message, Object... args) {
        super(format(message, args));
    }
}
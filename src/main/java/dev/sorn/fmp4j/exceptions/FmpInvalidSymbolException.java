package dev.sorn.fmp4j.exceptions;

import static java.lang.String.format;

public class FmpInvalidSymbolException extends RuntimeException {
    public FmpInvalidSymbolException(String message, Object... args) {
        super(format(message, args));
    }
}

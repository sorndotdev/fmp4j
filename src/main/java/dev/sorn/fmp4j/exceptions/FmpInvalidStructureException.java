package dev.sorn.fmp4j.exceptions;

import static java.lang.String.format;

public class FmpInvalidStructureException extends RuntimeException {

    public FmpInvalidStructureException(String message, Object... args) {
        super(format(message, args));
    }
}

package dev.sorn.fmp4j.services;

import static java.lang.String.format;

public class FmpServiceException extends RuntimeException {
    public FmpServiceException(String message, Object... args) {
        super(format(message, args));
    }
}

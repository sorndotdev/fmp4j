package dev.sorn.fmp4j.exceptions;

import static java.lang.String.format;

public class FmpException extends RuntimeException {
    public FmpException(Throwable t, String message, Object... args) {
        super(format(message, args), t);
    }

    public FmpException(String message, Object... args) {
        super(format(message, args));
    }
}

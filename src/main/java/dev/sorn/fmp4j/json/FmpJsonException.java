package dev.sorn.fmp4j.json;

import static java.lang.String.format;

public class FmpJsonException extends RuntimeException {
    public FmpJsonException(Throwable t, String message, Object... args) {
        super(format(message, args), t);
    }
}

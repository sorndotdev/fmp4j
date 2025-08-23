package dev.sorn.fmp4j.http;

import static java.lang.String.format;

public class FmpHttpException extends RuntimeException {
    public FmpHttpException(Throwable t, String message, Object... args) {
        super(format(message, args), t);
    }
}

package dev.sorn.fmp4j.exceptions;

public class FmpDeserializationException extends FmpException {
    public FmpDeserializationException(Throwable cause, String message, Object... args) {
        super(cause, message, args);
    }

    public FmpDeserializationException(String message, Object... args) {
        super(message, args);
    }
}

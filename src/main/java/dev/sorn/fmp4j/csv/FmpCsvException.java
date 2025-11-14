package dev.sorn.fmp4j.csv;

import static java.lang.String.format;

public class FmpCsvException extends RuntimeException {
    public FmpCsvException(Throwable t, String message, Object... args) {
        super(format(message, args), t);
    }
}

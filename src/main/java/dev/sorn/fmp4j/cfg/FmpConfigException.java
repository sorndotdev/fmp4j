package dev.sorn.fmp4j.cfg;

import static java.lang.String.format;

public class FmpConfigException extends RuntimeException {
    public FmpConfigException(String message, Object... args) {
        super(format(message, args));
    }

    public FmpConfigException(Throwable t, String message, Object... args) {
        super(format(message, args), t);
    }
}

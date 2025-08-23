package dev.sorn.fmp4j.models;

import java.io.Serial;

public record FmpPartialQuote(String symbol, Double price, Double change, Long volume) implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}

package dev.sorn.fmp4j.models;

import java.io.Serial;

public record FmpSearchBySymbol(String symbol, String name, String currency, String exchangeFullName, String exchange)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}

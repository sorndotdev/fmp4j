package dev.sorn.fmp4j.models;

import java.io.Serial;

public record FmpSearchByIsin(String symbol, String name, String isin, Long marketCap) implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}

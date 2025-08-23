package dev.sorn.fmp4j.models;

import java.io.Serial;
import java.time.LocalDate;

public record FmpHistoricalPriceEodLight(String symbol, LocalDate date, Double price, Double volume)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}

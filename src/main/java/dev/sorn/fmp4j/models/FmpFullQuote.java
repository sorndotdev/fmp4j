package dev.sorn.fmp4j.models;

import java.io.Serial;

public record FmpFullQuote(
        String symbol,
        String name,
        Double price,
        Double changePercentage,
        Double change,
        Long volume,
        Double dayLow,
        Double dayHigh,
        Double yearHigh,
        Double yearLow,
        Long marketCap,
        Double priceAvg50,
        Double priceAvg200,
        String exchange,
        Double open,
        Double previousClose,
        Long timestamp)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}

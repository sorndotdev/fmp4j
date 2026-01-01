package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpExchange;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.io.Serial;

public record FmpFullQuote(
        FmpSymbol symbol,
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
        FmpExchange exchange,
        Double open,
        Double previousClose,
        Long timestamp)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}

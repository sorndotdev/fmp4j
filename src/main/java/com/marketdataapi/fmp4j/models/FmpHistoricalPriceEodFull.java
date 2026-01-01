package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.io.Serial;
import java.time.LocalDate;

public record FmpHistoricalPriceEodFull(
        FmpSymbol symbol,
        LocalDate date,
        Double open,
        Double high,
        Double low,
        Double close,
        Double volume,
        Double change,
        Double changePercent,
        Double vwap)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}

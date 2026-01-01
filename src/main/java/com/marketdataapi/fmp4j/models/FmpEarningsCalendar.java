package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.io.Serial;
import java.time.LocalDate;

public record FmpEarningsCalendar(
        FmpSymbol symbol,
        LocalDate date,
        Double epsActual,
        Double epsEstimated,
        Long revenueActual,
        Long revenueEstimated,
        LocalDate lastUpdated)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}

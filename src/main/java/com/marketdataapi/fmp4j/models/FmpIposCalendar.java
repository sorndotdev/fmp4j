package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpExchange;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.io.Serial;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public record FmpIposCalendar(
        FmpSymbol symbol,
        LocalDate date,
        ZonedDateTime daa,
        String company,
        FmpExchange exchange,
        String actions,
        Long shares,
        String priceRange,
        Long marketCap)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}

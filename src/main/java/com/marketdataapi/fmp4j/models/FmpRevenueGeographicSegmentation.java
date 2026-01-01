package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpCurrency;
import com.marketdataapi.fmp4j.types.FmpPeriod;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import com.marketdataapi.fmp4j.types.FmpYear;
import java.io.Serial;
import java.time.LocalDate;
import java.util.Map;

public record FmpRevenueGeographicSegmentation(
        FmpSymbol symbol,
        FmpYear fiscalYear,
        FmpPeriod period,
        FmpCurrency reportedCurrency,
        LocalDate date,
        Map<String, Long> data)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}

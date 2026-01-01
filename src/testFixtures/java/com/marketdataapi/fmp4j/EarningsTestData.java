package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;

import com.marketdataapi.fmp4j.models.FmpEarning;
import java.time.LocalDate;

public interface EarningsTestData {
    default FmpEarning anEarning() {
        return new FmpEarning(
                symbol("AAPL"),
                LocalDate.parse("2025-05-01"),
                1.65,
                1.63,
                95359000000L,
                94542181546L,
                LocalDate.parse("2025-08-01"));
    }
}

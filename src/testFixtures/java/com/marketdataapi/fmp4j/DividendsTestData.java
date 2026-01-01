package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;

import com.marketdataapi.fmp4j.models.FmpDividend;
import java.time.LocalDate;

public interface DividendsTestData {
    default FmpDividend aDividend() {
        return new FmpDividend(
                symbol("AAPL"),
                LocalDate.parse("2025-08-11"),
                LocalDate.parse("2025-08-11"),
                LocalDate.parse("2025-08-14"),
                LocalDate.parse("2025-07-31"),
                0.26,
                0.26,
                0.44898318513953694,
                "Quarterly");
    }
}

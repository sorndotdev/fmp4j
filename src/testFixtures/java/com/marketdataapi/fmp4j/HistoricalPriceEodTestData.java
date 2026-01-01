package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;

import com.marketdataapi.fmp4j.models.FmpHistoricalPriceEodFull;
import com.marketdataapi.fmp4j.models.FmpHistoricalPriceEodLight;
import java.time.LocalDate;

public interface HistoricalPriceEodTestData {
    default FmpHistoricalPriceEodLight aHistoricalPriceEodLight() {
        return new FmpHistoricalPriceEodLight(symbol("AAPL"), LocalDate.parse("2024-02-28"), 181.42, 48953939.);
    }

    default FmpHistoricalPriceEodFull aHistoricalPriceEodFull() {
        return new FmpHistoricalPriceEodFull(
                symbol("AAPL"),
                LocalDate.parse("2024-02-22"),
                183.48,
                184.96,
                182.46,
                184.37,
                52292208.,
                0.89,
                0.48507,
                183.8175);
    }
}

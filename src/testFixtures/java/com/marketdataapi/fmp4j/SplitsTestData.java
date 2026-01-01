package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;

import com.marketdataapi.fmp4j.models.FmpSplit;
import java.time.LocalDate;

public interface SplitsTestData {
    default FmpSplit aSplit() {
        return new FmpSplit(symbol("AAPL"), LocalDate.parse("2020-08-31"), 4, 1, "AAPL split: 4 for 1");
    }
}

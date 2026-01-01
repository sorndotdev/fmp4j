package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;

import com.marketdataapi.fmp4j.models.FmpSplitsCalendar;
import java.time.LocalDate;

public interface SplitsCalendarTestData {
    default FmpSplitsCalendar aSplitsCalendarRecord() {
        return new FmpSplitsCalendar(symbol("NVDA"), LocalDate.parse("2024-06-10"), 10, 1, "NVDA split: 10 for 1");
    }
}

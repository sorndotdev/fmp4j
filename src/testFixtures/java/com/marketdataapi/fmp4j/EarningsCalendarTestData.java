package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;

import com.marketdataapi.fmp4j.models.FmpEarningsCalendar;
import java.time.LocalDate;

public interface EarningsCalendarTestData {
    default FmpEarningsCalendar anEarningsCalendarRecord() {
        return new FmpEarningsCalendar(
                symbol("BOOZT-DKK.CO"),
                LocalDate.parse("2025-08-15"),
                0.4207,
                0.592,
                1217400909L,
                1734000000L,
                LocalDate.parse("2025-08-16"));
    }
}

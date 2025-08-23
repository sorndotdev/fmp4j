package dev.sorn.fmp4j;

import dev.sorn.fmp4j.models.FmpEarningsCalendar;
import java.time.LocalDate;

public interface EarningsCalendarTestData {
    default FmpEarningsCalendar anEarningsCalendarRecord() {
        return new FmpEarningsCalendar(
                "BOOZT-DKK.CO",
                LocalDate.parse("2025-08-15"),
                0.4207,
                0.592,
                1217400909L,
                1734000000L,
                LocalDate.parse("2025-08-16"));
    }
}

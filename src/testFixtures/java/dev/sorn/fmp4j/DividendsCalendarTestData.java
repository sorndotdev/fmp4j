package dev.sorn.fmp4j;

import dev.sorn.fmp4j.models.FmpDividendsCalendar;
import java.time.LocalDate;

public interface DividendsCalendarTestData {
    default FmpDividendsCalendar aDividendsCalendarRecord() {
        return new FmpDividendsCalendar(
                "O",
                LocalDate.parse("2025-08-01"),
                LocalDate.parse("2025-08-01"),
                LocalDate.parse("2025-08-15"),
                LocalDate.parse("2025-07-08"),
                0.269,
                0.269,
                5.644334452890225,
                "Monthly");
    }
}

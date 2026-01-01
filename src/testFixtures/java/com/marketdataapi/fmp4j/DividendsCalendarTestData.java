package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;

import com.marketdataapi.fmp4j.models.FmpDividendsCalendar;
import java.time.LocalDate;

public interface DividendsCalendarTestData {
    default FmpDividendsCalendar aDividendsCalendarRecord() {
        return new FmpDividendsCalendar(
                symbol("O"),
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

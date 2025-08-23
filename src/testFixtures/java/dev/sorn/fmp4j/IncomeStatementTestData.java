package dev.sorn.fmp4j;

import dev.sorn.fmp4j.models.FmpIncomeStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface IncomeStatementTestData {
    default FmpIncomeStatement anAnnualIncomeStatement() {
        return new FmpIncomeStatement(
                LocalDate.parse("2024-09-28"),
                "AAPL",
                "USD",
                "0000320193",
                LocalDate.parse("2024-11-01"),
                LocalDateTime.of(LocalDate.parse("2024-11-01"), LocalTime.of(6, 1, 36)),
                "2024",
                "FY",
                391035000000L,
                210352000000L,
                180683000000L,
                31370000000L,
                0L,
                0L,
                26097000000L,
                0L,
                57467000000L,
                267819000000L,
                0L,
                0L,
                0L,
                11445000000L,
                134661000000L,
                123216000000L,
                0L,
                123216000000L,
                269000000L,
                123485000000L,
                29749000000L,
                93736000000L,
                0L,
                0L,
                93736000000L,
                0L,
                93736000000L,
                6.11,
                6.08,
                15343783000L,
                15408095000L);
    }
}

package dev.sorn.fmp4j;

import dev.sorn.fmp4j.models.FmpCashFlowStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface CashFlowStatementTestData {
    default FmpCashFlowStatement anAnnualCashFlowStatement() {
        return new FmpCashFlowStatement(
                LocalDate.parse("2024-09-28"),
                "AAPL",
                "USD",
                "0000320193",
                LocalDate.parse("2024-11-01"),
                LocalDateTime.of(LocalDate.parse("2024-11-01"), LocalTime.of(6, 1, 36)),
                "2024",
                "FY",
                93736000000L,
                11445000000L,
                0L,
                11688000000L,
                3651000000L,
                -5144000000L,
                -1046000000L,
                6020000000L,
                3821000000L,
                -2266000000L,
                118254000000L,
                -9447000000L,
                0L,
                -48656000000L,
                62346000000L,
                -1308000000L,
                2935000000L,
                -5998000000L,
                -9958000000L,
                3960000000L,
                -94949000000L,
                -94949000000L,
                0L,
                -94949000000L,
                0L,
                -15234000000L,
                -15234000000L,
                0L,
                -5802000000L,
                -121983000000L,
                0L,
                -794000000L,
                29943000000L,
                30737000000L,
                118254000000L,
                -9447000000L,
                108807000000L,
                26102000000L,
                0L);
    }
}

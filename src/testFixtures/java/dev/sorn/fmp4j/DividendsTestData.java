package dev.sorn.fmp4j;

import dev.sorn.fmp4j.models.FmpDividend;
import java.time.LocalDate;

public interface DividendsTestData {
    default FmpDividend aDividend() {
        return new FmpDividend(
                "AAPL",
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

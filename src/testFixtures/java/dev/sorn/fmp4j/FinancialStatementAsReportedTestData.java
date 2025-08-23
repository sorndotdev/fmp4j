package dev.sorn.fmp4j;

import dev.sorn.fmp4j.models.FmpFinancialStatementAsReported;
import java.time.LocalDate;
import java.util.Map;

public interface FinancialStatementAsReportedTestData {
    default FmpFinancialStatementAsReported aFinancialStatementAsReported() {
        return new FmpFinancialStatementAsReported(
                "KO",
                2024,
                "FY",
                null,
                LocalDate.parse("2024-12-30"),
                Map.of("somefield1", 123.45, "somefield2", 10000L, "somefield3", 90));
    }
}

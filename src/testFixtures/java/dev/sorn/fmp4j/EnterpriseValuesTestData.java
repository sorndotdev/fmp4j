package dev.sorn.fmp4j;

import dev.sorn.fmp4j.models.FmpEnterpriseValue;
import java.time.LocalDate;

public interface EnterpriseValuesTestData {
    default FmpEnterpriseValue anEnterpriseValue() {
        return new FmpEnterpriseValue(
                "AAPL",
                LocalDate.parse("2024-09-28"),
                227.79,
                15343783000L,
                3495160329570L,
                29943000000L,
                106629000000L,
                3571846329570L);
    }
}

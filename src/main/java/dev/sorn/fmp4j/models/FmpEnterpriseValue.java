package dev.sorn.fmp4j.models;

import java.io.Serial;
import java.time.LocalDate;

public record FmpEnterpriseValue(
        String symbol,
        LocalDate date,
        Double stockPrice,
        Long numberOfShares,
        Long marketCapitalization,
        Long minusCashAndCashEquivalents,
        Long addTotalDebt,
        Long enterpriseValue)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}

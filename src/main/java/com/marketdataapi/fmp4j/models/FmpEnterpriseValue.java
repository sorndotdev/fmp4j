package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.io.Serial;
import java.time.LocalDate;

public record FmpEnterpriseValue(
        FmpSymbol symbol,
        LocalDate date,
        Double stockPrice,
        Long numberOfShares,
        Long marketCapitalization,
        Long minusCashAndCashEquivalents,
        Long addTotalDebt,
        Long enterpriseValue)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}

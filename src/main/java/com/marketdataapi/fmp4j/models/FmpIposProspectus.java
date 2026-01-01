package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpCik;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.io.Serial;
import java.time.LocalDate;

public record FmpIposProspectus(
        FmpSymbol symbol,
        LocalDate acceptedDate,
        LocalDate filingDate,
        LocalDate ipoDate,
        FmpCik cik,
        Double pricePublicPerShare,
        Double pricePublicTotal,
        Double discountsAndCommissionsPerShare,
        Double discountsAndCommissionsTotal,
        Double proceedsBeforeExpensesPerShare,
        Double proceedsBeforeExpensesTotal,
        String form,
        String url)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}

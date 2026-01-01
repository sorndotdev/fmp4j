package com.marketdataapi.fmp4j.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marketdataapi.fmp4j.types.FmpCik;
import com.marketdataapi.fmp4j.types.FmpCurrency;
import com.marketdataapi.fmp4j.types.FmpPeriod;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import com.marketdataapi.fmp4j.types.FmpYear;
import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record FmpBalanceSheetStatement(
        LocalDate date,
        FmpSymbol symbol,
        FmpCurrency reportedCurrency,
        FmpCik cik,
        LocalDate filingDate,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime acceptedDate,
        FmpYear fiscalYear,
        FmpPeriod period,
        Long cashAndCashEquivalents,
        Long shortTermInvestments,
        Long cashAndShortTermInvestments,
        Long netReceivables,
        Long accountsReceivables,
        Long otherReceivables,
        Long inventory,
        Long prepaids,
        Long otherCurrentAssets,
        Long totalCurrentAssets,
        Long propertyPlantEquipmentNet,
        Long goodwill,
        Long intangibleAssets,
        Long goodwillAndIntangibleAssets,
        Long longTermInvestments,
        Long taxAssets,
        Long otherNonCurrentAssets,
        Long totalNonCurrentAssets,
        Long otherAssets,
        Long totalAssets,
        Long totalPayables,
        Long accountPayables,
        Long otherPayables,
        Long accruedExpenses,
        Long shortTermDebt,
        Long capitalLeaseObligationsCurrent,
        Long taxPayables,
        Long deferredRevenue,
        Long otherCurrentLiabilities,
        Long totalCurrentLiabilities,
        Long longTermDebt,
        Long capitalLeaseObligationsNonCurrent,
        Long deferredRevenueNonCurrent,
        Long deferredTaxLiabilitiesNonCurrent,
        Long otherNonCurrentLiabilities,
        Long totalNonCurrentLiabilities,
        Long otherLiabilities,
        Long capitalLeaseObligations,
        Long totalLiabilities,
        Long treasuryStock,
        Long preferredStock,
        Long commonStock,
        Long retainedEarnings,
        Long additionalPaidInCapital,
        Long accumulatedOtherComprehensiveIncomeLoss,
        Long otherTotalStockholdersEquity,
        Long totalStockholdersEquity,
        Long totalEquity,
        Long minorityInterest,
        Long totalLiabilitiesAndTotalEquity,
        Long totalInvestments,
        Long totalDebt,
        Long netDebt)
        implements FmpModel, FmpStatement {
    @Serial
    private static final long serialVersionUID = 100L;
}

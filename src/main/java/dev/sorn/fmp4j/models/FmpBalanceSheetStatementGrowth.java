package dev.sorn.fmp4j.models;

import dev.sorn.fmp4j.types.FmpCurrency;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpSymbol;
import dev.sorn.fmp4j.types.FmpYear;
import java.io.Serial;
import java.time.LocalDate;

public record FmpBalanceSheetStatementGrowth(
        FmpSymbol symbol,
        LocalDate date,
        FmpYear fiscalYear,
        FmpPeriod period,
        FmpCurrency reportedCurrency,
        Double growthCashAndCashEquivalents,
        Double growthShortTermInvestments,
        Double growthCashAndShortTermInvestments,
        Double growthNetReceivables,
        Double growthInventory,
        Double growthOtherCurrentAssets,
        Double growthTotalCurrentAssets,
        Double growthPropertyPlantEquipmentNet,
        Double growthGoodwill,
        Double growthIntangibleAssets,
        Double growthGoodwillAndIntangibleAssets,
        Double growthLongTermInvestments,
        Double growthTaxAssets,
        Double growthOtherNonCurrentAssets,
        Double growthTotalNonCurrentAssets,
        Double growthOtherAssets,
        Double growthTotalAssets,
        Double growthAccountPayables,
        Double growthShortTermDebt,
        Double growthTaxPayables,
        Double growthDeferredRevenue,
        Double growthOtherCurrentLiabilities,
        Double growthTotalCurrentLiabilities,
        Double growthLongTermDebt,
        Double growthDeferredRevenueNonCurrent,
        Double growthDeferredTaxLiabilitiesNonCurrent,
        Double growthOtherNonCurrentLiabilities,
        Double growthTotalNonCurrentLiabilities,
        Double growthOtherLiabilities,
        Double growthTotalLiabilities,
        Double growthPreferredStock,
        Double growthCommonStock,
        Double growthRetainedEarnings,
        Double growthAccumulatedOtherComprehensiveIncomeLoss,
        Double growthOthertotalStockholdersEquity,
        Double growthTotalStockholdersEquity,
        Double growthMinorityInterest,
        Double growthTotalEquity,
        Double growthTotalLiabilitiesAndStockholdersEquity,
        Double growthTotalInvestments,
        Double growthTotalDebt,
        Double growthNetDebt,
        Double growthAccountsReceivables,
        Double growthOtherReceivables,
        Double growthPrepaids,
        Double growthTotalPayables,
        Double growthOtherPayables,
        Double growthAccruedExpenses,
        Double growthCapitalLeaseObligationsCurrent,
        Double growthAdditionalPaidInCapital,
        Double growthTreasuryStock)
        implements FmpModel, FmpStatement {
    @Serial
    private static final long serialVersionUID = 3L;
}

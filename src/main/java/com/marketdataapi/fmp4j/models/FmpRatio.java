package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpCurrency;
import com.marketdataapi.fmp4j.types.FmpPeriod;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import com.marketdataapi.fmp4j.types.FmpYear;
import java.io.Serial;
import java.time.LocalDate;

public record FmpRatio(
        FmpSymbol symbol,
        LocalDate date,
        FmpYear fiscalYear,
        FmpPeriod period,
        FmpCurrency reportedCurrency,
        Double grossProfitMargin,
        Double ebitMargin,
        Double ebitdaMargin,
        Double operatingProfitMargin,
        Double pretaxProfitMargin,
        Double continuousOperationsProfitMargin,
        Double netProfitMargin,
        Double bottomLineProfitMargin,
        Double receivablesTurnover,
        Double payablesTurnover,
        Double inventoryTurnover,
        Double fixedAssetTurnover,
        Double assetTurnover,
        Double currentRatio,
        Double quickRatio,
        Double solvencyRatio,
        Double cashRatio,
        Double priceToEarningsRatio,
        Double priceToEarningsGrowthRatio,
        Double forwardPriceToEarningsGrowthRatio,
        Double priceToBookRatio,
        Double priceToSalesRatio,
        Double priceToFreeCashFlowRatio,
        Double priceToOperatingCashFlowRatio,
        Double debtToAssetsRatio,
        Double debtToEquityRatio,
        Double debtToCapitalRatio,
        Double longTermDebtToCapitalRatio,
        Double financialLeverageRatio,
        Double workingCapitalTurnoverRatio,
        Double operatingCashFlowRatio,
        Double operatingCashFlowSalesRatio,
        Double freeCashFlowOperatingCashFlowRatio,
        Double debtServiceCoverageRatio,
        Double interestCoverageRatio,
        Double shortTermOperatingCashFlowCoverageRatio,
        Double operatingCashFlowCoverageRatio,
        Double capitalExpenditureCoverageRatio,
        Double dividendPaidAndCapexCoverageRatio,
        Double dividendPayoutRatio,
        Double dividendYield,
        Double dividendYieldPercentage,
        Double revenuePerShare,
        Double netIncomePerShare,
        Double interestDebtPerShare,
        Double cashPerShare,
        Double bookValuePerShare,
        Double tangibleBookValuePerShare,
        Double shareholdersEquityPerShare,
        Double operatingCashFlowPerShare,
        Double capexPerShare,
        Double freeCashFlowPerShare,
        Double netIncomePerEBT,
        Double ebtPerEbit,
        Double priceToFairValue,
        Double debtToMarketCap,
        Double effectiveTaxRate,
        Double enterpriseValueMultiple,
        Double dividendPerShare)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 100L;
}

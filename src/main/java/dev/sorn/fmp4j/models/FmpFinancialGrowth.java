package dev.sorn.fmp4j.models;

import java.io.Serial;

public record FmpFinancialGrowth(
        String symbol,
        String date,
        String fiscalYear,
        String period,
        String reportedCurrency,
        Double revenueGrowth,
        Double grossProfitGrowth,
        Double ebitgrowth,
        Double operatingIncomeGrowth,
        Double netIncomeGrowth,
        Double epsgrowth,
        Double epsdilutedGrowth,
        Double weightedAverageSharesGrowth,
        Double weightedAverageSharesDilutedGrowth,
        Double dividendsPerShareGrowth,
        Double operatingCashFlowGrowth,
        Double receivablesGrowth,
        Double inventoryGrowth,
        Double assetGrowth,
        Double bookValueperShareGrowth,
        Double debtGrowth,
        Double rdexpenseGrowth,
        Double sgaexpensesGrowth,
        Double freeCashFlowGrowth,
        Double tenYRevenueGrowthPerShare,
        Double fiveYRevenueGrowthPerShare,
        Double threeYRevenueGrowthPerShare,
        Double tenYOperatingCFGrowthPerShare,
        Double fiveYOperatingCFGrowthPerShare,
        Double threeYOperatingCFGrowthPerShare,
        Double tenYNetIncomeGrowthPerShare,
        Double fiveYNetIncomeGrowthPerShare,
        Double threeYNetIncomeGrowthPerShare,
        Double tenYShareholdersEquityGrowthPerShare,
        Double fiveYShareholdersEquityGrowthPerShare,
        Double threeYShareholdersEquityGrowthPerShare,
        Double tenYDividendperShareGrowthPerShare,
        Double fiveYDividendperShareGrowthPerShare,
        Double threeYDividendperShareGrowthPerShare,
        Double ebitdaGrowth,
        Double growthCapitalExpenditure,
        Double tenYBottomLineNetIncomeGrowthPerShare,
        Double fiveYBottomLineNetIncomeGrowthPerShare,
        Double threeYBottomLineNetIncomeGrowthPerShare)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}

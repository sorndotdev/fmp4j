package com.marketdataapi.fmp4j.models;

import com.marketdataapi.fmp4j.types.FmpCurrency;
import com.marketdataapi.fmp4j.types.FmpPeriod;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import com.marketdataapi.fmp4j.types.FmpYear;
import java.io.Serial;
import java.time.LocalDate;

public record FmpFinancialGrowth(
        FmpSymbol symbol,
        LocalDate date,
        FmpYear fiscalYear,
        FmpPeriod period,
        FmpCurrency reportedCurrency,
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
        implements FmpModel, FmpStatement {
    @Serial
    private static final long serialVersionUID = 100L;
}

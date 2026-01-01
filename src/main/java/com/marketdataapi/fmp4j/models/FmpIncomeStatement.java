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

public record FmpIncomeStatement(
        LocalDate date,
        FmpSymbol symbol,
        FmpCurrency reportedCurrency,
        FmpCik cik,
        LocalDate filingDate,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime acceptedDate,
        FmpYear fiscalYear,
        FmpPeriod period,
        Long revenue,
        Long costOfRevenue,
        Long grossProfit,
        Long researchAndDevelopmentExpenses,
        Long generalAndAdministrativeExpenses,
        Long sellingAndMarketingExpenses,
        Long sellingGeneralAndAdministrativeExpenses,
        Long otherExpenses,
        Long operatingExpenses,
        Long costAndExpenses,
        Long netInterestIncome,
        Long interestIncome,
        Long interestExpense,
        Long depreciationAndAmortization,
        Long ebitda,
        Long ebit,
        Long nonOperatingIncomeExcludingInterest,
        Long operatingIncome,
        Long totalOtherIncomeExpensesNet,
        Long incomeBeforeTax,
        Long incomeTaxExpense,
        Long netIncomeFromContinuingOperations,
        Long netIncomeFromDiscontinuedOperations,
        Long otherAdjustmentsToNetIncome,
        Long netIncome,
        Long netIncomeDeductions,
        Long bottomLineNetIncome,
        Double eps,
        Double epsDiluted,
        Long weightedAverageShsOut,
        Long weightedAverageShsOutDil)
        implements FmpModel, FmpStatement {
    @Serial
    private static final long serialVersionUID = 100L;
}

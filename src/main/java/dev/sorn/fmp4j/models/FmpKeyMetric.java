package dev.sorn.fmp4j.models;

import java.io.Serial;
import java.time.LocalDate;

public record FmpKeyMetric(
        String symbol,
        LocalDate date,
        String fiscalYear,
        String period,
        String reportedCurrency,
        Long marketCap,
        Long enterpriseValue,
        Double evToSales,
        Double evToOperatingCashFlow,
        Double evToFreeCashFlow,
        Double evToEBITDA,
        Double netDebtToEBITDA,
        Double currentRatio,
        Double incomeQuality,
        Double grahamNumber,
        Double grahamNetNet,
        Double taxBurden,
        Double interestBurden,
        Long workingCapital,
        Long investedCapital,
        Double returnOnAssets,
        Double operatingReturnOnAssets,
        Double returnOnTangibleAssets,
        Double returnOnEquity,
        Double returnOnInvestedCapital,
        Double returnOnCapitalEmployed,
        Double earningsYield,
        Double freeCashFlowYield,
        Double capexToOperatingCashFlow,
        Double capexToDepreciation,
        Double capexToRevenue,
        Double salesGeneralAndAdministrativeToRevenue,
        Double researchAndDevelopementToRevenue,
        Double stockBasedCompensationToRevenue,
        Double intangiblesToTotalAssets,
        Long averageReceivables,
        Long averagePayables,
        Long averageInventory,
        Double daysOfSalesOutstanding,
        Double daysOfPayablesOutstanding,
        Double daysOfInventoryOutstanding,
        Double operatingCycle,
        Double cashConversionCycle,
        Long freeCashFlowToEquity,
        Long freeCashFlowToFirm,
        Long tangibleAssetValue,
        Long netCurrentAssetValue)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}

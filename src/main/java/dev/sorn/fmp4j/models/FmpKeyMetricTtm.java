package dev.sorn.fmp4j.models;

import java.io.Serial;

public record FmpKeyMetricTtm(
        String symbol,
        Long marketCap,
        Long enterpriseValueTTM,
        Double evToSalesTTM,
        Double evToOperatingCashFlowTTM,
        Double evToFreeCashFlowTTM,
        Double evToEBITDATTM,
        Double netDebtToEBITDATTM,
        Double currentRatioTTM,
        Double incomeQualityTTM,
        Double grahamNumberTTM,
        Double grahamNetNetTTM,
        Double taxBurdenTTM,
        Double interestBurdenTTM,
        Long workingCapitalTTM,
        Long investedCapitalTTM,
        Double returnOnAssetsTTM,
        Double operatingReturnOnAssetsTTM,
        Double returnOnTangibleAssetsTTM,
        Double returnOnEquityTTM,
        Double returnOnInvestedCapitalTTM,
        Double returnOnCapitalEmployedTTM,
        Double earningsYieldTTM,
        Double freeCashFlowYieldTTM,
        Double capexToOperatingCashFlowTTM,
        Double capexToDepreciationTTM,
        Double capexToRevenueTTM,
        Double salesGeneralAndAdministrativeToRevenueTTM,
        Double researchAndDevelopementToRevenueTTM,
        Double stockBasedCompensationToRevenueTTM,
        Double intangiblesToTotalAssetsTTM,
        Long averageReceivablesTTM,
        Long averagePayablesTTM,
        Long averageInventoryTTM,
        Double daysOfSalesOutstandingTTM,
        Double daysOfPayablesOutstandingTTM,
        Double daysOfInventoryOutstandingTTM,
        Double operatingCycleTTM,
        Double cashConversionCycleTTM,
        Long freeCashFlowToEquityTTM,
        Long freeCashFlowToFirmTTM,
        Long tangibleAssetValueTTM,
        Long netCurrentAssetValueTTM)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}

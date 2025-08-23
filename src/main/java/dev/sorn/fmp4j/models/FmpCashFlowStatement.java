package dev.sorn.fmp4j.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record FmpCashFlowStatement(
        LocalDate date,
        String symbol,
        String reportedCurrency,
        String cik,
        LocalDate filingDate,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime acceptedDate,
        String fiscalYear,
        String period,
        Long netIncome,
        Long depreciationAndAmortization,
        Long deferredIncomeTax,
        Long stockBasedCompensation,
        Long changeInWorkingCapital,
        Long accountsReceivables,
        Long inventory,
        Long accountsPayables,
        Long otherWorkingCapital,
        Long otherNonCashItems,
        Long netCashProvidedByOperatingActivities,
        Long investmentsInPropertyPlantAndEquipment,
        Long acquisitionsNet,
        Long purchasesOfInvestments,
        Long salesMaturitiesOfInvestments,
        Long otherInvestingActivities,
        Long netCashProvidedByInvestingActivities,
        Long netDebtIssuance,
        Long longTermNetDebtIssuance,
        Long shortTermNetDebtIssuance,
        Long netStockIssuance,
        Long netCommonStockIssuance,
        Long commonStockIssuance,
        Long commonStockRepurchased,
        Long netPreferredStockIssuance,
        Long netDividendsPaid,
        Long commonDividendsPaid,
        Long preferredDividendsPaid,
        Long otherFinancingActivities,
        Long netCashProvidedByFinancingActivities,
        Long effectOfForexChangesOnCash,
        Long netChangeInCash,
        Long cashAtEndOfPeriod,
        Long cashAtBeginningOfPeriod,
        Long operatingCashFlow,
        Long capitalExpenditure,
        Long freeCashFlow,
        Long incomeTaxesPaid,
        Long interestPaid)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}

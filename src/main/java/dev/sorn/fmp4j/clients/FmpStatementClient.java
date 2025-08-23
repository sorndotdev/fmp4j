package dev.sorn.fmp4j.clients;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpBalanceSheetStatement;
import dev.sorn.fmp4j.models.FmpCashFlowStatement;
import dev.sorn.fmp4j.models.FmpEnterpriseValue;
import dev.sorn.fmp4j.models.FmpFinancialGrowth;
import dev.sorn.fmp4j.models.FmpFinancialStatementAsReported;
import dev.sorn.fmp4j.models.FmpIncomeStatement;
import dev.sorn.fmp4j.models.FmpKeyMetric;
import dev.sorn.fmp4j.models.FmpKeyMetricTtm;
import dev.sorn.fmp4j.models.FmpRatio;
import dev.sorn.fmp4j.models.FmpRatioTtm;
import dev.sorn.fmp4j.models.FmpRevenueGeographicSegmentation;
import dev.sorn.fmp4j.models.FmpRevenueProductSegmentation;
import dev.sorn.fmp4j.services.FmpBalanceSheetStatementService;
import dev.sorn.fmp4j.services.FmpCashFlowStatementService;
import dev.sorn.fmp4j.services.FmpEnterpriseValuesService;
import dev.sorn.fmp4j.services.FmpFinancialGrowthService;
import dev.sorn.fmp4j.services.FmpFinancialStatementAsReportedService;
import dev.sorn.fmp4j.services.FmpIncomeStatementService;
import dev.sorn.fmp4j.services.FmpKeyMetricService;
import dev.sorn.fmp4j.services.FmpKeyMetricTtmService;
import dev.sorn.fmp4j.services.FmpRatioService;
import dev.sorn.fmp4j.services.FmpRatioTtmService;
import dev.sorn.fmp4j.services.FmpRevenueGeographicSegmentationService;
import dev.sorn.fmp4j.services.FmpRevenueProductSegmentationService;
import dev.sorn.fmp4j.services.FmpService;
import java.util.Optional;

public class FmpStatementClient {
    protected static final int DEFAULT_LIMIT = 5;

    protected final FmpService<FmpIncomeStatement[]> incomeStatementService;
    protected final FmpService<FmpBalanceSheetStatement[]> balanceSheetStatementService;
    protected final FmpService<FmpCashFlowStatement[]> cashFlowStatementService;
    protected final FmpService<FmpFinancialStatementAsReported[]> incomeStatementAsReportedService;
    protected final FmpService<FmpFinancialStatementAsReported[]> balanceSheetStatementAsReportedService;
    protected final FmpService<FmpFinancialStatementAsReported[]> cashFlowStatementAsReportedService;
    protected final FmpService<FmpFinancialGrowth[]> financialGrowthService;
    protected final FmpService<FmpRatio[]> ratioService;
    protected final FmpService<FmpRatioTtm[]> ratioTtmService;
    protected final FmpService<FmpKeyMetric[]> keyMetricService;
    protected final FmpService<FmpKeyMetricTtm[]> keyMetricTtmService;
    protected final FmpService<FmpEnterpriseValue[]> enterpriseValuesService;
    protected final FmpService<FmpRevenueGeographicSegmentation[]> revenueGeographicSegmentationService;
    protected final FmpService<FmpRevenueProductSegmentation[]> revenueProductSegmentationService;

    public FmpStatementClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.incomeStatementService = new FmpIncomeStatementService(fmpConfig, fmpHttpClient);
        this.balanceSheetStatementService = new FmpBalanceSheetStatementService(fmpConfig, fmpHttpClient);
        this.cashFlowStatementService = new FmpCashFlowStatementService(fmpConfig, fmpHttpClient);
        this.incomeStatementAsReportedService =
                new FmpFinancialStatementAsReportedService(fmpConfig, fmpHttpClient, "income");
        this.balanceSheetStatementAsReportedService =
                new FmpFinancialStatementAsReportedService(fmpConfig, fmpHttpClient, "balance-sheet");
        this.cashFlowStatementAsReportedService =
                new FmpFinancialStatementAsReportedService(fmpConfig, fmpHttpClient, "cash-flow");
        this.financialGrowthService = new FmpFinancialGrowthService(fmpConfig, fmpHttpClient);
        this.ratioService = new FmpRatioService(fmpConfig, fmpHttpClient);
        this.ratioTtmService = new FmpRatioTtmService(fmpConfig, fmpHttpClient);
        this.keyMetricService = new FmpKeyMetricService(fmpConfig, fmpHttpClient);
        this.keyMetricTtmService = new FmpKeyMetricTtmService(fmpConfig, fmpHttpClient);
        this.enterpriseValuesService = new FmpEnterpriseValuesService(fmpConfig, fmpHttpClient);
        this.revenueGeographicSegmentationService =
                new FmpRevenueGeographicSegmentationService(fmpConfig, fmpHttpClient);
        this.revenueProductSegmentationService = new FmpRevenueProductSegmentationService(fmpConfig, fmpHttpClient);
    }

    public synchronized FmpIncomeStatement[] income(String symbol, Optional<String> period, Optional<Integer> limit) {
        incomeStatementService.param("symbol", symbol);
        incomeStatementService.param("period", period.orElse("annual"));
        incomeStatementService.param("limit", limit.orElse(DEFAULT_LIMIT));
        return incomeStatementService.download();
    }

    public synchronized FmpFinancialStatementAsReported[] incomeAsReported(
            String symbol, Optional<String> period, Optional<Integer> limit) {
        incomeStatementAsReportedService.param("symbol", symbol);
        incomeStatementAsReportedService.param("period", period.orElse("annual"));
        incomeStatementAsReportedService.param("limit", limit.orElse(DEFAULT_LIMIT));
        return incomeStatementAsReportedService.download();
    }

    public synchronized FmpBalanceSheetStatement[] balanceSheet(
            String symbol, Optional<String> period, Optional<Integer> limit) {
        balanceSheetStatementService.param("symbol", symbol);
        balanceSheetStatementService.param("period", period.orElse("annual"));
        balanceSheetStatementService.param("limit", limit.orElse(DEFAULT_LIMIT));
        return balanceSheetStatementService.download();
    }

    public synchronized FmpFinancialStatementAsReported[] balanceSheetAsReported(
            String symbol, Optional<String> period, Optional<Integer> limit) {
        balanceSheetStatementAsReportedService.param("symbol", symbol);
        balanceSheetStatementAsReportedService.param("period", period.orElse("annual"));
        balanceSheetStatementAsReportedService.param("limit", limit.orElse(DEFAULT_LIMIT));
        return balanceSheetStatementAsReportedService.download();
    }

    public synchronized FmpCashFlowStatement[] cashFlow(
            String symbol, Optional<String> period, Optional<Integer> limit) {
        cashFlowStatementService.param("symbol", symbol);
        cashFlowStatementService.param("period", period.orElse("annual"));
        cashFlowStatementService.param("limit", limit.orElse(DEFAULT_LIMIT));
        return cashFlowStatementService.download();
    }

    public synchronized FmpFinancialStatementAsReported[] cashFlowAsReported(
            String symbol, Optional<String> period, Optional<Integer> limit) {
        cashFlowStatementAsReportedService.param("symbol", symbol);
        cashFlowStatementAsReportedService.param("period", period.orElse("annual"));
        cashFlowStatementAsReportedService.param("limit", limit.orElse(DEFAULT_LIMIT));
        return cashFlowStatementAsReportedService.download();
    }

    public synchronized FmpFinancialGrowth[] financialGrowth(
            String symbol, Optional<String> period, Optional<Integer> limit) {
        financialGrowthService.param("symbol", symbol);
        financialGrowthService.param("period", period.orElse("annual"));
        financialGrowthService.param("limit", limit.orElse(DEFAULT_LIMIT));
        return financialGrowthService.download();
    }

    public synchronized FmpRatio[] ratios(String symbol, Optional<String> period, Optional<Integer> limit) {
        ratioService.param("symbol", symbol);
        ratioService.param("period", period.orElse("annual"));
        ratioService.param("limit", limit.orElse(DEFAULT_LIMIT));
        return ratioService.download();
    }

    public synchronized FmpRatioTtm[] ratiosTtm(String symbol) {
        ratioTtmService.param("symbol", symbol);
        return ratioTtmService.download();
    }

    public synchronized FmpKeyMetric[] keyMetrics(String symbol, Optional<String> period, Optional<Integer> limit) {
        keyMetricService.param("symbol", symbol);
        keyMetricService.param("period", period.orElse("annual"));
        keyMetricService.param("limit", limit.orElse(DEFAULT_LIMIT));
        return keyMetricService.download();
    }

    public synchronized FmpKeyMetricTtm[] keyMetricsTtm(String symbol) {
        keyMetricTtmService.param("symbol", symbol);
        return keyMetricTtmService.download();
    }

    public synchronized FmpEnterpriseValue[] enterpriseValues(
            String symbol, Optional<String> period, Optional<Integer> limit) {
        enterpriseValuesService.param("symbol", symbol);
        enterpriseValuesService.param("period", period.orElse("annual"));
        enterpriseValuesService.param("limit", limit.orElse(DEFAULT_LIMIT));
        return enterpriseValuesService.download();
    }

    public synchronized FmpRevenueGeographicSegmentation[] revenueGeographicSegmentations(
            String symbol, Optional<String> period, Optional<String> structure) {
        revenueGeographicSegmentationService.param("symbol", symbol);
        revenueGeographicSegmentationService.param("period", period.orElse("annual"));
        revenueGeographicSegmentationService.param("structure", structure.orElse("flat"));
        return revenueGeographicSegmentationService.download();
    }

    public synchronized FmpRevenueProductSegmentation[] revenueProductSegmentations(
            String symbol, Optional<String> period, Optional<String> structure) {
        revenueProductSegmentationService.param("symbol", symbol);
        revenueProductSegmentationService.param("period", period.orElse("annual"));
        revenueProductSegmentationService.param("structure", structure.orElse("flat"));
        return revenueProductSegmentationService.download();
    }
}

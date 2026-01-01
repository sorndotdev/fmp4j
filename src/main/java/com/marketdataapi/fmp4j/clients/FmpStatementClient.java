package com.marketdataapi.fmp4j.clients;

import static com.marketdataapi.fmp4j.types.FmpLimit.limit;
import static com.marketdataapi.fmp4j.types.FmpPeriod.ANNUAL;
import static com.marketdataapi.fmp4j.types.FmpStructure.FLAT;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_PERIOD;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_STRUCTURE;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOL;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpBalanceSheetStatement;
import com.marketdataapi.fmp4j.models.FmpBalanceSheetStatementGrowth;
import com.marketdataapi.fmp4j.models.FmpCashFlowStatement;
import com.marketdataapi.fmp4j.models.FmpCashFlowStatementGrowth;
import com.marketdataapi.fmp4j.models.FmpEnterpriseValue;
import com.marketdataapi.fmp4j.models.FmpFinancialGrowth;
import com.marketdataapi.fmp4j.models.FmpFinancialStatementAsReported;
import com.marketdataapi.fmp4j.models.FmpIncomeStatement;
import com.marketdataapi.fmp4j.models.FmpIncomeStatementGrowth;
import com.marketdataapi.fmp4j.models.FmpKeyMetric;
import com.marketdataapi.fmp4j.models.FmpKeyMetricTtm;
import com.marketdataapi.fmp4j.models.FmpRatio;
import com.marketdataapi.fmp4j.models.FmpRatioTtm;
import com.marketdataapi.fmp4j.models.FmpRevenueGeographicSegmentation;
import com.marketdataapi.fmp4j.models.FmpRevenueProductSegmentation;
import com.marketdataapi.fmp4j.services.FmpBalanceSheetStatementGrowthService;
import com.marketdataapi.fmp4j.services.FmpBalanceSheetStatementService;
import com.marketdataapi.fmp4j.services.FmpBalanceSheetStatementTtmService;
import com.marketdataapi.fmp4j.services.FmpCashFlowStatementGrowthService;
import com.marketdataapi.fmp4j.services.FmpCashFlowStatementService;
import com.marketdataapi.fmp4j.services.FmpCashFlowStatementTtmService;
import com.marketdataapi.fmp4j.services.FmpEnterpriseValuesService;
import com.marketdataapi.fmp4j.services.FmpFinancialGrowthService;
import com.marketdataapi.fmp4j.services.FmpFinancialStatementAsReportedService;
import com.marketdataapi.fmp4j.services.FmpIncomeStatementGrowthService;
import com.marketdataapi.fmp4j.services.FmpIncomeStatementService;
import com.marketdataapi.fmp4j.services.FmpIncomeStatementTtmService;
import com.marketdataapi.fmp4j.services.FmpKeyMetricService;
import com.marketdataapi.fmp4j.services.FmpKeyMetricTtmService;
import com.marketdataapi.fmp4j.services.FmpRatioService;
import com.marketdataapi.fmp4j.services.FmpRatioTtmService;
import com.marketdataapi.fmp4j.services.FmpRevenueGeographicSegmentationService;
import com.marketdataapi.fmp4j.services.FmpRevenueProductSegmentationService;
import com.marketdataapi.fmp4j.services.FmpService;
import com.marketdataapi.fmp4j.types.FmpLimit;
import com.marketdataapi.fmp4j.types.FmpPeriod;
import com.marketdataapi.fmp4j.types.FmpStructure;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.util.List;
import java.util.Optional;

public class FmpStatementClient {
    protected static final FmpLimit DEFAULT_LIMIT = limit(5);

    protected final FmpService<FmpIncomeStatement> incomeStatementService;
    protected final FmpService<FmpBalanceSheetStatement> balanceSheetStatementService;
    protected final FmpService<FmpCashFlowStatement> cashFlowStatementService;
    protected final FmpService<FmpFinancialStatementAsReported> incomeStatementAsReportedService;
    protected final FmpService<FmpFinancialStatementAsReported> balanceSheetStatementAsReportedService;
    protected final FmpService<FmpFinancialStatementAsReported> cashFlowStatementAsReportedService;
    protected final FmpService<FmpIncomeStatementGrowth> incomeStatementGrowthService;
    protected final FmpService<FmpBalanceSheetStatementGrowth> balanceSheetStatementGrowthService;
    protected final FmpService<FmpCashFlowStatementGrowth> cashFlowStatementGrowthService;
    protected final FmpService<FmpIncomeStatement> incomeStatementTtmService;
    protected final FmpService<FmpBalanceSheetStatement> balanceSheetStatementTtmService;
    protected final FmpService<FmpCashFlowStatement> cashFlowStatementTtmService;
    protected final FmpService<FmpFinancialGrowth> financialGrowthService;
    protected final FmpService<FmpRatio> ratioService;
    protected final FmpService<FmpRatioTtm> ratioTtmService;
    protected final FmpService<FmpKeyMetric> keyMetricService;
    protected final FmpService<FmpKeyMetricTtm> keyMetricTtmService;
    protected final FmpService<FmpEnterpriseValue> enterpriseValuesService;
    protected final FmpService<FmpRevenueGeographicSegmentation> revenueGeographicSegmentationService;
    protected final FmpService<FmpRevenueProductSegmentation> revenueProductSegmentationService;

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
        this.incomeStatementTtmService = new FmpIncomeStatementTtmService(fmpConfig, fmpHttpClient);
        this.balanceSheetStatementTtmService = new FmpBalanceSheetStatementTtmService(fmpConfig, fmpHttpClient);
        this.cashFlowStatementTtmService = new FmpCashFlowStatementTtmService(fmpConfig, fmpHttpClient);
        this.incomeStatementGrowthService = new FmpIncomeStatementGrowthService(fmpConfig, fmpHttpClient);
        this.balanceSheetStatementGrowthService = new FmpBalanceSheetStatementGrowthService(fmpConfig, fmpHttpClient);
        this.cashFlowStatementGrowthService = new FmpCashFlowStatementGrowthService(fmpConfig, fmpHttpClient);
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

    public synchronized List<FmpIncomeStatement> income(
            FmpSymbol symbol, Optional<FmpPeriod> period, Optional<FmpLimit> limit) {
        incomeStatementService.param(PARAM_SYMBOL, symbol);
        incomeStatementService.param(PARAM_PERIOD, period.orElse(ANNUAL));
        incomeStatementService.param(PARAM_LIMIT, limit.orElse(DEFAULT_LIMIT));
        return incomeStatementService.download();
    }

    public synchronized List<FmpFinancialStatementAsReported> incomeAsReported(
            FmpSymbol symbol, Optional<FmpPeriod> period, Optional<FmpLimit> limit) {
        incomeStatementAsReportedService.param(PARAM_SYMBOL, symbol);
        incomeStatementAsReportedService.param(PARAM_PERIOD, period.orElse(ANNUAL));
        incomeStatementAsReportedService.param(PARAM_LIMIT, limit.orElse(DEFAULT_LIMIT));
        return incomeStatementAsReportedService.download();
    }

    public synchronized List<FmpIncomeStatementGrowth> incomeGrowth(
            FmpSymbol symbol, Optional<FmpPeriod> period, Optional<FmpLimit> limit) {
        incomeStatementGrowthService.param(PARAM_SYMBOL, symbol);
        incomeStatementGrowthService.param(PARAM_PERIOD, period.orElse(ANNUAL));
        incomeStatementGrowthService.param(PARAM_LIMIT, limit.orElse(DEFAULT_LIMIT));
        return incomeStatementGrowthService.download();
    }

    public synchronized List<FmpIncomeStatement> incomeTtm(FmpSymbol symbol, Optional<FmpLimit> limit) {
        incomeStatementTtmService.param(PARAM_SYMBOL, symbol);
        incomeStatementTtmService.param(PARAM_LIMIT, limit.orElse(DEFAULT_LIMIT));
        return incomeStatementTtmService.download();
    }

    public synchronized List<FmpBalanceSheetStatement> balanceSheet(
            FmpSymbol symbol, Optional<FmpPeriod> period, Optional<FmpLimit> limit) {
        balanceSheetStatementService.param(PARAM_SYMBOL, symbol);
        balanceSheetStatementService.param(PARAM_PERIOD, period.orElse(ANNUAL));
        balanceSheetStatementService.param(PARAM_LIMIT, limit.orElse(DEFAULT_LIMIT));
        return balanceSheetStatementService.download();
    }

    public synchronized List<FmpFinancialStatementAsReported> balanceSheetAsReported(
            FmpSymbol symbol, Optional<FmpPeriod> period, Optional<FmpLimit> limit) {
        balanceSheetStatementAsReportedService.param(PARAM_SYMBOL, symbol);
        balanceSheetStatementAsReportedService.param(PARAM_PERIOD, period.orElse(ANNUAL));
        balanceSheetStatementAsReportedService.param(PARAM_LIMIT, limit.orElse(DEFAULT_LIMIT));
        return balanceSheetStatementAsReportedService.download();
    }

    public synchronized List<FmpBalanceSheetStatementGrowth> balanceSheetGrowth(
            FmpSymbol symbol, Optional<FmpPeriod> period, Optional<FmpLimit> limit) {
        balanceSheetStatementGrowthService.param(PARAM_SYMBOL, symbol);
        balanceSheetStatementGrowthService.param(PARAM_PERIOD, period.orElse(ANNUAL));
        balanceSheetStatementGrowthService.param(PARAM_LIMIT, limit.orElse(DEFAULT_LIMIT));
        return balanceSheetStatementGrowthService.download();
    }

    public synchronized List<FmpBalanceSheetStatement> balanceSheetTtm(FmpSymbol symbol, Optional<FmpLimit> limit) {
        balanceSheetStatementTtmService.param(PARAM_SYMBOL, symbol);
        balanceSheetStatementTtmService.param(PARAM_LIMIT, limit.orElse(DEFAULT_LIMIT));
        return balanceSheetStatementTtmService.download();
    }

    public synchronized List<FmpCashFlowStatement> cashFlow(
            FmpSymbol symbol, Optional<FmpPeriod> period, Optional<FmpLimit> limit) {
        cashFlowStatementService.param(PARAM_SYMBOL, symbol);
        cashFlowStatementService.param(PARAM_PERIOD, period.orElse(ANNUAL));
        cashFlowStatementService.param(PARAM_LIMIT, limit.orElse(DEFAULT_LIMIT));
        return cashFlowStatementService.download();
    }

    public synchronized List<FmpFinancialStatementAsReported> cashFlowAsReported(
            FmpSymbol symbol, Optional<FmpPeriod> period, Optional<FmpLimit> limit) {
        cashFlowStatementAsReportedService.param(PARAM_SYMBOL, symbol);
        cashFlowStatementAsReportedService.param(PARAM_PERIOD, period.orElse(ANNUAL));
        cashFlowStatementAsReportedService.param(PARAM_LIMIT, limit.orElse(DEFAULT_LIMIT));
        return cashFlowStatementAsReportedService.download();
    }

    public synchronized List<FmpCashFlowStatementGrowth> cashFlowGrowth(
            FmpSymbol symbol, Optional<FmpPeriod> period, Optional<FmpLimit> limit) {
        cashFlowStatementGrowthService.param(PARAM_SYMBOL, symbol);
        cashFlowStatementGrowthService.param(PARAM_PERIOD, period.orElse(ANNUAL));
        cashFlowStatementGrowthService.param(PARAM_LIMIT, limit.orElse(DEFAULT_LIMIT));
        return cashFlowStatementGrowthService.download();
    }

    public synchronized List<FmpCashFlowStatement> cashFlowTtm(FmpSymbol symbol, Optional<FmpLimit> limit) {
        cashFlowStatementTtmService.param(PARAM_SYMBOL, symbol);
        cashFlowStatementTtmService.param(PARAM_LIMIT, limit.orElse(DEFAULT_LIMIT));
        return cashFlowStatementTtmService.download();
    }

    public synchronized List<FmpFinancialGrowth> financialGrowth(
            FmpSymbol symbol, Optional<FmpPeriod> period, Optional<FmpLimit> limit) {
        financialGrowthService.param(PARAM_SYMBOL, symbol);
        financialGrowthService.param(PARAM_PERIOD, period.orElse(ANNUAL));
        financialGrowthService.param(PARAM_LIMIT, limit.orElse(DEFAULT_LIMIT));
        return financialGrowthService.download();
    }

    public synchronized List<FmpKeyMetric> keyMetrics(
            FmpSymbol symbol, Optional<FmpPeriod> period, Optional<FmpLimit> limit) {
        keyMetricService.param(PARAM_SYMBOL, symbol);
        keyMetricService.param(PARAM_PERIOD, period.orElse(ANNUAL));
        keyMetricService.param(PARAM_LIMIT, limit.orElse(DEFAULT_LIMIT));
        return keyMetricService.download();
    }

    public synchronized List<FmpKeyMetricTtm> keyMetricsTtm(FmpSymbol symbol) {
        keyMetricTtmService.param(PARAM_SYMBOL, symbol);
        return keyMetricTtmService.download();
    }

    public synchronized List<FmpRatio> ratios(FmpSymbol symbol, Optional<FmpPeriod> period, Optional<FmpLimit> limit) {
        ratioService.param(PARAM_SYMBOL, symbol);
        ratioService.param(PARAM_PERIOD, period.orElse(ANNUAL));
        ratioService.param(PARAM_LIMIT, limit.orElse(DEFAULT_LIMIT));
        return ratioService.download();
    }

    public synchronized List<FmpRatioTtm> ratiosTtm(FmpSymbol symbol) {
        ratioTtmService.param(PARAM_SYMBOL, symbol);
        return ratioTtmService.download();
    }

    public synchronized List<FmpEnterpriseValue> enterpriseValues(
            FmpSymbol symbol, Optional<FmpPeriod> period, Optional<FmpLimit> limit) {
        enterpriseValuesService.param(PARAM_SYMBOL, symbol);
        enterpriseValuesService.param(PARAM_PERIOD, period.orElse(ANNUAL));
        enterpriseValuesService.param(PARAM_LIMIT, limit.orElse(DEFAULT_LIMIT));
        return enterpriseValuesService.download();
    }

    public synchronized List<FmpRevenueGeographicSegmentation> revenueGeographicSegmentations(
            FmpSymbol symbol, Optional<FmpPeriod> period, Optional<FmpStructure> structure) {
        revenueGeographicSegmentationService.param(PARAM_SYMBOL, symbol);
        revenueGeographicSegmentationService.param(PARAM_PERIOD, period.orElse(ANNUAL));
        revenueGeographicSegmentationService.param(PARAM_STRUCTURE, structure.orElse(FLAT));
        return revenueGeographicSegmentationService.download();
    }

    public synchronized List<FmpRevenueProductSegmentation> revenueProductSegmentations(
            FmpSymbol symbol, Optional<FmpPeriod> period, Optional<FmpStructure> structure) {
        revenueProductSegmentationService.param(PARAM_SYMBOL, symbol);
        revenueProductSegmentationService.param(PARAM_PERIOD, period.orElse(ANNUAL));
        revenueProductSegmentationService.param(PARAM_STRUCTURE, structure.orElse(FLAT));
        return revenueProductSegmentationService.download();
    }
}

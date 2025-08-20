package dev.sorn.fmp4j;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.clients.FmpCalendarClient;
import dev.sorn.fmp4j.clients.FmpChartClient;
import dev.sorn.fmp4j.clients.FmpDirectoryClient;
import dev.sorn.fmp4j.clients.FmpSearchClient;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpBalanceSheetStatement;
import dev.sorn.fmp4j.models.FmpCashFlowStatement;
import dev.sorn.fmp4j.models.FmpCompany;
import dev.sorn.fmp4j.models.FmpEnterpriseValue;
import dev.sorn.fmp4j.models.FmpEtfAssetExposure;
import dev.sorn.fmp4j.models.FmpEtfCountryWeighting;
import dev.sorn.fmp4j.models.FmpEtfHolding;
import dev.sorn.fmp4j.models.FmpEtfInfo;
import dev.sorn.fmp4j.models.FmpEtfSectorWeighting;
import dev.sorn.fmp4j.models.FmpIncomeStatement;
import dev.sorn.fmp4j.models.FmpKeyMetric;
import dev.sorn.fmp4j.models.FmpKeyMetricTtm;
import dev.sorn.fmp4j.models.FmpQuote;
import dev.sorn.fmp4j.models.FmpRatio;
import dev.sorn.fmp4j.models.FmpRatioTtm;
import dev.sorn.fmp4j.models.FmpRevenueGeographicSegmentation;
import dev.sorn.fmp4j.models.FmpRevenueProductSegmentation;
import dev.sorn.fmp4j.models.FmpShortQuote;
import dev.sorn.fmp4j.services.*;
import java.util.Optional;
import static dev.sorn.fmp4j.cfg.FmpConfigImpl.FMP_CONFIG;
import static dev.sorn.fmp4j.http.FmpHttpClientImpl.FMP_HTTP_CLIENT;

public class FmpClient {
    protected static final int DEFAULT_LIMIT = 5;
    protected final FmpConfig fmpConfig;
    protected final FmpHttpClient fmpHttpClient;

    // Search
    protected final FmpSearchClient fmpSearchClient;

    // Directory
    protected final FmpDirectoryClient fmpDirectoryClient;

    // Calendar
    protected final FmpCalendarClient fmpCalendarClient;

    // Chart
    protected final FmpChartClient fmpChartClient;

    // Company
    protected final FmpService<FmpCompany[]> fmpCompanyService;

    // Statements
    protected final FmpService<FmpIncomeStatement[]> incomeStatementService;
    protected final FmpService<FmpBalanceSheetStatement[]> balanceSheetStatementService;
    protected final FmpService<FmpCashFlowStatement[]> cashFlowStatementService;
    protected final FmpService<FmpRatio[]> ratioService;
    protected final FmpService<FmpRatioTtm[]> ratioTtmService;
    protected final FmpService<FmpKeyMetric[]> keyMetricService;
    protected final FmpService<FmpKeyMetricTtm[]> keyMetricTtmService;
    protected final FmpService<FmpEnterpriseValue[]> enterpriseValuesService;
    protected final FmpService<FmpRevenueGeographicSegmentation[]> revenueGeographicSegmentationService;
    protected final FmpService<FmpRevenueProductSegmentation[]> revenueProductSegmentationService;

    // ETF & Mutual Funds
    protected final FmpService<FmpEtfAssetExposure[]> etfAssetExposureService;
    protected final FmpService<FmpEtfCountryWeighting[]> etfCountryWeightingService;
    protected final FmpService<FmpEtfHolding[]> etfHoldingService;
    protected final FmpService<FmpEtfInfo[]> etfInfoService;
    protected final FmpService<FmpEtfSectorWeighting[]> etfSectorWeightingService;

    // Quotes
    protected final FmpService<FmpQuote[]> quoteService;
    protected final FmpService<FmpShortQuote[]> shortQuoteService;

    public FmpClient() {
        this(FMP_CONFIG, FMP_HTTP_CLIENT);
    }

    public FmpClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this(
            fmpConfig,
            fmpHttpClient,

            // Search
            new FmpSearchClient(fmpConfig, fmpHttpClient),

            // Directory
            new FmpDirectoryClient(fmpConfig, fmpHttpClient),

            // Calendar
            new FmpCalendarClient(fmpConfig, fmpHttpClient),

            // Chart
            new FmpChartClient(fmpConfig, fmpHttpClient),

            // Company
            new FmpCompanyService(fmpConfig, fmpHttpClient),

            // Statements
            new FmpIncomeStatementService(fmpConfig, fmpHttpClient),
            new FmpBalanceSheetStatementService(fmpConfig, fmpHttpClient),
            new FmpCashFlowStatementService(fmpConfig, fmpHttpClient),
            new FmpRatioService(fmpConfig, fmpHttpClient),
            new FmpRatioTtmService(fmpConfig, fmpHttpClient),
            new FmpKeyMetricService(fmpConfig, fmpHttpClient),
            new FmpKeyMetricTtmService(fmpConfig, fmpHttpClient),
            new FmpEnterpriseValuesService(fmpConfig, fmpHttpClient),
            new FmpRevenueGeographicSegmentationService(fmpConfig, fmpHttpClient),
            new FmpRevenueProductSegmentationService(fmpConfig, fmpHttpClient),

            // ETF & Mutual Funds
            new FmpEtfAssetExposureService(fmpConfig, fmpHttpClient),
            new FmpEtfCountryWeightingService(fmpConfig, fmpHttpClient),
            new FmpEtfHoldingService(fmpConfig, fmpHttpClient),
            new FmpEtfInfoService(fmpConfig, fmpHttpClient),
            new FmpEtfSectorWeightingService(fmpConfig, fmpHttpClient),

            // Quotes
            new FmpQuoteService(fmpConfig, fmpHttpClient),
            new FmpShortQuoteService(fmpConfig, fmpHttpClient)
        );
    }

    public FmpClient(
        FmpConfig fmpConfig,
        FmpHttpClient fmpHttpClient,

        // Search
        FmpSearchClient fmpSearchClient,

        // Directory
        FmpDirectoryClient fmpDirectoryClient,

        // Calendar
        FmpCalendarClient fmpCalendarClient,

        // Chart
        FmpChartClient fmpChartClient,

        // Company
        FmpCompanyService fmpCompanyService,

        // Statements
        FmpIncomeStatementService incomeStatementService,
        FmpBalanceSheetStatementService balanceSheetStatementService,
        FmpCashFlowStatementService cashFlowStatementService,
        FmpRatioService ratioService,
        FmpRatioTtmService ratioTtmService,
        FmpKeyMetricService keyMetricService,
        FmpKeyMetricTtmService keyMetricTtmService,
        FmpEnterpriseValuesService enterpriseValuesService,
        FmpRevenueGeographicSegmentationService revenueGeographicSegmentationService,
        FmpRevenueProductSegmentationService revenueProductSegmentationService,

        // ETF & Mutual Funds
        FmpEtfAssetExposureService etfAssetExposureService,
        FmpEtfCountryWeightingService etfCountryWeightingService,
        FmpEtfHoldingService etfHoldingService,
        FmpEtfInfoService etfInfoService,
        FmpEtfSectorWeightingService etfSectorWeightingService,

        // Quotes
        FmpQuoteService quoteService,
        FmpShortQuoteService shortQuoteService
    ) {
        this.fmpConfig = fmpConfig;
        this.fmpHttpClient = fmpHttpClient;

        // Search
        this.fmpSearchClient = fmpSearchClient;

        // Directory
        this.fmpDirectoryClient = fmpDirectoryClient;

        // Calendar
        this.fmpCalendarClient = fmpCalendarClient;

        // Chart
        this.fmpChartClient = fmpChartClient;

        // Company
        this.fmpCompanyService = fmpCompanyService;

        // Statements
        this.incomeStatementService = incomeStatementService;
        this.balanceSheetStatementService = balanceSheetStatementService;
        this.cashFlowStatementService = cashFlowStatementService;
        this.ratioService = ratioService;
        this.ratioTtmService = ratioTtmService;
        this.keyMetricService = keyMetricService;
        this.keyMetricTtmService = keyMetricTtmService;
        this.enterpriseValuesService = enterpriseValuesService;
        this.revenueGeographicSegmentationService = revenueGeographicSegmentationService;
        this.revenueProductSegmentationService = revenueProductSegmentationService;

        // ETF & Mutual Funds
        this.etfAssetExposureService = etfAssetExposureService;
        this.etfCountryWeightingService = etfCountryWeightingService;
        this.etfHoldingService = etfHoldingService;
        this.etfInfoService = etfInfoService;
        this.etfSectorWeightingService = etfSectorWeightingService;

        // Quotes
        this.quoteService = quoteService;
        this.shortQuoteService = shortQuoteService;
    }



    public FmpSearchClient search() {
        return fmpSearchClient;
    }

    public FmpDirectoryClient list() {
        return fmpDirectoryClient;
    }

    public FmpCalendarClient calendar() {
        return fmpCalendarClient;
    }

    public FmpChartClient chart() {
        return fmpChartClient;
    }

    public synchronized FmpCompany[] company(String symbol) {
        fmpCompanyService.param("symbol", symbol);
        return fmpCompanyService.download();
    }

    public synchronized FmpIncomeStatement[] incomeStatements(
        String symbol,
        Optional<String> period,
        Optional<Integer> limit
    ) {
        incomeStatementService.param("symbol", symbol);
        incomeStatementService.param("period", period.orElse("annual"));
        incomeStatementService.param("limit", limit.orElse(DEFAULT_LIMIT));
        return incomeStatementService.download();
    }

    public synchronized FmpBalanceSheetStatement[] balanceSheetStatements(
        String symbol,
        Optional<String> period,
        Optional<Integer> limit
    ) {
        balanceSheetStatementService.param("symbol", symbol);
        balanceSheetStatementService.param("period", period.orElse("annual"));
        balanceSheetStatementService.param("limit", limit.orElse(DEFAULT_LIMIT));
        return balanceSheetStatementService.download();
    }

    public synchronized FmpCashFlowStatement[] cashFlowStatements(String symbol, Optional<String> period, Optional<Integer> limit) {
        cashFlowStatementService.param("symbol", symbol);
        cashFlowStatementService.param("period", period.orElse("annual"));
        cashFlowStatementService.param("limit", limit.orElse(DEFAULT_LIMIT));
        return cashFlowStatementService.download();
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

    public synchronized FmpKeyMetricTtm[] keyMetricTtm(String symbol) {
        keyMetricTtmService.param("symbol", symbol);
        return keyMetricTtmService.download();
    }

    public synchronized FmpEnterpriseValue[] enterpriseValues(String symbol, Optional<String> period, Optional<Integer> limit) {
        enterpriseValuesService.param("symbol", symbol);
        enterpriseValuesService.param("period", period.orElse("annual"));
        enterpriseValuesService.param("limit", limit.orElse(DEFAULT_LIMIT));
        return enterpriseValuesService.download();
    }

    public synchronized FmpRevenueGeographicSegmentation[] revenueGeographicSegmentations(String symbol, Optional<String> period, Optional<String> structure) {
        revenueGeographicSegmentationService.param("symbol", symbol);
        revenueGeographicSegmentationService.param("period", period.orElse("annual"));
        revenueGeographicSegmentationService.param("structure", structure.orElse("flat"));
        return revenueGeographicSegmentationService.download();
    }

    public synchronized FmpRevenueProductSegmentation[] revenueProductSegmentations(String symbol, Optional<String> period, Optional<String> structure) {
        revenueProductSegmentationService.param("symbol", symbol);
        revenueProductSegmentationService.param("period", period.orElse("annual"));
        revenueProductSegmentationService.param("structure", structure.orElse("flat"));
        return revenueProductSegmentationService.download();
    }

    public synchronized FmpEtfAssetExposure[] etfAssetExposure(String symbol) {
        etfAssetExposureService.param("symbol", symbol);
        return etfAssetExposureService.download();
    }

    public synchronized FmpEtfCountryWeighting[] etfCountryWeightings(String symbol) {
        etfCountryWeightingService.param("symbol", symbol);
        return etfCountryWeightingService.download();
    }

    public synchronized FmpEtfHolding[] etfHoldings(String symbol) {
        etfHoldingService.param("symbol", symbol);
        return etfHoldingService.download();
    }

    public synchronized FmpEtfInfo[] etfInfo(String symbol) {
        etfInfoService.param("symbol", symbol);
        return etfInfoService.download();
    }

    public synchronized FmpEtfSectorWeighting[] etfSectorWeightings(String symbol) {
        etfSectorWeightingService.param("symbol", symbol);
        return etfSectorWeightingService.download();
    }

    public synchronized FmpQuote[] quotes(String symbol) {
        quoteService.param("symbol", symbol);
        return quoteService.download();
    }

    public synchronized FmpShortQuote[] shortQuotes(String symbol) {
        shortQuoteService.param("symbol", symbol);
        return shortQuoteService.download();
    }
}

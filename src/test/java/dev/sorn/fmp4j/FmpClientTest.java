package dev.sorn.fmp4j;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.types.FmpCik.cik;
import static dev.sorn.fmp4j.types.FmpCusip.cusip;
import static dev.sorn.fmp4j.types.FmpInterval.interval;
import static dev.sorn.fmp4j.types.FmpIsin.isin;
import static dev.sorn.fmp4j.types.FmpLimit.limit;
import static dev.sorn.fmp4j.types.FmpPage.page;
import static dev.sorn.fmp4j.types.FmpPart.part;
import static dev.sorn.fmp4j.types.FmpPeriod.period;
import static dev.sorn.fmp4j.types.FmpQuarter.quarter;
import static dev.sorn.fmp4j.types.FmpStructure.FLAT;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static dev.sorn.fmp4j.types.FmpYear.year;
import static java.lang.String.format;
import static java.lang.String.join;
import static java.lang.System.setProperty;
import static java.util.Collections.emptySet;
import static java.util.Optional.empty;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import dev.sorn.fmp4j.cfg.FmpConfigImpl;
import dev.sorn.fmp4j.models.FmpBalanceSheetStatement;
import dev.sorn.fmp4j.models.FmpBalanceSheetStatementGrowth;
import dev.sorn.fmp4j.models.FmpCashFlowStatement;
import dev.sorn.fmp4j.models.FmpCashFlowStatementGrowth;
import dev.sorn.fmp4j.models.FmpCompanies;
import dev.sorn.fmp4j.models.FmpCompany;
import dev.sorn.fmp4j.models.FmpDividend;
import dev.sorn.fmp4j.models.FmpDividendsCalendar;
import dev.sorn.fmp4j.models.FmpEarning;
import dev.sorn.fmp4j.models.FmpEarningsCalendar;
import dev.sorn.fmp4j.models.FmpEarningsCallTranscript;
import dev.sorn.fmp4j.models.FmpEarningsCallTranscriptDate;
import dev.sorn.fmp4j.models.FmpEarningsCallTranscriptLatest;
import dev.sorn.fmp4j.models.FmpEarningsCallTranscriptList;
import dev.sorn.fmp4j.models.FmpEnterpriseValue;
import dev.sorn.fmp4j.models.FmpEtf;
import dev.sorn.fmp4j.models.FmpEtfAssetExposure;
import dev.sorn.fmp4j.models.FmpEtfCountryWeighting;
import dev.sorn.fmp4j.models.FmpEtfHolding;
import dev.sorn.fmp4j.models.FmpEtfInfo;
import dev.sorn.fmp4j.models.FmpEtfSectorWeighting;
import dev.sorn.fmp4j.models.FmpFinancialGrowth;
import dev.sorn.fmp4j.models.FmpFinancialStatementAsReported;
import dev.sorn.fmp4j.models.FmpFullQuote;
import dev.sorn.fmp4j.models.FmpHistoricalChart;
import dev.sorn.fmp4j.models.FmpHistoricalPriceEodFull;
import dev.sorn.fmp4j.models.FmpHistoricalPriceEodLight;
import dev.sorn.fmp4j.models.FmpIncomeStatement;
import dev.sorn.fmp4j.models.FmpIncomeStatementGrowth;
import dev.sorn.fmp4j.models.FmpIposCalendar;
import dev.sorn.fmp4j.models.FmpIposDisclosure;
import dev.sorn.fmp4j.models.FmpIposProspectus;
import dev.sorn.fmp4j.models.FmpKeyMetric;
import dev.sorn.fmp4j.models.FmpKeyMetricTtm;
import dev.sorn.fmp4j.models.FmpNews;
import dev.sorn.fmp4j.models.FmpPartialQuote;
import dev.sorn.fmp4j.models.FmpRatio;
import dev.sorn.fmp4j.models.FmpRatioTtm;
import dev.sorn.fmp4j.models.FmpRevenueGeographicSegmentation;
import dev.sorn.fmp4j.models.FmpRevenueProductSegmentation;
import dev.sorn.fmp4j.models.FmpSearchByCik;
import dev.sorn.fmp4j.models.FmpSearchByCusip;
import dev.sorn.fmp4j.models.FmpSearchByIsin;
import dev.sorn.fmp4j.models.FmpSearchByName;
import dev.sorn.fmp4j.models.FmpSearchBySymbol;
import dev.sorn.fmp4j.models.FmpSearchPressRelease;
import dev.sorn.fmp4j.models.FmpSecFilingsSearchBySymbol;
import dev.sorn.fmp4j.models.FmpStock;
import dev.sorn.fmp4j.models.FmpStockPriceChange;
import dev.sorn.fmp4j.models.FmpTreasuryRate;
import dev.sorn.fmp4j.services.HttpTest;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class FmpClientTest extends HttpTest {
    private FmpClient fmpClient;

    @BeforeEach
    void setUp() {
        fmpClient = new FmpClient(config, client);
    }

    @Test
    void testConstructor_doesNotThrowAndCreatesInstance() {
        // given
        setProperty(FmpConfigImpl.FMP4J_API_KEY_ENV, "ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6");
        setProperty(FmpConfigImpl.FMP4J_BASE_URL_ENV, "https://financialmodelingprep.com/stable");

        // when // then
        assertNotNull(assertDoesNotThrow(() -> new FmpClient(config, client)));
    }

    @Test
    void searchPressReleases() {
        // given
        var symbol = symbol("V");
        var endpoint = "news/press-releases";
        var file = format("stable/%s/?symbols=%s.json", endpoint, symbol);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.search().pressReleases(symbol);

        // then
        assertValidResult(result, 3, FmpSearchPressRelease.class);
    }

    @Test
    void searchByIsin() {
        // given
        var isin = isin("NL0012969182");
        var endpoint = "search-isin";
        var file = format("stable/%s/?isin=%s.json", endpoint, isin);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.search().byIsin(isin);

        // then
        assertValidResult(result, 3, FmpSearchByIsin.class);
    }

    @Test
    void searchByName() {
        // given
        var query = "ADYEN";
        var endpoint = "search-name";
        var file = format("stable/%s/?query=%s.json", endpoint, query);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.search().byName(query);

        // then
        assertValidResult(result, 5, FmpSearchByName.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"quarter"})
    void cashFlowStatementGrowthBulk(String p) {
        // given
        var year = year("2025");
        var period = period(p);
        var endpoint = "cash-flow-statement-growth-bulk";
        var file = format("stable/%s/?year=%s&period=%s.csv", endpoint, year, period);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.bulk().cashFlowStatementGrowth(year, period);

        // then
        assertValidResult(result, 1, FmpCashFlowStatementGrowth.class);
    }

    @Test
    void searchByCusip() {
        // given
        var cusip = cusip("037833100");
        var endpoint = "search-cusip";
        var file = format("stable/%s/?cusip=%s.json", endpoint, cusip);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.search().byCusip(cusip);

        // then
        assertValidResult(result, 3, FmpSearchByCusip.class);
    }

    @Test
    void searchBySymbol() {
        // given
        var query = symbol("ADYEN");
        var endpoint = "search-symbol";
        var file = format("stable/%s/?query=%s.json", endpoint, query);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.search().bySymbol(query);

        // then
        assertValidResult(result, 1, FmpSearchBySymbol.class);
    }

    @Test
    void searchByCik() {
        // given
        var cik = cik("0000320193");
        var endpoint = "search-cik";
        var file = format("stable/%s/?cik=%s.json", endpoint, cik);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.search().byCik(cik);

        // then
        assertValidResult(result, 1, FmpSearchByCik.class);
    }

    @Test
    void stockDirectory() {
        // given
        var endpoint = "stock-list";
        var file = format("stable/%s/excerpt.json", endpoint);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.directory().stocks();

        // then
        assertValidResult(result, 2, FmpStock.class);
    }

    @Test
    void etfDirectory() {
        // given
        var endpoint = "etf-list";
        var file = format("stable/%s/excerpt.json", endpoint);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.directory().etfs();

        // then
        assertValidResult(result, 4, FmpEtf.class);
    }

    @Test
    void dividends_calendar() {
        // given
        var endpoint = "dividends-calendar";
        var file = format("stable/%s/excerpt.json", endpoint);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.calendar().dividends();

        // then
        assertValidResult(result, 4, FmpDividendsCalendar.class, Set.of("declarationDate"));
    }

    @Test
    void dividends() {
        // given
        var endpoint = "dividends";
        var symbol = symbol("AAPL");
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.calendar().dividends(symbol);

        // then
        assertValidResult(result, 4, FmpDividend.class, Set.of("declarationDate"));
    }

    @Test
    void earnings_calendar() {
        // given
        var endpoint = "earnings-calendar";
        var file = format("stable/%s/excerpt.json", endpoint);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.calendar().earnings();

        // then
        assertValidResult(
                result,
                4,
                FmpEarningsCalendar.class,
                Set.of("epsActual", "epsEstimated", "revenueActual", "revenueEstimated"));
    }

    @Test
    void earnings() {
        // given
        var endpoint = "earnings";
        var symbol = symbol("AAPL");
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.calendar().earnings(symbol);

        // then
        assertValidResult(
                result, 4, FmpEarning.class, Set.of("epsActual", "epsEstimated", "revenueActual", "revenueEstimated"));
    }

    @Test
    void iposCalendar() {
        // given
        var from = Optional.of(LocalDate.parse("2024-02-28"));
        var to = Optional.of(LocalDate.parse("2025-02-28"));
        var endpoint = "ipos-calendar";
        var file = format("stable/%s/excerpt.json", endpoint);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.calendar().ipos(from, to);

        // then
        assertValidResult(result, 2, FmpIposCalendar.class, Set.of("shares", "priceRange", "marketCap"));
    }

    @Test
    void iposDisclosure() {
        // given
        var from = Optional.of(LocalDate.parse("2024-02-28"));
        var to = Optional.of(LocalDate.parse("2025-02-28"));
        var endpoint = "ipos-disclosure";
        var file = format("stable/%s/excerpt.json", endpoint);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.calendar().disclosures(from, to);

        // then
        assertValidResult(result, 2, FmpIposDisclosure.class, Set.of());
    }

    @Test
    void iposProspectus() {
        // given
        var from = Optional.of(LocalDate.parse("2024-02-28"));
        var to = Optional.of(LocalDate.parse("2025-02-28"));
        var endpoint = "ipos-prospectus";
        var file = format("stable/%s/excerpt.json", endpoint);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.calendar().prospectus(from, to);

        // then
        assertValidResult(result, 2, FmpIposProspectus.class, Set.of());
    }

    @Test
    void historicalPriceEodLight() {
        // given
        var endpoint = "historical-price-eod/light";
        var symbol = symbol("AAPL");
        var from = LocalDate.parse("2024-02-22");
        var to = LocalDate.parse("2024-02-28");
        var file = format("stable/%s/?symbol=%s&from=%s&to=%s.json", endpoint, symbol, from, to);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.chart().historicalPriceEodLight(symbol, Optional.of(from), Optional.of(to));

        // then
        assertValidResult(result, 5, FmpHistoricalPriceEodLight.class, emptySet());
    }

    @Test
    void historicalPriceEodFull() {
        // given
        var endpoint = "historical-price-eod/full";
        var symbol = symbol("AAPL");
        var from = LocalDate.parse("2024-02-22");
        var to = LocalDate.parse("2024-02-28");
        var file = format("stable/%s/?symbol=%s&from=%s&to=%s.json", endpoint, symbol, from, to);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.chart().historicalPriceEodFull(symbol, Optional.of(from), Optional.of(to));

        // then
        assertValidResult(result, 5, FmpHistoricalPriceEodFull.class, emptySet());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1min", "5min", "15min", "30min", "1hour", "4hour"})
    void historicalChart(String intervalStr) {
        // given
        var endpoint = "historical-chart/" + intervalStr;
        var symbol = symbol("AAPL");
        var interval = interval(intervalStr);
        var from = LocalDate.parse("2024-01-01");
        var to = LocalDate.parse("2024-01-02");
        var file = format("stable/%s/?symbol=%s&from=%s&to=%s.json", endpoint, symbol, from, to);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.chart().historical(symbol, interval, Optional.of(from), Optional.of(to));

        // then
        assertValidResult(result, 2, FmpHistoricalChart.class, emptySet());
    }

    @Test
    void company() {
        // given
        var symbol = symbol("AAPL");
        var endpoint = "profile";
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.company().bySymbol(symbol);

        // then
        assertValidResult(result, 1, FmpCompany.class);
    }

    @Test
    void companies() {
        // given
        var part = part("0");
        var endpoint = "profile-bulk";
        var file = format("stable/%s/?part=%s.csv", endpoint, part);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.bulk().companies(part);

        // then
        assertValidResult(result, 1, FmpCompanies.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"annual", "quarter"})
    void incomeStatements(String p) {
        // given
        var period = period(p);
        var symbol = symbol("AAPL");
        var limit = limit(3);
        var endpoint = "income-statement";
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.statement().income(symbol, Optional.of(period), Optional.of(limit));

        // then
        assertValidResult(result, limit.value(), FmpIncomeStatement.class);
    }

    @Test
    void incomeStatementTtm() {
        // given
        var symbol = symbol("AAPL");
        var limit = limit(2);
        var endpoint = "income-statement-ttm";
        var file = format("stable/%s/?symbol=%s&limit=%s.json", endpoint, symbol, limit);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.statement().incomeTtm(symbol, Optional.of(limit));

        // then
        assertValidResult(result, limit.value(), FmpIncomeStatement.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"annual", "quarter"})
    void incomeStatementGrowth(String p) {
        // given
        var period = period(p);
        var symbol = symbol("AAPL");
        var limit = limit(2);
        var endpoint = "income-statement-growth";
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.statement().incomeGrowth(symbol, Optional.of(period), Optional.of(limit));

        // then
        assertValidResult(result, limit.value(), FmpIncomeStatementGrowth.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"annual", "quarter"})
    void incomeStatementsAsReported(String p) {
        // given
        var period = period(p);
        var symbol = symbol("KO");
        var limit = limit(2);
        var endpoint = "income-statement-as-reported";
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.statement().incomeAsReported(symbol, Optional.of(period), Optional.of(limit));

        // then
        assertValidResult(result, limit.value(), FmpFinancialStatementAsReported.class, Set.of("reportedCurrency"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"annual", "quarter"})
    void balanceSheetStatements(String p) {
        // given
        var period = period(p);
        var symbol = symbol("AAPL");
        var limit = limit(3);
        var endpoint = "balance-sheet-statement";
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.statement().balanceSheet(symbol, Optional.of(period), Optional.of(limit));

        // then
        assertValidResult(result, limit.value(), FmpBalanceSheetStatement.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"quarter"})
    void balanceSheetStatementsBulk(String periodType) {
        // given
        var period = period(periodType);
        var year = year("2023");
        var endpoint = "balance-sheet-statement-bulk";
        var file = String.format("stable/%s/?year=%s&period=%s.csv", endpoint, year, period);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.bulk().balanceSheetStatements(year, period);

        // then
        assertValidResult(result, 2, FmpBalanceSheetStatement.class);
    }

    @Test
    void balanceSheetStatementTtm() {
        // given
        var symbol = symbol("AAPL");
        var limit = limit(2);
        var endpoint = "balance-sheet-statement-ttm";
        var file = format("stable/%s/?symbol=%s&limit=%s.json", endpoint, symbol, limit);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.statement().balanceSheetTtm(symbol, Optional.of(limit));

        // then
        assertValidResult(
                result, limit.value(), FmpBalanceSheetStatement.class, Set.of("capitalLeaseObligationsNonCurrent"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"annual", "quarter"})
    void balanceSheetStatementGrowth(String p) {
        // given
        var period = period(p);
        var symbol = symbol("AAPL");
        var limit = limit(2);
        var endpoint = "balance-sheet-statement-growth";
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.statement().balanceSheetGrowth(symbol, Optional.of(period), Optional.of(limit));

        // then
        assertValidResult(result, limit.value(), FmpBalanceSheetStatementGrowth.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"annual", "quarter"})
    void balanceSheetStatementsAsReported(String p) {
        // given
        var period = period(p);
        var symbol = symbol("KO");
        var limit = limit(2);
        var endpoint = "balance-sheet-statement-as-reported";
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.statement().balanceSheetAsReported(symbol, Optional.of(period), Optional.of(limit));

        // then
        assertValidResult(result, limit.value(), FmpFinancialStatementAsReported.class, Set.of("reportedCurrency"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"annual", "quarter"})
    void cashFlowStatements(String p) {
        // given
        var period = period(p);
        var symbol = symbol("AAPL");
        var limit = limit(3);
        var endpoint = "cash-flow-statement";
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.statement().cashFlow(symbol, Optional.of(period), Optional.of(limit));

        // then
        assertValidResult(result, limit.value(), FmpCashFlowStatement.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"annual"})
    void cashFlowStatementsBulk(String p) {
        // given
        var period = period(p);
        var year = year("2023");
        var endpoint = "cash-flow-statement-bulk";
        var file = String.format("stable/%s/?year=%s&period=%s.csv", endpoint, year, period);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.bulk().cashFlowStatements(year, period);

        // then
        assertValidResult(result, 2, FmpCashFlowStatement.class);
    }

    @Test
    void cashFlowStatementTtm() {
        // given
        var symbol = symbol("AAPL");
        var limit = limit(2);
        var endpoint = "cash-flow-statement-ttm";
        var file = format("stable/%s/?symbol=%s&limit=%s.json", endpoint, symbol, limit);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.statement().cashFlowTtm(symbol, Optional.of(limit));

        // then
        assertValidResult(result, limit.value(), FmpCashFlowStatement.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"annual", "quarter"})
    void cashFlowStatementGrowth(String p) {
        // given
        var period = period(p);
        var symbol = symbol("AAPL");
        var limit = limit(2);
        var endpoint = "cash-flow-statement-growth";
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.statement().cashFlowGrowth(symbol, Optional.of(period), Optional.of(limit));

        // then
        assertValidResult(result, limit.value(), FmpCashFlowStatementGrowth.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"annual", "quarter"})
    void cashFlowStatementsAsReported(String p) {
        // given
        var period = period(p);
        var symbol = symbol("KO");
        var limit = limit(2);
        var endpoint = "cash-flow-statement-as-reported";
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.statement().cashFlowAsReported(symbol, Optional.of(period), Optional.of(limit));

        // then
        assertValidResult(result, limit.value(), FmpFinancialStatementAsReported.class, Set.of("reportedCurrency"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"annual", "quarter"})
    void financialGrowth(String p) {
        // given
        var period = period(p);
        var symbol = symbol("AAPL");
        var limit = limit(2);
        var endpoint = "financial-growth";
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.statement().financialGrowth(symbol, Optional.of(period), Optional.of(limit));

        // then
        assertValidResult(
                result,
                limit.value(),
                FmpFinancialGrowth.class,
                Set.of(
                        "ebitdaGrowth",
                        "growthCapitalExpenditure",
                        "tenYBottomLineNetIncomeGrowthPerShare",
                        "fiveYBottomLineNetIncomeGrowthPerShare",
                        "threeYBottomLineNetIncomeGrowthPerShare"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"annual", "quarter"})
    void ratios(String p) {
        // given
        var period = period(p);
        var symbol = symbol("AAPL");
        var limit = limit(3);
        var endpoint = "ratios";
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.statement().ratios(symbol, Optional.of(period), Optional.of(limit));

        // then
        assertValidResult(result, limit.value(), FmpRatio.class);
    }

    @Test
    void ratiosTtm() {
        // given
        var symbol = symbol("AAPL");
        var endpoint = "ratios-ttm";
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.statement().ratiosTtm(symbol);

        // then
        assertValidResult(result, 1, FmpRatioTtm.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"annual", "quarter"})
    void keyMetrics(String p) {
        // given
        var period = period(p);
        var symbol = symbol("AAPL");
        var limit = limit(3);
        var endpoint = "key-metrics";
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.statement().keyMetrics(symbol, Optional.of(period), Optional.of(limit));

        // then
        assertValidResult(result, limit.value(), FmpKeyMetric.class);
    }

    @Test
    void keyMetricsTtm() {
        // given
        var symbol = symbol("AAPL");
        var endpoint = "key-metrics-ttm";
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.statement().keyMetricsTtm(symbol);

        // then
        assertValidResult(result, 1, FmpKeyMetricTtm.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"annual", "quarter"})
    void enterpriseValues(String p) {
        // given
        var period = period(p);
        var symbol = symbol("AAPL");
        var limit = limit(3);
        var endpoint = "enterprise-values";
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.statement().enterpriseValues(symbol, Optional.of(period), Optional.of(limit));

        // then
        assertValidResult(result, 3, FmpEnterpriseValue.class);
    }

    @ParameterizedTest
    @CsvSource({"annual,15", "quarter,42"})
    void revenueProductSegmentation(String p, int expectedCount) {
        // given
        var period = period(p);
        var symbol = symbol("AAPL");
        var structure = FLAT;
        var endpoint = "revenue-product-segmentation";
        var file = format("stable/%s/?symbol=%s&period=%s&structure=%s.json", endpoint, symbol, period, structure);

        // when
        mockHttpGetFromFile(file);
        var result =
                fmpClient.statement().revenueProductSegmentations(symbol, Optional.of(period), Optional.of(structure));

        // then
        assertValidResult(result, expectedCount, FmpRevenueProductSegmentation.class, Set.of("reportedCurrency"));
    }

    @ParameterizedTest
    @CsvSource({"annual,15", "quarter,59"})
    void revenueGeographicSegmentation(String p, int expectedCount) {
        // given
        var period = period(p);
        var symbol = symbol("AAPL");
        var structure = FLAT;
        var endpoint = "revenue-geographic-segmentation";
        var file = format("stable/%s/?symbol=%s&period=%s&structure=%s.json", endpoint, symbol, period, structure);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient
                .statement()
                .revenueGeographicSegmentations(symbol, Optional.of(period), Optional.of(structure));

        // then
        assertValidResult(result, expectedCount, FmpRevenueGeographicSegmentation.class, Set.of("reportedCurrency"));
    }

    @Test
    void etfAssetExposure() {
        // given
        var symbol = symbol("NVO");
        var endpoint = "etf/asset-exposure";
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.etf().assetExposure(symbol);

        // then
        assertValidResult(result, 28, FmpEtfAssetExposure.class, emptySet());
    }

    @Test
    void etfCountryWeightings() {
        // given
        var symbol = symbol("SPY");
        var endpoint = "etf/country-weightings";
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.etf().countryWeightings(symbol);

        // then
        assertValidResult(result, 6, FmpEtfCountryWeighting.class, emptySet());
    }

    @ParameterizedTest
    @CsvSource({
        "FUSD.L,111",
        "SCHD,103",
    })
    void etfHoldings(FmpSymbol symbol, int holdings) {
        // given
        var endpoint = "etf/holdings";
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.etf().holdings(symbol);

        // then
        assertValidResult(result, holdings, FmpEtfHolding.class, Set.of("asset", "isin"));
    }

    @Test
    void etfInfo() {
        // given
        var symbol = symbol("SPY");
        var endpoint = "etf/info";
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.etf().info(symbol);

        // then
        assertValidResult(result, 1, FmpEtfInfo.class, emptySet());
    }

    @Test
    void cryptoNews() {
        // given
        var symbols = Set.of(symbol("BTCUSD"));
        var endpoint = "news/crypto";
        var file = format(
                "stable/%s/?symbols=%s.json",
                endpoint, join(",", symbols.stream().map(FmpSymbol::value).toList()));

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.news().crypto(symbols);

        // then
        assertValidResult(result, 2, FmpNews.class, emptySet());
    }

    @Test
    void cryptoNews_multiple() {
        // given
        var symbols = new TreeSet<FmpSymbol>(); // guarantee order
        symbols.add(symbol("BTCUSD"));
        symbols.add(symbol("ETHUSD"));
        var endpoint = "news/crypto";
        var file = format(
                "stable/%s/?symbols=%s.json",
                endpoint, join(",", symbols.stream().map(FmpSymbol::value).toList()));

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.news().crypto(symbols);

        // then
        assertValidResult(result, 4, FmpNews.class, emptySet());
    }

    @Test
    void forexNews() {
        // given
        var symbols = Set.of(symbol("EURUSD"));
        var endpoint = "news/forex";
        var file = format(
                "stable/%s/?symbols=%s.json",
                endpoint, join(",", symbols.stream().map(FmpSymbol::value).toList()));

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.news().forex(symbols);

        // then
        assertValidResult(result, 2, FmpNews.class, emptySet());
    }

    @Test
    void stockNews() {
        // given
        var symbols = Set.of(symbol("AAPL"));
        var endpoint = "news/stock";
        var file = format(
                "stable/%s/?symbols=%s.json",
                endpoint, join(",", symbols.stream().map(FmpSymbol::value).toList()));

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.news().stock(symbols);

        // then
        assertValidResult(result, 2, FmpNews.class, emptySet());
    }

    @Test
    void cryptoNews_withFromTo() {
        // given
        var symbols = Set.of(symbol("BTCUSD"));
        var from = Optional.of(LocalDate.of(2025, 1, 1));
        var to = Optional.of(LocalDate.of(2025, 1, 31));
        var page = page(0);
        var limit = limit(100);
        var endpoint = "news/crypto";

        var file = format(
                "stable/%s/?symbols=%s.json",
                endpoint, join(",", symbols.stream().map(FmpSymbol::value).toList()));

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.news().crypto(symbols, from, to, Optional.of(page), Optional.of(limit));

        // then
        assertValidResult(result, 2, FmpNews.class, emptySet());
    }

    @Test
    void etfSectorWeightings() {
        // given
        var symbol = symbol("SPY");
        var endpoint = "etf/sector-weightings";
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.etf().sectorWeightings(symbol);

        // then
        assertValidResult(result, 11, FmpEtfSectorWeighting.class, emptySet());
    }

    @Test
    void fullQuotes() {
        // given
        var symbol = symbol("AAPL");
        var endpoint = "quote";
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.quote().full(symbol);

        // then
        assertValidResult(result, 1, FmpFullQuote.class);
    }

    @Test
    void partialQuotes() {
        // given
        var symbol = symbol("AAPL");
        var endpoint = "quote-short";
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.quote().partial(symbol);

        // then
        assertValidResult(result, 1, FmpPartialQuote.class);
    }

    @Test
    void priceChange() {
        // given
        var symbol = symbol("AAPL");
        var endpoint = "stock-price-change";
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.quote().priceChange(symbol);

        // then
        assertValidResult(result, 1, FmpStockPriceChange.class);
    }

    @Test
    void secFilingsSearchBySymbol() {
        // given
        var symbol = symbol("AAPL");
        var from = LocalDate.parse("2024-01-01");
        var to = LocalDate.parse("2025-01-01");
        var page = page(0);
        var limit = limit(2);
        var endpoint = "sec-filings-search/symbol";
        var file = format(
                "stable/%s/?symbol=%s&from=%s&to=%s&page=%d&limit=%s.json",
                endpoint, symbol, from, to, page.value(), limit);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.secFilingsSearch().bySymbol(symbol, from, to, Optional.of(page), Optional.of(limit));

        // then
        assertValidResult(result, limit.value(), FmpSecFilingsSearchBySymbol.class);
    }

    @Test
    void treasuryRates() {
        // given
        var from = LocalDate.parse("2024-12-30");
        var to = LocalDate.parse("2025-01-01");
        var endpoint = "treasury-rates";
        var file = format("stable/%s/?from=%s&to=%s.json", endpoint, from, to);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.economics().treasuryRates(from, to);

        // then
        assertValidResult(result, 2, FmpTreasuryRate.class);
    }

    @Test
    void earningCallTranscript() {
        // given
        var symbol = symbol("AAPL");
        var year = year(2020);
        var quarter = quarter(3);
        var endpoint = "earning-call-transcript";
        var file = format("stable/%s/?symbol=%s&year=%s&quarter=%s.json", endpoint, symbol, year, quarter);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.earnings().transcripts(symbol, year, quarter, empty());

        // then
        assertValidResult(result, 1, FmpEarningsCallTranscript.class);
    }

    @Test
    void earningCallTranscriptDates() {
        // given
        var symbol = symbol("AAPL");
        var endpoint = "earning-call-transcript-dates";
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.earnings().dates(symbol);

        // then
        assertValidResult(result, 81, FmpEarningsCallTranscriptDate.class);
    }

    @Test
    void earningCallTranscriptLatest() {
        // given
        var page = page(0);
        var limit = limit(2);
        var endpoint = "earning-call-transcript-latest";
        var file = format("stable/%s/?page=%s&limit=%s.json", endpoint, page, limit);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.earnings().latest(Optional.of(limit), Optional.of(page));

        // then
        assertValidResult(result, 2, FmpEarningsCallTranscriptLatest.class);
    }

    @Test
    void earningsTranscriptList() {
        // given
        var endpoint = "earnings-transcript-list";
        var file = format("stable/%s/excerpt.json", endpoint);

        // when
        mockHttpGetFromFile(file);
        var result = fmpClient.earnings().list();

        // then
        assertValidResult(result, 4, FmpEarningsCallTranscriptList.class);
    }

    private <T> void assertValidResult(T[] result, int expectedLength, Class<?> expectedType) {
        assertValidResult(result, expectedLength, expectedType, emptySet());
    }

    private <T> void assertValidResult(
            T[] result, int expectedLength, Class<?> expectedType, Set<String> ignoreFields) {
        assertNotNull(result, "result was null, likely a missing stub");
        assertEquals(expectedLength, result.length);
        range(0, expectedLength).forEach(i -> assertInstanceOf(expectedType, result[i]));
        range(0, expectedLength).forEach(i -> assertAllFieldsNonNull(result[i], ignoreFields));
    }
}

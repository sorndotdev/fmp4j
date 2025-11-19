package dev.sorn.fmp4j;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.jsonTestResource;
import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;
import static dev.sorn.fmp4j.types.FmpCik.cik;
import static dev.sorn.fmp4j.types.FmpCusip.cusip;
import static dev.sorn.fmp4j.types.FmpInterval.interval;
import static dev.sorn.fmp4j.types.FmpIsin.isin;
import static dev.sorn.fmp4j.types.FmpLimit.limit;
import static dev.sorn.fmp4j.types.FmpPage.page;
import static dev.sorn.fmp4j.types.FmpPeriod.period;
import static dev.sorn.fmp4j.types.FmpStructure.FLAT;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static dev.sorn.fmp4j.types.FmpYear.year;
import static java.lang.String.format;
import static java.lang.String.join;
import static java.lang.System.setProperty;
import static java.util.Collections.emptySet;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.cfg.FmpConfigImpl;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpBalanceSheetStatement;
import dev.sorn.fmp4j.models.FmpBalanceSheetStatementGrowth;
import dev.sorn.fmp4j.models.FmpCashFlowStatement;
import dev.sorn.fmp4j.models.FmpCashFlowStatementGrowth;
import dev.sorn.fmp4j.models.FmpCompany;
import dev.sorn.fmp4j.models.FmpDividend;
import dev.sorn.fmp4j.models.FmpDividendsCalendar;
import dev.sorn.fmp4j.models.FmpEarning;
import dev.sorn.fmp4j.models.FmpEarningsCalendar;
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
import dev.sorn.fmp4j.models.FmpLatestEarningsCallTranscript;
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
import dev.sorn.fmp4j.types.FmpApiKey;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class FmpClientTest {
    private static final String BASE_URL = "https://financialmodelingprep.com/stable";
    private static final FmpApiKey API_KEY = new FmpApiKey("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6");
    private final FmpConfig fmpConfig = mock(FmpConfig.class);
    private final FmpHttpClient fmpHttpClient = mock(FmpHttpClient.class);
    private FmpClient fmpClient;

    @BeforeEach
    void setUp() {
        when(fmpConfig.fmpBaseUrl()).thenReturn(BASE_URL);
        when(fmpConfig.fmpApiKey()).thenReturn(API_KEY);
        fmpClient = new FmpClient(fmpConfig, fmpHttpClient);
    }

    @Test
    void testConstructor_doesNotThrowAndCreatesInstance() {
        setProperty(FmpConfigImpl.FMP4J_API_KEY_ENV, "ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6");
        setProperty(FmpConfigImpl.FMP4J_BASE_URL_ENV, "https://financialmodelingprep.com/stable");

        assertDoesNotThrow(() -> new FmpClient());
        FmpClient client = new FmpClient();
        assertNotNull(client);
    }

    @Test
    void searchPressReleases() {
        // given
        var symbol = symbol("V");
        var typeRef = typeRef(FmpSearchPressRelease[].class);
        var endpoint = "news/press-releases";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbols", symbol));
        var file = format("stable/%s/?symbols=%s.json", endpoint, symbol);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.search().pressReleases(symbol);

        // then
        assertValidResult(result, 3, FmpSearchPressRelease.class);
    }

    @Test
    void searchByIsin() {
        // given
        var isin = isin("NL0012969182");
        var typeRef = typeRef(FmpSearchByIsin[].class);
        var endpoint = "search-isin";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("isin", isin));
        var file = format("stable/%s/?isin=%s.json", endpoint, isin);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.search().byIsin(isin);

        // then
        assertValidResult(result, 3, FmpSearchByIsin.class);
    }

    @Test
    void cashFlowStatementGrowthBulk() {
        var year = year("2025");
        var period = period("quarter");
        var typeRef = typeRef(FmpCashFlowStatementGrowth[].class);
        var endpoint = "v4/cash-flow-statement-growth-bulk";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("year", year, "period", period));
        var file = format("stable/cash-flow-statement-growth-bulk/?year=%s&period=%s.json", year, period);

        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.bulk().cashFlowStatementGrowth(year, period);

        assertValidResult(result, 2, FmpCashFlowStatementGrowth.class);
    }

    @Test
    void searchByName() {
        // given
        var query = "ADYEN";
        var typeRef = typeRef(FmpSearchByName[].class);
        var endpoint = "search-name";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("query", query));
        var file = format("stable/%s/?query=%s.json", endpoint, query);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.search().byName(query);

        // then
        assertValidResult(result, 5, FmpSearchByName.class);
    }

    @Test
    void searchByCusip() {
        // given
        var cusip = cusip("037833100");
        var typeRef = typeRef(FmpSearchByCusip[].class);
        var endpoint = "search-cusip";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("cusip", cusip));
        var file = format("stable/%s/?cusip=%s.json", endpoint, cusip);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.search().byCusip(cusip);

        // then
        assertValidResult(result, 3, FmpSearchByCusip.class);
    }

    @Test
    void searchBySymbol() {
        // given
        var query = symbol("ADYEN");
        var typeRef = typeRef(FmpSearchBySymbol[].class);
        var endpoint = "search-symbol";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("query", query));
        var file = format("stable/%s/?query=%s.json", endpoint, query);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.search().bySymbol(query);

        // then
        assertValidResult(result, 1, FmpSearchBySymbol.class);
    }

    @Test
    void searchByCik() {
        // given
        var cik = cik("0000320193");
        var typeRef = typeRef(FmpSearchByCik[].class);
        var endpoint = "search-cik";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("cik", cik));
        var file = format("stable/%s/?cik=%s.json", endpoint, cik);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.search().byCik(cik);

        // then
        assertValidResult(result, 1, FmpSearchByCik.class);
    }

    @Test
    void stockDirectory() {
        // given
        var typeRef = typeRef(FmpStock[].class);
        var endpoint = "stock-list";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of());
        var file = format("stable/%s/excerpt.json", endpoint);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.directory().stocks();

        // then
        assertValidResult(result, 2, FmpStock.class);
    }

    @Test
    void etfDirectory() {
        // given
        var typeRef = typeRef(FmpEtf[].class);
        var endpoint = "etf-list";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of());
        var file = format("stable/%s/excerpt.json", endpoint);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.directory().etfs();

        // then
        assertValidResult(result, 4, FmpEtf.class);
    }

    @Test
    void dividends_calendar() {
        // given
        var typeRef = typeRef(FmpDividendsCalendar[].class);
        var endpoint = "dividends-calendar";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of());
        var file = format("stable/%s/excerpt.json", endpoint);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.calendar().dividends();

        // then
        assertValidResult(result, 4, FmpDividendsCalendar.class, Set.of("declarationDate"));
    }

    @Test
    void dividends() {
        // given
        var typeRef = typeRef(FmpDividend[].class);
        var endpoint = "dividends";
        var symbol = symbol("AAPL");
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol));
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.calendar().dividends(symbol);

        // then
        assertValidResult(result, 4, FmpDividend.class, Set.of("declarationDate"));
    }

    @Test
    void earnings_calendar() {
        // given
        var typeRef = typeRef(FmpEarningsCalendar[].class);
        var endpoint = "earnings-calendar";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of());
        var file = format("stable/%s/excerpt.json", endpoint);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var typeRef = typeRef(FmpEarning[].class);
        var endpoint = "earnings";
        var symbol = symbol("AAPL");
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol));
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var typeRef = typeRef(FmpIposCalendar[].class);
        var endpoint = "ipos-calendar";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("from", from, "to", to));
        var file = format("stable/%s/excerpt.json", endpoint);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.calendar().ipos(from, to);

        // then
        assertValidResult(result, 2, FmpIposCalendar.class, Set.of("shares", "priceRange", "marketCap"));
    }

    @Test
    void iposDisclosure() {
        // given
        var from = Optional.of(LocalDate.parse("2024-02-28"));
        var to = Optional.of(LocalDate.parse("2025-02-28"));
        var typeRef = typeRef(FmpIposDisclosure[].class);
        var endpoint = "ipos-disclosure";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("from", from, "to", to));
        var file = format("stable/%s/excerpt.json", endpoint);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.calendar().disclosures(from, to);

        // then
        assertValidResult(result, 2, FmpIposDisclosure.class, Set.of());
    }

    @Test
    void iposProspectus() {
        // given
        var from = Optional.of(LocalDate.parse("2024-02-28"));
        var to = Optional.of(LocalDate.parse("2025-02-28"));
        var typeRef = typeRef(FmpIposProspectus[].class);
        var endpoint = "ipos-prospectus";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("from", from, "to", to));
        var file = format("stable/%s/excerpt.json", endpoint);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.calendar().prospectus(from, to);

        // then
        assertValidResult(result, 2, FmpIposProspectus.class, Set.of());
    }

    @Test
    void historicalPriceEodLight() {
        // given
        var typeRef = typeRef(FmpHistoricalPriceEodLight[].class);
        var endpoint = "historical-price-eod/light";
        var symbol = symbol("AAPL");
        var from = LocalDate.parse("2024-02-22");
        var to = LocalDate.parse("2024-02-28");
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "from", from, "to", to));
        var file = format("stable/%s/?symbol=%s&from=%s&to=%s.json", endpoint, symbol, from, to);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.chart().historicalPriceEodLight(symbol, Optional.of(from), Optional.of(to));

        // then
        assertValidResult(result, 5, FmpHistoricalPriceEodLight.class, emptySet());
    }

    @Test
    void historicalPriceEodFull() {
        // given
        var typeRef = typeRef(FmpHistoricalPriceEodFull[].class);
        var endpoint = "historical-price-eod/full";
        var symbol = symbol("AAPL");
        var from = LocalDate.parse("2024-02-22");
        var to = LocalDate.parse("2024-02-28");
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "from", from, "to", to));
        var file = format("stable/%s/?symbol=%s&from=%s&to=%s.json", endpoint, symbol, from, to);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.chart().historicalPriceEodFull(symbol, Optional.of(from), Optional.of(to));

        // then
        assertValidResult(result, 5, FmpHistoricalPriceEodFull.class, emptySet());
    }

    @ParameterizedTest
    @ValueSource(
            strings = {
                "1min", "5min", "15min", "30min", "1hour", "4hour",
            })
    void historicalChart(String intervalStr) {
        // given
        var typeRef = typeRef(FmpHistoricalChart[].class);
        var endpoint = "historical-chart/" + intervalStr;
        var symbol = symbol("AAPL");
        var interval = interval(intervalStr);
        var from = LocalDate.parse("2024-01-01");
        var to = LocalDate.parse("2024-01-02");
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "from", from, "to", to));
        var file = format("stable/%s/?symbol=%s&from=%s&to=%s.json", endpoint, symbol, from, to);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.chart().historical(symbol, interval, Optional.of(from), Optional.of(to));

        // then
        assertValidResult(result, 2, FmpHistoricalChart.class, emptySet());
    }

    @Test
    void company() {
        // given
        var symbol = symbol("AAPL");
        var typeRef = typeRef(FmpCompany[].class);
        var endpoint = "profile";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol));
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.company().bySymbol(symbol);

        // then
        assertValidResult(result, 1, FmpCompany.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"annual", "quarter"})
    void incomeStatements(String p) {
        // given
        var period = period(p);
        var symbol = symbol("AAPL");
        var limit = limit(3);
        var typeRef = typeRef(FmpIncomeStatement[].class);
        var endpoint = "income-statement";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "period", period, "limit", limit));
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.statement().income(symbol, Optional.of(period), Optional.of(limit));

        // then
        assertValidResult(result, limit.value(), FmpIncomeStatement.class);
    }

    @Test
    void incomeStatementTtm() {
        // given
        var symbol = symbol("AAPL");
        var limit = limit(2);
        var typeRef = typeRef(FmpIncomeStatement[].class);
        var endpoint = "income-statement-ttm";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "limit", limit));
        var file = format("stable/%s/?symbol=%s&limit=%s.json", endpoint, symbol, limit);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var typeRef = typeRef(FmpIncomeStatementGrowth[].class);
        var endpoint = "income-statement-growth";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "period", period, "limit", limit));
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var typeRef = typeRef(FmpFinancialStatementAsReported[].class);
        var endpoint = "income-statement-as-reported";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "period", period, "limit", limit));
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var typeRef = typeRef(FmpBalanceSheetStatement[].class);
        var endpoint = "balance-sheet-statement";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "period", period, "limit", limit));
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.statement().balanceSheet(symbol, Optional.of(period), Optional.of(limit));

        // then
        assertValidResult(result, limit.value(), FmpBalanceSheetStatement.class);
    }

    @Test
    void balanceSheetStatementTtm() {
        // given
        var symbol = symbol("AAPL");
        var limit = limit(2);
        var typeRef = typeRef(FmpBalanceSheetStatement[].class);
        var endpoint = "balance-sheet-statement-ttm";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "limit", limit));
        var file = format("stable/%s/?symbol=%s&limit=%s.json", endpoint, symbol, limit);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var typeRef = typeRef(FmpBalanceSheetStatementGrowth[].class);
        var endpoint = "balance-sheet-statement-growth";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "period", period, "limit", limit));
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var typeRef = typeRef(FmpFinancialStatementAsReported[].class);
        var endpoint = "balance-sheet-statement-as-reported";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "period", period, "limit", limit));
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var typeRef = typeRef(FmpCashFlowStatement[].class);
        var endpoint = "cash-flow-statement";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "period", period, "limit", limit));
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.statement().cashFlow(symbol, Optional.of(period), Optional.of(limit));

        // then
        assertValidResult(result, limit.value(), FmpCashFlowStatement.class);
    }

    @Test
    void cashFlowStatementTtm() {
        // given
        var symbol = symbol("AAPL");
        var limit = limit(2);
        var typeRef = typeRef(FmpCashFlowStatement[].class);
        var endpoint = "cash-flow-statement-ttm";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "limit", limit));
        var file = format("stable/%s/?symbol=%s&limit=%s.json", endpoint, symbol, limit);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var typeRef = typeRef(FmpCashFlowStatementGrowth[].class);
        var endpoint = "cash-flow-statement-growth";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "period", period, "limit", limit));
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var typeRef = typeRef(FmpFinancialStatementAsReported[].class);
        var endpoint = "cash-flow-statement-as-reported";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "period", period, "limit", limit));
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var typeRef = typeRef(FmpFinancialGrowth[].class);
        var endpoint = "financial-growth";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "period", period, "limit", limit));
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var typeRef = typeRef(FmpRatio[].class);
        var endpoint = "ratios";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "period", period, "limit", limit));
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.statement().ratios(symbol, Optional.of(period), Optional.of(limit));

        // then
        assertValidResult(result, limit.value(), FmpRatio.class);
    }

    @Test
    void ratiosTtm() {
        // given
        var symbol = symbol("AAPL");
        var typeRef = typeRef(FmpRatioTtm[].class);
        var endpoint = "ratios-ttm";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol));
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var typeRef = typeRef(FmpKeyMetric[].class);
        var endpoint = "key-metrics";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "period", period, "limit", limit));
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.statement().keyMetrics(symbol, Optional.of(period), Optional.of(limit));

        // then
        assertValidResult(result, limit.value(), FmpKeyMetric.class);
    }

    @Test
    void keyMetricsTtm() {
        // given
        var symbol = symbol("AAPL");
        var typeRef = typeRef(FmpKeyMetricTtm[].class);
        var endpoint = "key-metrics-ttm";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol));
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var typeRef = typeRef(FmpEnterpriseValue[].class);
        var endpoint = "enterprise-values";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "period", period, "limit", limit));
        var file = format("stable/%s/?symbol=%s&period=%s&limit=%s.json", endpoint, symbol, period, limit);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var typeRef = typeRef(FmpRevenueProductSegmentation[].class);
        var endpoint = "revenue-product-segmentation";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "period", period, "structure", structure));
        var file = format("stable/%s/?symbol=%s&period=%s&structure=%s.json", endpoint, symbol, period, structure);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var typeRef = typeRef(FmpRevenueGeographicSegmentation[].class);
        var endpoint = "revenue-geographic-segmentation";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "period", period, "structure", structure));
        var file = format("stable/%s/?symbol=%s&period=%s&structure=%s.json", endpoint, symbol, period, structure);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var typeRef = typeRef(FmpEtfAssetExposure[].class);
        var endpoint = "etf/asset-exposure";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol));
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.etf().assetExposure(symbol);

        // then
        assertValidResult(result, 28, FmpEtfAssetExposure.class, emptySet());
    }

    @Test
    void etfCountryWeightings() {
        // given
        var symbol = symbol("SPY");
        var typeRef = typeRef(FmpEtfCountryWeighting[].class);
        var endpoint = "etf/country-weightings";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol));
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var typeRef = typeRef(FmpEtfHolding[].class);
        var endpoint = "etf/holdings";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol));
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.etf().holdings(symbol);

        // then
        assertValidResult(result, holdings, FmpEtfHolding.class, Set.of("asset", "isin"));
    }

    @Test
    void etfInfo() {
        // given
        var symbol = symbol("SPY");
        var typeRef = typeRef(FmpEtfInfo[].class);
        var endpoint = "etf/info";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol));
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.etf().info(symbol);

        // then
        assertValidResult(result, 1, FmpEtfInfo.class, emptySet());
    }

    @Test
    void cryptoNews() {
        // given
        var symbols = Set.of(symbol("BTCUSD"));
        var typeRef = typeRef(FmpNews[].class);
        var endpoint = "news/crypto";
        var page = page(0);
        var limit = limit(100);
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbols", symbols, "page", page, "limit", limit));
        var file = format(
                "stable/%s/?symbols=%s.json",
                endpoint, join(",", symbols.stream().map(FmpSymbol::value).toList()));

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var typeRef = typeRef(FmpNews[].class);
        var endpoint = "news/crypto";
        var page = page(0);
        var limit = limit(100);
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbols", symbols, "page", page, "limit", limit));
        var file = format(
                "stable/%s/?symbols=%s.json",
                endpoint, join(",", symbols.stream().map(FmpSymbol::value).toList()));

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.news().crypto(symbols);

        // then
        assertValidResult(result, 4, FmpNews.class, emptySet());
    }

    @Test
    void forexNews() {
        // given
        var symbols = Set.of(symbol("EURUSD"));
        var typeRef = typeRef(FmpNews[].class);
        var endpoint = "news/forex";
        var page = page(0);
        var limit = limit(100);
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbols", symbols, "page", page, "limit", limit));
        var file = format(
                "stable/%s/?symbols=%s.json",
                endpoint, join(",", symbols.stream().map(FmpSymbol::value).toList()));

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.news().forex(symbols);

        // then
        assertValidResult(result, 2, FmpNews.class, emptySet());
    }

    @Test
    void stockNews() {
        // given
        var symbols = Set.of(symbol("AAPL"));
        var typeRef = typeRef(FmpNews[].class);
        var endpoint = "news/stock";
        var page = page(0);
        var limit = limit(100);
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbols", symbols, "page", page, "limit", limit));
        var file = format(
                "stable/%s/?symbols=%s.json",
                endpoint, join(",", symbols.stream().map(FmpSymbol::value).toList()));

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();

        var params = buildParams(Map.of(
                "symbols", symbols,
                "from", from.get(),
                "to", to.get(),
                "page", page,
                "limit", limit));

        var file = format(
                "stable/%s/?symbols=%s.json",
                endpoint, join(",", symbols.stream().map(FmpSymbol::value).toList()));

        // when
        mockHttpGet(uri, headers, params, file, typeRef(FmpNews[].class));
        var result = fmpClient.news().crypto(symbols, from, to, Optional.of(page), Optional.of(limit));

        // then
        assertValidResult(result, 2, FmpNews.class, emptySet());
    }

    @Test
    void etfSectorWeightings() {
        // given
        var symbol = symbol("SPY");
        var typeRef = typeRef(FmpEtfSectorWeighting[].class);
        var endpoint = "etf/sector-weightings";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol));
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.etf().sectorWeightings(symbol);

        // then
        assertValidResult(result, 11, FmpEtfSectorWeighting.class, emptySet());
    }

    @Test
    void fullQuotes() {
        // given
        var symbol = symbol("AAPL");
        var typeRef = typeRef(FmpFullQuote[].class);
        var endpoint = "quote";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol));
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.quote().full(symbol);

        // then
        assertValidResult(result, 1, FmpFullQuote.class);
    }

    @Test
    void partialQuotes() {
        // given
        var symbol = symbol("AAPL");
        var typeRef = typeRef(FmpPartialQuote[].class);
        var endpoint = "quote-short";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol));
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.quote().partial(symbol);

        // then
        assertValidResult(result, 1, FmpPartialQuote.class);
    }

    @Test
    void priceChange() {
        // given
        var symbol = symbol("AAPL");
        var typeRef = typeRef(FmpStockPriceChange[].class);
        var endpoint = "stock-price-change";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol));
        var file = format("stable/%s/?symbol=%s.json", endpoint, symbol);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
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
        var typeRef = typeRef(FmpSecFilingsSearchBySymbol[].class);
        var endpoint = "sec-filings-search/symbol";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("symbol", symbol, "from", from, "to", to, "page", page, "limit", limit));
        var file = format(
                "stable/%s/?symbol=%s&from=%s&to=%s&page=%d&limit=%s.json",
                endpoint, symbol, from, to, page.value(), limit);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.secFilingsSearch().bySymbol(symbol, from, to, Optional.of(page), Optional.of(limit));

        // then
        assertValidResult(result, limit.value(), FmpSecFilingsSearchBySymbol.class);
    }

    @Test
    void treasuryRates() {
        // given
        var from = LocalDate.parse("2024-12-30");
        var to = LocalDate.parse("2025-01-01");

        var typeRef = typeRef(FmpTreasuryRate[].class);
        var endpoint = "treasury-rates";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("from", from, "to", to));
        var file = format("stable/%s/?from=%s&to=%s.json", endpoint, from, to);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.economics().treasuryRates(from, to);

        // then
        assertValidResult(result, 2, FmpTreasuryRate.class);
    }

    @Test
    void latestEarningsCallTranscript() {
        // given
        var page = page(0);
        var limit = limit(2);

        var typeRef = typeRef(FmpLatestEarningsCallTranscript[].class);
        var endpoint = "earning-call-transcript-latest";
        var uri = buildUri(endpoint);
        var headers = defaultHeaders();
        var params = buildParams(Map.of("page", page, "limit", limit));
        var file = format("stable/%s/?page=%s&limit=%s.json", endpoint, page, limit);

        // when
        mockHttpGet(uri, headers, params, file, typeRef);
        var result = fmpClient.latestEarningsCallTranscript().transcripts(limit, page);

        // then
        assertValidResult(result, 2, FmpLatestEarningsCallTranscript.class);
    }

    private URI buildUri(String endpoint) {
        return URI.create(BASE_URL + "/" + endpoint);
    }

    private Map<String, String> defaultHeaders() {
        return Map.of("Content-Type", "application/json");
    }

    private Map<String, Object> buildParams(Map<String, Object> customParams) {
        return new HashMap<>() {
            {
                putAll(customParams);
                put("apikey", API_KEY);
            }
        };
    }

    private synchronized <T> void mockHttpGet(
            URI uri, Map<String, String> headers, Map<String, Object> params, String file, TypeReference<T> typeRef) {
        when(fmpHttpClient.get(any(), eq(uri), eq(headers), eq(params))).thenReturn(jsonTestResource(typeRef, file));
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

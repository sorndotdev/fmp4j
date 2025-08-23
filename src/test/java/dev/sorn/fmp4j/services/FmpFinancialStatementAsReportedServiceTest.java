package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.HttpClientStub.httpClientStub;
import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.jsonTestResource;
import static dev.sorn.fmp4j.cfg.FmpConfigImpl.FMP_CONFIG;
import static dev.sorn.fmp4j.json.FmpJsonDeserializerImpl.FMP_JSON_DESERIALIZER;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.FinancialStatementAsReportedTestData;
import dev.sorn.fmp4j.HttpClientStub;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.http.FmpHttpClientImpl;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class FmpFinancialStatementAsReportedServiceTest implements FinancialStatementAsReportedTestData {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);

    @ParameterizedTest
    @ValueSource(strings = {"income", "balance-sheet", "cash-flow"})
    void relative_url(String type) {
        // given
        var service = new FmpFinancialStatementAsReportedService(FMP_CONFIG, http, type);

        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/" + type + "-statement-as-reported", relativeUrl);
    }

    @ParameterizedTest
    @ValueSource(strings = {"income", "balance-sheet", "cash-flow"})
    void required_params(String type) {
        // given
        var service = new FmpFinancialStatementAsReportedService(FMP_CONFIG, http, type);

        // when
        var params = service.requiredParams();

        // then
        assertEquals(Set.of("symbol"), params);
    }

    @ParameterizedTest
    @ValueSource(strings = {"income", "balance-sheet", "cash-flow"})
    void optional_params(String type) {
        // given
        var service = new FmpFinancialStatementAsReportedService(FMP_CONFIG, http, type);

        // when
        var params = service.optionalParams();

        // then
        assertEquals(Set.of("period", "limit"), params);
    }

    @ParameterizedTest
    @MethodSource("reportCompanyProvider")
    void successful_download(String type, String symbol, String period) {
        // given
        var service = new FmpFinancialStatementAsReportedService(FMP_CONFIG, http, type);
        var limit = 2;
        service.param("symbol", symbol);
        httpStub.configureResponse()
                .body(jsonTestResource(
                        "stable/%s-statement-as-reported/?symbol=%s&period=%s&limit=%d.json",
                        type, symbol, period, limit))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(limit, result.length);
        range(0, limit).forEach(i -> assertAllFieldsNonNull(result[i], Set.of("reportedCurrency")));
    }

    static Stream<Arguments> reportCompanyProvider() {
        var reports = List.of("income", "balance-sheet", "cash-flow");
        var symbols = List.of("KO", "O");
        var periods = List.of("annual", "quarter");
        return reports.stream().flatMap(report -> symbols.stream()
                .flatMap(company -> periods.stream().map(period -> Arguments.of(report, company, period))));
    }
}

package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.FinancialStatementAsReportedTestData;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class FmpFinancialStatementAsReportedServiceTest extends HttpTest implements FinancialStatementAsReportedTestData {
    @ParameterizedTest
    @ValueSource(strings = {"income", "balance-sheet", "cash-flow"})
    void relative_url(String type) {
        // given
        var service = new FmpFinancialStatementAsReportedService(config, client, type);

        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/" + type + "-statement-as-reported", relativeUrl);
    }

    @ParameterizedTest
    @ValueSource(strings = {"income", "balance-sheet", "cash-flow"})
    void required_params(String type) {
        // given
        var service = new FmpFinancialStatementAsReportedService(config, client, type);

        // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of("symbol", FmpSymbol.class), params);
    }

    @ParameterizedTest
    @ValueSource(strings = {"income", "balance-sheet", "cash-flow"})
    void optional_params(String type) {
        // given
        var service = new FmpFinancialStatementAsReportedService(config, client, type);

        // when
        var params = service.optionalParams();

        // then
        assertEquals(Map.of("period", FmpPeriod.class, "limit", FmpLimit.class), params);
    }

    @ParameterizedTest
    @MethodSource("reportCompanyProvider")
    void successful_download(String type, FmpSymbol symbol, String period) {
        // given
        var service = new FmpFinancialStatementAsReportedService(config, client, type);
        var limit = 2;
        service.param("symbol", symbol);
        httpStub.configureResponse()
                .body(testResource(
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

package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.types.FmpLimit.limit;
import static dev.sorn.fmp4j.types.FmpPeriod.period;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.IncomeStatementTestData;
import dev.sorn.fmp4j.models.FmpIncomeStatement;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FmpIncomeStatementServiceTest extends HttpTest implements IncomeStatementTestData {
    private FmpService<FmpIncomeStatement[]> service;

    @BeforeEach
    void setup() {
        service = new FmpIncomeStatementService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/income-statement", relativeUrl);
    }

    @Test
    void required_params() {
        // given // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of("symbol", FmpSymbol.class), params);
    }

    @Test
    void optional_params() {
        // given // when
        var params = service.optionalParams();

        // then
        assertEquals(Map.of("period", FmpPeriod.class, "limit", FmpLimit.class), params);
    }

    @Test
    void successful_download() {
        // given
        var symbol = symbol("AAPL");
        service.param("symbol", symbol);
        httpStub.configureResponse()
                .body(testResource("stable/income-statement/?symbol=%s.json", symbol))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(5, result.length);
        assertEquals(anAnnualIncomeStatement(), result[0]);
        range(0, 5).forEach(i -> assertAllFieldsNonNull(result[i]));
    }

    @ParameterizedTest
    @CsvSource({"AAPL,annual,3", "AAPL,quarter,3", "GLIBK,quarter,1"})
    void successful_download_with_optional_period_and_limit(String symbol, String period, int limit) {
        // given
        service.param("symbol", symbol(symbol));
        service.param("period", period(period));
        service.param("limit", limit(limit));
        httpStub.configureResponse()
                .body(testResource("stable/income-statement/?symbol=%s&period=%s&limit=%d.json", symbol, period, limit))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(limit, result.length);
        range(0, limit).forEach(i -> assertAllFieldsNonNull(result[i], Set.of("cik")));
    }
}

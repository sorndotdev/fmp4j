package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_PERIOD;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.BalanceSheetStatementTestData;
import dev.sorn.fmp4j.models.FmpBalanceSheetStatement;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FmpBalanceSheetStatementServiceTest extends HttpTest implements BalanceSheetStatementTestData {
    private FmpService<FmpBalanceSheetStatement[]> service;

    @BeforeEach
    void setup() {
        service = new FmpBalanceSheetStatementService(config, client);
    }

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/balance-sheet-statement", relativeUrl);
    }

    @Test
    void required_params() {
        // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of(PARAM_SYMBOL, FmpSymbol.class), params);
    }

    @Test
    void optional_params() {
        // when
        var params = service.optionalParams();

        // then
        assertEquals(Map.of(PARAM_PERIOD, FmpPeriod.class, PARAM_LIMIT, FmpLimit.class), params);
    }

    @Test
    void successful_download() {
        // given
        var symbol = symbol("AAPL");
        service.param(PARAM_SYMBOL, symbol);
        httpStub.configureResponse()
                .body(testResource("stable/balance-sheet-statement/?symbol=%s.json", symbol))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(5, result.length);
        assertEquals(anAnnualBalanceSheetStatement(), result[0]);
        range(0, 5).forEach(i -> assertAllFieldsNonNull(result[i]));
    }

    @ParameterizedTest
    @ValueSource(strings = {"annual", "quarter"})
    void successful_download_with_optional_period_and_limit(String period) {
        // given
        var symbol = symbol("AAPL");
        var limit = 3;
        service.param(PARAM_SYMBOL, symbol);
        httpStub.configureResponse()
                .body(testResource(
                        "stable/balance-sheet-statement/?symbol=%s&period=%s&limit=%d.json", symbol, period, limit))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(limit, result.length);
        range(0, limit).forEach(i -> assertAllFieldsNonNull(result[i]));
    }
}

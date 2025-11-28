package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.IncomeStatementGrowthTestData;
import dev.sorn.fmp4j.models.FmpIncomeStatementGrowth;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FmpIncomeStatementGrowthServiceTest extends HttpTest implements IncomeStatementGrowthTestData {
    private FmpService<FmpIncomeStatementGrowth[]> service;

    @BeforeEach
    void setup() {
        service = new FmpIncomeStatementGrowthService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/income-statement-growth", relativeUrl);
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

    @ParameterizedTest
    @ValueSource(strings = {"annual", "quarter"})
    void successful_download_with_optional_period_and_limit(String period) {
        // given
        var symbol = symbol("AAPL");
        var limit = 2;
        service.param("symbol", symbol);
        httpStub.configureResponse()
                .body(testResource(
                        "stable/income-statement-growth/?symbol=%s&period=%s&limit=%d.json", symbol, period, limit))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(limit, result.length);
        range(0, limit).forEach(i -> assertAllFieldsNonNull(result[i]));
    }
}

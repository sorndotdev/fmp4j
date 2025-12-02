package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_PERIOD;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.FinancialGrowthTestData;
import dev.sorn.fmp4j.models.FmpFinancialGrowth;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FmpFinancialGrowthServiceTest extends HttpTest implements FinancialGrowthTestData {
    private FmpService<FmpFinancialGrowth[]> service;

    @BeforeEach
    void setup() {
        service = new FmpFinancialGrowthService(config, client);
    }

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/financial-growth", relativeUrl);
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

    @ParameterizedTest
    @ValueSource(strings = {"annual", "quarter"})
    void successful_download(String period) {
        // given
        var symbol = symbol("AAPL");
        var limit = 2;
        service.param(PARAM_SYMBOL, symbol);
        httpStub.configureResponse()
                .body(testResource("stable/financial-growth/?symbol=%s&period=%s&limit=%d.json", symbol, period, limit))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(limit, result.length);
        range(0, limit)
                .forEach(i -> assertAllFieldsNonNull(
                        result[i],
                        Set.of(
                                "ebitdaGrowth",
                                "growthCapitalExpenditure",
                                "tenYBottomLineNetIncomeGrowthPerShare",
                                "fiveYBottomLineNetIncomeGrowthPerShare",
                                "threeYBottomLineNetIncomeGrowthPerShare")));
    }
}

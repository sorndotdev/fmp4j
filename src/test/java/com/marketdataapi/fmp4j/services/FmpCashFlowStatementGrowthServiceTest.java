package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.TestUtils.assertAllFieldsNonNull;
import static com.marketdataapi.fmp4j.TestUtils.testResource;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_PERIOD;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marketdataapi.fmp4j.CashFlowStatementGrowthTestData;
import com.marketdataapi.fmp4j.models.FmpCashFlowStatementGrowth;
import com.marketdataapi.fmp4j.types.FmpLimit;
import com.marketdataapi.fmp4j.types.FmpPeriod;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FmpCashFlowStatementGrowthServiceTest extends HttpTest implements CashFlowStatementGrowthTestData {
    private FmpService<FmpCashFlowStatementGrowth> service;

    @BeforeEach
    void setup() {
        service = new FmpCashFlowStatementGrowthService(config, client);
    }

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/cash-flow-statement-growth", relativeUrl);
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
    void successful_download_with_optional_period_and_limit(String period) {
        // given
        var symbol = symbol("AAPL");
        var limit = 2;
        service.param(PARAM_SYMBOL, symbol);
        httpStub.configureResponse()
                .body(testResource(
                        "stable/cash-flow-statement-growth/?symbol=%s&period=%s&limit=%d.json", symbol, period, limit))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(limit, result.size());
        range(0, limit).forEach(i -> assertAllFieldsNonNull(result.get(i)));
    }
}

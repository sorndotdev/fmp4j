package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.TestUtils.assertAllFieldsNonNull;
import static com.marketdataapi.fmp4j.TestUtils.testResource;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marketdataapi.fmp4j.CashFlowStatementTestData;
import com.marketdataapi.fmp4j.models.FmpCashFlowStatement;
import com.marketdataapi.fmp4j.types.FmpLimit;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpCashFlowStatementTtmServiceTest extends HttpTest implements CashFlowStatementTestData {
    private FmpService<FmpCashFlowStatement> service;

    @BeforeEach
    void setup() {
        service = new FmpCashFlowStatementTtmService(config, client);
    }

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/cash-flow-statement-ttm", relativeUrl);
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
        assertEquals(Map.of(PARAM_LIMIT, FmpLimit.class), params);
    }

    @Test
    void successful_download() {
        // given
        var symbol = symbol("AAPL");
        var limit = 2;
        service.param(PARAM_SYMBOL, symbol);
        httpStub.configureResponse()
                .body(testResource("stable/cash-flow-statement-ttm/?symbol=%s&limit=%d.json", symbol, limit))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(limit, result.size());
        range(0, limit).forEach(i -> assertAllFieldsNonNull(result.get(i)));
    }
}

package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.TestUtils.assertAllFieldsNonNull;
import static com.marketdataapi.fmp4j.TestUtils.testResource;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import com.marketdataapi.fmp4j.models.FmpEtfHolding;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpEtfHoldingServiceTest extends HttpTest {
    private FmpService<FmpEtfHolding> service;

    @BeforeEach
    void setup() {
        service = new FmpEtfHoldingService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/etf/holdings", relativeUrl);
    }

    @Test
    void required_params() {
        // given // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of(PARAM_SYMBOL, FmpSymbol.class), params);
    }

    @Test
    void optional_params() {
        // given // when
        var params = service.optionalParams();

        // then
        assertEquals(Map.of(), params);
    }

    @Test
    void successful_download() {
        // given
        var symbol = symbol("SCHD");
        service.param(PARAM_SYMBOL, symbol);
        httpStub.configureResponse()
                .body(testResource("stable/etf/holdings/?symbol=%s.json", symbol))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(103, result.size());
        range(0, 103).forEach(i -> assertInstanceOf(FmpEtfHolding.class, result.get(i)));
        range(0, 103).forEach(i -> assertAllFieldsNonNull(result.get(i), Set.of("isin")));
    }
}

package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.models.FmpSplit;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpSplitServiceTest extends HttpTest {
    private FmpService<FmpSplit> service;

    @BeforeEach
    void setup() {
        service = new FmpSplitService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/splits", relativeUrl);
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
        var symbol = symbol("AAPL");
        service.param(PARAM_SYMBOL, symbol);
        String jsonResponse =
                "[{\"symbol\":\"AAPL\",\"date\":\"2020-08-31\",\"numerator\":4,\"denominator\":1,\"label\":\"AAPL split: 4 for 1\"}]";
        httpStub.configureResponse().body(jsonResponse).statusCode(200).apply();

        // when
        var result = service.download();

        // then
        assertEquals(1, result.size());
        assertInstanceOf(FmpSplit.class, result.get(0));
        assertAllFieldsNonNull(result.get(0), Set.of());
        assertEquals(symbol("AAPL"), result.get(0).symbol());
        assertEquals(4, result.get(0).numerator());
        assertEquals(1, result.get(0).denominator());
    }
}

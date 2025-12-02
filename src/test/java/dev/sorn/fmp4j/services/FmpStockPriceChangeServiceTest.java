package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.StockPriceChangeTestData;
import dev.sorn.fmp4j.models.FmpStockPriceChange;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpStockPriceChangeServiceTest extends HttpTest implements StockPriceChangeTestData {
    private FmpService<FmpStockPriceChange[]> service;

    @BeforeEach
    void setup() {
        service = new FmpStockPriceChangeService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/stock-price-change", relativeUrl);
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
        httpStub.configureResponse()
                .body(testResource("stable/stock-price-change/?symbol=AAPL.json"))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(1, result.length);
        assertEquals(aStockPriceChange(), result[0]);
    }
}

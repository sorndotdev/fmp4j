package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.sorn.fmp4j.QuoteTestData;
import dev.sorn.fmp4j.models.FmpFullQuote;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpFullQuoteServiceTest extends HttpTest implements QuoteTestData {
    private FmpService<FmpFullQuote[]> service;

    @BeforeEach
    void setup() {
        service = new FmpQuoteService(config, client);
    }

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/quote", relativeUrl);
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
        assertEquals(Map.of(), params);
    }

    @Test
    void successful_download() {
        // given
        var symbol = symbol("AAPL");
        service.param(PARAM_SYMBOL, symbol);
        httpStub.configureResponse()
                .body(testResource("stable/quote/?symbol=AAPL.json"))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        var expected = new FmpFullQuote[] {aFullQuote()};
        assertArrayEquals(expected, result);
    }

    @Test
    void missing_symbol_throws() {
        // given // when
        Consumer<FmpService<FmpFullQuote[]>> f = FmpService::download;

        // then
        var e = assertThrows(FmpServiceException.class, () -> f.accept(service));
        assertEquals(format("[symbol] are required query params for endpoint [%s]", service.url()), e.getMessage());
    }

    @Test
    void unrecognized_param_throws() {
        var key = "unknown";
        var value = "value";

        // given // when
        BiConsumer<String, String> f = service::param;

        // then
        var e = assertThrows(FmpServiceException.class, () -> f.accept(key, value));
        assertEquals(
                format("'unknown' is not a recognized query param for endpoint [%s]", service.url()), e.getMessage());
    }
}

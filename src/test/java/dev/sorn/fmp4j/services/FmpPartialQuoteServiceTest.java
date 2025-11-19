package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.HttpClientStub.httpClientStub;
import static dev.sorn.fmp4j.TestUtils.jsonTestResource;
import static dev.sorn.fmp4j.json.FmpJsonDeserializer.FMP_JSON_DESERIALIZER;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.sorn.fmp4j.HttpClientStub;
import dev.sorn.fmp4j.QuoteTestData;
import dev.sorn.fmp4j.cfg.FmpConfigImpl;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.http.FmpHttpClientImpl;
import dev.sorn.fmp4j.models.FmpPartialQuote;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.junit.jupiter.api.Test;

class FmpPartialQuoteServiceTest implements QuoteTestData {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);
    private final FmpService<FmpPartialQuote[]> service = new FmpShortQuoteService(new FmpConfigImpl(), http);

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/quote-short", relativeUrl);
    }

    @Test
    void required_params() {
        // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of("symbol", FmpSymbol.class), params);
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
        service.param("symbol", symbol);
        httpStub.configureResponse()
                .body(jsonTestResource("stable/quote-short/?symbol=AAPL.json"))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        var expected = new FmpPartialQuote[] {aPartialQuote()};
        assertArrayEquals(expected, result);
    }

    @Test
    void missing_symbol_throws() {
        // given // when
        Consumer<FmpService<FmpPartialQuote[]>> f = FmpService::download;

        // then
        var e = assertThrows(FmpServiceException.class, () -> f.accept(service));
        assertEquals(format("'symbol' is a required query param for endpoint [%s]", service.url()), e.getMessage());
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

package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.HttpClientStub.httpClientStub;
import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.jsonTestResource;
import static dev.sorn.fmp4j.cfg.FmpConfigImpl.FMP_CONFIG;
import static dev.sorn.fmp4j.json.FmpJsonDeserializerImpl.FMP_JSON_DESERIALIZER;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.HttpClientStub;
import dev.sorn.fmp4j.NewsTestData;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.http.FmpHttpClientImpl;
import java.util.Set;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class FmpNewsServiceTest implements NewsTestData {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);

    @ParameterizedTest
    @ValueSource(strings = {"crypto", "forex", "stock"})
    void relative_url(String type) {
        // given
        var service = new FmpNewsService(FMP_CONFIG, http, type);

        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/news/" + type, relativeUrl);
    }

    @ParameterizedTest
    @ValueSource(strings = {"crypto", "forex", "stock"})
    void required_params(String path) {
        // given
        var service = new FmpNewsService(FMP_CONFIG, http, path);

        // when
        var params = service.requiredParams();

        // then
        assertEquals(Set.of("symbols"), params);
    }

    @ParameterizedTest
    @ValueSource(strings = {"crypto", "forex", "stock"})
    void optional_params(String type) {
        // given
        var service = new FmpNewsService(FMP_CONFIG, http, type);

        // when
        var params = service.optionalParams();

        // then
        assertEquals(Set.of("from", "to", "page", "limit"), params);
    }

    @ParameterizedTest
    @CsvSource({
        "crypto,BTCUSD",
        "forex,EURUSD",
        "stock,AAPL",
    })
    void successful_download(String type, String symbol) {
        // given
        var service = new FmpNewsService(FMP_CONFIG, http, type);
        service.param("symbols", symbol);
        httpStub.configureResponse()
                .body(jsonTestResource("stable/news/%s/?symbols=%s.json", type, symbol))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(2, result.length);
        range(0, 2).forEach(i -> assertAllFieldsNonNull(result[i]));
    }
}

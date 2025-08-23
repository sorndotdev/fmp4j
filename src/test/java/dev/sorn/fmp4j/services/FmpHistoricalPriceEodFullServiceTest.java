package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.HttpClientStub.httpClientStub;
import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.jsonTestResource;
import static dev.sorn.fmp4j.cfg.FmpConfigImpl.FMP_CONFIG;
import static dev.sorn.fmp4j.json.FmpJsonDeserializerImpl.FMP_JSON_DESERIALIZER;
import static java.util.Collections.emptySet;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.HttpClientStub;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.http.FmpHttpClientImpl;
import dev.sorn.fmp4j.models.FmpHistoricalPriceEodFull;
import java.util.Set;
import org.junit.jupiter.api.Test;

class FmpHistoricalPriceEodFullServiceTest {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);
    private final FmpService<FmpHistoricalPriceEodFull[]> service =
            new FmpHistoricalPriceEodFullService(FMP_CONFIG, http);

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/historical-price-eod/full", relativeUrl);
    }

    @Test
    void required_params() {
        // when
        var params = service.requiredParams();

        // then
        assertEquals(Set.of("symbol"), params);
    }

    @Test
    void optional_params() {
        // when
        var params = service.optionalParams();

        // then
        assertEquals(Set.of("from", "to"), params);
    }

    @Test
    void successful_download() {
        // given
        var symbol = "AAPL";
        var from = "2024-02-22";
        var to = "2024-02-28";
        service.param("symbol", symbol);
        service.param("from", from);
        service.param("to", to);
        httpStub.configureResponse()
                .body(jsonTestResource(
                        "stable/historical-price-eod/full/?symbol=%s&from=%s&to=%s.json", symbol, from, to))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(5, result.length);
        range(0, 5).forEach(i -> assertInstanceOf(FmpHistoricalPriceEodFull.class, result[i]));
        range(0, 5).forEach(i -> assertAllFieldsNonNull(result[i], emptySet()));
    }
}

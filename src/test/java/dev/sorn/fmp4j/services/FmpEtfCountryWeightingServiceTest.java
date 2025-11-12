package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.HttpClientStub.httpClientStub;
import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.jsonTestResource;
import static dev.sorn.fmp4j.json.FmpJsonDeserializerImpl.FMP_JSON_DESERIALIZER;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.HttpClientStub;
import dev.sorn.fmp4j.cfg.FmpConfigImpl;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.http.FmpHttpClientImpl;
import dev.sorn.fmp4j.models.FmpEtfCountryWeighting;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;
import org.junit.jupiter.api.Test;

class FmpEtfCountryWeightingServiceTest {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);
    private final FmpService<FmpEtfCountryWeighting[]> service =
            new FmpEtfCountryWeightingService(new FmpConfigImpl(), http);

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/etf/country-weightings", relativeUrl);
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
        var symbol = symbol("SPY");
        service.param("symbol", symbol);
        httpStub.configureResponse()
                .body(jsonTestResource("stable/etf/country-weightings/?symbol=%s.json", symbol))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(6, result.length);
        range(0, 6).forEach(i -> assertInstanceOf(FmpEtfCountryWeighting.class, result[i]));
        range(0, 6).forEach(i -> assertAllFieldsNonNull(result[i]));
    }
}

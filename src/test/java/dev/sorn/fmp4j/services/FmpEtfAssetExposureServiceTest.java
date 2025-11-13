package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.HttpClientStub.httpClientStub;
import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.jsonTestResource;
import static dev.sorn.fmp4j.json.FmpJsonDeserializer.FMP_JSON_DESERIALIZER;
import static java.util.Collections.emptySet;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.HttpClientStub;
import dev.sorn.fmp4j.cfg.FmpConfigImpl;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.http.FmpHttpClientImpl;
import dev.sorn.fmp4j.models.FmpEtfAssetExposure;
import java.util.Set;
import org.junit.jupiter.api.Test;

class FmpEtfAssetExposureServiceTest {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);
    private final FmpService<FmpEtfAssetExposure[]> service = new FmpEtfAssetExposureService(new FmpConfigImpl(), http);

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/etf/asset-exposure", relativeUrl);
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
        assertEquals(emptySet(), params);
    }

    @Test
    void successful_download() {
        // given
        var symbol = "NVO";
        service.param("symbol", symbol);
        httpStub.configureResponse()
                .body(jsonTestResource("stable/etf/asset-exposure/?symbol=%s.json", symbol))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(28, result.length);
        range(0, 28).forEach(i -> assertInstanceOf(FmpEtfAssetExposure.class, result[i]));
        range(0, 28).forEach(i -> assertAllFieldsNonNull(result[i]));
    }
}

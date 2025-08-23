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
import dev.sorn.fmp4j.models.FmpEnterpriseValue;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FmpEnterpriseValuesServiceTest {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);
    private final FmpService<FmpEnterpriseValue[]> service = new FmpEnterpriseValuesService(FMP_CONFIG, http);

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/enterprise-values", relativeUrl);
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
        assertEquals(Set.of("period", "limit"), params);
    }

    @ParameterizedTest
    @ValueSource(
            strings = {
                "annual", "quarter",
            })
    void successful_download(String period) {
        // given
        var symbol = "AAPL";
        var limit = 3;
        service.param("symbol", symbol);
        httpStub.configureResponse()
                .body(jsonTestResource(
                        "stable/enterprise-values/?symbol=%s&period=%s&limit=%d.json", symbol, period, limit))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(limit, result.length);
        range(0, limit).forEach(i -> assertInstanceOf(FmpEnterpriseValue.class, result[i]));
        range(0, limit).forEach(i -> assertAllFieldsNonNull(result[i], emptySet()));
    }
}

package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.HttpClientStub.httpClientStub;
import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.jsonTestResource;
import static dev.sorn.fmp4j.json.FmpJsonDeserializerImpl.FMP_JSON_DESERIALIZER;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.FmpSearchPressReleaseTestData;
import dev.sorn.fmp4j.HttpClientStub;
import dev.sorn.fmp4j.cfg.FmpConfigImpl;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.http.FmpHttpClientImpl;
import dev.sorn.fmp4j.models.FmpSearchPressRelease;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;
import org.junit.jupiter.api.Test;

class FmpSearchPressReleasesServiceTest implements FmpSearchPressReleaseTestData {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);
    private final FmpService<FmpSearchPressRelease[]> service =
            new FmpSearchPressReleasesService(new FmpConfigImpl(), http);

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/news/press-releases", relativeUrl);
    }

    @Test
    void required_params() {
        // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of("symbols", FmpSymbol.class), params);
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
        var symbol = symbol("V");
        service.param("symbols", symbol);
        httpStub.configureResponse()
                .body(jsonTestResource("stable/news/press-releases/?symbols=%s.json", symbol))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(3, result.length);
        assertAllFieldsNonNull(result[0]);
        assertEquals("V", result[0].symbol().value());
        assertEquals("Visa Reports Fiscal Q4 2024 Earnings", result[0].title());
    }
}

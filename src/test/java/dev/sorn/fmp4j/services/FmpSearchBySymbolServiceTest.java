package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.HttpClientStub.httpClientStub;
import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.jsonTestResource;
import static dev.sorn.fmp4j.json.FmpJsonDeserializer.FMP_JSON_DESERIALIZER;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.HttpClientStub;
import dev.sorn.fmp4j.cfg.FmpConfigImpl;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.http.FmpHttpClientImpl;
import dev.sorn.fmp4j.models.FmpSearchBySymbol;
import java.util.Set;
import org.junit.jupiter.api.Test;

class FmpSearchBySymbolServiceTest {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);
    private final FmpService<FmpSearchBySymbol[]> service = new FmpSearchBySymbolService(new FmpConfigImpl(), http);

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/search-symbol", relativeUrl);
    }

    @Test
    void required_params() {
        // when
        var params = service.requiredParams();

        // then
        assertEquals(Set.of("query"), params);
    }

    @Test
    void optional_params() {
        // when
        var params = service.optionalParams();

        // then
        assertEquals(emptySet(), params);
    }

    @Test
    void ignores_exchange_full_name_in_response() {
        // given
        var query = "ADYEN2";
        service.param("query", query);
        httpStub.configureResponse()
                .body(jsonTestResource("stable/search-symbol/?query=%s.json", query))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals("Euronext Amsterdam", result[0].exchange().fullName());
    }

    @Test
    void successful_download() {
        // given
        var query = "ADYEN";
        service.param("query", query);
        httpStub.configureResponse()
                .body(jsonTestResource("stable/search-symbol/?query=%s.json", query))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(1, result.length);
        assertInstanceOf(FmpSearchBySymbol.class, result[0]);
        assertAllFieldsNonNull(result[0]);
    }
}

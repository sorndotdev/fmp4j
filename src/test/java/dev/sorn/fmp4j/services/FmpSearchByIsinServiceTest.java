package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.HttpClientStub.httpClientStub;
import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.jsonTestResource;
import static dev.sorn.fmp4j.json.FmpJsonDeserializer.FMP_JSON_DESERIALIZER;
import static dev.sorn.fmp4j.types.FmpIsin.isin;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.HttpClientStub;
import dev.sorn.fmp4j.cfg.FmpConfigImpl;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.http.FmpHttpClientImpl;
import dev.sorn.fmp4j.models.FmpSearchByIsin;
import dev.sorn.fmp4j.types.FmpIsin;
import java.util.Map;
import org.junit.jupiter.api.Test;

class FmpSearchByIsinServiceTest {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);
    private final FmpService<FmpSearchByIsin[]> service = new FmpSearchByIsinService(new FmpConfigImpl(), http);

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/search-isin", relativeUrl);
    }

    @Test
    void required_params() {
        // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of("isin", FmpIsin.class), params);
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
        var isin = isin("NL0012969182");
        service.param("isin", isin);
        httpStub.configureResponse()
                .body(jsonTestResource("stable/search-isin/?isin=%s.json", isin))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(3, result.length);
        range(0, 3).forEach(i -> assertInstanceOf(FmpSearchByIsin.class, result[i]));
        range(0, 3).forEach(i -> assertAllFieldsNonNull(result[i]));
    }
}

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
import dev.sorn.fmp4j.models.FmpIposDisclosure;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class FmpIposDisclosureServiceTest {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);
    private final FmpService<FmpIposDisclosure[]> service = new FmpIposDisclosureService(new FmpConfigImpl(), http);

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/ipos-disclosure", relativeUrl);
    }

    @Test
    void required_params() {
        // when
        var params = service.requiredParams();

        // then
        assertEquals(emptySet(), params);
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
        httpStub.configureResponse()
                .body(jsonTestResource("stable/ipos-disclosure/excerpt.json"))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(2, result.length);
        range(0, 2).forEach(i -> assertInstanceOf(FmpIposDisclosure.class, result[i]));
        range(0, 2).forEach(i -> assertAllFieldsNonNull(result[i], Set.of()));
    }
}

package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.HttpClientStub.httpClientStub;
import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.jsonTestResource;
import static dev.sorn.fmp4j.json.FmpJsonDeserializerImpl.FMP_JSON_DESERIALIZER;
import static dev.sorn.fmp4j.types.FmpLimit.limit;
import static dev.sorn.fmp4j.types.FmpPage.page;
import static java.lang.String.format;
import static java.util.Collections.emptySet;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.sorn.fmp4j.HttpClientStub;
import dev.sorn.fmp4j.LatestEarningsCallTranscriptTestData;
import dev.sorn.fmp4j.cfg.FmpConfigImpl;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.http.FmpHttpClientImpl;
import dev.sorn.fmp4j.models.FmpLatestEarningsCallTranscript;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPage;
import org.junit.jupiter.api.Test;

class FmpLatestEarningsCallTranscriptServiceTest implements LatestEarningsCallTranscriptTestData {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);
    private final FmpService<FmpLatestEarningsCallTranscript[]> service =
            new FmpLatestEarningsCallTranscriptService(new FmpConfigImpl(), http);

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/earning-call-transcript-latest", relativeUrl);
    }

    @Test
    void required_params() {
        // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of("limit", FmpLimit.class, "page", FmpPage.class), params);
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
        var limit = 2;
        var page = "0";
        var endpoint = "earning-call-transcript-latest";
        service.param("limit", limit(limit));
        service.param("page", page(page));
        httpStub.configureResponse()
                .body(jsonTestResource("stable/%s/?page=%s&limit=%s.json", endpoint, page, limit))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(limit, result.length);
        assertEquals(aLatestEarningCallTranscript(), result[0]);
        range(0, limit).forEach(i -> assertAllFieldsNonNull(result[i]));
    }

    @Test
    void missing_params_throws() {
        // given // when
        Consumer<FmpService<FmpLatestEarningsCallTranscript[]>> f = FmpService::download;

        // then
        var e = assertThrows(FmpServiceException.class, () -> f.accept(service));
        String msg = e.getMessage();

        assertTrue(
                msg.equals(format("'limit' is a required query param for endpoint [%s]", service.url()))
                        || msg.equals(format("'page' is a required query param for endpoint [%s]", service.url())),
                "Expected exception message to mention either 'limit' or 'page' as the required param, but got: "
                        + msg);
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

package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.HttpClientStub.httpClientStub;
import static dev.sorn.fmp4j.TestUtils.jsonTestResource;
import static dev.sorn.fmp4j.json.FmpJsonDeserializer.FMP_JSON_DESERIALIZER;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.HttpClientStub;
import dev.sorn.fmp4j.RatioTtmTestData;
import dev.sorn.fmp4j.cfg.FmpConfigImpl;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.http.FmpHttpClientImpl;
import dev.sorn.fmp4j.models.FmpRatioTtm;
import java.util.Set;
import org.junit.jupiter.api.Test;

class FmpRatioTtmServiceTest implements RatioTtmTestData {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);
    private final FmpService<FmpRatioTtm[]> service = new FmpRatioTtmService(new FmpConfigImpl(), http);

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/ratios-ttm", relativeUrl);
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
        var symbol = "AAPL";
        service.param("symbol", symbol);
        httpStub.configureResponse()
                .body(jsonTestResource("stable/ratios-ttm/?symbol=%s.json", symbol))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(1, result.length);
        assertEquals(aTtmRatio(), result[0]);
    }
}

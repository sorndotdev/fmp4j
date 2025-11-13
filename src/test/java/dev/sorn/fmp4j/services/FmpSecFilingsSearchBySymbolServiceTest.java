package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.HttpClientStub.httpClientStub;
import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.jsonTestResource;
import static dev.sorn.fmp4j.json.FmpJsonDeserializer.FMP_JSON_DESERIALIZER;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.FinancialGrowthTestData;
import dev.sorn.fmp4j.HttpClientStub;
import dev.sorn.fmp4j.cfg.FmpConfigImpl;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.http.FmpHttpClientImpl;
import dev.sorn.fmp4j.models.FmpSecFilingsSearchBySymbol;
import java.util.Set;
import org.junit.jupiter.api.Test;

class FmpSecFilingsSearchBySymbolServiceTest implements FinancialGrowthTestData {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);
    private final FmpService<FmpSecFilingsSearchBySymbol[]> service =
            new FmpSecFilingsSearchBySymbolService(new FmpConfigImpl(), http);

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/sec-filings-search/symbol", relativeUrl);
    }

    @Test
    void required_params() {
        // when
        var params = service.requiredParams();

        // then
        assertEquals(Set.of("symbol", "from", "to"), params);
    }

    @Test
    void optional_params() {
        // when
        var params = service.optionalParams();

        // then
        assertEquals(Set.of("page", "limit"), params);
    }

    @Test
    void successful_download() {
        // given
        var symbol = "AAPL";
        var from = "2024-01-01";
        var to = "2025-01-01";
        var page = 0;
        var limit = 2;
        service.param("symbol", symbol);
        service.param("from", from);
        service.param("to", to);
        service.param("page", page);
        service.param("limit", limit);
        httpStub.configureResponse()
                .body(jsonTestResource(
                        "stable/sec-filings-search/symbol/?symbol=%s&from=%s&to=%s&page=%d&limit=%d.json",
                        symbol, from, to, page, limit))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(limit, result.length);
        range(0, limit).forEach(i -> assertAllFieldsNonNull(result[i]));
    }
}

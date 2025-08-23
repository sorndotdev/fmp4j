package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.HttpClientStub.httpClientStub;
import static dev.sorn.fmp4j.TestUtils.jsonTestResource;
import static dev.sorn.fmp4j.cfg.FmpConfigImpl.FMP_CONFIG;
import static dev.sorn.fmp4j.json.FmpJsonDeserializerImpl.FMP_JSON_DESERIALIZER;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.HttpClientStub;
import dev.sorn.fmp4j.StockPriceChangeTestData;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.http.FmpHttpClientImpl;
import dev.sorn.fmp4j.models.FmpStockPriceChange;
import java.util.Set;
import org.junit.jupiter.api.Test;

class FmpStockPriceChangeServiceTest implements StockPriceChangeTestData {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);
    private final FmpService<FmpStockPriceChange[]> service = new FmpStockPriceChangeService(FMP_CONFIG, http);

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/stock-price-change", relativeUrl);
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
        service.param("symbol", "AAPL");
        httpStub.configureResponse()
                .body(jsonTestResource("stable/stock-price-change/?symbol=AAPL.json"))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(1, result.length);
        assertEquals(aStockPriceChange(), result[0]);
    }
}

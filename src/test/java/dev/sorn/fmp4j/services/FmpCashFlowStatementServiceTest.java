package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.HttpClientStub.httpClientStub;
import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.jsonTestResource;
import static dev.sorn.fmp4j.cfg.FmpConfigImpl.FMP_CONFIG;
import static dev.sorn.fmp4j.json.FmpJsonDeserializerImpl.FMP_JSON_DESERIALIZER;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.CashFlowStatementTestData;
import dev.sorn.fmp4j.HttpClientStub;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.http.FmpHttpClientImpl;
import dev.sorn.fmp4j.models.FmpCashFlowStatement;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FmpCashFlowStatementServiceTest implements CashFlowStatementTestData {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);
    private final FmpService<FmpCashFlowStatement[]> service = new FmpCashFlowStatementService(FMP_CONFIG, http);

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/cash-flow-statement", relativeUrl);
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

    @Test
    void successful_download() {
        // given
        var symbol = "AAPL";
        service.param("symbol", symbol);
        httpStub.configureResponse()
                .body(jsonTestResource("stable/cash-flow-statement/?symbol=%s.json", symbol))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(5, result.length);
        assertEquals(anAnnualCashFlowStatement(), result[0]);
        range(0, 5).forEach(i -> assertAllFieldsNonNull(result[i]));
    }

    @ParameterizedTest
    @ValueSource(strings = {"annual", "quarter"})
    void successful_download_with_optional_period_and_limit(String period) {
        // given
        var symbol = "AAPL";
        var limit = 3;
        service.param("symbol", symbol);
        httpStub.configureResponse()
                .body(jsonTestResource(
                        "stable/cash-flow-statement/?symbol=%s&period=%s&limit=%d.json", symbol, period, limit))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(limit, result.length);
        range(0, limit).forEach(i -> assertAllFieldsNonNull(result[i]));
    }
}

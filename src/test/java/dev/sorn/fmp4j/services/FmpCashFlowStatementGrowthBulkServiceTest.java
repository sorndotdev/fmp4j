package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.HttpClientStub.httpClientStub;
import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.jsonTestResource;
import static dev.sorn.fmp4j.json.FmpJsonDeserializer.FMP_JSON_DESERIALIZER;
import static dev.sorn.fmp4j.types.FmpPeriod.period;
import static dev.sorn.fmp4j.types.FmpYear.year;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.HttpClientStub;
import dev.sorn.fmp4j.cfg.FmpConfigImpl;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.http.FmpHttpClientImpl;
import dev.sorn.fmp4j.models.FmpCashFlowStatementGrowth;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;
import org.junit.jupiter.api.Test;

class FmpCashFlowStatementGrowthBulkServiceTest {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);
    private final FmpService<FmpCashFlowStatementGrowth[]> service =
            new FmpCashFlowStatementGrowthBulkService(new FmpConfigImpl(), http);

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/cash-flow-statement-growth-bulk", relativeUrl);
    }

    @Test
    void required_params() {
        // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of("symbol", FmpSymbol.class), params);
    }

    @Test
    void optional_params() {
        // when
        var params = service.optionalParams();

        // then
        assertEquals(Map.of("period", FmpPeriod.class, "limit", FmpLimit.class), params);
    }

    @Test
    void successful_download() {
        // given
        var year = year("2025");
        var period = period("quarter");
        service.param("year", year);
        service.param("period", period);
        httpStub.configureResponse()
                .body(jsonTestResource("stable/cash-flow-statement-growth-bulk/?year=%s&period=%s.csv", year, period))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(1, result.length);
        range(0, result.length).forEach(i -> assertAllFieldsNonNull(result[i]));
    }
}

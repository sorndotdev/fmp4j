package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.HttpClientStub.httpClientStub;
import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.jsonTestResource;
import static dev.sorn.fmp4j.cfg.FmpConfigImpl.FMP_CONFIG;
import static dev.sorn.fmp4j.json.FmpJsonDeserializerImpl.FMP_JSON_DESERIALIZER;
import static java.util.Collections.emptySet;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.HttpClientStub;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.http.FmpHttpClientImpl;
import dev.sorn.fmp4j.models.FmpHistoricalChart;
import java.util.Set;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FmpHistoricalChartServiceTest {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);

    @ParameterizedTest
    @ValueSource(
            strings = {
                "1min", "5min", "15min", "30min", "1hour", "4hour",
            })
    void relative_url(String interval) {
        // given
        var service = new FmpHistoricalChartService(FMP_CONFIG, http, interval);

        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/historical-chart/" + interval, relativeUrl);
    }

    @ParameterizedTest
    @ValueSource(
            strings = {
                "1min", "5min", "15min", "30min", "1hour", "4hour",
            })
    void required_params(String interval) {
        // given
        var service = new FmpHistoricalChartService(FMP_CONFIG, http, interval);

        // when
        var params = service.requiredParams();

        // then
        assertEquals(Set.of("symbol"), params);
    }

    @ParameterizedTest
    @ValueSource(
            strings = {
                "1min", "5min", "15min", "30min", "1hour", "4hour",
            })
    void optional_params(String interval) {
        // given
        var service = new FmpHistoricalChartService(FMP_CONFIG, http, interval);

        // when
        var params = service.optionalParams();

        // then
        assertEquals(Set.of("from", "to"), params);
    }

    @ParameterizedTest
    @ValueSource(
            strings = {
                "1min", "5min", "15min", "30min", "1hour", "4hour",
            })
    void successful_download(String interval) {
        // given
        var service = new FmpHistoricalChartService(FMP_CONFIG, http, interval);
        var symbol = "AAPL";
        var from = "2024-01-01";
        var to = "2024-01-02";
        service.param("symbol", symbol);
        service.param("from", from);
        service.param("to", to);
        httpStub.configureResponse()
                .body(jsonTestResource(
                        "stable/historical-chart/%s/?symbol=%s&from=%s&to=%s.json", interval, symbol, from, to))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(2, result.length);
        range(0, 2).forEach(i -> assertInstanceOf(FmpHistoricalChart.class, result[i]));
        range(0, 2).forEach(i -> assertAllFieldsNonNull(result[i], emptySet()));
    }
}

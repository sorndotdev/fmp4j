package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.types.FmpInterval.interval;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static java.util.Collections.emptySet;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.models.FmpHistoricalChart;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FmpHistoricalChartServiceTest extends HttpTest {

    @ParameterizedTest
    @ValueSource(
            strings = {
                "1min", "5min", "15min", "30min", "1hour", "4hour",
            })
    void relative_url(String interval) {
        // given
        var service = new FmpHistoricalChartService(config, client, interval(interval));

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
        var service = new FmpHistoricalChartService(config, client, interval(interval));

        // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of("symbol", FmpSymbol.class), params);
    }

    @ParameterizedTest
    @ValueSource(
            strings = {
                "1min", "5min", "15min", "30min", "1hour", "4hour",
            })
    void optional_params(String interval) {
        // given
        var service = new FmpHistoricalChartService(config, client, interval(interval));

        // when
        var params = service.optionalParams();

        // then
        assertEquals(Map.of("from", LocalDate.class, "to", LocalDate.class), params);
    }

    @ParameterizedTest
    @ValueSource(
            strings = {
                "1min", "5min", "15min", "30min", "1hour", "4hour",
            })
    void successful_download(String interval) {
        // given
        var service = new FmpHistoricalChartService(config, client, interval(interval));
        var symbol = symbol("AAPL");
        var from = LocalDate.parse("2024-01-01");
        var to = LocalDate.parse("2024-01-02");
        service.param("symbol", symbol);
        service.param("from", from);
        service.param("to", to);
        httpStub.configureResponse()
                .body(testResource(
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

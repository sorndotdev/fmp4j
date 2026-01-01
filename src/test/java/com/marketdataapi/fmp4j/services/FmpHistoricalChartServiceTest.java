package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.TestUtils.assertAllFieldsNonNull;
import static com.marketdataapi.fmp4j.TestUtils.testResource;
import static com.marketdataapi.fmp4j.types.FmpInterval.interval;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_FROM;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_TO;
import static java.util.Collections.emptySet;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import com.marketdataapi.fmp4j.models.FmpHistoricalChart;
import com.marketdataapi.fmp4j.types.FmpSymbol;
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
        assertEquals(Map.of(PARAM_SYMBOL, FmpSymbol.class), params);
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
        assertEquals(Map.of(PARAM_FROM, LocalDate.class, PARAM_TO, LocalDate.class), params);
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
        service.param(PARAM_SYMBOL, symbol);
        service.param(PARAM_FROM, from);
        service.param(PARAM_TO, to);
        httpStub.configureResponse()
                .body(testResource(
                        "stable/historical-chart/%s/?symbol=%s&from=%s&to=%s.json", interval, symbol, from, to))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(2, result.size());
        range(0, 2).forEach(i -> assertInstanceOf(FmpHistoricalChart.class, result.get(i)));
        range(0, 2).forEach(i -> assertAllFieldsNonNull(result.get(i), emptySet()));
    }
}

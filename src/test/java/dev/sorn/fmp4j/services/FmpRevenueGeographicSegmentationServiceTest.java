package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_PERIOD;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_QUARTER;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_STRUCTURE;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.RevenueGeographicSegmentationTestData;
import dev.sorn.fmp4j.models.FmpRevenueGeographicSegmentation;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpStructure;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FmpRevenueGeographicSegmentationServiceTest extends HttpTest
        implements RevenueGeographicSegmentationTestData {
    private FmpService<FmpRevenueGeographicSegmentation[]> service;

    @BeforeEach
    void setup() {
        service = new FmpRevenueGeographicSegmentationService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/revenue-geographic-segmentation", relativeUrl);
    }

    @Test
    void required_params() {
        // given // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of(PARAM_SYMBOL, FmpSymbol.class), params);
    }

    @Test
    void optional_params() {
        // given // when
        var params = service.optionalParams();

        // then
        assertEquals(Map.of(PARAM_PERIOD, FmpPeriod.class, PARAM_STRUCTURE, FmpStructure.class), params);
    }

    @Test
    void successful_download() {
        // given
        var symbol = symbol("AAPL");
        service.param(PARAM_SYMBOL, symbol);
        httpStub.configureResponse()
                .body(testResource("stable/revenue-geographic-segmentation/?symbol=%s.json", symbol))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(15, result.length);
        range(0, 15).forEach(i -> assertAllFieldsNonNull(result[i], Set.of("reportedCurrency")));
        assertEquals(aRevenueGeographicSegmentation(), result[0]);
    }

    @Test
    void successful_download_annual_flat() {
        // given
        var symbol = symbol("AAPL");
        var period = "annual";
        var structure = "flat";
        service.param(PARAM_SYMBOL, symbol);
        httpStub.configureResponse()
                .body(testResource(
                        "stable/revenue-geographic-segmentation/?symbol=%s&period=%s&structure=%s.json",
                        symbol, period, structure))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(15, result.length);
        range(0, 15).forEach(i -> assertAllFieldsNonNull(result[i], Set.of("reportedCurrency")));
    }

    @Test
    void successful_download_quarter_flat() {
        // given
        var symbol = symbol("AAPL");
        var period = PARAM_QUARTER;
        var structure = "flat";
        service.param(PARAM_SYMBOL, symbol);
        httpStub.configureResponse()
                .body(testResource(
                        "stable/revenue-geographic-segmentation/?symbol=%s&period=%s&structure=%s.json",
                        symbol, period, structure))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(59, result.length);
        range(0, 59).forEach(i -> assertAllFieldsNonNull(result[i], Set.of("reportedCurrency")));
    }
}

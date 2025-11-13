package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.HttpClientStub.httpClientStub;
import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.jsonTestResource;
import static dev.sorn.fmp4j.json.FmpJsonDeserializer.FMP_JSON_DESERIALIZER;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.HttpClientStub;
import dev.sorn.fmp4j.RevenueGeographicSegmentationTestData;
import dev.sorn.fmp4j.cfg.FmpConfigImpl;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.http.FmpHttpClientImpl;
import dev.sorn.fmp4j.models.FmpRevenueGeographicSegmentation;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class FmpRevenueGeographicSegmentationServiceTest implements RevenueGeographicSegmentationTestData {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);
    private final FmpService<FmpRevenueGeographicSegmentation[]> service =
            new FmpRevenueGeographicSegmentationService(new FmpConfigImpl(), http);

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/revenue-geographic-segmentation", relativeUrl);
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
        assertEquals(Set.of("period", "structure"), params);
    }

    @Test
    void successful_download() {
        // given
        var symbol = "AAPL";
        service.param("symbol", symbol);
        httpStub.configureResponse()
                .body(jsonTestResource("stable/revenue-geographic-segmentation/?symbol=%s.json", symbol))
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
        var symbol = "AAPL";
        var period = "annual";
        var structure = "flat";
        service.param("symbol", symbol);
        httpStub.configureResponse()
                .body(jsonTestResource(
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
        var symbol = "AAPL";
        var period = "quarter";
        var structure = "flat";
        service.param("symbol", symbol);
        httpStub.configureResponse()
                .body(jsonTestResource(
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

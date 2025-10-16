package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.HttpClientStub.httpClientStub;
import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.jsonTestResource;
import static dev.sorn.fmp4j.json.FmpJsonDeserializerImpl.FMP_JSON_DESERIALIZER;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.HttpClientStub;
import dev.sorn.fmp4j.RevenueProductSegmentationTestData;
import dev.sorn.fmp4j.cfg.FmpConfigImpl;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.http.FmpHttpClientImpl;
import dev.sorn.fmp4j.models.FmpRevenueProductSegmentation;
import java.util.Map;
import java.util.Set;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpStructure;
import dev.sorn.fmp4j.types.FmpSymbol;
import org.junit.jupiter.api.Test;

class FmpRevenueProductSegmentationServiceTest implements RevenueProductSegmentationTestData {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);
    private final FmpService<FmpRevenueProductSegmentation[]> service =
            new FmpRevenueProductSegmentationService(new FmpConfigImpl(), http);

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/revenue-product-segmentation", relativeUrl);
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
        assertEquals(Map.of("period", FmpPeriod.class, "structure", FmpStructure.class), params);
    }

    @Test
    void successful_download() {
        // given
        var symbol = symbol("AAPL");
        service.param("symbol", symbol);
        httpStub.configureResponse()
                .body(jsonTestResource("stable/revenue-product-segmentation/?symbol=%s.json", symbol))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(15, result.length);
        range(0, 15).forEach(i -> assertAllFieldsNonNull(result[i], Set.of("reportedCurrency")));
        assertEquals(aRevenueProductSegmentation(), result[0]);
    }

    @Test
    void successful_download_annual_flat() {
        // given
        var symbol = symbol("AAPL");
        var period = "annual";
        var structure = "flat";
        service.param("symbol", symbol);
        httpStub.configureResponse()
                .body(jsonTestResource(
                        "stable/revenue-product-segmentation/?symbol=%s&period=%s&structure=%s.json",
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
        var period = "quarter";
        var structure = "flat";
        service.param("symbol", symbol);
        httpStub.configureResponse()
                .body(jsonTestResource(
                        "stable/revenue-product-segmentation/?symbol=%s&period=%s&structure=%s.json",
                        symbol, period, structure))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(42, result.length);
        range(0, 42).forEach(i -> assertAllFieldsNonNull(result[i], Set.of("reportedCurrency")));
    }
}

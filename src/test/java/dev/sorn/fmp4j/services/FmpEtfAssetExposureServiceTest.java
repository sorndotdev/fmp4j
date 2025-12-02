package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.models.FmpEtfAssetExposure;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpEtfAssetExposureServiceTest extends HttpTest {
    private FmpService<FmpEtfAssetExposure[]> service;

    @BeforeEach
    void setup() {
        service = new FmpEtfAssetExposureService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/etf/asset-exposure", relativeUrl);
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
        assertEquals(Map.of(), params);
    }

    @Test
    void successful_download() {
        // given
        var symbol = symbol("NVO");
        service.param(PARAM_SYMBOL, symbol);
        httpStub.configureResponse()
                .body(testResource("stable/etf/asset-exposure/?symbol=%s.json", symbol))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(28, result.length);
        range(0, 28).forEach(i -> assertInstanceOf(FmpEtfAssetExposure.class, result[i]));
        range(0, 28).forEach(i -> assertAllFieldsNonNull(result[i]));
    }
}

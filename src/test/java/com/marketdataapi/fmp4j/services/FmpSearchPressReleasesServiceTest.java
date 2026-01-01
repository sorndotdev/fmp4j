package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.TestUtils.assertAllFieldsNonNull;
import static com.marketdataapi.fmp4j.TestUtils.testResource;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOLS;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marketdataapi.fmp4j.FmpSearchPressReleaseTestData;
import com.marketdataapi.fmp4j.models.FmpSearchPressRelease;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpSearchPressReleasesServiceTest extends HttpTest implements FmpSearchPressReleaseTestData {
    private FmpService<FmpSearchPressRelease> service;

    @BeforeEach
    void setup() {
        service = new FmpSearchPressReleasesService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/news/press-releases", relativeUrl);
    }

    @Test
    void required_params() {
        // given // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of(PARAM_SYMBOLS, FmpSymbol.class), params);
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
        var symbol = symbol("V");
        service.param(PARAM_SYMBOLS, symbol);
        httpStub.configureResponse()
                .body(testResource("stable/news/press-releases/?symbols=%s.json", symbol))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(3, result.size());
        assertAllFieldsNonNull(result.get(0));
        assertEquals("V", result.get(0).symbol().value());
        assertEquals("Visa Reports Fiscal Q4 2024 Earnings", result.get(0).title());
    }
}

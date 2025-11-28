package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static java.util.Collections.emptySet;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.models.FmpEnterpriseValue;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FmpEnterpriseValuesServiceTest extends HttpTest {
    private FmpService<FmpEnterpriseValue[]> service;

    @BeforeEach
    void setup() {
        service = new FmpEnterpriseValuesService(config, client);
    }

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/enterprise-values", relativeUrl);
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

    @ParameterizedTest
    @ValueSource(
            strings = {
                "annual", "quarter",
            })
    void successful_download(String period) {
        // given
        var symbol = symbol("AAPL");
        var limit = 3;
        service.param("symbol", symbol);
        httpStub.configureResponse()
                .body(testResource(
                        "stable/enterprise-values/?symbol=%s&period=%s&limit=%d.json", symbol, period, limit))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(limit, result.length);
        range(0, limit).forEach(i -> assertInstanceOf(FmpEnterpriseValue.class, result[i]));
        range(0, limit).forEach(i -> assertAllFieldsNonNull(result[i], emptySet()));
    }
}

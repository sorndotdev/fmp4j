package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static java.util.Collections.emptySet;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.models.FmpHistoricalPriceEodLight;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpHistoricalPriceEodLightServiceTest extends HttpTest {
    private FmpService<FmpHistoricalPriceEodLight[]> service;

    @BeforeEach
    void setup() {
        service = new FmpHistoricalPriceEodLightService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/historical-price-eod/light", relativeUrl);
    }

    @Test
    void required_params() {
        // given // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of("symbol", FmpSymbol.class), params);
    }

    @Test
    void optional_params() {
        // given // when
        var params = service.optionalParams();

        // then
        assertEquals(Map.of("from", LocalDate.class, "to", LocalDate.class), params);
    }

    @Test
    void successful_download() {
        // given
        var symbol = symbol("AAPL");
        var from = LocalDate.parse("2024-02-22");
        var to = LocalDate.parse("2024-02-28");
        service.param("symbol", symbol);
        service.param("from", from);
        service.param("to", to);
        httpStub.configureResponse()
                .body(testResource("stable/historical-price-eod/light/?symbol=%s&from=%s&to=%s.json", symbol, from, to))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(5, result.length);
        range(0, 5).forEach(i -> assertInstanceOf(FmpHistoricalPriceEodLight.class, result[i]));
        range(0, 5).forEach(i -> assertAllFieldsNonNull(result[i], emptySet()));
    }
}

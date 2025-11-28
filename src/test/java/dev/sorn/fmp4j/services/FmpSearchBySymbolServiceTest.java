package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.models.FmpSearchBySymbol;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpSearchBySymbolServiceTest extends HttpTest {
    private FmpService<FmpSearchBySymbol[]> service;

    @BeforeEach
    void setup() {
        service = new FmpSearchBySymbolService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/search-symbol", relativeUrl);
    }

    @Test
    void required_params() {
        // given // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of("query", FmpSymbol.class), params);
    }

    @Test
    void optional_params() {
        // given // when
        var params = service.optionalParams();

        // then
        assertEquals(Map.of(), params);
    }

    @Test
    void ignores_exchange_full_name_in_response() {
        // given
        var query = symbol("ADYEN2");
        service.param("query", query);
        httpStub.configureResponse()
                .body(testResource("stable/search-symbol/?query=%s.json", query))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals("Euronext Amsterdam", result[0].exchange().fullName());
    }

    @Test
    void successful_download() {
        // given
        var query = symbol("ADYEN");
        service.param("query", query);
        httpStub.configureResponse()
                .body(testResource("stable/search-symbol/?query=%s.json", query))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(1, result.length);
        assertInstanceOf(FmpSearchBySymbol.class, result[0]);
        assertAllFieldsNonNull(result[0]);
    }
}

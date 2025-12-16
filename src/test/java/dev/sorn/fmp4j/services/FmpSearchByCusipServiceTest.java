package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.types.FmpCusip.cusip;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.models.FmpSearchByCusip;
import dev.sorn.fmp4j.types.FmpCusip;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpSearchByCusipServiceTest extends HttpTest {
    private FmpService<FmpSearchByCusip> service;

    @BeforeEach
    void setup() {
        service = new FmpSearchByCusipService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/search-cusip", relativeUrl);
    }

    @Test
    void required_params() {
        // given // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of("cusip", FmpCusip.class), params);
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
        var cusip = cusip("037833100");
        service.param("cusip", cusip);
        httpStub.configureResponse()
                .body(testResource("stable/search-cusip/?cusip=%s.json", cusip))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(3, result.size());
        range(0, 3).forEach(i -> assertInstanceOf(FmpSearchByCusip.class, result.get(i)));
        range(0, 3).forEach(i -> assertAllFieldsNonNull(result.get(i)));
    }
}

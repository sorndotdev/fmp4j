package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.types.FmpIsin.isin;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.models.FmpSearchByIsin;
import dev.sorn.fmp4j.types.FmpIsin;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpSearchByIsinServiceTest extends HttpTest {
    private FmpService<FmpSearchByIsin> service;

    @BeforeEach
    void setup() {
        service = new FmpSearchByIsinService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/search-isin", relativeUrl);
    }

    @Test
    void required_params() {
        // given // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of("isin", FmpIsin.class), params);
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
        var isin = isin("NL0012969182");
        service.param("isin", isin);
        httpStub.configureResponse()
                .body(testResource("stable/search-isin/?isin=%s.json", isin))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(3, result.size());
        range(0, 3).forEach(i -> assertInstanceOf(FmpSearchByIsin.class, result.get(i)));
        range(0, 3).forEach(i -> assertAllFieldsNonNull(result.get(i)));
    }
}

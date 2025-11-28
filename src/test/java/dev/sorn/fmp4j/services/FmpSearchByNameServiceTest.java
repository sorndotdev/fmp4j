package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.models.FmpSearchByName;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpSearchByNameServiceTest extends HttpTest {
    private FmpService<FmpSearchByName[]> service;

    @BeforeEach
    void setup() {
        service = new FmpSearchByNameService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/search-name", relativeUrl);
    }

    @Test
    void required_params() {
        // given // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of("query", String.class), params);
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
        var query = "ADYEN";
        service.param("query", query);
        httpStub.configureResponse()
                .body(testResource("stable/search-name/?query=%s.json", query))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(5, result.length);
        range(0, 5).forEach(i -> assertInstanceOf(FmpSearchByName.class, result[i]));
        range(0, 5).forEach(i -> assertAllFieldsNonNull(result[i]));
    }
}

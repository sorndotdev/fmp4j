package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.models.FmpSearchByCik;
import dev.sorn.fmp4j.types.FmpCik;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FmpSearchByCikServiceTest extends HttpTest {
    private FmpService<FmpSearchByCik[]> service;

    @BeforeEach
    void setup() {
        service = new FmpSearchByCikService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/search-cik", relativeUrl);
    }

    @Test
    void required_params() {
        // given // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of("cik", FmpCik.class), params);
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
        var cik = FmpCik.cik("0000320193");
        service.param("cik", cik);
        httpStub.configureResponse()
                .body(testResource("stable/search-cik/?cik=%s.json", cik))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(1, result.length);
        assertInstanceOf(FmpSearchByCik.class, result[0]);
        assertAllFieldsNonNull(result[0]);
    }
}

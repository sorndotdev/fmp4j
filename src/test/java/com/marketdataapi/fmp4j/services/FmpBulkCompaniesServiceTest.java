package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.TestUtils.assertAllFieldsNonNull;
import static com.marketdataapi.fmp4j.TestUtils.testResource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import com.marketdataapi.fmp4j.models.FmpCompanies;
import com.marketdataapi.fmp4j.types.FmpPart;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpBulkCompaniesServiceTest extends HttpTest {
    private FmpService<FmpCompanies> service;

    @BeforeEach
    void setup() {
        service = new FmpBulkCompaniesService(config, client);
    }

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/profile-bulk", relativeUrl);
    }

    @Test
    void required_params() {
        // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of("part", FmpPart.class), params);
    }

    @Test
    void optional_params() {
        // when
        var params = service.optionalParams();

        // then
        assertEquals(Map.of(), params);
    }

    @Test
    void successful_download() {
        // given
        var part = FmpPart.part("0");

        service.param("part", part);
        httpStub.configureResponse()
                .body(testResource("stable/profile-bulk/?part=%s.csv", part))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(1, result.size());
        assertInstanceOf(FmpCompanies.class, result.get(0));
        assertAllFieldsNonNull(result.get(0));
    }
}

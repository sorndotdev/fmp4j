package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.TestUtils.assertAllFieldsNonNull;
import static com.marketdataapi.fmp4j.TestUtils.testResource;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marketdataapi.fmp4j.models.FmpKeyMetricTtm;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpBulkKeyMetricTtmServiceTest extends HttpTest {
    private FmpService<FmpKeyMetricTtm> service;

    @BeforeEach
    void setup() {
        service = new FmpBulkKeyMetricTtmService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/key-metrics-ttm-bulk", relativeUrl);
    }

    @Test
    void required_params() {
        // given // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of(), params);
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
        httpStub.configureResponse()
                .body(testResource("stable/key-metrics-ttm-bulk/excerpt.csv"))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(2, result.size());
        range(0, result.size()).forEach(i -> assertAllFieldsNonNull(result.get(i)));
    }
}

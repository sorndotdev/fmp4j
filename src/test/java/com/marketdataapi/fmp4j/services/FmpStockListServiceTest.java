package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.TestUtils.assertAllFieldsNonNull;
import static com.marketdataapi.fmp4j.TestUtils.testResource;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import com.marketdataapi.fmp4j.models.FmpStock;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpStockListServiceTest extends HttpTest {
    private FmpService<FmpStock> service;

    @BeforeEach
    void setup() {
        service = new FmpStockListService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/stock-list", relativeUrl);
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
                .body(testResource("stable/stock-list/excerpt.json"))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(2, result.size());
        range(0, 2).forEach(i -> assertInstanceOf(FmpStock.class, result.get(i)));
        range(0, 2).forEach(i -> assertAllFieldsNonNull(result.get(i)));
    }

    @Test
    void partial_successful_download() {
        // given
        httpStub.configureResponse()
                .body(testResource("stable/stock-list/contains_invalid.json"))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(2, result.size());
        range(0, 2).forEach(i -> assertInstanceOf(FmpStock.class, result.get(i)));
        range(0, 2).forEach(i -> assertAllFieldsNonNull(result.get(i)));
    }
}

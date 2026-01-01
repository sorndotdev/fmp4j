package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.TestUtils.testResource;
import static com.marketdataapi.fmp4j.types.FmpPeriod.period;
import static com.marketdataapi.fmp4j.types.FmpYear.year;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_PERIOD;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_QUARTER;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_YEAR;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marketdataapi.fmp4j.TestUtils;
import com.marketdataapi.fmp4j.models.FmpCashFlowStatementGrowth;
import com.marketdataapi.fmp4j.types.FmpPeriod;
import com.marketdataapi.fmp4j.types.FmpYear;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpBulkCashFlowStatementGrowthServiceTest extends HttpTest {
    private FmpService<FmpCashFlowStatementGrowth> service;

    @BeforeEach
    void setup() {
        service = new FmpBulkCashFlowStatementGrowthService(config, client);
    }

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/cash-flow-statement-growth-bulk", relativeUrl);
    }

    @Test
    void required_params() {
        // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of(PARAM_YEAR, FmpYear.class, PARAM_PERIOD, FmpPeriod.class), params);
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
        var year = year("2025");
        var period = period(PARAM_QUARTER);
        service.param(PARAM_YEAR, year);
        service.param(PARAM_PERIOD, period);
        httpStub.configureResponse()
                .body(testResource("stable/cash-flow-statement-growth-bulk/?year=%s&period=%s.csv", year, period))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(1, result.size());
        result.forEach(TestUtils::assertAllFieldsNonNull);
    }
}

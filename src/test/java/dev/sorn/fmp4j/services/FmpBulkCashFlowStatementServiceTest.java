package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.types.FmpPeriod.period;
import static dev.sorn.fmp4j.types.FmpYear.year;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_PERIOD;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_YEAR;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.TestUtils;
import dev.sorn.fmp4j.models.FmpCashFlowStatement;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpYear;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FmpBulkCashFlowStatementServiceTest extends HttpTest {
    private FmpService<FmpCashFlowStatement> service;

    @BeforeEach
    void setup() {
        service = new FmpBulkCashFlowStatementService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/cash-flow-statement-bulk", relativeUrl);
    }

    @Test
    void required_params() {
        // given // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of(PARAM_YEAR, FmpYear.class, PARAM_PERIOD, FmpPeriod.class), params);
    }

    @Test
    void optional_params() {
        // given // when
        var params = service.optionalParams();

        // then
        assertEquals(Map.of(), params);
    }

    @ParameterizedTest
    @ValueSource(strings = {"annual"})
    void successful_download_with_required_params(String periodString) {
        // given
        var year = year("2023");
        var period = period(periodString);
        service.param(PARAM_YEAR, year);
        service.param(PARAM_PERIOD, period);
        httpStub.configureResponse()
                .body(testResource("stable/cash-flow-statement-bulk/?year=%s&period=%s.csv", year, period))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(2, result.size());
        result.forEach(TestUtils::assertAllFieldsNonNull);
    }
}

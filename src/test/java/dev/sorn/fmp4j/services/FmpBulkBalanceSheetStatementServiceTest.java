package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.types.FmpPeriod.period;
import static dev.sorn.fmp4j.types.FmpYear.year;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_PERIOD;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_YEAR;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.BalanceSheetStatementTestData;
import dev.sorn.fmp4j.models.FmpBalanceSheetStatement;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpYear;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FmpBulkBalanceSheetStatementServiceTest extends HttpTest implements BalanceSheetStatementTestData {
    private FmpService<FmpBalanceSheetStatement> service;

    @BeforeEach
    void setup() {
        service = new FmpBulkBalanceSheetStatementService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/balance-sheet-statement-bulk", relativeUrl);
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
    @ValueSource(strings = {"quarter"})
    void successful_download_with_required_params(String periodString) {
        // given
        var year = year("2023");
        var period = period(periodString);
        service.param(PARAM_YEAR, year);
        service.param(PARAM_PERIOD, period);
        httpStub.configureResponse()
                .body(testResource("stable/balance-sheet-statement-bulk/?year=%s&period=%s.csv", year, period))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(2, result.size());
        range(0, result.size()).forEach(i -> assertAllFieldsNonNull(result.get(i)));
    }
}

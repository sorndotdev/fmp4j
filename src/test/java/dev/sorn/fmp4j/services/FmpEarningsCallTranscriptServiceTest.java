package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.types.FmpQuarter.quarter;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static dev.sorn.fmp4j.types.FmpYear.year;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_QUARTER;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_YEAR;
import static java.util.Collections.emptySet;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.models.FmpEarningsCallTranscript;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpQuarter;
import dev.sorn.fmp4j.types.FmpSymbol;
import dev.sorn.fmp4j.types.FmpYear;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpEarningsCallTranscriptServiceTest extends HttpTest {
    private FmpService<FmpEarningsCallTranscript> service;

    @BeforeEach
    void setup() {
        service = new FmpEarningsCallTranscriptService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/earning-call-transcript", relativeUrl);
    }

    @Test
    void required_params() {
        // given // when
        var params = service.requiredParams();

        // then
        assertEquals(
                Map.of(
                        PARAM_SYMBOL, FmpSymbol.class,
                        PARAM_YEAR, FmpYear.class,
                        PARAM_QUARTER, FmpQuarter.class),
                params);
    }

    @Test
    void optional_params() {
        // given // when
        var params = service.optionalParams();

        // then
        assertEquals(Map.of(PARAM_LIMIT, FmpLimit.class), params);
    }

    @Test
    void successful_download() {
        // given
        var symbol = symbol("AAPL");
        var year = year(2020);
        var quarter = quarter(3);
        service.param(PARAM_SYMBOL, symbol);
        service.param(PARAM_YEAR, year);
        service.param(PARAM_QUARTER, quarter);
        httpStub.configureResponse()
                .body(testResource(
                        "stable/earning-call-transcript/?symbol=%s&year=%s&quarter=%s.json", symbol, year, quarter))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(1, result.size());
        range(0, 1).forEach(i -> assertInstanceOf(FmpEarningsCallTranscript.class, result.get(i)));
        range(0, 1).forEach(i -> assertAllFieldsNonNull(result.get(i), emptySet()));
    }
}

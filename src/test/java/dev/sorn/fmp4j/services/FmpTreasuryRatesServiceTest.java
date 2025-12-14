package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_FROM;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_TO;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.models.FmpTreasuryRate;
import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpTreasuryRatesServiceTest extends HttpTest {
    private FmpService<FmpTreasuryRate> service;

    @BeforeEach
    void setup() {
        service = new FmpTreasuryRatesService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/treasury-rates", relativeUrl);
    }

    @Test
    void required_params() {
        // given // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of(PARAM_FROM, LocalDate.class, PARAM_TO, LocalDate.class), params);
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
        var from = LocalDate.parse("2024-12-30");
        var to = LocalDate.parse("2025-01-01");
        service.param(PARAM_FROM, from);
        service.param(PARAM_TO, to);
        httpStub.configureResponse()
                .body(testResource("stable/treasury-rates/?from=%s&to=%s.json", from, to))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(2, result.size());
        range(0, 2).forEach(i -> assertInstanceOf(FmpTreasuryRate.class, result.get(i)));
        range(0, 2).forEach(i -> assertAllFieldsNonNull(result.get(i)));
    }
}

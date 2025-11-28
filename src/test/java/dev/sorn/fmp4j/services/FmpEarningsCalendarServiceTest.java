package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.models.FmpEarningsCalendar;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpEarningsCalendarServiceTest extends HttpTest {
    private FmpService<FmpEarningsCalendar[]> service;

    @BeforeEach
    void setup() {
        service = new FmpEarningsCalendarService(config, client);
    }

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/earnings-calendar", relativeUrl);
    }

    @Test
    void required_params() {
        // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of(), params);
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
        httpStub.configureResponse()
                .body(testResource("stable/earnings-calendar/excerpt.json"))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(4, result.length);
        range(0, 4).forEach(i -> assertInstanceOf(FmpEarningsCalendar.class, result[i]));
        range(0, 4)
                .forEach(i -> assertAllFieldsNonNull(
                        result[i], Set.of("epsActual", "epsEstimated", "revenueActual", "revenueEstimated")));
    }
}

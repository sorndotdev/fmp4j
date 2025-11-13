package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.HttpClientStub.httpClientStub;
import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.jsonTestResource;
import static dev.sorn.fmp4j.json.FmpJsonDeserializer.FMP_JSON_DESERIALIZER;
import static java.util.Collections.emptySet;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.HttpClientStub;
import dev.sorn.fmp4j.cfg.FmpConfigImpl;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.http.FmpHttpClientImpl;
import dev.sorn.fmp4j.models.FmpEarningsCalendar;
import java.util.Set;
import org.junit.jupiter.api.Test;

class FmpEarningsCalendarServiceTest {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);
    private final FmpService<FmpEarningsCalendar[]> service = new FmpEarningsCalendarService(new FmpConfigImpl(), http);

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
        assertEquals(emptySet(), params);
    }

    @Test
    void optional_params() {
        // when
        var params = service.optionalParams();

        // then
        assertEquals(emptySet(), params);
    }

    @Test
    void successful_download() {
        // given
        httpStub.configureResponse()
                .body(jsonTestResource("stable/earnings-calendar/excerpt.json"))
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

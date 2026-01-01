package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.TestUtils.assertAllFieldsNonNull;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import com.marketdataapi.fmp4j.models.FmpSplitsCalendar;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpSplitsCalendarServiceTest extends HttpTest {
    private FmpService<FmpSplitsCalendar> service;

    @BeforeEach
    void setup() {
        service = new FmpSplitsCalendarService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/splits-calendar", relativeUrl);
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
        String jsonResponse =
                "[{\"symbol\":\"NVDA\",\"date\":\"2024-06-10\",\"numerator\":10,\"denominator\":1,\"label\":\"NVDA split: 10 for 1\"},{\"symbol\":\"TSLA\",\"date\":\"2022-08-25\",\"numerator\":3,\"denominator\":1,\"label\":\"TSLA split: 3 for 1\"}]";
        httpStub.configureResponse().body(jsonResponse).statusCode(200).apply();

        // when
        var result = service.download();

        // then
        assertEquals(2, result.size());
        range(0, 2).forEach(i -> assertInstanceOf(FmpSplitsCalendar.class, result.get(i)));
        range(0, 2).forEach(i -> assertAllFieldsNonNull(result.get(i), Set.of()));
        assertEquals(symbol("NVDA"), result.get(0).symbol());
        assertEquals(10, result.get(0).numerator());
        assertEquals(1, result.get(0).denominator());
    }
}

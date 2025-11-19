package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.HttpClientStub.httpClientStub;
import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.json.FmpJsonDeserializer.FMP_JSON_DESERIALIZER;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.HttpClientStub;
import dev.sorn.fmp4j.cfg.FmpConfigImpl;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.http.FmpHttpClientImpl;
import dev.sorn.fmp4j.models.FmpSplitsCalendar;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

class FmpSplitsCalendarServiceTest {
    private final HttpClientStub httpStub = httpClientStub();
    private final FmpHttpClient http = new FmpHttpClientImpl(httpStub, FMP_JSON_DESERIALIZER);
    private final FmpService<FmpSplitsCalendar[]> service = new FmpSplitsCalendarService(new FmpConfigImpl(), http);

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/splits-calendar", relativeUrl);
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
        String jsonResponse =
                "[{\"symbol\":\"NVDA\",\"date\":\"2024-06-10\",\"numerator\":10,\"denominator\":1,\"label\":\"NVDA split: 10 for 1\"},{\"symbol\":\"TSLA\",\"date\":\"2022-08-25\",\"numerator\":3,\"denominator\":1,\"label\":\"TSLA split: 3 for 1\"}]";
        httpStub.configureResponse().body(jsonResponse).statusCode(200).apply();

        // when
        var result = service.download();

        // then
        assertEquals(2, result.length);
        range(0, 2).forEach(i -> assertInstanceOf(FmpSplitsCalendar.class, result[i]));
        range(0, 2).forEach(i -> assertAllFieldsNonNull(result[i], Set.of()));
        assertEquals(symbol("NVDA"), result[0].symbol());
        assertEquals(10, result[0].numerator());
        assertEquals(1, result[0].denominator());
    }
}

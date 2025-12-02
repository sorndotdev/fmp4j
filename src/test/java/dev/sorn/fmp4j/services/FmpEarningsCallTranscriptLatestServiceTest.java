package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.types.FmpLimit.limit;
import static dev.sorn.fmp4j.types.FmpPage.page;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_PAGE;
import static java.util.Collections.emptySet;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import dev.sorn.fmp4j.models.FmpEarningsCallTranscriptLatest;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPage;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpEarningsCallTranscriptLatestServiceTest extends HttpTest {
    private FmpService<FmpEarningsCallTranscriptLatest[]> service;

    @BeforeEach
    void setup() {
        service = new FmpEarningsCallTranscriptLatestService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/earning-call-transcript-latest", relativeUrl);
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
        assertEquals(Map.of(PARAM_LIMIT, FmpLimit.class, PARAM_PAGE, FmpPage.class), params);
    }

    @Test
    void successful_download() {
        // given
        var page = page(0);
        var limit = limit(2);
        service.param(PARAM_PAGE, page);
        service.param(PARAM_LIMIT, limit);
        httpStub.configureResponse()
                .body(testResource("stable/earning-call-transcript-latest/?page=%s&limit=%s.json", page, limit))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(2, result.length);
        range(0, 2).forEach(i -> assertInstanceOf(FmpEarningsCallTranscriptLatest.class, result[i]));
        range(0, 2).forEach(i -> assertAllFieldsNonNull(result[i], emptySet()));
    }
}

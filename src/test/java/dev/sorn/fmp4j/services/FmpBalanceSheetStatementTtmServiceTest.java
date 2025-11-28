package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.BalanceSheetStatementTestData;
import dev.sorn.fmp4j.models.FmpBalanceSheetStatement;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpBalanceSheetStatementTtmServiceTest extends HttpTest implements BalanceSheetStatementTestData {
    private FmpService<FmpBalanceSheetStatement[]> service;

    @BeforeEach
    void setup() {
        service = new FmpBalanceSheetStatementTtmService(config, client);
    }

    @Test
    void relative_url() {
        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/balance-sheet-statement-ttm", relativeUrl);
    }

    @Test
    void required_params() {
        // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of("symbol", FmpSymbol.class), params);
    }

    @Test
    void optional_params() {
        // when
        var params = service.optionalParams();

        // then
        assertEquals(Map.of("limit", FmpLimit.class), params);
    }

    @Test
    void successful_download() {
        // given
        var symbol = symbol("AAPL");
        var limit = 2;
        service.param("symbol", symbol);
        httpStub.configureResponse()
                .body(testResource("stable/balance-sheet-statement-ttm/?symbol=%s&limit=%d.json", symbol, limit))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(limit, result.length);
        range(0, limit).forEach(i -> assertAllFieldsNonNull(result[i], Set.of("capitalLeaseObligationsNonCurrent")));
    }
}

package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.TestUtils.assertAllFieldsNonNull;
import static dev.sorn.fmp4j.TestUtils.testResource;
import static dev.sorn.fmp4j.types.FmpLimit.limit;
import static dev.sorn.fmp4j.types.FmpPage.page;
import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_FROM;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_PAGE;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_TO;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.FinancialGrowthTestData;
import dev.sorn.fmp4j.models.FmpSecFilingsSearchBySymbol;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPage;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpSecFilingsSearchBySymbolServiceTest extends HttpTest implements FinancialGrowthTestData {
    private FmpService<FmpSecFilingsSearchBySymbol> service;

    @BeforeEach
    void setup() {
        service = new FmpSecFilingsSearchBySymbolService(config, client);
    }

    @Test
    void relative_url() {
        // given // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/sec-filings-search/symbol", relativeUrl);
    }

    @Test
    void required_params() {
        // given // when
        var params = service.requiredParams();

        // then
        assertEquals(
                Map.of(PARAM_SYMBOL, FmpSymbol.class, PARAM_FROM, LocalDate.class, PARAM_TO, LocalDate.class), params);
    }

    @Test
    void optional_params() {
        // given // when
        var params = service.optionalParams();

        // then
        assertEquals(Map.of(PARAM_PAGE, FmpPage.class, PARAM_LIMIT, FmpLimit.class), params);
    }

    @Test
    void successful_download() {
        // given
        var symbol = symbol("AAPL");
        var from = LocalDate.parse("2024-01-01");
        var to = LocalDate.parse("2025-01-01");
        var page = page(0);
        var limit = limit(2);
        service.param(PARAM_SYMBOL, symbol);
        service.param(PARAM_FROM, from);
        service.param(PARAM_TO, to);
        service.param(PARAM_PAGE, page);
        service.param(PARAM_LIMIT, limit);
        httpStub.configureResponse()
                .body(testResource(
                        "stable/sec-filings-search/symbol/?symbol=%s&from=%s&to=%s&page=%d&limit=%d.json",
                        symbol, from, to, page.value(), limit.value()))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(limit.value(), result.size());
        range(0, limit.value()).forEach(i -> assertAllFieldsNonNull(result.get(i)));
    }
}

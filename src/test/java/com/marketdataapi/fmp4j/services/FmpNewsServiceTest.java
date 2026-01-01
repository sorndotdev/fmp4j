package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.TestUtils.assertAllFieldsNonNull;
import static com.marketdataapi.fmp4j.TestUtils.testResource;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_FROM;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_PAGE;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOLS;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_TO;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.marketdataapi.fmp4j.NewsTestData;
import com.marketdataapi.fmp4j.types.FmpLimit;
import com.marketdataapi.fmp4j.types.FmpPage;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class FmpNewsServiceTest extends HttpTest implements NewsTestData {

    @ParameterizedTest
    @ValueSource(strings = {"crypto", "forex", "stock"})
    void relative_url(String type) {
        // given
        var service = new FmpNewsService(config, client, type);

        // when
        var relativeUrl = service.relativeUrl();

        // then
        assertEquals("/news/" + type, relativeUrl);
    }

    @ParameterizedTest
    @ValueSource(strings = {"crypto", "forex", "stock"})
    void required_params(String path) {
        // given
        var service = new FmpNewsService(config, client, path);

        // when
        var params = service.requiredParams();

        // then
        assertEquals(Map.of(PARAM_SYMBOLS, FmpSymbol.class), params);
    }

    @ParameterizedTest
    @ValueSource(strings = {"crypto", "forex", "stock"})
    void optional_params(String type) {
        // given
        var service = new FmpNewsService(config, client, type);

        // when
        var params = service.optionalParams();

        // then
        assertEquals(
                Map.of(
                        PARAM_FROM, LocalDate.class,
                        PARAM_TO, LocalDate.class,
                        PARAM_PAGE, FmpPage.class,
                        PARAM_LIMIT, FmpLimit.class),
                params);
    }

    @ParameterizedTest
    @CsvSource({
        "crypto,BTCUSD",
        "forex,EURUSD",
        "stock,AAPL",
    })
    void successful_download(String type, FmpSymbol symbol) {
        // given
        var service = new FmpNewsService(config, client, type);
        service.param(PARAM_SYMBOLS, symbol);
        httpStub.configureResponse()
                .body(testResource("stable/news/%s/?symbols=%s.json", type, symbol))
                .statusCode(200)
                .apply();

        // when
        var result = service.download();

        // then
        assertEquals(2, result.size());
        range(0, 2).forEach(i -> assertAllFieldsNonNull(result.get(i)));
    }
}

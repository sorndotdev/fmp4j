package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_FROM;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_PAGE;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_TO;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.types.FmpApiKey;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPage;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class FmpServiceTest {

    @Mock
    private FmpConfig mockConfig;

    @Mock
    private FmpHttpClient mockHttpClient;

    private ConcreteFmpService service;

    private MultiRequiredFmpService multiRequiredService;

    @BeforeEach
    void setup() {
        openMocks(this);
        when(mockConfig.fmpApiKey()).thenReturn(new FmpApiKey("abcdefghij1234567890abcdefghij12"));
        service = new ConcreteFmpService(mockConfig, mockHttpClient);

        multiRequiredService = new MultiRequiredFmpService(mockConfig, mockHttpClient);
    }

    @Test
    void should_accept_list_with_correct_type() {
        // given
        List<FmpSymbol> symbols = List.of(symbol("AAPL"), symbol("GOOGL"), symbol("MSFT"));

        // when // then
        assertDoesNotThrow(() -> service.param(PARAM_SYMBOL, symbols));
        assertEquals(symbols, service.params.get(PARAM_SYMBOL));
    }

    @Test
    void accepts_set_with_correct_type() {
        // given
        Set<FmpSymbol> symbols = Set.of(symbol("AAPL"), symbol("APPL"), symbol("MSFT"));

        // when // then
        assertDoesNotThrow(() -> service.param(PARAM_SYMBOL, symbols));
        assertEquals(symbols, service.params.get(PARAM_SYMBOL));
    }

    @Test
    void rejects_collection_with_incorrect_type() {
        // given
        List<Integer> invalidList = Arrays.asList(1, 2, 3);

        // when
        FmpServiceException exception =
                assertThrows(FmpServiceException.class, () -> service.param(PARAM_SYMBOL, invalidList));

        // then
        assertTrue(exception.getMessage().contains("Integer"));
        assertTrue(exception.getMessage().contains("not a valid type"));
        assertTrue(exception.getMessage().contains("FmpSymbol"));
    }

    @Test
    void handles_empty_collection() {
        // given // when // then
        assertDoesNotThrow(() -> service.param(PARAM_SYMBOL, List.of()));
        assertEquals(List.of(), service.params.get(PARAM_SYMBOL));
    }

    @Test
    void accepts_optional_with_correct_type() {
        // given
        Optional<FmpSymbol> optionalSymbol = Optional.of(symbol("AAPL"));

        // when // then
        assertDoesNotThrow(() -> service.param(PARAM_SYMBOL, optionalSymbol));
        assertEquals(optionalSymbol, service.params.get(PARAM_SYMBOL));
    }

    @Test
    void rejects_optional_with_incorrect_type() {
        // given
        Optional<Integer> invalidOptional = Optional.of(123);

        // when
        FmpServiceException exception =
                assertThrows(FmpServiceException.class, () -> service.param(PARAM_SYMBOL, invalidOptional));

        // then
        assertTrue(exception.getMessage().contains("Integer"));
        assertTrue(exception.getMessage().contains("not a valid type"));
        assertTrue(exception.getMessage().contains("FmpSymbol"));
    }

    @Test
    void handles_empty_optional() {
        // given
        Optional<String> emptyOptional = Optional.empty();

        // when // then
        assertDoesNotThrow(() -> service.param(PARAM_SYMBOL, emptyOptional));
        assertEquals(emptyOptional, service.params.get(PARAM_SYMBOL));
    }

    @Test
    void rejects_invalid_type_for_optional_param() {
        assertThrows(FmpServiceException.class, () -> service.param(PARAM_FROM, "2025-01-01"));
    }

    @Test
    void handles_null_key() {
        assertThrows(NullPointerException.class, () -> service.param(null, "value"));
    }

    @Test
    void validates_nested_collections() {
        // given
        List<List<String>> nestedList = Arrays.asList(Arrays.asList("AAPL", "GOOGL"));

        // when // then
        assertThrows(FmpServiceException.class, () -> service.param(PARAM_SYMBOL, nestedList));
    }

    @Test
    void should_show_all_missing_required_params() {
        // given // when
        FmpServiceException thrownEx = assertThrows(FmpServiceException.class, multiRequiredService::download);
        var msg = thrownEx.getMessage();
        // then
        assertTrue(
                msg.contains(PARAM_LIMIT) || msg.contains(PARAM_PAGE),
                "Expected exception message to mention either 'limit' and 'page' as the required param, but got: "
                        + msg);
    }

    @Test
    void should_not_throw_missing_required_params() {
        // given
        multiRequiredService.param(PARAM_PAGE, FmpPage.page(10));
        multiRequiredService.param(PARAM_LIMIT, FmpLimit.limit(10));

        // when // then
        assertDoesNotThrow(multiRequiredService::download);
    }

    // Concrete implementation for testing
    private static class ConcreteFmpService extends FmpService<String> {
        public ConcreteFmpService(FmpConfig cfg, FmpHttpClient http) {
            super(cfg, http, new TypeReference<String>() {});
        }

        @Override
        protected Map<String, Class<?>> requiredParams() {
            return Map.of(PARAM_SYMBOL, FmpSymbol.class);
        }

        @Override
        protected Map<String, Class<?>> optionalParams() {
            return Map.of(PARAM_FROM, LocalDate.class, PARAM_TO, LocalDate.class);
        }

        @Override
        protected String relativeUrl() {
            return "/test/endpoint";
        }
    }

    private static class MultiRequiredFmpService extends FmpService<String> {
        public MultiRequiredFmpService(FmpConfig cfg, FmpHttpClient http) {
            super(cfg, http, new TypeReference<String>() {});
        }

        @Override
        protected Map<String, Class<?>> requiredParams() {
            return Map.of(PARAM_LIMIT, FmpLimit.class, PARAM_PAGE, FmpPage.class);
        }

        @Override
        protected Map<String, Class<?>> optionalParams() {
            return Map.of();
        }

        @Override
        protected String relativeUrl() {
            return "/test/endpoint";
        }
    }
}

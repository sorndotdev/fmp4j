package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.types.FmpSymbol.symbol;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.types.FmpApiKey;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class FmpServiceTest {

    @Mock
    private FmpConfig mockConfig;

    @Mock
    private FmpHttpClient mockHttpClient;

    private ConcreteFmpService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockConfig.fmpApiKey()).thenReturn(new FmpApiKey("abcdefghij1234567890abcdefghij12"));
        service = new ConcreteFmpService(mockConfig, mockHttpClient);
    }

    @Test
    @DisplayName("Should accept List with correct element type")
    void shouldAcceptListWithCorrectType() {
        // given
        List<FmpSymbol> symbols = List.of(symbol("AAPL"), symbol("GOOGL"), symbol("MSFT"));

        // then
        assertDoesNotThrow(() -> service.param("symbol", symbols));
        assertEquals(symbols, service.params.get("symbol"));
    }

    @Test
    @DisplayName("Should accept Set with correct element type")
    void shouldAcceptSetWithCorrectType() {
        // given
        Set<FmpSymbol> symbols = Set.of(symbol("AAPL"), symbol("APPL"), symbol("MSFT"));

        // then
        assertDoesNotThrow(() -> service.param("symbol", symbols));
        assertEquals(symbols, service.params.get("symbol"));
    }

    @Test
    @DisplayName("Should reject Collection with incorrect element type")
    void shouldRejectCollectionWithIncorrectType() {
        // given
        List<Integer> invalidList = Arrays.asList(1, 2, 3);

        // when
        FmpServiceException exception =
                assertThrows(FmpServiceException.class, () -> service.param("symbol", invalidList));

        // then
        assertTrue(exception.getMessage().contains("Integer"));
        assertTrue(exception.getMessage().contains("not a valid type"));
        assertTrue(exception.getMessage().contains("FmpSymbol"));
    }

    @Test
    @DisplayName("Should handle empty Collection gracefully")
    void shouldHandleEmptyCollection() {
        // given when then
        assertDoesNotThrow(() -> service.param("symbol", List.of()));
        assertEquals(List.of(), service.params.get("symbol"));
    }

    @Test
    @DisplayName("Should accept Optional with correct type")
    void shouldAcceptOptionalWithCorrectType() {
        // given
        Optional<FmpSymbol> optionalSymbol = Optional.of(symbol("AAPL"));

        // then
        assertDoesNotThrow(() -> service.param("symbol", optionalSymbol));
        assertEquals(optionalSymbol, service.params.get("symbol"));
    }

    @Test
    @DisplayName("Should reject Optional with incorrect type")
    void shouldRejectOptionalWithIncorrectType() {
        // given
        Optional<Integer> invalidOptional = Optional.of(123);

        // when
        FmpServiceException exception =
                assertThrows(FmpServiceException.class, () -> service.param("symbol", invalidOptional));

        // then
        assertTrue(exception.getMessage().contains("Integer"));
        assertTrue(exception.getMessage().contains("not a valid type"));
        assertTrue(exception.getMessage().contains("FmpSymbol"));
    }

    @Test
    @DisplayName("Should handle empty Optional gracefully")
    void shouldHandleEmptyOptional() {
        // given
        Optional<String> emptyOptional = Optional.empty();

        // when then
        assertDoesNotThrow(() -> service.param("symbol", emptyOptional));
        assertEquals(emptyOptional, service.params.get("symbol"));
    }

    @Test
    @DisplayName("Should reject invalid type for optional parameter")
    void shouldRejectInvalidTypeForOptionalParam() {
        assertThrows(FmpServiceException.class, () -> service.param("from", "2025-01-01"));
    }

    @Test
    @DisplayName("Should throw NPE for null key")
    void shouldHandleNullKey() {
        assertThrows(NullPointerException.class, () -> service.param(null, "value"));
    }

    @Test
    @DisplayName("Should reject nested Collection")
    void shouldValidateNestedCollections() {
        // given a nested list
        List<List<String>> nestedList = Arrays.asList(Arrays.asList("AAPL", "GOOGL"));

        // when then
        assertThrows(FmpServiceException.class, () -> service.param("symbol", nestedList));
    }

    // Concrete implementation for testing
    private static class ConcreteFmpService extends FmpService<String> {
        public ConcreteFmpService(FmpConfig cfg, FmpHttpClient http) {
            super(cfg, http, new TypeReference<String>() {});
        }

        @Override
        protected Map<String, Class<?>> requiredParams() {
            return Map.of("symbol", FmpSymbol.class);
        }

        @Override
        protected Map<String, Class<?>> optionalParams() {
            return Map.of("from", LocalDate.class, "to", LocalDate.class);
        }

        @Override
        protected String relativeUrl() {
            return "/test/endpoint";
        }
    }
}

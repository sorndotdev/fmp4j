package com.marketdataapi.fmp4j.http;

import static com.marketdataapi.fmp4j.http.FmpUriUtils.uri;
import static com.marketdataapi.fmp4j.http.FmpUriUtils.uriWithParams;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.Map;
import org.junit.jupiter.api.Test;

class FmpUriUtilsTest {
    @Test
    void uri_with_params_single_param() {
        // given
        var uri = uri("https://example.com");
        var params = Map.<String, Object>of("param", 42);

        // when
        var uriWithParams = uriWithParams(uri, params);

        // then
        assertEquals("https://example.com?param=42", uriWithParams.toString());
    }

    @Test
    void uri_with_params_multiple_params() {
        // given
        var uri = uri("https://example.com");
        var params = Map.<String, Object>of("param", 42);

        // when
        var uriWithParams = uriWithParams(uri, params);

        // then
        assertEquals("https://example.com?param=42", uriWithParams.toString());
    }

    @Test
    void uri_with_params_null_params() {
        // given
        var uri = uri("https://example.com");
        var params = (Map<String, Object>) null;

        // when
        var uriWithParams = uriWithParams(uri, params);

        // then
        assertEquals("https://example.com", uriWithParams.toString());
    }

    @Test
    void uri_with_params_empty_params() {
        // given
        var uri = uri("https://example.com");
        var params = Map.<String, Object>of();

        // when
        var uriWithParams = uriWithParams(uri, params);

        // then
        assertEquals("https://example.com", uriWithParams.toString());
    }

    @Test
    void uri_with_params_illegal_query_key_throws_fmp_http_exception() {
        var uri = URI.create("https:/.com");
        var params = Map.<String, Object>of("param", 42);

        FmpHttpException ex = assertThrows(FmpHttpException.class, () -> {
            FmpUriUtils.uriWithParams(uri, params);
        });

        assertTrue(ex.getMessage().contains("Failed to build URI"));
    }

    @Test
    void private_constructor_throws_assertion_error() throws Exception {
        Constructor<FmpUriUtils> constructor = FmpUriUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException thrown = assertThrows(InvocationTargetException.class, constructor::newInstance);

        Throwable cause = thrown.getCause();
        assertInstanceOf(AssertionError.class, cause);
        assertTrue(cause.getMessage().contains("FmpUriUtils cannot be instantiated"));
    }
}

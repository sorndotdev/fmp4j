package dev.sorn.fmp4j.http;

import static dev.sorn.fmp4j.http.FmpUriUtils.uri;
import static dev.sorn.fmp4j.http.FmpUriUtils.uriWithParams;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.Test;

class FmpUriUtilsTest {
    @Test
    void uriWithParams_single_param() {
        // given
        var uri = uri("https://example.com");
        var params = Map.<String, Object>of("param", 42);

        // when
        var uriWithParams = uriWithParams(uri, params);

        // then
        assertEquals("https://example.com?param=42", uriWithParams.toString());
    }

    @Test
    void uriWithParams_multiple_params() {
        // given
        var uri = uri("https://example.com");
        var params = Map.<String, Object>of("param", 42);

        // when
        var uriWithParams = uriWithParams(uri, params);

        // then
        assertEquals("https://example.com?param=42", uriWithParams.toString());
    }

    @Test
    void uriWithParams_null_params() {
        // given
        var uri = uri("https://example.com");
        var params = (Map<String, Object>) null;

        // when
        var uriWithParams = uriWithParams(uri, params);

        // then
        assertEquals("https://example.com", uriWithParams.toString());
    }

    @Test
    void uriWithParams_empty_params() {
        // given
        var uri = uri("https://example.com");
        var params = Map.<String, Object>of();

        // when
        var uriWithParams = uriWithParams(uri, params);

        // then
        assertEquals("https://example.com", uriWithParams.toString());
    }
}

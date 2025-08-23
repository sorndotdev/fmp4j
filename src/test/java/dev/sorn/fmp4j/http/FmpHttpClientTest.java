package dev.sorn.fmp4j.http;

import static dev.sorn.fmp4j.json.FmpJsonDeserializerImpl.FMP_JSON_DESERIALIZER;
import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.sorn.fmp4j.TestObject;
import dev.sorn.fmp4j.TestObjectValue;
import dev.sorn.fmp4j.json.FmpJsonDeserializer;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FmpHttpClientTest {
    private final HttpClient httpClient = mock(HttpClient.class);
    private final FmpJsonDeserializer deserializer = FMP_JSON_DESERIALIZER;
    private final ClassicHttpResponse httpResponse = mock(ClassicHttpResponse.class);
    private final URI testUri = URI.create("https://financialmodelingprep.com/stable");
    private FmpHttpClientImpl client;

    @BeforeEach
    void setUp() {
        client = new FmpHttpClientImpl(httpClient, deserializer);
    }

    @Test
    void get_successful_request_object() throws Exception {
        // given
        var headers = Map.<String, String>of("Authorization", "Bearer token");
        var params = Map.<String, Object>of("param1", "value1");
        var jsonResponse =
                """
            {
                "key": "fmp4j",
                "object": {
                    "value": 42
                }
            }
            """
                        .replaceAll("\\s", "");
        var expected = new TestObject("fmp4j", new TestObjectValue(42));
        when(httpClient.executeOpen(any(), any(HttpGet.class), any())).thenReturn(httpResponse);
        when(httpResponse.getEntity()).thenReturn(new StringEntity(jsonResponse));

        // when
        var result = client.get(typeRef(TestObject.class), testUri, headers, params);

        // then
        assertNotNull(result);
        assertEquals(expected, result);
        verify(httpResponse).close();
    }

    @Test
    void get_successful_request_array() throws IOException {
        // given
        var headers = Map.<String, String>of("Authorization", "Bearer token");
        var params = Map.<String, Object>of("param1", "value1");
        var jsonResponse =
                """
            [
                {
                    "key": "28",
                    "object": {
                        "value": 28
                    }
                },
                {
                    "key": "42",
                    "object": {
                        "value": 42
                    }
                }
            ]
            """
                        .replaceAll("\\s", "");
        var expected = new TestObject[] {
            new TestObject("28", new TestObjectValue(28)), new TestObject("42", new TestObjectValue(42)),
        };
        when(httpClient.executeOpen(any(), any(HttpGet.class), any())).thenReturn(httpResponse);
        when(httpResponse.getEntity()).thenReturn(new StringEntity(jsonResponse));

        // when
        var result = client.get(typeRef(TestObject[].class), testUri, headers, params);

        // then
        assertEquals(2, result.length);
        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);
    }

    @Test
    void get_handles_execution_exception() throws Exception {
        // given
        var params = Map.<String, Object>of("param", "value");
        when(httpClient.executeOpen(any(), any(HttpGet.class), any())).thenThrow(new IOException("Connection failed"));

        // when // then
        var e = assertThrows(
                FmpHttpException.class, () -> client.get(typeRef(TestObject[].class), testUri, null, params));
        assertEquals("HTTP request failed: https://financialmodelingprep.com/stable", e.getMessage());
    }

    @Test
    void get_handles_entity_exception() throws Exception {
        // given
        when(httpClient.executeOpen(any(), any(HttpGet.class), any())).thenReturn(httpResponse);
        when(httpResponse.getEntity()).thenThrow(new RuntimeException("Invalid entity"));

        // when // then
        var e = assertThrows(
                FmpHttpException.class, () -> client.get(typeRef(TestObject[].class), testUri, null, null));
        assertEquals("HTTP request failed: https://financialmodelingprep.com/stable", e.getMessage());
    }
}

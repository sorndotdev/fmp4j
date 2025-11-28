package dev.sorn.fmp4j.http;

import static dev.sorn.fmp4j.TestDeserializationRegistry.TEST_DESERIALIZATION_REGISTRY;
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
import dev.sorn.fmp4j.types.FmpApiKey;
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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FmpHttpClientTest {
    private final HttpClient httpClient = mock(HttpClient.class);
    private final ClassicHttpResponse httpResponse = mock(ClassicHttpResponse.class);
    private final URI testUri = URI.create("https://financialmodelingprep.com/stable");
    private FmpHttpClientImpl client;

    @BeforeEach
    void setUp() {
        client = new FmpHttpClientImpl(httpClient, TEST_DESERIALIZATION_REGISTRY);
    }

    @Test
    void get_successful_request_object() throws Exception {
        // given
        var headers = Map.of("Authorization", "Bearer token", "Content-Type", "application/json");
        var params = Map.<String, Object>of("apikey", new FmpApiKey("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6"));
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
        when(httpResponse.getCode()).thenReturn(200);

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
        var headers = Map.of("some", "header", "Content-Type", "application/json");
        var params = Map.<String, Object>of("apikey", new FmpApiKey("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6"));
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
        when(httpResponse.getCode()).thenReturn(200);

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
        var params = Map.<String, Object>of("apikey", new FmpApiKey("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6"));
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

    @ParameterizedTest
    @ValueSource(ints = {401, 403})
    void throws_unauthorized_for_code(int code) throws IOException {
        // given
        var headers = Map.of("some", "header");
        var params = Map.<String, Object>of("apikey", new FmpApiKey("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6"));

        // when
        when(httpResponse.getCode()).thenReturn(code);
        when(httpClient.executeOpen(any(), any(HttpGet.class), any())).thenReturn(httpResponse);
        when(httpResponse.getEntity()).thenReturn(new StringEntity("{}"));

        // then
        var e = assertThrows(
                FmpUnauthorizedException.class,
                () -> client.get(typeRef(TestObject[].class), testUri, headers, params));
        assertEquals(
                "Unauthorized for type [class [Ldev.sorn.fmp4j.TestObject;], uri [https://financialmodelingprep.com/stable], headers [{some=header}], queryParams [{apikey=AB****************************y6}]",
                e.getMessage());
    }

    @Test
    void json_deserialization_of_wrong_type_throws_correct_exception() throws IOException {
        // given
        var code = 200;
        var res = "{\"wrong\": \"type\"}";
        var headers = Map.of("Content-Type", "application/json");
        var params = Map.<String, Object>of("apikey", new FmpApiKey("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6"));

        // when
        when(httpResponse.getCode()).thenReturn(code);
        when(httpClient.executeOpen(any(), any(HttpGet.class), any())).thenReturn(httpResponse);
        when(httpResponse.getEntity()).thenReturn(new StringEntity(res));

        // then
        var e = assertThrows(
                FmpHttpException.class, () -> client.get(typeRef(TestObject[].class), testUri, headers, params));
        assertEquals(
                "Deserialization failed for type [class [Ldev.sorn.fmp4j.TestObject;], uri [https://financialmodelingprep.com/stable], headers [{Content-Type=application/json}], queryParams [{apikey=AB****************************y6}]",
                e.getMessage());
    }

    @Test
    void csv_deserialization_of_wrong_type_throws_correct_exception() throws IOException {
        // given
        var code = 200;
        var res = "wrong,type\n1,2,3";
        var headers = Map.of("Content-Type", "text/csv");
        var params = Map.<String, Object>of("apikey", new FmpApiKey("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6"));

        // when
        when(httpResponse.getCode()).thenReturn(code);
        when(httpClient.executeOpen(any(), any(HttpGet.class), any())).thenReturn(httpResponse);
        when(httpResponse.getEntity()).thenReturn(new StringEntity(res));

        // then
        var e = assertThrows(
                FmpHttpException.class, () -> client.get(typeRef(TestObject[].class), testUri, headers, params));
        assertEquals(
                "Deserialization failed for type [class [Ldev.sorn.fmp4j.TestObject;], uri [https://financialmodelingprep.com/stable], headers [{Content-Type=text/csv}], queryParams [{apikey=AB****************************y6}]",
                e.getMessage());
    }
}

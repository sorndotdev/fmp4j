package dev.sorn.fmp4j.http;

import static dev.sorn.fmp4j.http.FmpUriUtils.uriWithParams;
import static dev.sorn.fmp4j.json.FmpJsonDeserializerImpl.FMP_JSON_DESERIALIZER;
import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sorn.fmp4j.json.FmpJsonDeserializer;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class FmpHttpClientImpl implements FmpHttpClient {
    public static final FmpHttpClient FMP_HTTP_CLIENT = new FmpHttpClientImpl();
    private final HttpClient http;
    private final FmpJsonDeserializer deserializer;

    private FmpHttpClientImpl() {
        this(HttpClients.createDefault(), FMP_JSON_DESERIALIZER);
    }

    public FmpHttpClientImpl(HttpClient httpClient, FmpJsonDeserializer deserializer) {
        this.http = requireNonNull(httpClient, "'httpClient' is required");
        this.deserializer = requireNonNull(deserializer, "'deserializer' is required");
    }

    @Override
    public <T> T get(TypeReference<T> type, URI uri, Map<String, String> headers, Map<String, Object> queryParams) {
        try {
            HttpGet request = buildRequest(uri, headers, queryParams);
            String responseBody = executeRequest(request);
            return deserializer.fromJson(responseBody, type);
        } catch (IOException | ParseException | RuntimeException e) {
            throw new FmpHttpException(e, "HTTP request failed: %s", uri);
        }
    }

    protected HttpGet buildRequest(URI uri, Map<String, String> headers, Map<String, Object> queryParams) {
        final URI finalUri = uriWithParams(uri, queryParams);
        final HttpGet request = new HttpGet(finalUri);
        if (headers != null) {
            headers.forEach(request::addHeader);
        }
        return request;
    }

    protected String executeRequest(HttpGet request) throws IOException, ParseException {
        try (ClassicHttpResponse response = http.executeOpen(null, request, null)) {
            return EntityUtils.toString(response.getEntity());
        }
    }
}

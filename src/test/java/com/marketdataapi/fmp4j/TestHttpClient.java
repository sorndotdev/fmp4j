package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.http.FmpUriUtils.uriWithParams;
import static java.util.Objects.requireNonNull;

import com.marketdataapi.fmp4j.exceptions.FmpDeserializationException;
import com.marketdataapi.fmp4j.http.FmpContentType;
import com.marketdataapi.fmp4j.http.FmpDeserializationRegistry;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.http.FmpHttpException;
import com.marketdataapi.fmp4j.http.FmpUnauthorizedException;
import com.marketdataapi.fmp4j.types.FmpApiKey;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public final class TestHttpClient implements FmpHttpClient {
    private final HttpClient http;
    private final FmpDeserializationRegistry deserializationRegistry;

    public TestHttpClient(HttpClient httpClient, FmpDeserializationRegistry deserializationRegistry) {
        this.http = requireNonNull(httpClient, "'httpClient' is required");
        this.deserializationRegistry = requireNonNull(deserializationRegistry, "'deserializationRegistry' is required");
    }

    @Override
    public <T> List<T> get(Class<T> clazz, URI uri, Map<String, String> headers, Map<String, Object> queryParams) {
        try {
            requireNonNull(headers, "'headers' is required");
            final var contentType = headers.get("Content-Type");
            final var request = buildRequest(uri, headers, queryParams);
            final var responsePair = executeRequest(request);
            final var statusCode = responsePair.getLeft();
            final var responseBody = responsePair.getRight();
            if (responseBody.isBlank()) {
                throw new FmpHttpException(
                        "Empty response for type [%s], uri [%s], headers [%s], queryParams [%s]",
                        clazz.getSimpleName(), uri, headers, queryParams);
            }
            if (statusCode == 401 || statusCode == 403) {
                throw new FmpUnauthorizedException(
                        "Unauthorized for type [%s], uri [%s], headers [%s], queryParams [%s]",
                        clazz.getSimpleName(), uri, headers, queryParams, responseBody);
            }
            return deserializationRegistry
                    .resolve(FmpContentType.fromContentTypeHeader(contentType))
                    .deserialize(responseBody, clazz);
        } catch (FmpHttpException e) {
            throw e;
        } catch (FmpDeserializationException e) {
            throw new FmpHttpException(
                    e,
                    "Deserialization failed for type [%s], uri [%s], headers [%s], queryParams [%s]",
                    clazz.getSimpleName(),
                    uri,
                    headers,
                    queryParams);
        } catch (ParseException | IOException | RuntimeException e) {
            throw new FmpHttpException(e, "HTTP request failed: %s", uri);
        }
    }

    HttpGet buildRequest(URI uri, Map<String, String> headers, Map<String, Object> queryParams) {
        final var copy = new HashMap<>(queryParams);
        final var key = (FmpApiKey) queryParams.get("apikey");
        copy.put("apikey", key.value());
        final var finalUri = uriWithParams(uri, copy);
        final var request = new HttpGet(finalUri);
        if (headers != null) {
            headers.forEach(request::addHeader);
        }
        return request;
    }

    Pair<Integer, String> executeRequest(HttpGet request) throws IOException, ParseException {
        try (ClassicHttpResponse response = http.executeOpen(null, request, null)) {
            return Pair.of(response.getCode(), EntityUtils.toString(response.getEntity()));
        }
    }
}

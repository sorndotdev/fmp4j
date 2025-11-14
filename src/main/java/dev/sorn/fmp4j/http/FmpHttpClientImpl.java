package dev.sorn.fmp4j.http;

import static dev.sorn.fmp4j.csv.FmpCsvDeserializer.FMP_CSV_DESERIALIZER;
import static dev.sorn.fmp4j.http.FmpUriUtils.uriWithParams;
import static dev.sorn.fmp4j.json.FmpJsonDeserializer.FMP_JSON_DESERIALIZER;
import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sorn.fmp4j.csv.FmpCsvDeserializer;
import dev.sorn.fmp4j.csv.FmpCsvException;
import dev.sorn.fmp4j.json.FmpJsonDeserializer;
import dev.sorn.fmp4j.json.FmpJsonException;
import dev.sorn.fmp4j.types.FmpApiKey;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class FmpHttpClientImpl implements FmpHttpClient {
    public static final FmpHttpClient FMP_HTTP_CLIENT = new FmpHttpClientImpl();
    private final HttpClient http;
    private final FmpJsonDeserializer jsonDeserializer;
    private final FmpCsvDeserializer csvDeserializer;

    private FmpHttpClientImpl() {
        this(HttpClients.createDefault(), FMP_JSON_DESERIALIZER, FMP_CSV_DESERIALIZER);
    }

    public FmpHttpClientImpl(HttpClient httpClient, FmpJsonDeserializer jsonDeserializer) {
        this(httpClient, jsonDeserializer, FMP_CSV_DESERIALIZER);
    }

    public FmpHttpClientImpl(
            HttpClient httpClient, FmpJsonDeserializer jsonDeserializer, FmpCsvDeserializer csvDeserializer) {
        this.http = requireNonNull(httpClient, "'httpClient' is required");
        this.jsonDeserializer = requireNonNull(jsonDeserializer, "'jsonDeserializer' is required");
        this.csvDeserializer = requireNonNull(csvDeserializer, "'csvDeserializer' is required");
    }

    @Override
    public <T> T get(TypeReference<T> type, URI uri, Map<String, String> headers, Map<String, Object> queryParams) {
        try {
            final var request = buildRequest(uri, headers, queryParams);
            final var responsePair = executeRequest(request);
            final var statusCode = responsePair.getLeft();
            final var responseBody = responsePair.getRight();
            if (statusCode == 401 || statusCode == 403) {
                throw new FmpUnauthorizedException(
                        "Unauthorized for type [%s], uri [%s], headers [%s], queryParams [%s];\nresponseBody: %s",
                        type.getType(), uri, headers, queryParams, responseBody);
            }

            String contentType = headers != null ? headers.get("Content-Type") : null;
            if ("text/csv".equals(contentType)) {
                return csvDeserializer.deserialize(responseBody, type);
            } else {
                return jsonDeserializer.deserialize(responseBody, type);
            }
        } catch (FmpUnauthorizedException e) {
            throw e;
        } catch (FmpJsonException e) {
            throw new FmpHttpException(
                    e,
                    "JSON deserialization failed for type [%s], uri [%s], headers [%s], queryParams [%s]",
                    type.getType(),
                    uri,
                    headers,
                    queryParams);
        } catch (FmpCsvException e) {
            throw new FmpHttpException(
                    e,
                    "CSV deserialization failed for type [%s], uri [%s], headers [%s], queryParams [%s]",
                    type.getType(),
                    uri,
                    headers,
                    queryParams);
        } catch (ParseException | IOException | RuntimeException e) {
            throw new FmpHttpException(e, "HTTP request failed: %s", uri);
        }
    }

    protected HttpGet buildRequest(URI uri, Map<String, String> headers, Map<String, Object> queryParams) {
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

    protected Pair<Integer, String> executeRequest(HttpGet request) throws IOException, ParseException {
        try (ClassicHttpResponse response = http.executeOpen(null, request, null)) {
            return Pair.of(response.getCode(), EntityUtils.toString(response.getEntity()));
        }
    }
}

package dev.sorn.fmp4j.http;

import com.fasterxml.jackson.core.type.TypeReference;
import java.net.URI;
import java.util.Map;

public interface FmpHttpClient {
    <T> T get(TypeReference<T> type, URI uri, Map<String, String> headers, Map<String, Object> queryParams);
}

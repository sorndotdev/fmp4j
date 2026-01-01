package com.marketdataapi.fmp4j.http;

import java.net.URI;
import java.util.List;
import java.util.Map;

public interface FmpHttpClient {
    <T> List<T> get(Class<T> clazz, URI uri, Map<String, String> headers, Map<String, Object> queryParams);
}

package com.marketdataapi.fmp4j.http;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import org.apache.hc.core5.net.URIBuilder;

public final class FmpUriUtils {
    private FmpUriUtils() {
        throw new AssertionError(FmpUriUtils.class.getSimpleName() + " cannot be instantiated.");
    }

    public static URI uri(String uri) {
        return URI.create(uri);
    }

    public static URI uriWithParams(URI uri, Map<String, Object> queryParams) {
        if (queryParams == null || queryParams.isEmpty()) {
            return uri;
        }
        try {
            final URIBuilder builder = new URIBuilder(uri);
            queryParams.forEach((key, value) -> builder.addParameter(key, String.valueOf(value)));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new FmpHttpException(e, "Failed to build URI [%s] with query params [%s]", uri, queryParams);
        }
    }
}

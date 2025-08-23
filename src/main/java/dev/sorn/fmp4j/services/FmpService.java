package dev.sorn.fmp4j.services;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import java.net.URI;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class FmpService<R> {
    protected final FmpConfig cfg;
    protected final FmpHttpClient http;
    protected final ConcurrentHashMap<String, Object> params = new ConcurrentHashMap<>();
    protected final TypeReference<R> typeRef;

    protected FmpService(FmpConfig cfg, FmpHttpClient http, TypeReference<R> typeRef) {
        this.cfg = cfg;
        this.http = http;
        this.typeRef = typeRef;
        this.params.put("apikey", cfg.fmpApiKey());
    }

    protected abstract String relativeUrl();

    protected final URI url() {
        return URI.create(cfg.fmpBaseUrl() + relativeUrl());
    }

    protected abstract Set<String> requiredParams();

    protected abstract Set<String> optionalParams();

    public final void param(String key, Object value) {
        if (!requiredParams().contains(key) && !optionalParams().contains(key)) {
            throw new FmpServiceException("'%s' is not a recognized query param for endpoint [%s]", key, url());
        }
        params.put(key, value);
    }

    protected final Map<String, String> headers() {
        return Map.of("Content-Type", "application/json");
    }

    public final R download() {
        final var required = requiredParams();
        for (final var req : required) {
            if (!params.containsKey(req)) {
                throw new FmpServiceException("'%s' is a required query param for endpoint [%s]", req, url());
            }
        }
        return http.get(typeRef, url(), headers(), params);
    }
}

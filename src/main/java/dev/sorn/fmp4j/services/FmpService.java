package dev.sorn.fmp4j.services;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
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

    protected abstract Map<String, Class<?>> requiredParams();

    protected abstract Map<String, Class<?>> optionalParams();

    protected R filter(R r) {
        return r;
    }

    private void validateParamKey(String key) {
        if (!requiredParams().containsKey(key) && !optionalParams().containsKey(key)) {
            throw new FmpServiceException("'%s' is not a recognized query param for endpoint [%s]", key, url());
        }
    }

    public final void param(String key, Object value) {
        validateParamKey(key);
        validateParamType(key, value);
        params.put(key, value);
    }

    private void validateParamType(String key, Object value) {
        Class<?> expectedClass =
                requiredParams().getOrDefault(key, optionalParams().get(key));
        Optional<? extends Class<?>> actualClass = Optional.of(value.getClass());

        if (value instanceof Collection<?> collection) {
            actualClass = collection.stream().map(Object::getClass).findFirst();
            ;
        }

        if (value instanceof Optional<?> optional) {
            actualClass = optional.map(Object::getClass);
        }

        if (actualClass.isPresent() && actualClass.get() != expectedClass) {
            throw new FmpServiceException(
                    "[%s] is not a valid type for query param [%s] for endpoint [%s]. Expected type is [%s]",
                    actualClass.get(), key, url(), expectedClass.getSimpleName());
        }
    }

    protected final Map<String, String> headers() {
        return Map.of("Content-Type", "application/json");
    }

    public final R download() {
        final var required = requiredParams();
        for (final var req : required.keySet()) {
            if (!params.containsKey(req)) {
                throw new FmpServiceException("'%s' is a required query param for endpoint [%s]", req, url());
            }
        }
        return filter(http.get(typeRef, url(), headers(), params));
    }
}

package dev.sorn.fmp4j.services;

import static java.util.Objects.requireNonNull;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public abstract class FmpService<R> {
    protected final FmpConfig cfg;
    protected final FmpHttpClient http;
    protected final ConcurrentHashMap<String, Object> params = new ConcurrentHashMap<>();
    protected final Class<R> clazz;

    protected FmpService(FmpConfig cfg, FmpHttpClient http, Class<R> clazz) {
        this.cfg = requireNonNull(cfg, "'cfg' is required");
        this.http = requireNonNull(http, "'http' is required");
        this.clazz = requireNonNull(clazz, "'clazz' is required");
        this.params.put("apikey", requireNonNull(cfg.fmpApiKey(), "'apikey' is required"));
    }

    protected abstract String relativeUrl();

    protected final URI url() {
        return URI.create(cfg.fmpBaseUrl() + relativeUrl());
    }

    protected abstract Map<String, Class<?>> requiredParams();

    protected abstract Map<String, Class<?>> optionalParams();

    protected boolean filter(R r) {
        return true;
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

    protected Map<String, String> headers() {
        return Map.of("Content-Type", "application/json");
    }

    public final List<R> download() {
        final var required = requiredParams();
        final var missing = required.keySet().stream()
                .filter(req -> !params.containsKey(req))
                .toList();

        if (!missing.isEmpty()) {
            throw new FmpServiceException("%s are required query params for endpoint [%s]", missing, url());
        }

        return http.get(clazz, url(), headers(), params).stream()
                .filter(this::filter)
                .toList();
    }
}

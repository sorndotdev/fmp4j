package dev.sorn.fmp4j.services;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpSearchByName;
import java.util.Map;

public class FmpSearchByNameService extends FmpService<FmpSearchByName> {
    public FmpSearchByNameService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpSearchByName.class);
    }

    @Override
    protected String relativeUrl() {
        return "/search-name";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("query", String.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of();
    }
}

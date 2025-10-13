package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpSearchByCik;
import dev.sorn.fmp4j.types.FmpCik;
import java.util.Map;

public class FmpSearchByCikService extends FmpService<FmpSearchByCik[]> {
    public FmpSearchByCikService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpSearchByCik[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/search-cik";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("cik", FmpCik.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of();
    }
}

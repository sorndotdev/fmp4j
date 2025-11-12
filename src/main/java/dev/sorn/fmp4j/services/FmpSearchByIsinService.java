package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpSearchByIsin;
import dev.sorn.fmp4j.types.FmpIsin;
import java.util.Map;

public class FmpSearchByIsinService extends FmpService<FmpSearchByIsin[]> {
    public FmpSearchByIsinService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpSearchByIsin[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/search-isin";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("isin", FmpIsin.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of();
    }
}

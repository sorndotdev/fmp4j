package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;
import static java.util.Collections.emptySet;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpSearchByIsin;
import java.util.Set;

public class FmpSearchByIsinService extends FmpService<FmpSearchByIsin[]> {
    public FmpSearchByIsinService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpSearchByIsin[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/search-isin";
    }

    @Override
    protected Set<String> requiredParams() {
        return Set.of("isin");
    }

    @Override
    protected Set<String> optionalParams() {
        return emptySet();
    }
}

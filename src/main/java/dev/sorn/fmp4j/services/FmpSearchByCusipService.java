package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;
import static java.util.Collections.emptySet;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpSearchByCusip;
import java.util.Set;

public class FmpSearchByCusipService extends FmpService<FmpSearchByCusip[]> {
    public FmpSearchByCusipService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpSearchByCusip[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/search-cusip";
    }

    @Override
    protected Set<String> requiredParams() {
        return Set.of("cusip");
    }

    @Override
    protected Set<String> optionalParams() {
        return emptySet();
    }
}

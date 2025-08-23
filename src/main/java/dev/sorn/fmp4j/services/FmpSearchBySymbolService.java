package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;
import static java.util.Collections.emptySet;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpSearchBySymbol;
import java.util.Set;

public class FmpSearchBySymbolService extends FmpService<FmpSearchBySymbol[]> {
    public FmpSearchBySymbolService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpSearchBySymbol[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/search-symbol";
    }

    @Override
    protected Set<String> requiredParams() {
        return Set.of("query");
    }

    @Override
    protected Set<String> optionalParams() {
        return emptySet();
    }
}

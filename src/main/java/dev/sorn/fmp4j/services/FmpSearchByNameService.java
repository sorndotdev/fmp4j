package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;
import static java.util.Collections.emptySet;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpSearchByName;
import java.util.Set;

public class FmpSearchByNameService extends FmpService<FmpSearchByName[]> {
    public FmpSearchByNameService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpSearchByName[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/search-name";
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

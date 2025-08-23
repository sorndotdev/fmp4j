package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;
import static java.util.Collections.emptySet;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpEarning;
import java.util.Set;

public class FmpEarningService extends FmpService<FmpEarning[]> {
    public FmpEarningService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpEarning[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/earnings";
    }

    @Override
    protected Set<String> requiredParams() {
        return Set.of("symbol");
    }

    @Override
    protected Set<String> optionalParams() {
        return emptySet();
    }
}

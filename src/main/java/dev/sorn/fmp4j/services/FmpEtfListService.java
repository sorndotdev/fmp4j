package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;
import static java.util.Collections.emptySet;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpEtf;
import java.util.Set;

public class FmpEtfListService extends FmpService<FmpEtf[]> {
    public FmpEtfListService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpEtf[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/etf-list";
    }

    @Override
    protected Set<String> requiredParams() {
        return emptySet();
    }

    @Override
    protected Set<String> optionalParams() {
        return emptySet();
    }
}

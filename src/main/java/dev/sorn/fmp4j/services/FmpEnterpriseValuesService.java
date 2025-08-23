package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpEnterpriseValue;
import java.util.Set;

public class FmpEnterpriseValuesService extends FmpService<FmpEnterpriseValue[]> {
    public FmpEnterpriseValuesService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpEnterpriseValue[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/enterprise-values";
    }

    @Override
    protected Set<String> requiredParams() {
        return Set.of("symbol");
    }

    @Override
    protected Set<String> optionalParams() {
        return Set.of("period", "limit");
    }
}

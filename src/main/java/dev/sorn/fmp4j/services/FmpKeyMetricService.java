package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpKeyMetric;
import java.util.Set;

public class FmpKeyMetricService extends FmpService<FmpKeyMetric[]> {
    public FmpKeyMetricService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpKeyMetric[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/key-metrics";
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

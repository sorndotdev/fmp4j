package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpKeyMetric;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpKeyMetricService extends FmpService<FmpKeyMetric[]> {
    public FmpKeyMetricService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpKeyMetric[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/key-metrics";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("symbol", FmpSymbol.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of("period", FmpPeriod.class, "limit", FmpLimit.class);
    }
}
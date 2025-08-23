package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpEtfCountryWeighting;
import java.util.Set;

public class FmpEtfCountryWeightingService extends FmpService<FmpEtfCountryWeighting[]> {
    public FmpEtfCountryWeightingService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpEtfCountryWeighting[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/etf/country-weightings";
    }

    @Override
    protected Set<String> requiredParams() {
        return Set.of("symbol");
    }

    @Override
    protected Set<String> optionalParams() {
        return Set.of();
    }
}

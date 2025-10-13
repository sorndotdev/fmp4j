package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpEtfCountryWeighting;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpEtfCountryWeightingService extends FmpService<FmpEtfCountryWeighting[]> {
    public FmpEtfCountryWeightingService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpEtfCountryWeighting[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/etf/country-weightings";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("symbol", FmpSymbol.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of();
    }
}

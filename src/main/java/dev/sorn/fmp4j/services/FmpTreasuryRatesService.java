package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpTreasuryRate;
import java.time.LocalDateTime;
import java.util.Map;

public class FmpTreasuryRatesService extends FmpService<FmpTreasuryRate[]> {
    public FmpTreasuryRatesService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpTreasuryRate[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/treasury-rates";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("from", LocalDateTime.class, "to", LocalDateTime.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of();
    }
}

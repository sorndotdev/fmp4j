package dev.sorn.fmp4j.services;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpSearchByCusip;
import dev.sorn.fmp4j.types.FmpCusip;
import java.util.Map;

public class FmpSearchByCusipService extends FmpService<FmpSearchByCusip> {
    public FmpSearchByCusipService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpSearchByCusip.class);
    }

    @Override
    protected String relativeUrl() {
        return "/search-cusip";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("cusip", FmpCusip.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of();
    }
}

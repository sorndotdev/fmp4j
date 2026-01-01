package com.marketdataapi.fmp4j.services;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpSearchByCusip;
import com.marketdataapi.fmp4j.types.FmpCusip;
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

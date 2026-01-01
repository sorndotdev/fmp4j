package com.marketdataapi.fmp4j.services;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpSearchByName;
import java.util.Map;

public class FmpSearchByNameService extends FmpService<FmpSearchByName> {
    public FmpSearchByNameService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpSearchByName.class);
    }

    @Override
    protected String relativeUrl() {
        return "/search-name";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("query", String.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of();
    }
}

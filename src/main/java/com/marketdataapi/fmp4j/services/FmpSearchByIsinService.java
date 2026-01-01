package com.marketdataapi.fmp4j.services;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpSearchByIsin;
import com.marketdataapi.fmp4j.types.FmpIsin;
import java.util.Map;

public class FmpSearchByIsinService extends FmpService<FmpSearchByIsin> {
    public FmpSearchByIsinService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpSearchByIsin.class);
    }

    @Override
    protected String relativeUrl() {
        return "/search-isin";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("isin", FmpIsin.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of();
    }
}

package com.marketdataapi.fmp4j.services;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpKeyMetricTtm;
import java.util.Map;

public class FmpBulkKeyMetricTtmService extends FmpService<FmpKeyMetricTtm> {
    public FmpBulkKeyMetricTtmService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpKeyMetricTtm.class);
    }

    @Override
    protected String relativeUrl() {
        return "/key-metrics-ttm-bulk";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of();
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of();
    }

    @Override
    protected Map<String, String> headers() {
        return Map.of("Content-Type", "text/csv");
    }
}

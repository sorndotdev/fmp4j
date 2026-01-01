package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_PERIOD;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_STRUCTURE;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOL;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpRevenueGeographicSegmentation;
import com.marketdataapi.fmp4j.types.FmpPeriod;
import com.marketdataapi.fmp4j.types.FmpStructure;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpRevenueGeographicSegmentationService extends FmpService<FmpRevenueGeographicSegmentation> {
    public FmpRevenueGeographicSegmentationService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpRevenueGeographicSegmentation.class);
    }

    @Override
    protected String relativeUrl() {
        return "/revenue-geographic-segmentation";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of(PARAM_SYMBOL, FmpSymbol.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of(PARAM_PERIOD, FmpPeriod.class, PARAM_STRUCTURE, FmpStructure.class);
    }
}

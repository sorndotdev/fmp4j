package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_PERIOD;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_STRUCTURE;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpRevenueGeographicSegmentation;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpStructure;
import dev.sorn.fmp4j.types.FmpSymbol;
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

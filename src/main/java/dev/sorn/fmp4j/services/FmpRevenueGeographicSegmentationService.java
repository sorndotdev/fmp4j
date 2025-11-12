package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpRevenueGeographicSegmentation;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpStructure;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpRevenueGeographicSegmentationService extends FmpService<FmpRevenueGeographicSegmentation[]> {
    public FmpRevenueGeographicSegmentationService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpRevenueGeographicSegmentation[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/revenue-geographic-segmentation";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("symbol", FmpSymbol.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of("period", FmpPeriod.class, "structure", FmpStructure.class);
    }
}

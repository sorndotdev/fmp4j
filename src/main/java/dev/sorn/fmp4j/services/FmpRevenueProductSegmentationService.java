package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpRevenueProductSegmentation;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpStructure;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpRevenueProductSegmentationService extends FmpService<FmpRevenueProductSegmentation[]> {
    public FmpRevenueProductSegmentationService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpRevenueProductSegmentation[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/revenue-product-segmentation";
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

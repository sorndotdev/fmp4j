package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpRevenueProductSegmentation;
import java.util.Set;

public class FmpRevenueProductSegmentationService extends FmpService<FmpRevenueProductSegmentation[]> {
    public FmpRevenueProductSegmentationService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpRevenueProductSegmentation[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/revenue-product-segmentation";
    }

    @Override
    protected Set<String> requiredParams() {
        return Set.of("symbol");
    }

    @Override
    protected Set<String> optionalParams() {
        return Set.of("period", "structure");
    }
}

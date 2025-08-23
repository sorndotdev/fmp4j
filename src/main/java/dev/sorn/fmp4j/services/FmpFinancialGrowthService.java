package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpFinancialGrowth;
import java.util.Set;

public class FmpFinancialGrowthService extends FmpService<FmpFinancialGrowth[]> {
    public FmpFinancialGrowthService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpFinancialGrowth[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/financial-growth";
    }

    @Override
    protected Set<String> requiredParams() {
        return Set.of("symbol");
    }

    @Override
    protected Set<String> optionalParams() {
        return Set.of("limit", "period");
    }
}

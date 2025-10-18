package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpCashFlowStatementGrowth;
import java.util.Set;

public class FmpCashFlowStatementGrowthBulkService extends FmpService<FmpCashFlowStatementGrowth[]> {
    public FmpCashFlowStatementGrowthBulkService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpCashFlowStatementGrowth[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/v4/cash-flow-statement-growth-bulk";
    }

    @Override
    protected Set<String> requiredParams() {
        return Set.of("year", "period");
    }

    @Override
    protected Set<String> optionalParams() {
        return Set.of();
    }
}

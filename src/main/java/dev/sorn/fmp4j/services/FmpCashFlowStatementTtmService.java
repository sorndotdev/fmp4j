package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpCashFlowStatement;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpCashFlowStatementTtmService extends FmpService<FmpCashFlowStatement[]> {
    public FmpCashFlowStatementTtmService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpCashFlowStatement[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/cash-flow-statement-ttm";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("symbol", FmpSymbol.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of("limit", FmpLimit.class);
    }
}

package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpCashFlowStatement;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpYear;
import java.util.Map;

public class FmpCashFlowStatementBulkService extends FmpService<FmpCashFlowStatement[]> {
    public FmpCashFlowStatementBulkService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpCashFlowStatement[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/cash-flow-statement-bulk";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("year", FmpYear.class, "period", FmpPeriod.class);
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

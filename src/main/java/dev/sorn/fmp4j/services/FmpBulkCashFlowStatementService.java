package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_PERIOD;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_YEAR;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpCashFlowStatement;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpYear;
import java.util.Map;

public class FmpBulkCashFlowStatementService extends FmpService<FmpCashFlowStatement[]> {
    public FmpBulkCashFlowStatementService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpCashFlowStatement[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/cash-flow-statement-bulk";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of(PARAM_YEAR, FmpYear.class, PARAM_PERIOD, FmpPeriod.class);
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

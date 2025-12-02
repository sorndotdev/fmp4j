package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpIncomeStatement;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpIncomeStatementTtmService extends FmpService<FmpIncomeStatement[]> {
    public FmpIncomeStatementTtmService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpIncomeStatement[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/income-statement-ttm";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of(PARAM_SYMBOL, FmpSymbol.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of(PARAM_LIMIT, FmpLimit.class);
    }
}

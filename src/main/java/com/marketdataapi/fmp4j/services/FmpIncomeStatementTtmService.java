package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOL;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpIncomeStatement;
import com.marketdataapi.fmp4j.types.FmpLimit;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpIncomeStatementTtmService extends FmpService<FmpIncomeStatement> {
    public FmpIncomeStatementTtmService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpIncomeStatement.class);
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

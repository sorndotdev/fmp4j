package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_PERIOD;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOL;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpBalanceSheetStatementGrowth;
import com.marketdataapi.fmp4j.types.FmpLimit;
import com.marketdataapi.fmp4j.types.FmpPeriod;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpBalanceSheetStatementGrowthService extends FmpService<FmpBalanceSheetStatementGrowth> {
    public FmpBalanceSheetStatementGrowthService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpBalanceSheetStatementGrowth.class);
    }

    @Override
    protected String relativeUrl() {
        return "/balance-sheet-statement-growth";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of(PARAM_SYMBOL, FmpSymbol.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of(PARAM_PERIOD, FmpPeriod.class, PARAM_LIMIT, FmpLimit.class);
    }
}

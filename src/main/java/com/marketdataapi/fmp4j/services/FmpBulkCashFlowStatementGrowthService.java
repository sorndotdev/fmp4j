package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_PERIOD;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_YEAR;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpCashFlowStatementGrowth;
import com.marketdataapi.fmp4j.types.FmpPeriod;
import com.marketdataapi.fmp4j.types.FmpYear;
import java.util.Map;

public class FmpBulkCashFlowStatementGrowthService extends FmpService<FmpCashFlowStatementGrowth> {
    public FmpBulkCashFlowStatementGrowthService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpCashFlowStatementGrowth.class);
    }

    @Override
    protected String relativeUrl() {
        return "/cash-flow-statement-growth-bulk";
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

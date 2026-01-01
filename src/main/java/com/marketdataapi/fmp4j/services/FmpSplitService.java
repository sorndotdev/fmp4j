package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOL;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpSplit;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpSplitService extends FmpService<FmpSplit> {
    public FmpSplitService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpSplit.class);
    }

    @Override
    protected String relativeUrl() {
        return "/splits";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of(PARAM_SYMBOL, FmpSymbol.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of();
    }
}

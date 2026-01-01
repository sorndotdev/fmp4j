package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOL;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpDividend;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpDividendService extends FmpService<FmpDividend> {
    public FmpDividendService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpDividend.class);
    }

    @Override
    protected String relativeUrl() {
        return "/dividends";
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

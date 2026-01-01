package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOL;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpRatioTtm;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpRatioTtmService extends FmpService<FmpRatioTtm> {
    public FmpRatioTtmService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpRatioTtm.class);
    }

    @Override
    protected String relativeUrl() {
        return "/ratios-ttm";
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

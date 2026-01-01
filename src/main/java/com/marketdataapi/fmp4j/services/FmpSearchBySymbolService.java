package com.marketdataapi.fmp4j.services;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpSearchBySymbol;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpSearchBySymbolService extends FmpService<FmpSearchBySymbol> {
    public FmpSearchBySymbolService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpSearchBySymbol.class);
    }

    @Override
    protected String relativeUrl() {
        return "/search-symbol";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("query", FmpSymbol.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of();
    }
}

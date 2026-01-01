package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_FROM;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_PAGE;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_TO;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpSecFilingsSearchBySymbol;
import com.marketdataapi.fmp4j.types.FmpLimit;
import com.marketdataapi.fmp4j.types.FmpPage;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.time.LocalDate;
import java.util.Map;

public class FmpSecFilingsSearchBySymbolService extends FmpService<FmpSecFilingsSearchBySymbol> {
    public FmpSecFilingsSearchBySymbolService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpSecFilingsSearchBySymbol.class);
    }

    @Override
    protected String relativeUrl() {
        return "/sec-filings-search/symbol";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of(PARAM_SYMBOL, FmpSymbol.class, PARAM_FROM, LocalDate.class, PARAM_TO, LocalDate.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of(PARAM_PAGE, FmpPage.class, PARAM_LIMIT, FmpLimit.class);
    }
}

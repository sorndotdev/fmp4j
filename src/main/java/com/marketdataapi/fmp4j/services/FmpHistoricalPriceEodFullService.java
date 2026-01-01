package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_FROM;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_TO;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpHistoricalPriceEodFull;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.time.LocalDate;
import java.util.Map;

public class FmpHistoricalPriceEodFullService extends FmpService<FmpHistoricalPriceEodFull> {
    public FmpHistoricalPriceEodFullService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpHistoricalPriceEodFull.class);
    }

    @Override
    protected String relativeUrl() {
        return "/historical-price-eod/full";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of(PARAM_SYMBOL, FmpSymbol.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of(PARAM_FROM, LocalDate.class, PARAM_TO, LocalDate.class);
    }
}

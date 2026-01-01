package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_FROM;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_PAGE;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOLS;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_TO;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpNews;
import com.marketdataapi.fmp4j.types.FmpLimit;
import com.marketdataapi.fmp4j.types.FmpPage;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.time.LocalDate;
import java.util.Map;

public class FmpNewsService extends FmpService<FmpNews> {
    protected final String type;

    public FmpNewsService(FmpConfig cfg, FmpHttpClient http, String type) {
        super(cfg, http, FmpNews.class);
        this.type = type;
    }

    @Override
    protected String relativeUrl() {
        return "/news/" + type;
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of(PARAM_SYMBOLS, FmpSymbol.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of(
                PARAM_FROM,
                LocalDate.class,
                PARAM_TO,
                LocalDate.class,
                PARAM_PAGE,
                FmpPage.class,
                PARAM_LIMIT,
                FmpLimit.class);
    }
}

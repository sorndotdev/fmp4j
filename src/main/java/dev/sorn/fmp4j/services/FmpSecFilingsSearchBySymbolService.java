package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_FROM;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_PAGE;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_TO;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpSecFilingsSearchBySymbol;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPage;
import dev.sorn.fmp4j.types.FmpSymbol;
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

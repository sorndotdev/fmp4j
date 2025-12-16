package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpCompany;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpCompanyService extends FmpService<FmpCompany> {
    public FmpCompanyService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpCompany.class);
    }

    @Override
    protected String relativeUrl() {
        return "/profile";
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

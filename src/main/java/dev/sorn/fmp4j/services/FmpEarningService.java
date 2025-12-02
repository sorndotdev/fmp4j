package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpEarning;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpEarningService extends FmpService<FmpEarning[]> {
    public FmpEarningService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpEarning[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/earnings";
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

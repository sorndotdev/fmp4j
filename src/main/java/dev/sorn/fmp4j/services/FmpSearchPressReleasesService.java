package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOLS;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpSearchPressRelease;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpSearchPressReleasesService extends FmpService<FmpSearchPressRelease[]> {

    public FmpSearchPressReleasesService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpSearchPressRelease[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/news/press-releases";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of(PARAM_SYMBOLS, FmpSymbol.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of();
    }
}

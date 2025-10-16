package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpSearchBySymbol;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpSearchBySymbolService extends FmpService<FmpSearchBySymbol[]> {
    public FmpSearchBySymbolService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpSearchBySymbol[].class));
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

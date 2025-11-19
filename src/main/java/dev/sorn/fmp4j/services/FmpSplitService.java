package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpSplit;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpSplitService extends FmpService<FmpSplit[]> {
    public FmpSplitService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpSplit[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/splits";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("symbol", FmpSymbol.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of();
    }
}

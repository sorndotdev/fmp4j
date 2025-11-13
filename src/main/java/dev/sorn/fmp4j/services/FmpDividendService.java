package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpDividend;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpDividendService extends FmpService<FmpDividend[]> {
    public FmpDividendService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpDividend[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/dividends";
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

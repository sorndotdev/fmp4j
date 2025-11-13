package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpEtf;
import java.util.Map;

public class FmpEtfListService extends FmpService<FmpEtf[]> {
    public FmpEtfListService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpEtf[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/etf-list";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of();
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of();
    }
}

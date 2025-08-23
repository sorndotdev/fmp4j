package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpEtfInfo;
import java.util.Set;

public class FmpEtfInfoService extends FmpService<FmpEtfInfo[]> {
    public FmpEtfInfoService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpEtfInfo[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/etf/info";
    }

    @Override
    protected Set<String> requiredParams() {
        return Set.of("symbol");
    }

    @Override
    protected Set<String> optionalParams() {
        return Set.of();
    }
}

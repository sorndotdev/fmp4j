package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;
import static java.util.Collections.emptySet;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpRatioTtm;
import java.util.Set;

public class FmpRatioTtmService extends FmpService<FmpRatioTtm[]> {
    public FmpRatioTtmService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpRatioTtm[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/ratios-ttm";
    }

    @Override
    protected Set<String> requiredParams() {
        return Set.of("symbol");
    }

    @Override
    protected Set<String> optionalParams() {
        return emptySet();
    }
}

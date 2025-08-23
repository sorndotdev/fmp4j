package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;
import static java.util.Collections.emptySet;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpFullQuote;
import java.util.Set;

public class FmpQuoteService extends FmpService<FmpFullQuote[]> {
    public FmpQuoteService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpFullQuote[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/quote";
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

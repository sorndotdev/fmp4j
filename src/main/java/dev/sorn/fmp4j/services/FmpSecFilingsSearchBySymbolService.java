package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpSecFilingsSearchBySymbol;
import java.util.Set;

public class FmpSecFilingsSearchBySymbolService extends FmpService<FmpSecFilingsSearchBySymbol[]> {
    public FmpSecFilingsSearchBySymbolService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpSecFilingsSearchBySymbol[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/sec-filings-search/symbol";
    }

    @Override
    protected Set<String> requiredParams() {
        return Set.of("symbol", "from", "to");
    }

    @Override
    protected Set<String> optionalParams() {
        return Set.of("page", "limit");
    }
}

package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpSecFilingsSearchBySymbol;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPage;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.time.LocalDateTime;
import java.util.Map;

public class FmpSecFilingsSearchBySymbolService extends FmpService<FmpSecFilingsSearchBySymbol[]> {
    public FmpSecFilingsSearchBySymbolService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpSecFilingsSearchBySymbol[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/sec-filings-search/symbol";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("symbol", FmpSymbol.class, "from", LocalDateTime.class, "to", LocalDateTime.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of("page", FmpPage.class, "limit", FmpLimit.class);
    }
}

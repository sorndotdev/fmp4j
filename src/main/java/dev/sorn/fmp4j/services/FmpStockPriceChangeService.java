package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;
import static java.util.Collections.emptySet;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpStockPriceChange;
import java.util.Set;

public class FmpStockPriceChangeService extends FmpService<FmpStockPriceChange[]> {
    public FmpStockPriceChangeService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpStockPriceChange[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/stock-price-change";
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

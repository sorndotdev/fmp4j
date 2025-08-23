package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpHistoricalPriceEodFull;
import java.util.Set;

public class FmpHistoricalPriceEodFullService extends FmpService<FmpHistoricalPriceEodFull[]> {
    public FmpHistoricalPriceEodFullService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpHistoricalPriceEodFull[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/historical-price-eod/full";
    }

    @Override
    protected Set<String> requiredParams() {
        return Set.of("symbol");
    }

    @Override
    protected Set<String> optionalParams() {
        return Set.of("from", "to");
    }
}

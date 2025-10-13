package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpHistoricalPriceEodFull;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.time.LocalDateTime;
import java.util.Map;

public class FmpHistoricalPriceEodFullService extends FmpService<FmpHistoricalPriceEodFull[]> {
    public FmpHistoricalPriceEodFullService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpHistoricalPriceEodFull[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/historical-price-eod/full";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("symbol", FmpSymbol.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of("from", LocalDateTime.class, "to", LocalDateTime.class);
    }
}

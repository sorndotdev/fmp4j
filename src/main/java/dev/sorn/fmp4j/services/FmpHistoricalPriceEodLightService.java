package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpHistoricalPriceEodLight;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.time.LocalDate;
import java.util.Map;

public class FmpHistoricalPriceEodLightService extends FmpService<FmpHistoricalPriceEodLight[]> {
    public FmpHistoricalPriceEodLightService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpHistoricalPriceEodLight[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/historical-price-eod/light";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("symbol", FmpSymbol.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of("from", LocalDate.class, "to", LocalDate.class);
    }
}

package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_FROM;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_TO;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpTreasuryRate;
import java.time.LocalDate;
import java.util.Map;

public class FmpTreasuryRatesService extends FmpService<FmpTreasuryRate[]> {
    public FmpTreasuryRatesService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpTreasuryRate[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/treasury-rates";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of(PARAM_FROM, LocalDate.class, PARAM_TO, LocalDate.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of();
    }
}

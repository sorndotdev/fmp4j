package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_FROM;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_TO;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpIposProspectus;
import java.time.LocalDate;
import java.util.Map;

public class FmpIposProspectusService extends FmpService<FmpIposProspectus> {
    public FmpIposProspectusService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpIposProspectus.class);
    }

    @Override
    protected String relativeUrl() {
        return "/ipos-prospectus";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of();
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of(PARAM_FROM, LocalDate.class, PARAM_TO, LocalDate.class);
    }
}

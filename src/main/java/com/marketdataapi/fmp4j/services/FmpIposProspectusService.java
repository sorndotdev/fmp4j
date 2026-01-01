package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_FROM;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_TO;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpIposProspectus;
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

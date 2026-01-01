package com.marketdataapi.fmp4j.services;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpSplitsCalendar;
import java.util.Map;

public class FmpSplitsCalendarService extends FmpService<FmpSplitsCalendar> {
    public FmpSplitsCalendarService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpSplitsCalendar.class);
    }

    @Override
    protected String relativeUrl() {
        return "/splits-calendar";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of();
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of();
    }
}

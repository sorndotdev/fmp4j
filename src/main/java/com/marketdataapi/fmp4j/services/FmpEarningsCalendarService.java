package com.marketdataapi.fmp4j.services;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpEarningsCalendar;
import java.util.Map;

public class FmpEarningsCalendarService extends FmpService<FmpEarningsCalendar> {
    public FmpEarningsCalendarService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpEarningsCalendar.class);
    }

    @Override
    protected String relativeUrl() {
        return "/earnings-calendar";
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

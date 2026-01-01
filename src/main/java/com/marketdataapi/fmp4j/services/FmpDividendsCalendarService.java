package com.marketdataapi.fmp4j.services;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpDividendsCalendar;
import java.util.Map;

public class FmpDividendsCalendarService extends FmpService<FmpDividendsCalendar> {
    public FmpDividendsCalendarService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpDividendsCalendar.class);
    }

    @Override
    protected String relativeUrl() {
        return "/dividends-calendar";
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

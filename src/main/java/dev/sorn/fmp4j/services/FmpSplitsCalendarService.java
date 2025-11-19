package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpSplitsCalendar;
import java.util.Map;

public class FmpSplitsCalendarService extends FmpService<FmpSplitsCalendar[]> {
    public FmpSplitsCalendarService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpSplitsCalendar[].class));
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

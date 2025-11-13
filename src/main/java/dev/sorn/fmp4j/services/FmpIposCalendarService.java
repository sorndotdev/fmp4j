package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpIposCalendar;
import java.time.LocalDate;
import java.util.Map;

public class FmpIposCalendarService extends FmpService<FmpIposCalendar[]> {
    public FmpIposCalendarService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpIposCalendar[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/ipos-calendar";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of();
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of("from", LocalDate.class, "to", LocalDate.class);
    }
}
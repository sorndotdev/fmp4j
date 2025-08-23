package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;
import static java.util.Collections.emptySet;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpEarningsCalendar;
import java.util.Set;

public class FmpEarningsCalendarService extends FmpService<FmpEarningsCalendar[]> {
    public FmpEarningsCalendarService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpEarningsCalendar[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/earnings-calendar";
    }

    @Override
    protected Set<String> requiredParams() {
        return emptySet();
    }

    @Override
    protected Set<String> optionalParams() {
        return emptySet();
    }
}

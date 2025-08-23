package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;
import static java.util.Collections.emptySet;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpDividendsCalendar;
import java.util.Set;

public class FmpDividendsCalendarService extends FmpService<FmpDividendsCalendar[]> {
    public FmpDividendsCalendarService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpDividendsCalendar[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/dividends-calendar";
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

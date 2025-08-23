package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpHistoricalChart;
import java.util.Set;

public class FmpHistoricalChartService extends FmpService<FmpHistoricalChart[]> {
    protected final String interval;

    public FmpHistoricalChartService(FmpConfig cfg, FmpHttpClient http, String interval) {
        super(cfg, http, typeRef(FmpHistoricalChart[].class));
        this.interval = interval;
    }

    @Override
    protected String relativeUrl() {
        return "/historical-chart/" + interval;
    }

    @Override
    protected Set<String> requiredParams() {
        return Set.of("symbol");
    }

    @Override
    protected Set<String> optionalParams() {
        return Set.of("from", "to");
    }
}

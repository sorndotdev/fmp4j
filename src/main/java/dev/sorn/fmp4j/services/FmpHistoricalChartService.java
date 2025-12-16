package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_FROM;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_TO;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpHistoricalChart;
import dev.sorn.fmp4j.types.FmpInterval;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.time.LocalDate;
import java.util.Map;

public class FmpHistoricalChartService extends FmpService<FmpHistoricalChart> {
    protected final FmpInterval interval;

    public FmpHistoricalChartService(FmpConfig cfg, FmpHttpClient http, FmpInterval interval) {
        super(cfg, http, FmpHistoricalChart.class);
        this.interval = interval;
    }

    @Override
    protected String relativeUrl() {
        return "/historical-chart/" + interval;
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of(PARAM_SYMBOL, FmpSymbol.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of(PARAM_FROM, LocalDate.class, PARAM_TO, LocalDate.class);
    }
}

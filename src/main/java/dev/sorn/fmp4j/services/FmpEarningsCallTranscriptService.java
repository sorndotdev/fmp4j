package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_QUARTER;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_YEAR;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpEarningsCallTranscript;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpQuarter;
import dev.sorn.fmp4j.types.FmpSymbol;
import dev.sorn.fmp4j.types.FmpYear;
import java.util.Map;

public class FmpEarningsCallTranscriptService extends FmpService<FmpEarningsCallTranscript> {
    public FmpEarningsCallTranscriptService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpEarningsCallTranscript.class);
    }

    @Override
    protected String relativeUrl() {
        return "/earning-call-transcript";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of(PARAM_SYMBOL, FmpSymbol.class, PARAM_YEAR, FmpYear.class, PARAM_QUARTER, FmpQuarter.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of(PARAM_LIMIT, FmpLimit.class);
    }
}

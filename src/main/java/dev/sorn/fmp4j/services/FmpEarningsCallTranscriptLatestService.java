package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_PAGE;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpEarningsCallTranscriptLatest;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPage;
import java.util.Map;

public class FmpEarningsCallTranscriptLatestService extends FmpService<FmpEarningsCallTranscriptLatest> {
    public FmpEarningsCallTranscriptLatestService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpEarningsCallTranscriptLatest.class);
    }

    @Override
    protected String relativeUrl() {
        return "/earning-call-transcript-latest";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of();
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of(PARAM_LIMIT, FmpLimit.class, PARAM_PAGE, FmpPage.class);
    }
}

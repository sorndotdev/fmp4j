package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_PAGE;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpEarningsCallTranscriptLatest;
import com.marketdataapi.fmp4j.types.FmpLimit;
import com.marketdataapi.fmp4j.types.FmpPage;
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

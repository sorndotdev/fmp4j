package com.marketdataapi.fmp4j.services;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpEarningsCallTranscriptList;
import java.util.Map;

public class FmpEarningsCallTranscriptListService extends FmpService<FmpEarningsCallTranscriptList> {
    public FmpEarningsCallTranscriptListService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpEarningsCallTranscriptList.class);
    }

    @Override
    protected String relativeUrl() {
        return "/earnings-transcript-list";
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

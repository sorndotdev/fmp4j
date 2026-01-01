package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOL;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpEarningsCallTranscriptDate;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpEarningsCallTranscriptDatesService extends FmpService<FmpEarningsCallTranscriptDate> {
    public FmpEarningsCallTranscriptDatesService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpEarningsCallTranscriptDate.class);
    }

    @Override
    protected String relativeUrl() {
        return "/earning-call-transcript-dates";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of(PARAM_SYMBOL, FmpSymbol.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of();
    }
}

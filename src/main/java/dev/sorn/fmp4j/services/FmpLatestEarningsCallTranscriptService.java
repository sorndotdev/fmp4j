package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpLatestEarningsCallTranscript;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPage;
import java.util.Map;

public class FmpLatestEarningsCallTranscriptService extends FmpService<FmpLatestEarningsCallTranscript[]> {
    public FmpLatestEarningsCallTranscriptService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpLatestEarningsCallTranscript[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/earning-call-transcript-latest";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("limit", FmpLimit.class, "page", FmpPage.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of();
    }
}

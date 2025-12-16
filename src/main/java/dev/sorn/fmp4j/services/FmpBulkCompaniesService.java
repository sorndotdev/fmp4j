package dev.sorn.fmp4j.services;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpCompanies;
import dev.sorn.fmp4j.types.FmpPart;
import java.util.Map;

public class FmpBulkCompaniesService extends FmpService<FmpCompanies> {
    public FmpBulkCompaniesService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, FmpCompanies.class);
    }

    @Override
    protected String relativeUrl() {
        return "/profile-bulk";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("part", FmpPart.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of();
    }

    @Override
    protected Map<String, String> headers() {
        return Map.of("Content-Type", "text/csv");
    }
}

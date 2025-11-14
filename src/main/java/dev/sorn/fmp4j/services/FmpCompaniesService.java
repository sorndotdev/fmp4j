package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpCompany;
import dev.sorn.fmp4j.types.FmpPart;
import java.util.Map;

public class FmpCompaniesService extends FmpService<FmpCompany[]> {
    public FmpCompaniesService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpCompany[].class));
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

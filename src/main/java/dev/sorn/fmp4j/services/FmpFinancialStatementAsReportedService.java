package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpFinancialStatementAsReported;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpFinancialStatementAsReportedService extends FmpService<FmpFinancialStatementAsReported[]> {
    protected final String type;

    public FmpFinancialStatementAsReportedService(FmpConfig cfg, FmpHttpClient http, String type) {
        super(cfg, http, typeRef(FmpFinancialStatementAsReported[].class));
        this.type = type;
    }

    @Override
    protected String relativeUrl() {
        return "/" + type + "-statement-as-reported";
    }

    @Override
    protected Map<String, Class<?>> requiredParams() {
        return Map.of("symbol", FmpSymbol.class);
    }

    @Override
    protected Map<String, Class<?>> optionalParams() {
        return Map.of("period", FmpPeriod.class, "limit", FmpLimit.class);
    }
}

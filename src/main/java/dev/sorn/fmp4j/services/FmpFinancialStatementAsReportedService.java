package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpFinancialStatementAsReported;
import java.util.Set;

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
    protected Set<String> requiredParams() {
        return Set.of("symbol");
    }

    @Override
    protected Set<String> optionalParams() {
        return Set.of("period", "limit");
    }
}

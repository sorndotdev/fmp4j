package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpBalanceSheetStatement;
import java.util.Set;

public class FmpBalanceSheetStatementService extends FmpService<FmpBalanceSheetStatement[]> {
    public FmpBalanceSheetStatementService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpBalanceSheetStatement[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/balance-sheet-statement";
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

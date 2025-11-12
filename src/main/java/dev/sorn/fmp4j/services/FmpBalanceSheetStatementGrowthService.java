package dev.sorn.fmp4j.services;

import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpBalanceSheetStatementGrowth;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpSymbol;
import java.util.Map;

public class FmpBalanceSheetStatementGrowthService extends FmpService<FmpBalanceSheetStatementGrowth[]> {
    public FmpBalanceSheetStatementGrowthService(FmpConfig cfg, FmpHttpClient http) {
        super(cfg, http, typeRef(FmpBalanceSheetStatementGrowth[].class));
    }

    @Override
    protected String relativeUrl() {
        return "/balance-sheet-statement-growth";
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

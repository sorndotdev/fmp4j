package dev.sorn.fmp4j.clients;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpCashFlowStatementGrowth;
import dev.sorn.fmp4j.services.FmpCashFlowStatementGrowthBulkService;
import dev.sorn.fmp4j.services.FmpService;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpYear;

public class FmpBulkClient {
    private final FmpService<FmpCashFlowStatementGrowth[]> cashFlowStatementGrowthService;

    public FmpBulkClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.cashFlowStatementGrowthService = new FmpCashFlowStatementGrowthBulkService(fmpConfig, fmpHttpClient);
    }

    public synchronized FmpCashFlowStatementGrowth[] cashFlowStatementGrowth(FmpYear year, FmpPeriod period) {
        cashFlowStatementGrowthService.param("year", year);
        cashFlowStatementGrowthService.param("period", period);
        return cashFlowStatementGrowthService.download();
    }
}

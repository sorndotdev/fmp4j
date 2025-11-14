package dev.sorn.fmp4j.clients;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpCashFlowStatement;
import dev.sorn.fmp4j.services.FmpCashFlowStatementBulkService;
import dev.sorn.fmp4j.services.FmpService;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpYear;

public class FmpBulkClient {

    protected final FmpService<FmpCashFlowStatement[]> cashFlowStatementBulkService;

    public FmpBulkClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.cashFlowStatementBulkService = new FmpCashFlowStatementBulkService(fmpConfig, fmpHttpClient);
    }

    public synchronized FmpCashFlowStatement[] cashFlowStatements(FmpYear year, FmpPeriod period) {
        cashFlowStatementBulkService.param("year", year);
        cashFlowStatementBulkService.param("period", period);

        return cashFlowStatementBulkService.download();
    }
}

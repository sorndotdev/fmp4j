package dev.sorn.fmp4j.clients;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpBalanceSheetStatement;
import dev.sorn.fmp4j.services.FmpBalanceSheetStatementBulkService;
import dev.sorn.fmp4j.services.FmpService;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpYear;

public class FmpBulkClient {

    protected final FmpService<FmpBalanceSheetStatement[]> balanceSheetStatementBulkService;

    public FmpBulkClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.balanceSheetStatementBulkService = new FmpBalanceSheetStatementBulkService(fmpConfig, fmpHttpClient);
    }

    public synchronized FmpBalanceSheetStatement[] balanceSheetStatements(FmpYear year, FmpPeriod period) {
        balanceSheetStatementBulkService.param("year", year);
        balanceSheetStatementBulkService.param("period", period);

        return balanceSheetStatementBulkService.download();
    }
}

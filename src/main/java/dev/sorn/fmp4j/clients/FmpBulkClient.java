package dev.sorn.fmp4j.clients;

import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_PERIOD;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_YEAR;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpBalanceSheetStatement;
import dev.sorn.fmp4j.models.FmpCashFlowStatement;
import dev.sorn.fmp4j.models.FmpCashFlowStatementGrowth;
import dev.sorn.fmp4j.models.FmpCompanies;
import dev.sorn.fmp4j.services.FmpBulkBalanceSheetStatementService;
import dev.sorn.fmp4j.services.FmpBulkCashFlowStatementGrowthService;
import dev.sorn.fmp4j.services.FmpBulkCashFlowStatementService;
import dev.sorn.fmp4j.services.FmpBulkCompaniesService;
import dev.sorn.fmp4j.services.FmpService;
import dev.sorn.fmp4j.types.FmpPart;
import dev.sorn.fmp4j.types.FmpPeriod;
import dev.sorn.fmp4j.types.FmpYear;

public class FmpBulkClient {

    // Alphabetical order
    protected final FmpService<FmpCompanies[]> fmpBulkCompaniesService;
    protected final FmpService<FmpBalanceSheetStatement[]> fmpBulkBalanceSheetService;
    protected final FmpService<FmpCashFlowStatement[]> fmpBulkCashFlowService;
    protected final FmpService<FmpCashFlowStatementGrowth[]> fmpBulkCashFlowStatementGrowthService;

    public FmpBulkClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.fmpBulkCompaniesService = new FmpBulkCompaniesService(fmpConfig, fmpHttpClient);
        this.fmpBulkBalanceSheetService = new FmpBulkBalanceSheetStatementService(fmpConfig, fmpHttpClient);
        this.fmpBulkCashFlowService = new FmpBulkCashFlowStatementService(fmpConfig, fmpHttpClient);
        this.fmpBulkCashFlowStatementGrowthService =
                new FmpBulkCashFlowStatementGrowthService(fmpConfig, fmpHttpClient);
    }

    public synchronized FmpCompanies[] companies(FmpPart part) {
        fmpBulkCompaniesService.param("part", part);
        return fmpBulkCompaniesService.download();
    }

    public synchronized FmpBalanceSheetStatement[] balanceSheetStatements(FmpYear year, FmpPeriod period) {
        fmpBulkBalanceSheetService.param(PARAM_YEAR, year);
        fmpBulkBalanceSheetService.param(PARAM_PERIOD, period);
        return fmpBulkBalanceSheetService.download();
    }

    public synchronized FmpCashFlowStatement[] cashFlowStatements(FmpYear year, FmpPeriod period) {
        fmpBulkCashFlowService.param(PARAM_YEAR, year);
        fmpBulkCashFlowService.param(PARAM_PERIOD, period);
        return fmpBulkCashFlowService.download();
    }

    public synchronized FmpCashFlowStatementGrowth[] cashFlowStatementGrowth(FmpYear year, FmpPeriod period) {
        fmpBulkCashFlowStatementGrowthService.param(PARAM_YEAR, year);
        fmpBulkCashFlowStatementGrowthService.param(PARAM_PERIOD, period);
        return fmpBulkCashFlowStatementGrowthService.download();
    }
}

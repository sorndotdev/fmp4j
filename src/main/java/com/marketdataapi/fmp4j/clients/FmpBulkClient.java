package com.marketdataapi.fmp4j.clients;

import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_PERIOD;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_YEAR;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpBalanceSheetStatement;
import com.marketdataapi.fmp4j.models.FmpCashFlowStatement;
import com.marketdataapi.fmp4j.models.FmpCashFlowStatementGrowth;
import com.marketdataapi.fmp4j.models.FmpCompanies;
import com.marketdataapi.fmp4j.services.FmpBulkBalanceSheetStatementService;
import com.marketdataapi.fmp4j.services.FmpBulkCashFlowStatementGrowthService;
import com.marketdataapi.fmp4j.services.FmpBulkCashFlowStatementService;
import com.marketdataapi.fmp4j.services.FmpBulkCompaniesService;
import com.marketdataapi.fmp4j.services.FmpService;
import com.marketdataapi.fmp4j.types.FmpPart;
import com.marketdataapi.fmp4j.types.FmpPeriod;
import com.marketdataapi.fmp4j.types.FmpYear;
import java.util.List;

public class FmpBulkClient {

    // Alphabetical order
    protected final FmpService<FmpCompanies> fmpBulkCompaniesService;
    protected final FmpService<FmpBalanceSheetStatement> fmpBulkBalanceSheetService;
    protected final FmpService<FmpCashFlowStatement> fmpBulkCashFlowService;
    protected final FmpService<FmpCashFlowStatementGrowth> fmpBulkCashFlowStatementGrowthService;

    public FmpBulkClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.fmpBulkCompaniesService = new FmpBulkCompaniesService(fmpConfig, fmpHttpClient);
        this.fmpBulkBalanceSheetService = new FmpBulkBalanceSheetStatementService(fmpConfig, fmpHttpClient);
        this.fmpBulkCashFlowService = new FmpBulkCashFlowStatementService(fmpConfig, fmpHttpClient);
        this.fmpBulkCashFlowStatementGrowthService =
                new FmpBulkCashFlowStatementGrowthService(fmpConfig, fmpHttpClient);
    }

    public synchronized List<FmpCompanies> companies(FmpPart part) {
        fmpBulkCompaniesService.param("part", part);
        return fmpBulkCompaniesService.download();
    }

    public synchronized List<FmpBalanceSheetStatement> balanceSheetStatements(FmpYear year, FmpPeriod period) {
        fmpBulkBalanceSheetService.param(PARAM_YEAR, year);
        fmpBulkBalanceSheetService.param(PARAM_PERIOD, period);
        return fmpBulkBalanceSheetService.download();
    }

    public synchronized List<FmpCashFlowStatement> cashFlowStatements(FmpYear year, FmpPeriod period) {
        fmpBulkCashFlowService.param(PARAM_YEAR, year);
        fmpBulkCashFlowService.param(PARAM_PERIOD, period);
        return fmpBulkCashFlowService.download();
    }

    public synchronized List<FmpCashFlowStatementGrowth> cashFlowStatementGrowth(FmpYear year, FmpPeriod period) {
        fmpBulkCashFlowStatementGrowthService.param(PARAM_YEAR, year);
        fmpBulkCashFlowStatementGrowthService.param(PARAM_PERIOD, period);
        return fmpBulkCashFlowStatementGrowthService.download();
    }
}

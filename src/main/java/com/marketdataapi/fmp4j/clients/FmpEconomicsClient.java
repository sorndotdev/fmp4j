package com.marketdataapi.fmp4j.clients;

import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_FROM;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_TO;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpTreasuryRate;
import com.marketdataapi.fmp4j.services.FmpService;
import com.marketdataapi.fmp4j.services.FmpTreasuryRatesService;
import java.time.LocalDate;
import java.util.List;

public class FmpEconomicsClient {
    protected final FmpService<FmpTreasuryRate> fmpTreasuryRatesService;

    public FmpEconomicsClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.fmpTreasuryRatesService = new FmpTreasuryRatesService(fmpConfig, fmpHttpClient);
    }

    public synchronized List<FmpTreasuryRate> treasuryRates(LocalDate from, LocalDate to) {
        fmpTreasuryRatesService.param(PARAM_FROM, from);
        fmpTreasuryRatesService.param(PARAM_TO, to);
        return fmpTreasuryRatesService.download();
    }
}

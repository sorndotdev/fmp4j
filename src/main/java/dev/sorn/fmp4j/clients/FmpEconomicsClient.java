package dev.sorn.fmp4j.clients;

import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_FROM;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_TO;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpTreasuryRate;
import dev.sorn.fmp4j.services.FmpService;
import dev.sorn.fmp4j.services.FmpTreasuryRatesService;
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

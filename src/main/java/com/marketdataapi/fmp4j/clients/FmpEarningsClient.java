package com.marketdataapi.fmp4j.clients;

import static com.marketdataapi.fmp4j.types.FmpLimit.limit;
import static com.marketdataapi.fmp4j.types.FmpPage.page;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_PAGE;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_QUARTER;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static com.marketdataapi.fmp4j.utils.FmpParameters.PARAM_YEAR;

import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClient;
import com.marketdataapi.fmp4j.models.FmpEarningsCallTranscript;
import com.marketdataapi.fmp4j.models.FmpEarningsCallTranscriptDate;
import com.marketdataapi.fmp4j.models.FmpEarningsCallTranscriptLatest;
import com.marketdataapi.fmp4j.models.FmpEarningsCallTranscriptList;
import com.marketdataapi.fmp4j.services.FmpEarningsCallTranscriptDatesService;
import com.marketdataapi.fmp4j.services.FmpEarningsCallTranscriptLatestService;
import com.marketdataapi.fmp4j.services.FmpEarningsCallTranscriptListService;
import com.marketdataapi.fmp4j.services.FmpEarningsCallTranscriptService;
import com.marketdataapi.fmp4j.services.FmpService;
import com.marketdataapi.fmp4j.types.FmpLimit;
import com.marketdataapi.fmp4j.types.FmpPage;
import com.marketdataapi.fmp4j.types.FmpQuarter;
import com.marketdataapi.fmp4j.types.FmpSymbol;
import com.marketdataapi.fmp4j.types.FmpYear;
import java.util.List;
import java.util.Optional;

public class FmpEarningsClient {

    // Alphabetical order
    protected final FmpService<FmpEarningsCallTranscript> fmpEarningsCallTranscriptService;
    protected final FmpService<FmpEarningsCallTranscriptDate> fmpEarningsCallTranscriptDatesService;
    protected final FmpService<FmpEarningsCallTranscriptLatest> fmpEarningsCallTranscriptLatestService;
    protected final FmpService<FmpEarningsCallTranscriptList> fmpEarningsCallTranscriptListService;

    public FmpEarningsClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this.fmpEarningsCallTranscriptService = new FmpEarningsCallTranscriptService(fmpConfig, fmpHttpClient);
        this.fmpEarningsCallTranscriptDatesService =
                new FmpEarningsCallTranscriptDatesService(fmpConfig, fmpHttpClient);
        this.fmpEarningsCallTranscriptLatestService =
                new FmpEarningsCallTranscriptLatestService(fmpConfig, fmpHttpClient);
        this.fmpEarningsCallTranscriptListService = new FmpEarningsCallTranscriptListService(fmpConfig, fmpHttpClient);
    }

    public synchronized List<FmpEarningsCallTranscript> transcripts(
            FmpSymbol symbol, FmpYear year, FmpQuarter quarter, Optional<FmpLimit> limit) {
        fmpEarningsCallTranscriptService.param(PARAM_SYMBOL, symbol);
        fmpEarningsCallTranscriptService.param(PARAM_YEAR, year);
        fmpEarningsCallTranscriptService.param(PARAM_QUARTER, quarter);
        limit.ifPresent(l -> fmpEarningsCallTranscriptService.param(PARAM_LIMIT, l));
        return fmpEarningsCallTranscriptService.download();
    }

    public synchronized List<FmpEarningsCallTranscriptDate> dates(FmpSymbol symbol) {
        fmpEarningsCallTranscriptDatesService.param(PARAM_SYMBOL, symbol);
        return fmpEarningsCallTranscriptDatesService.download();
    }

    public synchronized List<FmpEarningsCallTranscriptLatest> latest(Optional<FmpLimit> limit, Optional<FmpPage> page) {
        fmpEarningsCallTranscriptLatestService.param(PARAM_LIMIT, limit.orElse(limit(100)));
        fmpEarningsCallTranscriptLatestService.param(PARAM_PAGE, page.orElse(page(0)));
        return fmpEarningsCallTranscriptLatestService.download();
    }

    public synchronized List<FmpEarningsCallTranscriptList> list() {
        return fmpEarningsCallTranscriptListService.download();
    }
}

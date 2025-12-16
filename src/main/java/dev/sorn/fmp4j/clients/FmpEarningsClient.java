package dev.sorn.fmp4j.clients;

import static dev.sorn.fmp4j.types.FmpLimit.limit;
import static dev.sorn.fmp4j.types.FmpPage.page;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_LIMIT;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_PAGE;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_QUARTER;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_SYMBOL;
import static dev.sorn.fmp4j.utils.FmpParameters.PARAM_YEAR;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.http.FmpHttpClient;
import dev.sorn.fmp4j.models.FmpEarningsCallTranscript;
import dev.sorn.fmp4j.models.FmpEarningsCallTranscriptDate;
import dev.sorn.fmp4j.models.FmpEarningsCallTranscriptLatest;
import dev.sorn.fmp4j.models.FmpEarningsCallTranscriptList;
import dev.sorn.fmp4j.services.FmpEarningsCallTranscriptDatesService;
import dev.sorn.fmp4j.services.FmpEarningsCallTranscriptLatestService;
import dev.sorn.fmp4j.services.FmpEarningsCallTranscriptListService;
import dev.sorn.fmp4j.services.FmpEarningsCallTranscriptService;
import dev.sorn.fmp4j.services.FmpService;
import dev.sorn.fmp4j.types.FmpLimit;
import dev.sorn.fmp4j.types.FmpPage;
import dev.sorn.fmp4j.types.FmpQuarter;
import dev.sorn.fmp4j.types.FmpSymbol;
import dev.sorn.fmp4j.types.FmpYear;
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

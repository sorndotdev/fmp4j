package dev.sorn.fmp4j;

import static dev.sorn.fmp4j.http.FmpHttpClientImpl.FMP_HTTP_CLIENT;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.cfg.FmpConfigImpl;
import dev.sorn.fmp4j.clients.FmpBulkClient;
import dev.sorn.fmp4j.clients.FmpCalendarClient;
import dev.sorn.fmp4j.clients.FmpChartClient;
import dev.sorn.fmp4j.clients.FmpCompanyClient;
import dev.sorn.fmp4j.clients.FmpDirectoryClient;
import dev.sorn.fmp4j.clients.FmpEarningsCallTranscriptClient;
import dev.sorn.fmp4j.clients.FmpEconomicsClient;
import dev.sorn.fmp4j.clients.FmpEtfClient;
import dev.sorn.fmp4j.clients.FmpNewsClient;
import dev.sorn.fmp4j.clients.FmpQuoteClient;
import dev.sorn.fmp4j.clients.FmpSearchClient;
import dev.sorn.fmp4j.clients.FmpSecFilingsSearchClient;
import dev.sorn.fmp4j.clients.FmpStatementClient;
import dev.sorn.fmp4j.http.FmpHttpClient;

public class FmpClient {
    protected final FmpConfig fmpConfig;
    protected final FmpHttpClient fmpHttpClient;

    // Alphabetical order
    protected final FmpBulkClient fmpBulkClient;
    protected final FmpCalendarClient fmpCalendarClient;
    protected final FmpChartClient fmpChartClient;
    protected final FmpCompanyClient fmpCompanyClient;
    protected final FmpDirectoryClient fmpDirectoryClient;
    protected final FmpEarningsCallTranscriptClient fmpEarningsCallTranscriptClient;
    protected final FmpEconomicsClient fmpEconomicsClient;
    protected final FmpEtfClient fmpEtfClient;
    protected final FmpNewsClient fmpNewsClient;
    protected final FmpQuoteClient fmpQuoteClient;
    protected final FmpSearchClient fmpSearchClient;
    protected final FmpSecFilingsSearchClient fmpSecFilingsSearchClient;
    protected final FmpStatementClient fmpStatementClient;

    public FmpClient() {
        this(new FmpConfigImpl(), FMP_HTTP_CLIENT);
    }

    public FmpClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this(
                fmpConfig,
                fmpHttpClient,

                // Alphabetical order
                new FmpBulkClient(fmpConfig, fmpHttpClient),
                new FmpCalendarClient(fmpConfig, fmpHttpClient),
                new FmpChartClient(fmpConfig, fmpHttpClient),
                new FmpCompanyClient(fmpConfig, fmpHttpClient),
                new FmpDirectoryClient(fmpConfig, fmpHttpClient),
                new FmpEarningsCallTranscriptClient(fmpConfig, fmpHttpClient),
                new FmpEconomicsClient(fmpConfig, fmpHttpClient),
                new FmpEtfClient(fmpConfig, fmpHttpClient),
                new FmpNewsClient(fmpConfig, fmpHttpClient),
                new FmpQuoteClient(fmpConfig, fmpHttpClient),
                new FmpSearchClient(fmpConfig, fmpHttpClient),
                new FmpSecFilingsSearchClient(fmpConfig, fmpHttpClient),
                new FmpStatementClient(fmpConfig, fmpHttpClient));
    }

    public FmpClient(
            FmpConfig fmpConfig,
            FmpHttpClient fmpHttpClient,

            // Alphabetical order
            FmpBulkClient fmpBulkClient,
            FmpCalendarClient fmpCalendarClient,
            FmpChartClient fmpChartClient,
            FmpCompanyClient fmpCompanyClient,
            FmpDirectoryClient fmpDirectoryClient,
            FmpEarningsCallTranscriptClient fmpEarningsCallTranscriptClient,
            FmpEconomicsClient fmpEconomicsClient,
            FmpEtfClient fmpEtfClient,
            FmpNewsClient fmpNewsClient,
            FmpQuoteClient fmpQuoteClient,
            FmpSearchClient fmpSearchClient,
            FmpSecFilingsSearchClient fmpSecFilingsSearchClient,
            FmpStatementClient fmpStatementClient) {
        FmpEtfClient fmpEtfClient1;
        // Alphabetical order
        this.fmpConfig = fmpConfig;
        this.fmpHttpClient = fmpHttpClient;
        this.fmpBulkClient = fmpBulkClient;
        this.fmpCalendarClient = fmpCalendarClient;
        this.fmpChartClient = fmpChartClient;
        this.fmpCompanyClient = fmpCompanyClient;
        this.fmpDirectoryClient = fmpDirectoryClient;
        this.fmpEarningsCallTranscriptClient = fmpEarningsCallTranscriptClient;
        fmpEtfClient1 = fmpEtfClient;
        this.fmpEtfClient = fmpEtfClient1;
        this.fmpEconomicsClient = fmpEconomicsClient;
        this.fmpNewsClient = fmpNewsClient;
        this.fmpQuoteClient = fmpQuoteClient;
        this.fmpSecFilingsSearchClient = fmpSecFilingsSearchClient;
        this.fmpSearchClient = fmpSearchClient;
        this.fmpStatementClient = fmpStatementClient;
    }

    public FmpBulkClient bulk() {
        return fmpBulkClient;
    }

    public FmpCalendarClient calendar() {
        return fmpCalendarClient;
    }

    public FmpChartClient chart() {
        return fmpChartClient;
    }

    public FmpCompanyClient company() {
        return fmpCompanyClient;
    }

    public FmpDirectoryClient directory() {
        return fmpDirectoryClient;
    }

    public FmpEarningsCallTranscriptClient latestEarningsCallTranscript() {
        return fmpEarningsCallTranscriptClient;
    }

    public FmpEconomicsClient economics() {
        return fmpEconomicsClient;
    }

    public FmpEtfClient etf() {
        return fmpEtfClient;
    }

    public FmpNewsClient news() {
        return fmpNewsClient;
    }

    public FmpQuoteClient quote() {
        return fmpQuoteClient;
    }

    public FmpSearchClient search() {
        return fmpSearchClient;
    }

    public FmpSecFilingsSearchClient secFilingsSearch() {
        return fmpSecFilingsSearchClient;
    }

    public FmpStatementClient statement() {
        return fmpStatementClient;
    }
}

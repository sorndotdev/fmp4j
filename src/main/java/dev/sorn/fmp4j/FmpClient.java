package dev.sorn.fmp4j;

import dev.sorn.fmp4j.cfg.FmpConfig;
import dev.sorn.fmp4j.clients.FmpCalendarClient;
import dev.sorn.fmp4j.clients.FmpChartClient;
import dev.sorn.fmp4j.clients.FmpCompanyClient;
import dev.sorn.fmp4j.clients.FmpDirectoryClient;
import dev.sorn.fmp4j.clients.FmpEtfClient;
import dev.sorn.fmp4j.clients.FmpQuoteClient;
import dev.sorn.fmp4j.clients.FmpSearchClient;
import dev.sorn.fmp4j.clients.FmpStatementClient;
import dev.sorn.fmp4j.http.FmpHttpClient;

import static dev.sorn.fmp4j.cfg.FmpConfigImpl.FMP_CONFIG;
import static dev.sorn.fmp4j.http.FmpHttpClientImpl.FMP_HTTP_CLIENT;

public class FmpClient {
    protected final FmpConfig fmpConfig;
    protected final FmpHttpClient fmpHttpClient;

    // Search
    protected final FmpSearchClient fmpSearchClient;

    // Directory
    protected final FmpDirectoryClient fmpDirectoryClient;

    // Calendar
    protected final FmpCalendarClient fmpCalendarClient;

    // Chart
    protected final FmpChartClient fmpChartClient;

    // Company
    protected final FmpCompanyClient fmpCompanyClient;

    // Statements
    protected final FmpStatementClient fmpStatementClient;

    // ETF & Mutual Funds
    protected final FmpEtfClient fmpEtfClient;

    // Quotes
    protected final FmpQuoteClient fmpQuoteClient;

    public FmpClient() {
        this(FMP_CONFIG, FMP_HTTP_CLIENT);
    }

    public FmpClient(FmpConfig fmpConfig, FmpHttpClient fmpHttpClient) {
        this(
                fmpConfig,
                fmpHttpClient,

                new FmpSearchClient(fmpConfig, fmpHttpClient),
                new FmpDirectoryClient(fmpConfig, fmpHttpClient),
                new FmpCalendarClient(fmpConfig, fmpHttpClient),
                new FmpChartClient(fmpConfig, fmpHttpClient),
                new FmpCompanyClient(fmpConfig, fmpHttpClient),
                new FmpStatementClient(fmpConfig, fmpHttpClient),
                new FmpEtfClient(fmpConfig, fmpHttpClient),
                new FmpQuoteClient(fmpConfig, fmpHttpClient)
        );
    }

    public FmpClient(
            FmpConfig fmpConfig,
            FmpHttpClient fmpHttpClient,

            FmpSearchClient fmpSearchClient,
            FmpDirectoryClient fmpDirectoryClient,
            FmpCalendarClient fmpCalendarClient,
            FmpChartClient fmpChartClient,
            FmpCompanyClient fmpCompanyClient,
            FmpStatementClient fmpStatementClient,
            FmpEtfClient fmpEtfClient,
            FmpQuoteClient fmpQuoteClient
    ) {
        this.fmpConfig = fmpConfig;
        this.fmpHttpClient = fmpHttpClient;
        this.fmpSearchClient = fmpSearchClient;
        this.fmpDirectoryClient = fmpDirectoryClient;
        this.fmpCalendarClient = fmpCalendarClient;
        this.fmpChartClient = fmpChartClient;
        this.fmpCompanyClient = fmpCompanyClient;
        this.fmpStatementClient = fmpStatementClient;
        this.fmpEtfClient = fmpEtfClient;
        this.fmpQuoteClient = fmpQuoteClient;
    }


    public FmpSearchClient search() {
        return fmpSearchClient;
    }

    public FmpDirectoryClient list() {
        return fmpDirectoryClient;
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

    public FmpStatementClient statement() {
        return fmpStatementClient;
    }

    public FmpEtfClient etf() {
        return fmpEtfClient;
    }

    public FmpQuoteClient quote() {
        return fmpQuoteClient;
    }
}

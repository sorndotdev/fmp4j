![](../.github/badges/jacoco.svg)

```txt
╔───────────────────────────────────────────╗
│                                           │
│                                           │
│       _|                    |  |   _)     │
│      |    __ `__ \   __ \   |  |    |     │
│      __|  |   |   |  |   | ___ __|  |     │
│     _|   _|  _|  _|  .__/     _|    |     │
│                     _|          ___/      │
│                                           │
│                                           │
╚───────────────────────────────────────────╝
```

# fmp4j

**fmp4j** is a lightweight Java SDK for accessing the [Financial Modeling Prep (FMP)](https://site.financialmodelingprep.com/) API.

> **DO NOT CONTACT FOR API SUPPORT — WE ARE NOT AFFILIATED WITH FMP.**<br>
> This is an open-source Java SDK that wraps their API.<br>
> Please note: their API contains many inconsistencies, poor naming conventions, and is not RESTful.<br>
> This SDK does *not* attempt to fix or abstract any of these issues.

Go to [www.marketdataapi.com](https://www.marketdataapi.com) for more information.

## Usage

**Gradle (build.gradle)**

```groovy
implementation 'com.marketdataapi:fmp4j:0.100.0'
```

**Maven (pom.xml)**

```xml

<dependency>
    <groupId>com.marketdataapi</groupId>
    <artifactId>fmp4j</artifactId>
    <version>0.100.0</version>
</dependency>
```

### Configuration

Set environment variables

```sh
FMP_API_KEY=yourfmpapikey
FMP_BASE_URL=https://financialmodelingprep.com/stable
```

#### Spring Boot Bean

```java

@Configuration
public class FmpConfig {
    @Bean
    public FmpClient fmpClient() {
        return new FmpClient();
    }
}
```

## Supported Endpoints (Stable API)

The section names, ordering, and endpoints are copy-pasted from FMP docs.

### Search

Copied from [/developer/docs/stable#search](https://site.financialmodelingprep.com/developer/docs/stable#search)

|    |  Since  | Endpoint                    |
|:--:|:-------:|-----------------------------|
| ✅️ | 0.100.0 | `/search-symbol`            |
| ✅️ | 0.100.0 | `/search-name`              |
| ✅️ | 0.100.0 | `/search-cik`               |
| ✅️ | 0.100.0 | `/search-cusip`             |
| ✅️ | 0.100.0 | `/search-isin`              |
| ❌️ |    -    | `/search-exchange-variants` |
| ❌️ |    -    | `/company-screener`         |

### Directory

Copied from [/developer/docs/stable#directory](https://site.financialmodelingprep.com/developer/docs/stable#directory)

|    |  Since  | Endpoint                           |
|:--:|:-------:|------------------------------------|
| ✅️ | 0.100.0 | `/stock-list`                      |
| ❌️ |    -    | `/financial-statement-symbol-list` |
| ❌️ |    -    | `/cik-list`                        |
| ❌️ |    -    | `/symbol-change`                   |
| ✅️ | 0.100.0 | `/etf-list`                        |
| ❌️ |    -    | `/actively-trading-list`           |
| ✅️ | 0.100.0 | `/earnings-transcript-list`        |
| ❌️ |    -    | `/available-exchanges`             |
| ❌️ |    -    | `/available-sectors`               |
| ❌️ |    -    | `/available-industries`            |
| ❌️ |    -    | `/available-countries`             |

### Analyst

Copied from [/developer/docs/stable#analyst](https://site.financialmodelingprep.com/developer/docs/stable#analyst)

|    | Since | Endpoint                    |
|:--:|:-----:|-----------------------------|
| ❌️ |   -   | `/analyst-estimates`        |
| ❌️ |   -   | `/ratings-snapshot`         |
| ❌️ |   -   | `/ratings-historical`       |
| ❌️ |   -   | `/price-target-summary`     |
| ❌️ |   -   | `/price-target-consensus`   |
| ❌️ |   -   | `/price-target-news`        |
| ❌️ |   -   | `/price-target-latest-news` |
| ❌️ |   -   | `/grades`                   |
| ❌️ |   -   | `/grades-historical`        |
| ❌️ |   -   | `/grades-consensus`         |
| ❌️ |   -   | `/grades-news`              |
| ❌️ |   -   | `/grades-latest-news`       |

### Calendar

Copied from [/developer/docs/stable#calendar](https://site.financialmodelingprep.com/developer/docs/stable#calendar)

|     |  Since  | Endpoint              |
|:---:|:-------:|-----------------------|
| ✅️  | 0.100.0 | `/dividends`          |
| ✅️  | 0.100.0 | `/dividends-calendar` |
| ✅️  | 0.100.0 | `/earnings`           |
| ✅️  | 0.100.0 | `/earnings-calendar`  |
| ✅️  | 0.100.0 | `/ipos-calendar`      |
| ✅️️ | 0.100.0 | `/ipos-disclosure`    |
| ✅️️ | 0.100.0 | `/ipos-prospectus`    |
| ❌️  |    -    | `/splits`             |
| ❌️  |    -    | `/splits-calendar`    |

### Chart

Copied from [/developer/docs/stable#chart](https://site.financialmodelingprep.com/developer/docs/stable#chart)

|    |  Since  | Endpoint                                   |
|:--:|:-------:|--------------------------------------------|
| ✅️ | 0.100.0 | `/historical-price-eod/light`              |
| ✅️ | 0.100.0 | `/historical-price-eod/full`               |
| ❌️ |    -    | `/historical-price-eod/non-split-adjusted` |
| ❌️ |    -    | `/historical-price-eod/dividend-adjusted`  |
| ✅️ | 0.100.0 | `/historical-chart/1min`                   |
| ✅️ | 0.100.0 | `/historical-chart/5min`                   |
| ✅️ | 0.100.0 | `/historical-chart/15min`                  |
| ✅️ | 0.100.0 | `/historical-chart/30min`                  |
| ✅️ | 0.100.0 | `/historical-chart/1hour`                  |
| ✅️ | 0.100.0 | `/historical-chart/4hour`                  |

### Company

Copied from [/developer/docs/stable#company](https://site.financialmodelingprep.com/developer/docs/stable#company)

|    |  Since  | Endpoint                             |
|:--:|:-------:|--------------------------------------|
| ✅️ | 0.100.0 | `/profile`                           |
| ❌️ |    -    | `/profile-cik`                       |
| ❌️ |    -    | `/company-notes`                     |
| ❌️ |    -    | `/stock-peers`                       |
| ❌️ |    -    | `/delisted-companies`                |
| ❌️ |    -    | `/employee-count`                    |
| ❌️ |    -    | `/historical-employee-count`         |
| ❌️ |    -    | `/market-capitalization`             |
| ❌️ |    -    | `/market-capitalization`             |
| ❌️ |    -    | `/market-capitalization-batch`       |
| ❌️ |    -    | `/historical-market-capitalization`  |
| ❌️ |    -    | `/shares-float`                      |
| ❌️ |    -    | `/shares-float-all`                  |
| ❌️ |    -    | `/mergers-acquisitions-latest`       |
| ❌️ |    -    | `/mergers-acquisitions-search`       |
| ❌️ |    -    | `/key-executives`                    |
| ❌️ |    -    | `/governance-executive-compensation` |
| ❌️ |    -    | `/executive-compensation-benchmark`  |

### Commitment Of Traders

Copied
from [/developer/docs/stable#commitment-of-traders](https://site.financialmodelingprep.com/developer/docs/stable#commitment-of-traders)

|    | Since | Endpoint                          |
|:--:|:-----:|-----------------------------------|
| ❌️ |   -   | `/commitment-of-traders-report`   |
| ❌️ |   -   | `/commitment-of-traders-analysis` |
| ❌️ |   -   | `/commitment-of-traders-list`     |

### Economics

Copied from [/developer/docs/stable#economics](https://site.financialmodelingprep.com/developer/docs/stable#economics)

|    |  Since  | Endpoint               |
|:--:|:-------:|------------------------|
| ✅️ | 0.100.0 | `/treasury-rates`      |
| ❌️ |    -    | `/economic-indicators` |
| ❌️ |    -    | `/economic-calendar`   |
| ❌️ |    -    | `/market-risk-premium` |

### ESG

Copied from [/developer/docs/stable#esg](https://site.financialmodelingprep.com/developer/docs/stable#esg)

|    | Since | Endpoint           |
|:--:|:-----:|--------------------|
| ❌️ |   -   | `/esg-disclosures` |
| ❌️ |   -   | `/esg-ratins`      |
| ❌️ |   -   | `/esg-benchmark`   |

Copy of ### Discounted Cash Flow

Copied
from [/developer/docs/stable#discounted-cash-flow](https://site.financialmodelingprep.com/developer/docs/stable#discounted-cash-flow)

|    | Since | Endpoint                               |
|:--:|:-----:|----------------------------------------|
| ❌️ |   -   | `/discounted-cash-flow`                |
| ❌️ |   -   | `/levered-discounted-cash-flow`        |
| ❌️ |   -   | `/custom-discounted-cash-flow`         |
| ❌️ |   -   | `/custom-levered-discounted-cash-flow` |

### Statements

Copied from [/developer/docs/stable#statements](https://site.financialmodelingprep.com/developer/docs/stable#statements)

|    |  Since  | Endpoint                                |
|:--:|:-------:|-----------------------------------------|
| ❌️ |    -    | `/latest-financial-statements`          |
| ❌️ |    -    | `/financial-statement-full-as-reported` |
| ✅️ | 0.100.0 | `/income-statement`                     |
| ✅️ | 0.100.0 | `/income-statement-ttm`                 |
| ✅️ | 0.100.0 | `/income-statement-growth`              |
| ✅️ | 0.100.0 | `/income-statement-growth-as-reported`  |
| ✅️ | 0.100.0 | `/balance-sheet-statement`              |
| ✅️ | 0.100.0 | `/balance-sheet-statement-ttm`          |
| ✅️ | 0.100.0 | `/balance-sheet-statement-growth`       |
| ✅️ | 0.100.0 | `/balance-sheet-statement-as-reported`  |
| ✅️ | 0.100.0 | `/cash-flow-statement`                  |
| ✅️ | 0.100.0 | `/cash-flow-statement-ttm`              |
| ✅️ | 0.100.0 | `/cash-flow-statement-growth`           |
| ✅️ | 0.100.0 | `/cash-flow-statement-as-reported`      |
| ✅️ | 0.100.0 | `/financial-growth`                     |
| ✅️ | 0.100.0 | `/ratios`                               |
| ✅️ | 0.100.0 | `/ratios-ttm`                           |
| ✅️ | 0.100.0 | `/key-metrics`                          |
| ✅️ | 0.100.0 | `/key-metrics-ttm`                      |
| ❌️ |    -    | `/financial-scores`                     |
| ❌️ |    -    | `/owner-earnings`                       |
| ✅️ | 0.100.0 | `/enterprise-values`                    |
| ✅️ | 0.100.0 | `/revenue-product-segmentation`         |
| ✅️ | 0.100.0 | `/revenue-geographic-segmentation`      |

### Form 13F

Copied from [/developer/docs/stable#form-13f](https://site.financialmodelingprep.com/developer/docs/stable#form-13f)

|    | Since | Endpoint                                              |
|:--:|:-----:|-------------------------------------------------------|
| ❌️ |   -   | `/institutional-ownership/latest`                     |
| ❌️ |   -   | `/institutional-ownership/extract`                    |
| ❌️ |   -   | `/institutional-ownership/extract-analytics/holder`   |
| ❌️ |   -   | `/institutional-ownership/holder-performance-summary` |
| ❌️ |   -   | `/institutional-ownership/holder-industry-breakdown`  |
| ❌️ |   -   | `/institutional-ownership/symbols-positions-summary`  |
| ❌️ |   -   | `/institutional-ownership/industry-summary`           |

### Indexes

Copied from [/developer/docs/stable#indexes](https://site.financialmodelingprep.com/developer/docs/stable#indexes)

|    |  Since  | Endpoint                           |
|:--:|:-------:|------------------------------------|
| ❌️ |    -    | `/index-list`                      |
| ✅️ | 0.100.0 | `/quote`                           |
| ✅️ | 0.100.0 | `/quote-short`                     |
| ❌️ |    -    | `/batch-index-quotes`              |
| ✅️ | 0.100.0 | `/historical-price-eod/light`      |
| ✅️ | 0.100.0 | `/historical-price-eod/full`       |
| ✅️ | 0.100.0 | `/historical-chart/1min`           |
| ✅️ | 0.100.0 | `/historical-chart/5min`           |
| ✅️ | 0.100.0 | `/historical-chart/1hour`          |
| ❌️ |    -    | `/sp500-constituent`               |
| ❌️ |    -    | `/nasdaq-constituent`              |
| ❌️ |    -    | `/dowjones-constituent`            |
| ❌️ |    -    | `/historical-sp500-constituent`    |
| ❌️ |    -    | `/histoircal-nasdaq-constituent`   |
| ❌️ |    -    | `/historical-dowjones-constituent` |

### Insider Trades

Copied
from [/developer/docs/stable#insider-trades](https://site.financialmodelingprep.com/developer/docs/stable#insider-trades)

|    | Since | Endpoint                                |
|:--:|:-----:|-----------------------------------------|
| ❌️ |   -   | `/insider-trading`                      |
| ❌️ |   -   | `/insider-trading/reporting-name`       |
| ❌️ |   -   | `/insider-trading/search`               |
| ❌️ |   -   | `/insider-trading/statistics`           |
| ❌️ |   -   | `/insider-trading-transaction-type`     |
| ❌️ |   -   | `/acquisitions-of-beneficial-ownership` |

### Market Performance

Copied
from [/developer/docs/stable#market-performance](https://site.financialmodelingprep.com/developer/docs/stable#market-performance)

|    | Since | Endpoint                           |
|:--:|:-----:|------------------------------------|
| ❌️ |   -   | `/sector-performance-snapshot`     |
| ❌️ |   -   | `/industry-performance-snapshot`   |
| ❌️ |   -   | `/historical-sector-performance`   |
| ❌️ |   -   | `/historical-industry-performance` |
| ❌️ |   -   | `/sector-pe-snapshot`              |
| ❌️ |   -   | `/industry-pe-snapshot`            |
| ❌️ |   -   | `/historical-sector-pe`            |
| ❌️ |   -   | `/historical-industry-pe`          |
| ❌️ |   -   | `/biggest-gainers`                 |
| ❌️ |   -   | `/biggest-losers`                  |
| ❌️ |   -   | `/most-actives`                    |

### Market Hours

Copied
from [/developer/docs/stable#market-hours](https://site.financialmodelingprep.com/developer/docs/stable#market-hours)

|    | Since | Endpoint                     |
|:--:|:-----:|------------------------------|
| ❌️ |   -   | `/exchange-market-hours`     |
| ❌️ |   -   | `/holidays-by-exchange`      |
| ❌️ |   -   | `/all-exchange-market-hours` |

### ETF & Mutual Funds

Copied from [/developer/docs/stable#holdings](https://site.financialmodelingprep.com/developer/docs/stable#holdings)

|    |  Since  | Endpoint                           |
|:--:|:-------:|------------------------------------|
| ✅️ | 0.100.0 | `/etf/holdings`                    |
| ✅️ | 0.100.0 | `/etf/info`                        |
| ✅️ | 0.100.0 | `/etf/country-weightings`          |
| ✅️ | 0.100.0 | `/etf/asset-exposure`              |
| ✅️ | 0.100.0 | `/etf/sector-weightings`           |
| ❌️ |    -    | `/funds/disclosure`                |
| ❌️ |    -    | `/funds/disclosure-holders-latest` |
| ❌️ |    -    | `/funds/disclosure-holders-search` |
| ❌️ |    -    | `/funds/disclosure-dates`          |

### Commodities

Copied
from [/developer/docs/stable#commodities](https://site.financialmodelingprep.com/developer/docs/stable#commodities)

|    |  Since  | Endpoint                      |
|:--:|:-------:|-------------------------------|
| ❌️ |    -    | `/commodities-list`           |
| ✅️ | 0.100.0 | `/quote`                      |
| ✅️ | 0.100.0 | `/quote-short`                |
| ❌️ |    -    | `/batch-commodities-quotes`   |
| ✅️ | 0.100.0 | `/historical-price-eod/light` |
| ✅️ | 0.100.0 | `/historical-price-eod/full`  |
| ✅️ | 0.100.0 | `/historical-chart/1min`      |
| ✅️ | 0.100.0 | `/historical-chart/5min`      |
| ✅️ | 0.100.0 | `/historical-chart/1hour`     |

### Fundraisers

Copied
from [/developer/docs/stable#fundraisers](https://site.financialmodelingprep.com/developer/docs/stable#fundraisers)

|    | Since | Endpoint                         |
|:--:|:-----:|----------------------------------|
| ❌️ |   -   | `/crowdfunding-offerings-latest` |
| ❌️ |   -   | `/crowdfunding-offerings-search` |
| ❌️ |   -   | `/crowdfunding-offerings`        |
| ❌️ |   -   | `/fundraising-latest`            |
| ❌️ |   -   | `/fundraising-search`            |
| ❌️ |   -   | `/fundraising`                   |

### Crypto

Copied from [/developer/docs/stable#crypto](https://site.financialmodelingprep.com/developer/docs/stable#crypto)

|    |  Since  | Endpoint                      |
|:--:|:-------:|-------------------------------|
| ❌️ |    -    | `/cryptocurrency-list`        |
| ❌️ |    -    | `/batch-crypto-quotes`        |
| ✅️ | 0.100.0 | `/quote`                      |
| ✅️ | 0.100.0 | `/quote-short`                |
| ✅️ | 0.100.0 | `/historical-price-eod/light` |
| ✅️ | 0.100.0 | `/historical-price-eod/full`  |
| ✅️ | 0.100.0 | `/historical-chart/1min`      |
| ✅️ | 0.100.0 | `/historical-chart/5min`      |
| ✅️ | 0.100.0 | `/historical-chart/1hour`     |

### News

Copied from [/developer/docs/stable#news](https://site.financialmodelingprep.com/developer/docs/stable#news)

|    |  Since  | Endpoint                      |
|:--:|:-------:|-------------------------------|
| ❌️ |    -    | `/fmp-articles`               |
| ❌️ |    -    | `/news/general-latest`        |
| ❌️ |    -    | `/news/press-releases`        |
| ❌️ |    -    | `/news/press-releases-latest` |
| ✅️ | 0.100.0 | `/news/stock`                 |
| ❌️ |    -    | `/news/stock-latest`          |
| ✅️ | 0.100.0 | `/news/crypto`                |
| ❌️ |    -    | `/news/crypto-latest`         |
| ✅️ | 0.100.0 | `/news/forex`                 |
| ❌️ |    -    | `/news/forex-latest`          |

### Technical Indicators

Copied
from [/developer/docs/stable#technical-indicators](https://site.financialmodelingprep.com/developer/docs/stable#technical-indicators)

|    | Since | Endpoint                                  |
|:--:|:-----:|-------------------------------------------|
| ❌️ |   -   | `/technical-indicators-sma`               |
| ❌️ |   -   | `/technical-indicators-ema`               |
| ❌️ |   -   | `/technical-indicators-wma`               |
| ❌️ |   -   | `/technical-indicators-dema`              |
| ❌️ |   -   | `/technical-indicators-tema`              |
| ❌️ |   -   | `/technical-indicators-rsi`               |
| ❌️ |   -   | `/technical-indicators-standarddeviation` |
| ❌️ |   -   | `/technical-indicators-williams`          |
| ❌️ |   -   | `/technical-indicators-adx`               |

### Quote

Copied from [/developer/docs/stable#quote](https://site.financialmodelingprep.com/developer/docs/stable#quote)

|    |  Since  | Endpoint                   |
|:--:|:-------:|----------------------------|
| ✅️ | 0.100.0 | `/quote`                   |
| ✅️ | 0.100.0 | `/quote-short`             |
| ❌️ |    -    | `/aftermarket-trade`       |
| ❌️ |    -    | `/aftermarket-quote`       |
| ❌️ |    -    | `/stock-price-change`      |
| ❌️ |    -    | `/batch-quote`             |
| ❌️ |    -    | `/batch-quote-short`       |
| ❌️ |    -    | `/batch-aftermarket-trade` |
| ❌️ |    -    | `/batch-aftermarket-quote` |
| ❌️ |    -    | `/batch-exchange-quote`    |
| ❌️ |    -    | `/batch-mutaulfund-quotes` |
| ❌️ |    -    | `/batch-etf-quotes`        |
| ❌️ |    -    | `/batch-commodity-quotes`  |
| ❌️ |    -    | `/batch-crypto-quotes`     |
| ❌️ |    -    | `/batch-forex-quotes`      |
| ❌️ |    -    | `/batch-index-quotes`      |

### SEC Filings

Copied
from [/developer/docs/stable#sec-filings](https://site.financialmodelingprep.com/developer/docs/stable#sec-filings)

|    |  Since  | Endpoint                                   |
|:--:|:-------:|--------------------------------------------|
| ❌️ |    -    | `/sec-filings-8k`                          |
| ❌️ |    -    | `/sec-filings-financials`                  |
| ❌️ |    -    | `/sec-filings-search/form-type`            |
| ❌️ |    -    | `/sec-filings-search/cik`                  |
| ❌️ |    -    | `/sec-filings-company-search/name`         |
| ✅️ | 0.100.0 | `/sec-filings-company-search/symbol`       |
| ❌️ |    -    | `/sec-filings-company-search/cik`          |
| ❌️ |    -    | `/sec-profile`                             |
| ❌️ |    -    | `/standard-industrial-classification-list` |
| ❌️ |    -    | `/industry-classification-search`          |
| ❌️ |    -    | `/all-industry-classification`             |

### Earnings Transcript

Copied
from [/developer/docs/stable#earnings-transcript](https://site.financialmodelingprep.com/developer/docs/stable#earnings-transcript)

|    |  Since  | Endpoint                          |
|:--:|:-------:|-----------------------------------|
| ✅️ | 0.100.0 | `/earning-call-transcript-latest` |
| ✅️ | 0.100.0 | `/earning-call-transcript`        |
| ✅️ | 0.100.0 | `/earning-call-transcript-dates`  |
| ✅️ | 0.100.0 | `/earnings-transcript-list`       |

### Senate

Copied from [/developer/docs/stable#senate](https://site.financialmodelingprep.com/developer/docs/stable#senate)

|    | Since | Endpoint                 |
|:--:|:-----:|--------------------------|
| ❌️ |   -   | `/senate-latest`         |
| ❌️ |   -   | `/senate-trades`         |
| ❌️ |   -   | `/senate-trades-by-name` |
| ❌️ |   -   | `/house-latest`          |
| ❌️ |   -   | `/house-trades`          |
| ❌️ |   -   | `/house-trades-by-name`  |

### Bulk

Copied from [/developer/docs/stable#bulk](https://site.financialmodelingprep.com/developer/docs/stable#bulk)

|    |  Since  | Endpoint                               |
|:--:|:-------:|----------------------------------------|
| ✅️ | 0.100.0 | `/profile-bulk`                        |
| ❌️ |    -    | `/rating-bulk`                         |
| ❌️ |    -    | `/dcf-bulk`                            |
| ❌️ |    -    | `/scores-bulk`                         |
| ❌️ |    -    | `/price-target-summary-bulk`           |
| ❌️ |    -    | `/etf-holder-bulk`                     |
| ❌️ |    -    | `/upgrades-downgrades-consensus-bulk`  |
| ❌️ |    -    | `/key-metrics-ttm-bulk`                |
| ❌️ |    -    | `/ratios-ttm-bulk`                     |
| ❌️ |    -    | `/ratios-ttm-bulk`                     |
| ❌️ |    -    | `/peers-bulk`                          |
| ❌️ |    -    | `/earnings-surprises-bulk`             |
| ❌️ |    -    | `/income-statement-bulk`               |
| ❌️ |    -    | `/income-statement-growth-bulk`        |
| ✅️ | 0.100.0 | `/balance-sheet-statement-bulk`        |
| ❌️ |    -    | `/balance-sheet-statement-growth-bulk` |
| ❌️ |    -    | `/cash-flow-statement-bulk`            |
| ❌️ |    -    | `/cash-flow-statement-growth-bulk`     |
| ❌️ |    -    | `/eod-bulk`                            |

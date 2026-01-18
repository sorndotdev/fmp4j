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

**fmp4j** is a lightweight Java SDK for accessing
the [Financial Modeling Prep (FMP)](https://site.financialmodelingprep.com/) API.

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

|    | Endpoint                    |
|:--:|-----------------------------|
| ✅️ | `/search-symbol`            |
| ✅️ | `/search-name`              |
| ✅️ | `/search-cik`               |
| ✅️ | `/search-cusip`             |
| ✅️ | `/search-isin`              |
| ❌️ | `/search-exchange-variants` |
| ❌️ | `/company-screener`         |

### Directory

Copied from [/developer/docs/stable#directory](https://site.financialmodelingprep.com/developer/docs/stable#directory)

|    | Endpoint                           |
|:--:|------------------------------------|
| ✅️ | `/stock-list`                      |
| ❌️ | `/financial-statement-symbol-list` |
| ❌️ | `/cik-list`                        |
| ❌️ | `/symbol-change`                   |
| ✅️ | `/etf-list`                        |
| ❌️ | `/actively-trading-list`           |
| ✅️ | `/earnings-transcript-list`        |
| ❌️ | `/available-exchanges`             |
| ❌️ | `/available-sectors`               |
| ❌️ | `/available-industries`            |
| ❌️ | `/available-countries`             |

### Analyst

Copied from [/developer/docs/stable#analyst](https://site.financialmodelingprep.com/developer/docs/stable#analyst)

|    | Endpoint                    |
|:--:|-----------------------------|
| ❌️ | `/analyst-estimates`        |
| ❌️ | `/ratings-snapshot`         |
| ❌️ | `/ratings-historical`       |
| ❌️ | `/price-target-summary`     |
| ❌️ | `/price-target-consensus`   |
| ❌️ | `/price-target-news`        |
| ❌️ | `/price-target-latest-news` |
| ❌️ | `/grades`                   |
| ❌️ | `/grades-historical`        |
| ❌️ | `/grades-consensus`         |
| ❌️ | `/grades-news`              |
| ❌️ | `/grades-latest-news`       |

### Calendar

Copied from [/developer/docs/stable#calendar](https://site.financialmodelingprep.com/developer/docs/stable#calendar)

|     | Endpoint              |
|:---:|-----------------------|
| ✅️  | `/dividends`          |
| ✅️  | `/dividends-calendar` |
| ✅️  | `/earnings`           |
| ✅️  | `/earnings-calendar`  |
| ✅️  | `/ipos-calendar`      |
| ✅️️ | `/ipos-disclosure`    |
| ✅️️ | `/ipos-prospectus`    |
| ❌️  | `/splits`             |
| ❌️  | `/splits-calendar`    |

### Chart

Copied from [/developer/docs/stable#chart](https://site.financialmodelingprep.com/developer/docs/stable#chart)

|    | Endpoint                                   |
|:--:|--------------------------------------------|
| ✅️ | `/historical-price-eod/light`              |
| ✅️ | `/historical-price-eod/full`               |
| ❌️ | `/historical-price-eod/non-split-adjusted` |
| ❌️ | `/historical-price-eod/dividend-adjusted`  |
| ✅️ | `/historical-chart/1min`                   |
| ✅️ | `/historical-chart/5min`                   |
| ✅️ | `/historical-chart/15min`                  |
| ✅️ | `/historical-chart/30min`                  |
| ✅️ | `/historical-chart/1hour`                  |
| ✅️ | `/historical-chart/4hour`                  |

### Company

Copied from [/developer/docs/stable#company](https://site.financialmodelingprep.com/developer/docs/stable#company)

|    | Endpoint                             |
|:--:|--------------------------------------|
| ✅️ | `/profile`                           |
| ❌️ | `/profile-cik`                       |
| ❌️ | `/company-notes`                     |
| ❌️ | `/stock-peers`                       |
| ❌️ | `/delisted-companies`                |
| ❌️ | `/employee-count`                    |
| ❌️ | `/historical-employee-count`         |
| ❌️ | `/market-capitalization`             |
| ❌️ | `/market-capitalization`             |
| ❌️ | `/market-capitalization-batch`       |
| ❌️ | `/historical-market-capitalization`  |
| ❌️ | `/shares-float`                      |
| ❌️ | `/shares-float-all`                  |
| ❌️ | `/mergers-acquisitions-latest`       |
| ❌️ | `/mergers-acquisitions-search`       |
| ❌️ | `/key-executives`                    |
| ❌️ | `/governance-executive-compensation` |
| ❌️ | `/executive-compensation-benchmark`  |

### Commitment Of Traders

Copied
from [/developer/docs/stable#commitment-of-traders](https://site.financialmodelingprep.com/developer/docs/stable#commitment-of-traders)

|    | Endpoint                          |
|:--:|-----------------------------------|
| ❌️ | `/commitment-of-traders-report`   |
| ❌️ | `/commitment-of-traders-analysis` |
| ❌️ | `/commitment-of-traders-list`     |

### Economics

Copied from [/developer/docs/stable#economics](https://site.financialmodelingprep.com/developer/docs/stable#economics)

|    | Endpoint               |
|:--:|------------------------|
| ✅️ | `/treasury-rates`      |
| ❌️ | `/economic-indicators` |
| ❌️ | `/economic-calendar`   |
| ❌️ | `/market-risk-premium` |

### ESG

Copied from [/developer/docs/stable#esg](https://site.financialmodelingprep.com/developer/docs/stable#esg)

|    | Endpoint           |
|:--:|--------------------|
| ❌️ | `/esg-disclosures` |
| ❌️ | `/esg-ratins`      |
| ❌️ | `/esg-benchmark`   |

Copy of ### Discounted Cash Flow

Copied
from [/developer/docs/stable#discounted-cash-flow](https://site.financialmodelingprep.com/developer/docs/stable#discounted-cash-flow)

|    | Endpoint                               |
|:--:|----------------------------------------|
| ❌️ | `/discounted-cash-flow`                |
| ❌️ | `/levered-discounted-cash-flow`        |
| ❌️ | `/custom-discounted-cash-flow`         |
| ❌️ | `/custom-levered-discounted-cash-flow` |

### Statements

Copied from [/developer/docs/stable#statements](https://site.financialmodelingprep.com/developer/docs/stable#statements)

|    | Endpoint                                |
|:--:|-----------------------------------------|
| ❌️ | `/latest-financial-statements`          |
| ❌️ | `/financial-statement-full-as-reported` |
| ✅️ | `/income-statement`                     |
| ✅️ | `/income-statement-ttm`                 |
| ✅️ | `/income-statement-growth`              |
| ✅️ | `/income-statement-growth-as-reported`  |
| ✅️ | `/balance-sheet-statement`              |
| ✅️ | `/balance-sheet-statement-ttm`          |
| ✅️ | `/balance-sheet-statement-growth`       |
| ✅️ | `/balance-sheet-statement-as-reported`  |
| ✅️ | `/cash-flow-statement`                  |
| ✅️ | `/cash-flow-statement-ttm`              |
| ✅️ | `/cash-flow-statement-growth`           |
| ✅️ | `/cash-flow-statement-as-reported`      |
| ✅️ | `/financial-growth`                     |
| ✅️ | `/ratios`                               |
| ✅️ | `/ratios-ttm`                           |
| ✅️ | `/key-metrics`                          |
| ✅️ | `/key-metrics-ttm`                      |
| ❌️ | `/financial-scores`                     |
| ❌️ | `/owner-earnings`                       |
| ✅️ | `/enterprise-values`                    |
| ✅️ | `/revenue-product-segmentation`         |
| ✅️ | `/revenue-geographic-segmentation`      |

### Form 13F

Copied from [/developer/docs/stable#form-13f](https://site.financialmodelingprep.com/developer/docs/stable#form-13f)

|    | Endpoint                                              |
|:--:|-------------------------------------------------------|
| ❌️ | `/institutional-ownership/latest`                     |
| ❌️ | `/institutional-ownership/extract`                    |
| ❌️ | `/institutional-ownership/extract-analytics/holder`   |
| ❌️ | `/institutional-ownership/holder-performance-summary` |
| ❌️ | `/institutional-ownership/holder-industry-breakdown`  |
| ❌️ | `/institutional-ownership/symbols-positions-summary`  |
| ❌️ | `/institutional-ownership/industry-summary`           |

### Indexes

Copied from [/developer/docs/stable#indexes](https://site.financialmodelingprep.com/developer/docs/stable#indexes)

|    | Endpoint                           |
|:--:|------------------------------------|
| ❌️ | `/index-list`                      |
| ✅️ | `/quote`                           |
| ✅️ | `/quote-short`                     |
| ❌️ | `/batch-index-quotes`              |
| ✅️ | `/historical-price-eod/light`      |
| ✅️ | `/historical-price-eod/full`       |
| ✅️ | `/historical-chart/1min`           |
| ✅️ | `/historical-chart/5min`           |
| ✅️ | `/historical-chart/1hour`          |
| ❌️ | `/sp500-constituent`               |
| ❌️ | `/nasdaq-constituent`              |
| ❌️ | `/dowjones-constituent`            |
| ❌️ | `/historical-sp500-constituent`    |
| ❌️ | `/histoircal-nasdaq-constituent`   |
| ❌️ | `/historical-dowjones-constituent` |

### Insider Trades

Copied
from [/developer/docs/stable#insider-trades](https://site.financialmodelingprep.com/developer/docs/stable#insider-trades)

|    | Endpoint                                |
|:--:|-----------------------------------------|
| ❌️ | `/insider-trading`                      |
| ❌️ | `/insider-trading/reporting-name`       |
| ❌️ | `/insider-trading/search`               |
| ❌️ | `/insider-trading/statistics`           |
| ❌️ | `/insider-trading-transaction-type`     |
| ❌️ | `/acquisitions-of-beneficial-ownership` |

### Market Performance

Copied
from [/developer/docs/stable#market-performance](https://site.financialmodelingprep.com/developer/docs/stable#market-performance)

|    | Endpoint                           |
|:--:|------------------------------------|
| ❌️ | `/sector-performance-snapshot`     |
| ❌️ | `/industry-performance-snapshot`   |
| ❌️ | `/historical-sector-performance`   |
| ❌️ | `/historical-industry-performance` |
| ❌️ | `/sector-pe-snapshot`              |
| ❌️ | `/industry-pe-snapshot`            |
| ❌️ | `/historical-sector-pe`            |
| ❌️ | `/historical-industry-pe`          |
| ❌️ | `/biggest-gainers`                 |
| ❌️ | `/biggest-losers`                  |
| ❌️ | `/most-actives`                    |

### Market Hours

Copied
from [/developer/docs/stable#market-hours](https://site.financialmodelingprep.com/developer/docs/stable#market-hours)

|    | Endpoint                     |
|:--:|------------------------------|
| ❌️ | `/exchange-market-hours`     |
| ❌️ | `/holidays-by-exchange`      |
| ❌️ | `/all-exchange-market-hours` |

### ETF & Mutual Funds

Copied from [/developer/docs/stable#holdings](https://site.financialmodelingprep.com/developer/docs/stable#holdings)

|    | Endpoint                           |
|:--:|------------------------------------|
| ✅️ | `/etf/holdings`                    |
| ✅️ | `/etf/info`                        |
| ✅️ | `/etf/country-weightings`          |
| ✅️ | `/etf/asset-exposure`              |
| ✅️ | `/etf/sector-weightings`           |
| ❌️ | `/funds/disclosure`                |
| ❌️ | `/funds/disclosure-holders-latest` |
| ❌️ | `/funds/disclosure-holders-search` |
| ❌️ | `/funds/disclosure-dates`          |

### Commodities

Copied
from [/developer/docs/stable#commodities](https://site.financialmodelingprep.com/developer/docs/stable#commodities)

|    | Endpoint                      |
|:--:|-------------------------------|
| ❌️ | `/commodities-list`           |
| ✅️ | `/quote`                      |
| ✅️ | `/quote-short`                |
| ❌️ | `/batch-commodities-quotes`   |
| ✅️ | `/historical-price-eod/light` |
| ✅️ | `/historical-price-eod/full`  |
| ✅️ | `/historical-chart/1min`      |
| ✅️ | `/historical-chart/5min`      |
| ✅️ | `/historical-chart/1hour`     |

### Fundraisers

Copied
from [/developer/docs/stable#fundraisers](https://site.financialmodelingprep.com/developer/docs/stable#fundraisers)

|    | Endpoint                         |
|:--:|----------------------------------|
| ❌️ | `/crowdfunding-offerings-latest` |
| ❌️ | `/crowdfunding-offerings-search` |
| ❌️ | `/crowdfunding-offerings`        |
| ❌️ | `/fundraising-latest`            |
| ❌️ | `/fundraising-search`            |
| ❌️ | `/fundraising`                   |

### Crypto

Copied from [/developer/docs/stable#crypto](https://site.financialmodelingprep.com/developer/docs/stable#crypto)

|    | Endpoint                      |
|:--:|-------------------------------|
| ❌️ | `/cryptocurrency-list`        |
| ❌️ | `/batch-crypto-quotes`        |
| ✅️ | `/quote`                      |
| ✅️ | `/quote-short`                |
| ✅️ | `/historical-price-eod/light` |
| ✅️ | `/historical-price-eod/full`  |
| ✅️ | `/historical-chart/1min`      |
| ✅️ | `/historical-chart/5min`      |
| ✅️ | `/historical-chart/1hour`     |

### News

Copied from [/developer/docs/stable#news](https://site.financialmodelingprep.com/developer/docs/stable#news)

|    | Endpoint                      |
|:--:|-------------------------------|
| ❌️ | `/fmp-articles`               |
| ❌️ | `/news/general-latest`        |
| ❌️ | `/news/press-releases`        |
| ❌️ | `/news/press-releases-latest` |
| ✅️ | `/news/stock`                 |
| ❌️ | `/news/stock-latest`          |
| ✅️ | `/news/crypto`                |
| ❌️ | `/news/crypto-latest`         |
| ✅️ | `/news/forex`                 |
| ❌️ | `/news/forex-latest`          |

### Technical Indicators

Copied
from [/developer/docs/stable#technical-indicators](https://site.financialmodelingprep.com/developer/docs/stable#technical-indicators)

|    | Endpoint                                  |
|:--:|-------------------------------------------|
| ❌️ | `/technical-indicators-sma`               |
| ❌️ | `/technical-indicators-ema`               |
| ❌️ | `/technical-indicators-wma`               |
| ❌️ | `/technical-indicators-dema`              |
| ❌️ | `/technical-indicators-tema`              |
| ❌️ | `/technical-indicators-rsi`               |
| ❌️ | `/technical-indicators-standarddeviation` |
| ❌️ | `/technical-indicators-williams`          |
| ❌️ | `/technical-indicators-adx`               |

### Quote

Copied from [/developer/docs/stable#quote](https://site.financialmodelingprep.com/developer/docs/stable#quote)

|    | Endpoint                   |
|:--:|----------------------------|
| ✅️ | `/quote`                   |
| ✅️ | `/quote-short`             |
| ❌️ | `/aftermarket-trade`       |
| ❌️ | `/aftermarket-quote`       |
| ❌️ | `/stock-price-change`      |
| ❌️ | `/batch-quote`             |
| ❌️ | `/batch-quote-short`       |
| ❌️ | `/batch-aftermarket-trade` |
| ❌️ | `/batch-aftermarket-quote` |
| ❌️ | `/batch-exchange-quote`    |
| ❌️ | `/batch-mutaulfund-quotes` |
| ❌️ | `/batch-etf-quotes`        |
| ❌️ | `/batch-commodity-quotes`  |
| ❌️ | `/batch-crypto-quotes`     |
| ❌️ | `/batch-forex-quotes`      |
| ❌️ | `/batch-index-quotes`      |

### SEC Filings

Copied
from [/developer/docs/stable#sec-filings](https://site.financialmodelingprep.com/developer/docs/stable#sec-filings)

|    | Endpoint                                   |
|:--:|--------------------------------------------|
| ❌️ | `/sec-filings-8k`                          |
| ❌️ | `/sec-filings-financials`                  |
| ❌️ | `/sec-filings-search/form-type`            |
| ❌️ | `/sec-filings-search/cik`                  |
| ❌️ | `/sec-filings-company-search/name`         |
| ✅️ | `/sec-filings-company-search/symbol`       |
| ❌️ | `/sec-filings-company-search/cik`          |
| ❌️ | `/sec-profile`                             |
| ❌️ | `/standard-industrial-classification-list` |
| ❌️ | `/industry-classification-search`          |
| ❌️ | `/all-industry-classification`             |

### Earnings Transcript

Copied
from [/developer/docs/stable#earnings-transcript](https://site.financialmodelingprep.com/developer/docs/stable#earnings-transcript)

|    | Endpoint                          |
|:--:|-----------------------------------|
| ✅️ | `/earning-call-transcript-latest` |
| ✅️ | `/earning-call-transcript`        |
| ✅️ | `/earning-call-transcript-dates`  |
| ✅️ | `/earnings-transcript-list`       |

### Senate

Copied from [/developer/docs/stable#senate](https://site.financialmodelingprep.com/developer/docs/stable#senate)

|    | Endpoint                 |
|:--:|--------------------------|
| ❌️ | `/senate-latest`         |
| ❌️ | `/senate-trades`         |
| ❌️ | `/senate-trades-by-name` |
| ❌️ | `/house-latest`          |
| ❌️ | `/house-trades`          |
| ❌️ | `/house-trades-by-name`  |

### Bulk

Copied from [/developer/docs/stable#bulk](https://site.financialmodelingprep.com/developer/docs/stable#bulk)

|    | Endpoint                               |
|:--:|----------------------------------------|
| ✅️ | `/profile-bulk`                        |
| ❌️ | `/rating-bulk`                         |
| ❌️ | `/dcf-bulk`                            |
| ❌️ | `/scores-bulk`                         |
| ❌️ | `/price-target-summary-bulk`           |
| ❌️ | `/etf-holder-bulk`                     |
| ❌️ | `/upgrades-downgrades-consensus-bulk`  |
| ✅️ | `/key-metrics-ttm-bulk`                |
| ❌️ | `/ratios-ttm-bulk`                     |
| ❌️ | `/ratios-ttm-bulk`                     |
| ❌️ | `/peers-bulk`                          |
| ❌️ | `/earnings-surprises-bulk`             |
| ❌️ | `/income-statement-bulk`               |
| ❌️ | `/income-statement-growth-bulk`        |
| ✅️ | `/balance-sheet-statement-bulk`        |
| ❌️ | `/balance-sheet-statement-growth-bulk` |
| ❌️ | `/cash-flow-statement-bulk`            |
| ❌️ | `/cash-flow-statement-growth-bulk`     |
| ❌️ | `/eod-bulk`                            |

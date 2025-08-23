package dev.sorn.fmp4j;

import dev.sorn.fmp4j.models.FmpEtfInfo;
import dev.sorn.fmp4j.models.FmpEtfInfo.SectorExposure;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

public interface EtfInfoTestData {
    default FmpEtfInfo anEtfInfo() {
        return new FmpEtfInfo(
                "SPY",
                "SPDR S&P 500 ETF Trust",
                "The SPDR S&P 500 ETF Trust seeks to provide investment results that, before expenses, correspond generally to the price and yield performance of the S&P 500 Index (the “Index”)The S&P 500 Index is a diversified large cap U.S. index that holds companies across all eleven GICS sectorsLaunched in January 1993, SPY was the very first exchange traded fund listed in the United States",
                "US78462F1030",
                "Equity",
                "78462F103",
                "US",
                "https://www.ssga.com/us/en/institutional/etfs/spdr-sp-500-etf-trust-spy",
                "SPDR",
                0.0945,
                661539920000L,
                73651172L,
                LocalDate.parse("1993-01-22"),
                645.07,
                "USD",
                503,
                ZonedDateTime.parse("2025-08-17T21:15:43.242Z"),
                List.of(
                        new SectorExposure("Basic Materials", 1.63),
                        new SectorExposure("Communication Services", 9.92),
                        new SectorExposure("Consumer Cyclical", 10.52),
                        new SectorExposure("Consumer Defensive", 5.35),
                        new SectorExposure("Energy", 2.91),
                        new SectorExposure("Financial Services", 13.26),
                        new SectorExposure("Healthcare", 8.8),
                        new SectorExposure("Industrials", 7.77),
                        new SectorExposure("Real Estate", 1.99),
                        new SectorExposure("Technology", 35.4),
                        new SectorExposure("Utilities", 2.45)));
    }
}

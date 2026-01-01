package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpCurrency.USD;
import static com.marketdataapi.fmp4j.types.FmpIsin.isin;
import static com.marketdataapi.fmp4j.types.FmpSector.BASIC_MATERIALS;
import static com.marketdataapi.fmp4j.types.FmpSector.COMMUNICATION_SERVICES;
import static com.marketdataapi.fmp4j.types.FmpSector.CONSUMER_CYCLICAL;
import static com.marketdataapi.fmp4j.types.FmpSector.CONSUMER_DEFENSIVE;
import static com.marketdataapi.fmp4j.types.FmpSector.ENERGY;
import static com.marketdataapi.fmp4j.types.FmpSector.FINANCIAL_SERVICES;
import static com.marketdataapi.fmp4j.types.FmpSector.HEALTHCARE;
import static com.marketdataapi.fmp4j.types.FmpSector.INDUSTRIALS;
import static com.marketdataapi.fmp4j.types.FmpSector.REAL_ESTATE;
import static com.marketdataapi.fmp4j.types.FmpSector.TECHNOLOGY;
import static com.marketdataapi.fmp4j.types.FmpSector.UTILITIES;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;

import com.marketdataapi.fmp4j.models.FmpEtfInfo;
import com.marketdataapi.fmp4j.models.FmpEtfInfo.SectorExposure;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

public interface EtfInfoTestData {
    default FmpEtfInfo anEtfInfo() {
        return new FmpEtfInfo(
                symbol("SPY"),
                "SPDR S&P 500 ETF Trust",
                "The SPDR S&P 500 ETF Trust seeks to provide investment results that, before expenses, correspond generally to the price and yield performance of the S&P 500 Index (the “Index”)The S&P 500 Index is a diversified large cap U.S. index that holds companies across all eleven GICS sectorsLaunched in January 1993, SPY was the very first exchange traded fund listed in the United States",
                isin("US78462F1030"),
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
                USD,
                503,
                ZonedDateTime.parse("2025-08-17T21:15:43.242Z"),
                List.of(
                        new SectorExposure(BASIC_MATERIALS, 1.63),
                        new SectorExposure(COMMUNICATION_SERVICES, 9.92),
                        new SectorExposure(CONSUMER_CYCLICAL, 10.52),
                        new SectorExposure(CONSUMER_DEFENSIVE, 5.35),
                        new SectorExposure(ENERGY, 2.91),
                        new SectorExposure(FINANCIAL_SERVICES, 13.26),
                        new SectorExposure(HEALTHCARE, 8.8),
                        new SectorExposure(INDUSTRIALS, 7.77),
                        new SectorExposure(REAL_ESTATE, 1.99),
                        new SectorExposure(TECHNOLOGY, 35.4),
                        new SectorExposure(UTILITIES, 2.45)));
    }
}

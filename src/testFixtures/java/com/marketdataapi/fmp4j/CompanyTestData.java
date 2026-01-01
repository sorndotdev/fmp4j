package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpCik.cik;
import static com.marketdataapi.fmp4j.types.FmpCurrency.USD;
import static com.marketdataapi.fmp4j.types.FmpCusip.cusip;
import static com.marketdataapi.fmp4j.types.FmpExchange.NASDAQ;
import static com.marketdataapi.fmp4j.types.FmpIndustry.CONSUMER_ELECTRONICS;
import static com.marketdataapi.fmp4j.types.FmpIsin.isin;
import static com.marketdataapi.fmp4j.types.FmpSector.sector;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;

import com.marketdataapi.fmp4j.models.FmpCompany;
import java.time.LocalDate;

public interface CompanyTestData {
    default FmpCompany aCompany() {
        return new FmpCompany(
                symbol("AAPL"),
                232.8,
                3500823120000L,
                1.24,
                0.99,
                "164.08-260.1",
                4.79,
                2.1008,
                0L,
                50542058L,
                "Apple Inc.",
                USD,
                cik("0000320193"),
                isin("US0378331005"),
                cusip("037833100"),
                NASDAQ,
                CONSUMER_ELECTRONICS,
                "https://www.apple.com",
                "Apple Inc. designs, manufactures, and markets smartphones, personal computers, tablets, wearables, and accessories worldwide. The company offers iPhone, a line of smartphones; Mac, a line of personal computers; iPad, a line of multi-purpose tablets; and wearables, home, and accessories comprising AirPods, Apple TV, Apple Watch, Beats products, and HomePod. It also provides AppleCare support and cloud services; and operates various platforms, including the App Store that allow customers to discov...",
                "Mr. Timothy D. Cook",
                sector("Technology"),
                "US",
                "164000",
                "(408) 996-1010",
                "One Apple Park Way",
                "Cupertino",
                "CA",
                "95014",
                "https://images.financialmodelingprep.com/symbol/AAPL.png",
                LocalDate.parse("1980-12-12"),
                false,
                false,
                true,
                false,
                false);
    }
}

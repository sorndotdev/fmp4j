package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpPeriod.FY;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;
import static com.marketdataapi.fmp4j.types.FmpYear.year;

import com.marketdataapi.fmp4j.models.FmpRevenueProductSegmentation;
import java.time.LocalDate;
import java.util.Map;

public interface RevenueProductSegmentationTestData {
    default FmpRevenueProductSegmentation aRevenueProductSegmentation() {
        return new FmpRevenueProductSegmentation(
                symbol("AAPL"),
                year(2024),
                FY,
                null,
                LocalDate.parse("2024-09-28"),
                Map.of(
                        "Mac", 29984000000L,
                        "Service", 96169000000L,
                        "Wearables, Home and Accessories", 37005000000L,
                        "iPad", 26694000000L,
                        "iPhone", 201183000000L));
    }
}

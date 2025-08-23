package dev.sorn.fmp4j;

import dev.sorn.fmp4j.models.FmpRevenueGeographicSegmentation;
import java.time.LocalDate;
import java.util.Map;

public interface RevenueGeographicSegmentationTestData {
    default FmpRevenueGeographicSegmentation aRevenueGeographicSegmentation() {
        return new FmpRevenueGeographicSegmentation(
                "AAPL",
                2024,
                "FY",
                null,
                LocalDate.parse("2024-09-28"),
                Map.of(
                        "Americas Segment",
                        167045000000L,
                        "Europe Segment",
                        101328000000L,
                        "Greater China Segment",
                        66952000000L,
                        "Japan Segment",
                        25052000000L,
                        "Rest of Asia Pacific Segment",
                        30658000000L));
    }
}

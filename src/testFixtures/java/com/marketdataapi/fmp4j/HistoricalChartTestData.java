package com.marketdataapi.fmp4j;

import com.marketdataapi.fmp4j.models.FmpHistoricalChart;
import java.time.LocalDateTime;

public interface HistoricalChartTestData {
    default FmpHistoricalChart aHistoricalChart() {
        return new FmpHistoricalChart(
                LocalDateTime.parse("2024-01-02T15:30:00"), 184.42, 184.415, 185.93, 185.47, 12927017L);
    }
}

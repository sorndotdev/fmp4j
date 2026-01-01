package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpCurrency.USD;
import static com.marketdataapi.fmp4j.types.FmpPeriod.FY;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;
import static com.marketdataapi.fmp4j.types.FmpYear.year;

import com.marketdataapi.fmp4j.models.FmpKeyMetric;
import java.time.LocalDate;

public interface KeyMetricTestData {
    default FmpKeyMetric anAnnualKeyMetric() {
        return new FmpKeyMetric(
                symbol("AAPL"),
                LocalDate.parse("2024-09-28"),
                year("2024"),
                FY,
                USD,
                3495160329570L,
                3584276329570L,
                9.166126637180815,
                30.30997961650346,
                32.94159686022039,
                26.617033362072167,
                0.6617803224393106,
                0.8673125765340832,
                1.2615643936161134,
                22.587017267616833,
                -12.352478525015636,
                0.7590881483581001,
                1.0021831580314244,
                -23405000000L,
                22275000000L,
                0.25682503150857583,
                0.3434290787011036,
                0.25682503150857583,
                1.6459350307287095,
                0.4430708117427921,
                0.6533607652660827,
                0.026818798327209237,
                0.03113076074921754,
                0.07988736110406414,
                0.8254259501965924,
                0.02415896275269477,
                .0,
                0.08022299794136074,
                0.02988990755303234,
                .0,
                63614000000L,
                65785500000L,
                6808500000L,
                61.83255974529134,
                119.65847721913745,
                12.642570548414087,
                74.47513029370543,
                -45.18334692543202,
                19691000000L,
                117192805288L,
                56950000000L,
                -155043000000L);
    }
}

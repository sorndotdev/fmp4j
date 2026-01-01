package com.marketdataapi.fmp4j;

import com.marketdataapi.fmp4j.models.FmpTreasuryRate;
import java.time.LocalDate;

public interface TreasuryRatesTestData {
    default FmpTreasuryRate aFmpTreasuryRate() {
        return new FmpTreasuryRate(
                LocalDate.parse("2025-07-14"),
                1.27168,
                -1.70047,
                6.35536,
                16.6385,
                -7.24496,
                -6.59832,
                0.40557221,
                36.19566,
                80.96297,
                783.47556,
                177352.27892,
                1.3);
    }
}

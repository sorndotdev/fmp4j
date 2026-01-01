package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpCik.cik;
import static com.marketdataapi.fmp4j.types.FmpCurrency.USD;
import static com.marketdataapi.fmp4j.types.FmpPeriod.FY;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;
import static com.marketdataapi.fmp4j.types.FmpYear.year;

import com.marketdataapi.fmp4j.models.FmpBalanceSheetStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface BalanceSheetStatementTestData {
    default FmpBalanceSheetStatement anAnnualBalanceSheetStatement() {
        return new FmpBalanceSheetStatement(
                LocalDate.parse("2024-09-28"),
                symbol("AAPL"),
                USD,
                cik("0000320193"),
                LocalDate.parse("2024-11-01"),
                LocalDateTime.of(LocalDate.parse("2024-11-01"), LocalTime.of(6, 1, 36)),
                year("2024"),
                FY,
                29943000000L,
                35228000000L,
                65171000000L,
                66243000000L,
                33410000000L,
                32833000000L,
                7286000000L,
                0L,
                14287000000L,
                152987000000L,
                45680000000L,
                0L,
                0L,
                0L,
                91479000000L,
                19499000000L,
                55335000000L,
                211993000000L,
                0L,
                364980000000L,
                95561000000L,
                68960000000L,
                26601000000L,
                0L,
                20879000000L,
                1632000000L,
                26601000000L,
                8249000000L,
                50071000000L,
                176392000000L,
                85750000000L,
                10798000000L,
                0L,
                0L,
                35090000000L,
                131638000000L,
                0L,
                12430000000L,
                308030000000L,
                0L,
                0L,
                83276000000L,
                -19154000000L,
                0L,
                -7172000000L,
                0L,
                56950000000L,
                56950000000L,
                0L,
                364980000000L,
                126707000000L,
                119059000000L,
                89116000000L);
    }
}

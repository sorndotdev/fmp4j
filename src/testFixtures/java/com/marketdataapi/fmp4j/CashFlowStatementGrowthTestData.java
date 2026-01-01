package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpCurrency.USD;
import static com.marketdataapi.fmp4j.types.FmpPeriod.FY;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;
import static com.marketdataapi.fmp4j.types.FmpYear.year;

import com.marketdataapi.fmp4j.models.FmpCashFlowStatementGrowth;
import java.time.LocalDate;

public interface CashFlowStatementGrowthTestData {
    default FmpCashFlowStatementGrowth aCashFlowStatementGrowth() {
        return new FmpCashFlowStatementGrowth(
                symbol("AAPL"),
                LocalDate.parse("2024-09-28"),
                year("2024"),
                FY,
                USD,
                -0.033599670086086914,
                -0.006424168764649709,
                0.0,
                0.07892550540016616,
                1.555116314429071,
                -11.335731414868105,
                0.3535228677379481,
                4.1868713605082055,
                2.4402563136072373,
                -0.017512348450830714,
                0.06975566069312394,
                0.13796879277306323,
                0.0,
                -0.6486294175448107,
                0.3698202750801951,
                0.02169035153328347,
                -0.2078272604588394,
                -0.012662502110417018,
                0.0,
                -0.2243584784010316,
                -0.013910149750415973,
                0.03493013972055888,
                -0.12439163778482412,
                0.0,
                -1.1378472222222222,
                -0.02583205908188828,
                0.23061216319013492,
                0.06975566069312394,
                0.13796879277306323,
                0.092615279562982,
                0.3942026057973942,
                -0.6812426135404356,
                1.995475113122172,
                -0.2243584784010316,
                -0.013910149750415973,
                0.3973981476524439,
                -1.0);
    }
}

package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpCurrency.USD;
import static com.marketdataapi.fmp4j.types.FmpPeriod.FY;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;
import static com.marketdataapi.fmp4j.types.FmpYear.year;

import com.marketdataapi.fmp4j.models.FmpIncomeStatementGrowth;
import java.time.LocalDate;

public interface IncomeStatementGrowthTestData {
    default FmpIncomeStatementGrowth anIncomeStatementGrowth() {
        return new FmpIncomeStatementGrowth(
                symbol("AAPL"),
                LocalDate.parse("2024-09-28"),
                year("2024"),
                FY,
                USD,
                0.020219940775141214,
                -0.017675600199872046,
                0.06819471705252206,
                0.04776303446712012,
                0.04863780712017383,
                .0,
                .0,
                -1.,
                0.04776924900176856,
                -0.004331112631234571,
                -1.,
                -1.,
                -0.006424168764649709,
                0.07026704816404387,
                0.07799581805933456,
                0.08571604417246959,
                0.7770145152619318,
                -0.033599670086086914,
                -0.008116883116883088,
                -0.008156606851549727,
                -0.02543458616683152,
                -0.02557791606880283,
                0.0471407082579099,
                1.,
                1.,
                1.4761061946902654,
                -0.033599670086086914,
                .0,
                .0);
    }
}

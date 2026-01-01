package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpCik.cik;
import static com.marketdataapi.fmp4j.types.FmpFormType.formType;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;

import com.marketdataapi.fmp4j.models.FmpSecFilingsSearchBySymbol;
import java.time.LocalDate;
import java.time.LocalTime;

public interface SecFilingsSearchBySymbolTestData {
    default FmpSecFilingsSearchBySymbol aSecFilingsSearchBySymbol() {
        return new FmpSecFilingsSearchBySymbol(
                symbol("AAPL"),
                cik("0000320193"),
                LocalDate.parse("2024-12-18").atTime(LocalTime.parse("00:00:00")),
                LocalDate.parse("2024-12-18").atTime(LocalTime.parse("18:30:20")),
                formType("4"),
                "https://www.sec.gov/Archives/edgar/data/320193/000032019324000132/0000320193-24-000132-index.htm",
                "https://www.sec.gov/Archives/edgar/data/320193/000032019324000132/xslF345X05/wk-form4_1734564614.xml");
    }
}

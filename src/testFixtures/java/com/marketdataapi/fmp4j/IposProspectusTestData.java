package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpCik.cik;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;

import com.marketdataapi.fmp4j.models.FmpIposProspectus;
import java.time.LocalDate;

public interface IposProspectusTestData {
    default FmpIposProspectus anIpoProspectus() {
        return new FmpIposProspectus(
                symbol("ATAK"),
                LocalDate.parse("2025-02-03"),
                LocalDate.parse("2025-02-03"),
                LocalDate.parse("2022-03-20"),
                cik("0001883788"),
                0.78,
                4649936.72,
                0.04,
                254909.67,
                0.74,
                4395207.05,
                "424B4",
                "https://www.sec.gov/Archives/edgar/data/1883788/000149315225004604/form424b4.htm");
    }
}

package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.types.FmpCik.cik;
import static com.marketdataapi.fmp4j.types.FmpSymbol.symbol;

import com.marketdataapi.fmp4j.models.FmpIposDisclosure;
import java.time.LocalDate;

public interface IposDisclosureTestData {
    default FmpIposDisclosure anIpoDisclosureRecord() {
        return new FmpIposDisclosure(
                symbol("SCHM"),
                LocalDate.parse("2025-02-03"),
                LocalDate.parse("2025-02-03"),
                LocalDate.parse("2025-02-03"),
                cik("0001454889"),
                "CERT",
                "https://www.sec.gov/Archives/edgar/data/1454889/000114336225000044/SCCR020325.pdf");
    }
}

package com.marketdataapi.fmp4j.types;

import static com.marketdataapi.fmp4j.types.FmpSector.sector;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.marketdataapi.fmp4j.exceptions.FmpInvalidSectorException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FmpSectorTest {
    @ParameterizedTest
    @ValueSource(
            strings = {
                "Basic Materials",
                "Communication Services",
                "Consumer Cyclical",
                "Consumer Defensive",
                "Energy",
                "Financial Services",
                "Healthcare",
                "Industrials",
                "Real Estate",
                "Technology",
                "Utilities",
            })
    void valid_sectors(String readable) {
        // given // when
        var sector = sector(readable);

        // then
        assertEquals(readable, sector.value());
        assertEquals(readable, sector.toString());
    }

    @Test
    void invalid_sector() {
        // given
        var invalid = "Invalid Sector";

        // when // then
        var e = assertThrows(FmpInvalidSectorException.class, () -> sector(invalid));
        assertEquals("[Invalid Sector] is not a valid sector", e.getMessage());
    }
}

package com.marketdataapi.fmp4j.types;

import static com.marketdataapi.fmp4j.types.FmpIndustry.industry;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.marketdataapi.fmp4j.exceptions.FmpInvalidIndustryException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class FmpIndustryTest {

    // --- Parameterized test with all industries ---
    @ParameterizedTest(name = "industry should resolve correctly for {0}")
    @MethodSource("allIndustryLabels")
    void testAllIndustries(String label) {
        // given
        var industry = industry(label);

        // when
        var labelValue = industry.value();

        // then
        assertEquals(label, labelValue, "Industry enum should map back to the same label");
    }

    static Stream<String> allIndustryLabels() {
        return Stream.of(FmpIndustry.values()).map(FmpIndustry::value);
    }

    // --- Some invalid checks ---
    @ParameterizedTest
    @DisplayName("industry throws exception for invalid inputs")
    @ValueSource(strings = {"NotAnIndustry", "Steeel", ""})
    void testInvalidIndustries(String label) {
        // when
        Executable invalid = () -> industry(label);

        // then
        assertAll(() -> assertThrows(FmpInvalidIndustryException.class, invalid));
    }

    // toString test
    @Test
    void test_to_string() {
        // given
        var industry = FmpIndustry.STEEL;

        // then
        assertEquals("Steel", industry.toString());
    }
}

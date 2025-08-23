package dev.sorn.fmp4j.types;

import static dev.sorn.fmp4j.types.FmpStructure.structure;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.sorn.fmp4j.exceptions.FmpInvalidStructureException;
import org.junit.jupiter.api.Test;

class FmpStructureTest {
    @Test
    void valid_structure() {
        // given
        var structureValue = "flat";

        // when
        var structure = structure(structureValue);

        // then
        assertEquals(structureValue, structure.value());
        assertEquals(structureValue, structure.toString());
    }

    @Test
    void invalid_structure() {
        // given
        var invalidStructureValue = "unknown";

        // when
        var e = assertThrows(FmpInvalidStructureException.class, () -> structure(invalidStructureValue));
        assertEquals("[unknown] is not a valid structure", e.getMessage());
    }
}

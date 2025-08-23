package dev.sorn.fmp4j.models;

import static dev.sorn.fmp4j.TestUtils.deserialize;
import static dev.sorn.fmp4j.TestUtils.serialize;
import static dev.sorn.fmp4j.TestUtils.verifySerialization;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.EtfCountryWeightingsTestData;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpEtfCountryWeightingTest implements EtfCountryWeightingsTestData {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = anEtfCountryWeighting();

        // when
        var after = (FmpEtfCountryWeighting) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = anEtfCountryWeighting();

        // when // then
        verifySerialization(o);
    }
}

package dev.sorn.fmp4j.models;

import static dev.sorn.fmp4j.TestUtils.deserialize;
import static dev.sorn.fmp4j.TestUtils.serialize;
import static dev.sorn.fmp4j.TestUtils.verifySerialization;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.sorn.fmp4j.EtfAssetExposureTestData;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class FmpEtfAssetExposureTest implements EtfAssetExposureTestData {
    @Test
    void is_serializable() throws IOException, ClassNotFoundException {
        // given
        var before = anEtfAssetExposure();

        // when
        var after = (FmpEtfAssetExposure) deserialize(serialize(before));

        // then
        assertEquals(before, after);
    }

    @Test
    void serializes() throws IOException {
        // given
        var o = anEtfAssetExposure();

        // when // then
        verifySerialization(o);
    }
}

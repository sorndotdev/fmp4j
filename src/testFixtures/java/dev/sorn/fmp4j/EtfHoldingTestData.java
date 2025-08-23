package dev.sorn.fmp4j;

import dev.sorn.fmp4j.models.FmpEtfHolding;
import java.time.LocalDateTime;

public interface EtfHoldingTestData {
    default FmpEtfHolding anEtfHolding() {
        return new FmpEtfHolding(
                "FUSD.L",
                "DLB",
                "DOLBY LABORATORIES A",
                "US25659T1079",
                "25659T107",
                50906.27973141132,
                0.237,
                3754083.598792928,
                LocalDateTime.parse("2025-08-19T12:08:53"));
    }
}

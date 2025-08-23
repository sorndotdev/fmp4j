package dev.sorn.fmp4j.cfg;

import static dev.sorn.fmp4j.cfg.FmpConfigImpl.FMP_CONFIG;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class FmpConfigImplTest {
    @Test
    void load_fmp_api_key_from_properties() {
        // given
        var cfg = (FmpConfig) FMP_CONFIG;

        // when
        var fmpApiKey = cfg.fmpApiKey();

        // then
        assertNotNull(fmpApiKey);
        assertEquals("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6", fmpApiKey);
    }

    @Test
    void load_fmp_base_url_from_properties() {
        // given
        var cfg = (FmpConfig) FMP_CONFIG;

        // when
        var fmpApiKey = cfg.fmpBaseUrl();

        // then
        assertNotNull(fmpApiKey);
        assertEquals("https://financialmodelingprep.com/stable", fmpApiKey);
    }
}

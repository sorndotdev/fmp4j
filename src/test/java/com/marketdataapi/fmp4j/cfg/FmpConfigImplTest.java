package com.marketdataapi.fmp4j.cfg;

import static java.lang.System.clearProperty;
import static java.lang.System.setProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.SAME_THREAD)
class FmpConfigImplTest {
    private FmpConfig config;

    @Test
    void load_fmp_api_key_from_env() {
        // given
        setProperty(FmpConfigImpl.FMP4J_API_KEY_ENV, "ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6");
        setProperty(FmpConfigImpl.FMP4J_BASE_URL_ENV, "https://financialmodelingprep.com/stable");

        // when
        var config = new FmpConfigImpl();
        var fmpApiKey = config.fmpApiKey();

        // then
        assertNotNull(fmpApiKey);
        assertEquals("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6", fmpApiKey.value());
        assertEquals("AB****************************y6", fmpApiKey.toString());
    }

    @Test
    void load_fmp_base_url_from_env() {
        // given
        setProperty(FmpConfigImpl.FMP4J_API_KEY_ENV, "ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6");
        setProperty(FmpConfigImpl.FMP4J_BASE_URL_ENV, "https://financialmodelingprep.com/stable");

        // when
        var config = new FmpConfigImpl();
        var fmpBaseUrl = config.fmpBaseUrl();

        // then
        assertNotNull(fmpBaseUrl);
        assertEquals("https://financialmodelingprep.com/stable", fmpBaseUrl);
    }

    @Test
    void fail_when_api_key_missing() {
        // given
        clearProperty(FmpConfigImpl.FMP4J_API_KEY_ENV);
        setProperty(FmpConfigImpl.FMP4J_BASE_URL_ENV, "https://financialmodelingprep.com/stable");

        // when
        assertThrows(FmpConfigException.class, () -> config = new FmpConfigImpl());
    }

    @Test
    void fail_when_base_url_missing() {
        // given
        setProperty(FmpConfigImpl.FMP4J_API_KEY_ENV, "ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6");
        clearProperty(FmpConfigImpl.FMP4J_BASE_URL_ENV);

        // when
        assertThrows(FmpConfigException.class, () -> config = new FmpConfigImpl());
    }
}

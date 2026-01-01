package com.marketdataapi.fmp4j.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.marketdataapi.fmp4j.types.FmpCik;
import com.marketdataapi.fmp4j.types.FmpIsin;
import com.marketdataapi.fmp4j.types.FmpSymbol;

public final class FmpJsonModule extends SimpleModule {

    public static final String FMP_JSON_MODULE_NAME = "FmpJsonModule";

    public FmpJsonModule() {
        super(FMP_JSON_MODULE_NAME);
        this.addDeserializer(FmpCik.class, new FmpJsonBlankAsNullStringDeserializer<>(FmpCik::cik));
        this.addDeserializer(FmpIsin.class, new FmpJsonBlankAsNullStringDeserializer<>(FmpIsin::isin));
        this.addDeserializer(FmpSymbol.class, new FmpSymbolDeserializer());
    }
}

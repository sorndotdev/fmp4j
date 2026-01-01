package com.marketdataapi.fmp4j.types;

import com.marketdataapi.fmp4j.exceptions.FmpInvalidExchangeException;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;

public enum FmpExchange implements FmpValueObject<String> {
    AMEX("New York Stock Exchange Arca", "US", null),
    AMS("Euronext Amsterdam", "NL", ".AS"),
    AQS("Aquis Stock Exchange", "UK", ".AQ"),
    ASX("Australian Securities Exchange", "AU", ".AX"),
    ATH("Athens Stock Exchange", "GR", ".AT"),
    BER("Berlin Stock Exchange", "DE", ".BE"),
    BME("Madrid Stock Exchange", "ES", ".MC"),
    BRU("Euronext Brussels", "BE", ".BR"),
    BSE("Bombay Stock Exchange", "IN", ".BO"),
    BUD("Budapest Stock Exchange", "HU", ".BD"),
    BUE("Buenos Aires Stock Exchange", "AR", ".BA"),
    BVC("Colombia Stock Exchange", "CO", ".CL"),
    CBOE("Chicago Board Options Exchange", "US", null),
    CNQ("Canadian Securities Exchange", "CA", ".CN"),
    CPH("NASDAQ Copenhagen", "DK", ".CO"),
    DFM("Dubai Financial Market", "AE", ".AE"),
    DOH("Qatar Stock Exchange", "QA", ".QA"),
    DUB("Euronext Dublin", "IE", ".IR"),
    DUS("Dusseldorf Stock Exchange", "DE", ".DU"),
    DXE("CBOE Europe", null, ".XD"),
    EGX("Egyptian Exchange", "EG", ".CA"),
    EURONEXT("Euronext", null, null),
    HAM("Hamburg Stock Exchange", "DE", ".HM"),
    HEL("NASDAQ Helsinki", "FI", ".HE"),
    HKSE("Hong Kong Stock Exchange", "HK", ".HK"),
    HOSE("Ho Chi Minh Stock Exchange", "VN", ".VN"),
    ICE("NASDAQ Iceland", "IS", ".IC"),
    IOB("International Order Book", "UK", ".IL"),
    IST("Istanbul Stock Exchange", "TR", ".IS"),
    JKT("Indonesia Stock Exchange", "ID", ".JK"),
    JNB("Johannesburg Stock Exchange", "ZA", ".JO"),
    JPX("Tokyo Stock Exchange", "JP", ".T"),
    KLS("Malaysian Stock Exchange", "MY", ".KL"),
    KOE("KOSDAQ", "KR", ".KQ"),
    KSC("Korea Exchange", "KR", ".KS"),
    KUW("Kuwait Stock Exchange", "KW", ".KW"),
    LIS("Euronext Lisbon", null, ".LS"),
    LSE("London Stock Exchange", "UK", ".L"),
    MCX("Moscow Stock Exchange", "RU", ".ME"),
    MEX("Mexican Stock Exchange", "MX", ".MX"),
    MIL("Italian Stock Exchange", "IT", ".MI"),
    MUN("Munich Stock Exchange", "DE", ".MU"),
    NASDAQ("NASDAQ", "US", null),
    NEO("CBOE CA", "CA", ".NE"),
    NSE("National Stock Exchange of India", "IN", ".NS"),
    NYSE("New York Stock Exchange", "US", null),
    NZE("New Zealand Exchange", "NZ", ".NZ"),
    OSL("Oslo Stock Exchange", "NO", ".OL"),
    OTC("Other OTC", "US", null),
    PAR("Euronext Paris", "FR", ".PA"),
    PRA("Prague Stock Exchange", "CZ", ".PR"),
    RIS("NASDAQ Riga", "LV", ".RG"),
    SAO("B3 S.A.", "BR", ".SA"),
    SAU("Saudi Exchange", "SA", ".SS"),
    SES("Stock Exchange of Singapore", "SG", ".SI"),
    SET("Stock Exchange of Thailand", "TH", ".BK"),
    SGO("Santiago Stock Exchange", "CL", ".SN"),
    SHH("Shanghai Stock Exchange", "CN", ".SS"),
    SHZ("Shenzhen Stock Exchange", "CN", ".SZ"),
    SIX("Swiss Exchange", "CH", ".SW"),
    STO("Stockholm Stock Exchange", "SE", ".ST"),
    STU("Stuttgart Stock Exchange", "DE", ".SG"),
    TAI("Taipei Exchange", "TW", ".TW"),
    TAL("NASDAQ Tallinn", "EE", ".TL"),
    TLV("Tel Aviv Stock Exchange", "IL", ".TA"),
    TSX("Toronto Stock Exchange", "CA", ".TO"),
    TSXV("Toronto Stock Exchange Ventures", "CA", ".V"),
    TWO("Taiwan Stock Exchange", null, ".TWO"),
    VIE("Vienna Stock Exchange", "AT", ".VI"),
    WSE("Warsaw Stock Exchange", "PL", ".WA"),
    XETRA("Deutsche Börse", "DE", ".F");

    private final String fullName;
    private final String countryCode;
    private final String suffixSymbol;

    FmpExchange(String fullName, String countryCode, String suffixSymbol) {
        this.fullName = fullName;
        this.countryCode = countryCode;
        this.suffixSymbol = suffixSymbol;
    }

    public static FmpExchange exchange(String code) {
        try {
            return FmpExchange.valueOf(StringUtils.upperCase(code));
        } catch (IllegalArgumentException e) {
            throw new FmpInvalidExchangeException("[%s] is not a valid exchange", code);
        }
    }

    public String fullName() {
        return fullName;
    }

    public Optional<String> countryCode() {
        return Optional.ofNullable(countryCode);
    }

    public Optional<String> suffixSymbol() {
        return Optional.ofNullable(suffixSymbol);
    }

    @Override
    public String value() {
        return fullName;
    }

    @Override
    public String toString() {
        return value();
    }
}

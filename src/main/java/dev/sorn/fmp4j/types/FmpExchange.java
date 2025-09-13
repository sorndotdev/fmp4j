package dev.sorn.fmp4j.types;

import static java.util.Arrays.stream;

import dev.sorn.fmp4j.exceptions.FmpInvalidExchangeException;

public enum FmpExchange implements FmpValueObject<String> {
    AMEX("AMEX", "New York Stock Exchange Arca", "US", "N/A"),
    AMS("AMS", "Euronext Amsterdam", "NL", ".AS"),
    AQS("AQS", "Aquis Stock Exchange", "UK", ".AQ"),
    ASX("ASX", "Australian Securities Exchange", "AU", ".AX"),
    ATH("ATH", "Athens Stock Exchange", "GR", ".AT"),
    BER("BER", "Berlin Stock Exchange", "DE", ".BE"),
    BME("BME", "Madrid Stock Exchange", "ES", ".MC"),
    BRU("BRU", "Euronext Brussels", "BE", ".BR"),
    BSE("BSE", "Bombay Stock Exchange", "IN", ".BO"),
    BUD("BUD", "Budapest Stock Exchange", "HU", ".BD"),
    BUE("BUE", "Buenos Aires Stock Exchange", "AR", ".BA"),
    BVC("BVC", "Colombia Stock Exchange", "CO", ".CL"),
    CBOE("CBOE", "Chicago Board Options Exchange", "US", "N/A"),
    CNQ("CNQ", "Canadian Securities Exchange", "CA", ".CN"),
    CPH("CPH", "NASDAQ Copenhagen", "DK", ".CO"),
    DFM("DFM", "Dubai Financial Market", "AE", ".AE"),
    DOH("DOH", "Qatar Stock Exchange", "QA", ".QA"),
    DUB("DUB", "Euronext Dublin", "IE", ".IR"),
    DUS("DUS", "Dusseldorf Stock Exchange", "DE", ".DU"),
    DXE("DXE", "CBOE Europe", "", ".XD"),
    EGX("EGX", "Egyptian Exchange", "EG", ".CA"),
    EURONEXT("EURONEXT", "Euronext", "", ""),
    HAM("HAM", "Hamburg Stock Exchange", "DE", ".HM"),
    HEL("HEL", "NASDAQ Helsinki", "FI", ".HE"),
    HKSE("HKSE", "Hong Kong Stock Exchange", "HK", ".HK"),
    HOSE("HOSE", "Ho Chi Minh Stock Exchange", "VN", ".VN"),
    ICE("ICE", "NASDAQ Iceland", "IS", ".IC"),
    IOB("IOB", "International Order Book", "UK", ".IL"),
    IST("IST", "Istanbul Stock Exchange", "TR", ".IS"),
    JKT("JKT", "Indonesia Stock Exchange", "ID", ".JK"),
    JNB("JNB", "Johannesburg Stock Exchange", "ZA", ".JO"),
    JPX("JPX", "Tokyo Stock Exchange", "JP", ".T"),
    KLS("KLS", "Malaysian Stock Exchange", "MY", ".KL"),
    KOE("KOE", "KOSDAQ", "KR", ".KQ"),
    KSC("KSC", "Korea Exchange", "KR", ".KS"),
    KUW("KUW", "Kuwait Stock Exchange", "KW", ".KW"),
    LIS("LIS", "Euronext Lisbon", "", ".LS"),
    LSE("LSE", "London Stock Exchange", "UK", ".L"),
    MCX("MCX", "Moscow Stock Exchange", "RU", ".ME"),
    MEX("MEX", "Mexican Stock Exchange", "MX", ".MX"),
    MIL("MIL", "Italian Stock Exchange", "IT", ".MI"),
    MUN("MUN", "Munich Stock Exchange", "DE", ".MU"),
    NASDAQ("NASDAQ", "NASDAQ", "US", "N/A"),
    NEO("NEO", "CBOE CA", "CA", ".NE"),
    NSE("NSE", "National Stock Exchange of India", "IN", ".NS"),
    NYSE("NYSE", "New York Stock Exchange", "US", "N/A"),
    NZE("NZE", "New Zealand Exchange", "NZ", ".NZ"),
    OSL("OSL", "Oslo Stock Exchange", "NO", ".OL"),
    OTC("OTC", "Other OTC", "US", "N/A"),
    PAR("PAR", "Euronext Paris", "FR", ".PA"),
    PRA("PRA", "Prague Stock Exchange", "CZ", ".PR"),
    RIS("RIS", "NASDAQ Riga", "LV", ".RG"),
    SAO("SAO", "B3 S.A.", "BR", ".SA"),
    SAU("SAU", "Saudi Exchange", "SA", ".SS"),
    SES("SES", "Stock Exchange of Singapore", "SG", ".SI"),
    SET("SET", "Stock Exchange of Thailand", "TH", ".BK"),
    SGO("SGO", "Santiago Stock Exchange", "CL", ".SN"),
    SHH("SHH", "Shanghai Stock Exchange", "CN", ".SS"),
    SHZ("SHZ", "Shenzhen Stock Exchange", "CN", ".SZ"),
    SIX("SIX", "Swiss Exchange", "CH", ".SW"),
    STO("STO", "Stockholm Stock Exchange", "SE", ".ST"),
    STU("STU", "Stuttgart Stock Exchange", "DE", ".SG"),
    TAI("TAI", "Taipei Exchange", "TW", ".TW"),
    TAL("TAL", "NASDAQ Tallinn", "EE", ".TL"),
    TLV("TLV", "Tel Aviv Stock Exchange", "IL", ".TA"),
    TSX("TSX", "Toronto Stock Exchange", "CA", ".TO"),
    TSXV("TSXV", "Toronto Stock Exchange Ventures", "CA", ".V"),
    TWO("TWO", "Taiwan Stock Exchange", "", ".TWO"),
    VIE("VIE", "Vienna Stock Exchange", "AT", ".VI"),
    WSE("WSE", "Warsaw Stock Exchange", "PL", ".WA"),
    XETRA("XETRA", "Deutsche Börse", "DE", ".F");

    private final String shortName;
    private final String name;
    private final String countryCode;
    private final String suffixSymbol;

    FmpExchange(String shortName, String name, String countryCode, String suffixSymbol) {
        this.shortName = shortName;
        this.name = name;
        this.countryCode = countryCode;
        this.suffixSymbol = suffixSymbol;
    }

    public static FmpExchange exchange(String shortName) {
        return stream(FmpExchange.values())
                .filter(exchange -> exchange.shortName.equals(shortName))
                .findFirst()
                .orElseThrow(() -> new FmpInvalidExchangeException("[%s] is not a valid exchange", shortName));
    }

    @Override
    public String value() {
        return shortName;
    }

    @Override
    public String toString() {
        return String.format("%s:%s:%s:%s", shortName, name, countryCode, suffixSymbol);
    }
}

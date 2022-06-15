package com.ithome.web.ExChangeRateController;

public interface ApiService {
    //String getCurrentRate = "exchangerates.asmx";
    String getCurrentRate = "exchangerates.asmx?op=ExchangeRatesLatest";
}

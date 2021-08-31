package com.popspot.challenge.service;

import java.util.List;

public interface ExchangeRatesService {
    Double getExchangeRate(String baseCurr, String targetCurr);

    List<String> getSupportedCurrencies();
}

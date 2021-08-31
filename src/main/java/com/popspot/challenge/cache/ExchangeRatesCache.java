package com.popspot.challenge.cache;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class ExchangeRatesCache {

    private Map<String, Double> currencyRatesMap;

    public ExchangeRatesCache () {
        currencyRatesMap = new HashMap<>();
    }

    public Double getRate (String currency){
        return currencyRatesMap.get(currency);
    }
}
